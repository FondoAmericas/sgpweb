<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Avance de Resultados por Meta Actividad</title>
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

	function ocultaCampos() {
		estado = "<c:out value="${estado}"></c:out>";
		//alert(estado);
		if ((estado == 'apro') || (estado == 'eval')) {
			$(".hide").hide();//attr("style","visibility: hidden;");
			$(".disabled").attr("disabled", "disabled");
			//alert($('#cbxInstitucionEjecutora option:selected').html());
			//$("#div_cbxInstitucionEjecutora").attr("style","visibility: hidden;");
		}
	}
</script>

<script type="text/javascript">
$(document).ready(function() {
	$("#cuerpoAvanceResultadosActividad").load("cuerpoAvanceResultadosActividad.jspx",{
		reporteAvanceId:$("#reporteAvanceId").val()
	},function() {
		$("#grillaAvanceResultadosActividad").load("grillaAvanceResultadoActividad.jspx",
										{
			reporteAvanceId : $("#reporteAvanceId").val(),
			estado : "<c:out value="${estado}"></c:out>"
										}, function(){
											ocultaCampos();
										});
					});
});
</script>

</head>
<body>
	<div class="form-clasico">
		<div class="encabezado">Avance de Resultados por Meta Actividad del Reporte <c:out value="${reporteAvance.periodo }"></c:out> </div>

<input type="hidden" id="reporteAvanceId" name="reporteAvanceId"
				value="${reporteAvance.reporteAvanceID }"> 
<input type="hidden" id="estado" name="estado"
				value="${estado }"> 				

		<br class="hide">
	<div id="cuerpoAvanceResultadosActividad" class="hide"></div>		
		<br class="hide">
	<div id="grillaAvanceResultadosActividad"></div>
		<br>
	</div>
</body>
</html>

