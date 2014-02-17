<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		ocultaCampos();
	});
</script>

<script type="text/javascript">
	var mensaje = "<c:out value="${mensaje }"></c:out>";
	if (mensaje!=""){
		alert(mensaje);
	};
	arrayConclusionFinal = new Array();
</script>
<table class="table-clasico" width="100%">
														<caption>
															<label>Lista de Conclusiones</label>
														</caption>
														<thead>
															<tr>
																<td style="text-align: center; width: 20%"><label>Tipo de Conclusion</label>
																</td>
																<td style="text-align: center; width: 20%"><label>Detalle Tipo <br/>de Conclusion</label>
																</td>
																<td style="text-align: center; width: 55%"><label>Comentario</label>
																</td>
																<td style="text-align: center; width: 5%"  class="hide"><label>Opciones</label>
																</td>
															</tr>
														</thead>
	<tbody>
		<c:forEach items="${listConclusion}" var="conclusion"
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
				<td style="width: 20%; text-align: left;"><label><c:out
							value="${conclusion.detalleConcluIf.descripcionCabeceraConcluIF }"></c:out>
				</label><br>
				</td>
				<td style="width: 20%; text-align: left;"><label><c:out
							value="${conclusion.detalleConcluIf.descripcionConclusion }"></c:out>
				</label><br>
				</td>
				<td style="width: 55%; text-align: justify;"><label><c:out
							value="${conclusion.comentario }"></c:out>
				</label><br>
				</td>
				<td style="width: 5%; text-align: justify;"  class="hide"><label><a
						href="javascript:modificarConclusion('<c:out value="${conclusion.conclusionID }" ></c:out>','<c:out value="${conclusion.comentario }" ></c:out>','<c:out value="${conclusion.detalleConcluIf.detalleConcluIFID }" ></c:out>','<c:out value="${conclusion.detalleConcluIf.flidtablaespcabeceraconcluIF }" ></c:out>')"
						id="modificatEfectoProyecto" class="linkSelecciona">Modificar</a><br />
						<a
						href="javascript:eliminarRegistroInformeFinal('<c:out value="${conclusion.conclusionID }" ></c:out>','conclusion')"
						id="eliminarRegistro" class="linkSelecciona">Eliminar</a></label><br>
				</td>
			</tr>
			<script type="text/javascript">
var objConclusionFinal = new Object();
objConclusionFinal.detalleConcluIFID = "<c:out value="${conclusion.detalleConcluIf.detalleConcluIFID }"></c:out>";

arrayConclusionFinal.push(objConclusionFinal);

</script>
		</c:forEach>
	</tbody>
</table>	