package com.db.kursach.controllers;

import com.db.kursach.models.Employee;
import com.db.kursach.models.Position;
import com.db.kursach.models.Product;
import com.db.kursach.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> employees(@RequestParam(name = "fullName",required = false) String fullName){
        List<Employee> employees = employeeService.listEmployees(null);
        return ResponseEntity.ok(employees);
    }
    @GetMapping("/positions")
    public ResponseEntity<List<Position>> positions(){
        List<Position> positions = employeeService.listPositions();
        return ResponseEntity.ok(positions);
    }

    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return ResponseEntity.ok("Добавлен работник " + employee.getFullName());
    }
    @PostMapping("/employee/{id}/trash")
    public String createImage(@RequestParam("file") MultipartFile file,@PathVariable Long id)throws IOException{
        employeeService.saveImage(file,id);
        return "redirect:/employee/{id}";
    }
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Удален работник с id " + id);
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> employeeInfo(@PathVariable Long id){
        Employee employee=employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/employee/{id}/image")
    private ResponseEntity<?> getEmployeeImage(@PathVariable Long id){
        Employee employee=employeeService.getEmployeeById(id);
        return ResponseEntity.ok()
                .header("title",employee.getFullName())
                .body(new InputStreamResource(new ByteArrayInputStream(employee.getImage_bytes())));
    }
    @PutMapping("/employees/{id}")
    public ResponseEntity<String> editEmployee(@PathVariable Long id,@RequestBody Employee updatedEmployee)
    {
        employeeService.editEmployee(id, updatedEmployee);
        return ResponseEntity.ok("Изменен работник с id " + id);
    }

}
