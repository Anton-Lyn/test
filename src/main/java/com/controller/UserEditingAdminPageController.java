package com.controller;

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
@WebServlet(value = "/UserEditing")
public class UserEditingAdminPageController  extends HttpServlet {

    UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();
        String login = req.getParameter("email");

        Integer checkUserExistence = userService.checkUserExistence(login);

        try {
            if (checkUserExistence == null) {
                session.setAttribute("NotFoundUser", false);
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
