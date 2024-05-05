package com.example.shop_mng_system.service.serviceImpl;

import com.example.shop_mng_system.entity.Bill;
import com.example.shop_mng_system.entity.Product;
import com.example.shop_mng_system.entity.User;
import com.example.shop_mng_system.exception.ResourceNotFoundException;
import com.example.shop_mng_system.repository.BillRepository;
import com.example.shop_mng_system.repository.ProductRepository;
import com.example.shop_mng_system.repository.UserRepository;
import com.example.shop_mng_system.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

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
        // Retrieve the user entity from the database using the provided user ID
        User user = userRepository.findById(bill.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + bill.getUser().getId()));

        // Set the user for the bill
        bill.setUser(user);

        // Fetch product entities from the database using their IDs
        List<Long> productIds = bill.getProductIds();
        List<Product> products = productRepository.findAllById(productIds);

        // Check if all product IDs were found in the database
        if (products.size() != productIds.size()) {
            // Not all product IDs were found, throw a ResourceNotFoundException
            throw new ResourceNotFoundException("One or more products not found");
        }

        // Calculate total amount from the list of products
        Double totalAmount = calculateTotalAmount(products);
        bill.setAmount(totalAmount);

        // Set the current date as the bill date
        bill.setDate(LocalDate.now());

        // Save the bill to the database
        return billRepository.save(bill);
    }

    // Helper method to calculate the total amount from a list of products
    private Double calculateTotalAmount(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    @Override
    public Bill updateBill(Long id, Bill bill) {
        // Retrieve the existing bill from the database
        Bill existingBill = billRepository.findById(id).orElse(null);
        if (existingBill == null) {
            throw new ResourceNotFoundException("Bill not found with id: " + id);
        }

        // Fetch product entities from the database using their IDs
        List<Long> productIds = bill.getProductIds();
        List<Product> products = productRepository.findAllById(productIds);

        // Check if all product IDs were found in the database
        if (products.size() != productIds.size()) {
            // Not all product IDs were found, throw a ResourceNotFoundException
            throw new ResourceNotFoundException("One or more products not found");
        }

        // Calculate total amount from the list of products
        Double totalAmount = calculateTotalAmount(products);

        // Update bill properties
        existingBill.setProductIds(productIds);
        existingBill.setAmount(totalAmount);
        existingBill.setDate(bill.getDate());

        // Retrieve the user entity from the database using the provided user ID
        User user = userRepository.findById(bill.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + bill.getUser().getId()));
        existingBill.setUser(user);

        // Attempt to update the bill
        return billRepository.save(existingBill);
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

    @Override
    public Double calculateDayBalance(LocalDate date) {
        List<Bill> bills = billRepository.findByDate(date);
        Double dayBalance = 0.0;
        for (Bill bill : bills) {
            dayBalance += bill.getAmount();
        }
        return dayBalance;
    }
}
