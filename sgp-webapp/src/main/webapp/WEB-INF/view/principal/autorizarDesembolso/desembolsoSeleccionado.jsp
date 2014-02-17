<%@ include file="/common/includesTaglibsGenerico.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Desembolso</title>

<script type="text/javascript">
	var f = new Date();
	var dia = f.getDate();
	if (dia < 10) {
		dia = "0" + dia;
	}
	var mes = f.getMonth() + 1;
	if (mes < 10) {
		mes = "0" + mes;
	}
	var fechaActual = dia + "/" + (mes) + "/" + f.getFullYear();
	$(document).ready(function() {
		$("#fechaAutorizado").attr("value", fechaActual);
		
		var estado = "<c:out value="${desembolso.fkIdtablaespEstDesembolso}" ></c:out>";
		if (estado!=22){
			$(".hide").hide();
		}
		var prefijoPerfil = "<c:out value="${userSession.prefijoPerfil}" ></c:out>";
		if(prefijoPerfil != 'DAFI'){
			$(".noEvalua").hide();
			$("#btnGrabarAutorizacionDesembolso").hide();
		}else{
			$(".muestraEstado").hide();
		}
	});
</script>

<script type="text/javascript">
function ocultarDetalleDesembolso(){
	window.close();
}

function RegistrarAutorizarDesembolso(){
	
	//var estado=document.getElementById("cbxEstadoDesembolso").value;// 24:rechazado
	//if(estado != 24){
		var error = validarFormulario();
		if(error==0){
			var confirmacion =confirm("Esta seguro que desea grabar la autorizacion del desembolso?");	
		
			if(confirmacion == true){
			$.get("registrarAutoDesembolso.jspx", {
				fechaAutorizado : $("#fechaAutorizado").val(),
				montoAutorizado : $("#txtMontoAutorizado").val(),
				estadoDesembolso : $("#estadoDesembolso").val(),
				desembolsoId : $("#desembolsoId").val()
			}, function(){
				window.opener.cargaGrillaProyectosPorEvaluar();	
			});
			}
			}
	}		

function agregarObservacion(tablaId,datoProyectoID,tablaClaseId,tablaProfundidadId,claseId){
	var url = "showGestionarObservacion.jspx?tablaId=" + tablaId + "&datoProyectoID="+datoProyectoID+"&tablaClaseId="+tablaClaseId+"&tablaProfundidadId="+tablaProfundidadId+"&claseId="+claseId;
	var stiloPopUp = 'dialogWidth=800px; dialogHeight=500px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
	//ventanaPopUp = showModalDialog(url,'',stiloPopUp);//window.open(url,'',stiloPopUp);

	//window.opener.document.formWindowAsignaTutor.profesorAsignado.value = componenteId;

	//window.showModalDialog(url,datos,stiloPopUp);
	window.showModalDialog(url, "", stiloPopUp);

	}
</script>

