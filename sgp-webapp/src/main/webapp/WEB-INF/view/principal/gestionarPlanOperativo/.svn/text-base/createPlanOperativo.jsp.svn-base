<%@include file="includesTaglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><spring:message code="web.app.title"></spring:message></title>
	<script>
		function submitFormPlanOperativo(datoProyectoID) {
			document.formPlanOperativo.action = "actionSavePlanOperativo.jspx?datoProyectoID=" + datoProyectoID;
			document.formPlanOperativo.submit();
		}
		function submitFormResultado(datoProyectoID) {
			document.formPlanOperativo.action = "addPlanOperativoResultado.jspx?datoProyectoID=" + datoProyectoID;
			document.formPlanOperativo.submit();
		}
		function go(url) {
			window.location = url;
		}
		function removeResultado(url) {
			var isOK = confirm("Esta segura de eliminar?");
			if (isOK) {
				go(url);
			}
		}
	</script>
</head>
<body>
<div class="form-clasico">
<form:form name="formPlanOperativo" method="post" action="">
	<input type="hidden" id="datoProyectoID" name="datoProyectoID" value="${datoProyectoID}">
	<fieldset><legend>Plan Operativo Informacion Resultado</legend>
		<br>
		<table class="table-clasico">
			<tr>
				<td width="20%" align="right"><label>Definicion:</label></td>
				<td width="30%"><textarea rows="3" cols="50" id="definicionResultado" name="definicionResultado"></textarea></td>
				<td width="20%" align="right"><label>Supuesto:</label></td>
				<td width="30%"><textarea rows="3" cols="50" id="supuestoResultado" name="supuestoResultado"></textarea></td>
			</tr>
			<tr>
				<td width="20%" align="right"><label>Meta:</label></td>
				<td width="30%"><input type="text" id="metaResultado" name="metaResultado" style="width: 40px;"/>
				<label>Estrato:</label>
				<select id="estratoId" name="estratoId" style="width: 100px;">
					<c:forEach items="${listaEstratos}" var="estrato">  
 							<option value="${estrato.tablaEspecificaID}">${estrato.descripcionCabecera}</option>  
					</c:forEach>
				</select>
				</td>
				<td width="20%" align="right"><label>Duracion:</label></td>
				<td width="30%"><input type="text" id="duracionMeses" name="duracionMeses"/></td>
			</tr>
			<tr>
				<td colspan="4"></td>
			</tr>
			<tr>
				<td colspan="3" width="70%"></td>
				<td align="left" width="30%">
					<input type="button" name="addResult" value="Agregar Resultado" onClick="submitFormResultado('${datoProyectoID}')">
				</td>
			</tr>
			<tr>
				<td colspan="4"></td>
			</tr>
		</table>
		<br>
		<table class="table-clasico">
			<caption>Resultados</caption>
			<thead>
			<tr>
				<td><label>Id</label></td>
				<td><label>Definicion</label></td>
                <td><label>Supuesto</label></td>
                <td><label>Meta</label></td>
				<td><label>Estrato Nombre</label></td>
                <td><label>Duracion</label></td>
                <td><label>Accion</label></td>
			</tr>
			</thead>
			<tbody>
			<c:if test="${empty listResultado}">
				<tr>
					<td colspan="7">No Results found</td>
				</tr>
			</c:if>
			<c:if test="${!empty listResultado}">
				<c:forEach var="resultado" items="${listResultado}">		
	    			<tr>
						<td><c:out value="${resultado.resultadoID}"></c:out></td>
						<td><c:out value="${resultado.definicionResultado}"></c:out></td>
						<td><c:out value="${resultado.supuestoResultado}"></c:out> </td>
						<td><c:out value="${resultado.metaResultado}"></c:out></td>
						<td><c:out value="${resultado.estratoNombre}"></c:out></td>
						<td><c:out value="${resultado.duracionMeses}"></c:out></td>
						<td>
							<a href="javascript:removeResultado('removeResultado.jspx?resultadoID=${resultado.resultadoID}');">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			</tbody>
			</table>
			<br>
	</fieldset>
	<br>
	<c:if test="${!empty alterMessage}">
		<fieldset><legend>Mensajes de Alertas</legend>
			<textarea rows="1" cols="125"></textarea> 
		</fieldset>
	</c:if>
	<br>
	<input type="button" name="savePlanOperativo" value="Grabar Plan Operativo" onClick="submitFormPlanOperativo('${datoProyectoID}')">
</form:form>
</div>
</body>
</html>
