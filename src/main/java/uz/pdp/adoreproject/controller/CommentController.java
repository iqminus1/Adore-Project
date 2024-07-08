package uz.pdp.adoreproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.CommentCrudDTO;
import uz.pdp.adoreproject.payload.CommentDTO;
import uz.pdp.adoreproject.service.CommentServiceImpl;
import uz.pdp.adoreproject.utils.AppConstants;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/comment")
public class CommentController {
    private final CommentServiceImpl commentService;

    @GetMapping("/{restaurantId}")
    public ApiResultDTO<List<CommentDTO>> read(@PathVariable Integer restaurantId) {
        return commentService.readByRestaurantId(restaurantId);
    }

    @PostMapping
    public ApiResultDTO<CommentDTO> create(@RequestBody CommentCrudDTO crudDTO) {
        return commentService.create(crudDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        commentService.delete(id);
    }
}
