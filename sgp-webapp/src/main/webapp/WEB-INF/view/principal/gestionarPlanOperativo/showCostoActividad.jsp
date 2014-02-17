<%@include file="includesTaglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="web.app.title"></spring:message>
</title>
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
	$(document).ready(function() {
		 if($('#mensaje').attr("value") != '' && $('#mensaje').attr("value") != undefined){
		        alert($('#mensaje').attr("value"));
		     }
		 $("#rubroGenerico").hide();
		});
	
	function fEliminar(costoActividadID, detallePartidaGenerica){
		if(confirm("\u00BFEst\u00E1 seguro de eliminar el costo actividad \"" + detallePartidaGenerica + "\" ?")){
			var datoPlanOperativoID = $("#datoPlanOperativoID").val();
			var resultadoID = $("#resultadoID").val();
			var actividadID = $("#actividadID").val();
		    window.location.href = "actionDeleteCostoActividad.jspx?costoActividadID=" + costoActividadID 
			 	                 + "&datoPlanOperativoID=" + datoPlanOperativoID
				                 + "&resultadoID=" + resultadoID
				                 + "&actividadID=" + actividadID;
		}			
	}
	
	function modificarMetaActividad(costoActividadID ,categoriaActividadID ,partidaGenericaID ,partidaEspecificaID ,detallePartidaGenerica ,
									cantidadTotal ,unidadMedidaId ,precioUnitario,tipoMonedaPrecioUnitarioId,cantTotalCronogramaCostoActividad,
									cabeceraRubroGenericoID ,rubroGenericoID){
		
		var confirma = confirm("Esta seguro que desea cargar el registro para modificarlo?");
		
		if(confirma==true){
		$("#costoActividadID").attr("value",costoActividadID);
		$("#categoriaActividadID").attr("value",categoriaActividadID);
		onChangeCategoriaActividad(partidaGenericaID,partidaEspecificaID,cabeceraRubroGenericoID ,rubroGenericoID);
		
		$("#detallePartidaGenerica").attr("value",detallePartidaGenerica);
		$("#cantidadTotal").attr("value",cantidadTotal);
		$("#unidadMedidaId").attr("value",unidadMedidaId);
		$("#precioUnitario").attr("value",precioUnitario);
		$("#tipoMonedaId_").attr("value",tipoMonedaPrecioUnitarioId);
		$("#cantTotalCronogramaCostoActividad").attr("value",cantTotalCronogramaCostoActividad);
	}
	}
			
		function soloNumero(e) {
		    if ([e.keyCode||e.which]==8 || [e.keyCode||e.which]==9 || [e.keyCode||e.which]==46) 
		        return true;
		    if ([e.keyCode||e.which] < 48 || [e.keyCode||e.which] > 57)
		        e.preventDefault? e.preventDefault() : e.returnValue = false;
		}
		</script>
		
		<script type="text/javascript">	
		function onChangeCategoriaActividad(partidaGenericaID,partidaEspecificaID,cabeceraRubroGenericoID ,rubroGenericoID){
		    /*var selectedIndex = document.formCostoActividad.categoriaActividadID.selectedIndex;
		    var optionValue = document.formCostoActividad.categoriaActividadID.options[selectedIndex].value;
		    var categoriaActividadID = optionValue;*/
		    
			//var url = "showCostoActividad.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID}&actividadID=${actividad.actividadID}&categoriaActividadID="+$("#categoriaActividadID").val();//categoriaActividadID
			//goTo2(url);
			if($("#categoriaActividadID").val()==3){
				$("#cabeceraRubroGenericoID").load("cargarComboPlanOperativo.jspx", {categoriaActividadID:$("#categoriaActividadID").val(),metodo:"rubroGenerico"},
						function(){
					if(cabeceraRubroGenericoID!=0){
						$("#cabeceraRubroGenericoID").attr("value",cabeceraRubroGenericoID);
						onChangeRubroGenerico(rubroGenericoID);	
					}else{
						onChangeRubroGenerico(0);	
					}
					});
				$("#rubroGenerico").show("slow");
				
			}else{
				$("#rubroGenerico").hide("slow");
			}
			
			$("#partidaGenericaID").load("cargarComboPlanOperativo.jspx", {categoriaActividadID:$("#categoriaActividadID").val(),metodo:"partidaGenerica"},
				function(){
				if(partidaGenericaID!=0){
					$("#partidaGenericaID").attr("value",partidaGenericaID);
					onChangePartidaGenerica(partidaEspecificaID);
				}else{
					onChangePartidaGenerica(0);
				}
				});
			
			
		}
		
		function onChangePartidaGenerica(partidaEspecificaID){
			$("#partidaEspecificaID").load("cargarComboPlanOperativo.jspx", {partidaGenericaID:$("#partidaGenericaID").val(),metodo:"partidaEspecifica"},function(){
				if(partidaEspecificaID!=0){
					$("#partidaEspecificaID").attr("value",partidaEspecificaID);
				}
			});
			
		}
		
		function onChangeRubroGenerico(rubroGenericoID){
			$("#rubroGenericoID").load("cargarComboPlanOperativo.jspx", {categoriaActividadID:$("#categoriaActividadID").val(),cabeceraRubroGenericoID:$("#cabeceraRubroGenericoID").val(),metodo:"rubroEspecifico"},
					function(){
				if(rubroGenericoID!=0){
					$("#rubroGenericoID").attr("value",rubroGenericoID);
				}
			});
		}
				
		function submitFormCostoActividad() {
			if (validar()) {
				return;
			}
			if(confirm("\u00BFEst\u00E1 seguro de guardar los datos ingresados?")){
			   document.formCostoActividad.action = "actionSaveCostoActividad.jspx";
			   document.formCostoActividad.submit();
			}
		}
		
		function goTo(url) {
			//window.location = url;
			fOpenModalDialog(url,'1000','530','70','70');
		}
		function goTo2(url) {
			window.location = url;
		}
		function goBack(url) {
			goTo(url);
		}
		</script>
		
		<script type="text/javascript">
		function validar(){
		      //var cabecera = "Debe ingresar la siguiente informaci\u00F3n obligatoria(*):";
		      var mensaje = "";
		      if($('#categoriaActividadID').attr("value") == "0"){
		          mensaje += "\n Seleccionar Categoria.";
		      }
		      
				if($('#categoriaActividadID').attr("value") == "3"){
					if($('#cabeceraRubroGenericoID').attr("value") != undefined && $('#cabeceraRubroGenericoID').attr("value") == "0"){ 
				        mensaje += "\n Seleccionar Rubro Generico.";
				    }
				    if($('#rubroGenericoID').attr("value") != undefined && $('#rubroGenericoID').attr("value") == "0"){ 
						          mensaje += "\n Seleccionar Rubro Especifico.";
						      }
				}
		      
			  if($('#partidaGenericaID').attr("value") == "0"){
			      mensaje += "\n Seleccionar Partida Generica.";
			  }
			  
			  if($('#partidaEspecificaID').attr("value") == "0"){
			      mensaje += "\n Seleccionar Partida Especifica.";
			  }
			  
			  if($('#detallePartidaGenerica').attr("value") == ""){
			      mensaje += "\n Ingresar Detalle Partida.";
			  }
			  var cantidadTotal = parseFloat($('#cantidadTotal').attr("value"));
			  if($('#cantidadTotal').attr("value") == ""){
			      mensaje += "\n Ingresar Cantidad.";
			  }else if(cantidadTotal == 0){
				  mensaje += "\n La cantidad debe de ser mayor a 0.";
			  }
			  
			  var cantTotalCronogramaCostoActividad = parseInt($("#cantTotalCronogramaCostoActividad").val());
			  var cantidadTotal =  parseInt($("#cantidadTotal").val());
			  //alert("Total Cronograma="+cantTotalCronogramaCostoActividad+" Total="+cantidadTotal);
			  if(cantTotalCronogramaCostoActividad > cantidadTotal){
			      mensaje += "\n La cantidad ingresada es menor a la cantidad programada en el cronograma.";
			  }
			  
			  if($('#unidadMedidaId').attr("value") == "0"){
			      mensaje += "\n Seleccionar Unidad de Medida.";
			  }
			  
			  if($('#tipoMonedaId_').attr("value") == "0"){
			      mensaje += "\n Seleccionar Moneda.";
			  }
			  
			  var precioUnitario = parseFloat($('#precioUnitario').attr("value"));
			  if($('#precioUnitario').attr("value") == ""){
			      mensaje += "\n Ingresar Precio Unitario.";
			  }else if(precioUnitario == 0){
				  mensaje += "\n EL precio unitario debe de ser mayor a 0.";
			  }
			  
			  if($('#precioUnitario').attr("value") != "" && $('#cantidadTotal').attr("value") != ""){
				 
				  var costoTotal = parseFloat($('#costoTotal').attr("value")).toFixed(2);
				  var financiamientoTotal = parseFloat($('#financiamientoTotal').attr("value")).toFixed(2);
				  var total = precioUnitario*cantidadTotal;
				  var totalFinal = parseFloat(total) + parseFloat(costoTotal);
				  if(totalFinal > financiamientoTotal){
				      mensaje += "\n El precio ingresado supera el financiamiento.";
				  }				  
			  }			  
		     
		      if(mensaje != ""){
		         alert(mensaje);
		         return true;
		      }else{
		         return false;
		      }
		   
		}
		
	</script>

