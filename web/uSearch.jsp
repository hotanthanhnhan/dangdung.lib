<%-- 
    Document   : uSearch
    Created on : Nov 24, 2021, 3:15:35 PM
    Author     : ThanhNhan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="./include/Header_user.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>

<section class="search">
    <div class="container">
        <div class="row">
            <p style="color: red; font-size: 20px; margin-top: 20px">${mes}</p>

            <c:if test="${acc.userTypeID == 0 or acc.userTypeID == 2}">
                <div class="col-lg-12 products">
                    <c:forEach items="${list2}" var="d">
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
                                                <input style="height: 42px" class="form-control" type="number" required value="1" name="quantity" width="10px" placeholder="Số lượng mượn...">
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
            </c:if>

            <div class="col-lg-12 products">
                <c:forEach items="${list1}" var="b">
                    <div class="product col-md-3">
                        <div class="product-img">
                            <img height="100%" src="${b.bookImg}" alt="${b.bookName}">
                            <div class="product-label">                                
                            </div>
                        </div>
                        <div class="product-body">                                            
                            <h3 class="product-name"><a>${b.bookName}</a></h3>  
                            <h4 class="product-author"><c:forEach items="${b.authors}" var="a">
                                    ${a.authorName}<br>
                                </c:forEach></h4>   
                            <div class="product-rating">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                            </div>
                            <div class="product-btns">
                                <c:choose>
                                    <c:when test="${b.quantity > 0}">
                                        <button class="add-to-wishlist"><a href="borrowbook?id=${b.bookID}"><i style="padding: 7px" class="fa fa-shopping-cart"></i></a><span class="tooltipp">Mượn sách</span></button>                                                
                                            </c:when>
                                            <c:when test="${b.quantity == 0}">
                                        <a>Hết sách</a>
                                    </c:when>
                                </c:choose>
                                <button class="quick-view"><a href="read?id=${b.bookID}"><i style="padding: 7px" class="fa fa-eye"></i></a><span class="tooltipp">Đọc thử</span></button>
                                <button class="quick-view"><a href="write?id=${b.bookID}"><i style="padding: 7px" class="fa fa-pencil"></i></a><span class="tooltipp">Viết cảm nghĩ</span></button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>
<%@include file="include/Footer_user.jsp" %>