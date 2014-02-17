<%@ include file="/common/taglibs.jsp"%>

<table border="0" width="100%" class="table-clasico">
	<caption>Lista de Organizaciones</caption>
	<thead>
		<tr>
			<td style="width: 20%; text-align: center;"><label style="font-size: 10px;">Nombre Organizacion</label></td>
			<td style="width: 60%; text-align: center;"><label style="font-size: 10px;">Situacion Final</label></td>
			<td style="width: 20%; text-align: center;"><label style="font-size: 10px;">Opcion</label></td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${listOrganizacion }" var="organizacion" varStatus="indice">
	<c:choose>
										<c:when test="${indice.count %2== 0}">
											<c:set var="classIdi" value="f2"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdi" value="f1"></c:set>
										</c:otherwise>
									</c:choose>
	<tr class="<c:out value="${classIdi}"></c:out>">
			<td style="width: 20%;text-align: left;"><label><c:out value="${organizacion.nombreOrganizacion }"></c:out></label><br></td>
			<td style="width: 60%;text-align: left;"><label><c:out value="${organizacion.situacionFinal }"></c:out></label><br></td>
			<td style="width: 20%;text-align: center;"><label>
			<a	href="javascript:agregarObservacion('<c:out value="${organizacion.organizacionID }" ></c:out>','<c:out value="${datoProyectoId }" ></c:out>','10','44','<c:out value="${informeFinalId }" ></c:out>')"
							class="linkSelecciona">Observacion</a></label></td>
		</tr>		
	</c:forEach>
			</tbody>
	</table>