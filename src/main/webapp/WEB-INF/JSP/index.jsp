<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@taglib prefix='security'
	uri='http://www.springframework.org/security/tags'%>
<!doctype html>
<html lang="${pageContext.response.locale.language}">
<head>
<title><fmt:message key='menu' /></title>
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/default.css'>
<c:if test="${not empty kleur}">
	<style>
body {
	background-color: ${kleur
}
;
}
</style>
</c:if>
</head>
<body>
	<nav>
		<ul class='zonderbolletjes'>
			<li><a href="<c:url value='/filialen'/>"> <fmt:message
						key='filialen'>
						<fmt:param value='${aantalFilialen}' />
					</fmt:message>
			</a></li>
			<security:authorize url='/filialen/toevoegen'>
				<li><a href="<c:url value='/filialen/toevoegen'/>"> <fmt:message
							key='filiaalToevoegen' /></a></li>
			</security:authorize>
			<li><a href="<c:url value='/filialen/vantotpostcode'/>"> <fmt:message
						key="filialenVanTotPostcode" /></a></li>
			<security:authorize url='/werknemers'>
				<li><a href="<c:url value='/werknemers'/>"> <fmt:message
							key='werknemers' /></a></li>
			</security:authorize>
			<li><a href="<c:url value='/leningen/toevoegen'/>"> <fmt:message
						key='leningToevoegen' /></a></li>
			<security:authorize access='isAnonymous()'>
				<li><a href="<c:url value='/login'/>"><fmt:message
							key='aanmelden' /></a></li>
			</security:authorize>
			<security:authorize access='isAuthenticated()'>
				<security:authentication property='name' var='userName' />
				<li><a href="<c:url value='/j_spring_security_logout'/>"> <fmt:message
							key='afmelden'>
							<fmt:param value='${userName}' />
						</fmt:message></a></li>
			</security:authorize>
		</ul>
	</nav>
	<jsp:include page="/WEB-INF/JSP/taalkeuze.jsp"></jsp:include>
	<c:url var='rozeURL' value='/'>
		<c:param name='kleur' value='Pink' />
	</c:url>
	<c:url var='blauwURL' value='/'>
		<c:param name='kleur' value='LightBlue' />
	</c:url>
	<ul class='zonderbolletjes'>
		<li><a href='${rozeURL}'><fmt:message key='roze' /></a></li>
		<li><a href='${blauwURL}'><fmt:message key='blauw' /></a></li>
	</ul>
</body>
</html>