package com.db.kursach.services;

import com.db.kursach.models.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> listSuppliers();

    Supplier getSupplierById(Long id);

    void saveSupplier(Supplier supplier);

    void deleteSupplier(Long id);

    void editSupplier(Long id, Supplier supplier);
}
