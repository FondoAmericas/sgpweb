<%@include file="/common/includesTaglibsGenerico.jsp"%>

<style type="text/css">
div.ui-datepicker {
	font-size: 62.5%;
}

div.ui-dialog {
	font-size: 62.5%;
}

div.ui-accordion {
	font-size: 60.5%;
}
</style>

<script type="text/javascript">
	$(window).load(function() {
		ocultaCampos();
		
		var mensaje= "<c:out value="${mensaje}"></c:out>";
		if(mensaje!=''){
			alert(mensaje);
		}
	});

	function ocultaCampos() {
		var estado = "<c:out value="${estado}"></c:out>";
		
		//alert(estado);
		if ((estado == 'apro')|| (estado == 'eval')) {
			$(".hide").hide();//attr("style","visibility: hidden;");
			$(".disabled").attr("disabled", "disabled");
			//alert($('#cbxInstitucionEjecutora option:selected').html());
			//$("#div_cbxInstitucionEjecutora").attr("style","visibility: hidden;");
		}
	}
</script>

<script type="text/javascript">
var arrayDesenbolso = new Array();
var arrayCronogramaCA = new Array();
var arrayGrillaADP = new Array();
var montoCambio=parseFloat("<c:out value="${montoCambio}"></c:out>");

function fRegistrar(){
	var errores= validar();
	
	if (errores==0){
		fgetCronogramaDisponible();	
	}
}

function fEliminar(id){
	
	var form = document.formActividadDetallePago;
	form.action = "actionEliminarActividadDetallePago.jspx?actividadDetallePagoId="+id;
	form.submit();
	
}

function cerrar(){
	window.close();	
}

function validar(){
	  var mensaje = "";
	  var errores=0;
	  
	  if($('#resultado').attr("value") == 0){
		 mensaje += "\n Seleccionar Resultado.";
		 errores += 1;
	  }
	  if($('#actividad').attr("value") == 0){
		 mensaje += "\n Seleccionar Actividad.";
		 errores += 1;
	  }
      if($('#costoActividad').attr("value") == 0){
     	 mensaje += "\n Seleccionar Costo Actividad.";
     	errores += 1;
 	  }
	  if($('#periodo').attr("value") == 0){
		 mensaje += "\n Seleccionar Periodo.";
		 errores += 1;
	  }
	  /*if($('#montoGastado').attr("value") == 0){
			 mensaje += "\n Ingresar Monto Gastado.";
		  }*/
	  if($('#montoGastado').attr("value") == ''){
		 mensaje += "\n Ingresar Monto que cubre de periodo seleccionado.";
		 errores += 1;
	  }else{
		  var montoTotal=parseFloat($('#monto').attr("value"));
			var parte=parseFloat($('#montoGastado').attr("value"));
			var montoCambio=parseFloat("<c:out value="${montoCambio}"></c:out>");

			/*if(parte>montoTotal ){
				 alert("Monto ingresado debe ser menor o igual al Monto del periodo seleccionado.");
				 $('#montoGastado').attr("value","");
				 $('#porcentajeMontoTotal').attr("value","");
				//return;  
				 errores += 1;
			 }*/
			
			if( parte>montoCambio){
				 alert("Monto gastado Actividad Detalle debe ser menor al Monto Detalle Pago.");
				 $('#montoGastado').attr("value","");
				 $('#porcentajeMontoTotal').attr("value","");
				//return;  
				errores += 1;
			 }
	  }
	 
	  for ( var p = 0; p < arrayGrillaADP.length; p++) {
		    if($('#periodo').val() == arrayGrillaADP[p].cronogramaCostoActividadID){
		    	mensaje += "\n El periodo ya se encuentra en la lista.";
		    	errores += 1;
		    } 
		}

	  var montoTotal=parseFloat(0);
	  for ( var p = 0; p < arrayGrillaADP.length; p++) {
		    montoTotal +=parseFloat(arrayGrillaADP[p].montoTotal); 
		}
	  //alert(montoTotal);
		montoTotal=parseFloat(montoTotal).toFixed(2);
	
		var montoGastado=$('#montoGastado').val();
		var total=parseFloat(montoTotal) + parseFloat(montoGastado);
		//alert(total);
		if (parseFloat(montoCambio) < parseFloat(total)) {
			mensaje +="\n La suma de los montos de las Actividades seleccionadas, debe ser menor al monto del detalle del pago.";
			 $('#montoGastado').attr("value","");
			 $('#porcentajeMontoTotal').attr("value","");
			errores +=1;
		}
	
	  if(errores>0){
	     alert(mensaje);
		}

	  return errores;
	
}

