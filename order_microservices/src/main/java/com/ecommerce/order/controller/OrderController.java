package com.ecommerce.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.order.dto.OrderDTO;
import com.ecommerce.order.dto.OrderCustomerResponse;
import com.ecommerce.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    // ✅ 1. CREATE ORDER
    @PostMapping
    public OrderDTO createOrder(@RequestBody OrderDTO dto) {
        return service.save(dto);
    }

    // ✅ 2. GET ORDER BY ID
    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable Long id) {
        return service.getById(id);
    }

    // ✅ 3. GET ALL ORDERS
    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return service.getAll();
    }

    // ✅ 4. UPDATE ORDER
    @PutMapping("/{id}")
    public OrderDTO updateOrder(@PathVariable Long id, @RequestBody OrderDTO dto) {
        return service.update(id, dto);
    }

    // ✅ 5. DELETE ORDER
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {
        service.delete(id);
        return "Deleted Successfully";
    }

    // ✅ 6. COMBINED API (ORDER + CUSTOMER)
    @GetMapping("/with-customer/{id}")
    public OrderCustomerResponse getOrderWithCustomer(@PathVariable Long id) {
        return service.getOrderWithCustomer(id);
    }
}

