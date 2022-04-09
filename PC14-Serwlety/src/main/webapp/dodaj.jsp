<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dodawanie w JSP</title>
<link rel="stylesheet" type="text/css" href="styl.css">
</head>
<body>
<h1>Dodawanie w JSP</h1>

<form>
<input type="number" name="arg1" value="${param.arg1}">
+
<input type="number" name="arg2" value="${param.arg2}">
<button>=</button>
</form>

<div class="wynik">
Sumą jest: ${param.arg1 + param.arg2}
</div>

</body>
</html>