function fgetPortacentaje(){

	 if($('#montoGastado').attr("value") == ''){
	
		 mensaje += "\n Ingrese Monto Gastado de Actividad.";
		 return;
	 }
	var montoTotal=parseFloat($('#monto').attr("value"));
	var parte=parseFloat($('#montoGastado').attr("value"));
	//var montoCambio=parseFloat(${montoCambio});

	/*if(parte>montoTotal ){
		 alert("Monto gastado de Actividad Detalle debe ser menor al Monto Total del periodo del cronograma seleccionado.");
		 $('#montoGastado').attr("value","");
		 $('#porcentajeMontoTotal').attr("value","");
		return;  
	 }*/
	//alert(montoCambio);
	if( parte>montoCambio){
		 alert("Monto que cubre debe ser menor al monto total del detalle de pago seleccionado.");
		 $('#montoGastado').attr("value","");
		 $('#porcentajeMontoTotal').attr("value","");
		return;  
	 }
	   
	porcentaje=(parte*100)/montoTotal;
	
	$('#porcentajeMontoTotal').attr("value",parseFloat(porcentaje).toFixed(2)); 

}

function fCargarActividad(){       	
    ///var idResultado = $("#resultado").val();
    //var nomMetodo = "cargarActividad";
    $("#actividad").load("cargarCombo.jspx", {resultadoID:$("#resultado").val(),metodo:"cargarActividad"}, function(){fCargarCostoActividad();fCargarPeriodo();} );
    /*$('#periodo').attr("disabled",true);
    $('#periodo').attr("value",0);
    $('#costoActividad').attr("disabled",true);
    $('#costoActividad').attr("value",0);*/
    $('#montoTotalDeCostoActividad').attr("value",0);
    fSelect();
    
}


function fCargarCostoActividad(){       
    /*var idActividad = $("#actividad").val();
    var nomMetodo = "cargarCostoActividad";*/
    $("#costoActividad").load("cargarCombo.jspx", {actividadID:$("#actividad").val(),metodo:"cargarCostoActividad"}, function(){fCargarPeriodo();} );
    //$('#costoActividad').attr("disabled",false);
    /*$('#periodo').attr("disabled",true);
    $('#periodo').attr("value",0);*/
    $('#montoTotalDeCostoActividad').attr("value",0);

    fSelect();
    
}

function fCargarPeriodo(){       
    /*var idCostoActividad = $("#costoActividad").val();
    var idLiquidacion = $("#liquidacionGastoID").val();
    var nomMetodo = "cargarPeriodo";*/
   
    $("#periodo").load("cargarCombo.jspx", {costoActividadID:$("#costoActividad").val(),liquidacionGastoID:$("#liquidacionGastoID").val(),metodo:"cargarPeriodo"});
    //$('#periodo').attr("disabled",false);
    fSelect();
    $('#montoGastado').attr("value","");
	 $('#porcentajeMontoTotal').attr("value","");

    
}

function fSelect(){     
	  $('#periodo').attr("value",0);
	  fgetCronogramaCostoActividad();
	   $('#monto').attr("value",0);
}

function fgetMontoGastadoCostoActividad(){
    var idCostoActividad = $("#costoActividad").val();

	 $.ajax({ url: 'actionGetMontoGastadoCostoActividad.jspx',
	               data: 'costoActividadID='+idCostoActividad,
	               type:'get',
	               dataType: 'html',
	               success: function (response) {
	   				var monto=parseFloat(response).toFixed(2);
						$('#montoTotalDeCostoActividad').attr("value",monto);
	            	 		}
	               });
}

