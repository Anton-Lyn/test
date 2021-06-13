package com.controller;

import com.entity.User;
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

    UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // todo 1 set in a single line
        // todo 2 extract to service
        String hashPassword;
        hashPassword = userService.hashingPassword(password);

        // todo optional
        User receivedUser = userService.checkLogin(email, hashPassword);

        HttpSession session = req.getSession();
        session.setAttribute("id", receivedUser.getUserId());
        session.setAttribute("name", receivedUser.getUserName());
        session.setAttribute("login", receivedUser.getLoginUser());
        session.setAttribute("role", receivedUser.getUserRole());
        session.setAttribute("status", receivedUser.getUserStatus());
        session.setAttribute("dateCreatedUser", receivedUser.getDateCreatedUser());
        session.setAttribute("dateUpdateUser", receivedUser.getDateUpdateUser());
        try {
            // todo extract in method
            // todo move logic to service
            // todo add method isAdmin()
            if (
                    receivedUser.getLoginUser() != null &&
                    receivedUser.getUserRole().equals("admin") &&
                    receivedUser.getUserPassword().equals(hashPassword)) {
                resp.sendRedirect("adminPage.jsp");
            } else if (
                    receivedUser.getLoginUser() != null &&
                    receivedUser.getUserPassword().equals(hashPassword) &&
                    receivedUser.getUserStatus().equals(false)) {
                resp.sendRedirect("userPage.jsp");
            } else {
                resp.sendRedirect("loginError.jsp");
            }
        } catch (IOException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }
}
