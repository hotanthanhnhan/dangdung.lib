<%-- 
    Document   : CreateAdmin
    Created on : Nov 22, 2021, 4:55:55 PM
    Author     : ThanhNhan
--%>

<%@include file="./include/Header_user.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 
<c:if test="${acc.userTypeID != 0}">
    <jsp:forward page="login"></jsp:forward>
</c:if>
<div class="container-fluid" style="margin-top: 20px"> 
    <div>
        <table border="1" cellspacing="3" cellpadding="3" style="margin: auto; font-size: 120%">
            <thead>
                <tr style="text-align: center">
                    <th>Mã nhân viên</th>
                    <th>Họ và tên</th>
                    <th>Email</th>
                    <th>Lần đăng nhập cuối</th>     
                    <th>Xóa tài khoản</th>     
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="l">
                    <tr id="row${l.userID}">
                        <td>${l.userID}</td>
                        <td>${l.fullName}</td>
                        <td>${l.email}</td>
                        <td>${l.lastAccess}</td> 
                        <td style="text-align: center;"><a style="color: red" onclick="deleteAdmin('${l.userID}')">Xóa</a></td> 
                    </tr>
                </c:forEach>
            </tbody>
        </table> 
    </div>
</div>
<script>
    function deleteAdmin(id) {
        var conf = confirm("Bạn có chắc chắn muốn xóa admin '"+id+"' không?");
        if (conf) {
            $.ajax({
                url: '/dangdung.lib/deleteadmin',
                type: 'POST',
                data: {
                    id: id,
                },
                success: function () {
                    document.getElementById('row'+id).remove();
                }
            });
        }

    }
</script>
<div id="infor">            
    <div class="container pt-5">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form action="createadmin" method="POST">
                        <h3 class="text-center text-info">Thêm nhân viên</h3>                               
                        <div class="form-group">
                            <label for="userID" class="text-info">Mã nhân viên:</label><br>                            
                            <input type="text" required="" name="userID" id="userID" class="form-control" autocomplete="off">
                        </div>
                        <div class="form-group">
                            <label for="fullName" class="text-info">Họ tên nhân viên:</label><br>                            
                            <input type="text" name="fullName" required="" id="fullName" class="form-control" autocomplete="off">
                        </div>
                        <div class="form-group">
                            <label for="email" class="text-info">Email:</label><br>                            
                            <input type="email" name="email" id="email" class="form-control" autocomplete="off">
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

<%@include file="include/Footer_user.jsp" %>