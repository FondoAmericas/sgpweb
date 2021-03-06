<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Apreciación de Resultados</title>
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


<script type="text/javascript">
	$(window).load(function() {
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
		
	}
</script>
<script type="text/javascript">
var arrayApresiacionResultado = new Array();

	$(document).ready(
			function() {
				$("#grillaApresiacionResultados").load(
						"grillaApresiacionResultados.jspx", {
							reporteAvanceId : $("#reporteAvanceId").val()
						},function(){
							ocultaCampos();
						});
			});
</script>

<script type="text/javascript">
	function limpiaCajas(){
		$("#apreciacionResultadoID").attr("value","");
		$("#sltTipoApresiacion").attr("value","0");
		$("#sltTipoApresiacion").removeAttr("disabled");
		$("#comentario").attr("value","");
	}

</script>

<script type="text/javascript">
	function grabarAgregarApresiacionResultado() {
		var error = validaCajas();

		if (error == 0) {
			$.get("grabarApresiacionResultado.jspx", {
				apreciacionResultadoID : $("#apreciacionResultadoID").val(),
				sltTipoApresiacion : $("#sltTipoApresiacion").val(),
				comentario : $("#comentario").val(),
				reporteAvanceId : $("#reporteAvanceId").val()
			}, function() {
				$("#grillaApresiacionResultados").load(
						"grillaApresiacionResultados.jspx", {
							reporteAvanceId : $("#reporteAvanceId").val()
						});
				limpiaCajas();
			});
		}
	}

	function cerrarPantalla() {
		window.close();
	}
	
	function modificarApresiacionResultado(apreciacionResultadoID,fkIdtablaespApreciacionResultadoRaId,comentario){
		var confirma = confirm("Esta seguro que desea cargar el registro para modificarlo?");
		
		if(confirma==true){
		$("#apreciacionResultadoID").attr("value",apreciacionResultadoID);
		$("#sltTipoApresiacion").attr("value",fkIdtablaespApreciacionResultadoRaId);
		$("#sltTipoApresiacion").attr("disabled","disabled");
		$("#comentario").attr("value",comentario);
		//var m = "";
		for ( var p = 0; p < arrayApresiacionResultado.length; p++) {
			//m +="p: "+arrayApresiacionResultado[p].fkIdtablaespApreciacionResultadoRa; 
			if (arrayApresiacionResultado[p].fkIdtablaespApreciacionResultadoRa == fkIdtablaespApreciacionResultadoRaId) {
				arrayApresiacionResultado.splice(p);
				break;
			}
		}
		}
	}
	
	function eliminarApresiacionResultado(apreciacionResultadoID){
		var confirma= confirm("Esta seguro que desea eliminar el registro?");
		if(confirma==true){
			$.get("eliminarApresiacionResultado.jspx", {
				apreciacionResultadoID :apreciacionResultadoID
			}, function() {
				$("#grillaApresiacionResultados").load(
						"grillaApresiacionResultados.jspx", {
							reporteAvanceId : $("#reporteAvanceId").val()
						});
			});
		}
	}
</script>

<script type="text/javascript">
	function validaCajas() {
		var errores = 0;
		var mensaje = null;

		if ($("#sltTipoApresiacion").val() == 0) {
			var mensajeComp = "Debe seleccionar un tipo de apresiación. \n";
			if (mensaje == null) {
				mensaje = mensajeComp;
			} else {
				mensaje = mensaje + mensajeComp;
			}
			errores = errores + 1;
		}
		
		if ($("#comentario").val().length == 0) {
			var mensajeComp = "Debe ingresar un comentario. \n";
			if (mensaje == null) {
				mensaje = mensajeComp;
			} else {
				mensaje = mensaje + mensajeComp;
			}
			errores = errores + 1;
		}

		for ( var p = 0; p < arrayApresiacionResultado.length; p++) {
			if (arrayApresiacionResultado[p].fkIdtablaespApreciacionResultadoRa == $('#sltTipoApresiacion').attr("value")) {
				var mensajeComp = "El tipo de apresiación que desea ingresa ya se encuentra en la lista. \n";
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
</script>
</head>
<body>
	<div class="form-clasico">
		<div class="encabezado">Apreciación de Resultados del Reporte <c:out value="${reporteAvance.periodo }"></c:out></div>
		<br>
		<fieldset style="padding-left: 10px">
		<legend>Información de Apreciación</legend>
<div class="hide">
<input type="hidden" id="reporteAvanceId" name="reporteAvanceId"
				value="${reporteAvance.reporteAvanceID }"> <input type="hidden"
				id="apreciacionResultadoID" name="apreciacionResultadoID"
				value="${apreciacionResultadoID }">
			<table width="100%">
				<tr>
					<td style="width: 25%; text-align: right; vertical-align: top;"><label>Tipo
							de Apreciación:</label></td>
					<td style="width: 25%; text-align: left;"><select
						name="sltTipoApresiacion" id="sltTipoApresiacion">
							<option value="0">
								<c:out value="-- Apreciación --" />
							</option>
							<c:forEach items="${listTipoApresiacionResultado}"
								var="apresiacionResultado">
								<c:choose>
									<c:when
										test="${apresiacionResultado.tablaEspecificaID =='35' }">
										<option selected="selected"
											value="${apresiacionResultado.tablaEspecificaID}">
											<c:out value="${apresiacionResultado.descripcionCabecera }" />
										</option>
									</c:when>
									<c:otherwise>
										<option
											value="${apresiacionResultado.tablaEspecificaID}">
											<c:out value="${apresiacionResultado.descripcionCabecera }" />
										</option>
									</c:otherwise>
								</c:choose>

							</c:forEach>
					</select></td>
				<td colspan="2" style="width: 50%; text-align: left;">
					<input
						type="button" id="btnAgregarApresiacionResultado"
						name="btnAgregarApresiacionResultado"
						onclick="grabarAgregarApresiacionResultado()" value="Agregar Apreciacion">
						<input type="button" id="btnCerrarPantalla"
						name="btnCerrarPantalla" onclick="cerrarPantalla()" value="Cerrar">
				</td>
				</tr>
				<tr>
					<td style="width: 25%; text-align: right; vertical-align: top;"><label>Comentario:</label>
					</td>
					<td colspan="3" style="width: 75%; text-align: left;"><textarea
							id="comentario" name="comentario" rows="4" cols="5"
							style="width: 98%;"></textarea></td>
				</tr>
			</table>
			<br>
			</div>
			<div id="grillaApresiacionResultados"></div>
		</fieldset>
	</div>
</body>
</html>

