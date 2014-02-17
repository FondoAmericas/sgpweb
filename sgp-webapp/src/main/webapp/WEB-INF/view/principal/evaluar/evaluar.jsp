<%@ include file="/common/taglibs.jsp"%>


<script type="text/javascript">
var evalua;
var cargarProyec=0;
var dato;

function fMostrarCabezera(){
	if (evalua==173) {
		$('#cinstitucional').show();
	}else if (evalua==174) {
		$('#cperfil').show();
	}else if (evalua==175) {
		$('#cproyecto').show();
	}
	
}
$(document).ready(function(){
		$('#tab-container').easytabs();
		$('#tab-containerProyecto').easytabs();
		
		$('#detalleContrapartida').hide();
		$('#detalleCofinanciador').hide();
	
	$('#contrapartida').mouseover(function(){
        $('#detalleContrapartida').show("slow"); 
        $('#contrapartida').css("text-decoration", "underline"); 
    }).mouseout(function(){
        $('#detalleContrapartida').hide("fast");
        $('#contrapartida').css("text-decoration", "none");
    });
	
	$('#cofinanciador').mouseover(function(){
        $('#detalleCofinanciador').show("slow"); 
        $('#cofinanciador').css("text-decoration", "underline"); 
    }).mouseout(function(){
        $('#detalleCofinanciador').hide("fast");
        $('#cofinanciador').css("text-decoration", "none");
    });


	$('#divPerfil').hide();
	
	$('#divEvaluacion').hide();
	
	$('#divProyecto').hide();
	
	$('#cinstitucional').hide();
	
	$('#cperfil').hide();
	
	$('#cproyecto').hide();
	
	$('#divInstitucion').hide();

	evalua=${evaluacion};

	var a=$('#mensaje').attr('value');
	dato=$('#mostrarProyectos').attr('value');
	 if (a !='no' && a!="" ) {
		alert(a);
		return;
	}

	 fMostrarCabezera();	
});
var flag=0;
$(window).load(function(){
	flag=$('#flag').attr('value');
	
	fMostrarCabezera();

	if (dato==0) {
		$('#divListaProgramas').show();

	}	
	if (dato==1) {
		$('#divListaProgramas').hide();
		$('#divListProyectos').show('Bounce');
	
		
	}	
	if (dato==173) {
		$('#divEvaluacion').show();
		$('#divInstitucion').show();
		$('#divListProyectos').hide();
		$('#divListaProgramas').hide();

	}
	if (dato==174) {
		$('#divEvaluacion').show();
		$('#divPerfil').show();
		$('#divListProyectos').hide();
		$('#divListaProgramas').hide();

	}	
	if (dato==175) {
		$('#divEvaluacion').show();
		$('#divProyecto').show();
		$('#divListProyectos').hide();
		$('#divListaProgramas').hide();

	}
	if (flag==1) {
		$('#divListaProgramas').hide();
		$('#divListProyectos').show('Bounce');
	}	
	if (flag==2) {
		$('#divListaProgramas').show('Bounce');
		$('#divListProyectos').hide();
	}	
	});

function fBuscarPrograma(){
	var form = document.formEvaluar;
	form.action = "actionBuscarProgramaEvaluacion.jspx?evalu="+evalua;
	form.submit();
}

function fMostrarSelect(valor){
	if (valor ==0) {
		$('#divlistTipoPeriodos').hide();
		$('#divlistTipoCuentas').hide();
		$('#divlistModalidadFinanciera').hide();
		$('#divNombre').hide();
		$('#divSelect').show();
	} 
	if (valor ==100) {
		$('#divlistTipoPeriodos').show();
		$('#divlistTipoCuentas').hide();
		$('#divlistModalidadFinanciera').hide();
		$('#divNombre').hide();
		$('#divSelect').hide();
	} 
	if(valor ==101){
		$('#divlistTipoPeriodos').hide();
		$('#divlistTipoCuentas').show();
		$('#divlistModalidadFinanciera').hide();
		$('#divNombre').hide();
		$('#divSelect').hide();
	}
	if(valor ==102){
		$('#divlistTipoPeriodos').hide();
		$('#divlistTipoCuentas').hide();
		$('#divlistModalidadFinanciera').show();
		$('#divNombre').hide();
		$('#divSelect').hide();
	}
	if(valor ==103){
		$('#divNombre').show();
		$('#divlistTipoPeriodos').hide();
		$('#divlistTipoCuentas').hide();
		$('#divlistModalidadFinanciera').hide();
		$('#divSelect').hide();
	}
	if (valor ==104) {
		$('#divlistTipoPeriodos').hide();
		$('#divlistTipoCuentas').hide();
		$('#divlistModalidadFinanciera').hide();
		$('#divNombre').hide();
		$('#divSelect').hide();
	}	

}
function fCargarProgramas(proyec){
	var form = document.formEvaluar;
	form.action = "actionCargarProyectos.jspx?idPrograma="+proyec+"&&evalu="+evalua;
	form.submit();

}

function fCargarProyecto(idProyec){
	$('#idProyectoForm').val(idProyec);
	var form = document.formEvaluar;
	form.action = "actionMostrarInformacionEvaluacion.jspx?idProy="+idProyec+"&&evalu="+evalua;
	form.submit();
}

	function fCancelar(){
	$('#divListaProgramas').show('Bounce');
	$('#divListProyectos').hide();
	
}
function fCancelarPrograma(){
	
	$('#divListProyectos').show();
	$('#divEvaluacion').hide();
	$('#divInstitucion').hide();
	$('#divPerfil').hide();
	$('#divProyecto').hide();


}
  function validar(){
		
	  	var cabecera = "Debe ingresar la siguiente informaci\u00F3n obligatoria(*):";
		var minimo = parseFloat($('#minimo').attr('value'));
		var maximo =parseFloat( $('#maximo').attr('value'));
		var calificacion = $('#calificacion').attr('value');
		//var observacion = $('#observacionE').attr("value").toString();  
		var mensaje = "";
		
		if(calificacion == ""){
		   mensaje += "\n - Ingrese una calificaci\u00F3n.";
		}

		/*if(observacion == ""){
		   mensaje += "\n - Ingrese Observaci\u00F3n.";
		}*/
		if(calificacion != "" && !(calificacion <= maximo && calificacion >= minimo)){
		   mensaje += "\n - Ingrese Calificacion en el rango de: " + minimo + " - " + maximo + ".";
		}
		
		if(mensaje != ""){
		   alert(cabecera + mensaje);
		   return true;
		}else{
			 return false;
		};
	
  }

