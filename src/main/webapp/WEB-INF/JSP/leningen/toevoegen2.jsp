<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<html lang='nl'>
<head>
<title>Lening toevoegen</title>
<link rel='stylesheet' href='${pageContext.servletContext.contextPath}/styles/default.css'>
</head>
<body>
<a href="<c:url value='/'/>">Menu</a>
<h1>Lening toevoegen</h1>
<h2>Stap 2</h2>
<c:url value='/leningen' var='url'/>
<form:form action='${url}' method='post' commandName='lening'>
<form:label path='bedrag'>Bedrag:
<form:errors path='bedrag' cssClass='fout'/></form:label>
<form:input path='bedrag' size='8' type='number' autofocus='autofocus'/>
<form:label path='beginDatum'>Begindatum:
<form:errors path='beginDatum' cssClass='fout'/></form:label>
<form:input path='beginDatum' size='8'/>
<form:label path='aantalMaanden'>Aantal maanden:
<form:errors path='aantalMaanden' cssClass='fout'/></form:label>
<form:input path='aantalMaanden' size='4' type='number'/>
<div class='rij'>
<input type='submit' value='Vorige stap' name='van2naar1'>
<input type='submit' value='Bevestigen' name='bevestigen'>
</div>
</form:form>
</body>
</html>