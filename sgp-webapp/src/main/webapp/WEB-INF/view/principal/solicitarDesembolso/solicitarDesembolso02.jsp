<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/gestorAjax.js"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery-1.7.1.js"></c:url>"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/util.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.7.1.js"></script>	
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-ui-1.8.12.custom.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.ui.datepicker-es.js"></script> 	

<title>Solicitar Desembolso</title>
<script type="text/javascript">
var f = new Date();
var mes = f.getMonth() +1;
if (mes <10){
	mes="0"+mes;
}
var fechaActual=f.getDate() + "/" + (mes) + "/" + f.getFullYear();

$(document).ready(function() {
	$("#fechaSolicitud").attr("value",fechaActual);	
	$("#grillaListaDesembolso").load("cargaListaDesembolsos.jspx",{datoProyectoId:$("#datoProyectoId").val()});	
	
	var estadoPrefijoPlanOperativo= "<c:out value="${estadoPrefijoPlanOperativo }"></c:out>";
	if(estadoPrefijoPlanOperativo!='apro'){
		$("#cuerpoSolicitud").html('<label style=\"font-size: 13px; color: red;\">El PLAN OPERATIVO NO ESTA APROBADO, es por ello que no puede solicitar nuevos desembolsos.</label>');
	}
	});

/*$(function() {
	$("#fechaSolicitud").datepicker({
		changeMonth : true,
		changeYear : true
	});
});*/
</script>

<script type="text/javascript">
jQuery.fn.reset = function () {
	  $(this).each (function() { this.reset(); });
	};
/****************VALIDACION Y REGISTRO SOLICITUD**********************/
function registrarSolicitudDesembolso(){
	//console.log("entro a registrarSolicitudDesembolso");
	var campos = validarSolicitudDesembolso();
	capturarListasDatos();
	//alert("estado ->"+$("#cbxEstadoDesembolso").val() +" - TMSA->"+$("#cbxTipoMonedaSA").val() +" - TMMS->"+$("#cbxTipoMonedaMS").val() +" - Version->"+$("#txtVersionDesembolso").val()+" - Fuente->"+$("#cbxFuenteFinanciadora").val() );
	if(campos==0){
		//console.log("registrar solicitud desembolso !!!");
		//document.getElementById("solDesembolsoForm").submit();
		
		$.get("registrarSolicitudDesembolso.jspx", {
			fuenteFinanciadoraId  : $("#cbxFuenteFinanciadora").val(),
			tipoMonedaId : $("#cbxTipoMonedaMS").val(),
			estadoDesembolsoId : $("#cbxEstadoDesembolso").val(),
			tipoDesembolsoId : $("#cbxTipoDesembolso").val(),
			saldoDesembolsoAnterior : $("#txtSaldoDesembolsoAnterior").val(),
			montoSolicitado : $("#txtMontoSolicitado").val(),
			cuentaCorrienteId : $("#cbxCuentaCorriente").val(),
			periodo : $("#cbxCantPeriodoProy").val(),
			versionPeriodo : $("#txtVersionDesembolso").val(),
			datoProyectoId : $("#datoProyectoId").val(),
			arrayCronogramaSeleccionado : $("#txtArrayCronogramaSeleccionado").val(),
			arrayTipoCambio : $("#txtArrayTipoCambio").val(),
			fechaSolicitud : $("#fechaSolicitud").val()
		}, function(){
			$("#grillaListaDesembolso").load("cargaListaDesembolsos.jspx",{datoProyectoId:$("#datoProyectoId").val()});
			$("#solDesembolsoForm").reset();	
			$("#cbxFuenteFinanciadora").removeAttr("disabled");
			$("#txtMontoSolicitado").val(0);
			$("#txtVersionDesembolso").val("");
			$("#txtSaldoDesembolsoAnterior").val("0.0");
			$("#fechaSolicitud").val("");
			arrayCronogramaSeleccionado.splice(0); //borra un elemento apartir del indice que se le da
			arrayTipoCambio.splice(0); 
			$("#grillaDetalleCronogramaSeleccionado").empty();
			$("#grillaTipoCambio").empty();			
		});
	}		
}



