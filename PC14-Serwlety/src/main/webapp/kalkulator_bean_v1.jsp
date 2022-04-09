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

<jsp:useBean id="kalkulatorBean" class="kalkulator.KalkulatorBean"/>
<jsp:setProperty name="kalkulatorBean" property="arg1" param="arg1"/>
<jsp:setProperty name="kalkulatorBean" property="arg2" param="arg2"/>
<jsp:setProperty name="kalkulatorBean" property="operacja" param="operacja"/>

<c:if test="${not empty param.operacja}">
<div class="wynik">
${kalkulatorBean.arg1} ${kalkulatorBean.operacja} ${kalkulatorBean.arg2} =
	<strong>${kalkulatorBean.wynik}</strong>
 </div>
</c:if>

</body>
</html>