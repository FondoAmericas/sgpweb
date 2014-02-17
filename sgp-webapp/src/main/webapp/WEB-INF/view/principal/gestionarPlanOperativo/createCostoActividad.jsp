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
		function onChangeCategoriaActividad(){
		    var selectedIndex = document.formCostoActividad.categoriaActividadID.selectedIndex;
		    var optionValue = document.formCostoActividad.categoriaActividadID.options[selectedIndex].value;
	
		    var categoriaActividadID = optionValue;
		    
			var url = "createCostoActividad.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID}&actividadID=${actividad.actividadID}&categoriaActividadID="+categoriaActividadID;
			goTo(url);
		}
		function onChangePartidaGenerica(){
			var selectedIndex1 = document.formCostoActividad.categoriaActividadID.selectedIndex;
		    var optionValue1 = document.formCostoActividad.categoriaActividadID.options[selectedIndex1].value;
		    var categoriaActividadID = optionValue1;
		    
		    var selectedIndex2 = document.formCostoActividad.partidaGenericaID.selectedIndex;
		    var optionValue2 = document.formCostoActividad.partidaGenericaID.options[selectedIndex2].value;
		    var partidaGenericaID = optionValue2;
		    
			var url = "createCostoActividad.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID}&actividadID=${actividad.actividadID}&categoriaActividadID="+categoriaActividadID + "&partidaGenericaID=" + partidaGenericaID;
			goTo(url);
		}
		function submitFormCostoActividad() {
			document.formCostoActividad.action = "actionSaveCostoActividad.jspx";
			document.formCostoActividad.submit();
		}
		function goTo(url) {
			window.location = url;
		}
		function goBack(url) {
			goTo(url);
		}
	</script>
</head>
<body>
<div class="form-clasico">
	<c:if test="${!empty alterMessage}">
		<fieldset><legend>Mensajes de Alertas</legend>
			<label style="color:red;" for="alterMessage">${alterMessage}</label>
		</fieldset>
	</c:if>
