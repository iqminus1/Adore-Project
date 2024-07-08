package uz.pdp.adoreproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.NeighbourhoodCrudDTO;
import uz.pdp.adoreproject.payload.NeighbourhoodDTO;
import uz.pdp.adoreproject.service.NeighbourhoodService;
import uz.pdp.adoreproject.utils.AppConstants;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/neighbourhood")
public class NeighbourhoodController {
    public final NeighbourhoodService neighbourhoodService;

    @GetMapping("/{id}")
    public ApiResultDTO<NeighbourhoodDTO> read(@PathVariable Integer id) {
        return neighbourhoodService.read(id);
    }

    @GetMapping
    public ApiResultDTO<List<NeighbourhoodDTO>> read() {
        return neighbourhoodService.read();
    }


    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).CREATE_NEIGHBOURHOOD.name())")
    @PostMapping
    public ApiResultDTO<NeighbourhoodDTO> create(@RequestBody @Valid NeighbourhoodCrudDTO crudDTO) {
        return neighbourhoodService.create(crudDTO);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).UPDATE_NEIGHBOURHOOD.name())")
    @PutMapping("/{id}")
    public ApiResultDTO<NeighbourhoodDTO> update(@PathVariable Integer id, @RequestBody @Valid NeighbourhoodCrudDTO crudDTO) {
        return neighbourhoodService.update(id, crudDTO);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).DELETE_NEIGHBOURHOOD.name())")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        neighbourhoodService.delete(id);
    }
}
