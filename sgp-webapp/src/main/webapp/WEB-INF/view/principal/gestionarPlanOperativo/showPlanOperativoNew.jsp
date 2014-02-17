<%@include file="includesTaglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message code="web.app.title"></spring:message></title>
	
	<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/estiloContabilidad.css"></c:url>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/estiloGeneral.css"></c:url>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/estiloObservaciones.css"></c:url>" />	
	
<style type="text/css">
#menuReporte {
	background-repeat: repeat-x;
	margin: auto;
	padding-left: 1px;
	padding-top: 1px;
	font-size: 12px;
}

#navReporte {
	list-style: none;
}

#navReporte li {
	border: thin groove #1f5f20;
	font-weight: bold;
	color: #1f5f20;
	/*font-style: italic;*/
	background-color:#ffffff;
	float: left;
	/*background-image: url(nav_li_bg.png);*/
	background-repeat: no-repeat;
	background-position: right;
}

#navReporte li a {
	display: block;
	padding: 7px 10px;
	text-decoration: none;
	color: #1f5f20;
	font-weight: bold;
}

#navReporte li a:hover {
	color: #1f5f20;
	text-decoration: underline;
}
/* Submenu */
#navReporte ul.submenuReporte { /*border: 0px solid #1f5f20;*/
	padding: 5px;
	position: absolute;
	list-style: none;
	/*background-color: #333333;*/
}

#navReporte ul.submenuReporte li {
	float: none;
	width: auto;
	/*background-image: none;
	border-bottom: 0px solid #999999;
	width: 200px;*/
}
</style>

