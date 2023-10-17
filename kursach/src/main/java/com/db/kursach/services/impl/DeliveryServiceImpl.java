package com.db.kursach.services.impl;

import com.db.kursach.exceptions.NotFoundException;
import com.db.kursach.models.Delivery;
import com.db.kursach.repositories.DeliveryRepository;
import com.db.kursach.services.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor

public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public List<Delivery> listDeliveries(){
        return deliveryRepository.findAll();
    }

    public Delivery getDeliveryById(Long id) {
        if (deliveryRepository.findById(id).isEmpty())
            throw new NotFoundException("Поставка отсутствует");
        return deliveryRepository.findById(id).get();
    }
    public void saveDelivery(Delivery delivery) {

        deliveryRepository.save(delivery);
    }
    public void deleteDelivery(Long id){
        deliveryRepository.deleteById(id);
    }
    public void editDelivery(Long id, Delivery delivery){
        if (deliveryRepository.findById(id).isEmpty())
            throw new NotFoundException("Поставка отсутствует");
        Delivery deliveryToEdit=deliveryRepository.findById(id).get();
        editingDelivery(delivery,deliveryToEdit);
        deliveryRepository.save(Objects.requireNonNull(deliveryRepository.findById(id).orElse(null)));
    }
    private void editingDelivery(Delivery delivery,Delivery deliveryToEdit){
        deliveryToEdit.setDate(delivery.getDate());
        deliveryToEdit.setEmployee(delivery.getEmployee());
        deliveryToEdit.setAmount(delivery.getAmount());
        deliveryToEdit.setProduct(delivery.getProduct());
        deliveryToEdit.setSupplier(delivery.getSupplier());
    }
}
