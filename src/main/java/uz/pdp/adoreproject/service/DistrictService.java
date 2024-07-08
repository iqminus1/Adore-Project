package uz.pdp.adoreproject.service;

import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.DistrictCrudDTO;
import uz.pdp.adoreproject.payload.DistrictDTO;

import java.util.List;

public interface DistrictService {
    ApiResultDTO<List<DistrictDTO>> read();

    ApiResultDTO<DistrictDTO> read(Integer id);

    ApiResultDTO<DistrictDTO> create(DistrictCrudDTO crudDTO);

    ApiResultDTO<DistrictDTO> update(Integer id, DistrictCrudDTO crudDTO);

    void delete(Integer id);
}
