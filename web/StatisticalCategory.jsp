<%-- 
    Document   : ListBooks
    Created on : Nov 24, 2021, 9:23:25 PM
    Author     : ThanhNhan
--%>

<%@include file="./include/Header_admin.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 

<div class="container-fluid" style="margin-top: 20px">
    <a href="./theloai.xls" download="theloai.xls"><button class="btn btn-success">Kết xuất excel</button></a>
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
                <th scope="col">Mã thể loại</th>
                <th scope="col">Tên thể loại</th>
                <th scope="col">Số lượng đầu sách</th> 
                <th scope="col">Số lượng sách trong kho</th> 
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="l" varStatus="s">            
                <tr>
                    <td style="text-align: center">${s.index+1}</td> 
                    <td style="text-align: center">${l.categoryID}</td> 
                    <td>${l.categoryName}</td>                                        
                    <td style="text-align: center">${l.count}</td>  
                    <td style="text-align: center">${l.counts}</td>  
                </tr>
            </c:forEach>  
            <tr style="font-weight: bold">
                <td></td> 
                <td></td> 
                <td>Tổng số: </td>                                        
                <td style="text-align: center">${total}</td>
                <td style="text-align: center">${sum}</td>                    
            </tr>
        </tbody>
    </table>
</div> 


<%@include file="include/Footer_admin.jsp" %>