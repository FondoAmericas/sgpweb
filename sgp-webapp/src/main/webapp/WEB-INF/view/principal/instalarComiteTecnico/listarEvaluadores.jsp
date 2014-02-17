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
	var wid = window.innerWidth;
	var hei = window.innerHeight;
	function Resize() {
		self.resizeTo(wid + 5, hei + 80);
	}

	window.onresize = Resize;

	$(document).ready(function() {
		$('#enviar').click(function() {
			var selectedItems = new Array();

			$("input[@name='checkEvaluador[]']:checked").each(function() {
				evaluacionID = '#' + $(this).val();
				evaluacion = $(evaluacionID).attr('value');
				selectedItems.push($(this).val() + "," + evaluacion);
			});
			window.close();
			window.opener.loadPagina(selectedItems);

		});
	});

	function fMostrarNombre(valor) {
		if (valor == 100) {
			$('#divNombreEvaluador').show();
		} else {
			$('#divNombreEvaluador').hide();
		}

	}
</script>

<div class="form-clasico">
	<form:form name="formListarEvaluador"
		action="actionBuscarEvaluador.jspx">
		<div id="divMensaje"></div>
		<div class="encabezado">LISTA EVALUADOR</div>

		<table width="100%">
			<tr>
				<td style="width: 20%; text-align: right;"><label>Filtro:</label>
				</td>
				<td  style="width: 25%; text-align: left;">
				<select name="idFiltro" id="idFiltro"
					onchange="fMostrarNombre(this.value)" style="width: 250px;">
						<option value="-1">
							<c:out value="-- Seleccione --" />
						</option>
						<c:forEach items="${perfilUsuarios}" var="perfil">
							<option value="${perfil.perfilUsuarioID}">
								<c:out value="${perfil.descripcionPerfil}" />
							</option>
						</c:forEach>
						<option value="100">
							<c:out value="Nombre de Evaluador" />
						</option>
						<option value="101">
							<c:out value="Todos" />
						</option>
				</select>
				</td>
				<td  style="width: 25%; text-align: left;"><input value="Buscar Evaluador" type="submit" />
				</td>
				<td style="width: 30%; text-align: left;"><input type="button" id="enviar"
					value="Asignar Evaluador" />
				</td>
				</tr>
				<tr>
				<td style="width: 20%;"></td>
			<td colspan="3" style="width: 80%; text-align: left;">
					<div id="divNombreEvaluador" style="display: none;">
						<input type="text" name="buscar" style="width: 250px;"/>
					</div></td>
				

			</tr>
		</table>
		<br />

		<display:table uid="programa" name="${listaEvaluador}" defaultsort="1"
			defaultorder="ascending" pagesize="10" requestURI="" export="false"
			class="table-clasico" style="width:100%; font-size: 12px;">
			<display:caption>
				<label>Lista de Programas</label>
			</display:caption>
			<display:column title="Cargar">
				<input type="checkbox" name="checkEvaluador"
					value="${programa.datoUsuario.datoUsuarioID}">
			</display:column>
			<display:column title="Id" property="datoUsuario.datoUsuarioID" />
			<display:column property="datoUsuario.nombre" title="Nombre" />
			<display:column property="perfilUsuario.descripcionPerfil"
				title="Perfil" />
			<display:column title="Evaluaci&oacute;n">
				<select id="${programa.datoUsuario.datoUsuarioID}"
					name="tipoEvaluacion" onchange="fSeleccionarEvaluacion(this)">
					<c:forEach items="${listTipoEvaluacion}" var="evaluacion">
						<option value="${evaluacion.tablaEspecificaID}">
							<c:out value="${evaluacion.descripcionCabecera}" />
						</option>
					</c:forEach>
				</select>
			</display:column>
		</display:table>
	</form:form>
</div>
