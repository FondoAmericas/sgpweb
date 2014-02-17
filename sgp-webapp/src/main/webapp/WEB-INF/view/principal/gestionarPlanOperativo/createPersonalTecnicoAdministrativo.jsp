<%@include file="includesTaglibs.jsp"%>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util.js"></script>

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
			//alert($('#cbxInstitucionEjecutora option:selected').html());
			//$("#div_cbxInstitucionEjecutora").attr("style","visibility: hidden;");
		}
		
	}
	</script>

<script type="text/javascript">
    function submitFormPersonalTA() {
	  var valida = validar();

	  if(valida==0){
	  if(confirm("\u00BFEst\u00E1 seguro de guardar los datos ingresados?")){
	     document.formPersonalTA.action = "actionSavePersonalTecnicoAdministrativo.jspx";
	     document.formPersonalTA.submit();
	  }
	  }
    }
    
    function modificarPersonal(personalTecnicoAdministrativoID,nombreCompleto,fkIdtablaespFormacionProfesional,responsabilidad,
    							fkIdtablaespTiempoDedicado, porcentageParticipacion,fkIdtablaespEtapaPersonalTecnico,
    							fkIdpersonalTecnicoAdmReemplazo,salarioMensual ,fkIdtablaespTipoMoneda ,institucionID ){
    
    	var confirma = confirm("Esta seguro que desea cargar el registro para modificarlo?");
    	
    	if(confirma==true){
    	$('#personalTecnicoAdministrativoID').attr("value",personalTecnicoAdministrativoID);
    	   $('#nombreCompleto').attr("value",nombreCompleto);
    	   $('#sltFormacionProfesional').attr("value",fkIdtablaespFormacionProfesional);
    	   $('#sltTiempoDedicado').attr("value",fkIdtablaespTiempoDedicado);
    	   $('#responsabilidad').attr("value",responsabilidad);
    	   $('#porcentajeParticipacion').attr("value",porcentageParticipacion);
    	   $('#sltEtapaPersonal').attr("value",fkIdtablaespEtapaPersonalTecnico);
    	   $('#salario').attr("value",salarioMensual);
    	   $('#sltTipoMoneda').attr("value",fkIdtablaespTipoMoneda);
    	   $('#sltInstitucion').attr("value",institucionID);
    	   $('#sltPersonalTecnicoReemplazado').attr("value",fkIdpersonalTecnicoAdmReemplazo);
    	}
    }
    
    function eliminarPersonal(personalTecnicoAdministrativoID){
    	var confirma= confirm("Esta seguro que desea eliminar el registro?");
    	$('#personalTecnicoAdministrativoID').attr("value",personalTecnicoAdministrativoID);
		if(confirma==true){
			 document.formPersonalTA.action = "eliminarPersonal.jspx";
		     document.formPersonalTA.submit();
			/*$.get("eliminarPersonal.jspx", {
				personalTecnicoAdministrativoID :personalTecnicoAdministrativoID,
				datoPlanOperativoID : $("#datoPlanOperativoID").val()
			}, function() {
				fRefresh(100);
			});*/
		}
    }
/*
    function fRefresh(tiempo){
		fSetTimeOutRefreshLocation(tiempo);
		//location.reload();
	}*/
    
	function soloNumero(e) {
	    if ([e.keyCode||e.which]==8 || [e.keyCode||e.which]==9 || [e.keyCode||e.which]==46) 
	        return true;
	    if ([e.keyCode||e.which] < 48 || [e.keyCode||e.which] > 57)
	        e.preventDefault? e.preventDefault() : e.returnValue = false;
	}
  
	function validar(){
	      var errores = 0;
	      
	      var mensaje = "";
	      if($('#nombreCompleto').attr("value") == ""){
	          mensaje += "\n Ingresar Nombre.";
	      		errores +=1;
	      }
	      
		  if($('#sltFormacionProfesional').attr("value") == "0"){
		      mensaje += "\n Seleccionar Formacion Profesional.";
		      errores +=1;
		  }
		  
		  if($('#sltTiempoDedicado').attr("value") == "0"){
		      mensaje += "\n Seleccionar Tiempo Dedicado.";
		      errores +=1;
		  }
		  
		  if($('#responsabilidad').attr("value") == ""){
		      mensaje += "\n Ingresar Responsabilidad.";
		      errores +=1;
		  }
		  
		  if($('#porcentajeParticipacion').attr("value") == ""){
		      mensaje += "\n Porcentaje Porcentaje de Participacion.";
		      errores +=1;
		  }
		  
		  if($('#sltEtapaPersonal').attr("value") == "0"){
		      mensaje += "\n Seleccionar Etapa de Participacion.";
		      errores +=1;
		  }
		  
		  if($('#salario').attr("value") == ""){
	             mensaje += "\n Ingresar Salario.";
	             errores +=1;
	      }
		  
		  if($('#sltTipoMoneda').attr("value") == "0"){
		      mensaje += "\n Seleccionar Tipo de Moneda.";
		      errores +=1;
		  }
		   
	      if($('#sltInstitucion').attr("value") == "0"){
	             mensaje += "\n Seleccionar Institucion.";
	             errores +=1;
	      }
	     
	      if(errores > 0 ){
	         alert(mensaje);
	       }	   
	      return errores;
	}
