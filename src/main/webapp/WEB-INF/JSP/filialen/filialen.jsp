<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<title>Filialen</title>
<link
	rel='stylesheet" href="${pageContext.servletContext.contextPath}/styles/default.css'>
</head>
<body>
	<a href="<c:url value='/'/>">Menu</a>
	<h1>Filialen</h1>
	<c:forEach items='${filialen}' var='filiaal'>
		<spring:url var='url' value='/filialen/{id}'>
			<spring:param name='id' value='${filiaal.id}' />
		</spring:url>
		<<h2>
			<a href='${url}'>${filiaal.naam}</a>
		</h2>
		<p>${filiaal.adres.straat}
			${filiaal.adres.huisNr}<br> ${filiaal.adres.postcode}
			${filiaal.adres.gemeente}
		</p>
	</c:forEach>
</body>
</html>