package com.controller;

import com.entity.User;
import com.service.UserService;
import com.service.serviceImpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebServlet(value = "/login")
public class UserLoginController extends HttpServlet {

    UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        String hashPassword = userService.hashingPassword(password);
        User receivedUser = userService.checkLogin(email, hashPassword);

        HttpSession session = req.getSession();
        session.setAttribute("id", receivedUser.getUserId());
        session.setAttribute("name", receivedUser.getUserName());
        session.setAttribute("login", receivedUser.getLoginUser());
        session.setAttribute("role", receivedUser.getUserRole());
        session.setAttribute("status", receivedUser.getUserStatus());
        session.setAttribute("dateCreatedUser", receivedUser.getDateCreatedUser());
        session.setAttribute("dateUpdateUser", receivedUser.getDateUpdateUser());
        session.setAttribute("sort", 1);
        try {
            if (userService.isAdmin(receivedUser, hashPassword)) {
                resp.sendRedirect("adminPage.jsp");
            } else if (userService.idUser(receivedUser, hashPassword)) {
                resp.sendRedirect("userPage.jsp");
            } else {
                resp.sendRedirect("loginError.jsp");
            }
        } catch (IOException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();
        session.setAttribute("sort", req.getParameter("sort"));

        try {
            resp.sendRedirect("userPage.jsp");
        } catch (IOException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }
}
