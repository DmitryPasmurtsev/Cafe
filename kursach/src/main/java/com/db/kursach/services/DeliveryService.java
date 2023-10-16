package com.db.kursach.services;

import com.db.kursach.models.Delivery;

import java.util.List;

public interface DeliveryService {
    List<Delivery> listDeliveries();

    Delivery getDeliveryById(Long id);

    void saveDelivery(Delivery delivery);

    void deleteDelivery(Long id);

    void editDelivery(Long id, Delivery updatedDelivery);
}
