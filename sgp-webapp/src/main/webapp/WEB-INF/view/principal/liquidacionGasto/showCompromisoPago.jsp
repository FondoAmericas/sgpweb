<%@include file="/common/includesTaglibsGenerico.jsp"%>

<script type="text/javascript">
	$(window).load(function() {
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
	}
</script>

<script type="text/javascript">
	$(document).ready(
			function() {
				if ($('#compromisoPagoID').attr("value") > 0) {
					$('#periodo').attr("disabled", false);
					$('#costoActividad').attr("disabled", false);
					fgetCronogramaCostoActividad();
				}
				if ($('#mensaje').attr("value") != ''
						&& $('#mensaje').attr("value") != undefined) {
					alert($('#mensaje').attr("value"));
					fgetCronogramaCostoActividad();
				}

			});
	
	var arrayCronogramaCA = new Array();

	function fRegistrar() {
		if (validar()) {
			return;
		}
		compromisoID = $('#compromisoPagoID').attr("value");
		if (compromisoID == '0') {
			fgetCronogramaDisponible();

		} else {
			var form = document.formCompromisoPago;
			form.action = "actionRegistrarModificarCompromisoPago.jspx";
			form.submit();
		}

	}

	function fModificar(id) {
		var form = document.formCompromisoPago;

		form.action = "actionCargaCompromisoPago.jspx?compromisoPagoID=" + id;
		form.submit();

	}

	function fEliminar(id) {
		if (confirm("Desea eliminar el Compromiso Pago?")) {
			var form = document.formCompromisoPago;
			form.action = "actionEliminarCompromisoPago.jspx?compromisoPagoID="
					+ id+"&fuenteFinanciadora="+$("#fuenteFinanciadora").val();
			form.submit();

		}

	}

	function validar() {
		var cabecera = "Debe ingresar la siguiente informaci\u00F3n obligatoria(*):";
		var mensaje = "";
		if ($('#resultado').attr("value") == 0) {
			mensaje += "\n - Resultado.";
		}
		if ($('#actividad').attr("value") == 0) {
			mensaje += "\n - Actividad.";
		}

		if ($('#costoActividad').attr("value") == 0) {
			mensaje += "\n - Costo Actividad.";
		}
		if ($('#periodo').attr("value") == 0) {
			mensaje += "\n - Periodo.";
		}
		if ($('#observacion').attr("value") == '') {
			mensaje += "\n - Observaci\u00F3n.";
		}
		if ($('#montoCompromiso').attr("value") == '') {
			mensaje += "\n - Monto Compromiso.";
		}
		if (mensaje != "") {
			alert(cabecera + mensaje);
			return true;
		} else {
			return false;
		}

	}
	function fCargarActividad() {
		var idResultado = $("#resultado").val();
		var nomMetodo = "cargarActividad";
		$("#actividad").load("cargarCombo.jspx", {
			resultadoID : idResultado,
			metodo : nomMetodo
		});
		$('#periodo').attr("disabled", true);
		$('#periodo').attr("value", 0);
		$('#costoActividad').attr("disabled", true);
		$('#costoActividad').attr("value", 0);
	}

	function fCargarCostoActividad() {
		var idActividad = $("#actividad").val();
		var nomMetodo = "cargarCostoActividad";
		$("#costoActividad").load("cargarCombo.jspx", {
			actividadID : idActividad,
			metodo : nomMetodo
		});
		$('#costoActividad').attr("disabled", false);
		$('#periodo').attr("disabled", true);
		$('#periodo').attr("value", 0);
		fSelect();
	}

	function fCargarPeriodo() {
		var idCostoActividad = $("#costoActividad").val();
		var idLiquidacion = $("#liquidacionGastoID").val();
		var nomMetodo = "cargarPeriodo";

		$("#periodo").load("cargarCombo.jspx", {
			costoActividadID : idCostoActividad,
			liquidacionGastoID : idLiquidacion,
			metodo : nomMetodo
		});
		$('#periodo').attr("disabled", false);

	}

	function fSelect() {
		$('#periodo').attr("value", 0);
		fgetCronogramaCostoActividad();
		$('#montoCompromiso').attr("value", 0);
		$('#montoCompromisoH').attr("value", 0);
	}

	function fLimpiar() {
		var form = document.formCompromisoPago;
		form.action = "showCompromisoPago.jspx";
		form.submit();
	}

	function cerrar(){
		window.close();
	}
	
	function fgetCronogramaCostoActividad() {

		$('#divGrillaCronogramaCostoActividad')
				.load(
						"cargarCronogramaCostoActividad.jspx",
						{
							periodoID : $('#periodo').attr("value")
						},
						function() {
							for ( var p = 0; p < arrayCronogramaCA.length; p++) {
								if (arrayCronogramaCA[p].cronogramaID == $(
										'#periodo').attr("value")) {
									precioUnitario = arrayCronogramaCA[p].precioUnitario;
									cantidad = arrayCronogramaCA[p].cantidad;
									$('#montoCompromiso').attr("value",
											precioUnitario * cantidad);
									$('#montoCompromisoH').attr("value",
											precioUnitario * cantidad);
									break;
								}
							}
						});
	}
	function fgetCronogramaDisponible() {
		cronograma = $('#periodo').attr("value");
		liquidacion = $('#liquidacionGastoID').attr("value");

		$
				.ajax({
					url : 'actionGetCronogramaDisponible.jspx?cronogramaID='
							+ cronograma + '&liquidacionID=' + liquidacion,
					//data: 'cronogramaID='+liquidacion,
					type : 'get',
					dataType : 'html',
					success : function(response) {
						if (response != 'SI') {
							alert(response);
							return;
						} else {
							var form = document.formCompromisoPago;
							form.action = "actionRegistrarModificarCompromisoPago.jspx";
							form.submit();

						}
					}
				});
	}
</script>

<div class="form-clasico">
	<div class="encabezado">COMPROMISOS PAGOS</div>
	<form:form name="formCompromisoPago"
		action="actionShowCompromisoPago.jspx" method="POST">
		<input type="hidden" id="liquidacionGastoID" name="liquidacionGastoID"
			value="${liquidacionGastoID}">
		<input type="hidden" id="compromisoPagoID" name="compromisoPagoID"
			value="${compromisoPagoID}">
		<input type="hidden" id="mensaje" name="mensaje" value="${mensaje}">
		<br />

		<fieldset style="padding-left: 15px">
			<legend>Compromisos</legend>
			<table style="width: 100%" class="hide">
				<tr>
<td style="text-align: right; width: 25%;">
    <label>Fuente Financiadora:</label>
</td>
<td colspan="3"  style="text-align: left; width: 75%;">
    <label><c:out value="${fuenteFinanciadora }"></c:out> 
    <input type="hidden" id="fuenteFinanciadora" name="fuenteFinanciadora" value="<c:out value="${fuenteFinanciadora }"></c:out>"></label>
</td>
</tr>				
				<tr>
					<td style="text-align: right; width: 25%;"><label>Resultado:</label></td>
					<td colspan="3" style="text-align: left; width: 75%;"><select name="resultado" style="width: 100%"
						id="resultado" onchange="javascript:fCargarActividad(),fSelect();">
							<option value="0">
								<c:out value="-- Resultado --" />
							</option>
							<c:forEach items="${listResultado}" var="modali">
								<option value="${modali.resultadoID}"
									<c:if test="${ compromisoPagoBean.resultado == modali.resultadoID}"> selected="selected" </c:if>>
									<c:out value="${modali.definicionResultado}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td  style="text-align: right; width: 25%;"><label>Actividad:</label></td>
					<td colspan="3"  style="text-align: left; width: 75%;"><select name="actividad" style="width: 100%"
						id="actividad" onchange="javascript:fCargarCostoActividad();"
						onclick="javascript:fSelectActividad(),fSelect();">
							<option value="0">
								<c:out value="-- Actividad --" />
							</option>
							<c:forEach items="${listActividad}" var="modali">
								<option value="${modali.actividadID}"
									<c:if test="${compromisoPagoBean.actividad== modali.actividadID}"> selected="selected"  </c:if>>
									<c:out value="${modali.nombreActividad}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td style="text-align: right; width: 25%;"><label>Costo Actividad
							:</label></td>
					<td  style="text-align: left; width: 25%;"><select name="costoActividad" id="costoActividad"
						onchange="javascript:fCargarPeriodo(),fSelect();" style="width: 100%;"
						disabled="disabled">
							<option value="0">
								<c:out value="-- Cant. Total - Unidad Medida --" />
							</option>
							<c:forEach items="${listaCostoActividad}" var="modali">
								<option value="${modali.costoActividadID}"
									<c:if test="${ compromisoPagoBean.costoActividad == modali.costoActividadID}"> selected="selected" </c:if>>
									<c:out value="${modali.cantidadTotal}" />
									-
									<c:forEach items="${listUnidadMedida}" var="modali1">
										<c:if
											test="${ modali1.tablaEspecificaID == modali.fkIdtablaespUnidadMedida}">
											<c:out value="${modali1.descripcionCabecera}" />
										</c:if>
									</c:forEach>

								</option>
							</c:forEach>

					</select></td>
					<td  style="text-align: right; width: 25%;"><label>Periodo:</label></td>
					<td  style="text-align: left; width: 25%;"><select name="periodo" id="periodo"
						onchange="javascript:fgetCronogramaCostoActividad();"
						disabled="disabled">
							<option value="0">
								<c:out value="-- Periodo --" />
							</option>
							<c:forEach items="${listPeriodo}" var="modali">
								<option value="${modali.cronogramaCostoActividadID}"
									<c:if test="${compromisoPagoBean.periodo== modali.cronogramaCostoActividadID}"> selected="selected"  </c:if>>
									<c:out value="Periodo ${modali.periodo}" />
								</option>
								<script type="text/javascript">
									var objCronogramaCA = new Object();
									objCronogramaCA.cronogramaID = "<c:out value="${modali.cronogramaCostoActividadID }"></c:out>";
									objCronogramaCA.cantidad = "<c:out value="${modali.cantidad}"></c:out>";
									objCronogramaCA.precioUnitario = "<c:out value="${modali.costoActividad.precioUnitario}"></c:out>";
									arrayCronogramaCA.push(objCronogramaCA);
								</script>
							</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<td  style="width: 25%;"></td>
					<td colspan="3"  style="text-align: center; width: 75%;">
						<div id="divGrillaCronogramaCostoActividad"
							style="margin: 0, auto"></div></td>
				</tr>
				<tr>
					<td  style="text-align: right; width: 25%;"><label>Monto
							Compromiso:</label></td>
					<td  style="text-align: left; width: 75%;"><input type="text" name="montoCompromiso"
						id="montoCompromiso" disabled="disabled"
						value="${compromisoPagoBean.montoCompromiso}" maxlength="0">
						<input type="hidden" name="montoCompromisoH"
						id="montoCompromisoH" 
						value="${compromisoPagoBean.montoCompromiso}" >
					</td>
				</tr>
				<tr>
					<td  style="text-align: right; width: 25%;"><label>Observacion:</label></td>
					<td colspan="3"  style="text-align: left; width: 75%;"><textarea maxlength="250" style="width: 100%"
							id="observacion" name="observacion"><c:out value="${compromisoPagoBean.observacion}" /></textarea></td>
				</tr>
				<tr>
					<td colspan="4" style="text-align: right;"><input
						type="button" name="Agregar" value="Agregar"
						onclick="javascript:fRegistrar();"> <input type="reset"
						name="Limpiar" value="Limpiar" onclick="javascript:fLimpiar();">
						<input type="button" value="Cerrar" id="idBtnCerrar"  onclick="javascript:cerrar();"/></td>
				</tr>
			</table>
			<table class="table-clasico" style="width: 100%">
				<caption>Lista</caption>
				<thead>
					<tr>
						<th align="center"><label>Periodo</label>
						</th>
						<th align="center"><label>Cantidad</label>
						</th>
						<th align="center"><label>Resultado</label>
						</th>
						<th align="center"><label>Actividad</label>
						</th>
						<th align="center"><label>Monto Compromiso</label>
						</th>
						<th align="center"><label>Fuente Financiadora</label>
						</th>
						<th align="center" class="hide"><label>Operaciones</label>
						</th>

					</tr>
				</thead>
				<c:forEach var="compromisoPagoG" items="${listCompromisoPago}" varStatus="indice">
	<c:choose>
										<c:when test="${indice.count %2== 0}">
											<c:set var="classIdi" value="f2"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdi" value="f1"></c:set>
										</c:otherwise>
									</c:choose>
	<tr class="<c:out value="${classIdi}"></c:out>">
						<td align="center">Periodo <c:out
								value="${compromisoPagoG.cronogramaCostoActividad.periodo}"></c:out>
						</td>
						<td align="center"><c:out
								value="${compromisoPagoG.cronogramaCostoActividad.cantidad}"></c:out>&nbsp;
							<c:forEach items="${listUnidadMedida}" var="modali">
								<c:if
									test="${compromisoPagoG.cronogramaCostoActividad.costoActividad.fkIdtablaespUnidadMedida == modali.tablaEspecificaID}">
									<c:out value="${modali.descripcionCabecera}" />
								</c:if>
							</c:forEach></td>
						<td align="left"><c:forEach items="${listResultado}"
								var="modali">
								<c:if
									test="${ compromisoPagoG.cronogramaCostoActividad.costoActividad.actividad.resultado.resultadoID == modali.resultadoID}">
									<c:out value="${modali.definicionResultado}" />
								</c:if>
							</c:forEach></td>
						<td align="left"><c:forEach items="${listActividadG}"
								var="modali">
								<c:if
									test="${ compromisoPagoG.cronogramaCostoActividad.costoActividad.actividad.actividadID == modali.actividadID}">
									<c:out value="${modali.nombreActividad}" />
								</c:if>
							</c:forEach></td>
						<td align="center"><c:out
								value="${compromisoPagoG.montoCompromiso}"></c:out> &nbsp; <c:forEach
								items="${listTipoMoneda}" var="modali1">
								<c:if
									test="${ compromisoPagoG.cronogramaCostoActividad.costoActividad.fkIdtablaespTipoMoneda==modali1.tablaEspecificaID }">
									<c:out value="${modali1.descripcionCabecera}" />
								</c:if>
							</c:forEach></td>
						<td align="center"><c:out
								value="${compromisoPagoG.cronogramaCostoActividad.fuenteFinanciadora.institucion.nombreInstitucion}"></c:out>
						</td>
						<td align="center" class="hide"><!-- <a
							href="javascript:fModificar('${compromisoPagoG.compromisoPagoID}')"
							class="linkSelecciona" style="cursor: pointer" title="Modificar">Modificar</a> -->
							<a
							href="javascript:fEliminar('${compromisoPagoG.compromisoPagoID}')"
							class="linkSelecciona" style="cursor: pointer" title="Eliminar">Eliminar</a>
						</td>
					</tr>
				</c:forEach>

			</table>

		</fieldset>

	</form:form>
</div>