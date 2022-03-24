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
            <col width="200" span="1">
            <col width="auto" span="1">
            <col width="auto" span="1">
            <col width=auto span="1">
            <col width=auto span="1">
        </colgroup>
        <thead>
            <tr>
                <th scope="col">Họ và tên</th>                
                <th scope="col">Lớp</th>                
                <th scope="col">Sách</th>
                <th scope="col">Cảm nghĩ</th>                
                <th scope="col">Thời gian</th> 
                <th scope="col">Trạng thái</th>
                <th scope="col">Xử lý</th>
                <th scope="col">Xóa</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="l">
                <tr>
                    <td>${l.fullName}</td>
                    <td>${l.clas}</td>                    
                    <td>${l.bookName}</td>                    
                    <td><textarea required name="idea" id="idea" class="form-control" style="overflow: auto; font-size: 20px; background: none" rows="6" cols="30" readonly>${l.content}</textarea></td>        
                    <td>${l.time}</td>
                    <td id="status${l.ideaID}">
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
                            <button onclick="handle(${l.ideaID})">Hiện/Ẩn</button>
                        </form>                            


                    </td>
                    <td><a href="deleteidea?id=${l.ideaID}">Xóa</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div> 

<script>
    function handle(id) {
        $.ajax({
            url: '/dangdung.lib/idea',
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