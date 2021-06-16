package com.controller;

import com.DAO.DAOImpl.SubjectDAOImpl;
import com.DAO.SubjectDAO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;

@Slf4j
@WebServlet(value = "/createSubject")
public class AdminCreateSubjectController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        String nameNewSubject = req.getParameter("nameNewSubject");
        String complexityNewSubject = req.getParameter("complexity");
        Integer timeToTestNewSubject = Integer.valueOf(req.getParameter("time"));
        Time timeToTestNewSubjectToDB = getTimeNewSubject(timeToTestNewSubject);

        SubjectDAO subjectDAO = new SubjectDAOImpl();
        subjectDAO.addingANewSubject(nameNewSubject, complexityNewSubject, timeToTestNewSubjectToDB);

        try {
            resp.sendRedirect("adminPage.jsp");
        } catch (IOException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }

    public Time getTimeNewSubject (Integer time) {
        Integer hours = time / 60;
        Integer minutes = time % 60;
        return Time.valueOf(hours + ":" + minutes + ":" + "00");
    }

}
