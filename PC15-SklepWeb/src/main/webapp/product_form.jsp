<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edycja towaru</title>
<link rel="stylesheet" type="text/css" href="styl.css">
</head>
<body>

<c:choose>
	<c:when test="${not empty(product.productId)}">
		<h1>Edycja produktu nr ${product.productId}</h1>
	</c:when>
	<c:otherwise>
		<h1>Edycja nowego produktu</h1>
	</c:otherwise>
</c:choose>

<c:if test="${not empty(errors)}">
<div class="error">
${errors}
</div>
</c:if>

<form id="product-form" method="post">
<table class="form">
	<tr>
		<td><label for="productId">Numer:</label></td>
		<td><input name="productId" placeholder="brak" type="number" readonly="readonly" value="${product.productId}"/></td>
	</tr>
	<tr>
		<td><label for="name">Nazwa towaru:</label></td>
		<td><input name="name" placeholder="nazwa..." type="text" value="${product.productName}"/>
		</td>
	</tr>
	<tr>
		<td><label for="price">Cena:</label></td>
		<td><input name="price" placeholder="12.90" title="tu wpisz cenę" type="number" step="0.01" value="${product.price}"/>
		</td>
	</tr>
	<tr>
		<td><label for="price">Stawka VAT:</label></td>
		<td><input name="price" placeholder="0.23" title="tu wpisz cenę" type="number" step="0.01" value="${product.vat}"/>
		</td>
	</tr>
	<tr>
		<td><label for="description">Opis:</label></td>
		<td><textarea name="description" rows="10" cols="120">${product.description}</textarea></td>
	</tr>
	<tr>
		<td><button>Zapisz</button></td>
	</tr>
</table>
</form>

<p><a href="wypisz_produkty9.jsp">przejdź do listy produktów</a></p>

</body>
</html>
