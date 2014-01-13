<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
<title>${filiaal.naam}</title>
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/default.css'>
<style>
form,form input {
	display: inline; /* de twee forms naast elkaar */
}

form {
	margin-right: 5px;
}
</style>
</head>
<body>
	<a href="<c:url value='/'/>">Menu</a>
	<c:choose>
		<c:when test="${not empty filiaal}">
			<h1>${filiaal.naam}</h1>
			<dl>
				<dt>Straat</dt>
				<dd>${filiaal.adres.straat}</dd>
				<dt>Huisnr.</dt>
				<dd>${filiaal.adres.huisNr}</dd>
				<dt>Postcode</dt>
				<dd>${filiaal.adres.postcode}</dd>
				<dt>Gemeente</dt>
				<dd>${filiaal.adres.gemeente}</dd>
				<dt>Type</dt>
				<dd>${filiaal.hoofdFiliaal ? "Hoofdfiliaal" : "Bijfiliaal"}</dd>
				<dt>Waarde gebouw</dt>
				<dd>
					<spring:eval expression='filiaal.waardeGebouw' />
				</dd>
				<dt>Ingebruikname</dt>
				<dd>
					<spring:eval expression='filiaal.inGebruikName' />
				</dd>
				<c:if test='${heeftFoto}'>
					<dt>Foto</dt>
					<dd>
						<c:url value='/images/${filiaal.id}.jpg' var='fotoURL' />
						<img src='${fotoURL}' alt='${filiaal.id}'>
					</dd>
				</c:if>
			</dl>
			<spring:url value='/filialen/{id}' var='verwijderURL'>
				<spring:param name='id' value='${filiaal.id}' />
			</spring:url>
			<form:form action='${verwijderURL}' method='delete'
				id='verwijderform'>
				<input type='submit' value='Verwijderen' id='verwijderknop'>
			</form:form>
			<script>
				document.getElementById('verwijderform').onsubmit = function() {
					document.getElementById('verwijderknop').disabled = true;
				};
			</script>
			<spring:url value='/filialen/{id}/wijzigen' var='wijzigURL'>
				<spring:param name='id' value='${filiaal.id}' />
			</spring:url>
			<form action='${wijzigURL}' method='get'>
				<input type='submit' value='Wijzigen'>
			</form>
		</c:when>
		<c:otherwise>
			<div class="fout">Filiaal niet gevonden</div>

		</c:otherwise>
	</c:choose>
	<c:if test='${not empty param.fout}'>
		<div class='fout'>${param.fout}</div>
	</c:if>
</body>
</html>