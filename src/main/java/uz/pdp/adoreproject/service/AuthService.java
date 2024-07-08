package uz.pdp.adoreproject.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import uz.pdp.adoreproject.payload.*;

public interface AuthService extends UserDetailsService {
    ApiResultDTO<String> signUp(SignUpDTO signUpDTO);

    boolean verificationEmail(String code, String username);

    ApiResultDTO<TokenDTO> signIn(SignInDTO signInDTO);

    ApiResultDTO<String> forgotPassword(String email);
    boolean resetPassword(ResetUserDTO resetUserDTO);
}