</script>
</head>
<body>
	<div class="form-clasico">
	<div class="encabezado">PERSONAL TECNICO ADMINISTRATIVO</div>
		<br />
	<form:form name="formPersonalTA" method="post" action="">
		<fieldset style="padding-left: 10px">
			<legend>Crear Personal Tecnico Administrativo</legend>
			<div class="hide" >
			<input type="hidden" id="datoPlanOperativoID" name="datoPlanOperativoID" value="${datoPlanOperativoID}">
			<input type="hidden" id="personalTecnicoAdministrativoID" name="personalTecnicoAdministrativoID" > 
			
			<table width="100%">
			<tr>
			   <td style="width: 25%; text-align: right;"><label>Nombre Completo:</label></td>
			   <td colspan="3" style="width: 75%; text-align: left;">
				   <input type="text" id="nombreCompleto" name="nombreCompleto" style="width: 99%;">
			   </td>
			</tr>
			<tr>
			   <td style="width: 25%; text-align: right;"><label>Formacion Profesional: </label></td>
			   <td style="width: 25%; text-align: left;">
			   <select name="sltFormacionProfesional" id="sltFormacionProfesional" style="width: 100%;" >
				<option value="0"><c:out value="-- Formacion --" /></option>
				<c:forEach items="${listFormacionProfesional }" var="formacionProfesional">
						<option value="${formacionProfesional.tablaEspecificaID }" ><c:out
							value="${formacionProfesional.descripcionCabecera }" /></option>
				</c:forEach>
			    </select>
			    </td>
				<td style="width: 25%; text-align: right;"><label>Tiempo Dedicado: </label></td>
				<td style="width: 25%; text-align: left;">
				<select name="sltTiempoDedicado" id="sltTiempoDedicado"  style="width: 98%;">
				<option value="0"><c:out value="-- Formacion --" /></option>
				<c:forEach items="${listTiempoDedicado }" var="tiempoDedicado">
						<option value="${tiempoDedicado.tablaEspecificaID }" ><c:out
							value="${tiempoDedicado.descripcionCabecera }" /></option>
				</c:forEach>
			    </select>
				</td>
			</tr>
			<tr>
				<td style="width: 25%; text-align: right; vertical-align: top;"><label>Responsabilidad:</label></td>
				<td style="width: 25%; text-align: left;">
				 <textarea id="responsabilidad" name="responsabilidad" rows="4" cols="5" style="width: 98%;"></textarea>
				</td>
				<td style="width: 25%; text-align: right; vertical-align: top;"><label>Porcentaje Participacion:</label></td>
				<td style="width: 25%; text-align: left; vertical-align: top;">
				  <input type="text" id="porcentajeParticipacion" name="porcentajeParticipacion" onkeypress="soloNumero(event)" maxlength="3" style="width: 35px" > <label>%</label>
				</td>
			</tr>
			<tr>
				<td style="width: 25%; text-align: right;"><label>Etapa de Personal:</label></td>
				<td style="width: 25%; text-align: left;">	
				<select name="sltEtapaPersonal" id="sltEtapaPersonal"  style="width: 100%;">
				<option value="0"><c:out value="-- Etapa --" /></option>
				<c:forEach items="${listEtapaPersonal }" var="etapaPersonal">
						<option value="${etapaPersonal.tablaEspecificaID }" ><c:out
							value="${etapaPersonal.descripcionCabecera }" /></option>
				</c:forEach>
			    </select>
			    </td>
				<td style="width: 25%; text-align: right;" ><label>Personal Tecnico Reemplazado:</label></td>
				<td style="width: 25%; text-align: left;">
				<select name="sltPersonalTecnicoReemplazado" id="sltPersonalTecnicoReemplazado"  style="width: 98%;">
				 <option value="0"><c:out value="-- Personal Tecnico --" /></option>
				 <c:forEach items="${listPersonalTecnicoReemplazado }" var="personalTecnicoReemplazado">
					  <option value="${personalTecnicoReemplazado.personalTecnicoAdministrativoID }" ><c:out
							value="${personalTecnicoReemplazado.nombreCompleto }" /></option>
			     </c:forEach>
			     </select>
				 </td>
			</tr>
			<tr>
				<td style="width: 25%; text-align: right;  vertical-align: top;"><label>Salario Mensual:</label></td>
				<td colspan="3" style="width: 75%; text-align: left;  vertical-align: top;">
				 <input type="text" id="salario" name="salario" style="width: 80px;" onkeypress="soloNumero(event)" maxlength="5">
				 <select name="sltTipoMoneda" id="sltTipoMoneda" style="width: 100px;">
				 <option value="0"><c:out value="-- Moneda --" /></option>
				 <c:forEach items="${listTipoMoneda }" var="tipoMoneda">
						<option value="${tipoMoneda.tablaEspecificaID }" ><c:out
							value="${tipoMoneda.descripcionCabecera }" /></option>
				 </c:forEach>
			     </select>
				</td>
			</tr>
			<tr>
				<td style="width: 25%; text-align: right;  vertical-align: top;"><label>Institucion:</label></td>
				<td colspan="3" style="width: 75%; text-align: left;  vertical-align: top;">
				<select name="sltInstitucion" id="sltInstitucion" style="width: 99%;">
				<option value="0"><c:out value="-- Institucion --" /></option>
				<c:forEach items="${listInstitucion }" var="institucion">
						<option value="${institucion.institucion.institucionID}" ><c:out
							value="${institucion.institucion.nombreInstitucion}" /></option>
				</c:forEach>
			    </select>
				</td>
			</tr>
			<tr>
			   <td colspan="5" style="width: 50%;">&nbsp;</td>
			</tr>
			<tr>
					<td colspan="5" style="width: 50%; text-align: right;">
					<input type="button" id="btnPersonaTA" name="btnPersonaTA" onclick="submitFormPersonalTA()" value="Grabar Persona Tecnico Administrativo">					
					<input type="button" value="Cerrar" id="idBtnCerrar" />
					</td>
			</tr>

			</table>
	
	<br>
	</div>
    <form>
		<table class="table-clasico" style="width: 100%">
		<caption>Personal Tecnico Administrativo</caption>
		<thead>
        <tr>
