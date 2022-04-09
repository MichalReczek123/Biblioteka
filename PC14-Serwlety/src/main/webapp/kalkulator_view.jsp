<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kalkulator w JSP</title>
<link rel="stylesheet" type="text/css" href="styl.css">
</head>
<body>
<h1>Kalkulator w JSP</h1>

<form class="kalkulator" method="post">
<input type="number" name="liczba1" value="${param.liczba1}">

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

<input type="number" name="liczba2" value="${param.liczba2}">
<button style="color:red;font-weight:bold">Oblicz</button>
</form>

<%-- Obiekty zapisane w ServletContext, HttpSession i HttpRequest są dostępne "tak po prostu" poprzez ${expression language} --%>
<c:if test="${not empty wynik}">
<div class="wynik">
${param.liczba1} ${param.operacja} ${param.liczba2} = <strong>${wynik}</strong>
</div>
</c:if>


<h3>Historia globalna</h3>
<ul>
<%-- Nazwy historia_globalna i historia_klienta muszą być dokładnie takie, jak stałe zdef. w ListenerHistorii --%>
<c:forEach var="dzialanie" items="${historia_globalna}">
	<li>${dzialanie}</li>
</c:forEach>
</ul>

<h3>Historia klienta</h3>
<ul>
<c:forEach var="dzialanie" items="${historia_klienta}">
	<li>${dzialanie}</li>
</c:forEach>
</ul>

</body>
</html>