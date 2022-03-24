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
            <col width=auto span="1">
            <col width=auto span="1">
            <col width=300 span="1">
            <col width="auto" span="1">
            <col width="auto" span="1">
            <col width="auto" span="1">
            <col width="350" span="1">
        </colgroup>
        <thead>
            <tr>
                <th scope="col">STT</th>
                <th scope="col">Họ và tên</th>
                <th scope="col">Số thiết bị<br>đang mượn</th>
                <th scope="col">Thiết bị mượn</th>
                <th scope="col">Số lượng</th>
                <th scope="col">Trạng thái</th>
                <th scope="col">Thời gian mượn</th>
                <th scope="col">Xử lý</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="l" varStatus="s">
                <tr>
                    <td>${s.index+1}</td>      
                    <td>${l.fullName}</td>                                        
                    <td style="text-align: center">${l.quantityDeviceReceipt}</td>
                    <td>${l.deviceName}</td>
                    <td style="text-align: center">${l.quantity}</td>
                    <td style="color: ${l.statusID == 2 ? "orange" : "red"}">${l.status}</td>
                    <td>${l.orderDate}</td>
                    <td>
                        <c:choose>
                            <c:when test="${l.statusID == 2}">
                                <a style="color: green" onclick="openAccept(${l.deviceOrderID})"> Đồng ý </a> - <a style="color: red" onclick="openRefuse(${l.deviceOrderID})"> Từ chối </a>
                                <form action="deviceorder" id="refure${l.deviceOrderID}" hidden="" method="post">
                                    Lý do: <input type="text" name="reason" autocomplete="off" required>
                                    <input type="text" name="type" value="2" required hidden="" readonly>
                                    <input type="text" name="o" value="${l.deviceOrderID}" readonly="" required hidden="">
                                    <input type="submit" value="Gửi">
                                </form>                            
                                <form action="deviceorder" id="accept${l.deviceOrderID}" hidden="" method="post">
                                    <c:forEach step="1" begin="1" end="${l.quantity}" varStatus="i">
                                        Mã thiết bị ${i.index}: <select name="deviceCode${i.index}">
                                            <c:forEach items="${l.listDevices}" var="s">
                                                <option value="${s.deviceCode}">${s.code}</option>
                                            </c:forEach>
                                        </select><br>
                                    </c:forEach>

                                    <br>Thời gian nhận: <input type="datetime-local" name="time" value="" required>
                                    <input type="text" name="type" value="1" required hidden="">
                                    <input type="text" name="o" value="${l.deviceOrderID}" required hidden="">
                                    <input type="submit" value="Gửi">
                                </form>
                            </c:when>

                            <c:otherwise>
                                <i>Đã xử lý</i>
                            </c:otherwise>
                        </c:choose>

                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div> 

<script>
    function openRefuse(id) {
        const refure = document.getElementById("refure" + id);
        const accept = document.getElementById("accept" + id);
        refure.style = "display: block!important";
        accept.style = "display: none!important";
    }
    function openAccept(id) {
        const refure = document.getElementById("refure" + id);
        const accept = document.getElementById("accept" + id);
        refure.style = "display: none!important";
        accept.style = "display: block!important";
    }
</script>
<%@include file="include/Footer_admin.jsp" %>