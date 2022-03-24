<%-- 
    Document   : error
    Created on : Oct 20, 2021, 2:03:38 AM
    Author     : ThanhNhan
--%>

<%@page contentType="text/html" isErrorPage="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='./assets/css/err.css'>
        <title>Error</title>        
    </head>
    <body>
        <div class="content">
            <div class="browser-bar">
                
                <span class="close button"></span>
                <span class="min button"></span>
                <span class="max button"></span>                
            </div>
            <div class="text" style="text-align: center">
                <p style="font-size: 30px; color: white; background: none"><%=exception.getMessage()%></p>
                <input type="button" value="Trở lại" onclick="javascript:history.go(-1);" />
            </div>
        </div>
    </body>

</html>
