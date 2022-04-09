<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rozmowa JSP</title>
</head>
<body>
<h1>Rozmowa JSP</h1>

<form>
<label for="imie">Jak masz na imię?</label>
<input type="text" name="imie">
<button>Wyślij</button>
</form>

<p>Witaj ${param.imie}!</p>

</body>
</html>
