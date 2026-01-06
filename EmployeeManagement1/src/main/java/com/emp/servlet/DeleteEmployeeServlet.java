package com.emp.servlet;

import java.io.IOException;

import com.emp.dao.EmployeeDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {
 
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
 
        int id = Integer.parseInt(req.getParameter("id"));
 
        EmployeeDAO dao = new EmployeeDAO();
        dao.deleteEmployee(id);
 
        res.sendRedirect("viewEmployee.jsp");
    }
}
 
