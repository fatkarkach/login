package com.jwt.hibernate.Conroller;

import com.jwt.hibernate.dao.UserDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginController", value = "/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao loginDao;

    public void init() {
        loginDao = new UserDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            String username = request.getParameter("username");
            System.out.println(username);
            String password = request.getParameter("password");
            if (loginDao.validate(username, password)) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("login-success.jsp");
                dispatcher.forward(request, response);
            } else {
                throw new Exception("Login not successful..");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
