<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script type="text/javascript">
function agregarObservacion(tablaId,datoProyectoID,tablaClaseId,tablaProfundidadId,claseId){
	var url = "showGestionarObservacion.jspx?tablaId=" + tablaId + "&datoProyectoID="+datoProyectoID+"&tablaClaseId="+tablaClaseId+"&tablaProfundidadId="+tablaProfundidadId+"&claseId="+claseId;
	var stiloPopUp = 'dialogWidth=800px; dialogHeight=500px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
	//ventanaPopUp = showModalDialog(url,'',stiloPopUp);//window.open(url,'',stiloPopUp);

	//window.opener.document.formWindowAsignaTutor.profesorAsignado.value = componenteId;

	//window.showModalDialog(url,datos,stiloPopUp);
	window.showModalDialog(url, "", stiloPopUp);

	}
	
/*function grabarEstado(){
	//alert($("#datoPlanOperativoID").val());
	var form = document.formEvaluarPlanOperativo;
	form.action = "actionGrabarEvaluacionPlanOperativo.jspx?estado="+ $("#idEstadoabecera").val()+"&datoPlanOperativoId="+$("#datoPlanOperativoID").val();
	form.submit();
	$.get("actionGrabarEvaluacionPlanOperativo.jspx", {
		estado : $("#idEstadoabecera").val(),
		datoPlanOperativoId : $("#datoPlanOperativoID").val()
	});
	
}*/
</script>

<script type="text/javascript">
	//$(window).load(function() {
	$(document).ready(function() {
		////UBICACION DEL PROYECTO
		ocultaCampos();
		
		var f = new Date();
		var dia;
		if (f.getDate()<10){
			dia="0"+f.getDate();
		}else{
			dia=f.getDate();
		}
		$("#fechaAprobacion").attr("value",dia + "/" + (f.getMonth() +1) + "/" + f.getFullYear());
			
		
	});
	
	function ocultaCampos(){
		estado= "<c:out value="${prefijoEstadoSolicitud}"></c:out>" ;
		//alert(estado);
		if (estado =='apro'){
			$(".disabled").attr("disabled","disabled");
		}
	}
	
	function grabarEstadoSolicitudRpRf(){
		
		if($("#sltEstadoSolicitud").val()!=0){
		var confirma= confirm("Seguro que desea cambiar el estado a la solicitud de reprogramacion y/o reformulacion?");
		
		if(confirma==true){
			$.get("grabarEstadoSolicitudRpRf.jspx", {
				estadoSolicitud : $("#sltEstadoSolicitud").val(),
				fechaAprobacion : $("#fechaAprobacion").val(),
				solicitaRpRfId : $("#solicitaRpRfId").val(),
				versionPo : $("#versionPo").val(),
				datoPlanOperativoId : $("#datoPlanOperativoId").val()	
			}, function() {
				regresar();
				cargaGrillaAutorizarRpRf();
			});
		}
		}else if($("#sltEstadoSolicitud").val()==0){
			alert("Seleccionar el estado a grabar.");	
			$("#sltEstadoSolicitud").attr("value", $("#estadoSolicitudRpRf").val());
		}
	}
</script>

