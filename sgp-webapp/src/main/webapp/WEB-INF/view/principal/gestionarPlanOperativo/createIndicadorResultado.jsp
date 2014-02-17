<%@include file="includesTaglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><spring:message code="web.app.title"></spring:message></title>
	<script>
		function soloNumero(e) {
		    if ([e.keyCode||e.which]==8 || [e.keyCode||e.which]==9 || [e.keyCode||e.which]==46) 
		        return true;
		    if ([e.keyCode||e.which] < 48 || [e.keyCode||e.which] > 57)
		        e.preventDefault? e.preventDefault() : e.returnValue = false;
		}
		function submitFormIndicadorResultado() {
			document.formIndicadorResultado.action = "actionSaveIndicadorResultado.jspx";
			document.formIndicadorResultado.submit();
		}
		function goTo(url) {
			window.location = url;
		}
		function goBack(url) {
			goTo(url);
		}
	</script>
</head>
<body> 
<div class="form-clasico">
<form:form name="formIndicadorResultado" method="post" action="">
	<div class="encabezado">Indicadores de Resultados</div>
		<br>
	<input type="hidden" id="datoPlanOperativoID" name="datoPlanOperativoID" value="${planOperativo.datoPlanOperativoID}">
	<input type="hidden" id="resultadoID" name="resultadoID" value="${resultado.resultadoID}">
    <fieldset><legend>Crear Indicador Por Resultado</legend>
		<br>
		<table border="0" width="100%">
			<tr>
				<td  style="text-align: right; width: 20%;"><label>Proyecto Codigo:</label></td>
				<td  style="text-align: left; width: 30%;">
					<label for="proyectoCodigo">${planOperativo.codigoProyecto}</label>
				</td>
				<td  style="text-align: right; width: 20%;"><label>Proyecto Nombre:</label></td>
				<td  style="text-align: left; width: 30%;">
					<label for="proyectoNombre">${planOperativo.nombreProyecto}</label>
				</td>
			</tr>
			<tr>
				<td  style="text-align: right; width: 20%;"><label>Plan Operativo:</label></td>
				<td  style="text-align: left; width: 30%;">
					<label for="planOperativo">${planOperativo.version}</label>
				</td>
				<td  style="text-align: right; width: 20%;"><label>Resultado:</label></td>
				<td  style="text-align: left; width: 30%;">
					<label for="resultadoNombre">${resultado.definicionResultado}</label>
				</td>				
			</tr>
			<tr>
				<td  style="text-align: right; width: 20%;"><label>Tipo Indicador:</label></td>
				<td  style="text-align: left; width: 30%;">
					<select id="tipoIndicadorId" name="tipoIndicadorId" style="width: 100px;">
							<option value="0">--Tipo--</option>
						<c:forEach items="${listaTipoIndicadorResultado}" var="lista">  
	 							<option value="${lista.tablaEspecificaID}">${lista.descripcionCabecera}</option>  
						</c:forEach>
					</select>
				</td>
				<td style="text-align: right; width: 20%;"><label>Unidad Medida:</label></td>
				<td style="text-align: left; width: 30%;">
					<select id="unidadMedidaId" name="unidadMedidaId" style="width: 100px;">
							<option value="0">--Unidad--</option>
						<c:forEach items="${listaUnidadMedida}" var="lista">  
	 							<option value="${lista.tablaEspecificaID}">${lista.descripcionCabecera}</option>  
						</c:forEach>
					</select>
				</td>	
			</tr>
			<tr>
				<td style="text-align: right; width: 20%;"><label>Definicion Indicador:</label></td>
				<td style="text-align: left; width: 80%;" colspan="3">
					<textarea rows="3" cols="75" id="definicionIndicador" name="definicionIndicador" style="width: 95%;"></textarea>
				</td>
			</tr>
			<tr>
				<td style="text-align: right; width: 20%;"><label>Medio Verificacion:</label></td>
				<td style="text-align: left; width: 80%;" colspan="3">
					<textarea rows="3" cols="75" id="medioVerificacion" name="medioVerificacion" style="width: 95%;"></textarea>
				</td>
			</tr>
			<tr>
				<td style="text-align: right; width: 20%;"><label>Situacion Actual:</label></td>
				<td style="text-align: left; width: 30%;">
					<input type="text" id="situacionActual" name="situacionActual" size="11" onkeypress="soloNumero(event)" maxlength="5"/>
				</td>
				<td style="text-align: right; width: 20%;"><label>Situacion Final:</label></td>
				<td style="text-align: left; width: 30%;">
					<input type="text" id="situacionFinal" name="situacionFinal" size="11" onkeypress="soloNumero(event)" maxlength="5"/>
				</td>
			</tr>
			<tr>
				<td colspan="4"> 
					<br>
				</td>
			</tr>
			<tr>
				<td style="width: 50%;" colspan="2"></td>
				<td style="text-align: right; width: 20%;">
					<input type="button" value="Regresar" onClick="goBack('showIndicadorResultado.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID}');"> </td>
				
				<td style="text-align: right; width: 30%;">
					<input type="button" value="Grabar Indicador Resultado" onClick="submitFormIndicadorResultado()">
				</td>
			</tr>
		</table>
		<br>
    </fieldset>
</form:form>
</div>
</body>
</html>