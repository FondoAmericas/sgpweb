<%@include file="/common/includesTaglibsGenerico.jsp"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<script type="text/javascript"
	src="<c:url value="/js/util.js"></c:url>"></script>
	
	
<style type="text/css">
div.ui-datepicker {
	font-size: 62.5%;
}

div.ui-dialog {
	font-size: 62.5%;
}

div.ui-accordion {
	font-size: 60.5%;
}
</style>


<script type="text/javascript">
	$(window).load(function() {
		ocultaCampos();
	});

	function ocultaCampos() {
		var estado = "<c:out value="${estado}"></c:out>";
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
				var mensaje = "<c:out value="${mensaje}"></c:out>";
				if ((mensaje != '') && (mensaje != undefined)) {
					alert(mensaje);
				}
			});

	function fRegistrar() {
		//	window.opener.fRefresh("500");

		if (validar()) {
			return;
		}
		var form = document.formDetallePago;
		form.action = "actionRegistrarDetallePago.jspx";
		form.submit();

	}
	function getPrecioTotal() {
		var mensaje1 = "";

		/*if ($('#cantidad').attr("value") == '') {
			mensaje1 += "\n - cantidad.";
		}
		if ($('#precioUnitario').attr("value") == '') {
			mensaje1 += "\n - Precio Unitario.";
		}
		if (mensaje1 != "") {
			alert("Debe ingresar:" + mensaje1);
			return;
		}*/

		cantidad = parseFloat($('#cantidad').attr("value"));
		precioUnitario = parseFloat($('#precioUnitario').attr("value"));

		var precioTotal = parseFloat(cantidad * precioUnitario);
		var saldoDisponible = parseFloat($('#saldoDisponible').attr("value"));
		if (precioTotal > saldoDisponible) {
			alert("El Precio Total tiene que ser menor o igual a Saldo Disponible");
			/*$('#precioUnitario').attr("value", "");
			$('#cantidad').attr("value", "");*/
			$('#cantidad').focus();
			return;
		} else {
			if(($('#precioUnitario').attr("value") != '')&&($('#cantidad').attr("value") != '')){
			$('#precioTotal').attr("value", precioTotal.toFixed(2));
			}
		}

	}

	function validar() {
		var mensaje = "";
		//var cabecera = "Debe ingresar la siguiente informaci\u00F3n obligatoria(*):";
		if ($('#cantidad').attr("value") == '') {
			mensaje += "\n Ingresar cantidad.";
		}
		if ($('#precioUnitario').attr("value") == '') {
			mensaje += "\n Ingresar Precio Unitario.";
		}
		if ($('#precioTotal').attr("value") == 0) {
			mensaje += "\n - Precio Total.";
		}
		/*if ($('#activo').attr("value") == '') {
			mensaje += "\n - Activo.";
		}*/
		if ($('#concepto').attr("value") == '') {
			mensaje += "\n Ingresar Descripcion de Activo.";
		}

		var precioTotal = parseInt($('#precioTotal').val());
		var montoActividadDetallePagoDeclarado = parseInt($("#montoActividadDetallePagoDeclarado").val());	
		if (montoActividadDetallePagoDeclarado > precioTotal) {
			mensaje += "\n Precio Total no puede ser menor a la suma de los periodos de las actividades declaradas.";
		}
		
		if (mensaje != "") {
			alert(mensaje);
			return true;
		} else {
			return false;
		}

	}
	function goTo(url) {
		fOpenModalDialog(url, '900', '700', '50', '100');
	}

	function fCerrar() {
		 window.opener.fRefresh("50");
		//window.close();
		
	}
	function fModificar(detallePagoLiquidacionID,cantidad,fkIdtablaespUnidadMedida,
					precioUnitario,fkIdtablaespTipoMoneda,precioTotal,concepto,fkIdtablaespCategoriaActivo,
					activoID,montoActividadDetallePagoDeclarado) {
		if (confirm("Desea modificar el Detalle Pago?")) {
			
			var saldoDisponible = parseFloat($("#saldoDisponible").val());
			var saldoNuevo = saldoDisponible + parseFloat(precioTotal);
			$("#saldoDisponible").attr("value",saldoNuevo);
			$("#saldoDisponibleLabel").html(saldoNuevo);
			
			$('#detallePagoID').attr("value",detallePagoLiquidacionID);
			$('#montoActividadDetallePagoDeclarado').attr("value",montoActividadDetallePagoDeclarado);
			$('#cantidad').attr("value",cantidad);
			$('#precioUnitario').attr("value",precioUnitario);
			$('#precioTotal').attr("value",precioTotal);
			$('#concepto').html(concepto);
			$("#unidadMedida").attr("value",fkIdtablaespUnidadMedida);
			$("#categoriaActivo").attr("value",fkIdtablaespCategoriaActivo);
			llenaActivo(activoID);	
			/*var form = document.formDetallePago;
			form.action = "actionCargarDetallePago.jspx?detallePagoID=" + id;
			form.submit();*/
		}
	}
	function fEliminar(id) {
		if (confirm("Desea eliminar el Detalle Pago?")) {
			var form = document.formDetallePago;
			form.action = "actionEliminarDetallePago.jspx?detallePagoID=" + id;
			form.submit();
		}
	}
	
	function llenaActivo(activoID){
		  var categoriaActivoId = $("#categoriaActivo").val();
		    var nomMetodo = "cargarBienActivo";
		    $("#activo").load("cargarCombo.jspx", {categoriaActivoId:categoriaActivoId,metodo:nomMetodo},function(){
		    	if(activoID!=0){
		    		$("#activo").attr("value",activoID);
		    	}
		    });
	}
	
	function limpiarCajas(){
		
		$('#detallePagoID').attr("value",0);
		$('#montoActividadDetallePagoDeclarado').attr("value",0);
		$('#cantidad').attr("value","");
		$('#precioUnitario').attr("value","");
		$('#precioTotal').attr("value","");
		$('#concepto').html("");
		$("#unidadMedida").attr("value",0);
		$("#categoriaActivo").attr("value",0);
		llenaActivo(0);
	}
