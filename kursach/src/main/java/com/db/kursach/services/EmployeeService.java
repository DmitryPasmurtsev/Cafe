package com.db.kursach.services;

import com.db.kursach.models.Employee;
import com.db.kursach.models.Position;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {
    List<Employee> listEmployees();

    List<Position> listPositions();

    void saveEmployee(Employee employee);

    void deleteEmployee(Long id);

    Employee getEmployeeById(Long id);

    void editEmployee(Long id, Employee updatedEmployee);

    void updateImage(Long id, String linkToImage);
}
