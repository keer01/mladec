package com.emp.dao;



import java.util.List;
import com.emp.entity.Employee;
 
public interface EmployeeDAO {
 
    Employee addEmployee(Employee emp);
 
    Employee updateEmployee(Employee emp);
 
    List<Employee> deleteEmployee(int id);
 
    List<Employee> getAllEmployees();
}
