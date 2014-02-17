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
		showIngresosPropios();
		deshabilitarGuardarSiEstadoAprobado();
		
		var resultadoGrabacion="<c:out value="${resultadoGrabacion }"></c:out>";
		
		var estLiquidacionGastoPrefijo="<c:out value="${estLiquidacionGastoPrefijo }"></c:out>";
		
		if(resultadoGrabacion==1){
			cerrarVentana();
		}
		
		if(estLiquidacionGastoPrefijo == 'apro'){
			$(".hide").hide();
		}else if(estLiquidacionGastoPrefijo == 'liqgen'){
			$("#btnGuardar").hide();
		}
		
	});

</script>

<script type="text/javascript">

function showIngresosPropios(){
	
	//fOpenModalDialog('showIngresosPropios.jspx?liquidacionGastoID=${objLiquidacionGasto.liquidacionGastoID}&proyectoID=${objLiquidacionGasto.datoProyecto.datoProyectoID}','900','1000','70','70');
	$("#grillaIP").load("showIngresosPropios.jspx?liquidacionGastoID=${objLiquidacionGasto.liquidacionGastoID}&proyectoID=${objLiquidacionGasto.datoProyecto.datoProyectoID}&estLiquidacionGastoPrefijo=${estLiquidacionGastoPrefijo }");
}

function showCompromisosPago(){
	
	//fOpenModalDialog('showCompromisosPago.jspx?liquidacionGastoID=${objLiquidacionGasto.liquidacionGastoID}','800','1000','70','70');
	$("#grillaCP").load("showCompromisosPago.jspx?liquidacionGastoID=${objLiquidacionGasto.liquidacionGastoID}&estLiquidacionGastoPrefijo=${estLiquidacionGastoPrefijo }");
}

function showPagosRealizados(){
	
	//fOpenModalDialog('showPagosRealizados.jspx?liquidacionGastoID=${objLiquidacionGasto.liquidacionGastoID}','800','1000','70','70');
	$("#grillaPR").load("showPagosRealizados.jspx?liquidacionGastoID=${objLiquidacionGasto.liquidacionGastoID}&estLiquidacionGastoPrefijo=${estLiquidacionGastoPrefijo }");
}

function showFormulariosLlenos(){
	
	//fOpenModalDialog('showPagosRealizados.jspx?liquidacionGastoID=${objLiquidacionGasto.liquidacionGastoID}','800','1000','70','70');
	$("#grillaFL").load("showFormulariosLlenos.jspx?liquidacionGastoID=${objLiquidacionGasto.liquidacionGastoID}&estLiquidacionGastoPrefijo=${estLiquidacionGastoPrefijo }");
}

function grabarEstado(){
	if(document.getElementById("cbxEstadoLiqGasto").value != "0"){
		if(confirm("\u00BFEst\u00E1 seguro de la evaluacion?")){
			document.getElementById("detalleLiqGastosForm").submit();
	    	//window.close();				
		}
	}else{
		alert("Seleccionar estado para guardar la evaluacion!");
	}
	
}

function deshabilitarGuardarSiEstadoAprobado(){
	if(document.getElementById("fkIdDetalleEstadoCabEstLiqGasto").value == "53"){//liquidacion aprobada
		document.getElementById("cbxEstadoLiqGasto").disabled = true;
		document.getElementById("btnGuardar").disabled = true;
	}
}


$(window).load(function() {
	//alert("atssef");
	var estadoLiqCod = '${estadoLiqCod}';
	document.getElementById("cbxEstadoLiqGasto").value = estadoLiqCod;
	
});


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
	
	function cerrarVentana(){
		window.close();
	}
</script>

</head>
<body>

<form id="detalleLiqGastosForm" method="post" action="saveEvalLiquidacionGasto.jspx" class="form-clasico" accept-charset="UTF-8">
<input type="hidden" value="${objLiquidacionGasto.fkIdDetalleEstadoCabEstLiqGasto}" id="fkIdDetalleEstadoCabEstLiqGasto"> 
<div class="encabezado">DETALLE LIQUIDACION GASTOS</div>
		<br>
  <fieldset>
  	<legend>DETALLE LIQUIDACION GASTOS</legend>

