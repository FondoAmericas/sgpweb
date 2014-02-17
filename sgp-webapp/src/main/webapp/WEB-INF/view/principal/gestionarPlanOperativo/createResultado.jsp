<%@include file="includesTaglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><spring:message code="web.app.title"></spring:message></title>
	<script>
	var sumaTotalCronogramaMetaPorResultado ="<c:out value="${resultado.sumaTotalCronogramaMetaPorResultado}"></c:out>" ;
	
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
		function submitFormResultado() {
			var valida=validar();
			if (valida==0) {
				
			if(confirm("\u00BFEst\u00E1 seguro de guardar los datos ingresados?")){
			   document.formResultado.action = "actionSaveResultado.jspx";
			   //document.formResultado.target = "_self";
			   //window.close();
			   document.formResultado.submit();
			   window.opener.fRefresh("50");
			   //window.opener.fLocationInterval("50");
			   //window.opener.fRefresh("500");
			   
			   /*
			   $.get("actionSaveResultado.jspx", {
				   datoProyectoID : $("#datoProyectoID").val(),
				   datoPlanOperativoID : $("#datoPlanOperativoID").val(),
					definicionResultado : $("#definicionResultado").val(),
				supuestoResultado : $("#supuestoResultado").val(),
				metaResultado : $("#metaResultado").val(),
				unidadMedidaId : $("#unidadMedidaId").val(),
				duracionMeses : $("#duracionMeses").val()
			   }, function() {
						window.opener.fRefresh("500");
					   window.close();
					   
				});*/
			}
		}
		}
		
		function goTo(url) {
			window.location = url;
		}
		
		function goBack(url) {
			goTo(url);
		}
		
		function validar(){
		      var errores = 0;
		      var mensaje = "";
		      if($('#definicionResultado').attr("value") == ""){
		          mensaje += "Ingresar Definicion.\n ";
		          errores +=1;
		      }
		      
			  if($('#supuestoResultado').attr("value") == ""){
			      mensaje += "Ingresar Supuesto.\n ";
			      errores +=1;
			  }
			  
			  if($('#metaResultado').attr("value") == ""){
			      mensaje += "Ingresar Meta.\n ";
			      errores +=1;
			  }else{
				  if(sumaTotalCronogramaMetaPorResultado>$('#metaResultado').attr("value")){
					  mensaje += "La suma del cronograma del resultado no puede ser mayor a la meta ingresada.\n ";
				      errores +=1;  
				  }
			  }	
			  
			  if($('#unidadMedidaId').attr("value") == "0"){
			      mensaje += "Seleccionar Unidad de Medida.\n ";
			      errores +=1;
			  }
			  
			  if($('#duracionMeses').attr("value") == ""){
			      mensaje += "Ingresar Duracion.\n ";
			      errores +=1;
			  }
			  var duracionMeses = parseInt($('#duracionMeses').attr("value"));
			  var duracionProyecto = parseInt($('#duracionProyecto').attr("value"));
			  
			  if(duracionMeses > duracionProyecto){
				  mensaje += "La duracion no puede pasar la duracion del Proyecto (" + $('#duracionProyecto').attr("value") +" meses).\n ";
				  errores +=1;
			  }
			  
			  if (errores > 0) {
					alert(mensaje);
				}

				
				return errores;
		   
		}
		
	</script>
</head>
<body> 
<div class="form-clasico">
<div class="encabezado">Resultado para Plan Operativo</div>
<br/>
<form:form name="formResultado" method="post" action="" target="_self">
	<input type="hidden" id="datoProyectoID" name="datoProyectoID" value="${planOperativo.datoProyectoID}">
	<input type="hidden" id="datoPlanOperativoID" name="datoPlanOperativoID" value="${planOperativo.datoPlanOperativoID}">
	<input type="hidden" id="duracionProyecto" name="duracionProyecto" value="${duracionProyecto}">
	<input type="hidden" id="mensaje" name="mensaje" value="${mensaje}">
	<input type="hidden" id="resultadoId" name="resultadoId" value="${resultado.resultadoID }">
    <fieldset><legend>Crear Resultado</legend>
