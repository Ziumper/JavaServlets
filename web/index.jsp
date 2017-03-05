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
    <table>
      <tr>
        <td><B>Parametr1</td>
        <td><input type=textbox name="parametr1" size="25" value=""></td>
      </tr>
      <tr>
        <td><B>Parametr2</td>
        <td><input type=textbox name="parametr2" size="25" value=""></td>
      </tr>
    </table>
    <input type=submit value="Wyślij">
  </body>
</html>