function fRegistrarEvaluacion(){
	
	if (validar()) {
		return;
	}
	var form = document.formEvaluar;
	if(evalua == 173){
	   form.action = "actionRegistrarEvaluacionInstitucion.jspx?evalu="+evalua+"&idProyecto="+$("#idProyectoForm").val()+"&calificacion="+$("#calificacion").val()+"&observacionE="+$("#observacionE").val();
		
   }else if(evalua ==174){
	   form.action = "actionRegistrarEvaluacionPerfil.jspx?evalu="+evalua+"&idProyecto="+$("#idProyectoForm").val()+"&calificacion="+$("#calificacion").val()+"&observacionE="+$("#observacionE").val();
		
   }else if(evalua ==175){
	   form.action = "actionRegistrarEvaluacionProyecto.jspx?evalu="+evalua+"&idProyecto="+$("#idProyectoForm").val()+"&calificacion="+$("#calificacion").val()+"&observacionE="+$("#observacionE").val();
		
   }
	form.submit();
}

function fActualizarEvaluacion(){
	if (validar()) {
		return;
	}
   
	/*var form = document.formEvaluar;
	form.action = "actionActualizarEvaluacion.jspx?evalu="+evalua;
	form.submit();*/
	var form = document.formEvaluar;
	if(evalua == 173){
	   form.action = "actionRegistrarEvaluacionInstitucion.jspx?evalu="+evalua+"&evaluacionId="+$("#evaluacionId").val()+"&idProyecto="+$("#idProyectoForm").val()+"&calificacion="+$("#calificacion").val()+"&observacionE="+$("#observacionE").val();
		
   }else if(evalua ==174){
	   form.action = "actionRegistrarEvaluacionPerfil.jspx?evalu="+evalua+"&evaluacionId="+$("#evaluacionId").val()+"&idProyecto="+$("#idProyectoForm").val()+"&calificacion="+$("#calificacion").val()+"&observacionE="+$("#observacionE").val();
		
   }else if(evalua ==175){
	   form.action = "actionRegistrarEvaluacionProyecto.jspx?evalu="+evalua+"&evaluacionId="+$("#evaluacionId").val()+"&idProyecto="+$("#idProyectoForm").val()+"&calificacion="+$("#calificacion").val()+"&observacionE="+$("#observacionE").val();
		
   }
	form.submit();
}

</script>

<script type="text/javascript">
	function agregarObservacion(tablaId,datoProyectoID,tablaClaseId,tablaProfundidadId,claseId){
		var url = "showGestionarObservacion.jspx?tablaId=" + tablaId + "&datoProyectoID="+datoProyectoID+"&tablaClaseId="+tablaClaseId+"&tablaProfundidadId="+tablaProfundidadId+"&claseId="+claseId;
		var stiloPopUp = 'dialogWidth=800px; dialogHeight=500px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
		//ventanaPopUp = showModalDialog(url,'',stiloPopUp);//window.open(url,'',stiloPopUp);

		//window.opener.document.formWindowAsignaTutor.profesorAsignado.value = componenteId;

		//window.showModalDialog(url,datos,stiloPopUp);
		window.showModalDialog(url, "", stiloPopUp);

		}
	
