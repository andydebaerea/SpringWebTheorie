<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html lang="${pageContext.response.locale.language}">
<head>
<title><fmt:message key='menu' /></title>
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/default.css'>
</head>
<body>
	<nav>
		<ul class='zonderbolletjes'>
			<li><a href="<c:url value='/filialen'/>"> <fmt:message
						key='filialen'>
						<fmt:param value='${aantalFilialen}' />
					</fmt:message>
			</a></li>
			<li><a href="<c:url value='/filialen/toevoegen'/>"> <fmt:message
						key="filiaalToevoegen" /></a></li>
			<li><a href="<c:url value='/filialen/vantotpostcode'/>"> <fmt:message
						key="filialenVanTotPostcode" /></a></li>
			<li><a href="<c:url value='/werknemers'/>"> <fmt:message
						key="werknemers" /></a></li>
			<li><a href="<c:url value='/leningen/toevoegen'/>"> <fmt:message
						key='leningToevoegen' /></a></li>
		</ul>
	</nav>
	<jsp:include page="/WEB-INF/JSP/taalkeuze.jsp"></jsp:include>
</body>
</html>