<%@ include file="/common/taglibs.jsp"%>

<script>
var arrayProyectos = new Array();

</script>

<table border="0" width="100%" class="table-clasico">
	<caption>Listado proyectos del programa</caption>
	<thead>
		<tr>
			<td style="width: 5%; text-align: center;"><label>Rp.<br>Rf.</label></td>
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
					href="javascript:expandcollapse('div<c:out value="${proyecto.datoProyectoID }"></c:out>', 'one');">
						<img
						id='imgdiv<c:out value="${proyecto.datoProyectoID }"></c:out>'
						border="0" src="<c:url value="/images/Plus001.gif" />"
						width="15px" /> </a>
						</td>
						<td style="width: 10%; text-align: left;">
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
						id='div<c:out value="${proyecto.datoProyectoID }"></c:out>'
						style="display: none; position: relative; overflow: auto; padding-left: 15px; padding-bottom: 15px; width: 97%;">
						<legend><label>Lista de solicitudes de reprogramacion y reformulacion</label></legend>
						<table border="0" width="100%">
							<thead>
								<tr>
									<td style="width: 10%; text-align: center;"><label>Version de <br/>Plan Operativo</label></td>
									<td style="width: 45%; text-align: center;"><label>Observacion <br/>de solicitud</label></td>
									<td style="width: 15%; text-align: center;"><label>Fecha de <br/>solicitud <br/>(dd/MM/aaaa)</label></td>
									<td style="width: 15%; text-align: center;"><label>Fecha de <br/>aprobacion<br/>(dd/MM/aaaa)</label></td>
									<td style="width: 15%; text-align: center;"><label>Estado</label></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${proyecto.listSolicitaRpRf }"
									var="solicitaRpRf" varStatus="indiceInt">
									<c:choose>
										<c:when test="${indiceInt.count %2== 0}">
											<c:set var="classIdiInt" value="f1int"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdiInt" value="f2int"></c:set>
										</c:otherwise>
									</c:choose>
								<tr class="<c:out value="${classIdiInt}"></c:out>">
									<td style="width: 10%; text-align: center;"><label>Version <c:out value="${solicitaRpRf.versionPo }"></c:out></label></td>
									<td style="width: 45%; text-align: left;"><label><c:out value="${solicitaRpRf.observacionDeSolicitud }"></c:out></label></td>
									<td style="width: 15%; text-align: center;"><label><c:out value="${solicitaRpRf.fechaSolicitudString }"></c:out></label></td>
									<td style="width: 15%; text-align: center;"><label><c:out value="${solicitaRpRf.fechaAprobacionString }"></c:out></label></td>
									<td style="width: 15%; text-align: center;"><label>
										<a href="javascript:muestraInfoProyecto('<c:out value="${proyecto.datoProyectoID }"></c:out>','<c:out value="${solicitaRpRf.versionPo }"></c:out>','<c:out value="${solicitaRpRf.solicitaRpRfID }"></c:out>');"
												id="evaluarPropuesta" class="linkSelecciona"><c:out value="${solicitaRpRf.descripcionEstadoRpRf }"></c:out></a></label>    
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
				</div></td>
			</tr>
		</c:forEach>
	  </tbody>   
</table>