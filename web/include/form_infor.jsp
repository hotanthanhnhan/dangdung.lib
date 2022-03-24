<%-- 
    Document   : form_infor
    Created on : Oct 30, 2021, 2:34:27 PM
    Author     : ThanhNhan
--%>


<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>
<div id="infor">            
    <div class="container pt-5">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form id="login-form" class="form" action="information" method="post">
                        <h3 class="text-center text-info">Thay đổi thông tin tài khoản</h3>                        
                        <div class="form-group">
                            <label for="userName" class="text-info">Tên đăng nhập:</label><br>
                            <input type="text" name="userName" id="userName" value="${acc.userID}" readonly class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="name" class="text-info">Họ và tên:</label><br>
                            <input type="text" name="name" id="name" value="${acc.fullName}" class="form-control" readonly>
                        </div>
                        <div class="form-group">
                            <label for="email" class="text-info">Email:</label><br>
                            <input type="email" name="email" id="email" value="${acc.email}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="password" class="text-info">Mật khẩu:</label><br>
                            <input type="password" name="password" id="password" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <br>
                            <input type="submit" name="submit" class="btn btn-info btn-md" value="Đồng ý">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
