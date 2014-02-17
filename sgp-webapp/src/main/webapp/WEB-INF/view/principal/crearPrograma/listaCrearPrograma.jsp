<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/displaytagex.css" type="text/css" />
<script type="text/javascript">
$(function() {

	// DIALOGO ELIMINAR PROGRAMA
	$('#dialogoEliminarPrograma').dialog({
		autoOpen : false,
		width : 280,
		height: 100,
		buttons : {
			"Si" : function() {
				$(this).dialog("close");

				var form = document.formCrearPrograma;
				form.action = "actionEliminarPrograma.jspx";
				form.submit();
			},
			"No" : function() {
				$(this).dialog("close");
			}
		}
	});
	
	
});
	function fMostrarSelect(valor){
		if (valor ==100) {
			$('#divlistTipoPeriodos').show();
			$('#divlistTipoCuentas').hide();
			$('#divlistModalidadFinanciera').hide();
			$('#divNombre').hide();
		} 
		if(valor ==101){
			$('#divlistTipoPeriodos').hide();
			$('#divlistTipoCuentas').show();
			$('#divlistModalidadFinanciera').hide();
			$('#divNombre').hide();
		}
		if(valor ==102){
			$('#divlistTipoPeriodos').hide();
			$('#divlistTipoCuentas').hide();
			$('#divlistModalidadFinanciera').show();
			$('#divNombre').hide();
		}
		if(valor ==103){
			$('#divNombre').show();
			$('#divlistTipoPeriodos').hide();
			$('#divlistTipoCuentas').hide();
			$('#divlistModalidadFinanciera').hide();
		}
		if (valor ==104) {
			$('#divlistTipoPeriodos').hide();
			$('#divlistTipoCuentas').hide();
			$('#divlistModalidadFinanciera').hide();
			$('#divNombre').hide();
		}	

	}
	function fBuscarPrograma(){
		var form = document.formCrearPrograma;
		form.action = "actionBuscarCrearPrograma.jspx";
		form.submit();
	}
	
	function fEliminarPrograma(idPrograma) {
		document.getElementById('variable1').value=idPrograma; 
		document.getElementById("dialogoEliminarPrograma").innerHTML = "<p>&iquest;Esta Seguro de Eliminar el Programa?</p>";
		$(function() {
			$('#dialogoEliminarPrograma').dialog('open');
			return false;
		});	
	}
	
	function fCargarModificarDatosPrograma(idPrograma) {
		$("#variable1").attr("value",idPrograma);
		//alert(idPrograma);
		var form = document.formCrearPrograma;
		form.action = "actionCargarModificarDatosPrograma.jspx";
		//alert(form.action);
		form.submit();		
		//document.getElementById('variable').value=idPrograma; 		
		//parent.fCargarModificarDatosPrograma(idPrograma);	
	}
	
	</script>
<div class="form-clasico">
<form:form name="formBuscarCrearPrograma" action="actionBuscarCrearPrograma.jspx">
		<input type="hidden" id="variable1"/>
	<table >
		<tr>
			<td><label>Filtro:</label>
			</td>
			<td><select name="idFiltro" id="idFiltro"
				onchange="fMostrarSelect(this.value)">
				<option value="0"><c:out value="--Seleccionar--" />
				<option value="100"><c:out value="Tipo Periodo" /></option>
				<option value="101"><c:out value="Tipo Cuenta" /></option>
				<option value="102"><c:out value="Modalidad" /></option>
				<option value="103"><c:out value="Nombre Programa" /></option>
				<option value="104"><c:out value="Todos" /></option>
			</select></td>
		
			<td>
			<div id="divNombre" style="display: none;">
			<label>Nombre:</label><input type="text" name="buscarNombre" />
			</div>
			<div id="divlistTipoPeriodos" style="display: none;"><select
				name="idTipoPeriodos" id="idTipoPeriodos">
				<option value="0"><c:out value="--Seleccionar--" /></option>
				<c:forEach items="${listTipoPeriodos}" var="tipoPeriodo">
					<option value="${tipoPeriodo.tipoPeriodoID}"><c:out
						value="${tipoPeriodo.descripPeriodo}" /></option>

				</c:forEach>
			</select></div>
			<div id="divlistTipoCuentas" style="display: none;"><select
				name="idTipoCuentas" id="idTipoCuentas">
				<option value="0"><c:out value="-- Seleccionar --" /></option>
				<c:forEach items="${listTipoCuentas}" var="tipoCuenta">
					<option value="${tipoCuenta.tablaEspecificaID}"><c:out
						value="${tipoCuenta.descripcionCabecera}" /></option>
				</c:forEach>
			</select></div>
			<div id="divlistModalidadFinanciera" style="display: none;"><select
				name="idModalidadFinanciera" id="idModalidadFinanciera">
				<option value="0"><c:out value="-- Seleccionar --" /></option>
				<c:forEach items="${listModalidadFinanciera}" var="modali">
					<option value="${modali.tablaEspecificaID}"><c:out
						value="${modali.descripcionCabecera}" /></option>
				</c:forEach>
			</select></div>
			</td>
		
			<td ><input style="width: 95px; height: 15px" value="Buscar Programa" type="button" onclick="fBuscarPrograma()" /></td>
		</tr>
	</table>
		<display:table uid="programa" 
				name="${listPrograma}" defaultsort="1"
				defaultorder="ascending" pagesize="10"
				requestURI="crearProgramaPaginate.jspx" export="true" 
				class="table-clasico" style="width:100%; font-size: 12px;">
				<display:caption><label>Lista de Programas</label></display:caption>
				<display:column property="programaID"  title="Id" sortable="true" />
				<display:column property="nombrePrograma" title="Programa"  sortable="true"/>
				<display:column property="duracionPrograma" title="Duracion Programa" sortable="true"/>
				<display:column property="fechaConvocatoria" title="Fecha Convocatoria" sortable="true"/>
				<display:column property="identificadorModFinan" title="Identificador" sortable="true"/>
				<display:setProperty name="export.excel.filename" value="listCrearPrograma.xls" />
				<display:column title="Eliminar" >
            		<a href="javascript:fEliminarPrograma('${programa.programaID}')"><span class="ui-icon ui-icon-circle-close" style="cursor:pointer" title="Eliminar"></span></a>          
        		</display:column >
        		<display:column title="Modificar">
            		<a href="javascript:fCargarModificarDatosPrograma('${programa.programaID}')"><span class="ui-icon ui-icon-circle-arrow-s" style="cursor:pointer" title="Modificar"></span></a>
        		</display:column >	
          </display:table >  
</form:form>
 </div>  