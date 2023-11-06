package com.todo.controller;

import com.todo.db.DbConnection;
import com.todo.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController extends HttpServlet {
    private final Connection con;

    public RegisterController() {
        con= DbConnection.getConnection();
    }

    private String InsertSql="INSERT INTO auth(UserName, Password) VALUES (?, ?)";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String regusername= req.getParameter("username");
        String regpassword=req.getParameter("password");
            try {
                PreparedStatement preparedStatement= con.prepareStatement(InsertSql);
                preparedStatement.setString(1,regusername);
                preparedStatement.setString(2,regpassword);
                int InsertQuery=preparedStatement.executeUpdate();
                if(InsertQuery > 0){
                    System.out.println("Register sucessfullly");
                }else{
                    System.out.println("User registration is fail");
                }
                RequestDispatcher dispatcher= req.getRequestDispatcher("home.jsp");
                dispatcher.forward(req,resp);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
