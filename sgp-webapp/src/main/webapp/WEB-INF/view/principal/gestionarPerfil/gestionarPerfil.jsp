<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
	$(document).ready(function(){
		ocultaCampos();
		
	});
	
	function ocultaCampos(){
		var estado= "<c:out value="${estadoPerfil}"></c:out>" ;
		//alert(estado);
		if (estado =='apro'){
			$(".hide").hide();//attr("style","visibility: hidden;");	
			$(".label").attr("style","background-color: white");
			$(".label").attr("disabled","disabled");
			$(".label").attr("class","labelMuestra");
		}
		
	}
</script>
	
<script type="text/javascript">
var arrayResumen = new Array();

	$(document).ready(function() {

		var a = $('#mensaje').attr('value');
		if (a != 'no') {
			document.getElementById("divMensaje").innerHTML = a;
			/*$(function() {
				$('#divMensaje').dialog('open');
				return false;
			});*/
			alert(a);
		}
	});
	function fActualizarMontosPerfil() {
		var valida= validaDatos();
		if(valida==0){
		var form = document.formGestionarPerfil;
		form.action = "actionActualizarGestionarPerfil.jspx";
		form.submit();
		}
	}
	function fEliminarResumenProyecto(id) {
		var form = document.formGestionarPerfil;
		form.action = "actionEliminarResumenProyecto.jspx?idResumenProyecto="
				+ id;
		form.submit();
	}
	function fAgregarResumen() {
		if (validarAgregarResumen()) {
			return;
		}
		var form = document.formGestionarPerfil;
		form.action = "actionAgregarResumenProyecto.jspx";
		form.submit();
	}

	function validarAgregarResumen() {
		var mensaje = "";
		if ($('#idTipoResumen').attr("value") == 0) {

			mensaje += "\n Seleccionar un Tipo de Resumen.";
		}
		if ($('#definicionR').attr("value") == '') {
			mensaje += "\n Ingresar Definici\u00F3n de resumen.";
		}
		
		for ( var p = 0; p < arrayResumen.length; p++) {
			if (arrayResumen[p].resumenId == $('#idTipoResumen').attr("value")) {
				mensaje += "\n El tipo de resumen ya se encuentra en la lista.";
				break;
			}
		}

		if (mensaje != "") {
			alert(mensaje);
			return true;
		} else {
			return false;
		}
	}
	/*
	var objXHR78511;
	var objNomArchivo;
	function validarUpload() {
		objXHR78511 = new ObjetoAJAX();
		objXHR78511.enviar("validarUpload.jspx", "post", "validarUploadRpt",
				null);
	}
	function validarUploadRpt() {
		objNomArchivo = JSON.parse(objXHR78511.respuestaTexto());
		$("#nomFile").attr("value",objNomArchivo.fileName);
	}*/
</script>
<script type="text/javascript">
	function validaDatos(){
		var errores = 0;
		var mensaje = "";

		if ($("#fondam").val().length == 0) {
			mensaje += "Debe ingresar el monto financiado por Fondam. \n";
			errores = errores + 1;
		}
		
		if ($("#contrapartida").val().length == 0) {
			mensaje += "Debe ingresar el monto total financiado por la contrapartida. \n";
			errores = errores + 1;
		}
		
		if ($("#cofinanciador").val().length == 0) {
			mensaje += "Debe ingresar el monto total financiado por los cofinanciadores. \n";
			errores = errores + 1;
		}
		
		if ($("#duracionPerfil").val().length == 0) {
			mensaje += "Debe ingresar la duracion en meses del proyecto. \n";
			errores = errores + 1;
		}
		
		if (errores > 0) {
			alert(mensaje);
		}

		return errores;
	}

</script>
<div class="form-clasico">
	<form:form name="formGestionarPerfil"
		action="actionAgregarResumenProyecto.jspx" method="POST">

		<div class="encabezado">GESTIONAR PERFIL DEL PROYECTO</div>
		<br>

		<div id="divMensaje" style="visibility: hidden;"></div>
		<input type="hidden" id="mensaje" value="${mensaje}" />
		<input type="hidden" id="perfilId" name="perfilId" value="${perfil.perfilID }" />
		<input type="hidden" id="nomFile" name="nomFile"  />
		<fieldset>
			<legend>DATOS DEL PERFIL</legend>
			<br>
			<fieldset>
				<legend>Montos</legend>
				<table width="100%">
					<tr>
						<td style="text-align: right; width: 25%;"><label>Fondam:</label>
						</td>
						<td style="text-align: left; width: 25%;"><input id="fondam"
							name="montoSolicitadoFondam" value="${perfil.montoSolicitadoFondam}" class="label"
							type="text" /></td>
						<td style="text-align: right; width: 25%"><label>Contrapartida:</label>
						</td>
						<td style="text-align: left; width: 25%;"><input
							id="contrapartida" name="montoContrapartida"  class="label" 
							value="${perfil.montoContrapartida}" type="text" /></td>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%"><label>Cofinanciador:</label>
						</td>
						<td style="text-align: left; width: 25%;"><input
							id="cofinanciador" name="montoCofinanciador"  class="label"
							value="${perfil.montoCofinanciador}" type="text" /></td>
						<td style="text-align: right; width: 25%"><label>Duraci&ograve;n(meses):</label>
						</td>
						<td style="text-align: left; width: 25%;"><input
							id="duracionPerfil" name="duracionMeses" value="${perfil.duracionMeses} meses"  class="label"
							type="text" /></td>
					</tr>

					<tr class="hide">
						<td style="text-align: right; width: 25%;"><label>Documento
								Resumen (pdf,docx):</label></td>
						<td colspan="3" style="text-align: center; width: 75%;"><iframe
								src="showArchivoUploadPerfil.jspx" id="window" frameborder="0"
								height="48px" width="100%" scrolling="no"></iframe></td>
