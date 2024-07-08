package uz.pdp.adoreproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.RegionCrudDTO;
import uz.pdp.adoreproject.payload.RegionDTO;
import uz.pdp.adoreproject.service.RegionService;
import uz.pdp.adoreproject.utils.AppConstants;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/region")
public class RegionController {
    public final RegionService regionService;

    @GetMapping("/{id}")
    public ApiResultDTO<RegionDTO> read(@PathVariable Integer id) {
        return regionService.read(id);
    }

    @GetMapping
    public ApiResultDTO<List<RegionDTO>> read() {
        return regionService.read();
    }


    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).CREATE_REGION.name())")
    @PostMapping
    public ApiResultDTO<RegionDTO> create(@RequestBody @Valid RegionCrudDTO crudDTO) {
        return regionService.create(crudDTO);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).UPDATE_REGION.name())")
    @PutMapping("/{id}")
    public ApiResultDTO<RegionDTO> update(@PathVariable Integer id, @RequestBody @Valid RegionCrudDTO crudDTO) {
        return regionService.update(id, crudDTO);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).DELETE_REGION.name())")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        regionService.delete(id);
    }
}
