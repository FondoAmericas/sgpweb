<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Beneficiarios por Resultado</title>
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
	
	function ocultaCampos(){
		estado= "<c:out value="${estado}"></c:out>" ;
		//alert(estado);
		if ((estado =='apro')||(estado == 'eval')){
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
				cargaGrillaBeneficiario();
			});
</script>

<script type="text/javascript">
function cargaGrillaBeneficiario(){
	$("#grillaBeneficiarios").load(
			"grillaBeneficiarios.jspx",{
				reporteAvanceId : $("#reporteAvanceId").val()
			}, function(){
				cargaCombosDepProvDist();
				ocultaCampos();
			});
}
</script>

<script type="text/javascript">
//***********************  Llena combos  Departamento Provincia Distrito
var objXHR1;
var departamentos;
var provincias;
var distritos;

function cargaCombosDepProvDist() {
	var url= "cargaCombosDepProvDistRestriccionProyecto.jspx";//?reporteAvanceId="+$("#reporteAvanceId").val();
	objXHR1 = new ObjetoAJAX();
	objXHR1.enviar(url, "post",
			"llenaCombosDepProvDist", null);
}

function llenaCombosDepProvDist() {
	var depProvDist = JSON.parse(objXHR1.respuestaTexto());
	departamentos = depProvDist[0];
	provincias = depProvDist[1];
	distritos = depProvDist[2];
	llenaComboDepartamentos();
	//llenaComboDepartamentosApoderado();
}

function llenaComboDepartamentos(){
	var cbxDep = document.createElement("select");
	cbxDep.setAttribute("id", "cbxDepartamentos");
	cbxDep.setAttribute("name", "cbxDepartamentos");
	//evento para seleccionar la lista de provincias deacuerdo al departamento seleccionado.
	cbxDep.onchange=function(){
		llenaComboProvincias(this.value);limpiarComboDistritos();
		};
	//cbxDep.onchange=function(){alert(this.value);};
	//valor por defecto
	var op = document.createElement("option");
	op.setAttribute("value","0");
	var text=document.createTextNode("-- Departamento --");
	op.appendChild(text);
	cbxDep.appendChild(op);
	//llenar valores de todos los departamentos
	for(var i=0; i<departamentos.length; i++){
		op = document.createElement("option");
		op.setAttribute("value",departamentos[i].idDepartamento);
		text=document.createTextNode(departamentos[i].descripcion);
		op.appendChild(text);
		cbxDep.appendChild(op);
	}
	//limpia div
	document.getElementById("div_cbxDepartamentos").innerHTML="";
	//pinta combo en el div
	document.getElementById("div_cbxDepartamentos").appendChild(cbxDep);
	//deshabilitar los 3 combos
	//deshabilitarCombosDepProvDist();
	
}

//alumno
//var idDepartamento;
function llenaComboProvincias(departamentoId) {
	var cbxProv = document.createElement("select");
	cbxProv.setAttribute("id", "cbxProvincia");
	cbxProv.setAttribute("name", "cbxProvincia");
	cbxProv.onchange = function() {
		llenaComboDistritos(this.value);
	};
	////valor por defecto
	var op = document.createElement("option");
	op.setAttribute("value", "0");
	var text = document.createTextNode("-- Provincia --");
	op.appendChild(text);
	cbxProv.appendChild(op);
	////almacenar todas la provincias deacuerdo al departamento seleccionado
	//var provinciasPorDepartamento = new Array();
	for ( var i = 0; i < provincias.length; i++) {
		if (provincias[i].idDepartamento == departamentoId){
				//&& provincias[i].idprovincia != 0
				//&& provincias[i].iddistrito == 0) {
			//provinciasPorDepartamento.push(provincias[i]);
			op = document.createElement("option");
		op.setAttribute("value", provincias[i].idProvincia);
		text = document
				.createTextNode(provincias[i].descripcion);
		op.appendChild(text);
		cbxProv.appendChild(op);
		}
	}
	//limpia div
	document.getElementById("div_cbxProvincias").innerHTML = "";
	////pinta combo en el div
	document.getElementById("div_cbxProvincias").appendChild(cbxProv);
	$("#provincia").focus();
}

