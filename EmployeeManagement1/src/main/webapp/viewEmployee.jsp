<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ page import="java.util.*,com.emp.dao.EmployeeDAO,com.emp.model.Employee" %>
 
<html>
<head><title>View Employees</title></head>
<body>
 
<h2>Employee List</h2>
 
<table border="1">
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Email</th>
    <th>Salary</th>
</tr>
 
<%
EmployeeDAO dao = new EmployeeDAO();
List<Employee> list = dao.getAllEmployees();
for(Employee e : list) {
%>
<tr>
    <td><%= e.getId() %></td>
    <td><%= e.getName() %></td>
    <td><%= e.getEmail() %></td>
    <td><%= e.getSalary() %></td>
    <td>
        <a href="editEmployee?id=<%= e.getId() %>">Edit</a>
        <a href="deleteEmployee?id=<%= e.getId() %>">Delete</a>
    </td>
</tr><% } %>
 
</table>
 
</body>
</html>