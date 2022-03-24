<%-- 
    Document   : Header_admin
    Created on : Oct 23, 2021, 10:53:36 PM
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
<c:if test="${acc.fullName == null or acc.userTypeID != 1}">
    <jsp:forward page="login"></jsp:forward>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Quản trị thư viện trường THCS Đặng Dung</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">                
        <meta http-equiv="X-UA-Compatible" content="IE=edge">            
        <link href="./assets/css/magnific-popup.css" rel="stylesheet" type="text/css">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="./assets/css/icons.min.css" rel="stylesheet" type="text/css">
        <link href="./assets/css/app.min.css" rel="stylesheet" type="text/css">
        <link href="./assets/css/admin.css" rel="stylesheet" type="text/css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link rel="icon" type="image/png" href="./assets/imgs/ico.png"/>        
        <script src="./assets/js/jquery.table2excel.min.js"></script>
        <script src="./assets/js/jquery.min.js"></script>        
    </head>

    <body>

<script>
    function deleted(id, o){
        var conf = confirm("Bạn có chắc chắn muốn xóa không?");
        if(conf){
            $.ajax({
                url: '/dangdung.lib/delete',
                type: 'GET',
                data: {
                    id: id,
                    o: o
                },
                success: function () {
                    document.getElementById(o+id).remove();
                    alert("Xóa thành công!")
                }
            })
        }
    }
