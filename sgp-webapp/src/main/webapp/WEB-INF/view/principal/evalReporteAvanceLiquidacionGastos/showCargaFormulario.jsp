<%@include file="/common/includesTaglibsGenerico.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<script type="text/javascript">
function openWindow(url){
	window.open(url,'','width=900,height=850,left=0,top=50,screenX=0,screenY=50');
}
</script>

<form:form  commandName="cargaFormularioBean" id="formShowCargaFormulario" 
				name="formShowCargaFormulario" action="#"
				method="POST"  enctype="multipart/form-data" >
<div class="form-clasico">
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