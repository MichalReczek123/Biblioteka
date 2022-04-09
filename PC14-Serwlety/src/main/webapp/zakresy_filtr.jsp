<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Zakresy</title>
</head>
<body>
<h1>Zakresy zmiennych</h1>
<p>Zawartość ta sama co <code>zakresy.jsp</code>, ale ten adres przechodzi przez filtr.</p>
<p>Odświeżaj stronę i zobacz, które zmienne się zmieniają. Działanie seji można sprawdzić otwierając inną przeglądarkę lub profil prywatny.</p>

<jsp:useBean id="domyslnie" class="beans.InfoBean"/>
<jsp:useBean id="strona" class="beans.InfoBean" scope="page"/>
<jsp:useBean id="req" class="beans.InfoBean" scope="request"/>
<jsp:useBean id="sesja" class="beans.InfoBean" scope="session"/>
<jsp:useBean id="app" class="beans.InfoBean" scope="application"/>

<h2>Domyślnie</h2>
<p>${domyslnie.licznik}</p>
<p>${domyslnie.licznik}</p>

<h2>Page</h2>
<p>${strona.licznik}</p>
<p>${strona.licznik}</p>

<h2>Request</h2>
<p>${req.licznik}</p>
<p>${req.licznik}</p>

<h2>Session</h2>
<p>${sesja.licznik}</p>
<p>${sesja.licznik}</p>

<h2>Application</h2>
<p>${app.licznik}</p>
<p>${app.licznik}</p>

</body>
</html>