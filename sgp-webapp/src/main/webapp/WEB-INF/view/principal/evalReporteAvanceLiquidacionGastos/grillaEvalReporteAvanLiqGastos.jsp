<%@ include file="/common/taglibs.jsp"%>

<script>
var arrayProyectos = new Array();

</script>

<table border="0" width="100%" class="table-clasico">
	<caption>Listado proyectos del programa</caption>
	<thead>
		<tr>
			<td style="width: 5%; text-align: center;"><label>Rept<br>Liqd</label></td>
			<td style="width: 55%; text-align: center;"><label>Proyecto</label></td>
			<td style="width: 20%; text-align: center;"><label>Cod.Proyecto</label></td>

			<td style="width: 5%; text-align: center;"><label>Duracion</label></td>
			<td style="width: 5%; text-align: center;"><label>Cant.Periodos</label></td>
			<td style="width: 5%; text-align: center;"><label>Cant.<br>Reportes</label></td>
			<td style="width: 5%; text-align: center;"><label>Cant.<br>Liquidaciones</label></td>    
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
					href="javascript:expandcollapse('div<c:out value="${proyecto.datoProyectoID }"></c:out>', 'one');">
						<img
						id='imgdiv<c:out value="${proyecto.datoProyectoID }"></c:out>'
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
						<td style="width: 5%; text-align: center;">
							<label><c:out value="${proyecto.cantReportesPorEval }"></c:out></label>
						</td>
						<td style="width: 5%; text-align: center;">
							<label><c:out value="${proyecto.cantLiqPorEvaluar }"></c:out></label>
						</td>
				 
				</tr>
				<tr class="<c:out value="${classIdi}"></c:out>">
				<td colspan="100%">
					<div
						id='div<c:out value="${proyecto.datoProyectoID }"></c:out>'
						style="display: none; position: relative; overflow: auto; padding-left: 15px; padding-bottom: 15px; width: 97%;">
						<legend><label>Lista Reporte de Avance por Evaluar</label></legend>
						<table border="0" width="100%">
							<thead>
								<tr>
									<td style="width: 25%; text-align: center;">Reporte</td>
									<td style="width: 25%; text-align: center;">Estado de Reporte</td>
									<td style="width: 25%; text-align: center;">Evaluar</td>
								</tr>
							</thead>
							<tbody>
							<c:forEach
									items="${proyecto.lstRepAvance }"
									var="reporteAvance" varStatus="indiceInt">
									<c:choose>
										<c:when test="${indiceInt.count %2== 0}">
											<c:set var="classTblInt" value="f1int"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classTblInt" value="f2int"></c:set>
										</c:otherwise>
									</c:choose>
									<tr class="<c:out value="${classTblInt }"></c:out>">
									<td style="width: 25%; text-align: center;"><c:out value="${reporteAvance.repAvanceDesc}"></c:out></td>
									<td style="width: 25%; text-align: center;"><c:out value="${reporteAvance.estRepAvanceDesc }"></c:out></td>
									<td style="width: 25%; text-align: center;">
										<a href="javascript:fOpenModalDialog('showDatosReporteAvance.jspx?reporteAvanceID=${reporteAvance.reporteAvanceID}&reporteAvanceDesc=${reporteAvance.repAvanceDesc}','960','500','70','40');"
												id="evaluarReporte" class="linkSelecciona">Ver Reporte</a>    
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
						<br>
						<legend><label>Lista Liquidaciones de Gastos por Evaluar</label></legend>
						<table border="0" width="100%">
							<thead>
								<tr>
									<td style="width: 25%; text-align: center;">Liquidacion</td>
									<td style="width: 15%; text-align: center;">Periodo</td>
									<td style="width: 20%; text-align: center;">Version <br> Periodo</td>
									<td style="width: 20%; text-align: center;">Estado <br>Liquidacion</td>
									<td style="width: 20%; text-align: center;">Evaluar</td>
								</tr>
							</thead>
							<tbody>
							<c:forEach
									items="${proyecto.lstLiqGasto }"
									var="liquidacionGasto" varStatus="indiceInt">
									<c:choose>
										<c:when test="${indiceInt.count %2== 0}">
											<c:set var="classTblInt" value="f1int"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classTblInt" value="f2int"></c:set>
										</c:otherwise>
									</c:choose>									     
									<tr class="<c:out value="${classTblInt }"></c:out>">
									<td style="width: 25%; text-align: center;"><c:out value="${liquidacionGasto.liqGastoDesc}"></c:out></td>
									<td style="width: 15%; text-align: center;"><c:out value="${liquidacionGasto.periodo}"></c:out></td>
									<td style="width: 20%; text-align: center;"><c:out value="${liquidacionGasto.codVersion}"></c:out></td>
									<td style="width: 20%; text-align: center;"><c:out value="${liquidacionGasto.estLiqGastoDesc}"></c:out></td>
									<td style="width: 20%; text-align: center;">
										<a href="javascript:fOpenModalDialog('showDatosLiquidacionGastos.jspx?liquidacionGastoID=${liquidacionGasto.liquidacionGastoID}','1100','800','70','70');"
												id="evaluarLiquidacion" class="linkSelecciona">Ver<br> Liquidacion</a> 
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
				</div></td>
			</tr>
			    <script type="text/javascript">
						var objProyecto = new Object();
						objProyecto.datoProyectoID = "<c:out value="${proyecto.datoProyectoID }"></c:out>";
						objProyecto.codigoProyecto = "<c:out value="${proyecto.codigoProyecto}"></c:out>";				
						arrayProyectos.push(objProyecto);
				</script>
						
			 </c:forEach>
	  </tbody>   
</table>