<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/gestorAjax.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/AtssefGrid.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/AtssefCBX.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/AtssefValidadorForm.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/evalReporteAvanceLiquidacionGastos/evalReporteAvanceLiquidacionGastos.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/util.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
/***************INICIO**********************************************/
$(document).ready(function() {
	ocultarObservacion();
	$("#cuerpoFiltro").load("showCuerpoEvalPropuestaEInforme.jspx",function() {
		cargarDataCBXModalidadFinanciamiento();
		$("#grillaProyectos").load("showGrillaProyectosObservaciones.jspx",
					{
						cbxModalidadFinan : $("#cbxModalidadFinan").val(),
						cbxPrograma : $("#cbxPrograma").val(),
						txtCodProyecto : $("#txtCodProyecto").val(),
						txtNomProyecto : $("#txtNomProyecto").val()
					});
		});
});

/***************FIN INICIO*****************************************/

function buscarProyectos(){

	var modFinan = document.getElementById("cbxModalidadFinan").value;
	var programa = document.getElementById("cbxPrograma").value;
	
	if(modFinan==0){
		alert("seleccionar modalidad financiamiento!");
	}else if(programa==0){
		alert("seleccionar programa!");
	}else{	
		$("#grillaProyectos").load("showGrillaProyectosObservaciones.jspx",
				{
					cbxModalidadFinan : $("#cbxModalidadFinan").val(),
					cbxPrograma : $("#cbxPrograma").val(),
					txtCodProyecto : $("#txtCodProyecto").val(),
					txtNomProyecto : $("#txtNomProyecto").val()
				});
	}
}

function mostrarObservacion(datoProyectoId){
	$("#filtroBusqueda").hide();
	$("#observacionesPorProyecto").show();
	
	$("#obsXproy").load("mostrarObservacionPorProyecto.jspx",
			{
		datoProyectoId : datoProyectoId
	});
}

function ocultarObservacion(){
	$("#filtroBusqueda").show();
	$("#observacionesPorProyecto").hide();
}

</script>

</head>
<body>

<div class="form-clasico">
		<div id="filtroBusqueda">
		<div class="encabezado">GESTIONAR OBSERVACIONES</div>
		<br>		
		<fieldset style="padding-left: 10px" >
			<legend>Observaciones por Proyectos</legend>
			<div id="cuerpoFiltro"></div>
			<br>
			<div id="grillaProyectos"></div>
		</fieldset>
		</div>
		<div id="observacionesPorProyecto">
			<div id="obsXproy"></div>	
		</div>
</div>
<input type="hidden" id="datoProyectoId" name="datoProyectoId" value="">
</body>
</html>