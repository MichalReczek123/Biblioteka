<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista produktów 7</title>
<link rel="stylesheet" type="text/css" href="styl.css">
</head>
<body>

<h1>Lista produktów - wersja 7 JSP</h1>

<jsp:useBean id="bean" class="sklep.beans.ProductBean" />

<h2>Filtr cen</h2>
<form id="wyszukiwarka" method="get">
<table class="formularz">
<tr><td><label for="min_price">Cena minimalna:</label></td>
	<td><input type="number" name="min_price" value="${param.min_price}"></td></tr>
<tr><td><label for="max_price">Cena maksymalna:</label></td>
	<td><input type="number" name="max_price" value="${param.max_price}"></td></tr>
<tr><td><button>Szukaj</button></td></tr>
</table>
</form>

<%-- To jest wywołanie setterów setMinPrice i setMaxPrice z przekazaniem wartości parametrów min_price max_price --%>
<jsp:setProperty name="bean" property="minPrice" param="min_price"/>
<jsp:setProperty name="bean" property="maxPrice" param="max_price"/>

<h2>Produkty</h2>
<c:forEach var="product" items="${bean.filteredProducts}">
        <div class="product">
        <h3>${product.productName}</h3>
        <div class="price">${product.price}</div>
        <p>${product.description}</p>
        </div>
</c:forEach>

</body>
</html>
