package com.company;

import java.sql.*;

public class CartProcess {
    String url;
    String username;
    String password;
    CartProcess() throws SQLException, ClassNotFoundException {
        this.url="jdbc:mysql://127.0.0.1:3306/CarBooking?autoReconnect=true&useSSL=false";
        this.username="root";
        this.password="Vector@2003";
        Class.forName("com.mysql.jdbc.Driver");
    }
    void showCars() throws SQLException {

        Connection con= DriverManager.getConnection(url,username,password);

        PreparedStatement ps=con.prepareStatement("Select * from Car");
        ResultSet rs=ps.executeQuery();
        System.out.format("+%4s+%20s+%10s+%10s+\n","----", "--------------------", "----------", "----------");
        System.out.format("|%4s|%20s|%10s|%10s|\n", "id","mfd By", "model", "price");
        System.out.format("+%4s+%20s+%10s+%10s+\n", "----","--------------------", "----------", "----------");


        while(rs.next())
        {
            String id= rs.getString(1);
            String manufacturedBy= rs.getString(2);
            String model= rs.getString(3);
            String price= rs.getString(4);
            System.out.format("|%4s|%20s|%10s|%10s|\n",id, manufacturedBy, model, price);
            System.out.format("+%4s+%20s+%10s+%10s+\n","----", "--------------------", "----------", "----------");

        }

    }
    void addToCart(String car_id,String userEmail) throws SQLException {
        Connection con= DriverManager.getConnection(url,username,password);
        PreparedStatement ps=con.prepareStatement("Select id from User where email=?");
        ps.setString(1,userEmail);
        ResultSet rs=ps.executeQuery();
        rs.next();
        String user_id=rs.getString(1);
        ps=con.prepareStatement("insert into CartDetails(car_id,user_id) values(?,?)");
        ps.setString(1,car_id);
        ps.setString(2,user_id);
        int response=-1;
        try {
            response = ps.executeUpdate();
        }catch (Exception ignored) { }
        if(response>0)
        {
            System.out.println("Car with id:"+car_id+" added to cart successfully");
        }
        else{
            System.out.println("Some error occurred -> try adding again");
        }
    }
    void showCart(String userEmail) throws SQLException {
       Connection con=DriverManager.getConnection(url,username,password);
        PreparedStatement ps=con.prepareStatement("Select id from User where email=?");
        ps.setString(1,userEmail);
        ResultSet rs=ps.executeQuery();
        rs.next();
        String user_id=rs.getString(1);
       ps= con.prepareStatement("select CartDetails.id,manufacturedBy,model,price from CartDetails inner" +
               " join Car on CartDetails.car_id=Car.id where user_id=?;");
       ps.setString(1,user_id);
       rs=ps.executeQuery();
        System.out.println("list of all Cars in cart");
        System.out.format("+%4s+%20s+%10s+%10s+\n","----", "--------------------", "----------", "----------");
        System.out.format("|%4s|%20s|%10s|%10s|\n", "id","mfd By", "model", "price");
        System.out.format("+%4s+%20s+%10s+%10s+\n", "----","--------------------", "----------", "----------");

        int total=0;
        int totalrow=0;
        while(rs.next())
        {
            String id= rs.getString(1);
            String manufacturedBy= rs.getString(2);
            String model= rs.getString(3);
            String price= rs.getString(4);
            total+= Integer.parseInt(price);
            totalrow++;
            System.out.format("|%4s|%20s|%10s|%10s|\n",id, manufacturedBy, model, price);
            System.out.format("+%4s+%20s+%10s+%10s+\n","----", "--------------------", "----------", "----------");

        }
        System.out.println("Total Item in cart: "+totalrow);
        System.out.println("Total price to be paid: "+total);

    }
    void deleteFromCart(String id,String userEmail) throws SQLException {
        Connection con= DriverManager.getConnection(url,username,password);
        PreparedStatement ps=con.prepareStatement("Select id from User where email=?");
        ps.setString(1,userEmail);
        ResultSet rs=ps.executeQuery();
        rs.next();
        String user_id=rs.getString(1);
        ps=con.prepareStatement("delete from CartDetails where user_id=? and id=?");
        ps.setString(1,user_id);
        ps.setString(2,id);
        int response=-1;
        try {
            response = ps.executeUpdate();
        }catch (Exception ignored) { }
        if(response>0)
        {
            System.out.println("Car with id:"+id+" deleted from cart successfully");
        }
        else{
            System.out.println("Either cart was empty or Some error occurred");
        }
    }
    void clearCart(String userEmail) throws SQLException {
        Connection con= DriverManager.getConnection(url,username,password);
        PreparedStatement ps=con.prepareStatement("Select id from User where email=?");
        ps.setString(1,userEmail);
        ResultSet rs=ps.executeQuery();
        rs.next();
        String user_id=rs.getString(1);
        ps=con.prepareStatement("delete from CartDetails where user_id=?");
        ps.setString(1,user_id);
        int response=-1;
        try {
            response = ps.executeUpdate();
        }catch (Exception ignored) { }
        if(response>0)
        {
            System.out.println("All items in car deleted Successfully");
        }
        else{
            System.out.println("Either Cart was empty or Some error occurred");
        }
    }
}
