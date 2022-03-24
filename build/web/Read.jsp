
<%-- 
    Document   : Read
    Created on : Oct 23, 2021, 12:35:54 AM
    Author     : ThanhNhan
--%>

<%@page import="model.entity.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="./include/Header_user.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>

<section class="read">
    <div class="container">
        <div class="r-head">
            <div class="bookName">${book.bookName}</div>
            <i>Số lượt xem: ${book.viewCounter}</i><hr>
            <c:choose>
                <c:when test="${book.quantity > 0}">
                    <a href="borrowbook?id=${book.bookID}"><button class="add-to-wishlist btn btn-success"><i style="padding: 7px" class="fa fa-shopping-cart"></i><span class="tooltipp">Mượn sách</span></button></a>                                                
                            </c:when>
                            <c:when test="${book.quantity == 0}">
                    <a>Hết sách</a>
                </c:when>
            </c:choose>
            <a href="write?id=${book.bookID}"><button class="quick-view  btn btn-success"><i style="padding: 7px" class="fa fa-pencil"></i><span class="tooltipp">Viết cảm nghĩ</span></button></a>
        </div>
        <div>
            <h3 style="color: #6c0000"><b>CẢM NGHĨ CỦA BẠN ĐỌC</b></h3> 
            <br>
            <c:forEach items="${idea}" var="i">
                <a onclick="ap(${i.ideaID})"><h4 style="color: blue; display: inline-block"><i style="padding: 7px" class="fa fa-pencil"></i>${i.fullName}</h4></a>
                <br>
                <p id="p${i.ideaID}" hidden="">${i.contentView}</p><br>
            </c:forEach> 
        </div>
        <hr>
        <h3 style="color: #6c0000; display: flex; justify-content: space-between"><b>NỘI DUNG SÁCH</b>
            <c:if test="${book.pdfLink != null}">
                <a href="${book.pdfLink}" style="color: blue" target="_blank"> <img style="width: 32px; height: 22px; vertical-align: top; margin-right: -5px" src="./assets/imgs/right-254097_1280.png">  <img style="width: auto; height: 22px; vertical-align: top" src="./assets/imgs/PDF_file_icon.svg.png"> Mở file pdf</a>
            </c:if>   
        </h3> 

        <p style="font-size: 20px; font-family: time-new-roman">${book.contentView}</p>        
    </div>
</section>

<script>
    function ap(id) {
        var ap = document.getElementById("p" + id);
        if (ap.style.display == "block".trim()) {
            ap.style = "display: none";
        } else {
            ap.style = "display: block;font-style: italic";
        }
    }
</script>
<%@include file="include/Footer_user.jsp" %>