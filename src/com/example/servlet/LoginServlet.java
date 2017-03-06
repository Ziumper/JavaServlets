package com.example.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Tomek on 2017-03-06.
 */
@WebServlet(name = "LoginServlet",urlPatterns = {"/loginServlet","/login"})
public class LoginServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1250";
    private int licznik = 0;



    /**Process the HTTP doGet request.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nazwa = "";
        String haslo = "";

        nazwa = request.getParameter("nazwa");
        haslo = request.getParameter("haslo");

        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Login Serwlet</title></head>");
        out.println("<body>");
        out.println("<p>The servlet has received a GET. This is the reply.</p>");
        out.println("<form name=\"login_form\" action=\"login\" method=\"post\">");
        out.println(" <p>");
        out.println(" nazwa:<input type=\"text\" name=\"nazwa\"maxlength=\"20\" size=\"20\"/>");
        out.println(" </p>");
        out.println(" <p>");
        out.println(" hasło:");
        out.println(" <input type=\"password\" name=\"haslo\"maxlength=\"20\" size=\"20\"/>");
        out.println(" </p>");
        out.println(" <p>");
        out.println(" <input type=\"submit\" name=\"zaloguj\"value=\"zaloguj\"/>");
        out.println(" </p>");
        out.println(" </form>");
        out.println("</body></html>");
        out.close();
    }

    /**Process the HTTP doPost request.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nazwa = "";
        String haslo = "";

        nazwa = request.getParameter("nazwa");
        haslo = request.getParameter("haslo");

        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Login Serwlet</title></head>");
        out.println("<body>");
        out.println("<p>The servlet has received a POST. This is the reply.</p>");
        out.println("<p>próba numer: "+ (++licznik) +"</p>");
        if (nazwa.equals(haslo)) {
            out.println("<p>Złe hasło.</p>");
            out.println("<p><a href=\"login\">Kolejna próba</a></p>");
        } else
        {
            out.println("<p>Hasło poprawne.</p>");
        }
        out.println("</body></html>");
        out.close();
    }

    @Override
    public void init() throws ServletException {
        //inicjalizacja serwletu
        super.init();
    }

    @Override
    public void destroy() {
        //kasowanie instancji serwletu
        super.destroy();
    }
}
