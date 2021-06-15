package com.controller;

import com.DAO.DAOImpl.QuestionDAOImpl;
import com.DAO.QuestionDAO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@WebServlet(value = "/deleteQuestion")
public class TestDeleteQuestionController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        QuestionDAO questionDAO = new QuestionDAOImpl();

        String question = req.getParameter("topic2");;
        Integer idQuestion = questionDAO.getIdTestByQuestion(question);
        questionDAO.deleteQuestion(idQuestion);

        resp.sendRedirect("editTest.jsp");

    }
}
