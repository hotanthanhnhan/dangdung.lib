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
                        <form action="newdevice" method="POST" enctype="multipart/form-data">
                            <h3 class="text-center text-info">Thêm thiết bị mới</h3>                               
                        <div class="form-group">
                            <label for="deviceName" class="text-info">Tên thiết bị:</label><br>
                            <input type="text" name="deviceName" id="deviceName" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="subjectID" class="text-info">Môn học:</label><br>
                            <select class="form-control" name="subjectID" id="subjectID">
                                <c:forEach items="${su}" var="s">
                                    <option value="${s.subjectID}">${s.subjectName}</option>
                                </c:forEach>                                
                            </select>
                        </div>                                                
                        <div class="form-group">
                            <label for="deviceImg" class="text-info">Ảnh thiết bị:</label><br>                            
                            <input type="file" name="deviceImg" id="deviceImg" class="form-control">
                        </div>                        
                        <div class="form-group">
                            <label for="quantity" class="text-info">Số lượng thiết bị:</label><br>
                            <input type="number" name="quantity" id="quantity" class="form-control" required>
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