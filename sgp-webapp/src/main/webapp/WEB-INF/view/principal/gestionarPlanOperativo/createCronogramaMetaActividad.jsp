<%@include file="includesTaglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><spring:message code="web.app.title"></spring:message></title>
	<script>
	
	$(document).ready(function() {
		 if($('#mensaje').attr("value") != '' && $('#mensaje').attr("value") != undefined){
		        alert($('#mensaje').attr("value"));
		     }
		});
	
	
		function soloNumero(e) {
		    if ([e.keyCode||e.which]==8 || [e.keyCode||e.which]==9 || [e.keyCode||e.which]==46) 
		        return true;
		    if ([e.keyCode||e.which] < 48 || [e.keyCode||e.which] > 57)
		        e.preventDefault? e.preventDefault() : e.returnValue = false;
		}

		function submitformCronogramaMetaActividad() {
			if (validar()==0) {
			if(confirm("\u00BFEst\u00E1 seguro de guardar los datos ingresados?")){
			   document.formCronogramaMetaActividad.action = "actionSaveCronogramaMetaActividad.jspx";
			   //window.close();
			   document.formCronogramaMetaActividad.submit();
			   window.opener.fRefresh("50");
			   //top.location.reload(true);
			}
			}
		}
		
		function goTo(url) {
			window.location = url;
		}
		
		function goBack(url) {
			goTo(url);
		}
		
		function fCargarCantidadPeriodo(){
			document.formCronogramaMetaActividad.action = "cargarCantidadPeriodo.jspx";
			document.formCronogramaMetaActividad.method = "post";
			document.formCronogramaMetaActividad.submit();
						
		}
		
		function validar(){
		      //var cabecera = "Debe ingresar la siguiente informaci\u00F3n obligatoria(*):";
		     var errores=0;
		      var mensaje = "";
		      if($('#fuenteFinanciadoraID').attr("value") == "0"){
		          mensaje += "Seleccionar Fuente Financiadora.\n";
		          errores += 1;
		      }
		      		      
		      var cantidadTotal = 0;
		      for(i = 1; i<= $('#cantidadPerido').attr("value"); i++){
		    	  cantidadTotal += Number($('#cantidadPeriodo'+i).attr("value"));
		      }
		      
		      if(cantidadTotal > $('#cantidadMetaActividad').attr("value")){
		    	 mensaje += "La cantidad total supera la cantidad de la meta (" + $('#cantidadMetaActividad').attr("value") + ").\n";
		    	 errores += 1;
		      }
		      
		      if(cantidadTotal == 0){
		    	 mensaje += "Ingrese una cantidad en algun periodo.\n";
		    	 errores += 1;
		      }
		      
		      if(errores>0){
		         alert(mensaje);
		      }
		   return errores;
		}
		
	</script>
</head>
<body style="font-family: Arial; font-size: smaller;" >
<div class="form-clasico">
<div class="encabezado">Cronograma de Meta por Actividad</div>
		<br>