<div class="form-clasico">
		<div id="divListPlanOperativo"
			style="width: 100%; height: auto">
			<fieldset>
				<legend>Detalle de Proyecto</legend>
				<br>
				<table border="0" align="center" width="100%">
					<tr>
						<td style="text-align: right; width: 25%;"><label>Nombre
								Proyecto:</label></td>
						<td style="text-align: left; width: 75%;" colspan="3"><label
							for="nombreProyecto">${proyectoBean.nombreProyecto}</label>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%;"><label>Codigo
								Proyecto:</label></td>
						<td style="text-align: left; width: 25%;"><label
							for="codigoProyecto">${proyectoBean.codigoProyecto}</label>
						</td>
						<td style="text-align: right; width: 25%;"><label>Duracion
								Proyecto(meses):</label></td>
						<td style="text-align: left; width: 25%;"><label
							for="duracionProyecto">${proyectoBean.duracionProyecto}</label>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%;"><label>Cantidad
								Periodo:</label></td>
						<td style="text-align: left; width: 25%;"><label
							for="cantidadPeriodo">${proyectoBean.cantidadPeriodo }</label>
						</td>
						
						<td style="text-align: right; width: 25%;"><label>Estado Plan Operativo:</label>
						</td>
						<td style="text-align: left; width: 25%;"><select
							name="idEstadoabecera" id="idEstadoabecera" 
									disabled="disabled">
								<option value="0">
									<c:out value="--Seleccionar--" />
								</option>
								<c:forEach items="${listaEstadoCabecera}" var="estado">
									<option value="${estado.detalleEstadoCabeceraID}"
										<c:if
										test="${ planOperativo.estadoPlanOperativo == estado.detalleEstadoCabeceraID}">
											selected="selected"
									</c:if>>
										<c:out value="${estado.descripEstado}" />
									</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%;"><label>Version
								Plan Operativo:</label></td>
						<td style="text-align: left; width: 25%;"><label
							for="planOperativo">${planOperativo.version}
							<input type="hidden" id="versionPo" name="versionPo" value='<c:out value="${planOperativo.version}"></c:out>' >
							<input type="hidden" id="datoPlanOperativoId" name="datoPlanOperativoId" value='<c:out value="${planOperativo.datoPlanOperativoID}"></c:out>' ></label>
						</td>
					</tr>
					<tr>

						<td colspan="4" style="width: 100%; text-align: right;"><!-- <input
							type="button" value="Grabar Evaluaci&oacute;n"
							onclick="fGrabarEvaluacion()" />--> <input type="button"
							onclick="regresar()" value="Regresar" /></td>
					</tr>
				</table>
				<br/>
				<fieldset style="margin-top: 10px;" class="muestraSolicitud">
			<label style="font-size: 12px; font-weight: bold; text-decoration: underline;">Solicitud de Reprogramacion &amp; Reformulacion del Plan Operativo<input type="hidden" id="solicitaRpRfId" name="solicitaRpRfId" value="<c:out value="${solicitaRpRf.solicitaRpRfID }"></c:out>"> </label>
			<table class="muestraDetalleSolicitud">
				<tr>
					<td style="text-align: right; width: 25%; vertical-align: top; ">
					    <label>Observacion de Solicitud:</label>
					</td>
					<td colspan="3" style="text-align: left; width: 75%; ">
					    <label><c:out value="${solicitaRpRf.observacionDeSolicitud }"></c:out></label>
					</td>
				</tr>
				<tr>
					<td style="text-align: right; width: 25%; ">
					    <label>Estado de Solicitud:</label>
					</td>
					<td style="text-align: left; width: 25%; ">
					    <select name="sltEstadoSolicitud" id="sltEstadoSolicitud" class="disabled" onchange="grabarEstadoSolicitudRpRf();">
				<option value="0">
					<c:out value="-- Estado --" />
				</option>
				<c:forEach items="${listEstadoSolicitudDesembolso}"
					var="estadoSolicitudDesembolso">
							<option 
						<c:if test="${estadoSolicitudDesembolso.detalleEstadoCabeceraID == solicitaRpRf.fkIdDetalleEstadoCabRpRf }">selected="selected"</c:if>
								value="${estadoSolicitudDesembolso.detalleEstadoCabeceraID}">
								<c:out value="${estadoSolicitudDesembolso.descripEstado }" />
							</option>
				</c:forEach>
		</select>
		<input type="hidden" id="estadoSolicitudRpRf" name="estadoSolicitudRpRf" value="${solicitaRpRf.fkIdDetalleEstadoCabRpRf }">
					</td>
						<td style="text-align: right; width: 25%; ">
					    <label>Fecha de Solicitud:</label>
					</td>
					<td style="text-align: left; width: 25%; "><label><c:out value="${solicitaRpRf.fechaSolicitudString }"></c:out>  (dd/mm/aaaa)</label>
					</td>
			</tr>
			<tr>
			<td style="text-align: right; width: 25%; ">
					    <label>Fecha de Aprobacion:</label>
					</td>
					<td style="text-align: left; width: 25%; ">
					    <input type="text" name="fechaAprobacion" maxlength="0" style="width: 90px;"
							id="fechaAprobacion" disabled="disabled"
							onkeypress="javascript:return Valida_Dato(event,7);" /><label>(dd/mm/aaaa)</label>
					</td>
			</tr>
			</table>
			</fieldset>
				<br />
				<div id="divListResultado" style="width: 100%;">
					<fieldset>
						<table width="100%" class="table-clasico">
							<caption>
								<label>Lista de Resultados con Detalles</label>
							</caption>
							<thead>
								<tr>
									<td style="text-align: center; width: 3%;"><label>Det</label>
									</td>
									<td style="text-align: center; width: 5%;"><label>Codigo</label>
									</td>
									<td style="text-align: center; width: 60%;"><label>Resultado</label>
									</td>
									<td style="text-align: center; width: 17%;"><label>Meta</label>
									</td>
									<td style="text-align: center; width: 10%;"><label>Duracion<br />(Meses)</label>
									</td>
									<!-- <td style="text-align: center; width: 5%;"><label>Opciones</label>
									</td> -->
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${planOperativo.listResultadoForm }"
									var="resultado" varStatus="indice">
									<c:choose>
										<c:when test="${indice.count %2== 0}">
											<c:set var="classIdi" value="f2"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdi" value="f1"></c:set>
										</c:otherwise>
									</c:choose>
									<tr class="<c:out value="${classIdi}"></c:out>">
										<td style="width: 3%; text-align: center;"><a
											href="javascript:expandcollapse('divRes<c:out value="${resultado.resultadoID }"></c:out>', 'one');">
												<img
												id='imgdivRes<c:out value="${resultado.resultadoID }"></c:out>'
												border="0" src="<c:url value="/images/Plus001.gif" />"
												width="15px" /> </a>
										</td>
										<td style="text-align: center; width: 5%;"><label><c:out
													value="${resultado.codigoResultado }"></c:out> </label>
										</td>
										<td style="text-align: justify; width: 60%;"><label><c:out
													value="${resultado.definicionResultado }"></c:out> </label>
										</td>
										<td style="text-align: left; width: 17%;"><label><c:out
													value="${resultado.metaResultado }"></c:out> - <c:out
													value="${resultado.estratoNombre }"></c:out> </label>
										</td>
										<td style="text-align: center; width: 10%;"><label><c:out
													value="${resultado.duracionMeses }"></c:out> </label>
										</td>
										<!-- <td style="text-align: center; width: 5%;"><label><a	href="javascript:agregarObservacion('<c:out value="${resultado.resultadoID  }" ></c:out>','<c:out value="${planOperativo.datoProyectoID }" ></c:out>','4','10','<c:out value="${planOperativo.datoPlanOperativoID }" ></c:out>')"
														class="linkSelecciona">Observacion</a></label>
										</td> -->
									</tr>
									<tr class="<c:out value="${classIdi}"></c:out>">
										<td colspan="100%">
											<div
												id='divRes<c:out value="${resultado.resultadoID }"></c:out>'
												style="display: none; position: relative; overflow: auto; padding-left: 25px; padding-bottom: 15px; width: 97%;">

												<legend>Cronograma de Meta de Resultado</legend>
												<table width="100%">
													<thead>
														<tr>
															<td style="text-align: center; width: 30%;"><label>Periodo</label>
															</td>
															<td style="text-align: center; width: 30%;"><label>Avance
																	de Meta</label></td>
															<!-- <td style="text-align: center; width: 30%;"><label>Opcion</label></td>-->																	
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${resultado.listCronogramaMetaForm }"
															var="cronogramaMetaResultado" varStatus="indiceInt">
															<c:choose>
																<c:when test="${indiceInt.count %2== 0}">
																	<c:set var="classIdiInt" value="f2int"></c:set>
																</c:when>
																<c:otherwise>
																	<c:set var="classIdiInt" value="f1int"></c:set>
																</c:otherwise>
															</c:choose>
															<tr class="<c:out value="${classIdiInt}"></c:out>">
																<td style="text-align: center; width: 30%;"><label><c:out
																			value="${cronogramaMetaResultado.periodo }"></c:out>
																</label></td>
																<td style="text-align: center; width: 30%;"><label><c:out
																			value="${cronogramaMetaResultado.avanceMeta }"></c:out>
																		- <c:out value="${resultado.estratoNombre }"></c:out>
																</label></td>
																<!-- <td style="text-align: center; width: 30%;"><label><a	href="javascript:agregarObservacion('<c:out value="${cronogramaMetaResultado.cronogramaMetaPorResultadoID  }" ></c:out>','<c:out value="${planOperativo.datoProyectoID }" ></c:out>','4','18','<c:out value="${planOperativo.datoPlanOperativoID }" ></c:out>')"
														class="linkSelecciona">Observacion</a>
																</label></td>-->
															</tr>
														</c:forEach>
													</tbody>
												</table>
												<br />
												<legend>Actividades</legend>
												<table width="100%">
													<thead>
														<tr>
															<td style="text-align: center; width: 3%;"><label>Det</label>
															</td>
															<td style="text-align: center; width: 5%;"><label>Codigo</label>
															</td>
															<td style="text-align: center; width: 15%;"><label>Tipo
																	de <br />Actividad</label></td>
															<td style="text-align: center; width: 55%;"><label>Actividad</label>
															</td>
															<td style="text-align: center; width: 10%;"><label>Duracion<br />(meses)</label>
															</td>
															<!-- <td style="text-align: center; width: 10%;"><label>Opcion</label>
															</td>-->
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${resultado.listActividadForm }"
															var="actividad" varStatus="indiceInt">
															<c:choose>
																<c:when test="${indiceInt.count %2== 0}">
																	<c:set var="classIdiInt" value="f2int"></c:set>
																</c:when>
																<c:otherwise>
																	<c:set var="classIdiInt" value="f1int"></c:set>
																</c:otherwise>
															</c:choose>
															<tr class="<c:out value="${classIdiInt}"></c:out>">
																<td style="width: 3%; text-align: center;"><a
																	href="javascript:expandcollapse('divAct<c:out value="${actividad.actividadID }"></c:out>', 'one');">
																		<img
																		id='imgdivAct<c:out value="${actividad.actividadID }"></c:out>'
																		border="0" src="<c:url value="/images/Plus001.gif" />"
																		width="15px" /> </a>
																</td>
																<td style="text-align: center; width: 5%;"><label><c:out
																			value="${actividad.codigoActividad }"></c:out> </label></td>
																<td style="text-align: center; width: 15%;"><label><c:out
																			value="${actividad.tipoActividadNombre }"></c:out> </label></td>
																<td style="text-align: left; width: 55%;"><label><c:out
																			value="${actividad.nombreActividad }"></c:out> </label></td>
																<td style="text-align: center; width: 10%;"><label><c:out
																			value="${actividad.duracionMeses }"></c:out> </label></td>
																<!-- <td style="text-align: center; width: 10%;"><label><a	href="javascript:agregarObservacion('<c:out value="${actividad.actividadID  }" ></c:out>','<c:out value="${planOperativo.datoProyectoID }" ></c:out>','4','11','<c:out value="${planOperativo.datoPlanOperativoID }" ></c:out>')"
														class="linkSelecciona">Observacion</a></label></td>-->			
															</tr>
															<tr class="<c:out value="${classIdiInt}"></c:out>">
																<td colspan="100%">
																	<div
																		id='divAct<c:out value="${actividad.actividadID }"></c:out>'
																		style="display: none; position: relative; overflow: auto; padding-left: 25px; padding-bottom: 15px; width: 97%;">
																		<legend>Meta de Actividad</legend>
																		<table width="100%">
																			<thead>
																				<tr>
																					<td style="text-align: center; width: 3%;"><label>Det</label>
																					</td>
																					<td style="text-align: center; width: 40%;"><label>Tipo
																							Indicador</label></td>
																					<td style="text-align: center; width: 35%;"><label>Meta
																							Actividad</label></td>
																					<!-- <td style="text-align: center; width: 10%;"><label>Opcion</label></td>-->		
																				</tr>
																			</thead>
																			<tbody>
																				<c:forEach
																					items="${actividad.listMetaPorActividad  }"
																					var="metaPorActividad" varStatus="indiceInt2">
																					<c:choose>
																						<c:when test="${indiceInt2.count %2== 0}">
																							<c:set var="classIdiInt2" value="f2"></c:set>
																						</c:when>
																						<c:otherwise>
																							<c:set var="classIdiInt2" value="f1"></c:set>
																						</c:otherwise>
																					</c:choose>
																					<tr class="<c:out value="${classIdiInt2}"></c:out>">
																						<td style="width: 3%; text-align: center;"><a
																							href="javascript:expandcollapse('divMetaAct<c:out value="${metaPorActividad.metaPorActividadID }"></c:out>', 'one');">
																								<img
																								id='imgdivMetaAct<c:out value="${metaPorActividad.metaPorActividadID }"></c:out>'
																								border="0"
																								src="<c:url value="/images/Plus001.gif" />"
																								width="15px" /> </a>
																						</td>
																						<td style="text-align: left; width: 40%;"><label><c:out
																									value="${metaPorActividad.descripcionTipoIndicadorActividad }"></c:out>
																						</label></td>
																						<td style="text-align: center; width: 35%;"><label><c:out
																									value="${metaPorActividad.cantidadMetaActividad }"></c:out>
																								- <c:out
																									value="${metaPorActividad.descripcionUnidadMedida }"></c:out>
																						</label></td>
																						<!-- <td style="text-align: center; width: 10%;"><label><a	href="javascript:agregarObservacion('<c:out value="${metaPorActividad.metaPorActividadID  }" ></c:out>','<c:out value="${planOperativo.datoProyectoID }" ></c:out>','4','12','<c:out value="${planOperativo.datoPlanOperativoID }" ></c:out>')"
														class="linkSelecciona">Observacion</a></label></td>-->
																					</tr>
																					<tr class="<c:out value="${classIdiInt2}"></c:out>">
																						<td colspan="100%">
																							<div
																								id='divMetaAct<c:out value="${metaPorActividad.metaPorActividadID }"></c:out>'
																								style="display: none; position: relative; overflow: auto; padding-left: 25px; padding-bottom: 15px; width: 97%;">
																								<legend>Cronograma Meta de Actividad</legend>
																								<table width="100%">
																									<thead>
																										<tr>
																											<td style="text-align: center; width: 20%;"><label>Periodo</label>
																											</td>
																											<td style="text-align: center; width: 50%;"><label>Cantidad
																													Programada <br />Por Periodo</label></td>
																											<!-- <td style="text-align: center; width: 10%;"><label>Opcion</label></td>-->		
																										</tr>
																									</thead>
																									<tbody>
																										<c:forEach
																											items="${metaPorActividad.listCronogramaMetaPorActividad  }"
																											var="cronogramaMetaPorActividad"
																											varStatus="indiceInt2">
																											<c:choose>
																												<c:when test="${indiceInt2.count %2== 0}">
																													<c:set var="classIdiInt2CMA" value="f2int"></c:set>
																												</c:when>
																												<c:otherwise>
																													<c:set var="classIdiInt2CMA" value="f1int"></c:set>
																												</c:otherwise>
																											</c:choose>
																											<tr
																												class="<c:out value="${classIdiInt2CMA}"></c:out>">
																												<td style="text-align: center; width: 20%;"><label><c:out
																															value="${cronogramaMetaPorActividad.periodo }"></c:out>
																												</label></td>
																												<td style="text-align: center; width: 50%;"><label><c:out
																															value="${cronogramaMetaPorActividad.cantidadMetaActividadProgPorPeriodo }"></c:out>
																														- <c:out
																															value="${metaPorActividad.descripcionUnidadMedida }"></c:out>
																												</label></td>
																												<!-- <td style="text-align: center; width: 10%;"><label><a	href="javascript:agregarObservacion('<c:out value="${cronogramaMetaPorActividad.cronogramaMetaPorActividadID  }" ></c:out>','<c:out value="${planOperativo.datoProyectoID }" ></c:out>','4','13','<c:out value="${planOperativo.datoPlanOperativoID }" ></c:out>')"
														class="linkSelecciona">Observacion</a></label></td>-->
																											</tr>
																										</c:forEach>
																									</tbody>
																								</table>
																							</div>
																						</td>
																					</tr>
																				</c:forEach>
																			</tbody>
																		</table>
																		<br />
																		<legend>Costo de Actividad</legend>
																		<table width="100%">
																			<thead>
																				<tr>
																					<td style="text-align: center; width: 3%;"><label>Det</label>
																					</td>
																					<td style="text-align: center; width: 15%;"><label>Cantidad</label>
																					</td>
																					<td style="text-align: center; width: 12%;"><label>Precio
																							Unitario</label></td>
																					<td style="text-align: center; width: 15%;"><label>Partida
																							<br />Generica</label></td>
																					<td style="text-align: center; width: 15%;"><label>Partida
																							<br />Especifica</label></td>
																					<td style="text-align: center; width: 30%;"><label>Detalle</label>
																					</td>
																					<!-- <td style="text-align: center; width: 5%;"><label>Opcion</label>
																					</td>-->
																				</tr>
																			</thead>
																			<tbody>
																				<c:forEach items="${actividad.listCostoActividad }"
																					var="costoPorActividad" varStatus="indiceInt2CPA">
																					<c:choose>
																						<c:when test="${indiceInt2CPA.count %2== 0}">
																							<c:set var="classIdiInt2CPA" value="f2"></c:set>
																						</c:when>
																						<c:otherwise>
																							<c:set var="classIdiInt2CPA" value="f1"></c:set>
																						</c:otherwise>
																					</c:choose>
																					<tr
																						class="<c:out value="${classIdiInt2CPA}"></c:out>">
																						<td style="width: 3%; text-align: center;"><a
																							href="javascript:expandcollapse('divCostoAct<c:out value="${costoPorActividad.costoActividadID }"></c:out>', 'one');">
																								<img
																								id='imgdivCostoAct<c:out value="${costoPorActividad.costoActividadID }"></c:out>'
																								border="0"
																								src="<c:url value="/images/Plus001.gif" />"
																								width="15px" /> </a>
																						</td>
																						<td style="text-align: center; width: 15%;"><label><c:out
																									value="${costoPorActividad.cantidadTotal }"></c:out>
																								- <c:out
																									value="${costoPorActividad.descripcionUnidadMedida }"></c:out>
																						</label></td>
																						<td style="text-align: center; width: 12%;"><label><c:out
																									value="${costoPorActividad.precioUnitario }"></c:out>
																								<c:out
																									value="${costoPorActividad.descripcionTipoMoneda }"></c:out>
																						</label></td>
																						<td style="text-align: center; width: 15%;"><label><c:out
																									value="${costoPorActividad.partidaGenerica.descripcionPartidaGenerica }"></c:out>
																						</label></td>
																						<td style="text-align: center; width: 15%;"><label><c:out
																									value="${costoPorActividad.partidaEspecifica.descripcionPartidaEspecifica }"></c:out>
																						</label></td>
																						<td style="text-align: center; width: 30%;"><label><c:out
																									value="${costoPorActividad.detallePartidaGenerica }"></c:out>
																						</label></td>
																						<!-- <td style="text-align: center; width: 30%;"><label><a	href="javascript:agregarObservacion('<c:out value="${costoPorActividad.costoActividadID  }" ></c:out>','<c:out value="${planOperativo.datoProyectoID }" ></c:out>','4','14','<c:out value="${planOperativo.datoPlanOperativoID }" ></c:out>')"
														class="linkSelecciona">Observacion</a></label></td>-->
																					</tr>
																					<tr
																						class="<c:out value="${classIdiInt2CPA}"></c:out>">
																						<td colspan="100%">
																							<div
																								id='divCostoAct<c:out value="${costoPorActividad.costoActividadID }"></c:out>'
																								style="display: none; position: relative; overflow: auto; padding-left: 25px; padding-bottom: 15px; width: 97%;">
																								<legend>Cronograma Costo Actividad</legend>
																								<table width="100%">
																									<thead>
																										<tr>
																											<td style="text-align: center; width: 15%;"><label>Periodo</label>
																											</td>
																											<td style="text-align: center; width: 15%;"><label>Cantidad
																													Programada <br />Por Periodo</label></td>
																											<td style="text-align: center; width: 60%;"><label>Fuente<br />Financiadora</label>
																											</td>
																											<!-- <td style="text-align: center; width: 10%;"><label>Opcion</label>
																											</td>-->
																										</tr>
																									</thead>
																									<tbody>
																										<c:forEach
																											items="${costoPorActividad.listCronogramaCostoActividad  }"
																											var="cronogramaCostoPorActividad"
																											varStatus="indiceInt2CPA">
																											<c:choose>
																												<c:when test="${indiceInt2CPA.count %2== 0}">
																													<c:set var="classIdiInt2CPA" value="f2int"></c:set>
																												</c:when>
																												<c:otherwise>
																													<c:set var="classIdiInt2CPA" value="f1int"></c:set>
																												</c:otherwise>
																											</c:choose>
																											<tr
																												class="<c:out value="${classIdiInt2CPA }"></c:out>">
																												<td style="text-align: center; width: 15%;"><label><c:out
																															value="${cronogramaCostoPorActividad.periodo }"></c:out>
																												</label></td>
																												<td style="text-align: center; width: 15%;"><label><c:out
																															value="${cronogramaCostoPorActividad.cantidad }"></c:out>
																														- <c:out
																															value="${costoPorActividad.descripcionUnidadMedida }"></c:out>
																												</label></td>
																												<td style="text-align: left; width: 60%;"><label><c:out
																															value="${cronogramaCostoPorActividad.fuenteFinanciadora.institucion.nombreInstitucion }"></c:out>
																												</label></td>
																												<!-- <td style="text-align: left; width: 10%;"><label><a	href="javascript:agregarObservacion('<c:out value="${cronogramaCostoPorActividad.cronogramaCostoActividadID  }" ></c:out>','<c:out value="${planOperativo.datoProyectoID }" ></c:out>','4','15','<c:out value="${planOperativo.datoPlanOperativoID }" ></c:out>')"
														class="linkSelecciona">Observacion</a></label></td>-->
																											</tr>
																										</c:forEach>
																									</tbody>
																								</table>
																							</div>
																						</td>
																					</tr>
																				</c:forEach>
																			</tbody>
																		</table>
																	</div></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</td>
									</tr>

								</c:forEach>
							</tbody>
						</table>
					</fieldset>
				</div>
			</fieldset>
		</div>
</div>
