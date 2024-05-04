package com.example.shop_mng_system.repository;

import com.example.shop_mng_system.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByUserIdAndDate(Long userId, LocalDate date);
}
