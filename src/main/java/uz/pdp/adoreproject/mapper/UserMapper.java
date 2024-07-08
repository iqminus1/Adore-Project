package uz.pdp.adoreproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.adoreproject.payload.SignUpDTO;
import uz.pdp.adoreproject.entity.User;
import uz.pdp.adoreproject.payload.UserCrudDTO;
import uz.pdp.adoreproject.payload.UserDTO;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserCrudDTO userCrudDTO);

    UserDTO toDTO(User user);

    void updateEntity(@MappingTarget User user, UserCrudDTO userCrudDTO);

    List<UserDTO> toDTO(List<User> user);

    User toEntity(SignUpDTO signUpDTO);
}