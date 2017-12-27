package com.wcc.order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Orders, Long>, OrderService {
}
