<%@include file="includesTaglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><spring:message code="web.app.title"></spring:message></title>
	
	
	<script>
		function goTo(url) {
			window.location = url;
		}
	</script>
</head>
<body >
<div class="form-clasico">
	<fieldset><legend>Datos Plan Operativo</legend>
		<p>
		<table class="table-clasico" border="1">
			<caption>Plan Operativo</caption>
			<thead>
			<tr>
				<th>ID</th>
				<th>Version</th>
				<th>Estado</th>
				<th>Proyecto ID</th>
				<th>Proyecto Nombre</th>
				<th>Accion</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${empty planOperativo}">
			
				<tr>
					<td colspan="6">No Data found for Plan Operativo</td>
				</tr>
			</c:if>
			<c:if test="${!empty planOperativo}">
				<tr>
					<td><c:out value="${planOperativo.datoPlanOperativoID}"></c:out></td>
					<td><c:out value="${planOperativo.version}"></c:out></td>
					<td><c:out value="${planOperativo.estadoPlanOperativoDescripcion}"></c:out></td>
					<td><c:out value="${planOperativo.datoProyectoID}"></c:out></td>
					<td><c:out value="${planOperativo.nombreProyecto}"></c:out></td>
					<td><a href="editPlanOperativo.jspx?id=${planOperativo.datoPlanOperativoID}">Editar</a></td>
				</tr>
			</c:if>
			</tbody>
		</table>
		
		<p>
		<table class="table-clasico">
			<caption>Plan Operativo</caption>
			<thead>
			<tr>
				<th>ID</th>
				<th>Version</th>
				<th>Estado</th>
				<th>Proyecto ID</th>
				<th>Proyecto Nombre</th>
				<th>Accion</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${empty planOperativo}">
			
				<tr>
					<td colspan="6">No Data found for Plan Operativo</td>
				</tr>
			</c:if>
			<c:if test="${!empty planOperativo}">
				<tr>
					<td><c:out value="${planOperativo.datoPlanOperativoID}"></c:out></td>
					<td><c:out value="${planOperativo.version}"></c:out></td>
					<td><c:out value="${planOperativo.estadoPlanOperativoDescripcion}"></c:out></td>
					<td><c:out value="${planOperativo.datoProyectoID}"></c:out></td>
					<td><c:out value="${planOperativo.nombreProyecto}"></c:out></td>
					<td><a href="editPlanOperativo.jspx?id=${planOperativo.datoPlanOperativoID}">Editar</a></td>
				</tr>
			</c:if>
			</tbody>
		</table>
		<p>
		<c:if test="${!empty planOperativo}">
			<fieldset><legend>Lista de Resultados</legend>
				<p>
				<table class="table-clasico">
					<tr>
						<th>ID</th>
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
							<td>
								<input type="button" name="agregar" value="Detalle" onClick="goTo('showPlanOperativoDetail.jspx?resultadoID=${resultado.resultadoID}');">
							</td>
						</tr>
					</c:forEach>
				</table>
			</fieldset>
		</c:if>
		<p>
		<c:if test="${!empty alterMessage}">
			<fieldset><legend>Mensajes de Alertas</legend>
				<textarea rows="1" cols="125"></textarea> 
			</fieldset>
		</c:if>
		<p>
		<c:if test="${empty planOperativo}">
			<input type="button" name="savePlanOperativo" value="Crear Plan Operativo" onClick="goTo('createPlanOperativo.jspx');">
		</c:if>
	</fieldset>
</div>
</body>
</html>