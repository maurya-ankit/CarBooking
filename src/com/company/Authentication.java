package com.company;

import java.sql.*;

public class Authentication {
    int login(String Email,String Password) throws ClassNotFoundException, SQLException {
        String url="jdbc:mysql://127.0.0.1:3306/CarBooking?autoReconnect=true&useSSL=false";
        String username="root";
        String password="Vector@2003";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection(url,username,password);

        PreparedStatement ps=con.prepareStatement("Select * from User where email=? and password=?");
        ps.setString(1,Email);
        ps.setString(2,Password);
        ResultSet rs=ps.executeQuery();
        rs.next();
        int response=rs.getRow();

        if(response==1)
        {
            String firstname= rs.getString(2);
            String lastname= rs.getString(3);
            System.out.println("Welcome "+firstname+" "+lastname);
            return 1;
        }
        else
        {
            return 0;
        }

    }
    int signUp(String firstname,String lastname,String email,String Password) throws ClassNotFoundException, SQLException {
        String url="jdbc:mysql://127.0.0.1:3306/CarBooking?autoReconnect=true&useSSL=false";
        String username="root";
        String password="Vector@2003";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection(url,username,password);

        PreparedStatement ps=con.prepareStatement("INSERT INTO User(firstname,lastname,email,password) values(?,?,?,?)");
        ps.setString(1,firstname);
        ps.setString(2,lastname);
        ps.setString(3,email);
        ps.setString(4,Password);
        int response=-1;
        try {
            response = ps.executeUpdate();
        }catch (Exception ignored)
        {
        }
        if(response>0)
        {
            return 1;
        }
        else
        {
            return 0;
        }

    }
}