</script>

</head>
<body onunload="fCerrar();"> 

<div class="form-clasico">

	<div class="encabezado">Detalle Pago</div>
	<form:form id="formDetallePago" name="formDetallePago" 
		action="actionCrearLiquidacionGastos.jspx" method="POST" >
		<br />
		<input type="hidden" id="pagoLiquidacionID" name="pagoLiquidacionID"
			value="${pagoLiquidacionID}">
		<input type="hidden" id="detallePagoID" name="detallePagoID"
			value="${detallePagoID}">
		<input type="hidden" id="saldoDisponible" name="saldoDisponible"
			value="${pagoLiquidacion.saldoDisponible}">
		<input type="hidden" id="montoActividadDetallePagoDeclarado" name="montoActividadDetallePagoDeclarado"
			value="${montoActividadDetallePagoDeclarado }" >
		<fieldset style="padding-left: 15px">
			<legend>Regristrar</legend>
			<br />
			<fieldset style="padding-left: 15px">
				<legend>Datos Ingreso Pago</legend>
				<table style="width: 100%">
					<tr>
						<td style="text-align: right;"><label><b>N&uacute;mero
									Cheque:</b>
						</label></td>
						<td><label> <c:out
									value="${pagoLiquidacion.numeroCheque}" /> </label></td>
						<td style="text-align: right;"><label><b>Fecha
									Emision:</b>
						</label></td>
						<td><label><c:out
									value="${pagoLiquidacion.fechaEmision}" />
						</label></td>
					</tr>
					<tr>
						<td style="text-align: right;"><label><b>N&uacute;mero
									Comprobante:</b>
						</label></td>
						<td><label><c:out
									value="${pagoLiquidacion.numeroDocumento} " />-<c:forEach
									items="${listTipoComprobante}" var="modali">
									<c:if
										test="${ pagoLiquidacion.fkIdtablaespTipoComprobantePago == modali.tablaEspecificaID}">
										<c:out value="${modali.descripcionCabecera}" />
									</c:if>
								</c:forEach> </label></td>
						<td style="text-align: right;"><label><b>Ruc
									Proveedor:</b>
						</label></td>
						<td><label><c:out
									value="${pagoLiquidacion.rucProveedor}" />
						</label></td>
					</tr>
					<tr>
						<td style="text-align: right;"><label><b>Raz&oacute;n
									Social:</b>
						</label></td>
						<td><label><c:out
									value="${pagoLiquidacion.razonSocial}" />
						</label></td>
						<td style="text-align: right;"><label><b>Saldo
									disponible:</b>
						</label></td>
						<td><label id="saldoDisponibleLabel"><c:out
									value="${pagoLiquidacion.saldoDisponible}" /> </label><label><c:forEach
									items="${listTipoMoneda}" var="modali">
									<c:if
										test="${pagoLiquidacion.fkIdtablaespTipoMoneda == modali.tablaEspecificaID}">
										<c:out value="${modali.descripcionCabecera}" />
									</c:if>
								</c:forEach> </label></td>

					</tr>
				</table>
			</fieldset>
			<br />
			<table style="width: 100%" class="hide">
				<tr>
					<td style="text-align: right;"><label>Cantidad:</label></td>
					<td><input type="text" name="cantidad" id="cantidad"
						value="${detallePagoLiquidacion.cantidad}"
						onchange="getPrecioTotal()"
						onkeydown="if(event.keyCode == 13){getPrecioTotal();}"
						onkeypress="return isNumberKeyP(event);"> <select
						name="unidadMedida" id="unidadMedida" >
							<option value="0">
								<c:out value="-- Unidad de Medida --" />
							</option>
							<c:forEach items="${listUnidadMedida}" var="modali">
								<option value="${modali.tablaEspecificaID}"
									<c:if test="${ detallePagoLiquidacion.fkIdtablaespUnidadMedida == modali.tablaEspecificaID}"> selected="selected" </c:if>>
									<c:out value="${modali.descripcionCabecera}" />
								</option>
							</c:forEach>
					</select>
					</td>
					<td style="text-align: right;"><label>Precio Unitario:</label>
					</td>
					<td><input type="text" name="precioUnitario"
						id="precioUnitario" style="width: 100px;"
						value="${detallePagoLiquidacion.precioUnitario}"
						onkeydown="if(event.keyCode == 13){getPrecioTotal();}"
						onchange="getPrecioTotal()"
						onkeypress="return isNumberKeyP(event);">
						<c:forEach items="${listTipoMoneda}" var="modali">
							<c:if
								test="${pagoLiquidacion.fkIdtablaespTipoMoneda == modali.tablaEspecificaID}">
								<label><c:out value="${modali.descripcionCabecera}" />
								</label>
							</c:if>
						</c:forEach></td>
				</tr>
				<tr>
					<td style="text-align: right;"><label>Precio Total:</label></td>
					<td><input type="text" name="precioTotal" maxlength="0"
						id="precioTotal" value="${detallePagoLiquidacion.precioTotal}"
						onkeypress="javascript:return Valida_Dato(event,7), isNumberKeyPBD(event);" />
						<c:forEach items="${listTipoMoneda}" var="modali">
							<c:if
								test="${pagoLiquidacion.fkIdtablaespTipoMoneda == modali.tablaEspecificaID}">
								<label><c:out value="${modali.descripcionCabecera}" />
								</label>
							</c:if>
						</c:forEach></td>
				</tr>
				<tr>
					<td style="text-align: right;"><label>Concepto:</label></td>
					<td colspan="3"><textarea maxlength="250" style="width: 100%" id="concepto" name="concepto"><c:out value="${detallePagoLiquidacion.concepto}" /></textarea></td>
				</tr>
				<tr>
				<td style="text-align: right;"><label>Categoria Activo:</label></td>
					<td><select name="categoriaActivo" id="categoriaActivo" onchange="llenaActivo(0)">
							<option value="0">
								<c:out value="-- Categoria Activo --" />
							</option>
							<c:forEach items="${listCategoriaActivo}" var="modali">
								<option value="${modali.tablaEspecificaID}"
									<c:if test="${ activo.fkIdtablaespCategoriaActivo == modali.tablaEspecificaID}"> selected="selected" </c:if>>
									<c:out value="${modali.descripcionCabecera}" />
								</option>
							</c:forEach>
					</select></td>
					<td style="text-align: right;"><label>Bien / Activo:</label></td>
					<td><select name="activo" id="activo">
							<option value="0">
								<c:out value="-- Bien/Activo --" />
								<c:forEach items="${listActivo}" var="lActivo">
								<c:choose>
									<c:when
										test="${lActivo.activoID == activo.activoID }">
										<option selected="selected"
											value="${lActivo.activoID}">
											<c:out value="${lActivo.descripcionActivo }" />
										</option>
									</c:when>
									<c:otherwise>
										<option
											value="${lActivo.activoID}">
											<c:out value="${lActivo.descripcionActivo }" />
										</option>
									</c:otherwise>
								</c:choose>

							</c:forEach>
							</option>
					</select></td>
				</tr>
				<tr><td colspan="4" ><br/></td></tr>
				<tr>
					<td colspan="4" style="text-align: right;"><input
						type="button" name="Agregar" value="Grabar"
						onclick="javascript:fRegistrar();"> <input type="reset"
						name="Limpiar" value="Limpiar" onclick="javascript:limpiarCajas();">  <input type="button"
						value="Cerrar" id="idBtnCerrar" onclick="fCerrar()"/></td>
				</tr>

			</table>

			<table class="table-clasico" style="width: 100%">
				<caption>Lista de Detalle de Pago</caption>
				<thead>
					<tr>
						<th align="center"><label>Cantidad</label>
						</th>
						<th align="center"><label>Precio Unitario</label>
						</th>
						<th align="center"><label>Precio Total</label>
						</th>
						<th align="center"><label>Concepto</label>
						</th>
						<th align="center"><label>Categoria Activo<br/>Bien/Activo</label>
						</th>
						<th align="center"><label>Actividad</label>
						</th>
						<th align="center" class="hide" ><label>Operaciones</label></th>
					</tr>
				</thead>
				<c:forEach var="detallePagoG" items="${listDetallePagoLiquidacion}"
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

						<td align="center"><c:out value="${detallePagoG.cantidad}"></c:out>&nbsp;
							<c:forEach items="${listUnidadMedida}" var="modali">
								<c:if
									test="${ detallePagoG.fkIdtablaespUnidadMedida == modali.tablaEspecificaID}">
									<c:out value="${modali.descripcionCabecera}" />
								</c:if>
							</c:forEach></td>
						<td align="center"><c:out
								value="${detallePagoG.precioUnitario}"></c:out>&nbsp; <c:forEach
								items="${listTipoMoneda}" var="modali1">
								<c:if
									test="${modali1.tablaEspecificaID== detallePagoG.pagoLiquidacion.fkIdtablaespTipoMoneda}">
									<c:out value="${modali1.descripcionCabecera}" />
								</c:if>
							</c:forEach></td>
						<td align="center"><c:out value="${detallePagoG.precioTotal}"></c:out>
							&nbsp; <c:forEach items="${listTipoMoneda}" var="modali1">
								<c:if
									test="${modali1.tablaEspecificaID== detallePagoG.pagoLiquidacion.fkIdtablaespTipoMoneda}">
									<c:out value="${modali1.descripcionCabecera}" />
								</c:if>
							</c:forEach></td>
							<td align="center"><c:out value="${detallePagoG.concepto}"></c:out>
							</td>
