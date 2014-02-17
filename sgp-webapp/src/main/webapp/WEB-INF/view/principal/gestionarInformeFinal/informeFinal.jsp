<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/includesTaglibsGenerico.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GESTIONAR INFORME FINAL</title>
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
var arrayEfectoProyecto;
var arrayMaterialProducido;
var arrayEvaluacionFinal;
var arrayLeccionAprendida;
var arrayConclusionFinal;
var arrayBien = new Array();

	$(document).ready(function() {
		$('#tab-container').easytabs();
		cargaGrillaProblemasSoluciones();
		$("#grillaRecursoUtilizado").load("cargaGrillaRecursoUtilizado.jspx");
		ocultaCampos();
	});
	/*$(window).load(function() {
		cargaGrillaDirectivaBeneficiario();
	});*/
</script>


<script type="text/javascript">
function ocultaCampos(){
	estado= "<c:out value="${estadoInformeFinal}"></c:out>" ;
	//alert(estado);
	if ((estado =='apro')|| (estado == 'eval')){
		$(".hide").hide();//attr("style","visibility: hidden;");
		$(".disabled").attr("disabled","disabled");
	}
}
</script>

<script type="text/javascript">

	function cargaGrillaProblemasSoluciones() {
		$("#grillaProblemasSoluciones").load(
				"cargaGrillaProblemasSoluciones.jspx",
				{
					datoProyectoId : $("#datoProyectoId")
							.val()
				});
	}

	function cargaGrillaEfectoProyecto() {
		$("#grillaEfectoProyecto").load(
				"cargaGrillaEfectoProyecto.jspx",
				{
					informeFinalId : $("#informeFinalId")
							.val()
				});
	}

	function cargaGrillaMaterialProducido() {
		$("#grillaMaterialProducido").load(
				"cargaGrillaMaterialProducido.jspx",
				{
					informeFinalId : $("#informeFinalId")
							.val()
				});
	}
	
	function cargaGrillaEvaluacionFinal(){
		$("#grillaEvaluacionFinal").load(
				"cargaGrillaEvaluacionFinal.jspx",
				{
					informeFinalId : $("#informeFinalId")
							.val()
				});
	}
	
	function cargaGrillaLeccionAprendida(){
		$("#grillaLeccionAprendida").load(
				"cargaGrillaLeccionAprendida.jspx",
				{
					informeFinalId : $("#informeFinalId")
							.val()
				});
	}
	
	function cargaGrillaConclucionInformeFinal(){
		$("#grillaConclucionInformeFinal").load(
				"cargaGrillaConclucionInformeFinal.jspx",
				{
					informeFinalId : $("#informeFinalId")
							.val()
				});
	}
	
	function cargaGrillaOrganizacion() {
		$("#grillaOrganizacion").load("cargaGrillaOrganizacionInformeFinal.jspx", {
			datoProyectoId : $("#datoProyectoId").val()
		});
	}

	function cargaGrillaBien() {
		$("#grillaBien").load("cargaGrillaBienInformeFinal.jspx", {
			datoProyectoId : $("#datoProyectoId").val()
		});
	}
	
	
</script>

<script type="text/javascript">
function grabarInformeFinal() {
	var error = validaCajasInformeFinal();

	if (error == 0) {
		var confirma = confirm("Seguro que desea grabar el informe final?");
		if(confirma==true){
		document.frmInformeFinal.action = "grabarInformeFinal.jspx";
		document.frmInformeFinal.submit();
	}
	}
}

function grabarPSRelevanteAlProyecto(problemaRelevante,problemaSolucionID){
	/*var problemaRelevante = 0;
	if ($("#problemaRelevante").is(":checked")) {
		problemaRelevante = 1;
	} */

	$.get("grabarPSRelevanteAlProyecto.jspx", {
		problemaRelevante : problemaRelevante,
		problemaSolucionID : problemaSolucionID
	}, function() {
		cargaGrillaProblemasSoluciones();
	});

}

function grabarEfectoProyecto(){
	var error = validaCajasEfectoProyecto();

	if (error == 0) {

	$.get("grabarEfectoProyecto.jspx", {
		sltTipoEfectoProyecto : $("#sltTipoEfectoProyecto").val(),
		comentarioEfectoProyecto : $("#comentarioEfectoProyecto").val(),
		informeFinalId : $("#informeFinalId").val(),
		efectoProyectoId : $("#efectoProyectoId").val()
	}, function() {
		cargaGrillaEfectoProyecto();
		limpiaCajasEfectoProyecto();
	});
	}
}

