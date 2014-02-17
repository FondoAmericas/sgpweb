<%@ include file="/common/taglibs.jsp"%>

<script>
var arrayProyectos = new Array();

</script>

<table border="0" width="100%" class="table-clasico">
	<caption>Listado proyectos del programa</caption>
	<thead>
		<tr>
			<td style="width: 5%; text-align: center;"><label>Prop.<br>Inf.</label></td>
			<td style="width: 55%; text-align: center;"><label>Proyecto</label></td>
			<td style="width: 20%; text-align: center;"><label>Cod.Proyecto</label></td>

			<td style="width: 5%; text-align: center;"><label>Duracion</label></td>
			<td style="width: 5%; text-align: center;"><label>Cant.Periodos</label></td>
			    
		</tr>
	</thead>

	  <tbody>
			<c:forEach items="${lstProyectosPorPrograma }" var="proyecto" varStatus="indice">
				<c:choose>
					<c:when test="${indice.count %2== 0}">
						<c:set var="classIdi" value="f2"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="classIdi" value="f1"></c:set>
					</c:otherwise>
				</c:choose>
				
				<tr  class="<c:out value="${classIdi}"></c:out>">
						<td style="width: 10%; text-align: center;">
						<a
					href="javascript:expandcollapse('divPTIF<c:out value="${proyecto.datoProyectoID }"></c:out>', 'one');">
						<img
						id='imgdivPTIF<c:out value="${proyecto.datoProyectoID }"></c:out>'
						border="0" src="<c:url value="/images/Plus001.gif" />"
						width="15px" /> </a>
						</td>
						<td style="width: 10%; text-align: center;">
							<label><c:out value="${proyecto.nombreProyecto }"></c:out></label>
						</td>
						<td style="width: 10%; text-align: center;">
						    <label><c:out value="${proyecto.codigoProyecto }"></c:out></label>
						</td>
						<td style="width: 55%; text-align: center;">
							<label><c:out value="${proyecto.duracionProyecto }"></c:out></label>
						</td>
				 		<td style="width: 5%; text-align: center;">
							<label><c:out value="${proyecto.cantidadPeriodo }"></c:out></label>
						</td>
				</tr>
				<tr class="<c:out value="${classIdi}"></c:out>">
				<td colspan="100%">
					<div
						id='divPTIF<c:out value="${proyecto.datoProyectoID }"></c:out>'
						style="display: none; position: relative; overflow: auto; padding-left: 15px; padding-bottom: 15px; width: 97%;">
						<legend><label>Propuesta de Transferencia</label></legend>
						<table border="0" width="100%">
							<thead>
								<tr>
									<td style="width: 35%; text-align: center;"><label>Resumen Descripcion <br/>de Transferencia</label></td>
									<td style="width: 35%; text-align: center;"><label>Resumen Plan <br/>de Transferencia</label></td>
									<td style="width: 25%; text-align: center;"><label>Opcion</label></td>
								</tr>
							</thead>
							<tbody>
							<c:choose>
								<c:when test="${proyecto.cantPropuestaTransferencia != '0' }">
								<tr >
									<td style="width: 35%; text-align: left;"><label><c:out value="${proyecto.propuestaTransferencia.resumenDescripTrans }"></c:out></label></td>
									<td style="width: 35%; text-align: left;"><label><c:out value="${proyecto.propuestaTransferencia.resumenPlanTrans }"></c:out></label></td>
									<td style="width: 25%; text-align: center;"><label>
										<a href="javascript:fOpenModalDialog('showDatosPropuestaTransferencia.jspx?propuestaTransferenciaId=${proyecto.propuestaTransferencia.propuestaTransferenciaID }','900','500','70','40');"
												id="evaluarPropuesta" class="linkSelecciona">Evaluar Propuesta</a></label>    
									</td>
								</tr>
								</c:when>
								<c:otherwise><tr><td colspan="3" style="width: 100%; text-align: left;"><label>No se encontro propuesta de transferencia ingresada</label></td></tr></c:otherwise>
							</c:choose>
							</tbody>
						</table>
						<br>
						<legend><label>Informe Final</label></legend>
						<table border="0" width="100%">
							<thead>
								<tr>
									<td style="width: 60%; text-align: center;"><label>Resultado del <br/>Proyecto</label></td>
									<td style="width: 20%; text-align: center;"><label>Opcion</label></td>
								</tr>
							</thead>
							<tbody>
							<c:choose>
								<c:when test="${proyecto.cantInformeFinal != '0' }">
								<tr>
									<td style="width: 60%; text-align: left;"><label><c:out value="${proyecto.informeFinal.resultadoProyecto }"></c:out></label></td>
									<td style="width: 20%; text-align: center;"><label><a href="javascript:fOpenModalDialog('showDatosInformeFinal.jspx?informeFinalId=${proyecto.informeFinal.informeFinalID }','900','600','70','70');"
												id="evaluarLiquidacion" class="linkSelecciona">Evaluar Informe</a></label> 
									</td>
								</tr>
								</c:when>
								<c:otherwise><tr><td colspan="2" style="width: 100%; text-align: left;"><label>No se encontro informe final ingresado</label></td></tr></c:otherwise>
							</c:choose>
							</tbody>
						</table>
				</div></td>
			</tr>
		</c:forEach>
	  </tbody>   
</table>