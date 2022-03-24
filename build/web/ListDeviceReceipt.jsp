<%-- 
    Document   : Borrowed
    Created on : Nov 4, 2021, 3:31:37 PM
    Author     : ThanhNhan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="./include/Header_user.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>

<section class="library" style="min-height: 42.4vh">
    <div class="container">
        <div class="row">
            <table class="table" style="font-size: 22px; margin-top: 35px" cellspacing="3" cellpadding="3">
                <thead>
                    <tr>
                        <th scope="col">Tên thiết bị</th>     
                        <th scope="col">Số lượng</th>     
                        <th scope="col">Trạng thái</th>
                        <th scope="col">Ngày mượn</th>
                        <th scope="col">Ngày trả</th>
                    </tr>
                </thead>
                <tbody id="device-receipt">
                    <c:forEach items="${list}" var="l">
                        <tr>
                            <td>${l.deviceName}</td>  
                            <td>${l.quantity}</td>  
                            <c:choose>
                                <c:when test="${l.statusID == 4}">
                                    <td style="color: orange">${l.status}</td>
                                </c:when>   
                                <c:when test="${l.statusID == 5}">
                                    <td style="color: green">${l.status}</td>
                                </c:when> 
                            </c:choose>
                            <td>${l.borrowDate}</td>
                            <td>${l.payDate}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</section>
<script>
    setInterval(function (){        
        $.ajax({
                url: '/dangdung.lib/loaddevicereceipt',
                type: 'POST',
                data: {                    
                },
                success: function (data) {                    
                    var deviceReceipt = document.getElementById("device-receipt");
                    deviceReceipt.innerHTML = data;
                }
            });
    }, 1000);
</script>
<%@include file="include/Footer_user.jsp" %>