package com.ecommerce.order.dto;

public class OrderCustomerResponse {

    private OrderDTO order;
    private CustomerDTO customer;

    public OrderDTO getOrder() { return order; }
    public void setOrder(OrderDTO order) { this.order = order; }

    public CustomerDTO getCustomer() { return customer; }
    public void setCustomer(CustomerDTO customer) { this.customer = customer; }
}

