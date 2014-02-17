<%@include file="includesTaglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="web.app.title"></spring:message>
</title>

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
	
		function soloNumero(e) {
		    if ([e.keyCode||e.which]==8 || [e.keyCode||e.which]==9 || [e.keyCode||e.which]==46) 
		        return true;
		    if ([e.keyCode||e.which] < 48 || [e.keyCode||e.which] > 57)
		        e.preventDefault? e.preventDefault() : e.returnValue = false;
		}
		function submitFormBeneficiarioResultado() {
			if (validar()) {
				return;
			}
			if(confirm("\u00BFEst\u00E1 seguro de guardar los datos ingresados?")){
			   document.formBeneficiarioResultado.action = "actionSaveBeneficiarioResultado.jspx";
			   document.formBeneficiarioResultado.submit();
			}
		}
	
		function goBack(url) {
			goTo(url);
		}
		function goTo(url) {
			window.location = url;
		}
		
		function fAgregarBeneficiariosProyecto(url){
			fOpenModalDialog(url,'870','300','140','140');
		}
		
		function fRefresh(tiempo){
			fSetTimeOutRefreshLocation(tiempo);
		}
		
		function validar(){
		      var cabecera = "Debe ingresar la siguiente informaci\u00F3n obligatoria(*):";
		      var mensaje = "";
		      if($('#tipoBeneficiarioId').attr("value") == "0"){
		          mensaje += "\n - Tipo Beneficiario.";
		      }
		      
			  if($('#caracteristicas').attr("value") == ""){
			      mensaje += "\n - Caracteristica.";
			  }
			  
			  if($('#descripcion').attr("value") == ""){
			      mensaje += "\n - Descripcion.";
			  }
			  
			  if($('#cantidadProgramado').attr("value") == ""){
			      mensaje += "\n - Cantidad Programada.";
			  }
			  
			  if($('#estratoId').attr("value") == "0"){
			      mensaje += "\n - Estrato.";
			  }

		      if(mensaje != ""){
		         alert(cabecera + mensaje);
		         return true;
		      }else{
		         return false;
		      }
		   
		}
		
		function fEliminar(beneficiariosPorResultadoID, descripcionBeneficiario){
			if(confirm("\u00BFEst\u00E1 seguro que desea eliminar el beneficiario " + descripcionBeneficiario + "?")){
				var datoPlanOperativoID = $("#datoPlanOperativoID").val();
				var resultadoID = $("#resultadoID").val();
			    window.location.href = "actionDeleteBeneficiario.jspx?beneficiariosPorResultadoID=" + beneficiariosPorResultadoID 
				 	                 + "&datoPlanOperativoID=" + datoPlanOperativoID
					                 + "&resultadoID=" + resultadoID;
			}			
		}
		
		function modificarBeneficiario(beneficiariosPorResultadoID,tipoBeneficiarioId ,caracteristicasPoblacion ,descripcion ,cantidadProgramado ,estratoId ){
			var confirma = confirm("Esta seguro que desea cargar el registro para modificarlo?");
			
			if(confirma==true){
			$('#beneficiariosPorResultadoID').attr("value",beneficiariosPorResultadoID);
			$('#tipoBeneficiarioId').attr("value",tipoBeneficiarioId);
			  $('#caracteristicas').attr("value",caracteristicasPoblacion);
			  $('#descripcion').attr("value",descripcion);
			  $('#cantidadProgramado').attr("value",cantidadProgramado);
			  $('#estratoId').attr("value",estratoId);
			}
		}
		
		function load(){
			var mensaje = $('#mensaje').attr("value");
			if(mensaje != "")
			   alert(mensaje);
			   $('#mensaje').val("");
		}
		
	</script>
