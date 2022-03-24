<%-- 
    Document   : NewBook
    Created on : Nov 1, 2021, 3:55:43 PM
    Author     : ThanhNhan
--%>

<%@include file="./include/Header_admin.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 

<div class="container-fluid" style="margin-top: 20px"> 
    <div class="container pt-5">
        <div class="row justify-content-center align-items-center">
            <div class="col-md-6">
                <div class="col-md-12">
                    <form action="newactive" method="POST" enctype="multipart/form-data">
                        <h3 class="text-center text-info">Thêm hoạt động mới</h3>                               
                        <div class="form-group">
                            <label for="name" class="text-info">Tên hoạt động:</label><br>
                            <input type="text" name="name" autocomplete="off" id="name" class="form-control" required>
                        </div>                                               
                        <div class="form-group">
                            <label for="activeImg" class="text-info">Hình ảnh:</label><br>                            
                            <input type="file" name="activeImg" id="activeImg" class="form-control" required>
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
<%@include file="include/Footer_admin.jsp" %>