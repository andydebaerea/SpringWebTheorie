<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<html lang='nl'>
<head>
<title>Lening toevoegen</title>
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/default.css'>
</head>
<body>
	<a href="<c:url value='/'/>">Menu</a>
	<h1>Lening toevoegen</h1>
	<h2>Stap 1</h2>
	<c:url value='/leningen' var='url' />
	<form:form action='${url}' method='post' commandName='lening'>
		<form:label path='voornaam'>Voornaam:
<form:errors path='voornaam' cssClass='fout' />
		</form:label>
		<form:input path='voornaam' size='50' autofocus='autofocus' />
		<form:label path='familienaam'>Familienaam:
<form:errors path='familienaam' cssClass='fout' />
		</form:label>
		<form:input path='familienaam' size='50' />
		<form:label path='doel'>Doel van de lening:
<form:errors path='doel' cssClass='fout' />
		</form:label>
		<form:input path='doel' size='100' />
		<div>Telefoonnr(s). waar u bereikbaar bent:</div>
		<c:forEach items='${lening.telefoonNrs}' varStatus='status'>
			<div class='rij'>
				<form:input path='telefoonNrs[${status.index}]' type='tel' />
				<form:errors path='telefoonNrs[${status.index}]' cssClass='fout' />
			</div>
		</c:forEach>
		<input type='submit' value='Nog een telefoonnummer'
			name='nogeennummer'>
		<div>Aantal personen ten laste van de voorbije jaren:</div>
		<c:forEach items='${lening.aantalPersonenTenLastePerJaar}' var='entry'>
			<div class='rij'>
				<form:label path='aantalPersonenTenLastePerJaar[${entry.key}]'>${entry.key}:</form:label>
				<form:input path='aantalPersonenTenLastePerJaar[${entry.key}]'
					type='number' />
				<form:errors path='aantalPersonenTenLastePerJaar[${entry.key}]'
					cssClass='fout' />
			</div>
		</c:forEach>
		<input type='submit' value='Volgende stap' name='van1naar2'>
	</form:form>
</body>
</html>