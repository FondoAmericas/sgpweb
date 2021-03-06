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
<style>
div.ui-dialog {
	font-size: 62.5%;
}
</style>
<script type="text/javascript">
	var idProg;
	$(window).ready(function() {
		idProg = "<c:out value=" ${idPrograma}"></c:out>";

		var valor = document.getElementById('mensaje').value;

		if (valor != null && valor != "") {
			parent.loadPagina1(valor);
		}

	});
	$(function() {

		$("#divl").dialog({
			width : 230,
			height : 115,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
				}
			}
		});

	});

	function fEliminarEvaluadorAsignado(id) {

		var form = document.formListarEvaluadorAsignado;
		form.action = "actionEliminarEvaluadorAsignado.jspx?idEvaluador=" + id;

		form.submit();

	}
	function fAsignarEvaluador() {
		
		url = "showListarEvaluadores.jspx?idPrograma=" + idProg;
		fOpenModalDialog(url, '800', '500', '90', '60');
		/*x = window
				.open(
						url,
						"",
						"toolbar=0,width=600,height=400,location=0,resizable=0,fullscreen=0,status=0,scrollbars=0,left=400,top=200,screenX=400,screenY=200");

	*/}
	function loadPagina(lista1) {

		var form = document.formListarEvaluadorAsignado;
		form.action = "actionAsignarEvaluador.jspx?listaEvaluadores="
				+ lista1.join(";");
		form.submit();

	}
</script>

<div class="form-clasico">
	<form:form name="formListarEvaluadorAsignado" action="">
		<input type="hidden" name="mensaje" id="mensaje" value="${mensaje}" />

		<input  type="button"
			name="asignarEvaluador" value="Asignar Evaluador"
			onclick="fAsignarEvaluador()">
		<br />
		<c:if test="${listaEvaluadorAsignado==null}">
			<c:out value="El programa no tiene evaluadores asignados" />
		</c:if>
		<c:if test="${listaEvaluadorAsignado!=null}">
			<display:table uid="evaluador" name="${listaEvaluadorAsignado}"
				defaultsort="1" defaultorder="ascending" requestURI=""
				export="false" class="table-clasico"
				style="width:100%; font-size: 12px;">
				<display:caption>
					<label>Lista de Evaluadores Asignados</label>
				</display:caption>
				<display:column title="Nombre Completo">
				${evaluador.datoUsuario.nombre} ${evaluador.datoUsuario.paterno} ${evaluador.datoUsuario.materno}
				</display:column>
				<display:column title="Evaluaci&oacute;n">
					<c:forEach items="${listTipoEvaluacion}" var="evaluacion">
						<c:if
							test="${evaluacion.tablaEspecificaID== evaluador.fkIdtablaespTipoEvaluacion}">
							<c:out value="${evaluacion.descripcionCabecera}" />
						</c:if>
					</c:forEach>
				</display:column>
				<display:column title="Eliminar">
					<a class="ui-icon ui-icon-circle-close" style="cursor: pointer" title="Eliminar"
						href="javascript:fEliminarEvaluadorAsignado('${evaluador.evaluadorID}')">
					</a>
				</display:column>
			</display:table>
		</c:if>
	</form:form>
</div>
