<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Tạo mới Book</h2>

<span style="color: red">${message}</span>
<form action="/book/save" method="post">
    <label for="fname">Tên sách:</label><br>
    <input type="text" id="fname" name="name" value=""><br>
    <label for="lname">Tác giả:</label><br>
    <input type="text" id="lname" name="author" value=""><br><br>
    <input type="submit" value="Tạo mới">
</form>