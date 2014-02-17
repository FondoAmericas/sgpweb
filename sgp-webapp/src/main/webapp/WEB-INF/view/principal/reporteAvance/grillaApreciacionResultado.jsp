<%@ include file="/common/taglibs.jsp"%>

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

<table border="0" width="100%" class="table-clasico">
	<caption>Lista de apreciaciones de resultados para el reporte <c:out value="${reporteAvance.periodo }"></c:out> </caption>
	<thead>
		<tr>
			<td style="width: 10%; text-align: center;"><label style="font-size: 10px;">Tipo<br>apreciaci�n</label></td>
			<td style="width: 80%; text-align: center;"><label style="font-size: 10px;">Comentario</label></td>
			<td style="width: 10%; text-align: center;" class="hide"><label style="font-size: 10px;" >Eli<br>Mod</label></td>
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
			<td style="width: 80%;text-align: justify;"><label><c:out value="${apreciacionResultado.comentario }"></c:out></label><br></td>
			<td style="width: 10%; text-align: center;" class="hide" ><label >
			<a
						href="javascript:eliminarApresiacionResultado('<c:out value="${apreciacionResultado.apreciacionResultadoID }"></c:out>')"
						id="modificarProblemasSoluciones" class="linkSelecciona">Eliminar</a><br><br>
			<a
						href="javascript:modificarApresiacionResultado('<c:out value="${apreciacionResultado.apreciacionResultadoID }"></c:out>','<c:out value="${apreciacionResultado.fkIdtablaespApreciacionResultadoRa }"></c:out>','<c:out value="${apreciacionResultado.comentario }" ></c:out>')"
						id="modificarProblemasSoluciones" class="linkSelecciona">Modificar</a></label></td>
		</tr>
				<script type="text/javascript">
var objApresiacionResultado = new Object();
objApresiacionResultado.fkIdtablaespApreciacionResultadoRa = "<c:out value="${apreciacionResultado.fkIdtablaespApreciacionResultadoRa}"></c:out>";

arrayApresiacionResultado
		.push(objApresiacionResultado);
</script>
	</c:forEach>
		<tr bordercolor="#b8e9b9">
			<td colspan="2" style="width: 90%;"><div style="width: 100%; text-align: right;"><a href="javascript:mostrarDetalleApresiacion('<c:out value="${reporteAvance.reporteAvanceID }" ></c:out>')"
												class="linkSelecciona">Mostrar mas...</a></div></td>
			
		</tr>
			</tbody>
</table>