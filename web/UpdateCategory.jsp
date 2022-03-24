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
                    <form action="updatecategory" method="POST">
                        <h3 class="text-center text-info">Thay đổi thông tin thể loại</h3>                           
                        <input type="text" autocomplete="off" name="id" value="${d.categoryID}" hidden readonly>
                        <div class="form-group">
                            <label for="categoryID" class="text-info">Mã thể loại:</label><br>
                            <input type="text" autocomplete="off" name="categoryID" id="categoryID" value="${d.categoryID}" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="categoryName" class="text-info">Tên thể loại:</label><br>
                            <input type="text" autocomplete="off" name="categoryName" id="categoryName" value="${d.categoryName}" class="form-control" required>
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