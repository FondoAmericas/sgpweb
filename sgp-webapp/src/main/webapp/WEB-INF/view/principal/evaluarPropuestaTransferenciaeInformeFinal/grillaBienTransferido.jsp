<%@ include file="/common/taglibs.jsp"%>

<table border="0" width="100%" class="table-clasico">
	<caption>Lista de Bienes Transferidos</caption>
	<thead>
		<tr>
			<td style="width: 20%; text-align: center;"><label
				style="font-size: 10px;">Bien Transferido</label></td>
			<td style="width: 15%; text-align: center;"><label
				style="font-size: 10px;">Beneficiarios</label></td>
			<td style="width: 20%; text-align: center;"><label
				style="font-size: 10px;">Organizacion</label></td>
			<td style="width: 35%; text-align: center;"><label
				style="font-size: 10px;">Observaciones</label></td>	
			<td style="width: 10%; text-align: center;"><label
				style="font-size: 10px;">Opcion</label></td>	
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listOrgBienTranferido }" var="orgBienTranferido" varStatus="indice">
			<c:choose>
				<c:when test="${indice.count %2== 0}">
					<c:set var="classIdi" value="f2"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="classIdi" value="f1"></c:set>
				</c:otherwise>
			</c:choose>
			<tr class="<c:out value="${classIdi}"></c:out>">
				<td style="width: 20%; text-align: justify;"><label><c:out
							value="${orgBienTranferido.propuestaTransferenciaBien.bien.descripcionBien }"></c:out> </label><br></td>
				<td style="width: 15%; text-align: justify;"><label><c:out
							value="${orgBienTranferido.propuestaTransferenciaBeneficiario.cantidadFinal }"></c:out> - <c:out
							value="${orgBienTranferido.propuestaTransferenciaBeneficiario.beneficiariosPorResultado.descripcionEstrato }"></c:out></label><br>
				</td>
				<td style="width: 20%; text-align: justify;"><label><c:out value="${orgBienTranferido.organizacion.nombreOrganizacion }"></c:out></label><br>
				</td>
				<td style="width: 35%; text-align: justify;"><label><c:out value="${orgBienTranferido.observaciones }"></c:out></label><br>
				</td>
				<td style="width: 10%; text-align: justify;"><label>
			<a	href="javascript:agregarObservacion('<c:out value="${orgBienTranferido.orgBienTranferidoID }" ></c:out>','<c:out value="${datoProyectoId }" ></c:out>','11','37','<c:out value="${propuestaTransferenciaId }" ></c:out>')"
														class="linkSelecciona">Observacion</a></label><br>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>