<!--         	<th align="center"><label></label></th> -->
			<th align="center"><label>Nombre Completo</label></th>
			<th align="center"><label>Formacio Profesional</label></th>
			<th align="center"><label>Tiempo Dedicado</label></th>
			<th align="center"><label>Responsabilidad</label></th>
            <th align="center"><label>Porcentaje Participacion</label></th>
            <th align="center"><label>Etapa de Personal</label></th>
            <th align="center"><label>Personal Tecnico Reemplazado</label></th>
            <th align="center"><label>Salario Mensual</label></th>
            <th align="center"><label>Institucion</label></th>
            <th align="center" class="hide" ><label>Opciones</label></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="personalTA" items="${listPersonalTecnicoReemplazado}" varStatus="indice">
			<c:choose>
				<c:when test="${indice.count %2== 0}">
					<c:set var="classIdi" value="f2"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="classIdi" value="f1"></c:set>
				</c:otherwise>
			</c:choose>
			<tr class="<c:out value="${classIdi}"></c:out>">
<!-- 	        	<td align="center"><input type="radio" name="costoActividadId" value=""></td> -->
	        	<td align="center"><c:out value="${personalTA.nombreCompleto}" /></td>
	        	<td align="center"><c:out value="${personalTA.formacionProfesionalNombre}" /></td>
	        	<td align="center"><c:out value="${personalTA.tiempoDedicadoNombre}" /></td>
	        	<td align="center"><c:out value="${personalTA.responsabilidad}" /></td>
	        	<td align="center"><c:out value="${personalTA.porcentageParticipacion}" /> %</td>
	        	<td align="center"><c:out value="${personalTA.etapaPersonalTecnicoNombre}" /></td>
	        	<td align="center"><c:out value="${personalTA.personalTecnicoAdmReemplazoNombre}" /></td>
	        	<td align="center"><c:out value="${personalTA.salarioMensual}" /> <c:out value="${personalTA.tipoMonedaNombre}" /></td>
	        	<td align="center"><c:out value="${personalTA.institucion.nombreInstitucion}" /></td>
	        	<td align="center" class="hide"><label><a
						href="javascript:modificarPersonal('<c:out value="${personalTA.personalTecnicoAdministrativoID }"></c:out>',
																'<c:out value="${personalTA.nombreCompleto }"></c:out>',
																'<c:out value="${personalTA.fkIdtablaespFormacionProfesional }"></c:out>',
																'<c:out value="${personalTA.responsabilidad }"></c:out>',
																'<c:out value="${personalTA.fkIdtablaespTiempoDedicado }"></c:out>',
																'<c:out value="${personalTA.porcentageParticipacion }"></c:out>',
																'<c:out value="${personalTA.fkIdtablaespEtapaPersonalTecnico }"></c:out>',
																'<c:out value="${personalTA.fkIdpersonalTecnicoAdmReemplazo }"></c:out>',
																'<c:out value="${personalTA.salarioMensual }"></c:out>',
																'<c:out value="${personalTA.fkIdtablaespTipoMoneda }"></c:out>',
																'<c:out value="${personalTA.institucion.institucionID }"></c:out>')"
						id="modificarPersonal" class="linkSelecciona">Modificar</a><br>
			<a
						href="javascript:eliminarPersonal('<c:out value="${personalTA.personalTecnicoAdministrativoID }"></c:out>')"
						id="eliminarPersonal" class="linkSelecciona">Eliminar</a></label></td>
	        </tr>
        </c:forEach>
        </tbody>
        </table>
     </form>
	</fieldset>
	</form:form>
</div>
</body>
</html>

