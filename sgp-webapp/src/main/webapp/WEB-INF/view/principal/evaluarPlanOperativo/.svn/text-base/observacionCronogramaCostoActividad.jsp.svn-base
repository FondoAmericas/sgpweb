<%@page import="pe.com.fondam.sgp.web.constants.SgpWebConstants"%>
<%@page import="pe.com.fondam.sgp.web.session.UserSession"%>
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
<% 
UserSession userSession = (UserSession) request.getSession().getAttribute(SgpWebConstants.SESSION_USER);
int idUsusrio = userSession.getDatoUsuarioID();
%>
	
<script type="text/javascript">

var wid = window.innerWidth;
var hei = window.innerHeight;
function Resize(){
self.resizeTo(wid+5, hei+80);
}

window.onresize=Resize;

function fUpdateEstadoObservacionCCA(estadoOCCA) {
	 if(confirm("Se modificara el estado, Desea Continuar?")){
	 var form = document.formListaObservacionCronogramaCostoActividad;
		form.action = "actionUpdateEstadoObservacionCronogramaCostoActividad.jspx?tipoEstado="+estadoOCCA.value +"&observacionCCAid="+estadoOCCA.name;
		form.submit();
	 }

}
</script>	
	
<div class="form-clasico">


<form:form name="formListaObservacionCronogramaCostoActividad" action="actionSaveObservacionCronogramaCostoActividad.jspx">
<div id="divMensaje"></div>
		<input type="hidden" value="${cronogramaCostoActividadID}" name="cronogramaCostoActividadID"/>

	<div class="encabezado">LISTA OBSERVACION CRONOGRAMA COSTO ACTIVIDAD</div>

	<table width="100%">
		<tr>
			<td><label>Observacion:</label></td>
			<td>
			<textarea name="observacion"   rows="2" cols="15"></textarea>
			</td>
			<td>
			<input value="Grabar" id="grabar" type="submit" /></td>
		</tr>
	</table>
	<br /> 
	<c:set value="<%=idUsusrio %>" var="idUsusrio" />
	<display:table uid="observacion" name="${listaObservacionCCA}" defaultsort="1"
		defaultorder="ascending" pagesize="10" requestURI="" export="false"
		class="dataTable">
		<display:column title="Atendida">
		<c:forEach items="${listEstadoEvaluacion}" var="evaluacion">
		<c:if test="${observacion.observacionAtendida==evaluacion.prefijoEstado}">
		<option value="${evaluacion.prefijoEstado}" >
		<c:out value="${evaluacion.descripEstado}" /></option>
		</c:if>
		</c:forEach>
			</display:column>
		<display:column property="observacion"	title="Observacion" sortable="true" />
		<display:column title="Estado">		
	    <select id="${observacion.observacionAtendida}" 
	        name="${observacion.observacioncronogramacostoactividadID}" 
	        onchange="fUpdateEstadoObservacionCCA(this)"	        
	        <c:if test="${observacion.datoUsuarioID != idUsusrio}">disabled="disabled"</c:if>
	        >	       
		<c:forEach items="${listEstadoEvaluacion}" var="evaluacion1">
		<option value="${evaluacion1.prefijoEstado}" >
		<c:out value="${evaluacion1.descripEstado}" /></option>
		</c:forEach>
		</select>
			</display:column>
	</display:table>
</form:form>
 </div>  