<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="customtag" uri="/WEB-INF/tlds/customtags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Testing Our Custom Tag</title>
    </head>
    <body>
        
            <h2>Get Subscription Information:</h2>
        <form action="GetSubscriptionInfo.do" method="GET">
            <table>
            <tr>
                <td style="text-align:right">Enter e-mail:</td>
                <td><customtag:emailList/></td>
               
            </tr>
            <tr>
                 <td style="text-align:center" colspan="2">
                <input type="submit" value="Get User Info"/></td>
            </tr>
            </table>
        </form>
        <br>
    </body>
</html>
