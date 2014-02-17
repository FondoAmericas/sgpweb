<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>APROBAR PROYECTO EN CONSEJO</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/viewMain.css" ></c:url>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/estiloContabilidad.css"></c:url>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/estiloGeneral.css"></c:url>" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/gestorAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/AtssefGrid.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/validador.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#grillaListaProyectos").load("grillaListaProyectos.jspx", {
			programaId : $("#sltPrograma").val()
		});
	});
</script>

<script type="text/javascript">
	function fCargarProgramas() {
		$("#sltPrograma").load("cargarProgramasDeModalidad.jspx", {
			modalidadFinanciamiento : $("#sltModalidadFinanciamiento").val(),
			metodo : "programa"
		}, function(){
			$("#grillaListaProyectos").load("grillaListaProyectos.jspx", {
				programaId : $("#sltPrograma").val()
			});
		});
	}

	function fCargarProyectos() {
		$("#grillaListaProyectos").load("grillaListaProyectos.jspx", {
			programaId : $("#sltPrograma").val(),
			programaNombre : $("#sltPrograma option:selected").html()
		});
	}
</script>
</head>
<body>
	<div class="form-clasico">
		<div class="encabezado">APROBAR PROYECTOS EN CONSEJO ADMINISTRATIVO</div>
		<br>
		<fieldset style="padding-left: 10px">
			<legend>Listado de Proyectos a Evaluar</legend>
			<table width="100%;">
				<tr>
					<td style="width: 25%; text-align: right;"><label>Modalidad
							Financiamiento:</label>
					</td>
					<td style="width: 25%; text-align: left;"><label> <select
							name="sltModalidadFinanciamiento" id="sltModalidadFinanciamiento"
							onchange="javascript:fCargarProgramas();">
								<option value="0">
									<c:out value="-- Modalidad --" />
								</option>
								<c:forEach items="${listModalidadFinanciamiento}"
									var="modalidad">
									<option value="${modalidad.tablaEspecificaID}">
										<c:out value="${modalidad.descripcionCabecera}" />
									</option>
								</c:forEach>
						</select> </label>
					</td>
					<td style="width: 25%; text-align: right;"><label>Programa:</label>
					</td>
					<td style="width: 25%; text-align: left;"><label> <select
							name="sltPrograma" id="sltPrograma"
							onchange="javascript:fCargarProyectos();">
								<option value="0">
									<c:out value="-- Programa --" />
								</option>

						</select> </label>
					</td>
				</tr>
				<tr>
					<td colspan="4" style="width: 100%; text-align: left; padding-top: 10px; padding-bottom: 5px;"><label style="color: red; font-size: 13px;" >* Si proyecto cuenta con plan operativo, el estado se desabilita.</label></td>
				</tr>
			</table>
		</fieldset>
		<br>
		<div id="grillaListaProyectos"></div>
	</div>
</body>
</html>