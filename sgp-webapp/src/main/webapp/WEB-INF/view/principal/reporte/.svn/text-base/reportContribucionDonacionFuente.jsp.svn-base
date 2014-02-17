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
			var selectedIndex1 = document.reportContribucionDonacion.fuenteFinanciadoraID.selectedIndex;
		    var optionValue1 = document.reportContribucionDonacion.fuenteFinanciadoraID.options[selectedIndex1].value;
		    var fuenteFinanciadoraID = optionValue1;
			var url = "reportPlanOperativoContribucionDonacion.jspx?fuenteFinanciadoraID=" + fuenteFinanciadoraID;
			openWindow(url);
		}
		function openWindow(url) {
			window.open(url,'','width=800,height=600,left=0,top=100,screenX=0,screenY=100');
		}
	</script>
</head>
<body style="font-family: Arial; font-size: smaller;">
<form name="reportContribucionDonacion">
<div class="form-clasico">
    <fieldset><legend>Reporte Contribucion de la Donacion por Fuente</legend>
		<br>
		<table border="0">
			<tr>
				<td align="right">Fuente Financiamiento:</td>
				<td align="left" colspan="5">
					<select id="fuenteFinanciadoraID" name="fuenteFinanciadoraID" style="width: 200px;" >
						<c:forEach items="${listFuenteFinanciadora}" var="lista">  
	 						<option value="${lista.fuenteFinanciadoraID}" >${lista.institucion.nombreInstitucion}</option>
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