<script type="text/javascript">
$(document).ready(function() {
	$("#div_txtNomProyecto").hide();	
	$("#div_txtCodigoProyecto").hide();
});

function cambiarFiltro(){
	opcionBusqueda=$("#opBusEvalReporte").val();

	if(opcionBusqueda=="1"){ //por nombre de proyecto
		
		$("#div_txtNomProyecto").show('slow');
		$("#txtNomProyecto").focus();
		$("#txtCodProyecto").attr("value","");
		$("#div_txtCodigoProyecto").hide();
	}
	else if(opcionBusqueda=="2"){ //por codigo de proyecto
		
		$("#div_txtCodigoProyecto").show('slow');
		$("#txtCodProyecto").focus();
		$("#txtNomProyecto").attr("value","");
		$("#div_txtNomProyecto").hide();
	}
	}
</script>



<table width="100%">
					
					<tr>
						<td style="text-align: right; width: 25%;">
								<label>Modalidad	Finaciamiento:</label>
						</td>
						<td style="text-align: left; width: 25%;"><div
								id="div_cbxModalidadFinan">
								<select name="cbxModalidadFinan" id="cbxModalidadFinan">
									<option value="0">-- Modalidad --</option>
								</select>
							</div></td>
						<td style="text-align: right; width: 25%;">
								<label>Programa:</label>
						</td>
						<td style="text-align: left; width: 25%;">
							<div id="div_cbxPrograma">
								<select name="cbxPrograma" id="cbxPrograma">
									<option value="0">-- Programa --</option>
								</select>
							</div>
					    </td>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%;">
						    <label>Buscar por:</label>
					    </td>
						<td style="text-align: left; width: 25%;">
						        <select name="opBusEvalReporte" id="opBusEvalReporte" onchange="cambiarFiltro()">
							       <option value="0">----seleccionar----</option>
							       <option value="1">Por nombre proyecto</option>
							       <option value="2">Por codigo proyecto</option>
							    </select>
						</td>
	                    <td style="width: 25%;"></td>
	                    <td style="text-align: left; width: 25%;">
							<!-- <input type="button" name="btnBusProg" id="btnBusProg" value="Buscar" onclick="onclick_btnBusDesembolso()" size="20px"/> -->
							<input type="button" onclick="buscarProyectos();" value="buscar">
						</td>
	                    </tr>
	                    <tr>
	                    <td style="width: 25%;"></td>
						<td colspan="3" style="width: 75%;text-align: left;">
						    <div id="div_txtNomProyecto">
				    			<input type="text" id="txtNomProyecto" name="txtNomProyecto" style="width: 95%;"/>
				 			</div>
				 			<div id="div_txtCodigoProyecto">
				    			<input type="text" name="txtCodProyecto" id="txtCodProyecto" style="width: 95%;"/>
				 			</div>
					    </td>
			    	</tr>	
		</table>
