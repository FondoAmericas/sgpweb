<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
	arrayLeccionAprendida = new Array();

	var mensaje = "<c:out value="${mensaje }"></c:out>";
	if (mensaje != "") {
		alert(mensaje);
	};
</script>
<table class="table-clasico" width="100%">
	<caption>
		<label>Lista de Lecciones Aprendidas</label>
	</caption>
	<thead>
		<tr>
			<td style="text-align: center; width: 30%"><label>Tipo
					de Leccion</label></td>
			<td style="text-align: center; width: 60%"><label>Comentario</label>
			</td>
			<td style="text-align: center; width: 10%"><label>Opcion</label>
			</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listLeccionApendida}" var="leccionApendida"
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
				<td style="width: 30%; text-align: justify;"><label><c:out
							value="${leccionApendida.descripcionTipoLeccion }"></c:out> </label><br>
				</td>
				<td style="width: 60%; text-align: justify;"><label><c:out
							value="${leccionApendida.comentario }"></c:out> </label><br></td>
				<td style="width: 10%; text-align: justify;"><label>
			<a	href="javascript:agregarObservacion('<c:out value="${leccionApendida.leccionApendidaID }" ></c:out>','<c:out value="${datoProyectoId }" ></c:out>','10','42','<c:out value="${informeFinalId }" ></c:out>')"
							class="linkSelecciona">Observacion</a></label></td>
			</tr>
			</c:forEach>
	</tbody>
</table>
