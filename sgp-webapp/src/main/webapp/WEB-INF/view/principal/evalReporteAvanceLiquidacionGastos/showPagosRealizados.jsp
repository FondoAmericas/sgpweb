<%@ include file="/common/includesTaglibsGenerico.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
$(document).ready(function() {
	var estLiquidacionGastoPrefijo="<c:out value="${estLiquidacionGastoPrefijo }"></c:out>";
	
	if(estLiquidacionGastoPrefijo == 'apro'){
		$(".hide").hide();
	}
});
</script>

<script type="text/javascript">
function showPagoRealizado(pagoLiquidacionID){
	console.log("id pago liquidacion :: "+pagoLiquidacionID);
	fOpenModalDialog('showPagoRealizado.jspx?pagoLiquidacionID='+pagoLiquidacionID+'','800','1000','70','70');
	
}

</script>

</head>
<body>

<form id="detalleLiqGastosForm" method="post" action="#" class="form-clasico" accept-charset="UTF-8">
<input type="hidden" value="${objLiquidacionGasto.fkIdDetalleEstadoCabEstLiqGasto}" id="fkIdDetalleEstadoCabEstLiqGasto"> 
<table class="table-clasico" style="width: 100%">
		<caption>Listado de pagos realizados</caption>
		<thead>
        <tr>
        	<td style="text-align: center; width: 3%;"><label>Det</label></td>
        	<td style="text-align: center; width: 22%;"><label>Razon social</label></td>
        	<td style="text-align: center; width: 10%;"><label>Ruc proveedor</label></td>
            <td style="text-align: center; width: 12%;"><label>N°&amp; Tipo <br/>Comprobante</label></td>
			<td style="text-align: center; width: 13%;"><label>Fecha Emision</label></td>
			<td style="text-align: center; width: 10%;"><label>Sub <br/>Total</label></td>
			<td style="text-align: center; width: 10%;"><label>IGV</label></td>
			<td style="text-align: center; width: 10%;"><label>Total</label></td>
			<td style="text-align: center; width: 5%;" class="hide"><label>Opcion</label></td>
		 </tr>
        </thead>
        <c:forEach var="pagoLiq" items="${lstPagoLiquidacion}"  varStatus="indice">
        <c:choose>
					<c:when test="${indice.count %2== 0}">
						<c:set var="classIdi" value="f2"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="classIdi" value="f1"></c:set>
					</c:otherwise>
				</c:choose>
				<tr  class="<c:out value="${classIdi}"></c:out>">
	        	<td style="text-align: center; width: 3%;"><label><a
					href="javascript:expandcollapse('divPR<c:out value="${pagoLiq.pagoLiquidacionID }"></c:out>', 'one');">
						<img
						id='imgdivPR<c:out value="${pagoLiq.pagoLiquidacionID }"></c:out>'
						border="0" src="<c:url value="/images/Plus001.gif" />"
						width="15px" /> </a></label></td>
        	<td style="text-align: center; width: 22%;"><label><c:out value="${pagoLiq.razonSocial}"></c:out></label></td>
        	<td style="text-align: center; width: 10%;"><label><c:out value="${pagoLiq.rucProveedor}"></c:out></label></td>
            <td style="text-align: center; width: 12%;"><label><c:out value="${pagoLiq.tipoComprobantePagoDesc}"></c:out> - <c:out value="${pagoLiq.numeroDocumento}"></c:out></label></td>
			<td style="text-align: center; width: 13%;"><label><c:out value="${pagoLiq.fechaEmisionString}"></c:out></label></td>
			<td style="text-align: center; width: 10%;"><label><c:out value="${pagoLiq.montoTotal - pagoLiq.montoIgv }"></c:out> <c:out value="${pagoLiq.tipoMonedaDesc }"></c:out></label></td>
			<td style="text-align: center; width: 10%;"><label><c:out value="${pagoLiq.montoIgv }"></c:out> <c:out value="${pagoLiq.tipoMonedaDesc }"></c:out></label></td>
			<td style="text-align: center; width: 10%;"><label><c:out value="${pagoLiq.montoTotal }"></c:out> <c:out value="${pagoLiq.tipoMonedaDesc }"></c:out></label></td>
			<td style="text-align: center; width: 5%;" class="hide"><label><a	href="javascript:agregarObservacion('<c:out value="${pagoLiq.pagoLiquidacionID  }" ></c:out>','<c:out value="${pagoLiq.liquidacionGasto.datoProyecto.datoProyectoID }" ></c:out>','6','29','<c:out value="${pagoLiq.liquidacionGasto.liquidacionGastoID }" ></c:out>')"
														class="linkSelecciona">Observacion</a></label></td>
			</tr>
	        <tr class="<c:out value="${classIdi}"></c:out>">
				<td colspan="100%">
					<div
						id='divPR<c:out value="${pagoLiq.pagoLiquidacionID }"></c:out>'
						style="display: none; position: relative; overflow: auto; padding-left: 15px; padding-bottom: 15px; width: 97%;">
				<div><label>Factura Escaneada:</label><iframe src="showImagenArchivoDownloadPagoLiquidacion.jspx?pagoLiquidacionId=${pagoLiq.pagoLiquidacionID }"
											id="window" frameborder="0" height="40px" width="100%"
											scrolling="no"></iframe></div><br/>
				<legend><label>Detalle de Pago</label></legend>
				<table width="100%">
					<thead>
						<tr>
							<td style="text-align: center; width: 3%;"><label>Act</label>
							</td>
							<td style="text-align: center; width: 25%;"><label>Concepto</label>
							</td>
							<td style="text-align: center; width: 25%;"><label>Categoria Activo<br/>Bien/Activo</label>
							</td>
							<td style="text-align: center; width: 10%;"><label>Cantidad</label>
							</td>
							<td style="text-align: center; width: 10%;"><label>Precio<br/>Unitario</label>
							</td>
							<td style="text-align: center; width: 10%;"><label>Precio<br/>Total</label></td>
							<td style="text-align: center; width: 10%;" class="hide"><label>Opcion</label></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagoLiq.lstDetallePagoLiquidacion }"
							var="detallePagoLiquidacion" varStatus="indiceInt">
							<c:choose>
								<c:when test="${indiceInt.count %2== 0}">
									<c:set var="classIdiInt" value="f2int"></c:set>
								</c:when>
								<c:otherwise>
									<c:set var="classIdiInt" value="f1int"></c:set>
								</c:otherwise>
							</c:choose>
							<tr class="<c:out value="${classIdiInt}"></c:out>">
							<td style="text-align: center; width: 3%;"><label><a
					href="javascript:expandcollapse('divDPL<c:out value="${detallePagoLiquidacion.detallePagoLiquidacionID }"></c:out>', 'one');">
						<img
						id='imgdivDPL<c:out value="${detallePagoLiquidacion.detallePagoLiquidacionID }"></c:out>'
						border="0" src="<c:url value="/images/Plus001.gif" />"
						width="15px" /> </a></label>
							</td>
							<td style="text-align: center; width: 25%;"><label><c:out value="${detallePagoLiquidacion.concepto }"></c:out></label>
							</td>
							<td style="text-align: center; width: 25%;"><label><c:out value="${detallePagoLiquidacion.activo.descripcionCategoriaActivo }"></c:out><br/><c:out value="${detallePagoLiquidacion.activo.descripcionActivo }"></c:out></label>
							</td>
							<td style="text-align: center; width: 10%;"><label><c:out value="${detallePagoLiquidacion.cantidad }"></c:out></label>
							</td>
							<td style="text-align: center; width: 10%;"><label><c:out value="${detallePagoLiquidacion.precioUnitario }"></c:out>  <c:out value="${pagoLiq.tipoMonedaDesc }"></c:out></label>
							</td>
							<td style="text-align: center; width: 10%;"><label><c:out value="${detallePagoLiquidacion.precioTotal }"></c:out>  <c:out value="${pagoLiq.tipoMonedaDesc }"></c:out></label></td>
							<td style="text-align: center; width: 10%;" class="hide"><label><a href="javascript:agregarObservacion('<c:out value="${detallePagoLiquidacion.detallePagoLiquidacionID  }" ></c:out>','<c:out value="${pagoLiq.liquidacionGasto.datoProyecto.datoProyectoID }" ></c:out>','6','30','<c:out value="${pagoLiq.liquidacionGasto.liquidacionGastoID }" ></c:out>')"
														class="linkSelecciona">Observacion</a></label></td>
							</tr>
							<tr class="<c:out value="${classIdiInt}"></c:out>">
				<td colspan="100%">
					<div
						id='divDPL<c:out value="${detallePagoLiquidacion.detallePagoLiquidacionID }"></c:out>'
						style="display: none; position: relative; overflow: auto; padding-left: 15px; padding-bottom: 15px; width: 97%;">

					<legend><label>Actividades asociada al detalle de pago</label></legend>
					<table width="100%">
					<thead>
						<tr>
							<td style="text-align: center; width: 25%;"><label>Resultado</label>
							</td>
							<td style="text-align: center; width: 25%;"><label>Actividad</label>
							</td>
							<td style="text-align: center; width: 10%;"><label>Costo Actividad</label>
							</td>
							<td style="text-align: center; width: 10%;"><label>Costo de Periodo</label>
							</td>
							<td style="text-align: center; width: 10%;"><label>Monto Gastado</label></td>
							<td style="text-align: center; width: 10%;"><label>% Monto Total</label></td>
							<td style="text-align: center; width: 10%;" class="hide"><label>Opcion</label></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${detallePagoLiquidacion.lstActividadDetallePago }"
							var="actividadDetallePago" varStatus="indiceInt2">
							<c:choose>
								<c:when test="${indiceInt2.count %2== 0}">
									<c:set var="classIdiInt2" value="f2int2"></c:set>
								</c:when>
								<c:otherwise>
									<c:set var="classIdiInt2" value="f1int2"></c:set>
								</c:otherwise>
							</c:choose>
							<tr class="<c:out value="${classIdiInt2}"></c:out>">
							<td style="text-align: left; width: 25%;"><label><c:out value="${actividadDetallePago.cronogramaCostoActividad.costoActividad.actividad.resultado.definicionResultado }"></c:out></label>
							</td>
							<td style="text-align: left; width: 25%;"><label><c:out value="${actividadDetallePago.cronogramaCostoActividad.costoActividad.actividad.nombreActividad }"></c:out></label>
							</td>
							<td style="text-align: left; width: 10%;"><label>* <c:out value="${actividadDetallePago.cronogramaCostoActividad.costoActividad.categoriaActividad.descripcionCategoriaActividad }"></c:out><br/>
							* <c:out value="${actividadDetallePago.cronogramaCostoActividad.costoActividad.partidaGenerica.descripcionPartidaGenerica }"></c:out><br/>
							* <c:out value="${actividadDetallePago.cronogramaCostoActividad.costoActividad.partidaEspecifica.descripcionPartidaEspecifica }"></c:out><br/>
							* <c:out value="${actividadDetallePago.cronogramaCostoActividad.costoActividad.cantidadTotal }"></c:out> - <c:out value="${actividadDetallePago.cronogramaCostoActividad.costoActividad.descripcionUnidadMedida }"></c:out><br/>
							* PU: <c:out value="${actividadDetallePago.cronogramaCostoActividad.costoActividad.precioUnitario }"></c:out> - <c:out value="${actividadDetallePago.cronogramaCostoActividad.costoActividad.descripcionTipoMoneda }"></c:out></label>
							</td>
							<td style="text-align: center; width: 10%;"><label>* Periodo:  <c:out value="${actividadDetallePago.cronogramaCostoActividad.periodo }"></c:out><br/>* Cant.:  <c:out value="${actividadDetallePago.cronogramaCostoActividad.cantidad }"></c:out> - <c:out value="${actividadDetallePago.cronogramaCostoActividad.costoActividad.descripcionUnidadMedida }"></c:out><br/>
							* CT.:  <c:out value="${actividadDetallePago.cronogramaCostoActividad.cantidad * actividadDetallePago.cronogramaCostoActividad.costoActividad.precioUnitario}"></c:out> - <c:out value="${actividadDetallePago.cronogramaCostoActividad.costoActividad.descripcionTipoMoneda }"></c:out></label>
							</td>
							<td style="text-align: center; width: 10%;"><label><c:out value="${actividadDetallePago.montoGastado }"></c:out> - <c:out value="${actividadDetallePago.descripcionTipoMoneda }"></c:out></label></td>
							<td style="text-align: center; width: 10%;"><label><c:out value="${actividadDetallePago.porcentajeMontoTotal }"></c:out> %</label></td>
							<td style="text-align: center; width: 10%;" class="hide"><label><a href="javascript:agregarObservacion('<c:out value="${actividadDetallePago.actividadDetallePagoID  }" ></c:out>','<c:out value="${pagoLiq.liquidacionGasto.datoProyecto.datoProyectoID }" ></c:out>','6','31','<c:out value="${pagoLiq.liquidacionGasto.liquidacionGastoID }" ></c:out>')"
														class="linkSelecciona">Observacion</a></label></td>
							</tr>
							
						</c:forEach>
					</tbody>
				</table>
					</div>
				</td>
			</tr>						
						</c:forEach>
					</tbody>
				</table>
						</div>
					</td>
			</tr>	
		</c:forEach>
        </table>
</form>
</body>
</html>



                                                                                                                    
