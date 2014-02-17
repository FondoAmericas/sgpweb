<%@ include file="/common/includesTaglibsGenerico.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/tavConteiner.css" />
<script type="text/javascript"
	src="<c:url value="/js/jquery.hashchange.js"></c:url>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.easytabs.js"></c:url>"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#tab-container').easytabs();
		cargaGrillaProblemasSoluciones();
	});
</script>


<script type="text/javascript">

/*function showIngresosPropios(){
	
	//fOpenModalDialog('showIngresosPropios.jspx?liquidacionGastoID=${objLiquidacionGasto.liquidacionGastoID}&proyectoID=${objLiquidacionGasto.datoProyecto.datoProyectoID}','900','1000','70','70');
	$("#grillaIP").load("showIngresosPropios.jspx?liquidacionGastoID=${objLiquidacionGasto.liquidacionGastoID}&proyectoID=${objLiquidacionGasto.datoProyecto.datoProyectoID}");
}*/

function cargaGrillaProblemasSoluciones() {
	$("#grillaPS").load(
			"cargaGrillaProblemasSolucionesEvaluar.jspx",
			{
				informeFinalId : $("#informeFinalId")
				.val(),
				datoProyectoId : $("#datoProyectoId")
						.val()
			});
}

function cargaGrillaEfectoProyecto() {
	$("#grillaEP").load(
			"cargaGrillaEfectoProyectoEvaluar.jspx",
			{
				informeFinalId : $("#informeFinalId")
				.val(),
				datoProyectoId : $("#datoProyectoId")
						.val()
			});
}

function cargaGrillaMaterialProducido() {
	$("#grillaMP").load(
			"cargaGrillaMaterialProducidoEvaluar.jspx",
			{
				informeFinalId : $("#informeFinalId")
				.val(),
				datoProyectoId : $("#datoProyectoId")
						.val()
			});
}

function cargaGrillaEvaluacionFinal(){
	$("#grillaEF").load(
			"cargaGrillaEvaluacionFinalEvaluar.jspx",
			{
				informeFinalId : $("#informeFinalId")
				.val(),
				datoProyectoId : $("#datoProyectoId")
						.val()
			});
}

function cargaGrillaLeccionAprendida(){
	$("#grillaLA").load(
			"cargaGrillaLeccionAprendidaEvaluar.jspx",
			{
				informeFinalId : $("#informeFinalId")
				.val(),
				datoProyectoId : $("#datoProyectoId")
						.val()
			});
}

function cargaGrillaConclucionInformeFinal(){
	$("#grillaCO").load(
			"cargaGrillaConclucionInformeFinalEvaluar.jspx",
			{
				informeFinalId : $("#informeFinalId")
				.val(),
				datoProyectoId : $("#datoProyectoId")
						.val()
			});
}

function cargaGrillaOrganizacion() {
	$("#grillaOR").load("cargaGrillaOrganizacionInformeFinalEvaluar.jspx", {
		informeFinalId : $("#informeFinalId")
		.val(),
		datoProyectoId : $("#datoProyectoId")
				.val()
	});
}

function cargaGrillaBien() {
	$("#grillaBI").load("cargaGrillaBienInformeFinalEvaluar.jspx", {
		informeFinalId : $("#informeFinalId")
		.val(),
		datoProyectoId : $("#datoProyectoId")
				.val()
	});
}


</script>

<script type="text/javascript">
	function agregarObservacion(tablaId,datoProyectoID,tablaClaseId,tablaProfundidadId,claseId){
		var url = "showGestionarObservacion.jspx?tablaId=" + tablaId + "&datoProyectoID="+datoProyectoID+"&tablaClaseId="+tablaClaseId+"&tablaProfundidadId="+tablaProfundidadId+"&claseId="+claseId;
		var stiloPopUp = 'dialogWidth=800px; dialogHeight=500px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
		//ventanaPopUp = showModalDialog(url,'',stiloPopUp);//window.open(url,'',stiloPopUp);

		//window.opener.document.formWindowAsignaTutor.profesorAsignado.value = componenteId;

		//window.showModalDialog(url,datos,stiloPopUp);
		window.showModalDialog(url, "", stiloPopUp);

		}
	
	function grabarEstado(){
		var confirma = confirm("Seguro que desea grabar el cambio de estado del informe final?");
		if(confirma==true){
		$.get("grabarEstadoInformeFinal.jspx", {
			estadoInforme : $("#estadoInformeFinal").val(),
			informeFinalId : $("#informeFinalId").val()
		});	
		}
	}
	
	function cerrarVentana(){
		window.close();
	}
