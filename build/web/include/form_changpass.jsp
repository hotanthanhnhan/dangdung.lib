<%-- 
    Document   : form_changpass
    Created on : Oct 30, 2021, 2:36:49 PM
    Author     : ThanhNhan
--%>

<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>

<div id="changepass" style="min-height: 43.5vh">            
    <div class="container pt-5">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form id="login-form" class="form" action="changepass" method="post">
                        <h3 class="text-center text-info">Đổi mật khẩu</h3>
                        <div class="form-group">
                            <label for="oldPass" class="text-info">Mật khẩu cũ</label><br>
                            <input type="password" name="oldPass" id="oldPass" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="newPass" class="text-info">Mật khẩu mới</label><br>
                            <input type="password" name="newPass" id="newPass" class="form-control" required>
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