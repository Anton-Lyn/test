package com.controller;

import com.DAO.DAOImpl.TestDAOImpl;
import com.DAO.TestDAO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebServlet(value = "/editTests")
public class TestEditController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        TestDAO testDAO = new TestDAOImpl();

        Integer idSubject = Integer.valueOf(req.getParameter("deleteTest"));

        testDAO.deleteResults(idSubject);
        testDAO.deleteTests(idSubject);
        testDAO.deleteSubject(idSubject);

        try {
            resp.sendRedirect("workWithTest.jsp");
        } catch (IOException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();

        Integer idSubject = Integer.valueOf(req.getParameter("subject"));
        session.setAttribute("idSubject", idSubject);

        try {
            resp.sendRedirect("editTest.jsp");
        } catch (IOException exception) {
            log.error(exception.getLocalizedMessage());
        }

    }
}
