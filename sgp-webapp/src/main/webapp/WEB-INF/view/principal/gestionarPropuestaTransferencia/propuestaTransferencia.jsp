<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/includesTaglibsGenerico.jsp"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GESTIONAR REPORTE DE AVANCE</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/viewMain.css" ></c:url>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/estiloContabilidad.css"></c:url>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/estiloGeneral.css"></c:url>" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/tavConteiner.css" />
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
<script type="text/javascript"
	src="<c:url value="/js/jquery.hashchange.js"></c:url>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.easytabs.js"></c:url>"></script>

<script type="text/javascript">
var arrayBien = new Array();

	$(document).ready(function() {
		$('#tab-container').easytabs();
		cargaGrillaDirectivaBeneficiario();
		$("#grillaRecursoUtilizado").load("cargaGrillaRecursoUtilizado.jspx");
		ocultaCampos();
	});
	/*$(window).load(function() {
		cargaGrillaDirectivaBeneficiario();
	});*/
</script>

<script type="text/javascript">
function ocultaCampos(){
	estado= "<c:out value="${estadoPropuestaTransferencia}"></c:out>" ;
	//alert(estado);
	if ((estado =='apro')|| (estado == 'eval')){
		$(".hide").hide();
		$(".disabled").attr("disabled","disabled");
		
	}
}
</script>


<script type="text/javascript">
	function cargaGrillaDirectivaBeneficiario() {
		$("#grillaDirectivaBeneficiario").load(
				"cargaGrillaDirectivaBeneficiario.jspx",
				{
					propuestaTransferenciaId : $("#propuestaTransferenciaId")
							.val()
				});
	}

	function cargaGrillaBeneficiarioPropuestaTransferencia() {
		$("#grillaBeneficiarioPropuestaTransferencia").load(
				"cargaGrillaBeneficiarioPropuestaTransferencia.jspx",
				{
					propuestaTransferenciaId : $("#propuestaTransferenciaId")
							.val()
				});
	}

	function cargaGrillaOrganizacion() {
		$("#grillaOrganizacion").load("cargaGrillaOrganizacion.jspx", {
			datoProyectoId : $("#datoProyectoId").val()
		});
	}

	function cargaGrillaBien() {
		$("#grillaBien").load("cargaGrillaBien.jspx", {
			datoProyectoId : $("#datoProyectoId").val()
		});
	}
	
	function cargaGrillaBienTransferido() {
		$("#grillaBienTransferido").load("cargaGrillaBienTransferido.jspx", {
			datoProyectoId : $("#datoProyectoId").val(),
			propuestaTransferenciaId : $("#propuestaTransferenciaId").val()
		});
	}
</script>

<script type="text/javascript">
	function grabarPropuestaTransferencia() {
		var error = validaCajasPropuestaTransferencia();

		if (error == 0) {
			var validaArchivoAdjunto;
			var existeImagen = "<c:out value="${existeImagen }"></c:out>" ;
			
			if(existeImagen == 1){
				validaArchivoAdjunto = true; 
			}else{
				validaArchivoAdjunto = validarExtensionArchivo();
			}
			
			if(validaArchivoAdjunto==true){
				var confirma = confirm("Seguro que desea grabar la propuesta de transferencia?");
				if(confirma==true){
				document.frmPropuestaTransferencia.action = "grabarPropuestaTransferencia.jspx";
				document.frmPropuestaTransferencia.submit();
			}
			}
		}
	}

	function grabarDirectivaBeneficiario() {
		var error = validaCajasDirectiva();

		if (error == 0) {
			$.get("grabarDirectivaBeneficiario.jspx",
					{
						nombreCompleto : $("#nombreCompleto").val(),
						numeroDocumentoIdentidad : $(
								"#numeroDocumentoIdentidad").val(),
						sltTipoDocumento : $("#sltTipoDocumento").val(),
						sltCargo : $("#sltCargo").val(),
						acreditacion : $("#acreditacion").val(),
						vigenciaPoderes : $("#vigenciaPoderes").val(),
						directivaBeneficiarioId : $("#directivaBeneficiarioId")
								.val(),
						propuestaTransferenciaId : $(
								"#propuestaTransferenciaId").val()
					}, function() {
						cargaGrillaDirectivaBeneficiario();
						limpiaCajasDirectiva();
					});
		}
	}

	function grabarOrganizacion() {
		var error = validaCajasOrganizacion();

		if (error == 0) {
			$.get("grabarOrganizacion.jspx", {
				nombreOrganizacion : $("#nombreOrganizacion").val(),
				situacionFinal : $("#situacionFinal").val(),
				organizacionId : $("#organizacionId").val(),
				propuestaTransferenciaId : $("#propuestaTransferenciaId").val(),
				datoProyectoId : $("#datoProyectoId").val()
			}, function() {
				cargaGrillaOrganizacion();
				limpiaCajasOrganizacion();
				//cargaComboBienTransferido();
			});
		}
	}

	function grabarBien() {
		var error = validaCajasBien();

		if (error == 0) {
			$.get("grabarBien.jspx",
					{
						descripcionBien : $("#descripcionBien").val(),
						sltTipoBien : $("#sltTipoBien").val(),
						sltEstadoConservacion : $("#sltEstadoConservacion")
								.val(),
						cantidadTotal : $("#cantidadTotal").val(),
						sltUnidadMedida : $("#sltUnidadMedida").val(),
						cantidadSinTransferir : $("#cantidadSinTransferir")
								.val(),
						observacionBien : $("#observacionBien").val(),
						localizacionUbicacion : $("#localizacionUbicacion")
								.val(),
						costoUnitario : $("#costoUnitario").val(),

						sltTipoMoneda : $("#sltTipoMoneda").val(),
						costoTotal : $("#costoTotal").val(),
						activo : $("#activo").val(),
						datoProyectoId : $("#datoProyectoId").val(),
						propuestaTransferenciaId : $(
								"#propuestaTransferenciaId").val(),
						bienId : $("#bienId").val()
					}, function() {
						cargaGrillaBien();
						limpiaCajasBien();
						$("#grillaRecursoUtilizado").load(
								"cargaGrillaRecursoUtilizado.jspx");
						//cargaComboBienTransferido();
					});
		}
	}

	function grabarBienTransferido() {
		var error = validaCajasBienTransferido();

		if (error == 0) {
			$.get("grabarBienTransferido.jspx",
					{
				sltOrganizacionBienTrnasferido : $("#sltOrganizacionBienTrnasferido").val(),
				sltBienBienTransferido : $("#sltBienBienTransferido").val(),
				sltBeneficiarioBienTransferido : $("#sltBeneficiarioBienTransferido").val(),
				organizacionBienTranferidoId : $("#organizacionBienTranferidoId").val(),
				observacionBienTransferido : $("#observacionBienTransferido").val()
											}, function() {
						cargaGrillaBienTransferido();
						limpiaCajasBienTransferido();
						//$("#grillaRecursoUtilizado").load("cargaGrillaRecursoUtilizado.jspx");
						//cargaComboBienTransferido();
					});
		}
	}
	
