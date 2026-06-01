package com.ecommerce.order.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
 
import com.ecommerce.order.dto.CustomerDTO;
import com.ecommerce.order.dto.OrderCustomerResponse;
import com.ecommerce.order.dto.OrderDTO;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.repository.OrderRepository;
 
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
 
@Service
public class OrderServiceImpl implements OrderService {
 
    @Autowired
    private OrderRepository repo;
 
    @Autowired
    private RestTemplate restTemplate;
 
    // CUSTOMER MICROSERVICE URL
    private final String URL = "http://localhost:8081/customer/";
 
    // ENTITY TO DTO
    private OrderDTO mapToDTO(Order order) {
 
        OrderDTO dto = new OrderDTO();
 
        dto.setId(order.getId());
        dto.setProduct(order.getProduct());
        dto.setQuantity(order.getQuantity());
        dto.setCustomerId(order.getCustomerId());
 
        return dto;
    }
 
    // CREATE ORDER
    @Override
    public OrderDTO save(OrderDTO dto) {
 
        Order order = new Order();
 
        order.setProduct(dto.getProduct());
        order.setQuantity(dto.getQuantity());
        order.setCustomerId(dto.getCustomerId());
 
        return mapToDTO(repo.save(order));
    }
 
    // GET ORDER BY ID
    @Override
    public OrderDTO getById(Long id) {
 
        Order order = repo.findById(id).orElse(null);
 
        if (order == null) {
            throw new RuntimeException("Order not found");
        }
 
        return mapToDTO(order);
    }
 
    // GET ALL ORDERS
    @Override
    public List<OrderDTO> getAll() {
 
        return repo.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }
 
    // UPDATE ORDER
    @Override
    public OrderDTO update(Long id, OrderDTO dto) {
 
        Order order = repo.findById(id).orElse(null);
 
        if (order == null) {
            throw new RuntimeException("Order not found");
        }
 
        order.setProduct(dto.getProduct());
        order.setQuantity(dto.getQuantity());
        order.setCustomerId(dto.getCustomerId());
 
        return mapToDTO(repo.save(order));
    }
 
    // DELETE ORDER
    @Override
    public void delete(Long id) {
 
        repo.deleteById(id);
    }
 
    // CIRCUIT BREAKER METHOD
    @CircuitBreaker(name = "customerService", fallbackMethod = "fallbackCustomer")
    @Override
    public OrderCustomerResponse getOrderWithCustomer(Long id) {
 
        Order order = repo.findById(id).orElse(null);
 
        if (order == null) {
            throw new RuntimeException("Order not found");
        }
 
        // CALL CUSTOMER SERVICE
        CustomerDTO customer = restTemplate.getForObject(
                URL + order.getCustomerId(),
                CustomerDTO.class
        );
 
        OrderCustomerResponse response = new OrderCustomerResponse();
 
        response.setOrder(mapToDTO(order));
        response.setCustomer(customer);
 
        return response;
    }
 
    // FALLBACK METHOD
    public OrderCustomerResponse fallbackCustomer(Long id, Exception ex) {
 
        OrderDTO order = getById(id);
 
        CustomerDTO customer = new CustomerDTO();
 
        customer.setId(0L);
        customer.setName("Customer Service Down");
        customer.setEmail("Not Available");
 
        OrderCustomerResponse response = new OrderCustomerResponse();
 
        response.setOrder(order);
        response.setCustomer(customer);
 
        return response;
    }
}


