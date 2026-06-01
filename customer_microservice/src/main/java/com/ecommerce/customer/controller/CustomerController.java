package com.ecommerce.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.customer.dto.CustomerDTO;
import com.ecommerce.customer.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;


    @PostMapping
    public CustomerDTO save(@RequestBody CustomerDTO dto) {
        return service.save(dto);
    }

    @GetMapping("/{id}")
    public CustomerDTO get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<CustomerDTO> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public CustomerDTO update(@PathVariable Long id, @RequestBody CustomerDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted";
    }
}
