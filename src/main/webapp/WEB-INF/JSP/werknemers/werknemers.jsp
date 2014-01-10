<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<title>Werknemers</title>
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/default.css'>
</head>
<body>
	<a href="<c:url value='/'/>">Menu</a>
	<h1>Werknemers</h1>
	<table>
		<thead>
			<tr>
				<th>Naam</th>
				<th>Filiaal</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items='${werknemers}' var='werknemer'>
				<tr>
					<td>${werknemer.voornaam}${werknemer.familienaam}</td>
					<td>${werknemer.filiaal.naam}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>