</head>
<body>
	<div class="form-clasico">
	<div class="encabezado">Costos por Actividad</div>
		<br>
		<fieldset>
			<legend>Crear Costo por Actividad</legend>

			<div class="form-clasico">
				<c:if test="${!empty alterMessage}">
					<fieldset>
						<legend>Mensajes de Alertas</legend>
						<label style="color: red;" for="alterMessage">${alterMessage}</label>
					</fieldset>
				</c:if>
				<form:form name="formCostoActividad" method="post" action="">
					<input type="hidden" id="datoPlanOperativoID"
						name="datoPlanOperativoID"
						value="${planOperativo.datoPlanOperativoID}">
					<input type="hidden" id="resultadoID" name="resultadoID"
						value="${resultado.resultadoID}" />
					<input type="hidden" id="actividadID" name="actividadID"
						value="${actividad.actividadID}" />
					<input type="hidden" id="tipoMonedaId" name="tipoMonedaId"
						value="${planOperativo.idTipoMoneda}" />
					<input type="hidden" id="costoTotal" name="costoTotal"
						value="${costoTotal}" />
					<input type="hidden" id="financiamientoTotal"
						name="financiamientoTotal" value="${financiamientoTotal}" />
					<input type="hidden" id="mensaje" name="mensaje" value="${mensaje}">
					<input type="hidden" id="costoActividadID" name="costoActividadID">
					<input type="hidden" id="cantTotalCronogramaCostoActividad" name="cantTotalCronogramaCostoActividad" value="0">
					<table border="0" width="100%">
						<tr>
							<td style="text-align: right; width: 20%;"><label>Proyecto:</label>
							</td>
							<td style="text-align: left; width: 80%;" colspan="3"><label
								for="proyectoNombre">${planOperativo.nombreProyecto}</label></td>
						</tr>
						<tr>
							<td style="text-align: right; width: 20%;"><label>Codigo
									Proyecto:</label>
							</td>
							<td style="text-align: left; width: 30%;"><label
								for="proyectoCodigo">${planOperativo.codigoProyecto}</label></td>
							<td style="text-align: right; width: 20%;"><label>Version
									PO:</label>
							</td>
							<td style="text-align: left; width: 30%;"><label
								for="planOperativo">${planOperativo.version}</label></td>
						</tr>
						<tr>
							<td style="text-align: right; width: 20%;"><label>Resultado:</label>
							</td>
							<td style="text-align: left; width: 80%;" colspan="3"><label
								for="resultadoNombre">${resultado.definicionResultado}</label></td>
						</tr>
						<tr>
							<td style="text-align: right; width: 20%;"><label>Actividad:</label>
							</td>
							<td style="text-align: left; width: 80%;" colspan="3"><label
								for="resultadoNombre">${actividad.nombreActividad}</label></td>
						</tr>
						<tr>
							<td style="text-align: right; width: 20%;"><label>Tipo
									Actividad:</label>
							</td>
							<td style="text-align: left; width: 80%;" colspan="2"><label
								for="resultadoNombre">${actividad.tipoActividadNombre}</label></td>
						</tr>
						<tr>
					<td style="text-align: right; width: 20%; vertical-align: top">
					    <label>Tipo de Periodo:</label>
					</td>
					<td colspan="3" style="text-align: left; width: 80%; vertical-align: top">
					    <label>${planOperativo.datoProyecto.programa.tipoPeriodo.descripPeriodo }</label>
					</td>
				</tr>
					</table>
					<br class="hide" />
					<fieldset class="hide">
						<legend>
							<label>Informacion del Costo por Actividad</label>
						</legend>
						<table width="100%">
							<tr>
								<td style="text-align: right; width: 20%;"><label>Categoria
										Actividad:</label>
								</td>
								<td style="text-align: left; width: 80%;" colspan="3"><select
									id="categoriaActividadID" name="categoriaActividadID"
									style="width: 98%;" onchange='onChangeCategoriaActividad(0,0,0,0);'>
										<option value="0">-- Categoria Actividad --</option>
										<c:forEach items="${listaCategoriaActividad}" var="lista">
											<option value="${lista.categoriaActividadID}">${lista.descripcionCategoriaActividad}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr id="rubroGenerico">
								<td style="text-align: right; width: 20%;"><label>Rubro
										Generico:</label>
								</td>
								<td style="text-align: left; width: 30%;"><select
									id="cabeceraRubroGenericoID" name="cabeceraRubroGenericoID" 
									style="width: 98%;" onchange="onChangeRubroGenerico(0)">
										<option value="0">--Rubro Generico--</OPTION>
										<c:forEach items="${listaRubroGenerico}" var="lista">
											<option value="${lista.rubroGenericoID}">${lista.descripEspecificacionRubroGenerico}</option>
										</c:forEach>
								</select></td>
								<td style="text-align: right; width: 20%;"><label>Rubro
										Especifico:</label>
								</td>
								<td style="text-align: left; width: 30%;"><select
									id="rubroGenericoID" name="rubroGenericoID" style="width: 94%;">
										<option value="0">--Rubro Especifico--</OPTION>
										<c:forEach items="${listaRubroGenerico}" var="lista">
											<option value="${lista.rubroGenericoID}">${lista.descripEspecificacionRubroGenerico}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td style="text-align: right; width: 20%;"><label>Partida
										Generica:</label>
								</td>
								<td style="text-align: left; width: 30%;"><select
									id="partidaGenericaID" name="partidaGenericaID"
									style="width: 98%;" onchange='onChangePartidaGenerica(0);'>
										<option value="0">--Partida Generica--</option>
										<c:forEach items="${listaPartidaGenerica}" var="lista">
											<option value="${lista.partidaGenericaID}">${lista.descripcionPartidaGenerica}</option>
										</c:forEach>
								</select></td>
								<td style="text-align: right; width: 20%;"><label>Partida
										Especifica:</label>
								</td>
								<td style="text-align: left; width: 30%;"><select
									id="partidaEspecificaID" name="partidaEspecificaID"
									style="width: 94%;">
										<option value="0">--Partida Especifica--</option>
										<c:forEach items="${listaPartidaEspecifica}" var="lista">
											<option value="${lista.partidaEspecificaID}">${lista.descripcionPartidaEspecifica}</option>
										</c:forEach>
								</select></td>
							</tr>
							<!-- 
							<c:if test="${valorRubro != 1}">
								<tr>
									<td style="text-align: right; width: 20%;"><label>Categoria
											Actividad:</label>
									</td>
									<td style="text-align: left; width: 80%;" colspan="3"><select
										id="categoriaActividadID" name="categoriaActividadID"
										style="width: 100px;" onchange='onChangeCategoriaActividad();'>
											<option value="0">--Categoria--</option>
											<c:forEach items="${listaCategoriaActividad}" var="lista">
												<option value="${lista.categoriaActividadID}"
													${lista.categoriaActividadID==categoriaActividadIDSelected ? 'selected' : ''}>${lista.descripcionCategoriaActividad}</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: right; width: 20%;"><label>Partida
											Generica:</label>
									</td>
									<td style="text-align: left; width: 30%;"><select
										id="partidaGenericaID" name="partidaGenericaID"
										style="width: 100px;" onchange='onChangePartidaGenerica();'>
											<option value="0">--Seleccionar--</option>
											<c:forEach items="${listaPartidaGenerica}" var="lista">
												<option value="${lista.partidaGenericaID}"
													${lista.partidaGenericaID==partidaGenericaIDSelected ? 'selected' : ''}>${lista.descripcionPartidaGenerica}</option>
											</c:forEach>
									</select></td>
									<td style="text-align: right; width: 20%;"><label>Partida
											Especifica:</label>
									</td>
									<td style="text-align: left; width: 30%;"><select
										id="partidaEspecificaID" name="partidaEspecificaID"
										style="width: 100px;">
											<option value="0">--Seleccionar--</option>
											<c:forEach items="${listaPartidaEspecifica}" var="lista">
												<option value="${lista.partidaEspecificaID}">${lista.descripcionPartidaEspecifica}</option>
											</c:forEach>
									</select></td>
								</tr>
							</c:if>
							-->
							<tr>
								<td style="text-align: right; width: 20%;"><label>Detalle:</label>
								</td>
								<td style="text-align: left; width: 80%;" colspan="3"><textarea
										rows="3" cols="100" id="detallePartidaGenerica"
										name="detallePartidaGenerica" style="width: 98%;"></textarea>
								</td>
							</tr>
							<tr>
								<td style="text-align: right; width: 20%;"><label>Cantidad:</label>
								</td>
								<td style="text-align: left; width: 30%;"><input
									type="text" id="cantidadTotal" name="cantidadTotal" size="11"
									onkeypress="soloNumero(event)" style="width: 65px;"
									maxlength="4" /> <select id="unidadMedidaId"
									name="unidadMedidaId" style="width: 120px;">
										<option value="0">--Unidad--</option>
										<c:forEach items="${listaUnidadMedida}" var="lista">
											<option value="${lista.tablaEspecificaID}">${lista.descripcionCabecera}</option>
										</c:forEach>
								</select></td>
								<td style="text-align: right; width: 20%;"><label>Precio
										Unitario:</label>
								</td>
								<td style="text-align: left; width: 30%;"><input
									type="text" id="precioUnitario" name="precioUnitario" size="11"
									onkeypress="soloNumero(event)" style="width: 65px;"
									maxlength="7" /> <select id="tipoMonedaId_"
									name="tipoMonedaId_" style="width: 100px;" disabled="disabled">
										<option value="0">--Moneda--</option>
										<c:forEach items="${listaTipoMoneda}" var="lista">
											<option value="${lista.tablaEspecificaID}"
												<c:if test="${lista.tablaEspecificaID == planOperativo.idTipoMoneda}"> selected="selected" </c:if>>${lista.descripcionCabecera}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td colspan="4"><br></td>
							</tr>
							<tr>
								<td style="width: 50%;" colspan="2"></td>
								<td style="text-align: right; width: 20%;"><input
									type="button" value="Cerrar" id="idBtnCerrar" />
								</td>
								<td style="text-align: right; width: 30%;"><input
									type="button" value="Grabar Costo Actividad"
									onClick="submitFormCostoActividad()"></td>
							</tr>
						</table>
					</fieldset>
				</form:form>
			</div>
			<form>
				<table class="table-clasico" style="width: 100%">
					<caption>Costo Actividad</caption>
					<thead>
						<tr>
							<!-- <th align="center"><label></label></th> -->
							<th align="center"><label>Categoria</label>
							</th>
							<th align="center"><label>Partida <br/>Genérica</label>
							</th>
							<th align="center"><label>Partida <br/>Especifica</label>
							</th>
							<th align="center"><label>Descripcion</label>
							</th>
							<th align="center"><label>Cantidad</label>
							</th>
							<th align="center"><label>Precio<br/>Unitario</label>
							</th>
							<th align="center"><label>Costo <br/>Total</label>
							</th>
							<th align="center"><label>Cronograma</label>
							</th>
							<th align="center" class="hide"><label>Operaciones</label>
							</th>

						</tr>
					</thead>
					<c:forEach var="costoActividad" items="${listCostoActividad}"
						varStatus="indice">
						<c:choose>
							<c:when test="${indice.count %2== 0}">
								<c:set var="classIdi" value="f1"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="classIdi" value="f2"></c:set>
							</c:otherwise>
						</c:choose>
						<tr class="<c:out value="${classIdi}"></c:out>">
							<!-- <td align="center"><input type="radio" name="costoActividadId" value="${costoActividad.costoActividadID}"></td> -->
							<td align="left"><c:out
									value="${costoActividad.descripcionCategoriaActividad}"></c:out>
							</td>
							<td align="left"><c:out
									value="${costoActividad.descripcionPartidaGenerica}"></c:out>
							</td>
							<td align="left"><c:out
									value="${costoActividad.descripcionPartidaEspecifica}"></c:out>
							</td>
							<td align="left"><c:out
									value="${costoActividad.detallePartidaGenerica}"></c:out>
							</td>
							<td align="left"><c:out
									value="${costoActividad.cantidadTotal} - ${costoActividad.unidadMedidaNombre}"></c:out>
							</td>
							<td align="left"><c:out
									value="${costoActividad.precioUnitario} - ${costoActividad.tipoMonedaPrecioUnitarioNombre}"></c:out>
							</td>
							<td align="left"><c:out
									value="${costoActividad.montoTotal} - ${costoActividad.tipoMonedaPrecioUnitarioNombre}"></c:out>
							</td>
							<td align="center"><label><a
									href="javascript:goTo('showCronogramaCostoActividad.jspx?datoPlanOperativoID=${planOperativo.datoPlanOperativoID}&resultadoID=${resultado.resultadoID}&actividadID=${actividad.actividadID}&costoActividadID=${costoActividad.costoActividadID}&estadoPlanOperativo=${estadoPlanOperativo}')"
									id="cronograma" class="linkSelecciona">Cronograma</a> </label>
							</td>
							<td align="center" class="hide"> <a
						href="javascript:modificarMetaActividad('<c:out value="${costoActividad.costoActividadID }"></c:out>',
																'<c:out value="${costoActividad.categoriaActividadID }"></c:out>',
																'<c:out value="${costoActividad.partidaGenericaID }"></c:out>',
																'<c:out value="${costoActividad.partidaEspecificaID }"></c:out>',
																'<c:out value="${costoActividad.detallePartidaGenerica }"></c:out>',
																'<c:out value="${costoActividad.cantidadTotal }"></c:out>',
																'<c:out value="${costoActividad.unidadMedidaId }"></c:out>',
																'<c:out value="${costoActividad.precioUnitario }"></c:out>',
																'<c:out value="${costoActividad.tipoMonedaPrecioUnitarioId }"></c:out>',
																'<c:out value="${costoActividad.cantTotalCronogramaCostoActividad }"></c:out>',
																'<c:out value="${costoActividad.cabeceraRubroGenericoID }"></c:out>',
																'<c:out value="${costoActividad.rubroGenericoID }"></c:out>')"
						id="modificarMetaActividad" class="linkSelecciona">Modificar</a> <br /> <a
								href="#"
								onclick="fEliminar(${costoActividad.costoActividadID}, '${costoActividad.detallePartidaGenerica}')"  class="linkSelecciona" >Eliminar</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</fieldset>
	</div>
</body>
</html>
