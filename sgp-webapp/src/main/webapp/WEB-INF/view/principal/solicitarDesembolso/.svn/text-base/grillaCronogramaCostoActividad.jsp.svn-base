<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRONOGRAMA COSTO ACTIVIDAD</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/estiloContabilidad.css"></c:url>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/estiloGeneral.css"></c:url>" />
<script type="text/javascript"
	src="<c:url value="/js/jquery-1.7.1.js"></c:url>"></script>

<script type="text/javascript">
	var arrayCronogramaSeleccionado = new Array();
	//capturo array enviado de padre
	//window.dialogArguments = arrayCronogramaSeleccionadoP;
	arrayCronogramaSeleccionado = window.dialogArguments;
	
	function fCargarActividad() {
		var idResultado = $("#resultado").val();
		var nomMetodo = "cargarActividad";
		$("#actividad").load("cargarCombo.jspx", {
			resultadoID : idResultado,
			metodo : nomMetodo
		});
		$("#costoActividad").load("cargarCombo.jspx", {
			actividadID : 0,
			metodo : "cargarCostoActividad"
		});
		$("#grillaCronogramaCostoActividad").load(
				"cargarGrillaCronogramaCostoActividad.jspx", {
					costoActividadId : 0,
					fuenteFinanciadoraId : 0
				});
	}

	function fCargarCostoActividad() {
		var idActividad = $("#actividad").val();
		var nomMetodo = "cargarCostoActividad";
		$("#costoActividad").load("cargarCombo.jspx", {
			actividadID : idActividad,
			metodo : nomMetodo
		});
		$("#grillaCronogramaCostoActividad").load(
				"cargarGrillaCronogramaCostoActividad.jspx", {
					costoActividadId : 0,
					fuenteFinanciadoraId : 0
				});
	}

	function fCargarCronogramaCostoActividad() {
		$("#grillaCronogramaCostoActividad").load(
				"cargarGrillaCronogramaCostoActividad.jspx", {
					costoActividadId : $("#costoActividad").val(),
					fuenteFinanciadoraId : $("#fuenteFinanciadoraId").val()
				});
	}

	function seleccionarCronograma(cronogramaCostoActividadId, montoPorGastar) {
		var valida = validarCronogramaSeleccionado(cronogramaCostoActividadId);
		if (valida == 0) {
			var objCronogramaSeleccionado = new Object();
			objCronogramaSeleccionado.cronogramaCostoActividadId = cronogramaCostoActividadId;
			objCronogramaSeleccionado.montoPorGastar = montoPorGastar;

			arrayCronogramaSeleccionado.push(objCronogramaSeleccionado);
			window.opener.capturaDatosHijo(cronogramaCostoActividadId, montoPorGastar);
		}
		
		/*var mensaje="";
		for ( var i = 0; i < arrayCronogramaSeleccionado.length; i++) {
			mensaje = mensaje +" CCAId -->" + arrayCronogramaSeleccionado[i].cronogramaCostoActividadId + " MPG --> "+arrayCronogramaSeleccionado[i].montoPorGastar+" Indice --> "+i+"\n";
		}
		alert(mensaje);*/
	}
</script>

<script type="text/javascript">
	function validarCronogramaSeleccionado(cronogramaCostoActividadId) {
		var errores = 0;
		var mensaje = null;

		for ( var p = 0; p < arrayCronogramaSeleccionado.length; p++) {
			if (arrayCronogramaSeleccionado[p].cronogramaCostoActividadId == cronogramaCostoActividadId) {
				var mensajeComp = "El cronograma seleccionado ya se encuentra en la lista. \n";
				if (mensaje == null) {
					mensaje = mensajeComp;
				} else {
					mensaje = mensaje + mensajeComp;
				}
				errores = errores + 1;
				break;
			}
		}
		if (errores > 0) {
			alert(mensaje);
		}

		return errores;
	}
	
	function cerrarPantalla(){
		window.close();
	}
</script>
</head>
<body class="form-clasico">
	<br />
	<fieldset>
		<legend>PLAN OPERATIVO (Costo Actividad / Cronograma Costo
			Actividad)</legend>
		<input type="hidden" id="fuenteFinanciadoraId"
			name="fuenteFinanciadoraId" value="${fuenteFinanciadoraId }">
		<table width="100%" border="0">

			<tr>
				<td style="text-align: right; width: 20%;"><label>Resultado:</label>
				</td>
				<td style="text-align: left; width: 80%;"><select
					name="resultado" id="resultado" style="width: 95%;"
					onchange="javascript:fCargarActividad();">
						<option value="0">--Resultado--</option>
						<c:forEach items="${listResultado}" var="resultado">
							<option value="${resultado.resultadoID }">
								<c:out value="${resultado.definicionResultado }" />
							</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td style="text-align: right; width: 20%;"><label>Actividad:</label>
				</td>
				<td style="text-align: left; width: 80%;"><select
					name="actividad" id="actividad" style="width: 95%;"
					onchange="javascript:fCargarCostoActividad()">
						<option value="0">--Actividad--</option>
				</select></td>
			</tr>
			<tr>
				<td style="text-align: right; width: 20%;"><label>Costo
						Actividad:</label>
				</td>
				<td style="text-align: left; width: 80%;"><select
					name="costoActividad" id="costoActividad" style="width: 95%;"
					onchange="javascript:fCargarCronogramaCostoActividad()">
						<option value="0">-- Costo Actividad ** Rubro ** Partida Especifica--</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="2" style="width: 100%; text-align: right;">
					<input type="button" value="Cerrar"
								onClick="cerrarPantalla()" />
				</td>
			</tr>
			<tr>
				<td colspan="4" style="width: 100%;"><br />
					<table width="100%" class="table-clasico">
						<caption>
							<label>Cronograma Costo Actividad</label>
						</caption>
						<thead>
							<tr>
								<td style="text-align: center; width: 5%;"><label>Periodo</label>
								</td>
								<td style="text-align: center; width: 35%;"><label>Cantidad - Unidad Medida<br/>Rubro - Partida</label>
								</td>
								<td style="text-align: center; width: 25%;"><label>Fuente
										<br />Financiadora</label>
								</td>
								<td style="text-align: center; width: 15%;"><label>Monto
										<br />Total</label>
								</td>
								<td style="text-align: center; width: 15%;"><label>Monto
										<br />Por Gastar</label>
								</td>
								<td style="text-align: center; width: 5%;"><label>Seleccionar</label>
								</td>
							</tr>
						</thead>
						<tbody id="grillaCronogramaCostoActividad">

						</tbody>
					</table></td>
			</tr>
		</table>
	</fieldset>
</body>
</html>