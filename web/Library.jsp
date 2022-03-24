<%-- 
    Document   : Library
    Created on : Nov 20, 2021, 11:41:37 PM
    Author     : ThanhNhan
--%>

<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>

<%@include file="./include/Header_user.jsp" %>

<div class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <div class="col-md-12">
                <div class="section-title">
                    <h3 class="title"><img src="./assets/imgs/smcn.png" style="width: 100%"></h3>
                </div>
            </div>
            <div class="col-md-12">
                <div class="row">
                    <div class="products-tabs">
                        <!-- tab -->
                        <div id="tab1" class="tab-pane active">
                            <div class="products-slick" data-nav="#slick-nav-1">
                                <!-- product -->
                                <c:forEach items="${newBook}" var="b">
                                    <div class="product">
                                        <div class="product-img">
                                            <img height="100%" src="${b.bookImg}" alt="${b.bookName}">
                                            <div class="product-label">                                                
                                                <span class="new">NEW</span>
                                            </div>
                                        </div>
                                        <div class="product-body">
                                            <!--<p class="product-category">Category</p>-->
                                            <div class="product-name-author">
                                                <h3 class="product-name"><a>${b.bookName}</a></h3>                                                
                                            </div>
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
                            <div id="slick-nav-1" class="products-slick-nav"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-title">
                    <h3 class="title"><img src="./assets/imgs/tsh.png" style="width: 100%"></h3>
                </div>
            </div>
            <div class="col-md-12">
                <div class="row">
                    <div class="products-tabs">
                        <!-- tab -->
                        <div id="tab2" class="tab-pane fade in active">
                            <div class="products-slick" data-nav="#slick-nav-2">
                                <!-- product -->
                                <c:forEach items="${topBook}" var="b">
                                    <div class="product">
                                        <div class="product-img">
                                            <img height="100%" src="${b.bookImg}" alt="${b.bookName}">
                                            <div class="product-label">                                                
                                                <span class="new">TOP</span>
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
                            <div id="slick-nav-2" class="products-slick-nav"></div>
                        </div>
                    </div>
                </div>
            </div>            
        </div>
    </div>
</div>

<div class="section">

    <div class="section">

        <div class="container">

            <div class="row">
                <div class="col-md-12">
                    <div class="section-title">
                        <h3 class="title"><img src="./assets/imgs/kh.png" style="width: 100%"></h3>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="row plan">
                        <c:forEach items="${plan}" var="p" begin="0" end="9">
                            <div>
                                <div style="font-size: 22px; margin:0 10% 20px 10%;"><a style="font-weight: bold" onclick="op(${p.planID})">${p.titleView}</a></div>
                                <div style="margin:0 10% 20px 10%; text-align: right"><i style="font-size: 16px">Thời gian tạo: ${p.time}</i></div>
                                <div hidden id="co${p.planID}">
                                    <div style="margin: 0 10%; font-size: 17px"><p>${p.descriptionView}</p></div>
                                    <div style="text-align: center">
                                        <c:if test="${p.planImg != null}">
                                            <img src="${p.planImg}" style="width: 50%; height: auto; position: static" alt="${p.title}">
                                        </c:if>
                                    </div> 
                                    <hr>
                                </div>
                            </div>                           
                        </c:forEach>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<div class="container">    
    <div class="row">
        <div class="col-md-12">
            <div class="section-title">
                <h3 class="title"><img src="./assets/imgs/hd.png" style="width: 100%"></h3>
            </div>
        </div>  
    </div>
    <div class="container hd">
        <c:forEach items="${active}" var="at" varStatus="s" begin="0" end="9">
            <div class="divslide"  idx="${s.index}">
                <img class="slide" src="${at.activeImg}" alt="">
                <p>${at.name}</p>
            </div>
        </c:forEach>
        <img class="btn-change" id="next" src="./assets/imgs/next.png" alt="">
        <img class="btn-change" id="prev" src="./assets/imgs/prev.png" alt="">
        <div class="change-img">
            <c:forEach items="${active}" varStatus="s">
                <button idx="${s.index}" class="${s.index == 0 ? "active" : ""}">${s.index+1}</button>
            </c:forEach>
        </div>    
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>

<script>
                                    function op(id) {
                                        var co = document.getElementById("co" + id);
                                        if (co.style.display == "block".trim()) {
                                            co.style = "display: none";
                                        } else {
                                            co.style = "display: block";
                                        }
                                    }

                                    $(document).ready(function () {
                                        var stt = 0;
                                        var endImg = $(".divslide:last").attr("idx");

                                        $("button").click(function () {
                                            stt = $(this).attr("idx");

                                            changeImg(stt);
                                        });

                                        $("#next").click(function () {
                                            if (++stt > endImg) {
                                                stt = 0;
                                            }

                                            changeImg(stt);
                                        });

                                        $("#prev").click(function () {
                                            if (--stt < 0) {
                                                stt = endImg;
                                            }

                                            changeImg(stt);
                                        });

                                        var interval;
                                        var timer = function () {
                                            interval = setInterval(function () {
                                                $("#next").click();
                                            }, 5000);
                                        };
                                        timer();
                                    });

                                    function changeImg(stt) {
                                        $(".divslide").hide();
                                        $(".divslide").eq(stt).fadeIn(500);
                                        $("button").removeClass("active");
                                        $("button").eq(stt).addClass("active");

                                        clearInterval(interval);
                                        timer();
                                    }
                                    ;
</script>

<%@include file="./include/Footer_user.jsp" %>