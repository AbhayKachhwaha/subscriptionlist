<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
  String result = (String) request.getAttribute("message");
%>    
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Registration</title>
    </head>
    <body>
        <h1><%= result %></h1>
    </body>
</html>
