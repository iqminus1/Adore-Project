package uz.pdp.adoreproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.OrderCrudDTO;
import uz.pdp.adoreproject.payload.OrderDTO;
import uz.pdp.adoreproject.service.OrderService;
import uz.pdp.adoreproject.utils.AppConstants;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/order")
public class OrderController {
    public final OrderService orderService;

    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).READ_ORDER.name())")
    @GetMapping("/{id}")
    public ApiResultDTO<OrderDTO> read(@PathVariable Integer id) {
        return orderService.read(id);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).READ_ALL_ORDERS.name())")
    @GetMapping
    public ApiResultDTO<List<OrderDTO>> read() {
        return orderService.read();
    }


    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).CREATE_ORDER.name())")
    @PostMapping

    public ApiResultDTO<OrderDTO> create(@RequestBody @Valid OrderCrudDTO crudDTO) {
        return orderService.create(crudDTO);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).UPDATE_ORDER.name())")
    @PutMapping("/{id}")
    public ApiResultDTO<OrderDTO> update(@PathVariable Integer id, @RequestBody @Valid OrderCrudDTO crudDTO) {
        return orderService.update(id, crudDTO);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.adoreproject.enums.PermissionEnum).DELETE_ORDER.name())")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        orderService.delete(id);
    }

    //------------------- USER ORDER ---------------------//
    @GetMapping("/my-orders")
    public ApiResultDTO<List<OrderDTO>> readUserOrders(){
        return orderService.userOrders();
    }
    @PostMapping("/create-order")
    public ApiResultDTO<OrderDTO> createUserOrder(@RequestBody @Valid OrderCrudDTO crudDTO){
        return orderService.createOrder(crudDTO);
    }
}
