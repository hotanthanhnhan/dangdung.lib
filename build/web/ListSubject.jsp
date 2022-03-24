<%-- 
    Document   : ListBook
    Created on : Nov 24, 2021, 5:12:18 PM
    Author     : ThanhNhan
--%>

<%@include file="./include/Header_admin.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 

<div class="container-fluid" style="margin-top: 20px"> 
    <table class="table" style="font-size: 18px" cellspacing="3" cellpadding="3">
<!--        <colgroup>
            <col width="400" span="1">
            <col width=auto span="1">
            <col width=auto span="1">
            <col width=auto span="1">
            <col width="120" span="1">
            <col width=auto span="1">
            <col width=auto span="1">
            <col width=auto span="1">
        </colgroup>-->
        <thead>
            <tr>
                <th scope="col">Số thứ tự</th>
                <th scope="col">Tên môn học</th>
                <th scope="col">Mã môn học</th>
                <th scope="col">Số lượng sách</th>                
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="l" varStatus="s">            
                <tr>
                    <td style="text-align: center">${s.index+1}</td> 
                    <td>${l.subjectName}</td>    
                    <td style="text-align: center">${l.subjectID}</td>    
                    <td style="text-align: center">${l.count}</td>                    
                </tr>
            </c:forEach>               
        </tbody>
    </table>
</div> 
<%@include file="include/Footer_admin.jsp" %>