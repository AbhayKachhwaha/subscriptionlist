<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sheridan E-mail Subscription List</title>
    </head>
    <body>
        <h1 style="text-align: center">List of all Subscriptions</h1>
        <table style="margin-left: auto; margin-right: auto">
            <tr>
                <th>Name</th>
                <th>E-ail Address</th>
                <th>Currently Subscribed</th>
                <th>Last Update Time</th>
            </tr>
             <c:forEach var="s" items="${subscriptions}">
                <tr>
                    <td>${s.firstName} ${s.lastName}</td>
                    <td>${s.email}</td>
                    <td>${s.subscribed}</td>
                    <td>${s.lastUpdate}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
