package com.example.shop_mng_system.controller;

import com.example.shop_mng_system.entity.Bill;
import com.example.shop_mng_system.exception.ResourceNotFoundException;
import com.example.shop_mng_system.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    /**
     * Get the day balance for a specific user on a given date.
     *
     * @param userId The ID of the user for whom the day balance is calculated.
     * @param date   The date for which the day balance is calculated.
     * @return ResponseEntity containing the day balance as a Double.
     */
    @GetMapping("/user/{userId}/dayBalance")
    public ResponseEntity<Double> getUserDayBalance(@PathVariable Long userId,
                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Double dayBalance = billService.calculateDayBalanceForUser(userId, date);
        return ResponseEntity.ok(dayBalance);
    }

    /**
     * Get the day balance for a specific user on a given month.
     *
     * @param userId The ID of the user for whom the day balance is calculated.
     * @param month   The date for which the day balance is calculated.
     * @return ResponseEntity containing the day balance as a Double.
     */
    @GetMapping("/user/{userId}/monthBalance")
    public ResponseEntity<Double> getUserMouthBalance(@PathVariable Long userId,
                                                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
        Double dayBalance = billService.calculateMonthBalanceForUser(userId, month);
        return ResponseEntity.ok(dayBalance);
    }

    /**
     * Get the day balance for all users on a given date.
     *
     * @param date   The date for which the day balance is calculated.
     * @return ResponseEntity containing the day balance as a Double.
     */
    @GetMapping("/dayBalance")
    public ResponseEntity<Double> getDayBalance(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Double dayBalance = billService.calculateDayBalance(date);
        return ResponseEntity.ok(dayBalance);
    }

    /**
     * Get the day balance for all users on a given month.
     *
     * @param month   The date for which the day balance is calculated.
     * @return ResponseEntity containing the day balance as a Double.
     */
    @GetMapping("/monthBalance")
    public ResponseEntity<Double> getMonthBalance(@RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
        Double dayBalance = billService.calculateMonthBalance(month);
        return ResponseEntity.ok(dayBalance);
    }

    /**
     * Get a bill by its ID.
     *
     * @param id The ID of the bill to retrieve.
     * @return ResponseEntity containing the retrieved bill.
     */
    @GetMapping("/getBill/{id}")
    public ResponseEntity<Bill> getBill(@PathVariable Long id){

        Bill bill = billService.getBill(id);
        if (bill == null) {
            return ResponseEntity.notFound().build(); // Return 404 if bill is not found
        }
        return ResponseEntity.ok(bill);
    }

    /**
     * Get all bills.
     *
     * @return ResponseEntity containing a list of all bills.
     */
    @GetMapping("/getAllBills")
    public ResponseEntity<List<Bill>> getAllBills(){
        List<Bill> bills = billService.getAllBills();
        return ResponseEntity.ok(bills);
    }

    /**
     * Add a new bill.
     *
     * @param bill The bill object containing user, products, amount, and date.
     * @return ResponseEntity containing the added bill or an error response.
     */
    @PostMapping("/addBill")
    public ResponseEntity<Bill> addBill(@RequestBody Bill bill) {
        try {
            // Delegate the creation logic to the service layer
            Bill createdBill = billService.addBill(bill);
            return ResponseEntity.ok(createdBill);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); // Return 400 if there's an error
        }
    }

    /**
     * Update an existing bill with the provided ID.
     *
     * @param id   The ID of the bill to be updated.
     * @param bill The updated bill data including products, amount, date, and userId.
     * @return ResponseEntity containing the updated bill or an error response.
     */
    @PutMapping("/updateBill/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody Bill bill){
        try {
            Bill updatedBill = billService.updateBill(id, bill);
            return ResponseEntity.ok(updatedBill);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build(); // Return 404 if bill or user not found
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); // Return 400 for other errors
        }
    }

    /**
     * Delete a bill by its ID.
     *
     * @param id The ID of the bill to be deleted.
     */
    @DeleteMapping("/deleteBill/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id){
        boolean deleted = billService.deleteBill(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // Return 204 if bill is successfully deleted
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if bill is not found
        }
    }
}
