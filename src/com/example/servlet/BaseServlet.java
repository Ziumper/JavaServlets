package com.example.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;


public class BaseServlet extends GenericServlet
{
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html");

        PrintWriter pw =  servletResponse.getWriter();
        pw.println("<h1>Hello from Base Servlet Class</h1>");
        pw.println("SERVER_NAME="+servletRequest.getServerName()+"<BR>");
        pw.println("REMOTE_HOST="+servletRequest.getRemoteHost()+"<BR>");
        pw.println("REMOTE_ADDR="+servletRequest.getRemoteAddr()+"<BR>");

        Enumeration e = servletRequest.getParameterNames();

        while(e.hasMoreElements()) {
            String pname = (String)e.nextElement();
            pw.println(pname + " = ");
            String pvalue = servletRequest.getParameter(pname);
            pw.println(pvalue +"<br>");
        }
        pw.close();
    }
}
