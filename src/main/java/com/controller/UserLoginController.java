package com.controller;

import com.entity.User;
import com.service.serviceImpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
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
        String hashPassword;
        UserServiceImpl userService1 = new UserServiceImpl();

        hashPassword = userService1.haschedPassword(password);

        User receivedUser = userService.checkLogin(email, hashPassword);

        HttpSession session = req.getSession();
        session.setAttribute("id", receivedUser.getId());
        session.setAttribute("name", receivedUser.getName());
        session.setAttribute("login", receivedUser.getLogin());
        session.setAttribute("role", receivedUser.getRole());
        session.setAttribute("status", receivedUser.getBlocked());
        session.setAttribute("created", receivedUser.getCreatedAt());
        session.setAttribute("update", receivedUser.getUpdateAT());


        try {
            if ( receivedUser.getLogin() != null && receivedUser.getPassword().equals(hashPassword)) {
                getServletContext().getRequestDispatcher("/userPage.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("loginError.jsp");
            }
        } catch (IOException | ServletException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }
}
