package uz.pdp.adoreproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.SignInDTO;
import uz.pdp.adoreproject.payload.SignUpDTO;
import uz.pdp.adoreproject.payload.TokenDTO;
import uz.pdp.adoreproject.service.AuthServiceImpl;
import uz.pdp.adoreproject.payload.ResetUserDTO;
import uz.pdp.adoreproject.utils.AppConstants;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/auth")
public class AuthController {
    private final AuthServiceImpl authService;

    @PostMapping("/sign-up")
    public Object signUp(@RequestBody @Valid SignUpDTO signUpDTO, @RequestParam(defaultValue = "") String code) {
        if (!code.isBlank()) return authService.verificationEmail(code, signUpDTO.getUsername());

        return authService.signUp(signUpDTO);
    }

    @PostMapping("/sign-in")
    public ApiResultDTO<TokenDTO> signIn(@RequestBody @Valid SignInDTO signInDTO) {
        return authService.signIn(signInDTO);
    }

    @GetMapping("/forgot-password")
    public ApiResultDTO<String> forgotPassword(@RequestParam String email) {
        return authService.forgotPassword(email);
    }

    @PostMapping("/reset-password")
    public boolean resetPassword(@RequestBody @Valid ResetUserDTO resetUserDTO){
        return authService.resetPassword(resetUserDTO);
    }
}
