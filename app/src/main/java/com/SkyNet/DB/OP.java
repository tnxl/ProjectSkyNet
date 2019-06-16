package com.SkyNet.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OP {

    Connection connection;
    PreparedStatement psm;
    ResultSet resultSet;

    private String sql = "select * from user where username = ?";

    public boolean login(String username,String password){
        connection = DataBase.getConnection();
        try {
            psm = connection.prepareStatement(sql);
            psm.setString(1,username);
            resultSet = psm.executeQuery();
            if (resultSet.next()){
                String pwd = resultSet.getString("password");
                return pwd.equals(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean register(String username,String password){
        connection = DataBase.getConnection();
        try {
            psm = connection.prepareStatement(sql);
            psm.setString(1,username);
            resultSet = psm.executeQuery();
            if (!resultSet.next()){
                sql = "insert into user(username,password) value('" +
                        username+"'," +
                        "'"+password+"'" +
                        ")";
                psm = connection.prepareStatement(sql);
                int n = psm.executeUpdate();
                return n!=0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
