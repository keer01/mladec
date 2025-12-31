package com.emp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.emp.dao.EmployeeDAO;
import com.emp.dao.EmployeeDAOImpl;
import com.emp.entity.Employee;
 
public class EmployeeDAOTest {
 
    EmployeeDAO dao = new EmployeeDAOImpl();
 
    // Positive test
    @Test
    void testAddEmployee() {
        Employee emp = new Employee(0, "Keer", 25000, "IT");
        assertNotNull(dao.addEmployee(emp));
    }
 
    // Negative test
    @Test
    void testUpdateInvalidEmployee() {
        Employee emp = new Employee(999, "", 0, "");
        assertNull(dao.updateEmployee(emp));
    }
 
    @Test
    void testGetAllEmployees() {
        List<Employee> list = dao.getAllEmployees();
        assertTrue(list.size() >= 0);
    }
 
    @Test
    void testDeleteEmployee() {
        List<Employee> list = dao.deleteEmployee(1);
        assertNotNull(list);
    }
}
 