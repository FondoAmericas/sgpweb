<%@ include file="/common/taglibs.jsp"%>


<script type="text/javascript">
	/*$(window).load(function() {
		ocultaCampos();
	});
	
	function ocultaCampos(){
		estado= "<c:out value="${estado}"></c:out>" ;
		//alert(estado);
		if ((estado =='apro')|| (estado == 'eval')){
			$(".hide").hide();//attr("style","visibility: hidden;");
			$(".disabled").attr("disabled","disabled");
			//alert($('#cbxInstitucionEjecutora option:selected').html());
			//$("#div_cbxInstitucionEjecutora").attr("style","visibility: hidden;");
		}
		
	}*/
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#cronograma").show("fast");
		$("#cronogramaSeleccionado").hide("fast");
	});
</script>

<script type="text/javascript">
	function buscaActividades(resultadoId,actividadID,metaPorActividadID) {
		/*$("#cuerpoAvanceResultadosActividad").load(
				"cuerpoAvanceResultadosActividad.jspx", {
					resultadoId : resultadoId
				});*/
		$("#sltActividad").load("cargarCombo.jspx", {resultadoID:resultadoId,metodo:"cargarActividad"},function(){
			if(actividadID!=0){
				$("#sltActividad").attr("value",actividadID);
			}
			buscaMetasXActividad($("#sltActividad").val(),metaPorActividadID);
			
		});		
	}

	function buscaMetasXActividad(actividadId,metasXActividadId) {
		/*$("#cuerpoAvanceResultadosActividad").load(
				"cuerpoAvanceResultadosActividad.jspx", {
					resultadoId : $("#sltResultado").val(),
					actividadId : actividadId
				}, function(){
					$("#descripcionActividad").text($("#sltActividad option:selected").attr("title"));
				});*/
		$("#sltMetaActividad").load("cargarCombo.jspx", {actividadID : actividadId,metodo:"cargarMetaActividad"}, function(){
			$("#descripcionActividad").text($("#sltActividad option:selected").attr("title"));
			if(metasXActividadId!=0){
				$("#sltMetaActividad").attr("value",metasXActividadId);
			}
			buscaCronogramaMetasXActividad($("#sltMetaActividad").val());
			//}
		});		
	}

	function buscaCronogramaMetasXActividad(metasXActividadId) {
		/*$("#cuerpoAvanceResultadosActividad").load(
				"cuerpoAvanceResultadosActividad.jspx", {
					resultadoId : $("#sltResultado").val(),
					actividadId : $("#sltActividad").val(),
					metasXActividadId : metasXActividadId
				}, function(){
					$("#descripcionActividad").text($("#sltActividad option:selected").attr("title"));
				});*/
		$("#cuerpoCronograma").load("cargarCombo.jspx",{metasXActividadId:metasXActividadId,metodo:"cargarCronogramaMetaActividad"},function(){
			//$("#descripcionActividad").text($("#sltActividad option:selected").attr("title"));
			//$("#unidadMedidaSeleccionado").text($("#sltMetaActividad option:selected").attr("title"));
			$("#unidadMedidaSeleccionado").attr("value",$("#sltMetaActividad option:selected").attr("title"));
		});
	}

	function cerrarPantalla() {
		window.close();
	}

</script>

