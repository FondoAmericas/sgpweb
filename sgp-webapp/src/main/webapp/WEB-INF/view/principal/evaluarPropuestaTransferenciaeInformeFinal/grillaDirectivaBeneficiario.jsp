<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
	/*function mostrarDetalle(problemaSolucionId){
	 var url = "mostrarDetalleProblemasSoluciones.jspx?problemaSolucionId=" + problemaSolucionId;
	 var stiloPopUp = 'dialogWidth=800px; dialogHeight=700px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
	 //ventanaPopUp = showModalDialog(url,'',stiloPopUp);//window.open(url,'',stiloPopUp);

	 //window.opener.document.formWindowAsignaTutor.profesorAsignado.value = componenteId;

	 //window.showModalDialog(url,datos,stiloPopUp);
	 window.showModalDialog(url, "", stiloPopUp);
	 }*/
</script>

<table border="0" width="100%" class="table-clasico">
	<caption>Lista de directiva de beneficiarios</caption>
	<thead>
		<tr>
			<td style="width: 5%; text-align: center;"><label
				style="font-size: 10px;">Det</label></td>
			<td style="width: 30%; text-align: center;"><label
				style="font-size: 10px;">Nombre Completo</label></td>
			<td style="width: 20%; text-align: center;"><label
				style="font-size: 10px;">Cargo</label></td>
			<td style="width: 35%; text-align: center;"><label
				style="font-size: 10px;">Documento Identidad</label></td>
			<td style="width: 10%; text-align: center;"><label
				style="font-size: 10px;">Opcion</label></td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listDirectivaBeneficiario }"
			var="directivaBeneficiario" varStatus="indice">
			<c:choose>
				<c:when test="${indice.count %2== 0}">
					<c:set var="classIdi" value="f2"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="classIdi" value="f1"></c:set>
				</c:otherwise>
			</c:choose>
			<tr class="<c:out value="${classIdi}"></c:out>">
				<td style="width: 5%; text-align: center;"><a
					href="javascript:expandcollapse('divDB<c:out value="${directivaBeneficiario.directivaBeneficiarioID }"></c:out>', 'one');">
						<img
						id='imgdivDB<c:out value="${directivaBeneficiario.directivaBeneficiarioID}"></c:out>'
						border="0" src="<c:url value="/images/Plus001.gif" />"
						width="15px" /> </a></td>
				<td style="width: 30%; text-align: justify;"><label><c:out
							value="${directivaBeneficiario.nombreCompleto }"></c:out> </label><br>
				</td>
				<td style="width: 20%; text-align: justify;"><label><c:out
							value="${directivaBeneficiario.descripcionCargo }"></c:out> </label><br>
				</td>
				<td style="width: 35%; text-align: justify;"><label><c:out
							value="${directivaBeneficiario.numeroDocumento }"></c:out> - <c:out
							value="${directivaBeneficiario.descripcionTipoDocumento }"></c:out>
				</label><br></td>
				<td style="width: 10%; text-align: justify;"><label>
			<a	href="javascript:agregarObservacion('<c:out value="${directivaBeneficiario.directivaBeneficiarioID  }" ></c:out>','<c:out value="${directivaBeneficiario.propuestaTransferencia.datoProyecto.datoProyectoID }" ></c:out>','11','33','<c:out value="${directivaBeneficiario.propuestaTransferencia.propuestaTransferenciaID}" ></c:out>')"
														class="linkSelecciona">Observacion</a></label><br>
				</td>
			</tr>
			<tr class="<c:out value="${classIdi}"></c:out>">
				<td colspan="100%">
					<div
						id='divDB<c:out value="${directivaBeneficiario.directivaBeneficiarioID }"></c:out>'
						style="display: none; position: relative; overflow: auto; padding-left: 35px; padding-bottom: 15px; width: 95%;">

						<table width="100%">
							<thead>
								<tr>
									<td style="width: 50%; text-align: center;"><label>Acreditacion</label>
									</td>
									<td style="width: 50%; text-align: center;"><label>Vigencia
											de Poderes</label></td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td style="width: 50%; text-align: center;"><label><c:out
												value="${directivaBeneficiario.acreditacion }"></c:out> </label></td>
									<td style="width: 50%; text-align: center;"><label><c:out
												value="${directivaBeneficiario.vigenciaPoder }"></c:out> </label></td>
								</tr>
							</tbody>
						</table>
					</div></td>
			</tr>
		</c:forEach>
	</tbody>
</table>