<script type="text/javascript">
$(document).ready(function() {
	mainmenu();
	
	$("#div_carga").hide().ajaxStart(function() {
		$(this).show();
	}).ajaxStop(function() {
		$(this).hide();
	});
});

	$(window).load(function() {
		////UBICACION DEL PROYECTO
		ocultaCampos();
		ocultaDetalleSolicitud();
		
		var f = new Date();
		var dia;
		if (f.getDate()<10){
			dia="0"+f.getDate();
		}else{
			dia=f.getDate();
		}
		
		$("#fechaSolicitud").attr("value",dia + "/" + (f.getMonth() +1) + "/" + f.getFullYear());
			
	});
	
	function mainmenu() {
		// Oculto los submenus
		$(" #navReporte ul ").css({
			display : "none"
		});
		// Defino que submenus deben estar visibles cuando se pasa el mouse por encima
		$(" #navReporte li").hover(function() {
			$(this).find('ul:first:hidden').css({
				visibility : "visible",
				display : "none"
			}).slideDown(400);
		}, function() {
			$(this).find('ul:first').slideUp(400);
		});
	}
	
	function ocultaCampos(){
		estado= "<c:out value="${estadoPlanOperativo}"></c:out>" ;
		cantDesembolso="<c:out value="${cantDesembolso}"></c:out>" ;
		//alert(estado);
		if ((estado =='peval')||(estado =='revi')||(estado =='apro')||(estado =='recha')){
			$(".hide").hide();//attr("style","visibility: hidden;");
			$(".disabled").attr("disabled","disabled");
			//alert($('#cbxInstitucionEjecutora option:selected').html());
			//$("#div_cbxInstitucionEjecutora").attr("style","visibility: hidden;");
		}
		
		if ((estado=='apro')&&(cantDesembolso > 0)){
			$(".muestraSolicitud").show("fast");
		}else{
			$(".muestraSolicitud").hide("fast");
		}
	}
	
	function ocultaDetalleSolicitud(){
		//var check=0;
		if ($("#solicitarRpRf").is(":checked")) {
			$(".muestraDetalleSolicitud").show("fast");
		}else{
			$(".muestraDetalleSolicitud").hide("fast");
		}		
	}
	</script>
	
	<script type="text/javascript">
	function openWindow(url){
		window.open(url,'','width=900,height=850,left=0,top=50,screenX=0,screenY=50');
	}
	/*function goTo(url) {
		window.location = url;
	}*/
	
		function goTo(url,ancho,alto) {
			//var flag;
			/*if($('#listaTipoCambioSize').attr("value") != ""){
				alert("Debe Ingresar Tipo de Cambio.");
				return;
			}*/
			fOpenModalDialog(url,ancho,alto,'70','70');
		}
		
		function goTo2(url) {
			if (validar()) {
				return;
			}
			if(confirm("\u00BFEst\u00E1 seguro de crear el plan operativo?")){
			   url = url + "&tipoMonedaId=" +$("#tipoMoneda").attr("value");
			   window.location = url;
			}
		}
		
		function validar(){
		      var cabecera = "Debe ingresar la siguiente informaci\u00F3n obligatoria(*):";
		      var mensaje = "";
		      if($('#tipoMoneda').attr("value") == "0"){
		          mensaje += "\n - Tipo Moneda.";
		      }	
			  
		      if(mensaje != ""){
		         alert(cabecera + mensaje);
		         return true;
		      }else{
		         return false;
		      }
		   
		}
		
		function fRefresh(tiempo){
			fSetTimeOutRefreshLocation(tiempo);
		}
		
		function fEliminar(resultadoID, resultadoCod){
			if(confirm("\u00BFEst\u00E1 seguro de eliminar el resultado " + resultadoCod + "?")){
			   window.location.href = "actionDeleteResultado.jspx?resultadoID=" + resultadoID;
			}			
		}
		
		function fEliminarTipoCambio(tipoCambioID){
			if(confirm("\u00BFEst\u00E1 seguro de eliminar el tipo de Cambio ?")){
				window.location.href = "actionDeleteTipoCambio.jspx?tipoCambioID=" + tipoCambioID;
			}
		}
		
		function load(){
			var mensaje = $('#mensaje').attr("value");
			if(mensaje != "")
			   alert(mensaje);
			   $('#mensaje').val("");
			  
		}
		
		function fLocationInterval(tiempo){
		   window.setInterval("fLocation()",tiempo);
		}
		
		function fLocation(){
			window.location.href = "showPlanOperativoNew.jspx";
		} 
		
		function cambiaEstadoPlanOperativo(){
			if($("#sltEstadoPlanOperativo").val()!=0){
			var confirmacion = confirm("Esta seguro que desea mandar el PLAN OPERATIVO para ser evaluado por Fondam. ");
			
			if (confirmacion){
				document.frmPlanOperativo.action = "cambiaEstadoValidaInformacion.jspx";
				document.frmPlanOperativo.submit();
			}
			}else if($("#sltEstadoPlanOperativo").val()==0){
				alert("Seleccionar el estado a grabar.");
				$("#sltEstadoPlanOperativo").attr("value",$("#estadoPlanOperativoId").val());
			}
		}
		
		function grabarSolicitud(){
			/*var error= validaCajas();
			if(error==0){*/
			var confirma = confirm("Seguro que desea solicitar una reprogramacion o reformulacion del plan operativo?");
			if(confirma==true){
			$.get("grabarSolicitudRpRf.jspx", {
						estadoSolicitudRpRf : $("#sltEstadoSolicitud").val(),
						versionPo : $("#versionPlanOperativo").val(), 
						observacionDeSolicitud : $("#observacionRpRf").val(),
						datoProyectoId : $("#datoProyectoID").val(),
						fechaSolicitud : $("#fechaSolicitud").val()
					}, function() {
						$("#solicitarRpRf").attr("disabled","disabled");
						$("#observacionRpRf").attr("disabled","disabled"); 
						$("#btnGrabarSolicitud").hide("fast");
					});	
			}
		};
	</script>

</head>
<body onload="load()">
<form id="frmPlanOperativo" name="frmPlanOperativo" class="form-clasico">
<input type="hidden" id="mensaje" name="mensaje" value="${mensaje}" />
<!-- <input type="hidden" id="listaTipoCambioSize" name="listaTipoCambioSize" value="<c:if test="${empty listaTipoCambio}">0</c:if>" /> -->
<div id="div_carga">
		<img id="cargador" src="<c:url value="/images/ajax-loaderB.gif"/>"
			width="120px" height="120px" />
	</div>
	
