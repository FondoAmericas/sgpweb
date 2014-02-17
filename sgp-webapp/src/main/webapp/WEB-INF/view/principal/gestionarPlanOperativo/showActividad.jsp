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
		function goTo(url,ancho,alto) {
			//window.location = url;
			fOpenModalDialog(url,ancho,alto,'100','100');
		}
		
		function soloNumero(e) {
		    if ([e.keyCode||e.which]==8 || [e.keyCode||e.which]==9 || [e.keyCode||e.which]==46) 
		        return true;
		    if ([e.keyCode||e.which] < 48 || [e.keyCode||e.which] > 57)
		        e.preventDefault? e.preventDefault() : e.returnValue = false;
		}
		function submitFormActividad() {
			if (validar()) {
				return;
			}
			if(confirm("\u00BFEst\u00E1 seguro de guardar los datos ingresados?")){
			   document.formActividad.action = "actionSaveActividad.jspx";
			   document.formActividad.submit();
			}
		}
		
		function validar(){
		      var cabecera = "Debe ingresar la siguiente informaci\u00F3n obligatoria(*):";
		      var mensaje = "";
		      if($('#nombreActividad').attr("value") == ""){
		          mensaje += "\n - Nombre.";
		      }
		      
			  if($('#descripcionActividad').attr("value") == ""){
			      mensaje += "\n - Descripcion.";
			  }
			   
		      if($('#duracionMeses').attr("value") == ""){
		             mensaje += "\n - Duracion.";
		      }
		      var duracionMeses = parseInt($('#duracionMeses').attr("value"));
		      var duracionResultdo = parseInt($('#duracionResultado').attr("value"));
			  if(duracionMeses > duracionResultdo){
				  mensaje += "\n - La duracion del actividad no puede exceder a la del Resultado (" + $('#duracionResultado').attr("value") +" meses).";
			  }
		     
		      if(mensaje != ""){
		         alert(cabecera + mensaje);
		         return true;
		      }else{
		         return false;
		      }
		   
		}
		
		function fEliminar(actividadID, codigoResultado, codigoActividad){
			if(confirm("\u00BFEst\u00E1 seguro de eliminar la actividad " + codigoResultado + "." + codigoActividad + " ?")){
				var datoPlanOperativoID = $("#datoPlanOperativoID").val();
				var resultadoID = $("#resultadoID").val();
			   window.location.href = "actionDeleteActividad.jspx?actividadID=" + actividadID 
					                + "&datoPlanOperativoID=" + datoPlanOperativoID
					                + "&resultadoID=" + resultadoID;
			}			
		}
		
		function modificarActividad(actividadID,codigoResultado,codigoActividad,nombreActividad,duracionMeses,descripcionActividad){
			var confirma = confirm("Esta seguro que desea cargar el registro para modificarlo?");
			
			if(confirma==true){
			$('#actividadID').attr("value",actividadID); 
			$('#nombreActividad').attr("value",nombreActividad);
			 $('#descripcionActividad').attr("value",descripcionActividad);
			 $('#duracionMeses').attr("value",duracionMeses);
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
<body onload="load()">

	<div class="form-clasico">
	<div class="encabezado">Actividades por Resultado</div>
		<br>
		<fieldset>
			<legend>Crear Actividad</legend>
			<form:form name="formActividad" method="post" action="">
				<input type="hidden" id="mensaje" name="mensaje" value="${mensaje}" />
				<input type="hidden" id="datoPlanOperativoID"
					name="datoPlanOperativoID"
					value="${planOperativo.datoPlanOperativoID}">
				<input type="hidden" id="resultadoID" name="resultadoID"
					value="${resultado.resultadoID}">
				<input type="hidden" id="duracionResultado" name="duracionResultado"
					value="${resultado.duracionMeses}">
				<input type="hidden" id="actividadID" name="actividadID">	
				
				<table border="0" width="100%">
					<tr>
						<td style="text-align: right; width: 20%;"><label>Proyecto:</label>
						</td>
						<td style="text-align: left; width: 80%" colspan="3"><label
							for="proyectoNombre">${planOperativo.nombreProyecto}</label>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 20%"><label>Codigo
								Proyecto:</label></td>
						<td style="text-align: left; width: 30%"><label
							for="proyectoCodigo">${planOperativo.codigoProyecto}</label>
						</td>
						<td style="text-align: right; width: 20%"><label>Version
								PO:</label></td>
						<td style="text-align: left; width: 30%"><label
							for="planOperativo">${planOperativo.version}</label>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 20%"><label>Resultado:</label>
						</td>
						<td style="text-align: left; width: 80%" colspan="3"><label
							for="resultadoNombre">${resultado.definicionResultado}</label>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 20%"><label>Duracion
								Resultado:</label></td>
						<td style="text-align: left; width: 80%" colspan="3"><label
							for="planOperativo">${resultado.duracionMeses} meses</label>
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
				<fieldset class="hide">
					<legend>
						<label>Informacion de la Actividad</label>
					</legend>
					<table>
						<tr>
							<td style="text-align: right; width: 20%"><label>Nombre
									Actividad:</label></td>
							<td style="text-align: left; width: 70%" colspan="3"><textarea
									rows="3" cols="75" id="nombreActividad" name="nombreActividad"
									style="width: 95%;"></textarea>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 20%"><label>Descripcion
									Actividad:</label></td>
							<td style="text-align: left; width: 70%" colspan="3"><textarea
									rows="3" cols="75" id="descripcionActividad"
									name="descripcionActividad" style="width: 95%;"></textarea>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 20%"><label>Duracion:</label>
							</td>
							<td style="text-align: left; width: 70%" colspan="3"><input
								type="text" id="duracionMeses" name="duracionMeses" size="11"
								onkeypress="soloNumero(event)" maxlength="3"
								style="width: 45px;" /> <label>(meses)</label>
							</td>
						</tr>
						<tr>
							<td colspan="4"><br>
							</td>
						</tr>
						<tr>
							<td style="width: 50%;" colspan="2">&nbsp;</td>
							<td style="text-align: right; width: 30%;"><input
								type="button" value="Cerrar" id="idBtnCerrar" /></td>
							<td style="text-align: center; width: 20%;"><input
								type="button" name="save" value="Grabar Actividad"
								onClick="submitFormActividad()">
							</td>
						</tr>
					</table>
				</fieldset>
				<br class="hide">
			</form:form>
			<form>
				<table class="table-clasico" style="width: 100%">
					<caption>Actividades</caption>
					<thead>
						<tr>
							<!-- <th align="center"><label></label></th> -->
							<th align="center"><label>Codigo</label></th>
							<th align="center"><label>Actividades Principales</label></th>
							<th align="center"><label>Tipo Actividad</label></th>
							<th align="center"><label>Duracion<br>(Meses)</label></th>
							<th align="center"><label>Opciones</label></th>
							<th align="center" class="hide"><label>Operaciones</label></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="actividad" items="${listActividad}"
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
								<!-- <td align="center"><input type="radio" name="actividadId" value="${actividad.actividadID}"></td> -->
								<td align="left"><c:out
										value="${resultado.codigoResultado}.${actividad.codigoActividad}"></c:out>
								</td>
								<td align="left"><c:out
										value="${actividad.nombreActividad}"></c:out></td>
								<td align="left"><c:out
										value="${actividad.tipoActividadNombre}"></c:out></td>
								<td align="center"><c:out
										value="${actividad.duracionMeses}"></c:out></td>
								<td align="center"><label><a
										href="javascript:goTo('showMetaActividad.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID}&actividadID=${actividad.actividadID}&estadoPlanOperativo=${estadoPlanOperativo}','800','650')"
										id="cronograma" class="linkSelecciona">Meta Actividad</a> </label> <br>
									<label><a
										href="javascript:goTo('showCostoActividad.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID}&actividadID=${actividad.actividadID}&estadoPlanOperativo=${estadoPlanOperativo}','800','650')"
										id="cronograma" class="linkSelecciona">Costo Actividad</a> </label>
								</td>
								<td align="center" class="hide">
								<c:choose>
									<c:when test="${resultado.codigoResultado != 0 }">
								<a
									href="javascript:modificarActividad('${actividad.actividadID}','${resultado.codigoResultado}',
																		'${actividad.codigoActividad}','${actividad.nombreActividad}',
																		'${actividad.duracionMeses}','${actividad.descripcionActividad}')"  class="linkSelecciona">Modificar</a><br /> <a
									href="#"
									onclick="fEliminar(${actividad.actividadID},${resultado.codigoResultado},${actividad.codigoActividad})"  class="linkSelecciona">Eliminar</a>
									</c:when>
									<c:otherwise><label>-</label></c:otherwise>
								</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 
		<br>
		<table border="0" width="100%">
			<tr>
				<td style="text-align: right;">
				<input type="button" 
				       value="Meta por Actividad" 
				       onClick="goToMetaActividad(this.form, 'showMetaActividad.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID}&actividadID=');">
				<input type="button" 
				       value="Costo por Actividad" 
					   onClick="goToCostoActividad(this.form, 'showCostoActividad.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID}&actividadID=');">
				</td>
			</tr>
		</table> -->
			</form>
		</fieldset>
	</div>

</body>
</html>
