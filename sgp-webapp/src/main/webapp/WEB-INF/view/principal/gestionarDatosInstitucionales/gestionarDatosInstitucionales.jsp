<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
 <%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/displaytagex.css" type="text/css" />
<script type="text/javascript">
$(document).ready(function(){

		var a=$('#mensaje').attr('value');
		 if (a !='no') {
			document.getElementById("mensaje").innerHTML =a;
			alert(a);
			/*$(function() {
				$('#divMensaje').dialog('open');
				return false;
			});*/	
		}
	});

function grabaDatosInstitucionales(){
	var valida=validarDatoInstitucional();
	if(valida==0){
		$.get("actionModificarInstitucion.jspx", {
			telefono : $('#telefono').val(),
			contacto : $('#contacto').val(),
			correo : $('#correo').val(),
			representanteLegal : $('#representanteLegal').val(),
			direccion : $('#direccion').val(),
			observacionD : $("#observacionD").val()
		},function(){
			alert("Se modifico exitosamente!");
		});
	}
}

function validarDatoInstitucional(){
	  var mensaje = "";
	  var errores=0;
	  if($('#telefono').val().length == 0) {
		 mensaje += "\n Ingresar telefono para contactar.";
		 errores+=1;
	  }
	  if($('#contacto').val().length == 0) {
			 mensaje += "\n Ingresar nombre de un contacto para el proyecto.";
			 errores+=1;
		  }
	  if($('#correo').val().length == 0) {
			 mensaje += "\n Ingresar un correo electronico para el proyecto.";
			 errores+=1;
		  }else{
			  var validaEmail=validacorreo($("#correo").val());
				if(validaEmail==false){
					 mensaje += "\n Formato de correo incorrecto";
					errores+=1;
				}	  
		  }  
	  if($('#representanteLegal').val().length == 0) {
			 mensaje += "\n Ingresar un representante legal.";
			 errores+=1;
		  }  
	  if($('#direccion').val().length == 0) {
			 mensaje += "\n Ingresar una direccion para el proyecto.";
			 errores+=1;
		  }  
	  
	  	
	  if(errores>0){
	     alert(mensaje);
	     }
	  return errores;
}

</script>
<div class="form-clasico" ><form:form name="formGestionarDatosInstitucionales"
	action="#" method="POST">
	
	<div class="encabezado">GESTIONAR DATOS INSTITUCIONALES</div>
<br>
	<!-- <div id="divMensaje"></div>-->
 <input type="hidden" id="mensaje" value="${mensaje}" />
<fieldset><legend>DATOS INSTITUCI&Oacute;N</legend>
	<table width="100%">
		<tr>
			<td style="text-align: right; width: 25%;">
			<label>Nombre Instituci&oacute;n:</label></td>
			<td colspan="3" style="text-align: left; width: 75%;" ><label id="nombreInstitucion"><c:out value="${nombreInstitucion}"></c:out></label></td>
			
		</tr>
		<tr>
		<td style="text-align: right; width: 25%"><label>RUC:</label></td>
			<td style="text-align: left; width: 25%;"><label id="rucInstitucion"><c:out value="${rucInstitucion}"></c:out> </label></td>
			<td style="text-align: right; width: 25%"><label>Contacto:</label></td>
			<td style="text-align: left; width: 25%;"><input id="contacto" name="contacto" value="${contacto}" type="text" /></td>
		</tr>
		<tr>
			<td style="text-align: right; width: 25%"><label>Tel&eacute;fono:</label></td>
			<td style="text-align: left; width: 25%;"><input id="telefono" name="telefono"  value="${telefono}" type="text" /></td>
			<td style="text-align: right; width: 25%"><label>Correo:</label></td>
			<td colspan="3" style="text-align: left; width: 75%;"><input id="correo" name="correo" value="${correo}" type="text" onkeypress="javascript:return Valida_Dato(event,7);" /></td>
		</tr>
		<tr>
			<td style="text-align: right; width: 25%"><label>Representante Legal:</label></td>
			<td colspan="3" style="text-align: left; width: 75%;">
				<input id="representanteLegal" name="representanteLegal" style="width: 94%;" value="${representanteLegal}" type="text" /></td>
			
		</tr>
		<tr>
		<td style="text-align: right; width: 25%; vertical-align: top;"><label>Direcci&oacute;n:</label></td>
			<td colspan="3" style="text-align: left; width: 75%;">
			<textarea rows="3" cols="23" id="direccion" name="direccion" style="width:94%;"><c:out value="${direccion}"></c:out></textarea>
			</td>
			
		</tr>
		<tr>
			<td style="text-align: right; width: 25%; vertical-align: top;"><label>Observaciones:</label></td>
			<td  colspan="3"  style="text-align: left; width: 75%;">
			<textarea rows="3" cols="23" id="observacionD" name="observacionDeInstitucion" style="width:94%;"><c:out value="${observacionDeInstitucion}"></c:out></textarea>
			</td>		
		</tr>
		<tr>
		<td colspan="3" style="width: 75%;"></td>
		<td  style="text-align: center; width: 25%;"><input type="button" value="Modificar" onclick="grabaDatosInstitucionales()"/></td>
		</tr>
		
	</table>
	<br />
	</fieldset>
</form:form></div>