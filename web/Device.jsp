<%-- 
    Document   : Category
    Created on : Nov 23, 2021, 9:57:46 PM
    Author     : ThanhNhan
--%>


<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="./include/Header_user.jsp" %>

<section class="library">
    <div class="container">
        <div class="row">
            <div class="col-lg-4">
                <form action="device" method="post" style="display: flex; margin-top: 7%">
                    <input name="v" class="input form-control" style="height: 46px" value="${v}" autocomplete="off" placeholder="Tìm kiếm trong thiết bị...">
                    <input name="id" type="text" hidden value="${tag}" readonly>
                    <button class="search-btn btn btn-danger">Tìm kiếm</button>
                </form>
                <div class="library__categories">
                    <div class="library__categories__all">
                        <i class="fa fa-bars"></i>
                        <a href="device"><span>Tất cả môn học</span></a>
                    </div>
                    <ul>
                        <c:forEach items="${listSubject}" var="s">
                            <li><a href="device?id=${s.subjectID}" class="${tag == s.subjectID ? "active" : ""}">${s.subjectName}</a></li>                                              
                            </c:forEach>
                    </ul>
                </div>
            </div>
            <p style="color: red; font-size: 20px; margin-top: 20px">${mes}</p>
            <div class="col-lg-8 products">               
                <c:forEach items="${list}" var="d">
                    <div class="product col-md-4">
                        <div class="product-img tb">
                            <img height="100%" src="${d.deviceImg}" alt="${d.deviceName}">
                            <div class="product-label">                                
                            </div>
                        </div>
                        <div class="product-body">                                            
                            <h3 class="product-name"><a>${d.deviceName}</a></h3>                             
                            <div class="product-rating">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                            </div>
                            <div class="product-btns">
                                <c:choose>
                                    <c:when test="${d.quantity > 0}">
                                        <form style="width: 100%; text-align: center; display: flex" method="post" action="borrowdevice">
                                            <input type="text" name="id" hidden readonly value="${d.deviceID}">
                                            <input style="height: 42px" type="number" class="form-control" value="1" name="quantity" required width="10px" placeholder="Số lượng mượn...">
                                            <button type="submit" class="add-to-wishlist btn btn-default" style="padding: 2px"><a><i style="padding: 2px" class="fa fa-shopping-cart"></i></a><span class="tooltipp">Mượn</span></button>                                                
                                        </form>        
                                            </c:when>
                                            <c:when test="${d.quantity == 0}">
                                        <a>Hết thiết bị</a>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>
<%@include file="include/Footer_user.jsp" %>