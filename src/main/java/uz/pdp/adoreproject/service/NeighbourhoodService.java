package uz.pdp.adoreproject.service;

import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.NeighbourhoodCrudDTO;
import uz.pdp.adoreproject.payload.NeighbourhoodDTO;

import java.util.List;

public interface NeighbourhoodService {
    ApiResultDTO<List<NeighbourhoodDTO>> read();

    ApiResultDTO<NeighbourhoodDTO> read(Integer id);

    ApiResultDTO<NeighbourhoodDTO> create(NeighbourhoodCrudDTO crudDTO);

    ApiResultDTO<NeighbourhoodDTO> update(Integer id, NeighbourhoodCrudDTO crudDTO);

    void delete(Integer id);
}