function capturarListasDatos(){
	//Limpio cajas
	document.getElementById("txtArrayCronogramaSeleccionado").innerHTML = "";
	document.getElementById("txtArrayTipoCambio").innerHTML = "";
	//lleno cajas
	document.getElementById("txtArrayCronogramaSeleccionado").value=JSON.stringify(arrayCronogramaSeleccionado);
	document.getElementById("txtArrayTipoCambio").value=JSON.stringify(arrayTipoCambio);
	
}

function llenaSaldoAnterior(){
	$("#divSaldoDesembolsoAnterior").load("cargaSaldoAnterior.jspx",{
		datoProyectoId : $("#datoProyectoId").val(),
		fuenteFinanciadoraId : $("#cbxFuenteFinanciadora").val()
	});
}
</script>

<script type="text/javascript">
var arrayUltimaVerisonPeriodo=new Array();
var arrayCronogramaSeleccionado = new Array();
var arrayTipoCambio = new Array();

	function colocaVersion(){
		for ( var i = 0; i < arrayUltimaVerisonPeriodo.length; i++) {
			if(arrayUltimaVerisonPeriodo[i].periodo==$("#cbxCantPeriodoProy").val()){
				$("#txtVersionDesembolso").attr("value",arrayUltimaVerisonPeriodo[i].version);
		
			}
		}
	}
	
	function agregarDetalleDesembolso(){
		/*var objCronogramaSeleccionado = new Object();
		objCronogramaSeleccionado.cronogramaCostoActividadId = 01;
		objCronogramaSeleccionado.montoPorGastar = 01;
		arrayCronogramaSeleccionadoP.push(objCronogramaSeleccionado);*/

		var error=validaFuenteFinanciadora();
		if (error==0){
		var url = "mostrarDetalleCronogramaCostoActividad.jspx?datoPlanOperativoId="+$("#datoPlanOperativoId").val()+"&fuenteFinanciadoraId="+$("#cbxFuenteFinanciadora").val();
		var stiloPopUp = 'dialogWidth=800px; dialogHeight=500px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
		//ventanaPopUp = showModalDialog(url,'',stiloPopUp);//window.open(url,'',stiloPopUp);

		//window.opener.document.formWindowAsignaTutor.profesorAsignado.value = componenteId;

		//window.showModalDialog(url,datos,stiloPopUp);
		window.showModalDialog(url, arrayCronogramaSeleccionado, stiloPopUp);
		}else{
			$("#cbxFuenteFinanciadora").focus();
		}
	}
	
	function capturaDatosHijo(cronogramaCostoActividadId, montoPorGastar){
		/*var objCronogramaSeleccionado = new Object();
		objCronogramaSeleccionado.cronogramaCostoActividadId = cronogramaCostoActividadId;
		objCronogramaSeleccionado.montoPorGastar = montoPorGastar;
		arrayCronogramaSeleccionado.push(objCronogramaSeleccionado);
		*/
		
		var montoSolicitado=0;	
		for ( var i = 0; i < arrayCronogramaSeleccionado.length; i++) {
			montoSolicitado = parseFloat(montoSolicitado) + parseFloat(arrayCronogramaSeleccionado[i].montoPorGastar);
		}

		$("#grillaDetalleCronogramaSeleccionado").load(
				"cargarGrillaDetalleCronogramaSeleccionado.jspx", {
					cronogramaCostoActividadId : cronogramaCostoActividadId,
					montoPorGastar : montoPorGastar
		}, function(){
			$("#txtMontoSolicitado").attr("value",montoSolicitado);	
			if(arrayCronogramaSeleccionado.length > 0){
				$("#cbxFuenteFinanciadora").attr("disabled","disabled");
			}
		});
	}
	
	
	function eliminarSeleccionCronograma(cronogramaCostoActividadId){
		//var confirma =
		var indiceElimina;
		//var mensaje;
		//alert(cronogramaCostoActividadId);
		for ( var i in arrayCronogramaSeleccionado) {
			if (arrayCronogramaSeleccionado[i].cronogramaCostoActividadId == cronogramaCostoActividadId){
				indiceElimina=i;
				break;
			}
			/*mensaje = mensaje +" CCAId -->" + arrayCronogramaSeleccionado[i].cronogramaCostoActividadId + " MPG --> "+arrayCronogramaSeleccionado[i].montoPorGastar+" Indice --> "+i+"\n";*/
		}
		//alert(indiceElimina);
		arrayCronogramaSeleccionado.splice(indiceElimina,1); //borra un elemento apartir del indice que se le da
		
		$("#grillaDetalleCronogramaSeleccionado").load(
				"deleteRegistroGrillaDetalleCronogramaSeleccionado.jspx", {
					cronogramaCostoActividadId : cronogramaCostoActividadId
		}, function(){
			var montoSolicitado=0;	
			for ( var i = 0; i < arrayCronogramaSeleccionado.length; i++) {
				montoSolicitado = parseFloat(montoSolicitado) + parseFloat(arrayCronogramaSeleccionado[i].montoPorGastar);
			}
			$("#txtMontoSolicitado").attr("value",montoSolicitado);
			
			if(arrayCronogramaSeleccionado.length == 0){
				$("#cbxFuenteFinanciadora").removeAttr("disabled");
			}
		});
	}
