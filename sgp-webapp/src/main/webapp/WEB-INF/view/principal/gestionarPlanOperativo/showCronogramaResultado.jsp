<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="includesTaglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	function submitFormCronogramaResultado() {
		if (validar()) {
			return;
		}
		if(confirm("\u00BFEst\u00E1 seguro de guardar los datos ingresados?")){
		   document.formCronogramaResultado.action = "actionSaveCronogramaResultado.jspx";
		   document.formCronogramaResultado.submit();
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
	      if($('#avanceMeta').attr("value") == ""){
	          mensaje += "\n - Debe ingresar el Avance de la Meta.";
	      }
	      
		  if($('#sltPeriodo').attr("value") == "0"){
		      mensaje += "\n - Debe seleccionar el Periodo.";
		  }		  
		  
	      if(mensaje != ""){
	         alert(cabecera + mensaje);
	         return true;
	      }else{
	         return false;
	      }
	   
	}
	
	function fEliminar(cronogramaMetaPorResultadoID, periodo){
		if(confirm("\u00BFEst\u00E1 seguro de eliminar el periodo \"" + periodo + "\" ?")){
			var datoPlanOperativoID = $("#datoPlanOperativoID").val();
			var resultadoID = $("#resultadoID").val();
		    window.location.href = "actionDeleteCronogramaResultado.jspx?cronogramaMetaPorResultadoID=" + cronogramaMetaPorResultadoID 
			 	                 + "&datoPlanOperativoID=" + datoPlanOperativoID
				                 + "&resultadoID=" + resultadoID;
		}			
	}
	
	function modificarMetaResultado(resultadoId){
		var error = validaCampoMetaResultado();
		
		if (error == 0) {
			$.get("grabarCantidadMetaResultado.jspx", {
				cantidadMetaResultado : $("#cantidadMetaResultado").val(),
				resultadoId : resultadoId
			},function(){
				alert("Se modifico exitosamente la cantidad de la meta del resultado");
				window.opener.fRefresh("50");
			});
	}
	}
		
	function fModificar(cronogramaMetaPorResultadoID,periodo,avanceMeta){
		$("#avanceMeta").attr("value",avanceMeta);
		$("#sltPeriodo").attr("value",periodo);
		$("#cronogramaMetaPorResultadoID").attr("value",cronogramaMetaPorResultadoID);
	}
	
	function validaCampoMetaResultado(){
		var errores = 0;
		var mensaje = "";
		
		if ($("#cantidadMetaResultado").val().length == 0) {
			mensaje += "Debe ingresar la meta del resultado. \n";
			errores = errores + 1;
		}

		if ($("#cantidadMetaResultado").val() == 0) {
			mensaje += "Debe ingresar una cantidad en la meta del resultado. \n";
			errores = errores + 1;
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
	<div class="encabezado">Cronograma de la Meta del Resultado</div>
		<br>
		<fieldset>
			<legend>Crear Cronograma Meta por Resultados</legend>
			<form:form name="formCronogramaResultado" method="post" action="">
				<input type="hidden" id="datoPlanOperativoID"
					name="datoPlanOperativoID"
					value="${planOperativo.datoPlanOperativoID}">
				<input type="hidden" id="resultadoID" name="resultadoID"
					value="${resultado.resultadoID}">
				<input type="hidden" id="cronogramaMetaPorResultadoID" name="cronogramaMetaPorResultadoID">	
				<input type="hidden" id="mensaje" name="mensaje" value="${mensaje}">
				<table border="0" width="100%">
					<tr>
						<td style="text-align: right; width: 30%; vertical-align: top;"><label>Proyecto:</label>
						</td>
						<td style="text-align: left; width: 70%;" colspan="3"><label
							for="proyectoCodigo">${planOperativo.nombreProyecto}</label></td>
					</tr>
					<tr>
						<td style="text-align: right; width: 30%;"><label>Codigo
								Proyecto:</label>
						</td>
						<td style="text-align: left; width: 20%;"><label
							for="proyectoNombre">${planOperativo.codigoProyecto}</label></td>
						<td style="text-align: right; width: 20%;"><label>Version
								PO:</label>
						</td>
						<td style="text-align: left; width: 30%;"><label
							for="planOperativo">${planOperativo.version}</label></td>
					</tr>
					<tr>
					<td style="text-align: right; width: 30%; vertical-align: top">
					    <label>Tipo de Periodo:</label>
					</td>
					<td colspan="3" style="text-align: left; width: 70%; vertical-align: top">
					    <label>${planOperativo.datoProyecto.programa.tipoPeriodo.descripPeriodo }</label>
					</td>
				</tr>
					<tr>
						<td style="text-align: right; width: 30%;"><label>Resultado:</label>
						</td>
						<td style="text-align: left; width: 70%;" colspan="3"><label
							for="resultado">${resultado.definicionResultado}</label></td>
					</tr>
					<tr>
						<td style="text-align: right; width: 30%;"><label>Meta
								Resultado:</label>
						</td>
						<td style="text-align: left; width: 70%;" colspan="3"><label
							for="metaResultado"><input type="text"
							id="cantidadMetaResultado" name="cantidadMetaResultado"
							onkeypress="soloNumero(event)" maxlength="6" style="width: 50px; text-align: center;" value="${resultado.metaResultado }"/> -
								${resultado.estratoNombre}  <a style="margin-left: 40px;" 
											href="javascript:modificarMetaResultado('${resultado.resultadoID }')"
											class="hide" class="linkSelecciona">Modificar</a></label></td>
					</tr>
					</table>
					<fieldset class="hide">
					<table>
					<tr>
						<td style="text-align: right; width: 30%;"><label>Cantidad
								Avance de Meta:</label>
						</td>
						<td style="text-align: left; width: 20%;"><input type="text"
							id="avanceMeta" name="avanceMeta" size="11"
							onkeypress="soloNumero(event)" maxlength="6" style="width: 65px;text-align: center;" />
						</td>
						<td style="text-align: right; width: 20%;"><label>Periodo:</label>
						</td>
						<td style="text-align: left; width: 30%;">
							<!-- <input type="text" id="periodo" name="periodo" size="11" onkeypress="soloNumero(event)" maxlength="2" style="width:65px;"/> -->
							<select name="sltPeriodo" id="sltPeriodo" style="text-align: center;">
								<option value="0" >
									<c:out value="-- Periodo --" />
								</option>
								<c:forEach items="${listPeriodos}" var="periodo">
									<option value="${periodo}" >
										<c:out value="${periodo}" />
									</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td colspan="4"><br></td>
					</tr>
					<tr>
						<td style="text-align: right; width: 100%" colspan="4"><input
							type="button" value="Cerrar" id="idBtnCerrar" /> <input
							type="button" value="Grabar Cronograma Resultado"
							onClick="submitFormCronogramaResultado()"></td>
					</tr>

				</table>
				</fieldset>
			</form:form>
			<br class="hide"/>
			<form>

				<table class="table-clasico" style="width: 100%; margin: 0 auto;">
					<caption>Cronograma Resultado</caption>
					<thead>
						<tr>
							<th align="center"><label>Avance de Meta</label>
							</th>
							<th align="center"><label>Periodo</label>
							</th>
							<th align="center" class="hide"><label>Operaciones</label>
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cronograma" items="${listCronogramaResultadoView}"
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
								<td align="center"><c:out
										value="${cronograma.avanceMeta} - ${resultado.estratoNombre}"></c:out>
								</td>
								<td align="center"><c:out
										value="Periodo ${cronograma.periodo}"></c:out>
								</td>
								<td align="center" class="hide"><a href="#"
									onclick="fModificar(${cronograma.cronogramaMetaPorResultadoID}, ${cronograma.periodo},${cronograma.avanceMeta})" class="linkSelecciona">Modificar</a> <br /> <a href="#"
									onclick="fEliminar(${cronograma.cronogramaMetaPorResultadoID}, ${cronograma.periodo})" class="linkSelecciona">Eliminar</a>
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
