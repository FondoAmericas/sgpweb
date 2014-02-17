<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
	/*$(window).load(function() {
		ocultaCampos();
	});

	function ocultaCampos() {
		estado = "<c:out value="${estado}"></c:out>";
		//alert(estado);
		if ((estado == 'apro')|| (estado == 'eval')) {
			$(".hide").hide();//attr("style","visibility: hidden;");
			$(".disabled").attr("disabled", "disabled");
			//alert($('#cbxInstitucionEjecutora option:selected').html());
			//$("#div_cbxInstitucionEjecutora").attr("style","visibility: hidden;");
		}
	}*/
</script>

<script type="text/javascript">
	function modificarReporte(reporteAvanceId, periodo, resumen,fechaInicio,fechaFin) {
		$("#sltPeriodo").attr("value", periodo);
		$("#sltPeriodo").attr("disabled", "disabled");
		$("#resumenReporteAvance").attr("value", resumen);
		$("#reporteAvanceId").attr("value", reporteAvanceId);
		$("#fechaInicio").attr("value", fechaInicio);
		$("#fechaFin").attr("value", fechaFin);
	}

	function problemasSoluciones(reporteAvanceId,estado) {

		var url = "cuerpoProblemasSoluciones.jspx?reporteAvanceId="
				+ reporteAvanceId+"&estado="+estado;
		var stiloPopUp = 'dialogWidth=800px; dialogHeight=700px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
		//ventanaPopUp = showModalDialog(url,'',stiloPopUp);//window.open(url,'',stiloPopUp);

		//window.opener.document.formWindowAsignaTutor.profesorAsignado.value = componenteId;

		//window.showModalDialog(url,datos,stiloPopUp);
		window.showModalDialog(url, "", stiloPopUp);

	}

	function apresiacionResultados(reporteAvanceId,estado) {

		var url = "cuerpoApresiacionResultados.jspx?reporteAvanceId="
				+ reporteAvanceId+"&estado="+estado;
		var stiloPopUp = 'dialogWidth=800px; dialogHeight=700px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
		//ventanaPopUp = showModalDialog(url,'',stiloPopUp);//window.open(url,'',stiloPopUp);

		//window.opener.document.formWindowAsignaTutor.profesorAsignado.value = componenteId;

		//window.showModalDialog(url,datos,stiloPopUp);
		window.showModalDialog(url, "", stiloPopUp);

	}

	function beneficiariosResultado(reporteAvanceId,estado) {

		var url = "cuerpoBeneficiariosResultado.jspx?reporteAvanceId="
				+ reporteAvanceId+"&estado="+estado;
		var stiloPopUp = 'dialogWidth=800px; dialogHeight=700px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
		//ventanaPopUp = showModalDialog(url,'',stiloPopUp);//window.open(url,'',stiloPopUp);

		//window.opener.document.formWindowAsignaTutor.profesorAsignado.value = componenteId;

		//window.showModalDialog(url,datos,stiloPopUp);
		window.showModalDialog(url, "", stiloPopUp);

	}

	function avanceResultadosActividad(reporteAvanceId,estado) {

		var url = "avanceResultadosActividad.jspx?reporteAvanceId="
				+ reporteAvanceId+"&estado="+estado;
		var stiloPopUp = 'dialogWidth=800px; dialogHeight=700px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
		//ventanaPopUp = showModalDialog(url,'',stiloPopUp);//window.open(url,'',stiloPopUp);

		//window.opener.document.formWindowAsignaTutor.profesorAsignado.value = componenteId;

		//window.showModalDialog(url,datos,stiloPopUp);
		window.showModalDialog(url, "", stiloPopUp);

	}


</script>

