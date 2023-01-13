<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<style>
    .error {
        color: red;
    }
</style>
<jsp:include page="/jsp/frontend/head.jsp"></jsp:include>


<div class="container-fluid">
    <div class="row px-xl-5">
        <div class="col-12">
            <nav class="breadcrumb bg-light mb-30">
                <a class="breadcrumb-item text-dark" href="#">Home</a>
                <a class="breadcrumb-item text-dark" href="#">Shop</a>
                <span class="breadcrumb-item active">Shopping Cart</span>
            </nav>
        </div>
    </div>
</div>
<!-- Breadcrumb End -->


<!-- Cart Start -->
<div class="container-fluid">
    <div class="row px-xl-5">
        <div class="col-lg-8 table-responsive mb-5">
            <table class="table table-light table-borderless table-hover text-center mb-0">
                <thead class="thead-dark">
                <tr>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                    <th>Tổng tiền</th>
                    <th>Xóa</th>
                </tr>
                </thead>
                <tbody class="align-middle">
                <c:forEach items="${products}" var="p">
                    <tr name="row-p" data-id="${p.id}">
                        <td class="align-middle"><img src="/2208/${p.productsEntity.mainImage}" alt="" style="width: 50px;"> ${p.productsEntity.name}</td>
                        <td class="align-middle" name="price">$${p.productsEntity.price}</td>
                        <td class="align-middle">
                            <div class="input-group quantity mx-auto" style="width: 100px;">
                                <div class="input-group-btn">
                                    <button class="btn btn-sm btn-primary btn-minus" >
                                        <i class="fa fa-minus"></i>
                                    </button>
                                </div>
                                <input type="text" name="number" class="number form-control form-control-sm bg-secondary border-0 text-center" value="${p.number}">
                                <div class="input-group-btn">
                                    <button class="btn btn-sm btn-primary btn-plus">
                                        <i class="fa fa-plus"></i>
                                    </button>
                                </div>
                            </div>
                        </td>
                        <td class="align-middle" name="total">$${p.productsEntity.price * p.number}</td>
                        <td class="align-middle"><a class="btn btn-sm btn-danger" href="/backend/product/deleteCart/${p.productsEntity.id}/${p.id}"><i class="fa fa-times"></i></a></td>
                    </tr>
                </c:forEach>


                </tbody>
            </table>
        </div>
        <div class="col-lg-4">
            <form class="mb-30" action="">
                <div class="input-group">
                    <input type="text" class="form-control border-0 p-4" placeholder="Coupon Code">
                    <div class="input-group-append">
                        <button class="btn btn-primary">Apply Coupon</button>
                    </div>
                </div>
            </form>
            <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Cart Summary</span></h5>
            <div class="bg-light p-30 mb-5">
                <div class="border-bottom pb-2">
                    <div class="d-flex justify-content-between mb-3">
                        <h6>Subtotal</h6>
                        <h6 class="total">$0</h6>
                    </div>
                    <div class="d-flex justify-content-between">
                        <h6 class="font-weight-medium">Shipping</h6>
                        <h6 class="font-weight-medium">$0</h6>
                    </div>
                </div>
                <div class="pt-2">
                    <div class="d-flex justify-content-between mt-2">
                        <h5>Total</h5>
                        <h5 class="total">$160</h5>
                    </div>
                    <button id="checkout" onclick="checkout()" class="btn btn-block btn-primary font-weight-bold my-3 py-3">Thanh toán</button>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/jsp/frontend/footer.jsp"></jsp:include>
<!-- Cart End -->

<script>
    $('.number').on('change', function () {
        console.log($(this).val());
        var price = $(this).parent().parent().parent().find('[name="price"]').text().replace('$', '');
        var number = $(this).val();
        $(this).parent().parent().parent().find('[name="total"]').text('$' + (price * number));
        total()
    })
    function total() {
        var t = 0;
        var list = $(document).find('[name="total"]');
        list.each(function(a){
            t += $(list[a]).text().replace('$', '') *1;
        })
        $('.total').text('$' + t);
    }
    // nó sẽ chạy sau khi load toàn bộ html css và js
    $(document).ready(function () {
        total();
    })
    function checkout() {
        var listRow = $(document).find('[name="row-p"]');
        var arr = [];
        listRow.each(function(a){
            var id =  $(listRow[a]).data('id');
            var number = $(listRow[a]).find('[name="number"]').val();
            var obj = {'id': id, 'number': number};
            arr.push(obj);
        })
        $.ajax({
            url: '/backend/product/checkout',
            type: 'POST',
            data: JSON.stringify(arr),
            contentType: 'application/json; charset=utf-8',
            success    : function(msg){
                alert(msg)
            }
        })
    }
</script>