</script>
</head>
<body class="form-clasico">
<div class="encabezado">EVALUAR INFORME FINAL</div>
		<br>
  <fieldset>
  	<legend>Detalle Informe Final</legend>
<div style="text-align: right;">
			<input type="button" value="Observaciones Generales" class="hide"
				onclick="agregarObservacion('<c:out value="${informeFinal.informeFinalID }" ></c:out>','<c:out value="${informeFinal.datoProyecto.datoProyectoID }" ></c:out>',10,49,'<c:out value="${informeFinal.informeFinalID }" ></c:out>');" />
			<input type="button" value="Cerrar"
				onclick="cerrarVentana();" />
			
		</div>
		<br />
	  <table width="100%" border="0">
		  <tr>
		    <td style="width:30%; text-align: right;" >
		        <label>Resultado de Proyecto:</label>
		        <input type="hidden" id="informeFinalId" name="informeFinalId" value="${informeFinal.informeFinalID }">
		        <input type="hidden" id="datoProyectoId" name="datoProyectoId" value="${informeFinal.datoProyecto.datoProyectoID }">
		    </td>
		    <td style="width:70%; text-align: left;" >
				<label>${informeFinal.resultadoProyecto }</label>
		    </td>
		  </tr>
		  <tr>
		  	<td style="width:30%; text-align: right; vertical-align: top;" ><label>Estado:</label></td>
		    <td style="width:75%; text-align: left;" >
		     <c:choose>
							<c:when test="${informeFinal.cantObservacionesRelevantes > 0 }">
							<input type="hidden" name="estadoInformeFinal" id="estadoInformeFinal" value="${informeFinal.fkIdDetalleEstadoCabEstInfFinal}">
							<label><c:out value="${informeFinal.descripcionEstadoInformeFinal }"></c:out></label><br/>
							<label style="color: red;">Informe Final Tiene observaciones relevantes sin atender.</label>
							</c:when>
							<c:otherwise>
		    	<select name="estadoInformeFinal" id="estadoInformeFinal" style="width:auto;" onchange="grabarEstado();">
				    <option value="0"><c:out value="-- Estado --" /></option>
					<c:forEach items="${lstEstadoInforme}"
						var="estadoInforme">
						<option value="${estadoInforme.detalleEstadoCabeceraID}" <c:if test="${estadoInforme.detalleEstadoCabeceraID == informeFinal.fkIdDetalleEstadoCabEstInfFinal }">selected="selected"</c:if> >
						<c:out value="${estadoInforme.descripEstado}"></c:out>
						</option>
					</c:forEach>
				</select>
				</c:otherwise>
                </c:choose>
		    </td>
		  </tr>
		   <tr>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
	    </tr>
		  <tr>
	    	<td colspan="4" align="left">
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
					<div id="grillaPS"></div>
				</div>
				<div id="efectoProyecto">
					<br />
					<div id="grillaEP"></div>
				</div>
				<div id="materialProducido">
					<br />
					<div id="grillaMP"></div>
				</div>
				<div id="evaluacionFinal">
					<br />
					<div id="grillaEF"></div>
				</div>
				<div id="leccionAprendida">
					<br />
					<div id="grillaLA"></div>
				</div>
				<div id="conclusion">
					<br />
					<div id="grillaCO"></div>
				</div>
				<div id="organizacion">
					<br />
					<div id="grillaOR"></div>
				</div>
				<div id="bien">
					<br />
					<div id="grillaBI"></div>
				</div>
			</div>
		</div>
	    	</td>
	    </tr>
	  </table>
	</fieldset>
</body>
</html>