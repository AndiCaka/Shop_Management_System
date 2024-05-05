package com.example.shop_mng_system.repository;

import com.example.shop_mng_system.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByUserIdAndDate(Long userId, LocalDate date);

    List<Bill> findByDate(LocalDate date);

    // Custom query to find bills by user ID and year/month
    @Query("SELECT b FROM Bill b WHERE b.user.id = :userId AND YEAR(b.date) = :year AND MONTH(b.date) = :month")
    List<Bill> findByUserIdAndYearMonth(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);
}