<form:form name="formCostoActividad" method="post" action="">
	<input type="hidden" id="datoPlanOperativoID" name="datoPlanOperativoID" value="${planOperativo.datoPlanOperativoID}">
	<input type="hidden" id="resultadoID" name="resultadoID" value="${resultado.resultadoID}">
	<input type="hidden" id="actividadID" name="actividadID" value="${actividad.actividadID}">

    <fieldset><legend>Crear Costo por Actividad</legend>
		<br>
		<table border="0" width="100%">
			<tr>
				<td style="text-align: right; width: 20%;"><label>Proyecto Codigo:</label></td>
				<td style="text-align: left; width: 30%;">
					<label for="proyectoCodigo">${planOperativo.codigoProyecto}</label>
				</td>
				<td style="text-align: right; width: 20%;"><label>Proyecto Nombre:</label></td>
				<td style="text-align: left; width: 30%;">
					<label for="proyectoNombre">${planOperativo.nombreProyecto}</label>
				</td>
			</tr>
			<tr>
				<td style="text-align: right; width: 20%;"><label>Plan Operativo:</label></td>
				<td style="text-align: left; width: 30%;">
					<label for="planOperativo">${planOperativo.version}</label>
				</td>
				<td style="width: 50%;" colspan="2"></td>
			</tr>
			<tr>
				<td style="text-align: right; width: 20%;"><label>Resultado:</label></td>
				<td style="text-align: left; width: 30%;">
					<label for="resultadoNombre">${resultado.definicionResultado}</label>
				</td>				
				<td style="text-align: right; width: 20%;"><label>Actividad:</label></td>
				<td style="text-align: left; width: 30%;">
					<label for="resultadoNombre">${actividad.nombreActividad}</label>
				</td>				
			</tr>
			<tr>
				<td style="text-align: right; width: 20%;"><label>Tipo Actividad:</label></td>
				<td style="text-align: left; width: 80%;" colspan="3">
					<label for="resultadoNombre">${actividad.tipoActividadNombre}</label>
				</td>				
			</tr>
			<tr>
				<th align="left" colspan="4"><br>Informacion del Costo por Actividad</th>
			</tr>
			<c:if test="${valorRubro == 1}" >
				<tr>
					<td style="text-align: right; width: 20%;"><label>Categoria Actividad:</label></td>
					<td style="text-align: left; width: 30%;" >
						<select id="categoriaActividadID" name="categoriaActividadID" style="width: 100px;" onchange='onChangeCategoriaActividad();'>
							<option value="0">--Categoria--</OPTION> 
							<c:forEach items="${listaCategoriaActividad}" var="lista">  
		 						<option value="${lista.categoriaActividadID}" ${lista.categoriaActividadID == categoriaActividadIDSelected ? 'selected' : ''}>${lista.descripcionCategoriaActividad}</option>
							</c:forEach>
						</select>
					</td>
					<td style="text-align: right; width: 20%;"><label>Rubro Generico:</label></td>
					<td style="text-align: left; width: 30%;">
						<select id="rubroGenericoID" name="rubroGenericoID" style="width: 100px;">
							<option value="0">--Rubro--</OPTION>
							<c:forEach items="${listaRubroGenerico}" var="lista">  
		 							<option value="${lista.rubroGenericoID}">${lista.descripEspecificacionRubroGenerico}</option>  
							</c:forEach>
						</select>
					</td>
					</tr>
					<tr>
					<td style="text-align: right; width: 20%;"><label>Partida Generica:</label></td>
					<td style="text-align: left; width: 30%;">
						<select id="partidaGenericaID" name="partidaGenericaID" style="width: 100px;" onchange='onChangePartidaGenerica();' >
							<option value="0">--Seleccionar--</option>
							<c:forEach items="${listaPartidaGenerica}" var="lista">  
		 							<option value="${lista.partidaGenericaID}" ${lista.partidaGenericaID == partidaGenericaIDSelected ? 'selected' : ''}>${lista.descripcionPartidaGenerica}</option>
							</c:forEach>
						</select>
					</td>
					<td style="text-align: right; width: 20%;"><label>Partida Especifica:</label></td>
					<td style="text-align: left; width: 30%;">
						<select id="partidaEspecificaID" name="partidaEspecificaID" style="width: 100px;">
							<option value="0">--Seleccionar--</OPTION>
							<c:forEach items="${listaPartidaEspecifica}" var="lista">  
		 							<option value="${lista.partidaEspecificaID}">${lista.descripcionPartidaEspecifica}</option>  
							</c:forEach>
						</select>
					</td>
				</tr>    
			</c:if>
			<c:if test="${valorRubro != 1}" >
				<tr>
					<td style="text-align: right; width: 20%;"><label>Categoria Actividad:</label></td>
					<td style="text-align: left; width: 80%;" colspan="3">
						<select id="categoriaActividadID" name="categoriaActividadID" style="width: 100px;" onchange='onChangeCategoriaActividad();' >
							<option value="0">--Categoria--</OPTION>
							<c:forEach items="${listaCategoriaActividad}" var="lista">  
		 							<option value="${lista.categoriaActividadID}" ${lista.categoriaActividadID == categoriaActividadIDSelected ? 'selected' : ''}>${lista.descripcionCategoriaActividad}</option>  
							</c:forEach>
						</select>
					</td>
					</tr>
					<tr>
					<td style="text-align: right; width: 20%;"><label>Partida Generica:</label></td>
					<td style="text-align: left; width: 30%;">
						<select id="partidaGenericaID" name="partidaGenericaID" style="width: 100px;" onchange='onChangePartidaGenerica();' >
							<option value="0">--Seleccionar--</option>
							<c:forEach items="${listaPartidaGenerica}" var="lista">  
		 							<option value="${lista.partidaGenericaID}" ${lista.partidaGenericaID == partidaGenericaIDSelected ? 'selected' : ''}>${lista.descripcionPartidaGenerica}</option>  
							</c:forEach>
						</select>
					</td>
					<td style="text-align: right; width: 20%;"><label>Partida Especifica:</label></td>
					<td style="text-align: left; width: 30%;">
						<select id="partidaEspecificaID" name="partidaEspecificaID" style="width: 100px;">
							<option value="0">--Seleccionar--</OPTION>
							<c:forEach items="${listaPartidaEspecifica}" var="lista">  
		 							<option value="${lista.partidaEspecificaID}">${lista.descripcionPartidaEspecifica}</option>  
							</c:forEach>
						</select>
					</td>
				</tr>
			</c:if>
			<tr>
				<td style="text-align: right; width: 20%;"><label>Detalle:</label></td>
				<td style="text-align: left; width: 80%;" colspan="3">
					<textarea rows="1" cols="100" id="detallePartidaGenerica" name="detallePartidaGenerica" style="width: 95%;"></textarea>
				</td>
			</tr>
			<tr>
				<td style="text-align: right; width: 20%;"><label>Cantidad:</label></td>
				<td style="text-align: left; width: 30%;">
					<input type="text" id="cantidadTotal" name="cantidadTotal" size="11" onkeypress="soloNumero(event)" style="width: 65px;" maxlength="4"/>
					<select id="unidadMedidaId" name="unidadMedidaId" style="width: 100px;">
							<option value="0">--Unidad--</option>
						<c:forEach items="${listaUnidadMedida}" var="lista">  
	 							<option value="${lista.tablaEspecificaID}">${lista.descripcionCabecera}</option>  
						</c:forEach>
					</select>
				</td>
				<td style="text-align: right; width: 20%;"><label>Precio Unitario:</label></td>
				<td style="text-align: left; width: 30%;">
					<select id="tipoMonedaId" name="tipoMonedaId" style="width: 100px;">
							<option value="0">--Moneda--</option>
						<c:forEach items="${listaTipoMoneda}" var="lista">  
	 							<option value="${lista.tablaEspecificaID}">${lista.descripcionCabecera}</option>  
						</c:forEach>
					</select>
					<input type="text" id="precioUnitario" name="precioUnitario" size="11" onkeypress="soloNumero(event)" style="width: 65px;" maxlength="7"/>
				</td>
			</tr>
			<tr>
				<td colspan="4"> 
					<br>
				</td>
			</tr>
			<tr>
				<td style="width: 50%;" colspan="2"></td>	
				<td style="text-align: right; width: 20%;">
					<input type="button" value="Regresar" onClick="goBack('showCostoActividad.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID}&actividadID=${actividad.actividadID}');">
				</td>
				<td style="text-align: right; width: 30%;"> 
					<input type="button" value="Grabar Costo Actividad" onClick="submitFormCostoActividad()">
				</td>
			</tr>
		</table>
		<br>
    </fieldset>
</form:form>
</div>
</body>
</html>