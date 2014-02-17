<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/includesTaglibsGenerico.jsp"%>
	
<script type="text/javascript">
$(document).ready(function() {
	/*$("#fechaObservacion").datepicker({
		changeMonth : true,
		changeYear : true
	});*/
	cargaGrillaObservaciones();
	$("#no").show("fast");
	$("#si").hide("fast");
	
	$("#div_carga").hide().ajaxStart(function() {
		$(this).show();
	}).ajaxStop(function() {
		$(this).hide();
	});
});

function fCancelar(){
	window.close();
}

</script>
<script type="text/javascript">
function cargaGrillaObservaciones() {
	$("#grillaObservaciones").load(
			"cargaGrillaObservacion.jspx",
			{
				datoProyectoID : $("#datoProyectoID").val()
			});
}

function funcionRelevanteProyecto(){
	if($("#observacionRelevanteProyecto").is(":checked")){
		$("#no").hide("fast");
		$("#si").show("fast");
	}else{
		$("#no").show("fast");
		$("#si").hide("fast");
	}
}

function registrarObservacion(){
	var error= validaCajas();
	
	if(error==0){
		var observacionRelevanteProyecto=0;
		if ($("#observacionRelevanteProyecto").is(":checked")) {
			observacionRelevanteProyecto = 1;
		}
		
	$.get("grabarObservacion.jspx", {
		descripcion : $("#descripcion").val(),
		tipoObservacion : $("#tipoObservacion").val(),
		fechaObservacion : $("#fechaObservacion").val(),
		estadoObservacion : $("#estadoObservacion").val(),
		usuarioId : $("#usuarioId").val(),
		datoProyectoID : $("#datoProyectoID").val(),
		tablaClaseId : $("#tablaClaseId").val(),
		tablaId : $("#tablaId").val(),
		tablaProfundidadesId : $("#tablaProfundidadesId").val(),
		claseId : $("#claseId").val(),
		observacionRelevanteProyecto : observacionRelevanteProyecto
			}, function() {
				cargaGrillaObservaciones();
				limpiaCajas();
			});	
	}
	
}

</script>

<script type="text/javascript">
function validaCajas() {
	var errores = 0;
	var mensaje = "";

	if ($("#descripcion").val().length== 0) {
		mensaje += "Debe ingresar una descripcion para la observacion. \n";
		errores = errores + 1;
	}
	
	if ($("#tipoObservacion").val()== 0) {
		mensaje += "Debe seleccionar un tipo de observacion. \n";
		errores = errores + 1;
	}
		
	if (errores > 0) {
		alert(mensaje);
	}

	return errores;
}

function limpiaCajas(){
	$("#descripcion").attr("value","");
	$("#tipoObservacion").attr("value",0);
	$("#observacionRelevanteProyecto").removeAttr("checked");
	$("#no").show();
	$("#si").hide("fast");	
}
</script>
<div id="div_carga">
		<img id="cargador" src="<c:url value="/images/ajax-loaderB.gif"/>"
			width="120px" height="120px" />
	</div>
<div class="form-clasico" >
	<div class="encabezado">OBSERVACIONES</div>
	<br/>
	<div id="divMensaje"></div>
 <input type="hidden" id="mensaje" value="${mensaje}" />
<fieldset><legend>REGISTRAR OBSERVACI&Oacute;N</legend>
	<table width="100%">
		<tr>
			<td style="text-align: right; width: 25%;"><label>Descripci&oacute;n:</label></td>
			<td colspan="3" style="text-align: left;width: 75%;"><textarea rows="3" cols="23" id="descripcion" name="descripcion" style="width:98%;"><c:out value="${bean.descripcion}"></c:out></textarea>
			<input type="hidden" name="usuarioId" 
							id="usuarioId" value="<c:out value="${userSession.usuarioID }"></c:out>" />
							<input type="hidden" name="datoProyectoID"
							id="datoProyectoID" value="<c:out value="${datoProyectoID }"></c:out>" />
							<input type="hidden" name="tablaClaseId" 
							id="tablaClaseId" value='<c:out value="${tablaClaseId }"></c:out>' />
							<input type="hidden" name="tablaProfundidadesId"
							id="tablaProfundidadesId"  value='<c:out value="${tablaProfundidadId }"></c:out>' />
							<input type="hidden" name="tablaId"
							id="tablaId"  value='<c:out value="${tablaId }"></c:out>' />
							<input type="hidden" name="claseId"
							id="claseId"  value='<c:out value="${claseId }"></c:out>' /></td>
		</tr>

		<tr>
			<td style="text-align: right;width: 25%">
			<label>Tipo de Observaci&oacute;n:</label></td>
			<td style="text-align: left;width: 25%" ><select name="tipoObservacion"
				id="tipoObservacion">
				<option value="0"><c:out value="-- Tipo --" /></option>
				<c:forEach items="${listTipoObservacion}" var="tObservacion">
						<option value="${tObservacion.tablaEspecificaID}">
						<c:out value="${tObservacion.descripcionCabecera}" />
						</option>
				</c:forEach>
			</select>
			</td>					
			<td style="text-align: right; width: 25%"><label>Fecha Observacion:</label></td>
			<td style="text-align: left; width: 25%"><input type="text" name="fechaObservacion" maxlength="0" style="width: 90px;"
							id="fechaObservacion" disabled="disabled" value="${fechaObservacion }"
							onkeypress="javascript:return Valida_Dato(event,7);" /><label>(dd/mm/aaaa)</label>
			</td>			
		</tr>
		<tr>
			<td style="text-align: right;width: 25%">
			<label>Estado:</label></td>
			<td style="text-align: left;width: 25%" ><select name="estadoObservacion"
				id="estadoObservacion" <c:if test="${estado == -1 }">disabled="disabled"</c:if> >
				<option value="0" ><c:out value="-- Estado --" /></option>
				<c:forEach items="${listEstadoAtencion}" var="estadoObservacion">
						<option value="<c:out value="${estadoObservacion.detalleEstadoCabeceraID }"/>" 
						<c:if test="${estadoObservacion.prefijoEstado == 'ndes' && estado == '-1' }">selected="selected"</c:if>
						>
						<c:out value="${estadoObservacion.descripEstado }" />
						</option>
				</c:forEach>
				
			</select>
			</td>					
			<td colspan="2" style="width: 50%"></td>			
		</tr>
		<tr>
			<td style="text-align: right;width: 25%">
			<label>Observacion en:</label></td>
			<td style="text-align: left;width: 25%" >
			<label><c:out value="${tablaClase.titulo }"></c:out> </label>
			</td>					
			<td style="text-align: right;width: 25%"><label>Ubicacion:</label></td>
			<td style="text-align: left;width: 25%"><label><c:out value="${profundidad }"></c:out></label> </td>			
		
		</tr>
		<tr>
			<td style="text-align: right;width: 25%">
			<label>Observacion Relevante <br/>al Documento:</label></td>
			<td colspan="3" style="text-align: left;width: 75%" >
				<input type="checkbox" id="observacionRelevanteProyecto" name="observacionRelevanteProyecto" onchange="funcionRelevanteProyecto()">
				<label id="si" style="color: blue;">Si</label>
				<label id="no" style="color: red;">No</label>
			</td>					
		</tr>
		</table>
		<br/>
		<div style="text-align: right;"><input type="button" value="Registrar" onclick="javascript:registrarObservacion();" />
				<input type="button" value="Cerrar" onclick="javascript:fCancelar();" />
		</div>
	<br />
	<div id="grillaObservaciones" ></div>
	</fieldset>
</div>