</script>

<script type="text/javascript">
function eliminarRegistroPropuestaTransferencia(idRegistro,tablaOpcion) {
	var error = confirm("Esta seguro que desea eliminar el registro?");

	if (error == true) {
		$.get("eliminarRegistroPropuestaTransferencia.jspx",
				{
			idRegistro : idRegistro,
			tablaOpcion : tablaOpcion
										}, function() {
					if(tablaOpcion=='directivaBeneficiario'){
						cargaGrillaDirectivaBeneficiario();
					}else if(tablaOpcion=='organizacion'){
						cargaGrillaOrganizacion();
					}else if(tablaOpcion=='orgBienTranferido'){
						cargaGrillaBienTransferido();
					}else if(tablaOpcion=='bien'){
						cargaGrillaBien();
					}
											
				});
	}
}

function eliminarRecursoUtilizado(fuenteFinanciadoraID){
	var error = confirm("Esta seguro que desea eliminar el registro?");

	if (error == true) {
		$.get("eliminarRecursoUtilizado.jspx",
				{
			fuenteFinanciadoraID : fuenteFinanciadoraID
										}, function() {
											$("#grillaRecursoUtilizado").load(
											"cargaGrillaRecursoUtilizado.jspx");
				});	
}
	}
	
	function eliminarBien(bienID){
		var error = confirm("Esta seguro que desea eliminar el registro?");

		if (error == true) {
			$.get("eliminarBien.jspx",
					{
				bienID : bienID
				}, function() {
					cargaGrillaBien();
	});		
	}}
</script>

<script type="text/javascript">
function modificarDirectivaBeneficiario(directivaBeneficiarioID,nombreCompleto ,numeroDocumento ,fkIdtablaespTipoDocumento ,fkIdtablaespCargo ,acreditacion ,vigenciaPoder ){
	$("#nombreCompleto").attr("value", nombreCompleto);
	$("#numeroDocumentoIdentidad").attr("value", numeroDocumento);
	$("#sltTipoDocumento").attr("value", fkIdtablaespTipoDocumento);
	$("#sltCargo").attr("value", fkIdtablaespCargo);
	$("#acreditacion").attr("value", acreditacion);
	$("#vigenciaPoderes").attr("value", vigenciaPoder);
	$("#directivaBeneficiarioId").attr("value", directivaBeneficiarioID);
}

function modificarOrganizacion(organizacionID ,nombreOrganizacion ,situacionFinal ){
	$("#nombreOrganizacion").attr("value", nombreOrganizacion);
	$("#situacionFinal").attr("value", situacionFinal);
	$("#organizacionId").attr("value", organizacionID);
};

function modificarBien(bienID ,descripcionBien ,fkIdtablaespTipoBien ,fkIdDetalleEstCabEstadoConservacion ,
		cantidadTotal ,fkIdtablaespUnidadMedida ,cantidadSinTransferir ,observacion ,localizacionUbicacion ,
		costoUnitario ,costoTotal ,fkIdtablaespTipoMoneda, activoID ,categoriaActivoId){
	
	$("#bienId").attr("value", bienID);
	$("#descripcionBien").attr("value", descripcionBien);
	$("#sltTipoBien").attr("value", fkIdtablaespTipoBien);
	$("#sltEstadoConservacion").attr("value", fkIdDetalleEstCabEstadoConservacion);
	$("#cantidadTotal").attr("value", cantidadTotal);
	$("#sltUnidadMedida").attr("value", fkIdtablaespUnidadMedida);
	$("#sltUnidadMedida2").attr("value", fkIdtablaespUnidadMedida);
	$("#cantidadSinTransferir").attr("value", cantidadSinTransferir);
	$("#observacionBien").attr("value", observacion);
	$("#localizacionUbicacion").attr("value", localizacionUbicacion);
	$("#costoUnitario").attr("value", costoUnitario);
	$("#sltTipoMoneda").attr("value", fkIdtablaespTipoMoneda);
	$("#sltTipoMoneda2").attr("value", fkIdtablaespTipoMoneda);
	$("#sltTipoMonedaRecursoUtilizado").attr("value", fkIdtablaespTipoMoneda);
	$("#costoTotal").attr("value", costoTotal);
	$("#categoriaActivo").attr("value", categoriaActivoId);
	llenaActivo(activoID);
	
	$("#grillaRecursoUtilizado").load("cargaGrillaModificarRecursoUtilizado.jspx",{bienID : bienID});
}

</script>

