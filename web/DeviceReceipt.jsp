<%-- 
    Document   : ListBooks
    Created on : Nov 24, 2021, 9:23:25 PM
    Author     : ThanhNhan
--%>

<%@include file="./include/Header_admin.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 

<div class="container-fluid" style="margin-top: 20px">         
    <table class="table" style="font-size: 18px" cellspacing="3" cellpadding="3">
                <colgroup>                                
                    <col width=auto span="1">
                    <col width=300 span="1">
                    <col width="auto" span="1">
                    <col width="230" span="1">
                    <col width="auto" span="1">
                    <col width="230" span="1">
                    <col width="230" span="1">
                    <col width="auto" span="1">
                </colgroup>
        <thead>
            <tr>
                <th scope="col">Họ và tên</th>               
                <th scope="col">Thiết bị mượn</th>
                <th scope="col">Số lượng</th>
                <th scope="col">Mã thiết bị</th>
                <th scope="col">Trạng thái</th>
                <th scope="col">Ngày mượn</th>
                <th scope="col">Ngày trả</th>
                <th scope="col">Trả thiết bị</th>
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
                    <td id='devicereceipt${l.deviceReceiptID}'>
                        <c:choose>
                            <c:when test="${l.statusID == 4}">
                                <form onsubmit="return false" method="post">                                    
                                    <button onclick="devicereceipt(${l.deviceReceiptID}, '${l.fullName}', '${l.deviceName}', ${l.quantity})" style="border: none; background: none; color: blue">Trả thiết bị</button>
                                </form>                                                                                 
                            </c:when>
                            <c:otherwise>
                                <i>Đã trả</i>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div> 
<script>
    function devicereceipt(deviceReceiptID, name, device, quantity) {
        var devicereceipt = document.getElementById("devicereceipt"+deviceReceiptID);
        var status = document.getElementById("status"+deviceReceiptID);
        var pay = document.getElementById("pay"+deviceReceiptID);
        var today = new Date();
        var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds() + " " + today.getDate() + "-" + (today.getMonth()+1) + "-" + today.getFullYear();        
        var conf = confirm("Xác nhận cho người dùng '"+name+"' trả "+quantity+" thiết bị '"+device+"'?");
        if (conf) {
            
        }
        $.ajax({
            url: '/dangdung.lib/devicereceipt',
            type: 'POST',
            data: {
                id: deviceReceiptID
            },
            success: function () {
                devicereceipt.innerHTML = "<i>Đã trả</i>";
                status.innerHTML = "Đã trả";
                status.style = "color: green";
                pay.innerHTML = time;
            }
        });
    }
</script>
<%@include file="include/Footer_admin.jsp" %>