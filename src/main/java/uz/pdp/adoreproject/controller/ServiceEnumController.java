package uz.pdp.adoreproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.adoreproject.enums.ServiceEnum;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.utils.AppConstants;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(AppConstants.BASE_PATH_V1 + "/services")
public class ServiceEnumController {
    @GetMapping
    public ApiResultDTO<?> read() {
        Map<String, String> values = new HashMap<>();
        for (ServiceEnum value : ServiceEnum.values()) {
            values.put(value.name(), value.getDescription());
        }
        return ApiResultDTO.success(values);
    }
}
