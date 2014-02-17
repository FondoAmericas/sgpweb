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
	<caption>Lista de Bienes</caption>
	<thead>
		<tr>
			<td style="width: 5%; text-align: center;"><label
				style="font-size: 10px;">Det</label></td>
			<td style="width: 20%; text-align: center;"><label
				style="font-size: 10px;">Descripcion</label></td>
			<td style="width: 5%; text-align: center;"><label
				style="font-size: 10px;">Estado<br />Conservacion</label></td>
			<td style="width: 30%; text-align: center;"><label
				style="font-size: 10px;">Cantidades</label></td>
			<td style="width: 30%; text-align: center;"><label
				style="font-size: 10px;">Costos</label></td>
			<td style="width: 10%; text-align: center;"><label
				style="font-size: 10px;">Opcion</label></td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listBien }" var="bien" varStatus="indice">
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
					href="javascript:expandcollapse('divB<c:out value="${bien.bienID }"></c:out>', 'one');">
						<img id='imgdivB<c:out value="${bien.bienID}"></c:out>' border="0"
						src="<c:url value="/images/Plus001.gif" />" width="15px" /> </a></td>
				<td style="width: 25%; text-align: justify;"><label><c:out
							value="${bien.descripcionBien }"></c:out> </label><br></td>
				<td style="width: 5%; text-align: justify;"><label><c:out
							value="${bien.descripcionEstadoConservacion }"></c:out> </label><br>
				</td>
				<td style="width: 30%; text-align: justify;"><label>Cantidad
						Total: <c:out value="${bien.cantidadTotal }"></c:out> <c:out
							value="${bien.descripcionUnidadMedida }"></c:out><br />Cantidad
						Sin Transferir: <c:out value="${bien.cantidadSinTransferir }"></c:out>
						<c:out value="${bien.descripcionUnidadMedida }"></c:out> </label><br>
				</td>
				<td style="width: 30%; text-align: justify;"><label>Costo
						Unitario: <c:out value="${bien.costoUnitario }"></c:out> <c:out
							value="${bien.descripcionTipoMoneda }"></c:out><br />Costo
						Total: <c:out value=" ${bien.costoTotal }"></c:out> <c:out
							value="${bien.descripcionTipoMoneda }"></c:out> </label><br></td>
				<td style="width: 10%; text-align: justify;"><label>
			<a	href="javascript:agregarObservacion('<c:out value="${bien.bienID }" ></c:out>','<c:out value="${datoProyectoId }" ></c:out>','11','35','<c:out value="${propuestaTransferenciaId }" ></c:out>')"
														class="linkSelecciona">Observacion</a></label><br>
				</td>
			</tr>
			<tr class="<c:out value="${classIdi}"></c:out>">
				<td colspan="100%">
					<div id='divB<c:out value="${bien.bienID }"></c:out>'
						style="display: none; position: relative; overflow: auto; padding-left: 35px; padding-bottom: 15px; width: 95%;">
						<hr style="border-color: #3e7a3f;">
						<label style="width: 95%; text-align: justify;">Tipo de Bien: <c:out value="${bien.descripcionTipoBien }"></c:out>
						</label> <br />
						<label style="width: 95%; text-align: justify;">Observaciones: <c:out value="${bien.observacion }"></c:out>
						</label> <br />
						<label style="width: 95%; text-align: justify;">Localizacion: <c:out
								value="${bien.localizacionUbicacion }"></c:out> </label> <br />
						<label style="width: 95%; text-align: justify;">Categoria Activo: <c:out
								value="${bien.activo.descripcionCategoriaActivo }"></c:out> </label> <br />
						<label style="width: 95%; text-align: justify;">Bien/Activo: <c:out
								value="${bien.activo.descripcionActivo }"></c:out> </label><br />
						<legend>
							<label>Recursos Utilizados</label>
						</legend>
						<table width="100%">
							<thead>
								<tr>
									<td style="width: 30%; text-align: center;"><label>Monto</label>
									</td>
									<td style="width: 70%; text-align: center;"><label>Fuente
											Financiamiento</label></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${bien.listRecursoUtilizado }"
									var="recursoUtilizado" varStatus="indice">
									<c:choose>
										<c:when test="${indice.count %2== 0}">
											<c:set var="classIdi" value="f2"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdi" value="f1"></c:set>
										</c:otherwise>
									</c:choose>
									<tr class="<c:out value="${classIdi}"></c:out>">
										<td style="width: 30%; text-align: center;"><label><c:out
													value="${recursoUtilizado.monto }"></c:out> <c:out
													value="${recursoUtilizado.descripcionTipoMoneda }"></c:out>
										</label></td>
										<td style="width: 70%; text-align: center;"><label><c:out
													value="${recursoUtilizado.fuenteFinanciadora.institucion.nombreInstitucion }"></c:out>
										</label></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div></td>
			</tr>
		</c:forEach>
	</tbody>
</table>