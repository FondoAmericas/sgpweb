<%@ include file="/common/includesTaglibsGenerico.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EVALUAR DE REPORTE AVANCE</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/tavConteiner.css" />
<script type="text/javascript"
	src="<c:url value="/js/jquery.hashchange.js"></c:url>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.easytabs.js"></c:url>"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#tab-container').easytabs();
		showProblemasSoluciones();
		
		var resultadoGrabacion="<c:out value="${resultadoGrabacion }"></c:out>";
		
		var estReporteAvancePrefijo="<c:out value="${estReporteAvancePrefijo }"></c:out>";
		
		if(resultadoGrabacion==1){
			cerrarVentana();
		}
		
		if(estReporteAvancePrefijo == 'apro'){
			$(".hide").hide();
		}else if(estReporteAvancePrefijo == 'repgen'){
			$("#btnGuardar").hide();
		}
	});
</script>

<script type="text/javascript">

function showProblemasSoluciones(){
	
	//fOpenModalDialog('showProblemasSoluciones.jspx?reporteAvanceID=${objReporteAvance.reporteAvanceID}','800','1000','70','70');
	$("#grillaPS").load("showProblemasSoluciones.jspx?reporteAvanceID=${objReporteAvance.reporteAvanceID}&estReporteAvancePrefijo=${estReporteAvancePrefijo }");
	
}

function showAvanceResultadosActividad(){
	
	//fOpenModalDialog('showAvanceResultadosActividad.jspx?reporteAvanceID=${objReporteAvance.reporteAvanceID}','800','1000','70','70');
	$("#grillaARA").load("showAvanceResultadosActividad.jspx?reporteAvanceID=${objReporteAvance.reporteAvanceID}&estReporteAvancePrefijo=${estReporteAvancePrefijo }"); 
}

function showApreciacionResultados(){
	
	//fOpenModalDialog('showApreciacionResultados.jspx?reporteAvanceID=${objReporteAvance.reporteAvanceID}','800','1000','70','70');
	$("#grillaAR").load("showApreciacionResultados.jspx?reporteAvanceID=${objReporteAvance.reporteAvanceID}&estReporteAvancePrefijo=${estReporteAvancePrefijo }"); 
}

function showBeneficiarios(){
	
	//fOpenModalDialog('showBeneficiarios.jspx?reporteAvanceID=${objReporteAvance.reporteAvanceID}','800','1000','70','70');
	$("#grillaB").load("showBeneficiarios.jspx?reporteAvanceID=${objReporteAvance.reporteAvanceID}&estReporteAvancePrefijo=${estReporteAvancePrefijo }");
}

function showPersonalTecnicoAdministrativo(){
	
	//fOpenModalDialog('showPersonalTecnicoAdministrativo.jspx?reporteAvanceID=${objReporteAvance.reporteAvanceID}','800','1000','70','70');
	$("#grillaPTA").load("showPersonalTecnicoAdministrativo.jspx?reporteAvanceID=${objReporteAvance.reporteAvanceID}&estReporteAvancePrefijo=${estReporteAvancePrefijo }");
}

function grabarEstado(){
	if(document.getElementById("cbxEstadoRepAvance").value != "0"){
		if(confirm("\u00BFEst\u00E1 seguro de la evaluacion?")){
			document.getElementById("detalleReporteAvanceForm").submit();
	    	//window.close();
		}
	}else{
		alert("Seleccionar estado para guardar la evaluacion!");
	}
	
}


function deshabilitarGuardarSiEstadoAprobado(){
	if(document.getElementById("fkIdDetalleEstadoCabEstRepAvance").value == "37"){//estado aprobado
		document.getElementById("cbxEstadoRepAvance").disabled = true;
		document.getElementById("btnGuardar").disabled = true;
	}
}

$(document).ready(function() {
	deshabilitarGuardarSiEstadoAprobado();
	
});