function grabarMaterialProducido(){
	var error = validaCajasMaterialProducido();

	if (error == 0) {

	$.get("grabarMaterialProducido.jspx", {
		materialProducidoId : $("#materialProducidoId").val(),
		descripcionMaterialProducido : $("#descripcionMaterialProducido").val(),
		cantidadMaterialProducido : $("#cantidadMaterialProducido").val(),
		sltTipoMaterialProducido : $("#sltTipoMaterialProducido").val(),
		informeFinalId : $("#informeFinalId").val()
	}, function() {
		cargaGrillaMaterialProducido();
		limpiaCajasMaterialProducido();
	});
	}	
}

function grabarEvaluacionFinal(){
	var error = validaCajasEvaluacionFinal();

	if (error == 0) {

	$.get("grabarEvaluacionFinal.jspx", {
		sltDescripcionTipoEvaluacionFinal : $("#sltDescripcionTipoEvaluacionFinal").val(),
		comentarioEvaluacionFinal : $("#comentarioEvaluacionFinal").val(),
		evaluacionFinalId : $("#evaluacionFinalId").val(),
		informeFinalId : $("#informeFinalId").val()
	}, function() {
		cargaGrillaEvaluacionFinal();
		limpiaCajasEvaluacionFinal();
	});
	}	
}
    
function grabarLeccionAprendida(){
	var error = validaCajasLeccionAprendida();

	if (error == 0) {
	$.get("grabarLeccionAprendida.jspx", {
		sltTipoLeccion : $("#sltTipoLeccion").val(),
		comentarioLeccionAprendida : $("#comentarioLeccionAprendida").val(),
		leccionApendidaId : $("#leccionApendidaId").val(),
		informeFinalId : $("#informeFinalId").val()
	}, function() {
		cargaGrillaLeccionAprendida();
		limpiaCajasLeccionAprendida();
	});
	}	
}

function grabarConclusionFinal(){
	var error = validaCajasConclusion();

	if (error == 0) {
	$.get("grabarConclusion.jspx", {
		sltDetalleTipoConclusion : $("#sltDetalleTipoConclusion").val(),
		comentarioConclusionInformeFinal : $("#comentarioConclusionInformeFinal").val(),
		conclucionId : $("#conclucionId").val(),
		informeFinalId : $("#informeFinalId").val()
	}, function() {
		cargaGrillaConclucionInformeFinal();
		limpiaCajasConclusionInformeFinal();
	});
	}	
}

function grabarOrganizacionInformeFinal() {
	var error = validaCajasOrganizacion();

	if (error == 0) {
		$.get("grabarOrganizacionInformeFinal.jspx", {
			nombreOrganizacion : $("#nombreOrganizacion").val(),
			situacionFinal : $("#situacionFinal").val(),
			organizacionId : $("#organizacionId").val(),
			informeFinalId : $("#informeFinalId").val(),
			datoProyectoId : $("#datoProyectoId").val()
		}, function() {
			cargaGrillaOrganizacion();
			limpiaCajasOrganizacion();
		});
	}
}

