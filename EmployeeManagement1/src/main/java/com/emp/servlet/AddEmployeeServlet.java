package com.emp.servlet;
 
import com.emp.dao.EmployeeDAO;
import com.emp.model.Employee;
 
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
 
import java.io.IOException;
 
@WebServlet("/addEmployee")
public class AddEmployeeServlet extends HttpServlet {
 
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
 
        Employee emp = new Employee();
        emp.setName(req.getParameter("name"));
        emp.setEmail(req.getParameter("email"));
        emp.setSalary(Double.parseDouble(req.getParameter("salary")));
 
        EmployeeDAO dao = new EmployeeDAO();
        dao.saveEmployee(emp);
 
        res.sendRedirect("viewEmployee.jsp");
    }
}