</script>

<script type="text/javascript">

function cargaTipoCambio(){
	//cargarGrillaTipoCambio	
	var url = "createTipoCambioDesembolso.jspx";
	var stiloPopUp = 'dialogWidth=610px; dialogHeight=185px; dialogTop=70px; dialogLeft=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';

	window.showModalDialog(url, arrayTipoCambio, stiloPopUp);
}

function capturaDatosHijoTipoCambio(tipoCambio,de,a){
	$("#grillaTipoCambio").load(
			"cargarGrillaTipoCambio.jspx", {
				tipoCambio : tipoCambio,
				de : de,
				a : a
	});
}

function eliminarSeleccionTipoCambio(de,a){
	var indiceEliminaTC;
	//var mensaje;
	for ( var i in arrayTipoCambio) {
		if ((arrayTipoCambio[i].de == de) &&(arrayTipoCambio[i].a == a)){
			indiceEliminaTC=i;
			break;
		}
		/*mensaje = mensaje +" CCAId -->" + arrayCronogramaSeleccionado[i].cronogramaCostoActividadId + " MPG --> "+arrayCronogramaSeleccionado[i].montoPorGastar+" Indice --> "+i+"\n";*/
	}
	//alert(indiceElimina);
	arrayTipoCambio.splice(indiceEliminaTC,1); //borra un elemento apartir del indice que se le da
	
	$("#grillaTipoCambio").load(
			"deleteRegistroGrillaTipoCambio.jspx", {
				de : de,
				a : a
	});

}
</script>

<script type="text/javascript">
function validaFuenteFinanciadora(){
	var errores = 0;
	var mensaje = "";

	if ($("#cbxFuenteFinanciadora").val() == 0) {
		mensaje += "Debe seleccionar una fuente financiadora antes de seleccionar montos. \n";
		errores = errores + 1;
	}
	
	if (errores > 0) {
		alert(mensaje);
	}

	return errores;
	
}


