<%-- 
    Document   : UpdateBooks
    Created on : Oct 31, 2021, 1:53:59 PM
    Author     : ThanhNhan
--%>

<%@include file="./include/Header_admin.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 

<div class="container-fluid" style="margin-top: 20px"> 
    <div class="container pt-5">
        <div class="row justify-content-center align-items-center">
            <div class="col-md-6">
                <div class="col-md-12">
                    <form action="updateauthor" method="POST">
                        <h3 class="text-center text-info">Thay đổi thông tin tác giả</h3>   
                        <input type="number" name="authorID" value="${d.authorID}" hidden readonly>
                        <div class="form-group">
                            <label for="authorName" class="text-info">Tên tác giả:</label><br>
                            <input type="text" autocomplete="off" name="authorName" id="authorName" value="${d.authorName}" class="form-control" required>
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
    <%@include file="include/Footer_admin.jsp" %>