<%@include file="/common/includesTaglibsGenerico.jsp"%>

<script type="text/javascript">

$(document).ready(function() {
	$("#muestraDocDescarga").hide();
	
	if($('#mensaje').attr("value") != ''&& $('#mensaje').attr("value") != undefined){
        alert($('#mensaje').attr("value"));
     }
	
	var estadoPrefijoPlanOperativo= "<c:out value="${estadoPrefijoPlanOperativo }"></c:out>";
	if(estadoPrefijoPlanOperativo!='apro'){
		$("#tablaCuerpo").hide("fast");
		$("#divMensajeCuerpo").html('<label style=\"font-size: 13px; color: red;\">El PLAN OPERATIVO NO ESTA APROBADO, es por ello que no puede ingresar nuevas liquidaciones.</label>');
	}
});


var arrayReporteAvance = new Array();
var arrayFuenteFinanciadora = new Array();
$(window).load(function () {
	$('#divGrillaLiquidacionGasto').load(
			"showGrillaLiquidacionGastoByFuenteFinanciadora.jspx?fuenteFinanciadoraID="+$('#fuenteFinanciadora').attr("value"));	
}); 

function getMontoDisponible(){
alert("ingrese MontoDip");
}

function crearCodigoVersion(){
	var periodo;
	var numLiq;
	
	for ( var p = 0; p < arrayReporteAvance.length; p++) {
		if (arrayReporteAvance[p].reporteAvanceID == $('#reporteAvance').attr("value")) {
			numLiq = parseInt(arrayReporteAvance[p].totalLiquidaciones) + 1;	
			periodo = arrayReporteAvance[p].periodo;
			break;
		}
	}
	  if($('#fuenteFinanciadora').attr("value") != 0 && $('#reporteAvance').attr("value") != 0 ){
	     	$('#codVersion').attr("value",periodo+"."+ $('#fuenteFinanciadora').val()+"."+numLiq); 
	 	}else if($('#reporteAvance').attr("value") == '0' ){
	     	 alert("Seleccionar un Reporte de Avance.");
	     	$('#reporteAvance').focus();
	 	}else  if($('#fuenteFinanciadora').attr("value") == '0' ){
	 		 alert("Seleccionar una Fuente Financiadora.");
	 		$('#fuenteFinanciadora').focus();
	 	}
	
}

function registrarLiquidacionGasto(){
	if (validar()) {
		return;
	}
	var form = document.formShowLiquidacionGasto;
	form.action = "actionRegistrarLiquidacionGasto.jspx";
	form.submit();
	
}

function mostarMontoEnMontoDisponible(){
	$('#divGrillaLiquidacionGasto').load(
			"showGrillaLiquidacionGastoByFuenteFinanciadora.jspx?fuenteFinanciadoraID="+$('#fuenteFinanciadora').attr("value"));	

	var saldoDisponible=0;
	for ( var p = 0; p < arrayFuenteFinanciadora.length; p++) {
		if (arrayFuenteFinanciadora[p].fuenteFinanciadoraID == $('#fuenteFinanciadora').attr("value")) {
			saldoDisponible = arrayFuenteFinanciadora[p].saldoDisponible;
			$('#saldoDisponible').attr("value",saldoDisponible);
			break;
		}
	}
	
			  
}
function fEliminar(id){
	if (confirm("Desea eliminar la liquidacion de gasto?")) {
		var form = document.formShowLiquidacionGasto;
	    form.action = "actionEliminarLiquidacionGasto.jspx?liquidacionGastoID="+id;
	    form.submit();
	   
	}
	
}

