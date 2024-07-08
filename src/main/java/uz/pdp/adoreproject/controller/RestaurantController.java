package uz.pdp.adoreproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.RestaurantCrudDTO;
import uz.pdp.adoreproject.payload.RestaurantDTO;
import uz.pdp.adoreproject.service.RestaurantService;
import uz.pdp.adoreproject.utils.AppConstants;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/restaurant")
public class RestaurantController {
    public final RestaurantService restaurantService;

    @GetMapping("/{id}")
    public ApiResultDTO<RestaurantDTO> read(@PathVariable Integer id) {
        return restaurantService.read(id);
    }

    @GetMapping
    public ApiResultDTO<List<RestaurantDTO>> read() {
        return restaurantService.read();
    }


    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).CREATE_RESTAURANT.name())")
    @PostMapping
    public ApiResultDTO<RestaurantDTO> create(@RequestBody @Valid RestaurantCrudDTO crudDTO) {
        return restaurantService.create(crudDTO);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).UPDATE_RESTAURANT.name())")
    @PutMapping("/{id}")
    public ApiResultDTO<RestaurantDTO> update(@PathVariable Integer id, @RequestBody @Valid RestaurantCrudDTO crudDTO) {
        return restaurantService.update(id, crudDTO);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).DELETE_RESTAURANT.name())")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        restaurantService.delete(id);
    }
}
