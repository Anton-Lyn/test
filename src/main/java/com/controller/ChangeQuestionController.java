package com.controller;

import com.DAO.DAOImpl.QuestionDAOImpl;
import com.DAO.QuestionDAO;
import com.entity.Test;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebServlet(value = "/takeQuestion")
public class ChangeQuestionController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();

        QuestionDAO questionDAO = new QuestionDAOImpl();
        Test test = new Test();

        String changedQuestion = (String) session.getAttribute("question");
        Integer idQuestion = questionDAO.getIdTestByQuestion(changedQuestion);

        test.setIdTest(idQuestion);
        test.setQuestion(req.getParameter("question"));
        test.setAnswer1(req.getParameter("answer1"));
        test.setAnswer2(req.getParameter("answer2"));
        test.setAnswer3(req.getParameter("answer3"));
        test.setAnswer4(req.getParameter("trueAnswer"));

        questionDAO.changeQuestion(test);

        try {
            resp.sendRedirect("editTest.jsp");
        } catch (IOException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();
        String question = req.getParameter("topic1");
        session.setAttribute("question", question);

        try {
            resp.sendRedirect("changeQuestion.jsp");
        } catch (IOException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }
}
