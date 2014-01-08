<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<title>Lening toevoegen (stap 1)</title>
<link rel='stylesheet' href='${pageContext.servletContext.contextPath}/styles/default.css'>
</head>
<body>
<a href="<c:url value='/'/>">Menu</a>
<h1>Lening toevoegen</h1>
<h2>Stap 1</h2>
<form method='post' action="<c:url value='/leningen'/>">
<div> Hier vraag je later de voornaam en familienaam van de ontlener en het doel van de lening </div>
<input type='submit' value='Volgende stap' name='van1naar2'>
</form>
</body>
</html>