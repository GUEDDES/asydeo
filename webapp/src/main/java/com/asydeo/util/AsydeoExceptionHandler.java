package com.asydeo.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import net.sourceforge.stripes.exception.ExceptionHandler;
import net.sourceforge.stripes.config.Configuration;


public class AsydeoExceptionHandler implements ExceptionHandler {
    public void init(Configuration configuration) throws Exception { }
    
    public void handle(Throwable throwable,
                       HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("exception", throwable);
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}