$(window).load(function() {
	//alert("atssef");
	var estReporteAvanceCod = '${estReporteAvanceCod}';
	document.getElementById("cbxEstadoRepAvance").value = estReporteAvanceCod;
	
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

<form id="detalleReporteAvanceForm" method="post" action="saveEvalReporteAvance.jspx" class="form-clasico" accept-charset="UTF-8">
<input type="hidden" value="${objReporteAvance.fkIdDetalleEstadoCabEstRepAvance}" id="fkIdDetalleEstadoCabEstRepAvance"> 
<div class="encabezado">EVALUAR REPORTE AVANCE</div>
		<br>
  <fieldset>
  	<legend>Detalle ${reporteAvanceDesc}</legend>
  	<div style="text-align: right;">
			<input type="button" value="Observaciones Generales" class="hide"
				onclick="agregarObservacion('<c:out value="${objReporteAvance.reporteAvanceID }" ></c:out>','<c:out value="${objReporteAvance.datoProyecto.datoProyectoID }" ></c:out>',8,19,'<c:out value="${objReporteAvance.reporteAvanceID }" ></c:out>');" />
			<input type="button" value="Cerrar"
				onclick="cerrarVentana();" />
			
		</div>
		<br />
	  <table width="100%" border="0">
		  <tr>
		    <td style="text-align: right; width: 25%;">
		        <label>Periodo:</label>
		    </td>
		    <td style="text-align: left; width: 25%;">
				<label>${objReporteAvance.periodo}</label>
		    </td>
		    <td style="text-align: right; width: 25%;vertical-align: top;">
		       <label>Estado:</label>
		    </td>
		    <td style="text-align: left; width: 25%;">
		    <c:choose>
							<c:when test="${objReporteAvance.cantObservacionesRelevantes > 0 }">
							<input type="hidden"  name="cbxEstadoRepAvance" id="cbxEstadoRepAvance" value="${objReporteAvance.fkIdDetalleEstadoCabEstRepAvance }">
							<label><c:out value="${objReporteAvance.estRepAvanceDesc }"></c:out></label><br/>
							<label style="color: red;">Reporte de Avance Tiene observaciones relevantes sin atender.</label>
							</c:when>
							<c:when test="${estReporteAvancePrefijo=='repgen' }">
								<label><c:out value="${objReporteAvance.estRepAvanceDesc }"></c:out></label>
							</c:when>
							<c:otherwise>
				<select name="cbxEstadoRepAvance" id="cbxEstadoRepAvance" >
				    <option value="0"><c:out value="-- Estado --" /></option>
					<c:forEach items="${lstDetEstCab}"
						var="objDetEstCab">
						<option value="${objDetEstCab.detalleEstadoCabeceraID}" <c:if test="${objDetEstCab.detalleEstadoCabeceraID == objReporteAvance.fkIdDetalleEstadoCabEstRepAvance}">selected="selected"</c:if>>
						<c:out value="${objDetEstCab.descripEstado}"></c:out>
						</option>
					</c:forEach>
				</select>
                </c:otherwise>
                </c:choose>
		    </td>
		  </tr> 
		  <tr> 
		    <td style="text-align: right; width: 25%;"><label>Fecha de Inicio:</label></td>
		    <td style="text-align: left; width: 25%;"><label><c:out value="${objReporteAvance.fechaInicioString }"></c:out> (dd/MM/aaaa)</label>
		    </td>
		    <td style="text-align: right; width: 25%;"><label>Fecha de Fin:</label></td>
		    <td style="text-align: left; width: 25%;"><label><c:out value="${objReporteAvance.fechaFinString }"></c:out> (dd/MM/aaaa)</label>
		    </td>
	    </tr>
		  <tr>
		    <td style="text-align: right; width: 25%;"><label>Resumen:</label></td>
		    <td colspan="3" style="text-align: left; width: 75%;">
		          <label>${objReporteAvance.resumen}</label>
		    </td>
		  </tr>
		  <tr> 
		    <td colspan="3" style="text-align: right; width: 75%;"></td>
		    <td style="text-align: right; width: 25%;">
		         <input type="button" class="hide" value="Guardar" onclick="grabarEstado()" id="btnGuardar"/>
		    </td>
	    </tr>
		</table>
		<br/>
		<table width="100%">
	    <tr>
	    	<td colspan="4" align="left">
	    	<div id="tab-container" class="tab-container" style="width: 100%;">
			<ul class='etabs'>
				<li class='tab'><a href="#problemasSoluciones" onclick="showProblemasSoluciones();">Problemas <br/>&amp; Soluciones</a></li>
				<li class='tab'><a href="#avanceResultadoActividad" onclick="showAvanceResultadosActividad();">Avance de Resultados <br/>por Actividad</a></li>
				<li class='tab'><a href="#apreciacionResultados" onclick="showApreciacionResultados();">Apreciacion de<br/> Resultados</a></li>
				<li class='tab'><a href="#beneficiarios" onclick="showBeneficiarios();">Beneficiarios<br/>Por Resultado</a></li>
				<li class='tab'><a href="#personalTecnicoAdministrativo" onclick="showPersonalTecnicoAdministrativo()">Personal Tecnico <br/>Administrativo</a></li>
			</ul>
			<div class='panel-container'>
				<div id="problemasSoluciones">
					<br />
					<div id="grillaPS"></div>
				</div>
				<div id="avanceResultadoActividad">
					<br />
					<div id="grillaARA"></div>
				</div>
				<div id="apreciacionResultados">
					<br />
					<div id="grillaAR"></div>
				</div>
				<div id="beneficiarios">
					<br />
					<div id="grillaB"></div>
				</div>
				<div id="personalTecnicoAdministrativo">
					<br />
					<div id="grillaPTA"></div>
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