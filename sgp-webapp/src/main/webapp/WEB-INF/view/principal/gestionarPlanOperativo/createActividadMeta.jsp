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
		function submitFormActividadMeta() {
			document.formActividadMeta.action = "actionSaveActividadMeta.jspx";
			document.formActividadMeta.submit();
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
<form:form name="formActividadMeta" method="post" action="">
	<input type="hidden" id="datoPlanOperativoID" name="datoPlanOperativoID" value="${planOperativo.datoPlanOperativoID}">
	<input type="hidden" id="resultadoID" name="resultadoID" value="${resultado.resultadoID}">
<!--     <fieldset><legend>Crear Actividad Por Meta</legend> -->
<!-- 		<br> -->
		<table border="0" width="100%">
<!-- 			<tr> -->
<!-- 				<td style="text-align: right; width: 20%"><label>Proyecto Codigo:</label></td> -->
<!-- 				<td style="text-align: left; width: 30%"> -->
<%-- 					<label for="proyectoCodigo">${planOperativo.codigoProyecto}</label> --%>
<!-- 				</td> -->
<!-- 				<td style="text-align: right; width: 20%"><label>Proyecto Nombre:</label></td> -->
<!-- 				<td style="text-align: left; width: 30%"> -->
<%-- 					<label for="proyectoNombre">${planOperativo.nombreProyecto}</label> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td style="text-align: right; width: 20%"><label>Plan Operativo:</label></td> -->
<!-- 				<td style="text-align: left; width: 30%"> -->
<%-- 					<label for="planOperativo">${planOperativo.version}</label> --%>
<!-- 				</td> -->
<!-- 				<td style="text-align: right; width: 20%"><label>Resultado:</label></td> -->
<!-- 				<td style="text-align: left; width: 30%"> -->
<%-- 					<label for="resultadoNombre">${resultado.definicionResultado}</label> --%>
<!-- 				</td>				 -->
<!-- 			</tr> -->
			<tr>
				<th align="left" colspan="4"><br> Informacion de la Actividad</th>
			</tr>
<!--			<tr>-->
<!--				<td align="right">Tipo Actividad:</td>-->
<!--				<td align="left" colspan='3'>-->
<!--					<select id="tipoActividadId" name="tipoActividadId" style="width: 100px;">-->
<!--						<c:forEach items="${listaTipoActividad}" var="lista">  -->
<!--	 							<option value="${lista.tablaEspecificaID}">${lista.descripcionCabecera}</option>  -->
<!--						</c:forEach>-->
<!--					</select>-->
<!--				</td>-->
<!--			</tr>-->
			<tr>
				<td style="text-align: right; width: 20%"><label>Nombre Actividad:</label></td>
				<td style="text-align: left; width: 70%" colspan="3">
					<textarea rows="3" cols="75" id="nombreActividad" name="nombreActividad" style="width:95%;"></textarea>
				</td>
			</tr>
			<tr>
				<td style="text-align: right; width: 20%"><label>Descripcion Actividad:</label></td>
				<td style="text-align: left; width: 70%" colspan="3">
					<textarea rows="3" cols="75" id="descripcionActividad" name="descripcionActividad"  style="width:95%;"></textarea>
				</td>
			</tr>
			<tr>
				<td style="text-align: right; width: 20%"><label>Duracion(meses):</label></td>
				<td style="text-align: left; width: 70%" colspan="3">
					<input type="text" id="duracionMeses" name="duracionMeses" size="11" onkeypress="soloNumero(event)"  maxlength="2" style="width:65px;"/>
				</td>
			</tr>
			<tr>
				<th align="left" colspan="4"><br>Informacion de la Meta</th>
			</tr>
			<tr>
				<td style="text-align: right; width: 20%"><label>Unidad Medida:</label></td>
				<td style="text-align: left; width: 30%">
					<select id="unidadMedidaId" name="unidadMedidaId" style="width: 100px;">
						<option value="0">--Unidad--</option>
						<c:forEach items="${listaUnidadMedida}" var="lista">  
	 							<option value="${lista.tablaEspecificaID}">${lista.descripcionCabecera}</option>  
						</c:forEach>
					</select>
				</td>
				<td style="text-align: right; width: 20%"><label>Cantidad:</label></td>
				<td style="text-align: left; width: 30%">
					<input type="text" id="cantidadMetaActividad" name="cantidadMetaActividad" maxlength="5" onkeypress="soloNumero(event)" style="width: 55px"/>
				</td>
			</tr>
			<tr>
				<td colspan="4"> 
					<br>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="width: 50%"></td>
				<td style="text-align: right; width: 20%">
<%-- 					<input type="button" name="save" value="Regresar" onClick="goBack('showActividad.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID}');"> </td> --%>
				
				<td style="text-align: right; width: 30%">
					<input type="button" name="save" value="Grabar Actividad Meta" onClick="submitFormActividadMeta()">
				</td>
			</tr>
		</table>
		<br>
<!--     </fieldset> -->
</form:form>
</div>
</body>
</html>