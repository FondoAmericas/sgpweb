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
		function submitFormBeneficiarioResultado() {
			document.formBeneficiarioResultado.action = "actionSaveBeneficiarioResultado.jspx";
			document.formBeneficiarioResultado.submit();
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
<form:form name="formBeneficiarioResultado" method="post" action="">
	<input type="hidden" id="datoPlanOperativoID" name="datoPlanOperativoID" value="${planOperativo.datoPlanOperativoID}">
	<input type="hidden" id="resultadoID" name="resultadoID" value="${resultado.resultadoID}">
    <fieldset><legend>Crear Beneficiario Por Resultado</legend>
		<br>
		<table border="0">
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
				<td style="text-align: right; width: 20%;"><label>Tipo Beneficiario:</label></td>
				<td  style="text-align: left; width: 80%;" colspan="3">
					<select id="tipoBeneficiarioId" name="tipoBeneficiarioId" style="width: 100px;">
							<option value="0">--Tipo--</option>
						<c:forEach items="${listaTipoBeneficiario}" var="lista">  
	 							<option value="${lista.tablaEspecificaID}">${lista.descripcionCabecera}</option>  
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td  style="text-align: right; width: 20%;"><label>Caracteristicas:</label></td>
				<td  style="text-align: left; width: 80%;" colspan="3">
					<textarea rows="3" cols="75" id="caracteristicas" name="caracteristicas" style="width: 95%;"></textarea>
				</td>
			</tr>
			<tr>
				<td  style="text-align: right; width: 20%;"><label>Cantidad Programada:</label></td>
				<td  style="text-align: left; width: 30%;" >
					<input type="text" id="cantidadProgramado" name="cantidadProgramado" size="11" onkeypress="soloNumero(event)" />
				</td>
				<td  style="text-align: right; width: 20%;"><label>Estrato:</label></td>
				<td  style="text-align: left; width: 30%;">
					<select id="estratoId" name="estratoId" style="width: 100px;">
						<option value="0">--Estrato--</option>
					<c:forEach items="${listaEstratos}" var="estrato">  
 							<option value="${estrato.tablaEspecificaID}">${estrato.descripcionCabecera}</option>  
					</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td  style="text-align: right; width: 20%;"><label>Descripcion:</label></td>
				<td  style="text-align: left; width: 80%;" colspan="3">
					<textarea rows="3" cols="75" id="descripcion" name="descripcion" style="width: 95%;"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4"> 
					<br>
				</td>
			</tr>
			<tr>
				<td  style="width: 50%;" colspan="2"></td>
				<td  style="text-align: right; width: 20%;">
					<input type="button" value="Regresar" onClick="goBack('showBeneficiarioResultado.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID}');"> </td>
				
				<td  style="text-align: right; width: 30%;">
					<input type="button" value="Grabar Beneficiario Resultado" onClick="submitFormBeneficiarioResultado()">
				</td>
			</tr>
		</table>
		<br>
    </fieldset>
</form:form>
</div>
</body>
</html>