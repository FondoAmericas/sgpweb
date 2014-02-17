<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
 <%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/displaytagex.css" type="text/css" />
<link type="text/css"
	href="<%=request.getContextPath()%>/css/custom-theme/jquery-ui-1.8.12.custom.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-ui-1.8.12.custom.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.ui.datepicker-es.js"></script>
<link type="image/x-icon"
	href="<%=request.getContextPath()%>/images/americas.ico"
	rel="shortcut icon" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/viewMain.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/estiloContabilidad.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/estiloGeneral.css" />
<script type="text/javascript">

var wid = window.innerWidth;
var hei = window.innerHeight;
function Resize(){
self.resizeTo(wid+5, hei+80);
}

window.onresize=Resize;

function fCargarPrograma(id){
	//window.opener.cargarPrograma(id);
	window.opener.fCargarComiteTecnico(id);
	window.close();  

	
}

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
</script>	
	
<div class="form-clasico">
<form:form name="formCargarPrograma" action="actionBuscarPrograma.jspx">
	<div class="encabezado">LISTA PROGRAMA</div>

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
				<c:forEach  items="${listTipoPeriodos}" var="tipoPeriodo">
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
			<td ><input value="Buscar Programa" type="submit" /></td>
		</tr>
	</table>
<br />
<display:table uid="programa" 
				name="${listarProgramas}" defaultsort="1"
				defaultorder="ascending" pagesize="12"
				requestURI="" export="false" 
class="table-clasico" style="width:100%; font-size: 12px;">
	<display:caption><label>Lista de Programas</label></display:caption>
				<display:column title="Cargar">
            		<a href="javascript:fCargarPrograma('${programa.programaID}')" class="ui-icon ui-icon-circle-arrow-s" style="cursor: pointer" title="Modificar"></a>
        		</display:column >
				<display:column property="nombrePrograma" title="Programa"  sortable="true"/>
				<display:column property="duracionPrograma" title="Duracion Programa" sortable="true"/>
				<display:column property="fechaConvocatoria" title="Fecha Convocatoria" format="{0,date,dd-MM-yyyy}" sortable="true"/>
				<display:column property="identificadorModFinan" title="Identificador" sortable="true"/>
          </display:table >    
</form:form>
 </div>  