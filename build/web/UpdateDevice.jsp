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
                        <form action="updatedevice" method="POST" enctype="multipart/form-data">
                            <h3 class="text-center text-info">Thay đổi thông tin thiết bị</h3>   
                            <input type="number" name="deviceID" value="${d.deviceID}" hidden readonly>
                        <div class="form-group">
                            <label for="deviceName" class="text-info">Tên thiết bị:</label><br>
                            <input type="text" name="deviceName" autocomplete="off" id="deviceName" value="${d.deviceName}" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="subjectID" class="text-info">Môn học:</label><br>
                            <select class="form-control" name="subjectID" id="subjectID">
                                <c:forEach items="${su}" var="s">
                                    <option value="${s.subjectID}" ${s.subjectID == d.subjectID ? "selected" : ""}>${s.subjectName}</option>
                                </c:forEach>                                
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="deviceImg" class="text-info">Ảnh thiết bị:</label><br>
                            <img src="${d.deviceImg}" alt="${d.deviceName}" width="100%">
                            <input type="file" name="deviceImg" id="deviceImg" class="form-control">
                        </div>                       
                        <div class="form-group">
                            <label for="quantity" class="text-info">Tổng số lượng:</label><br>
                            <input type="number" name="quantity" autocomplete="off" id="quantity" value="${d.total}" class="form-control" required>
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