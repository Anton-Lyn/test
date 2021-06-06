package com.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpServletResponse httpRes = (HttpServletResponse) servletResponse;

        String uri = httpReq.getRequestURI();
        HttpSession session = httpReq.getSession(false);
        Object user = httpReq.getSession().getAttribute("name");
        String s = httpReq.getParameter("email");

        if (user == null &&
                !(uri.endsWith("index.jsp") ||
                        uri.endsWith("loginError.jsp") ||
                        uri.endsWith("registerPage.jsp") ||
                        uri.endsWith("registerPageError.jsp")) &&
                s == null) {
            log.info("Unauthorized request");
            httpRes.sendRedirect("index.jsp");
        } else {
            log.info("Authorized request, session: " + session);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
