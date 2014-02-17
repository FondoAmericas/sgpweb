<%@ include file="/common/taglibs.jsp"%>


<script type="text/javascript"	src="${pageContext.request.contextPath}/js/gestorAjax.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/AtssefGrid.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/AtssefCBX.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/AtssefValidadorForm.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/evalReporteAvanceLiquidacionGastos/evalReporteAvanceLiquidacionGastos.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/util.js"></script>


<script type="text/javascript">
/***************INICIO**********************************************/
$(document).ready(function() {
	$("#filtro").show("fast");
	$("#seleccionado").hide("fast");
	$("#cuerpoAutorizarRpRf").load("showCuerpoAutorizarRpRf.jspx",function() {
		cargarDataCBXModalidadFinanciamiento();
		cargaGrillaAutorizarRpRf();
	});
	
	$("#div_carga").hide().ajaxStart(function() {
		$(this).show();
	}).ajaxStop(function() {
		$(this).hide();
	});
});

function cargaGrillaAutorizarRpRf(){
	$("#grillaAutorizarRpRf").load("showGrillaAutorizarRpRf.jspx",
			{
				cbxModalidadFinan : $("#cbxModalidadFinan").val(),
				cbxPrograma : $("#cbxPrograma").val(),
				txtCodProyecto : $("#txtCodProyecto").val(),
				txtNomProyecto : $("#txtNomProyecto").val()
			});
}

function buscarProyectos(){

	var modFinan = document.getElementById("cbxModalidadFinan").value;
	var programa = document.getElementById("cbxPrograma").value;
	
	if(modFinan==0){
		alert("seleccionar modalidad financiamiento!");
	}else if(programa==0){
		alert("seleccionar programa!");
	}else{
		
		$("#grillaAutorizarRpRf").load("showGrillaAutorizarRpRf.jspx",
				{
					cbxModalidadFinan : $("#cbxModalidadFinan").val(),
					cbxPrograma : $("#cbxPrograma").val(),
					txtCodProyecto : $("#txtCodProyecto").val(),
					txtNomProyecto : $("#txtNomProyecto").val()
				});
	}
	
}

function muestraInfoProyecto(datoProyectoId,versionPo,solicitaRpRfId){
	$("#filtro").hide("fast");
	$("#seleccionado").show("fast");
	$("#seleccionado").load("showProyectoSeleccionado.jspx",
			{
		datoProyectoId : datoProyectoId,
		versionPo : versionPo,
		solicitaRpRfId : solicitaRpRfId
			});
}

function regresar(){
	$("#filtro").show("fast");
	$("#seleccionado").hide("fast");
	$("#seleccionado").empty();
	}
</script>

<div id="div_carga">
		<img id="cargador" src="<c:url value="/images/ajax-loaderB.gif"/>"
			width="120px" height="120px" />
	</div> 
<div class="form-clasico">
		<div class="encabezado">AUTORIZACION DE SOLICITUD DE REPROGRAMACION &amp; REFORMULACION</div>
		<br>
		<div id="filtro">
		<fieldset style="padding-left: 10px">
			<legend>Busqueda de Proyectos</legend>
			<div id="cuerpoAutorizarRpRf"></div>
			<br>
			<div id="grillaAutorizarRpRf"></div>
		</fieldset>
		</div>
		<div id="seleccionado">
		</div>
</div>
