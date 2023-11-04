package com.db.kursach.services.impl;

import com.db.kursach.exceptions.NotCreatedException;
import com.db.kursach.exceptions.NotFoundException;
import com.db.kursach.models.Employee;
import com.db.kursach.models.Position;
import com.db.kursach.repositories.EmployeeRepository;
import com.db.kursach.repositories.PositionRepository;
import com.db.kursach.services.EmployeeService;
//import com.db.kursach.services.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;

    //private final AuthServiceImpl authService;

    public List<Employee> listEmployees(){
        return employeeRepository.findAll();
    }
    public void saveEmployee(Employee employee) {
        if(employeeRepository.findByEmail(employee.getEmail())!=null){
            throw new NotCreatedException("Работник с такой почтой уже существует");
        }
        if(employeeRepository.findByPhone(employee.getPhone())!=null){
            throw new NotCreatedException("Работник с таким номером телефона уже существует");
        }
        employeeRepository.save(employee);
    }
    public void saveImage(MultipartFile file,Long id) throws IOException {
        if (employeeRepository.findById(id).isEmpty())
            throw new NotFoundException("Работник отсутствует");
        Employee employee=employeeRepository.findById(id).get();
        if(!file.isEmpty()) {
            employee.setImage_bytes(file.getBytes());
            employeeRepository.save(employee);
        }
    }

    public void deleteImage(Long id){
        /*Employee employee=getEmployeeById(id);
        employee.setImage_bytes(null);*/
    }
    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    public Employee getEmployeeById(Long id) {
        if (employeeRepository.findById(id).isEmpty())
            throw new NotFoundException("Работник отсутствует");
        return employeeRepository.findById(id).get();
    }
    public void editEmployee(Long id, Employee employee){
        if (employeeRepository.findById(id).isEmpty())
            throw new NotFoundException("Работник отсутствует");
        Employee employeeToEdit=employeeRepository.findById(id).get();
        editingEmployee(employee,employeeToEdit);
        //if (employeeToEdit.getUser()!=null) employeeToEdit.setUser(authService.setUserRole(employeeToEdit, employeeToEdit.getUser()));
        employeeRepository.save(Objects.requireNonNull(employeeRepository.findById(id).orElse(null)));
    }
    private void editingEmployee(Employee employee,Employee employeeToEdit){
        employeeToEdit.setDate(employee.getDate());
        employeeToEdit.setImage_bytes(employeeToEdit.getImage_bytes());
        employeeToEdit.setExperience(employee.getExperience());
        employeeToEdit.setFullName(employee.getFullName());
        employeeToEdit.setPhone(employee.getPhone());
        employeeToEdit.setPosition(employee.getPosition());
        employeeToEdit.setEmail(employee.getEmail());
       // employeeToEdit.getUser().setEmail(employee.getEmail());
        employeeToEdit.setSalary(employee.getSalary());
    }

    public List<Position> listPositions() {
        return positionRepository.findAll();
    }
}
