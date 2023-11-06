package com.todo.controller;

import com.todo.dao.UserDao;
import com.todo.db.DbConnection;
import com.todo.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthController extends HttpServlet {

    private final UserDao userDao;
    public AuthController() {
        DbConnection.getConnection();
        userDao=new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String username= req.getParameter("username");
      String password=req.getParameter("password");
      User LoggedInuser= userDao.login(username,password);
      if(LoggedInuser !=null)
      {
          HttpSession httpSession= req.getSession();
          httpSession.setAttribute("id",LoggedInuser.getId());
          RequestDispatcher rd= req.getRequestDispatcher("home.jsp");
          rd.forward(req,resp);
      }else{
          req.setAttribute("error",true);
          System.out.println("error");
          RequestDispatcher dispatcher= req.getRequestDispatcher("Register.jsp");
          dispatcher.forward(req,resp);
      }



}

}
