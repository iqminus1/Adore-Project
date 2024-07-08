package uz.pdp.adoreproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.UserCrudDTO;
import uz.pdp.adoreproject.payload.UserDTO;
import uz.pdp.adoreproject.payload.UserProfileUpdateDTO;
import uz.pdp.adoreproject.service.UserService;
import uz.pdp.adoreproject.utils.AppConstants;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/user")
public class UserController {
    public final UserService userService;

    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).READ_USER.name())")
    @GetMapping("/{id}")
    public ApiResultDTO<UserDTO> read(@PathVariable Integer id) {
        return userService.read(id);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).READ_ALL_USERS.name())")
    @GetMapping
    public ApiResultDTO<List<UserDTO>> read() {
        return userService.read();
    }


    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).CREATE_USER.name())")
    @PostMapping

    public ApiResultDTO<UserDTO> create(@RequestBody @Valid UserCrudDTO crudDTO) {
        return userService.create(crudDTO);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).UPDATE_USER.name())")
    @PutMapping("/{id}")
    public ApiResultDTO<UserDTO> update(@PathVariable Integer id, @RequestBody @Valid UserCrudDTO crudDTO) {
        return userService.update(id, crudDTO);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).DELETE_USER.name())")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    @PostMapping("/update-profile")
    public ApiResultDTO<String> updateProfile(@RequestBody @Valid UserProfileUpdateDTO updateDTO){
        return userService.userProfileUpdate(updateDTO);
    }
    @GetMapping("/verify-changed-email")
    public boolean verifyChangedEmail(@RequestParam String code){
        return userService.verifyEmail(code);
    }
}