//alumno
function llenaComboDistritos(provinciaId) {
	//obtener datos de la provincia seleccionada para llenar sus distritos respectivas
	var cbxDist = document.createElement("select");
	cbxDist.setAttribute("id", "cbxDistrito");
	cbxDist.setAttribute("name", "cbxDistrito");
	//valor por defecto
	var op = document.createElement("option");
	op.setAttribute("value", "0");
	var text = document.createTextNode("-- Distrito --");
	op.appendChild(text);
	cbxDist.appendChild(op);
	//almacenar todas la provincias deacuerdo al departamento seleccionado
	//var distritosPorProvincias = new Array();
	for ( var i = 0; i < distritos.length; i++) {
		if (distritos[i].idProvincia == provinciaId ){
				/*&& distritos[i].idprovincia == idProvincia
				&& distritos[i].iddistrito != 0) {*/
			//distritosPorProvincias.push(distritos[i]);
			op = document.createElement("option");
			op.setAttribute("value", distritos[i].depProvDistID);
			text = document
					.createTextNode(distritos[i].descripcion);
			op.appendChild(text);
			cbxDist.appendChild(op);
		}
	}
	//limpia div
	document.getElementById("div_cbxDistritos").innerHTML = "";
	//pinta combo en el div
	document.getElementById("div_cbxDistritos").appendChild(cbxDist);
	$("#distrito").focus();

}

function limpiarComboProvincias() {
	var cbxProv = document.createElement("select");
	cbxProv.setAttribute("id", "cbxProvincia");
	cbxProv.setAttribute("name", "cbxProvincia");
	//valor por defecto
	var op = document.createElement("option");
	op.setAttribute("value", "0");
	var text = document.createTextNode("-- Provincia --");
	op.appendChild(text);
	cbxProv.appendChild(op);
	//limpia div
	document.getElementById("div_cbxProvincias").innerHTML = "";
	//pinta combo en el div
	document.getElementById("div_cbxProvincias").appendChild(cbxProv);
}

function limpiarComboDistritos() {
	var cbxDist = document.createElement("select");
	cbxDist.setAttribute("id", "cbxDistrito");
	cbxDist.setAttribute("name", "cbxDistrito");
	//valor por defecto
	var op = document.createElement("option");
	op.setAttribute("value", "0");
	var text = document.createTextNode("-- Distrito --");
	op.appendChild(text);
	cbxDist.appendChild(op);
	//limpia div
	document.getElementById("div_cbxDistritos").innerHTML = "";
	//pinta combo en el div
	document.getElementById("div_cbxDistritos").appendChild(cbxDist);
}

</script>

