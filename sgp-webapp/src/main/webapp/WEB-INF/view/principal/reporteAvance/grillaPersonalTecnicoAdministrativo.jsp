<%@ include file="/common/taglibs.jsp"%>


<table border="0" width="100%" class="table-clasico">
	<caption>Lista de Personal Tecnico Administrativo</caption>
	<thead>
		<tr>
			<td style="width: 15%; text-align: center;"><label
				style="font-size: 10px;">Nombre Completo</label>
			</td>
			<td style="width: 10%; text-align: center;"><label
				style="font-size: 10px;">Formacion Profesional</label>
			</td>
			<td style="width: 10%; text-align: center;"><label
				style="font-size: 10px;">Responsabilidad</label>
			</td>
			<td style="width: 10%; text-align: center;"><label
				style="font-size: 10px;">Tiempo Dedicado</label>
			</td>
			<td style="width: 5%; text-align: center;"><label
				style="font-size: 10px;">% Participacion</label>
			</td>
			<td style="width: 10%; text-align: center;"><label
				style="font-size: 10px;">Etapa</label>
			</td>
			<td style="width: 15%; text-align: center;"><label
				style="font-size: 10px;">Personal<br>Reemplazo</label>
			</td>
			<td style="width: 10%; text-align: center;"><label
				style="font-size: 10px;">Salario</label>
			</td>
			<td style="width: 10%; text-align: center;"><label
				style="font-size: 10px;">Institucion</label>
			</td>
			<td style="width: 5%; text-align: center;" class="hide"><label
				style="font-size: 10px;" >Opciones</label>
			</td>
		</tr>
	</thead>
	<tbody>

		<c:forEach items="${listPersonalTecnicoAdministrativoBean }"
			var="personalTecnicoAdministrativoBean" varStatus="indice">
			<c:choose>
				<c:when test="${indice.count %2== 0}">
					<c:set var="classIdi" value="f2"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="classIdi" value="f1"></c:set>
				</c:otherwise>
			</c:choose>
			<tr class="<c:out value="${classIdi}"></c:out>">
				<td style="width: 15%; text-align: center;"><label
				style="font-size: 10px;"><c:out value="${personalTecnicoAdministrativoBean.nombreCompleto }"></c:out></label>
			</td>
			<td style="width: 10%; text-align: center;"><label
				style="font-size: 10px;"><c:out value="${personalTecnicoAdministrativoBean.formacionProfesionalNombre }"></c:out></label>
			</td>
			<td style="width: 10%; text-align: center;"><label
				style="font-size: 10px;"><c:out value="${personalTecnicoAdministrativoBean.responsabilidad }"></c:out></label>
			</td>
			<td style="width: 10%; text-align: center;"><label
				style="font-size: 10px;"><c:out value="${personalTecnicoAdministrativoBean.etapaPersonalTecnicoNombre }"></c:out> </label>
			</td>
			<td style="width: 5%; text-align: center;"><label
				style="font-size: 10px;"><c:out value="${personalTecnicoAdministrativoBean.porcentageParticipacion }"></c:out></label>
			</td>
			<td style="width: 10%; text-align: center;"><label
				style="font-size: 10px;"><c:out value="${personalTecnicoAdministrativoBean.etapaPersonalTecnicoNombre }"></c:out></label>
			</td>
			<td style="width: 15%; text-align: center;"><label
				style="font-size: 10px;"><c:out value="${personalTecnicoAdministrativoBean.personalTecnicoAdmReemplazoNombre }"></c:out></label>
			</td>
			<td style="width: 10%; text-align: center;"><label
				style="font-size: 10px;"><c:out value="${personalTecnicoAdministrativoBean.salarioMensual }  "></c:out><c:out value="${personalTecnicoAdministrativoBean.tipoMonedaNombre }"></c:out></label>
			</td>
			<td style="width: 10%; text-align: center;"><label
				style="font-size: 10px;"><c:out value="${personalTecnicoAdministrativoBean.institucion.nombreInstitucion }  "></c:out></label>
			</td>
			<td style="width: 5%; text-align: center;" class="hide"><label
				style="font-size: 10px;" ><a
						href="javascript:modificarPersonal('<c:out value="${personalTecnicoAdministrativoBean.personalTecnicoAdministrativoID }"></c:out>',
																'<c:out value="${personalTecnicoAdministrativoBean.nombreCompleto }"></c:out>',
																'<c:out value="${personalTecnicoAdministrativoBean.fkIdtablaespFormacionProfesional }"></c:out>',
																'<c:out value="${personalTecnicoAdministrativoBean.responsabilidad }"></c:out>',
																'<c:out value="${personalTecnicoAdministrativoBean.fkIdtablaespTiempoDedicado }"></c:out>',
																'<c:out value="${personalTecnicoAdministrativoBean.porcentageParticipacion }"></c:out>',
																'<c:out value="${personalTecnicoAdministrativoBean.fkIdtablaespEtapaPersonalTecnico }"></c:out>',
																'<c:out value="${personalTecnicoAdministrativoBean.fkIdpersonalTecnicoAdmReemplazo }"></c:out>',
																'<c:out value="${personalTecnicoAdministrativoBean.salarioMensual }"></c:out>',
																'<c:out value="${personalTecnicoAdministrativoBean.fkIdtablaespTipoMoneda }"></c:out>',
																'<c:out value="${personalTecnicoAdministrativoBean.institucion.institucionID }"></c:out>')"
						id="modificarPersonal" class="linkSelecciona">Modificar</a></label>
			</td>
			</tr>
		</c:forEach>
	</tbody>
</table>