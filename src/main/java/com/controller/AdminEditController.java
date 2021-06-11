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
import java.io.UnsupportedEncodingException;

@Slf4j
@WebServlet(value = "/EditUser")
public class AdminEditController extends HttpServlet {

    UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException exception) {
            log.error(exception.getLocalizedMessage());
        }

        Integer idUser = (Integer) session.getAttribute("UserId");
        String newName = req.getParameter("newName");
        String newLogin = req.getParameter("newEmail");
        String newPassword = req.getParameter("newPassword");
        String newRole = req.getParameter("newRole");
        Integer newLang = Integer.valueOf(req.getParameter("newLang"));
        Boolean newStatus = Boolean.valueOf(req.getParameter("newStatus"));

        boolean checkValidEmail = userService.validEmail(newLogin);

        if (checkValidEmail) {

            User modifiedUser = new User();

            modifiedUser.setUserId(idUser);
            modifiedUser.setUserName(newName);
            modifiedUser.setLoginUser(newLogin);
            modifiedUser.setUserPassword(newPassword);
            modifiedUser.setUserRole(newRole);
            modifiedUser.setUserPreferredLang(newLang);
            modifiedUser.setUserStatus(newStatus);

            userService.editUserService(modifiedUser);

            try {
                resp.sendRedirect("adminPage.jsp");
            } catch (IOException exception) {
                log.error(exception.getLocalizedMessage());
            }

        }
    }
}
