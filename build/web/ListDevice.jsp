<%-- 
    Document   : ListBook
    Created on : Nov 24, 2021, 5:12:18 PM
    Author     : ThanhNhan
--%>

<%@include file="./include/Header_admin.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 

<div class="container-fluid" style="margin-top: 20px">
    <table class="table" style="font-size: 18px" cellspacing="3" cellpadding="3">
        <colgroup>
            <col width="400" span="1">
            <col width=auto span="1">
            <col width="auto" span="1">
            <col width=auto span="1">
            <col width=auto span="1">
            <col width=auto span="1">
            <col width=auto span="1">
        </colgroup>
        <thead>
            <tr>
                <th scope="col">Tên thiết bị</th>
                <th scope="col">Hình ảnh</th>                                
                <th scope="col">Môn học</th>
                <th scope="col">Số lượng tồn kho</th>
                <th scope="col">Số lượng ban đầu</th>
                <th scope="col">Chỉnh sửa</th>
                <th scope="col">Chỉnh sửa</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="l">            
                <tr id="d${l.deviceID}">
                    <td>${l.deviceName}</td>
                    <td><img width="200px" src="${l.deviceImg}" alt="${l.deviceName}"></td>
                    <td>${l.subjectName}</td>
                    <td style="text-align: center">${l.quantity}</td>
                    <td style="text-align: center">${l.total}</td>
                    <td style="text-align: center"><a href="updatedevice?id=${l.deviceID}">Sửa</a></td>
                    <td style="text-align: center">
                        <c:if test="${l.check}">
                            <a style="color: red;" onclick="deleted(${l.deviceID}, 'd')">Xóa</a>
                        </c:if>                        
                    </td>
                </tr>
            </c:forEach>               
        </tbody>
    </table>
</div> 
<%@include file="include/Footer_admin.jsp" %>