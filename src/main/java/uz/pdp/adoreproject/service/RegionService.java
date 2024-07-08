package uz.pdp.adoreproject.service;

import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.RegionCrudDTO;
import uz.pdp.adoreproject.payload.RegionDTO;

import java.util.List;

public interface RegionService {
    ApiResultDTO<List<RegionDTO>> read();

    ApiResultDTO<RegionDTO> read(Integer id);

    ApiResultDTO<RegionDTO> create(RegionCrudDTO crudDTO);

    ApiResultDTO<RegionDTO> update(Integer id, RegionCrudDTO crudDTO);

    void delete(Integer id);
}
