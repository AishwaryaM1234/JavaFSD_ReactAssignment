package com.ecommerce.order.service;

import java.util.List;
import com.ecommerce.order.dto.OrderDTO;
import com.ecommerce.order.dto.OrderCustomerResponse;

public interface OrderService {

    OrderDTO save(OrderDTO dto);
    OrderDTO getById(Long id);
    List<OrderDTO> getAll();
    OrderDTO update(Long id, OrderDTO dto);
    void delete(Long id);

    OrderCustomerResponse getOrderWithCustomer(Long id);
}

