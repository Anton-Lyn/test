package com.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalTime;

@Slf4j
@WebServlet(value = "/userTest")
public class TestController extends HttpServlet {

    Integer id = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        id = Integer.valueOf(req.getParameter("topic"));
        session.setAttribute("topicId", id);
        try {
            LocalTime valueTime = getTime();
            session.setAttribute("time", valueTime);
            resp.sendRedirect("testPage.jsp");
        } catch (IOException exception) {
            log.error(exception.getLocalizedMessage());
        }
    }

    public LocalTime getTime() {
        return LocalTime.now();
    }
}
