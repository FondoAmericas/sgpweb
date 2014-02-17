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
		cargaGrillaDirectivaBeneficiario();
	});
</script>
<script type="text/javascript">
/*	$(window).load(function() {
		ocultaCampos();
	});
	
	function ocultaCampos(){
		estado= "<c:out value="${estado}"></c:out>" ;
		//alert(estado);
		if ((estado =='apro')|| (estado == 'eval')){
			$(".hide").hide();
			$(".disabled").attr("disabled","disabled");
		}
		
	}*/
</script>

<script type="text/javascript">

/*
function showPersonalTecnicoAdministrativo(){
	
	//fOpenModalDialog('showPersonalTecnicoAdministrativo.jspx?reporteAvanceID=${objReporteAvance.reporteAvanceID}','800','1000','70','70');
	$("#grillaPTA").load("showPersonalTecnicoAdministrativo.jspx?reporteAvanceID=${objReporteAvance.reporteAvanceID}");
}*/

function cargaGrillaDirectivaBeneficiario() {
	$("#grillaDB").load(
			"cargaGrillaDirectivaBeneficiarioEvaluar.jspx",
			{
				propuestaTransferenciaId : $("#propuestaTransferenciaId")
						.val()
			});
}

function cargaGrillaBeneficiarioPropuestaTransferencia() {
	$("#grillaBE").load(
			"cargaGrillaBeneficiarioPropuestaTransferenciaEvaluar.jspx",
			{
				propuestaTransferenciaId : $("#propuestaTransferenciaId")
						.val(),
						datoProyectoId : $("#datoProyectoId").val()		
			});
}

function cargaGrillaOrganizacion() {
	$("#grillaO").load("cargaGrillaOrganizacionEvaluar.jspx", {
		propuestaTransferenciaId : $("#propuestaTransferenciaId")
		.val(),
		datoProyectoId : $("#datoProyectoId").val()
	});
}

function cargaGrillaBien() {
	$("#grillaBI").load("cargaGrillaBienEvaluar.jspx", {
		propuestaTransferenciaId : $("#propuestaTransferenciaId")
		.val(),
		datoProyectoId : $("#datoProyectoId").val()
	});
}

