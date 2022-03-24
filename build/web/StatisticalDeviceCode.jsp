<%-- 
    Document   : ListBooks
    Created on : Nov 24, 2021, 9:23:25 PM
    Author     : ThanhNhan
--%>

<%@include file="./include/Header_admin.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 

<div class="container-fluid" style="margin-top: 20px">
    <div style="display: flex; margin-bottom: 5px">
        <form action="statisticaldevicecode" method="POST" onsubmit="return false" style="margin-right: 5px">
            <b>Nhập số lượng thiết bị bạn muốn kết xuất: </b> <input type="number" id="quantitydevicecode"> <button onclick="statisticalDeviceCode(${size})" class="btn btn-success">Đồng ý</button>  
        </form>
        <a href="./mathietbi.xls" download="mathietbi.xls" id="kx" style="display: none"><button class="btn btn-success">Kết xuất excel</button></a>
        <i style="margin-left: 5px">(Vui lòng chọn <b>Đồng ý</b> trước khi kết xuất)</i>
    </div>

    <table class="table" style="font-size: 18px" cellspacing="3" cellpadding="3">
        <thead>
            <tr>
                <th scope="col">STT</th>
                <th scope="col">Tên thiết bị</th>
                <th scope="col">Mã thiết bị</th>
            </tr>
        </thead>
        <tbody id="lds">
        </tbody>
    </table>
</div>

<script>
    function statisticalDeviceCode(size) {
        const quantity = document.getElementById("quantitydevicecode").value;
        if (quantity <= 0) {
            alert("Vui lòng nhập số lượng kết xuất lớn hơn 0!")
        } else if (quantity > size) {
            alert("Vui lòng nhập số lượng tối đa bằng tổng số thiết bị trong kho!\n(Số thiết bị trong kho: " + size + ")")
        } else {
            $.ajax({
                url: '/dangdung.lib/statisticaldevicecode',
                type: 'POST',
                data: {
                    quantity: quantity,
                },
                success: function (data) {
                    document.getElementById("kx").style = "display: inline-block!important"
                    var ls = document.getElementById("lds");
                    ls.innerHTML = data;
                }
            });
        }
    }
</script>

<%@include file="include/Footer_admin.jsp" %>