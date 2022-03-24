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
                <colgroup>
                    <col width="600" span="1">
                    <col width=auto span="1">
                    <col width=auto span="1">
                    <col width=auto span="1">
                    <col width="auto" span="1">
                </colgroup>
                <thead>
                    <tr>
                        <th scope="col">Tên sách</th>                        
                        <th scope="col">Trạng thái</th>
                        <th scope="col">Thời gian đặt mượn</th>
                        <th scope="col">Hủy</th>
                    </tr>
                </thead>
                <tbody id="book-order">
                    <c:forEach items="${list}" var="l">
                        <tr>
                            <td>${l.bookName}</td>                            
                            <td style="color: orange">${l.status}</td>
                            <td>${l.orderDate}</td>
                            <td>
                                <c:if test="${l.statusID == 2}">
                                    <a style="color: red; border: none; background: none" onclick="cancelBookOrder(${l.bookOrderID})">Hủy</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</section>
<script>
    setInterval(function () {
        $.ajax({
            url: '/dangdung.lib/loadbookorder',
            type: 'POST',
            data: {
            },
            success: function (data) {
                var bookOrder = document.getElementById("book-order");
                bookOrder.innerHTML = data;
            }
        });
    }, 1000);

    function cancelBookOrder(id) {
        var conf = confirm("Bạn có chắc chắn muốn hủy không?");
        if (conf) {
            $.ajax({
                url: '/dangdung.lib/listbookorder',
                type: 'POST',
                data: {
                    id: id
                },
                success: function () {
                    alert("Hủy thành công!")
                }
            })
        }
    }
</script>
<%@include file="include/Footer_user.jsp" %>