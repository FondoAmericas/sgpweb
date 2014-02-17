<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
function mostrarDetalle(problemaSolucionId){
	var url = "mostrarDetalleProblemasSolucionesInformeFinal.jspx?problemaSolucionId=" + problemaSolucionId;
	var stiloPopUp = 'dialogWidth=800px; dialogHeight=700px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
	//ventanaPopUp = showModalDialog(url,'',stiloPopUp);//window.open(url,'',stiloPopUp);

	//window.opener.document.formWindowAsignaTutor.profesorAsignado.value = componenteId;

	//window.showModalDialog(url,datos,stiloPopUp);
	window.showModalDialog(url, "", stiloPopUp);
}

</script>
<c:choose>
	<c:when test="${cantProblemaSolucion > 0 }">
<table border="0" width="100%" class="table-clasico">
	<caption>Lista de problemas y soluciones del Proyecto</caption>
	<thead>
		<tr>
			<td style="width: 10%; text-align: center;"><label style="font-size: 10px;">Problema<br>Relevante</label></td> 
			<td style="width: 20%; text-align: center;"><label style="font-size: 10px;">Problema</label></td>
			<td style="width: 20%; text-align: center;"><label style="font-size: 10px;">Solucion</label></td>
			<td style="width: 22%; text-align: center;"><label style="font-size: 10px;">Resultado</label></td>
			<td style="width: 22%; text-align: center;"><label style="font-size: 10px;">Comentario</label></td>
			<td style="width: 6%; text-align: center;"><label style="font-size: 10px;">Opcion</label></td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${listProblemaSolucion }" var="problemaSolucion" varStatus="indice">
	<c:choose>
										<c:when test="${indice.count %2== 0}">
											<c:set var="classIdi" value="f2"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdi" value="f1"></c:set>
										</c:otherwise>
									</c:choose>
	<tr class="<c:out value="${classIdi}"></c:out>">
			<td style="width: 10%; text-align: center; vertical-align: middle;">
			<c:choose>
			<c:when test="${problemaSolucion.problemaRelevanteAlProy == 1}"><label style="color: blue;">Si</label></c:when>
			<c:otherwise><label style="color:red;">No</label></c:otherwise>
			</c:choose>
			</td> 
			<td style="width: 20%;text-align: justify;"><label><c:out value="${problemaSolucion.problema }"></c:out>...</label><br></td>
			<td style="width: 20%;text-align: justify;"><label><c:out value="${problemaSolucion.solucion }"></c:out>...</label><br></td>
			<td style="width: 22%;text-align: justify;"><label><c:out value="${problemaSolucion.resultado }"></c:out>...</label><br></td>
			<td style="width: 22%;text-align: justify;"><label><c:out value="${problemaSolucion.comentario }"></c:out>...</label><br></td>
			<td style="width: 6%; text-align: center;"><label>
			<a	href="javascript:agregarObservacion('<c:out value="${problemaSolucion.problemaSolucionID }" ></c:out>','<c:out value="${datoProyectoId }" ></c:out>','10','38','<c:out value="${informeFinalId }" ></c:out>')"
														class="linkSelecciona">Observacion</a></label></td>
		</tr>
		<tr class="<c:out value="${classIdi}"></c:out>">
			<td colspan="6" style="width: 100%;"><div style="width: 100%; text-align: right;"><a href="javascript:mostrarDetalle('<c:out value="${problemaSolucion.problemaSolucionID }" ></c:out>')"
												class="linkSelecciona">Mostrar mas...</a></div></td>
		</tr>		
	</c:forEach>
			</tbody>
	</table>
	
	</c:when>
	<c:otherwise><label style="color: red; font-size: 13px;font-weight: bold;">No se encontraron problemas registrados</label></c:otherwise>
</c:choose> 