function grabarBienInformeFinal() {
	var error = validaCajasBien();

	if (error == 0) {
		$.get("grabarBienInformeFinal.jspx",
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
					informeFinalId : $("#informeFinalId").val(),
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
</script>

<script type="text/javascript">
function eliminarRegistroInformeFinal(idRegistro,tablaOpcion) {
	var error = confirm("Esta seguro que desea eliminar el registro?");

	if (error == true) {
		$.get("eliminarRegistroInformeFinal.jspx",
				{
			idRegistro : idRegistro,
			tablaOpcion : tablaOpcion
										}, function() {
					if(tablaOpcion=='efectoProyecto'){
						cargaGrillaEfectoProyecto();
					}else if(tablaOpcion=='materialProducido'){
						cargaGrillaMaterialProducido();
					}else if(tablaOpcion=='organizacion'){
						cargaGrillaOrganizacion();
					}else if(tablaOpcion=='leccionApendida'){
						cargaGrillaLeccionAprendida();
					}else if(tablaOpcion=='evaluacionFinal'){
						cargaGrillaEvaluacionFinal();
					}else if(tablaOpcion=='conclusion'){
						cargaGrillaConclucionInformeFinal();
					}
		});
	}
}

function eliminarRecursoUtilizado(fuenteFinanciadoraID){
	var error = confirm("Esta seguro que desea eliminar el registro?");

	if (error == true) {
		$.get("eliminarRecursoUtilizadoIF.jspx",
				{
			fuenteFinanciadoraID : fuenteFinanciadoraID
										}, function() {
											$("#grillaRecursoUtilizado").load(
											"cargaGrillaRecursoUtilizado.jspx");
				});	
}
	}

function eliminarBienInformeFinal(bienID){
	var error = confirm("Esta seguro que desea eliminar el registro?");

	if (error == true) {
		$.get("eliminarBienInformeFinal.jspx",
				{
			bienID : bienID
			}, function() {
				cargaGrillaBien();
});		
}}
</script>

<script type="text/javascript">
function modificarEfectoProyecto(efectoProyectoID ,fkidtablaespefectoProy ,comentario ){
	$("#sltTipoEfectoProyecto").attr("value", fkidtablaespefectoProy);
	$("#comentarioEfectoProyecto").attr("value", comentario);
	$("#efectoProyectoId").attr("value", efectoProyectoID);
	arrayEfectoProyecto = new Array();
}

function modificarMaterialProducido(materialProducidoID ,fkIdtablaespTipoMaterial ,cantidad ,descripcionMaterialProducido ){
	$("#sltTipoMaterialProducido").attr("value", fkIdtablaespTipoMaterial);
	$("#descripcionMaterialProducido").attr("value", descripcionMaterialProducido);
	$("#cantidadMaterialProducido").attr("value", cantidad);
	$("#materialProducidoId").attr("value", materialProducidoID);
	arrayMaterialProducido = new Array();

}

function modificarOrganizacionInformeFinal(organizacionID ,nombreOrganizacion ,situacionFinal ){
	$("#nombreOrganizacion").attr("value", nombreOrganizacion);
	$("#situacionFinal").attr("value", situacionFinal);
	$("#organizacionId").attr("value", organizacionID);
};

function modificarLeccionAprendida (leccionApendidaID ,fkIdtablaespTipoLeccion ,comentario ){
	$("#sltTipoLeccion").attr("value", fkIdtablaespTipoLeccion);
	$("#comentarioLeccionAprendida").attr("value", comentario);
	$("#leccionApendidaId").attr("value", leccionApendidaID);
	arrayLeccionAprendida = new Array();
	
	}
	
function modificarEvaluacionFinal( evaluacionFinalID ,comentario ,descripcionEFID ,fkIdtablaespEvalFinalCabecera ){
	$("#sltTipoEvaluacionFinal").attr("value", fkIdtablaespEvalFinalCabecera);
	cargaComboDescripcionTipoEvaluacion(fkIdtablaespEvalFinalCabecera,descripcionEFID);
	$("#comentarioEvaluacionFinal").attr("value", comentario);
	$("#evaluacionFinalId").attr("value", evaluacionFinalID);
	arrayEvaluacionFinal = new Array();
}

function modificarConclusion (conclusionID ,comentario ,detalleConcluIFID ,flidtablaespcabeceraconcluIF ) {
	$("#sltTipoConclusion").attr("value", flidtablaespcabeceraconcluIF);
	cargaComboDescripcionTipoConclusion(flidtablaespcabeceraconcluIF,detalleConcluIFID);
	$("#comentarioConclusionInformeFinal").attr("value", comentario);
	$("#conclucionId").attr("value", conclusionID);
	arrayConclusionFinal = new Array();
}

function modificarBienInformeFinal(bienID ,descripcionBien ,fkIdtablaespTipoBien ,fkIdDetalleEstCabEstadoConservacion ,
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

	function limpiaCajasEfectoProyecto() {
		$("#sltTipoEfectoProyecto").attr("value", "0");
		$("#comentarioEfectoProyecto").attr("value", "");
		$("#efectoProyectoId").attr("value", "0");
	}

	function limpiaCajasMaterialProducido() {
		$("#sltTipoMaterialProducido").attr("value", "0");
		$("#descripcionMaterialProducido").attr("value", "");
		$("#cantidadMaterialProducido").attr("value", "");
		$("#materialProducidoId").attr("value", "0");
	}
	
	function limpiaCajasEvaluacionFinal() {
		$("#sltTipoEvaluacionFinal").attr("value", "0");
		cargaComboDescripcionTipoEvaluacion(0,0);
		$("#comentarioEvaluacionFinal").attr("value", "");
		$("#evaluacionFinalId").attr("value", "0");
	}
	
	function limpiaCajasLeccionAprendida(){
		$("#sltTipoLeccion").attr("value", "0");
		$("#comentarioLeccionAprendida").attr("value", "");
		$("#leccionApendidaId").attr("value", "0");
	}
	
	function limpiaCajasConclusionInformeFinal(){
		$("#sltTipoConclusion").attr("value", "0");
		cargaComboDescripcionTipoConclusion(0,0);
		$("#comentarioConclusionInformeFinal").attr("value", "");
		$("#conclucionId").attr("value", "0");
		
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
	
</script>

<script type="text/javascript">
	function validaCajasInformeFinal() {
		var errores = 0;
		var mensaje = "";

		if ($("#resultadoProyectoInformeFinal").val().length == 0) {
			mensaje += "Debe ingresar un resultado de proyecto. \n";
			errores = errores + 1;
		}
		
		if ($("#sltEstadoInformeFinal").val() == 0) {
			mensaje += "Debe seleccionar un estado. \n";
			errores = errores + 1;
		}

		if (errores > 0) {
			alert(mensaje);
		}

		return errores;
	}
	
	function validaCajasEfectoProyecto(){
		var errores = 0;
		var mensaje = "";

		if ($("#sltTipoEfectoProyecto").val() == 0) {
			mensaje += "Debe seleccionar un tipo de efecto de proyecto. \n";
			errores = errores + 1;
		}
		
		if ($("#comentarioEfectoProyecto").val().length == 0) {
			mensaje += "Debe ingresar un comentario. \n";
			errores = errores + 1;
		}

		for ( var p = 0; p < arrayEfectoProyecto.length; p++) {
			if (arrayEfectoProyecto[p].tipoEfectoProyecto == $('#sltTipoEfectoProyecto').attr("value")) {
				mensaje += "El tipo de efecto de proyecto que desea ingresa ya se encuentra en la lista. \n";
				errores = errores + 1;
				break;
			}
		}
		if (errores > 0) {
			alert(mensaje);
		}

		return errores;
	}
	
	function validaCajasMaterialProducido(){
		var errores = 0;
		var mensaje = "";

		if ($("#descripcionMaterialProducido").val().length == 0) {
			mensaje += "Debe ingresar la descripcion del material producido. \n";
			errores = errores + 1;
		}
		
		if ($("#cantidadMaterialProducido").val().length == 0) {
			mensaje += "Debe ingresar la cantidad. \n";
			errores = errores + 1;
		}


		if ($("#sltTipoMaterialProducido").val() == 0) {
			mensaje += "Debe seleccionar el tipo de material producido. \n";
			errores = errores + 1;
		}

		for ( var p = 0; p < arrayMaterialProducido.length; p++) {
			if (arrayMaterialProducido[p].tipoMaterialProducido == $('#sltTipoMaterialProducido').attr("value")) {
				mensaje += "El tipo de material producido que desea ingresa ya se encuentra en la lista. \n";
				errores = errores + 1;
				break;
			}
		}
		if (errores > 0) {
			alert(mensaje);
		}

		return errores;
	}
	
	
	function validaCajasEvaluacionFinal(){
		var errores = 0;
		var mensaje = "";

		if ($("#sltTipoEvaluacionFinal").val() == 0) {
			mensaje += "Debe seleccionar un tipo de evaluacion. \n";
			errores = errores + 1;
		}
		
		if ($("#sltDescripcionTipoEvaluacionFinal").val()== 0) {
			mensaje += "Debe seleccionar una descripcion del tipo de evaluacion. \n";
			errores = errores + 1;
		}


		if ($("#comentarioEvaluacionFinal").val().length == 0) {
			mensaje += "Debe ingresar un comentario. \n";
			errores = errores + 1;
		}

		for ( var p = 0; p < arrayEvaluacionFinal.length; p++) {
			if (arrayEvaluacionFinal[p].tipoEvaluacionFinal == $('#sltDescripcionTipoEvaluacionFinal').attr("value")) {
				mensaje += "La descripcion del tipo de evaluacion que desea ingresar ya se encuentra en la lista. \n";
				errores = errores + 1;
				break;
			}
		}
		if (errores > 0) {
			alert(mensaje);
		}

		return errores;
	}
	
	function validaCajasLeccionAprendida(){
		var errores = 0;
		var mensaje = "";

		if ($("#sltTipoLeccion").val() == 0) {
			mensaje += "Debe seleccionar un tipo de leccion. \n";
			errores = errores + 1;
		}
		
		if ($("#comentarioLeccionAprendida").val().length == 0) {
			mensaje += "Debe ingresar un comentario para el tipo de leccion. \n";
			errores = errores + 1;
		}

		for ( var p = 0; p < arrayLeccionAprendida.length; p++) {
			if (arrayLeccionAprendida[p].tipoLeccion == $('#sltTipoLeccion').attr("value")) {
				mensaje += "La descripcion del tipo de leccion aprendida que desea ingresar ya se encuentra en la lista. \n";
				errores = errores + 1;
				break;
			}
		}
		if (errores > 0) {
			alert(mensaje);
		}

		return errores;
	}
	
	function validaCajasConclusion(){
		var errores = 0;
		var mensaje = "";

		if ($("#sltTipoConclusion").val() == 0) {
			mensaje += "Debe seleccionar un tipo de conclusion. \n";
			errores = errores + 1;
		}
		
		if ($("#sltDetalleTipoConclusion").val()== 0) {
			mensaje += "Debe seleccionar un detalle del tipo de conclusion. \n";
			errores = errores + 1;
		}


		if ($("#comentarioConclusionInformeFinal").val().length == 0) {
			mensaje += "Debe ingresar un comentario. \n";
			errores = errores + 1;
		}

		for ( var p = 0; p < arrayConclusionFinal.length; p++) {
			if (arrayConclusionFinal[p].detalleConcluIFID == $('#sltDetalleTipoConclusion').attr("value")) {
				mensaje += "El detalle del tipo de conclusion que desea ingresar ya se encuentra en la lista. \n";
				errores = errores + 1;
				break;
			}
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
</script>

<script type="text/javascript">
function cargaComboDescripcionTipoEvaluacion(tipoEvaluacionFinalId,descripcionEFID) {
	$("#sltDescripcionTipoEvaluacionFinal").load("cargarCombo.jspx", {tipoEvaluacionFinalId:tipoEvaluacionFinalId,metodo:"cargarDescripcionEvaluacionFinal"},function(){
		$("#sltDescripcionTipoEvaluacionFinal").attr("value", descripcionEFID);	
	});
}

function cargaComboDescripcionTipoConclusion(tipoConclusionFinalId,detalleConcluIFID){
	$("#sltDetalleTipoConclusion").load("cargarCombo.jspx", {tipoConclusionFinalId:tipoConclusionFinalId,metodo:"cargarDetalleConclusionFinal"}, function(){
		$("#sltDetalleTipoConclusion").attr("value", detalleConcluIFID);
	});
}

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
				"cargaGrillaRecursoUtilizadoInformeFinal.jspx",
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
</script>
</head>
<body>
	<div class="form-clasico">
		<div class="encabezado">GESTIONAR INFORME FINAL</div>
		<br>
		<fieldset style="padding-left: 10px">
			<legend>Informacion de Informe Final</legend>
			<form id="frmInformeFinal" name="frmInformeFinal">
				<input type="hidden" id="datoProyectoId" name="datoProyectoId"
					value="${datoProyectoId }"> <input type="hidden"
					id="informeFinalId" name="informeFinalId"
					value="${informeFinal.informeFinalID }">

				<table width="100%">
					<tr>
						<td style="width: 25%; text-align: right; vertical-align: top;"><label>Resultado del Proyecto:</label>
						</td>
						<td colspan="3" style="width: 75%; text-align: left;"><textarea
								id="resultadoProyectoInformeFinal" class="disabled"
								name="resultadoProyectoInformeFinal" rows="5" cols="5"
								style="width: 98%;">${informeFinal.resultadoProyecto }</textarea>
						</td>
					</tr>
					<tr>
						<td style="width: 25%; text-align: right;"><label>Estado:</label>
						</td>
						<td style="width: 25%; text-align: left;"><select
							name="sltEstadoInformeFinal" class="disabled"
							id="sltEstadoInformeFinal">
								<option value="0">
									<c:out value="-- Estado --" />
								</option>
								<c:forEach items="${listDetalleEstadoCabecera}"
									var="estadoInformeFinal">
									<option
										<c:if test="${informeFinal.fkIdDetalleEstadoCabEstInfFinal == estadoInformeFinal.detalleEstadoCabeceraID }"> selected="selected"</c:if>
										value="${estadoInformeFinal.detalleEstadoCabeceraID}">
										<c:out value="${estadoInformeFinal.descripEstado }" />
									</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr>
						<td colspan="3" style="width: 75%;"></td>
						<td style="width: 25%; text-align: left;"><input
							type="button" id="btnGrabarInformeFinal" class="hide"
							name="btnGrabarInformeFinal"
							onclick="grabarInformeFinal()"
							value="Grabar Informe Final">
						</td>
					</tr>
				</table>
			</form>
			<div id="tab-container" class="tab-container" style="width: 100%;">
				<ul class='etabs'>
					<li class='tab'><a href="#problemasSoluciones" onclick="cargaGrillaProblemasSoluciones()">Problemas<br/>Soluciones</a></li>
					<li class='tab'><a href="#efectoProyecto" onclick="cargaGrillaEfectoProyecto()">Efecto del<br/>Proyecto</a></li>
					<li class='tab'><a href="#materialProducido" onclick="cargaGrillaMaterialProducido()">Material<br/>Producido</a></li>
					<li class='tab'><a href="#evaluacionFinal" onclick="cargaGrillaEvaluacionFinal()">Evaluacion<br/>Final</a></li>
					<li class='tab'><a href="#leccionAprendida" onclick="cargaGrillaLeccionAprendida()">Lecciones<br/>Aprendidas</a></li>
					<li class='tab'><a href="#conclusion" onclick="cargaGrillaConclucionInformeFinal()">Conclusiones<br/>Proyecto</a></li>
					<li class='tab'><a href="#organizacion" onclick="cargaGrillaOrganizacion()">Organizaciones<br/>Proyecto</a></li>
					<li class='tab'><a href="#bien" onclick="cargaGrillaBien()" >Bienes<br/>Producidos</a></li>
				</ul>
				<div class='panel-container'>
					<div id="problemasSoluciones">
						<br />
						<div id="grillaProblemasSoluciones"></div>
					</div>
					<div id="efectoProyecto">
						<br />
						<input type="hidden" id="efectoProyectoId" name="efectoProyectoId" value="0">
						<table width="100%;"  class="hide">
							<tr>
								<td style="text-align: right; width: 25%;"><label>Tipo de Efecto:</label>
								</td>
								<td colspan="3" style="text-align: left; width: 75%;"><label><select
									name="sltTipoEfectoProyecto" 
									id="sltTipoEfectoProyecto" style="width: 98%;">
										<option value="0">
											<c:out value="--Efecto del Proyecto--" />
										</option>
										<c:forEach items="${listTipoEfectoProyecto}"
											var="tipoEfectoProyecto">
											<option value="${tipoEfectoProyecto.tablaEspecificaID }">
												<c:out value="${tipoEfectoProyecto.descripcionCabecera }" />
											</option>
										</c:forEach>
								</select></label></td>
							</tr>
							<tr>
								<td style="width: 25%; text-align: right; vertical-align: top;"><label>Comentario:</label>
								</td>
								<td colspan="3" style="width: 75%; text-align: left;"><textarea
										id="comentarioEfectoProyecto" name="comentarioEfectoProyecto" rows="5" cols="5"
										style="width: 98%;"></textarea></td>
							</tr>
							<tr>
								<td colspan="4" style="width: 100%; text-align: right;"><input
									type="button" id="btnGrabarEfectoProyecto"
									name="btnGrabarEfectoProyecto" onclick="grabarEfectoProyecto()"
									value="Grabar Efecto del Proyecto">
								</td>
							</tr>
						</table>
						<div id="grillaEfectoProyecto"></div>
					</div>
					<div id="materialProducido">
						<br />
						<input type="hidden" id="materialProducidoId" name="materialProducidoId" value="0">
						<table width="100%;"  class="hide">
							<tr>
								<td style="text-align: right; width: 25%;"><label>Tipo de Material:</label>
								</td>
								<td style="text-align: left; width: 25%;"><label><select
									name="sltTipoMaterialProducido"
									id="sltTipoMaterialProducido" style="width: 98%;">
										<option value="0">
											<c:out value="--Tipo de Material--" />
										</option>
										<c:forEach items="${listTipoMaterialProducido}"
											var="tipoMaterialProducido">
											<option value="${tipoMaterialProducido.tablaEspecificaID }">
												<c:out value="${tipoMaterialProducido.descripcionCabecera }" />
											</option>
										</c:forEach>
								</select></label></td>
								<td style="text-align: right; width: 25%;"><label>Cantidad:</label>
								</td>
								<td style="text-align: left; width: 25%;">
									<input type="text" id="cantidadMaterialProducido" name="cantidadMaterialProducido" onkeypress="javascript:return Valida_Dato(event,8);" maxlength="10">
									</td>
							</tr>
							<tr>
								<td style="width: 25%; text-align: right; vertical-align: top;"><label>Descripcion:</label>
								</td>
								<td colspan="3" style="width: 75%; text-align: left;"><textarea
										id="descripcionMaterialProducido" name="descripcionMaterialProducido" rows="5" cols="5"
										style="width: 98%;"></textarea></td>
							</tr>
							<tr>
								<td colspan="4" style="width: 100%; text-align: right;"><input
									type="button" id="btnGrabarMaterialProducido"
									name="btnGrabarMaterialProducido" onclick="grabarMaterialProducido()"
									value="Grabar Material Producido">
								</td>
							</tr>
						</table>
						<div id="grillaMaterialProducido"></div>
					</div>
					<div id="evaluacionFinal">
						<br />
						<input type="hidden" id="evaluacionFinalId" name="evaluacionFinalId" value="0">
						<table width="100%;"  class="hide">
							<tr>
								<td style="text-align: right; width: 25%;"><label>Tipo de Evaluacion:</label>
								</td>
								<td colspan="3" style="text-align: left; width: 75%;"><label><select
									name="sltTipoEvaluacionFinal" onchange="cargaComboDescripcionTipoEvaluacion(this.value,0)"
									id="sltTipoEvaluacionFinal" style="width: 98%;">
										<option value="0">
											<c:out value="--Tipo de Evaluacion--" />
										</option>
										<c:forEach items="${listTipoEvaluacion}"
											var="tipoEvaluacion">
											<option value="${tipoEvaluacion.tablaEspecificaID }">
												<c:out value="${tipoEvaluacion.descripcionCabecera }" />
											</option>
										</c:forEach>
								</select></label></td>
								
							</tr>
							<tr>
								<td style="text-align: right; width: 25%;"><label>Descripcion Tipo Evaluacion:</label>
								</td>
								<td colspan="3" style="text-align: left; width: 75%;"><label><select
									name="sltDescripcionTipoEvaluacionFinal"
									id="sltDescripcionTipoEvaluacionFinal" style="width: 98%;">
										<option value="0">
											<c:out value="--Descripcion Tipo de Evaluacion--" />
										</option>
										<c:forEach items="${listDescripcionTipoEvaluacion}"
											var="descripcionTipoEvaluacion">
											<option value="${descripcionTipoEvaluacion}">
												<c:out value="${descripcionTipoEvaluacion}" />
											</option>
										</c:forEach>
								</select></label></td>
								
							</tr>
							<tr>
								<td style="width: 25%; text-align: right; vertical-align: top;"><label>Comentario:</label>
								</td>
								<td colspan="3" style="width: 75%; text-align: left;"><textarea
										id="comentarioEvaluacionFinal" name="comentarioEvaluacionFinal" rows="5" cols="5"
										style="width: 98%;"></textarea></td>
							</tr>
							<tr>
								<td colspan="4" style="width: 100%; text-align: right;"><input
									type="button" id="btnGrabarEvaluacionFinal"
									name="btnGrabarEvaluacionFinal" onclick="grabarEvaluacionFinal()"
									value="Grabar Evaluacion Final">
								</td>
							</tr>
						</table>
						<div id="grillaEvaluacionFinal"></div>
					</div>
					<div id="leccionAprendida">
					<br />
						<input type="hidden" id="leccionApendidaId" name="leccionApendidaId" value="0">
						<table width="100%;"  class="hide">
							<tr>
								<td style="text-align: right; width: 25%;"><label>Tipo de Leccion:</label>
								</td>
								<td colspan="3" style="text-align: left; width: 75%;"><label><select
									name="sltTipoLeccion"
									id="sltTipoLeccion" style="width: 98%;">
										<option value="0">
											<c:out value="--Tipo de Leccion--" />
										</option>
										<c:forEach items="${listTipoLeccion}"
											var="tipoLeccion">
											<option value="${tipoLeccion.tablaEspecificaID }">
												<c:out value="${tipoLeccion.descripcionCabecera }" />
											</option>
										</c:forEach>
								</select></label></td>
							</tr>
							<tr>
								<td style="width: 25%; text-align: right; vertical-align: top;"><label>Comentario:</label>
								</td>
								<td colspan="3" style="width: 75%; text-align: left;"><textarea
										id="comentarioLeccionAprendida" name="comentarioLeccionAprendida" rows="5" cols="5"
										style="width: 98%;"></textarea></td>
							</tr>
							<tr>
								<td colspan="4" style="width: 100%; text-align: right;"><input
									type="button" id="btnGrabarLeccionAprendida"
									name="btnGrabarLeccionAprendida" onclick="grabarLeccionAprendida()"
									value="Grabar Leccion Aprendida">
								</td>
							</tr>
						</table>
						<div id="grillaLeccionAprendida"></div>
					</div>
					<div id="conclusion">
						<br />
						<input type="hidden" id="conclucionId" name="conclucionId" value="0">
						<table width="100%;"  class="hide">
							<tr>
								<td style="text-align: right; width: 25%;"><label>Tipo de Conclusion:</label>
								</td>
								<td colspan="3" style="text-align: left; width: 75%;"><label><select
									name="sltTipoConclusion" onchange="cargaComboDescripcionTipoConclusion(this.value,0)"
									id="sltTipoConclusion" style="width: 98%;">
										<option value="0">
											<c:out value="--Tipo de Conclusion--" />
										</option>
										<c:forEach items="${listTipoConclusion}"
											var="tipoConclusion">
											<option value="${tipoConclusion.tablaEspecificaID }">
												<c:out value="${tipoConclusion.descripcionCabecera }" />
											</option>
										</c:forEach>
								</select></label></td>
								
							</tr>
							<tr>
								<td style="text-align: right; width: 25%;"><label>Detalle Tipo Conclusion:</label>
								</td>
								<td colspan="3" style="text-align: left; width: 75%;"><label><select
									name="sltDetalleTipoConclusion"
									id="sltDetalleTipoConclusion" style="width: 98%;">
										<option value="0">
											<c:out value="--Detalle Tipo de Conclusion--" />
										</option>
										<c:forEach items="${listDetalleTipoConclusion}"
											var="detalleTipoConclusion">
											<option value="${detalleTipoConclusion}">
												<c:out value="${detalleTipoConclusion}" />
											</option>
										</c:forEach>
								</select></label></td>
								
							</tr>
							<tr>
								<td style="width: 25%; text-align: right; vertical-align: top;"><label>Comentario:</label>
								</td>
								<td colspan="3" style="width: 75%; text-align: left;"><textarea
										id="comentarioConclusionInformeFinal" name="comentarioConclusionInformeFinal" rows="5" cols="5"
										style="width: 98%;"></textarea></td>
							</tr>
							<tr>
								<td colspan="4" style="width: 100%; text-align: right;"><input
									type="button" id="btnGrabarConclusionInformeFinal"
									name="btnGrabarConclusionInformeFinal" onclick="grabarConclusionFinal()"
									value="Grabar Conclusion">
								</td>
							</tr>
						</table>
						<div id="grillaConclucionInformeFinal"></div>
					</div>
					<div id="organizacion">
						<br />
						<table width="100%;"  class="hide">
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
									name="btnGrabarOrganizacion" onclick="grabarOrganizacionInformeFinal()"
									value="Grabar Organizacion">
								</td>
							</tr>
						</table>
						<div id="grillaOrganizacion"></div>
					</div>
					<div id="bien">
						<br />
						<table width="100%;"  class="hide">
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
													name="sltTipoMonedaRecursoUtilizado" disabled="disabled"
													id="sltTipoMonedaRecursoUtilizado"
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
									onclick="grabarBienInformeFinal()" value="Grabar Bien">
								</td>
							</tr>
						</table>
						<div id="grillaBien"></div>
					</div>
				</div>
			</div>
		</fieldset>
	</div>
</body>
</html>