<br> 
		<table border="0">
			<tr>
				<td  style="text-align: right; width: 20%;"><label>Proyecto:</label></td>
				<td  style="text-align: left; width: 80%;" colspan="3">
					<label for="proyectoNombre">${planOperativo.nombreProyecto}</label>
				</td>
			</tr>
			<tr>
				<td style="text-align: right; width: 20%;"><label>Codigo Proyecto:</label></td>
				<td  style="text-align: left; width: 30%;">
					<label for="proyectoCodigo">${planOperativo.codigoProyecto}</label>
				</td>
				<td  style="text-align: right; width: 20%;"><label>Version P.O.:</label></td>
				<td  style="text-align: left; width: 30%;">
					<label for="planOperativo">${planOperativo.version}</label>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
<!-- 			<tr> -->
<!-- 				<td style="text-align: right; width: 20%;"><label>Codigo Resultado:</label></td> -->
<!-- 				<td style="text-align: left; width: 70%;" colspan="3"> -->
<!-- 					<input type="text" id="codigoInterno" name="codigoInterno" size="11"/> -->
<!-- 				</td>				 -->
<!-- 			</tr> -->
			<tr>
				<td  style="text-align: right; width: 20%;"><label>Definicion Resultado:</label></td>
				<td  style="text-align: left; width: 80%;" colspan="3">
					<textarea rows="3" cols="75" id="definicionResultado" name="definicionResultado"  style="width: 98%;"><c:out value="${resultado.definicionResultado }"></c:out></textarea>
				</td>
			</tr>
			<tr>
				<td  style="text-align: right; width: 20%;"><label>Supuesto Resultado:</label></td>
				<td  style="text-align: left; width: 80%;" colspan="3">
					<textarea rows="3" cols="75" id="supuestoResultado" name="supuestoResultado" style="width: 98%;"><c:out value="${resultado.supuestoResultado }"></c:out></textarea>
				</td>
			</tr>
			<tr>
				<td style="text-align: right; width: 20%;"><label>Meta Resultado:</label></td>
				<td style="text-align: left; width: 30%;">
					<input type="text" id="metaResultado" name="metaResultado" size="11" maxlength="5" onkeypress="soloNumero(event)"  style="width: 65px;" value="<c:out value="${resultado.metaResultado }"></c:out>"/>
				
					<select id=unidadMedidaId name="unidadMedidaId" style="width: 120px;">
					  <option value="0">--Unidad--</option>
					  <c:forEach items="${listaUnidadMedida}" var="unidadMedida">  
 							<option value="${unidadMedida.tablaEspecificaID}" <c:if test="${unidadMedida.tablaEspecificaID == resultado.fkIdtablaespUnidadMedida }">selected="selected"</c:if>>${unidadMedida.descripcionCabecera}</option>  
					  </c:forEach>
					</select>
				</td>
				<td style="text-align: right; width: 20%;"><label>Duracion:</label></td>
				<td style="text-align: left; width: 30%;">
					<input type="text" id="duracionMeses" name="duracionMeses" size="11" maxlength="3" onkeypress="soloNumero(event)" style="width: 45px;" value="<c:out value="${resultado.duracionMeses }"></c:out>"/>
					<label>(meses)</label>
				</td>
			</tr>
			<tr>
				<td colspan='4'> 
					<br>
				</td>
			</tr>
			<tr>
				<td style="width: 50%;" colspan="2"></td>
				<td colspan="2" style="text-align: right; width: 50%;">
                    <input type="button" value="Cerrar" id="idBtnCerrar" />
                	<input type="button" value="Grabar Resultado" onClick="submitFormResultado()">
				</td>
				
			</tr>
		</table>
    </fieldset>
</form:form>
</div>
</body>
</html>