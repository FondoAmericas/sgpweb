<%@include file="includesTaglibs.jsp"%>
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
	
	function soloNumero(e) {
	    if ([e.keyCode||e.which]==8 || [e.keyCode||e.which]==9 || [e.keyCode||e.which]==46) 
	        return true;
	    if ([e.keyCode||e.which] < 48 || [e.keyCode||e.which] > 57)
	        e.preventDefault? e.preventDefault() : e.returnValue = false;
	}
	
	function submitFormIndicadorResultado() {
		if (validar()) {
			return;
		}
		if(confirm("\u00BFEst\u00E1 seguro de guardar los datos ingresados?")){
		   document.formIndicadorResultado.action = "actionSaveIndicadorResultado.jspx";
		   document.formIndicadorResultado.submit();
		}
	}
	
		function goBack(url) {
			goTo(url);
		}
		function goTo(url) {
			window.location = url;
		}
		
		function validar(){
		      var cabecera = "Debe ingresar la siguiente informaci\u00F3n obligatoria(*):";
		      var mensaje = "";
		      if($('#tipoIndicadorId').attr("value") == "0"){
		          mensaje += "\n - Tipo	Indicador.";
		      }
		      
			  if($('#definicionIndicador').attr("value") == ""){
			      mensaje += "\n - Definicion Indicador.";
			  }
			  
			  if($('#medioVerificacion').attr("value") == ""){
			      mensaje += "\n - Medio de Verificacion.";
			  }
			  
			  if($('#situacionActual').attr("value") == ""){
			      mensaje += "\n - Situacion Actual.";
			  }
			  
			  if($('#situacionFinal').attr("value") == ""){
			      mensaje += "\n - Situacion Final.";
			  }
			  
			  if($('#unidadMedidaId').attr("value") == "0"){
			      mensaje += "\n - Unidad de Medida.";
			  }
			  
		      if(mensaje != ""){
		         alert(cabecera + mensaje);
		         return true;
		      }else{
		         return false;
		      }
		   
		}
		
		function fEliminar(indicadorResultadoID, definicionIndicador){
			if(confirm("\u00BFEst\u00E1 seguro de eliminar el indicador \"" + definicionIndicador + "\" ?")){
				var datoPlanOperativoID = $("#datoPlanOperativoID").val();
				var resultadoID = $("#resultadoID").val();
			   window.location.href = "actionDeleteIndicador.jspx?indicadorResultadoID=" + indicadorResultadoID 
					                + "&datoPlanOperativoID=" + datoPlanOperativoID
					                + "&resultadoID=" + resultadoID;
			}			
		}		

		function modificarIndicadorResultado(indicadorResultadoID,definicionIndicador,medioVerificacion,
					metodoCalculo,situacionActual,situacionFinal,tipoIndicadorId,unidadMedidaId){
			var confirma = confirm("Esta seguro que desea cargar el registro para modificarlo?");
			
			if(confirma==true){
			$('#tipoIndicadorId').attr("value",tipoIndicadorId);
			$('#definicionIndicador').attr("value",definicionIndicador);
			$('#medioVerificacion').attr("value",medioVerificacion);
			$('#metodoCalculo').attr("value",metodoCalculo);
			$('#situacionActual').attr("value",situacionActual);
			$('#situacionFinal').attr("value",situacionFinal);
			$('#unidadMedidaId').attr("value",unidadMedidaId);
			$('#unidadMedidaSituacionFinal').attr("value",unidadMedidaId);
			$("#indicadorResultadoID").attr("value",indicadorResultadoID);
		}
		}
				
		function marcaUnidad(){
			$("#unidadMedidaSituacionFinal").attr("value",$("#unidadMedidaId").val());
		}
	</script>
