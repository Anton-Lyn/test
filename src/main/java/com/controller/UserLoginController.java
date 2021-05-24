package com.controller;

import com.service.serviceImpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(value = "/login")
public class UserLoginController extends HttpServlet {



    UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        log.info("");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        boolean resultCheckLogin = userService.checkLogin(email, password);
        if (resultCheckLogin) {
            resp.sendRedirect("register.jsp");
        }
        else {
            resp.sendRedirect("error.jsp");
        }
    }
}
