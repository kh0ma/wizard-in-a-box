package com.github.kh0ma.tools.dropwizard.box.it.sample.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="checkLoggingServlet", urlPatterns={"/check/logging"})
public class CheckLoggingServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(getClass());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Executing get ...");
        PrintWriter out = response.getWriter();
        out.print("LoggerFactory type = " + StaticLoggerBinder.getSingleton().getLoggerFactory().getClass().getName());
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

}