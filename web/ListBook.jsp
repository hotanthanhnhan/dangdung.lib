<%-- 
    Document   : ListBook
    Created on : Nov 24, 2021, 5:12:18 PM
    Author     : ThanhNhan
--%>

<%@include file="./include/Header_admin.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 

<div class="container-fluid" style="margin-top: 20px"> 
    <table class="table" style="font-size: 15px" cellspacing="3" cellpadding="3">
        <colgroup>
            <col width="150" span="1">
            <col width=auto span="1">
            <col width=auto span="1">
            <col width=auto span="1">
            <col width="90" span="1">
            <col width=auto span="1">
            <col width=auto span="1">
            <col width=auto span="1">
            <col width=auto span="1">
        </colgroup>
        <thead>
            <tr>
                <th scope="col">Tên sách</th>
                <th scope="col">Ảnh bìa</th>
                <th scope="col">Tác giả</th>
                <th scope="col">Thể loại</th>
                <th scope="col">Môn học</th>
                <th scope="col">Số lượng<br>tồn kho</th>
                <th scope="col">Số lượng<br>ban đầu</th>
                <th scope="col">Chỉnh sửa</th>
                <th scope="col">Xóa</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="l">            
                <tr id="b${l.bookID}">
                    <td>${l.bookName}</td>
                    <td><img width="100px" src="${l.bookImg}" alt="${l.bookName}"></td>
                    <td>
                        <c:forEach items="${l.authors}" var="a">
                            ${a.authorName}<br>
                        </c:forEach>
                    </td>
                    <td>${l.categoryName}</td>
                    <td>${l.subjectName}</td>
                    <td style="text-align: center">${l.quantity}</td>
                    <td style="text-align: center">${l.total}</td>
                    <td style="text-align: center"><a href="updatebook?id=${l.bookID}">Sửa</a></td>
                    <td style="text-align: center">
                        <c:if test="${l.check}">                            
                            <a style="color: red;" onclick="deleted(${l.bookID}, 'b')">Xóa</a>
                        </c:if>                        
                    </td>
                </tr>
            </c:forEach>               
        </tbody>
    </table>
</div> 
<%@include file="include/Footer_admin.jsp" %>