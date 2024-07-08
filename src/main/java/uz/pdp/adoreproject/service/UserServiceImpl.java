package uz.pdp.adoreproject.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.adoreproject.entity.Code;
import uz.pdp.adoreproject.entity.Role;
import uz.pdp.adoreproject.entity.User;
import uz.pdp.adoreproject.enums.RoleTypeEnum;
import uz.pdp.adoreproject.exceptions.AlreadyExistsException;
import uz.pdp.adoreproject.exceptions.NotFoundException;
import uz.pdp.adoreproject.mapper.UserMapper;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.UserCrudDTO;
import uz.pdp.adoreproject.payload.UserDTO;
import uz.pdp.adoreproject.payload.UserProfileUpdateDTO;
import uz.pdp.adoreproject.repository.CodeRepository;
import uz.pdp.adoreproject.repository.RoleRepository;
import uz.pdp.adoreproject.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final CodeService codeService;
    private final MailSenderService mailSenderService;
    private final CodeRepository codeRepository;

    @Override
    public ApiResultDTO<UserDTO> read(Integer id) {
        return ApiResultDTO.success(userMapper.toDTO(userRepository.findById(id).orElseThrow()));
    }

    @Override
    public ApiResultDTO<List<UserDTO>> read() {
        return ApiResultDTO.success(userMapper.toDTO(userRepository.findAll()));
    }

    @Override
    @Transactional
    public ApiResultDTO<UserDTO> create(UserCrudDTO userCrudDTO) {
        if (userRepository.existsByUsernameOrEmail(userCrudDTO.getUsername(), userCrudDTO.getEmail()))
            throw new AlreadyExistsException("username or email already exists");

        User user = userMapper.toEntity(userCrudDTO);
        return saveUserAndSendCodeIfEnable(userCrudDTO, user);
    }

    @Override
    @Transactional
    public ApiResultDTO<UserDTO> update(Integer id, UserCrudDTO userCrudDTO) {
        User user = userRepository.findById(id).orElseThrow();
        if (!user.getEmail().equals(userCrudDTO.getEmail()))
            if (userRepository.existsByEmail(userCrudDTO.getEmail()))
                throw new AlreadyExistsException("email already exists another account");

        if (!user.getUsername().equals(userCrudDTO.getUsername()))
            if (userRepository.existsByUsername(userCrudDTO.getUsername()))
                throw new AlreadyExistsException("username already exists another account");

        userMapper.updateEntity(user, userCrudDTO);
        return saveUserAndSendCodeIfEnable(userCrudDTO, user);
    }


    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public ApiResultDTO<String> userProfileUpdate(UserProfileUpdateDTO profileUpdateDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getEmail();
        user.setUsername(profileUpdateDTO.getUsername());
        user.setFullName(profileUpdateDTO.getFullName());
        user.setPhoneNumber(profileUpdateDTO.getPhoneNumber());
        if (!email.equals(profileUpdateDTO.getEmail())) {
            Code code = new Code(codeService.generateCode(), 5, user.getId(), profileUpdateDTO.getEmail());
            codeRepository.save(code);
            mailSenderService.sendMessage(user.getEmail(), code.getCode(), "Verification code");
            return ApiResultDTO.success("You must verifier email");
        }
        user.setEmail(profileUpdateDTO.getEmail());
        userRepository.save(user);
        return ApiResultDTO.success("success");
    }

    @Override
    public boolean verifyEmail(String codeString) {
        Code code = codeRepository.findByCode(codeString).orElseThrow();
        if (!code.getCode().equals(codeString)) {
            if (code.getAttempt() == 0) {
                codeRepository.delete(code);
                return false;
            }
            code.setAttempt(code.getAttempt() - 1);
            codeRepository.save(code);
            return false;
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setEmail(code.getEmail());
        userRepository.save(user);
        codeRepository.delete(code);
        return true;
    }


    private ApiResultDTO<UserDTO> saveUserAndSendCodeIfEnable(UserCrudDTO userCrudDTO, User user) {
        Role defaultRole = roleRepository.findByRoleType(RoleTypeEnum.USER).orElseThrow(() -> new NotFoundException("System has not any role"));
        if (userCrudDTO.getRoleId() != null) {
            user.setRole(roleRepository.findById(userCrudDTO.getRoleId()).orElseGet(() -> defaultRole));
        } else user.setRole(defaultRole);

        userRepository.save(user);

        if (!user.isEnabled()) {
            String codeString = codeService.generateCode();
            Code code = new Code(codeString, 5, user.getId());
            codeRepository.save(code);
            mailSenderService.sendMessage(user.getEmail(), codeString, "Verification code");
        }


        UserDTO userDTO = userMapper.toDTO(user);
        userDTO.setRoleId(user.getRole().getId());
        return ApiResultDTO.success(userDTO);
    }
}
