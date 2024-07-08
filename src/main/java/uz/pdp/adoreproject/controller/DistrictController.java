package uz.pdp.adoreproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.DistrictCrudDTO;
import uz.pdp.adoreproject.payload.DistrictDTO;
import uz.pdp.adoreproject.service.DistrictService;
import uz.pdp.adoreproject.utils.AppConstants;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/district")
public class DistrictController {
    public final DistrictService districtService;

    @GetMapping("/{id}")
    public ApiResultDTO<DistrictDTO> read(@PathVariable Integer id) {
        return districtService.read(id);
    }

    @GetMapping
    public ApiResultDTO<List<DistrictDTO>> read(){
        return districtService.read();
    }


    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).CREATE_DISTRICT.name())")
    @PostMapping
    public ApiResultDTO<DistrictDTO> create(@RequestBody @Valid DistrictCrudDTO crudDTO) {
        return districtService.create(crudDTO);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).UPDATE_DISTRICT.name())")
    @PutMapping("/{id}")
    public ApiResultDTO<DistrictDTO> update(@PathVariable Integer id, @RequestBody @Valid DistrictCrudDTO crudDTO) {
        return districtService.update(id, crudDTO);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).DELETE_DISTRICT.name())")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        districtService.delete(id);
    }
}
