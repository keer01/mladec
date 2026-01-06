<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="com.emp.model.Employee" %>
<%
Employee e = (Employee) request.getAttribute("emp");
%>
 
<html>
<body>
<h2>Edit Employee</h2>
 
<form action="updateEmployee" method="post">
    <input type="hidden" name="id" value="<%= e.getId() %>">
 
    Name: <input type="text" name="name" value="<%= e.getName() %>"><br><br>
    Email: <input type="text" name="email" value="<%= e.getEmail() %>"><br><br>
    Salary: <input type="text" name="salary" value="<%= e.getSalary() %>"><br><br>
 
    <input type="submit" value="Update">
</form>
</body>
</html>