<div style="text-align: right;">
			<input type="button" value="Observaciones Generales" class="hide"
				onclick="agregarObservacion('<c:out value="${objLiquidacionGasto.liquidacionGastoID }" ></c:out>','<c:out value="${objLiquidacionGasto.datoProyecto.datoProyectoID }" ></c:out>',6,25,'<c:out value="${objLiquidacionGasto.liquidacionGastoID }" ></c:out>');" />
			<input type="button" value="Cerrar"
				onclick="cerrarVentana();" />
			
		</div>
		<br />
	  <table width="100%" border="0">
		  <tr>
		    <td style="width:25%; text-align: right;" >
		        <label>Codigo de version:</label>
		    </td>
		    <td colspan="3" style="width:75%; text-align: left;" >
				<label>${objLiquidacionGasto.codVersion}</label>
		    </td>
		  </tr>
		  <tr>
		    <td style="width:25%; text-align: right;" >
		    	<label>Periodo:</label>
		    </td>
		    <td style="width:25%; text-align: left;" >
				<label>${objLiquidacionGasto.periodo}</label>
		    </td>
		    <td style="width:25%; text-align: right;" >
		    	<label>Fuente Financiadora:</label>
		    </td>
		    <td style="width:25%; text-align: left;" >
				<label>${objLiquidacionGasto.fuenteFinanciadora.institucion.nombreInstitucion}</label>
		    </td>
		  </tr>
		  <tr>
		    <td style="width:25%; text-align: right;" >
		    	<label>Fecha inicio:</label>
		    </td>
		    <td style="width:25%; text-align: left;" >
				<label>${objLiquidacionGasto.fechaInicioString} (dd/MM/aaaa)</label>
		    </td>
		    <td style="width:25%; text-align: right;" >
		    	<label>Fecha fin:</label>
		    </td>
		    <td style="width:25%; text-align: left;" >
				<label>${objLiquidacionGasto.fechaFinString} (dd/MM/aaaa)</label>
		    </td>
		  </tr>
		  <tr>
		    <td style="width:25%; text-align: right;" >
		    	<label>Observaciones:</label>
		    </td>
		    <td colspan="3" style="width:75%; text-align: left;" >
				<label>${objLiquidacionGasto.observacion}</label>
		    </td>
		  </tr>
		  <tr>
		  	<td style="width:25%; text-align: right;vertical-align: top;" ><label>Estado:</label></td>
		    <td style="width:25%; text-align: left;" >
		     <c:choose>
							<c:when test="${objLiquidacionGasto.cantObservacionesRelevantes > 0 }">
							<input type="hidden" name="cbxEstadoLiqGasto" id="cbxEstadoLiqGasto" value="${objReporteAvance.fkIdDetalleEstadoCabEstRepAvance}">
							<label><c:out value="${objLiquidacionGasto.estLiqGastoDesc }"></c:out></label><br/>
							<label style="color: red;">Liquidacion de Gastos Tiene observaciones relevantes sin atender.</label>
							</c:when>
							<c:when test="${estLiquidacionGastoPrefijo=='liqgen' }">
								<label><c:out value="${objLiquidacionGasto.estLiqGastoDesc }"></c:out></label>
							</c:when>
							<c:otherwise>
		    	<select name="cbxEstadoLiqGasto" id="cbxEstadoLiqGasto" style="width:100%">
				    <option value="0"><c:out value="-- Estado --" /></option>
					<c:forEach items="${lstDetEstCab}"
						var="objDetEstCab">
						<option value="${objDetEstCab.detalleEstadoCabeceraID}" <c:if test="${objDetEstCab.detalleEstadoCabeceraID == objLiquidacionGasto.fkIdDetalleEstadoCabEstLiqGasto}">selected="selected"</c:if>>
						<c:out value="${objDetEstCab.descripEstado}"></c:out>
						</option>
					</c:forEach>
				</select>
				 </c:otherwise>
                </c:choose>
		    </td>
		    <td colspan="2" style="width:50%; text-align: right;" >
				<input type="button"  class="hide" value="Guardar" onclick="grabarEstado()" id="btnGuardar"/>
		    </td>
		  </tr>
		  <tr>
	    	<td colspan="4" align="left">
	    	<div id="tab-container" class="tab-container" style="width: 100%;">
			<ul class='etabs'>
				<li class='tab'><a href="#ingresosPropios" onclick="showIngresosPropios();">Ingresos<br/>Propios</a></li>
				<li class='tab'><a href="#compromisoPago" onclick="showCompromisosPago();">Compromisos<br/> de Pago</a></li>
				<li class='tab'><a href="#pagosRealizados" onclick="showPagosRealizados();">Pagos<br/> Realizados</a></li>
				<li class='tab'><a href="#formulariosLlenos" onclick="showFormulariosLlenos();">Formularios<br/> Llenos</a></li>
			</ul>
			<div class='panel-container'>
				<div id="ingresosPropios">
					<br />
					<div id="grillaIP"></div>
				</div>
				<div id="compromisoPago">
					<br />
					<div id="grillaCP"></div>
				</div>
				<div id="pagosRealizados">
					<br />
					<div id="grillaPR"></div>
				</div>
				<div id="formulariosLlenos">
					<br />
					<div id="grillaFL"></div>
				</div>
			</div>
		</div>
	    	</td>
	    </tr>
	  </table>
	</fieldset>
</form>
</body>
</html>