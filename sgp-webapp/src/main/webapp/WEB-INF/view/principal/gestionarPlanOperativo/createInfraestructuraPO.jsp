<%@include file="includesTaglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="web.app.title"></spring:message>
</title>
	<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/estiloContabilidad.css"></c:url>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/estiloGeneral.css"></c:url>" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/validador.js"></script>	
	
	

<script>
	
	$(document).ready(function() {
		 if($('#mensaje').attr("value") != '' && $('#mensaje').attr("value") != undefined){
		        alert($('#mensaje').attr("value"));
		     }
		cargarGrillaRecursosEjecutor();
		});
	
		</script>
			
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
			$(".disabled").attr("disabled","disabled");	
		}
		
	}
	
	function cargarGrillaRecursosEjecutor(){
		 $("#grillaRecursoEjecutor").load("cargaGrillaRecursoEjecutor.jspx",{
			 datoPlanOperativoID : $("#datoPlanOperativoID").val(),
			 estadoPlanOperativo : "<c:out value="${estadoPlanOperativo}"></c:out>"
		 });
	}
	</script>
	
		<script type="text/javascript">

		function validarInfraestructura(){
				var errores = 0;
				var mensaje = "";

				if ($("#descripcion").val().length  == 0) {
					mensaje += "Debe ingresar una descripcion. \n";
					errores = errores + 1;
				}
				
				if ($("#ubicacion").val().length == 0) {
					mensaje += "Debe ingresar una ubicacion. \n";
					errores = errores + 1;
				}
				if ($("#categoriaActivo").val() == 0) {
					mensaje += "Debe seleccionar una categoria de activo. \n";
					errores = errores + 1;
				}
				
				if ($("#activo").val() == 0) {
					mensaje += "Debe seleccionar un activo. \n";
					errores = errores + 1;
				}
				
				if (errores > 0) {
					alert(mensaje);
				}

				return errores;
			
		}

				</script>

		<script type="text/javascript">
		function grabarInfraestructura(){
			var validacion = validarInfraestructura();
			if (validacion==0){
				$.get("grabarInfraestructura.jspx", {
					infraestructuraPOID : $("#infraestructuraPOID").val(),
					descripcion : $("#descripcion").val(),
					ubicacion : $("#ubicacion").val(),
					activo : $("#activo").val(),
					datoPlanOperativoID : $("#datoPlanOperativoID").val()
				}, function(){
					//window.close();
					cargarGrillaRecursosEjecutor();
					limpiarCajas();
					llenaActivo(0);
				});
			}
		}
		
		function eliminarRecursoEjecutor(infraestructuraPOID ){
			var confirma= confirm("Esta seguro que desea eliminar el registro?");
			if(confirma==true){
				$.get("eliminarRecursoEjecutor.jspx", {
					infraestructuraPOID :infraestructuraPOID
				}, function() {
					cargarGrillaRecursosEjecutor();
				});
			}

		}
		
		function modificarRecursoEjecutor(infraestructuraPOID,descripcion ,ubicacion ,activoID ,fkIdtablaespCategoriaActivo ){
			var confirma = confirm("Esta seguro que desea cargar el registro para modificarlo?");
			
			if(confirma==true){
			$("#descripcion").attr("value",descripcion);
			$("#ubicacion").attr("value",ubicacion);
			$("#categoriaActivo").attr("value",fkIdtablaespCategoriaActivo);
			$("#infraestructuraPOID").attr("value",infraestructuraPOID);
			llenaActivo(activoID);
			}
		}
		
		
		function llenaActivo(activoID){
			  var categoriaActivoId = $("#categoriaActivo").val();
			    var nomMetodo = "cargarBienActivo";
			    $("#activo").load("cargarCombo.jspx", {categoriaActivoId:categoriaActivoId,metodo:nomMetodo}, function(){
			    	if(activoID!=0){
			    	$("#activo").attr("value",activoID);
			    	}
			    });
		
		}
		
		function limpiarCajas(){
			$("#descripcion").val("");
			$("#ubicacion").val("");
			$("#categoriaActivo").val("");
			$("#infraestructuraPOID").val("");
	
		}
		
		function cerrar(){
			window.close();
		}
		</script>
</head>
<body>
	<div class="form-clasico">
		<div class="encabezado">RECURSOS DEL EJECUTOR</div>
		<br />
		<form:form name="formResultado" method="post" action="" target="_self">
			<input type="hidden" id="datoPlanOperativoID" name="datoPlanOperativoID"
				value="${datoPlanOperativoID }">
			<input type="hidden" id="infraestructuraPOID" name="infraestructuraPOID">	
			<input type="hidden" id="mensaje" name="mensaje" value="${mensaje}">
			<fieldset>
				<legend>Listado de Recursos</legend>
				<table width="100%">
					<tr>
						<td style="text-align: right; width: 15%;"><label>Descripción:</label></td>
						<td colspan="3" style="text-align: left; width: 85%;"><textarea id="descripcion" class="disabled"
								name="descripcion" COLS="55" ROWS="3"
								style="width: 99%;"><c:out value="${infraestructuraPo.descripcion }"></c:out></textarea></td>
					</tr>
					<tr>
						<td style="text-align: right; width: 15%;"><label>Ubicación:</label></td>
						<td colspan="3" style="text-align: left; width: 85%;"><textarea id="ubicacion" class="disabled"
								name="ubicacion" COLS="55" ROWS="3"
								style="width: 99%;"><c:out value="${infraestructuraPo.ubicacion }"></c:out></textarea></td>
					</tr>
					<tr>
				<td style="text-align: right; width: 15%;"><label>Categoria Activo:</label></td>
					<td style="text-align: left; width: 35%;"><select name="categoriaActivo" id="categoriaActivo"  class="disabled" onchange="llenaActivo(0)" style="width: 100%;">
							<option value="0">
								<c:out value="-- Categoria Activo --" />
							</option>
							<c:forEach items="${listCategoriaActivo}" var="categoriaActivo">
								<option value="${categoriaActivo.tablaEspecificaID}"
									<c:if test="${ infraestructuraPo.activo.fkIdtablaespCategoriaActivo == categoriaActivo.tablaEspecificaID}"> selected="selected" </c:if>>
									<c:out value="${categoriaActivo.descripcionCabecera}" />
								</option>
							</c:forEach>
					</select></td>
					<td style="text-align: right; width: 15%;"><label>Bien / Activo:</label></td>
					<td style="text-align: right; width: 35%;"><select name="activo" id="activo"  class="disabled" style="width: 100%;">
							<option value="0">
								<c:out value="-- Bien/Activo --" />
								<c:forEach items="${listActivo}" var="lActivo">
										<option <c:if test="${lActivo.activoID == infraestructuraPo.activo.activoID }">selected="selected" </c:if>
											value="${lActivo.activoID}">
											<c:out value="${lActivo.descripcionActivo }" />
										</option>
									
							</c:forEach>
							</option>
					</select></td>
				</tr>					
				</table>
				<br/>
				<div style="width: 100%; text-align: right;" class="hide"><input type="button"
									name="btnGrabar" id="btnGrabar"
									value="Grabar Recurso"
									onclick="grabarInfraestructura()" /> <input type="button"
									name="btnCerrar" id="btnCerrar"
									value="Cerrar"
									onclick="cerrar()" /></div>
				<br>
				<div id="grillaRecursoEjecutor"></div>
			</fieldset>
		</form:form>
	</div>
</body>
</html>