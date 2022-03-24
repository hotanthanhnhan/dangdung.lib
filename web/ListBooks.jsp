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
            <col width="200" span="1">
            <col width=auto span="1">
            <col width=auto span="1">
            <col width=auto span="1">            
        </colgroup>
        <thead>
            <tr>
                <th scope="col">STT</th>   
                <th scope="col">Tên sách</th>                                                                
                <th scope="col">Mã sách</th>   
                <th scope="col">Người mượn</th> 
                <th scope="col">Trạng thái</th> 
                <th scope="col">Xóa sách</th> 
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="l" varStatus="loop">            
                <tr id="bs${l.bookCode}">
                    <td style="text-align: center">${loop.index+1}</td>
                    <td>${l.bookName}</td>                    
                    <td>${l.code}</td>   
                    <td><i style="color: ${l.typeID == 0 ? "green" : "blue"}">${l.borrower}</i></td>
                    <td><i style="color: ${l.typeID == 0 ? "green" : "blue"}">${l.typeName}</i></td>
                    <td style="text-align: center">
                        <c:if test="${l.typeID == 0}">
                            <a style="color: red" onclick="deleteBook(${l.bookCode})">Xóa</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>               
        </tbody>
    </table>
</div> 
<script>    
        function deleteBook(id){
        var conf = confirm("Bạn có chắc chắn muốn xóa không?");
        if(conf){
            $.ajax({
                url: '/dangdung.lib/deletebook',
                type: 'GET',
                data: {
                    id: id,                    
                },
                success: function () {
                    document.getElementById("bs"+id).remove();
                    alert("Xóa thành công!")
                }
            })
        }
    }    
</script>
<%@include file="include/Footer_admin.jsp" %>