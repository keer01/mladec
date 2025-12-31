package com.emp.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
 
import com.emp.entity.Employee;
import com.emp.util.DBUtil;
 
public class EmployeeDAOImpl implements EmployeeDAO {
 
    @Override
    public Employee addEmployee(Employee emp) {
        String sql = "INSERT INTO employee(name, salary, department) VALUES(?,?,?)";
 
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setString(1, emp.getName());
            ps.setDouble(2, emp.getSalary());
            ps.setString(3, emp.getDepartment());
 
            ps.executeUpdate();
            return emp;
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    @Override
    public Employee updateEmployee(Employee emp) {
        String sql = "UPDATE employee SET name=?, salary=?, department=? WHERE id=?";
 
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setString(1, emp.getName());
            ps.setDouble(2, emp.getSalary());
            ps.setString(3, emp.getDepartment());
            ps.setInt(4, emp.getId());
 
            int rows = ps.executeUpdate();
            return rows > 0 ? emp : null;
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    @Override
    public List<Employee> deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id=?";
 
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setInt(1, id);
            ps.executeUpdate();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getAllEmployees();
    }
 
    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee";
 
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
 
            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("salary"),
                        rs.getString("department")
                );
                list.add(emp);
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
 