<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/includesTaglibsGenerico.jsp"%>
	
<script type="text/javascript">
$(document).ready(function() {
	var f = new Date();
	var dia;
	if (f.getDate()<10){
		dia="0"+f.getDate();
	}else{
		dia=f.getDate();
	}
	
	$("#fechaAtendidoPorEjecutor").attr("value",dia + "/" + (f.getMonth() +1) + "/" + f.getFullYear());
	
	cargaGrillaObservaciones();
	$("#no").show("fast");
	$("#si").hide("fast");
	$("#registrarObservacion").attr("disabled","disabled");
	
	$("#div_carga").hide().ajaxStart(function() {
		$(this).show();
	}).ajaxStop(function() {
		$(this).hide();
	});
});

function fCancelar(){
	limpiaCajas();
}

function cerrar(){
	window.close();
}
</script>
<script type="text/javascript">
function cargaGrillaObservaciones() {
	$("#grillaObservaciones").load(
			"cargaGrillaObservacionEjecutor.jspx",
			{
				datoProyectoID : $("#datoProyectoID").val(),
				tablaClaseId : $("#observacionEn").val()
			});
}

/*function funcionRelevanteProyecto(){
	if($("#observacionRelevanteProyecto").is(":checked")){
		$("#no").hide("fast");
		$("#si").show("fast");
	}else{
		$("#no").show("fast");
		$("#si").hide("fast");
	}
}*/

function registrarObservacion(){
	//var error= validaCajas();
	
	/*if(error==0){
		var observacionRelevanteProyecto=0;
		if ($("#observacionRelevanteProyecto").is(":checked")) {
			observacionRelevanteProyecto = 1;
		}
		*/
	var confirma = confirm("Seguro que desea colocar el estado como atendido para que Fondam lo revise?");
	if(confirma==true){	
	$.get("grabarLevanteObservacionEjecutor.jspx", {
		descripcionEjecutor : $("#descripcionEjecutor").val(),
		observacionId : $("#observacionId").val(),
		estadoObservacion : $("#estadoObservacion").val(),
		fechaAtendidoPorEjecutor : $("#fechaAtendidoPorEjecutor").val()
			}, function() {
				cargaGrillaObservaciones();
				limpiaCajas();
			});	
	}
	//}
}

function levantarObservacion(observacionId,descripcion,tipoObservacionId,fechaObservacion,tableclaseID,relevanteProyecto,descripcionEjecutor){
	$("#observacionId").attr("value",observacionId);
	$("#descripcion").attr("value",descripcion);
	$("#descripcionEjecutor").attr("value",descripcionEjecutor);
	$("#tipoObservacion").attr("value",tipoObservacionId);
	$("#fechaObservacion").attr("value",fechaObservacion);
	$("#observacionEn").attr("value",tableclaseID);
	$("#observacionEn").attr("disabled","disabled");
	if(relevanteProyecto==1){
		$("#no").hide("fast");
		$("#si").show("fast");
	}else{
		$("#no").show("fast");
		$("#si").hide("fast");
	}
	$("#registrarObservacion").removeAttr("disabled");
}
</script>

<script type="text/javascript">
/*function validaCajas() {
	var errores = 0;
	var mensaje = "";

	if ($("#descripcionEjecutor").val().length== 0) {
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
}*/

