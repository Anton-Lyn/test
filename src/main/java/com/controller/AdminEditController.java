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
@WebServlet(value = "/EditUser")
public class AdminEditController extends HttpServlet {

    UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        Integer idUser = (Integer) session.getAttribute("UserId");
        String newName = req.getParameter("newName");
        String newLogin = req.getParameter("newEmail");
        String newPassword = req.getParameter("newPassword");
        String newRole = req.getParameter("newRole");
        Integer newLang = Integer.valueOf(req.getParameter("newLang"));
        Boolean newStatus = Boolean.valueOf(req.getParameter("newStatus"));

        boolean validEmail = userService.emailValidityCheck(newLogin);

        if (validEmail) {

            User modifiedUser = new User();

            modifiedUser.setUserId(idUser);
            modifiedUser.setUserName(newName);
            modifiedUser.setLoginUser(newLogin);
            modifiedUser.setUserPassword(newPassword);
            modifiedUser.setUserRole(newRole);
            modifiedUser.setUserPreferredLang(newLang);
            modifiedUser.setUserStatus(newStatus);

            userService.userUpdate(modifiedUser);

            try {
                resp.sendRedirect("adminPage.jsp");
            } catch (IOException exception) {
                log.error(exception.getLocalizedMessage());
            }

        }
    }
}