</tr>
<tr>
<td style="text-align: right; width: 25%; vertical-align: top;"><label>Documento
								Resumen:</label></td>
						<td colspan="3" style="text-align: center; width: 75%;"><iframe
								src="showImagenArchivoDownloadGestionarPerfil.jspx" id="window"
								frameborder="0" style="height: auto;" width="100%" scrolling="no"></iframe>
						</td>
					</tr>
					<tr class="hide">
						<td colspan="3" style="width: 75%;"></td>
						<td style="text-align: right; width: 25%;"><input
							type="button" value="Actualizar"
							onclick="fActualizarMontosPerfil()">
						</td>
					</tr>
				</table>
			</fieldset>
			<br> <br />
			<fieldset>
				<legend>RESUMEN Y OBSERVACIONES DEL PERFIL</legend>
				<table width="100%" border="0"  class="hide">
					<tr>
						<td style="width: 100%;" colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td style="width: 25%; text-align: right;"><label>Tipo de Resumen de Proyecto:</label>
						</td>
						<td colspan="3"  style="width: 75%; text-align: left;"><select name="idTipoResumen"
							id="idTipoResumen" style="width: 98%;">
								<option value="0">
									<c:out value="-- Tipo Resumen --" />
								</option>
								<c:forEach items="${listTipoResumen}" var="tipoResumen">
									<option value="${tipoResumen.tablaEspecificaID}">
										<c:out value="${tipoResumen.descripcionCabecera}" />
									</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td  style="width: 25%; text-align: right;"><label>Resumen:</label></td>
						<td colspan="3"  style="width: 75%; text-align: left;"><textarea id="definicionR" name="definicionR"
								COLS="50" ROWS="4" style="width: 99%;"></textarea></td>
					</tr>
					<tr>
						<td colspan="4" style="width: 100%; text-align: right;"><input type="button"
							name="btnAgregarResumen" onclick="javascript:fAgregarResumen()"
							value="Agregar Resumen" /></td>
					</tr>
				</table>
				<table width="100%" border="0" >	
					<tr>
						<td colspan="4">
						<table class="table-clasico" width="100%">
							<caption><label>Resumenes del Perfil</label></caption>
							<thead>
								<tr>
									<td style="width: 25%; text-align: center;"><label>Tipo <br>Resumen</label></td>
									<td style="width: 70%; text-align: center;"><label>Definicion</label></td>
									<td style="width: 5%; text-align: center;" class="hide" ><label>Eliminar</label></td>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${listResumenProyecto}" var="resumen" varStatus="indice">
	<c:choose>
										<c:when test="${indice.count %2== 0}">
											<c:set var="classIdi" value="f2"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdi" value="f1"></c:set>
										</c:otherwise>
									</c:choose>
	<tr class="<c:out value="${classIdi}"></c:out>">
									<td style="width: 25%; text-align: left; vertical-align: top;"><label>
									<c:forEach items="${listTipoResumen}" var="val">
										<c:if
											test="${val.tablaEspecificaID== resumen.fkIdtablaespTipoResumenProy}">
											<c:out value="${val.descripcionCabecera}" />
										</c:if>
									</c:forEach></label></td>
									<td style="width: 70%; text-align: left;"><label><c:out value="${resumen.definicion }"></c:out></label></td>
									<td style="width: 5%; text-align: center;" class="hide">
										<a href="javascript:fEliminarResumenProyecto('${resumen.resumenProyectoID}')"><img
										alt="Eliminar" src="<c:url value="/images/del.png" />" /> </a>
									</td>
								</tr>
												<script type="text/javascript">
var objResumen = new Object();
objResumen.resumenId = "<c:out value="${resumen.fkIdtablaespTipoResumenProy}"></c:out>";

arrayResumen
		.push(objResumen);
</script>
								</c:forEach>
							</tbody>
						</table>
						</td>
					</tr>
				</table>
			</fieldset>
		</fieldset>
	</form:form>
</div>