<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@include file="/common/includesTaglibsGenerico.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/displaytagex.css"
	type="text/css" />
<link type="text/css"
	href="<%=request.getContextPath()%>/css/custom-theme/jquery-ui-1.8.12.custom.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-ui-1.8.12.custom.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.ui.datepicker-es.js"></script>
<link type="image/x-icon"
	href="<%=request.getContextPath()%>/images/americas.ico"
	rel="shortcut icon" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/viewMain.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/estiloContabilidad.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/estiloGeneral.css" />
	
<script type="text/javascript">
	var tipoActividad = 0;

	$(window).load(function() {
		tipoActividad = document.getElementById('tipoActividad').value;
		fMostrar();

	});
	function fMostrar() {
		if (tipoActividad == 1) {
			$('#costo').show();
			$('#meta').hide();
			$('#riesgo').hide();
		}
		if (tipoActividad == 2) {
			$('#meta').show();			
			$('#cronograma').hide();
			$('#costo').hide();
			$('#riesgo').hide();
		}
		if (tipoActividad == 3) {
			$('#riesgo').show();
			$('#cronograma').hide();
			$('#costo').hide();
			$('#meta').hide();

		}
		if (tipoActividad == 4) {
			$('#costo').show();
			$('#cronograma').show();
			$('#riesgo').hide();			
			$('#meta').hide();

		}
		if (tipoActividad == 0) {
			$('#costo').hide();
			$('#meta').hide();
			$('#riesgo').hide();
			$('#cronograma').hide();


		}

	}

	function fCargarCronograma(actidad){
		var form = document.formDetalleActividad;
		form.tipoActividad.value = 4;
		form.actividadID.value = actidad;
		form.submit();
	}
	function fCargarObservaciones(costoActividadID) {
		var url = "observacionCostoActividad.jspx?costoActividadID=" + costoActividadID;
		var stiloPopUp='dialogWidth=600px; dialogHeight=450px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
		window.showModalDialog(url,'',stiloPopUp);
		}
	
	function fCargarObservacionesCCA(cronogramaCostoActividadID) {
		var url = "observacionCronogramaCostoActividad.jspx?cronogramaCostoActividadID=" + cronogramaCostoActividadID;
		var stiloPopUp='dialogWidth=600px; dialogHeight=450px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
		window.showModalDialog(url,'',stiloPopUp);
		}



	</script>

<div class="form-clasico">
	<form:form name="formDetalleActividad"
		action="showDetalleActividad.jspx">
<div class="encabezado">Actividad</div>
		<input type="hidden" name="tipoActividad" id="tipoActividad"
			value="${tipoActividad}">
		<input type="hidden" name="actividadID" id="actividadID"
			value="${actividadID}">
		
		<div id="costo" style="display: none;">
		<fieldset>
			<legend>Lista Costo Actividad</legend>
			<display:table uid="costo" name="${listaCosto}" class="table-clasico" style="width: 100%; font-size:12px;"
			defaultsort="1" defaultorder="ascending" pagesize="12" requestURI=""
			export="false" >
			<display:caption>
							<label>Lista Costo Actividad</label>
							</display:caption>
				<display:column property="descripcionPartidaEspecifica"
					title="Partida Especifica" sortable="true" />
				<display:column property="unidadMedidaNombre"
					title="Unidad Medida" sortable="true" />
				<display:column property="cantidadTotal"
					title="Cantidad Total" sortable="true" />
				<display:column property="tipoMonedaPrecioUnitarioNombre"
					title="Moneda Precio Unitario" sortable="true" />
				<display:column property="precioUnitario"
					title="Moneda Monto Total" sortable="true" />
						<display:column property="tipoMonedaMontoTotalNombre"
					title="precioUnitario" sortable="true" />
						<display:column property="precioUnitario"
					title="Precio Unitario" sortable="true" />
						<display:column property="montoTotal"
					title="Monto Total" sortable="true" />
								<display:column title="Cronograma">
					<a href="javascript:fCargarCronograma('${costo.costoActividadID}')"><span
						class="ui-icon ui-icon-circle-arrow-s" style="cursor: pointer"
						title="Cronograma"></span>
					</a>	
				</display:column>
					<display:column title="Observaciones">
					<a href="javascript:fCargarObservaciones('${costo.costoActividadID}')"><span
						class="ui-icon ui-icon-circle-arrow-s" style="cursor: pointer"
						title="Observaciones"></span>
					</a>
				</display:column>
				</display:table>
				</fieldset>
		</div>
	<div id="cronograma" style="display: none;">
		
		<fieldset>
			<legend>Cronograma Costo Activida </legend>
		<display:table uid="cronograma" name="${listarCronogramaActividad}"
			defaultsort="1" defaultorder="ascending" pagesize="12" requestURI=""
			export="false" class="table-clasico" style="width: 100%; font-size: 12px;">
				<display:caption>
							<label>Lista Cronograma Actividad</label>
							</display:caption>
				<display:column 
					title="Periodo" sortable="true" >
					<c:forEach items="${listPeriodo}" var="val">
								<c:out value="${val.numeroPerido}" />
					</c:forEach>
				</display:column>
				<display:column 
					title="Cantidad" sortable="true" >
				<c:forEach items="${listPeriodo}" var="val">
						<c:out value="${val.montoTotal}" />
					</c:forEach>
					</display:column>
				<display:column property="fuenteFinanciamiento"
					title="Fuente Financiamiento" sortable="true" />		
			<display:column title="Observaciones">
					<a href="javascript:fCargarObservacionesCCA('${cronograma.cronogramaCostoActividadID}')"><span
						class="ui-icon ui-icon-circle-arrow-s" style="cursor: pointer"
						title="Observaciones"></span>
					</a>
				</display:column>
				</display:table>
		
				</fieldset>
		</div>
<div id="meta" style="display: none;">
	<fieldset>
		<legend>Lista Meta</legend>
		<display:table uid="meta" name="${listaMeta}" class="table-clasico" style="width: 100%; font-size: 12px;"
			defaultsort="1" defaultorder="ascending" pagesize="12" requestURI=""
			export="false" >
			<display:caption>
							<label>Lista Meta</label>
							</display:caption>
			<display:column property="cantidadMetaActividad" title="Cantidad Meta"
				sortable="true" />
			<display:column property="cantidadMetaActividadEjecutada" title="Cantidad Meta Ejecutada"
				sortable="true" />
			<display:column property="logroAlcanzado"
				title="Logro Alcanzado"  />
			<display:column property="contribucionProposito"
				title="Contribucion Proposito" />
		</display:table>
	</fieldset>
</div>
<div id="riesgo" style="display: none;">
	<fieldset>
		<legend>Lista Riesgo</legend>
		<display:table uid="riesgo" name="${listaRiesgo}" defaultsort="1" class="table-clasico" style="width: 100%; font-size: 12px;"
			defaultorder="ascending" pagesize="12" requestURI="" export="false"
			>
			<display:caption>
							<label>Lista Riesgo</label>
							</display:caption>
<display:column property="descripcionRiesgo" title="Riesgo"
				/>
			<display:column property="mitigacionRiesgo" title="Mitigacion"
				/>
					</display:table>
	</fieldset>
</div>
</form:form>
</div>
