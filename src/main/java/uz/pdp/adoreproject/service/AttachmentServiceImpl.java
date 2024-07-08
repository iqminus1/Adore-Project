package uz.pdp.adoreproject.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.pdp.adoreproject.entity.Attachment;
import uz.pdp.adoreproject.exceptions.NotFoundException;
import uz.pdp.adoreproject.mapper.AttachmentMapper;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.AttachmentDTO;
import uz.pdp.adoreproject.repository.AttachmentRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
    @Value("${app.base-path}")
    private String BASE_PATH;
    private final AttachmentMapper attachmentMapper;

    @Override
    public void read(Integer id, HttpServletResponse resp) {
        try {
            Attachment attachment = attachmentRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("attachment not found by id -> %s".formatted(id)));
            resp.setContentType(attachment.getContentType());
            Files.copy(Path.of(attachment.getPath()), resp.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public void read(HttpServletResponse resp) {
//        try {
//            List<Attachment> all = attachmentRepository.findAll();
//            if (all.isEmpty()) {
//                throw new NotFoundException("Any attachments not found");
//            }
//            for (Attachment attachment : all) {
//                Path path = Path.of(attachment.getPath());
//                resp.setContentType(attachment.getContentType());
//                Files.copy(path,resp.getOutputStream());
//            }
//        } catch (IOException e) {
//            throw new RuntimeException();
//        }
//    }


    @Override
    public ApiResultDTO<List<AttachmentDTO>> create(HttpServletRequest req) {
        List<AttachmentDTO> attachmentDTOs = new ArrayList<>();
        try {
            Collection<Part> parts = req.getParts();
            if (parts.isEmpty()) {
                throw new RuntimeException(HttpStatus.BAD_REQUEST.name());
            }

            for (Part part : parts) {
                attachmentDTOs.add(createOrUpdate(new Attachment(), false, part));
            }
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
        return ApiResultDTO.success(attachmentDTOs);
    }

    @Override
    public ApiResultDTO<AttachmentDTO> update(Integer id, HttpServletRequest req) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Attachment not found by id -> %s".formatted(id)));
        try {
            Collection<Part> parts = req.getParts();
            if (parts.size() == 1) {
                for (Part part : parts) {
                    return ApiResultDTO.success(createOrUpdate(attachment, true, part));
                }
            }
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException(HttpStatus.BAD_REQUEST.name());
    }

    @Override
    public void delete(Integer id) {
        attachmentRepository.deleteById(id);
    }


    private AttachmentDTO createOrUpdate(Attachment attachment, boolean update, Part part) {
        if (update) {
            try {
                Files.delete(Path.of(attachment.getPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {

            String contentType = part.getContentType();
            String originalName = part.getSubmittedFileName();
            long size = part.getSize();

            String[] split = originalName.split("\\.");
            String s = split[split.length - 1];
            UUID uuid = UUID.randomUUID();

            String name = uuid + "." + s;

            String pathString = BASE_PATH + "/" + name;

            Files.copy(part.getInputStream(), Path.of(pathString));

            attachment.setName(name);
            attachment.setSize(size);
            attachment.setPath(pathString);
            attachment.setContentType(contentType);
            attachment.setOriginalName(originalName);

            attachmentRepository.save(attachment);

            return attachmentMapper.toDTO(attachment);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
