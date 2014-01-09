<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
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
			</dl>
			<c:url value='/filialen/verwijderen' var='verwijderURL'>
				<c:param name='id' value='${filiaal.id}' />
			</c:url>
			<form action='${verwijderURL}' method='post' id='verwijderform'>
				<input type='submit' value='Verwijderen' id='verwijderknop'>
				<script>
					document.getElementById('verwijderform').onsubmit = function() {
						document.getElementById('verwijderknop').disabled = true;
					};
				</script>
			</form>
			<c:url value='/filialen/wijzigen' var='wijzigURL' />
			<form action='${wijzigURL}' method='get'>
				<input type='hidden' name='id' value='${filiaal.id}'> <input
					type='submit' value='Wijzigen'>
			</form>
		</c:when>
		<c:otherwise>
			<div class="fout">Filiaal niet gevonden</div>

		</c:otherwise>
	</c:choose>
</body>
</html>