<%@ include file="/common/taglibs.jsp"%>

<table border="0" width="100%" class="table-clasico">
	<caption>Listado de Proyectos</caption>
	<thead>
		<tr>
			<td style="width: 10%; text-align: center;"><label>Des</label></td>
			<td style="width: 20%; text-align: center;"><label>Codigo <br/>Proyecto</label></td>
			<td style="width: 68%; text-align: center;"><label>Nombre Proyecto</label></td>
		</tr>
	</thead>
	<tbody>
			<c:forEach items="${listDatoProyecto }" var="proyecto" varStatus="indice">
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
						<td style="width: 20%; text-align: center;">
						    <label><c:out value="${proyecto.codigoProyecto }"></c:out></label>
						</td>
						<td style="width: 68%; text-align: Left;">
							<label><c:out value="${proyecto.nombreProyecto }"></c:out></label>
						</td>
				</tr>
				<tr class="<c:out value="${classIdi}"></c:out>">
				<td colspan="100%">
					<div
						id='div<c:out value="${proyecto.datoProyectoID }"></c:out>'
						style="display: none; position: relative; overflow: auto; padding-left: 15px; padding-bottom: 15px; width: 97%;">
						<legend><label>Lista de Desembolsos</label></legend>
						<table border="0" width="100%">
							<thead>
								<tr>
									<td style="width: 10%; text-align: center;"><label>Periodo</label></td>
									<td style="width: 10%; text-align: center;"><label>Version de <br/>Desembolso</label></td>
									<td style="width: 30%; text-align: center;"><label>Fuente <br/>Financiadora</label></td>
									<td style="width: 20%; text-align: center;"><label>Monto<br/>Solicitado</label></td>
									<td style="width: 25%; text-align: center;"><label>Estado</label></td>
								</tr>
							</thead>
							<tbody>
							<c:forEach
									items="${proyecto.listDesembolso }"
									var="desembolso" varStatus="indiceInt">
									<c:choose>
										<c:when test="${indiceInt.count %2== 0}">
											<c:set var="classTblInt" value="f1int"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classTblInt" value="f2int"></c:set>
										</c:otherwise>
									</c:choose>
									<tr class="<c:out value="${classTblInt }"></c:out>">
									<td style="width: 10%; text-align: center;"><label><c:out value="${desembolso.periodo }"></c:out></label></td>
									<td style="width: 10%; text-align: center;"><label><c:out value="${desembolso.versionDePeriodo }"></c:out></label></td>
									<td style="width: 30%; text-align: center;"><label><c:out value="${desembolso.fuenteFinanciadora.institucion.nombreInstitucion }"></c:out></label></td>
									<td style="width: 20%; text-align: center;"><label><c:out value="${desembolso.montoSolicitado }"></c:out> <c:out value="${desembolso.descripcionTipoMoneda }"></c:out></label></td>
									<td style="width: 25%; text-align: center;"><label><a href="javascript:fOpenModalDialog('mostrarDesembolsoSelecionado.jspx?desembolsoId=${desembolso.desembolsoID }','960','500','70','40');"
												id="desembolsoSeleccionado" class="linkSelecciona"><c:out value="${desembolso.descripcionEstDesembolso }"></c:out></a></label></td>
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