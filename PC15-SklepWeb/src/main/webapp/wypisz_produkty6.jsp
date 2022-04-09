<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista produktów 6</title>
<link rel="stylesheet" type="text/css" href="styl.css">
</head>
<body>

<h1>Lista produktów - wersja 6 JSP</h1>

<jsp:useBean id="bean" class="sklep.beans.ProductBean"/>

<%-- Tutaj tak naprawdę wywołujemy metodę getAllProducts i w pętli przeglądamy elementy listy.
	Każdy element to jest obiekt klasy Product i np. odczyt product.productNam
	to jest wywołanie metody getProductName() z tego produktu.
 --%>
<c:forEach var="product" items="${bean.allProducts}">
        <div class="product">
        <h3>${product.productName}</h3>
        <div class="price">${product.price}</div>
        <p>${product.description}</p>
        </div>
</c:forEach>

</body>
</html>
