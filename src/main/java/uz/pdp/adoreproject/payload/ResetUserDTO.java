package uz.pdp.adoreproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResetUserDTO {
    private String email;
    private String code;
    private String password;
}
