<%@include file="/common/includesTaglibsGenerico.jsp"%>
<%@include file="/common/taglibs.jsp"%>


<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript"
	src="<c:url value="/js/util.js"></c:url>"></script>
	
<style type="text/css">
div.ui-datepicker {
	font-size: 62.5%;
	background:#ade89f;
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
	});

	function ocultaCampos() {
		estado = "<c:out value="${estado}"></c:out>";
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
var v_igv = new Number('18');
var m_igv = new Number(v_igv / 100);

$(document).ready(function() {
	if($('#mensaje').attr("value") != ''&& $('#mensaje').attr("value") != undefined){
        alert($('#mensaje').attr("value"));
     }
	//$("#etiquetaTipoComprobante").attr("class","inputTextlabelMuestra");
	$("#igv").hide("fast");
	$("#renta").hide("fast");
	$("#muestraImagenModifica").hide("fast");
});

function fRefresh(tiempo){
	fSetTimeOutRefreshLocation(tiempo);
	//location.reload();
}

 function getMontos(){
	 
	 if ($('#igvMonto').attr("value")=="" ){
			alert("Ingresar igv");
			return;
		} 
		if ($('#montoTotal').attr("value")=="") {
			alert("Ingresar Monto Total");
			return;
		} 
 	var v1_igv =(parseInt( $('#igvMonto').attr("value"))+100)/100;
 	var v2_igv =parseInt( $('#igvMonto').attr("value"))/100;
 	base = parseFloat($('#montoTotal').attr("value"));
 	subTotal=parseFloat(base/v1_igv).toFixed(2);
 	igv =parseFloat(subTotal*v2_igv).toFixed(2);
 	document.getElementsByName('montoIgv')[0].value = igv; 
 	document.getElementsByName('subTotal')[0].value = subTotal; 

 }

 function getMensajeComprobanteRuc() {
	 var ruc = $("#rucProveedor").attr("value");
	  var numero = $("#numeroDocumento").attr("value");
	  
	 $.ajax({ url: 'actionGetMensajeComprobanteRuc.jspx',
	               data: 'rucComprador='+ruc+'&numeroComprobante='+numero,
	               type:'get',
	               dataType: 'html',
	               success: function (response) {
						var estado=response;
						if (estado=='SI') {
							  alert("Documento ya existe asignado al Proveedor"); 
				               			return;
						}else{
							 var form = document.formShowIngresoPago;
					    form.action = "actionRegistrarIngresoPago.jspx";
					    form.submit();
						}
	            	 		}
	               });
	 //limpiarCajas();
 }

function fRegistrar(){
	var validaCajas = validar();
	
	if(validaCajas==0){
		if ($("#ingresoPagoID").val()=='0') {
			var validaArchivo = validarExtensionArchivo();
			if(validaArchivo==true){
				getMensajeComprobanteRuc();
			}
		}else{
		   	 var form = document.formShowIngresoPago;
			    form.action = "actionRegistrarIngresoPago.jspx";
			    form.submit();
		}    
	}
	//limpiarCajas();
}

function fDetallePago(url) {

	fOpenModalDialog(url,'900','600','90','60');
}


function validar(){
      var mensaje = "";
      var errores=0;

      if($('#tipoComprobante').val() == 0){
         mensaje += "\n Seleccione Tipo Comprobante.";
         errores +=1;
      }
      if($('#numeroDocumento').val().length == 0){
         mensaje += "\n Ingrese Numero Comprobante.";
         errores +=1;
      }
      if($('#fechaEmision').val().length == 0){
          mensaje += "\n Ingrese Fecha Emision.";
          errores +=1;
       }
      if($('#rucProveedor').val().length == 0){
         mensaje += "\n Ingrese Ruc del vendedor.";
         errores +=1;
      }
      if($('#razonSocial').val().length == 0){
         mensaje += "\n Ingrese Razon Social.";
         errores +=1;
      }
      if($('#montoTotal').val().length == 0){
             mensaje += "\n Ingrese Monto Total.";
             errores +=1;
      }
      if($('#tipoMoneda').val() == 0){
             mensaje += "\n Seleccione Tipo Moneda.";
             errores +=1;
          }
      if($('#desembolsoID').val() == 0){
          mensaje += "\n Seleccione desembolso asociado al gasto.";
          errores +=1;
       }
      if($('#igvMonto').val().length == 0){
          mensaje += "\n Ingrese porcentaje de impuesto.";
          errores +=1;
   		}
      //numeroCheque
      
      /*if($('#saldoPagado').attr("value") == ''){
         mensaje += "\n - saldoPagado.";
      }
      if($('#montoIgv').attr("value") == ''){
             mensaje += "\n - Monto Igv.";
      }
      if($('#imagen').attr("value") == ''){
         mensaje += "\n - Imagen Factura.";
      }*/
      
            if(errores >0){
         alert(mensaje);
      }
   return errores;
}


$(function() {
	$("#fechaEmision").datepicker({
		changeMonth : true,
		changeYear : true
	});
});

function fModificar(pagoLiquidacionID,fechaEmisionString,desembolsoID,numeroDocumento,fkIdtablaespTipoComprobantePago,numeroCheque,
				rucProveedor,chequeCobrado,razonSocial,tasaIgv,montoIgv,montoTotal,fkIdtablaespTipoMoneda,saldoPagado){
	var confirma = confirm("Esta seguro que desea cargar el registro para modificarlo?");
	
	if(confirma==true){
	if (chequeCobrado==1) {
		$("#chequeCobrado").attr("checked","checked");
	} 
	$('#ingresoPagoID').attr("value",pagoLiquidacionID);
    $('#tipoComprobante').attr("value",fkIdtablaespTipoComprobantePago);
    $('#numeroDocumento').attr("value",numeroDocumento);
    $('#fechaEmision').attr("value",fechaEmisionString);
    $('#rucProveedor').attr("value",rucProveedor);
    $('#razonSocial').attr("value",razonSocial);
    $('#montoTotal').attr("value",montoTotal);
    $('#tipoMoneda').attr("value",fkIdtablaespTipoMoneda);
     $('#desembolsoID').attr("value",desembolsoID);
     $('#igvMonto').attr("value",tasaIgv); //taza
     $('#saldoPagado').attr("value",saldoPagado);
     $('#montoIgv').attr("value",montoIgv);
     $("#subTotal").attr("value",montoTotal-montoIgv);
     $("#numeroCheque").attr("value",numeroCheque);
   
     $("#muestraImagenModifica").show("fast");
     $("#archivoDescargable").load("showImagenArchivoDownloadPagoLiquidacion.jspx?pagoLiquidacionId="+pagoLiquidacionID);
	}
}

function limpiarCajas(){
	
	$("#chequeCobrado").removeAttr("checked");
	$('#ingresoPagoID').attr("value",0);
    $('#tipoComprobante').attr("value",0);
    $('#numeroDocumento').attr("value","");
    $('#fechaEmision').attr("value","");
    $('#rucProveedor').attr("value","");
    $('#razonSocial').attr("value","");
    $('#montoTotal').attr("value","");
    $('#tipoMoneda').attr("value",0);
     $('#desembolsoID').attr("value",0);
     $('#igvMonto').attr("value",""); //taza
     $('#saldoPagado').attr("value",0);
     $('#montoIgv').attr("value","");
     $("#subTotal").attr("value","");
     $("#numeroCheque").attr("value","");
   
     $("#muestraImagenModifica").hide("fast");
	
}

function fEliminar(id){
	//alert("Por Implementar");
	$("#ingresoPagoID").attr("value",id);
	if (confirm("Desea eliminar el Ingreso Pago?")) {
		var form = document.formShowIngresoPago;
	    form.action = "actionEliminarIngresoPago.jspx";
	    form.submit();
	   
	}
	
}

function cambiarEtiqueta(){
	if($("#tipoComprobante").val()=='104'||$("#tipoComprobante").val()=='106'||$("#tipoComprobante").val()=='108'){
		$("#igv").show("fast");
		$("#renta").hide("fast");
		$("#igvMonto").attr("value",18);
	}else{
		$("#igv").hide("fast");
		$("#renta").show("fast");
		$("#igvMonto").attr("value",10);
	}
}
</script>

<script type="text/javascript">
function validarExtensionArchivo(){
	   extensiones_permitidas = new Array("doc","docx","pdf","DOC","DOCX","PDF","jpg","jpeg","JPG","JPEG");
	   ruta_archivo=$("#imagenODocumento").val();
	   if(!ruta_archivo){
	      alert("No se ha seleccionado ningun archivo(Factura Escaneada)!");
	      return false;
	   }else{
		  arrayNombreArchivo = ruta_archivo.split(".");
		  nombreArchivo=arrayNombreArchivo[0];
		  
		  nombreArchivoUpload=nombreArchivo;//variable en gestorAjax
		  
		  nombreArchivo = nombreArchivo.split('\\');
		  nombreArchivo=nombreArchivo[nombreArchivo.length-1];
		  //extension="."+arrayNombreArchivo[1];
		  extension=arrayNombreArchivo[1];
		  //alert("nombre de archivo: "+nombreArchivo);
	      //alert("extension de archivo: "+extension);
	      $("#nombreArchivo").attr("value",nombreArchivo);
	      $("#extension").attr("value",extension);
	      //alert(extension);
	      extensionPermitida=false;
		  for (var i = 0; i < extensiones_permitidas.length; i++) { 
	         if (extensiones_permitidas[i] == extension) { 
				 extensionPermitida=true; 
				 break; 
	         } 
	      } 
		  if (!extensionPermitida) { 
			alert("Solo se pueden subir archivos con las siguientes extensiones: "+extensiones_permitidas.join()); 
		    return false;
		  }
	   }
	   return true;
	}

</script>

</head>
<body> 

<div class="form-clasico">
<div class="encabezado">Reporte de Facturas</div>
	<form:form  commandName="imagenOArchivoTempBean" id="formShowIngresoPago" 
				name="formShowIngresoPago" action="showIngresoPago.jspx"
				method="POST"  enctype="multipart/form-data">
	<input type="hidden" id="ingresoPagoID" name="ingresoPagoID" value="${ingresoPagoID }">
		<input type="hidden" id="liquidacionGastoID" name="liquidacionGastoID"
			value="${liquidacionGastoID}">
		<input type="hidden" id="fuenteFinanciadora" name="fuenteFinanciadora" value="${liquidacion.fuenteFinanciadora.institucion.nombreInstitucion}">	
		<input type="hidden" id="mensaje" name="mensaje" value="${mensaje}">
		<br />
		<fieldset style="padding-left: 15px">
			<legend>Listado de Facturas</legend>
			<fieldset style="padding-left: 15px">
				<legend>Informacion de Fuente Financiadora</legend>
				<table style="width: 100%;">
					<tr>
						<td style="text-align: right; width: 25%;"><label><b>Fuente
									Financiadora: </b>
						</label></td>
						<td  style="text-align: left; width: 25%;"><label> <c:out
									value="${liquidacion.fuenteFinanciadora.institucion.nombreInstitucion}" />
						</label></td>
						<td  style="text-align: right; width: 25%;"><label><b>Saldo
									Disponible de Financiamiento:</b>
						</label></td>
						<td  style="text-align: left; width: 25%;"><label><c:out value="${saldoDisponible}" /> - <c:forEach
									items="${listTipoMoneda}" var="tipoMoneda">
									<c:if
										test="${ tipoMoneda.tablaEspecificaID == liquidacion.fuenteFinanciadora.fkIdtablaespTipoMoneda}">
										<c:out value="${tipoMoneda.descripcionCabecera}" />
									</c:if>
								</c:forEach> </label></td>
					</tr>
				</table>
			</fieldset>
			<br />
			<table width="100%" class="hide">
				<tr>
					<td style="text-align: right; width: 20%;"><label>Fecha Emision:</label></td>
					<td style="text-align: left; width: 30%;"><input type="text" name="fechaEmision" maxlength="0"
						id="fechaEmision" value="${fechaEmision}" style="width: 110px;"
						onkeypress="javascript:return Valida_Dato(event,7), isNumberKeyPBD(event);" /><label>(dd/mm/aaaa)</label>
					</td>
					<td style="text-align: right; width: 20%;"><label> Desembolso:</label>
					</td>
					<td style="text-align: left; width: 30%;"><select name="desembolsoID" id="desembolsoID">
							<option value="0">
								<c:out value="-- Desembolso --" />
							</option>
							<c:forEach items="${listDesembolso}" var="desembolso1">
								<option value="${desembolso1.desembolsoID}">
									<c:forEach items="${listTipoDesembolso}" var="modali">
										<c:if
											test="${desembolso1.fkIdtablaespTipoDesembolso == modali.tablaEspecificaID}">
											<c:out
												value="${desembolso1.periodo}.${desembolso1.fuenteFinanciadora.fuenteFinanciadoraID}.${desembolso1.versionDePeriodo}.${modali.descripcionCabecera}" />
										</c:if>
									</c:forEach>
								</option>
							</c:forEach>
					</select>
					<td>
				</tr>
				<tr>
					<td style="text-align: right; width: 20%;"><label>N&uacute;mero
							Comprobante:</label></td>
					<td style="text-align: left; width: 30%;"><input type="text" name="numeroDocumento"
						id="numeroDocumento" value="${pagoLiquidacion.numeroDocumento}"
						maxlength="11" onkeypress="return isNumberKeyP(event);" style="width: 100px;">
						<select name="tipoComprobante" id="tipoComprobante" style="width: 150px;" onchange="cambiarEtiqueta()">
							<option value="0">
								<c:out value="--Tipo Comprobante--" />
							</option>
							<c:forEach items="${listTipoComprobante}" var="modali">
								<option value="${modali.tablaEspecificaID}"
									<c:if test="${ pagoLiquidacion.fkIdtablaespTipoComprobantePago == modali.tablaEspecificaID}"> selected="selected" </c:if>>
									<c:out value="${modali.descripcionCabecera}" />
								</option>
							</c:forEach>
					</select></td>
					<td style="text-align: right; width: 20%;"><label>N&uacute;mero
							Cheque:</label></td>
					<td style="text-align: left; width: 30%;"><input type="text" name="numeroCheque" id="numeroCheque"
						value="${pagoLiquidacion.numeroCheque}" maxlength="11"
						onkeypress="return isNumberKeyP(event);"></td>

				</tr>
				<tr>
					<td style="text-align: right; width: 20%;"><label>Ruc Proveedor:</label></td>
					<td colspan="3" style="text-align: left; width: 80%;"><input type="text" name="rucProveedor" id="rucProveedor"
						value="${pagoLiquidacion.rucProveedor}" maxlength="11"
						onkeypress="javascript:return Valida_Dato(event,8);" /> <label>Cheque
							Cobrado:</label> <input type="checkbox" name="chequeCobrado" 
						id="chequeCobrado" value="1"></td>
				</tr>
				<tr>
					<td style="text-align: right; width: 20%;"><label>Raz&oacute;n Social:</label></td>
					<td colspan="3"  style="text-align: left; width: 80%;"><input type="text" style="width: 100%"
						name="razonSocial" id="razonSocial"
						value="${pagoLiquidacion.razonSocial}"/></td>
				</tr>
				<tr>
					<td style="text-align: right; width: 20%;"><label>Sub Total:</label></td>
					<td colspan="3" style="text-align: left; width: 80%;"><input type="text" name="subTotal" maxlength="0"
						id="subTotal"
						onkeypress="return isNumberKeyP(event), isNumberKeyPBD(event)"
						onkeydown="if(event.keyCode == 13){getMontos();}" /></td>
				</tr>
				<tr>
					<td style="text-align: right; width: 20%;"><label id="igv">IGV </label><label id="renta">Renta 4ta. </label>
					<input
							type="text" name="igvMonto" style="width: 20px;"
							onchange="getMontos()" id="igvMonto"
							value="${pagoLiquidacion.tasaIgv}" maxlength="2"
							onkeypress="return isNumberKeyP(event);" /><label>%:</label></td>
					<td colspan="3" style="text-align: left; width: 80%;"><input type="text" name="montoIgv" maxlength="0"
						id="montoIgv" value="${pagoLiquidacion.montoIgv}"
						onkeypress="return isNumberKeyP(event), isNumberKeyPBD(event);" />
					</td>
				</tr>
				<tr>
					<td style="text-align: right; width: 20%;"><label>Monto Total:</label></td>
					<td colspan="3" style="text-align: left; width: 80%;"><input type="text" name="montoTotal" maxlength="500"
						id="montoTotal" onchange="getMontos()"
						onkeypress="return isNumberKeyP(event);"
						value="${pagoLiquidacion.montoTotal}"
						onkeydown="if(event.keyCode == 13){getMontos();}" /> <select
						name="tipoMoneda" id="tipoMoneda">
							<option value="0">
								<c:out value="-- Tipo Moneda --" />
							</option>
							<c:forEach items="${listTipoMoneda}" var="modali">
								<option value="${modali.tablaEspecificaID}"
									<c:if test="${pagoLiquidacion.fkIdtablaespTipoMoneda == modali.tablaEspecificaID}" > selected="selected" </c:if>>
									<c:out value="${modali.descripcionCabecera}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td style="text-align: right; width: 20%;"><label>Saldo Pagado:</label></td>
					<td colspan="3" style="text-align: left; width: 80%;"><input type="text" name="saldoPagado" maxlength="0"
						id="saldoPagado" value="0"
						onkeypress="return isNumberKeyPBD(event);" /></td>
				</tr>
				<tr>
					<td style="text-align: right; width: 20%;"><label>Imagen De
							Factura:</label></td>
					<td colspan="3"  style="text-align: left; width: 80%;"><!-- <iframe
							src="showArchivoUploadPagoLiquidacion.jspx" id="window"
							frameborder="0" height="25px;" width="100%" scrolling="no"></iframe> -->
							<div>
							<spring:bind path="imagenODocumento">
											<input type="file" name="imagenODocumento" id="imagenODocumento" />
										</spring:bind>
										</div>
										<input type="hidden" id="nombreDocumentoAdjunto" name="nombreDocumentoAdjunto">
										<input type="hidden" id="nombreArchivo" name="nombreArchivo">
										<input type="hidden" id="extension" name="extension">
					</td>
				</tr>
				<tr id="muestraImagenModifica">
					<td  style="text-align: right; width: 20%;"><label>Factura Escaneada:</label></td>
					<td colspan="3"  style="text-align: left; width: 80%;"><div id="archivoDescargable"></div></td>
				</tr>
				<tr>
					<td colspan="4" style="text-align: right; width: 100%;"><input
						type="button" name="Agregar" value="Grabar Documento"
						onclick="javascript:fRegistrar();"> <input type="button"
						name="Limpiar" value="Limpiar" onclick="javascript:limpiarCajas();"> <input type="button"
						value="Cerrar" id="idBtnCerrar" /></td>
				</tr>
			</table>
			<div style="width: 100%; text-align: left;" class="hide"><label style="color: red; font-size: 13px;">* Para eliminar o modificar una factura debe eliminar primero su detalle.</label></div>
			<br/>
			<table class="table-clasico" style="width: 100%">
				<caption>Lista de Facturas</caption>
				<thead>
					<tr>
						<td style="width: 5%; text-align: center;"><label>Img<br/>Doc</label>
						</td>
						<td style="width: 25%; text-align: center;"><label>Raz&oacute;n Social</label>
						</td>
						<td style="width: 15%; text-align: center;"><label>Ruc</label>
						</td>
						<td style="width: 10%; text-align: center;"><label>Monto Total</label>
						</td>
						<td style="width: 10%; text-align: center;"><label>Igv</label>
						</td>
						<td style="width: 10%; text-align: center;"><label>Saldo Disponible</label>
						</td>
						<td style="width: 10%; text-align: center;"><label>Saldo Pagado</label>
						</td>
						<td style="width: 5%; text-align: center;"><label>DetallePago</label>
						</td>
						<td style="width: 5%; text-align: center;" class="hide"><label>Operaciones</label>
						</td>
					</tr>
				</thead>
				<c:forEach var="ingresoPagoG" items="${listPagoLiquidacion}"
					varStatus="indice">
					<c:choose>
						<c:when test="${indice.count %2== 0}">
							<c:set var="classIdi" value="f2"></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="classIdi" value="f1"></c:set>
						</c:otherwise>
					</c:choose>
					<tr class="<c:out value="${classIdi}"></c:out>">
						<td style="width: 5%; text-align: center;"><a
							href="javascript:expandcollapse('divPl<c:out value="${ingresoPagoG.pagoLiquidacionID }"></c:out>', 'one');">
						<img id='imgdivPl<c:out value="${ingresoPagoG.pagoLiquidacionID }"></c:out>'
						border="0" src="<c:url value="/images/Plus001.gif" />"
						width="15px" /> </a></td>
						<td align="left"><c:out value="${ingresoPagoG.razonSocial}"></c:out>
						</td>
						<td align="left"><c:out value="${ingresoPagoG.rucProveedor}"></c:out>
						</td>
						<td align="left"><c:out value="${ingresoPagoG.montoTotal}"></c:out>
							&nbsp; <c:forEach items="${listTipoMoneda}" var="modali1">
								<c:if
									test="${modali1.tablaEspecificaID== ingresoPagoG.fkIdtablaespTipoMoneda}">
									<c:out value="${modali1.descripcionCabecera}" />
								</c:if>
							</c:forEach></td>
						<td align="center"><c:out value="${ingresoPagoG.montoIgv}"></c:out>
						</td>
						<td align="center"><c:out
								value="${ingresoPagoG.saldoDisponible}"></c:out> <c:forEach
								items="${listTipoMoneda}" var="modali1">
								<c:if
									test="${modali1.tablaEspecificaID== ingresoPagoG.fkIdtablaespTipoMoneda}">
									<c:out value="${modali1.descripcionCabecera}" />
								</c:if>
							</c:forEach></td>
						<td align="center"><c:out value="${ingresoPagoG.saldoPagado}"></c:out>
							&nbsp; <c:forEach items="${listTipoMoneda}" var="modali2">
								<c:if
									test="${modali2.tablaEspecificaID== ingresoPagoG.fkIdtablaespTipoMoneda}">
									<c:out value="${modali2.descripcionCabecera}" />
								</c:if>
							</c:forEach></td>
						<td><a
							href="javascript:fDetallePago('showDetallePago.jspx?pagoLiquidacionID=${ingresoPagoG.pagoLiquidacionID}&liquidacionGastoID=${liquidacionGastoID }');"
							class="linkSelecciona" style="cursor: pointer" title="Modificar">Detalle
								Pago</a></td>
						<td  class="hide" style="text-align: center;">
						<c:choose>
							<c:when test="${ingresoPagoG.cantidadDetallePagoLiquidacion==0 }">
						 <a href="javascript:fModificar('${ingresoPagoG.pagoLiquidacionID}','${ingresoPagoG.fechaEmisionString}',
						 								'${ingresoPagoG.desembolsoID}','${ingresoPagoG.numeroDocumento}',
						 								'${ingresoPagoG.fkIdtablaespTipoComprobantePago}','${ingresoPagoG.numeroCheque}',
						 								'${ingresoPagoG.rucProveedor}','${ingresoPagoG.chequeCobrado}',
						 								'${ingresoPagoG.razonSocial}','${ingresoPagoG.tasaIgv}',
						 								'${ingresoPagoG.montoIgv}','${ingresoPagoG.montoTotal}',
						 								'${ingresoPagoG.fkIdtablaespTipoMoneda}','${ingresoPagoG.saldoPagado}')"  style="cursor:pointer" class="linkSelecciona" title="Modificar">Modificar</a><br/>	
                         <a href="javascript:fEliminar('${ingresoPagoG.pagoLiquidacionID}')"  style="cursor:pointer" class="linkSelecciona" title="Eliminar">Eliminar</a>
                         </c:when>
                         <c:otherwise>
                         <label>---<br/>---</label>
                         </c:otherwise>
						</c:choose>
						</td>		
					</tr>
						<tr class="<c:out value="${classIdi}"></c:out>">
				<td colspan="100%">
					<div
						id='divPl<c:out value="${ingresoPagoG.pagoLiquidacionID }"></c:out>'
						style="display: none; position: relative; overflow: auto; padding-left: 15px; padding-bottom: 15px; width: 97%;">
							<label>Fecha de Emision: <c:out value="${ingresoPagoG.fechaEmisionString }"></c:out> </label><br/>
							<label>Desembolso: <c:out value="${ingresoPagoG.descripcionDesembolso }"></c:out> </label>
							<br/>
							<iframe src="showImagenArchivoDownloadPagoLiquidacion.jspx?pagoLiquidacionId=${ingresoPagoG.pagoLiquidacionID }"
											id="window" frameborder="0" height="40px" width="100%"
											scrolling="no"></iframe>
					</div>
					</td>
					</tr>
				</c:forEach>
			</table>
		</fieldset>
	</form:form>
</div>
</body>
</html>