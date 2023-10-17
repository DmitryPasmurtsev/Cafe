package com.db.kursach.controllers;

import com.db.kursach.dto.EmployeeDTO;
import com.db.kursach.dto.PositionDTO;
import com.db.kursach.models.Employee;
import com.db.kursach.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    @GetMapping("/employees")
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

    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee=modelMapper.map(employeeDTO, Employee.class);
        employeeService.saveEmployee(employee);
        return ResponseEntity.ok("Добавлен работник " + employee.getFullName());
    }
   /* @PostMapping("/employee/{id}/trash") //переименовать
    public String createImage(@RequestParam("file") MultipartFile file,@PathVariable Long id)throws IOException{
        employeeService.saveImage(file,id);
        return "redirect:/employee/{id}";
    } //вопросы начинаются с самого названия метода)))*/
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Удален работник с id " + id);
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> employeeInfo(@PathVariable Long id){
        EmployeeDTO employee=modelMapper.map(employeeService.getEmployeeById(id), EmployeeDTO.class);
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
    public ResponseEntity<String> editEmployee(@PathVariable Long id,@RequestBody EmployeeDTO employeeDTO)
    {
        Employee updatedEmployee=modelMapper.map(employeeDTO, Employee.class);
        employeeService.editEmployee(id, updatedEmployee);
        return ResponseEntity.ok("Изменен работник с id " + id);
    }

}
