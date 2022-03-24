<%-- 
    Document   : Category
    Created on : Nov 23, 2021, 9:57:46 PM
    Author     : ThanhNhan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="./include/Header_user.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>

<section class="library">
    <div class="container">
        <div class="row">
            <div class="col-lg-4">
                <form action="author" method="post" style="display: flex; margin-top: 7%">
                    <input name="v" class="input form-control" style="height: 46px" value="${v}" autocomplete="off" placeholder="Tìm kiếm tên sách trong tác giả...">
                    <input name="id" type="text" hidden value="${tag}" readonly>
                    <button class="search-btn btn btn-danger">Tìm kiếm</button>
                </form>
                <div class="library__categories">
                    <div class="library__categories__all">
                        <i class="fa fa-bars"></i>
                        <a href="author"><span>Tất cả tác giả</span></a>
                    </div>
                    <ul>
                        <c:forEach items="${listAuthor}" var="a">
                            <li><a href="author?id=${a.authorID}" class="${tag == a.authorID ? "active" : ""}">${a.authorName}</a></li>                                              
                            </c:forEach>
                    </ul>
                </div>
            </div>
            <p style="color: red; font-size: 20px; margin-top: 20px">${mes}</p>
            <div class="col-lg-8 products">                
                <c:forEach items="${list}" var="b">
                    <div class="product col-md-4">
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