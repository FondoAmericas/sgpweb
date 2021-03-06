<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@include file="/common/includesTaglibsGenerico.jsp"%>

<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/css/ui.multiselect.css" />

<script
	src="<%=request.getContextPath()%>/js/jquery-ui-1.8.12.custom.min.js"
	type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/grid.locale-es.js"
	type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/gestorAjax.js"
	type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.min.js"
	type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/grid.subgrid.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$.jgrid.no_legacy_api = true;
	$.jgrid.useJSON = true;
</script>

<script type="text/javascript">
	//var objXHR1;
/*
	$(function() {

		$("#divl").dialog({
			autoOpen : false,
			width : 230,
			height : 115,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
				}
			}
		});

	});*/
	$(document).ready(function() {

		$('#divPrograma').hide();

	});

	$(window).load(function() {
		//objXHR1 = new ObjetoAJAX();
		//objXHR1.enviar("cargarListaPrograma.jspx", "post", "mostrar2", null);
		var actualizar = document.getElementById('variable').value;
		if (actualizar == "actualizar") {
			$('#divPrograma').show();
			$('#divListaPrograma').hide();
			$('#divBuscarPrograma').hide();
		}

	});

	function fEliminarComiteTecnico(id) {
		var form = document.formInstalarComiteTecnico;
		form.action = "actionEliminarComiteTecnico.jspx?idEvaluador=" + id;
		form.submit();
	}
	function fCargarComiteTecnico(id) {

		var form = document.formInstalarComiteTecnico;
		form.action = "actionCargarPrograma.jspx?idPrograma=" + id
				+ "&variable=actualizar";
		form.submit();
	}

	function fAsignarPrograma() {
		fOpenModalDialog("showListarProgramas.jspx", '800', '500', '90', '60');
	}
	function loadPagina1(valor) {
		alert(valor);
		/*document.getElementById("divl").innerHTML = valor;

		$(function() {
			$('#divl').dialog('open');
			return false;
		});*/

	}
	function fCancelar() {

		var form = document.formInstalarComiteTecnico;
		form.action = "showInstalarComiteTecnico.jspx";
		form.submit();
		/* 	$('#divPrograma').hide();
		 $('#divListaPrograma').show();
		 $('#divBuscarPrograma').show(); */
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
		if (valor == 105) {
			$('#divlistTipoPeriodos').hide();
			$('#divlistTipoCuentas').hide();
			$('#divlistModalidadFinanciera').hide();
			$('#divSelect').hide();
			$('#divNombre').show();

		}

	}
</script>

<style>
#popup {
	position: absolute;
	border: 1px solid #666666;
	background-color: #F7F7F7;
	width: 200px;
	height: 100px;
	margin-top: 50px;
}
</style>


<div class="form-clasico">
	<div class="encabezado">INSTALAR COMITE TECNICO</div>
	<form:form name="formInstalarComiteTecnico"
		action="actionBuscarComiteTecnico.jspx" method="POST">

		<table id="list485"></table>
		<div id="plist485"></div>


		<table id="list14"></table>
		<div id="pager14"></div>

		<div id="divl"></div>
		<input type="hidden" id="variable" value="${actualizar}" />
		<br />
		<div id="divListaPrograma">
			<fieldset style="padding-left: 15px">
				<legend>Listado de Programas con Evaluadores Asignados</legend>
				<table style="width: 100%;">
					<tr>
						<td style="width: 20%; text-align: right;"><label>Filtro:</label></td>
						<td style="width: 20%;">
						<select name="idFiltro" id="idFiltro" style="width: 120px;"
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
								<option value="103">
									<c:out value="Nombre Programa" />
								</option>
								<option value="104">
									<c:out value="Todos" />
								</option>
						</select>
						</td>
						<td style="width: 20%;">
							<div id="divSelect">
								<select name="sele" disabled="disabled" style="width: 120px;">
									<option value="0">
										<c:out value="--Seleccionar--" />
									</option>
								</select>
							</div>
							<div id="divNombre" style="display: none;">
								<label>Nombre:</label><input type="text" name="buscarNombre" style="width: 120px;"/>
							</div>
							<div id="divlistTipoPeriodos" style="display: none;">
								<select name="idTipoPeriodos" id="idTipoPeriodos" style="width: 120px;">
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
								<select name="idTipoCuentas" id="idTipoCuentas" style="width: 120px;">
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
								<select name="idModalidadFinanciera" id="idModalidadFinanciera" style="width: 120px;">
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
						<td style="width: 20%;"><input value="Buscar Programa" type="submit" />
						</td>
						<td  style="width: 20%;"><input type="button" name="buscarPrograma"
							value="Asignar Evaluadores" onclick="fAsignarPrograma()"></td>
					</tr>
				</table>
				<br />