<td align="center"><c:out value="${detallePagoG.activo.descripcionCategoriaActivo}"></c:out><br/>
<c:out value="${detallePagoG.activo.descripcionActivo}"></c:out>
							</td>

						<td align="center"><a
							href="javascript:goTo('showActividadDetallePago.jspx?detallePagoID=${detallePagoG.detallePagoLiquidacionID}&estado=${estado }');"
							class="linkSelecciona" style="cursor: pointer" title="Actividad">Actividad</a>
						</td>
						<td align="center" class="hide"><a
							href="javascript:fModificar('${detallePagoG.detallePagoLiquidacionID}','${detallePagoG.cantidad}',
														'${detallePagoG.fkIdtablaespUnidadMedida}','${detallePagoG.precioUnitario}',
														'${detallePagoG.pagoLiquidacion.fkIdtablaespTipoMoneda}','${detallePagoG.precioTotal}',
														'${detallePagoG.concepto}','${detallePagoG.activo.fkIdtablaespCategoriaActivo}',
														'${detallePagoG.activo.activoID}','${detallePagoG.montoActividadDetallePagoDeclarado}')"
							style="cursor: pointer" class="linkSelecciona" title="Modificar">Modificar</a><br />
							<a
							href="javascript:fEliminar('${detallePagoG.detallePagoLiquidacionID}')"
							style="cursor: pointer" class="linkSelecciona" title="Eliminar">Eliminar</a>
						</td>
					</tr>
				</c:forEach>

			</table>

		</fieldset>
	</form:form>
</div>
</body>
</html>