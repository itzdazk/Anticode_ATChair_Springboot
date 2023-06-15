package com.example.repository;

import com.example.model.Category;
import com.example.model.Customer;
import com.example.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select count(*) from orders o where o.order_status = 'Pending'", nativeQuery = true)
    Long findAllPendingOrder();

    @Query(value = "select count(*) from orders o where o.order_status = 'Completed'", nativeQuery = true)
    Long findAllCompletedOrder();


    @Query(value = "select * from orders o where o.order_status = 'Pending'", nativeQuery = true)
    List<Order> findAllOrderPending();
}
