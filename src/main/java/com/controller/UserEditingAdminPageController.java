package com.controller;

import com.service.serviceImpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebServlet(value = "/UserEditing")
public class UserEditingAdminPageController  extends HttpServlet {

    UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();
        String login = req.getParameter("email");

        boolean foundUser = false;
        int checkUserExistence = userService.checkUserExistence(login);

        if (checkUserExistence != 0) {
            foundUser = true;
        }

        try {
            if (checkUserExistence == 0) {
                session.setAttribute("NotFoundUser", foundUser);
                resp.sendRedirect("adminPageError.jsp");
            } else {
                session.setAttribute("UserId", checkUserExistence);
                resp.sendRedirect("editUserPage.jsp");
            }
        } catch (IOException exception){
            log.error(exception.getLocalizedMessage());
        }
    }
}