<!--
				<display:table uid="comite" name="${listComiteTecnico}"
					defaultsort="1" defaultorder="ascending" pagesize="10"
					export="false" class="table-clasico"
					style="width:100%; font-size: 12px;">
					<display:caption>
						<label>Lista de Programas</label>
					</display:caption>
					<display:column property="nombrePrograma" title="Programa" />
					<display:column property="duracionPrograma"
						title="Duracion Programa" />
					<display:column property="fechaConvocatoria"
						title="Fecha Convocatoria" />
					<display:column property="identificadorModFinan"
						title="Identificador" />
					<display:column title="Tipo Periodo">
						<c:forEach items="${listTipoPeriodos}" var="val">
							<c:if
								test="${val.tipoPeriodoID== comite.tipoPeriodo.tipoPeriodoID}">
								<c:out value="${val.descripPeriodo}" />
							</c:if>
						</c:forEach>
					</display:column>
					<display:column title="Tipo Cuentas">
						<c:forEach items="${listTipoCuentas}" var="val">
							<c:if
								test="${val.tablaEspecificaID== comite.fkIdtablaespTipoCuenta}">
								<c:out value="${val.descripcionCabecera}" />
							</c:if>
						</c:forEach>
					</display:column>
					<display:column title="Modalidad Financiera">
						<c:forEach items="${listModalidadFinanciera}" var="val">
							<c:if
								test="${val.tablaEspecificaID== comite.fkIdtablaespModalidadFinancia}">
								<c:out value="${val.descripcionCabecera}" />
							</c:if>
						</c:forEach>
					</display:column>
					<display:column title="Cargar">
						<a href="javascript:fCargarComiteTecnico('${comite.programaID}')"><span
							class="ui-icon ui-icon-circle-arrow-s" style="cursor: pointer"
							title="Cargar"></span>
						</a>
					</display:column>
				</display:table>  -->
				
				<table width="100%" class="table-clasico">
							<caption>
								<label>Lista de Programas</label>
							</caption>
							<thead>
								<tr>
									<td style="text-align: center; width: 20%;"><label>Programa</label>
									</td>
									<td style="text-align: center; width: 5%;"><label>Duracion</label>
									</td>
									<td style="text-align: center; width: 10%;"><label>Fecha <br/>Convocatoria</label>
									</td>
									<td style="text-align: center; width: 10%;"><label>Identificador</label>
									</td>
									<td style="text-align: center; width: 10%;"><label>Tipo<br />Periodo</label>
									</td>
									<td style="text-align: center; width: 15%;"><label>Tipo<br />Cuenta</label>
									</td>
									<td style="text-align: center; width: 15%;"><label>Modalidad<br />Financiamiento</label>
									</td>
									<td style="text-align: center; width: 10%;"><label>Cargar</label>
									</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listComiteTecnico }"
									var="proyecto" varStatus="indice">
									<c:choose>
										<c:when test="${indice.count %2== 0}">
											<c:set var="classIdi" value="f2"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdi" value="f1"></c:set>
										</c:otherwise>
									</c:choose>
									<tr class="<c:out value="${classIdi}"></c:out>">
									<td style="text-align: left; width: 20%;"><label><c:out value="${proyecto.nombrePrograma }"></c:out></label>
									</td>
									<td style="text-align: center; width: 5%;"><label><c:out value="${proyecto.duracionPrograma }"></c:out></label>
									</td>
									<td style="text-align: center; width: 10%;"><label><c:out value="${proyecto.fechaConvocatoria }"></c:out></label>
									</td>
									<td style="text-align: center; width: 10%;"><label><c:out value="${proyecto.identificadorModFinan }"></c:out></label>
									</td>
									<td style="text-align: center; width: 10%;"><label><c:forEach items="${listTipoPeriodos}" var="val">
							<c:if
								test="${val.tipoPeriodoID== proyecto.tipoPeriodo.tipoPeriodoID}">
								<c:out value="${val.descripPeriodo}" />
							</c:if>
						</c:forEach></label>
									</td>
									<td style="text-align: center; width: 15%;"><label><c:forEach items="${listTipoCuentas}" var="val">
							<c:if
								test="${val.tablaEspecificaID== proyecto.fkIdtablaespTipoCuenta}">
								<c:out value="${val.descripcionCabecera}" />
							</c:if>
						</c:forEach></label>
									</td>
									<td style="text-align: center; width: 15%;"><label><c:forEach items="${listModalidadFinanciera}" var="val">
							<c:if
								test="${val.tablaEspecificaID== proyecto.fkIdtablaespModalidadFinancia}">
								<c:out value="${val.descripcionCabecera}" />
							</c:if>
						</c:forEach></label>
									</td>
									<td style="text-align: center; width: 10%;"><label><a href="javascript:fCargarComiteTecnico('${proyecto.programaID}')" class="linkSelecciona">Detalle<br/>Programa</a></label>
									</td>
									</tr>
									</c:forEach>
									</tbody>
									</table>
				
				<br />
			</fieldset>
		</div>

		<div id="divPrograma">
			<!-- Inicio del tag crearPrograma -->

			<fieldset style="padding-left: 15px">
				<legend>DATOS PROGRAMA</legend>
				<table width="100%">
					<tr>
						<td style="text-align: right; width: 25%;"><label>Programa:</label>
						</td>
						<td style="text-align: left;width: 25%;"><input type="hidden" name="nombrePrograma"
							id="nombrePrograma" disabled="disabled" value="${nombrePrograma}" />
							<label><c:out value="${nombrePrograma}"></c:out> </label>
						</td>
						<td style="text-align: right;width: 25%;"><label>Indicador:</label>
						</td>
						<td style="text-align: left;width: 25%;"><input type="hidden" name="identificadorModFinan"
							disabled="disabled" id="identificadorModFinan"
							value="${identificadorModFinan}" />
							<label><c:out value="${identificadorModFinan}"></c:out> </label>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%"><label>Tipo Cuenta:</label>
						</td>
						<td style="text-align: left;width: 25%;"><!-- <select name="idTipoCuentas" id="idTipoCuentas"
							disabled="disabled">
								<option value="0">
									<c:out value="-- Seleccionar --" />
								</option>
								<c:forEach items="${listTipoCuentas}" var="tipoCuenta">
									<c:if test="${ idTipoCuentas != tipoCuenta.tablaEspecificaID}">
										<option value="${tipoCuenta.tablaEspecificaID}">
											<c:out value="${tipoCuenta.descripcionCabecera}" />
										</option>
									</c:if>
									<c:if test="${ idTipoCuentas == tipoCuenta.tablaEspecificaID}">
										<option value="${tipoCuenta.tablaEspecificaID}"
											selected="selected">
											<c:out value="${tipoCuenta.descripcionCabecera}" />
											<c:set value="${tipoCuenta.descripcionCabecera}" var="cuenta"></c:set>
										</option>
									</c:if>

								</c:forEach>
						</select> -->
						<label><c:forEach items="${listTipoCuentas}" var="tipoCuenta">
									<c:if test="${ idTipoCuentas == tipoCuenta.tablaEspecificaID}">
											<c:out value="${tipoCuenta.descripcionCabecera}" />
									</c:if>
								</c:forEach></label>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%;"><label>Modalidad:</label>
						</td>
						<td style="text-align: left; width: 25%;"><!-- <select name="idModalidadFinanciera"
							id="idModalidadFinanciera" disabled="disabled">
								<option value="0">
									<c:out value="-- Seleccionar --" />
								</option>
								<c:forEach items="${listModalidadFinanciera}" var="modali">
									<c:if
										test="${ idModalidadFinanciera == modali.tablaEspecificaID}">
										<option value="${modali.tablaEspecificaID}"
											selected="selected">
											<c:out value="${modali.descripcionCabecera}" />
										</option>
									</c:if>

								</c:forEach>
						</select> -->
						<label><c:forEach items="${listModalidadFinanciera}" var="modali">
									<c:if
										test="${ idModalidadFinanciera == modali.tablaEspecificaID}">
											<c:out value="${modali.descripcionCabecera}" />
									</c:if>
								</c:forEach></label>
						</td>
						<td style="text-align: right; width: 25%;"><label>Fecha Convocatoria:</label>
						</td>
						<td style="text-align: left; width: 25%;"><input type="hidden" name="fechaConvocatoria"
							id="fechaConvocatoria" value="${fechaConvocatoria}"
							disabled="disabled" />
							<label><c:out value="${fechaConvocatoria}"></c:out></label>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%;"><label>Duraci&oacute;n Max: </label>
						</td>
						<td style="text-align: left; width: 25%;"><input type="hidden" name="duracionPrograma"
							id="duracionPrograma" value="${duracionPrograma}"
							disabled="disabled" /><label><c:out value="${duracionPrograma} meses"></c:out> </label>
						</td>
						<td style="text-align: right; width: 25%;"><label>Periodo:</label>
						</td>
						<td style="text-align: left; width: 25%;"><!-- <select name="idTipoPeriodos" id="idTipoPeriodos"
							disabled="disabled">
								<option value="0">
									<c:out value="--Seleccionar--" />
								</option>
								<c:forEach items="${listTipoPeriodos}" var="tipoPeriodo">
									<c:if test="${ idTipoPeriodos == tipoPeriodo.tipoPeriodoID}">
										<option value="${tipoPeriodo.tipoPeriodoID}"
											selected="selected">
											<c:out value="${tipoPeriodo.descripPeriodo}" />
										</option>
									</c:if>
								</c:forEach>
						</select> -->
						<label><c:forEach items="${listTipoPeriodos}" var="tipoPeriodo">
									<c:if test="${ idTipoPeriodos == tipoPeriodo.tipoPeriodoID}">
											<c:out value="${tipoPeriodo.descripPeriodo}" />
									</c:if>
								</c:forEach></label>
						</td>
					</tr>
					<tr>
						<td colspan="3" style="width: 75%;"></td>
						<td  style="text-align: left; width: 25%;"><input style="width: 98px; height: 19px;" type="button"
					value="Regresar" onclick="fCancelar()" /></td>
					</tr>
				</table>
				<br />
				<fieldset>
					<legend>PUNTAJE EVALUACI&Oacute;N</legend>

					<table width="100%">
						<tr>
							<c:if test="${chkRqPYG== null || chkRqPYG==0}">
								<td colspan="4"><input type="checkbox" name="chkRqPYG"
									disabled="disabled" id="chkRqPYG"><label>
										Requiere Proyecto Global?</label>
								</td>
							</c:if>
							<c:if test="${chkRqPYG==1}">
								<td colspan="4"><input type="checkbox" name="chkRqPYG"
									id="chkRqPYG" checked="checked" disabled="disabled"><label>
										Requiere Proyecto Global?</label>
								</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${chkRqEI== null || chkRqEI==0}">
								<td><input type="checkbox" name="chkRqEI" id="chkRqEI"
									disabled="disabled"><label>Requiere
										Evaluaci&oacute;n Institucional?</label>
								</td>
							</c:if>
							<c:if test="${chkRqEI== 1}">
								<td><input type="checkbox" name="chkRqEI" id="chkRqEI"
									checked="checked" " disabled="disabled"><label>RequiereEvaluaci&oacute;n
										Institucional?</label>
								</td>
							</c:if>
							<td colspan="3">
								<table width="100%">
									<tr>
										<td><label>Puntaje Min:</label>
										</td>
										<td><input type="text" name="puntajeMinRqEI"
											id="puntajeMinRqEI" value="${puntajeMinRqEI}"
											disabled="disabled" style="width: 40px;">
										</td>
										<td><label>Puntaje Max:</label>
										</td>
										<td><input type="text" name="puntajeMaxRqEI"
											id="puntajeMaxRqEI" value="${puntajeMaxRqEI}"
											disabled="disabled" style="width: 40px;">
										</td>
										<td><label>Minimo Aprobatorio:</label>
										</td>
										<td><input type="text" name="minimoAprobatorioEI"
											id="minimoAprobatorioEI" value="${minimoAprobatorioEI}"
											disabled="disabled" style="width: 40px;">
										</td>
									</tr>
								</table></td>
						</tr>
						<tr>
							<c:if test="${chkRqET== null || chkRqET==0 }">
								<td><input type="checkbox" name="chkRqET" id="chkRqET"
									onclick="fActivaInput()" disabled="disabled"><label>Requiere
										Evaluaci&oacute;n T&eacute;cnica?</label></td>
							</c:if>
							<c:if test="${chkRqET== 1}">
								<td><input type="checkbox" name="chkRqET" id="chkRqET"
									checked="checked" onclick="fActivaInput()" disabled="disabled"><label>Requiere
										Evaluaci&oacute;n T&eacute;cnica?</label></td>
							</c:if>
							<td colspan="3">
								<table width="100%">
									<tr>
										<td><label>Puntaje Min:</label>
										</td>
										<td><input type="text" name="puntajeMinRqET"
											id="puntajeMinRqET" value="${puntajeMinRqET}"
											disabled="disabled" style="width: 40px;">
										</td>
										<td><label>Puntaje Max:</label>
										</td>
										<td><input type="text" name="puntajeMaxRqET"
											id="puntajeMaxRqET" value="${puntajeMaxRqET}"
											disabled="disabled" style="width: 40px;">
										</td>
										<td><label>Minimo Aprobatorio:</label>
										</td>
										<td><input type="text" name="minimoAprobatorioET"
											id="minimoAprobatorioET" value="${minimoAprobatorioET}"
											disabled="disabled" style="width: 40px;">
										</td>
									</tr>
								</table></td>
						</tr>
						<tr>
							<c:if test="${chkRqPY== null||chkRqPY== 0}">
								<td><input type="checkbox" name="chkRqPY" id="chkRqPY"
									onclick="fActivaInput()" disabled="disabled"><label>Requiere
										Evaluaci&oacute;n Proyecto?</label>
								</td>
							</c:if>
							<c:if test="${chkRqPY== 1}">
								<td><input type="checkbox" name="chkRqPY" id="chkRqPY"
									checked="checked" onclick="fActivaInput()" disabled="disabled"><label>Requiere
										Evaluaci&oacute;n Proyecto?</label>
								</td>
							</c:if>
							<td colspan="3">
								<table width="100%">
									<tr>
										<td><label>Puntaje Min:</label>
										</td>
										<td><input type="text" name="puntajeMinRqPY"
											id="puntajeMinRqPY" value="${puntajeMinRqPY}"
											disabled="disabled" style="width: 40px;">
										</td>
										<td><label>Puntaje Max:</label>
										</td>
										<td><input type="text" name="puntajeMaxRqPY"
											id="puntajeMaxRqPY" value="${puntajeMaxRqPY}"
											disabled="disabled" style="width: 40px;">
										</td>
										<td><label>Minimo Aprobatorio:</label>
										</td>
										<td><input type="text" name="minimoAprobatorioPY"
											id="minimoAprobatorioPY" value="${minimoAprobatorioPY}"
											disabled="disabled" style="width: 40px;">
										</td>
									</tr>
								</table></td>
						</tr>
					</table>
				</fieldset>
				<br />
				<table style="width: 100%;">
					<tr>
						<td style="vertical-align: top; width: 50%;">
							<fieldset>
								<legend>RESTRICCI&Oacute;N </legend>
								<table class="table-clasico" style="width: 100%;">
									<caption style="text-align: center;">
										<label>Lista de Restricciones</label>
									</caption>
									<thead>
										<tr>
											<td style="text-align: center;width: 50%;"><label>Restriccion</label>
											</td>
											<td style="text-align: center;width: 25%;"><label>%Minimo</label>
											</td>
											<td  style="text-align: center;width: 25%;"><label>%Maximo</label>
											</td>
									</thead>
									<tbody>
										<c:forEach items="${listaRestriccion}" var="restriccion">
											<tr>
												<c:if
													test="${restriccion.fkIdtablaespTipoRestriccionProg==176}">
													<td colspan=1><label>Mano de Obra</label>
													</td>
												</c:if>
												<c:if
													test="${restriccion.fkIdtablaespTipoRestriccionProg==177}">
													<td colspan=1><label>Inversiones</label>
													</td>
												</c:if>
												<c:if
													test="${restriccion.fkIdtablaespTipoRestriccionProg==178}">
													<td colspan=1><label>Gastos Administrativos</label>
													</td>
												</c:if>

												<td colspan=1><label>${restriccion.porcenMinimo}</label>
												</td>
												<td colspan=1><label>${restriccion.porcenMaximo}</label>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</fieldset></td>
						<td style="vertical-align: top; width: 50%;">
							<fieldset>
								<legend>ACTIVIDAD OBLIGATORIA</legend>
								<table class="table-clasico" style="width: 100%;">
									<caption style="text-align: center;">
										<label>Lista Actividad Obligatoria</label>
									</caption>
									<thead>
										<tr>
											<td style="text-align: center;"><label>Actividad</label>
											</td>
									<tbody>
										<c:forEach items="${listaActividadObligatoria}"
											var="actividad">
											<tr>
												<td colspan=1><label>${actividad.tipoActividadObligatoriaPrograma.descripcion}</label>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</fieldset></td>
					</tr>
				</table>

				<c:if test="${chkRqEI== 1 || chkRqET==1 || chkRqPY== 1 }">
					<div>
						<fieldset>
							<legend>EVALUADORES</legend>
							<iframe src="showListarEvaluadorAsignado.jspx" id="window"
								frameborder="0" width="100%" height="350px" scrolling="auto"></iframe>
						</fieldset>
					</div>
				</c:if>
				
			</fieldset>

		</div>

	</form:form>
</div>
