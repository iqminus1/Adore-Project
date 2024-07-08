package uz.pdp.adoreproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.adoreproject.entity.Restaurant;
import uz.pdp.adoreproject.enums.ServiceEnum;
import uz.pdp.adoreproject.enums.StatusEnum;
import uz.pdp.adoreproject.enums.WorkTimeEnum;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Restaurant}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO implements Serializable {
    private Integer id;
    private String name;
    private String price;
    private String description;
    private String phoneNumber;
    private Integer neighbourhoodId;
    private Integer peopleSize;
    private List<WorkTimeEnum> workTimes;
    private List<ServiceEnum> services;
    private List<Integer> attachmentIds;
}