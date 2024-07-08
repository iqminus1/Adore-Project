package uz.pdp.adoreproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.adoreproject.entity.Code;
import uz.pdp.adoreproject.entity.User;
import uz.pdp.adoreproject.exceptions.NotFoundException;
import uz.pdp.adoreproject.payload.*;
import uz.pdp.adoreproject.repository.CodeRepository;
import uz.pdp.adoreproject.repository.UserRepository;
import uz.pdp.adoreproject.security.JwtProvider;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final CodeRepository codeRepository;
    private final AuthenticationProvider authProvider;
    private final JwtProvider jwtProvider;
    private final CodeServiceImpl codeService;
    private final MailSenderService mailSenderService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Cacheable(cacheNames = "user",key = "#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by  username -> %s".formatted(username)));
    }

    @Override
    public ApiResultDTO<String> signUp(SignUpDTO signUpDTO) {
        UserCrudDTO userCrudDTO = new UserCrudDTO(
                signUpDTO.getFullName(),
                signUpDTO.getEmail(),
                signUpDTO.getPhoneNumber(),
                signUpDTO.getUsername(),
                passwordEncoder.encode(signUpDTO.getPassword()),
                false,
                null);
        userService.create(userCrudDTO);
        return ApiResultDTO.success("You must verifier email");
    }

    @Override
    public boolean verificationEmail(String codeString, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("user not found with username -> " + username));
        Code code = codeRepository.findByUserId(user.getId()).orElseThrow(() -> new RuntimeException(user.getEmail() + " verify code don`t have"));
        if (!code.getCode().equals(codeString)) {
            if (code.getAttempt() == 0) {
                codeRepository.delete(code);
                userRepository.delete(user);
                return false;
            }
            code.setAttempt(code.getAttempt() - 1);
            codeRepository.save(code);
            return false;
        }
        user.setEnabled(true);
        userRepository.save(user);
        codeRepository.delete(code);
        return true;
    }

    @Override
    public ApiResultDTO<TokenDTO> signIn(SignInDTO signInDTO) {
        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(signInDTO.getUsername(), signInDTO.getPassword());
            authProvider.authenticate(authentication);
            String token = jwtProvider.generateToken(signInDTO.getUsername());
            User user = userRepository.findByUsername(signInDTO.getUsername()).orElseThrow();
            codeRepository.findByUserId(user.getId()).ifPresent(codeRepository::delete);
            return ApiResultDTO.success(new TokenDTO(token));
        } catch (AuthenticationException e) {
            throw new RuntimeException();
        }

    }

    @Override
    public ApiResultDTO<String> forgotPassword(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("user not found by email -> " + email));
        String stringCode = codeService.generateCode();
        Code code = new Code(stringCode, 5, user.getId());
        codeRepository.save(code);
        mailSenderService.sendMessage(user.getEmail(), stringCode, "Verification code");
        return ApiResultDTO.success(email);
    }

    @Override
    public boolean resetPassword(ResetUserDTO resetUserDTO) {
        String email = resetUserDTO.getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("user not found by email -> " + email));
        Code code = codeRepository.findByUserId(user.getId()).orElseThrow(() -> new NotFoundException("there was no password reset on this email-> " + email));
        if (!code.getCode().equals(resetUserDTO.getCode())) {
            if (code.getAttempt() == 1) {
                codeRepository.delete(code);
                return false;
            }
            code.setAttempt(code.getAttempt() - 1);
            codeRepository.save(code);
            return false;
        }
        user.setPassword(passwordEncoder.encode(resetUserDTO.getPassword()));
        userRepository.save(user);
        codeRepository.delete(code);
        return true;
    }
}