<script type="text/javascript">
	function grabarAvanceResultado() {
		var error = validaCajas();

		if (error == 0) {
			$.get("grabarAvanceResultadoActividad.jspx", {
				avanceResultadoActividadId : $("#avanceResultadoActividadId").val(),	
				cantidadAvanceEjecutado : $("#cantidadAvanceEjecutado").val(),
				descripcionActividad : $("#descripcionActividad").val(),
				resumenEjecutivo : $("#resumenEjecutivo").val(),
				elementoVerificacion : $("#elementoVerificacion").val(),
				observaciones : $("#observaciones").val(),
				reporteAvanceId : $("#reporteAvanceId").val(),
				metaActividadId : $("#sltMetaActividad").val()
			}, function() {
				limpiarCajas();
				$("#grillaAvanceResultadosActividad").load(
						"grillaAvanceResultadoActividad.jspx", {
							reporteAvanceId : $("#reporteAvanceId").val(),
							estado : $("#estado").val()
						});
				
			});
		}
	}
	
	function modificarAvanceResultadoActividad(avanceResultadoActividadID, cantidadAvanceEjecutado ,descripcionActividad ,
			resumenEjecutivoPeriodo ,elementoVerificacion ,observaciones,metaPorActividadID ,actividadID,resultadoID ){

		var confirma = confirm("Esta seguro que desea cargar el registro para modificarlo?");
		
		if(confirma==true){
		$("#sltResultado").attr("value",resultadoID);
		buscaActividades(resultadoID,actividadID,metaPorActividadID);
		
		$("#avanceResultadoActividadId").attr("value",avanceResultadoActividadID);
		$("#cantidadAvanceEjecutado").attr("value",cantidadAvanceEjecutado);
		$("#descripcionActividad").attr("value",descripcionActividad);
		$("#resumenEjecutivo").attr("value",resumenEjecutivoPeriodo);
		$("#elementoVerificacion").attr("value",elementoVerificacion);
		$("#observaciones").attr("value",observaciones);
	}
	}
	
	function eliminarAvanceResultadoActividad(avanceResultadoActividadID ){
		var confirma = confirm("Desea eliminar el registro seleccionado?");

		if (confirma == true) {
			$.get("eliminarAvanceResultadoActividad.jspx", {
				avanceResultadoActividadId : avanceResultadoActividadID	
			}, function() {
				//limpiarCajas();
				$("#grillaAvanceResultadosActividad").load(
						"grillaAvanceResultadoActividad.jspx", {
							reporteAvanceId : $("#reporteAvanceId").val(),
							estado : $("#estado").val()
						});
				
			});
		}		
	}
</script>

<script type="text/javascript">
function limpiarCajas(){
	$("#sltResultado").attr("value",0);
	buscaActividades(0,0,0);
	//$("#sltActividad").attr("value",actividadID);
	//buscaMetasXActividad(0,metaPorActividadID);
	//$("#sltMetaActividad").attr("value",metaPorActividadID);
	//buscaCronogramaMetasXActividad(metaPorActividadID);
	
	$("#avanceResultadoActividadId").attr("value","");
	$("#cantidadAvanceEjecutado").attr("value","");
	$("#descripcionActividad").attr("value","");
	$("#resumenEjecutivo").attr("value","");
	$("#elementoVerificacion").attr("value","");
	$("#observaciones").attr("value","");

}

</script>

<script type="text/javascript">
	function validaCajas() {
		var errores = 0;
		var mensaje = null;

		if ($("#sltMetaActividad").val().length == 0) {
			var mensajeComp = "Debe seleccionar una meta actividad . \n";
			if (mensaje == null) {
				mensaje = mensajeComp;
			} else {
				mensaje = mensaje + mensajeComp;
			}
			errores = errores + 1;
		}

		if ($("#cantidadAvanceEjecutado").val().length == 0) {
			var mensajeComp = "Debe ingresar la cantidad de la meta ejecutada. \n";
			if (mensaje == null) {
				mensaje = mensajeComp;
			} else {
				mensaje = mensaje + mensajeComp;
			}
			errores = errores + 1;
		}

		if ($("#descripcionActividad").val().length == 0) {
			var mensajeComp = "Debe ingresar la descripcion de la actividad. \n";
			if (mensaje == null) {
				mensaje = mensajeComp;
			} else {
				mensaje = mensaje + mensajeComp;
			}
			errores = errores + 1;
		}

		if ($("#resumenEjecutivo").val().length == 0) {
			var mensajeComp = "Debe ingresar el resumen ejecutivo. \n";
			if (mensaje == null) {
				mensaje = mensajeComp;
			} else {
				mensaje = mensaje + mensajeComp;
			}
			errores = errores + 1;
		}

		if ($("#elementoVerificacion").val().length == 0) {
			var mensajeComp = "Debe ingresar el elemento de verificacion. \n";
			if (mensaje == null) {
				mensaje = mensajeComp;
			} else {
				mensaje = mensaje + mensajeComp;
			}
			errores = errores + 1;
		}

		if ($("#observaciones").val().length == 0) {
			var mensajeComp = "Debe ingresar una observacion. \n";
			if (mensaje == null) {
				mensaje = mensajeComp;
			} else {
				mensaje = mensaje + mensajeComp;
			}
			errores = errores + 1;
		}

		if (errores > 0) {
			alert(mensaje);
		}

		return errores;
	}
</script>

