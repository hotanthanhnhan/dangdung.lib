<%-- 
    Document   : ListBooks
    Created on : Nov 24, 2021, 9:23:25 PM
    Author     : ThanhNhan
--%>

<%@include file="./include/Header_admin.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 

<div class="container-fluid" style="margin-top: 20px">
    <a href="./thietbi.xls" download="thietbi.xls"><button class="btn btn-success">Kết xuất excel</button></a>
    <table class="table" style="font-size: 18px" cellspacing="3" cellpadding="3">
        <colgroup>
            <col width="400" span="1">            
            <col width="auto" span="1">
            <col width=auto span="1">
            <col width=auto span="1">
            <col width=auto span="1">
            <col width=auto span="1">
        </colgroup>
        <thead>
            <tr>
                <th scope="col">Tên thiết bị</th>                                             
                <th scope="col">Môn học</th>
                <th scope="col">Mã thiết bị</th>
                <th scope="col">Trạng thái</th>
                <th scope="col">Số lượng tồn kho</th>
                <th scope="col">Số lượng ban đầu</th>                
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="l">            
                <tr>
                    <td>${l.deviceName}</td>                    
                    <td>${l.subjectName}</td>
                    <td colspan="1">
                        <c:forEach items="${l.devices}" var="a">
                            ${a.code}<br>
                        </c:forEach>
                    </td>
                    <td colspan="1">
                        <c:forEach items="${l.devices}" var="a">
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
                    <td colspan="2"></td>
                    <td style="text-align: center">${sum}</td>
                    <td style="text-align: center">${total}</td>                    
                </tr>
        </tbody>
    </table>
</div> 


<%@include file="include/Footer_admin.jsp" %>