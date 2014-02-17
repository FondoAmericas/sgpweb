<%@include file="includesTaglibs.jsp"%>
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
		function roundToPrecision(inputNum, desiredPrecision){
			 var precisionGuide = Math.pow(10, desiredPrecision);
			 return( Math.round(inputNum * precisionGuide) / precisionGuide );
		}
		function calcularMontoPerido(periodo) {
			var precio = document.getElementById("precio").value;
			var montoPeriodo = precio * document.getElementById("cantidadPeriodo"+periodo).value;
			document.getElementById("montoPerido"+periodo).value=roundToPrecision(montoPeriodo,2);
		}

		function submitFormCronogramaCostoActividad() {
			var valida = validar();
			if (valida==0) {
				var erroresMontoLiquidado= validaMontoLiquidado();
				if(erroresMontoLiquidado==0){
					if(confirm("\u00BFEst\u00E1 seguro de guardar los datos ingresados?")){
					   document.formCronogramaCostoActividad.action = "actionSaveCronogramaCostoActividad.jspx";
					   //window.close();
					   document.formCronogramaCostoActividad.submit();
					   window.opener.fRefresh("50");
					}
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
			document.formCronogramaCostoActividad.action = "cargarCantidadPeriodo.jspx";
			document.formCronogramaCostoActividad.method = "post";
			document.formCronogramaCostoActividad.submit();
		}
		
		function validar(){
		      var errores = 0;
		      var mensaje = "";
		      if($('#fuenteFinanciadoraID').attr("value") == "0"){
		          mensaje += "\n Seleccionar Fuente Financiadora.";
		          errores +=1;
		      }
		      
		      var cantidadTotal = 0;
		      for(i = 1; i<= $('#cantidadPerido').attr("value"); i++){
		    	  cantidadTotal += Number($('#cantidadPeriodo'+i).attr("value"));
		    	 //alert("Cantidad Total 01 = "+cantidadTotal );
		      }
		      cantidadTotal += Number($('#totalCantidad').attr("value"));
		      //alert("Cantidad Total 02 = "+cantidadTotal);
		      
		      if(cantidadTotal > $('#cantidadMetaActividad').attr("value")){
			    	 mensaje += "\n La cantidad total supera la cantidad de la meta (" + $('#cantidadMetaActividad').attr("value") + ").";
			    	 errores +=1;
		      } else if(cantidadTotal == 0){
			    	 mensaje += "\n Ingrese una cantidad en algun periodo.";
			    	 errores +=1;
			  } else{
				  var montoTotal = 0;
			      for(i = 1; i<= $('#cantidadPerido').attr("value"); i++){
			    	  montoTotal += Number($('#montoPerido'+i).attr("value"));
			      }
			     // alert("montoTotal= "+montoTotal);
			      if($('#precioUnitario').attr("value") != "" && $('#cantidadTotal').attr("value") != ""){			    	  
				     var costoTotalByFuenteFinanciadora = parseFloat($('#costoTotalByFuenteFinanciadora').attr("value")).toFixed(2);
				     //alert("costoTotalByFuenteFinanciadora="+costoTotalByFuenteFinanciadora);
				    
				     var financiamientoTotal = parseFloat($('#financiamientoTotal').attr("value")).toFixed(2);
				     //alert("financiamientoTotal="+financiamientoTotal);
				     //alert("montoTotal="+montoTotal);
				     var totalFinal = parseFloat(costoTotalByFuenteFinanciadora) + parseFloat(montoTotal);
				     //alert("totalFinal="+totalFinal);
				     if(totalFinal > financiamientoTotal){
				        mensaje += "\n Los montos ingresados superan el monto financiado por la institucion.";
				        errores +=1;
				     }

			     }
			  
			  }
		      
		      if(mensaje != ""){
		         alert(mensaje);
		      }
			  return errores;
		    
		}
		
		function validaMontoLiquidado(){
			var errores=0;
			var mensaje="";
			var cantidadPerido="<c:out value="${cantidadPerido}"></c:out>";
			//alert(periodos);
			for(var i=1;i<=cantidadPerido ;i++){
				//alert(i );
				var montoPeriodo = parseFloat($("#montoPerido"+i).val());
				var montoPeriodoLiquidado = parseFloat($("#montoPerido"+i+"MLHM").val());
	
				if(montoPeriodo < montoPeriodoLiquidado ){
					mensaje +="Se tiene liquidado el monto de "+ montoPeriodoLiquidado +" para el periodo "+ i+", no puede ingresar menos de este monto.\n";
					errores += 1;
				}
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
<div class="encabezado">Cronograma de Costo por Actividad</div>
		<br>
<form:form name="formCronogramaCostoActividad" method="post" action="">
	<input type="hidden" id="datoPlanOperativoID" name="datoPlanOperativoID" value="${planOperativo.datoPlanOperativoID}" />
	<input type="hidden" id="resultadoID" name="resultadoID" value="${resultado.resultadoID}" />
	<input type="hidden" id="actividadID" name="actividadID" value="${actividad.actividadID}" />
	<input type="hidden" id="costoActividadID" name="costoActividadID" value="${costoActividad.costoActividadID}" />
	<input type="hidden" id="cantidadPerido" name="cantidadPerido" value="${cantidadPerido}" />
	<input type="hidden" id="periodos" name="periodos" value="${periodos}" />
	<input type="hidden" id="operacion" name="operacion" value="${operacion}" />
	<input type="hidden" id="precio" name="precio" value="${costoActividad.precioUnitario}" />
	<input type="hidden" id="totalCantidad" name="totalCantidad" value="${totalCantidad}" />
	<input type="hidden" id="cantidadMetaActividad" name="cantidadMetaActividad" value="${costoActividad.cantidadTotal}" />
	<input type="hidden" id="costoTotalByFuenteFinanciadora" name="costoTotalByFuenteFinanciadora" value="${costoTotalByFuenteFinanciadora}" />
    <input type="hidden" id="financiamientoTotal" name="financiamientoTotal" value="${financiamientoTotal}" />
    <input type="hidden" id="mensaje" name="mensaje" value="${mensaje}">
    <fieldset><legend>Crear Cronograma de Costo por Actividad</legend>
		<br>
		<table border="0" width="100%">
			<tr>
				<td style="width: 25%; text-align: right;"><label>Proyecto:</label></td>
				<td style="width: 75%; text-align: left;" colspan="3">
					<label for="proyectoCodigo">${planOperativo.nombreProyecto}</label>
				</td>
			</tr>
			<tr>
				<td style="width: 25%; text-align: right;"><label>Codigo Proyecto:</label></td>
				<td style="width: 25%; text-align: left;">
					<label for="proyectoNombre">${planOperativo.codigoProyecto}</label>
				</td>
				<td style="width: 25%; text-align: right;"><label>Version P.O.:</label></td>
				<td style="width: 25%; text-align: left;">
					<label for="planOperativo">${planOperativo.version}</label>
				</td>
			</tr>
			<tr>
				<td style="width: 25%; text-align: right;"><label>Resultado:</label></td>
				<td style="width: 75%; text-align: left;" colspan="3">
					<label for="resultado">${resultado.definicionResultado}</label>
				</td>
			</tr>
			<tr>	
				<td style="width: 25%; text-align: right;"><label>Actividad:</label></td>
				<td style="width: 25%; text-align: left;" colspan="3">
					<label for="resultado">${actividad.nombreActividad}</label>
				</td>
			</tr>
			<tr>
				<td style="width: 25%; text-align: right;"><label>Tipo Actividad:</label></td>
				<td style="width: 75%; text-align: left;" colspan="3">
					<label for="resultado">${costoActividad.descripcionCategoriaActividad}</label>
				</td>
			</tr>
			<tr>
				<td style="width: 25%; text-align: right;"><label>Partida Generica:</label></td>
				<td style="width: 25%; text-align: left;">
					<label for="resultado">${costoActividad.descripcionPartidaGenerica}</label>
				</td>
				<td style="width: 25%; text-align: right;"><label>Partida Especifica:</label></td>
				<td style="width: 25%; text-align: left;">
					<label for="resultado">${costoActividad.descripcionPartidaEspecifica}</label>
				</td>
			</tr>
			<tr>
				<td style="width: 25%; text-align: right;"><label>Cantidad:</label></td>
				<td style="width: 75%; text-align: left;" colspan="3">
					<label for="unidad">${costoActividad.cantidadTotal} - ${costoActividad.unidadMedidaNombre}</label>
				</td>
			</tr>
			<tr>
				<td style="width: 25%; text-align: right;"><label>Precio Unitario:</label></td>
				<td style="width: 75%; text-align: left;" colspan="3" >
					<label for="precio">${costoActividad.precioUnitario} ${costoActividad.tipoMonedaPrecioUnitarioNombre}</label>
				</td>
			</tr>
			<tr>
				<td style="width: 25%; text-align: right;"><label>Costo Total:</label></td>
				<td style="width: 75%; text-align: left;" colspan="3">
					<label for="montoTotal">${costoActividad.montoTotal} ${costoActividad.tipoMonedaPrecioUnitarioNombre}</label>
				</td>
			</tr>
			<tr>
				<td style="width: 100%;" colspan='4'> 
					<br>
				</td>
			</tr>
			<tr>
				<td style="width: 25%; text-align: right;"><label>Fuente Financiamiento:</label></td>
				<td style="width: 75%; text-align: left;" colspan="3">
					<select id="fuenteFinanciadoraID" name="fuenteFinanciadoraID" style="width: 95%;" onchange="fCargarCantidadPeriodo()" >
						<option value="0" >--Seleccionar--</option>
						<c:forEach items="${listFuenteFinanciadora}" var="lista">  
	 						<option value="${lista.fuenteFinanciadoraID}" <c:if test="${fuenteFinanciadoraID == lista.fuenteFinanciadoraID}">selected="selected"</c:if> >${lista.institucion.nombreInstitucion}</option>
						</c:forEach>
					</select>
				</td>
			</tr>

            <c:if test="${listaCronogramaCostoActividad != null}"> 

	        <c:forEach var="cronogramaCostoActividad" items="${listaCronogramaCostoActividad}" varStatus="status">
			    <tr>
					<td style="width: 25%; text-align: right;"><label>Cantidad Periodo ${status.count}:</label></td>
					<td style="width: 25%; text-align: left;">
						<input type="hidden" id="periodo${status.count}" name="periodo${status.count}" value="${status.count}" >
						<c:choose>
						<c:when test="${cronogramaCostoActividad.ejecutado ==  0 }">
							<input type="text" id="cantidadPeriodo${status.count}" name="cantidadPeriodo${status.count}" 
								value="${cronogramaCostoActividad.cantidad}" size="5" onkeypress="soloNumero(event)" 
								onchange="calcularMontoPerido(${status.count});" />
						
						</c:when>
						<c:otherwise>
						<input type="hidden" id="cantidadPeriodo${status.count}" name="cantidadPeriodo${status.count}" value="${cronogramaCostoActividad.cantidad}"  />
						<label>${cronogramaCostoActividad.cantidad} - Liquidado</label>
						</c:otherwise>
						</c:choose>
						
						
					</td>
					<td style="width: 25%; text-align: right;"><label>Monto Periodo ${status.count}:</label></td>
					<td style="width: 25%; text-align: left;">
					<c:choose>
						<c:when test="${cronogramaCostoActividad.ejecutado ==  0 }">
					<input type="text" id="montoPerido${status.count}" name="montoPerido${status.count}" value="${cronogramaCostoActividad.cantidad * costoActividad.precioUnitario}" 
							size="11" disabled="disabled" />	
					<input type="hidden" id="montoPerido${status.count}MLHM" name="montoPerido${status.count}MLHM" 
								value="${cronogramaCostoActividad.montoLiquidadoHastaElMomento}"  />
					
						</c:when>
						<c:otherwise>
						<input type="hidden" id="montoPerido${status.count}" name="montoPerido${status.count}" value="${cronogramaCostoActividad.cantidad * costoActividad.precioUnitario}" />
						<label>${cronogramaCostoActividad.cantidad * costoActividad.precioUnitario} - Liquidado</label>
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
	        </c:forEach>
	        
	       </c:if>
	       
	       <c:if test="${listaCronogramaCostoActividad == null}">
	       
	        <c:forEach var="periodo" items="${periodos}">
			    <tr>
					<td style="width: 25%; text-align: right;"><label>Cantidad Periodo ${periodo}:</label></td>
					<td style="width: 25%; text-align: left;">
						<input type="hidden" id="periodo${periodo}" name="periodo${periodo}" value="${periodo}" >
						<input type="text" id="cantidadPeriodo${periodo}" name="cantidadPeriodo${periodo}" value="0" size="5" onkeypress="soloNumero(event)" onchange="calcularMontoPerido(${periodo});" />
					</td>
					<td style="width: 25%; text-align: right;"><label>Monto Periodo ${periodo}:</label></td>
					<td style="width: 25%; text-align: left;">
						<input type="text" id="montoPerido${periodo}" name="montoPerido${periodo}" value="0.00" size="11" disabled="disabled"/>
					</td>
				</tr>
	        </c:forEach>
	        
	        </c:if>
			<tr>
				<td colspan='4'> 
					<br>
				</td>
			</tr>
			<tr>
				<td style="width: 100%; text-align: right;" colspan='4'> 
				    <input type="button" value="Cerrar" id="idBtnCerrar" />
					<input type="button" value="Grabar Cronograma Costo Actividad" onclick="submitFormCronogramaCostoActividad()" />
				</td>
			</tr>
		</table>
		<br>
    </fieldset>
</form:form>
</div>
</body>
</html>