function validarSolicitudDesembolso(){
	var errores = 0;
	var mensaje = "";

	if(arrayCronogramaSeleccionado.length == 0){
		mensaje += "Debe seleccionar al menos un costo para el desembolso. \n";
		errores = errores + 1;
		
	}
	
	if(arrayTipoCambio.length == 0){
		mensaje += "Debe ingresar al menos un tipo de cambio. \n";
		errores = errores + 1;
		
	}
	
	if($("#cbxTipoDesembolso").val() == 0){
		mensaje += "Seleccionar tipo de desembolso. \n";
		errores = errores + 1;
		
	}

	if($("#cbxCuentaCorriente").val() == 0){
		mensaje += "Seleccionar Cuenta Corriente. \n";
		errores = errores + 1;
		
	}

	if($("#cbxCantPeriodoProy").val() == 0){
		mensaje += "Seleccionar periodo. \n";
		errores = errores + 1;
		
	}
	
	if (errores > 0) {
		alert(mensaje);
	}

	return errores;
}


</script>

</head>
<body>
<input type="hidden" id="datoProyectoId"
			name="datoProyectoId"
			value="${datoProyectoId }" /> 
<input type="hidden" id="datoPlanOperativoId"
			name="datoPlanOperativoId"
			value="${datoPlanOperativo.datoPlanOperativoID }" /> 			
	<form id="solDesembolsoForm" class="form-clasico"
		accept-charset="UTF-8">
		<div class="encabezado">SOLICITAR DESEMBOLSO</div>
		
		<br />
		<fieldset>
			<legend>Solicitud de Desembolso</legend>
			<c:choose>
				<c:when test="${cantLiquidacionesSinAprobar == 0 }">
				<div id="cuerpoSolicitud">
			<table width="100%" border="0">
				<tr>
					<td style="text-align: right; width: 25%;"><label>Fuente
							Financiadora:</label></td>
					<td colspan="3" style="text-align: left; width: 75%;">
							<select name="cbxFuenteFinanciadora" id="cbxFuenteFinanciadora" onchange="llenaSaldoAnterior()"
								style="width: 98%;">
								<option value="0">--Fuente Financiadora--</option>
								<c:forEach items="${listFuenteFinanciadoras}"
									var="fuenteFinanciadoras">
									<option value="${fuenteFinanciadoras.fuenteFinanciadoraID }">
										<c:out
											value="${fuenteFinanciadoras.institucion.nombreInstitucion }" />
									</option>
								</c:forEach>
							</select>
						</td>
				</tr>
				<tr>
					<td style="text-align: right; width: 25%;"><label>Estado
							de&nbsp;&nbsp; Desembolso:</label>
					</td>
					<td style="text-align: left; width: 25%;"><select
						name="cbxEstadoDesembolso" id="cbxEstadoDesembolso"
						disabled="disabled" style="width: 180px;">
							<option value="0">--Estado--</option>
							<c:forEach items="${listEstadoDesembolso}" var="estadoDesembolso">
								<c:choose>
									<c:when
										test="${estadoDesembolso.detalleEstadoCabeceraID =='22' }">
										<option selected="selected"
											value="${estadoDesembolso.detalleEstadoCabeceraID }">
											<c:out value="${estadoDesembolso.descripEstado }" />
										</option>
									</c:when>
									<c:otherwise>
										<option value="${estadoDesembolso.detalleEstadoCabeceraID }">
											<c:out value="${estadoDesembolso.descripEstado }" />
										</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></td>
					<td colspan="2" rowspan="5" style="width: 50%; text-align: right;">
							<table style="width: 100%" class="table-clasico">
								<caption>
								<div style="width: 50%; float: left;">
						<label>Tipos de Cambio</label>
					</div>
					<div style="width: 50%; float: right; text-align: right;">
								<a href="javascript:cargaTipoCambio()" style="font-size: 13px;"
											id="apresiacionResultados" class="linkSelecciona" >Nuevo Tipo Cambio</a>
									
					</div></caption>
					<thead>
					<tr>
						<td style="width: 30%; text-align: center; "><label>Tipo Cambio</label></td>
						<td style="width: 25%; text-align: center; "><label>Fecha</label></td>
						<td style="width: 20%; text-align: center; "><label>De</label></td>
						<td style="width: 20%; text-align: center; "><label>A</label></td>
						<td style="width: 5%; text-align: center; "><label>Eliminar</label></td>
					</tr>
					</thead>
					<tbody id="grillaTipoCambio">
					
					</tbody>
							</table>
						</td>
				</tr>
				<tr>
					<td style="text-align: right; width: 25%;"><label>Tipo&nbsp;&nbsp;
							Desembolso:</label></td>
					<td style="text-align: left; width: 25%;"><select
						name="cbxTipoDesembolso" id="cbxTipoDesembolso"
						style="width: 180px;">
							<option value="0">--Tipo Desembolso--</option>
							<c:forEach items="${listTipoDesembolso}" var="tipoDesembolso">
								<option value="${tipoDesembolso.tablaEspecificaID }">
									<c:out value="${tipoDesembolso.descripcionCabecera }" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td style="text-align: right; width: 25%;"><label>Saldo
							Desem. Anterior:</label></td>
					<td style="text-align: left; width: 25%;">
					<div id="divSaldoDesembolsoAnterior" style="width: 90px; float: left; margin-right: 8px;"><input
						name="txtSaldoDesembolsoAnterior" id="txtSaldoDesembolsoAnterior"
						type="text" disabled="disabled" value="0.0" style="width: 90px;" /></div> <select
						name="cbxTipoMonedaSA" id="cbxTipoMonedaSA" style="width: 80px;"
						disabled="disabled">
							<option value="0">--Moneda--</option>
							<c:forEach items="${listTipoMoneda}" var="TipoMoneda">
								<c:choose>
									<c:when
										test="${TipoMoneda.tablaEspecificaID == tipoMonedaPlanOpe }">
										<option selected="selected"
											value="${TipoMoneda.tablaEspecificaID  }">
											<c:out value="${TipoMoneda.descripcionCabecera  }" />
										</option>
									</c:when>
									<c:otherwise>
										<option value="${TipoMoneda.tablaEspecificaID  }">
											<c:out value="${TipoMoneda.descripcionCabecera }" />
										</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td style="text-align: right; width: 25%;"><label>Monto
							Solicitado:</label></td>
					<td style="text-align: left; width: 25%;"><input type="text"
						name="txtMontoSolicitado" id="txtMontoSolicitado"
						disabled="disabled" style="width: 90px;" value="0" /> <select
						name="cbxTipoMonedaMS" id="cbxTipoMonedaMS" style="width: 80px;"
						disabled="disabled">
							<option value="0">--Moneda--</option>
							<c:forEach items="${listTipoMoneda}" var="TipoMoneda">
								<c:choose>
									<c:when
										test="${TipoMoneda.tablaEspecificaID == tipoMonedaPlanOpe }">
										<option selected="selected"
											value="${TipoMoneda.tablaEspecificaID  }">
											<c:out value="${TipoMoneda.descripcionCabecera  }" />
										</option>
									</c:when>
									<c:otherwise>
										<option value="${TipoMoneda.tablaEspecificaID  }">
											<c:out value="${TipoMoneda.descripcionCabecera }" />
										</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td style="text-align: right; width: 25%;"><label>Cuenta
							Corriente:</label></td>
					<td style="text-align: left; width: 25%;"><select
						name="cbxCuentaCorriente" id="cbxCuentaCorriente"
						style="width: 180px;">
							<option value="0">--Cuentas--</option>
							<c:forEach items="${listCuentaCorriente}" var="cuentaCorriente">
								<option value="${cuentaCorriente.cuentaCorrienteID }">
									<c:out value="${cuentaCorriente.numeroCuenta }" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td style="text-align: right; width: 25%;"><label>Periodo:</label>
					</td>
					<td style="text-align: left; width: 25%;"><select
						name="cbxCantPeriodoProy" id="cbxCantPeriodoProy"
						style="width: 180px;" onchange="colocaVersion()">
							<option value="0">--Periodo--</option>
							<c:forEach begin="1" end="${cantidadPeriodosProyecto }"
								varStatus="num">
								<option value="${num.count }">
									<c:out value="${num.count }" />
								</option>
								<c:set var="version" value="0"></c:set>
								<c:forEach items="${listDesembolso }" var="desembolso">
									<c:if test="${desembolso.periodo==num.count }">
										<c:if test="${desembolso.versionDePeriodo > version }">
											<c:set var="version" value="${desembolso.versionDePeriodo}"></c:set>
										</c:if>
									</c:if>
								</c:forEach>
								<script type="text/javascript">
												var objUltimaVerisonPeriodo = new Object();
												objUltimaVerisonPeriodo.periodo = "<c:out value="${num.count }"></c:out>";
												objUltimaVerisonPeriodo.version = "<c:out value="${version + 1}"></c:out>";
												
												arrayUltimaVerisonPeriodo
														.push(objUltimaVerisonPeriodo);
								</script>
							</c:forEach>
					</select></td>
					<td style="text-align: right; width: 25%;"><label>Version
							de&nbsp;Desembolso:</label></td>
					<td style="text-align: left; width: 25%;"><input
						name="txtVersionDesembolso" id="txtVersionDesembolso" type="text"
						maxlength="3" disabled="disabled" /></td>
				</tr>
				<tr>
				<td style="text-align: right; width: 25%;"><label>Fecha de Solicitud:</label></td>
					<td style="text-align: left; width: 25%;">
					<input type="text"
							name="fechaSolicitud" maxlength="0" style="width: 90px;"
							id="fechaSolicitud" disabled="disabled"
							onkeypress="javascript:return Valida_Dato(event,7);" /><label>(dd/mm/aaaa)</label>
							</td>
				</tr>
				<tr>
					<td colspan="4" style="width: 100%;">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: right; width: 75%;">&nbsp;
					</td>
					<td style="text-align: right; width: 25%;"><input
						type="button" id="btnGrabarSolicitudDesembolso"
						value="Grabar Solicitud" onclick="registrarSolicitudDesembolso();" />
					</td>
				</tr>
			</table>
			<br />
			<table width="100%" class="table-clasico">
				<caption>
					<div style="width: 50%; float: left;">
						<label>Detalle de Desembolso</label>
					</div>
					<div style="width: 50%; float: right; text-align: right;">
								<a href="javascript:agregarDetalleDesembolso()" style="font-size: 13px;"
											id="apresiacionResultados" class="linkSelecciona" >Agregar Detalle de Desembolso</a>	
					</div>
				</caption>
				<thead>
					<tr>
						<td style="text-align: center; width: 20%;"><label>Resultado</label>
						</td>
						<td style="text-align: center; width: 20%;"><label>Actividad</label>
						</td>
						<td style="text-align: center; width: 15%;"><label>Cantidad Total - Actividad<br/>Rubro - Partida</label></td>
						<td style="text-align: center; width: 10%;"><label>Periodo
						</label></td>
						<td style="text-align: center; width: 15%;"><label>Cantidad<br />Periodo</label>
						</td>
						<td style="text-align: center; width: 15%;"><label>Monto</label>
						</td>
						<td style="text-align: center; width: 5%;"><label>Eliminar</label>
						</td>
					</tr>
				</thead>
				<tbody id="grillaDetalleCronogramaSeleccionado">
					
				</tbody>
			</table>
</div>
</c:when>
			<c:otherwise>
				<label style="color: red; font-size: 14px;"> Debe tener todas las liquidaciones aprobadas para poder solicitar un nuevo desembolso.</label>
			</c:otherwise>
			</c:choose>
		</fieldset>

		<br />

		<fieldset>
			<legend>Listado de Solicitudes de Desembolso Realizadas</legend>
			<br />
			<div id="grillaListaDesembolso"></div>					
			<br />
		</fieldset>

		<input type="hidden" id="txtArrayCronogramaSeleccionado"
			name="txtArrayCronogramaSeleccionado" /> 
		<input type="hidden" id="txtArrayTipoCambio"
			name="txtArrayTipoCambio" />
	</form>
</body>
</html>


