<%-- 
    Document   : CreateStudent
    Created on : Nov 21, 2021, 8:13:31 PM
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
                        <form action="createstudent" method="POST" enctype="multipart/form-data">
                            <h3 class="text-center text-info">Thêm học sinh</h3>                               
                            <div class="form-group">
                                <label for="listStudent" class="text-info">Danh sách học sinh:</label><br>                            
                                <input type="file" name="listStudent" id="listStudent" class="form-control" value="">
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