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
	<caption>Lista de problemas y soluciones de Reporte del periodo <c:out value="${reporteAvance.periodo }"></c:out> </caption>
	<thead>
		<tr>
			<!-- <td style="width: 6%; text-align: center;"><label style="font-size: 10px;">Rel <br>al Proy</label></td> -->
			<td style="width: 22%; text-align: center;"><label style="font-size: 10px;">Problema</label></td>
			<td style="width: 22%; text-align: center;"><label style="font-size: 10px;">Solucion</label></td>
			<td style="width: 22%; text-align: center;"><label style="font-size: 10px;">Resultado</label></td>
			<td style="width: 22%; text-align: center;"><label style="font-size: 10px;">Comentario</label></td>
			<td style="width: 6%; text-align: center;" class="hide"><label style="font-size: 10px;" >Eli<br>Mod</label></td>
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
			<!-- <td style="width: 6%; text-align: center;"><label>
			<c:choose>
			<c:when test="${problemaSolucion.problemaRelevanteAlProy == 1}">Si</c:when>
			<c:otherwise>No</c:otherwise>
			</c:choose>
			</label>
			</td> -->
			<td style="width: 22%;text-align: justify;"><label><c:out value="${problemaSolucion.problema }"></c:out>...</label><br></td>
			<td style="width: 22%;text-align: justify;"><label><c:out value="${problemaSolucion.solucion }"></c:out>...</label><br></td>
			<td style="width: 22%;text-align: justify;"><label><c:out value="${problemaSolucion.resultado }"></c:out>...</label><br></td>
			<td style="width: 22%;text-align: justify;"><label><c:out value="${problemaSolucion.comentario }"></c:out>...</label><br></td>
			<td style="width: 6%; text-align: center;" class="hide"><label >
			<a
						href="javascript:eliminarProblemasSoluciones('<c:out value="${problemaSolucion.problemaSolucionID }"></c:out>')"
						id="modificarProblemasSoluciones" class="linkSelecciona">Eliminar</a><br><br>
			<a
						href="javascript:modificarProblemasSoluciones('<c:out value="${problemaSolucion.problemaSolucionID }"></c:out>','<c:out value="${problemaSolucion.problema }" ></c:out>','<c:out value="${problemaSolucion.solucion }"></c:out>','<c:out value="${problemaSolucion.resultado }"></c:out>','<c:out value="${problemaSolucion.comentario }"></c:out>')"
						id="modificarProblemasSoluciones" class="linkSelecciona">Modificar</a></label></td>
		</tr>
		<tr class="<c:out value="${classIdi}"></c:out>">
			<td colspan="4" style="width: 100%;"><div style="width: 100%; text-align: right;"><a href="javascript:mostrarDetalle('<c:out value="${problemaSolucion.problemaSolucionID }" ></c:out>')"
												class="linkSelecciona">Mostrar mas...</a></div></td>
		</tr>		
	</c:forEach>
			</tbody>
	</table>