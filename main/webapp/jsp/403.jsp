<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<style>
    .error {
        color: red;
    }
</style>
<jsp:include page="/jsp/frontend/head.jsp" ></jsp:include>

<div class="container">
    <br>
    <h1>Bạn không có quyền thực hiện chức năng này, vui lòng thử lại</h1>
    <h2 style="color: red">${message}</h2>
    <br>
</div>
<jsp:include page="/jsp/frontend/footer.jsp" ></jsp:include>