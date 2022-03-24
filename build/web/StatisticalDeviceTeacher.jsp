<%-- 
    Document   : ListBooks
    Created on : Nov 24, 2021, 9:23:25 PM
    Author     : ThanhNhan
--%>

<%@include file="./include/Header_admin.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 

<div class="container-fluid" style="margin-top: 20px">
    <form action="statisticaldeviceteacher" method="post">
        <b>Thời gian mượn từ:</b> <input type="date" name="from" value="${from}" required> <b>Đến:</b> <input type="date" name="to" value="${to}" required>
        <input type="submit" name="action" value="Xem" class="btn btn-success">   
        <a href="./giaovienmuonthietbi.xls" download="giaovienmuonthietbi.xls"><button type="button" class="btn btn-success">Kết xuất excel</button></a>
        <i>(Vui lòng chọn <b>xem</b> trước khi kết xuất)</i>
    </form>
        
    
    <table class="table" style="font-size: 18px" cellspacing="3" cellpadding="3">
        <!--        <colgroup>            
                    <col width=auto span="1">
                    <col width=auto span="1">
                    <col width=auto span="1">
                    <col width="250" span="1">
                    <col width="230" span="1">
                    <col width="auto" span="1">
                    <col width="230" span="1">
                    <col width="230" span="1">
                    <col width="auto" span="1">
                </colgroup>-->
        <thead>
            <tr>
                <th scope="col">Họ và tên</th>               
                <th scope="col">Thiết bị mượn</th>
                <th scope="col">Số lượng</th>
                <th scope="col">Mã thiết bị</th>
                <th scope="col">Trạng thái</th>
                <th scope="col">Ngày mượn</th>
                <th scope="col">Ngày trả</th>             
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="l">
                <tr>
                    <td>${l.fullName}</td>                                                          
                    <td>${l.deviceName}</td>
                    <td style="text-align: center">${l.quantity}</td>
                    <td>
                        <c:forEach items="${l.code}" var="s">
                            ${s.code}<br>
                        </c:forEach>
                    </td>
                    <td id="status${l.deviceReceiptID}" style="color: ${l.statusID == 4 ? "orange" : "green"}">${l.status}</td>
                    <td>${l.borrowDate}</td>
                    <td id="pay${l.deviceReceiptID}">${l.payDate}</td>                                        
                </tr>
            </c:forEach>
                <tr style="font-weight: bold">
                <td></td>                                         
                <td>Tổng số: </td>
                <td style="text-align: center">${total}</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>                    
            </tr>
        </tbody>
    </table>
</div> 


<%@include file="include/Footer_admin.jsp" %>