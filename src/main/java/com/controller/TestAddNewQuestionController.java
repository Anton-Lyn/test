package com.controller;

import com.DAO.DAOImpl.TestDAOImpl;
import com.DAO.TestDAO;
import com.entity.Test;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Slf4j
@WebServlet(value = "/addQuestion")
public class TestAddNewQuestionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.setAttribute("idSub", req.getParameter("subject"));
        resp.sendRedirect("addQuestion.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();
        Test newTest = new Test();

        String getIdSubject = String.valueOf(session.getAttribute("idSub"));
        Integer castIdSubjectToInteger = Integer.valueOf(getIdSubject);

        newTest.setIdSubject(castIdSubjectToInteger);
        newTest.setQuestion(req.getParameter("question"));
        newTest.setAnswer1(req.getParameter("answer1"));
        newTest.setAnswer2(req.getParameter("answer2"));
        newTest.setAnswer3(req.getParameter("answer3"));
        newTest.setAnswer4(req.getParameter("trueAnswer"));

        TestDAO testDAO = new TestDAOImpl();
        testDAO.addTest(newTest);

        try {
            resp.sendRedirect("workWithTest.jsp");
        } catch (IOException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }
}
