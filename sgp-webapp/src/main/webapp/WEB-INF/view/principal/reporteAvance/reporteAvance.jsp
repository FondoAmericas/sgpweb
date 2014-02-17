<%@ include file="/common/taglibs.jsp"%>

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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/gestorAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/AtssefGrid.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/validador.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		var estadoPrefijoPlanOperativo= "<c:out value="${estadoPrefijoPlanOperativo }"></c:out>";
		
						$("#cuerpoReporteAvance").load("showCuerpoReporteAvance.jspx",function() {
							$("#grillaReporteAvance").load("showGrillaReporteAvance.jspx",
															{
																datoProyectoId : $("#datoProyectoId").val()
															});
							if(estadoPrefijoPlanOperativo!='apro'){
								$("#cuerpoReporteAvance").html('<label style=\"font-size: 13px; color: red;\">El PLAN OPERATIVO NO ESTA APROBADO, es por ello que no puede ingresar nuevos reportes.</label>');
							}
										});
					});
</script>
</head>
<body>
<input type="hidden" id="datoProyectoId" name="datoProyectoId"
	value="${datoProyectoId }">

	<div class="form-clasico">
		<div class="encabezado">GESTIONAR REPORTE DE AVANCE</div>
		<br>
		<fieldset style="padding-left: 10px">
			<legend>Informacion de Reportes de Avance</legend>
				<div id="cuerpoReporteAvance"></div>
				<br>
				<div id="grillaReporteAvance"></div>
		</fieldset>
	</div>
</body>
</html>