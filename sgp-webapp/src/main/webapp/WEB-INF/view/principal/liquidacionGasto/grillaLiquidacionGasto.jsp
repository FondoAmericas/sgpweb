<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.multiselect.css" />

<script type="text/javascript">
function guardarEstadoLiquidacionGasto(liquidacionGastoId){
	if($("#sltEstado"+liquidacionGastoId).val()!=0){
	var confirmacion = confirm("Seguro que desea mandar la liquidacion a ser evaluado por Fondam? \n ADVERTENCIA: Una vez enviado no se podran hacer cambios.");
	
	if(confirmacion == true){
		var estadoId = $("#sltEstado"+liquidacionGastoId).val();
		
		$.get("grabarEstadoLiquidacionGastos.jspx", {
			sltEstadoLiquidacionGasto : estadoId,
			liquidacionGastoId : liquidacionGastoId
		}, function() {
			$('#divGrillaLiquidacionGasto').load(
					"showGrillaLiquidacionGastoByFuenteFinanciadora.jspx?fuenteFinanciadoraID="+$('#fuenteFinanciadora').attr("value"));
		});
	}
	}else if($("#sltEstado"+liquidacionGastoId).val()==0){
		$('#divGrillaLiquidacionGasto').load(
				"showGrillaLiquidacionGastoByFuenteFinanciadora.jspx?fuenteFinanciadoraID="+$('#fuenteFinanciadora').attr("value"), function(){
					alert("Seleccionar el estado a grabar.");
				})
	}
}

</script>


<div class="form-clasico" >
<br/>

<form:form  name="formCrearLiquidacionGastos"	action="actionCrearLiquidacionGastos.jspx" method="POST" >

