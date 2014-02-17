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
		function submitFormCronogramaResultado() {
			document.formCronogramaResultado.action = "actionSaveCronogramaResultado.jspx";
			document.formCronogramaResultado.submit();
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
<form:form name="formCronogramaResultado" method="post" action="">
<div class="encabezado">Cronograma de la Meta del Resultado</div>
		<br>
	<input type="hidden" id="datoPlanOperativoID" name="datoPlanOperativoID" value="${planOperativo.datoPlanOperativoID}">
	<input type="hidden" id="resultadoID" name="resultadoID" value="${resultado.resultadoID}">
    <fieldset><legend>Crear Cronograma Por Resultados</legend>
		<br>
		<table border="0" width="100%">
			<tr>
				<td style="text-align: right; width: 20%;"><label>Proyecto Codigo:</label></td>
				<td style="text-align: left; width: 30%;">
					<label for="proyectoCodigo">${planOperativo.codigoProyecto}</label>
				</td>
				<td style="text-align: right; width: 20%;"><label>Proyecto Nombre:</label></td>
				<td style="text-align: left; width: 30%;">
					<label for="proyectoNombre">${planOperativo.nombreProyecto}</label>
				</td>
			</tr>
			<tr>
				<td style="text-align: right; width: 20%;"><label>Plan Operativo:</label></td>
				<td style="text-align: left; width: 30%;">
					<label for="planOperativo">${planOperativo.version}</label>
				</td>
				<td style="text-align: right; width: 20%;"><label>Resultado:</label></td>
				<td style="text-align: left; width: 30%;">
					<label for="resultadoNombre">${resultado.definicionResultado}</label>
				</td>				
			</tr>
			<tr>
				<td style="text-align: right; width: 20%;"><label>Avance de Meta:</label></td>
				<td style="text-align: left; width: 30%;">
					<input type="text" id="avanceMeta" name="avanceMeta" size="11" onkeypress="soloNumero(event)" maxlength="6" style="width:65px;"/>
				</td>
				<td style="text-align: right; width: 20%;"><label>Periodo:</label></td>
				<td style="text-align: left; width: 30%;">
					<input type="text" id="periodo" name="periodo" size="11" onkeypress="soloNumero(event)" maxlength="2" style="width:65px;"/>
				</td>
			</tr>
			<tr>
				<td colspan="4"> 
					<br>
				</td>
			</tr>
			<tr>
				<td style="width: 50%;" colspan="2"></td>
				<td style="text-align: right; width: 20%">
					<input type="button" value="Regresar" onClick="goBack('showCronogramaResultado.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID}');"> </td>
				
				<td style="text-align: right; width: 30%">
					<input type="button" value="Grabar Cronograma Resultado" onClick="submitFormCronogramaResultado()">
				</td>
			</tr>
		</table>
		<br>
    </fieldset>
</form:form>
</div>
</body>
</html>