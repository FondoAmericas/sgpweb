<%@include file="includesTaglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><spring:message code="web.app.title"></spring:message></title>
	<script>
	
		function soloNumero(e) {
		    if ([e.keyCode||e.which]==8 || [e.keyCode||e.which]==9 || [e.keyCode||e.which]==46) 
		        return true;
		    if ([e.keyCode||e.which] < 48 || [e.keyCode||e.which] > 57)
		        e.preventDefault? e.preventDefault() : e.returnValue = false;
		}
		function submitFormBeneficiarioResultado() {
			document.formBeneficiarioResultado.action = "actionSaveBeneficiarioResultado.jspx";
			document.formBeneficiarioResultado.submit();
		}
	
		function goBack(url) {
			goTo(url);
		}
		function goTo(url) {
			window.location = url;
		}
		
		function fSubmitFormBeneficiarioProyecto(){
			if(confirm("\u00BFEst\u00E1 seguro de guardar los datos seleccionados?")){
			   document.formBeneficiarioProyecto.action = "actionSaveBeneficiarioProyecto.jspx";
			   //window.close();
			   document.formBeneficiarioProyecto.submit();
			   window.opener.fRefresh("500");
			}
		}	
		
	</script>
</head>
<body style="font-family: Arial; font-size: smaller;"> 

<div class="form-clasico">
    <fieldset><legend>Beneficiarios Proyecto</legend>
		<br>

        <form name="formBeneficiarioProyecto" id="formBeneficiarioProyecto" method="post" action="">
         <input type="hidden" id="datoPlanOperativoID" name="datoPlanOperativoID" value="${planOperativo.datoPlanOperativoID}" />
	     <input type="hidden" id="resultadoID" name="resultadoID" value="${resultado.resultadoID}" />
		<table class="table-clasico" width="100%">
		<caption>Beneficiarios Resultado</caption>
			<thead>
			<tr>
			    <th align="center"><label><input id="marcarTodo" type="checkbox" value="true"  name="chkregistroTodo" onclick="marcarTodos('formBeneficiarioProyecto')"></label></th>
				<th align="center"><label>Tipo Beneficiario</label></th>
				<th align="center"><label>Caracteristicas</label></th>
				<th align="center"><label>Cantidad Programada</label></th>
				<th align="center"><label>Descripcion</label></th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="beneficiario" items="${listaBeneficiarioProyecto}" varStatus="indice">
	<c:choose>
										<c:when test="${indice.count %2== 0}">
											<c:set var="classIdi" value="f2"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdi" value="f1"></c:set>
										</c:otherwise>
									</c:choose>
	<tr class="<c:out value="${classIdi}"></c:out>">
				    <th align="center"><label>&nbsp;<input id="data" type="checkbox" value="${beneficiario.beneficiariosPorResultadoID}"  name="chkregistro" onclick="marcar('formBeneficiarioProyecto')"></label></th>
					<td align="left"><c:out value="${beneficiario.tipoBeneficiarioNombre}"></c:out></td>
					<td align="left"><c:out value="${beneficiario.caracteristicasPoblacion}"></c:out></td>
					<td align="left"><c:out value="${beneficiario.cantidadProgramado} - ${beneficiario.estratoNombre}"></c:out></td>
					<td align="left"><c:out value="${beneficiario.descripcion}"></c:out></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
        </form>
		<table border="0" width="100%">
			<tr>
				<td style="width: 90%;"></td>
				<td><input type="button" value="Cerrar" id="idBtnCerrar" /></td>
				<td style="text-align: center; width: 10%;">
					<input type="button" value="Agregar" onclick="fSubmitFormBeneficiarioProyecto()" >
				</td>
			</tr>
			</table>
    </fieldset>
 </div>
</body>
</html>
