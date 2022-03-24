<%-- 
    Document   : ListBooks
    Created on : Nov 24, 2021, 9:23:25 PM
    Author     : ThanhNhan
--%>

<%@include file="./include/Header_admin.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 

<div class="container-fluid" style="margin-top: 20px">
    <a href="./sach.xls" download="sach.xls"><button class="btn btn-success">Kết xuất excel</button></a>
    <table class="table" style="font-size: 18px" cellspacing="3" cellpadding="3">
        <colgroup>
            <col width=230 span="1">
            <col width=160 span="1">
            <col width=120 span="1">
            <col width=120 span="1">
            <col width=180 span="1">
            <col width=140 span="1">
            <col width=130 span="1">
            <col width=130 span="1">            
        </colgroup>
        <thead>
            <tr>
                <th scope="col">Tên sách</th>                
                <th scope="col">Tác giả</th>
                <th scope="col">Thể loại</th>
                <th scope="col">Môn học</th>
                <th scope="col">Mã sách</th>
                <th scope="col">Trạng thái</th>
                <th scope="col">Số lượng tồn kho</th>
                <th scope="col">Số lượng ban đầu</th>                
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="l">            
                <tr>
                    <td>${l.bookName}</td>                    
                    <td>
                        <c:forEach items="${l.authors}" var="a">
                            ${a.authorName}<br>
                        </c:forEach>
                    </td>
                    <td>${l.categoryName}</td>
                    <td>${l.subjectName}</td>
                    <td>
                        <c:forEach items="${l.books}" var="a">
                            ${a.code}<br>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${l.books}" var="a">
                            ${a.typeName}<br>
                        </c:forEach>
                    </td>
                    <td style="text-align: center">${l.quantity}</td>
                    <td style="text-align: center">${l.total}</td>                    
                </tr>
            </c:forEach>   
                <tr style="font-weight: bold">
                    <td style="display: flex; justify-content: space-between"><div>Tổng số:</div><div>${size}</div></td>                    
                    <td></td>
                    <td></td>
                    <td></td>
                    <td colspan="2"></td>
                    <td style="text-align: center">${sum}</td>
                    <td style="text-align: center">${total}</td>                    
                </tr>
        </tbody>
    </table>
</div> 


<%@include file="include/Footer_admin.jsp" %>