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
                    <form action="updateplan" method="POST" enctype="multipart/form-data">
                        <h3 class="text-center text-info">Chỉnh sửa kế hoạch mới</h3>    
                        <input type="number" name="id" readonly value="${p.planID}" hidden="">
                        <div class="form-group">
                            <label for="title" class="text-info">Tên kế hoạch:</label><br>
                            <input type="text" name="title" autocomplete="off" id="title" value="${p.title}" class="form-control" required>
                        </div>   
                        <div class="form-group">
                            <label for="description" class="text-info">Mô tả kế hoạch:</label><br>
                            <textarea required name="description" id="description" class="form-control" style="overflow: auto" rows="10" placeholder="Mô tả kế hoạch...">${p.description}</textarea>
                        </div>                         
                        <div class="form-group">
                            <label for="planImg" class="text-info">Hình ảnh:</label><br>  

                            <c:if test="${p.planImg != null}">
                                <img src="${p.planImg}" alt="${p.title}" width="100%">
                            </c:if> 
                            <input type="file" name="planImg" id="planImg" class="form-control">
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