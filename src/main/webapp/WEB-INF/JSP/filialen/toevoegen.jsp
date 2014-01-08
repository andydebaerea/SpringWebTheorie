<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
<title>Filiaal toevoegen</title>
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/default.css'>
</head>
<body>
	<a href="<c:url value='/'/>">Menu</a>
	<h1>Filiaal toevoegen</h1>
	<c:url value='/filialen' var='url' />
	<form:form action='${url}' method='post' commandName='filiaal'
		id='toevoegform'>
		<jsp:include page='filiaalformfields.jsp' />
		<input type='submit' value='Toevoegen' id='toevoegknop'>
	</form:form>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
</body>
</html>