function validar(){
	  //var cabecera = "Debe ingresar la siguiente informaci\u00F3n obligatoria(*):";
	  var mensaje = "";
	  if($('#reporteAvance').attr("value") == 0){
		 mensaje += "\n Seleccionar un Reporte de Avance.";
	  }
	  if($('#estado').attr("value") == 0){
		 mensaje += "\n Seleccionar un estado.";
	  }
      if($('#fuenteFinanciadora').attr("value") == 0){
     	 mensaje += "\n Seleccionar una Fuente Financiadora.";
 	  }
	  if($('#codVersion').attr("value") == ''){
		 mensaje += "\n Codigo.";
	  }
	 
	  if($('#fechaInicio').attr("value") == ''){
			 mensaje += "\n Ingresar fecha Inicio.";
		  }
	  if($('#fechaFin').attr("value") == ''){
			 mensaje += "\n Ingresar Fecha Fin.";
		  }
	  if($('#observacion').attr("value") == ''){
		 mensaje += "\n Ingresar Observaci\u00F3n.";
	  }

	  if (($("#fechaInicio").val().length > 0)&&($("#fechaFin").val().length > 0)){
			var fechaInicio = new Date();
			var fechaFin = new Date();

			var fi = $("#fechaInicio").val();
			fechaInicio.setFullYear(fi.substring(6, 10), fi.substring(3, 5), fi
					.substring(0, 2));

			var ff = $("#fechaFin").val();
			fechaFin.setFullYear(ff.substring(6, 10), ff.substring(3, 5), ff
					.substring(0, 2));

			//alert((fi.substring(6,10)+" / "+  fi.substring(3, 5)+" / "+ fi.substring(0, 2)));
			//alert((ff.substring(6,10)+" / "+  ff.substring(3, 5)+" / "+ ff.substring(0, 2)));

			if (fechaInicio > fechaFin) {
				mensaje += "La fecha de inicio no puede ser mayor a la fecha de fin.";
				//errores = errores + 1;
			}
		}

	  if(mensaje != ""){
	     alert(mensaje);
	     return true;
	  }else{
		 return false;
	  }
	
}
function goTo(url) {
	fOpenModalDialog(url,'900','500','40','40');
}
function calcularFechasMayorMenor() {

    if ($("#fechaFin").val().length > 0) {
            var fechaInicio = new Date();
            var fechaFin = new Date();

            var fi = $("#fechaInicio").val();
            fechaInicio.setFullYear(fi.substring(6, 10), fi.substring(3, 5), fi
                            .substring(0, 2));

            var ff = $("#fechaFin").val();
            fechaFin.setFullYear(ff.substring(6, 10), ff.substring(3, 5), ff
                            .substring(0, 2));

            
            if (fechaInicio > fechaFin) {
                    alert("La fecha de inicio no puede ser mayor a la fecha de fin.");
                    $("#fechaFin").attr("value", "");
                    $("#fechaFin").focus();
            }
    }
}

$(function() {
	
	$("#fechaInicio").datepicker({
		changeMonth : true,
		changeYear : true
	});
	$("#fechaFin").datepicker({
		changeMonth : true,
		changeYear : true
	});

});

</script>

<script type="text/javascript">
function muestraDescargas(){
	$("#muestraDocDescarga").toggle(10); //mostrar y/o ocultar un div
}
</script>

<div class="form-clasico" >

<div class="encabezado">LIQUIDACI&Oacute;N DE GASTOS</div>
<form:form  name="formShowLiquidacionGasto"	action="actionShowLiquidacionGasto.jspx" method="POST" >
<br/>
<input type="hidden" id="mensaje" name="mensaje" value="${mensaje}">
<fieldset style="padding-left: 15px"><legend>Liquidaciones</legend>
<div id="divMensajeCuerpo"></div>
<table width="100%" id="tablaCuerpo" >
<tr>
<td style="width:50%; text-align:right;" colspan="2">
	<label>Seleccione Reporte de Avance Asociado:</label>
</td>
<td  style="width:50%; text-align:left;" colspan="2">
	<select name="reporteAvance"  id="reporteAvance" onchange="crearCodigoVersion()">
		<option value="0"><c:out value="-- Seleccionar --" /></option>
			<c:forEach items="${listReporteAvance}" var="reporteAvance1">
			<option value="${reporteAvance1.reporteAvanceID}">
				<c:out value="Reporte Avance ${reporteAvance1.periodo}" />
			</option>	
			<script type="text/javascript">
												var objReporteAvance = new Object();
												objReporteAvance.reporteAvanceID = "<c:out value="${reporteAvance1.reporteAvanceID }"></c:out>";
												objReporteAvance.periodo = "<c:out value="${reporteAvance1.periodo}"></c:out>";
												objReporteAvance.totalLiquidaciones = "<c:out value="${reporteAvance1.totalLiquidaciones }"></c:out>";
												
												
												arrayReporteAvance
														.push(objReporteAvance);
												</script>		
			</c:forEach>
	</select></td>
