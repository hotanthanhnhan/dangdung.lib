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
            <col width=auto span="1">
            <col width="auto" span="1">
            <col width="250" span="1">
            <col width="170" span="1">
            <col width="230" span="1">
            <col width="350" span="1">
        </colgroup>
        <thead>
            <tr>
                <th scope="col">STT</th>
                <th scope="col">Họ và tên</th>
                <th scope="col">Chức vụ</th>
                <th scope="col">Lớp</th>
                <th scope="col">Số sách<br>đang mượn</th>
                <th scope="col">Sách mượn</th>
                <th scope="col">Trạng thái</th>
                <th scope="col">Thời gian mượn</th>
                <th scope="col">Xử lý</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="l" varStatus="s">
                <tr>
                    <td>${s.index + 1}</td>
                    <td>${l.fullName}</td>
                    <td>${l.typeName}</td>
                    <td style="text-align: center">${l.typeID == 3 ? l.clas : "-"}</td>
                    <td style="text-align: center">${l.quantityBookReceipt}</td>
                    <td>${l.bookName}</td>
                    <td style="color: ${l.statusID == 2 ? "orange" : "red"}" id="status${l.bookOrderID}">${l.status}</td>
                    <td>${l.orderDate}</td>
                    <td id="handle${l.bookOrderID}" style="text-align: center">
                        <c:choose>
                            <c:when test="${l.statusID == 2}">
                                <a style="color: green" onclick="openAccept(${l.bookOrderID})"> Đồng ý </a> - <a style="color: red" onclick="openRefuse(${l.bookOrderID})"> Từ chối </a>
                                <form action="bookorder" id="refure${l.bookOrderID}" hidden="" method="post">
                                    Lý do: <input type="text" name="reason" autocomplete="off" required>
                                    <input type="text" name="type" value="2" readonly required hidden="">
                                    <input type="text" name="o" value="${l.bookOrderID}" readonly required hidden="">
                                    <input type="submit" value="Gửi">
                                </form>                             
                                    <form action="bookorder" id="accept${l.bookOrderID}" hidden="" method="post">
                                    Mã sách: <select name="bookCode">
                                        <c:forEach items="${l.listBooks}" var="s">
                                            <option value="${s.bookCode}">${s.code}</option>                                        
                                        </c:forEach>
                                    </select>                                    
                                    <input value="${l.bookOrderID}" name="o" hidden="">
                                    <input type="text" value="1" name="type" hidden="">
                                    <br>Thời gian nhận: <input type="datetime-local" name="time" value="" required>                                    
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
        refure.style = "display: block!important; text-align: left";
        accept.style = "display: none!important";
    }
    function openAccept(id) {
        const refure = document.getElementById("refure" + id);
        const accept = document.getElementById("accept" + id);
        refure.style = "display: none!important";
        accept.style = "display: block!important; text-align: left";
    }
    function refuse(o, type) {
        var reason = document.getElementById("reason" + o).value;
        var handle = document.getElementById("handle" + o);
        var status = document.getElementById("status" + o);

        $.ajax({
            url: '/dangdung.lib/bookorder',
            type: 'POST',
            data: {
                o: o,
                type: type,
                reason: reason
            },
            success: function () {
                status.innerHTML = "Từ chối";
                status.style = "color: red";
                handle.innerHTML = "<i>Đã xử lý</i>";
            }
        });
    }    
</script>
<%@include file="include/Footer_admin.jsp" %>