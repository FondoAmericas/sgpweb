<%@include file="includesTaglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><spring:message code="web.app.title"></spring:message></title>
	
	<script type="text/javascript">
	$(window).load(function() {
		////UBICACION DEL PROYECTO
		ocultaCampos();
	});
	
	function ocultaCampos(){
		estado= "<c:out value="${estadoPlanOperativo}"></c:out>" ;
		//alert(estado);
		if ((estado =='peval')||(estado =='revi')||(estado =='apro')||(estado =='recha')){
			$(".hide").hide();//attr("style","visibility: hidden;");
			//alert($('#cbxInstitucionEjecutora option:selected').html());
			//$("#div_cbxInstitucionEjecutora").attr("style","visibility: hidden;");
		}
		
	}
	</script>
	<script>
	
	$(document).ready(function() {
		 if($('#mensaje').attr("value") != '' && $('#mensaje').attr("value") != undefined){
		        alert($('#mensaje').attr("value"));
		 }
	});
	
	function fEliminar(metaActividadID, metaActividadNombre){
		if(confirm("\u00BFEst\u00E1 Seguro que desea eliminar la meta actividad " + metaActividadNombre + " ?\n\n SE ELIMINARA LA META CON LA PROGRAMACION DE SU CRONOGRAMA.")){
		   var datoPlanOperativoID = $("#datoPlanOperativoID").val();
		   var resultadoID = $("#resultadoID").val();
		   var actividadID = $("#actividadID").val();
		   window.location.href = "actionDeleteMetaActividad.jspx?metaActividadID=" + metaActividadID 
				                + "&datoPlanOperativoID=" + datoPlanOperativoID
				                + "&resultadoID=" + resultadoID
		                        + "&actividadID=" + actividadID;
		}			
	}
	
	function modificarMetaActividad(metaActividadID ,cantidadMetaActividad ,unidadMedidaId ,tipoIndicadorActividadId ,cantTotalCronogramaMetaActividad ){
		var confirma = confirm("Esta seguro que desea cargar el registro para modificarlo?");
		
		if(confirma==true){
		$('#metaActividadID').attr("value",metaActividadID);
		$('#cantidadMetaActividad').attr("value",cantidadMetaActividad);
		  $('#unidadMedidaId').attr("value",unidadMedidaId);	      
	      $('#tipoIndicadorActividadId').attr("value",tipoIndicadorActividadId);
	      $('#cantTotalCronogramaMetaActividad').attr("value",cantTotalCronogramaMetaActividad);
		}
	}
	
		selectValue = "";
		function goToCronogramaMetaActividad(form, url){
			if(form.metaActividadId != undefined){
                if(form.metaActividadId.length != undefined){
					for(var i = 0; i <form.metaActividadId.length; i++){
						if(form.metaActividadId[i].checked){
							selectValue = form.metaActividadId[i].value;
						  	break;
						}
					}
                }else{
                	if(form.metaActividadId.checked){
                	   selectValue = form.metaActividadId.value;
                	}
                }
				
				if (selectValue == "")	{
					alert ("Debe seleccionar un valor");
				}else{
					goTo(url + selectValue);
				}
				
			} else{
				alert ("La lista se encuentra vacia.");
			}
		}
		
		function soloNumero(e) {
		    if ([e.keyCode||e.which]==8 || [e.keyCode||e.which]==9 || [e.keyCode||e.which]==46) 
		        return true;
		    if ([e.keyCode||e.which] < 48 || [e.keyCode||e.which] > 57)
		        e.preventDefault? e.preventDefault() : e.returnValue = false;
		}
		
		function submitFormMetaActividad() {
			var errores = validar();

			if(errores==0){
				if(confirm("\u00BFEst\u00E1 seguro de guardar los datos ingresados?")){
				   document.formMetaActividad.action = "actionSaveMetaActividad.jspx";
				   document.formMetaActividad.submit();
				}
			}
		}
		
		function goTo(url) {
			//window.location = url;
			//alert(url);
			fOpenModalDialog(url,'1000','500','70','70');
		
		}
		function goTo2(url) {
			window.location = url;
		}
		function goBack(url) {
			goTo(url);
		}
		
		function validar(){
		      var errores = 0;
		      var mensaje = "";
		      
			  if($('#cantidadMetaActividad').attr("value") == ""){
			      mensaje += "Ingresar Cantidad.\n";
			      errores += 1;
			  }
			  
			  if($('#cantidadMetaActividad').attr("value") == "0"){
			      mensaje += "La cantidad ingresada de la meta no puede ser CERO.\n";
			      errores += 1;
			  }
			  
		      if($('#unidadMedidaId').attr("value") == "0"){
		          mensaje += "Seleccionar Unidad de Medida.\n";
		          errores += 1;
		      }
		      
		      if($('#tipoIndicadorActividadId').attr("value") == "0"){
		          mensaje += "Seleccionar Tipo Indicador.\n";
		          errores += 1;
		      }
		      
		      var cantTotalCronogramaMetaActividad = parseInt($('#cantTotalCronogramaMetaActividad').val());
		      var cantidadMetaActividad = parseInt($('#cantidadMetaActividad').val());
		      
		      if(cantTotalCronogramaMetaActividad > cantidadMetaActividad){
		          mensaje += "La cantidad ingresada es menor que lo programado en el cronograma.\n";
		          errores += 1;
		      }
		      
		      
		     
		      if(errores>0){
		         alert(mensaje);
		        }
			return errores;
		   
		}
		
	</script>
