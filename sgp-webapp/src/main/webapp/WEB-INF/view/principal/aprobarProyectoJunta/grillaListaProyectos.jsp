<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
 function grabarEstadoProyecto(estadoId,proyectoId,cantCuentasCorrientes){
	 //alert(cantCuentasCorrientes);
	 //var confirma;
	 if (cantCuentasCorrientes == 0){
		 alert("Este proyecto no tiene aun una CUENTA CORRIENTE ingresada, el ejecutor debe ingresarla.\n El estado no se grabara.");
			//fSetTimeOutRefreshLocation(50);
		 $("#grillaListaProyectos").load("grillaListaProyectos.jspx", {
				programaId : $("#sltPrograma").val()
			});
	 }else{
		$.get("grabarEstadoProyecto.jspx", {
			datoProyectoId : proyectoId,
			estadoId : estadoId
		}, function(){
			$("#grillaListaProyectos").load("grillaListaProyectos.jspx", {
				programaId : $("#sltPrograma").val()
			});
		});
		}	
 }
</script>

<fieldset>
	<table border="0" width="100%" class="table-clasico">
		<caption>
			<label>Lista de Proyectos del Programa <c:out
					value="${programaNombre }"></c:out> </label>
		</caption>
		<thead>
			<tr>
				<td style="width: 10%; text-align: center;"><label
					style="font-size: 10px;">Codigo</label></td>
				<td style="width: 25%; text-align: center;"><label
					style="font-size: 10px;">Nombre<br>Proyecto</label></td>
				<td style="width: 6%; text-align: center;"><label
					style="font-size: 10px;">Duracion<br>(meses)</label></td>
				<td style="width: 19%; text-align: center;"><label
					style="font-size: 10px;">Area<br>Tematica</label></td>
				<td style="width: 10%; text-align: center;"><label
					style="font-size: 10px;">Calif.<br>Inst.</label></td>
				<td style="width: 10%; text-align: center;"><label
					style="font-size: 10px;">Calif.<br>Perfil.</label></td>
				<td style="width: 10%; text-align: center;"><label
					style="font-size: 10px;">Calif.<br>Proy.</label></td>
				<td style="width: 10%; text-align: center;"><label
					style="font-size: 10px;">Estado</label></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listDatoProyectoBean }" var="proyectoBean"
				varStatus="indice">
				<c:choose>
					<c:when test="${indice.count %2== 0}">
						<c:set var="classIdi" value="f2"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="classIdi" value="f1"></c:set>
					</c:otherwise>
				</c:choose>
				<tr class="<c:out value="${classIdi}"></c:out>">
					<td style="width: 10%; text-align: center;"><label
						style="font-size: 10px;"> <c:out
								value="${proyectoBean.codigoProyecto }"></c:out> </label></td>
					<td style="width: 25%; text-align: left;"><label
						style="font-size: 10px;"><c:out
								value="${proyectoBean.nombreProyecto }"></c:out> </label></td>
					<td style="width: 6%; text-align: center;"><label
						style="font-size: 10px;"><c:out
								value="${proyectoBean.duracionProyecto }"></c:out> </label></td>
					<td style="width: 19%; text-align: left;"><label
						style="font-size: 10px;"><b><c:out
									value="${proyectoBean.descAreaTematica }" /> </b><br> <c:out
								value="${proyectoBean.descSubAreaTematica }" /> </label></td>
					<td style="width: 10%; text-align: center;"><label
						style="font-size: 10px;"><c:out
								value="${proyectoBean.promEvalInstitucion }"></c:out> </label></td>
					<td style="width: 10%; text-align: center;"><label
						style="font-size: 10px;"><c:out
								value="${proyectoBean.promEvalPerfil }"></c:out> </label></td>
					<td style="width: 10%; text-align: center;"><label
						style="font-size: 10px;"><c:out
								value="${proyectoBean.promEvalProyecto }"></c:out> </label></td>
					<td style="width: 10%; text-align: center;"><label
						style="font-size: 10px;"> <select name="sltDetalleEstadoCabecera" id="sltDetalleEstadoCabecera"
						<c:if test="${proyectoBean.cuentaPlanOperativo >0}"> disabled="disabled" </c:if>
							onchange="javascript:grabarEstadoProyecto(this.value,<c:out value="${proyectoBean.datoProyectoID }"></c:out>,<c:out value="${proyectoBean.cantCuentaCorriente }"></c:out>);">
								<option value="0"><c:out value="-- Estado --" /></option>
								<c:forEach items="${listDetalleEstadoCabecera}"
									var="detalleEstadoCabecera">
									<option
										<c:if test="${detalleEstadoCabecera.detalleEstadoCabeceraID==proyectoBean.fkIddetallestadocabEstproy }"> selected="selected" </c:if>
										value="${detalleEstadoCabecera.detalleEstadoCabeceraID}">
										<c:out value="${detalleEstadoCabecera.descripEstado}" />
									</option>
								</c:forEach>
						</select> </label></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
</fieldset>