</head>
<body onload="load()" style="font-family: Arial; font-size: smaller;">

	<div class="form-clasico">
	<div class="encabezado">Beneficiarios por Resultado</div>
		<br>
		<fieldset>
			<legend>Crear Beneficiarios</legend>
			<!-- 		<br> -->
			<form:form name="formBeneficiarioResultado" method="post" action="">
				<input type="hidden" id="mensaje" name="mensaje" value="${mensaje}" />
				<input type="hidden" id="datoPlanOperativoID"
					name="datoPlanOperativoID"
					value="${planOperativo.datoPlanOperativoID}" />
				<input type="hidden" id="resultadoID" name="resultadoID"
					value="${resultado.resultadoID}" />
				<input type="hidden" id="beneficiariosPorResultadoID" name="beneficiariosPorResultadoID">	
				<table border="0" width="100%">
					<tr>
						<td style="text-align: right; width: 20%;"><label>Proyecto:</label>
						</td>
						<td style="text-align: left; width: 30%;" colspan="3"><label
							for="proyectoCodigo">${planOperativo.nombreProyecto}</label></td>
					</tr>
					<tr>
						<td style="text-align: right; width: 20%;"><label>Codigo
								Proyecto:</label>
						</td>
						<td style="text-align: left; width: 30%;"><label
							for="proyectoNombre">${planOperativo.codigoProyecto}</label></td>
						<td style="text-align: right; width: 20%;"><label>Version
								PO:</label>
						</td>
						<td style="text-align: left; width: 30%;"><label
							for="planOperativo">${planOperativo.version}</label></td>
					</tr>
					<tr>

						<td style="text-align: right; width: 20%;"><label>Resultado:</label>
						</td>
						<td style="text-align: left; width: 30%;" colspan="3"><label
							for="resultado">${resultado.definicionResultado}</label></td>
					</tr>
				</table>
				<br class="hide"/>
				<fieldset class="hide">
					<table>
						<tr>
							<td style="text-align: right; width: 20%;"><label>Tipo
									Beneficiario:</label>
							</td>
							<td style="text-align: left; width: 80%;" colspan="3"><select
								id="tipoBeneficiarioId" name="tipoBeneficiarioId"
								style="width: 100px;">
									<option value="0">--Tipo--</option>
									<c:forEach items="${listaTipoBeneficiario}" var="lista">
										<option value="${lista.tablaEspecificaID}">${lista.descripcionCabecera}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td style="text-align: right; width: 20%;"><label>Caracteristicas:</label>
							</td>
							<td style="text-align: left; width: 80%;" colspan="3"><textarea
									rows="3" cols="75" id="caracteristicas" name="caracteristicas"
									style="width: 95%;"></textarea></td>
						</tr>
						<tr>
							<td style="text-align: right; width: 20%;"><label>Descripcion:</label>
							</td>
							<td style="text-align: left; width: 80%;" colspan="3"><textarea
									rows="3" cols="75" id="descripcion" name="descripcion"
									style="width: 95%;"></textarea></td>
						</tr>
						<tr>
							<td style="text-align: right; width: 20%;"><label>Cantidad
									Programada:</label>
							</td>
							<td style="text-align: left; width: 30%;" colspan="3"><input
								type="text" id="cantidadProgramado" name="cantidadProgramado"
								size="11" onkeypress="soloNumero(event)" /> <select
								id="estratoId" name="estratoId" style="width: 100px;">
									<option value="0">--Estrato--</option>
									<c:forEach items="${listaEstratos}" var="estrato">
										<option value="${estrato.tablaEspecificaID}">${estrato.descripcionCabecera}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td colspan="4"><br></td>
						</tr>
						<tr>
							<td style="text-align: right; width: 50%;" colspan="2"><input
								type="button" value="Cerrar" id="idBtnCerrar" />
							</td>
							<td style="text-align: right; width: 20%;"><input
								type="button" value="Agregar Beneficiarios Proyecto"
								onClick="fAgregarBeneficiariosProyecto('showBeneficiarioProyecto.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID}');">
							</td>

							<td style="text-align: right; width: 30%;"><input
								type="button" value="Grabar Beneficiario Resultado"
								onClick="submitFormBeneficiarioResultado()"></td>
						</tr>

					</table>
				</fieldset>
			</form:form>
			<div style="text-align: left;" class="hide"><label style="color: red;font-weight: bold;font-size: 13px;">* Si beneficiario ya ha sido reportado en algun reporte de avance, ya no se puede eliminar.</label></div>
			<br>
			<form>
				<table class="table-clasico" width="100%">
					<caption>Beneficiarios Resultado</caption>
					<thead>
						<tr>
							<th align="center"><label>Tipo Beneficiario</label>
							</th>
							<th align="center"><label>Caracteristicas</label>
							</th>
							<th align="center"><label>Cantidad Programada</label>
							</th>
							<th align="center"><label>Descripcion</label>
							</th>
							<th align="center" class="hide"><label>Operaciones</label>
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="beneficiario"
							items="${listBeneficiarioResultadoView}" varStatus="indice">
							<c:choose>
								<c:when test="${indice.count %2== 0}">
									<c:set var="classIdi" value="f2"></c:set>
								</c:when>
								<c:otherwise>
									<c:set var="classIdi" value="f1"></c:set>
								</c:otherwise>
							</c:choose>
							<tr class="<c:out value="${classIdi}"></c:out>">
								<td align="left"><c:out
										value="${beneficiario.tipoBeneficiarioNombre}"></c:out>
								</td>
								<td align="left"><c:out
										value="${beneficiario.caracteristicasPoblacion}"></c:out>
								</td>
								<td align="left"><c:out
										value="${beneficiario.cantidadProgramado} - ${beneficiario.estratoNombre}"></c:out>
								</td>
								<td align="left"><c:out value="${beneficiario.descripcion}"></c:out>
								</td>
								<td align="center" class="hide"> <a
						href="javascript:modificarBeneficiario('<c:out value="${beneficiario.beneficiariosPorResultadoID }"></c:out>',
																'<c:out value="${beneficiario.tipoBeneficiarioId }"></c:out>',
																'<c:out value="${beneficiario.caracteristicasPoblacion }"></c:out>',
																'<c:out value="${beneficiario.descripcion }"></c:out>',
																'<c:out value="${beneficiario.cantidadProgramado }"></c:out>',
																'<c:out value="${beneficiario.estratoId }"></c:out>')"
						id="modificarMetaActividad" class="linkSelecciona">Modificar</a> <br /> 
								<c:if test="${beneficiario.cantPropuestaTransferenciaBeneficiario == 0 && beneficiario.cantReporteAvanceBeneficiario == 0  }">
								<a href="#"
									onclick="fEliminar(${beneficiario.beneficiariosPorResultadoID},'${beneficiario.cantidadProgramado} - ${beneficiario.estratoNombre}')" class="linkSelecciona">Eliminar</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
			<br> <br>
		</fieldset>
	</div>
</body>
</html>