<div class="encabezado">GESTIONAR PLAN OPERATIVO</div>
<br>
<div>
<fieldset style="padding-bottom: 7px;"><legend>Gestion Plan Operativo</legend>
	<c:if test="${!empty alterMessage}">
		<fieldset><legend>Mensajes de Alertas</legend>
			<label for="alterMessage">${alterMessage}</label> 
		</fieldset>
	</c:if>
	<c:if test="${empty alterMessage}">
		<c:if test="${empty planOperativo}">
			<label>Tipo moneda con que se creara el Plan Operativo:   </label>
			<select id="tipoMoneda" name="tipoMoneda" style="width: 100px;" disabled="disabled">
				<option value="0">--Moneda--</option>
				<c:forEach items="${listaTipoMoneda}" var="tipoMoneda" >  
				<c:choose>
									<c:when test="${tipoMoneda.tablaEspecificaID ==169}">
										<option selected="selected" value="${tipoMoneda.tablaEspecificaID}" >
										<c:out value="${tipoMoneda.descripcionCabecera }" />
									</option>
									</c:when>
									<c:otherwise>
										<option value="${tipoMoneda.tablaEspecificaID}">
										<c:out value="${tipoMoneda.descripcionCabecera }" />
									</option>
									</c:otherwise>
								</c:choose>  
				</c:forEach>
			</select>
			<br><br>
			<input type="button" name="create" value="Crear Plan Operativo" onClick="goTo2('createPlanOperativo.jspx?datoProyectoID=${datoProyectoID}');">
		</c:if>
	</c:if>
	
	<c:if test="${!empty planOperativo}">
		