<table border="0" width="100%" class="table-clasico">
	<caption>Lista de Liquidaciones de Gastos</caption>
	<thead>
		<tr>
			<td style="width: 5%; text-align: center;"><label>Detalle</label></td>
			<td style="width: 7%; text-align: center;"><label>Codigo<br/>Version</label></td>
			<td style="width: 8%; text-align: center;"><label>Periodo</label></td>
			<td style="width: 30%; text-align: center;"><label>Fuente<br/>Financiadora</label></td>
			<td style="width: 16%; text-align: center;"><label>Estado<br/>Liquidacion</label></td>
			<td style="width: 12%; text-align: center;"><label>Fecha Inicio<br/>(dd/MM/aaaa)</label></td>
			<td style="width: 12%; text-align: center;"><label>Fecha Fin<br/>(dd/MM/aaaa)</label></td>
			<td style="width: 10%; text-align: center;"><label>Operaciones</label>
			</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listLiquidacionGasto }" var="liquidacionGasto"
			varStatus="indice">
			<c:choose>
				<c:when test="${indice.count %2== 0}">
					<c:set var="classIdi" value="f1"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="classIdi" value="f2"></c:set>
				</c:otherwise>
			</c:choose>
			<tr class="<c:out value="${classIdi}"></c:out>">
				<td style="width: 5%; text-align: center;"><a
					href="javascript:expandcollapse('div<c:out value="${liquidacionGasto.liquidacionGastoID }"></c:out>', 'one');">
						<img
						id='imgdiv<c:out value="${liquidacionGasto.liquidacionGastoID }"></c:out>'
						border="0" src="<c:url value="/images/Plus001.gif" />"
						width="15px" /> </a></td>
				<td style="width: 7%; text-align: center;"><label><c:out
							value="${liquidacionGasto.codVersion }"></c:out>
				</label>
				</td>
				<td style="width: 8%; text-align: center;"><label><c:out
							value="${liquidacionGasto.periodo }"></c:out>
				</label>
				</td>
				<td style="width: 30%; text-align: center;"><label><c:out
							value="${liquidacionGasto.fuenteFinanciadora.institucion.nombreInstitucion }"></c:out>
				</label>
				</td>
				<td style="width: 16%; text-align: center;"><label>
				<c:choose>
					<c:when test="${liquidacionGasto.prefijoEstadoLiqGasto=='liqgen' }">
						<select
			name="sltEstado<c:out value="${liquidacionGasto.liquidacionGastoID }"></c:out>" 
			id="sltEstado<c:out value="${liquidacionGasto.liquidacionGastoID }"></c:out>" 
			onchange="guardarEstadoLiquidacionGasto(<c:out value="${liquidacionGasto.liquidacionGastoID }"></c:out>)">
				<option value="0">
					<c:out value="-- Estados --" />
				</option>
				<c:forEach items="${lstDetEstCabFinal}" var="estado">
					<option value="${estado.detalleEstadoCabeceraID}" title="${estado.prefijoEstado}" 
					 	<c:if test="${estado.detalleEstadoCabeceraID == liquidacionGasto.fkIdDetalleEstadoCabEstLiqGasto }"> selected="selected" </c:if> >
						<c:out value="${estado.descripEstado}" />
					</option>
				</c:forEach>
		</select>
					</c:when>
					<c:otherwise>
						<c:out
							value="${liquidacionGasto.estLiqGastoDesc }"></c:out>
					</c:otherwise>	
				</c:choose>
				</label></td>
				<td style="width: 12%; text-align: center;"><label><c:out
							value="${liquidacionGasto.fechaInicioString }"></c:out>
				</label>
				</td>
				<td style="width: 12%; text-align: center;"><label><c:out
							value="${liquidacionGasto.fechaFinString }"></c:out>
				</label>
				</td>
				<td style="width: 10%; text-align: center;"><label>
				<c:choose>
					<c:when test="${liquidacionGasto.prefijoEstadoLiqGasto !='apro' && liquidacionGasto.prefijoEstadoLiqGasto !='eval'}">
					<a href="javascript:fEliminar('${liquidacionGasto.liquidacionGastoID}')"  style="cursor:pointer" class="linkSelecciona" title="Eliminar">Eliminar</a>
					</c:when>
					<c:otherwise>-----</c:otherwise>
				</c:choose>
				</label>
				</td>
			</tr>
			<tr class="<c:out value="${classIdi}"></c:out>">
				<td colspan="100%">
					<div
						id='div<c:out value="${liquidacionGasto.liquidacionGastoID }"></c:out>'
						style="display: none; position: relative; overflow: auto; padding-left: 15px; padding-bottom: 15px; width: 97%;">

						<table>
							<tbody>
								<tr>
									<td style="width: 10%; text-align: center;"><label><a href="javascript:fOpenModalDialog('showIngresoPropio.jspx?liquidacionGastoID=${liquidacionGasto.liquidacionGastoID}&fuenteFinanciadora=${liquidacionGasto.fuenteFinanciadora.institucion.nombreInstitucion }','900','650','70','70')"  class="linkSelecciona" style="cursor:pointer" title="Ingreso Propio">Ingresos<br/>Propios</a>
									</label>
									</td>
									<td style="width: 10%; text-align: center;"><label><a href="javascript:fOpenModalDialog('showCompromisoPago.jspx?liquidacionGastoID=${liquidacionGasto.liquidacionGastoID}&fuenteFinanciadora=${liquidacionGasto.fuenteFinanciadora.institucion.nombreInstitucion }','850','600','80','80')" class="linkSelecciona" style="cursor:pointer" title="Compromiso Pago">Compromisos<br/>de Pagos</a>
									</label>
									</td>
									<td style="width: 10%; text-align: center;"><label><a href="javascript:fOpenModalDialog('showIngresoPago.jspx?liquidacionGastoID=${liquidacionGasto.liquidacionGastoID}&fuenteFinanciadora=${liquidacionGasto.fuenteFinanciadora.institucion.nombreInstitucion }','950','700','40','40')" class="linkSelecciona" style="cursor:pointer" title="Facturas Pagos">Facturas<br/>Pagos</a>
									</label>
									</td>
									<td style="width: 10%; text-align: center;"><label><a href="javascript:fOpenModalDialog('cargarFormulario.jspx?liquidacionGastoID=${liquidacionGasto.liquidacionGastoID}&fuenteFinanciadora=${liquidacionGasto.fuenteFinanciadora.institucion.nombreInstitucion }','950','700','40','40')" class="linkSelecciona" style="cursor:pointer" title="Cargar Formulario">Cargar<br/>Formulario</a>
									</label>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</form:form>
</div>