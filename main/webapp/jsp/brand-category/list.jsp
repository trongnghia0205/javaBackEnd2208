<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<jsp:include page="/jsp/common/nav.jsp"></jsp:include>--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style>
    .errors{
        color: red;
    }
    .ordertag:hover{
        color: #FFD333;
    }
</style>

<jsp:include page="/jsp/frontend/head.jsp"></jsp:include>

<div class="container">
    <br>
    <h1>Danh sách ${title}</h1>

    <span style="color: red">${message}</span>
    <br>
    <jsp:include page="/jsp/common/paging_head.jsp"></jsp:include>
    <br>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Tên</th>
            <th scope="col">Mô tả</th>
            <th scope="col">Hình ảnh</th>
            <sec:authorize access="hasAnyRole('ADMIN')"><th scope="col">Tùy chỉnh</th></sec:authorize>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list.list}" var="d">
            <tr>
                <th scope="row">${d.id}</th>
                <td>${d.name}</td>
                <td>${d.description}</td>
                <td><img width="100px" src="/2208/${d.mainImage}"></td>
                <sec:authorize access="hasAnyRole('ADMIN')">
                <td>
                    <a href="/backend/${list.path}/edit/${d.id}" style="color: black!important">Sửa</a>
                    <a href="/backend/${list.path}/delete/${d.id}" style="color: black!important" data-bs-toggle="modal"
                       data-bs-target="#deleteModal" data-bs-id="${d.id}">Xóa</a>
                </td>
                </sec:authorize>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <jsp:include page="/jsp/common/paging_foot.jsp" ></jsp:include>
    <jsp:include page="/jsp/frontend/footer.jsp"></jsp:include>
</div>

<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Bạn có chắc chắn xóa ${title} không?</h5>
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
<script>
    var id = null;
    var exampleModal = document.getElementById('deleteModal');
    exampleModal.addEventListener('show.bs.modal', function (event) {
        // Button that triggered the modal
        var a = event.relatedTarget;
        // Extract info from data-bs-* attributes
        id = a.getAttribute('data-bs-id');

    })

    function deleteButton() {
        fetch('/backend/${list.path}/delete/' + id, {
            method: 'DELETE'
        }).then(async data => {
            alert(await data.text());
            window.location.href = "/backend/${list.path}/list";
        }).catch(function () {
            console.log("Booo");
        })
    }
</script>
