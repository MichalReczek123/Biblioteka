<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.time.LocalTime"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Przykład JSP</title>
</head>
<body>
<h1>Przykład JSP</h1>
<p>Ala ma kota.</p>
<p>Piszemy jak w normalnym HTML-u...</p>
<p>Ale Java Server Pages ma kilka dodatkowych możliwości.</p>

<h2>Scriptlet</h2>
<p>Czyli fragmenty kodu Java wewnątrz stron JSP</p>
<%
System.out.println("Wypisuję na System.out");
out.println("<p>Wypisuję poprzez wyjście serwletu</p>");
// w scriptletach dostępne są pewne standardowe zmienne, m.in. request, response, out

int x = 12 + 15;
out.println("<div>Liczba = " + x + "</div>");
String imie = request.getParameter("imie");
if(imie != null) {
    out.println("<div>Witaj " + imie + "</div>");
}
%>

<%-- To jest komentarz - czyli to się nie wykonuje, ani nie widać tego w outpucie --%>
<!-- To jest komentarz HTML i on zostanie wysłany do klienta. -->

<%-- To będzie odczytanie wartości: --%>
<div>To jest wynik: <%=x*10%></div>
<div>Teraz jest godzina: <%= LocalTime.now() %></div>

<%-- A to jest fragment kodu do umieszczenia na poziomie klasy, a nie wewnątrz metody. To mogą być deklaracjew zmiennych i metod: --%>
<%!
static int licznik = 100;
%>

<%-- Tu już zwykły odczyt: --%>
<div>Licznik: <%= ++ licznik %></div>

<p>Jednak umieszczanie „gołego kodu” Javy wewnątrz stron nie jest najbardziej zalecanym podejściem.
JSP posiada również inne możliwości.</p>

<h2>Expression Language (EL)</h2>
<p>To jest możliwość pisania wyrażeń zaczynających się od znaku <code>$</code> bezpośrednio w treści.
Wyrażenia te obejmują arytmetykę itp. proste operacje oraz pozwalają odwoływać się do obiektów Javy
obecnych w kontekście aplikacji, tzw. „beanów”.</p>

<p>Proste obliczenie: ${2+3*4}</p>
<p>Prarametr imie: ${param.imie}</p>

<jsp:useBean id="obiekt" class="beans.ExampleBean"/>
<p>Mam beana: ${obiekt}</p>
<p>Tekst: ${obiekt.tekst}</p>
<%-- Tak naprawdę wywoływane jest obiekt.getTekst() --%>

<%-- A teraz obiekt.setTekst("Ola ma psa") --%>
<jsp:setProperty name="obiekt" property="tekst" value="Ola ma psa nr ${2+3}"/>
<p>Tekst po zmianie: ${obiekt.tekst}</p>

<p>upper: ${obiekt.tekstDuze}</p>
<p>czas: ${obiekt.currentTime}</p>
<p>pola daty i czasu: ${obiekt.currentTime.hour}:${obiekt.currentTime.minute} , dzień roku: ${obiekt.currentTime.dayOfYear}</p>


<h2>Biblioteki tagów (taglib)</h2>
<p>Oprócz tagów HTML, w skryptach JSP można używać dodatkowych tagów, które wpływają na działanie.
Są one dostępne w formie bibliotek, przy czym zdecydowanie najczęściej używa się standardowej biblioteki tagów: JSTL.</p>

<c:if test="${not empty(param.imie)}">
    <div>Witaj po raz kolejny: ${param.imie}</div>
</c:if>

<ol>
<c:forEach var="miasto" items="Warszawa,Kraków,Gdańsk">
    <li>Pozdrawiamy miasto ${miasto}.</li>
</c:forEach>
</ol>
<%-- forEach najczęściej używa się do przeglądania danych z bazy itp, źródeł - nie trzeba elementów wymieniać po przecinku --%>


</body>
</html>
