package com.example.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "UploadServlet",urlPatterns = "/fileUpload")
@MultipartConfig(
        fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize=1024*1024*50
)
public class UploadServlet extends HttpServlet {
    public static final String PATH = "C:\\uploadFiles";

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        //String appPath = request.getServletContext().getRealPath("");

        String savePath = PATH;
        File fileSaveDir = new File(savePath);

        if (!fileSaveDir.exists())
        {
            fileSaveDir.mkdir();
        }

        for (Part part : request.getParts())
        {
            String fileName = part.getSubmittedFileName();
            fileName = new File(fileName).getName();
            part.write(savePath + "\\" + fileName);
        }

        PrintWriter pw = response.getWriter();
        pw.println("Witaj! Pomyślnie dodano plik! Zapisano w: " + savePath);


        File folder = new File(PATH);
        File[] listOfFiles = folder.listFiles();
        pw.println("<ul>");
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                pw.print("<li>");
                pw.println("<a href=\"/download?file=" + listOfFiles[i].getName() + "\">Pobierz " + listOfFiles[i].getName()+"</a>");
                pw.print("</li>");
            }
        }
        pw.println("</ul>");

        pw.close();
    }

}