</script>
        <div id="wrapper">
            <div class="navbar-custom">
                <ul class="list-unstyled topnav-menu float-right mb-0">
                    <li class="d-none d-sm-block">                           
                        <form class="app-search" style="height: auto;max-width: 400px; ${appa}" hidden action="${action}" method="POST">
                            <input name="value" autocomplete="off" value="${value}" style="color: black; border-radius: 4" type="text" class="form-control" placeholder="Tìm kiếm...">
                            <div class="input-group-append">
                                <button class="btn" type="submit">
                                    <i class="fe-search"></i>
                                </button>
                            </div>
                        </form>
                    </li>

                    <li class="dropdown notification-list" style="margin-right: 40px">

                        <a class="nav-link dropdown-toggle nav-user mr-0 waves-effect waves-light" data-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">                            
                            <span class="pro-user-name ml-1">
                                ${acc.fullName}
                            </span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right profile-dropdown inf">                            
                            <a href="information" class="dropdown-item notify-item">
                                <i class="fe-user"></i>
                                <span>Thông tin tài khoản</span>
                            </a>
                            <a href="changepass" class="dropdown-item notify-item">
                                <i class="fe-settings"></i>
                                <span>Đổi mật khẩu</span>
                            </a>
                            <div class="dropdown-divider"></div>

                            <a href="logout" class="dropdown-item notify-item">
                                <i class="fe-log-out"></i>
                                <span>Đăng xuất</span>
                            </a>
                        </div>
                    </li>
                </ul>
                <div class="logo-box">
                    <a href="manage" class="logo text-center">
                        <span class="logo-lg">
                            <img src="assets/imgs/logo.jpg" alt="" height="70px" style="background-color: white; width: 271px;vertical-align: baseline;">                            
                        </span>
                    </a>
                </div>
            </div>
            <div class="left-side-menu">
                <div class="slimscroll-menu">
                    <div id="sidebar-menu">
                        <ul class="metismenu" id="side-menu">
                            <li class="menu-title">Hoạt động</li>                            
                            <li>
                                <a>
                                    <i class="la la-cube"></i>
                                    <span> Quản lý sách </span>
                                    <span class="menu-arrow"></span>
                                </a>
                                <ul class="nav-second-level" aria-expanded="false">
                                    <li>
                                        <a href="listbook">
                                            <i class="la la-briefcase"></i>
                                            <span> Danh sách các đầu sách </span>                                    
                                        </a>
                                    </li>
                                    <li>
                                        <a href="listbooks">
                                            <i class="la la-briefcase"></i>
                                            <span> Tất cả sách trong kho </span>                                    
                                        </a>
                                    </li>
                                    <li>
                                        <a href="bookorder">
                                            <i class="la la-briefcase"></i>
                                            <span> Đang chờ xử lý </span>                                    
                                        </a>
                                    </li>
                                    <li>
                                        <a href="bookreceipt">
                                            <i class="la la-briefcase"></i>
                                            <span> Lịch sử mượn/trả </span>                                    
                                        </a>
                                    </li>
                                    <li>
                                        <a href="idea">
                                            <i class="la la-briefcase"></i>
                                            <span> Cảm nghĩ </span>                                    
                                        </a>
                                    </li>     
                                    <li>
                                        <a href="listauthor">
                                            <i class="la la-briefcase"></i>
                                            <span> Danh sách tác giả </span>                                    
                                        </a>
                                    </li>
                                    <li>
                                        <a href="listcategory">
                                            <i class="la la-briefcase"></i>
                                            <span> Danh sách thể loại </span>                                    
                                        </a>
                                    </li>
                                    <li>
                                        <a href="listsubject">
                                            <i class="la la-briefcase"></i>
                                            <span> Danh sách môn học </span>                                    
                                        </a>
                                    </li>
                                    <li>
                                        <a href="statisticalbookcode">
                                            <i class="la la-briefcase"></i>
                                            <span> Kết xuất mã sách </span>                                    
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a>
                                    <i class="la la-cube"></i>
                                    <span> Quản lý thiết bị </span>
                                    <span class="menu-arrow"></span>
                                </a>
                                <ul class="nav-second-level" aria-expanded="false">
                                    <li>
                                        <a href="listdevice">
                                            <i class="la la-briefcase"></i>
                                            <span> Danh sách thiết bị </span>                                    
                                        </a>
                                    </li>
                                    <li>
                                        <a href="listdevices">
                                            <i class="la la-briefcase"></i>
                                            <span> Tất cả thiết bị trong kho </span>                                    
                                        </a>
                                    </li>
                                    <li>
                                        <a href="deviceorder">
                                            <i class="la la-briefcase"></i>
                                            <span> Đang chờ xử lý </span>                                    
                                        </a>
                                    </li>
                                    <li>
                                        <a href="devicereceipt">
                                            <i class="la la-briefcase"></i>
                                            <span> Lịch sử mượn/trả </span>                                    
                                        </a>
                                    </li>
                                    <li>
                                        <a href="statisticaldevicecode">
                                            <i class="la la-briefcase"></i>
                                            <span> Kết xuất mã thiết bị </span>                                    
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a>
                                    <i class="la la-file-text-o"></i>                                    
                                    <span> Chỉnh sửa </span>
                                    <span class="menu-arrow"></span>
                                </a>
                                <ul class="nav-second-level">
                                    <li>
                                        <a href="updatebook">Sách</a>
                                    </li>
                                    <li>
                                        <a href="updateauthor">Tác giả</a>
                                    </li>
                                    <li>
                                        <a href="updatecategory">Thể loại</a>
                                    </li>
                                    <li>
                                        <a href="updatedevice">Thiết bị</a>
                                    </li>
                                </ul>
                            </li>

                            <li>
                                <a>
                                    <i class="la la-cube"></i>
                                    <span> Tạo mới </span>
                                    <span class="menu-arrow"></span>
                                </a>
                                <ul class="nav-second-level" aria-expanded="false">
                                    <li>
                                        <a href="newbook">Sách</a>
                                    </li>
                                    <li>
                                        <a href="newdevice">Thiết bị</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a>
                                    <i class="la la-cube"></i>
                                    <span> Thống kê/Báo cáo </span>
                                    <span class="menu-arrow"></span>
                                </a>
                                <ul class="nav-second-level" aria-expanded="false">
                                    <li>
                                        <a href="statisticalbook">
                                            <i class="la la-briefcase"></i>
                                            <span> Thống kê sách </span>                                    
                                        </a>
                                    </li>
                                    <li>
                                        <a href="statisticalcategory">
                                            <i class="la la-briefcase"></i>
                                            <span> Thống kê thể loại </span>                                    
                                        </a>
                                    </li>   
                                    <li>
                                        <a href="statisticalauthor">
                                            <i class="la la-briefcase"></i>
                                            <span> Thống kê tác giả </span>                                    
                                        </a>
                                    </li> 
                                    <li>
                                        <a href="statisticalsubject">
                                            <i class="la la-briefcase"></i>
                                            <span> Thống kê môn học </span>                                    
                                        </a>
                                    </li> 
                                    <li>
                                        <a href="statisticaldevice">
                                            <i class="la la-briefcase"></i>
                                            <span> Thống kê thiết bị </span>                                    
                                        </a>
                                    </li> 
                                    <li>
                                        <a href="statisticaldeviceteacher">
                                            <i class="la la-briefcase"></i>
                                            <span> Giáo viên mượn thiết bị </span>                                    
                                        </a>
                                    </li> 
                                    <li>
                                        <a href="statisticalbookteacher">
                                            <i class="la la-briefcase"></i>
                                            <span> Giáo viên mượn sách </span>                                    
                                        </a>
                                    </li> 
                                    <li>
                                        <a href="statisticalclass">
                                            <i class="la la-briefcase"></i>
                                            <span> Học sinh mượn sách </span>                                    
                                        </a>
                                    </li> 
                                </ul>
                            </li>
                            <li>
                                <a>
                                    <i class="la la-cube"></i>
                                    <span> Các hoạt động thư viện </span>
                                    <span class="menu-arrow"></span>
                                </a>
                                <ul class="nav-second-level" aria-expanded="false">
                                    <li>
                                        <a href="listactive">
                                            <i class="la la-briefcase"></i>
                                            <span> Quản lý các hoạt động </span>                                    
                                        </a>
                                    </li>
                                    <li>
                                        <a href="newactive">
                                            <i class="la la-briefcase"></i>
                                            <span> Thêm hoạt động </span>                                    
                                        </a>
                                    </li>   
                                    <li>
                                        <a href="plan">
                                            <i class="la la-briefcase"></i>
                                            <span> Kế hoạch </span>                                    
                                        </a>
                                    </li> 
                                    <li>
                                        <a href="newplan">
                                            <i class="la la-briefcase"></i>
                                            <span> Thêm kế hoạch </span>                                    
                                        </a>
                                    </li> 
                                </ul>
                            </li>
                            <li>
                                <a href="messenger" class="active">
                                    <i class="la la-envelope"></i>
                                    <span class="badge badge-danger float-right">New</span>
                                    <span> Tin nhắn </span>                                    
                                </a>
                            </li>

                            <li>
                                <a class="active">
                                    <i class="la la-diamond"></i>                                    
                                    <span> Tài khoản </span>
                                    <span class="menu-arrow"></span>
                                </a>
                                <ul class="nav-second-level" aria-expanded="false">
                                    <li>
                                        <a href="student">Học sinh</a>
                                    </li>
                                    <li>
                                        <a href="teacher">Giáo viên</a>
                                    </li>                                    
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
            <div class="content-page">
                <div class="content">
                    <div class="container-fluid" style="margin-top: 20px">