<script type="text/javascript">
	function limpiaCajasDirectiva() {
		$("#nombreCompleto").attr("value", "");
		$("#numeroDocumentoIdentidad").attr("value", "");
		$("#sltTipoDocumento").attr("value", "0");
		$("#sltCargo").attr("value", "0");
		$("#acreditacion").attr("value", "");
		$("#vigenciaPoderes").attr("value", "");
		$("#directivaBeneficiarioId").attr("value", "0");
	}

	function limpiaCajasOrganizacion() {
		$("#nombreOrganizacion").attr("value", "");
		$("#situacionFinal").attr("value", "");
		$("#organizacionId").attr("value", "0");
	}

	function limpiarCajasRecursoUtilizado() {
		$("#montoRecursoUtilizado").attr("value", "");
		/*$("#sltTipoMonedaRecursoUtilizado").attr("value", "0");*/
		$("#fuenteFinanciadora").attr("value", "0");
	}

	function limpiaCajasBien() {
		$("#descripcionBien").attr("value", "");
		$("#sltTipoBien").attr("value", "0");
		$("#sltEstadoConservacion").attr("value", "0");
		$("#cantidadTotal").attr("value", "");
		$("#sltUnidadMedida").attr("value", "0");
		$("#cantidadSinTransferir").attr("value", "");
		$("#observacionBien").attr("value", "");
		$("#localizacionUbicacion").attr("value", "");
		$("#costoUnitario").attr("value", "");
		$("#sltTipoMoneda").attr("value", "0");
		$("#costoTotal").attr("value", "");
		$("#categoriaActivo").attr("value", "0");
		$("#activo").attr("value", "0");
		$("#bienId").attr("value", "0");
	}
	
	function limpiaCajasBienTransferido(){
		$("#sltOrganizacionBienTrnasferido").attr("value","0");
		$("#sltBienBienTransferido").attr("value","0");
		$("#sltBeneficiarioBienTransferido").attr("value","0");
		$("#organizacionBienTranferidoId").attr("value","0");
		$("#observacionBienTransferido").attr("value","");
	}
</script>

<script type="text/javascript">
	function validaCajasPropuestaTransferencia() {
		var errores = 0;
		var mensaje = "";

		if ($("#resumenDescripcionTranferencia").val().length == 0) {
			mensaje += "Debe ingresar un resumen de la descripcion de la transferencia. \n";
			errores = errores + 1;
		}

		if ($("#resumenPlanTransferencia").val().length == 0) {
			mensaje += "Debe ingresar un resumen del plan de transferencia. \n";
			errores = errores + 1;
		}

		if ($("#sltEstadoPropuestaTransferencia").val() == 0) {
			mensaje += "Debe seleccionar un estado. \n";
			errores = errores + 1;
		}

		if (errores > 0) {
			alert(mensaje);
		}

		return errores;
	}

	function validaCajasDirectiva() {
		var errores = 0;
		var mensaje = "";

		if ($("#nombreCompleto").val().length == 0) {
			mensaje += "Debe ingresar un nombre completo. \n";
			errores = errores + 1;
		}

		if ($("#numeroDocumentoIdentidad").val().length == 0) {
			mensaje += "Debe ingresar el documento de identidad. \n";
			errores = errores + 1;
		}

		if ($("#sltTipoDocumento").val() == 0) {
			mensaje += "Debe seleccionar el tipo de documento. \n";
			errores = errores + 1;
		}

		if ($("#sltCargo").val() == 0) {
			mensaje += "Debe seleccionar el cargo. \n";
			errores = errores + 1;
		}

		if ($("#acreditacion").val() == 0) {
			mensaje += "Debe ingresar la acreditacion. \n";
			errores = errores + 1;
		}

		if ($("#vigenciaPoderes").val() == 0) {
			mensaje += "Debe ingresar la vigencia de poderes. \n";
			errores = errores + 1;
		}
		if (errores > 0) {
			alert(mensaje);
		}

		return errores;
	}

	function validaCajasOrganizacion() {
		var errores = 0;
		var mensaje = "";

		if ($("#nombreOrganizacion").val().length == 0) {
			mensaje += "Debe ingresar un nombre de organizacion. \n";
			errores = errores + 1;
		}

		if ($("#situacionFinal").val().length == 0) {
			mensaje += "Debe ingresar la situacion final. \n";
			errores = errores + 1;
		}

		if (errores > 0) {
			alert(mensaje);
		}

		return errores;
	}

	function validaCajasRecursoUtilizado() {
		var errores = 0;
		var mensaje = "";

		if ($("#montoRecursoUtilizado").val().length == 0) {
			mensaje += "Debe ingresar un monto. \n";
			errores = errores + 1;
		}

		if ($("#sltTipoMonedaRecursoUtilizado").val() == 0) {
			mensaje += "Debe seleccionar un tipo de moneda. \n";
			errores = errores + 1;
		}

		if ($("#fuenteFinanciadora").val() == 0) {
			mensaje += "Debe seleccionar la fuente financiadora. \n";
			errores = errores + 1;
		}

		var montoActual = parseInt(0);
		for ( var p = 0; p < arrayBien.length; p++) {
			montoActual += parseInt(arrayBien[p].monto);
		}
		
		if ($("#montoRecursoUtilizado").val().length > 0) {
			montoActual += parseInt($("#montoRecursoUtilizado").val());
		}
		//alert(montoActual);
		if(montoActual > $("#costoTotal").val() ){
			mensaje += "La suma del recurso utilizado no puede ser mayor al costo total. \n";
			errores = errores + 1;
		}
		
		if (errores > 0) {
			alert(mensaje);
		}

		return errores;
	}

	function validaCajasBien() {
		var errores = 0;
		var mensaje = "";

		if ($("#descripcionBien").val().length == 0) {
			mensaje += "Debe ingresar una descricpion. \n";
			errores = errores + 1;
		}

		if ($("#sltTipoBien").val() == 0) {
			mensaje += "Debe seleccionar un tipo de bien. \n";
			errores = errores + 1;
		}

		if ($("#sltEstadoConservacion").val() == 0) {
			mensaje += "Debe seleccionar un estado de conservacion. \n";
			errores = errores + 1;
		}
		if ($("#sltUnidadMedida").val() == 0) {
			mensaje += "Debe seleccionar una unidad de medida. \n";
			errores = errores + 1;
		}

		if ($("#cantidadTotal").val().length == 0) {
			mensaje += "Debe ingresar la cantidad total. \n";
			errores = errores + 1;
		}

		if ($("#cantidadSinTransferir").val().length == 0) {
			mensaje += "Debe ingresar la cantidad sin transferir. \n";
			errores = errores + 1;
		}

		if ($("#observacionBien").val().length == 0) {
			mensaje += "Debe ingresar una observacion. \n";
			errores = errores + 1;
		}

		if ($("#localizacionUbicacion").val().length == 0) {
			mensaje += "Debe ingresar una localizacion. \n";
			errores = errores + 1;
		}

		if ($("#costoUnitario").val().length == 0) {
			mensaje += "Debe ingresar un costo unitario. \n";
			errores = errores + 1;
		}

		if ($("#sltTipoMoneda").val() == 0) {
			mensaje += "Debe seleccionar el tipo de moneda. \n";
			errores = errores + 1;
		}

		if ($("#categoriaActivo").val() == 0) {
			mensaje += "Debe seleccionar una categoria del activo. \n";
			errores = errores + 1;
		}

		if ($("#activo").val() == 0) {
			mensaje += "Debe seleccionar un activo. \n";
			errores = errores + 1;
		}

		if ($("#cantRecursoUtilizado").val().length == 0) {
			mensaje += "Debe ingresar al menos un recurso utilizado. \n";
			errores = errores + 1;
		}

		if (errores > 0) {
			alert(mensaje);
		}

		return errores;
	}
	
	function validaCajasBienTransferido() {
		var errores = 0;
		var mensaje = "";
		
		if ($("#sltBeneficiarioBienTransferido").val() == 0) {
			mensaje += "Debe seleccionar un beneficiario. \n";
			errores = errores + 1;
		}

		if ($("#sltBienBienTransferido").val() == 0) {
			mensaje += "Debe seleccionar un bien. \n";
			errores = errores + 1;
		}

		if ($("#sltOrganizacionBienTrnasferido").val() == 0) {
			mensaje += "Debe seleccionar una organizacion. \n";
			errores = errores + 1;
		}
		
		if (errores > 0) {
			alert(mensaje);
		}

		return errores;
	}
