package uz.pdp.adoreproject.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.adoreproject.enums.ServiceEnum;
import uz.pdp.adoreproject.enums.StatusEnum;
import uz.pdp.adoreproject.enums.WorkTimeEnum;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link uz.pdp.adoreproject.entity.Restaurant}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantCrudDTO implements Serializable {
    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String name;

    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String price;

    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String description;

    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String phoneNumber;

    @NotNull(message = "Null")
    private Integer peopleSize;

    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String regionName;

    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String districtName;

    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String neighbourhoodName;

    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String neighbourhoodStreet;

    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String neighbourhoodHouseNumber;

    @NotNull(message = "Null")
    private List<WorkTimeEnum> workTimes;

    @NotNull(message = "Null")
    private List<ServiceEnum> services;

    private List<Integer> attachmentIds;
}