</script>


	<div class="form-clasico">
		<div class="encabezado" id="cinstitucional">EVALUAR	INSTITUCI&Oacute;N</div>
		<div class="encabezado" id="cperfil">EVALUAR PERFIL</div>
		<div class="encabezado" id="cproyecto">EVALUAR PROYECTO</div>
		
		<form:form name="formEvaluar" action="actionEvaluar.jspx"
			method="POST" enctype="multipart/form-data">
			<input type="hidden" id="mostrarProyectos" name="mostrarProyectos"
				value="${cargarProyecto}">
			<input type="hidden" id="idProyectoForm" name="idProyectoForm"
				value="${idProyectoForm}">
			<input type="hidden" id="variable" name="variable"
				value="${variable}">
			<input type="hidden" id="flag" name="flag" value="${flag}">
			<br />
			<div id="divEvaluacion" style="display: none;">
				<fieldset>
					<legend>EVALUACI&Oacute;N</legend>
					<input type="hidden" value="${minimo}" id="minimo"> <input
						type="hidden" value="${maximo}" id="maximo">

					<table width="100%" align="center">
						<tr>
							<td style="text-align: right; width: 20%;"><label><b>Puntaje
										Min:</b> </label>
							</td>
							<td style="text-align: left; width: 10%;"><label>${minimo}</label>
							</td>
							<td style="text-align: right; width: 20%;"><label><b>Puntaje
										Max:</b> </label>
							</td>
							<td style="text-align: left; width: 10%;"><label>${maximo}</label>
							</td>
							<td style="text-align: right; width: 30%;"><label><b>Minimo
										Aprobatorio:</b> </label>
							</td>
							<td style="text-align: left; width: 10%;"><label>${minimoAprobatorio}</label>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 20%;"><label><b>Calificaci&oacute;n:</b>
							</label>
							</td>
							<c:choose>
								<c:when test="${cantObservacionesRelevantes>0 }">
								<td colspan="3" style="text-align: left; width: 80%;">
									<input type="hidden" name="calificacion" id="calificacion" >
									<label style="color: red;">Proyecto Tiene observaciones relevantes sin atender.</label>
									</td> 
								</c:when>
								<c:otherwise>
								<td style="text-align: left; width: 10%;">
									<input type="text"
								name="calificacion" id="calificacion" value="${calificacion}"
								onkeypress="javascript:return isNumberKeyP(event);"
								style="width: 40px;" maxlength="3"> 
								
							<input type="hidden"
								name="evaluacionId" id="evaluacionId" value="${evaluacionId }">
							</td>
							<td style="text-align: right; width: 20%;"><label><b>Observaci&oacute;n:</b>
							</label>
							</td>
							<td colspan="3" style="text-align: left; width: 50%;"><textarea
									rows="3" cols="29" id="observacionE" name="observacionE"
									style="width: 98%;"><c:out value="${observacionE}" /></textarea>
							</td>
							</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<td><c:if test="${variable!=2}">

									<c:if test="${variable!=1}">
										<input align="top" type="button"
											onclick="fRegistrarEvaluacion()"
											value="Registrar Evaluaci&oacute;n" />
									</c:if>
									<c:if test="${variable==1}">
										<input align="top" type="button"
											onclick="fActualizarEvaluacion()"
											value="Actualizar Evaluaci&oacute;n" />
									</c:if>

								</c:if></td>
							<td><input type="button" onclick="fCancelarPrograma()"
								value="Cancelar" align="top" /></td>
						</tr>
					</table>
				</fieldset>
			</div>
			<input type="hidden" id="mensaje" value="${mensajeEvaluador}" />
			<div id="divListaProgramas" style="display: none;">
				<fieldset>
					<legend>Programas</legend>
					<table>
						<tr>
							<td><label>Filtro:</label></td>
							<td><select name="idFiltro" id="idFiltro"
								onchange="fMostrarSelect(this.value)">
									<option value="0">
										<c:out value="--Seleccionar--" />
									<option value="100">
										<c:out value="Tipo Periodo" />
									</option>
									<option value="101">
										<c:out value="Tipo Cuenta" />
									</option>
									<option value="102">
										<c:out value="Modalidad" />
									</option>
									<option value="104">
										<c:out value="Todos" />
									</option>
							</select>
							</td>

							<td>
								<div id="divSelect">
									<select name="sele" disabled="disabled">
										<option value="0">
											<c:out value="--Seleccionar--" />
										</option>
									</select>
								</div>
								<div id="divlistTipoPeriodos" style="display: none;">
									<select name="idTipoPeriodos" id="idTipoPeriodos">
										<option value="0">
											<c:out value="--Seleccionar--" />
										</option>
										<c:forEach items="${listTipoPeriodos}" var="tipoPeriodo">
											<option value="${tipoPeriodo.tipoPeriodoID}">
												<c:out value="${tipoPeriodo.descripPeriodo}" />
											</option>

										</c:forEach>
									</select>
								</div>
								<div id="divlistTipoCuentas" style="display: none;">
									<select name="idTipoCuentas" id="idTipoCuentas">
										<option value="0">
											<c:out value="-- Seleccionar --" />
										</option>
										<c:forEach items="${listTipoCuentas}" var="tipoCuenta">
											<option value="${tipoCuenta.tablaEspecificaID}">
												<c:out value="${tipoCuenta.descripcionCabecera}" />
											</option>
										</c:forEach>
									</select>
								</div>
								<div id="divlistModalidadFinanciera" style="display: none;">
									<select name="idModalidadFinanciera" id="idModalidadFinanciera">
										<option value="0">
											<c:out value="-- Seleccionar --" />
										</option>
										<c:forEach items="${listModalidadFinanciera}" var="modali">
											<option value="${modali.tablaEspecificaID}">
												<c:out value="${modali.descripcionCabecera}" />
											</option>
										</c:forEach>
									</select>
								</div></td>
							<td><input value="Buscar Programa" type="button"
								onclick="fBuscarPrograma()" />
							</td>
						</tr>
					</table>
					<!-- <div id="contenidoTabla" class="contenidoTabla"> -->
					<display:table uid="prog" name="${listaProgramaAndProyecto}"
						defaultsort="1" defaultorder="descending" pagesize="10"
						requestURI="" export="false" class="table-clasico"
						style="width:100%; font-size: 12px;">
						<display:caption>
							<label>Lista de Programas</label>
						</display:caption>
						<display:column property="programaID" title="Id" sortable="true" />
						<display:column property="nombrePrograma" title="Programa"
							sortable="true" />
						<display:column property="duracionPrograma"
							title="Duracion Programa" sortable="true" />
						<display:column title="Tipo Periodo">
							<c:forEach items="${listTipoPeriodos}" var="val">
								<c:if
									test="${val.tipoPeriodoID== prog.tipoPeriodo.tipoPeriodoID}">
									<c:out value="${val.descripPeriodo}" />
								</c:if>
							</c:forEach>
						</display:column>
						<display:column title="Tipo Cuentas">
							<c:forEach items="${listTipoCuentas}" var="val">
								<c:if
									test="${val.tablaEspecificaID== prog.fkIdtablaespTipoCuenta}">
									<c:out value="${val.descripcionCabecera}" />
								</c:if>
							</c:forEach>
						</display:column>
						<display:column title="Modalidad Financiera">
							<c:forEach items="${listModalidadFinanciera}" var="val">
								<c:if
									test="${val.tablaEspecificaID== prog.fkIdtablaespModalidadFinancia}">
									<c:out value="${val.descripcionCabecera}" />
								</c:if>
							</c:forEach>
						</display:column>

						<display:column title="Cargar">
							<a href="javascript:fCargarProgramas('${prog.programaID}')"><span
								class="ui-icon ui-icon-circle-arrow-s" style="cursor: pointer"
								title="Cargar Proyectos"></span> </a>
						</display:column>
					</display:table>
					<!-- </div> -->
				</fieldset>
			</div>
			<div id="divListProyectos" style="display: none;">
				<fieldset>
					<legend>Proyectos</legend>
					<table style="width: 100%;">
						<tr>
							<td style="width: 25%; text-align: right;"><label>Programa:</label>
							</td>
							<td style="width: 25%; text-align: left;"><label><c:out
										value="${programaBean.nombrePrograma }"></c:out> </label></td>
							<td style="width: 25%; text-align: right;"><label>Modalidad
									de Financiamiento:</label></td>
							<td style="width: 25%; text-align: left;"><label><c:out
										value="${programaBean.modalidadFinancia }"></c:out> </label></td>
						</tr>
						<tr>
							<td style="width: 25%; text-align: right;"><label>Duracion
									Maxima(Meses):</label></td>
							<td style="width: 25%; text-align: left;"><label><c:out
										value="${programaBean.duracionPrograma }"></c:out> </label></td>
							<td style="width: 25%; text-align: right;"><label>Tipo
									de Periodo:</label></td>
							<td style="width: 25%; text-align: left;"><label><c:out
										value="${programaBean.nombreTipoPeriodo }"></c:out> </label></td>
						</tr>
					</table>
					<br>

					<display:table uid="proy" name="${listTmpDatoProyecto}"
						requestURI="" pagesize="7" class="table-clasico"
						style="width:100%; font-size: 12px;">
						<display:caption>
							<label>Lista de Proyectos</label>
						</display:caption>
						<display:column property="nombreProyecto" title="Nombre"
							sortable="true" />
						<display:column property="duracionProyecto"
							title="Duraci&oacute;n" sortable="true" />
						<display:column property="codigoProyecto" title="Codig&oacute;"
							sortable="true" />
						<display:column property="cantidadPeriodo"
							title="Cantidad Periodo " sortable="true" />
						<display:column property="descripcionSubAreaTematica"
							title="subArea Tematica" sortable="true" />
						<display:column property="descripcionAreaTematica"
							title="Area Tematica" />
						<display:column title="Cargar">
							<a href="javascript:fCargarProyecto('${proy.datoProyectoID}')"><span
								class="ui-icon ui-icon-circle-arrow-s" style="cursor: pointer"
								title="Cargar Proyecto"></span> </a>
						</display:column>
					</display:table>
					<input type="button" onclick="fCancelar()" value="Regresar"
						align="middle" />
				</fieldset>
			</div>
			<div id="divInstitucion">
				<br />
				<fieldset>
					<legend>DATOS INSTITUCI&Oacute;N</legend>
					<table width="100%">
						<tr>
							<td style="text-align: right; width: 25%;"><label><b>Nombre
										Instituci&oacute;n:</b> </label>
							</td>
							<td colspan="3" style="text-align: left; width: 75%;"><label><c:out
										value="${institucion.nombreInstitucion}" /> </label>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 25%;"><label><b>RUC:</b>
							</label>
							</td>
							<td style="text-align: left; width: 25%;"><label><c:out
										value="${institucion.rucInstitucion}" /> </label>
							</td>
							<td style="text-align: right; width: 25%;"><label><b>Tel&eacute;fono:</b>
							</label>
							</td>
							<td style="text-align: left; width: 25%;"><label><c:out
										value="${institucion.telefono}" /> </label>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 25%;"><label><b>Direcci&oacute;n:</b>
							</label>
							</td>
							<td colspan="3" style="text-align: left; width: 75%;"><label><c:out
										value="${institucion.direccion}" /> </label>
							</td>

						</tr>
						<tr>
							<td style="text-align: right; width: 25%;"><label><b>Representante
										Legal:</b> </label>
							</td>
							<td style="text-align: left; width: 25%;"><label><c:out
										value="${institucion.representanteLegal}" /> </label>
							</td>
							<td style="text-align: right; width: 25%;"><label><b>Contacto:</b>
							</label>
							</td>
							<td style="text-align: left; width: 25%;"><label><c:out
										value="${institucion.contacto}" /> </label>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 25%;"><label><b>Fuente
										Finaciadora:</b> </label>
							</td>
							<td style="text-align: left; width: 25%;"><label><c:out
										value="${fuenteFinanciadora.descripcionCabecera}" /> </label>
							</td>
							<td style="text-align: right; width: 25%;"><label><b>Monto
										Financiado:</b> </label>
							</td>
							<td style="text-align: left; width: 25%;"><label><c:out
										value="${montoFinanciado}  ${moneda.descripcionCabecera}"></c:out>
							</label></td>
						</tr>
						<tr>
							<td style="text-align: right; width: 25%;"><label><b>Correo de Contacto:</b>
							</label>
							</td>
							<td colspan="3" style="text-align: left; width: 75%;"><label>
									<c:out value="${institucion.correo}"></c:out>
							</label></td>
						</tr>
						<tr>
							<td style="text-align: right; width: 25%;"><label><b>Observaciones:</b>
							</label>
							</td>
							<td colspan="3" style="text-align: left; width: 75%;"><label>
									<c:out value="${institucion.observacionDeInstitucion}"></c:out>
							</label></td>
						</tr>
					</table>
					<br />
				</fieldset>
			</div>
			<div id="divPerfil">
				<br />
				<fieldset>
					<legend>INFORMACION BASICA DEL PERFIL</legend>
					<br />
					<div id="tab-container" class="tab-container" style="width: 100%;">
						<ul class='etabs'>
							<li class='tab'><a href="#montosFinanciados">Montos
									Financiados</a></li>
							<li class='tab'><a href="#documento">Documento</a></li>
							<li class='tab'><a href="#resumenObservaciones">Resumen
									&amp; Observaciones</a></li>
						</ul>
						<div class='panel-container'>
							<div id="montosFinanciados">
								<br />
								<fieldset>
									<table width="100%">
										<tr>
											<td style="text-align: right; width: 25%;"><label><b>Fondam:</b>
											</label>
											</td>
											<td style="text-align: left; width: 25%;"><label><c:out
														value="${perfil.montoSolicitadoFondam}" /> <c:if
														test="${perfil.montoSolicitadoFondam >0 }">Dolares</c:if>
											</label>
											</td>
											<td style="text-align: right; width: 25%;"><label><b>Duraci&ograve;n(meses):</b>
											</label>
											</td>
											<td style="text-align: left; width: 25%;"><label><c:out
														value="${perfil.duracionMeses}" /> Meses</label>
											</td>
										</tr>
										<tr>
											<td style="text-align: right; width: 25%;"><label><b><span id="contrapartida">Contrapartida:</span></b>
											</label>
											</td>
											<td style="text-align: left; width: 25%;"><label><c:out
														value="${perfil.montoContrapartida}" /> <c:if
														test="${perfil.montoContrapartida >0 }">Dolares</c:if> </label>
											</td>
											<td style="text-align: right; width: 25%;"><label><b><span id="cofinanciador">Cofinanciador:</span></b>
											</label>
											</td>
											<td style="text-align: left; width: 25%;"><label><c:out
														value="${perfil.montoCofinanciador}" /> <c:if
														test="${perfil.montoCofinanciador >0 }">Dolares</c:if> </label>
											</td>										
										</tr>
										<tr>
											<td colspan="2" style="width: 50%; text-align: center;"><div id="detalleContrapartida">
												<table width="100%">
												<c:forEach items="${perfil.listTmpFuenteFinanciadoraContrapartida }" var="fuenteContrapartida">
													<tr>
														<td style="width: 50%; text-align: right;"><label><b><c:out value="${fuenteContrapartida.nombreInstitucion }"></c:out>:</b></label></td>
														<td style="width: 50%; text-align: left;"><label><c:out value="${fuenteContrapartida.montoFinanciado }"></c:out> Dolares</label></td>
													</tr>
													</c:forEach>
												</table>
											</div>
											</td>
											<td colspan="2" style="width: 50%; text-align: center;"><div id="detalleCofinanciador">
												<table width="100%">
													<c:forEach items="${perfil.listTmpFuenteFinanciadoraCofinanciador }" var="fuenteCofinanciador">
													<tr>
														<td style="width: 50%; text-align: right;"><label><b><c:out value="${fuenteCofinanciador.nombreInstitucion }"></c:out>:</b></label></td>
														<td style="width: 50%; text-align: left;"><label><c:out value="${fuenteCofinanciador.montoFinanciado }"></c:out> Dolares</label></td>
													</tr>
													</c:forEach>
												</table>
											</div>
											</td>
										</tr>
									</table>
								</fieldset>
							</div>
							<div id="documento">
								<br />
								<fieldset>
									<table width="100%">
										<tr>
											<td style="width: 25%; text-align: right; vertical-align: top;"><label>Documentos de Sustento:</label></td>
											<td style="width: 75%; text-align: left;">
											<iframe
													src="showImagenArchivoDownloadEvaluarPerfil.jspx?idProyectoForm=${idProyectoForm}"
													id="window" frameborder="0" width="100%" style="height: auto;"
													scrolling="no"></iframe></td>
										</tr>
									</table>
								</fieldset>
							</div>

							<div id="resumenObservaciones">
								<br />
								<fieldset>
									<legend>Resumen y Obervaciones</legend>
									<display:table uid="resumen" name="${listTmpResumenProyecto}"
										defaultsort="1" defaultorder="ascending" pagesize="10"
										requestURI="" class="table-clasico"
										style="width:100%; font-size: 12px;">
										<display:caption>
											<label>Lista de Resumenes y Observaciones</label>
										</display:caption>
										<display:column title="Tipo Resumen" style="width:25%;">
											<c:forEach items="${listTipoResumen}" var="val">
												<c:if
													test="${val.tablaEspecificaID== resumen.fkIdtablaespTipoResumenProy}">
													<c:out value="${val.descripcionCabecera}" />
												</c:if>
											</c:forEach>
										</display:column>
										<display:column property="definicion" title="Definicion"
											style="width:75%;" sortable="true" />

									</display:table>
								</fieldset>
							</div>
						</div>
					</div>
				</fieldset>
				<br />
			</div>
			<div id="divProyecto">
				<br />
				<fieldset>
					<legend>Dato Proyecto</legend>
					<div style="text-align: right;">
			<input type="button" value="Observaciones Generales" class="hide"
				onclick="agregarObservacion('<c:out value="${idProyectoForm }" ></c:out>','<c:out value="${idProyectoForm }" ></c:out>',2,46,'<c:out value="${idProyectoForm }" ></c:out>');" />
		</div>
		<br />
					<table width="100%">
						<tr>
							<td style="text-align: right; width: 25%; vertical-align: top;"><label><b>Proyecto:</b>
							</label>
							</td>
							<td colspan="3" style="text-align: left; width: 75%;"><label><c:out
										value="${nombreProyecto}" /> </label>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 25%;"><label><b>C&oacute;digo
										Proyecto:</b> </label>
							</td>
							<td style="text-align: left; width: 25%;"><label><c:out
										value="${codigoProyecto}" /> </label>
							</td>
							<td style="text-align: right; width: 25%;"><label><b>Duraci&oacute;n:</b>
							</label>
							</td>
							<td style="text-align: left; width: 25%;"><label><c:out
										value="${duracionProyecto } meses" /> </label>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 25%; vertical-align: top;"><label><b>Área
										Temática:</b> </label>
							</td>
							<td style="text-align: left; width: 25%; vertical-align: top;"><label>
									<c:forEach items="${listAreaTematica}" var="val">
										<c:if
											test="${val.tablaEspecificaID==proyecto.subAreaTematica.fkIdtablaespAreaTematica}">
											<c:out value="${val.descripcionCabecera}" />
										</c:if>
									</c:forEach> </label>
							</td>
							<td style="text-align: right; width: 25%; vertical-align: top;"><label><b>Sub
										área Tematica:</b> </label>
							</td>
							<td style="text-align: left; width: 25%;"><label><c:out
										value="${descripcionSubAt}" /> </label>
							</td>

						</tr>
						<tr>
							<td style="text-align: right; width: 25%;"><label><b>Programa:</b>
							</label>
							</td>
							<td style="text-align: left; width: 25%;"><label><c:out
										value="${nombrePrograma}" /> </label>
							</td>
							<td style="text-align: right; width: 25%;"><label><b>Duraci&oacute;n
										Programa:</b> </label>
							</td>
							<td style="text-align: left; width: 25%;"><label><c:out
										value="${duracionPrograma} meses" /> </label>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 25%;"><label><b>Periodo:</b>
							</label>
							</td>
							<td colspan="3" style="text-align: left; width: 75%;"><label><c:out
										value="${descripPeriodo}" /> </label>
							</td>
						</tr>
					</table>
					<br />
					<div id="tab-containerProyecto" class="tab-containerProyecto"
						style="width: 100%;">
						<ul class='etabs'>
							<li class='tab'><a href="#cuentaCorriente">Cuenta
									Corriente</a>
							</li>
							<li class='tab'><a href="#ubicacionProyecto">Ubicacion
									de Proyecto</a>
							</li>
							<li class='tab'><a href="#financiamiento">Informacion de
									Financiamiento</a>
							</li>
							<li class='tab'><a href="#resumenProyecto">Resumen de
									Proyecto</a>
							</li>
							<li class='tab'><a href="#documento">Documento Adjunto</a>
							</li>
						</ul>
						<div class='panel-container'>
							<div id="cuentaCorriente">
								<br />
								<table class="table-clasico" width="100%">
									<!-- class="table-clasico"-->
									<caption>
										<label>Lista de Cuentas Corrientes del Proyecto</label>
									</caption>
									<thead>
										<tr>
											<td style="width: 3%; text-align: center;"><label>Nro</label>
											</td>
											<td style="width: 25%; text-align: center;"><label>Banco</label>
											</td>
											<td style="width: 15%; text-align: center;"><label>Tipo
													Moneda</label></td>
											<td style="width: 50%; text-align: center;"><label>Numero
													de Cuenta</label></td>
											<td style="width: 7%; text-align: center;"><label>Opciones</label>
											</td>
										</tr>
									</thead>
									<tbody id="tbodyCuentaCorriente">

										<c:forEach items="${listCuentaCorrienteBean }"
											var="cuentaCorrienteBean" varStatus="indice">
											<c:choose>
												<c:when test="${indice.count %2== 0}">
													<c:set var="classIdi" value="f2"></c:set>
												</c:when>
												<c:otherwise>
													<c:set var="classIdi" value="f1"></c:set>
												</c:otherwise>
											</c:choose>
											<tr class="<c:out value="${classIdi}"></c:out>">
												<td style="width: 3%; text-align: center;"><label><c:out
															value="${indice.count }"></c:out> </label></td>
												<td style="width: 25%; text-align: left;"><label><c:out
															value="${cuentaCorrienteBean.descripcionBanco }"></c:out>
												</label></td>
												<td style="width: 15%; text-align: left;"><label><c:out
															value="${cuentaCorrienteBean.descripcionTipoMoneda }"></c:out>
												</label></td>
												<td style="width: 50%; text-align: left;"><label><c:out
															value="${cuentaCorrienteBean.numeroCuenta }"></c:out> </label></td>
												<td style="width: 7%; text-align: center;"><label>
														<a
														href="javascript:agregarObservacion('<c:out value="${cuentaCorrienteBean.cuentaCorrienteID  }" ></c:out>','<c:out value="${proyecto.datoProyectoID }" ></c:out>','2','1','<c:out value="${idProyectoForm}" ></c:out>')"
														class="linkSelecciona">Observacion</a></label>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<br>
							</div>
							<div id="ubicacionProyecto">
								<br />
								<table class="table-clasico" width="100%">
									<caption>
										<label>Ubicaciones del Proyecto</label>
									</caption>
									<thead>
										<tr>
											<td style="width: 2%; text-align: center;"><label>Ben.</label>
											</td>
											<td style="width: 15%; text-align: center;"><label>Dep.</label>
											</td>
											<td style="width: 15%; text-align: center;"><label>Prov.</label>
											</td>
											<td style="width: 15%; text-align: center;"><label>Dist.</label>
											</td>
											<td style="width: 40%; text-align: center;"><label>Localización</label>
											</td>
											<td style="width: 8%; text-align: center;"><label>Opciones</label>
											</td>
										</tr>
									</thead>
									<tbody id="tbodyUbiProy">
										<c:forEach items="${listUbicacionProyecto }"
											var="ubicacionProyecto" varStatus="indice">
											<c:choose>
												<c:when test="${indice.count %2== 0}">
													<c:set var="classIdi" value="f2"></c:set>
												</c:when>
												<c:otherwise>
													<c:set var="classIdi" value="f1"></c:set>
												</c:otherwise>
											</c:choose>
											<tr class="<c:out value="${classIdi}"></c:out>">
												<td style="width: 2%; text-align: center;"><label><a
														href="javascript:expandcollapse('divUP<c:out value="${ubicacionProyecto.ubicacionProyectoID }"></c:out>', 'one');">
															<img
															id='imgdivUP<c:out value="${ubicacionProyecto.ubicacionProyectoID }"></c:out>'
															border="0" src="<c:url value="/images/Plus001.gif" />"
															width="15px" /> </a>
												</label></td>
												<td style="width: 15%; text-align: center;"><label><c:out
															value="${ubicacionProyecto.descripcionDepartamento }"></c:out>
												</label></td>
												<td style="width: 15%; text-align: center;"><label><c:out
															value="${ubicacionProyecto.descripcionProvincia }"></c:out>
												</label></td>
												<td style="width: 15%; text-align: center;"><label><c:out
															value="${ubicacionProyecto.descripcionDistrito }"></c:out>
												</label></td>
												<td style="width: 40%; text-align: center;"><label><c:out
															value="${ubicacionProyecto.detalleUbicacion }"></c:out>
												</label></td>
												<td style="width: 8%; text-align: center;"><label><a
														href="javascript:agregarObservacion('<c:out value="${ubicacionProyecto.ubicacionProyectoID  }" ></c:out>','<c:out value="${proyecto.datoProyectoID }" ></c:out>','2','3','<c:out value="${idProyectoForm}" ></c:out>')"
														class="linkSelecciona">Observacion</a>
												</label></td>
											</tr>
											<tr class="<c:out value="${classIdi}"></c:out>">
												<td colspan="100%">
													<div
														id='divUP<c:out value="${ubicacionProyecto.ubicacionProyectoID }"></c:out>'
														style="display: none; position: relative; overflow: auto; padding-left: 15px; padding-bottom: 15px; width: 97%;">
														<table class="table-clasico" width="100%">
															<caption>
																<label>Beneficiarios</label>
															</caption>
															<thead>
																<tr>
																	<td style="width: 15%; text-align: center;"><label>Beneficiarios<br />Estrato</label>
																	</td>
																	<td style="width: 10%; text-align: center;"><label>Tipo
																			Ben.</label>
																	</td>
																	<td style="width: 32%; text-align: center;"><label>Carac.
																			Poblacion</label>
																	</td>
																	<td style="width: 33%; text-align: center;"><label>Descripción</label>
																	</td>
																	<td style="width: 5%; text-align: center;"><label>Opciones</label>
																	</td>
																</tr>
															</thead>
															<tbody id="tbodyBeneficiarios">
																<c:forEach
																	items="${ubicacionProyecto.listBeneficiariosPorResultado }"
																	var="beneficiariosPorResultado" varStatus="indice">
																	<c:choose>
																		<c:when test="${indice.count %2== 0}">
																			<c:set var="classIdiInt" value="f2int"></c:set>
																		</c:when>
																		<c:otherwise>
																			<c:set var="classIdiInt" value="f1int"></c:set>
																		</c:otherwise>
																	</c:choose>
																	<tr class="<c:out value="${classIdiInt}"></c:out>">
																		<td style="width: 15%; text-align: center;"><label><c:out
																					value="${beneficiariosPorResultado.cantidadProgramado }"></c:out>
																				<c:out
																					value="${beneficiariosPorResultado.descripcionEstrato }"></c:out>
																		</label></td>
																		<td style="width: 10%; text-align: center;"><label><c:out
																					value="${beneficiariosPorResultado.descripcionTipoBeneficiario }"></c:out>
																		</label>
																		</td>
																		<td style="width: 32%; text-align: center;"><label><c:out
																					value="${beneficiariosPorResultado.caracteristicasPoblacion }"></c:out>
																		</label>
																		</td>
																		<td style="width: 33%; text-align: center;"><label><c:out
																					value="${beneficiariosPorResultado.descripcion }"></c:out>
																		</label></td>
																		<td style="width: 5%; text-align: center;"><label>
																				<a
														href="javascript:agregarObservacion('<c:out value="${beneficiariosPorResultado.beneficiariosPorResultadoID  }" ></c:out>','<c:out value="${proyecto.datoProyectoID }" ></c:out>','2','2','<c:out value="${idProyectoForm}" ></c:out>')"
														class="linkSelecciona">Observacion</a>
																		</label>
																		</td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div id="financiamiento">
								<br />
								<fieldset>
									<table class="table-clasico" width="100%">
										<caption>
											<label>Lista de Instituciones</label>
										</caption>
										<thead>
											<tr>
												<td style="width: 2%; text-align: center;"><label>Q</label>
												</td>
												<td style="width: 13%; text-align: center;"><label>RUC.</label>
												</td>
												<td style="width: 10%; text-align: center;"><label>Inst.</label>
												</td>
												<td style="width: 15%; text-align: center;"><label>Dir.</label>
												</td>
												<td style="width: 10%; text-align: center;"><label>Tfno.</label>
												</td>
												<td style="width: 15%; text-align: center;"><label>Rep.
														Legal</label></td>
												<td style="width: 15%; text-align: center;"><label>Contacto</label>
												</td>
												<td style="width: 10%; text-align: center;"><label>Correo</label>
												</td>
												<td style="width: 10%; text-align: center;"><label>Observ.</label>
												</td>
											</tr>
										</thead>
										<tbody id="tbodyInstitucion">
											<c:forEach items="${listFuenteFinanciamiento }"
												var="fuenteFinanciamiento" varStatus="indice">
												<c:choose>
													<c:when test="${indice.count %2== 0}">
														<c:set var="classIdi" value="f2"></c:set>
													</c:when>
													<c:otherwise>
														<c:set var="classIdi" value="f1"></c:set>
													</c:otherwise>
												</c:choose>
												<tr class="<c:out value="${classIdi}"></c:out>">
													<td style="width: 2%; text-align: center;"><label><a
															href="javascript:expandcollapse('divFF<c:out value="${fuenteFinanciamiento.fuenteFinanciadoraID }"></c:out>', 'one');">
																<img
																id='imgdivFF<c:out value="${fuenteFinanciamiento.fuenteFinanciadoraID }"></c:out>'
																border="0" src="<c:url value="/images/Plus001.gif" />"
																width="15px" /> </a>
													</label></td>
													<td style="width: 13%; text-align: center;"><label><c:out
																value="${fuenteFinanciamiento.institucion.rucInstitucion }"></c:out>
													</label></td>
													<td style="width: 10%; text-align: center;"><label><c:out
																value="${fuenteFinanciamiento.institucion.nombreInstitucion }"></c:out>
													</label></td>
													<td style="width: 15%; text-align: center;"><label><c:out
																value="${fuenteFinanciamiento.institucion.direccion }"></c:out>
													</label></td>
													<td style="width: 10%; text-align: center;"><label><c:out
																value="${fuenteFinanciamiento.institucion.telefono }"></c:out>
													</label></td>
													<td style="width: 15%; text-align: center;"><label><c:out
																value="${fuenteFinanciamiento.institucion.representanteLegal }"></c:out>
													</label></td>
													<td style="width: 15%; text-align: center;"><label><c:out
																value="${fuenteFinanciamiento.institucion.contacto }"></c:out>
													</label></td>
													<td style="width: 10%; text-align: center;"><label><c:out
																value="${fuenteFinanciamiento.institucion.correo }"></c:out>
													</label></td>
													<td style="width: 10%; text-align: center;"><label><c:out
																value="${fuenteFinanciamiento.institucion.observacionDeInstitucion }"></c:out>
													</label></td>
												</tr>
												<tr class="<c:out value="${classIdi}"></c:out>">
													<td colspan="100%">
														<div
															id='divFF<c:out value="${fuenteFinanciamiento.fuenteFinanciadoraID }"></c:out>'
															style="display: none; position: relative; overflow: auto; padding-left: 15px; padding-bottom: 15px; width: 97%;">
															<table class="table-clasico" width="100%">
																<caption>
																	<label>Montos</label>
																</caption>
																<thead>
																	<tr>
																		<td style="width: 15%; text-align: center;"><label>Monto<br />Financiado</label>
																		</td>
																		<td style="width: 10%; text-align: center;"><label>Fuente<br />Financiadora</label>
																		</td>
																		<td style="width: 5%; text-align: center;"><label>Opciones</label>
																		</td>
																	</tr>
																</thead>
																<tbody id="tbodyBeneficiarios">

																	<tr class="<c:out value="${classIdi}"></c:out>">
																		<td style="width: 15%; text-align: center;"><label><c:out
																					value="${fuenteFinanciamiento.montoFinanciado }"></c:out>
																				<c:out
																					value="${fuenteFinanciamiento.descripcionTipoMoneda }"></c:out>
																		</label></td>
																		<td style="width: 10%; text-align: center;"><label><c:out
																					value="${fuenteFinanciamiento.descripcionTipoFuenteFinanciadora }"></c:out>
																		</label>
																		</td>
																		<td style="width: 5%; text-align: center;"><label>
																				<a
														href="javascript:agregarObservacion('<c:out value="${fuenteFinanciamiento.fuenteFinanciadoraID  }" ></c:out>','<c:out value="${proyecto.datoProyectoID }" ></c:out>','2','4','<c:out value="${idProyectoForm}" ></c:out>')"
														class="linkSelecciona">Observacion</a>
																		</label>
																		</td>
																	</tr>
																</tbody>
															</table>
														</div></td>
												</tr>
											</c:forEach>
										</tbody>
										<tfoot>
											<tr>
												<td colspan="5" style="width: 50%; text-align: center;"></td>
												<td colspan="4" style="width: 50%; text-align: right;"><label>Institucion
														Ejecutora: <c:forEach items="${listFuenteFinanciamiento }"
															var="fuenteFinanciamiento" varStatus="indice">
															<c:if
																test="${fuenteFinanciamiento.defineSiEsEjecutor =='1' }">
																<c:out
																	value="  ${fuenteFinanciamiento.institucion.nombreInstitucion }"></c:out>
															</c:if>
														</c:forEach>
												</label></td>
											</tr>
										</tfoot>
									</table>
								</fieldset>
							</div>
							<div id="resumenProyecto">
								<br />
								<table class="table-clasico" width="100%">
									<!-- class="table-clasico"-->
									<caption>
										<label>Lista de Resumenes y Observaciones</label>
									</caption>
									<thead>
										<tr>
											<td style="width: 3%; text-align: center;"><label>Nro</label>
											</td>
											<td style="width: 20%; text-align: center;"><label>Tipo.</label>
											</td>
											<td style="width: 72%; text-align: center;"><label>Resumen</label>
											</td>
											<td style="width: 5%; text-align: center;" class="hide"><label>Opciones</label>
											</td>
										</tr>
									</thead>
									<tbody id="tbodyResumenObserv">

										<c:forEach items="${listResumenProyectoBean }"
											var="resumenProyectoBean" varStatus="indice">
											<c:choose>
												<c:when test="${indice.count %2== 0}">
													<c:set var="classIdi" value="f2"></c:set>
												</c:when>
												<c:otherwise>
													<c:set var="classIdi" value="f1"></c:set>
												</c:otherwise>
											</c:choose>
											<tr class="<c:out value="${classIdi}"></c:out>">
												<td style="width: 3%; text-align: center;"><label><c:out
															value="${indice.count }"></c:out> </label></td>
												<td style="width: 20%; text-align: left;"><label><c:out
															value="${resumenProyectoBean.descripcionTipoResumenProy }"></c:out>
												</label></td>
												<td style="width: 72%; text-align: left;"><label><c:out
															value="${resumenProyectoBean.definicion }"></c:out> </label></td>

												<td style="width: 10%; text-align: center;" class="hide"><label>
														<a
														href="javascript:agregarObservacion('<c:out value="${resumenProyectoBean.resumenProyectoID  }" ></c:out>','<c:out value="${proyecto.datoProyectoID }" ></c:out>','2','5','<c:out value="${idProyectoForm}" ></c:out>')"
														class="linkSelecciona">Observacion</a></label>
											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>
							<div id="documento">
						<br />
						<fieldset>
							<table width="100%">
								<tr>
									<td style="text-align: right; width: 20%;"><label>Documento:</label>
									</td>
									<td><iframe src="showImagenArchivoDownloadProyecto.jspx?datoProyectoId=${idProyectoForm}"
											id="window" frameborder="0" height="40px" width="100%"
											scrolling="no"></iframe>
									</td>
								</tr>
							</table>
						</fieldset>
					</div>
						</div>
					</div>
				</fieldset>
			</div>
		</form:form>
	</div>