function fgetCronogramaCostoActividad(){
		
	$('#divGrillaCronogramaCostoActividad').load("cargarCronogramaCostoActividad.jspx", 
			{periodoID:$('#periodo').attr("value")},function (){
				for ( var p = 0; p < arrayCronogramaCA.length; p++) {
					if (arrayCronogramaCA[p].cronogramaID == $('#periodo').attr("value")) {
						precioUnitario = arrayCronogramaCA[p].precioUnitario;	
						cantidad = arrayCronogramaCA[p].cantidad;
						$('#monto').attr("value",parseFloat(precioUnitario)*parseFloat(cantidad));
						break;
					} 
					}
				}
		   );	
}

function fgetCronogramaDisponible(){
	cronograma=$('#periodo').attr("value");
	liquidacion=$('#liquidacionGastoID').attr("value");

	 
$.ajax({ url: 'actionGetCronogramaDisponible.jspx?cronogramaID='+cronograma+'&liquidacionID='+liquidacion,
    type:'get',
    dataType: 'html',
    success: function (response) {
     	if (response!='SI') {
    		alert(response);
    			return;
		}else {
				var form = document.formActividadDetallePago;
			    form.action = "actionRegistrarActividadDetallePago.jspx";
			    form.submit();			
	 	 			
			}
	 	 		}
    });
	
}
</script>
<div class="form-clasico">
	<div class="encabezado">Actividad que Cubre</div>
	<form:form name="formActividadDetallePago"
		action="showActividadDetallePago.jspx" method="POST">
		<input type="hidden" id="liquidacionGastoID" name="liquidacionGastoID"
			value="${liquidacionGastoID}">
		<input type="hidden" id="detallePagoID" name="detallePagoID"
			value="${detallePagoID}">
		<input type="hidden" id="desembolsoID" name="desembolsoID"
			value="${desembolso.desembolsoID}">
		<input type="hidden" id="estado" name="estado"
			value="${estado}">	<!-- estado de la liquidacion -->
		<input type="hidden" id="montoCambio" name="montoCambio"
			value="${montoCambio}">	
		<br />
		<fieldset style="padding-left: 15px">

			<c:forEach items="${listPeriodo}" var="modali">
				<script type="text/javascript">
												var objCronogramaCA = new Object();
												objCronogramaCA.cronogramaID = "<c:out value="${modali.cronogramaCostoActividadID }"></c:out>";
												objCronogramaCA.cantidad = "<c:out value="${modali.cantidad}"></c:out>";
												objCronogramaCA.precioUnitario = "<c:out value="${modali.costoActividad.precioUnitario}"></c:out>";
												arrayCronogramaCA.push(objCronogramaCA);
												</script>
			</c:forEach>
			<fieldset style="padding-left: 15px">
				<legend>Detalle Pago</legend>

				<table style="width: 100%;">
					<tr>
						<td style="width: 25%; text-align: right;"><label><b>Cantidad:
							</b> </label>
						</td>
						<td style="width: 25%; text-align: left;"><label> <c:out
									value="${detallePagoLiquidacion.cantidad}"></c:out>&nbsp; <c:forEach
									items="${listUnidadMedida}" var="modali">
									<c:if
										test="${ detallePagoLiquidacion.fkIdtablaespUnidadMedida == modali.tablaEspecificaID}">
										<c:out value="${modali.descripcionCabecera}" />
									</c:if>
								</c:forEach> </label></td>
						<td style="width: 25%; text-align: right;"><label><b>Monto
									Total en <c:forEach items="${listTipoMoneda}" var="modali1">
										<c:if
											test="${modali1.tablaEspecificaID== detallePagoLiquidacion.pagoLiquidacion.fkIdtablaespTipoMoneda}">
											<c:out value="${modali1.descripcionCabecera}:" />
										</c:if>
									</c:forEach> </b> </label>
						</td>
						<td style="width: 25%; text-align: left;"><label> <c:out
									value="${detallePagoLiquidacion.precioTotal} "></c:out> <c:forEach
									items="${listTipoMoneda}" var="modali1">
									<c:if
										test="${modali1.tablaEspecificaID== detallePagoLiquidacion.pagoLiquidacion.fkIdtablaespTipoMoneda}">
										<c:out value="${modali1.descripcionCabecera}" />
									</c:if>
								</c:forEach> </label>
						</td>

					</tr>
					<tr>
						<td style="width: 25%; text-align: right;"><label><b>Precio
									Unitario: </b> </label>
						</td>
						<td style="width: 25%; text-align: left;"><label> <c:out
									value="${detallePagoLiquidacion.precioUnitario}"></c:out>&nbsp;
								<c:forEach items="${listTipoMoneda}" var="modali1">
									<c:if
										test="${modali1.tablaEspecificaID== detallePagoLiquidacion.pagoLiquidacion.fkIdtablaespTipoMoneda}">
										<c:out value="${modali1.descripcionCabecera}" />
									</c:if>
								</c:forEach> </label>
						</td>
						<td style="width: 25%; text-align: right;"><label><b>Monto
									Total en <c:forEach items="${listTipoMoneda}" var="modali">
										<c:if
											test="${ modali.tablaEspecificaID == desembolso.fkIdtablaespTipoMoneda}">
											<c:out value="${modali.descripcionCabecera}:" />
										</c:if>
									</c:forEach> </b> </label>
						</td>
						<td style="width: 25%; text-align: left;"><label> <c:out
									value="${montoCambio} "></c:out> <c:forEach
									items="${listTipoMoneda}" var="modali">
									<c:if
										test="${ modali.tablaEspecificaID == desembolso.fkIdtablaespTipoMoneda}">
										<c:out value="${modali.descripcionCabecera}" />
									</c:if>
								</c:forEach> </label>
						</td>

					</tr>
					<tr>
						<td style="width: 25%; text-align: right;"><label><b>Tipo
									Cambio: </b> </label>
						</td>
						<td style="width: 25%; text-align: left;"><label><c:out
									value="${tipoCambio.tipoCambio}" /> <c:forEach items="${listTipoMoneda}"
									var="modali">
									<c:if
										test="${ modali.tablaEspecificaID == tipoCambio.fkIdtablaespTipoMonedaConvertDE }">
										<c:out value="${modali.descripcionCabecera}" />
									</c:if>
								</c:forEach> </label>
						</td>
					</tr>

				</table>
			</fieldset>
			<table style="width: 100%;" class="hide">
				<tr>
					<td colspan="4" style="width: 100%;">
						<hr></td>
				</tr>
				<tr>
					<td style="text-align: right;width: 20%"><label>Desembolso:</label></td>
					<td style="text-align: left;width: 80%" colspan="3"><label> <c:forEach items="${listTipoDesembolso}"
								var="modali">
								<c:if
									test="${ modali.tablaEspecificaID == desembolso.fkIdtablaespTipoDesembolso}">

									<c:out
										value="${desembolso.periodo}.${desembolso.fuenteFinanciadora.fuenteFinanciadoraID}.${desembolso.versionDePeriodo}.${modali.descripcionCabecera}" />
								</c:if>
							</c:forEach> - <c:forEach items="${listTipoMoneda}" var="modali">
								<c:if
									test="${ modali.tablaEspecificaID == desembolso.fkIdtablaespTipoMoneda}">
									<c:out value="${modali.descripcionCabecera}" />
								</c:if>
							</c:forEach> </label></td>
				</tr>
				<tr>
					<td style="text-align: right; width: 20%"><label>Resultado:</label></td>
					<td style="text-align: left; width: 80%" colspan="3"><select name="resultado" style="width: 100%"
						id="resultado" onchange="javascript:fCargarActividad();">
							<option value="0">
								<c:out value="-- Resultado --" />
							</option>
							<c:forEach items="${listResultado}" var="modali">
								<option value="${modali.resultadoID}">
									<c:out value="${modali.definicionResultado}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td style="text-align: right;width: 20%"><label>Actividad:</label></td>
					<td  style="text-align: left; width: 80%" colspan="3"><select name="actividad" style="width: 100%"
						id="actividad" onchange="javascript:fCargarCostoActividad();">
							<option value="0">
								<c:out value="-- Actividad --" />
							</option>

					</select></td>
				</tr>
				<tr>
					<td style="text-align: right;width: 20%"><label>Costo Actividad
							:</label></td>
					<td  style="text-align: left; width: 80%" colspan="3"><select name="costoActividad" id="costoActividad" style="width: 100%"
						onchange="javascript:fCargarPeriodo();fgetMontoGastadoCostoActividad();">
							<option value="0">
								<c:out value="-- Costo Actividad ** Rubro ** Partida Especifica--" />
							</option>

					</select></td>
				</tr>
				<tr>
					<td style="text-align: right; width: 20%"><label>Periodo:</label></td>
					<td style="text-align: left; width: 80%" colspan="3"><select name="periodo" id="periodo"
						onchange="javascript:fgetCronogramaCostoActividad();">
							<option value="0">
								<c:out value="-- Periodo --" />
							</option>
					</select>
					</td>
				</tr>
				<tr>
					<td style="width: 20%;">
					<td colspan="3" style="width: 80%;">
						<div id="divGrillaCronogramaCostoActividad"></div></td>
				</tr>
			</table>
		</fieldset>
		<br>
		<fieldset style="padding-left: 15px">
			<legend>Lista de Cronograma de Costos Liquidados en Factura</legend>
			<table style="width: 100%;"  class="hide">
				<tr>
					<td style="text-align: right;width: 30%;"><label>Porcentaje
							Cubre:</label></td>
					<td style="text-align: left;width: 20%;"><input type="text" name="porcentajeMontoTotal"
						maxlength="0" id="porcentajeMontoTotal" style="width: 80px;"
						onkeypress="return isNumberKeyPBD(event);"
						value="${actividadDetallePago.porcentajeMontoTotal}" /> <label>%</label>
					</td>
					<td style="text-align: right;width: 30%;"><label> <!-- Monto Gastado
							(actvidad costo) -->Monto Total <br/>de Costo Actividad:</label></td>
					<td style="text-align: left;width: 20%;"><input type="text" name="montoTotalDeCostoActividad" style="width: 50%"
						id="montoTotalDeCostoActividad" maxlength="0"
						onkeypress="return isNumberKeyPBD(event);"
						value="${actividadDetallePago.montoTotalDeCostoActividad}" /> <label> <c:forEach
								items="${listTipoMoneda}" var="modali">
								<c:if
									test="${ modali.tablaEspecificaID == desembolso.fkIdtablaespTipoMoneda}">
									<c:out value="${modali.descripcionCabecera}" />
								</c:if>
							</c:forEach> </label></td>
				</tr>
				<tr>
					<td style="text-align: right;width: 30%;"><label> <!-- Monto Gastado 
							(actividad detalle pago) -->Monto que cubre de Periodo Seleccionado:</label></td>
					<td  style="text-align: left;width: 20%;"><input type="text" name="montoGastado"
						style="width: 50%" id="montoGastado"
						value="${actividadDetallePago.montoGastado}"
						onkeydown="if(event.keyCode == 13){fgetPortacentaje();}"
						onchange="fgetPortacentaje()" /> <label> <c:forEach
								items="${listTipoMoneda}" var="modali">
								<c:if
									test="${ modali.tablaEspecificaID == desembolso.fkIdtablaespTipoMoneda}">
									<c:out value="${modali.descripcionCabecera}" />
								</c:if>
							</c:forEach> </label></td>
