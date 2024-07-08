package uz.pdp.adoreproject.service;

import jakarta.servlet.http.HttpServletRequest;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.DistrictCrudDTO;
import uz.pdp.adoreproject.payload.RestaurantCrudDTO;
import uz.pdp.adoreproject.payload.RestaurantDTO;

import java.util.List;

public interface RestaurantService {
    ApiResultDTO<List<RestaurantDTO>> read();

    ApiResultDTO<RestaurantDTO> read(Integer id);

    ApiResultDTO<RestaurantDTO> create(RestaurantCrudDTO crudDTO);

    ApiResultDTO<RestaurantDTO> update(Integer id, RestaurantCrudDTO crudDTO);

    void delete(Integer id);
}
