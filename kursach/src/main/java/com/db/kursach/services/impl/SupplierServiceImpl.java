package com.db.kursach.services.impl;
import com.db.kursach.exceptions.NotFoundException;
import com.db.kursach.models.Supplier;
import com.db.kursach.repositories.SupplierRepository;
import com.db.kursach.services.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor

public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    public List<Supplier> listSuppliers(){
        return supplierRepository.findAll();
    }
    public Supplier getSupplierById(Long id) {
        if (supplierRepository.findById(id).isEmpty())
            throw new NotFoundException("Поставщик отсутствует");
        return supplierRepository.findById(id).get();
    }
    public void saveSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }
    public void deleteSupplier(Long id){
        supplierRepository.deleteById(id);
    }
    public void editSupplier(Long id, Supplier supplier){
        if (supplierRepository.findById(id).isEmpty())
            throw new NotFoundException("Поставщик отсутствует");
        Supplier supplierToEdit=supplierRepository.findById(id).get();
        editingSupplier(supplier,supplierToEdit);
        supplierRepository.save(Objects.requireNonNull(supplierRepository.findById(id).orElse(null)));
    }
    private void editingSupplier(Supplier supplier,Supplier supplierToEdit){
        supplierToEdit.setPhone(supplier.getPhone());
        supplierToEdit.setCity(supplier.getCity());
        supplierToEdit.setCountry(supplier.getCountry());
        supplierToEdit.setAddress(supplier.getAddress());
        supplierToEdit.setName(supplier.getName());
    }
}
