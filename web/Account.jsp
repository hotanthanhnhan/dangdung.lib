<%-- 
    Document   : Account
    Created on : Oct 24, 2021, 3:56:30 PM
    Author     : ThanhNhan
--%>

<%@include file="./include/Header_admin.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 

<div class="container-fluid" style="margin-top: 20px">     
    <div>
        <div class="text-center mb-5" ${st}>
            <a href="createstudent">Thêm học sinh?</a>
        </div>
        <div class="text-center mb-5" ${te}>
            <a href="createteacher">Thêm giáo viên?</a>
        </div>
        <table border="1" cellspacing="3" cellpadding="3" style="margin: auto; font-size: 120%">
            <thead>
                <tr style="text-align: center">
                    <th>${tdn}</th>
                    <th>Họ và tên</th>
                    <th ${st}>Khối</th>
                    <th ${st}>Lớp</th>
                    <th>Email</th>
                    <th>Lần đăng nhập cuối</th>
                    <th>Số sách đang mượn</th> 
                    <th>Sửa thông tin</th>  
                    <th>Đặt lại mật khẩu</th>    
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="l">
                    <tr>
                        <td>${l.userID}</td>
                        <td>${l.fullName}</td>
                        <td ${st} style="text-align: center">${l.grade}</td>
                        <td ${st}>${l.clas}</td>
                        <td>${l.email}</td>
                        <td>${l.lastAccess}</td>
                        <td style="text-align: center">${l.quantityBookReceipt}</td>  
                        <td style="text-align: center;">
                            <c:if test="${l.userTypeID != 0}">
                                <a style="color: blue" href="updateaccount?id=${l.userID}">Sửa</a>
                            </c:if>                            
                        </td> 
                        <td style="text-align: center;">
                            <c:if test="${l.userTypeID != 0}">
                                <a style="color: red" onclick="reset('${l.userID}')">Reset</a>
                            </c:if>                            
                        </td> 
                    </tr>
                </c:forEach>
            </tbody>
        </table>        

    </div>
</div> 
<script>
    function reset(id) {
        var conf = confirm("Bạn có chắc chắn muốn đặt lại mật khẩu cho tài khoản '"+id+"' không?");
        if (conf) {
            $.ajax({
                url: '/dangdung.lib/reset',
                type: 'POST',
                data: {
                    id: id,
                },
                success: function () {
                    alert("Đặt lại mật khẩu mặt định '123' cho tài khoản '"+id+"' thành công!")
                }
            });
        }

    }
</script>                                        
<%@include file="include/Footer_admin.jsp" %>