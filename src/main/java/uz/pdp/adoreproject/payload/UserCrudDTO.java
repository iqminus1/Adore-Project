package uz.pdp.adoreproject.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.adoreproject.entity.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCrudDTO implements Serializable {
    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String fullName;

    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String email;

    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String phoneNumber;

    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String username;

    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String password;

    private boolean enabled;

    private Integer roleId;
}