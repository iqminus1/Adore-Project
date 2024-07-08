package uz.pdp.adoreproject.service;

import uz.pdp.adoreproject.payload.*;

import java.util.List;

public interface OrderService {
    ApiResultDTO<List<OrderDTO>> read();

    ApiResultDTO<OrderDTO> read(Integer id);

    ApiResultDTO<OrderDTO> create(OrderCrudDTO crudDTO);

    ApiResultDTO<OrderDTO> update(Integer id, OrderCrudDTO crudDTO);

    void delete(Integer id);

    ApiResultDTO<List<OrderDTO>> userOrders();

    ApiResultDTO<OrderDTO> createOrder(OrderCrudDTO crudDTO);
}
