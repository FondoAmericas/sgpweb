<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Personal Tecnico Administrativo</title>
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
	$(window).load(function() {
		ocultaCampos();
	});

	function ocultaCampos() {
		estado = "<c:out value="${estado}"></c:out>";
		//alert(estado);
		if ((estado == 'apro') || (estado == 'eval')){
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
				cargaGrillaPersonalTecnicoAdministrativo();
			});
</script>

<script type="text/javascript">
function cargaGrillaPersonalTecnicoAdministrativo(){
	$("#grillaPersonalTecnicoAdministrativo").load(
			"grillaPersonalTecnicoAdministrativo.jspx",{
				reporteAvanceId : $("#reporteAvanceId").val()
			},function(){
				ocultaCampos();
			});
}
</script>

<script type="text/javascript">
	function grabarPersonalTecnico(){
		var error = validaCajas();

		if (error == 0) {
			//alert("Entro");
	
			$.get("grabarPersonalTecnico.jspx", {
				reporteAvanceId : $("#reporteAvanceId").val(),
				personalTecnicoAdministrativoID : $('#personalTecnicoAdministrativoID').val(),
				nombreCompleto : $("#nombreCompleto").val(),
				sltFormacionProfesional : $("#sltFormacionProfesional").val(),
				sltTiempoDedicado : $("#sltTiempoDedicado").val(),
				responsabilidad : $("#responsabilidad").val(),
				porcentajeParticipacion : $("#porcentajeParticipacion").val(),
				sltEtapaPersonal : $("#sltEtapaPersonal").val(),
				sltPersonalTecnicoReemplazado : $("#sltPersonalTecnicoReemplazado").val(),
				salario : $("#salario").val(),
				sltTipoMoneda : $("#sltTipoMoneda").val(),
				sltInstitucion : $("#sltInstitucion").val()
			}, function() {
				cargaGrillaPersonalTecnicoAdministrativo();
				limpiarCajas();
			});
		}
	}

	function modificarPersonal(personalTecnicoAdministrativoID,nombreCompleto,fkIdtablaespFormacionProfesional,responsabilidad,
			fkIdtablaespTiempoDedicado, porcentageParticipacion,fkIdtablaespEtapaPersonalTecnico,
			fkIdpersonalTecnicoAdmReemplazo,salarioMensual ,fkIdtablaespTipoMoneda ,institucionID ){

		var confirma = confirm("Esta seguro que desea cargar el registro para modificarlo?");
		
		if(confirma==true){
		$('#personalTecnicoAdministrativoID').attr("value",personalTecnicoAdministrativoID);
		$('#nombreCompleto').attr("value",nombreCompleto);
		$('#sltFormacionProfesional').attr("value",fkIdtablaespFormacionProfesional);
		$('#sltTiempoDedicado').attr("value",fkIdtablaespTiempoDedicado);
		$('#responsabilidad').attr("value",responsabilidad);
		$('#porcentajeParticipacion').attr("value",porcentageParticipacion);
		$('#sltEtapaPersonal').attr("value",fkIdtablaespEtapaPersonalTecnico);
		$('#salario').attr("value",salarioMensual);
		$('#sltTipoMoneda').attr("value",fkIdtablaespTipoMoneda);
		$('#sltInstitucion').attr("value",institucionID);
		$('#sltPersonalTecnicoReemplazado').attr("value",fkIdpersonalTecnicoAdmReemplazo);
		}
	}

	
	function cerrarPantalla() {
		window.close();
	}
</script>

