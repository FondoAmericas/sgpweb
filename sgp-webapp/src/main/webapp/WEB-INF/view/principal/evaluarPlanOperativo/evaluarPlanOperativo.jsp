<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@include file="/common/includesTaglibsGenerico.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/displaytagex.css"
	type="text/css" />
<script type="text/javascript"
	src="<c:url value="/js/gestorAjax.js"></c:url>"></script>
	
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
	var evalua;
	var cargarProyec = 0;
	var dato;

	$(document).ready(function() {
		mainmenu();
		dato = $('#cargarProyecto').attr('value'); 
		var mensaje= "<c:out value="${mensaje}"></c:out>" ;
		if (mensaje!=""){
			alert(mensaje);
		}
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

	function openWindow(url){
		window.open(url,'','width=900,height=850,left=0,top=50,screenX=0,screenY=50');
	}

	$(window).load(function() {

		if (dato == 0) {
			$('#divListaProgramas').show();

		}
		if (dato == 1) {
			$('#divListaProgramas').hide();
			$('#divListProyectos').show('Bounce');

		}
		if (dato == 2) {
			$('#divListPlanOperativo').show('Bounce');
			$('#divListaProgramas').hide();
			$('#divListProyectos').hide();
		}
		if (dato == 3) {
			$('#divListPlanOperativo').show('Bounce');
			//$('#divListResultadoPorActividad').show('Bounce');
			$('#divListResultado').hide();

			$('#divListaProgramas').hide();
			$('#divListProyectos').hide();

		}
	});

	function fBuscarPrograma() {
		var form = document.formEvaluar;
		form.action = "actionBuscarProgramaEvaluacion.jspx?evalu=" + evalua;
		form.submit();
	}

	function fMostrarSelect(valor) {
		if (valor == 0) {
			$('#divlistTipoPeriodos').hide();
			$('#divlistTipoCuentas').hide();
			$('#divlistModalidadFinanciera').hide();
			$('#divNombre').hide();
			$('#divSelect').show();
		}
		if (valor == 100) {
			$('#divlistTipoPeriodos').show();
			$('#divlistTipoCuentas').hide();
			$('#divlistModalidadFinanciera').hide();
			$('#divNombre').hide();
			$('#divSelect').hide();
		}
		if (valor == 101) {
			$('#divlistTipoPeriodos').hide();
			$('#divlistTipoCuentas').show();
			$('#divlistModalidadFinanciera').hide();
			$('#divNombre').hide();
			$('#divSelect').hide();
		}
		if (valor == 102) {
			$('#divlistTipoPeriodos').hide();
			$('#divlistTipoCuentas').hide();
			$('#divlistModalidadFinanciera').show();
			$('#divNombre').hide();
			$('#divSelect').hide();
		}
		if (valor == 103) {
			$('#divNombre').show();
			$('#divlistTipoPeriodos').hide();
			$('#divlistTipoCuentas').hide();
			$('#divlistModalidadFinanciera').hide();
			$('#divSelect').hide();
		}
		if (valor == 104) {
			$('#divlistTipoPeriodos').hide();
			$('#divlistTipoCuentas').hide();
			$('#divlistModalidadFinanciera').hide();
			$('#divNombre').hide();
			$('#divSelect').hide();
		}

	}
	function fCargarProgramas(proyec) {
		var form = document.formEvaluarPlanOperativo;
		form.action = "actionCargarProyectosEvaluarPlanOperativo.jspx?idPrograma="
				+ proyec;
		form.submit();

	}

	function fCargarPlanOperativo(id) {

		var form = document.formEvaluarPlanOperativo;
		form.action = "actionShowEvaluarPlanOperativo.jspx?datoProyectoID="
				+ id;
		form.submit();
	}

	/*function fCargarActividad(idResultado) {
		var form = document.formEvaluarPlanOperativo;
		form.action = "actionActividadEvaluarPlanOperativo.jspx?resultadoId="
				+ idResultado;
		form.submit();
	}*/
	function fRegresar() {
		$('#divListProyectos').show('Bounce');
		$('#divListPlanOperativo').hide();
		$('#divListResultado').hide();
		//$('#divListResultadoPorActividad').hide();
		$('#divListaProgramas').hide();
	}

	function fCancelar() {
		$('#divListaProgramas').show('Bounce');
		$('#divListProyectos').hide();

	}
	function grabarEstado(){

	if($("#idEstadoabecera").val()!=0){
		var form = document.formEvaluarPlanOperativo;
		form.action = "actionGrabarEvaluacionPlanOperativo.jspx?estado="+ $("#idEstadoabecera").val()+"&datoPlanOperativoId="+$("#datoPlanOperativoID").val();
		form.submit();
		/*$.get("actionGrabarEvaluacionPlanOperativo.jspx", {
			estado : $("#idEstadoabecera").val(),
			datoPlanOperativoId : $("#datoPlanOperativoID").val()
		});*/
	}else if($("#idEstadoabecera").val()==0){
		alert("Seleccionar el estado a grabar.");
		$("#idEstadoabecera").attr("value",$("#estadoPlanOperativoId").val());
	}
		
	}
	/*
	function fRegresarResultado() {
		$('#divListPlanOperativo').show('Bounce');
		$('#divListResultado').show('Bounce');
		//$('#divListResultadoPorActividad').hide();

		$('#divListaProgramas').hide();
		$('#divListProyectos').hide();

	}
	
	function fObtenerResultado(tipo) {
		if (getResultadoID() != 0) {

			fOpenModalDialog("showDetalleResultado.jspx?tipoResultado=" + tipo
					+ "&resultadoID=" + getResultadoID(), '900', '500', '90',
					'60');
		} else {
			alert("Debe seleccionar un Resultado");
			return;
		}
	}

	function fObtenerActividad(tipo) {
		if (getActividadID() != 0) {
			fOpenModalDialog("showDetalleActividad.jspx?tipoActividad=" + tipo
					+ "&actividadID=" + getActividadID(), '900', '500', '90',
					'60');
		} else {
			alert("Debe seleccionar una Actividad");
			return;
		}

	}*/
	function fBuscarPrograma() {
		var form = document.formEvaluarPlanOperativo;
		form.action = "actionBuscarEvaluarPlanOperativo.jspx";
		form.submit();
	};
/*
	function fSeleccionResultado(resultado) {
		codigoResultado = resultado;
	};

	function fSeleccionActividad(actividad) {
		codigoActividad = actividad;
	};
	*/
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
</script>

<div class="form-clasico">

	<form:form name="formEvaluarPlanOperativo"
		action="actionEvaluarPlanOperativo.jspx" method="POST">
		<!-- <div id="divMensaje"></div> -->

		<div class="encabezado" id="cproyecto">EVALUAR PLAN OPERATIVO</div>

		<br />
		<input type="hidden" id="cargarProyecto" value="${cargarProyecto}" />
		<!-- <input type="hidden" id="mensaje" value="${mensajeEvaluador}" /> -->
		<input type="hidden" value="${planOperativo.datoPlanOperativoID}"
			name="datoPlanOperativoID" id="datoPlanOperativoID"/>

		<div id="divListaProgramas" style="display: none;">
			<fieldset>
				<legend>Programas</legend>
				<table>
					<tr>
						<td><label>Filtro:</label></td>
						<td><select name="idFiltro" id="idFiltro"
							onchange="fMostrarSelect(this.value)">
								<option value="0">
									<c:out value="--Seleccionar--" />
								<option value="100">
									<c:out value="Tipo Periodo" />
								</option>
								<option value="101">
									<c:out value="Tipo Cuenta" />
								</option>
								<option value="102">
									<c:out value="Modalidad" />
								</option>
								<option value="104">
									<c:out value="Todos" />
								</option>
						</select>
						</td>

						<td>
							<div id="divSelect">
								<select name="sele" disabled="disabled">
									<option value="0">
										<c:out value="--Seleccionar--" />
									</option>
								</select>
							</div>
							<div id="divlistTipoPeriodos" style="display: none;">
								<select name="idTipoPeriodos" id="idTipoPeriodos">
									<option value="0">
										<c:out value="--Seleccionar--" />
									</option>
									<c:forEach items="${listTipoPeriodos}" var="tipoPeriodo">
										<option value="${tipoPeriodo.tipoPeriodoID}">
											<c:out value="${tipoPeriodo.descripPeriodo}" />
										</option>

									</c:forEach>
								</select>
							</div>
							<div id="divlistTipoCuentas" style="display: none;">
								<select name="idTipoCuentas" id="idTipoCuentas">
									<option value="0">
										<c:out value="-- Seleccionar --" />
									</option>
									<c:forEach items="${listTipoCuentas}" var="tipoCuenta">
										<option value="${tipoCuenta.tablaEspecificaID}">
											<c:out value="${tipoCuenta.descripcionCabecera}" />
										</option>
									</c:forEach>
								</select>
							</div>
							<div id="divlistModalidadFinanciera" style="display: none;">
								<select name="idModalidadFinanciera" id="idModalidadFinanciera">
									<option value="0">
										<c:out value="-- Seleccionar --" />
									</option>
									<c:forEach items="${listModalidadFinanciera}" var="modali">
										<option value="${modali.tablaEspecificaID}">
											<c:out value="${modali.descripcionCabecera}" />
										</option>
									</c:forEach>
								</select>
							</div></td>
						<td><input value="Buscar Programa" type="button"
							onclick="fBuscarPrograma()" />
						</td>
					</tr>
				</table>
				<br/>
				<table class="table-clasico" width="100%">
					<caption><label>Lista de Programas</label></caption>
					<thead>
						<tr>
							<td style="text-align: center; width: 3%;"><label>N°</label></td>
							<td style="text-align: center; width: 30%;"><label>Programa</label></td>
							<td style="text-align: center; width: 7%;"><label>Duracion<br/>Maxima<br/>(meses)</label></td>
							<td style="text-align: center; width: 10%;"><label>Tipo de<br/>Periodo</label></td>
							<td style="text-align: center; width: 20%;"><label>Tipo de <br/>Cuenta</label></td>
							<td style="text-align: center; width: 20%;"><label>Modalidad <br/>Financiamiento</label></td>
							<td style="text-align: center; width: 10%;"><label>Opcion</label></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaProgramaAndProyecto }" var="programaAndProyecto" varStatus="indice">
	<c:choose>
										<c:when test="${indice.count %2== 0}">
											<c:set var="classIdi" value="f2"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdi" value="f1"></c:set>
										</c:otherwise>
									</c:choose>
	<tr class="<c:out value="${classIdi}"></c:out>">
							<td style="text-align: center; width: 3%;"><label><c:out value="${indice.count }"></c:out></label></td>
							<td style="text-align: left; width: 30%;"><label><c:out value="${programaAndProyecto.nombrePrograma }"></c:out></label></td>
							<td style="text-align: center; width: 7%;"><label><c:out value="${programaAndProyecto.duracionPrograma }"></c:out> </label></td>
							<td style="text-align: center; width: 10%;"><label><c:out value="${programaAndProyecto.tipoPeriodo.descripPeriodo }"></c:out></label></td>
							<td style="text-align: center; width: 20%;"><label><c:out value="${programaAndProyecto.descripcionTipoCuenta }"></c:out></label></td>
							<td style="text-align: center; width: 20%;"><label><c:out value="${programaAndProyecto.descripcionModalidadFinancia }"></c:out></label></td>
							<td style="text-align: center; width: 10%;"><label><a href="javascript:fCargarProgramas('${programaAndProyecto.programaID}')"
							class="linkSelecciona" style="cursor: pointer;"	title="Cargar Proyectos">Ver <br/>Proyectos</a></label></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</fieldset>
		</div>
		<div id="divListProyectos" style="display: none;">
			<fieldset>
				<legend>Proyectos del Programa</legend>
				<table style="width: 100%;">
					<tr>
						<td style="width: 25%; text-align: right;"><label>Programa:</label>
						</td>
						<td style="width: 25%; text-align: left;"><label><c:out
									value="${programa.nombrePrograma }"></c:out> </label></td>
						<td style="width: 25%; text-align: right;"><label>Modalidad
								de Financiamiento:</label></td>
						<td style="width: 25%; text-align: left;"><label><c:out
									value="${programa.modalidadFinancia }"></c:out> </label></td>
					</tr>
					<tr>
						<td style="width: 25%; text-align: right;"><label>Duracion
								Maxima(Meses):</label></td>
						<td style="width: 25%; text-align: left;"><label><c:out
									value="${programa.duracionPrograma }"></c:out> </label></td>
						<td style="width: 25%; text-align: right;"><label>Tipo
								de Periodo:</label></td>
						<td style="width: 25%; text-align: left;"><label><c:out
									value="${programa.nombreTipoPeriodo }"></c:out> </label></td>
					</tr>
				</table>
				<br><div style="width: 100%; text-align: right;"><input type="button" onclick="fCancelar()"
					value="Regresar" align="middle" /></div>
					<br/>
					<table width="100%" class="table-clasico">
						<caption><label>Lista de Proyectos del Programa</label></caption>
						<thead>
							<tr>
							<td style="text-align: center; width: 30%;"><label>Nombre de Proyecto</label></td>
							<td style="text-align: center; width: 10%;"><label>Codigo</label></td>
							<td style="text-align: center; width: 10%;"><label>Duracion<br/>(meses)</label></td>
							<td style="text-align: center; width: 10%;"><label>Cantidad de <br/>Periodos</label></td>
							<td style="text-align: center; width: 20%;"><label>Sub Area <br/>Tematica</label></td>
							<td style="text-align: center; width: 10%;"><label>Estado<br/>Proyecto</label></td>
							<td style="text-align: center; width: 10%;"><label>Opcion</label></td>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${listDatoProyecto }" var="proyecto" varStatus="indice">
	<c:choose>
										<c:when test="${indice.count %2== 0}">
											<c:set var="classIdi" value="f2"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdi" value="f1"></c:set>
										</c:otherwise>
									</c:choose>
	<tr class="<c:out value="${classIdi}"></c:out>">
							<td style="text-align: left; width: 30%;"><label><c:out value="${proyecto.nombreProyecto }"></c:out></label></td>
							<td style="text-align: center; width: 10%;"><label><c:out value="${proyecto.codigoProyecto }"></c:out></label></td>
							<td style="text-align: center; width: 10%;"><label><c:out value="${proyecto.duracionProyecto }"></c:out></label></td>
							<td style="text-align: center; width: 10%;"><label><c:out value="${proyecto.cantidadPeriodo }"></c:out></label></td>
							<td style="text-align: left; width: 20%;"><label><c:out value="${proyecto.subAreaTematica.descripcionSubAt }"></c:out></label></td>
							<td style="text-align: center; width: 10%;"><label><c:out value="${proyecto.descripcionEstadoProyecto }"></c:out></label></td>
							<td style="text-align: center; width: 10%;"><label><a
							href="javascript:fCargarPlanOperativo('${proyecto.datoProyectoID}')"
							class="linkSelecciona" style="cursor: pointer"
							title="Cargar Plan Operativo">Ver<br/>Plan Operativo</a></label></td>
						</tr>
						</c:forEach>
						</tbody>
					</table>
			</fieldset>

		</div>
		<div id="divListPlanOperativo"
			style="display: none; width: 100%; height: auto">
			<fieldset>
				<legend>Plan Operativo</legend>
					<div id="menuReporte" style="background-color: white;">
		<ul id="navReporte">
			<li><a href="#">Seleccionar Reporte</a>
				<ul class="submenuReporte">
					<li><a href="javascript:openWindow('reportPlanOperativoResultados.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID }')">Plan Operativo - Resultados</a></li>
					<li><a href="javascript:openWindow('reportPlanOperativoCronogramaActividades.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID }')">Plan Operativo - Cronograma de Actividades</a></li>
					<li><a href="javascript:openWindow('reportPlanOperativoCostoActividadFuente.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID }')">Plan Operativo - Costo Actividad por Fuente</a></li>
					<li><a href="javascript:openWindow('reportPlanOperativoEstructuraInversionFinanciamiento.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID }')">Plan Operativo - Estructura de Inversion y Financiamiento</a></li>
					<!-- <li><a href="#">Plan Operativo - Contribucion de la Donacion por Fuente</a></li> -->
					<li><a href="javascript:openWindow('reportPlanOperativoBeneficiario.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID }')">Plan Operativo - Beneficiarios en el Area de Intervencion(Directos)</a></li>
					<li><a href="javascript:openWindow('reportPlanOperativoCronogramaResultados.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID }')">Plan Operativo - Operaciones y Costos(Cronograma de Resultados)</a></li>
					<!-- <li><a href="#">Plan Operativo - ContraPartida</a></li> -->
					<li><a href="javascript:openWindow('reportPlanOperativoCostoActividadFuenteMontos.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID }')">Plan Operativo - Desemboloso de Recursos de Donacion</a></li>
					
					
				</ul>
			</li>
		</ul>
	</div>
				<div style="text-align: right;">
			<input type="button" value="Observaciones Generales" class="hide"
				onclick="agregarObservacion('<c:out value="${planOperativo.datoPlanOperativoID }" ></c:out>','<c:out value="${planOperativo.datoProyectoID }" ></c:out>',4,47,'<c:out value="${planOperativo.datoPlanOperativoID }" ></c:out>');" />
			
		</div>
		<br />
<table border="0" align="center" width="100%">
					<tr>
						<td style="text-align: right; width: 25%;"><label>Nombre
								Proyecto:</label></td>
						<td style="text-align: left; width: 75%;" colspan="3"><label
							for="nombreProyecto">${proyectoBean.nombreProyecto}</label>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%;"><label>Codigo
								Proyecto:</label></td>
						<td style="text-align: left; width: 25%;"><label
							for="codigoProyecto">${proyectoBean.codigoProyecto}</label>
						</td>
						<td style="text-align: right; width: 25%;"><label>Duracion
								Proyecto(meses):</label></td>
						<td style="text-align: left; width: 25%;"><label
							for="duracionProyecto">${proyectoBean.duracionProyecto}</label>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%;"><label>Cantidad
								Periodo:</label></td>
						<td style="text-align: left; width: 25%;"><label
							for="cantidadPeriodo">${proyectoBean.cantidadPeriodo }</label>
						</td>
						
						<td style="text-align: right; width: 25%; vertical-align: top;"><label>Estado Plan Operativo:</label>
						</td>
						<td style="text-align: left; width: 25%;">
						<c:choose>
							<c:when test="${planOperativo.cantObservacionesRelevantes > 0 }">
							<label><c:out value="${planOperativo.estadoPlanOperativoDescripcion }"></c:out></label><br/>
							<label style="color: red;">Plan Operativo Tiene observaciones relevantes sin atender.</label>
							</c:when>
							<c:otherwise>
						<select
							name="idEstadoabecera" id="idEstadoabecera" onchange="grabarEstado()" style="width: 100%;" <c:if
										test="${ planOperativo.estadoPlanOperativo == 61}">
											disabled="disabled"
									</c:if>
									<c:if test="${userSession.perfilUsuarioID!=2 }">
									disabled="disabled"
									</c:if>>
								<option value="0">
									<c:out value="--Seleccionar--" />
								</option>
								<c:forEach items="${listaEstadoCabecera}" var="estado">
									<option value="${estado.detalleEstadoCabeceraID}"
										<c:if
										test="${ planOperativo.estadoPlanOperativo == estado.detalleEstadoCabeceraID}">
											selected="selected"
									</c:if>>
										<c:out value="${estado.descripEstado}" />
									</option>
								</c:forEach>
						</select>
						<input type="hidden" id="estadoPlanOperativoId" name="estadoPlanOperativoId" value="${planOperativo.estadoPlanOperativo }">
						</c:otherwise>
						</c:choose></td>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%;"><label>Version
								Plan Operativo:</label></td>
						<td style="text-align: left; width: 25%;"><label
							for="planOperativo">${planOperativo.version}</label>
						</td>
						<!-- <td  style="text-align: right; width: 25%;"><label>Resumen Ejecutivo:</label></td>
						<td style="text-align: left; width: 25%;"><label for="proyectoNombre"> <c:out
									value="${marcoLogico.resumenEjecutivo}" /> </label></td>
					 -->
					</tr>
					<!-- <tr>				
						<td  style="text-align: right;  width: 25%;"><label>Fin Descripcion:</label></td>
						<td  style="text-align: left; width: 25%;"><label> <c:out
									value="${marcoLogico.finDescrip}" /> </label></td>
						<td style="text-align: right; width: 25%;"><label>Fin Supuesto:</label></td>
						<td  style="text-align: left; width: 25%;"><label> <c:out
									value="${marcoLogico.finSupuesto}" /> </label></td>
					
					</tr>
					<tr>				
						<td style="text-align: right;  width: 25%;"><label>Proposito Descripcion:</label></td>
						<td  style="text-align: left; width: 25%;"><label> <c:out
									value="${marcoLogico.propositoDescrip}" /> </label></td>
						<td style="text-align: right; width: 25%;"><label>Proposito Supuesto:</label></td>
						<td  style="text-align: left; width: 25%;"><label for="proyectoNombre"> <c:out
									value="${marcoLogico.propositoSupuesto}" /> </label></td>
					</tr> -->
					<tr>

						<td colspan="4" style="width: 100%; text-align: right;"><!-- <input
							type="button" value="Grabar Evaluaci&oacute;n"
							onclick="fGrabarEvaluacion()" />--> <input type="button"
							onclick="fRegresar()" value="Regresar" /></td>
					</tr>
				</table>
				<br />
				<div id="divListResultado" style="width: 100%;">
					<fieldset>
						<table width="100%" class="table-clasico">
							<caption>
								<label>Lista de Resultados con Detalles</label>
							</caption>
							<thead>
								<tr>
									<td style="text-align: center; width: 3%;"><label>Det</label>
									</td>
									<td style="text-align: center; width: 5%;"><label>Codigo</label>
									</td>
									<td style="text-align: center; width: 60%;"><label>Resultado</label>
									</td>
									<td style="text-align: center; width: 17%;"><label>Meta</label>
									</td>
									<td style="text-align: center; width: 10%;"><label>Duracion<br />(Meses)</label>
									</td>
									<td style="text-align: center; width: 5%;"><label>Opciones</label>
									</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${planOperativo.listResultadoForm }"
									var="resultado" varStatus="indice">
									<c:choose>
										<c:when test="${indice.count %2== 0}">
											<c:set var="classIdi" value="f2"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdi" value="f1"></c:set>
										</c:otherwise>
									</c:choose>
									<tr class="<c:out value="${classIdi}"></c:out>">
										<td style="width: 3%; text-align: center;"><a
											href="javascript:expandcollapse('divRes<c:out value="${resultado.resultadoID }"></c:out>', 'one');">
												<img
												id='imgdivRes<c:out value="${resultado.resultadoID }"></c:out>'
												border="0" src="<c:url value="/images/Plus001.gif" />"
												width="15px" /> </a>
										</td>
										<td style="text-align: center; width: 5%;"><label><c:out
													value="${resultado.codigoResultado }"></c:out> </label>
										</td>
										<td style="text-align: justify; width: 60%;"><label><c:out
													value="${resultado.definicionResultado }"></c:out> </label>
										</td>
										<td style="text-align: left; width: 17%;"><label><c:out
													value="${resultado.metaResultado }"></c:out> - <c:out
													value="${resultado.estratoNombre }"></c:out> </label>
										</td>
										<td style="text-align: center; width: 10%;"><label><c:out
													value="${resultado.duracionMeses }"></c:out> </label>
										</td>
										<td style="text-align: center; width: 5%;"><label><a	href="javascript:agregarObservacion('<c:out value="${resultado.resultadoID  }" ></c:out>','<c:out value="${planOperativo.datoProyectoID }" ></c:out>','4','10','<c:out value="${planOperativo.datoPlanOperativoID }" ></c:out>')"
														class="linkSelecciona">Observacion</a></label>
										</td>
									</tr>
									<tr class="<c:out value="${classIdi}"></c:out>">
										<td colspan="100%">
											<div
												id='divRes<c:out value="${resultado.resultadoID }"></c:out>'
												style="display: none; position: relative; overflow: auto; padding-left: 25px; padding-bottom: 15px; width: 97%;">

												<legend>Cronograma de Meta de Resultado</legend>
												<table width="100%">
													<thead>
														<tr>
															<td style="text-align: center; width: 30%;"><label>Periodo</label>
															</td>
															<td style="text-align: center; width: 30%;"><label>Avance
																	de Meta</label></td>
															<td style="text-align: center; width: 30%;"><label>Opcion</label></td>																	
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${resultado.listCronogramaMetaForm }"
															var="cronogramaMetaResultado" varStatus="indiceInt">
															<c:choose>
																<c:when test="${indiceInt.count %2== 0}">
																	<c:set var="classIdiInt" value="f2int"></c:set>
																</c:when>
																<c:otherwise>
																	<c:set var="classIdiInt" value="f1int"></c:set>
																</c:otherwise>
															</c:choose>
															<tr class="<c:out value="${classIdiInt}"></c:out>">
																<td style="text-align: center; width: 30%;"><label><c:out
																			value="${cronogramaMetaResultado.periodo }"></c:out>
																</label></td>
																<td style="text-align: center; width: 30%;"><label><c:out
																			value="${cronogramaMetaResultado.avanceMeta }"></c:out>
																		- <c:out value="${resultado.estratoNombre }"></c:out>
																</label></td>
																<td style="text-align: center; width: 30%;"><label><a	href="javascript:agregarObservacion('<c:out value="${cronogramaMetaResultado.cronogramaMetaPorResultadoID  }" ></c:out>','<c:out value="${planOperativo.datoProyectoID }" ></c:out>','4','18','<c:out value="${planOperativo.datoPlanOperativoID }" ></c:out>')"
														class="linkSelecciona">Observacion</a>
																</label></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
												<br />
												<legend>Actividades</legend>
												<table width="100%">
													<thead>
														<tr>
															<td style="text-align: center; width: 3%;"><label>Det</label>
															</td>
															<td style="text-align: center; width: 5%;"><label>Codigo</label>
															</td>
															<td style="text-align: center; width: 15%;"><label>Tipo
																	de <br />Actividad</label></td>
															<td style="text-align: center; width: 55%;"><label>Actividad</label>
															</td>
															<td style="text-align: center; width: 10%;"><label>Duracion<br />(meses)</label>
															</td>
															<td style="text-align: center; width: 10%;"><label>Opcion</label>
															</td>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${resultado.listActividadForm }"
															var="actividad" varStatus="indiceInt">
															<c:choose>
																<c:when test="${indiceInt.count %2== 0}">
																	<c:set var="classIdiInt" value="f2int"></c:set>
																</c:when>
																<c:otherwise>
																	<c:set var="classIdiInt" value="f1int"></c:set>
																</c:otherwise>
															</c:choose>
															<tr class="<c:out value="${classIdiInt}"></c:out>">
																<td style="width: 3%; text-align: center;"><a
																	href="javascript:expandcollapse('divAct<c:out value="${actividad.actividadID }"></c:out>', 'one');">
																		<img
																		id='imgdivAct<c:out value="${actividad.actividadID }"></c:out>'
																		border="0" src="<c:url value="/images/Plus001.gif" />"
																		width="15px" /> </a>
																</td>
																<td style="text-align: center; width: 5%;"><label><c:out
																			value="${actividad.codigoActividad }"></c:out> </label></td>
																<td style="text-align: center; width: 15%;"><label><c:out
																			value="${actividad.tipoActividadNombre }"></c:out> </label></td>
																<td style="text-align: left; width: 55%;"><label><c:out
																			value="${actividad.nombreActividad }"></c:out> </label></td>
																<td style="text-align: center; width: 10%;"><label><c:out
																			value="${actividad.duracionMeses }"></c:out> </label></td>
																<td style="text-align: center; width: 10%;"><label><a	href="javascript:agregarObservacion('<c:out value="${actividad.actividadID  }" ></c:out>','<c:out value="${planOperativo.datoProyectoID }" ></c:out>','4','11','<c:out value="${planOperativo.datoPlanOperativoID }" ></c:out>')"
														class="linkSelecciona">Observacion</a></label></td>			
															</tr>
															<tr class="<c:out value="${classIdiInt}"></c:out>">
																<td colspan="100%">
																	<div
																		id='divAct<c:out value="${actividad.actividadID }"></c:out>'
																		style="display: none; position: relative; overflow: auto; padding-left: 25px; padding-bottom: 15px; width: 97%;">
																		<legend>Meta de Actividad</legend>
																		<table width="100%">
																			<thead>
																				<tr>
																					<td style="text-align: center; width: 3%;"><label>Det</label>
																					</td>
																					<td style="text-align: center; width: 40%;"><label>Tipo
																							Indicador</label></td>
																					<td style="text-align: center; width: 35%;"><label>Meta
																							Actividad</label></td>
																					<td style="text-align: center; width: 10%;"><label>Opcion</label></td>		
																				</tr>
																			</thead>
																			<tbody>
																				<c:forEach
																					items="${actividad.listMetaPorActividad  }"
																					var="metaPorActividad" varStatus="indiceInt2">
																					<c:choose>
																						<c:when test="${indiceInt2.count %2== 0}">
																							<c:set var="classIdiInt2" value="f2"></c:set>
																						</c:when>
																						<c:otherwise>
																							<c:set var="classIdiInt2" value="f1"></c:set>
																						</c:otherwise>
																					</c:choose>
																					<tr class="<c:out value="${classIdiInt2}"></c:out>">
																						<td style="width: 3%; text-align: center;"><a
																							href="javascript:expandcollapse('divMetaAct<c:out value="${metaPorActividad.metaPorActividadID }"></c:out>', 'one');">
																								<img
																								id='imgdivMetaAct<c:out value="${metaPorActividad.metaPorActividadID }"></c:out>'
																								border="0"
																								src="<c:url value="/images/Plus001.gif" />"
																								width="15px" /> </a>
																						</td>
																						<td style="text-align: left; width: 40%;"><label><c:out
																									value="${metaPorActividad.descripcionTipoIndicadorActividad }"></c:out>
																						</label></td>
																						<td style="text-align: center; width: 35%;"><label><c:out
																									value="${metaPorActividad.cantidadMetaActividad }"></c:out>
																								- <c:out
																									value="${metaPorActividad.descripcionUnidadMedida }"></c:out>
																						</label></td>
																						<td style="text-align: center; width: 10%;"><label><a	href="javascript:agregarObservacion('<c:out value="${metaPorActividad.metaPorActividadID  }" ></c:out>','<c:out value="${planOperativo.datoProyectoID }" ></c:out>','4','12','<c:out value="${planOperativo.datoPlanOperativoID }" ></c:out>')"
														class="linkSelecciona">Observacion</a></label></td>
																					</tr>
																					<tr class="<c:out value="${classIdiInt2}"></c:out>">
																						<td colspan="100%">
																							<div
																								id='divMetaAct<c:out value="${metaPorActividad.metaPorActividadID }"></c:out>'
																								style="display: none; position: relative; overflow: auto; padding-left: 25px; padding-bottom: 15px; width: 97%;">
																								<legend>Cronograma Meta de Actividad</legend>
																								<table width="100%">
																									<thead>
																										<tr>
																											<td style="text-align: center; width: 20%;"><label>Periodo</label>
																											</td>
																											<td style="text-align: center; width: 50%;"><label>Cantidad
																													Programada <br />Por Periodo</label></td>
																											<td style="text-align: center; width: 10%;"><label>Opcion</label></td>		
																										</tr>
																									</thead>
																									<tbody>
																										<c:forEach
																											items="${metaPorActividad.listCronogramaMetaPorActividad  }"
																											var="cronogramaMetaPorActividad"
																											varStatus="indiceInt2">
																											<c:choose>
																												<c:when test="${indiceInt2.count %2== 0}">
																													<c:set var="classIdiInt2CMA" value="f2int"></c:set>
																												</c:when>
																												<c:otherwise>
																													<c:set var="classIdiInt2CMA" value="f1int"></c:set>
																												</c:otherwise>
																											</c:choose>
																											<tr
																												class="<c:out value="${classIdiInt2CMA}"></c:out>">
																												<td style="text-align: center; width: 20%;"><label><c:out
																															value="${cronogramaMetaPorActividad.periodo }"></c:out>
																												</label></td>
																												<td style="text-align: center; width: 50%;"><label><c:out
																															value="${cronogramaMetaPorActividad.cantidadMetaActividadProgPorPeriodo }"></c:out>
																														- <c:out
																															value="${metaPorActividad.descripcionUnidadMedida }"></c:out>
																												</label></td>
																												<td style="text-align: center; width: 10%;"><label><a	href="javascript:agregarObservacion('<c:out value="${cronogramaMetaPorActividad.cronogramaMetaPorActividadID  }" ></c:out>','<c:out value="${planOperativo.datoProyectoID }" ></c:out>','4','13','<c:out value="${planOperativo.datoPlanOperativoID }" ></c:out>')"
														class="linkSelecciona">Observacion</a></label></td>
																											</tr>
																										</c:forEach>
																									</tbody>
																								</table>
																							</div>
																						</td>
																					</tr>
																				</c:forEach>
																			</tbody>
																		</table>
																		<br />
																		<legend>Costo de Actividad</legend>
																		<table width="100%">
																			<thead>
																				<tr>
																					<td style="text-align: center; width: 3%;"><label>Det</label>
																					</td>
																					<td style="text-align: center; width: 15%;"><label>Cantidad</label>
																					</td>
																					<td style="text-align: center; width: 12%;"><label>Precio
																							Unitario</label></td>
																					<td style="text-align: center; width: 15%;"><label>Partida
																							<br />Generica</label></td>
																					<td style="text-align: center; width: 15%;"><label>Partida
																							<br />Especifica</label></td>
																					<td style="text-align: center; width: 30%;"><label>Detalle</label>
																					</td>
																					<td style="text-align: center; width: 5%;"><label>Opcion</label>
																					</td>
																				</tr>
																			</thead>
																			<tbody>
																				<c:forEach items="${actividad.listCostoActividad }"
																					var="costoPorActividad" varStatus="indiceInt2CPA">
																					<c:choose>
																						<c:when test="${indiceInt2CPA.count %2== 0}">
																							<c:set var="classIdiInt2CPA" value="f2"></c:set>
																						</c:when>
																						<c:otherwise>
																							<c:set var="classIdiInt2CPA" value="f1"></c:set>
																						</c:otherwise>
																					</c:choose>
																					<tr
																						class="<c:out value="${classIdiInt2CPA}"></c:out>">
																						<td style="width: 3%; text-align: center;"><a
																							href="javascript:expandcollapse('divCostoAct<c:out value="${costoPorActividad.costoActividadID }"></c:out>', 'one');">
																								<img
																								id='imgdivCostoAct<c:out value="${costoPorActividad.costoActividadID }"></c:out>'
																								border="0"
																								src="<c:url value="/images/Plus001.gif" />"
																								width="15px" /> </a>
																						</td>
																						<td style="text-align: center; width: 15%;"><label><c:out
																									value="${costoPorActividad.cantidadTotal }"></c:out>
																								- <c:out
																									value="${costoPorActividad.descripcionUnidadMedida }"></c:out>
																						</label></td>
																						<td style="text-align: center; width: 12%;"><label><c:out
																									value="${costoPorActividad.precioUnitario }"></c:out>
																								<c:out
																									value="${costoPorActividad.descripcionTipoMoneda }"></c:out>
																						</label></td>
																						<td style="text-align: center; width: 15%;"><label><c:out
																									value="${costoPorActividad.partidaGenerica.descripcionPartidaGenerica }"></c:out>
																						</label></td>
																						<td style="text-align: center; width: 15%;"><label><c:out
																									value="${costoPorActividad.partidaEspecifica.descripcionPartidaEspecifica }"></c:out>
																						</label></td>
																						<td style="text-align: center; width: 30%;"><label><c:out
																									value="${costoPorActividad.detallePartidaGenerica }"></c:out>
																						</label></td>
																						<td style="text-align: center; width: 30%;"><label><a	href="javascript:agregarObservacion('<c:out value="${costoPorActividad.costoActividadID  }" ></c:out>','<c:out value="${planOperativo.datoProyectoID }" ></c:out>','4','14','<c:out value="${planOperativo.datoPlanOperativoID }" ></c:out>')"
														class="linkSelecciona">Observacion</a></label></td>
																					</tr>
																					<tr
																						class="<c:out value="${classIdiInt2CPA}"></c:out>">
																						<td colspan="100%">
																							<div
																								id='divCostoAct<c:out value="${costoPorActividad.costoActividadID }"></c:out>'
																								style="display: none; position: relative; overflow: auto; padding-left: 25px; padding-bottom: 15px; width: 97%;">
																								<legend>Cronograma Costo Actividad</legend>
																								<table width="100%">
																									<thead>
																										<tr>
																											<td style="text-align: center; width: 15%;"><label>Periodo</label>
																											</td>
																											<td style="text-align: center; width: 15%;"><label>Cantidad
																													Programada <br />Por Periodo</label></td>
																											<td style="text-align: center; width: 60%;"><label>Fuente<br />Financiadora</label>
																											</td>
																											<td style="text-align: center; width: 10%;"><label>Opcion</label>
																											</td>
																										</tr>
																									</thead>
																									<tbody>
																										<c:forEach
																											items="${costoPorActividad.listCronogramaCostoActividad  }"
																											var="cronogramaCostoPorActividad"
																											varStatus="indiceInt2CPA">
																											<c:choose>
																												<c:when test="${indiceInt2CPA.count %2== 0}">
																													<c:set var="classIdiInt2CPA" value="f2int"></c:set>
																												</c:when>
																												<c:otherwise>
																													<c:set var="classIdiInt2CPA" value="f1int"></c:set>
																												</c:otherwise>
																											</c:choose>
																											<tr
																												class="<c:out value="${classIdiInt2CPA }"></c:out>">
																												<td style="text-align: center; width: 15%;"><label><c:out
																															value="${cronogramaCostoPorActividad.periodo }"></c:out>
																												</label></td>
																												<td style="text-align: center; width: 15%;"><label><c:out
																															value="${cronogramaCostoPorActividad.cantidad }"></c:out>
																														- <c:out
																															value="${costoPorActividad.descripcionUnidadMedida }"></c:out>
																												</label></td>
																												<td style="text-align: left; width: 60%;"><label><c:out
																															value="${cronogramaCostoPorActividad.fuenteFinanciadora.institucion.nombreInstitucion }"></c:out>
																												</label></td>
																												<td style="text-align: left; width: 10%;"><label><a	href="javascript:agregarObservacion('<c:out value="${cronogramaCostoPorActividad.cronogramaCostoActividadID  }" ></c:out>','<c:out value="${planOperativo.datoProyectoID }" ></c:out>','4','15','<c:out value="${planOperativo.datoPlanOperativoID }" ></c:out>')"
														class="linkSelecciona">Observacion</a></label></td>
																											</tr>
																										</c:forEach>
																									</tbody>
																								</table>
																							</div>
																						</td>
																					</tr>
																				</c:forEach>
																			</tbody>
																		</table>
																	</div></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</td>
									</tr>

								</c:forEach>
							</tbody>
						</table>
						<!-- <br />
						<table>
							<tr>
								<td><input type="button" onclick="fObtenerResultado(2)"
									value="Beneficiario" align="middle" /></td>
								<td><input type="button" onclick="fObtenerResultado(3)"
									value="Indicador" align="middle" /></td>

							</tr>
						</table>
						 -->
					</fieldset>
				</div>
			</fieldset>
		</div>
	</form:form>
</div>
