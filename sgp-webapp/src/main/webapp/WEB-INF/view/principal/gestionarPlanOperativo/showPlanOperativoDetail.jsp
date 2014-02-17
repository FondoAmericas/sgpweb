<%@include file="includesTaglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><spring:message code="web.app.title"></spring:message></title>
	
	
	<script>
		function go(url) {
			window.location = url;
		}
		function removeResultado(url) {
			var isOK = confirm("Esta segura de eliminar?");
			if (isOK) {
				go(url);
			}
		}
		function submitFormAddIndicador() {
			document.formPlanOperativoDetalle.action = "actionAddIndicador.jspx";
			document.formPlanOperativoDetalle.submit();
		}
		function submitFormAddBeneficiario() {
			document.formPlanOperativoDetalle.action = "actionAddBeneficiario.jspx";
			document.formPlanOperativoDetalle.submit();
		}
		function submitFormAddCronograma() {
			document.formPlanOperativoDetalle.action = "actionAddCronograma.jspx";
			document.formPlanOperativoDetalle.submit();
		}
		function submitFormAddActividad() {
			document.formPlanOperativoDetalle.action = "actionAddActividad.jspx";
			document.formPlanOperativoDetalle.submit();
		}
	</script>
</head>
<body>
<div class="form-clasico">
<form:form name="formPlanOperativoDetalle" method="post" action="">
	<input type="hidden" id="resultadoID" name="resultadoID" value="${resultadoID}">
	<fieldset><legend>Detalle Plan Operativo</legend>
		<br>
		<fieldset><legend>Indicador Resultado</legend>
			<table class="table-clasico">
			<tr>
				<td align="right"><label>Tipo Indicador:</label></td>
				<td>
					<select id="tipoIndicadorId" name="tipoIndicadorId" style="width: 100px;">
						<c:forEach items="${listaTipoIndicadorResultado}" var="indicadorResultado">  
 							<option value="${indicadorResultado.tablaEspecificaID}">${indicadorResultado.descripcionCabecera}</option>  
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right"><label>Definicion del Indicador:</label></td>
				<td><textarea rows="2" cols="50" id="definicionIndicador" name="definicionIndicador"></textarea></td>
			</tr>
			<tr>
				<td align="right"><label>Unidad de Medida:</label></td>
				<td>
					<select id="unidadMedidaId" name="unidadMedidaId" style="width: 100px;">
						<c:forEach items="${listaUnidadMedida}" var="unidadMedida">  
 							<option value="${unidadMedida.tablaEspecificaID}">${unidadMedida.descripcionCabecera}</option>  
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right"><label>Medio de Verificacion:</label></td>
				<td><textarea rows="2" cols="50" id="medioVerificacion" name="medioVerificacion"></textarea></td>
			</tr>
			<tr>
				<td align="right"><label>Situacion Actual:</label></td>
				<td ><input type="text" id="situacionActual" name="situacionActual" style="width: 40px;"/></td>
			</tr>
			<tr>
				<td align="right"><label>Situacion Final:</label></td>
				<td><input type="text" id="situacionFinal" name="situacionFinal" style="width: 40px;"/></td>
			</tr>
			<tr>
				<td align="right"><label>Logro Alcanzado:</label></td>
				<td><input type="text" id="logroAlcanzado" name="logroAlcanzado" style="width: 40px;"/>
			</tr>
			<tr>
				<td align="right"><label>Observacion:</label></td>
				<td><textarea rows="2" cols="50" id="observacion" name="observacion"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" name="addResult" value="Agregar Indicador" onClick="submitFormAddIndicador()">
				</td>
			</tr>
			<tr>
				<td colspan="2">
				</td>
			</tr>
		</table>
		<br>
		<table class="table-clasico">
			<caption>Indicadores Resultados</caption>
			<thead>
			<tr>
				<th>Tipo Indicador</th>
                <th>Definicion Indicador</th>
                <th>Unidad de Medida</th>
				<th>Medio Verificacion</th>
                <th>Situacion Actual</th>
                <th>Situacion Final</th>
                <th>Logro Alcanzado</th>
                <th>Observacion</th>
                <th>Accion</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${empty listIndicadorResultado}">
				<tr>
					<td colspan="7">No Results found</td>
				</tr>
			</c:if>
			<c:if test="${!empty listIndicadorResultado}">
				<c:forEach var="indicadorResultado" items="${listIndicadorResultado}">		
	    			<tr>
						<td><c:out value="${indicadorResultado.tipoIndicador}"></c:out></td>
						<td><c:out value="${indicadorResultado.definicionIndicador}"></c:out></td>
						<td><c:out value="${indicadorResultado.unidadMedida}"></c:out> </td>
						<td><c:out value="${indicadorResultado.medioVerificacion}"></c:out></td>
						<td><c:out value="${indicadorResultado.situacionActual}"></c:out></td>
						<td><c:out value="${indicadorResultado.situacionFinal}"></c:out></td>
						<td><c:out value="${indicadorResultado.logroAlcanzado}"></c:out></td>
						<td><c:out value="${indicadorResultado.observacion}"></c:out></td>
						<td>
							<a href="javascript:removeIndicadorResultado('removeIndicadorResultado.jspx?indicadorResultadoID=${indicadorResultado.indicadorResultadoID}');">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			</tbody>
		</table>
		<br>
		</fieldset>
		<br>
		<fieldset><legend>Beneficiario Resultado</legend>
			<table class="table-clasico">
			<tr>
				<td align="right"><label>Tipo Beneficiario:</label></td>
				<td>
					<select id="estratoId" name="estratoId" style="width: 100px;">
						<c:forEach items="${listaTipoBeneficiario}" var="tipoBeneficiario">  
 							<option value="${tipoBeneficiario.tablaEspecificaID}">${tipoBeneficiario.descripcionCabecera}</option>  
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right"><label>Caracteristicas Poblacion:</label></td>
				<td><textarea rows="2" cols="50" id="definicionResultado" name="definicionResultado"></textarea></td>
			</tr>
			<tr>
				<td align="right"><label>Cantidad Programado:</label></td>
				<td ><input type="text" id="metaResultado" name="metaResultado" style="width: 40px;"/></td>
			</tr>
			<tr>
				<td align="right"><label>Estrato:</label></td>
				<td>
					<select id="estratoId" name="estratoId" style="width: 100px;">
						<c:forEach items="${listaEstratos}" var="estrato">  
 							<option value="${estrato.tablaEspecificaID}">${estrato.descripcionCabecera}</option>  
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right"><label>Descripcion:</label></td>
				<td><textarea rows="2" cols="50" id="definicionResultado" name="definicionResultado"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" name="addResult" value="Agregar Beneficiario" onClick="submitFormAddBeneficiario()">
				</td>
			</tr>
			<tr>
				<td colspan="2">
				</td>
			</tr>
		</table>
		<br>
		<table class="table-clasico">
			<caption>Beneficiarios Resultados</caption>
			<thead>
			<tr>
				<th>Tipo Beneficiario</th>
                <th>Caracteristicas Poblacion</th>
                <th>Cantidad Programado</th>
				<th>Estrato</th>
                <th>Descripcion</th>
                <th>Accion</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${empty listIndicadorResultado}">
				<tr>
					<td colspan="7">No Results found</td>
				</tr>
			</c:if>
			<c:if test="${!empty listIndicadorResultado}">
				<c:forEach var="indicadorResultado" items="${listIndicadorResultado}">		
	    			<tr>
						<td><c:out value="${indicadorResultado.tipoIndicador}"></c:out></td>
						<td><c:out value="${indicadorResultado.definicionIndicador}"></c:out></td>
						<td><c:out value="${indicadorResultado.unidadMedida}"></c:out> </td>
						<td><c:out value="${indicadorResultado.medioVerificacion}"></c:out></td>
						<td><c:out value="${indicadorResultado.situacionActual}"></c:out></td>
						<td><c:out value="${indicadorResultado.situacionFinal}"></c:out></td>
						<td><c:out value="${indicadorResultado.logroAlcanzado}"></c:out></td>
						<td><c:out value="${indicadorResultado.observacion}"></c:out></td>
						<td>
							<a href="javascript:removeIndicadorResultado('removeIndicadorResultado.jspx?indicadorResultadoID=${indicadorResultado.indicadorResultadoID}');">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			</tbody>
		</table>
		<br>
		</fieldset>
		<br>
		<fieldset><legend>Cronograma Meta Resultado</legend>
			<table class="table-clasico">
			<tr>
				<td align="right"><label>Avanze Meta:</label></td>
				<td><input type="text" id="metaResultado" name="metaResultado" style="width: 40px;"/>
			</tr>
			<tr>
				<td align="right"><label>Periodo:</label></td>
				<td><textarea rows="2" cols="50" id="definicionResultado" name="definicionResultado"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" name="addResult" value="Agregar Cronograma" onClick="submitFormAddCronograma()">
				</td>
			</tr>
			<tr>
				<td colspan="2">
				</td>
			</tr>
		</table>
		<br>
		<table class="table-clasico">
			<caption>Metas Resultados</caption>
			<thead>
			<tr>
				<th>Avanze Meta</th>
                <th>Periodo</th>
                <th>Accion</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${empty listCronogramaMetaResultado}">
				<tr>
					<td colspan="7">No Results found</td>
				</tr>
			</c:if>
			<c:if test="${!empty listCronogramaMetaResultado}">
				<c:forEach var="cronogramaMetaResultado" items="${listCronogramaMetaResultado}">		
	    			<tr>
						<td><c:out value="${cronogramaMetaResultado.avanzeMeta}"></c:out></td>
						<td><c:out value="${cronogramaMetaResultado.periodo}"></c:out></td>
						<td>
							<a href="javascript:removeIndicadorResultado('removeIndicadorResultado.jspx?indicadorResultadoID=${indicadorResultado.indicadorResultadoID}');">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			</tbody>
		</table>
		<br>
		</fieldset>
		<br>
		<fieldset><legend>Actividad</legend>
			<table class="table-clasico">
			<tr>
				<td align="right"><label>Tipo Actividad:</label></td>
				<td>
					<select id="estratoId" name="estratoId" style="width: 100px;">
						<c:forEach items="${listaTipoActividad}" var="tipoActividad">  
 							<option value="${tipoActividad.tablaEspecificaID}">${tipoActividad.descripcionCabecera}</option>  
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right"><label>Nombre Actividad:</label></td>
				<td><textarea rows="2" cols="50" id="definicionResultado" name="definicionResultado"></textarea></td>
			</tr>
			<tr>
				<td align="right"><label>Descripcion Actividad:</label></td>
				<td><textarea rows="2" cols="50" id="definicionResultado" name="definicionResultado"></textarea></td>
			</tr>
			<tr>
				<td align="right"><label>Duracion Meses:</label></td>
				<td ><input type="text" id="metaResultado" name="metaResultado" style="width: 40px;"/></td>
			</tr>
			<tr>
				<td align="right"><label>Actividad Transaferencia:</label></td>
				<td>
					<select id="estratoId" name="estratoId" style="width: 100px;">
						<c:forEach items="${listaActividadTransferencia}" var="actividadTransferencia">  
 							<option value="${actividadTransferencia.tablaEspecificaID}">${actividadTransferencia.descripcionCabecera}</option>  
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" name="addResult" value="Agregar Actividad" onClick="submitFormAddActividad()">
				</td>
			</tr>
			<tr>
				<td colspan="2">
				</td>
			</tr>
		</table>
		<br>
		<table class="table-clasico">
			<caption>Actividades</caption>
			<thead>
			<tr>
				<th>Tipo Actividad</th>
                <th>Nombre Actividad</th>
                <th>Descripcion Actividad</th>
				<th>Duracion Meses</th>
                <th>Actividad Transaferencia</th>
                <th>Accion</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${empty listActividades}">
				<tr>
					<td colspan="7">No Results found</td>
				</tr>
			</c:if>
			<c:if test="${!empty listActividades}">
				<c:forEach var="actividades" items="${listActividades}">		
	    			<tr>
						<td><c:out value="${actividades.tipoActividad}"></c:out></td>
						<td><c:out value="${actividades.nombreActividad}"></c:out></td>
						<td><c:out value="${actividades.descripcionActividad}"></c:out> </td>
						<td><c:out value="${actividades.duracionMeses}"></c:out></td>
						<td><c:out value="${actividades.actividadTransaferencia}"></c:out></td>
						<td>
							<a href="javascript:removeIndicadorResultado('removeIndicadorResultado.jspx?indicadorResultadoID=${indicadorResultado.indicadorResultadoID}');">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			</tbody>
		</table>
		<br>
		</fieldset>
		<br>
	</fieldset>
	<br>
	<c:if test="${!empty alterMessage}">
		<fieldset><legend>Mensajes de Alertas</legend>
			<textarea rows="1" cols="125"></textarea> 
		</fieldset>
	</c:if>
	<br>
</form:form>
</div>
</body>
</html>
