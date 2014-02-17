<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
	var mensaje = "<c:out value="${mensaje }"></c:out>";
	if (mensaje!=""){
		alert(mensaje);
	};
	
	arrayEvaluacionFinal = new Array();
</script>
<table class="table-clasico" width="100%">
														<caption>
															<label>Lista de Evaluacion Final</label>
														</caption>
														<thead>
															<tr>
																<td style="text-align: center; width: 20%"><label>Tipo de Evaluacion</label>
																</td>
																<td style="text-align: center; width: 20%"><label>Descripcion Tipo <br/>de Evaluacion</label>
																</td>
																<td style="text-align: center; width: 55%"><label>Comentario</label>
																</td>
																<td style="text-align: center; width: 5%"><label>Opcion</label>
																</td>
															</tr>
														</thead>
	<tbody>
		<c:forEach items="${listEvaluacionFinal}" var="evaluacionFinal"
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
							value="${evaluacionFinal.descripcionEf.descripcionEvalFinal }"></c:out>
				</label><br>
				</td>
				<td style="width: 20%; text-align: left;"><label><c:out
							value="${evaluacionFinal.descripcionEf.descripcionFinal }"></c:out>
				</label><br>
				</td>
				<td style="width: 55%; text-align: justify;"><label><c:out
							value="${evaluacionFinal.comentario }"></c:out>
				</label><br>
				</td>
				<td style="width: 5%; text-align: justify;"><label>
			<a	href="javascript:agregarObservacion('<c:out value="${evaluacionFinal.evaluacionFinalID }" ></c:out>','<c:out value="${datoProyectoId }" ></c:out>','10','41','<c:out value="${informeFinalId }" ></c:out>')"
							class="linkSelecciona">Observacion</a></label>
														</td>
			</tr>
		</c:forEach>
	</tbody>
</table>	