function limpiaCajas(){
	$("#descripcion").attr("value","");
	$("#descripcionEjecutor").attr("value","");
	$("#tipoObservacion").attr("value",0);
	$("#fechaObservacion").attr("value","");
	//$("#observacionRelevanteProyecto").removeAttr("checked");
	$("#no").show("fast");
	$("#si").hide("fast");	
	$("#observacionId").attr("value",0);
	$("#observacionEn").attr("value",0);
	$("#observacionEn").removeAttr("disabled");
	cargaGrillaObservaciones();
	$("#registrarObservacion").attr("disabled","disabled");
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
<fieldset><legend>LEVANTAR OBSERVACI&Oacute;N</legend>
	<table width="100%">
		<tr>
			<td style="text-align: right; width: 25%;"><label>Descripci&oacute;n:</label></td>
			<td colspan="3" style="text-align: left;width: 75%;"><textarea rows="3" cols="23" id="descripcion" name="descripcion" style="width:98%;" disabled="disabled"><c:out value="${bean.descripcion}"></c:out></textarea>
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
							id="claseId"  value='<c:out value="${claseId }"></c:out>' />
							<input type="hidden" name="observacionId"
							id="observacionId" value="0" /></td>
		</tr>
		<tr>
			<td style="text-align: right; width: 25%;"><label>Observacion de Ejecutor:</label></td>
			<td colspan="3" style="text-align: left;width: 75%;"><textarea rows="3" cols="23" id="descripcionEjecutor" name="descripcionEjecutor" style="width:98%;" ><c:out value="${bean.descripcion}"></c:out></textarea>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;width: 25%">
			<label>Tipo de Observaci&oacute;n:</label></td>
			<td style="text-align: left;width: 25%" ><select name="tipoObservacion" disabled="disabled"
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
				id="estadoObservacion" disabled="disabled" >
				<option value="0" ><c:out value="-- Estado --" /></option>
				<c:forEach items="${listEstadoAtencion}" var="estadoObservacion">
						<option value="<c:out value="${estadoObservacion.detalleEstadoCabeceraID }"/>" 
						<c:if test="${estadoObservacion.prefijoEstado == 'reej' && estado == '0' }">selected="selected"</c:if>
						>
						<c:out value="${estadoObservacion.descripEstado }" />
						</option>
				</c:forEach>
				
			</select>
			</td>					
			<td style="text-align: right; width: 25%"><label>Fecha Atendido por Ejecutor:</label></td>
			<td style="text-align: left; width: 25%"><input type="text" name="fechaAtendidoPorEjecutor" maxlength="0" style="width: 90px;"
							id="fechaAtendidoPorEjecutor" disabled="disabled" 
							onkeypress="javascript:return Valida_Dato(event,7);" /><label>(dd/mm/aaaa)</label>
			</td>				
		</tr>
		<tr>
			<td style="text-align: right;width: 25%">
			<label>Observacion en:</label></td>
			<td style="text-align: left;width: 25%" >
			<select name="observacionEn" id="observacionEn" onchange="cargaGrillaObservaciones()">
				<option value="0" ><c:out value="-- Todos --" /></option>
				<c:forEach items="${listTablaClase}" var="tablaClase">
						<option value="<c:out value="${tablaClase.tableclaseID }"/>" >
						<c:out value="${tablaClase.titulo }" />
						</option>
				</c:forEach>
				
			</select>
			</td>					
			<!-- <td style="text-align: right;width: 25%"><label>Ubicacion:</label></td>
			<td style="text-align: left;width: 25%"><label><c:out value="${profundidad }"></c:out></label> </td>			
		 -->
		</tr>
		<!--<tr>
			<td style="text-align: right;width: 25%">
			<label>Observacion Relevante <br/>al Documento:</label></td>
			<td colspan="3" style="text-align: left;width: 75%" >-->
				<!-- <input type="checkbox" id="observacionRelevanteProyecto" name="observacionRelevanteProyecto" onchange="funcionRelevanteProyecto()"> -->
				<!-- <label id="si" style="color: blue;">Si</label>
				<label id="no" style="color: red;">No</label>
			</td>					
		</tr>-->
		</table>
		<br/>
		<div style="text-align: right;"><input type="button" value="Registrar" id="registrarObservacion" name="registrarObservacion" onclick="javascript:registrarObservacion();" />
				<input type="button" value="Cancelar" onclick="javascript:fCancelar();" />
				<input type="button" value="Cerrar" onclick="javascript:cerrar();" />
		</div>
	<br />
	<div id="grillaObservaciones" ></div>
	</fieldset>
</div>