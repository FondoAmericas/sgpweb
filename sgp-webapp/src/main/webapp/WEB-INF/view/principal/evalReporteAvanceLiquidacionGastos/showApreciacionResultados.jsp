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
function mostrarDetalleApresiacion(reporteAvanceID){
	var url = "mostrarDetalleApresiacionResultado.jspx?reporteAvanceID=" + reporteAvanceID;
	var stiloPopUp = 'dialogWidth=800px; dialogHeight=700px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
	//ventanaPopUp = showModalDialog(url,'',stiloPopUp);//window.open(url,'',stiloPopUp);

	//window.opener.document.formWindowAsignaTutor.profesorAsignado.value = componenteId;

	//window.showModalDialog(url,datos,stiloPopUp);
	window.showModalDialog(url, "", stiloPopUp);
}
</script>

</head>
<body>

<form id="detalleReporteAvanceForm" method="post" action="#" class="form-clasico" accept-charset="UTF-8">
<div id="div_apreciacionResultados">
<table border="0" width="100%" class="table-clasico">
	<caption>Lista de apresiaciones de resultados para el reporte <c:out value="${reporteAvance.periodo }"></c:out> </caption>
	<thead>
		<tr>
			<td style="width: 10%; text-align: center;"><label style="font-size: 10px;">Tipo<br>apresiación</label></td>
			<td style="width: 90%; text-align: center;"><label style="font-size: 10px;">Comentario</label></td>
		 	<td style="width: 10%; text-align: center;" class="hide"><label style="font-size: 10px;">Opcion</label></td>   
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${listApreciacionResultado }" var="apreciacionResultado" varStatus="indice">
	<c:choose>
										<c:when test="${indice.count %2== 0}">
											<c:set var="classIdi" value="f2"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdi" value="f1"></c:set>
										</c:otherwise>
									</c:choose>
	<tr class="<c:out value="${classIdi}"></c:out>">
			<td style="width: 10%;text-align: left;"><label><c:out value="${apreciacionResultado.descripcionTipoApreciacionResultadoRa }"></c:out></label><br></td>
			<td style="width: 90%;text-align: justify;"><label><c:out value="${apreciacionResultado.comentario }"></c:out></label><br></td>
			<td style="width: 10%; text-align: center;" class="hide"><label>
			<a	href="javascript:agregarObservacion('<c:out value="${apreciacionResultado.apreciacionResultadoID  }" ></c:out>','<c:out value="${reporteAvance.datoProyecto.datoProyectoID }" ></c:out>','8','22','<c:out value="${reporteAvance.reporteAvanceID}" ></c:out>')"
														class="linkSelecciona">Observacion</a></label></td>  
		</tr>
				<script type="text/javascript">
var objApresiacionResultado = new Object();
objApresiacionResultado.fkIdtablaespApreciacionResultadoRa = "<c:out value="${apreciacionResultado.fkIdtablaespApreciacionResultadoRa}"></c:out>";

arrayApresiacionResultado
		.push(objApresiacionResultado);
</script>
	</c:forEach>
		<tr bordercolor="#b8e9b9">
			<td colspan="2" style="width: 100%;"><div style="width: 100%; text-align: right;"><a href="javascript:mostrarDetalleApresiacion('<c:out value="${reporteAvance.reporteAvanceID }" ></c:out>')"
												class="linkSelecciona">Mostrar mas...</a></div></td>
			
		</tr>
			</tbody>
</table>
</div>
</form>
</body>
</html>