<%@ include file="/common/includesTaglibsGenerico.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
$(document).ready(function() {
	var estReporteAvancePrefijo="<c:out value="${estReporteAvancePrefijo }"></c:out>";
	
	if(estReporteAvancePrefijo == 'apro'){
		$(".hide").hide();
	}
});
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

</head>
<body>
<form id="detalleReporteAvanceForm" method="post" action="#" class="form-clasico" accept-charset="UTF-8">
<div id="div_avanceResultadoActividad">
<table border="0" width="100%" class="table-clasico">
	<caption>Lista de Avance de Resultados de Reporte <c:out value="${reporteAvance.periodo }"></c:out> </caption>
	<thead>
		<tr>
			<td style="width: 10%; text-align: center;"><label style="font-size: 10px;">Cantidad <br>Programada</label></td>
			<td style="width: 10%; text-align: center;"><label style="font-size: 10px;">Cantidad <br>Ejecutado</label></td>
			<td style="width: 20%; text-align: center;"><label style="font-size: 10px;">Descripcion<br>Actividad</label></td>
			<td style="width: 20%; text-align: center;"><label style="font-size: 10px;">Resumen<br>Ejecutivo</label></td>
			<td style="width: 20%; text-align: center;"><label style="font-size: 10px;">Elemento<br>Verificacion</label></td>
			<td style="width: 20%; text-align: center;"><label style="font-size: 10px;">Observaciones</label></td>
		 	<td style="width: 6%; text-align: center;" class="hide"><label style="font-size: 10px;">Opcion</label></td>
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
			<td style="width: 10%; text-align: center;"><label style="font-size: 10px;"><c:out value="${avanceResultadoActividadBean.metaPorActividad.cantidadMetaActividad  } - "></c:out><c:out value="${avanceResultadoActividadBean.descripcionUnidadMedida }"></c:out>
			</label></td>
			<td style="width: 10%; text-align: center;"><label style="font-size: 10px;"><c:out value="${avanceResultadoActividadBean.cantidadAvanceEjecutado } - "></c:out><c:out value="${avanceResultadoActividadBean.descripcionUnidadMedida }"></c:out></label></td>
			<td style="width: 20%; text-align:justify;"><label style="font-size: 10px;"><c:out value="${avanceResultadoActividadBean.descripcionActividad }"></c:out></label></td>
			<td style="width: 20%; text-align:justify;"><label style="font-size: 10px;"><c:out value="${avanceResultadoActividadBean.resumenEjecutivoPeriodo }"></c:out></label></td>
			<td style="width: 20%; text-align: justify;"><label style="font-size: 10px;"><c:out value="${avanceResultadoActividadBean.elementoVerificacion }"></c:out></label></td>
			<td style="width: 20%; text-align: justify;"><label style="font-size: 10px;"><c:out value="${avanceResultadoActividadBean.observaciones }"></c:out></label></td>
		 	<td style="width: 6%; text-align: center;" class="hide"><label style="font-size: 10px;">
				<a href="javascript:agregarObservacion('<c:out value="${avanceResultadoActividadBean.avanceResultadoActividadID }" ></c:out>','<c:out value="${reporteAvance.datoProyecto.datoProyectoID }" ></c:out>','8','21','<c:out value="${reporteAvance.reporteAvanceID}" ></c:out>')"
														class="linkSelecciona">Observacion</a>
				</label></td>   
		</tr>
		<tr class="<c:out value="${classIdi}"></c:out>">
			<td colspan="6" style="width: 94%;"><div style="width: 100%; text-align: right;"><a href="javascript:mostrarDetalle('<c:out value="${avanceResultadoActividadBean.avanceResultadoActividadID }" ></c:out>')"
												class="linkSelecciona">Mostrar mas...</a></div></td>
			<td style="width: 6%;"></td>
		</tr>		
	</c:forEach>
			</tbody>
</table>
</div>

</form>
</body>
</html>