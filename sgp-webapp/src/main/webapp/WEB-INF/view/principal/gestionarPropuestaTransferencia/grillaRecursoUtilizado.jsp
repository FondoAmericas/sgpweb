<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
var arrayBien = new Array();

$(document).ready(function() {
	ocultaCampos();
});
</script>

<script type="text/javascript">
	var mensaje = "<c:out value="${mensaje }"></c:out>";
	if (mensaje!=""){
		alert(mensaje);
	};
</script>
<table class="table-clasico" width="100%">
														<caption>
															<label>Recurso Utilizado por Fuente Financiadora</label>
														</caption>
														<thead>
															<tr>
																<td style="text-align: center; width: 55%"><label>Fuente
																		Financiadora</label>
																</td>
																<td style="text-align: center; width: 30%"><label>Monto</label>
																</td>
																<td style="text-align: center; width: 15%" class="hide"><label>Opciones</label>
																</td>
															</tr>
														</thead>
	<tbody>
		<c:forEach items="${listRecursoUtilizado}" var="recursoUtilizado"
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
							value="${recursoUtilizado.fuenteFinanciadora.institucion.nombreInstitucion }"></c:out>
				</label><br>
				</td>
				<td style="width: 55%; text-align: justify;"><label><c:out
							value="${recursoUtilizado.monto }"></c:out> <c:out
							value="${recursoUtilizado.descripcionTipoMoneda }"></c:out>
				</label><br>
				</td>
				<td style="width: 10%; text-align: justify;" class="hide"><label><a
						href="javascript:eliminarRecursoUtilizado('<c:out value="${recursoUtilizado.fuenteFinanciadora.fuenteFinanciadoraID }" ></c:out>')"
						id="modificatEfectoProyecto" class="linkSelecciona">Eliminar</a></label><br>
				</td>
			</tr>
			<script type="text/javascript">
				var objBien = new Object();
				objBien.monto = "<c:out value="${recursoUtilizado.monto }"></c:out>";
				
				arrayBien.push(objBien);
			</script>
		</c:forEach>
	</tbody>
</table>	
<input type="hidden" id="cantRecursoUtilizado" name="cantRecursoUtilizado" value="<c:out value="${cantRecursoUtilizado }"></c:out>">