<script type="text/javascript">
function guardarEstadoReporteAvance(reporteAvanceId){
	if($("#sltEstado"+reporteAvanceId).val()!=0){
	var confirmacion = confirm("Seguro que desea mandar el reporte a ser evaluado por Fondam? \n ADVERTENCIA: Una vez enviado no se podran hacer cambios.");
	
	if(confirmacion == true){
		var estadoId = $("#sltEstado"+reporteAvanceId).val();
		
		$.get("grabarEstadoReporteAvance.jspx", {
			sltEstadoReporteAvance : estadoId,
			reporteAvanceId : reporteAvanceId
		}, function() {
			$("#grillaReporteAvance").load("showGrillaReporteAvance.jspx",
					{
						datoProyectoId : $("#datoProyectoId").val()
					});
		});

	}
	}else if($("#sltEstado"+reporteAvanceId).val()==0){
		$("#grillaReporteAvance").load("showGrillaReporteAvance.jspx",{
					datoProyectoId : $("#datoProyectoId").val()
				}, function(){
					alert("Seleccionar el estado a grabar.");
				});
	}
}

</script>

<table border="0" width="100%" class="table-clasico">
	<caption>Lista de Reportes</caption>
	<thead>
		<tr>
			<td style="width: 5%; text-align: center;"><label>Detalle</label>
			</td>
			<td style="width: 5%; text-align: center;"><label>N°<br>Reporte</label>
			</td>
			<td style="width: 20%; text-align: center;"><label>Estado</label>
			</td>
			<td style="width: 46%; text-align: center;"><label>Resumen</label>
			</td>
			<td style="width: 10%; text-align: center;"><label>Fecha Inicio<br/>(dd/mm/aaaa)</label>
			</td>
			<td style="width: 10%; text-align: center;"><label>Fecha Fin<br/>(dd/mm/aaaa)</label>
			</td>
			<td style="width: 4%; text-align: center;" class="hide"><label>Modificar</label>
			</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listReporteAvance }" var="reporteAvance"
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
				<td style="width: 5%; text-align: center;"><a
					href="javascript:expandcollapse('div<c:out value="${reporteAvance.reporteAvanceID }"></c:out>', 'one');">
						<img
						id='imgdiv<c:out value="${reporteAvance.reporteAvanceID }"></c:out>'
						border="0" src="<c:url value="/images/Plus001.gif" />"
						width="15px" /> </a></td>
				<td style="width: 5%; text-align: center;"><label>Reporte
						<c:out value="${reporteAvance.periodo }"></c:out>
				</label>
				</td>
				<td style="width: 20%; text-align: center;"><label>
				<c:choose>
					<c:when test="${reporteAvance.prefijoEstadoRepAvance=='repgen' }">
						<select
			name="sltEstado<c:out value="${reporteAvance.reporteAvanceID }"></c:out>" 
			id="sltEstado<c:out value="${reporteAvance.reporteAvanceID }"></c:out>" 
			onchange="guardarEstadoReporteAvance(<c:out value="${reporteAvance.reporteAvanceID }"></c:out>)">
				<option value="0">
					<c:out value="-- Estados --" />
				</option>
				<c:forEach items="${lstDetEstCabFinal}" var="estado">
					<option value="${estado.detalleEstadoCabeceraID}" title="${estado.prefijoEstado}" 
					 	<c:if test="${estado.detalleEstadoCabeceraID == reporteAvance.fkIdDetalleEstadoCabEstRepAvance }"> selected="selected" </c:if> >
						<c:out value="${estado.descripEstado}" />
					</option>
				</c:forEach>
		</select>
					</c:when>
					<c:otherwise>
						<c:out
							value="${reporteAvance.descripcionEstadoRepAvance }"></c:out>
					</c:otherwise>	
				</c:choose>
				</label>
				</td>
				<td style="width: 46%; text-align: justify;"><label><c:out
							value="${reporteAvance.resumen }"></c:out>
				</label>
				</td>
				<td style="width: 10%; text-align: center;"><label><c:out
							value="${reporteAvance.fechaInicio }"></c:out>
				</label>
				</td>
				<td style="width: 10%; text-align: center;"><label><c:out
							value="${reporteAvance.fechaFin }"></c:out>
				</label>
				</td>
				<td style="width: 4%; text-align: center;" class="hide"><label>
				<c:choose>
					<c:when test="${reporteAvance.prefijoEstadoRepAvance !='apro' && reporteAvance.prefijoEstadoRepAvance !='eval'}">
					<a
						href="javascript:modificarReporte('<c:out value="${reporteAvance.reporteAvanceID }" ></c:out>','<c:out value="${reporteAvance.periodo }"></c:out>','<c:out value="${reporteAvance.resumen }"></c:out>','<c:out value="${reporteAvance.fechaInicio }"></c:out>','<c:out value="${reporteAvance.fechaFin }"></c:out>')"
						id="modificarReporte" class="linkSelecciona">Modificar</a>
					</c:when>
					<c:otherwise>-----</c:otherwise>
				</c:choose>
				</label>
				</td>
			</tr>
			<tr class="<c:out value="${classIdi}"></c:out>">
				<td colspan="100%">
					<div
						id='div<c:out value="${reporteAvance.reporteAvanceID }"></c:out>'
						style="display: none; position: relative; overflow: auto; padding-left: 15px; padding-bottom: 15px; width: 97%;">

						<table>
							<tbody>
								<tr>
									<td style="width: 10%; text-align: center;"><label><a
											href="javascript:avanceResultadosActividad('<c:out value="${reporteAvance.reporteAvanceID }" />','<c:out value="${reporteAvance.prefijoEstadoRepAvance }" />')"
											id="avanceResultadosActividad" class="linkSelecciona">Avance<br>Resultado<br>Actividad</a>
									</label>
									</td>
									<td style="width: 10%; text-align: center;"><label><a
											href="javascript:apresiacionResultados('<c:out value="${reporteAvance.reporteAvanceID }" />','<c:out value="${reporteAvance.prefijoEstadoRepAvance }" />')"
											id="apresiacionResultados" class="linkSelecciona">Apreciación<br>Resultados</a>
									</label>
									</td>
									<td style="width: 10%; text-align: center;"><label><a
											href="javascript:beneficiariosResultado('<c:out value="${reporteAvance.reporteAvanceID }" />','<c:out value="${reporteAvance.prefijoEstadoRepAvance }" />')"
											id="beneficiarios" class="linkSelecciona">Beneficiarios</a>
									</label>
									</td>
									<td style="width: 10%; text-align: center;"><label><a
											href="javascript:problemasSoluciones('<c:out value="${reporteAvance.reporteAvanceID }" ></c:out>','<c:out value="${reporteAvance.prefijoEstadoRepAvance }" />')"
											id="problemasSoluciones" class="linkSelecciona">Problemas<br>Soluciones</a>
									</label>
									</td>
									<!-- <td style="width: 10%; text-align: center;"><label><a
											href="javascript:personalTecnicoAdministratico('<c:out value="${reporteAvance.reporteAvanceID }" />','<c:out value="${reporteAvance.prefijoEstadoRepAvance }" />')"
											id="beneficiarios" class="linkSelecciona">Personal<br>Tecnico<br>Administratico</a>
									</label>
									</td> -->
									<!-- <td style="width: 10%; text-align: center;"><label><a href="javascript:imagenReporte('<c:out value="${reporteAvance.reporteAvanceID }" />')"
												id="beneficiarios" class="linkSelecciona">Imagenes<br>de<br>Reporte</a></label></td>	 -->
								</tr>
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			<script type="text/javascript">
				var objReporteAvance = new Object();
				objReporteAvance.reporteAvanceID = "<c:out value="${reporteAvance.reporteAvanceID }"></c:out>";
				objReporteAvance.periodo = "<c:out value="${reporteAvance.periodo}"></c:out>";

				arrayReporteAvance.push(objReporteAvance);
			</script>
		</c:forEach>
	</tbody>
</table>
