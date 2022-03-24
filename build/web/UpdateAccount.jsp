<%-- 
    Document   : UpdateAccount
    Created on : Dec 16, 2021, 10:40:39 PM
    Author     : ThanhNhan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="./include/Header_admin.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 
<div id="infor">            
    <div class="container pt-5">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form id="login-form" class="form" action="updateaccount" method="post">
                        <h3 class="text-center text-info">Thay đổi thông tin tài khoản</h3>                        
                        <div class="form-group">
                            <label for="userID" class="text-info">Tên đăng nhập:</label><br>
                            <input type="text" name="userID" id="userID" value="${u.userID}" readonly class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="fullName" class="text-info">Họ và tên:</label><br>
                            <input type="text" name="fullName" id="fullName" value="${u.fullName}" autocomplete="off" class="form-control" required="">
                        </div>
                        <div class="form-group">
                            <label for="email" class="text-info">Email:</label><br>
                            <input type="email" name="email" id="email" value="${u.email}" autocomplete="off" class="form-control">
                        </div>
                        <c:if test="${u.userTypeID == 3}">
                            <div class="form-group">
                            <label for="grade" class="text-info">Khối:</label><br>
                            <select class="form-control" name="grade">                                                                
                                    <option value="6" ${u.grade == 6 ? "selected" : ""}>6</option>
                                    <option value="7" ${u.grade == 7 ? "selected" : ""}>7</option>
                                    <option value="8" ${u.grade == 8 ? "selected" : ""}>8</option>
                                    <option value="9" ${u.grade == 9 ? "selected" : ""}>9</option>
                            </select>
                        </div> 
                            <div class="form-group">
                                <label for="clas" class="text-info">Lớp:</label><br>
                                <input type="text" name="clas" id="clas" value="${u.clas}" autocomplete="off" placeholder="*/*" class="form-control">
                            </div>
                        </c:if>
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
<%@include file="include/Footer_admin.jsp" %>