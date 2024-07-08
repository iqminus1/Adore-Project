package uz.pdp.adoreproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.adoreproject.entity.Comment;
import uz.pdp.adoreproject.entity.User;
import uz.pdp.adoreproject.enums.RoleTypeEnum;
import uz.pdp.adoreproject.mapper.CommentMapper;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.CommentCrudDTO;
import uz.pdp.adoreproject.payload.CommentDTO;
import uz.pdp.adoreproject.repository.CommentRepository;
import uz.pdp.adoreproject.repository.RestaurantRepository;
import uz.pdp.adoreproject.repository.UserRepository;
import uz.pdp.adoreproject.security.AuditConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AuditConfig auditConfig;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public ApiResultDTO<List<CommentDTO>> readByRestaurantId(Integer id) {
        List<Comment> byRestaurantId = commentRepository.findByRestaurantId(id);
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (Comment comment : byRestaurantId) {
            CommentDTO dto = commentMapper.toDTO(comment);
            if (comment.getUser()!=null) {
                dto.setUserId(comment.getUser().getId());
            }
            dto.setRestaurantId(comment.getRestaurant().getId());
            commentDTOs.add(dto);
        }
        return ApiResultDTO.success(commentDTOs);
    }

    @Override
    public ApiResultDTO<CommentDTO> create(CommentCrudDTO crudDTO) {
        Optional<Integer> currentAuditor = auditConfig.getCurrentAuditor();
        if (currentAuditor.isEmpty()) {
            throw new RuntimeException();
        }
        User user = userRepository.findById(currentAuditor.get()).orElseThrow();
        Comment comment = commentMapper.toEntity(crudDTO);
        comment.setUser(user);
        comment.setRestaurant(restaurantRepository.findById(crudDTO.getRestaurantId()).orElseThrow());
        commentRepository.save(comment);
        return ApiResultDTO.success(new CommentDTO(comment.getId(), comment.getText(), comment.getUser().getId(), comment.getRestaurant().getId()));
    }

    @Override
    public void delete(Integer id) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        Optional<Integer> currentAuditor = auditConfig.getCurrentAuditor();
        if (currentAuditor.isEmpty()) {
            throw new RuntimeException();
        }
        if ((!comment.getUser().getId().equals(currentAuditor.get())) && comment.getUser().getRole().getRoleType() != RoleTypeEnum.ADMIN) {
            throw new RuntimeException();
        }
        commentRepository.delete(comment);
    }
}
