package com.example.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Tomek on 2017-03-09.
 */
@WebServlet(name = "DownloadServlet",urlPatterns = {"/download","/downloadServlet"})
public class DownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathName = UploadServlet.PATH;

        String fileName = request.getParameter("file");
        pathName += "\\" + fileName;

        doSomeInterestingStuff(pathName,response);

    }

    private void doSomeInterestingStuff(String pathName, HttpServletResponse response) throws IOException {
        File downloadFile = new File(pathName);
        FileInputStream inStream = new FileInputStream(downloadFile);

        //String relativePath = getServletContext().getRealPath("");
        //System.out.println("relativePath = " + relativePath);

        ServletContext context = getServletContext();

        // gets MIME type of the file
        String mimeType = context.getMimeType(pathName);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        // modifies response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // obtains response's output stream
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inStream.close();
        outStream.close();
    }
}
