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
<input type="number" name="arg1" value="${param.arg1}">

<select name="operacja">
<c:forEach var="op" items="+,-,*,/">
	<c:choose>
	<c:when test="${op == param.operacja}">
		<option value="${op}" selected="selected">${op}</option>
	</c:when>
	<c:otherwise>
		<option value="${op}">${op}</option>
	</c:otherwise>
	</c:choose>
</c:forEach>
</select>

<input type="number" name="arg2" value="${param.arg2}">
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