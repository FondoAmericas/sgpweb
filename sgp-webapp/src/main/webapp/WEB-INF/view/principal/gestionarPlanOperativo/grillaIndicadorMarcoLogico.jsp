
<%@ include file="/common/taglibs.jsp"%>

	
<c:forEach items="${listIndicadorMarcoLogico }"
	var="indicadorMarcoLogico" varStatus="indice">
	<c:choose>
		<c:when test="${indice.count %2== 0}">
			<c:set var="classIdi" value="f2"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="classIdi" value="f1"></c:set>
		</c:otherwise>
	</c:choose>
	<tr class="<c:out value="${classIdi}"></c:out>">

		<td style="width: 15%; text-align: left;"><label><c:out
					value="${indicadorMarcoLogico.indicador }"></c:out>
		</label></td>
		<td style="width: 25%; text-align: left;"><label><c:out
					value="${indicadorMarcoLogico.definicionIndicador }"></c:out>
		</label></td>
		<td style="width: 15%; text-align: left;"><label><c:out
					value="${indicadorMarcoLogico.medioVerificacion }"></c:out>
		</label></td>
		<td style="width: 20%; text-align: left;"><label><c:out
					value="${indicadorMarcoLogico.metodoCalculo }"></c:out>
		</label></td>
		<td style="width: 10%; text-align: center;"><label><c:out
					value="${indicadorMarcoLogico.situacionActualDescripcion }"></c:out>
		</label></td>
		<td style="width: 10%; text-align: center;"><label><c:out
					value="${indicadorMarcoLogico.situacionFinalDescripcion }"></c:out>
		</label></td>
		<td style="width: 5%; text-align: center;"  class="hide" ><label> <a
				href="javascript:eliminarIndicador('<c:out value="${indicadorMarcoLogico.indicadorMarcoLogicoID }"></c:out>','<c:out value="${indicadorMarcoLogico.indicador }"></c:out>')"
				class="linkSelecciona">Eliminar</a><br> <a
				href="javascript:modificarIndicador('<c:out value="${indicadorMarcoLogico.indicadorMarcoLogicoID }"></c:out>','<c:out value="${indicadorMarcoLogico.unidadMedida }"></c:out>','<c:out value="${indicadorMarcoLogico.indicador }"></c:out>','<c:out value="${indicadorMarcoLogico.definicionIndicador }"></c:out>','<c:out value="${indicadorMarcoLogico.medioVerificacion }"></c:out>',
			'<c:out value="${indicadorMarcoLogico.metodoCalculo }"></c:out>','<c:out value="${indicadorMarcoLogico.situacionActural }"></c:out>','<c:out value="${indicadorMarcoLogico.situacionFinal }"></c:out>')"
				class="linkSelecciona">Modificar</a> </label></td>
	</tr>
</c:forEach>
