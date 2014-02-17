<%@ include file="/common/taglibs.jsp"%>
	<script type="text/javascript">
	ocultaCampos();
	
</script>

<table border="0" width="100%" class="table-clasico">
	<caption>Lista de recursos del ejecutor</caption>
	<thead>
		<tr>
			<td style="width: 30%; text-align: center;"><label style="font-size: 10px;">Descripcion</label></td>
			<td style="width: 30%; text-align: center;"><label style="font-size: 10px;">Ubicacion</label></td>
			<td style="width: 15%; text-align: center;"><label style="font-size: 10px;">Categoria<br/>Activo</label></td>
			<td style="width: 15%; text-align: center;"><label style="font-size: 10px;">Bien/Activo</label></td>
			<td style="width: 10%; text-align: center;" class="hide"><label style="font-size: 10px;">Eli<br>Mod</label></td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${listInfraestructuraPo }" var="infraestructura" varStatus="indice">
	<c:choose>
										<c:when test="${indice.count %2== 0}">
											<c:set var="classIdi" value="f2"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdi" value="f1"></c:set>
										</c:otherwise>
									</c:choose>
	<tr class="<c:out value="${classIdi}"></c:out>">
			<td style="width: 30%;text-align: left;"><label><c:out value="${infraestructura.descripcion }"></c:out></label><br></td>
			<td style="width: 30%;text-align: left;"><label><c:out value="${infraestructura.ubicacion }"></c:out></label><br></td>
			<td style="width: 15%;text-align: center;"><label><c:out value="${infraestructura.descripcionCategoriaActivo }"></c:out></label><br></td>
			<td style="width: 15%;text-align: center;"><label><c:out value="${infraestructura.descripcionActivo }"></c:out></label><br></td>
			<td style="width: 10%; text-align: center;" class="hide"><label >
			<a
						href="javascript:modificarRecursoEjecutor('<c:out value="${infraestructura.infraestructuraPOID }"></c:out>',
																'<c:out value="${infraestructura.descripcion }"></c:out>',
																'<c:out value="${infraestructura.ubicacion }"></c:out>',
																'<c:out value="${infraestructura.activo.activoID }"></c:out>',
																'<c:out value="${infraestructura.activo.fkIdtablaespCategoriaActivo }"></c:out>')"
						id="modificarRecursoEjecutor" class="linkSelecciona">Modificar</a><br>
			<a
						href="javascript:eliminarRecursoEjecutor('<c:out value="${infraestructura.infraestructuraPOID }"></c:out>')"
						id="eliminarRecursoEjecutor" class="linkSelecciona">Eliminar</a></label></td>
		</tr>
			</c:forEach>
					</tbody>
</table>