package uz.pdp.adoreproject.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.AttachmentDTO;

import java.util.List;

public interface AttachmentService {
    void read(Integer id, HttpServletResponse resp);

//    void read(HttpServletResponse resp);

    ApiResultDTO<List<AttachmentDTO>> create(HttpServletRequest req);

    ApiResultDTO<AttachmentDTO> update(Integer id, HttpServletRequest req);

    void delete(Integer id);
}
