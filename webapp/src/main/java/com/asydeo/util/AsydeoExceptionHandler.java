package com.asydeo.util;

import java.io.IOException;

//import java.lang.StringBuffer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;
import javax.servlet.ServletException;

import net.sourceforge.stripes.exception.ExceptionHandler;
import net.sourceforge.stripes.config.Configuration;

import static com.asydeo.util.Logger.*;


public class AsydeoExceptionHandler implements ExceptionHandler {

    
    public void init(Configuration configuration) throws Exception { }
    
    public void handle(Throwable throwable,
                       HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String sessionId = request.getSession().getId();
        StringBuffer str =
          new StringBuffer("sessionId = ")
            .append(sessionId)
            .append(", uri = ")
            .append( HttpUtils.getRequestURL(request).toString() )
            .append( request.getQueryString() )
            .append(", user = ")
            .append( request.getRemoteUser() );
         
        error(str.toString(), throwable);
        
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("exception", throwable);
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}