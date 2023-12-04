package com.db.kursach.services.impl;

import com.db.kursach.exceptions.NotCreatedException;
import com.db.kursach.exceptions.NotFoundException;
import com.db.kursach.models.Employee;
import com.db.kursach.models.Position;
import com.db.kursach.repositories.EmployeeRepository;
import com.db.kursach.repositories.PositionRepository;
import com.db.kursach.services.EmployeeService;
import com.db.kursach.services.MailSenderService;
import com.db.kursach.services.impl.AuthServiceImpl;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final MailSenderService mailSenderService;
    private final AuthServiceImpl authService;

    public List<Employee> listEmployees(){
        return employeeRepository.findAll();
    }

    public void saveEmployee(Employee employee) {
        if(employeeRepository.findByEmail(employee.getEmail())!=null){
            throw new NotCreatedException("Работник с такой почтой уже существует","email");
        }
        if (employeeRepository.findByPhone(employee.getPhone())!=null){
            throw new NotCreatedException("Работник с таким номером телефона уже существует","phone");
        }
        Thread thread = new Thread(() -> sendEmail(employee.getFullName(), employee.getEmail()));
        thread.start();
        employeeRepository.save(employee);
    }

    private void sendEmail(String employeeFullName, String employeeEmail) {
        mailSenderService.sendEmail(employeeEmail, "Сообщение о принятии на работу", employeeFullName);
    }

    public void deleteImage(Long id){
        Employee employee=getEmployeeById(id);
        employee.setLinkToImage(null);
    }
    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    public Employee getEmployeeById(Long id) {
        if (employeeRepository.findById(id).isEmpty())
            throw new NotFoundException("Работник с id = " +id +" отсутствует");
        return employeeRepository.findById(id).get();
    }
    public void editEmployee(Long id, Employee employee){
        if (employeeRepository.findById(id).isEmpty())
            throw new NotFoundException("Работник с id = " +id +" отсутствует");
        if(employeeRepository.findByPhone(employee.getPhone())!=null&& !Objects.equals(employeeRepository.findByPhone(employee.getPhone()).getId(), id))
            throw new NotCreatedException("Работник с таким номером уже существует","phone");
        if(employeeRepository.findByEmail(employee.getEmail())!=null&& !Objects.equals(employeeRepository.findByEmail(employee.getEmail()).getId(), id))
            throw new NotCreatedException("Работник с такой почтой уже существует","email");
        Employee employeeToEdit=employeeRepository.findById(id).get();
        editingEmployee(employee,employeeToEdit);
        //if (employeeToEdit.getUser()!=null) employeeToEdit.setUser(authService.setUserRole(employeeToEdit, employeeToEdit.getUser()));
        employeeRepository.save(Objects.requireNonNull(employeeRepository.findById(id).orElse(null)));
    }

    @Override
    public void updateImage(Long id, String linkToImage) {
        Employee employee = getEmployeeById(id);
        employee.setLinkToImage(linkToImage);
        employeeRepository.save(employee);
    }

    private void editingEmployee(Employee newEmployee,Employee oldEmployee){
        oldEmployee.setDate(newEmployee.getDate());
        oldEmployee.setExperience(newEmployee.getExperience());
        oldEmployee.setFullName(newEmployee.getFullName());
        oldEmployee.setPhone(newEmployee.getPhone());
        oldEmployee.setPosition(newEmployee.getPosition());
        oldEmployee.setEmail(newEmployee.getEmail());
        if (oldEmployee.getUser()!=null) {
            oldEmployee.setUser(authService.setUserRole(oldEmployee, oldEmployee.getUser()));
            oldEmployee.getUser().setEmail(newEmployee.getEmail());
        }
        oldEmployee.setSalary(newEmployee.getSalary());
    }

    public List<Position> listPositions() {
        return positionRepository.findAll();
    }
}
