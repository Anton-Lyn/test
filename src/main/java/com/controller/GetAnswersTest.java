package com.controller;

import com.DAO.DAOImpl.SubjectDAOImpl;
import com.DAO.DAOImpl.TestDAOImpl;
import com.DAO.SubjectDAO;
import com.DAO.TestDAO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.*;

@Slf4j
@WebServlet(value = "/getAnswers")
public class GetAnswersTest extends HttpServlet {

    TestDAO testDAO = new TestDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession();

        Integer idSubject = (Integer) session.getAttribute("idTopic");

        testDAO.addFrequency(idSubject);

        LocalTime testStartTime = (LocalTime) session.getAttribute("time");
        LocalTime testEndTime = LocalTime.now();

        SubjectDAO subjectDAO = new SubjectDAOImpl();
        Time time = subjectDAO.getTimeTest(idSubject);
        String[] s3 = String.valueOf(time).split(":");
        int hoursForTheTest = Integer.parseInt(s3[0]);
        int minutesForTheTest = Integer.parseInt(s3[1]);
        int secondsForTheTest = Integer.parseInt(s3[2]);

        LocalTime timeLimitForTheTest = testStartTime
                .plusHours(hoursForTheTest)
                .plusMinutes(minutesForTheTest)
                .plusSeconds(secondsForTheTest);

        if (testEndTime.isBefore(timeLimitForTheTest)) {

            Map<String, String> userAnswers = new HashMap<>();
            ArrayList<String> questions = testDAO.getAllQuestions();

            for (String s : questions) {
                String s1 = req.getParameter(s);
                if (!(s1 == null)) {
                    String[] s2 = s1.split(",");
                    userAnswers.put(s2[0], s2[1]);
                }
            }

            int result = testDAO.returnResultTest(userAnswers);
            Integer idUserInSession = (Integer) session.getAttribute("id");

            testDAO.setResultUserInDB(idUserInSession, idSubject, result);


            try {
                resp.sendRedirect("userPage.jsp");
            } catch (IOException exception) {
                log.error(exception.getLocalizedMessage());
            }
        } else {
            resp.sendRedirect("timeOut.jsp");
        }
    }
}
