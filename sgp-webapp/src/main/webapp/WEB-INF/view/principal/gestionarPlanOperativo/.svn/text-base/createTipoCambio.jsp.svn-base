<%@include file="includesTaglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><spring:message code="web.app.title"></spring:message></title>
	<script>
	
	$(document).ready(function() {
		 if($('#mensaje').attr("value") != '' && $('#mensaje').attr("value") != undefined){
		        alert($('#mensaje').attr("value"));
		     }
		});
	
		var RECARGA_TIPO_CAMBIO_DESDE_BASE_DATOS = "1";
		var RECARGA_TIPO_CAMBIO_DESDE_PANTALLA = "2";
	
		function soloNumero(e) {
		    if ([e.keyCode||e.which]==8 || [e.keyCode||e.which]==9 || [e.keyCode||e.which]==46) 
		        return true;
		    if ([e.keyCode||e.which] < 48 || [e.keyCode||e.which] > 57)
		        e.preventDefault? e.preventDefault() : e.returnValue = false;
		}
		function submitFormTipoCambio() {
	
			if (validar()) {
				return;
			}
			if(confirm("\u00BFEst\u00E1 seguro de guardar los datos ingresados?")){
			   document.formResultado.action = document.getElementById("action1").value;
			   document.formResultado.submit();			   
			   if(document.getElementById("tipoRefresh").value == RECARGA_TIPO_CAMBIO_DESDE_BASE_DATOS){
				  window.opener.fLocationInterval("500");
			   }else if(document.getElementById("tipoRefresh").value == RECARGA_TIPO_CAMBIO_DESDE_PANTALLA){
				   window.opener.location.replace(document.getElementById("action2").value);
			   }
			   window.close();
			}
		}
		
		function goTo(url) {
			window.location = url;
		}
		
		function goBack(url) {
			goTo(url);
		}
		
		function validar(){
		      var cabecera = "Debe ingresar la siguiente informaci\u00F3n obligatoria(*):";
		      var mensaje = "";
		      if($('#tipoCambio').attr("value") == ""){
		          mensaje += "\n - Tipo cambio.";
		      }
		      
			  if($('#tipoMonedaDeId').attr("value") == "0"){
			      mensaje += "\n - Tipo de moneda de.";
			  }
			  
			  if($('#tipoMonedaAId').attr("value") == "0"){
			      mensaje += "\n - tipo de moneda a.";
			  }	
			  
		      if(mensaje != ""){
		         alert(cabecera + mensaje);
		         return true;
		      }else{
		         return false;
		      }
		   
		}
		
	</script>
</head>
<body> 
<div class="form-clasico">
<form:form name="formResultado" method="post">
	<input type="hidden" id="datoPlanOperativoID" name="datoPlanOperativoID" value="${datoPlanOperativoID}">
	<input type="hidden" id="desembolsoID" name="desembolsoID" value="${desembolsoID}">
	<input type="hidden" id="action1" name="action1" value="${action1}">
	<input type="hidden" id="action2" name="action2" value="${action2}">
	<input type="hidden" id="tipoRefresh" name="tipoRefresh" value="${tipoRefresh}">
	<input type="hidden" id="tipoMonedaAId" name="tipoMonedaAId" value="${tipoMonedaID}">
	<input type="hidden" id="mensaje" name="mensaje" value="${mensaje}">
    <fieldset><legend>Crear Tipo de Cambio</legend>
    <br />
		<table border="0">
			<tr>
				<td style="text-align: right; width: 20%;"><label>Tipo de Cambio:</label></td>
				<td style="text-align: left; width: 50%;" colspan="2">
					<input type="text" id="tipoCambio" name="tipoCambio" size="11" maxlength="5" onkeypress="soloNumero(event)"  style="width: 65px;"/>
					<label> de 
				   <select id=tipoMonedaDeId name="tipoMonedaDeId" style="width: 100px;">
					  <option value="0">--Moneda--</option>
					  <c:forEach items="${listaTipoMoneda}" var="tipoMonedaDe">
					        <c:if test="${tipoMonedaDe.tablaEspecificaID != tipoMonedaID}">
 							  <option value="${tipoMonedaDe.tablaEspecificaID}">${tipoMonedaDe.descripcionCabecera}</option>
 							</c:if>  
					  </c:forEach>
				    </select>
				    a 
				    <select id="tipoMoneda" name="tipoMoneda" style="width: 100px;" disabled="disabled">
					  <option value="0">--Moneda--</option>
					  <c:forEach items="${listaTipoMoneda}" var="tipoMonedaA">  
 							<option value="${tipoMonedaA.tablaEspecificaID}" <c:if test="${tipoMonedaA.tablaEspecificaID == tipoMonedaID}"> selected="selected" </c:if> >${tipoMonedaA.descripcionCabecera}</option>  
					  </c:forEach>
				    </select>
				    </label>					
				</td>
			</tr>
			<tr>
				<td colspan='3'> 
					<br>
				</td>
			</tr>
			<tr>
				
				<td style="text-align: right; width: 20%;" colspan="3">
				<br />
                    <input type="button" value="Cerrar" id="idBtnCerrar"/> 
                    <input type="button" value="Grabar Tipo de Cambio" onClick="submitFormTipoCambio()">
                    <br />
                </td>
				
			</tr>
		</table>
    </fieldset>
</form:form>
</div>
</body>
</html>