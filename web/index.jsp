<%--
  Created by IntelliJ IDEA.
  User: Tomek
  Date: 2017-03-05
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form name="Form1"
        method="post"
        action="http://localhost:8080/baseServlet">
    <div>
      <label for="parametr1">Parametr 1</label>
      <input id="parametr1" type=text name="parametr1" size="25" value="">
    </div>
    <div>
     <label for="parametr2">Parametr 2</label>
      <input id="parametr2" type=text name="parametr2" size="25" value="">
    </div>
    <input type=submit value="Wyślij">
  </form>
  <h1>File Upload</h1>
  <form method="post" action="fileUpload"
        enctype="multipart/form-data">
    Select file to upload: <input type="file" name="file" size="60" /><br />
    <br /> <input type="submit" value="Upload" />
  </form>
  </body>
</html>
