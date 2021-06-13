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
@WebServlet(value = "/register")
public class UserRegisterController extends HttpServlet {

    // todo naxui tebe interface
    UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        try {
            // todo facepalm
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException exception) {
            log.error(exception.getLocalizedMessage());
        }

        String name = req.getParameter("name");
        String login = req.getParameter("email");
        String password = req.getParameter("password");
        Integer language = Integer.valueOf(req.getParameter("lang"));

        int checkUserExistence = userService.checkUserExistence(login);
        boolean checkValidEmail = userService.validEmail(login);

        boolean check;
        check = checkUserExistence != 0;

        try {
            if (checkValidEmail && checkUserExistence == 0) {

                    User user = new User();

                    user.setUserName(name);
                    user.setLoginUser(login);
                    user.setUserPassword(password);
                    user.setUserPreferredLang(language);
                    userService.registerNewUser(user);
                    resp.sendRedirect("index.jsp");

            } else {
                session.setAttribute("checkUserExistence", check);
                session.setAttribute("checkValidEmail", !checkValidEmail);
                resp.sendRedirect("registerPageError.jsp");
            }
        } catch (IOException exception){
            log.error(exception.getLocalizedMessage());
        }
    }
}
