<%-- 
    Document   : ListBooks
    Created on : Nov 24, 2021, 9:23:25 PM
    Author     : ThanhNhan
--%>

<%@include file="./include/Header_admin.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 

<div class="container-fluid" style="margin-top: 20px">         
    <table class="table" style="font-size: 18px" cellspacing="3" cellpadding="3">
        <!--        <colgroup>            
                    <col width=auto span="1">
                    <col width=auto span="1">                    
                    <col width="400" span="1">
                    <col width="auto" span="1">
                    <col width="auto" span="1">
                    <col width=auto span="1">
                    <col width=auto span="1">
                </colgroup>-->
        <thead>
            <tr>
                <th scope="col">Số thứ tự</th>                
                <th scope="col">Tên hoạt động</th>                
                <th scope="col">Hình ảnh</th>                             
                <th scope="col">Thời gian</th> 
                <th scope="col">Trạng thái</th>
                <th scope="col">Ẩn/Hiện</th>
                <th scope="col">Xóa</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="l" varStatus="t">
                <tr>
                    <td style="text-align: center">${t.index+1}</td>
                    <td>${l.name}</td>                    
                    <td><img width="500px" alt="${l.name}" src="${l.activeImg}"></td>                                              
                    <td>${l.time}</td>
                    <td id="status${l.activeID}">
                        <c:choose>
                            <c:when test="${l.appear}">                                
                                Đang hiện                          
                            </c:when>
                            <c:otherwise>
                                Đang ẩn
                            </c:otherwise>
                        </c:choose>

                    </td>
                    <td>
                        <form onsubmit="return false" method="post">                                    
                            <button onclick="handle(${l.activeID})">Ẩn/Hiện</button>
                        </form>                            
                    </td>
                    <td style="text-align: center"><a href="deleteactive?id=${l.activeID}">Xóa</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div> 

<script>
    function handle(id) {
        var status = document.getElementById("status" + id).innerHTML;
        $.ajax({
            url: '/dangdung.lib/listactive',
            type: 'POST',
            data: {
                id: id
            },
            success: function () {
                if (status.trim() == "Đang ẩn") {
                    document.getElementById("status" + id).innerHTML = "Đang hiện";
                } else {
                    document.getElementById("status" + id).innerHTML = "Đang ẩn";
                }
            }
        });
    }
</script>
<%@include file="include/Footer_admin.jsp" %>