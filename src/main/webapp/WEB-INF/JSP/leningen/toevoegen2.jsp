<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<title>Lening toevoegen (stap 2)</title>
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/default.css'>
</head>
<body>
	<a href="<c:url value='/'/>">Menu</a>
	<h1>Lening toevoegen</h1>
	<h2>Stap 2</h2>
	<form method='post' action="<c:url value='/leningen'/>">
		<div>Hier vraag je later het bedrag, de begindatum en het aantal
			maanden afbetaling van dezelfde lening</div>
		<input type='submit' value='Vorige stap' name='van2naar1'> <input
			type='submit' value='Bevestigen' name='bevestigen'>
	</form>
</body>
</html>