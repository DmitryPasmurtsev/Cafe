package com.db.kursach.controllers;

import com.db.kursach.dto.EmployeeDTO;
import com.db.kursach.dto.ImageDTO;
import com.db.kursach.dto.PositionDTO;
import com.db.kursach.dto.UserDTO;
import com.db.kursach.dto.auth.AuthResponse;
import com.db.kursach.models.Employee;
import com.db.kursach.services.EmployeeService;
import com.db.kursach.services.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;
    private final AuthServiceImpl authService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> employees(){
        List<EmployeeDTO> employeeDTOs=employeeService.listEmployees().stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeDTOs);
    }

    @GetMapping("/positions")
    public ResponseEntity<List<PositionDTO>> positions(){
        List<PositionDTO> positionDTOs=employeeService.listPositions().stream()
                .map(position -> modelMapper.map(position, PositionDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(positionDTOs);
    }

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee=modelMapper.map(employeeDTO, Employee.class);
        employeeService.saveEmployee(employee);
        return ResponseEntity.ok("Добавлен работник " + employee.getFullName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Удален работник с id " + id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> employeeInfo(@PathVariable Long id){
        EmployeeDTO employee=modelMapper.map(employeeService.getEmployeeById(id), EmployeeDTO.class);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editEmployee(@PathVariable Long id,@RequestBody EmployeeDTO employeeDTO)
    {
        Employee updatedEmployee=modelMapper.map(employeeDTO, Employee.class);
        employeeService.editEmployee(id, updatedEmployee);
        return ResponseEntity.ok("Изменен работник с id " + id);
    }
    @PutMapping("/{id}/image")
    public ResponseEntity<String> deleteImage(@PathVariable Long id){
        employeeService.deleteImage(id);
        return ResponseEntity.ok("Картинка работника с id " + id+"удалена");
    }

    @PutMapping("/{id}/updateImage")
    public ResponseEntity<AuthResponse> updateImage(@PathVariable Long id,@RequestBody ImageDTO linkToImage)
    {
        System.out.println(linkToImage);
        employeeService.updateImage(id, linkToImage.getLinkToImage());
        UserDTO userDTO = modelMapper.map(employeeService.getEmployeeById(id).getUser(), UserDTO.class);
        AuthResponse response = new AuthResponse("", userDTO);
        return ResponseEntity.ok(response);
    }

}
