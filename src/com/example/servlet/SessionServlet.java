package com.example.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Tomek on 2017-03-06.
 */
@WebServlet(name = "SessionServlet",urlPatterns = "/sessionServlet")
public class SessionServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1250";
    private static final String LICZSTR = "licznik";
    private int licznik = 0;



    /**Process the HTTP doGet request.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nazwa = "";
        String haslo = "";

        try {
            nazwa = request.getParameter("nazwa");
            haslo = request.getParameter("haslo");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer licznikSesji = 0;
        HttpSession mojaSesja;
        mojaSesja = request.getSession(true);
        mojaSesja.setAttribute(LICZSTR, licznikSesji);

        sprawdzCiasteczko(request,response);

        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Session Servlet</title></head>");
        out.println("<body>");
        out.println("<p>The servlet has received a GET. This is the reply.</p>");
        out.println("<form name=\"login_form\" action=\"sessionServlet\" method=\"post\">");
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

        //SESJA
        Integer licznikSesji = 0;
        HttpSession mojaSesja = request.getSession(false);
        if(mojaSesja !=null)
        {
            licznikSesji = (Integer)mojaSesja.getAttribute(LICZSTR);
            licznikSesji++;
            mojaSesja.setAttribute(LICZSTR,licznikSesji);
        }

        //CIASTECZKA
        Cookie ciasteczkoLicznika = pobierzCiasteczko(LICZSTR,request);
        int licznikCiasteczka = 0;

        if(ciasteczkoLicznika!=null)
        {
            //dodanie wartości
            licznikCiasteczka = Integer.parseInt(ciasteczkoLicznika.getValue());
            licznikCiasteczka++;
            ciasteczkoLicznika.setValue(Integer.toString(licznikCiasteczka));
            ciasteczkoLicznika.setMaxAge(60);

            //dodaj do odpowiedzi!
            response.addCookie(ciasteczkoLicznika);


        }

        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Session Servlet</title></head>");
        out.println("<body>");
        out.println("<p>The servlet has received a POST. This is the reply.</p>");
        out.println("<p>próba numer: "+ (++licznik) +"</p>");

        if(licznikCiasteczka > 0 && ciasteczkoLicznika!=null)
        {
            out.println("<p>Wynik z ciasteczka - próba numer: "+ licznikCiasteczka +"</p>");
        }
        else
        {
            //response.sendError(HttpServletResponse.SC_FORBIDDEN);
            out.println("<p>Nie ma ciasteczka z wartości licznika!</p>");

        }

        out.println("<p>Wynik z atrybutu sesji - próba numer: " + licznikSesji + "</p>" );

        if (nazwa.equals(haslo)) {
            out.println("<p>Złe hasło.</p>");
            out.println("<p><a href=\"sessionServlet\">Kolejna próba</a></p>");
        } else {
            out.println("<p>Hasło poprawne.</p>");
            out.println("</body></html>");
            out.close();
        }


    }


    private Cookie pobierzCiasteczko(String nazwaCiasteczka, HttpServletRequest request)
    {
        Cookie mojeCiasteczko = null;
        Cookie[] ciasteczka = request.getCookies();
        if(ciasteczka != null) for(int i = 0 ; i < ciasteczka.length; i++)
        {
            if(ciasteczka[i].getName().equals(nazwaCiasteczka))
            {
                mojeCiasteczko = ciasteczka[i];
                break;
            }
        }
        return mojeCiasteczko;
    }

    private void dodajCiasteczkoDoOdpowiedzi(HttpServletResponse response)
    {
        //tworzenie ciasteczka
        int licznik = 0;
        int czasZyciaCiasteczka = 60;
        Cookie mojeCiasteczko = new Cookie(LICZSTR,Integer.toString(licznik));

        //dodanie czasu trwania ciasteczka
        mojeCiasteczko.setMaxAge(czasZyciaCiasteczka);

        //dodanie ciasteczka do odpowiedzi http
        response.addCookie(mojeCiasteczko);

    }

    private void sprawdzCiasteczko(HttpServletRequest request, HttpServletResponse response)
    {
        boolean flaga = true;
        Cookie[] ciasteczka  = request.getCookies();
        if(ciasteczka != null) for(int i = 0; i < ciasteczka.length; i++) {
            if(ciasteczka[i].getName().equals(LICZSTR))
            {
                flaga = false;
                break;
            }

        }

        //Jezeli nie ma to dodaj
        if(flaga) {
            dodajCiasteczkoDoOdpowiedzi(response);
        }


    }
}
