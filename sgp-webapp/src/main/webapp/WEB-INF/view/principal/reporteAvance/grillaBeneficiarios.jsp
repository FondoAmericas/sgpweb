<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
	function agregarCantidadLograda(beneficiarioPorResultadoId,reporteAvanceId) {
		var url = "cuerpoBeneficiariosResultadoLogrado.jspx?beneficiarioPorResultadoId=" + beneficiarioPorResultadoId+"&reporteAvanceId="+reporteAvanceId ;
		var stiloPopUp = 'dialogWidth=800px; dialogHeight=500px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';

		window.showModalDialog(url, "", stiloPopUp);
		 
	}
</script>

<table border="0" width="100%" class="table-clasico">
	<caption>Lista de beneficiarios por resultado</caption>
	<thead>
		<tr>
			<td style="width: 3%; text-align: center;"><label
				style="font-size: 10px;">Det <br>Lograda</label>
			</td>
			<td style="width: 20%; text-align: center;"><label
				style="font-size: 10px;">Resultado</label>
			</td>
			<td style="width: 10%; text-align: center;"><label
				style="font-size: 10px;">Tipo Beneficiario</label>
			</td>
			<td style="width: 35%; text-align: center;"><label
				style="font-size: 10px;">Caracistica <br>Poblacion</label>
			</td>
			<td style="width: 12%; text-align: center;"><label
				style="font-size: 10px;">Departamento<br>Provincia<br>Distrito</label>
			</td>
			<td style="width: 10%; text-align: center;"><label
				style="font-size: 10px;">Cantidad<br>Programada</label>
			</td>
			<td style="width: 10%; text-align: center;" class="hide"><label
				style="font-size: 10px;" >Ingresar<br>Cantidad <br>Lograda</label>
			</td>
		</tr>
	</thead>
	<tbody>

		<c:forEach items="${listBeneficiariosPorResultadoBean }"
			var="beneficiariosPorResultadoBean" varStatus="indice">
			<c:choose>
				<c:when test="${indice.count %2== 0}">
					<c:set var="classIdi" value="f2"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="classIdi" value="f1"></c:set>
				</c:otherwise>
			</c:choose>
			<tr class="<c:out value="${classIdi}"></c:out>">
				<td style="width: 3%; text-align: justify;"><a
					href="javascript:expandcollapse('div<c:out value="${beneficiariosPorResultadoBean.beneficiariosPorResultadoID }"></c:out>', 'one');">
						<img
						id='imgdiv<c:out value="${beneficiariosPorResultadoBean.beneficiariosPorResultadoID }"></c:out>'
						border="0" src="<c:url value="/images/Plus001.gif" />"
						width="15px" /> </a></td>
				<td style="width: 20%; text-align: justify;"><label><c:out
							value="${beneficiariosPorResultadoBean.descripcionResultado }"></c:out>
				</label><br>
				</td>
				<td style="width: 10%; text-align: justify;"><label>Beneficiarios
						<c:out
							value="${beneficiariosPorResultadoBean.descripcionTipoBeneficiario }"></c:out>
				</label><br>
				</td>
				<td style="width: 35%; text-align: justify;"><label><c:out
							value="${beneficiariosPorResultadoBean.caracteristicasPoblacion }"></c:out>
				</label><br>
				</td>
				<td style="width: 12%; text-align: center;"><label><c:out
							value="${beneficiariosPorResultadoBean.departamento }"></c:out><br>
					<c:out value="${beneficiariosPorResultadoBean.provincia }"></c:out><br>
					<c:out value="${beneficiariosPorResultadoBean.distrito }"></c:out>
				</label>
				</td>
				<td style="width: 10%; text-align: center;"><label><c:out
							value="${beneficiariosPorResultadoBean.cantidadProgramado }"></c:out>
						<c:out
							value="${beneficiariosPorResultadoBean.descripcionEstrato }"></c:out>
				</label>
				</td>
				<td style="width: 10%; text-align: center;" class="hide"><label > <a
						href="javascript:agregarCantidadLograda('<c:out value="${beneficiariosPorResultadoBean.beneficiariosPorResultadoID }"></c:out>','<c:out value="${reporteAvanceId}"></c:out>');"
						class="linkSelecciona">Agregar</a> </label>
				</td>
			</tr>
			<tr class="<c:out value="${classIdi}"></c:out>">
				<td colspan="100%">
					<div
						id='div<c:out value="${beneficiariosPorResultadoBean.beneficiariosPorResultadoID }"></c:out>'
						style="display: none; position: relative; overflow: auto; padding-left: 15px; padding-bottom: 15px; width: 97%;">

						<table>
							<caption>Cantidad Lograda por Reporte</caption>
							<thead>
								<tr>
									<td style="width: 30%; text-align: center;"><label>Reporte
											N°</label></td>
									<td style="width: 40%; text-align: center;"><label>Cantidad
											Lograda</label></td>
									<td style="width: 20%; text-align: center;" class="hide"><label>Eliminar</label></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach
									items="${beneficiariosPorResultadoBean.listReporteAvanceBeneficiarioBean }"
									var="reporteAvanceBeneficiarioBean" varStatus="indiceInt">
									<c:choose>
										<c:when test="${indiceInt.count %2== 0}">
											<c:set var="classTblInt" value="f1int"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classTblInt" value="f2int"></c:set>
										</c:otherwise>
									</c:choose>
									<tr class="<c:out value="${classTblInt }"></c:out>">
										<td style="width: 30%; text-align: center;"><label>
												Reporte N° <c:out
													value="${reporteAvanceBeneficiarioBean.periodoReporte }"></c:out>
										</label>
										</td>
										<td style="width: 40%; text-align: center;"><label>
												<c:out
													value="${reporteAvanceBeneficiarioBean.cantidadLograda }"></c:out>
												<c:out
													value="${beneficiariosPorResultadoBean.descripcionEstrato }"></c:out>
										</label>
										</td>
										<td style="width: 20%; text-align: center;" class="hide">
										<c:if test="${reporteAvanceId == reporteAvanceBeneficiarioBean.reporteAvance.reporteAvanceID}">
										<label ><a
						href="javascript:eliminarReporteAvanceBeneficiario('<c:out value="${reporteAvanceBeneficiarioBean.reporteAvanceBeneficiarioID }"></c:out>')"
						id="modificarProblemasSoluciones" class="linkSelecciona">Eliminar</a></label>
										</c:if>
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