package com.db.kursach.controllers;

import com.db.kursach.models.viewsModels.WellPaidEmployee;
import com.db.kursach.repositories.viewsRepository.WellPaidEmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class AppController {
    private final WellPaidEmployeesRepository wellPaidEmployeesRepository;

    @GetMapping("/views")
    public ResponseEntity<List<WellPaidEmployee>> wellPaidEmployees(){
        List<WellPaidEmployee> wellPaidEmployees = wellPaidEmployeesRepository.findAll();
        return ResponseEntity.ok(wellPaidEmployees);
    }
}
