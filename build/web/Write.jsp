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
        </div>
        <form action="write" method="post">
            <div class="form-group">
                <label for="idea" class="text-info">Viết cảm nghĩ:</label><br>
                <textarea required name="idea" id="idea" class="form-control" style="overflow: auto; font-size: 20px" rows="10" placeholder="Viết cảm nghĩ..."></textarea>                            
            </div>
            <input type="text" value="${book.bookID}" name="bookID" hidden>
            <div class="form-group">
                <br>
                <input type="submit" name="submit" class="btn btn-info btn-md" value="Gửi">
            </div>
        </form>

    </div>
</section>
<%@include file="include/Footer_user.jsp" %>