</tr>
<tr>
<td  style="width:20%; text-align:right;" >
<label>Estado:</label>
</td>
<td  style="width:30%; text-align:left;" ><select name="sltEstadoLiquidacionGasto" id="sltEstadoLiquidacionGasto"
			disabled="disabled">
				<option value="0">
					<c:out value="-- Estado --" />
				</option>
				<c:forEach items="${listDetalleEstadoCabecera}"
					var="estadoLiquidacionGasto">
							<option <c:if test="${estadoLiquidacionGasto.detalleEstadoCabeceraID=='51' }">selected="selected"</c:if>
								value="${estadoLiquidacionGasto.detalleEstadoCabeceraID}">
								<c:out value="${estadoLiquidacionGasto.descripEstado }" />
							</option>
				</c:forEach>
		</select>
</td>
<td  style="width:20%; text-align:right;" >
<label>Fuente Financiadora:</label>
</td>
<td style="width:30%; text-align:left;" >
<select name="fuenteFinanciadora" id="fuenteFinanciadora" onchange="mostarMontoEnMontoDisponible(),crearCodigoVersion();" style="width: 100%;">
	<option value="0"><c:out value="-- Seleccionar --" /></option>
	<c:forEach items="${listFuenteFinanciadora}" var="fuente">
			<option value="${fuente.fuenteFinanciadoraID}" >
			<c:out	value="${fuente.institucion.nombreInstitucion}" /></option>
			<script type="text/javascript">
												var objFuenteFinanciadora = new Object();
												objFuenteFinanciadora.fuenteFinanciadoraID = "<c:out value="${fuente.fuenteFinanciadoraID}"></c:out>";
												objFuenteFinanciadora.saldoDisponible = "<c:out value="${fuente.montoFinanciado}"></c:out>";
												arrayFuenteFinanciadora.push(objFuenteFinanciadora);
												</script>		
			</c:forEach>

</select>
</td>
</tr>
<tr>
<td  style="width:20%; text-align:right;" >
<label>C&oacute;digo:</label>
</td>
<td  style="width:80%; text-align:left;" colspan="3" >
<input type="text" name="codVersion"	id="codVersion" value="${codVersion}"  maxlength="0" onkeypress="return isNumberKeyP(event);" >
</td>

</tr>
<tr>
<td style="width:20%; text-align:right;" >
<label style="text-align: right;">Fecha Inicio:</label>
</td>
<td style="width:30%; text-align:left;" >
<input type="text" name="fechaInicio" maxlength="0" id="fechaInicio" onchange="javascript:calcularFechasMayorMenor();" value="${fechaInicio}" onkeypress="javascript:return Valida_Dato(event,7), isNumberKeyPBD(event);;" />
<label>(dd/mm/aaaa)</label>
</td>
<td  style="width:20%; text-align:right;" >
<label >Fecha Fin:</label>
</td>
<td style="width:30%; text-align:left;" >
<input type="text" name="fechaFin" maxlength="0" id="fechaFin" value="${fechaFin}" onkeypress="javascript:return Valida_Dato(event,7), isNumberKeyPBD(event);;" />
<label>(dd/mm/aaaa)</label>
</td>
</tr>
<tr>
<td  style="width:20%; text-align:right; vertical-align: top;" >
<label style="text-align: right;">Observaci&oacute;n:</label>
</td>
<td  style="width:80%; text-align:left;" colspan="3"><textarea  maxlength="250"  style="width: 98%"  id="observacion" name="observacion"><c:out value="${observacion}"/></textarea></td>
</tr>
<tr>
<td colspan="4" style="text-align: right; width: 100%;" >
<input type="button" name="Registrar" value="Registrar" onclick="javascript:registrarLiquidacionGasto();">
</td>
</tr>
</table>
<label id="descargaFormularios" class="linkSelecciona" onclick="muestraDescargas()" >Descarga Formularios</label>
<div id="muestraDocDescarga" style="width: 100%;">
	<c:forEach  items="${listCargaFormulario}"
					var="formulario">
					<br/>
							<iframe src="showFormulario.jspx?formularioId=${formulario.cargaFormularioID }"
											id="window" frameborder="0" height="40px" width="100%"
											scrolling="no"></iframe>
	
	</c:forEach>
</div>																												
<div id="divGrillaLiquidacionGasto"></div>
</fieldset>
</form:form>
</div>