package com.controller;

import com.entity.User;
import com.service.serviceImpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Slf4j
@WebServlet(value = "/register")
public class UserRegistController extends HttpServlet {

    UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException exception) {
            log.error(exception.getLocalizedMessage());
        }
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Integer language = Integer.valueOf(req.getParameter("lang"));

        User user = new User();

        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        user.setPreferredLang(language);
        userService.registerNewUser(user);
        try {
            resp.sendRedirect("index.jsp");
        } catch (IOException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }
}
