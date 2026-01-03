package com.orderapp;



import com.orderapp.Order;
import java.util.List;
 
public interface OrderDao {
 
    boolean addOrder(Order order);
    boolean updateOrder(Order order);
    boolean deleteOrder(int orderId);
    List<Order> getAllOrders();
}
 