</script>

<script type="text/javascript">
	function igualaComboUnidadMedida(unidadMedidaId) {
		$("#sltUnidadMedida2").attr("value", unidadMedidaId);
	}

	function igualaComboTipoMoneda(tipoMonedaId) {
		$("#sltTipoMoneda2").attr("value", tipoMonedaId);
		$("#sltTipoMonedaRecursoUtilizado").attr("value", tipoMonedaId);
	}

	function llenaActivo(activoID) {
		$("#activo").load("cargarCombo.jspx", {
			categoriaActivoId : $("#categoriaActivo").val(),
			metodo : "cargarBienActivo"
		},function(){
			if(activoID!=0){
				$("#activo").attr("value", activoID);
			}	
		});
		
	}

	function agregarRecursoUtilizado() {
		var valida = validaCajasRecursoUtilizado();
		if (valida == 0) {
			$("#grillaRecursoUtilizado").load(
					"cargaGrillaRecursoUtilizado.jspx",
					{
						montoRecursoUtilizado : $("#montoRecursoUtilizado")
								.val(),
						sltTipoMonedaRecursoUtilizado : $(
								"#sltTipoMonedaRecursoUtilizado").val(),
						fuenteFinanciadora : $("#fuenteFinanciadora").val()
					}, function() {
						limpiarCajasRecursoUtilizado();
					});
		}
		$("#montoRecursoUtilizado").focus();
	}

	function calculaCostoTotal() {
		if (($("#costoUnitario").val() != "")
				&& ($("#cantidadTotal").val() != "")) {
			var costoUnitario = $("#costoUnitario").val();
			var cantidadTotal = $("#cantidadTotal").val();

			var costoTotal = parseInt(costoUnitario) * parseInt(cantidadTotal);
			$("#costoTotal").attr("disabled", "disabled");
			$("#costoTotal").attr("value", costoTotal);
		}
	}

	function cargaComboBienTransferido() {
		$("#sltBeneficiarioBienTransferido").load("cargarCombo.jspx", {propuestaTransferenciaId:$("#propuestaTransferenciaId").val(),metodo:"cargarBeneficiarioBienTransferido"});
		$("#sltBienBienTransferido").load("cargarCombo.jspx", {propuestaTransferenciaId:$("#propuestaTransferenciaId").val(),metodo:"cargarBienBienTransferido"});
		$("#sltOrganizacionBienTrnasferido").load("cargarCombo.jspx", {datoProyectoId:$("#datoProyectoId").val(),metodo:"cargarOrganizacionBienTransferido"});
		
	}
	
	function cambiaCantidadNumeroDocumentoIdentidad(tipoDocumentoId){
		if(tipoDocumentoId=='165'){ //dni
			$("#numeroDocumentoIdentidad").removeAttr("disabled");
			$("#numeroDocumentoIdentidad").attr("maxlength","8");
			$("#numeroDocumentoIdentidad").attr("value","");
			$("#numeroDocumentoIdentidad").focus();
		}else if(tipoDocumentoId=='166'){ //ruc
			$("#numeroDocumentoIdentidad").removeAttr("disabled");
			$("#numeroDocumentoIdentidad").attr("maxlength","11");
			$("#numeroDocumentoIdentidad").focus();
		}else{ // ninguno
			$("#numeroDocumentoIdentidad").attr("disabled","disabled");
			$("#numeroDocumentoIdentidad").attr("value","");
		}
	}
</script>