<fieldset>
<input type="hidden" id="avanceResultadoActividadId" name="avanceResultadoActividadId" >
	<legend>Detalle de Avance de Resultados</legend>
	<div>
		<table width="100%">
			<tr>
				<td style="width: 25%; text-align: right; vertical-align: top;"><label>Resultado:</label>
				</td>
				<td colspan="3" style="width: 75%; text-align: left;"><select
					name="sltResultado" id="sltResultado" style="width: 98%;"
					onchange="buscaActividades(this.value,0,0)">
						<option value="0">-- Resultado --</option>

						<c:forEach items="${listResultado }" var="resultado">
							<c:choose>
								<c:when test="${resultado.resultadoID == resultadoId }">
									<option value="${resultado.resultadoID }" selected="selected">
										<c:out value="${resultado.definicionResultado }" />
									</option>
								</c:when>
								<c:otherwise>
									<option value="${resultado.resultadoID }">
										<c:out value="${resultado.definicionResultado }" />
									</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td style="width: 25%; text-align: right; vertical-align: top;"><label>Actividad:</label>
				</td>
				<td colspan="3" style="width: 75%; text-align: left;"><select
					name="sltActividad" id="sltActividad" style="width: 98%;"
					onchange="buscaMetasXActividad(this.value,0)">
						<option value="0">-- Actividad --</option>
						<c:forEach items="${listActividad }" var="actividad">
							<c:choose>
								<c:when test="${actividad.actividadID == actividadId }">
									<option value="${actividad.actividadID }" selected="selected" title="<c:out value="${actividad.descripcionActividad }" />" >
										<c:out value="${actividad.nombreActividad }" />
									</option>
								</c:when>
								<c:otherwise>
									<option value="${actividad.actividadID }"  title="<c:out value="${actividad.descripcionActividad }" />" >
										<c:out value="${actividad.nombreActividad }" />
									</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
							<td style="width: 25%; text-align: right; vertical-align: top;"><label>Descripcion
									de Actividad:</label>
							</td>
							<td colspan="3" style="width: 75%; text-align: left;"><textarea
									id="descripcionActividad" name="descripcionActividad" rows="4"
									cols="5" style="width: 98%;" disabled="disabled"></textarea>
							</td>
						</tr>
			<tr>
				<td style="width: 25%; text-align: right; vertical-align: top;"><label>Meta
						por Actividad:</label>
				</td>
				<td style="width: 25%; text-align: left;"><select
					name="sltMetaActividad" id="sltMetaActividad" style="width: 98%;"
					onchange="buscaCronogramaMetasXActividad(this.value,0)">
						<option value="0">-- Meta Actividad --</option>
						<c:forEach items="${listMetaPorActividadBean }"
							var="metaPorActividadBean">
							<c:choose>
								<c:when
									test="${metaPorActividadBean.metaPorActividadID == metasXActividadId }">
									<option value="${metaPorActividadBean.metaPorActividadID }"
										selected="selected">
										<c:out
											value="${metaPorActividadBean.cantidadMetaActividad } - " />
										<c:out
											value="${metaPorActividadBean.descripcionUnidadMedida }" />

									</option>
								</c:when>
								<c:otherwise>
									<option value="${metaPorActividadBean.metaPorActividadID }">
										<c:out
											value="${metaPorActividadBean.cantidadMetaActividad } - " />
										<c:out
											value="${metaPorActividadBean.descripcionUnidadMedida }" />
									</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select>
				</td>
				<td colspan="2" style="width: 50%;"></td>
			</tr>
			<tr id="cronograma">
				<td style="width: 25%;"></td>
				<td colspan="3" style="width: 75%;"><br>
					<table border="0" width="98%" class="table-clasico">
						<caption>Cronograma de Metas Por Actividad</caption>
						<thead>
							<tr>
								<td style="width: 30%; text-align: center;"><label>Periodo</label>
								</td>
								<td style="width: 35%; text-align: center;"><label>Cantidad
										Programada</label></td>
								<!-- <td style="width: 35%; text-align: center;"><label>Seleccionar</label></td> -->
							</tr>
						</thead>
						<tbody id="cuerpoCronograma">
							<c:forEach items="${listCronogramaMetaPorActividadBean }"
								var="cronogramaMetaPorActividadBean" varStatus="indice">
								<c:choose>
									<c:when test="${indice.count %2== 0}">
										<c:set var="classIdi" value="f2"></c:set>
									</c:when>
									<c:otherwise>
										<c:set var="classIdi" value="f1"></c:set>
									</c:otherwise>
								</c:choose>
								<tr class="<c:out value="${classIdi}"></c:out>">
									<td style="width: 30%; text-align: left;"><label>Periodo
											<c:out value="${cronogramaMetaPorActividadBean.periodo }"></c:out>
									</label></td>
									<td style="width: 35%; text-align: left;"><label><c:out
												value="${cronogramaMetaPorActividadBean.cantidadMetaActividadProgPorPeriodo } - "></c:out>
											<c:out
												value="${cronogramaMetaPorActividadBean.descripcionUnidadMedida }"></c:out>
									</label></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>
			<!-- <tr id="cronogramaSeleccionado">
					<td colspan="4" style="width: 100%;">
					<table width="100%" border="0">
					<tr>
					<td style="width: 25%;text-align: right;"><label>Periodo:</label></td>
					<td style="width: 25%;text-align: left;">
					<input type="text" id="periodoSeleccionado" name="periodoSeleccionado" disabled="disabled" style="background-color: white;" class="labelMuestra" >
					<input type="hidden" id="cronogramaMetaActividadId" name="cronogramaMetaActividadId" >
					 </td>
					<td style="width: 25%;text-align: right;"><label>Cantidad Programada:</label></td>
					<td style="width: 25%;text-align: left;">
					<input type="text" id="cantidadProgramadaSeleccionado" name="cantidadProgramadaSeleccionado" disabled="disabled" style="background-color: white;text-align: center;" class="labelMuestra">
					</td>
				</tr>
				<tr>	
					<td colspan="2" style="width: 50%;">
					<td colspan="2" style="width: 50%;text-align: center;">
						<br>
						<input type="button" value="Cancelar Seleccion" onclick="cancelarSeleccion()">
					</td>
					</tr>
					</table>
					</td>
				</tr>-->
			<tr>
				<td colspan="4" style="width: 100%;"><hr style="width: 97%;">
				</td>
			</tr>
			<tr>
				<td colspan="4" style="width: 100%;">
					<table style="width: 100%;">
						<tr>
							<td style="width: 25%; text-align: right; vertical-align: top;"><label>Cantidad
									Avance Ejecutado:</label>
							</td>
							<td colspan="3" style="width: 75%; text-align: left;"><input
								type="text" id="cantidadAvanceEjecutado"
								name="cantidadAvanceEjecutado" style="width: 100px;"
								maxlength="14" 
								onkeypress="javascript:return Valida_Dato(event,8);"> <input
								type="text" id="unidadMedidaSeleccionado"
								name="unidadMedidaSeleccionado" disabled="disabled"
								style="background-color: white; text-align: center;"
								class="labelMuestra" value="${unidadMedida }">
							</td>
						</tr>
						<tr>
							<td style="width: 25%; text-align: right; vertical-align: top;"><label>Resumen
									Ejecutivo:</label>
							</td>
							<td colspan="3" style="width: 75%; text-align: left;"><textarea
									id="resumenEjecutivo" name="resumenEjecutivo" rows="4" cols="5"
									style="width: 98%;"></textarea>
							</td>
						</tr>
						<tr>
							<td style="width: 25%; text-align: right; vertical-align: top;"><label>Elemento
									de Verificación:</label>
							</td>
							<td colspan="3" style="width: 75%; text-align: left;"><textarea
									id="elementoVerificacion" name="elementoVerificacion" rows="4"
									cols="5" style="width: 98%;"></textarea>
							</td>
						</tr>
						<tr>
							<td style="width: 25%; text-align: right; vertical-align: top;"><label>Observaciones:</label>
							</td>
							<td colspan="3" style="width: 75%; text-align: left;"><textarea
									id="observaciones" name="observaciones" rows="4" cols="5"
									style="width: 98%;"></textarea>
							</td>
						</tr>
						<tr>
							<td style="width: 25%;"></td>
							<td colspan="3" style="width: 75%; text-align: right;"><input
								type="button" id="btnAgregarAvanceResultado"
								name="btnAgregarAvanceResultado"
								onclick="grabarAvanceResultado()"
								value="Agregar Avance Resultado"> <input type="button"
								id="btnCerrarPantalla" name="btnCerrarPantalla"
								onclick="cerrarPantalla()" value="Cerrar">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</fieldset>