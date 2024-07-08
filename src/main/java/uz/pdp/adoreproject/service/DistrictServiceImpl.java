package uz.pdp.adoreproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.adoreproject.entity.District;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.DistrictCrudDTO;
import uz.pdp.adoreproject.payload.DistrictDTO;
import uz.pdp.adoreproject.repository.DistrictRepository;
import uz.pdp.adoreproject.repository.RegionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;

    @Override
    public ApiResultDTO<List<DistrictDTO>> read() {
        List<DistrictDTO> list = districtRepository.findAll().stream().map(this::toDTO).toList();
        return ApiResultDTO.success(list);
    }

    @Override
    public ApiResultDTO<DistrictDTO> read(Integer id) {
        return ApiResultDTO.success(toDTO(districtRepository.findById(id).orElseThrow()));
    }

    @Override
    public ApiResultDTO<DistrictDTO> create(DistrictCrudDTO crudDTO) {
        DistrictDTO districtDTO = setAndSave(new District(), crudDTO);
        return ApiResultDTO.success(districtDTO);
    }

    @Override
    public ApiResultDTO<DistrictDTO> update(Integer id, DistrictCrudDTO crudDTO) {
        return ApiResultDTO.success(setAndSave(districtRepository.findById(id).orElseThrow(), crudDTO));
    }

    @Override
    public void delete(Integer id) {
        districtRepository.deleteById(id);
    }

    private DistrictDTO toDTO(District district) {
        return new DistrictDTO(district.getId(), district.getRegion().getId(), district.getName());
    }

    private DistrictDTO setAndSave(District district, DistrictCrudDTO crudDTO) {
        district.setName(crudDTO.getName());
        district.setRegion(regionRepository.findById(crudDTO.getRegionId()).orElseThrow());
        districtRepository.save(district);
        return toDTO(district);
    }

}
