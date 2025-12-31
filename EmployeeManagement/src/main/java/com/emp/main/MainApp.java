package com.emp.main;

import java.util.List;
import java.util.Scanner;

import com.emp.dao.EmployeeDAO;
import com.emp.dao.EmployeeDAOImpl;
import com.emp.entity.Employee;

public class MainApp {

   public static void main(String[] args) {

       EmployeeDAO dao = new EmployeeDAOImpl();
       Scanner sc = new Scanner(System.in);

       System.out.println("1.Add  2.Update  3.Delete  4.View All");
       int choice = sc.nextInt();

       switch (choice) {

       case 1:
           System.out.println("Enter name salary department");
           Employee emp = new Employee(0, sc.next(), sc.nextDouble(), sc.next());
           dao.addEmployee(emp);
           break;

       case 2:
           System.out.println("Enter id name salary department");
           Employee emp2 = new Employee(sc.nextInt(), sc.next(), sc.nextDouble(), sc.next());
           dao.updateEmployee(emp2);
           break;

       case 3:
           System.out.println("Enter id");
           List<Employee> list = dao.deleteEmployee(sc.nextInt());
           list.forEach(System.out::println);
           break;

       case 4:
           dao.getAllEmployees().forEach(System.out::println);
           break;

       default:
           System.out.println("Invalid choice");
       }
       sc.close();
   }
}