<script type="text/javascript">
function validarExtensionArchivo(){
	   extensiones_permitidas = new Array("doc","docx","pdf","DOC","DOCX","PDF","jpg","jpeg","JPG","JPEG");
	   ruta_archivo=$("#imagenODocumento").val();
	   if(!ruta_archivo){
	      alert("No se ha seleccionado ningun archivo!");
	      return false;
	   }else{
		  arrayNombreArchivo = ruta_archivo.split(".");
		  nombreArchivo=arrayNombreArchivo[0];
		  
		  nombreArchivoUpload=nombreArchivo;//variable en gestorAjax
		  
		  nombreArchivo = nombreArchivo.split('\\');
		  nombreArchivo=nombreArchivo[nombreArchivo.length-1];
		  //extension="."+arrayNombreArchivo[1];
		  extension=arrayNombreArchivo[1];
		  //alert("nombre de archivo: "+nombreArchivo);
	      //alert("extension de archivo: "+extension);
	      $("#nombreArchivo").attr("value",nombreArchivo);
	      $("#extension").attr("value",extension);
	      //alert(extension);
	      extensionPermitida=false;
		  for (var i = 0; i < extensiones_permitidas.length; i++) { 
	         if (extensiones_permitidas[i] == extension) { 
				 extensionPermitida=true; 
				 break; 
	         } 
	      } 
		  if (!extensionPermitida) { 
			alert("Solo se pueden subir archivos con las siguientes extensiones: "+extensiones_permitidas.join()); 
		    return false;
		  }
	   }
	   return true;
	}

</script>

