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

</head>
<body>

<form id="detalleLiqGastosForm" method="post" action="#" class="form-clasico" accept-charset="UTF-8">
<input type="hidden" value="${objLiquidacionGasto.fkIdDetalleEstadoCabEstLiqGasto}" id="fkIdDetalleEstadoCabEstLiqGasto"> 
<table class="table-clasico" style="width: 100%">
		<caption>Lista de compromisos de pago</caption>
		<thead>
        <tr>
        	<td align="center"><label>Periodo</label></td>
        	<td align="center"><label>Cantidad</label></td>
            <td align="center"><label>Monto Compromiso</label></td>
			<td align="center"><label>Fuente Financiadora</label></td>
			<td align="center" class="hide"><label>Opcion</label></td>
		 </tr>
        </thead>
        <c:forEach var="compromisoPagoG" items="${listCompromisoPago}"  varStatus="indice">
        <c:choose>
					<c:when test="${indice.count %2== 0}">
						<c:set var="classIdi" value="f2"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="classIdi" value="f1"></c:set>
					</c:otherwise>
				</c:choose>
				<tr  class="<c:out value="${classIdi}"></c:out>">
	        	<td align="center"><label><c:out value="${compromisoPagoG.cronogramaCostoActividad.periodo}"></c:out></label>
	        	</td>
				<td align="center"><label><c:out value="${compromisoPagoG.cronogramaCostoActividad.cantidad}"></c:out>&nbsp;
				<c:forEach items="${listUnidadMedida}" var="modali">
            		<c:if test="${ compromisoPagoG.cronogramaCostoActividad.costoActividad.fkIdtablaespUnidadMedida == modali.tablaEspecificaID}"> 
                        <c:out value="${modali.descripcionCabecera}" />
            		</c:if>
        		</c:forEach></label>
				</td>
				<td align="center"><label><c:out value="${compromisoPagoG.montoCompromiso}"></c:out>
				  &nbsp;
				  <c:forEach items="${listTipoMoneda}" var="modali1">
                       <c:if	test="${ compromisoPagoG.cronogramaCostoActividad.costoActividad.fkIdtablaespTipoMoneda==modali1.tablaEspecificaID }">
            				<c:out value="${modali1.descripcionCabecera}" />
                        </c:if>
                    </c:forEach></label>
				</td>
				<td align="center"><label><c:out value="${compromisoPagoG.cronogramaCostoActividad.fuenteFinanciadora.institucion.nombreInstitucion}"></c:out></label></td>
				<td align="center" class="hide"><label><a	href="javascript:agregarObservacion('<c:out value="${compromisoPagoG.compromisoPagoID }" ></c:out>','<c:out value="${liquidacionGasto.datoProyecto.datoProyectoID }" ></c:out>','6','27','<c:out value="${liquidacionGastoID}" ></c:out>')"
														class="linkSelecciona">Observacion</a></label></td>
			</tr>
        </c:forEach>
</table>
</form>
</body>
</html>