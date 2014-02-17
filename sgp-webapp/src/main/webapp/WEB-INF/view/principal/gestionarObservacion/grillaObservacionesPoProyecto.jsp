<%@ include file="/common/taglibs.jsp"%>

<script>
var arrayProyectos = new Array();

</script>

<table border="0" width="100%" class="table-clasico">
	<caption>Listado proyectos del programa</caption>
	<thead>
		<tr>
			<td style="width: 55%; text-align: center;"><label>Proyecto</label></td>
			<td style="width: 20%; text-align: center;"><label>Cod.Proyecto</label></td>
			<td style="width: 5%; text-align: center;"><label>Duracion</label></td>
			<td style="width: 5%; text-align: center;"><label>Cant.Periodos</label></td>
			<td style="width: 10%; text-align: center;"><label>Cant.Observaciones<br/>Sin Aprobar</label></td>
			<td style="width: 5%; text-align: center;"><label>Mostrar<br/>Observaciones</label></td>    
		</tr>
	</thead>

	  <tbody>
			<c:forEach items="${lstProyectosPorPrograma }" var="proyecto" varStatus="indice">
				<c:choose>
					<c:when test="${indice.count %2== 0}">
						<c:set var="classIdi" value="f2"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="classIdi" value="f1"></c:set>
					</c:otherwise>
				</c:choose>
				
				<tr  class="<c:out value="${classIdi}"></c:out>">
						<td style="width: 55%; text-align: left;">
							<label><c:out value="${proyecto.nombreProyecto }"></c:out></label>
						</td>
						<td style="width: 20%; text-align: center;">
						    <label><c:out value="${proyecto.codigoProyecto }"></c:out></label>
						</td>
						<td style="width: 5%; text-align: center;">
							<label><c:out value="${proyecto.duracionProyecto }"></c:out></label>
						</td>
				 		<td style="width: 5%; text-align: center;">
							<label><c:out value="${proyecto.cantidadPeriodo }"></c:out></label>
						</td>
						<td style="width: 15%; text-align: center;">
							<label><c:out value="${proyecto.cantObservaciones }"></c:out></label>
						</td>
						<td style="width: 5%; text-align: center;"><label>
			<a	href="javascript:mostrarObservacion('${proyecto.datoProyectoID }')" class="linkSelecciona">Mostrar</a></label></td>
				</tr>
		</c:forEach>
	  </tbody>   
</table>