<%@include file="includesTaglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><spring:message code="web.app.title"></spring:message></title>
	<script>
		function goToBack(url) {
			window.location = url;
		}
	</script>
</head>
<body style="font-family: Arial; font-size: smaller;"> 
<form>
<div class="form-clasico">
    <fieldset><legend>Actividad</legend>
		<br>
		<table border="0">
			<tr>
				<td align="right">Codigo Proyecto:</td>
				<td align="left" colspan="5">
					<label for="proyectoNombre">${planOperativo.codigoProyecto}</label>
				</td>
			</tr>
			<tr>
				<td align="right">Nombre Proyecto:</td>
				<td align="left" colspan="5">
					<label for="proyectoCodigo">${planOperativo.nombreProyecto}</label>
				</td>
			</tr>
			<tr>
				<td align="right">Plan Operativo:</td>
				<td align="left" colspan="5">
					<label for="planOperativo">${planOperativo.version}</label>
				</td>
			</tr>
			<tr>
				<td align="right">Resultado:</td>
				<td align="left" colspan="5">
					<label for="resultado">${resultado.definicionResultado}</label>
				</td>
			</tr>
			<tr>
				<td align="right">Nombre Actividad:</td>
				<td align="left" colspan="5">
					<label for="resultado">${resultado.definicionResultado}</label>
				</td>
			</tr>
		</table>
		<input type="button" name="save" value="Crear Actividad" onClick="goTo('createResultado.jspx');">
		<table border="1">
			<tr>
				<th align="center">Tipo Actividad</th>
				<th align="center">Nombre</th>
				<th align="center">Descripcion</th>
				<th align="center">Medio Verificacion</th>
				<th align="center">Duracion</th>
				<th align="center">Actividad Transferencia</th>
			</tr>
			<c:forEach var="indicador" items="${listIndicador}">
				<tr>
					<td align="left"><c:out value="${indicador.tipoIndicadorNombre}"></c:out></td>
					<td align="left"><c:out value="${indicador.definicionIndicador}"></c:out></td>
					<td align="left"><c:out value="${indicador.unidadMedidaNombre}"></c:out></td>
					<td align="left"><c:out value="${indicador.medioVerificacion}"></c:out></td>
					<td align="left"><c:out value="${indicador.situacionActual}"></c:out></td>
					<td align="left"><c:out value="${indicador.situacionFinal}"></c:out></td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<br>
		<table border="0">
			<tr>
				<td align="center"><input type="button" name="save" value="regresar" onClick="goToBack()"></td>
				<td align="center"><input type="button" name="save" value="Meta Por Actividad" onClick="goToBack()"></td>
				<td align="center"><input type="button" name="save" value="Costo Por Actividad" onClick="goToBack()"></td>
			</tr>
			</table>
    </fieldset>
 </div>
 </form>
</body>
</html>
