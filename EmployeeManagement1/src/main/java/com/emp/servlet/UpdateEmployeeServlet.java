package com.emp.servlet;

import java.io.IOException;

import com.emp.dao.EmployeeDAO;
import com.emp.model.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateEmployee")
public class UpdateEmployeeServlet extends HttpServlet {
 
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
 
        Employee e = new Employee();
        e.setId(Integer.parseInt(req.getParameter("id")));
        e.setName(req.getParameter("name"));
        e.setEmail(req.getParameter("email"));
        e.setSalary(Double.parseDouble(req.getParameter("salary")));
 
        EmployeeDAO dao = new EmployeeDAO();
        dao.updateEmployee(e);
 
        res.sendRedirect("viewEmployee.jsp");
    }
}