function cargaGrillaBienTransferido() {
	$("#grillaBT").load("cargaGrillaBienTransferidoEvaluar.jspx", {
		datoProyectoId : $("#datoProyectoId").val(),
		propuestaTransferenciaId : $("#propuestaTransferenciaId").val()
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
		var confirma = confirm("Seguro que desea grabar el cambio de estado de la propuesta de transferencia?");
		if(confirma==true){
		$.get("grabarEstadoPropuestaTransferencia.jspx", {
			estadoPropuesta : $("#estadoPropuesta").val(),
			propuestaTransferenciaId : $("#propuestaTransferenciaId").val()
		});
		}
	}
	
	function cerrarVentana(){
		window.close();
	}
</script>
</head>
<body class="form-clasico">
<div class="encabezado">EVALUAR PROPUESTA DE TRANSFERENCIA</div>
		<br>
  <fieldset>
  	<legend>Detalle de Propuesta de Transferencia</legend>
  	<div style="text-align: right;">
			<input type="button" value="Observaciones Generales" class="hide"
				onclick="agregarObservacion('<c:out value="${propuestaTransferencia.propuestaTransferenciaID }" ></c:out>','<c:out value="${propuestaTransferencia.datoProyecto.datoProyectoID }" ></c:out>',11,48,'<c:out value="${propuestaTransferencia.propuestaTransferenciaID }" ></c:out>');" />
			<input type="button" value="Cerrar"
				onclick="cerrarVentana();" />
			
		</div>
		<br />
	  <table width="100%" border="0">
		  <tr>
		    <td style="width: 30%; text-align: right;">
		        <label>Resumen Descripcion de Transferencia:</label>
		    </td>
		    <td style="width: 70%; text-align: left;">
				<label>${propuestaTransferencia.resumenDescripTrans}</label>
				<input type="hidden" id="propuestaTransferenciaId" name="propuestaTransferenciaId" value="${propuestaTransferencia.propuestaTransferenciaID }">
				<input type="hidden" id="datoProyectoId" name="datoProyectoId" value="${propuestaTransferencia.datoProyecto.datoProyectoID }">
		    </td>
		    </tr>
		    <tr>
		    <td style="width: 30%; text-align: right;">
		        <label>Resumen Plan de Transferencia:</label>
		    </td>
		    <td style="width: 70%; text-align: left;">
				<label>${propuestaTransferencia.resumenPlanTrans}</label>
		    </td>
		    </tr>
		    <tr>
		    <td style="width: 30%; text-align: right;vertical-align: top;">
				<label>Estado:</label>
				</td>
		    <td style="width: 70%; text-align: left;">
		    <c:choose>
							<c:when test="${propuestaTransferencia.cantObservacionesRelevantes > 0 }">
							<input type="hidden" name="estadoPropuesta" id="estadoPropuesta" value="${propuestaTransferencia.fkIdDetalleEstadoCabEstInfPropTransfer}">
							<label><c:out value="${propuestaTransferencia.descripcionEstadoPropuesta }"></c:out></label><br/>
							<label style="color: red;">Propuesta de Tranferencia Tiene observaciones relevantes sin atender.</label>
							</c:when>
							<c:otherwise>
		    		        <select name="estadoPropuesta" id="estadoPropuesta" onchange="grabarEstado()" >
				    <option value="0"><c:out value="-- Estado --" /></option>
					<c:forEach items="${lstEstadoPropuesta}"
						var="estadoPropuesta">
						<option value="${estadoPropuesta.detalleEstadoCabeceraID}" <c:if test="${estadoPropuesta.detalleEstadoCabeceraID == propuestaTransferencia.fkIdDetalleEstadoCabEstInfPropTransfer }">selected="selected"</c:if>>
						<c:out value="${estadoPropuesta.descripEstado}"></c:out>
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
					<li class='tab'><a href="#directivaBeneficiario" onclick="cargaGrillaDirectivaBeneficiario()">Directiva
							de Beneficiario</a></li>
					<li class='tab'><a href="#beneficiarios" onclick="cargaGrillaBeneficiarioPropuestaTransferencia()">Beneficiarios</a></li>
					<li class='tab'><a href="#bien" onclick="cargaGrillaBien()">Bienes</a></li>
					<li class='tab'><a href="#organizacion" onclick="cargaGrillaOrganizacion()">Organizaciones</a></li>
					<li class='tab'><a href="#bienTransferidos" onclick="cargaGrillaBienTransferido();">Bienes
							Transferidos</a></li>
					<li class='tab'><a href="#actaCierre" >Acta de Cierre</a></li>		
			</ul>
			<div class='panel-container'>
				<div id="directivaBeneficiario">
					<br />
					<div id="grillaDB"></div>
				</div>
				<div id="beneficiarios">
					<br />
					<div id="grillaBE"></div>
				</div>
				<div id="bien">
					<br />
					<div id="grillaBI"></div>
				</div>
				<div id="organizacion">
					<br />
					<div id="grillaO"></div>
				</div>
				<div id="bienTransferidos">
					<br />
					<div id="grillaBT"></div>
				</div>
				<div id="actaCierre">
					<br />
					<label>Acta Escaneada: </label>
					<iframe src="showImagenArchivoDownloadPropuestaTransferencia.jspx?propuestaTransferenciaId=${propuestaTransferencia.propuestaTransferenciaID }"
											id="window" frameborder="0" height="40px" width="100%"
											scrolling="no"></iframe>
				</div>
			</div>
		</div>
	    	</td>
	    </tr>
	  </table>
	</fieldset>
</body>
</html>