package com.example.shop_mng_system.service.serviceImpl;

import com.example.shop_mng_system.entity.Bill;
import com.example.shop_mng_system.repository.BillRepository;
import com.example.shop_mng_system.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Override
    public Bill getBill(Long id) {
        return billRepository.findById(id).orElse(null);
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill addBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill updateBill(Long id, Bill bill) {
        Bill existingBill = billRepository.findById(id).orElse(null);
        if (existingBill != null) {
            existingBill.setProducts(bill.getProducts());
            existingBill.setAmount(bill.getAmount());
            return billRepository.save(existingBill);
        }
        return null;
    }

    @Override
    public boolean deleteBill(Long id) {
        try {
            billRepository.deleteById(id);
            return true; // Return true if deletion is successful
        } catch (Exception e) {
            // Log the exception or handle it gracefully
            return false; // Return false if an error occurs during deletion
        }
    }

    @Override
    public Double calculateDayBalanceForUser(Long userId, LocalDate date) {
        List<Bill> bills = billRepository.findByUserIdAndDate(userId, date);
        Double dayBalance = 0.0;
        for (Bill bill : bills) {
            dayBalance += bill.getAmount();
        }
        return dayBalance;
    }
}
