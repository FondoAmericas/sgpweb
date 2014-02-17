<%@include file="/common/includesTaglibsGenerico.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<script type="text/javascript">
function fRegistrar(){
			var validaArchivo = validarExtensionArchivo();
			if(validaArchivo==true){
		   	 var form = document.formShowCargaFormulario ;
			    form.action = "grabarFormulario.jspx";
			    form.submit();
		}
}
</script>

<script type="text/javascript">
function validarExtensionArchivo(){
	   extensiones_permitidas = new Array("doc","docx","pdf","DOC","DOCX","PDF","jpg","jpeg","JPG","JPEG");
	   ruta_archivo=$("#formulario").val();
	   if(!ruta_archivo){
	      alert("No se ha seleccionado ningun archivo(!");
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

<div class="form-clasico">
<form:form  commandName="cargaFormularioBean" id="formShowCargaFormulario" 
				name="formShowCargaFormulario" action="#"
				method="POST"  enctype="multipart/form-data" >

<table>
<tr>
					<td style="text-align: right; width: 20%;"><label>Cargar Formulario:</label></td>
					<td colspan="3"  style="text-align: left; width: 80%;">
							<div>
							<spring:bind path="formulario">
											<input type="file" name="formulario" id="formulario" />
										</spring:bind>
										</div>
										<input type="hidden" id="nombreDocumentoAdjunto" name="nombreDocumentoAdjunto">
										<input type="hidden" id="nombreArchivo" name="nombreArchivo">
										<input type="hidden" id="extension" name="extension">
					</td>
				</tr>
				<tr>
					<td colspan="4" style="text-align: right; width: 100%;"><input
						type="button" name="Agregar" value="Cargar Formulario" onclick="javascript:fRegistrar();"> <input type="button"
						name="Limpiar" value="Limpiar" onclick="javascript:limpiarCajas();"></td>
				</tr>
</table>
</form:form>
</div>