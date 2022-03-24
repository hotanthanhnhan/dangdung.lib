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
            <col width=200 span="1">
            <col width=auto span="1">
            <col width=auto span="1">
            <col width="200" span="1">
            <col width="230" span="1">
            <col width="auto" span="1">
            <col width="230" span="1">
            <col width="230" span="1">
            <col width="auto" span="1">
        </colgroup>
        <thead>
            <tr>
                <th scope="col">Họ và tên</th>
                <th scope="col">Chức vụ</th>
                <th scope="col">Lớp</th>                
                <th scope="col">Sách mượn</th>
                <th scope="col">Mã sách</th>
                <th scope="col">Trạng thái</th>
                <th scope="col">Ngày mượn</th>
                <th scope="col">Ngày trả</th>
                <th scope="col">Trả sách</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="l">
                <tr>
                    <td>${l.fullName}</td>
                    <td>${l.typeName}</td>
                    <td style="text-align: center">${l.typeID == 3 ? l.clas : "-"}</td>                    
                    <td>${l.bookName}</td>
                    <td>${l.code}</td>
                    <td id="status${l.bookReceiptID}" style="color: ${l.statusID == 4 ? "orange" : "green"}">${l.status}</td>
                    <td>${l.borrowDate}</td>
                    <td id="pay${l.bookReceiptID}">${l.payDate}</td>                    
                    <td id='bookreceipt${l.bookReceiptID}'>
                        <c:choose>
                            <c:when test="${l.statusID == 4}">
                                <form onsubmit="return false" method="post">                                    
                                    <button onclick="bookreceipt(${l.bookReceiptID}, '${l.fullName}', '${l.bookName}')" style="border: none; background: none; color: blue">Trả sách</button>
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
    function bookreceipt(bookReceiptID, name, book) {
        var bookreceipt = document.getElementById("bookreceipt" + bookReceiptID);
        var status = document.getElementById("status" + bookReceiptID);
        var pay = document.getElementById("pay" + bookReceiptID);
        var today = new Date();
        var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds() + " " + today.getDate() + "-" + today.getMonth() + "-" + today.getFullYear();
        var conf = confirm("Xác nhận cho người dùng '"+name+"' trả sách '"+book+"'?");
        if (conf) {
            $.ajax({
                url: '/dangdung.lib/bookreceipt',
                type: 'POST',
                data: {
                    id: bookReceiptID
                },
                success: function () {
                    bookreceipt.innerHTML = "<i>Đã trả</i>";
                    status.innerHTML = "Đã trả";
                    status.style = "color: green";
                    pay.innerHTML = time;
                }
            });
        }
    }
</script>

<%@include file="include/Footer_admin.jsp" %>