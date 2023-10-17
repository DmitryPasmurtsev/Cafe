package com.db.kursach.controllers;
import com.db.kursach.dto.DeliveryDTO;
import com.db.kursach.models.Delivery;
import com.db.kursach.services.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class DeliveryController {
    private final DeliveryService deliveryService;
    private final ModelMapper modelMapper;

    @GetMapping("/deliveries")
    public ResponseEntity<List<DeliveryDTO>> deliveries(){
        List<Delivery> deliveries = deliveryService.listDeliveries();
        deliveries.sort(Comparator.comparing(Delivery::getDate).reversed());
        List<DeliveryDTO> deliveryDTO=deliveries.stream()
                .map(delivery -> modelMapper.map(delivery, DeliveryDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(deliveryDTO);
    }

    @GetMapping("/deliveries/{id}")
    public ResponseEntity<DeliveryDTO> deliveryInfo(@PathVariable Long id) {
        DeliveryDTO delivery = modelMapper.map(deliveryService.getDeliveryById(id), DeliveryDTO.class);
        return ResponseEntity.ok(delivery);
    }
    @PostMapping("/deliveries")
    public ResponseEntity<String> createDelivery(@RequestBody DeliveryDTO deliveryDTO){
        Delivery delivery=modelMapper.map(deliveryDTO, Delivery.class);
        deliveryService.saveDelivery(delivery);
        return ResponseEntity.ok("Добавлена поставка на " + delivery.getDate());
    }
    @DeleteMapping("/deliveries/{id}")
    public ResponseEntity<String> deleteDelivery(@PathVariable Long id){
        deliveryService.deleteDelivery(id);
        return ResponseEntity.ok("Удалена поставка с id " + id);
    }
    @PutMapping("/deliveries/{id}")
    public ResponseEntity<String> editDelivery(@PathVariable Long id, @RequestBody DeliveryDTO deliveryDTO)
    {
        Delivery updatedDelivery=modelMapper.map(deliveryDTO, Delivery.class);
        deliveryService.editDelivery(id,updatedDelivery);
        return ResponseEntity.ok("Изменена поставка с id " + id);
    }



}
