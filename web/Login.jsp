<%-- 
    Document   : Login
    Created on : Oct 20, 2021, 1:28:33 AM
    Author     : ThanhNhan
--%>

<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chào mừng bạn đến với thư viện trường THCS Đặng Dung</title>
        <link rel='stylesheet' href='./assets/css/login.css'>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="icon" type="image/png" href="./assets/imgs/ico.png"/>
    </head>
    
    <body>
        <div id="login">            
            <div class="container">
                <div id="login-row" class="row justify-content-center align-items-center">
                    <div id="login-column" class="col-md-8">
                        <div id="login-box" class="col-md-12">
                            <form id="login-form" class="form" action="login" method="post">
                                <h3 class="text-center text-info">Chào mừng bạn đến với thư viện trực tuyến<br>trường THCS Đặng Dung</h3>                                
                                <div class="form-group">
                                    <label for="userID" class="text-info">Tên đăng nhập:</label><br>
                                    <input type="text" name="userID" id="userID" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="text-info">Mật khẩu:</label><br>
                                    <input type="password" name="password" id="password" class="form-control" required>
                                </div>
                                <div class="form-group" style="float: right">                                    
                                    <input type="submit" name="submit" class="btn btn-info btn-md" value="Đăng nhập">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>                                                                
    </body>
</html>