</head>
<body>

	<div class="form-clasico">
	<div class="encabezado">Indicadores por Resultado</div>
		<br>
		<fieldset>
			<legend>Crear Indicador</legend>
			<!-- 		<br> -->
			<form:form name="formIndicadorResultado" method="post" action="">
				<input type="hidden" id="datoPlanOperativoID"
					name="datoPlanOperativoID"
					value="${planOperativo.datoPlanOperativoID}">
				<input type="hidden" id="resultadoID" name="resultadoID"
					value="${resultado.resultadoID}">
				<input type="hidden" id="indicadorResultadoID" name="indicadorResultadoID">
				<input type="hidden" id="mensaje" name="mensaje" value="${mensaje}">
				<table border="0" width="100%">
					<tr>
						<td style="text-align: right; width: 20%;"><label>Proyecto:</label>
						</td>
						<td style="text-align: left; width: 30%;" colspan="3"><label
							for="proyectoCodigo">${planOperativo.nombreProyecto}</label>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 20%;"><label>Codigo
								Proyecto:</label></td>
						<td style="text-align: left; width: 30%;"><label
							for="proyectoNombre">${planOperativo.codigoProyecto}</label>
						</td>
						<td style="text-align: right; width: 20%;"><label>Version
								PO:</label></td>
						<td style="text-align: left; width: 30%;"><label
							for="planOperativo">${planOperativo.version}</label>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 20%;"><label>Resultado:</label>
						</td>
						<td style="text-align: left; width: 30%;"><label
							for="resultado">${resultado.definicionResultado}</label>
						</td>
					</tr>
				</table>
				<br class="hide" />
				<fieldset class="hide">
					<table>
						<tr>
							<td style="text-align: right; width: 20%;"><label>Tipo
									Indicador:</label></td>
							<td style="text-align: left; width: 80%;" colspan="3"><select
								id="tipoIndicadorId" name="tipoIndicadorId" style="width: 95%;">
									<option value="0">--Tipo--</option>
									<c:forEach items="${listaTipoIndicadorResultado}" var="lista">
										<option value="${lista.tablaEspecificaID}">${lista.descripcionCabecera}</option>
									</c:forEach>
							</select>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 20%;"><label>Definicion
									Indicador:</label></td>
							<td style="text-align: left; width: 80%;" colspan="3"><textarea
									rows="3" cols="75" id="definicionIndicador"
									name="definicionIndicador" style="width: 95%;"></textarea>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 20%;"><label>Medio
									Verificacion:</label></td>
							<td style="text-align: left; width: 80%;" colspan="3"><textarea
									rows="3" cols="75" id="medioVerificacion"
									name="medioVerificacion" style="width: 95%;"></textarea>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 20%;"><label>Metodo
									de Calculo:</label></td>
							<td style="text-align: left; width: 80%;" colspan="3"><textarea
									rows="3" cols="75" id="metodoCalculo" name="metodoCalculo"
									style="width: 95%;"></textarea>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 20%;"><label>Situacion
									Actual:</label></td>
							<td style="text-align: left; width: 30%;"><input type="text"
								id="situacionActual" name="situacionActual" size="6"
								style="width: 60px;" onkeypress="soloNumero(event)"
								maxlength="6" /> <select id="unidadMedidaId"
								name="unidadMedidaId" style="width: 120px;"
								onchange="marcaUnidad()">
									<option value="0">--Unidad Medida--</option>
									<c:forEach items="${listaUnidadMedida}" var="lista">
										<option value="${lista.tablaEspecificaID}">${lista.descripcionCabecera}</option>
									</c:forEach>
							</select></td>
							<td style="text-align: right; width: 20%;"><label>Situacion
									Final:</label></td>
							<td style="text-align: left; width: 30%;"><input type="text"
								id="situacionFinal" name="situacionFinal" size="8"
								style="width: 60px;" onkeypress="soloNumero(event)"
								maxlength="8" /> <select id="unidadMedidaSituacionFinal"
								name="unidadMedidaSituacionFinal" style="width: 120px;"
								disabled="disabled">
									<option value="0">--Unidad Medida--</option>
									<c:forEach items="${listaUnidadMedida}" var="lista">
										<option value="${lista.tablaEspecificaID}">${lista.descripcionCabecera}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td colspan="4"><br>
							</td>
						</tr>
						<tr>
							<td style="width: 50%;" colspan="2"></td>
							<td style="text-align: right; width: 20%;"><input
								type="button" value="Cerrar" id="idBtnCerrar" /></td>
							<td style="text-align: right; width: 30%;"><input
								type="button" value="Grabar Indicador Resultado"
								onClick="submitFormIndicadorResultado()">
							</td>
						</tr>

					</table>
				</fieldset>
			</form:form>
			<br class="hide" />
			<form>
				<table class="table-clasico" width="100%">
					<caption>Indicador Resultado</caption>
					<thead>
						<tr>
							<th style="text-align: center; width: 20%;"><label>Tipo
									Indicador</label></th>
							<th style="text-align: center; width: 35%;"><label>Definicion
									Indicador</label></th>
							<th style="text-align: center; width: 10%;"><label>Medio
									Verificacion</label></th>
							<th style="text-align: center; width: 10%;"><label>Metodo
									Calculo</label></th>
							<th style="text-align: center; width: 10%;"><label>Situacion
									Actual</label></th>
							<th style="text-align: center; width: 10%;"><label>Situacion
									Final</label></th>
							<th style="text-align: center; width: 5%;" class="hide"><label>Operaciones</label>
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="indicador" items="${listIndicadorResultadoView}"
							varStatus="indice">
							<c:choose>
								<c:when test="${indice.count %2== 0}">
									<c:set var="classIdi" value="f1"></c:set>
								</c:when>
								<c:otherwise>
									<c:set var="classIdi" value="f2"></c:set>
								</c:otherwise>
							</c:choose>
							<tr class="<c:out value="${classIdi}"></c:out>">
								<td style="text-align: left; width: 20%;"><c:out
										value="${indicador.tipoIndicadorNombre}"></c:out></td>
								<td style="text-align: left; width: 35%;"><c:out
										value="${indicador.definicionIndicador}"></c:out></td>
								<td style="text-align: left; width: 10%;"><c:out
										value="${indicador.medioVerificacion}"></c:out></td>
								<td style="text-align: left; width: 10%;"><c:out
										value="${indicador.metodoCalculo}"></c:out></td>
								<td style="text-align: left; width: 10%;"><c:out
										value="${indicador.situacionActual} - ${indicador.unidadMedidaNombre}"></c:out>
								</td>
								<td style="text-align: left; width: 10%;"><c:out
										value="${indicador.situacionFinal} - ${indicador.unidadMedidaNombre}"></c:out>
								</td>
								<td style="text-align: left; width: 5%;"  class="hide"><a 
									href="javascript:modificarIndicadorResultado('${indicador.indicadorResultadoID}', '${indicador.definicionIndicador}',
														'${indicador.medioVerificacion}','${indicador.metodoCalculo}',
														'${indicador.situacionActual}','${indicador.situacionFinal}',
														'${indicador.tipoIndicadorId}','${indicador.unidadMedidaId}')" class="linkSelecciona" >Modificar</a><br />
									<a href="#"
									onclick="fEliminar(${indicador.indicadorResultadoID}, '${indicador.definicionIndicador}')" class="linkSelecciona" >Eliminar</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</fieldset>
	</div>
</body>
</html>
