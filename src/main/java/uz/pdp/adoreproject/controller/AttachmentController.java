package uz.pdp.adoreproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.AttachmentDTO;
import uz.pdp.adoreproject.service.AttachmentService;
import uz.pdp.adoreproject.utils.AppConstants;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/attachment")
public class AttachmentController {
    public final AttachmentService attachmentService;


//    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).READ_ATTACHMENT.name())")
    @GetMapping("/{id}")
    public void read(@PathVariable Integer id, HttpServletResponse resp) {
        attachmentService.read(id, resp);
    }

//    @GetMapping
//    public void read(HttpServletResponse resp) {
//        attachmentService.read(resp);
//    }

    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).CREATE_ATTACHMENT.name())")
    @PostMapping
    public ApiResultDTO<List<AttachmentDTO>> create(HttpServletRequest req) {
        return attachmentService.create(req);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).UPDATE_ATTACHMENT.name())")
    @PutMapping("/{id}")
    public ApiResultDTO<AttachmentDTO> update(@PathVariable Integer id, HttpServletRequest req) {
        return attachmentService.update(id, req);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).DELETE_ATTACHMENT.name())")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        attachmentService.delete(id);
    }
}