</head>
<body>
	<div class="form-clasico">
		<div class="encabezado">GESTIONAR PROPUESTA DE TRANSFERENCIA</div>
		<br>
		<fieldset style="padding-left: 10px">
			<legend>Informacion de Propuesta de Transferencia</legend>
			<form:form commandName="imagenOArchivoTempBean" id="frmPropuestaTransferencia" 
					name="frmPropuestaTransferencia"	method="post"
					class="form-clasico" enctype="multipart/form-data" >
					
				<input type="hidden" id="datoProyectoId" name="datoProyectoId"
					value="${datoProyectoId }"> <input type="hidden"
					id="propuestaTransferenciaId" name="propuestaTransferenciaId"
					value="${propuestaTransferencia.propuestaTransferenciaID }">

				<table width="100%">
					<tr>
						<td style="width: 25%; text-align: right; vertical-align: top;"><label>Resumen
								Descripcion Transferencia:</label>
						</td>
						<td colspan="3" style="width: 75%; text-align: left;"><textarea
								id="resumenDescripcionTranferencia" class="disabled"
								name="resumenDescripcionTranferencia" rows="5" cols="5"
								style="width: 98%;">${propuestaTransferencia.resumenDescripTrans }</textarea>
						</td>
					</tr>
					<tr>
						<td style="width: 25%; text-align: right; vertical-align: top;"><label>Resumen
								Plan Transferencia:</label>
						</td>
						<td colspan="3" style="width: 75%; text-align: left;"><textarea
								id="resumenPlanTransferencia" name="resumenPlanTransferencia" class="disabled"
								rows="5" cols="5" style="width: 98%;">${propuestaTransferencia.resumenPlanTrans }</textarea>
						</td>
					</tr>
					<tr>
						<td style="width: 25%; text-align: right;"><label>Estado:</label>
						</td>
						<td style="width: 25%; text-align: left;"><select
							name="sltEstadoPropuestaTransferencia"  class="disabled"
							id="sltEstadoPropuestaTransferencia">
								<option value="0">
									<c:out value="-- Estado --" />
								</option>
								<c:forEach items="${listDetalleEstadoCabecera}"
									var="estadoPropuestaTransferencia">
									<option
										<c:if test="${propuestaTransferencia.fkIdDetalleEstadoCabEstInfPropTransfer == estadoPropuestaTransferencia.detalleEstadoCabeceraID }"> selected="selected"</c:if>
										value="${estadoPropuestaTransferencia.detalleEstadoCabeceraID}">
										<c:out value="${estadoPropuestaTransferencia.descripEstado }" />
									</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr class="hide">
						<td colspan="3" style="width: 75%;"></td>
						<td style="width: 25%; text-align: left;"><input
							type="button" id="btnGrabarPropuestaTransferencia"
							name="btnGrabarPropuestaTransferencia"
							onclick="grabarPropuestaTransferencia()"
							value="Grabar Propuesta Transferencia">
						</td>
					</tr>
				</table>
			<div id="tab-container" class="tab-container" style="width: 100%;">
				<ul class='etabs'>
					<li class='tab'><a href="#directivaBeneficiario" onclick="cargaGrillaDirectivaBeneficiario()">Directiva
							de Beneficiario</a></li>
					<li class='tab'><a href="#beneficiarios" onclick="cargaGrillaBeneficiarioPropuestaTransferencia()">Beneficiarios</a></li>
					<li class='tab'><a href="#bien" onclick="cargaGrillaBien()">Bienes</a></li>
					<li class='tab'><a href="#organizacion" onclick="cargaGrillaOrganizacion()">Organizaciones</a></li>
					<li class='tab'><a href="#bienTransferidos" onclick="cargaGrillaBienTransferido();cargaComboBienTransferido();">Bienes
							Transferidos</a></li>
					<li class='tab'><a href="#actaCierre" >Acta Cierre</a></li>		
				</ul>
				<div class='panel-container'>
					<div id="directivaBeneficiario">
						<br />
						<table width="100%;" class="hide">
							<tr>
								<td style="text-align: right; width: 25%;"><label>Nombre
										Completo:</label>
								</td>
								<td colspan="3" style="text-align: left; width: 75%;"><label><input
										type="text" id="nombreCompleto" name="nombreCompleto"
										style="width: 98%;"> </label> <input type="hidden"
									id="directivaBeneficiarioId" name="directivaBeneficiarioId"
									value="0"></td>
							</tr>
							<tr>
								<td style="text-align: right; width: 25%;"><label>Numero
										Documento Identidad:</label>
								</td>
								<td style="text-align: left; width: 25%;"><select
									name="sltTipoDocumento" id="sltTipoDocumento" onchange="cambiaCantidadNumeroDocumentoIdentidad(this.value)"
									style="width: 120px; float: left;">
										<option value="0">
											<c:out value="--Tipo Doc--" />
										</option>
										<c:forEach items="${listTipoDocumento}" var="tipoDocumento">
											<option value="${tipoDocumento.tablaEspecificaID}">
												<c:out value="${tipoDocumento.descripcionCabecera }" />
											</option>
										</c:forEach>
								</select><input
									type="text" id="numeroDocumentoIdentidad" disabled="disabled"
									name="numeroDocumentoIdentidad" 
									style="width: 100px; float: right;">
								</td>
								<td style="text-align: right; width: 25%;"><label>Cargo:</label>
								</td>
								<td style="text-align: left; width: 25%;"><select
									name="sltCargo" id="sltCargo">
										<option value="0">
											<c:out value="-- Cargo --" />
										</option>
										<c:forEach items="${listCargo}" var="cargo">
											<option value="${cargo.tablaEspecificaID}">
												<c:out value="${cargo.descripcionCabecera }" />
											</option>
										</c:forEach>
								</select>
								</td>
							</tr>
							<tr>
								<td style="width: 25%; text-align: right; vertical-align: top;"><label>Acreditacion:</label>
								</td>
								<td colspan="3" style="width: 75%; text-align: left;"><textarea
										id="acreditacion" name="acreditacion" rows="5" cols="5"
										style="width: 98%;"></textarea></td>
							</tr>
							<tr>
								<td style="width: 25%; text-align: right; vertical-align: top;"><label>Vigencia
										de Poderes:</label>
								</td>
								<td colspan="3" style="width: 75%; text-align: left;"><textarea
										id="vigenciaPoderes" name="vigenciaPoderes" rows="5" cols="5"
										style="width: 98%;"></textarea></td>
							</tr>
							<tr>
								<td colspan="4" style="width: 100%; text-align: right;"><input
									type="button" id="btnGrabarDirectivaBeneficiario"
									name="btnGrabarDirectivaBeneficiario"
									onclick="grabarDirectivaBeneficiario()"
									value="Grabar Directiva Beneficiario">
								</td>
							</tr>
						</table>
						<div id="grillaDirectivaBeneficiario"></div>
					</div>
					<div id="beneficiarios">
						<br />
						<div id="grillaBeneficiarioPropuestaTransferencia"></div>
					</div>
					<div id="bien">
						<br />
						<table width="100%;" class="hide">
							<tr>
								<td style="text-align: right; width: 25%; vertical-align: top;"><label>Descripcion
										Bien:</label>
								</td>
								<td colspan="3" style="text-align: left; width: 75%;"><textarea
										id="descripcionBien" name="descripcionBien" rows="5" cols="5"
										style="width: 98%;"></textarea><input type="hidden"
									id="bienId" name="bienId" value="0"></td>
							</tr>
							<tr>
								<td style="text-align: right; width: 25%;"><label>Tipo
										de Bien:</label>
								</td>
								<td style="text-align: left; width: 25%;"><select
									name="sltTipoBien" id="sltTipoBien">
										<option value="0">
											<c:out value="--Tipo Bien--" />
										</option>
										<c:forEach items="${listTipoBien}" var="tipoBien">
											<option value="${tipoBien.tablaEspecificaID}">
												<c:out value="${tipoBien.descripcionCabecera }" />
											</option>
										</c:forEach>
								</select>
								</td>
								<td style="text-align: right; width: 25%;"><label>Estado
										Conservacion:</label>
								</td>
								<td style="text-align: left; width: 25%;"><select
									name="sltEstadoConservacion" id="sltEstadoConservacion">
										<option value="0">
											<c:out value="--Estado Conservacion--" />
										</option>
										<c:forEach items="${listEstadoConservacion}"
											var="estadoConservacion">
											<option value="${estadoConservacion.detalleEstadoCabeceraID}">
												<c:out value="${estadoConservacion.descripEstado }" />
											</option>
										</c:forEach>
								</select>
								</td>
							</tr>
							<tr>
								<td style="width: 25%; text-align: right; vertical-align: top;"><label>Cantidad
										Total:</label>
								</td>
								<td style="width: 25%; text-align: left;"><input
									type="text" id="cantidadTotal" name="cantidadTotal"
									onkeypress="javascript:return Valida_Dato(event,8);"
									onchange="calculaCostoTotal()" maxlength="10"
									style="width: 100px; float: left;"><select
									name="sltUnidadMedida" id="sltUnidadMedida"
									style="width: 85px; float: right;"
									onchange="igualaComboUnidadMedida(this.value)">
										<option value="0">
											<c:out value="--Unidad--" />
										</option>
										<c:forEach items="${listUnidadMedida}" var="unidadMedida">
											<option value="${unidadMedida.tablaEspecificaID}">
												<c:out value="${unidadMedida.descripcionCabecera }" />
											</option>
										</c:forEach>
								</select></td>
								<td style="width: 25%; text-align: right; vertical-align: top;"><label>Cantidad
										Sin Transferir:</label>
								</td>
								<td style="width: 25%; text-align: left;"><input
									type="text" id="cantidadSinTransferir"
									onkeypress="javascript:return Valida_Dato(event,8);"
									maxlength="10" name="cantidadSinTransferir"
									style="width: 100px; float: left;"><select
									disabled="disabled" name="sltUnidadMedida2"
									id="sltUnidadMedida2" style="width: 85px; float: right;">
										<option value="0">
											<c:out value="--Unidad--" />
										</option>
										<c:forEach items="${listUnidadMedida}" var="unidadMedida">
											<option value="${unidadMedida.tablaEspecificaID}">
												<c:out value="${unidadMedida.descripcionCabecera }" />
											</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td style="width: 25%; text-align: right; vertical-align: top;"><label>Observacion:</label>
								</td>
								<td colspan="3" style="width: 75%; text-align: left;"><textarea
										id="observacionBien" name="observacionBien" rows="5" cols="5"
										style="width: 98%;"></textarea></td>
							</tr>
							<tr>
								<td style="width: 25%; text-align: right; vertical-align: top;"><label>Localizacion
										Ubicacion:</label>
								</td>
								<td colspan="3" style="width: 75%; text-align: left;"><textarea
										id="localizacionUbicacion" name="localizacionUbicacion"
										rows="5" cols="5" style="width: 98%;"></textarea></td>
							</tr>
							<tr>
								<td style="width: 25%; text-align: right; vertical-align: top;"><label>Costo
										Unitario:</label>
								</td>
								<td style="width: 25%; text-align: left;"><input
									type="text" id="costoUnitario" name="costoUnitario"
									onkeypress="javascript:return Valida_Dato(event,4);"
									onchange="calculaCostoTotal()" maxlength="10"
									style="width: 100px; float: left;"><select
									name="sltTipoMoneda" id="sltTipoMoneda"
									onchange="igualaComboTipoMoneda(this.value)"
									style="width: 85px; float: right;">
										<option value="0">
											<c:out value="--Moneda--" />
										</option>
										<c:forEach items="${listTipoMoneda}" var="tipoMoneda">
											<option value="${tipoMoneda.tablaEspecificaID}">
												<c:out value="${tipoMoneda.descripcionCabecera }" />
											</option>
										</c:forEach>
								</select></td>
								<td style="width: 25%; text-align: right; vertical-align: top;"><label>Costo
										Total:</label>
								</td>
								<td style="width: 25%; text-align: left;"><input
									type="text" id="costoTotal" name="costoTotal"
									onkeypress="javascript:return Valida_Dato(event,4);"
									maxlength="10" style="width: 100px; float: left;"><select
									disabled="disabled" name="sltTipoMoneda2" id="sltTipoMoneda2"
									style="width: 85px; float: right;">
										<option value="0">
											<c:out value="--Moneda--" />
										</option>
										<c:forEach items="${listTipoMoneda}" var="tipoMoneda">
											<option value="${tipoMoneda.tablaEspecificaID}">
												<c:out value="${tipoMoneda.descripcionCabecera }" />
											</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td style="text-align: right; width: 25%;"><label>Categoria
										Activo:</label></td>
								<td style="text-align: left; width: 25%;"><select
									name="categoriaActivo" id="categoriaActivo"
									onchange="llenaActivo(0)">
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
								<td style="text-align: right; width: 25%;"><label>Bien
										/ Activo:</label></td>
								<td style="text-align: left; width: 25%;"><select
									name="activo" id="activo">
										<option value="0">
											<c:out value="-- Bien/Activo --" />
											<c:forEach items="${listActivo}" var="lActivo">
												<c:choose>
													<c:when test="${lActivo.activoID == activo.activoID }">
														<option selected="selected" value="${lActivo.activoID}">
															<c:out value="${lActivo.descripcionActivo }" />
														</option>
													</c:when>
													<c:otherwise>
														<option value="${lActivo.activoID}">
															<c:out value="${lActivo.descripcionActivo }" />
														</option>
													</c:otherwise>
												</c:choose>

											</c:forEach>
										</option>
								</select></td>
							</tr>
							<tr>
								<td colspan="4" style="width: 100%">
									<fieldset>
										<legend>
											<label>Recurso Utilizado</label>
										</legend>
										<table width="100%">
											<tr>
												<td style="width: 25%; text-align: right;"><label>Monto:</label>
												</td>
												<td style="width: 25%; text-align: left;"><input
													type="text" id="montoRecursoUtilizado"
													name="montoRecursoUtilizado"
													onkeypress="javascript:return Valida_Dato(event,4);"
													maxlength="10" style="width: 96px; float: left;"><select
													name="sltTipoMonedaRecursoUtilizado"
													id="sltTipoMonedaRecursoUtilizado" disabled="disabled"
													style="width: 80px; float: right;">
														<option value="0">
															<c:out value="--Moneda--" />
														</option>
														<c:forEach items="${listTipoMoneda}" var="tipoMoneda">
															<option value="${tipoMoneda.tablaEspecificaID}">
																<c:out value="${tipoMoneda.descripcionCabecera }" />
															</option>
														</c:forEach>
												</select></td>
												<td style="width: 25%; text-align: right;"><label>Fuente
														Financiadora:</label></td>
												<td style="width: 25%; text-align: left;"><select
													name="fuenteFinanciadora" id="fuenteFinanciadora">
														<option value="0">
															<c:out value="-- Fuente Financiadora --" />
														</option>
														<c:forEach items="${listFuenteFinanciadora}" var="fuente">
															<option value="${fuente.fuenteFinanciadoraID}">
																<c:out value="${fuente.institucion.nombreInstitucion}" />
															</option>
															<script type="text/javascript">
																/*		var objFuenteFinanciadora = new Object();
																		objFuenteFinanciadora.fuenteFinanciadoraID = "<c:out value="${fuente.fuenteFinanciadoraID}"></c:out>";
																		objFuenteFinanciadora.saldoDisponible = "<c:out value="${fuente.montoFinanciado}"></c:out>";
																		arrayFuenteFinanciadora.push(objFuenteFinanciadora);*/
															</script>
														</c:forEach>

												</select>
												</td>
											</tr>
											<tr>
												<td colspan="3" style="width: 75%;"></td>
												<td style="width: 25%; text-align: left;"><a
													href="javascript:agregarRecursoUtilizado()"
													id="btnRecursoUtilizado" class="linkSelecciona">Agregar
														Recurso Utilizado</a>
												</td>
											</tr>
											<tr>
												<td colspan="4" style="width: 100%;">
													<div style="float: right; width: 80%;">
														<div id="grillaRecursoUtilizado"></div>
													</div>
												</td>
											</tr>
										</table>
									</fieldset>
								</td>
							</tr>
							<tr>
								<td colspan="4" style="width: 100%; text-align: right;"><input
									type="button" id="btnGrabarBien" name="btnGrabarBien"
									onclick="grabarBien()" value="Grabar Bien">
								</td>
							</tr>
						</table>
						<div id="grillaBien"></div>
					</div>
					<div id="organizacion">
						<br />
						<table width="100%;" class="hide">
							<tr>
								<td style="text-align: right; width: 25%;"><label>Nombre
										Completo:</label>
								</td>
								<td colspan="3" style="text-align: left; width: 75%;"><label><input
										type="text" id="nombreOrganizacion" name="nombreOrganizacion"
										style="width: 98%;"> </label> <input type="hidden"
									id="organizacionId" name="organizacionId" value="0"></td>
							</tr>
							<tr>
								<td style="width: 25%; text-align: right; vertical-align: top;"><label>Situacion
										Final:</label>
								</td>
								<td colspan="3" style="width: 75%; text-align: left;"><textarea
										id="situacionFinal" name="situacionFinal" rows="5" cols="5"
										style="width: 98%;"></textarea></td>
							</tr>
							<tr>
								<td colspan="4" style="width: 100%; text-align: right;"><input
									type="button" id="btnGrabarOrganizacion"
									name="btnGrabarOrganizacion" onclick="grabarOrganizacion()"
									value="Grabar Organizacion">
								</td>
							</tr>
						</table>
						<div id="grillaOrganizacion"></div>
					</div>
					<div id="bienTransferidos">
						<br />
						<table width="100%;" class="hide">
							<tr>
								<td style="text-align: right; width: 25%;"><label>Organizacion:</label>
								<input type="hidden" id="organizacionBienTranferidoId" name="organizacionBienTranferidoId" value="0">
								</td>
								<td colspan="3" style="text-align: left; width: 75%;"><select
									name="sltOrganizacionBienTrnasferido"
									id="sltOrganizacionBienTrnasferido" style="width: 98%;">
										<option value="0">
											<c:out value="--Organizacion--" />
										</option>
										<!--<c:forEach items="${listOrganizacionBienTrnasferido}"
											var="organizacionBienTrnasferido">
											<option value="${organizacionBienTrnasferido}">
												<c:out value="${organizacionBienTrnasferido}" />
											</option>
										</c:forEach>-->
								</select></td>
							</tr>
							<tr>
								<td style="width: 25%; text-align: right; vertical-align: top;"><label>Bien
										a Transferir:</label>
								</td>
								<td colspan="3" style="width: 75%; text-align: left;"><select
									name="sltBienBienTransferido" id="sltBienBienTransferido"
									style="width: 98%;">
										<option value="0">
											<c:out value="--Bien--" />
										</option>
										<!--<c:forEach items="${listBien}" var="bien">
											<option value="${bien}">
												<c:out value="${bien}" />
											</option>
										</c:forEach>-->
								</select></td>
							</tr>
							<tr>
								<td style="width: 25%; text-align: right; vertical-align: top;"><label>Beneficiarios:</label>
								</td>
								<td colspan="3" style="width: 75%; text-align: left;"><select
									name="sltBeneficiarioBienTransferido"
									id="sltBeneficiarioBienTransferido" style="width: 98%;">
										<option value="0">
											<c:out value="--Beneficiario--" />
										</option>
										<!--<c:forEach items="${listBeneficiario}" var="beneficiario">
											<option value="${beneficiario}">
												<c:out value="${beneficiario}" />
											</option>
										</c:forEach>-->
								</select></td>
							</tr>
							<tr>
								<td style="width: 25%; text-align: right; vertical-align: top;"><label>Observacion:</label>
								</td>
								<td colspan="3" style="width: 75%; text-align: left;"><textarea
								id="observacionBienTransferido" name="observacionBienTransferido" 
								rows="5" cols="5" style="width: 98%;"></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="4" style="width: 100%; text-align: right;"><input
									type="button" id="btnGrabarBienTransferido"
									name="btnGrabarBienTransferido"
									onclick="grabarBienTransferido()"
									value="Grabar Bien Transferido">
								</td>
							</tr>
						</table>
						<div id="grillaBienTransferido"></div>
					</div>
					<div id="actaCierre">
						<br />
						<fieldset>
							<table width="100%">
								<tr>
									<td style="text-align: right; width: 20%;"><label>Documento:</label>
									</td>
									<td><iframe src="showImagenArchivoDownloadPropuestaTransferencia.jspx?propuestaTransferenciaId=${propuestaTransferencia.propuestaTransferenciaID }"
											id="window" frameborder="0" height="40px" width="100%"
											scrolling="no"></iframe>
											
									</td>
								</tr>
								<tr class="hide">
									<td style="text-align: right;"><label>Agregar Documento: (pdf,docx,jpg)</label>
									</td>
									<td><!--  <iframe src="showArchivoUploadProyecto.jspx"
											id="window" frameborder="0" height="50px" width="100%"
											scrolling="no"></iframe>-->
											<div>
							<spring:bind path="imagenODocumento">
											<input type="file" name="imagenODocumento" id="imagenODocumento" />
										</spring:bind>
										</div>
										<input type="hidden" id="nombreDocumentoAdjunto" name="nombreDocumentoAdjunto">
										<input type="hidden" id="nombreArchivo" name="nombreArchivo">
										<input type="hidden" id="extension" name="extension">
										</td>
								</tr>
							</table>
						</fieldset>
					</div>
				</div>
			</div>
			</form:form>
		</fieldset>
	</div>
</body>
</html>