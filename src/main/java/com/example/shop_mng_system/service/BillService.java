package com.example.shop_mng_system.service;

import com.example.shop_mng_system.entity.Bill;

import java.time.LocalDate;
import java.util.List;

public interface BillService {
    Bill getBill(Long id);

    List<Bill> getAllBills();

    Bill addBill(Bill bill);

    Bill updateBill(Long id, Bill bill);

    boolean deleteBill(Long id);

    Double calculateDayBalanceForUser(Long userId, LocalDate date);

    Double calculateDayBalance(LocalDate date);
}
