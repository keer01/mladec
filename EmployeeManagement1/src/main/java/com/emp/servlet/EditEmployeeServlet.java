package com.emp.servlet;

import java.io.IOException;

import com.emp.dao.EmployeeDAO;
import com.emp.model.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editEmployee")
public class EditEmployeeServlet extends HttpServlet {
 
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
 
        int id = Integer.parseInt(req.getParameter("id"));
 
        EmployeeDAO dao = new EmployeeDAO();
        Employee emp = dao.getEmployeeById(id);
 
        req.setAttribute("emp", emp);
        req.getRequestDispatcher("editEmployee.jsp").forward(req, res);
    }
}