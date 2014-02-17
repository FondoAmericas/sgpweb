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
	$("#fechaAprobacionPorFondam").attr("value",dia + "/" + (f.getMonth() +1) + "/" + f.getFullYear());
		
	cargaGrillaObservaciones();

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
			"cargaGrillaObservacionAtendidas.jspx",
			{
				datoProyectoID : $("#datoProyectoID").val(),
				fechaActual : $("#fechaAprobacionPorFondam").val()
			});
}

function aprobarObservacion(observacionID){
	var confirma = confirm("Seguro que desea aprobar la observacion seleccionada?");
	if(confirma==true){	
		var check=0;
		if ($("#observacionLevantada"+observacionID).is(":checked")) {
			check = 1;
		}
		
	$.get("grabarAprobarObservacion.jspx", {
		observacionId : observacionID,
		fechaAprobacionPorFondam : $("#fechaAprobacionPorFondam").val(),
		estadoObservacion : $("#estadoObservacion").val(),
		check : check
			}, function() {
				cargaGrillaObservaciones();
				//limpiaCajas();
			});	
	}else if(confirma==false){	
		$("#observacionLevantada"+observacionID).removeAttr("checked");	
		}
}
</script>
<div id="div_carga">
		<img id="cargador" src="<c:url value="/images/ajax-loaderB.gif"/>"
			width="120px" height="120px" />
	</div>
	
<div class="form-clasico" >
	<div class="encabezado">OBSERVACIONES DEL PROYECTO <br/> <c:out value="${datoProyecto.nombreProyecto }"></c:out></div>
	<br/>
<fieldset>
<legend>Observaciones</legend>
<input type="hidden" name="usuarioId" 
							id="usuarioId" value="<c:out value="${userSession.usuarioID }"></c:out>" />
							<input type="hidden" name="datoProyectoID"
							id="datoProyectoID" value="<c:out value="${datoProyecto.datoProyectoID }"></c:out>" />
							<input type="hidden" name="tablaClaseId" 
							id="tablaClaseId" value='<c:out value="${tablaClaseId }"></c:out>' />
							<input type="hidden" name="tablaProfundidadesId"
							id="tablaProfundidadesId"  value='<c:out value="${tablaProfundidadId }"></c:out>' />
							<input type="hidden" name="tablaId"
							id="tablaId"  value='<c:out value="${tablaId }"></c:out>' />
							<input type="hidden" name="claseId"
							id="claseId"  value='<c:out value="${claseId }"></c:out>' />
	<!-- <table width="100%">
		<tr>
			<td style="text-align: right; width: 25%;"><label>Observacion:</label></td>
			<td colspan="3" style="text-align: left;width: 75%;"><textarea rows="3" cols="23" id="descripcion" name="descripcion" style="width:98%;"><c:out value="${bean.descripcion}"></c:out></textarea>
			<input type="hidden" name="usuarioId" 
							id="usuarioId" value="<c:out value="${userSession.usuarioID }"></c:out>" />
							<input type="hidden" name="datoProyectoID"
							id="datoProyectoID" value="<c:out value="${datoProyecto.datoProyectoID }"></c:out>" />
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
			<td style="text-align: right; width: 25%;"><label>Observacion de Ejecutor:</label></td>
			<td colspan="3" style="text-align: left;width: 75%;"><textarea rows="3" cols="23" id="descripcionEjecutor" name="descripcionEjecutor" style="width:98%;"><c:out value="${bean.descripcion}"></c:out></textarea>
			</td>
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
			<td style="text-align: right; width: 25%"><label>Fecha Atendido por Ejecutor:</label></td>
			<td style="text-align: left; width: 25%"><input type="text" name="fechaAtendidoPorEjecutor" maxlength="0" style="width: 90px;"
							id="fechaAtendidoPorEjecutor" disabled="disabled" value="${fechaObservacion }"
							onkeypress="javascript:return Valida_Dato(event,7);" /><label>(dd/mm/aaaa)</label>
			</td>			
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
		</table>
		<br/>-->
		<table>
			<tr>
				<td style="text-align: right; width: 35%"><label>Fecha de Aprobacion por Fondam:</label></td>
				<td style="text-align: left; width: 25%"><input type="text" name="fechaAprobacionPorFondam" maxlength="0" style="width: 90px;"
								id="fechaAprobacionPorFondam" disabled="disabled" 
								onkeypress="javascript:return Valida_Dato(event,7);" /><label>(dd/mm/aaaa)</label>
				</td>		
				<td  style="text-align: right; width: 15%"><label>Estado:</label></td>
				<td  style="text-align: left; width: 25%"><select name="estadoObservacion" style="width: 100%;"
				id="estadoObservacion" disabled="disabled">
				<option value="0" ><c:out value="-- Estado --" /></option>
				<c:forEach items="${listEstadoAtencion}" var="estadoObservacion">
						<option value="<c:out value="${estadoObservacion.detalleEstadoCabeceraID }"/>" 
						<c:if test="${estadoObservacion.prefijoEstado == 'apob' }">selected="selected"</c:if>
						>
						<c:out value="${estadoObservacion.descripEstado }" />
						</option>
				</c:forEach>
				
			</select></td>
			</tr>
		</table>
		<div style="text-align: right;">
				<input type="button" id="ocultar" name="ocultar" value="Regresar" onclick="ocultarObservacion()">
		</div>
	<br />
	<div id="grillaObservaciones" ></div>
	</fieldset>
</div>