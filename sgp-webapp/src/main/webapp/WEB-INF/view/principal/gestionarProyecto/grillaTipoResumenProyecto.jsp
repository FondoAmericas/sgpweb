	<%@ include file="/common/taglibs.jsp"%>
	<script type="text/javascript">
	ocultaCampos();
	arrayResumenPerfil = new Array();
	
</script>
<table class="table-clasico" width="100%">
								<!-- class="table-clasico"-->
								<caption>
									<label>Lista de Resumenes y Observaciones</label>
								</caption>
								<thead>
									<tr>
										<td style="width: 3%; text-align: center;"><label>Nro</label>
										</td>
										<td style="width: 20%; text-align: center;"><label>Tipo.</label>
										</td>
										<td style="width: 72%; text-align: center;"><label>Resumen</label>
										</td>
										<td style="width: 5%; text-align: center;" class="hide"><label>Opciones</label>
										</td>
									</tr>
								</thead>
								<tbody>	
	<c:forEach items="${listResumenProyectoBean }" var="resumenProyectoBean" varStatus="indice">
	<c:choose>
										<c:when test="${indice.count %2== 0}">
											<c:set var="classIdi" value="f2"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdi" value="f1"></c:set>
										</c:otherwise>
									</c:choose>
	<tr class="<c:out value="${classIdi}"></c:out>">
					<td style="width: 3%; text-align: center;"><label><c:out value="${indice.count }"></c:out> </label>
							</td>
							<td style="width: 20%; text-align: left;"><label><c:out value="${resumenProyectoBean.descripcionTipoResumenProy }"></c:out> </label>
							</td>
							<td style="width: 72%; text-align: left;"><label><c:out value="${resumenProyectoBean.definicion }"></c:out> </label>
							</td>
							
					<td style="width: 10%; text-align: center;" class="hide"><label>
			<a href="javascript:eliminarResumenProyecto('<c:out value="${resumenProyectoBean.resumenProyectoID  }" ></c:out>')" 
			class="linkSelecciona" >Eliminar</a><br>
			<a href="javascript:modificarResumenProyecto('<c:out value="${resumenProyectoBean.fkIdtablaespTipoResumenProy  }" ></c:out>')" 
			 class="linkSelecciona">Modificar</a></label></td>
		</tr>
				<script type="text/javascript">
var objResumenPerfil = new Object();
objResumenPerfil.arrayId= "<c:out value="${indice.count }"></c:out>";
objResumenPerfil.resumenProyectoId = "<c:out value="${resumenProyectoBean.resumenProyectoID }"></c:out>";
objResumenPerfil.tipoResumenId = "<c:out value="${resumenProyectoBean.fkIdtablaespTipoResumenProy }"></c:out>";
objResumenPerfil.resumenProyecto = "<c:out value="${resumenProyectoBean.definicion }"></c:out>";


arrayResumenPerfil
		.push(objResumenPerfil);
</script>
	</c:forEach>
								</tbody>
							</table>