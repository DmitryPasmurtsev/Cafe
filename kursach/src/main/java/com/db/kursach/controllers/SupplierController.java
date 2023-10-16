package com.db.kursach.controllers;
import com.db.kursach.models.Supplier;
import com.db.kursach.services.SupplierService;
import com.db.kursach.services.impl.SupplierServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping("/suppliers")
    public ResponseEntity<List<Supplier>> suppliers() {
         List<Supplier> suppliers =  supplierService.listSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/suppliers/{id}")
    public ResponseEntity<Supplier> supplierInfo(@PathVariable Long id){
        Supplier supplier = supplierService.getSupplierById(id);
        return ResponseEntity.ok(supplier);
    }

    @PostMapping("/suppliers")
    public ResponseEntity<String> createSupplier(@RequestBody Supplier supplier) {
        supplierService.saveSupplier(supplier);
        return ResponseEntity.ok("Добавлен поставщик " + supplier.getName());
    }
    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id){
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok("Удален поставщик " + id);
    }
    @PutMapping("/suppliers/{id}")
    public ResponseEntity<String> editSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
        supplierService.editSupplier(id,supplier);
        return ResponseEntity.ok("Изменен поставщик " + id);
    }

}
