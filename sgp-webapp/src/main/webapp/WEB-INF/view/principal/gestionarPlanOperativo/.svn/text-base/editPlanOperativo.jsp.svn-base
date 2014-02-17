<%@include file="includesTaglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="web.app.title"></spring:message></title>
</head>
<body style="font-family: Arial; font-size: smaller;">
<div class="form-clasico">
	<fieldset><legend>Datos Plan Operativo</legend>
		<table class="table-clasico">
			<tr>
				<th>Id</th>
				<th>Version</th>
				<th>Estado</th>
				<th>Proyecto Nombre</th>
			</tr>
			<tr>
				<td><c:out value="${planOperativo.datoPlanOperativoID}"></c:out></td>
				<td><c:out value="${planOperativo.version}"></c:out></td>
				<td><c:out value="${planOperativo.estadoPlanOperativoDescripcion}"></c:out></td>
				<td><c:out value="${planOperativo.nombreProyecto}"></c:out></td>
			</tr>
		</table>
		<p>
		<fieldset><legend>Lista de Resultados</legend>
			<table class="table-clasico">
				<tr>
					<th>Resultado Id</th>
					<th>Definicion</th>
					<th>Supuesto</th>
					<th>Meta</th>
					<th>Estrato</th>
					<th>Duracion</th>
					<th>Accion</th>
				</tr>
				<c:forEach var="resultado" items="${planOperativo.listResultadoForm}">
					<tr>
						<td><c:out value="${resultado.resultadoID}"></c:out></td>
						<td><c:out value="${resultado.definicionResultado}"></c:out></td>
						<td><c:out value="${resultado.supuestoResultado}"></c:out></td>
						<td><c:out value="${resultado.metaResultado}"></c:out></td>
						<td><c:out value="${resultado.estratoNombre}"></c:out></td>
						<td><c:out value="${resultado.duracionMeses}"></c:out></td>
						<td><a href="editResultado.jspx?id=${resultado.resultadoID}">Editar</a></td>
					</tr>
				</c:forEach>
			</table>
		</fieldset>
		<c:if test="${!empty alterMessage}">
			<fieldset><legend>Mensajes de Alertas</legend>
				<textarea rows="1" cols="125"></textarea> 
			</fieldset>
		</c:if>
		<p>
		<input type="button" id="regresar" name="regresar" value="Regresar" />
	</fieldset>
</div>
</body>
</html>
