package uz.pdp.adoreproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import uz.pdp.adoreproject.entity.Comment;
import uz.pdp.adoreproject.payload.CommentCrudDTO;
import uz.pdp.adoreproject.payload.CommentDTO;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {
    CommentDTO toDTO(Comment comment);

    List<CommentDTO> toDTO(List<Comment> comments);
    Comment toEntity(CommentCrudDTO commentCrudDTO);
}