<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav aria-label="...">
    <ul class="pagination">
        <li class="page-item <c:if test="${list.page == 1}">disabled</c:if> " style="background-color: black">
            <a class="page-link" href="/backend/${list.path}/list?page=${list.page-1}&perpage=${list.perpage}&key=${list.key}" tabindex="-1" aria-disabled="true" style="color: gray">Trước</a>
        </li>
        <c:forEach begin="1" step="1" end="${list.totalPage}" var="p" >
            <li class="page-item  <c:if test="${p == list.page}">active</c:if>" style="background-color: black">
                <a class="page-link" href="/backend/${list.path}/list?page=${p}&perpage=${list.perpage}&key=${list.key}" style="color: gray">${p}</a>
            </li>
        </c:forEach>
        <li class="page-item <c:if test="${list.page == list.totalPage}">disabled</c:if>" style="background-color: black">
            <a class="page-link" href="/backend/${list.path}/list?page=${list.page+1}&perpage=${list.perpage}&key=${list.key}" style="color: gray">Sau</a>
        </li>
    </ul>
</nav>