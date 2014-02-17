<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.multiselect.css" />

<div class="form-clasico" >
<form:form  name="formCronogramaCostoActividad"	action="actionCronogramaCostoActivida.jspx" method="POST" >
<table border="0" width="100%" class="table-clasico">
	<caption>Lista de Cronograma</caption>
	<thead>
		<tr>
			<td style="width: 10%; text-align: center;"><label>Periodo</label></td>
			<td style="width: 15%; text-align: center;"><label>Cantidad</label></td>
			<td style="width: 15%; text-align: center;"><label>Precio<br>Unitario</label></td>
			<td style="width: 60%; text-align: center;"><label>Fuente<br>Financiadora</label></td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${listCronogramaCostoActividad}" var="cronograma" varStatus="indice">
	<c:choose>
										<c:when test="${indice.count %2== 0}">
											<c:set var="classIdi" value="f2"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdi" value="f1"></c:set>
										</c:otherwise>
									</c:choose>
	<tr  class="<c:out value="${classIdi}"></c:out>">
			<td style="width: 10%; text-align: center;"><label><c:out value="${cronograma.periodo }"></c:out></label></td>
			<td style="width: 15%; text-align: center;"><label><c:out value="${cronograma.cantidad }"></c:out>
			        &nbsp; <c:forEach items="${listUnidadMedida}" var="modali1">
              <c:if test="${ modali1.tablaEspecificaID == cronograma.costoActividad.fkIdtablaespUnidadMedida}">
            <c:out value="${modali1.descripcionCabecera}"/>
            </c:if>
        </c:forEach>
			</label></td>
			<td style="width: 15%; text-align: center;">
			<label><c:out value="${cronograma.costoActividad.precioUnitario}"></c:out>
			<c:forEach items="${listTipoMoneda}" var="modali1">
              <c:if test="${ modali1.tablaEspecificaID == cronograma.costoActividad.fkIdtablaespTipoMoneda}">
            <c:out value="${modali1.descripcionCabecera}"/>
            </c:if>
        </c:forEach>
			
			</label></td>
			<td style="width: 60%; text-align: center;"><label><c:out value="${cronograma.fuenteFinanciadora.institucion.nombreInstitucion }"></c:out></label></td>
		</tr>
	</c:forEach>
			</tbody>
</table>

</form:form>
</div>