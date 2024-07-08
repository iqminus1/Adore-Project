package uz.pdp.adoreproject.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignInDTO {
    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String username;

    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String password;
}
