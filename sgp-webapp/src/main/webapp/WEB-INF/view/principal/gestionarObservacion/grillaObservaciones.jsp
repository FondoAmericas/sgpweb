<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
function mostrarDetalle(problemaSolucionId){
	var url = "mostrarDetalleProblemasSoluciones.jspx?problemaSolucionId=" + problemaSolucionId;
	var stiloPopUp = 'dialogWidth=800px; dialogHeight=700px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
	//ventanaPopUp = showModalDialog(url,'',stiloPopUp);//window.open(url,'',stiloPopUp);

	//window.opener.document.formWindowAsignaTutor.profesorAsignado.value = componenteId;

	//window.showModalDialog(url,datos,stiloPopUp);
	window.showModalDialog(url, "", stiloPopUp);
}
</script>

<table border="0" width="100%" class="table-clasico">
	<caption>Lista de Observaciones del Proyecto</caption>
	<thead>
		<tr>
			<td style="width: 5%; text-align: center;" ><label style="font-size: 10px;" title="Observacion Relevante al Documento">Rel<br/>Doc</label></td>
			<td style="width: 50%; text-align: center;"><label style="font-size: 10px;">Observacion</label></td>
			<td style="width: 15%; text-align: center;"><label style="font-size: 10px;">Ubicacion</label></td>
			<td style="width: 10%; text-align: center;"><label style="font-size: 10px;">Estado</label></td>
			<td style="width: 15%; text-align: center;"><label style="font-size: 10px;">Usuario</label></td>
			<!-- <td style="width: 10%; text-align: center;"><label style="font-size: 10px;">Atencion</label></td> -->
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${listObservaciones }" var="observacion" varStatus="indice">
	<c:choose>
										<c:when test="${indice.count %2== 0}">
											<c:set var="classIdi" value="f2"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdi" value="f1"></c:set>
										</c:otherwise>
									</c:choose>
	<tr class="<c:out value="${classIdi}"></c:out>">
			<td style="width: 5%;text-align: center;">
			<c:choose>
			<c:when test="${observacion.relevanteProyecto == 1}"><label style="color: blue;">Si</label></c:when>
			<c:otherwise><label style="color: red;">No</label></c:otherwise>
			</c:choose>
			</td>
			<td style="width: 50%;text-align: left;"><label><c:out value="${observacion.descripcion }"></c:out></label></td>
			<td style="width: 15%;text-align: left;"><label><c:out value="${observacion.tablaClase.titulo }"></c:out></label></td>
			<td style="width: 15%;text-align: left;"><label><c:out value="${observacion.descripcionEstado }"></c:out></label></td>
			<td style="width: 15%;text-align: left;"><label><c:out value="${observacion.usuario.datoUsuario.nombre }"></c:out>  <c:out value="${observacion.usuario.datoUsuario.paterno }"></c:out>  <c:out value="${observacion.usuario.datoUsuario.materno }"></c:out></label></td>
			<!-- <td style="width: 10%;text-align: center;"><label>
			<input type="checkbox" id="observacionLevantada" name="observacionLevantada" 
			<c:if test="${userSession.usuarioID != observacion.usuario.usuarioID }">disabled="disabled"</c:if>>
			</label><br></td> -->
		</tr>
	</c:forEach>
			</tbody>
	</table>