</head>
<body> 
<div class="form-clasico">
<div class="encabezado">Metas por Actividad</div>
		<br>
    <fieldset><legend>Crear Meta por Actividad</legend>
<div class="form-clasico">
	<c:if test="${!empty alterMessage}">
		<fieldset><legend>Mensajes de Alertas</legend>
			<label style="color:red;" for="alterMessage">${alterMessage}</label>
		</fieldset>
	</c:if>
<form:form name="formMetaActividad" method="post" action="">
	<input type="hidden" id="datoPlanOperativoID" name="datoPlanOperativoID" value="${planOperativo.datoPlanOperativoID}">
	<input type="hidden" id="resultadoID" name="resultadoID" value="${resultado.resultadoID}">
	<input type="hidden" id="actividadID" name="actividadID" value="${actividad.actividadID}">
	<input type="hidden" id="metaActividadID" name="metaActividadID" >
	<input type="hidden" id="cantTotalCronogramaMetaActividad" name="cantTotalCronogramaMetaActividad" value="0">
    <input type="hidden" id="mensaje" name="mensaje" value="${mensaje}">
    
		<table border="0" width="100%">
			<tr>
				<td style="text-align: right; width: 20%;"><label>Proyecto:</label></td>
				<td style="text-align: left; width: 80%;" colspan="3">
					<label for="proyectoNombre">${planOperativo.nombreProyecto}</label>
				</td>
			</tr>
			<tr>
				<td style="text-align: right; width: 20%;"><label>Codigo Proyecto:</label></td>
				<td style="text-align: left; width: 30%;">
					<label for="proyectoCodigo">${planOperativo.codigoProyecto}</label>
				</td>
				<td style="text-align: right; width: 20%;"><label>Version PO:</label></td>
				<td style="text-align: left; width: 30%;">
					<label for="planOperativo">${planOperativo.version}</label>
				</td>
			</tr>
			<tr>
				<td style="text-align: right; width: 20%;"><label>Resultado:</label></td>
				<td style="text-align: left; width: 80%;" colspan="3">
					<label for="resultadoNombre">${resultado.definicionResultado}</label>
				</td>
			</tr>
			<tr>
				<td style="text-align: right; width: 20%;"><label>Actividad:</label></td>
				<td style="text-align: left; width: 80%;"  colspan="3">
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
					<td style="text-align: right; width: 20%; vertical-align: top">
					    <label>Tipo de Periodo:</label>
					</td>
					<td colspan="3" style="text-align: left; width: 80%; vertical-align: top">
					    <label>${planOperativo.datoProyecto.programa.tipoPeriodo.descripPeriodo }</label>
					</td>
				</tr>
			</table>
			<br class="hide" />
			<fieldset class="hide" >
			<legend><label>Informacion de la Meta</label></legend>
			<table width="100%">
			<tr>
				<td style="text-align: right; width: 20%"><label>Cantidad:</label></td>
				<td style="text-align: left; width: 80%" colspan="3">
					<input type="text" id="cantidadMetaActividad" name="cantidadMetaActividad" maxlength="5" onkeypress="soloNumero(event)" style="width: 55px"/>
					<select id="unidadMedidaId" name="unidadMedidaId" style="width: 300px;">
						<option value="0">--Unidad--</option>
						<c:forEach items="${listaUnidadMedida}" var="lista">  
	 							<option value="${lista.tablaEspecificaID}">${lista.descripcionCabecera}</option>  
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td style="text-align: right; width: 20%"><label>Tipo Indicador:</label></td>
				<td style="text-align: left; width: 80%" colspan="3">
					<select id="tipoIndicadorActividadId" name="tipoIndicadorActividadId" style="width: 358px;">
						<option value="0">--Seleccionar--</option>
						<c:forEach items="${listaTipoIndicadorActividad}" var="lista">  
	 							<option value="${lista.tablaEspecificaID}">${lista.descripcionCabecera}</option>  
						</c:forEach>
					</select>
				</td>
			</tr>
			<!-- <tr>
				<td style="text-align: right; width: 20%; vertical-align: top;"><label>Contribucion Proposito:</label></td>
				<td style="text-align: left; width: 80%" colspan="3">
                <textarea rows="3" cols="75" id="contribucionProposito" name="contribucionProposito" style="width:358px;"></textarea>
				</td>
			</tr>
			 -->
			<tr>
				<td colspan="4"> 
					<br>
				</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: right; width: 100%;">
					<input type="button" value="Cerrar" id="idBtnCerrar" />
					<input type="button" name="save" value="Grabar Meta" onClick="submitFormMetaActividad()">
				</td>
			</tr>
		</table>
		</fieldset>
