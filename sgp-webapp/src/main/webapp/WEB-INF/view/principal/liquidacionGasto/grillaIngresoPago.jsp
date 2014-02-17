<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.multiselect.css" />

<script type="text/javascript">

</script>


<div class="form-clasico" >
<br/>

<form:form  name="formCrearLiquidacionGastos"	action="actionCrearLiquidacionGastos.jspx" method="POST" >

	<display:table uid="liquidacion" 
				name="${listLiquidacionGasto}" defaultsort="1"
				defaultorder="ascending" pagesize="20"
				requestURI=""   class="table-clasico" style="width:100%; font-size: 12px;">
				<display:caption><label>Lista</label></display:caption>
				<display:column title="Id" >
				<c:out value="${liquidacion_rowNum}"/>
									</display:column>
				<display:column property="codVersion" title="C&oacute;digo" />
				<display:column property="periodo" title="Periodo" />
				<display:column title="Fuente Financiadora"  >
				<c:out value="${liquidacion.fuenteFinanciadora.institucion.nombreInstitucion}"/>
				</display:column >	
				<display:column property="fechaInicio" title="Fecha Inicio" />
				<display:column property="fechaFin" title="Fecha Fin" />
				<display:column property="saldoDisponible" title="Saldo Disponible" />
				<display:column title="Ingreso Propio" >
            		<a href="javascript:goTo('showIngresoPropio.jspx?liquidacionGastoID=${liquidacion.liquidacionGastoID}')" class="ui-icon ui-icon-circle-close"  style="cursor:pointer" title="Ingreso Propio"></a>          
        		</display:column >
				<display:column title="Compromiso Pago" >
            		<a href="javascript:fOpenModalDialog('showCompromisoPago.jspx?liquidacionGastoID=${liquidacion.liquidacionGastoID}','600','500','70','70')" class="ui-icon ui-icon-circle-close" style="cursor:pointer" title="Compromiso Pago"></a>          
        		</display:column >
        		<display:column title="Ingreso Pago">
            		<a href="javascript:goTo('showIngresoPago.jspx?liquidacionGastoID=${liquidacion.liquidacionGastoID}')" class="ui-icon ui-icon-circle-arrow-s" style="cursor:pointer" title="Ingreso Pago"></a>
        		</display:column >	
          </display:table >          																											


</form:form>
</div>