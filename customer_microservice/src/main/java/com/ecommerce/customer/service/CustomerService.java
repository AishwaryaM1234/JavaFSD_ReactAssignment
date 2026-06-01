package com.ecommerce.customer.service;

import java.util.List;
import com.ecommerce.customer.dto.CustomerDTO;

public interface CustomerService {

    CustomerDTO save(CustomerDTO dto);
    CustomerDTO getById(Long id);
    List<CustomerDTO> getAll();
    CustomerDTO update(Long id, CustomerDTO dto);
    void delete(Long id);
}

