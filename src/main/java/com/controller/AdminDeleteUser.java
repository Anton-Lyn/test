package com.controller;

import com.DAO.DAOImpl.TestDAOImpl;
import com.DAO.DAOImpl.UserDAOImpl;
import com.DAO.TestDAO;
import com.DAO.UserDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deleteUser")
public class AdminDeleteUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Integer idUser = Integer.valueOf(req.getParameter("deleteUser"));

        UserDAO userDAO = UserDAOImpl.getInstance();
        TestDAO testDAO = new TestDAOImpl();

        testDAO.deleteResultsByUser(idUser);
        userDAO.deleteUser(idUser);

        resp.sendRedirect("adminPageAllUsers.jsp");
    }
}
