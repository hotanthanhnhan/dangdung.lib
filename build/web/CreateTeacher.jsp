<%-- 
    Document   : CreateTeacher
    Created on : Nov 22, 2021, 2:10:36 AM
    Author     : ThanhNhan
--%>

<%@include file="./include/Header_admin.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 

<div class="container-fluid" style="margin-top: 20px"> 
    <div class="container-fluid" style="margin-top: 20px"> 
        <div class="container pt-5">
            <div class="row justify-content-center align-items-center">
                <div class="col-md-6">
                    <div class="col-md-12">
                        <form action="createteacher" method="POST" enctype="multipart/form-data">
                            <h3 class="text-center text-info">Thêm giáo viên</h3>                               
                            <div class="form-group">
                                <label for="listTeacher" class="text-info">Danh sách giáo viên:</label><br>                            
                                <input type="file" name="listTeacher" id="listTeacher" class="form-control" value="">
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
</div> 

<%@include file="include/Footer_admin.jsp" %>