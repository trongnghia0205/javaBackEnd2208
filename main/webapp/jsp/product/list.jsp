<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<style>
    .error {
        color: red;
    }
</style>

<jsp:include page="/jsp/frontend/head.jsp"></jsp:include>

<div class="container">
    <br>
    <h1>Danh sách Sản Phẩm</h1>
    <jsp:include page="/jsp/common/paging_head.jsp"></jsp:include>
    <h2 style="color: red">${message}</h2>
    <br>

    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">STT</th>
                <th scope="col">Mô tả ngắn</th>
                <sec:authorize access="hasAnyRole('ADMIN')">
                    <th scope="col">Thời gian tạo</th>
                </sec:authorize>
                <th scope="col">Ảnh sản phẩm</th>
                <th scope="col">Giá sản phẩm</th>
                <th scope="col">Tên sản phẩm</th>
                <sec:authorize access="hasAnyRole('ADMIN')">
                    <th scope="col">Thời gian cập nhật</th>
                </sec:authorize>
                <sec:authorize access="hasAnyRole('CUSTOMER')">
                    <th scope="col"></th>
                </sec:authorize>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${list.list}" var="product" varStatus="s">
            <tr>
                <th scope="row">${s.index + 1} </th>
                <td><a href="/backend/product/detail/${product.id}" style="color: black">${product.shortDescription}</a></td>
                <sec:authorize access="hasAnyRole('ADMIN')">
                    <td>${product.createdTime}</td>
                </sec:authorize>
                <td><img width="100px" src="/2208/${product.mainImage}"></td>
                <td>${product.price}</td>
                <td>${product.name}</td>
                <sec:authorize access="hasAnyRole('ADMIN')">
                    <td>${product.updatedTime}</td>
                </sec:authorize>
                <sec:authorize access="hasAnyRole('ADMIN')">
                <td>
                    <a href="/backend/product/edit/${product.id}" style="color: black!important">Sửa</a>
                    <a href="/backend/prudct/delete/${product.id}" style="color: black!important" data-bs-toggle="modal"
                       data-bs-target="#deleteUserModal" data-bs-id="${product.id}">Xóa</a>
                </td>
                </sec:authorize>
                <sec:authorize access="hasAnyRole('CUSTOMER')">
                    <td>
                        <a href="/backend/${list.path}/detail/${product.id}" style="color: gray!important" class="ordertag">Xem hàng</a>
                    </td>
                </sec:authorize>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <jsp:include page="/jsp/common/paging_foot.jsp" ></jsp:include>
</div>


<div class="modal fade" id="deleteUserModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Bạn có chắc chắn sản phẩm này không</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" id="close" data-bs-dismiss="modal">Close</button>
                <button type="button" id="delete" onclick="deleteButton()" class="btn btn-primary">Xóa</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/jsp/frontend/footer.jsp"></jsp:include>
<script>
    var id = null;
    var exampleModal = document.getElementById('deleteUserModal');
    exampleModal.addEventListener('show.bs.modal', function (event) {
        // Button that triggered the modal
        var a = event.relatedTarget;
        // Extract info from data-bs-* attributes
        id = a.getAttribute('data-bs-id');
    })
    function deleteButton() {
        fetch('/backend/product/delete/' + id, {
            method: 'DELETE'
        }).then(async data => {
            alert(await data.text());
            window.location.href = "/backend/product/list";
        }).catch(function() {
            console.log("Booo");
        })
    }
</script>