<script type="text/javascript">
	function validaCajas() {
		var errores = 0;
		var mensaje = "";

		if ($("#nombreCompleto").val().length == 0) {
			mensaje += "Debe ingresar un nombre. \n";
			errores = errores + 1;
		}
		
		if ($("#sltFormacionProfesional").val() == 0) {
			mensaje += "Debe seleccionar la formacion profesional. \n";
			errores = errores + 1;
		}

		if ($("#sltTiempoDedicado").val() == 0) {
			mensaje += "Debe seleccionar el tiempo que dedicara. \n";
			errores = errores + 1;
		}
		
		if ($("#responsabilidad").val().length == 0) {
			mensaje += "Debe ingresar la responsabilidad. \n";
			errores = errores + 1;
		}
		
		if ($("#porcentajeParticipacion").val().length == 0) {
			mensaje += "Debe ingresar el porcentaje participacion. \n";
			errores = errores + 1;
		}
		
		if ($("#sltEtapaPersonal").val() == 0) {
			mensaje += "Debe seleccionar la etapa en que se desempeñara. \n";
			errores = errores + 1;
		}
		
		if ($("#salario").val().length == 0) {
			mensaje += "Debe ingresar el el salario. \n";
			errores = errores + 1;
		}
		
		if ($("#sltTipoMoneda").val() == 0) {
			mensaje += "Debe seleccionar el tipo de maneda del salario. \n";
			errores = errores + 1;
		}
		
		if ($("#sltInstitucion").val() == 0) {
			mensaje += "Debe seleccionar la institucion. \n";
			errores = errores + 1;
		}
		
		if (errores > 0) {
			alert(mensaje);
		}

		return errores;
		
	}
	
	function limpiarCajas(){
		$('#personalTecnicoAdministrativoID').attr("value","");
		$("#nombreCompleto").attr("value","");
		$("#sltFormacionProfesional").attr("value",0);
		$("#sltTiempoDedicado").attr("value",0);
		$("#responsabilidad").attr("value","");
		$("#porcentajeParticipacion").attr("value","");
		$("#sltEtapaPersonal").attr("value",0);
		$("#sltPersonalTecnicoReemplazado").attr("value",0);
		$("#salario").attr("value","");
		$("#sltTipoMoneda").attr("value",0);
		$("#sltInstitucion").attr("value",0);
	}
</script>