<script type="text/javascript">
	function grabarBeneficiario(){
		var error = validaCajas();

		if (error == 0) {
			//alert("Entro");
	
			$.get("grabarBeneficiario.jspx", {
				sltResultado : $("#sltResultado").val(),
				sltTipoBeneficiario : $("#sltTipoBeneficiario").val(),
				cantidadProgramada : $("#cantidadProgramada").val(),
				sltEstrato : $("#sltEstrato").val(),
				caracteristica : $("#caracteristica").val(),
				descripcion : $("#descripcion").val(),
				cbxDistrito : $("#cbxDistrito").val()//,localizacion : $("#localizacion").val()
			}, function() {
				$("#grillaBeneficiarios").load(
						"grillaBeneficiarios.jspx");
				cargaCombosDepProvDist();
				limpiarCajas();
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

		if ($("#sltResultado").val() == 0) {
			var mensajeComp = "Debe seleccionar un resultado. \n";
			if (mensaje == null) {
				mensaje = mensajeComp;
			} else {
				mensaje = mensaje + mensajeComp;
			}
			errores = errores + 1;
		}
		
		if ($("#sltTipoBeneficiario").val() == 0) {
			var mensajeComp = "Debe seleccionar un tipo de beneficiario. \n";
			if (mensaje == null) {
				mensaje = mensajeComp;
			} else {
				mensaje = mensaje + mensajeComp;
			}
			errores = errores + 1;
		}

		if ($("#cantidadProgramada").val().length == 0) {
			var mensajeComp = "Debe ingresar la cantidad programada. \n";
			if (mensaje == null) {
				mensaje = mensajeComp;
			} else {
				mensaje = mensaje + mensajeComp;
			}
			errores = errores + 1;
		}

		if ($("#sltEstrato").val() == 0) {
			var mensajeComp = "Debe seleccionar un estrato. \n";
			if (mensaje == null) {
				mensaje = mensajeComp;
			} else {
				mensaje = mensaje + mensajeComp;
			}
			errores = errores + 1;
		}

		if ($("#caracteristica").val().length == 0) {
			var mensajeComp = "Debe ingresar la caracteristica de la poblacion. \n";
			if (mensaje == null) {
				mensaje = mensajeComp;
			} else {
				mensaje = mensaje + mensajeComp;
			}
			errores = errores + 1;
		}

		if ($("#descripcion").val().length == 0) {
			var mensajeComp = "Debe ingresar la descripcion de la poblacion. \n";
			if (mensaje == null) {
				mensaje = mensajeComp;
			} else {
				mensaje = mensaje + mensajeComp;
			}
			errores = errores + 1;
		}
		
		if ($("#cbxDepartamentos").val() == 0) {
			var mensajeComp = "Debe seleccionar un departamento. \n";
			if (mensaje == null) {
				mensaje = mensajeComp;
			} else {
				mensaje = mensaje + mensajeComp;
			}
			errores = errores + 1;
		}
		
		if ($("#cbxProvincia").val() == 0) {
			var mensajeComp = "Debe seleccionar una provincia. \n";
			if (mensaje == null) {
				mensaje = mensajeComp;
			} else {
				mensaje = mensaje + mensajeComp;
			}
			errores = errores + 1;
		}

		if ($("#cbxDistrito").val() == 0) {
			var mensajeComp = "Debe seleccionar una distrito. \n";
			if (mensaje == null) {
				mensaje = mensajeComp;
			} else {
				mensaje = mensaje + mensajeComp;
			}
			errores = errores + 1;
		}

		/*if ($("#localizacion").val().length == 0) {
			var mensajeComp = "Debe ingresar una localizacion. \n";
			if (mensaje == null) {
				mensaje = mensajeComp;
			} else {
				mensaje = mensaje + mensajeComp;
			}
			errores = errores + 1;
		}*/
		
		if (errores > 0) {
			alert(mensaje);
		}

		return errores;
		
	}
	
	function limpiarCajas(){
		$("#sltResultado").attr("value",0);
		$("#sltTipoBeneficiario").attr("value",0);
		$("#cantidadProgramada").attr("value","");
		$("#sltEstrato").attr("value",0);
		$("#caracteristica").attr("value","");
		$("#descripcion").attr("value","");
		/*$("#cbxDepartamentos").val()
		$("#cbxProvincia").val() == 0) {
		$("#cbxDistrito").val() == 0) {*/
		$("#localizacion").attr("value","");
		limpiarComboProvincias();
		limpiarComboDistritos();
	
	}
</script>

<script type="text/javascript">
function eliminarReporteAvanceBeneficiario(reporteAvanceBeneficiarioID){
	var confirma= confirm("Esta seguro que desea eliminar el registro?");
	if(confirma==true){
		$.get("eliminarReporteAvanceBeneficiario.jspx", {
			reporteAvanceBeneficiarioID :reporteAvanceBeneficiarioID
		}, function() {
			cargaGrillaBeneficiario();
		});
	}
}
</script>
</head>
<body>
	<div class="form-clasico">
		<div class="encabezado">Beneficiarios por Resultado del Reporte <c:out value="${reporteAvance.periodo }"></c:out></div>
		<br>
		<fieldset style="padding-left: 10px">
		<legend>Informacion de Beneficiarios</legend>
		<div class="hide">
			<input type="hidden" id="reporteAvanceId" name="reporteAvanceId"
				value="${reporteAvance.reporteAvanceID }"> <input type="hidden"
				id="beneficiariosPorResultadoID" name="beneficiariosPorResultadoID"
				value="${beneficiariosPorResultadoID }">
			<table width="100%">
			<tr>
					<td style="width: 25%; text-align: right;"><label>Resultado:</label>
					</td>
					<td colspan="3" style="width: 75%; text-align: left;">
<select name="sltResultado" id="sltResultado" style="width: 98%;">
									<option value="0">-- Resultado --</option>
									
									<c:forEach items="${listResultado }" var="resultado">
						<option value="${resultado.resultadoID }" ><c:out
							value="${resultado.definicionResultado }" /></option>
				</c:forEach>
								</select>
					</td>
				</tr>
				<tr>
					<td style="width: 25%; text-align: right;">
						<label>Tipo Beneficiario: </label>
					</td>
					<td style="width: 25%; text-align: left;">
						<select name="sltTipoBeneficiario"
				id="sltTipoBeneficiario">
				<option value="0"><c:out value="-- Tipo --" /></option>
				<c:forEach items="${listTipoBeneficiario }" var="tipoBeneficiario">
						<option value="${tipoBeneficiario.tablaEspecificaID }" ><c:out
							value="${tipoBeneficiario.descripcionCabecera }" /></option>
				</c:forEach>
			</select>
					</td>
					<td style="width: 25%; text-align: right;">
						<label>Cantidad Programada: </label>
					</td>
					<td style="width: 25%; text-align: left;">
						<input type="text" id="cantidadProgramada" name="cantidadProgramada" style="width: 50px; text-align: center;" value="0" disabled="disabled" onkeypress="javascript:return Valida_Dato(event,8);" />
						<select name="sltEstrato"
				id="sltEstrato" style="width: 120px;">
				<option value="0"><c:out value="-- Estrato --" /></option>
				<c:forEach items="${listEstrato }" var="estrato">
						<option value="${estrato.tablaEspecificaID }" ><c:out
							value="${estrato.descripcionCabecera }" /></option>
				</c:forEach>
			</select>
					</td>
				</tr>
				<tr>
					<td style="width: 25%; text-align: right; vertical-align: top;"><label>Caracteristicas de Poblacion:</label>
					</td>
					<td style="width: 25%; text-align: left;"><textarea
							id="caracteristica" name="caracteristica" rows="4" cols="5"
							style="width: 98%;"></textarea>
					</td>
					<td style="width: 25%; text-align: right; vertical-align: top;"><label>Descripcion:</label>
					</td>
					<td style="width: 25%; text-align: left;"><textarea
							id="descripcion" name="descripcion" rows="4" cols="5"
							style="width: 98%;"></textarea>
					</td>
				</tr>
				<tr>
						<td style="width: 25%; text-align: right;"><label>Departamento:</label>
						</td>
						<td style="width: 25%; text-align: left;"><div id="div_cbxDepartamentos">
								<select name="cbxDepartamentos" id="cbxDepartamentos">
									<option value="0">-- Departamento --</option>
								</select>
							</div></td>
						<td style="width: 25%; text-align: right;" ><label>Provincia:</label>
						</td>
						<td style="width: 25%; text-align: left;"><div id="div_cbxProvincias">
								<select name="cbxProvincia" id="cbxProvincia">
									<option value="0">-- Provincia --</option>
								</select>
							</div></td>
					</tr>
					<tr>
						<td style="width: 25%; text-align: right;  vertical-align: top;"><label>Distrito:</label>
						</td>
						<td style="width: 25%; text-align: left;  vertical-align: top;"><div id="div_cbxDistritos">
								<select name="cbxDistrito" id="cbxDistrito">
									<option value="0">-- Distrito --</option>
								</select>
							</div></td>
							<!-- 
						<td  style="width: 25%; text-align: right; vertical-align: top;"><label >Localizacion:</label> 
						</td>
						<td  style="width: 25%; text-align: left;"><textarea id="localizacion" name="localizacion"
								COLS="50" ROWS="2"
								onkeypress="javascript:return Valida_Dato(event,10);"
								maxlength="240"></textarea> 
						</td>
					</tr>
				<tr>
					<td colspan="2" style="width: 50%;"></td>-->
					<td colspan="2" style="width: 50%; text-align: right;"><input
						type="button" id="btnAgregarBeneficiario"
						name="btnAgregarBeneficiario"
						onclick="grabarBeneficiario()" value="Agregar Beneficiario">
						<input type="button" id="btnCerrarPantalla"
						name="btnCerrarPantalla" onclick="cerrarPantalla()" value="Cerrar">
					</td>
				</tr>

			</table>
	
	<br>
	</div>
	<div id="grillaBeneficiarios"></div>
	</fieldset>
</div>
</body>
</html>

