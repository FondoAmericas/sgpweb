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

	$("#cuerpoEvalReporteAvanLiqGastos").load("showCuerpoEvalReporteAvanLiqGastos.jspx",function() {
		
		cargarDataCBXModalidadFinanciamiento();
		
		$("#grillaEvalReporteAvanLiqGastos").load("showGrillaEvalReporteAvanLiqGastos.jspx",
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
		
		$("#grillaEvalReporteAvanLiqGastos").load("showGrillaEvalReporteAvanLiqGastos.jspx",
				{
					cbxModalidadFinan : $("#cbxModalidadFinan").val(),
					cbxPrograma : $("#cbxPrograma").val(),
					txtCodProyecto : $("#txtCodProyecto").val(),
					txtNomProyecto : $("#txtNomProyecto").val()
				});
	}
	
}
</script>

</head>
<body>

<div class="form-clasico">
		<div class="encabezado">EVALUACION DE LIQUIDACION DE GASTOS Y REPORTE DE AVANCE</div>
		<br>
		<fieldset style="padding-left: 10px">
			<legend>Evaluacion</legend>
			<div id="cuerpoEvalReporteAvanLiqGastos"></div>
			<br>
			<div id="grillaEvalReporteAvanLiqGastos"></div>
		</fieldset>
</div>
<input type="hidden" id="datoProyectoId" name="datoProyectoId" value="atssef">

</body>
</html>