</head>
<body>
	<div class="form-clasico">
		<div class="encabezado">Personal Tecnico Administrativo</div>
		<br>
		<fieldset style="padding-left: 10px">
		<legend>Informacion de Personal Tecnico Administrativo</legend>
		<div class="hide">
			<input type="hidden" id="reporteAvanceId" name="reporteAvanceId"
				value="${reporteAvance.reporteAvanceID }"> 
			<input type="hidden" id="personalTecnicoAdministrativoID" name="personalTecnicoAdministrativoID" >	
			<table width="100%">
			<tr>
					<td style="width: 25%; text-align: right;"><label>Nombre Completo:</label>
					</td>
					<td colspan="3" style="width: 75%; text-align: left;">
						<input type="text" id="nombreCompleto" name="nombreCompleto" style="width: 95%;">
					</td>
				</tr>
				<tr>
					<td style="width: 25%; text-align: right;">
						<label>Formacion Profesional: </label>
					</td>
					<td style="width: 25%; text-align: left;">
						<select name="sltFormacionProfesional"
				id="sltFormacionProfesional">
				<option value="0"><c:out value="-- Formacion --" /></option>
				<c:forEach items="${listFormacionProfesional }" var="formacionProfesional">
						<option value="${formacionProfesional.tablaEspecificaID }" ><c:out
							value="${formacionProfesional.descripcionCabecera }" /></option>
				</c:forEach>
			</select>
					</td>
					<td style="width: 25%; text-align: right;">
						<label>Tiempo Dedicado: </label>
					</td>
					<td style="width: 25%; text-align: left;">
						<select name="sltTiempoDedicado"
				id="sltTiempoDedicado">
				<option value="0"><c:out value="-- Formacion --" /></option>
				<c:forEach items="${listTiempoDedicado }" var="tiempoDedicado">
						<option value="${tiempoDedicado.tablaEspecificaID }" ><c:out
							value="${tiempoDedicado.descripcionCabecera }" /></option>
				</c:forEach>
			</select>
					</td>
				</tr>
				<tr>
					<td style="width: 25%; text-align: right; vertical-align: top;"><label>Responsabilidad:</label>
					</td>
					<td style="width: 25%; text-align: left;"><textarea
							id="responsabilidad" name="responsabilidad" rows="4" cols="5"
							style="width: 98%;"></textarea>
					</td>
					<td style="width: 25%; text-align: right; vertical-align: top;"><label>Porcentaje Participacion:</label>
					</td>
					<td style="width: 25%; text-align: left; vertical-align: top;">
						<input type="text" id="porcentajeParticipacion" name="porcentajeParticipacion" maxlength="3"  onkeypress="javascript:return Valida_Dato(event,8);" style="width: 30px;"><label>%</label>
					</td>
				</tr>
				<tr>
						<td style="width: 25%; text-align: right;"><label>Etapa de Personal:</label>
						</td>
						<td style="width: 25%; text-align: left;">	
						<select name="sltEtapaPersonal"
				id="sltEtapaPersonal">
				<option value="0"><c:out value="-- Etapa --" /></option>
				<c:forEach items="${listEtapaPersonal }" var="etapaPersonal">
						<option value="${etapaPersonal.tablaEspecificaID }" ><c:out
							value="${etapaPersonal.descripcionCabecera }" /></option>
				</c:forEach>
			</select>
			</td>
						<td style="width: 25%; text-align: right;" ><label>Personal Tecnico Reemplazado:</label>
						</td>
						<td style="width: 25%; text-align: left;">
								<select name="sltPersonalTecnicoReemplazado"
				id="sltPersonalTecnicoReemplazado">
				<option value="0"><c:out value="-- Personal Tecnico --" /></option>
				<c:forEach items="${listPersonalTecnicoReemplazado }" var="personalTecnicoReemplazado">
						<option value="${personalTecnicoReemplazado.personalTecnicoAdministrativoID }" ><c:out
							value="${personalTecnicoReemplazado.nombreCompleto }" /></option>
				</c:forEach>
			</select>
						</td>
					</tr>
					<tr>
						<td style="width: 25%; text-align: right;  vertical-align: top;"><label>Salario Mensual:</label>
						</td>
						<td colspan="3" style="width: 75%; text-align: left;  vertical-align: top;">
							<input type="text" id="salario" name="salario" style="width: 80px;" onkeypress="javascript:return Valida_Dato(event,8);" maxlength="6">
							<select name="sltTipoMoneda"
				id="sltTipoMoneda" style="width: 100px;">
				<option value="0"><c:out value="-- Moneda --" /></option>
				<c:forEach items="${listTipoMoneda }" var="tipoMoneda">
						<option value="${tipoMoneda.tablaEspecificaID }" ><c:out
							value="${tipoMoneda.descripcionCabecera }" /></option>
				</c:forEach>
			</select>
						</td>
					</tr>
					<tr>
						<td style="width: 25%; text-align: right;  vertical-align: top;"><label>Institucion:</label>
						</td>
						<td colspan="3" style="width: 75%; text-align: left;  vertical-align: top;">
							<select name="sltInstitucion"
				id="sltInstitucion" style="width: 95%;">
				<option value="0"><c:out value="-- Institucion --" /></option>
				<c:forEach items="${listInstitucion }" var="institucion">
						<option value="${institucion.institucion.institucionID }" ><c:out
							value="${institucion.institucion.nombreInstitucion }" /></option>
				</c:forEach>
			</select>
						</td>
					</tr>
				<tr>
					<td colspan="2" style="width: 50%;"></td>
					<td colspan="2" style="width: 50%; text-align: right;"><input
						type="button" id="btnAgregarPersonalTecnico"
						name="btnAgregarPersonalTecnico"
						onclick="grabarPersonalTecnico()" value="Agregar Personal Tecnico">
						<input type="button" id="btnCerrarPantalla"
						name="btnCerrarPantalla" onclick="cerrarPantalla()" value="Cerrar">
					</td>
				</tr>

			</table>
	<br>
	</div>
	<div id="grillaPersonalTecnicoAdministrativo"></div>
	</fieldset>
</div>
</body>
</html>

