package uz.pdp.adoreproject.service;

import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.UserCrudDTO;
import uz.pdp.adoreproject.payload.UserDTO;
import uz.pdp.adoreproject.payload.UserProfileUpdateDTO;

import java.util.List;

public interface UserService {
    ApiResultDTO<UserDTO> read(Integer id);

    ApiResultDTO<List<UserDTO>> read();

    ApiResultDTO<UserDTO> create(UserCrudDTO userCrudDTO);

    ApiResultDTO<UserDTO> update(Integer id, UserCrudDTO userCrudDTO);

    void delete(Integer id);
    ApiResultDTO<String> userProfileUpdate(UserProfileUpdateDTO updateDTO);
    boolean verifyEmail(String codeString);
}
