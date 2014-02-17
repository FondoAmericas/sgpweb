<%@include file="/common/includesTaglibsGenerico.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

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
function openWindow(url){
	window.open(url,'','width=900,height=850,left=0,top=50,screenX=0,screenY=50');
}

function fRegistrar(){
	var valida = validaSeleccion();
	if(valida==0){
			var validaArchivo = validarExtensionArchivo();
			if(validaArchivo==true){
		   	 var form = document.formShowCargaFormulario ;
			    form.action = "grabarFormularioLiquidacion.jspx";
			    form.submit();
			}
		}
}
</script>

<script type="text/javascript">
function validaSeleccion(){
	var errores = 0;
	var mensaje = "";

	if ($("#formularioId").val() == 0) {
			mensaje = mensaje + "Debe seleccionar un tipo de formulario a guardar.";
			errores = errores + 1;
	}
		
	if (errores > 0) {
		alert(mensaje);
	}

	return errores;
}

function validarExtensionArchivo(){
	   extensiones_permitidas = new Array("doc","docx","pdf","DOC","DOCX","PDF","jpg","jpeg","JPG","JPEG");
	   ruta_archivo=$("#formulario").val();
	   if(!ruta_archivo){
	      alert("No se ha seleccionado ningun archivo!");
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

<form:form  commandName="cargaFormularioBean" id="formShowCargaFormulario" 
				name="formShowCargaFormulario" action="#"
				method="POST"  enctype="multipart/form-data" >
<div class="form-clasico">
	<div class="encabezado">CARGAR FORMULARIOS LLENOS</div>

<br class="hide"/>	
	<fieldset class="hide">
		<legend>Seleccionar Formulario</legend>
		<table width="100%">
			<tr>
				<td style="width: 20%; text-align: right;"><label>Seleccionar Archivo:</label></td>
				<td style="width: 30%; text-align: center;"><div>
					<input type="hidden" id="liquidacionGastoID" name="liquidacionGastoID" value="${liquidacionGastoID }">
							<spring:bind path="formulario">
											<input type="file" name="formulario" id="formulario" style="width: 100%;"/>
										</spring:bind>
										</div>
										<input type="hidden" id="nombreDocumentoAdjunto" name="nombreDocumentoAdjunto">
										<input type="hidden" id="nombreArchivo" name="nombreArchivo">
										<input type="hidden" id="extension" name="extension"></td>
				<td style="width: 25%; text-align: left;">
				<select name="formularioId" id="formularioId" style="width: 100%;">
	<option value="0"><c:out value="-- Formulario --" /></option>
			<option value="4" >Formulario 4</option>
			<option value="5" >Formulario 5</option>

</select> </td>
<td style="width: 25%; text-align: left;"><input	type="button" name="Agregar" value="Grabar Formulario" onclick="javascript:fRegistrar();" ></td>
			</tr>
		</table>
		
		
	</fieldset>
<br/>	
	<fieldset>
		<legend>Formulario 2</legend>
		<label ><a href="javascript:openWindow('reportFormato2.jspx?liquidacionGastoID=${liquidacionGastoID }')">LIQUIDACION DE GASTOS DE FONDOS RECIBIDOS - Formato 2</a></label>
	</fieldset>	
<br/>	
	<fieldset>
		<legend>Formulario 4</legend>
		<iframe src="showFormularioPorNumero.jspx?liquidacionGastoID=${liquidacionGastoID }&numeroFormulario=4"
											id="window" frameborder="0" height="40px" width="100%"
											scrolling="no"></iframe>
	</fieldset>	
	
	<br/>	
	<fieldset>
		<legend>Formulario 5</legend>
		<iframe src="showFormularioPorNumero.jspx?liquidacionGastoID=${liquidacionGastoID }&numeroFormulario=5"
											id="window" frameborder="0" height="40px" width="100%"
											scrolling="no"></iframe>
	</fieldset>	
</div>	
</form:form>