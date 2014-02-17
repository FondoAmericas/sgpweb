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
	function agregarCantidadLograda(beneficiarioPorResultadoId,reporteAvanceId) {
		var url = "cuerpoBeneficiariosResultadoLogrado.jspx?beneficiarioPorResultadoId=" + beneficiarioPorResultadoId+"&reporteAvanceId="+reporteAvanceId ;
		var stiloPopUp = 'dialogWidth=800px; dialogHeight=500px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';

		window.showModalDialog(url, "", stiloPopUp);
		 
	}
</script>

</head>
<body>

<form id="detalleReporteAvanceForm" method="post" action="#" class="form-clasico" accept-charset="UTF-8">
<div id="div_beneficiarios">
<table border="0" width="100%" class="table-clasico">
	<caption>Lista del personal tecnico administrativo</caption>
	<thead>
		<tr>
			<td style="width: 7%; text-align: center;"><label
				style="font-size: 10px;">Detalle</label>
			</td>
			<td style="width: 30%; text-align: center;"><label
				style="font-size: 10px;">Nombre</label>
			</td>
			<td style="width: 18%; text-align: center;"><label
				style="font-size: 10px;">Profesion</label>
			</td>
			<td style="width: 18%; text-align: center;"><label
				style="font-size: 10px;">Tiempo<br>dedicado</label>
			</td>
			<td style="width: 10%; text-align: center;"><label
				style="font-size: 10px;">Porcentaje<br>%</label>
			</td>
			<td style="width: 10%; text-align: center;"><label
				style="font-size: 10px;">Etapa de<br>personal</label>
			</td>
			<td style="width: 6%; text-align: center;"  class="hide"><label
				style="font-size: 10px;">Opcion</label>
			</td>
		</tr>
	</thead>
	<tbody>

		<c:forEach items="${lstPersonalTecAdm }"
			var="objPersonalTecAdm" varStatus="indice">
			<c:choose>
				<c:when test="${indice.count %2== 0}">
					<c:set var="classIdi" value="f2"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="classIdi" value="f1"></c:set>
				</c:otherwise>
			</c:choose>
			<tr class="<c:out value="${classIdi}"></c:out>">
				<td style="text-align: justify;"><a
					href="javascript:expandcollapse('div<c:out value="${objPersonalTecAdm.personalTecnicoAdministrativoID }"></c:out>', 'one');">
						<img
						id='imgdiv<c:out value="${objPersonalTecAdm.personalTecnicoAdministrativoID }"></c:out>'
						border="0" src="<c:url value="/images/Plus001.gif" />"
						width="15px" /> </a>
				</td>
				<td style="text-align: justify;"><label><c:out
							value="${objPersonalTecAdm.nombreCompleto }"></c:out>
				</label><br>
				</td>
				<td style="text-align: justify;"><label>
						<c:out
							value="${objPersonalTecAdm.formacionProfesionalNombre }"></c:out>
				</label><br>
				</td>
				<td style="text-align: justify;"><label><c:out
							value="${objPersonalTecAdm.tiempoDedicadoNombre }"></c:out>
				</label><br>
				</td>
				<td style="text-align: center;"><label><c:out
							value="${objPersonalTecAdm.porcentageParticipacion }"></c:out>
				</label><br>
				</td>
				<td style="text-align: center;"><label><c:out
							value="${objPersonalTecAdm.etapaPersonalTecnicoNombre }"></c:out>
				</label>
				</td>
				<td style="text-align: center;" class="hide"><label>
				<a  href="javascript:agregarObservacion('<c:out value="${objPersonalTecAdm.personalTecnicoAdministrativoID }" ></c:out>','<c:out value="${reporteAvance.datoProyecto.datoProyectoID }" ></c:out>','8','24','<c:out value="${reporteAvance.reporteAvanceID}" ></c:out>')"
					class="linkSelecciona">Observacion</a>
				</label>
				</td>
			</tr>
		  	<tr class="<c:out value="${classIdi}"></c:out>">
				<td colspan="100%">
					<div
						id='div<c:out value="${objPersonalTecAdm.personalTecnicoAdministrativoID }"></c:out>'
						style="display: none; position: relative; overflow: auto; padding-left: 15px; padding-bottom: 15px; width: 97%;">
						<legend><label>Mas detalle</label></legend>
						<table width="100%">
							<thead>
								<tr>
									<td style="width: 40%; text-align: justify;">
									    <label>Responsabilidad</label>
									</td>
									<td style="width: 20%; text-align: center;">
									    <label>Personal tecnico<br>reemplazo</label>
									</td>
									<td style="width: 15%; text-align: center;">
									    <label>Salario<br>mensual</label>
									</td>
									<td style="width: 20%; text-align: center;">
									    <label>Institucion</label>
									</td>
								</tr>
							</thead>
							<tbody>
								<c:set var="classTblInt" value="f1int"></c:set>
								<tr class="<c:out value="${classTblInt }"></c:out>">
										<td style="width: 30%; text-align: center;">
										    <label>
										        <c:out value="${objPersonalTecAdm.responsabilidad }"></c:out>
										    </label>
										</td>
										<td style="width: 40%; text-align: center;">
											<label>
										        <c:out value="${objPersonalTecAdm.personalTecnicoAdmReemplazoNombre }"></c:out>
										    </label>												
										</td>
										<td style="width: 40%; text-align: center;">
											<label>
										        <c:out value="${objPersonalTecAdm.salarioMensual }"></c:out>
										    </label>												
										</td>
										<td style="width: 40%; text-align: center;">
											<label>
										        <c:out value="${objPersonalTecAdm.institucion.nombreInstitucion }"></c:out>
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
</div>

</form>

</body>
</html>