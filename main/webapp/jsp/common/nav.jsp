<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/font/fontawesome.min.css" rel="stylesheet">
<script src="/js/bootstrap.bundle.min.js"></script>


<nav class="navbar navbar-dark bg-primary navbar-expand-lg ">
    <div class="container">
        <a class="navbar-brand" href="/home">T3H</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText"
                aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link <c:if test="${path == null}">active</c:if>" aria-current="page" href="/home"><spring:message code="title.home"></spring:message></a>
                </li>
                <sec:authorize access="hasAnyRole('ADMIN')">
                    <li class="nav-item">
                        <a class="nav-link <c:if test="${path == 'user'}">active</c:if> " href="/backend/user/list">Tài khoản</a>
                    </li>
                </sec:authorize>

                <li class="nav-item">
                    <a class="nav-link <c:if test="${path == 'user'}">active</c:if> " href="/backend/product/list">Sản phẩm</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle <c:if test="${path == 'brand' || path == 'category'}">active</c:if>" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Nhãn hiệu
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item <c:if test="${path == 'brand'}">active</c:if>" href="/backend/brand/list">Thương hiệu</a></li>
                        <li><a class="dropdown-item <c:if test="${path == 'category'}">active</c:if>" href="/backend/category/list">Thể loại</a></li>
                    </ul>
                </li>

            </ul>
            <ul class="navbar-nav mb-2 mb-lg-0" style="float: right">
                <li class="nav-item dropdown" >
                    <a class="nav-link dropdown-toggle <c:if test="${path == 'brand' || path == 'category'}">active</c:if>" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <spring:message code="title.language"></spring:message>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item <c:if test="${path == 'brand'}">active</c:if>" href="?lang=vi"><spring:message code="title.language.vi"></spring:message></a></li>
                        <li><a class="dropdown-item <c:if test="${path == 'category'}">active</c:if>" href="?lang=en"><spring:message code="title.language.en"></spring:message></a></li>
                    </ul>
                </li>
            </ul>
            <ul class="navbar-nav mb-2 mb-lg-0" style="float: right">
                <li class="nav-item dropdown" >
                    <a class="nav-link dropdown-toggle <c:if test="${path == 'brand' || path == 'category'}">active</c:if>" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <sec:authentication property="principal.fullName"/>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item " href="/backend/user/changepass">Đổi mật khẩu</a></li>
                        <li><a class="dropdown-item"  href="/backend/user/info">Thông tin tài khoản đăng nhập</a></li>
                        <li><a class="dropdown-item"  href="/logout">Đăng xuất</a></li>
                    </ul>
                </li>
            </ul>
            <span class="navbar-text">
      </span>
        </div>
    </div>
</nav>