<form:form name="formCronogramaMetaActividad" method="post" action="">
	<input type="hidden" id="datoPlanOperativoID" name="datoPlanOperativoID" value="${planOperativo.datoPlanOperativoID}">
	<input type="hidden" id="resultadoID" name="resultadoID" value="${resultado.resultadoID}">
	<input type="hidden" id="actividadID" name="actividadID" value="${actividad.actividadID}">
	<input type="hidden" id="metaActividadID" name="metaActividadID" value="${metaActivdad.metaActividadID}">
	<input type="hidden" id="cantidadPerido" name="cantidadPerido" value="${cantidadPerido}">
	<input type="hidden" id="periodos" name="periodos" value="${periodos}">
	<input type="hidden" id="operacion" name="operacion" value="${operacion}">
	<input type="hidden" id="cantidadMetaActividad" name="cantidadMetaActividad" value="${metaActivdad.cantidadMetaActividad}">
	<input type="hidden" id="mensaje" name="mensaje" value="${mensaje}">
    <fieldset><legend>Crear Cronograma de Meta por Actividad</legend>
		<br>
		<table border="0" style="width: 100%;">
			<tr>				
				<td style="text-align: right; width: 25%; vertical-align: top;"><label>Proyecto:</label></td>
				<td style="text-align: left; width: 75%;" colspan="3">
					<label for="proyectoNombre">${planOperativo.nombreProyecto}</label>
				</td>
			</tr>
			<tr>
				<td  style="text-align: right; width: 25%;"><label>Codigo Proyecto:</label></td>
				<td  style="text-align: left; width: 25%;">
					<label for="proyectoCodigo">${planOperativo.codigoProyecto}</label>
				</td>
				<td  style="text-align: right; width: 25%;"><label>Version P.O.:</label></td>
				<td  style="text-align: left; width: 25%;">
					<label for="planOperativo">${planOperativo.version}</label>
				</td>
			</tr>
			<tr>
				<td  style="text-align: right; width: 25%; vertical-align: top;"><label>Resultado:</label></td>
				<td  style="text-align: left; width: 75%;" colspan="3">
					<label for="resultadoNombre">${resultado.definicionResultado}</label>
				</td>
			</tr>
			<tr>	
				<td  style="text-align: right; width: 25%; vertical-align: top;"><label>Actividad:</label></td>
				<td  style="text-align: left; width: 75%;" colspan="3">
					<label for="resultadoNombre">${actividad.nombreActividad}</label>
				</td>
			</tr>
			<tr>
				<td  style="text-align: right; width: 25%;"><label>Tipo Actividad:</label></td>
				<td  style="text-align: left; width: 25%;">
					<label for="resultadoNombre">${actividad.tipoActividadNombre}</label>
				</td>
				<td  style="text-align: right; width: 25%;"><label>Cantidad:</label></td>
				<td  style="text-align: left; width: 25%;">
					<label for="cantidad">${metaActivdad.cantidadMetaActividad} - ${metaActivdad.unidadMedidaNombre}</label>
				</td>
			</tr>
			<tr>
	        	<td colspan="4" style="width: 100%;"><br></td>
	        </tr>
	       <c:if test="${listCronogramaMetaActividadView != null}">
	       
	        <c:forEach var="periodo" items="${listCronogramaMetaActividadView[0].listPeriodo}" varStatus="status">
			    <tr>
					<td  style="text-align: right; width: 25%;"><label>Cantidad Periodo ${cronogramaCostoActividad}:</label></td>
					<td  style="text-align: left; width: 75%;" colspan="3">
						<input type="hidden" id="periodo${status.count}" name="periodo${status.count}" value="${status.count}" >
						<c:choose>
							<c:when test="${periodo.periodoReportado == 0 }">
							<input type="text" id="cantidadPeriodo${status.count}" name="cantidadPeriodo${status.count}"
							 value="${periodo.cantidadPeriodo}" size="5" onkeypress="soloNumero(event)" style="width: 120px;"/>
							</c:when>
							<c:when test="${periodo.periodoReportado == 1 }">
							<label>${periodo.cantidadPeriodo} - Reportado en Rep. Avance ${status.count}</label>
							<input type="hidden" id="cantidadPeriodo${status.count}" name="cantidadPeriodo${status.count}"
							 value="${periodo.cantidadPeriodo}" />
							</c:when>
							
						</c:choose>	 
					</td>
				</tr>
	        </c:forEach>
	        
	        </c:if>
	        
	       <c:if test="${listCronogramaMetaActividadView == null}">
	       
	        <c:forEach var="periodo" items="${periodos}">
			    <tr>
					<td  style="text-align: right; width: 25%;"><label>Cantidad Periodo ${periodo}:</label></td>
					<td  style="text-align: left; width: 75%;" colspan="3">
						<input type="hidden" id="periodo${periodo}" name="periodo${periodo}" value="${periodo}" >
						<input type="text" id="cantidadPeriodo${periodo}" name="cantidadPeriodo${periodo}" value="0" size="5" onkeypress="soloNumero(event)" />
					</td>
				</tr>
	        </c:forEach>
	        
	        </c:if>
	        <tr>
	        	<td colspan="4" style="width: 100%;"><br></td>
	        </tr>
			<tr>
				<td style="text-align: right; width: 100%;" colspan="4"> 
                    <input type="button" value="Cerrar" id="idBtnCerrar" />
					<input type="button" value="Grabar Cronograma Meta Actividad" onclick="submitformCronogramaMetaActividad()" />
				</td>
			</tr>
		</table>
		<br>
    </fieldset>
</form:form>
</div>
</body>
</html>