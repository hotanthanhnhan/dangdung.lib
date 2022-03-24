<%-- 
    Document   : ListBooks
    Created on : Nov 24, 2021, 9:23:25 PM
    Author     : ThanhNhan
--%>

<%@include file="./include/Header_admin.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 

<div class="container-fluid" style="margin-top: 20px">
    <a href="./tacgia.xls" download="tacgia.xls"><button class="btn btn-success">Kết xuất excel</button></a>
    <table class="table" style="font-size: 18px">
        <thead>
            <tr>
                <th scope="col">Số thứ tự</th>
                <th scope="col">Tên tác giả</th>
                <th scope="col">Số lượng đầu sách</th>  
                <th scope="col">Số lượng sách trong kho</th>  
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="l" varStatus="s">            
                <tr>
                    <th scope="row" style="text-align: center">${s.index+1}</th> 
                    <td>${l.authorName}</td>                                        
                    <td style="text-align: center">${l.count}</td>   
                    <td style="text-align: center">${l.counts}</td>   
                </tr>
            </c:forEach>  
        </tbody>
    </table>            
</div>


<%@include file="include/Footer_admin.jsp" %>