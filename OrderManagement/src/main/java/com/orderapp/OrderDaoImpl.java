package com.orderapp;



import com.orderapp.Order;
import com.orderapp.DBUtil;
import com.orderapp.OrderDao;
 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
 
public class OrderDaoImpl implements OrderDao {
 
    @Override
    public boolean addOrder(Order order) {
        String sql = "INSERT INTO orders(customer_name, product, quantity, price) VALUES(?,?,?,?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setString(1, order.getCustomerName());
            ps.setString(2, order.getProduct());
            ps.setInt(3, order.getQuantity());
            ps.setDouble(4, order.getPrice());
 
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
 
    @Override
    public boolean updateOrder(Order order) {
        String sql = "UPDATE orders SET quantity=?, price=? WHERE order_id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setInt(1, order.getQuantity());
            ps.setDouble(2, order.getPrice());
            ps.setInt(3, order.getOrderId());
 
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
 
    @Override
    public boolean deleteOrder(int orderId) {
        String sql = "DELETE FROM orders WHERE order_id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setInt(1, orderId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
 
    @Override
    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM orders";
 
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
 
            while (rs.next()) {
                Order o = new Order();
                o.setOrderId(rs.getInt("order_id"));
                o.setCustomerName(rs.getString("customer_name"));
                o.setProduct(rs.getString("product"));
                o.setQuantity(rs.getInt("quantity"));
                o.setPrice(rs.getDouble("price"));
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
 
