<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<style>
    .error {
        color: red;
    }
</style>
<jsp:include page="/jsp/frontend/head.jsp"></jsp:include>

<div class="container">
    <br>
    <h1>Tạo mới tài khoản</h1>
    <h2 style="color: red">${message}</h2>
    <br>
    <f:form action="/backend/user/changepass" modelAttribute="user" method="post" class=" g-3 needs-validation" >
        <div class="row mb-4">
            <div class="col-md-4">
                <label for="validationCustomUsername" class="form-label">Mật khẩu mới</label>
                <div class="input-group has-validation">
                    <f:input type="password" class="form-control" path="password" id="validationCustomUsername"
                           aria-describedby="inputGroupPrepend"/>
                </div>
                <f:errors cssClass="error" path="password"/>
            </div>

            <div class="col-md-4">
                <label for="validationCustomUsername" class="form-label">nhập lại mật khẩu mới</label>
                <div class="input-group has-validation">
                    <f:input type="password" class="form-control" path="rePassword" id="validationCustomUsername"
                             aria-describedby="inputGroupPrepend" />
                </div>
                <f:errors cssClass="error" path="rePassword"/>
            </div>

        </div>
        <div class="row mb-4">
            <div class="col-md-8">
                <label for="validationCustom03" class="form-label">Mật khẩu cũ</label>
                <f:input type="text" class="form-control" path="oldPassword" id="validationCustom03" />
                <f:errors cssClass="error" path="oldPassword"/>
            </div>
        </div>

        <div class="row mb-4">
            <div class="col-5">
                <a href="/backend/user/list" class="btn btn-info" ><i class="fa-solid fa-arrow-turn-down-left"></i>Trở về</a>
            </div>
            <div class="col-6">
                <button class="btn btn-primary" type="submit">Đổi mật khẩu</button>
            </div>
        </div>

    </f:form>
</div>
<jsp:include page="/jsp/frontend/footer.jsp"></jsp:include>