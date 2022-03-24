<%-- 
    Document   : header_user
    Created on : Oct 20, 2021, 8:53:47 PM
    Author     : ThanhNhan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.entity.Account"%>
<%
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("utf-8");
%>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>
<jsp:useBean id="acc" class="model.entity.Account" scope="session"></jsp:useBean>
<c:if test="${acc.fullName == null or acc.userTypeID == 1}">
    <jsp:forward page="login"></jsp:forward>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">          
        <title>Thư viện trường THCS Đặng Dung</title>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">            
        <link type="text/css" rel="stylesheet" href="./assets/css/bootstrap.css"/>
        <link type="text/css" rel="stylesheet" href="./assets/css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="./assets/css/slick-theme.css"/>
        <link type="text/css" rel="stylesheet" href="./assets/css/nouislider.min.css"/>
        <link rel="stylesheet" href="./assets/css/font-awesome.min.css">
        <link type="text/css" rel="stylesheet" href="./assets/css/style.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link rel="icon" type="image/png" href="./assets/imgs/ico.png"/>        
        <script>
            function sent() {
                const mess = document.getElementById('txtMess').value;
                const userID = document.getElementById('userID').value;
                var typeID = "true";
                if (mess != null && mess.trim() != "") {
                    $.ajax({
                        url: '/dangdung.lib/messenger',
                        type: 'POST',
                        data: {
                            mess: mess,
                            userID: userID,
                            typeID: typeID
                        },
                        success: function () {
                            document.getElementById('txtMess').value = '';
                            var messs = document.getElementById("mess");
                            messs.innerHTML = "<div class='client-mess'><div>" + mess + "</div></div>" + messs.innerHTML;
                        }
                    });
                }
            }
            $('#sent input[type="text"]').keypress(function () {
                const mess = document.getElementById('txtMess').value;
                if (mess != null && mess.trim() != "") {
                    var keycode = (event.keyCode ? event.keyCode : event.which);
                    if (keycode == '13') {
                        sent();
                    }
                }
            })
            setInterval(function () {
                const userID = document.getElementById('userID').value;
                $.ajax({
                    url: '/dangdung.lib/loadmess',
                    type: 'POST',
                    data: {
                        userID: userID,
                    },
                    success: function (data) {
                        var mess = document.getElementById("mess");
                        mess.innerHTML = data;
                    }
                });
            }, 500);
        </script>
    </head>
    <body style="overflow-x: hidden">
        <header style="background-repeat: no-repeat;background-size: cover;background-image: url(./assets/imgs/head.jpg);">
            <div id="top-header">
                <div class="container">
                    <ul class="header-links pull-left">                                                    
                        <li><a href="library"><i class="fa fa-map-marker"></i> Thư Viện Trường THCS Đặng Dung</a></li>
                    </ul>
                    <ul class="header-links pull-right">                            
                        <li>
                            <div class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                    <i class="fa fa-user-o"></i> ${acc.fullName}
                                </a>
                                <div class="cart-dropdown" style="margin-top: 10px" id="tt">
                                    <a href="information">Thông tin tài khoản</a>
                                    <c:if test="${acc.userTypeID == 0}">
                                        <a href="createadmin">Tài khoản admin</a>
                                    </c:if>             
                                    <a href="listbookreceipt">Lịch sử mượn sách <b style="float: right; border-radius: 50%; background-color: #D10024; width: 25px;text-align: center">${acc.quantityBookReceipt}</b> </a>  
                                    <c:if test="${acc.userTypeID == 0 or acc.userTypeID == 2}">
                                        <a href="listdevicereceipt">Lịch sử mượn thiết bị <b style="float: right; border-radius: 50%; background-color: #D10024; width: 25px;text-align: center">${acc.quantityDeviceReceipt}</b> </a>  
                                    </c:if>
                                    <a href="changepass">Đổi mật khẩu</a>                                    
                                    <hr style="margin: 0">
                                    <a href="logout">Đăng xuất</a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>

            <div id="header">
                <div class="container">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="header-logo">
                                <a href="library" class="logo">
                                    <img width="100%" src="./assets/imgs/logo.png" alt="">
                                </a>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="header-search">
                                <form action="search" method="POST">
                                    <select class="input-select" name="option">
                                        <option value="all" ${all}>Tất cả danh mục</option>
                                        <option value="sB" ${sB}>Tên sách</option>
                                        <option value="sC" ${sC}>Tên thể loại</option>
                                        <option value="sA" ${sA}>Tên tác giả</option>
                                        <option value="sS" ${sS}>Tên môn học</option>
                                        <c:if test="${acc.userTypeID == 0 or acc.userTypeID == 2}">
                                            <option value="sD" ${sD}>Tên thiết bị</option>
                                        </c:if>
                                    </select>
                                    <input name="value" value="${value}" class="input" autocomplete="off" placeholder="Tìm kiếm...">
                                    <button class="search-btn">Tìm kiếm</button>
                                </form>
                            </div>
                        </div>
                        <div class="col-md-3 clearfix">
                            <div class="header-ctn">                                                               
                                <div class="dropdown">
                                    <a href="listbookorder">
                                        <i class="fa fa-shopping-cart"></i>
                                        <span>Sách đang đợi</span>
                                        <div class="qty">${acc.quantityBookOrder}</div>
                                    </a>                                    
                                </div>

                                <c:if test="${acc.userTypeID == 0 or acc.userTypeID == 2}">

                                    <div class="dropdown">
                                        <a href="listdeviceorder">
                                            <i class="fa fa-shopping-cart"></i>
                                            <span>Thiết bị đang đợi</span>
                                            <div class="qty">${acc.quantityDeviceOrder}</div>
                                        </a>                                    
                                    </div>                                    
                                </c:if>



                                <div class="menu-toggle">
                                    <a>
                                        <i class="fa fa-bars"></i>
                                        <span>Menu</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <nav id="navigation">
            <div class="container" style="background-image: linear-gradient(360deg, #569779, honeydew);">
                <div id="responsive-nav">
                    <ul class="main-nav nav navbar-nav" style="font-size: 200%">                        
                        <li class="${li}"><a href="library"><b>Trang chủ</b></a></li>
                        <li class="${ca}"><a href="category"><b>Thể loại</b></a></li>
                        <li class="${au}"><a href="author"><b>Tác giả</b></a></li>
                        <li class="${su}"><a href="subject"><b>Môn học</b></a></li>
                            <c:if test="${acc.userTypeID == 0 or acc.userTypeID == 2}">
                            <li class="${de}"><a href="device"><b>Thiết bị</b></a></li>
                            </c:if>
                    </ul>
                </div>
            </div>
        </nav>
        <div id="contact">                    
            <input type="checkbox" hidden id="contact-btn" ${o}>                    
            <div id="ht"><label for="contact-btn"><i class="fa fa-snapchat" style="font-size: 50px; width: 50px; margin: auto 0"></i></label></div>
            <div id="chat">
                <label for="contact-btn">
                    <div id="chat-header">
                        Hỗ trợ
                        <div id="out"><i class="fa fa-angle-double-right"></i></div>
                    </div>
                </label>
                <div id="mess">                    
                    <c:forEach items="${messenger}" var="m">                          
                        <div class='${m.typeID ? "client-mess" : "admin-mess"}'><div>${m.message}</div></div>                        
                            </c:forEach>
                </div>
                <form id='sent' method="post" onsubmit="return false">
                    <input type="text" id='userID' value="${acc.userID}" name="userID" hidden readonly>                    
                    <input id="txtMess" type="text" name='message' autocomplete="off" class="form-control" value="" required placeholder="Aa...">
                    <button onclick="sent()" class="btn btn-primary" style="background-color: black">Gửi</button>
                </form>
            </div>
        </div>

