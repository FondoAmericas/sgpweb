<%@include file="includesTaglibs.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="web.app.title"></spring:message></title>
	<script type="text/javascript">
	$(window).load(function() {
		////UBICACION DEL PROYECTO
		ocultaCampos();
	});
	
	function ocultaCampos(){
		estado= "<c:out value="${estadoPlanOperativo}"></c:out>" ;
		//alert(estado);
		if ((estado =='peval')||(estado =='revi')||(estado =='apro')||(estado =='recha')){
			$(".hide").hide();//attr("style","visibility: hidden;");
			//alert($('#cbxInstitucionEjecutora option:selected').html());
			//$("#div_cbxInstitucionEjecutora").attr("style","visibility: hidden;");
		}
		
	}
	</script>
<script>
function goTo(url) {
	//window.location = url;
	fOpenModalDialog(url,'680','500','140','140');
}
function goBack(url) {
	goTo(url);
}
function fRefresh(tiempo){
	fSetTimeOutRefreshLocation(tiempo);
	//location.reload();
}

</script>
</head>
<body style="font-family: Arial; font-size: smaller;">
<div class="form-clasico">
<div class="encabezado">Cronograma de Meta por Actividad</div>
		<br>
	<form>
		
			<fieldset>
				<legend>Cronograma Meta Actividad</legend>
				<!-- 		<br> -->
				<table border="0" style="width: 100%;">
					<tr>
						<td style="text-align: right; width: 25%; vertical-align: top;"><label>Proyecto:</label>
						</td>
						<td style="text-align: left; width: 75%" colspan="3"><label
							for="proyectoNombre">${planOperativo.nombreProyecto}</label>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%"><label>Codigo
								Proyecto:</label></td>
						<td style="text-align: left; width: 25%"><label
							for="proyectoCodigo">${planOperativo.codigoProyecto}</label>
						</td>
						<td style="text-align: right; width: 25%"><label>Version
								PO:</label></td>
						<td style="text-align: left; width: 25%"><label
							for="planOperativo">${planOperativo.version}</label>
						</td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%"><label>Resultado:</label>
						</td>
						<td style="text-align: left; width: 75%" colspan="3"><label
							for="resultadoNombre">${resultado.definicionResultado}</label>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%"><label>Actividad:</label>
						</td>
						<td style="text-align: left; width: 75%" colspan="3"><label
							for="resultadoNombre">${actividad.nombreActividad}</label>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%"><label>Tipo
								Actividad:</label></td>
						<td style="text-align: left; width: 25%"><label
							for="resultadoNombre">${actividad.tipoActividadNombre}</label>
						</td>
						<td style="text-align: right; width: 25%"><label>Cantidad:</label>
						</td>
						<td style="text-align: left; width: 25%"><label
							for="cantidad">${metaActivdad.cantidadMetaActividad} -
								${metaActivdad.unidadMedidaNombre}</label>
						</td>
					</tr>
					<tr>
					<td style="text-align: right; width: 25%; vertical-align: top">
					    <label>Tipo de Periodo:</label>
					</td>
					<td colspan="3" style="text-align: left; width: 75%; vertical-align: top">
					    <label>${planOperativo.datoProyecto.programa.tipoPeriodo.descripPeriodo }</label>
					</td>
				</tr>
					<tr>
						<td style="text-align: right; width: 100%" colspan="4"><br>
							<input type="button" value="Crear Cronograma Meta Actividad" class="hide"
							onClick="goTo('createCronogramaMetaActividad.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID}&actividadID=${actividad.actividadID}&metaActividadID=${metaActivdad.metaActividadID}');">
							<input type="button" value="Cerrar" id="idBtnCerrar" />
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 100%" colspan="4">
							<table class="table-clasico" width="100%">
								<caption>Cronograma Meta Actividad</caption>
								<thead>
									<tr>
										<th rowspan="3" colspan="3" align="center"><label>Cantidad</label>
										</th>
										<th colspan="${cantidadPeridoX2}" align="center"><label>Cronograma
												por Periodos</label></th>
									</tr>
									<tr>
										<c:forEach var="periodo" items="${periodos}">
											<th align="center"><label><c:out
														value="${periodo}"></c:out> </label></th>
										</c:forEach>
									</tr>
									<tr>
										<c:forEach var="periodo" items="${periodos}">
											<th align="center"><label><c:out
														value="Cantidad"></c:out> </label></th>
										</c:forEach>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="cronogramaMetaActividad"
										items="${listCronogramaMetaActividadView}" varStatus="indice">
										<c:choose>
											<c:when test="${indice.count %2== 0}">
												<c:set var="classIdi" value="f1"></c:set>
											</c:when>
											<c:otherwise>
												<c:set var="classIdi" value="f2"></c:set>
											</c:otherwise>
										</c:choose>
										<tr class="<c:out value="${classIdi}"></c:out>">
											<td align="center" colspan="3"><c:out
													value="${cronogramaMetaActividad.cantidadTotal}"></c:out></td>
											<c:forEach var="periodo"
												items="${cronogramaMetaActividad.listPeriodo}">
												<td align="center"><c:out
														value="${periodo.cantidadPeriodo=='0'?'-':periodo.cantidadPeriodo}"></c:out></td>
											</c:forEach>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="2" align="center">Total</td>
										<td align="center">${totalesCantidadTotal}</td>
										<c:forEach var="periodo" items="${periodos}">
											<td align="center"><c:out value=""></c:out></td>
										</c:forEach>
									</tr>
								</tfoot>
							</table>
						</td>
					</tr>
				</table>
			</fieldset>
		
	</form>
	</div>
</body>
</html>
