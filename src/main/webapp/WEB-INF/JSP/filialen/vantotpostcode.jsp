<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
<title>Filialen van tot postcode</title>
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/default.css'>
</head>
<body>
	<a href="<c:url value='/'/>">Menu</a>
	<h1>Filialen van tot postcode</h1>
	<c:url value='/filialen' var='url' />
	<form:form action='${url}' method='get'
		commandName='vanTotPostcodeForm'>
		<form:label path='vanpostcode'>Van:
		</form:label>
		<form:errors path='vanpostcode' cssClass='fout' />
		<form:input path='vanpostcode' type='number' autofocus='autofocus' />
		<form:label path='totpostcode'>Tot: </form:label>
		<form:errors path='totpostcode' cssClass='fout' />
		<form:input path='totpostcode' type='number' />
		<input type='submit' value='Zoeken'>
		<form:errors cssClass='fout' element='div'/>
	</form:form>
	<c:forEach items='${filialen}' var='filiaal'>
		<h2>${filiaal.naam}</h2>
		<p>${filiaal.adres.straat}
			${filiaal.adres.huisNr}<br> ${filiaal.adres.postcode}
			${filiaal.adres.gemeente}
		</p>
	</c:forEach>
</body>
</html>