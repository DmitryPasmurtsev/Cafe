package com.db.kursach.controllers;
import com.db.kursach.dto.SupplierDTO;
import com.db.kursach.models.Supplier;
import com.db.kursach.services.SupplierService;
import com.db.kursach.services.impl.SupplierServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class SupplierController {
    private final SupplierService supplierService;
    private final ModelMapper modelMapper;

    @GetMapping("/suppliers")
    public ResponseEntity<List<SupplierDTO>> suppliers() {
         List<SupplierDTO> suppliers =  supplierService.listSuppliers()
                 .stream().map(supplier -> modelMapper.map(supplier, SupplierDTO.class))
                 .collect(Collectors.toList());
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/suppliers/{id}")
    public ResponseEntity<SupplierDTO> supplierInfo(@PathVariable Long id){
        SupplierDTO supplier = modelMapper.map(supplierService.getSupplierById(id), SupplierDTO.class);
        return ResponseEntity.ok(supplier);
    }

    @PostMapping("/suppliers")
    public ResponseEntity<String> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        Supplier supplier=modelMapper.map(supplierDTO, Supplier.class);
        supplierService.saveSupplier(supplier);
        return ResponseEntity.ok("Добавлен поставщик " + supplier.getName());
    }
    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id){
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok("Удален поставщик " + id);
    }
    @PutMapping("/suppliers/{id}")
    public ResponseEntity<String> editSupplier(@PathVariable Long id, @RequestBody SupplierDTO supplierDTO) {
        Supplier supplier=modelMapper.map(supplierDTO, Supplier.class);
        supplierService.editSupplier(id,supplier);
        return ResponseEntity.ok("Изменен поставщик " + id);
    }

}