<script type="text/javascript">
function validarFormulario(){
	var errores = 0;
	var mensaje = "";

	if ($("#txtMontoAutorizado").val().length == 0) {
		mensaje += "Debe ingresar el monto autorizado. \n";
		errores = errores + 1;
	}
	
	if ($("#txtMontoAutorizado").val().length > 0) {
		var montoSolicitado ="<c:out value="${desembolso.montoSolicitado}"></c:out>";
		if ($("#txtMontoAutorizado").val()>montoSolicitado ){
			mensaje += "El monto autorizado es mayor al monto solicitado. \n";
			errores = errores + 1;
		}
	}

	if (errores > 0) {
		alert(mensaje);
	}

	return errores;
}
</script>
</head>
<body class="form-clasico">
	<div class="encabezado">Desembolso Seleccionado</div>
	<br>
	<fieldset>
		<legend>Detalle de Solicitud de Desembolso</legend>

		<div style="text-align: right;">
			<input type="button" value="Observaciones" class="hide"
				onclick="agregarObservacion('<c:out value="${desembolso.desembolsoID }" ></c:out>','<c:out value="${desembolso.datoProyecto.datoProyectoID }" ></c:out>',5,32,'<c:out value="${desembolso.desembolsoID }" ></c:out>');" />
			<input type="button" value="Cerrar"
				onclick="ocultarDetalleDesembolso();" />
			<input type="hidden" id="desembolsoId" name="desembolsoId" value="${desembolso.desembolsoID }">	
		</div>
		<br />
		<table width="100%" border="0px">
			<tr>
				<td style="text-align: right; width: 25%;"><label>Nombre
						Proyecto:</label></td>
				<td colspan="3" style="text-align: left; width: 75%;"><label>
				<c:out value="${desembolso.datoProyecto.nombreProyecto }"></c:out>
				</label>
				</td>
			</tr>
			<tr>
				<td style="text-align: right; width: 25%;"><label>Ejecutor:</label>
				</td>
				<td colspan="3" style="text-align: left; width: 75%;"><label><c:out value="${desembolso.institucionEjecutora.nombreInstitucion }"></c:out></label></td>
			</tr>
			<tr>
				<td style="text-align: right; width: 25%;"><label>Periodo:</label>
				</td>
				<td style="text-align: left; width: 25%;"><label><c:out value="${desembolso.periodo }"></c:out></label></td>
				<td style="text-align: right; width: 25%;"><label>Version
						Desembolso:</label></td>
				<td style="text-align: left; width: 25%;"><label><c:out value="${desembolso.versionDePeriodo }"></c:out></label></td>
			</tr>
			<tr>
				<td style="text-align: right; width: 25%;"><label>Tipo
						Desembolso:</label></td>
				<td style="text-align: left; width: 25%;"><label><c:out value="${desembolso.descripcionTipoDesembolso }"></c:out></label></td>
				<td style="text-align: right; width: 25%;"><label>Cuenta
						Corriente:</label></td>
				<td style="text-align: left; width: 25%;"><label><c:out value="${desembolso.cuentaCorriente.numeroCuenta }"></c:out></label></td>
			</tr>
			<tr>
				<td style="text-align: right; width: 25%;"><label>Monto
						Solicitado:</label></td>
				<td style="text-align: left; width: 25%;"><label><c:out value="${desembolso.montoSolicitado }"></c:out>  <c:out value="${desembolso.descripcionTipoMoneda }"></c:out></label></td>
			<td style="text-align: right; width: 25%;"><label>Monto
						Autorizado:</label></td>
				<td style="text-align: left; width: 25%;">
					<div style="float: left;">
						<input style="width: 80px" name="txtMontoAutorizado"
							id="txtMontoAutorizado" type="text" maxlength="8"
							onkeypress="javascript:return Valida_Dato(event,8);"
							value="<c:out value="${desembolso.montoAutorizado }"></c:out>"
							<c:if test="${desembolso.fkIdtablaespEstDesembolso != 22}">disabled="disabled" </c:if>/>
						<select
							name="tipoMoneda" id="tipoMoneda" style="width: 120px" disabled="disabled">
							<option value="0">-- Moneda --</option>
							<c:forEach items="${listTipoMoneda }" var="tipoMoneda">
								<option value="${tipoMoneda.tablaEspecificaID }" <c:if test="${desembolso.fkIdtablaespTipoMoneda == tipoMoneda.tablaEspecificaID}">selected="selected" </c:if> >${tipoMoneda.descripcionCabecera }</option>
							</c:forEach>
						</select>

					</div></td>
			</tr>
			<tr>
				<td style="text-align: right; width: 25%;"><label>Fuente
						Financiadora:</label></td>
				<td style="text-align: left; width: 25%;"><label><c:out value="${desembolso.fuenteFinanciadora.institucion.nombreInstitucion }"></c:out></label></td>
				<td style="text-align: right; width: 25%;"><label>Fecha
						de Autorizacion:</label></td>
				<td style="text-align: left; width: 25%;"><input type="text"
					name="fechaAutorizado" maxlength="0" style="width: 90px;"
					id="fechaAutorizado" disabled="disabled"
					onkeypress="javascript:return Valida_Dato(event,7);" /><label>(dd/mm/aaaa)</label>
					<!-- <input type="hidden" id="fechaAutorizadoHide" name="fechaAutorizadoHide"> -->
				</td>
			</tr>
			<tr>
				<td style="text-align: right; width: 25%;"><label>Fecha
						de Solicitud:</label></td>
				<td style="text-align: left; width: 25%;"><label><c:out value="${desembolso.fechaSolicitudString }"></c:out> (dd/mm/aaaa)</label>
					<!-- <input type="hidden" id="fechaAutorizadoHide" name="fechaAutorizadoHide"> -->
				</td>
				<td style="text-align: right; width: 25%; vertical-align: top;" ><label>Estado
						Desembolso:</label></td>
				<td style="text-align: left; width: 25%;">
					<c:choose>
							<c:when test="${desembolso.cantObservacionesRelevantes > 0 }">
							<input type="hidden" name="estadoDesembolso" id="estadoDesembolso" value="${desembolso.fkIdtablaespEstDesembolso}">
							<label><c:out value="${desembolso.descripcionEstDesembolso }"></c:out></label><br/>
							<label style="color: red;">Desembolso Tiene observaciones relevantes sin atender.</label>
							</c:when>
							<c:otherwise>
		    <select name="estadoDesembolso" id="estadoDesembolso"  class="noEvalua" <c:if test="${desembolso.fkIdtablaespEstDesembolso != 22}">disabled="disabled" </c:if>>
							<option value="0">-- Estado --</option>
							<c:forEach items="${listEstadoDesembolso }" var="estadoDesembolso">
								<option value="${estadoDesembolso.detalleEstadoCabeceraID }" <c:if test="${estadoDesembolso.detalleEstadoCabeceraID == desembolso.fkIdtablaespEstDesembolso }">selected="selected"</c:if>>${estadoDesembolso.descripEstado }</option>
							</c:forEach>
					</select>
							 </c:otherwise>
                </c:choose>
					<label class="muestraEstado"><c:out value="${desembolso.descripcionEstDesembolso }"></c:out></label>
				</td>				
			</tr>
				<tr>
				<td style="text-align: right; width: 25%;"><label>Saldo
						Desembolso Anterior:</label></td>
				<td colspan="3" style="text-align: left; width: 75%;"><label><c:out value="${desembolso.saldoDesembolsoAnterior }"></c:out>  <c:out value="${desembolso.descripcionTipoMoneda }"></c:out></label></td>
				
			</tr>
			<tr>
				<td colspan="4" style="text-align: right; width: 100%;"><input
					type="button" id="btnGrabarAutorizacionDesembolso"
					value="Grabar Autorizacion" class="hide"
					onclick="RegistrarAutorizarDesembolso();" /></td>
			</tr>
		</table>
		<br />
		<table width="100%" class="table-clasico">
					<caption>
						<label>Detalle de Desembolso</label>
					</caption>
					<thead>
						<tr>
							<td style="text-align: center; width: 25px;"><label>Resultado</label>
							</td>
							<td style="text-align: center; width: 20px;"><label>Actividad</label>
							</td>
							<td style="text-align: center; width: 15px;"><label>Cantidad
									Total Actividad<br />Rubro - Partida</label></td>
							<td style="text-align: center; width: 10px;"><label>Periodo</label>
							</td>
							<td style="text-align: center; width: 15px;"><label>Cantidad<br>Periodo</label>
							</td>
							<td style="text-align: center; width: 15px;"><label>Monto</label>
							</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${desembolso.listDetalleDesembolso }"
							var="detalleDesembolso" varStatus="indiceInt">
							<c:choose>
								<c:when test="${indiceInt.count %2== 0}">
									<c:set var="classIdiInt" value="f2int"></c:set>
								</c:when>
								<c:otherwise>
									<c:set var="classIdiInt" value="f1int"></c:set>
								</c:otherwise>
							</c:choose>
							<tr class="<c:out value="${classIdiInt}"></c:out>">
								<td style="text-align: center; width: 25px;"><label><c:out
											value="${detalleDesembolso.resultado.definicionResultado }"></c:out>
								</label></td>
								<td style="text-align: center; width: 20px;"><label><c:out
											value="${detalleDesembolso.actividad.nombreActividad }"></c:out>
								</label></td>
								<td style="text-align: center; width: 15px;"><label><c:out
											value="${detalleDesembolso.costoActividad.cantidadTotal }"></c:out>
										<c:out
											value="${detalleDesembolso.costoActividad.descripcionUnidadMedida }"></c:out><br />
										<c:out
											value="${detalleDesembolso.costoActividad.partidaGenerica.descripcionPartidaGenerica }"></c:out> - <c:out
											value="${detalleDesembolso.costoActividad.partidaEspecifica.descripcionPartidaEspecifica }"></c:out>
								</label></td>
								<td style="text-align: center; width: 10px;"><label><c:out
											value="${detalleDesembolso.cronogramaCostoActividadID.periodo }"></c:out>
								</label></td>
								<td style="text-align: center; width: 15px;"><label><c:out
											value="${detalleDesembolso.cronogramaCostoActividadID.cantidad }"></c:out> - <c:out
											value="${detalleDesembolso.costoActividad.descripcionUnidadMedida }"></c:out>
								</label></td>
								<td style="text-align: center; width: 15px;"><label><c:out
											value="${detalleDesembolso.montoSolicitado }"></c:out>
										<c:out value="${detalleDesembolso.descripcionTipoMonedaMs }"></c:out>
								</label></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>	

	</fieldset>
</body>
</html>