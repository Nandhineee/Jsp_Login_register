package com.todo.dao;

import com.todo.db.DbConnection;
import com.todo.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.ResultSet.*;

public class UserDao {
private final Connection con;

    public UserDao() {
        con= DbConnection.getConnection();
    }

    private String SelectSql="SELECT id,username,password FROM auth WHERE username=? AND PASSWORD=?;";

    public User login(String username,String password){
        User user=null;
        try {
            PreparedStatement statement= con.prepareStatement(SelectSql);
            statement.setString(1,username);
            statement.setString(2,password);
            ResultSet rs =statement.executeQuery();
         if(rs.next()){
            user=new User();
            user.setId(Integer.parseInt(rs.getString("id")));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
         }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

}


