<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
	var mensaje = "<c:out value="${mensaje }"></c:out>";
	if (mensaje != "") {
		alert(mensaje);
	};

	arrayMaterialProducido = new Array();
</script>
<table class="table-clasico" width="100%">
	<caption>
		<label>Lista de Materiales Producidos</label>
	</caption>
	<thead>
		<tr>
			<td style="text-align: center; width: 15%"><label>Tipo
					de Material</label></td>
			<td style="text-align: center; width: 15%"><label>Cantidad</label>
			</td>
			<td style="text-align: center; width: 65%"><label>Descripcion</label>
			</td>
			<td style="text-align: center; width: 5%"><label>Opcion</label>
			</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listMaterialProducido}" var="materialProducido"
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
				<td style="width: 15%; text-align: justify;"><label><c:out
							value="${materialProducido.descripcionTipoMaterial }"></c:out> </label><br>
				</td>
				<td style="width: 15%; text-align: center;"><label><c:out
							value="${materialProducido.cantidad }"></c:out> </label><br></td>
				<td style="width: 65%; text-align: justify;"><label><c:out
							value="${materialProducido.descripcionMaterialProducido }"></c:out>
				</label><br></td>
				<td style="width: 5%; text-align: justify;"><label>
			<a	href="javascript:agregarObservacion('<c:out value="${materialProducido.materialProducidoID }" ></c:out>','<c:out value="${datoProyectoId }" ></c:out>','10','40','<c:out value="${informeFinalId }" ></c:out>')"
														class="linkSelecciona">Observacion</a></label></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