<!-- 			<br> -->
			<table style="border: 0; width: 100%">
				<tr>
					<td colspan="4" style="text-align: left; width: 100%">
					<div id="menuReporte" style="background-color: white;">
		<ul id="navReporte">
			<li><a href="#">Seleccionar Reporte</a>
				<ul class="submenuReporte">
					<li><a href="javascript:openWindow('reportPlanOperativoResultados.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID }')">Plan Operativo - Resultados</a></li>
					<li><a href="javascript:openWindow('reportPlanOperativoCronogramaActividades.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID }')">Plan Operativo - Cronograma fisico de Actividades</a></li>
					<li><a href="javascript:openWindow('reportPlanOperativoCostoActividadFuente.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID }')">Plan Operativo - Costo Actividad por Fuente</a></li>
					<li><a href="javascript:openWindow('reportPlanOperativoEstructuraInversionFinanciamiento.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID }')">Plan Operativo - Estructura de Inversion y Financiamiento</a></li>
					<!-- <li><a href="#">Plan Operativo - Contribucion de la Donacion por Fuente</a></li>-->
					<li><a href="javascript:openWindow('reportPlanOperativoBeneficiario.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID }')">Plan Operativo - Beneficiarios en el Area de Intervencion(Directos)</a></li>
					<li><a href="javascript:openWindow('reportPlanOperativoCronogramaResultados.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID }')">Plan Operativo - Operaciones y Costos(Cronograma de Resultados)</a></li>
					<!-- <li><a href="#">Plan Operativo - ContraPartida</a></li>-->
					<li><a href="javascript:openWindow('reportPlanOperativoCostoActividadFuenteMontos.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID }')">Plan Operativo - Desemboloso de Recursos de Donacion</a></li>
					
				</ul>
			</li>
		</ul>
	</div>
					</td>
				</tr>
				<tr>
					<td style="text-align: right; width: 20%; vertical-align: top">
					    <label>Proyecto:</label>
					</td>
					<td style="text-align: left; width: 80%; vertical-align: top;" colspan="2" >
					    <label>${planOperativo.nombreProyecto}</label>
					    <input type="hidden" id="datoProyectoID" name="datoProyectoID" value="${datoProyectoID}">
					    <input type="hidden" id="datoPlanOperativoId" name="datoPlanOperativoId" value="${planOperativo.datoPlanOperativoID}">
					    <input type="hidden" id="versionPlanOperativo" name="versionPlanOperativo" value="${planOperativo.version}">
					</td>
					
				</tr>
				<tr>
					<td style="text-align: right; width: 20%; vertical-align: top">
					    <label>Codigo Proyecto:</label> 
					</td>
					<td style="text-align: left; width: 30%; vertical-align: top">
					    <label>${planOperativo.codigoProyecto}</label>
					</td>
					<!-- <td style="text-align: left; width: 45%;" rowspan="3" valign="top">
					
						<div id="tipoCambio" style="width: 100%;text-align: right;">
							<table class="table-clasico" style="width: 100%;">
								<caption style="text-align: center;">
								<div style="float: left;">
								<label>Lista Tipo Cambio</label></div>
								<div style="float: right;">
								<a style="font-size: 13px;" href="javascript:fOpenModalDialog('createTipoCambio.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}','610','185','70','70');"
											id="nuevoTipoCambio" class="linkSelecciona" >Nuevo Tipo Cambio</a>
									</div>
								</caption>
								<thead>
								<tr style="width: 20%; text-align: center;">
									<td><label>Tipo Cambio</label></td>
									<td><label>Fecha</label></td>
									<td><label>De</label></td>
									<td><label>A</label></td>
									<td><label>Operaciones</label></td>
								</tr>
								</thead>
								<c:forEach var="tipoCambio" items="${listaTipoCambio}" varStatus="indice">
			<c:choose>
				<c:when test="${indice.count %2== 0}">
					<c:set var="classIdi" value="f1"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="classIdi" value="f2"></c:set>
				</c:otherwise>
			</c:choose>
			<tr class="<c:out value="${classIdi}"></c:out>">
									<td><label>${tipoCambio.tipoCambio}</label></td>
									<td><label><fmt:formatDate value="${tipoCambio.fechaTipoCambio}" pattern="dd/MM/yyyy" /></label></td>
									<td><label>${tipoCambio.tipoMonedaDENombre}</label></td>
									<td><label>${tipoCambio.tipoMonedaANombre}</label></td>
									<td align="center">
						            <a href="#" onclick="fEliminarTipoCambio(${tipoCambio.tipoCambioID})" > Eliminar</a>
						            </td>
								</tr>
								</c:forEach>
							</table>
						</div>
                    </td>    
				</tr>
				<tr>-->
					<td style="text-align: right; width: 20%; vertical-align: top">
					    <label>Version PO:</label>
					</td>
					<td style="text-align: left; width: 30%; vertical-align: top">
					    <label>${planOperativo.version}</label>
					</td>
				</tr>	
				<tr>
					<td style="text-align: right; width: 20%; vertical-align: top">
					    <label>Moneda:</label>
					</td>
					<td style="text-align: left; width: 30%; vertical-align: top">
					    <label>${planOperativo.tipoMonedaNombre}</label>
					</td>
				<!-- </tr>
				<tr> -->
					<td style="text-align: right; width: 20%; vertical-align: top">
					    <label>Estado:</label>
					</td>
					<td style="text-align: left; width: 30%; vertical-align: top">
					    <select name="sltEstadoPlanOperativo" id="sltEstadoPlanOperativo" onchange="cambiaEstadoPlanOperativo()" style="width: 100%;" class="disabled">
				<option value="0">
					<c:out value="-- Estado --" />
				</option>
				<c:forEach items="${listEstadoPlanOperativo}"
					var="estadoPlanOperativo">
							<option <c:if test="${estadoPlanOperativo.detalleEstadoCabeceraID == planOperativo.estadoPlanOperativo }">selected="selected"</c:if> 
								value="${estadoPlanOperativo.detalleEstadoCabeceraID}">
								<c:out value="${estadoPlanOperativo.descripEstado }" />
							</option>
				</c:forEach>
		</select><input type="hidden" id="estadoPlanOperativoId" name="estadoPlanOperativoId" value="${planOperativo.estadoPlanOperativo }">
					</td>
				</tr>	
				<tr>
					<td style="text-align: right; width: 20%; vertical-align: top">
					    <label>Tipo de Periodo:</label>
					</td>
					<td colspan="3" style="text-align: left; width: 80%; vertical-align: top">
					    <label>${planOperativo.datoProyecto.programa.tipoPeriodo.descripPeriodo }</label>
					</td>
					
				</tr>
			</table>
			<br/>
				<input type="button" value="Marco Logico" onClick="fOpenModalDialog('createMarcoLogicoIndicadores.jspx?datoProyectoID=${datoProyectoID}&estadoPlanOperativo=${estadoPlanOperativo}','875','750','70','70');">
                <input type="button" class="hide" value="Crear Resultado" onClick="fOpenModalDialog('createResultado.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}','730','400','70','70');">
                <input type="button" value="Recursos del Ejecutor" onClick="fOpenModalDialog('createInfraestructura.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&estadoPlanOperativo=${estadoPlanOperativo}','850','600','70','70');">
                <input type="button" value="Personal Administrativo" onClick="fOpenModalDialog('createPersonalTecnicoAdministrativo.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&estadoPlanOperativo=${estadoPlanOperativo}','950','650','70','70');">
			<br/>
			<fieldset style="margin-top: 10px;" class="muestraSolicitud">
			<c:choose>
				<c:when test="${planOperativo.cantReportesSinAProbar == 0 && planOperativo.cantLiquidacionesSinAProbar == 0  }">
					<label style="font-size: 12px; font-weight: bold;">Solicitar Reprogramacion &amp; Reformulacion  <input type="checkbox" id="solicitarRpRf" name="solicitarRpRf" onchange="ocultaDetalleSolicitud();"  
					<c:if test="${cantSolicitaRpRf > 0  }">checked="checked"  disabled="disabled"</c:if>></label>	
				</c:when>
				<c:otherwise>
					<label style="color: red; font-size: 14px;">Para poder solicitar una Reprogramacion y/o Reformulacion se requiere tener todos los Reportes y Liquidaciones presentadas APROBADOS.</label>
				</c:otherwise>
			</c:choose>
			<table class="muestraDetalleSolicitud">
				<tr>
					<td style="text-align: right; width: 20%; vertical-align: top; ">
					    <label>Observacion de Solicitud:</label>
					</td>
					<td colspan="3" style="text-align: left; width: 80%; ">
					    <label><textarea rows="3" cols="75" id="observacionRpRf" name="observacionRpRf"
									style="width: 98%;" <c:if test="${cantSolicitaRpRf > 0  }"> disabled="disabled"</c:if>><c:out value="${solicitaRpRf.observacionDeSolicitud }"></c:out></textarea></label>
					</td>
				</tr>
				<tr>
					<td style="text-align: right; width: 20%; ">
					    <label>Estado de Solicitud:</label>
					</td>
					<td style="text-align: left; width: 30%; ">
					    <select name="sltEstadoSolicitud" id="sltEstadoSolicitud" class="disabled">
				<option value="0">
					<c:out value="-- Estado --" />
				</option>
				<c:forEach items="${listEstadoSolicitudRpRf}"
					var="estadoSolicitudRpRf">
							<option 
								<c:choose>
					<c:when test="${cantSolicitaRpRf == 0 }">
						<c:if test="${estadoSolicitudRpRf.prefijoEstado == 'eval' }">selected="selected"</c:if>
					</c:when>
					<c:otherwise>
						<c:if test="${estadoSolicitudRpRf.detalleEstadoCabeceraID == solicitaRpRf.fkIdDetalleEstadoCabRpRf }">selected="selected"</c:if>
					</c:otherwise>
				</c:choose>		
								value="${estadoSolicitudRpRf.detalleEstadoCabeceraID}">
								<c:out value="${estadoSolicitudRpRf.descripEstado }" />
							</option>
				</c:forEach>
				
		</select>
					</td>
					<td style="text-align: right; width: 20%; ">
					    <label>Fecha de Solicitud:</label>
					</td>
					<td style="text-align: left; width: 30%; ">
					    <input type="text" name="fechaSolicitud" maxlength="0" style="width: 90px;"
							id="fechaSolicitud" disabled="disabled"
							onkeypress="javascript:return Valida_Dato(event,7);" /><label>(dd/mm/aaaa)</label>
					</td>
			</tr>
			<tr>		
					<td colspan="4" style="text-align: right; width: 100%; ">
					<c:if test="${cantSolicitaRpRf == '0'  }"><input type="button" id="btnGrabarSolicitud" value="Grabar Solicitud" onclick="grabarSolicitud();" ></c:if>
					</td>
				</tr>
			</table>
			</fieldset>
			<br/>
			<c:if test="${totalValidaciones > 0 }">
			<div id="tablaValidacion">
			<div id="validacionCantidad"><label style="color: red;font-weight: bold;">Tiene cronogramas que no estan completos</label></div>
			<table width="100%" class="table-clasico-obs">
				<caption><label>Validacion de Cantidades</label></caption>
				<thead>
					<tr>
						<td style="width: 14%;text-align: center;"><label>Codigo <br/>Resultado</label></td>
						<td style="width: 14%;text-align: center;"><label>Codigo <br/>Actividad</label></td>
						<td style="width: 24%;text-align: center;"><label>Meta Por Resultado</label></td>
						<td style="width: 24%;text-align: center;"><label>Meta Por Actividad</label></td>
						<td style="width: 24%;text-align: center;"><label>Costo Por Actividad</label></td>
					</tr>
				</thead>
				<tbody>
					<!--<c:out value="${mensajeTablaValidaciones }"></c:out> -->
					<c:forEach items="${listValidaCantidadesCompletasPlanOperativo }" var="valida" varStatus="indice">
	<c:choose>
										<c:when test="${indice.count %2== 0}">
											<c:set var="classIdi" value="f2"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdi" value="f1"></c:set>
										</c:otherwise>
									</c:choose>
	<tr class="<c:out value="${classIdi}"></c:out>">
			<td style="width: 14%;text-align: center;"><label><c:out value="${valida.codigoResultado }"></c:out> </label></td>
						<td style="width: 14%;text-align: center;"><label><c:out value="${valida.codigoActividad }"></c:out> </label></td>
						<td style="width: 24%;text-align: center;"><label><c:out value="${valida.cantidadMetaPorResultado }"></c:out> </label></td>
						<td style="width: 24%;text-align: center;"><label><c:out value="${valida.cantidadMetaPorActividad }"></c:out> </label></td>
						<td style="width: 24%;text-align: center;"><label><c:out value="${valida.cantidadCostoActividad }"></c:out> </label></td>
		</tr>
	</c:forEach>
				</tbody>
			</table>
			 </div>
			<br>
			</c:if>
			
			<table class="table-clasico" style="width: 100%">
				<caption>Resultados</caption>
				<thead>
					<tr>
						<td style="width: 5%;text-align: center;"><label></label></td>
						<td style="width: 5%;text-align: center;"><label>Cod</label></td>
		                <td style="width: 30%;text-align: center;"><label>Definicion Resultado</label></td>
		                <td style="width: 30%;text-align: center;"><label>Supuesto Resultado</label></td>
						<td style="width: 15%;text-align: center;"><label>Meta Resultado</label></td>
		                <td style="width: 10%;text-align: center;"><label>Duracion<br>(Meses)</label></td>
		                <td  style="width: 5%;text-align: center;"class="hide" ><label>Operaciones</label></td>
					</tr>
				</thead>
				<c:forEach var="resultado" items="${planOperativo.listResultadoForm}" varStatus="indice">
			<c:choose>
				<c:when test="${indice.count %2== 0}">
					<c:set var="classIdi" value="f1"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="classIdi" value="f2"></c:set>
				</c:otherwise>
			</c:choose>
			<tr class="<c:out value="${classIdi}"></c:out>">
						<td align="center"><!-- <input type="radio" name="resultadoId" value="${resultado.resultadoID}" > -->
						<a
					href="javascript:expandcollapse('div<c:out value="${resultado.resultadoID }"></c:out>', 'one');">
						<img
						id='imgdiv<c:out value="${resultado.resultadoID }"></c:out>'
						border="0" src="<c:url value="/images/Plus001.gif" />"
						width="15px" /> </a>
						</td>
						<td align="left"><c:out value="${resultado.codigoResultado}"></c:out></td>
						<td align="left"><c:out value="${resultado.definicionResultado}"></c:out></td>
						<td align="left"><c:out value="${resultado.supuestoResultado}"></c:out></td>
						<td align="center"><c:out value="${resultado.metaResultado} - ${resultado.estratoNombre}"></c:out></td>
						<td align="center"><c:out value="${resultado.duracionMeses} meses"></c:out></td>
						<td class="hide" align="center">
						  <c:if test="${resultado.codigoResultado != 0}">
						  <a href="#" onclick="fOpenModalDialog('modificarResultado.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoId=${resultado.resultadoID}','780','400','70','70')" class="linkSelecciona" >Modificar</a><br />
						  <a href="#" onclick="fEliminar(${resultado.resultadoID},${resultado.codigoResultado})" class="linkSelecciona" >Eliminar</a>
						  </c:if>
						  <c:if test="${resultado.codigoResultado == 0}">
						   -
						  </c:if>
						  </td>
					</tr>
					<tr class="<c:out value="${classIdi}"></c:out>">
				<td colspan="100%">
					<div
						id='div<c:out value="${resultado.resultadoID }"></c:out>'
						style="display: none; position: relative; overflow: auto; padding-left: 5px; padding-bottom: 15px; width: 97%;">
					<table style="margin-left: 25px;" width="90%">
						<tr>
							<td style="width: 40%; text-align: left;"><label><a
											href="javascript:goTo('showCronogramaResultado.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID }&estadoPlanOperativo=${estadoPlanOperativo}','600','500')"
											id="apresiacionResultados" class="linkSelecciona" >Cronograma Meta Resultados</a>
									</label></td>
							<td style="width: 20%; text-align: left;"><label><a
											href="javascript:goTo('showIndicadorResultado.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID }&estadoPlanOperativo=${estadoPlanOperativo}','900','600')"
											id="apresiacionResultados" class="linkSelecciona" >Indicadores</a>
									</label></td>
							<td style="width: 20%; text-align: left;"><label><a
											href="javascript:goTo('showBeneficiarioResultado.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID }&estadoPlanOperativo=${estadoPlanOperativo}','900','600')"
											id="apresiacionResultados" class="linkSelecciona" >Beneficiarios</a>
									</label></td>
							<td style="width: 20%; text-align: left;"><label><a 
											href="javascript:goTo('showActividad.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID }&estadoPlanOperativo=${estadoPlanOperativo}','950','650')"
											id="apresiacionResultados" class="linkSeleccionaEsp" >Actividades</a>
									</label></td>
							
						</tr>
					</table>
					</div>
					</td>
					</tr>
				</c:forEach>
			</table>

			<br>
			<!-- <table style="border: 0; width: 100%">
				<tr>
					<td style="width: 40%;"></td>
					<td style="width: 15%; text-align: right;"><input type="button" value="Actividades" onClick="goToForm(this.form, 'showActividad.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=');"></td>
					<td style="width: 15%; text-align: right;"><input type="button" value="Beneficiarios" onClick="goToForm(this.form, 'showBeneficiarioResultado.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=');"></td>
					<td style="width: 15%; text-align: right;"><input type="button" value="Indicadores" onClick="goToForm(this.form, 'showIndicadorResultado.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=');"></td>
					<td style="width: 15%; text-align: right;"><input type="button" value="Cronograma Meta Resultados" onClick="goToForm2(this.form, 'showCronogramaResultado.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=');"></td>
				</tr>
			</table>-->
	    
	</c:if>
	</fieldset>	
 </div>
</form>
</body>
</html>
