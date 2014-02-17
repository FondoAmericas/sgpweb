<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Problemas &amp; Soluciones</title>
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
	$(document).ready(
			function() {
				$("#grillaProblemasSoluciones").load(
						"grillaProblemasSoluciones.jspx", {
							reporteAvanceId : $("#reporteAvanceId").val()
						}, function(){
							ocultaCampos();
						});
			});
</script>

<script type="text/javascript">
	function limpiaCajas(){
		//$("#problemaRelevante").removeAttr("checked");
		$("#problema").attr("value","");
		$("#solucion").attr("value","");
		$("#resultado").attr("value","");
		$("#comentario").attr("value","");
		$("#problemaSolucionId").attr("value","");
	}

	function personalTecnicoAdministratico(reporteAvanceId,estado) {

		var url = "cuerpoPersonalTecnicoAdministrativo.jspx?reporteAvanceId="
				+ reporteAvanceId+"&estado="+estado;
		var stiloPopUp = 'dialogWidth=800px; dialogHeight=700px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
		//ventanaPopUp = showModalDialog(url,'',stiloPopUp);//window.open(url,'',stiloPopUp);

		//window.opener.document.formWindowAsignaTutor.profesorAsignado.value = componenteId;

		//window.showModalDialog(url,datos,stiloPopUp);
		window.showModalDialog(url, "", stiloPopUp);

	}
</script>

<script type="text/javascript">
	function grabarProblemaSolucion() {
		var error = validaCajas();

		if (error == 0) {
			var problemaRelevante=0;
			/*if ($("#problemaRelevante").is(":checked")) {
				problemaRelevante = 1;
			} else {
				problemaRelevante = 0;
			}*/

			$.get("grabarProblemaSolucion.jspx", {
				reporteAvanceId : $("#reporteAvanceId").val(),
				//problemaRelevante : problemaRelevante,
				problemaSolucionID :$("#problemaSolucionId").val(), 
				problema : $("#problema").val(),
				solucion : $("#solucion").val(),
				resultado : $("#resultado").val(),
				comentario : $("#comentario").val()
			}, function() {
				$("#grillaProblemasSoluciones").load(
						"grillaProblemasSoluciones.jspx", {
							reporteAvanceId : $("#reporteAvanceId").val()
						});
				limpiaCajas();
			});
		}
	}

	function cerrarPantalla() {
		window.close();
	}
</script>

<script type="text/javascript">
	function validaCajas() {
		var errores = 0;
		var mensaje = null;

		if ($("#problema").val() == 0) {
			var mensajeComp = "Debe ingresar al menos un problema para grabar. \n";
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

<script type="text/javascript">
	function modificarProblemasSoluciones(problemaSolucionID,problema,solucion,resultado,comentario){
		var confirma = confirm("Esta seguro que desea cargar el registro para modificarlo?");
    	
    	if(confirma==true){
			$("#problemaSolucionId").attr("value",problemaSolucionID);
			$("#problema").attr("value",problema);
			$("#solucion").attr("value",solucion);
			$("#resultado").attr("value",resultado);
			$("#comentario").attr("value",comentario);
	}
	}
	
	function eliminarProblemasSoluciones(problemaSolucionID ){
		var confirma= confirm("Esta seguro que desea eliminar el registro?");
		if(confirma==true){
			$.get("eliminarProblemaSolucion.jspx", {
				problemaSolucionID :problemaSolucionID 
			}, function() {
				$("#grillaProblemasSoluciones").load(
						"grillaProblemasSoluciones.jspx", {
							reporteAvanceId : $("#reporteAvanceId").val()
						});
			});
		}
	}
</script>

</head>
<body>
	<div class="form-clasico">
		<div class="encabezado">Problemas &amp; Soluciones del Reporte <c:out value="${reporteAvance.periodo }"></c:out></div>
		<br>
		<fieldset style="padding-left: 10px">
<legend>Informacion de Problemas y Soluciones</legend>
<div class="hide">
			<input type="hidden" id="reporteAvanceId" name="reporteAvanceId"
				value="${reporteAvance.reporteAvanceID }"> <input type="hidden"
				id="problemaSolucionId" name="problemaSolucionId"
				value="${problemaSolucuinId }">
			<table width="100%">
				<!-- <tr>
					<td colspan="2"
						style="width: 50%; text-align: right; vertical-align: top;">
						<label>Problema Relevante al Proyecto: </label><input
						type="checkbox" id="problemaRelevante" name="problemaRelevante">
					</td>
					<td colspan="2" style="width: 50%;"></td>
				</tr> -->
				<tr>
					<td style="width: 25%; text-align: right; vertical-align: top;"><label>Problema:</label>
					</td>
					<td colspan="3" style="width: 75%; text-align: left;"><textarea
							id="problema" name="problema" rows="4" cols="5"
							style="width: 98%;"></textarea>
					</td>
				</tr>
				<tr>
					<td style="width: 25%; text-align: right; vertical-align: top;"><label>Solución:</label>
					</td>
					<td colspan="3" style="width: 75%; text-align: left;"><textarea
							id="solucion" name="solucion" rows="4" cols="5"
							style="width: 98%;"></textarea>
					</td>
				</tr>
				<tr>
					<td style="width: 25%; text-align: right; vertical-align: top;"><label>Resultado:</label>
					</td>
					<td colspan="3" style="width: 75%; text-align: left;"><textarea
							id="resultado" name="resultado" rows="4" cols="5"
							style="width: 98%;"></textarea>
					</td>
				</tr>
				<tr>
					<td style="width: 25%; text-align: right; vertical-align: top;"><label>Comentario:</label>
					</td>
					<td colspan="3" style="width: 75%; text-align: left;"><textarea
							id="comentario" name="comentario" rows="4" cols="5"
							style="width: 98%;"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" style="width: 100%; text-align: right;"><input
						type="button" id="btnPersonalTecnicoAdministrativo"
						name="btnPersonalTecnicoAdministrativo"
						onclick="personalTecnicoAdministratico('<c:out value="${reporteAvance.reporteAvanceID }" />','<c:out value="${estado}"></c:out>')" value="Personal Tecnico Administrativo">
					<input
						type="button" id="btnAgregarProblemaSolucion"
						name="btnAgregarProblemaSolucion"
						onclick="grabarProblemaSolucion()" value="Agregar Problema">
						<input type="button" id="btnCerrarPantalla"
						name="btnCerrarPantalla" onclick="cerrarPantalla()" value="Cerrar">
					</td>
				</tr>

			</table>
	<br>
	</div>
	<div id="grillaProblemasSoluciones"></div>
	</fieldset>
</div>
</body>
</html>

