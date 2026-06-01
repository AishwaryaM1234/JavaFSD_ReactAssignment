package com.ecommerce.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
