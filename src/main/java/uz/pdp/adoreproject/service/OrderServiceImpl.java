package uz.pdp.adoreproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.adoreproject.entity.Order;
import uz.pdp.adoreproject.entity.User;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.OrderCrudDTO;
import uz.pdp.adoreproject.payload.OrderDTO;
import uz.pdp.adoreproject.repository.OrderRepository;
import uz.pdp.adoreproject.repository.RestaurantRepository;
import uz.pdp.adoreproject.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;

    @Override
    public ApiResultDTO<List<OrderDTO>> read() {
        List<OrderDTO> list = orderRepository.findAll().stream().map(this::toDTO).toList();
        return ApiResultDTO.success(list);
    }

    @Override
    public ApiResultDTO<OrderDTO> read(Integer id) {
        return ApiResultDTO.success(toDTO(orderRepository.findById(id).orElseThrow()));
    }

    @Override
    public ApiResultDTO<OrderDTO> create(OrderCrudDTO crudDTO) {
        return ApiResultDTO.success(setAndSave(new Order(), crudDTO));
    }

    @Override
    public ApiResultDTO<OrderDTO> update(Integer id, OrderCrudDTO crudDTO) {
        Order order = orderRepository.findById(id).orElseThrow();
        return ApiResultDTO.success(setAndSave(order, crudDTO));
    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public ApiResultDTO<List<OrderDTO>> userOrders() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ApiResultDTO.success(orderRepository.findAllByUserId(user.getId()).stream().map(this::toDTO).toList());
    }

    @Override
    public ApiResultDTO<OrderDTO> createOrder(OrderCrudDTO crudDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        crudDTO.setUserId(user.getId());
        return create(crudDTO);
    }

    private OrderDTO setAndSave(Order order, OrderCrudDTO crudDTO) {
        order.setDate(crudDTO.getDate());
        order.setUser(userRepository.findById(crudDTO.getUserId()).orElseThrow());
        order.setRestaurant(restaurantRepository.findById(crudDTO.getRestaurantId()).orElseThrow());
        order.setStatusEnum(crudDTO.getStatusEnum());
        orderRepository.save(order);
        return toDTO(order);
    }

    private OrderDTO toDTO(Order order) {
        return new OrderDTO(order.getId(),
                order.getDate(),
                order.getRestaurant().getId(),
                order.getUser().getId(),
                order.getStatusEnum(),
                order.getWorkTime());
    }
}
