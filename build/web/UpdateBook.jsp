<%-- 
    Document   : UpdateBooks
    Created on : Oct 31, 2021, 1:53:59 PM
    Author     : ThanhNhan
--%>

<%@include file="./include/Header_admin.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 

<div class="container-fluid" style="margin-top: 20px"> 
    <div class="container pt-5">
        <div class="row justify-content-center align-items-center">
            <div class="col-md-6">
                <div class="col-md-12">
                    <form action="updatebook" method="POST" enctype="multipart/form-data">
                        <h3 class="text-center text-info">Thay đổi thông tin sách</h3>   
                        <input type="number" name="bookID" value="${b.bookID}" hidden readonly>
                        <div class="form-group">
                            <label for="bookName" class="text-info">Tên sách:</label><br>
                            <input type="text" name="bookName" autocomplete="off" id="bookName" value="${b.bookName}" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="authorID1" class="text-info">Tác giả 1:<i style="color: red">(*)</i></label><br>
                            <select class="form-control" name="authorID1" id="authorID1">
                                <option value="-1">--------------------</option>
                                <c:forEach items="${au}" var="a">
                                    <option value="${a.authorID}" ${a.authorID == b.author1 ? "selected" : ""}>${a.authorName}</option>
                                </c:forEach>                                
                            </select>
                        </div>         
                        <a style="color: #17a2b8" onclick="add(this, 1)">Chưa có tác giả trong danh sách?</a>
                        <div class="form-group" id="n1" hidden>
                            <label for="authorName1" class="text-info">Tên tác giả mới:</label><br>
                            <input type="text" name="authorName1" id="authorName1" autocomplete="off" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="authorID2" class="text-info">Tác giả 2:</label><br>
                            <select class="form-control" name="authorID2" id="authorID2">
                                <option value="-1">--------------------</option>
                                <c:forEach items="${au}" var="a">
                                    <option value="${a.authorID}" ${a.authorID == b.author2 ? "selected" : ""}>${a.authorName}</option>
                                </c:forEach>                                
                            </select>
                        </div>         
                        <a style="color: #17a2b8" onclick="add(this, 2)">Chưa có tác giả trong danh sách?</a>
                        <div class="form-group" id="n2" hidden>
                            <label for="authorName2" class="text-info">Tên tác giả mới:</label><br>
                            <input type="text" name="authorName2" autocomplete="off" id="authorName2" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="authorID3" class="text-info">Tác giả 3:</label><br>
                            <select class="form-control" name="authorID3" id="authorID3">
                                <option value="-1">--------------------</option>
                                <c:forEach items="${au}" var="a">
                                    <option value="${a.authorID}" ${a.authorID == b.author3 ? "selected" : ""}>${a.authorName}</option>
                                </c:forEach>                                
                            </select>
                        </div>         
                        <a style="color: #17a2b8" onclick="add(this, 3)">Chưa có tác giả trong danh sách?</a>
                        <div class="form-group" id="n3" hidden>
                            <label for="authorName3" class="text-info">Tên tác giả mới:</label><br>
                            <input type="text" name="authorName3" autocomplete="off" id="authorName3" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="authorID4" class="text-info">Tác giả 4:</label><br>
                            <select class="form-control" name="authorID4" id="authorID4">
                                <option value="-1">--------------------</option>
                                <c:forEach items="${au}" var="a">
                                    <option value="${a.authorID}" ${a.authorID == b.author4 ? "selected" : ""}>${a.authorName}</option>
                                </c:forEach>                                
                            </select>
                        </div>         
                        <a style="color: #17a2b8" onclick="add(this, 4)">Chưa có tác giả trong danh sách?</a>
                        <div class="form-group" id="n4" hidden>
                            <label for="authorName4" class="text-info">Tên tác giả mới:</label><br>
                            <input type="text" name="authorName4" autocomplete="off" id="authorName4" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="authorID5" class="text-info">Tác giả 5:</label><br>
                            <select class="form-control" name="authorID5" id="authorID5">
                                <option value="-1">--------------------</option>
                                <c:forEach items="${au}" var="a">
                                    <option value="${a.authorID}" ${a.authorID == b.author5 ? "selected" : ""}>${a.authorName}</option>
                                </c:forEach>                                
                            </select>
                        </div>         
                        <a style="color: #17a2b8" onclick="add(this, 5)">Chưa có tác giả trong danh sách?</a>
                        <div class="form-group" id="n5" hidden>
                            <label for="authorName5" class="text-info">Tên tác giả mới:</label><br>
                            <input type="text" name="authorName5" autocomplete="off" id="authorName5" class="form-control">
                        </div>
                        <script>
                            function add(w, id) {
                                document.getElementById("n" + id).style = "display: block!important";
                                w.style = "display: none";
                            }
                        </script>
                        <div class="form-group">
                            <label for="categoryID" class="text-info">Thể loại:</label><br>
                            <select class="form-control" name="categoryID" id="categoryID">
                                <c:forEach items="${ca}" var="c">
                                    <option value="${c.categoryID}" ${c.categoryID eq b.categoryID ? "selected" : ""}>${c.categoryName}</option>
                                </c:forEach>                                
                            </select>
                        </div>
                        <a style="color: #17a2b8" onclick="ca(this)">Chưa có thể loại trong danh sách?</a>
                        <div class="form-group" id="cn" hidden>
                            <label for="categoryName" class="text-info">Tên thể loại mới:</label><br>
                            <input type="text" name="newcategoryName" autocomplete="off" id="categoryName" class="form-control">
                        </div>
                        <div class="form-group" id="ci" hidden>
                            <label for="categoryID" class="text-info">Mã thể loại:</label><br>
                            <input type="text" name="newcategoryID" autocomplete="off" id="categoryID" class="form-control">
                        </div>
                        <script>
                            function ca(w) {
                                document.getElementById("cn").style = "display: block!important";
                                document.getElementById("ci").style = "display: block!important";
                                w.style = "display: none";
                            }
                        </script>
                        <div class="form-group">
                            <label for="subjectID" class="text-info">Môn học:</label><br>
                            <select class="form-control" name="subjectID" id="subjectID">
                                <c:forEach items="${su}" var="s">
                                    <option value="${s.subjectID}" ${s.subjectID == b.subjectID ? "selected" : ""}>${s.subjectName}</option>
                                </c:forEach>                                
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="bookImg" class="text-info">Bìa sách:</label><br> 
                            <img src="${b.bookImg}" alt="${b.bookName}" width="100%">
                            <input type="file" name="bookImg" id="bookImg" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="pdfLink" class="text-info">Bản mềm sách:</label><br>
                            <c:if test="${b.pdfLink != null}">
                                <a href="${b.pdfLink}" style="color: blue" target="_blank"> <img style="width: 32px; height: 22px; vertical-align: top; margin-right: -5px" src="./assets/imgs/right-254097_1280.png">  <img style="width: auto; height: 22px; vertical-align: top" src="./assets/imgs/PDF_file_icon.svg.png"> Mở file pdf</a>
                                </c:if>                             
                            <input type="file" name="pdfLink" id="pdfLink" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="content" class="text-info">Nội dung:</label><br>
                            <textarea required name="content" id="content" class="form-control" style="overflow: auto" rows="10" placeholder="Nhập nội dung sách...">${b.content}</textarea>                            
                        </div>
                        <div class="form-group">
                            <label for="quantity" class="text-info">Số lượng sách:</label><br>
                            <input type="number" name="quantity" id="quantity" class="form-control" value="${b.total}" required>
                        </div>
                        <div class="form-group">
                            <br>
                            <input type="submit" name="submit" class="btn btn-info btn-md" value="Đồng ý">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div> 
<%@include file="include/Footer_admin.jsp" %>