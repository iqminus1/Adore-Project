package uz.pdp.adoreproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.adoreproject.entity.Region;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.RegionCrudDTO;
import uz.pdp.adoreproject.payload.RegionDTO;
import uz.pdp.adoreproject.repository.RegionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    @Override
    public ApiResultDTO<List<RegionDTO>> read() {
        List<RegionDTO> list = regionRepository.findAll().stream().map(RegionServiceImpl::getRegionDTO).toList();
        return ApiResultDTO.success(list);
    }

    @Override
    public ApiResultDTO<RegionDTO> read(Integer id) {
        Region region = regionRepository.findById(id).orElseThrow();
        return ApiResultDTO.success(getRegionDTO(region));
    }


    @Override
    public ApiResultDTO<RegionDTO> create(RegionCrudDTO crudDTO) {
        Region region = new Region();
        region.setName(crudDTO.getName());
        regionRepository.save(region);
        return ApiResultDTO.success(getRegionDTO(region));
    }

    @Override
    public ApiResultDTO<RegionDTO> update(Integer id, RegionCrudDTO crudDTO) {
        Region region = regionRepository.findById(id).orElseThrow();
        region.setName(crudDTO.getName());
        regionRepository.save(region);
        return ApiResultDTO.success(getRegionDTO(region));
    }

    @Override
    public void delete(Integer id) {
        regionRepository.deleteById(id);
    }

    private static RegionDTO getRegionDTO(Region region) {
        return new RegionDTO(region.getId(), region.getName());
    }
}
