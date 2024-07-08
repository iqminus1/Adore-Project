package uz.pdp.adoreproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import uz.pdp.adoreproject.entity.Attachment;
import uz.pdp.adoreproject.payload.AttachmentDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AttachmentMapper {
    AttachmentDTO toDTO(Attachment attachment);
}