<td style="text-align: right;width: 30%;"><label>Monto Total<br />(Periodo
							Seleccionado):</label></td>
					<td style="text-align: left;width: 20%;"><input type="text" maxlength="0" style="width: 50%"
						id="monto" name="monto" onkeypress="return isNumberKeyPBD(event);">
						<label> <c:forEach items="${listTipoMoneda}" var="modali">
								<c:if
									test="${ modali.tablaEspecificaID == desembolso.fkIdtablaespTipoMoneda}">
									<c:out value="${modali.descripcionCabecera}" />
								</c:if>
							</c:forEach> </label></td>

					
				</tr>
				<tr>
					<td colspan="4" style="text-align: right; width: 100%;"><input
						type="button" name="Agregar" value="Agregar"
						onclick="javascript:fRegistrar();"> <input type="button"
						value="Cerrar" id="idBtnCerrar" onclick="javascript:cerrar();"/></td>
				</tr>

			</table>
			<table class="table-clasico" style="width: 100%">
				<caption>Lista</caption>
				<thead>
					<tr>
						<th align="center"><label>Resultado</label>
						</th>
						<th align="center"><label>Actividad</label>
						</th>
						<th align="center"><label>Costo Actividad</label>
						</th>
						<th align="center"><label>Periodo </label>
						</th>
						<th align="center"><label>Monto Total <br />Costo
								Actividad</label>
						</th>
						<th align="center"><label>Monto Gastado <br/>de Periodo </label>
						</th>
						<th align="center"><label>Porcentaje<br/> Cubierto de Periodo</label>
						</th>
						<th align="center"  class="hide"><label>Eliminar</label>
						</th>
					</tr>
				</thead>
				<c:forEach var="actividadG" items="${listActividadDetallePago}"varStatus="indice">
	<c:choose>
										<c:when test="${indice.count %2== 0}">
											<c:set var="classIdi" value="f2"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdi" value="f1"></c:set>
										</c:otherwise>
									</c:choose>
	<tr class="<c:out value="${classIdi}"></c:out>">
						<td align="center"><c:out
								value="${actividadG.cronogramaCostoActividad.costoActividad.actividad.resultado.definicionResultado}" />
						</td>
						<td align="center"><c:out
								value="${actividadG.cronogramaCostoActividad.costoActividad.actividad.nombreActividad}" />
						</td>
						<td align="center"><c:out
								value="${actividadG.cronogramaCostoActividad.costoActividad.cantidadTotal}" />
							- <c:forEach items="${listUnidadMedida}" var="modali">
								<c:if
									test="${actividadG.cronogramaCostoActividad.costoActividad.fkIdtablaespUnidadMedida == modali.tablaEspecificaID}">
									<c:out value="${modali.descripcionCabecera}" />
								</c:if>
							</c:forEach></td>
						<td align="center"><c:out
								value="${actividadG.cronogramaCostoActividad.periodo}"></c:out>&nbsp;
						</td>
						<td align="center"><c:out value="${actividadG.montoTotalDeCostoActividad}"></c:out>&nbsp;
							<c:forEach items="${listTipoMoneda}" var="modali1">
								<c:if
									test="${modali1.tablaEspecificaID== actividadG.fkIdtablaespTipoMoneda}">
									<c:out value="${modali1.descripcionCabecera}" />
								</c:if>
							</c:forEach></td>
						<td align="center"><c:out
								value="${actividadG.montoGastado}"></c:out>&nbsp; <c:forEach
								items="${listTipoMoneda}" var="modali1">
								<c:if
									test="${modali1.tablaEspecificaID== actividadG.fkIdtablaespTipoMoneda}">
									<c:out value="${modali1.descripcionCabecera}" />
								</c:if>
							</c:forEach></td>
						<td align="center"><c:out
								value="${actividadG.porcentajeMontoTotal}"></c:out>&nbsp; %</td>

						<td align="center" class="hide"><a
							href="javascript:fEliminar('${actividadG.actividadDetallePagoID}');"
							class="linkSelecciona" style="cursor: pointer" title="Actividad">Eliminar</a>
						</td>
					</tr>
					<script type="text/javascript">
												var objGrillaADP = new Object();
												objGrillaADP.montoTotal = "<c:out value="${actividadG.montoGastado}"></c:out>";
												objGrillaADP.cronogramaCostoActividadID = "<c:out value="${actividadG.cronogramaCostoActividad.cronogramaCostoActividadID}"></c:out>";
												arrayGrillaADP.push(objGrillaADP);
												</script>
				</c:forEach>
			</table>
		</fieldset>
	</form:form>
</div>