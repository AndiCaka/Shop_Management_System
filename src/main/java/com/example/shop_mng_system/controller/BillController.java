package com.example.shop_mng_system.controller;

import com.example.shop_mng_system.entity.Bill;
import com.example.shop_mng_system.entity.Product;
import com.example.shop_mng_system.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

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
     * @param selectedProducts The list of products to be added to the bill.
     * @return ResponseEntity containing the created bill.
     */
    @PostMapping("/addBill")
    public ResponseEntity<Bill> addBill(@RequestBody List<Product> selectedProducts){
        // Calculate total amount
        Double totalAmount = 0.0;
        for (Product product : selectedProducts) {
            totalAmount += product.getPrice();
        }

        // Create a new Bill object
        Bill bill = new Bill();
        bill.setProducts(selectedProducts);
        bill.setAmount(totalAmount);

        try {
            // Attempt to add the bill
            Bill createdBill = billService.addBill(bill);
            return ResponseEntity.ok(createdBill);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); // Return 400 if there's an error
        }
    }

    /**
     * Update an existing bill.
     *
     * @param id   The ID of the bill to be updated.
     * @param bill The updated bill object.
     * @return ResponseEntity containing the updated bill.
     */
    @PutMapping("/updateBill/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody Bill bill){
        try {
            // Attempt to update the bill
            Bill updatedBill = billService.updateBill(id, bill);
            return ResponseEntity.ok(updatedBill);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); // Return 400 if there's an error
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
