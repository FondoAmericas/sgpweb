<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
 <%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
 <%@include file="/common/includesTaglibsGenerico.jsp"%>
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
var tipoResultado=0;

$(window).load(function () {
	tipoResultado=document.getElementById('tipoResultado').value;
	fMostrar();
	
});
function fMostrar(){
	if (tipoResultado ==1) {
		$('#metaResultado').show();
		$('#beneficiario').hide();
		$('#indicador').hide();
	} 
	if(tipoResultado ==2){
		$('#beneficiario').show();
		$('#metaResultado').hide();
		$('#indicador').hide();
	}
	if(tipoResultado ==3){
		$('#indicador').show();
		$('#beneficiario').hide();
		$('#metaResultado').hide();
		
	}
	if(tipoResultado ==0){
		$('#indicador').hide();
		$('#beneficiario').hide();
		$('#metaResultado').hide();
		
	}

}


</script>	
	
<div class="form-clasico" >
<form:form name="formDetalleResultado" action="showDetalleResultado.jspx"  >
<div class="encabezado">Resultado</div>
<input type="hidden" name="tipoResultado" id="tipoResultado" value="${tipoResultado}">
<input type="hidden" name="resultadoID" id="resultadoID" value="${resultadoID}">
	<table style="width=100%">
<tr>
				<td align="right"><b>Resultado:</b></td>
				<td align="left" >
					<c:out value="${resultado.definicionResultado}"></c:out>
				</td>
			</tr>
		</table>
<div id="metaResultado" >
<fieldset><legend>Meta Por Resulado</legend>
<display:table uid="meta"  class="table-clasico" style="width: 100%; font-size: 12px;"
				name="${listMeta}" defaultsort="1"
				defaultorder="ascending" pagesize="12"
				requestURI="" export="false"  >
				<display:caption>
							<label>Lista Resultados Por Resultado</label>
							</display:caption>
				<display:column property="avanceMeta" title="avanceMeta"  sortable="true"/>
				<display:column property="periodo" title="periodo" sortable="true"/>

</display:table >    		
		</fieldset> 
</div>

<div id="beneficiario" > 
<fieldset><legend>Lista Beneficiario</legend>
<display:table uid="programa" 
				name="${listBeneficiario}" defaultsort="1" class="table-clasico" style="width: 100%; font-size: 12px;"
				defaultorder="ascending" pagesize="12"
				requestURI="" export="false"  >
				<display:caption>
							<label>Lista Beneficiarios</label>
				</display:caption>
				<display:column property="tipoBeneficiarioNombre" title="Tipo Beneficiario"  sortable="true"/>
				<display:column property="caracteristicasPoblacion" title="Poblacion" sortable="true"/>
				<display:column property="cantidadProgramado" title="Cant Programado" sortable="true"/>
				<display:column property="estratoNombre" title="Estrato" sortable="true"/>
				<display:column property="descripcion" title="Descripcion" sortable="true"/>
				
</display:table >    
</fieldset> 
</div>
<div id="indicador" style="display: none;">

<fieldset><legend>Lista Indicador</legend>

		<display:table uid="meta" class="table-clasico" style="width: 100%; font-size: 12px;"
				name="${listIndicador}" defaultsort="1"
				defaultorder="ascending" pagesize="12"
				requestURI="" export="false"  >
				<display:caption>
							<label>Lista Indicadores</label>
							</display:caption>
				<display:column property="tipoIndicadorNombre" title="Tipo Indicador"  sortable="true"/>
				<display:column property="definicionIndicador" title="Definicion" sortable="true"/>
				<display:column property="unidadMedidaNombre" title="Tipo Indicador"  sortable="true"/>
				<display:column property="medioVerificacion" title="Definicion" sortable="true"/>
				<display:column property="situacionActual" title="Tipo Indicador"  sortable="true"/>
				<display:column property="situacionFinal" title="Definicion" sortable="true"/>
				
	</display:table >    
    
</fieldset> 
</div>
</form:form>
 </div>  