<%@include file="includesTaglibs.jsp"%>

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
	
	function goTo(url,ancho,alto) {
		//window.location = url;
		fOpenModalDialog(url,ancho,alto,'100','100');
	}
	
	function fRefresh(tiempo){
		fSetTimeOutRefreshLocation(tiempo);
		//location.reload();
	}
	</script>
</head>
<body style="font-family: Arial; font-size: smaller;">
	<form>
		<div class="form-clasico">
		<div class="encabezado">Cronograma de Costo por Actividad</div>
		<br>
			<fieldset>
				<legend>Cronograma Costo Actividad</legend>
				<!-- 		<br> -->
				<table border="0" width="100%">
					<tr>
						<td style="width: 25%; text-align: right; vertical-align: top;"><label>Proyecto:</label>
						</td>
						<td style="width: 75%; text-align: left;" colspan="3"><label
							for="proyectoNombre">${planOperativo.nombreProyecto}</label>
						</td>
					</tr>
					<tr>
						<td style="width: 25%; text-align: right;"><label>Codigo
								Proyecto:</label></td>
						<td style="width: 25%; text-align: letf;"><label
							for="proyectoCodigo">${planOperativo.codigoProyecto}</label>
						</td>
						<td style="width: 25%; text-align: right;"><label>Version
								PO:</label></td>
						<td style="width: 25%; text-align: left;"><label
							for="planOperativo">${planOperativo.version}</label>
						</td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td style="width: 25%; text-align: right;"><label>Resultado:</label>
						</td>
						<td style="width: 75%; text-align: left;" colspan="3"><label
							for="resultadoNombre">${resultado.definicionResultado}</label>
						</td>
					</tr>
					<tr>
						<td style="width: 25%; text-align: right;"><label>Actividad:</label>
						</td>
						<td style="width: 25%; text-align: left;" colspan="3"><label
							for="resultadoNombre">${actividad.nombreActividad}</label>
						</td>
					</tr>
					<tr>
						<td style="width: 25%; text-align: right;"><label>Tipo
								Actividad:</label></td>
						<td style="width: 25%; text-align: left;"><label
							for="resultadoNombre">${costoActividad.descripcionCategoriaActividad}</label>
						</td>
						<td style="width: 25%; text-align: right;"><label>Partida
								Generica:</label></td>
						<td style="width: 25%; text-align: left;"><label
							for="resultadoNombre">${costoActividad.descripcionPartidaGenerica}</label>
						</td>
					</tr>
					<tr>
						<td style="width: 25%; text-align: right;"><label>Partida
								Especifica:</label></td>
						<td style="width: 25%; text-align: left;"><label
							for="resultadoNombre">${costoActividad.descripcionPartidaEspecifica}</label>
						</td>
						<td style="width: 25%; text-align: right;"><label>Detalle
								Partida:</label></td>
						<td style="width: 25%; text-align: left;"><label
							for="resultadoNombre">${costoActividad.detallePartidaGenerica}</label>
						</td>
					</tr>
					<tr>
						<td style="width: 25%; text-align: right;"><label>Cantidad:</label>
						</td>
						<td style="width: 25%; text-align: left;" colspan="3"><label
							for="unidad">${costoActividad.cantidadTotal} -
								${costoActividad.unidadMedidaNombre}</label>
						</td>
					</tr>
					<tr>
						<td style="width: 25%; text-align: right;"><label>Precio
								Unitario:</label></td>
						<td style="width: 25%; text-align: left;" colspan="3"><label
							for="precio">${costoActividad.precioUnitario}
								${costoActividad.tipoMonedaPrecioUnitarioNombre}</label>
						</td>
					</tr>
					<tr>
						<td style="width: 25%; text-align: right;"><label>Costo
								Total:</label></td>
						<td style="width: 25%; text-align: left;" colspan="3"><label
							for="costoTotal">${costoActividad.montoTotal}
								${costoActividad.tipoMonedaPrecioUnitarioNombre}</label>
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
						<td style="width: 100%;" colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td style="width: 100%; text-align: right;" colspan="4"><input
							type="button" value="Crear Cronograma Costo Actividad"
							class="hide"
							onClick="goTo('createCronogramaCostoActividad.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID}&actividadID=${actividad.actividadID}&costoActividadID=${costoActividad.costoActividadID}',800,500);">
							<input type="button" value="Cerrar" id="idBtnCerrar" />
							</td>
					</tr>
					<tr>
						<td style="width: 100%;" colspan="4">
							<table class="table-clasico" style="width: 100%;">
								<caption>Cronograma Costo Actividad</caption>
								<thead>
									<tr>
										<th rowspan="3" align="center"><label>Fuente
												Financ.</label></th>
										<th rowspan="3" align="center"><label>Partida
												Especifica</label></th>
										<th rowspan="3" align="center"><label>Cantidad</label></th>
										<th rowspan="3" align="center"><label>Precio<br>Unitario</label>
										</th>
										<th rowspan="3" align="center"><label>Costo Total</label>
										</th>
										<th colspan="${cantidadPeridoX2}" align="center"><label>Cronograma
												por Periodos</label></th>
									</tr>
									<tr>
										<c:forEach var="periodo" items="${periodos}">
											<th colspan="2" align="center"><label><c:out
														value="${periodo}"></c:out> </label></th>
										</c:forEach>
									</tr>
									<tr>
										<c:forEach var="periodo" items="${periodos}">
											<th align="center"><label><c:out value="Cant."></c:out>
											</label></th>
											<th align="center"><label><c:out value="Monto"></c:out>
											</label></th>
										</c:forEach>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="cronogramaCostoActividad"
										items="${listCronogramaCostoActividad}" varStatus="indice">
										<c:choose>
											<c:when test="${indice.count %2== 0}">
												<c:set var="classIdi" value="f1"></c:set>
											</c:when>
											<c:otherwise>
												<c:set var="classIdi" value="f2"></c:set>
											</c:otherwise>
										</c:choose>
										<tr class="<c:out value="${classIdi}"></c:out>">
											<td align="left"><c:out
													value="${cronogramaCostoActividad.nombreInstitucion}"></c:out>
											</td>
											<td align="left"><c:out
													value="${cronogramaCostoActividad.descripcionPartidaEspecifica}"></c:out>
											</td>
											<td align="center"><c:out
													value="${cronogramaCostoActividad.cantidadTotal}"></c:out>
											</td>
											<td align="right"><c:out
													value="${cronogramaCostoActividad.precioUnitario}"></c:out>
											</td>
											<td align="right"><c:out
													value="${cronogramaCostoActividad.costoTotal}"></c:out></td>
											<c:forEach var="periodo"
												items="${cronogramaCostoActividad.listPeriodo}">
												<td align="center"><c:out
														value="${periodo.cantidadPeriodo=='0'?'-':periodo.cantidadPeriodo}"></c:out>
												</td>
												<td align="right"><c:out
														value="${periodo.montoPerido=='0.0'?'-':periodo.montoPerido}"></c:out>
												</td>
											</c:forEach>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="2" align="center">Totales</td>
										<td align="center">${totalesCantidadTotal}</td>
										<td align="center"></td>
										<td align="right">${totalesCostoTotal}</td>
										<c:forEach var="periodo" items="${periodos}">
											<td align="center"><c:out value=""></c:out></td>
											<td align="center"><c:out value=""></c:out></td>
										</c:forEach>
									</tr>
								</tfoot>
							</table>
						</td>
					</tr>
				</table>
				<br />
			</fieldset>
		</div>
	</form>
</body>
</html>
