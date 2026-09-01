package com.purchase.billingsoftware.repository;

import com.purchase.billingsoftware.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemEntityRepository extends JpaRepository<OrderItemEntity,Long> {
}
