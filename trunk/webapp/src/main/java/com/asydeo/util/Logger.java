package com.asydeo.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Logger {

    private static final Log logger = LogFactory.getLog(Logger.class);


    public static void debug(String msg) {
        System.out.println("Received: " + msg);
        logger.debug(msg);
    }
    
    public static void error(String msg) {
        logger.error(msg);
    }
    public static void error(Throwable th) {
        logger.error( toString(th) );
    }
    public static void error(String msg, Throwable th) {
        logger.error(msg + ". " + toString(th));
    }
    
    private static String toString(Throwable th) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        th.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }
}
