package uz.pdp.adoreproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.adoreproject.entity.Neighbourhood;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.NeighbourhoodCrudDTO;
import uz.pdp.adoreproject.payload.NeighbourhoodDTO;
import uz.pdp.adoreproject.repository.DistrictRepository;
import uz.pdp.adoreproject.repository.NeighbourhoodRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NeighbourhoodServiceImpl implements NeighbourhoodService {

    private final DistrictRepository districtRepository;
    private final NeighbourhoodRepository neighbourhoodRepository;

    @Override
    public ApiResultDTO<List<NeighbourhoodDTO>> read() {
        List<NeighbourhoodDTO> list = neighbourhoodRepository.findAll().stream().map(this::toDTO).toList();
        return ApiResultDTO.success(list);
    }

    @Override
    public ApiResultDTO<NeighbourhoodDTO> read(Integer id) {
        return ApiResultDTO.success(toDTO(neighbourhoodRepository.findById(id).orElseThrow()));
    }

    @Override
    public ApiResultDTO<NeighbourhoodDTO> create(NeighbourhoodCrudDTO crudDTO) {
        return ApiResultDTO.success(setAndSave(new Neighbourhood(), crudDTO));
    }

    @Override
    public ApiResultDTO<NeighbourhoodDTO> update(Integer id, NeighbourhoodCrudDTO crudDTO) {
        Neighbourhood neighbourhood = neighbourhoodRepository.findById(id).orElseThrow();
        return ApiResultDTO.success(setAndSave(neighbourhood, crudDTO));
    }

    @Override
    public void delete(Integer id) {
        neighbourhoodRepository.deleteById(id);
    }

    private NeighbourhoodDTO setAndSave(Neighbourhood neighbourhood, NeighbourhoodCrudDTO crudDTO) {
        neighbourhood.setName(crudDTO.getName());
        neighbourhood.setStreet(crudDTO.getStreet());
        neighbourhood.setHouseNumber(crudDTO.getHouseNumber());
        neighbourhood.setDistrict(districtRepository.findById(crudDTO.getDistrictId()).orElseThrow());
        neighbourhoodRepository.save(neighbourhood);
        return toDTO(neighbourhood);
    }

    private NeighbourhoodDTO toDTO(Neighbourhood neighbourhood) {
        NeighbourhoodDTO neighbourhoodDTO = new NeighbourhoodDTO(
                neighbourhood.getId(),
                null,
                neighbourhood.getName(),
                neighbourhood.getStreet(),
                neighbourhood.getHouseNumber());
        if (neighbourhood.getDistrict()!=null)
            neighbourhoodDTO.setDistrictId(neighbourhood.getDistrict().getId());
        return neighbourhoodDTO;
    }
}
