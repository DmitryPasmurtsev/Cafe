package com.db.kursach.controllers;
import com.db.kursach.models.Delivery;
import com.db.kursach.services.DeliveryService;
import com.db.kursach.services.impl.DeliveryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class DeliveryController {
    private final DeliveryService deliveryService;

    @GetMapping("/deliveries")
    public ResponseEntity<List<Delivery>> deliveries(){
        List<Delivery> deliveries = deliveryService.listDeliveries();
        deliveries.sort(Comparator.comparing(Delivery::getDate).reversed());
        return ResponseEntity.ok(deliveries);
    }

    @GetMapping("/deliveries/{id}")
    public ResponseEntity<Delivery> deliveryInfo(@PathVariable Long id) {
        Delivery delivery = deliveryService.getDeliveryById(id);
        return ResponseEntity.ok(delivery);
    }
    @PostMapping("/deliveries")
    public ResponseEntity<String> createDelivery(@RequestBody Delivery delivery){
        deliveryService.saveDelivery(delivery);
        return ResponseEntity.ok("Добавлена поставка на " + delivery.getDate());
    }
    @DeleteMapping("/deliveries/{id}")
    public ResponseEntity<String> deleteDelivery(@PathVariable Long id){
        deliveryService.deleteDelivery(id);
        return ResponseEntity.ok("Удалена поставка с id " + id);
    }
    @PutMapping("/deliveries/{id}")
    public ResponseEntity<String> editDelivery(@PathVariable Long id, @RequestBody Delivery updatedDelivery)
    {
        deliveryService.editDelivery(id,updatedDelivery);
        return ResponseEntity.ok("Изменена поставка с id " + id);
    }



}
