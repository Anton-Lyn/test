package com.controller;

import com.DAO.DAOImpl.QuestionDAOImpl;
import com.DAO.QuestionDAO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@WebServlet(value = "/deleteQuestion")
public class TestDeleteQuestionController extends HttpServlet {

    QuestionDAO questionDAO = new QuestionDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        String question = req.getParameter("topic2");
        Integer idQuestion = questionDAO.getIdTestByQuestion(question);
        questionDAO.deleteQuestion(idQuestion);
        try {
            resp.sendRedirect("editTest.jsp");
        } catch (IOException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }
}
