<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
	var mensaje = "<c:out value="${mensaje }"></c:out>";
	if (mensaje != "") {
		alert(mensaje);
	};

	arrayResumenProyecto = new Array();
	
	$("#cantidadResumenPerfil").attr("value","<c:out value="${cantidadResumenPerfil }"></c:out>");
</script>
<input type="hidden" id="cantidadResumenPerfil" name="cantidadResumenPerfil" value='<c:out value="${cantidadResumenPerfil }"></c:out>'>
<table class="table-clasico" width="100%">
	<caption>
		<label>Lista de Resumenes y Observaciones del Proyecto</label>
	</caption>
	<thead>
		<tr>
			<td style="text-align: center; width: 25%"><label>Tipo
					de Resumen</label></td>
			<td style="text-align: center; width: 65%"><label>Resumen</label>
			</td>
			<td style="text-align: center; width: 10%"><label>Opciones</label>
			</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listResumenProyecto}" var="resumenProyecto"
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
				<td style="width: 25%; text-align: left;"><label><c:out
							value="${resumenProyecto.descripcionTipoResumenProy }"></c:out> </label><br>
				</td>
				<td style="width: 65%; text-align: left;"><label><c:out
							value="${resumenProyecto.definicion }"></c:out> </label><br></td>
				<td style="width: 10%; text-align: center;"><label><a
						href="javascript:modificarResumenProyecto('<c:out value="${resumenProyecto.definicion }" ></c:out>','<c:out value="${resumenProyecto.fkIdtablaespTipoResumenProy }"></c:out>')"
						id="modificarResumenProyecto" class="linkSelecciona">Modificar</a><br />
						<a
						href="javascript:eliminarResumenProyecto('<c:out value="${resumenProyecto.fkIdtablaespTipoResumenProy }" ></c:out>')"
						id="eliminarRegistro" class="linkSelecciona">Eliminar</a>
				</label><br></td>
			</tr>
			<script type="text/javascript">
				var objResumenProyecto = new Object();
				objResumenProyecto.tipoResumenProyecto = "<c:out value="${resumenProyecto.fkIdtablaespTipoResumenProy}"></c:out>";
				objResumenProyecto.resumenProyecto = "<c:out value="${resumenProyecto.definicion}"></c:out>";

				arrayResumenProyecto.push(objResumenProyecto);
			</script>
		</c:forEach>
	</tbody>
</table>
