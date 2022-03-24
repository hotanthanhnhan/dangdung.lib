<%-- 
    Document   : Footer_user
    Created on : Oct 20, 2021, 8:56:38 PM
    Author     : ThanhNhan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>

<footer id="footer">    
    <div class="section">        
        <div class="container">
            <div class="row ft">
                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">Thông tin</h3>
                        <p>Trường THCS Đặng Dung</p>
                        <ul class="footer-links">
                            <li><a><i class="fa fa-map-marker"></i>98 Nguyễn Vịnh - Thị Trấn Sịa</a></li>
                            <li><a><i class="fa fa-phone"></i>0234.3555.213</a></li>
                            <li><a><i class="fa fa-envelope-o"></i>anhdung307@gmail.com</a></li>
                        </ul>
                    </div>
                </div>

                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">Thư viện</h3>
                        <ul class="footer-links">
                            <li><a href="library">Trang chủ</a></li>  
                            <li><a href="category">Thể loại</a></li>                            
                            <li><a href="author">Tác giả</a></li>
                            <li><a href="subject">Môn học</a></li>
                                <c:if test="${acc.userTypeID == 0 or acc.userTypeID == 2}">
                                <li><a href="device">Thiết bị</a></li>
                                </c:if>
                        </ul>
                    </div>
                </div>

                <div class="clearfix visible-xs"></div>

                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">Chức năng</h3>
                        <ul class="footer-links">
                            <li><a href="information">Thông tin tài khoản</a></li>
                            <li><a href="listbookorder">Xem sách đang đợi</a></li>
                            <li><a href="listbookreceipt">Xem lịch sử mượn sách</a></li>       
                            <c:if test="${acc.userTypeID == 0 or acc.userTypeID == 2}">
                            <li><a href="listdeviceorder">Xem thiết bị đang dợi</a></li>
                            <li><a href="listdevicereceipt">Xem lịch sử mượn thiết bị</a></li>  
                            </c:if>
                        </ul>
                    </div>
                </div>

                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">Hỗ trợ</h3>
                        <ul class="footer-links">                            
                            <li><a href="information">Thay đổi thông tin tài khoản</a></li>
                            <li><a href="changepass">Đổi mật khẩu</a></li>                            
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>   
</footer>

<script src="./assets/js/jquery.min.js"></script>
<script src="./assets/js/bootstrap.min.js"></script>
<script src="./assets/js/slick.min.js"></script>
<script src="./assets/js/nouislider.min.js"></script>
<script src="./assets/js/jquery.zoom.min.js"></script>
<script src="./assets/js/main.js"></script>

</body>
</html>