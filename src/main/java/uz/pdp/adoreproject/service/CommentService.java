package uz.pdp.adoreproject.service;

import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.CommentCrudDTO;
import uz.pdp.adoreproject.payload.CommentDTO;

import java.util.List;

public interface CommentService {
    ApiResultDTO<List<CommentDTO>> readByRestaurantId(Integer id);
    ApiResultDTO<CommentDTO> create(CommentCrudDTO crudDTO);
    void delete(Integer id);
}
