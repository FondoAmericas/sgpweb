<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
	/*$(window).load(function() {
		ocultaCampos();
	});

	function ocultaCampos() {
		estado = "<c:out value="${estado}"></c:out>";
		//alert(estado);
		if ((estado == 'apro')|| (estado == 'eval')) {
			$(".hide").hide();//attr("style","visibility: hidden;");
			$(".disabled").attr("disabled", "disabled");
			//alert($('#cbxInstitucionEjecutora option:selected').html());
			//$("#div_cbxInstitucionEjecutora").attr("style","visibility: hidden;");
		}
	}*/
</script>

<script type="text/javascript">
function mostrarDetalle(avanceResultadoActividadId){
	var url = "mostrarDetalleAvanceResultadoActividad.jspx?avanceResultadoActividadId=" + avanceResultadoActividadId;
	var stiloPopUp = 'dialogWidth=700px; dialogHeight=700px; dialogTop=50px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
	//ventanaPopUp = showModalDialog(url,'',stiloPopUp);//window.open(url,'',stiloPopUp);

	//window.opener.document.formWindowAsignaTutor.profesorAsignado.value = componenteId;

	//window.showModalDialog(url,datos,stiloPopUp);
	window.showModalDialog(url, "", stiloPopUp);
}
</script>
<fieldset>
<table border="0" width="100%" class="table-clasico">
	<caption>Lista de Avance de Resultados de Reporte <c:out value="${reporteAvance.periodo }"></c:out> </caption>
	<thead>
		<tr>
			<td style="width: 8%; text-align: center;"><label style="font-size: 10px;">Cantidad <br>Programada</label></td>
			<td style="width: 8%; text-align: center;"><label style="font-size: 10px;">Cantidad <br>Ejecutado</label></td>
			<td style="width: 18%; text-align: center;"><label style="font-size: 10px;">Descripcion<br>Actividad</label></td>
			<td style="width: 20%; text-align: center;"><label style="font-size: 10px;">Resumen<br>Ejecutivo</label></td>
			<td style="width: 20%; text-align: center;"><label style="font-size: 10px;">Elemento<br>Verificacion</label></td>
			<td style="width: 20%; text-align: center;"><label style="font-size: 10px;">Observaciones</label></td>
			<td style="width: 6%; text-align: center;" class="hide"><label style="font-size: 10px;" >Eli<br>Mod</label></td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${listAvanceResultadoActividadBean }" var="avanceResultadoActividadBean" varStatus="indice">
	<c:choose>
										<c:when test="${indice.count %2== 0}">
											<c:set var="classIdi" value="f2"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdi" value="f1"></c:set>
										</c:otherwise>
									</c:choose>
	<tr class="<c:out value="${classIdi}"></c:out>">
			<td style="width: 8%; text-align: center;"><label style="font-size: 10px;"><c:out value="${avanceResultadoActividadBean.metaPorActividad.cantidadMetaActividad } - "></c:out><c:out value="${avanceResultadoActividadBean.descripcionUnidadMedida }"></c:out>
			</label></td>
			<td style="width: 8%; text-align: center;"><label style="font-size: 10px;"><c:out value="${avanceResultadoActividadBean.cantidadAvanceEjecutado } - "></c:out><c:out value="${avanceResultadoActividadBean.descripcionUnidadMedida }"></c:out></label></td>
			<td style="width: 18%; text-align:justify;"><label style="font-size: 10px;"><c:out value="${avanceResultadoActividadBean.descripcionActividad }"></c:out></label></td>
			<td style="width: 20%; text-align:justify;"><label style="font-size: 10px;"><c:out value="${avanceResultadoActividadBean.resumenEjecutivoPeriodo }"></c:out></label></td>
			<td style="width: 20%; text-align: justify;"><label style="font-size: 10px;"><c:out value="${avanceResultadoActividadBean.elementoVerificacion }"></c:out></label></td>
			<td style="width: 20%; text-align: justify;"><label style="font-size: 10px;"><c:out value="${avanceResultadoActividadBean.observaciones }"></c:out></label></td>
			<td style="width: 6%; text-align: center;" class="hide"><label style="font-size: 10px;" ><a
						href="javascript:eliminarAvanceResultadoActividad('<c:out value="${avanceResultadoActividadBean.avanceResultadoActividadID }" ></c:out>')"
						class="linkSelecciona">Eliminar</a><br>
<a
						href="javascript:modificarAvanceResultadoActividad('<c:out value="${avanceResultadoActividadBean.avanceResultadoActividadID }" ></c:out>',
																			'<c:out value="${avanceResultadoActividadBean.cantidadAvanceEjecutado }"></c:out>',
																			'<c:out value="${avanceResultadoActividadBean.descripcionActividad }" ></c:out>',
																			'<c:out value="${avanceResultadoActividadBean.resumenEjecutivoPeriodo }"></c:out>',
																			'<c:out value="${avanceResultadoActividadBean.elementoVerificacion }"></c:out>',
																			'<c:out value="${avanceResultadoActividadBean.observaciones }"></c:out>',
																			'<c:out value="${avanceResultadoActividadBean.metaPorActividad.metaPorActividadID }"></c:out>',
																			'<c:out value="${avanceResultadoActividadBean.metaPorActividad.actividad.actividadID }"></c:out>',
																			'<c:out value="${avanceResultadoActividadBean.metaPorActividad.actividad.resultado.resultadoID }"></c:out>')"
						id="modificarProblemasSoluciones" class="linkSelecciona">Modificar</a></label></td>
		</tr>
		<tr class="<c:out value="${classIdi}"></c:out>">
			<td colspan="6" style="width: 94%;"><div style="width: 100%; text-align: right;"><a href="javascript:mostrarDetalle('<c:out value="${avanceResultadoActividadBean.avanceResultadoActividadID }" ></c:out>')"
												class="linkSelecciona">Mostrar mas...</a></div></td>
			
		</tr>		
	</c:forEach>
			</tbody>
	</table>
<br>
</fieldset>