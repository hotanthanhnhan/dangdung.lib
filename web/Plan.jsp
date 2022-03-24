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
                <th scope="col">Tiêu đề</th>                
                <th scope="col">Mô tả</th>
                <th scope="col">Hình ảnh</th>                
                <th scope="col">Thời gian</th> 
                <th scope="col">Trạng thái</th>
                <th scope="col">Xử lý</th>
                <th scope="col">Sửa</th>
                <th scope="col">Xóa</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="l" varStatus="s">
                <tr>
                    <td style="text-align: center">${s.index+1}</td>
                    <td>${l.title}</td>
                    <td><textarea class="form-control" style="overflow: auto; font-size: 20px; background: none" rows="6" readonly>${l.description}</textarea></td>                         
                    <td>
                        <c:if test="${l.planImg != null}">
                            <img src="${l.planImg}" alt="${l.title}" style="width: 300px">
                        </c:if>                            
                    </td>                                        
                    <td>${l.time}</td>
                    <td id="status${l.planID}">
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
                            <button onclick="handle(${l.planID})">Hiện/Ẩn</button>
                        </form>                            
                    </td>
                    <td style="text-align: center"><a href="updateplan?id=${l.planID}">Sửa</a></td>
                    <td style="text-align: center"><a href="deleteplan?id=${l.planID}">Xóa</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div> 

<script>
    function handle(id) {
        $.ajax({
            url: '/dangdung.lib/plan',
            type: 'POST',
            data: {
                id: id
            },
            success: function () {
                var status = document.getElementById("status" + id).innerHTML;
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