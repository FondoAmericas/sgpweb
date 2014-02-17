<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
/*function mostrarDetalle(problemaSolucionId){
	var url = "mostrarDetalleProblemasSoluciones.jspx?problemaSolucionId=" + problemaSolucionId;
	var stiloPopUp = 'dialogWidth=800px; dialogHeight=700px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
	//ventanaPopUp = showModalDialog(url,'',stiloPopUp);//window.open(url,'',stiloPopUp);

	//window.opener.document.formWindowAsignaTutor.profesorAsignado.value = componenteId;

	//window.showModalDialog(url,datos,stiloPopUp);
	window.showModalDialog(url, "", stiloPopUp);
}*/
</script>

<table border="0" width="100%" class="table-clasico">
	<caption>Lista de Observaciones del Proyecto</caption>
	<thead>
		<tr>
			<td style="width: 5%; text-align: center;"><label style="font-size: 10px;" >Det</label></td>
			<td style="width: 5%; text-align: center;" ><label style="font-size: 10px;" title="Observacion Relevante al Documento">Rel<br/>Doc</label></td>
			<td style="width: 45%; text-align: center;"><label style="font-size: 10px;">Observacion</label></td>
			<td style="width: 15%; text-align: center;"><label style="font-size: 10px;">Ubicacion</label></td>
			<td style="width: 10%; text-align: center;"><label style="font-size: 10px;">Estado</label></td>
			<td style="width: 15%; text-align: center;"><label style="font-size: 10px;">Usuario</label></td>
			<td style="width: 10%; text-align: center;"><label style="font-size: 10px;">Atencion</label></td> 
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
			<td style="width: 5%; text-align: center;">
						<a
					href="javascript:expandcollapse('div<c:out value="${observacion.observacionID }"></c:out>', 'one');">
						<img
						id='imgdiv<c:out value="${observacion.observacionID }"></c:out>'
						border="0" src="<c:url value="/images/Plus001.gif" />"
						width="15px" /> </a>
						</td>
			<td style="width: 5%;text-align: center;">
			<c:choose>
			<c:when test="${observacion.relevanteProyecto == 1}"><label style="color: blue;">Si</label></c:when>
			<c:otherwise><label style="color: red;">No</label></c:otherwise>
			</c:choose>
			</td>
			<td style="width: 45%;text-align: left;"><label><c:out value="${observacion.descripcion }"></c:out></label></td>
			<td style="width: 15%;text-align: left;"><label><c:out value="${observacion.tablaClase.titulo }"></c:out></label></td>
			<td style="width: 15%;text-align: left;"><label><c:out value="${observacion.descripcionEstado }"></c:out></label></td>
			<td style="width: 15%;text-align: left;"><label><c:out value="${observacion.usuario.datoUsuario.nombre }"></c:out>  <c:out value="${observacion.usuario.datoUsuario.paterno }"></c:out>  <c:out value="${observacion.usuario.datoUsuario.materno }"></c:out></label></td>
			<td style="width: 10%;text-align: center;"><label>
			<c:choose>
				<c:when test="${observacion.flagEstado != '-1'  }"><input type="checkbox" id="observacionLevantada<c:out value="${observacion.observacionID }"></c:out>" name="observacionLevantada<c:out value="${observacion.observacionID }"></c:out>" onchange="aprobarObservacion('${observacion.observacionID }')"
			<c:if test="${userSession.usuarioID != observacion.usuario.usuarioID }">disabled="disabled"</c:if> 
			<c:if test="${observacion.flagEstado == '1'  }">checked="checked" disabled="disabled"</c:if>></c:when>
			<c:otherwise>NO Resuelto<br/> por Ejecutor</c:otherwise>
			</c:choose>
			<!--<c:if test="${observacion.fechaLevantamientoObservacionString != fechaActual && observacion.fechaLevantamientoObservacionString != '0' }">disabled="disabled"</c:if>//con esta linea no permite modificar la observacion si la fecha de levantamiento no es la actual-->
			</label><br></td> 
		</tr>
		<tr class="<c:out value="${classIdi}"></c:out>">
				<td colspan="100%">
					<div
						id='div<c:out value="${observacion.observacionID }"></c:out>'
						style="display: none; position: relative; overflow: auto; padding-left: 15px; padding-bottom: 15px; width: 97%;">
						<table width="96%">
							<tr>
								<td style="width: 30%; font-weight: bold; text-align:right; "><label>Descripcion Ejecutor:</label></td>
								<td style="width: 70%; text-align:left;"><label><c:out value="${observacion.descripcionEjecutor }"></c:out></label></td>
							</tr>
							<tr>
								<td style="width: 25%; font-weight: bold; text-align:right; "><label>Observacion ubicada en:</label></td>
								<td style="width: 75%; text-align:left;"><label><c:out value="${observacion.descripcionTablaProfundidades }"></c:out></label></td>
							</tr>
							<tr>
								<td style="width: 25%; font-weight: bold; text-align:right;"><label>Fecha de Observacion:</label></td>
								<td style="width: 75%; text-align:left;"><label><c:out value="${observacion.fechaObservacionString }"></c:out> (dd/mm/aaaa)</label></td>
							</tr>
							<tr>
								<td style="width: 30%; font-weight: bold; text-align:right;"><label>Fecha Atendido por Ejecutor:</label></td>
								<td style="width: 70%; text-align:left;"><label><c:out value="${observacion.fechaAtencionObservacionString }"></c:out> (dd/mm/aaaa)</label></td>
							</tr>
							<tr>
								<td style="width: 30%; font-weight: bold; text-align:right;"><label>Fecha Aprobado por Fondam:</label></td>
								<td style="width: 70%; text-align:left;"><label><c:out value="${observacion.fechaLevantamientoObservacionString }"></c:out> (dd/mm/aaaa)</label></td>
							</tr>
						</table>
					</div>
				</td>
		</tr>				
	</c:forEach>
			</tbody>
	</table>