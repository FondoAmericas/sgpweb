<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Beneficiarios Logrados por Resultado </title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/viewMain.css" ></c:url>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/estiloContabilidad.css"></c:url>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/estiloGeneral.css"></c:url>" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/gestorAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/AtssefGrid.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/validador.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/util.js"></script>	

<script type="text/javascript">
	$(document).ready(
			function() {
				$("#logrado").focus();
			});

	function grabarCantidadLograda(){
		$.get("grabarCantidadLogradaPropuestaTransferencia.jspx", {
			propuestaTransferenciaId : $("#propuestaTransferenciaId").val(),
			cantidadFinal : $("#logrado").val(),
			beneficiariosPorResultadoID : $("#beneficiariosPorResultadoID").val()
		}, function(){
			cerrarPantalla();
			window.opener.cargaGrillaBeneficiarioPropuestaTransferencia();
			window.opener.cargaComboBienTransferido();
		});	
	}
	
	function cerrarPantalla(){
		window.close();
	}
</script>

</head>
<body onunload="window.opener.cargaGrillaBeneficiario()">
	<div class="form-clasico">
		<div class="encabezado">Beneficiarios Logrados por Resultado</div>
		<br>
		<fieldset style="padding-left: 10px">
			<legend>Informacion de Beneficiarios</legend>

			<input type="hidden" id="propuestaTransferenciaId" name="propuestaTransferenciaId"
				value="${propuestaTransferenciaId }"> <input type="hidden"
				id="beneficiariosPorResultadoID" name="beneficiariosPorResultadoID"
				value="${beneficiariosPorResultadoBean.beneficiariosPorResultadoID }">
			<table width="100%">
			<tr>
					<td style="width: 25%; text-align: right;"><label>Resultado:</label>
					</td>
					<td colspan="3" style="width: 75%; text-align: left;">
						<label><c:out value="${beneficiariosPorResultadoBean.descripcionResultado }"></c:out></label>
					</td>
				</tr>
				<tr>
					<td style="width: 25%; text-align: right;">
						<label>Tipo Beneficiario: </label>
					</td>
					<td style="width: 25%; text-align: left;">
						<label><c:out value="${beneficiariosPorResultadoBean.descripcionTipoBeneficiario }"></c:out></label>
					</td>
					<td style="width: 25%; text-align: right;">
						<label>Cantidad Programada: </label>
					</td>
					<td style="width: 25%; text-align: left;">
						<label><c:out value="${beneficiariosPorResultadoBean.cantidadProgramado } - "></c:out>
						<c:out value="${beneficiariosPorResultadoBean.descripcionEstrato }"></c:out></label>
					</td>
				</tr>
				<tr>
					<td style="width: 25%; text-align: right; vertical-align: top;"><label>Caracteristicas de Poblacion:</label>
					</td>
					<td style="width: 25%; text-align: left;">
						<label><c:out value="${beneficiariosPorResultadoBean.caracteristicasPoblacion }"></c:out></label>
					</td>
					<td style="width: 25%; text-align: right; vertical-align: top;"><label>Descripcion:</label>
					</td>
					<td style="width: 25%; text-align: left;">
					<label><c:out value="${beneficiariosPorResultadoBean.descripcion }"></c:out></label>
					</td>
				</tr>
				<tr>
						<td style="width: 25%; text-align: right;"><label>Departamento:</label>
						</td>
						<td style="width: 25%; text-align: left;">
							<label><c:out value="${beneficiariosPorResultadoBean.departamento }"></c:out></label>
						</td>
						<td style="width: 25%; text-align: right;" ><label>Provincia:</label>
						</td>
						<td style="width: 25%; text-align: left;">
							<label><c:out value="${beneficiariosPorResultadoBean.provincia }"></c:out></label>
						</td>
					</tr>
					<tr>
						<td style="width: 25%; text-align: right;  vertical-align: top;"><label>Distrito:</label>
						</td>
						<td style="width: 25%; text-align: left;  vertical-align: top;">
							<label><c:out value="${beneficiariosPorResultadoBean.distrito }"></c:out></label>
						</td>
						<td  style="width: 25%; text-align: right; vertical-align: top;"><label>Localizacion:</label>
						</td>
						<td  style="width: 25%; text-align: left;">
						<label><c:out value="${beneficiariosPorResultadoBean.detalleUbicacion }"></c:out></label>
						
						</td>
					</tr>
					<tr>
						<td style="width: 25%; text-align: right;  vertical-align: top;"><label>Cantidad Final:</label>
						</td>
						<td style="width: 25%; text-align: left;  vertical-align: top;">
							<input type="text" id="logrado" name="logrado" style="width: 80px; text-align: center;" maxlength="10" onkeypress="javascript:return Valida_Dato(event,8);" >
							<label><c:out value=" - ${beneficiariosPorResultadoBean.descripcionEstrato }"></c:out></label>
						</td>
						<td colspan="2" style="width: 50%;"></td>
					</tr>
				<tr>
					<td colspan="2" style="width: 50%;"></td>
					<td colspan="2" style="width: 50%; text-align: right;"><input
						type="button" id="btnCantidadLograda"
						name="btnCantidadLograda"
						onclick="grabarCantidadLograda()" value="Grabar">
						<input type="button" id="btnCerrarPantalla"
						name="btnCerrarPantalla" onclick="cerrarPantalla()" value="Cerrar">
					</td>
				</tr>

			</table>
	
	<br>
	
	</fieldset>
</div>
</body>
</html>

