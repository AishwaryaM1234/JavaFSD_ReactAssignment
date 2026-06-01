package com.ecommerce.customer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.customer.dto.CustomerDTO;
import com.ecommerce.customer.entity.Customer;
import com.ecommerce.customer.repository.CustomerRepository;

@Service  // ✅ VERY IMPORTANT
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repo;

    private CustomerDTO mapToDTO(Customer c) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setEmail(c.getEmail());
        return dto;
    }

    private Customer mapToEntity(CustomerDTO dto) {
        Customer c = new Customer();
        c.setId(dto.getId());
        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        return c;
    }

    @Override
    public CustomerDTO save(CustomerDTO dto) {
        return mapToDTO(repo.save(mapToEntity(dto)));
    }

    @Override
    public CustomerDTO getById(Long id) {
        return mapToDTO(repo.findById(id).orElse(null));
    }

    @Override
    public List<CustomerDTO> getAll() {
        return repo.findAll()
                   .stream()
                   .map(this::mapToDTO)
                   .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO update(Long id, CustomerDTO dto) {
        Customer c = repo.findById(id).orElse(null);
        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        return mapToDTO(repo.save(c));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}

