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
    <h1>Chi tiết tài khoản</h1>
    <h2 style="color: red">${message}</h2>
    <br>
    <div class="row mb-4">
        <div class="col-md-6">
            <label for="validationCustomUsername" class="form-label">Tên đăng nhập</label>
            <div class="input-group has-validation">
                <span class="input-group-text" id="inputGroupPrepend">@</span>
                <input type="text" class="form-control" value="<sec:authentication property="principal.username" />" id="validationCustomUsername"
                       aria-describedby="inputGroupPrepend" readonly="true"/>
            </div>
        </div>

    </div>
    <div class="row mb-4">
        <div class="col-md-6">
            <label for="validationCustom03" class="form-label">Họ và tên</label>
            <input type="text" class="form-control" value="<sec:authentication property="principal.fullName" />" readonly="true" path="fullName"
                   id="validationCustom03"/>
        </div>

        <div class="col-md-6">
            <label for="validationCustom03" class="form-label">Quyền</label>
            <input type="text" class="form-control" value="<sec:authentication property="principal.role" />" readonly="true" path="fullName"
                   id="validationCustom03"/>
        </div>
    </div>
    <div class="row mb-4">
        <div class="col-12">
            <label for="validationCustom08" class="form-label">Địa chỉ</label>
            <input type="text" readonly="true" value="<sec:authentication property="principal.address" />" class="form-control" path="address"
                   id="validationCustom08"/>

        </div>
    </div>
    <div class="row mb-4">
        <div class="col-12">
            <a href="/" class="btn btn-info" >Trở về</a>
        </div>
    </div>

</div>
<jsp:include page="/jsp/frontend/footer.jsp"></jsp:include>