<%@include file="includesTaglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><spring:message code="web.app.title"></spring:message></title>
	<script>
		function goTo(url) {
			window.location = url;
		}
		function goBack(url) {
			goTo(url);
		}
		function showReporte(){
			var selectedIndex1 = document.reportCostoActividadFuente.fuenteFinanciadoraID.selectedIndex;
		    var optionValue1 = document.reportCostoActividadFuente.fuenteFinanciadoraID.options[selectedIndex1].value;
		    var fuenteFinanciadoraID = optionValue1;
		    
		    var selectedIndex2 = document.reportCostoActividadFuente.actividadID.selectedIndex;
		    var optionValue2 = document.reportCostoActividadFuente.actividadID.options[selectedIndex2].value;
		    var actividadID = optionValue2;
			var url = "reportPlanOperativoCostoActividadFuente.jspx?fuenteFinanciadoraID="+fuenteFinanciadoraID + "&actividadID="+actividadID;
			openWindow(url);
		}
		
		function openWindow(url){
			window.open(url,'','width=800,height=600,left=0,top=100,screenX=0,screenY=100');
		}
	</script>
</head>
<body style="font-family: Arial; font-size: smaller;">
<form name="reportCostoActividadFuente">
<div class="form-clasico">
    <fieldset><legend>Reporte Costo Actividad por Fuente</legend>
		<br>
		<table border="0">
			<tr>
				<td align="right">Fuente Financiamiento:</td>
				<td align="left" colspan="5">
					<select id="fuenteFinanciadoraID" name="fuenteFinanciadoraID" style="width: 200px;" >
<!--						<option value="0">Seleccionar</OPTION>-->
						<c:forEach items="${listFuenteFinanciadora}" var="lista">  
	 						<option value="${lista.fuenteFinanciadoraID}" >${lista.institucion.nombreInstitucion}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">Actividades:</td>
				<td align="left" colspan="5">
					<select id="actividadID" name="actividadID" style="width: 200px;" >
<!--						<option value="0">Seleccionar</OPTION>-->
						<c:forEach items="${listActividad}" var="lista">  
	 						<option value="${lista.actividadID}" >${lista.resultado.codigoResultado}.${lista.codigoActividad} - ${lista.nombreActividad}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan='6'> 
					<br>
				</td>
			</tr>
			<tr>
				<td align="center" colspan='6'> 
					<input type="button" value="Consultar Reporte" onClick="showReporte();">
				</td>
			</tr>
		</table>
		<br>
    </fieldset>
</div>
</form>
</body>
</html>