</form:form>
</div>
		<form>
		<table class="table-clasico" style="width: 100%">
		<caption>Meta Actividad</caption>
		<thead>
        <tr>
        	<!-- <th align="center"><label></label></th> -->
        	<td align="center" width="5%"><label>N°</label></td>
        	<td align="center" width="25%"><label>Cantidad</label></td>
			<td align="center" width="40%"><label>Tipo Indicador</label></td>
			<!-- <th align="center" width="40%"><label>Contribucion Proposito</label></th> -->
			<td align="center" width="15%"><label>Cronograma</label></td>
			<td align="center" width="15%"  class="hide" ><label>Operaciones</label></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="metaActividad" items="${listaMetaActividad}" varStatus="indice">
			<c:choose>
				<c:when test="${indice.count %2== 0}">
					<c:set var="classIdi" value="f1"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="classIdi" value="f2"></c:set>
				</c:otherwise>
			</c:choose>
			<tr class="<c:out value="${classIdi}"></c:out>">
				<td align="center" width="5%"><label><c:out value="${indice.count }"></c:out> </label></td>
	        	<!-- <td align="center"><input type="radio" name="metaActividadId" value="${metaActividad.metaActividadID}"></td> -->
				<td align="center"><c:out value="${metaActividad.cantidadMetaActividad} - ${metaActividad.unidadMedidaNombre}"></c:out></td>
				<td align="left"><c:out value="${metaActividad.tipoIndicadorActividadNombre}"></c:out></td>
				<!-- <td align="left"><c:out value="${metaActividad.contribucionProposito}"></c:out></td> -->
				<td align="left"><label><a href="javascript:goTo('showCronogramaMetaActividad.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID}&actividadID=${actividad.actividadID}&metaActividadID=${metaActividad.metaActividadID}&estadoPlanOperativo=${estadoPlanOperativo}')"
												id="cronograma" class="linkSelecciona">Cronograma</a></label></td>
				<td align="center" class="hide" >
				  <a
						href="javascript:modificarMetaActividad('<c:out value="${metaActividad.metaActividadID }"></c:out>',
																'<c:out value="${metaActividad.cantidadMetaActividad }"></c:out>',
																'<c:out value="${metaActividad.unidadMedidaId }"></c:out>',
																'<c:out value="${metaActividad.tipoIndicadorActividadId }"></c:out>',
																'<c:out value="${metaActividad.cantTotalCronogramaMetaActividad }"></c:out>')"
						id="modificarMetaActividad" class="linkSelecciona">Modificar</a><br />
				  <a href="#" onclick="fEliminar(${metaActividad.metaActividadID},'${metaActividad.cantidadMetaActividad} - ${metaActividad.unidadMedidaNombre}')"  class="linkSelecciona">Eliminar</a>
				</td>
	        </tr>
        </c:forEach>
        </tbody>
        </table>
		<!-- <br>
		<table border="0" width="100%">
			<tr>
				<td style="width: 60%;"></td>
<%-- 				<td style="text-align: right;width: 20%;"><input type="button" value="Regresar" onClick="goBack('showActividad.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID}');"> </td> 
				<td style="text-align: right;width: 20%;"><input type="button" value="Cronograma Meta Actividad" onClick="goToCronogramaMetaActividad(this.form, 'showCronogramaMetaActividad.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID}&actividadID=${actividad.actividadID}&metaActividadID=');"> </td>
				--%>
			</tr>
			</table>
			 -->
			</form>
    </fieldset>
 </div>
</body>
</html>
