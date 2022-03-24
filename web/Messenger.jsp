<%-- 
    Document   : Messenger
    Created on : Oct 23, 2021, 11:45:54 PM
    Author     : ThanhNhan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="./include/Header_admin.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 
<jsp:useBean id="m" class="model.entity.Message" scope="session"></jsp:useBean>
    <div class="container-fluid" style="margin-top: 20px">
        <div class="row">
            <div class="col-md-5 us-mess">
                <ul id="sender">
                <c:forEach items="${sender}" var="s">
                    <a href='messenger?userID=${s.userID}'>
                        <li class="${s.userID == tag ? "choose" : ""}">
                            <div class="name"><i style="color: gray">${s.job}:</i> ${s.fullName} ${s.job eq "Học sinh" ? " - lớp: " : ""}${s.job eq "Học sinh" ? s.clas : ""}</div>
                            <div>${s.message}</div>
                            <hr>
                            <i style="color: gray">${s.time}</i>
                        </li>
                    </a>
                    <hr>
                </c:forEach>
            </ul>
        </div>
        <form class="col-md-7 co-mess" id='send' onsubmit="return false" method="post">
            <div class="co-name">${client.fullName}</div>
            <hr>
            <div id="mess">
                <c:forEach items="${mess}" var="m">                         
                    <div class='${m.typeID ? "client-mess" : "admin-mess"}'><div>${m.message}</div></div>
                    <div style="text-align: ${m.typeID ? "left" : "right"}; font-size: 14px; padding: 0 30px"><i>${m.time}</i></div>                            
                        </c:forEach>
            </div>
            <hr>
            <div class="messBottom">                
                <input type="text" value="${tag}" name="userID" id="userID" hidden readonly>
                <input class="txtMess form-control" id="txtMesss" type="text" placeholder="Aa..." autocomplete="off" name="message" required>
                <button onclick="send()" id="sub" class="sent btn btn-primary" style="background-color: black">Gửi</button>
            </div>

        </form>
    </div>
</div>

<script>
    function send() {
        const mess = document.getElementById('txtMesss').value;
        const userID = document.getElementById('userID').value;
        var typeID = "false";
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
                    document.getElementById('txtMesss').value = '';
                }
            });
        }
    }

    $('#send input[type="text"]').keypress(function () {
        const mess = document.getElementById('txtMesss').value;
        if (mess != null && mess.trim() != "") {
            var keycode = (event.keyCode ? event.keyCode : event.which);
            if (keycode == '13') {
                send();
                event.preventDefault()
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
    setInterval(function () {
        const userID = document.getElementById('userID').value;
        $.ajax({
            url: '/dangdung.lib/loadsender',
            type: 'POST',
            data: {
                userID: userID,
            },
            success: function (data) {
                var sender = document.getElementById("sender");
                sender.innerHTML = data;
            }
        });
    }, 500);
</script>

<%@include file="include/Footer_admin.jsp" %>
