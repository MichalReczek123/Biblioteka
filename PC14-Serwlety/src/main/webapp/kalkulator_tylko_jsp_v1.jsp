<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dodawanie w JSP</title>
<link rel="stylesheet" type="text/css" href="styl.css">
</head>
<body>
<h1>Dodawanie w JSP</h1>

<form class="kalkulator" method="post">
<input type="number" name="arg1">

<select name="operacja">
<option value="+">+</option>
<option value="-">-</option>
<option value="*">*</option>
<option value="/">/</option>
</select>

<input type="number" name="arg2">
<button style="color:red;font-weight:bold">Oblicz</button>
</form>

<c:if test="${not empty param.operacja}">
<div class="wynik">
${param.arg1} ${param.operacja} ${param.arg2} = 
	<strong><c:choose>
	<c:when test="${param.operacja == '+'}">${param.arg1 + param.arg2}</c:when>
	<c:when test="${param.operacja == '-'}">${param.arg1 - param.arg2}</c:when>
	<c:when test="${param.operacja == '*'}">${param.arg1 * param.arg2}</c:when>
	<c:when test="${param.operacja == '/'}">${param.arg1 / param.arg2}</c:when>
	</c:choose></strong>
</div>
</c:if>

</body>
</html>