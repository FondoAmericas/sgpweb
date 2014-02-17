<%@ include file="/common/includesTaglibsGenerico.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function() {
	var estLiquidacionGastoPrefijo="<c:out value="${estLiquidacionGastoPrefijo }"></c:out>";
	
	if(estLiquidacionGastoPrefijo == 'apro'){
		$(".hide").hide();
	}
});
</script>
</head>
<body>

<form id="detalleLiqGastosForm" method="post" action="#" class="form-clasico" accept-charset="UTF-8">
<input type="hidden" value="${objLiquidacionGasto.fkIdDetalleEstadoCabEstLiqGasto}" id="fkIdDetalleEstadoCabEstLiqGasto"> 
<table class="table-clasico" style="width: 100%">
		<caption>Listado de ingresos propios</caption>
		<thead>
        <tr>
        	<td align="center"><label>Det</label></td>
        	<td align="center"><label>Raz&oacute;n Social</label></td>
        	<td align="center"><label>Ruc Comprador</label></td>
            <td align="center"><label>N&uacute;mero Comprobante</label></td>
            <td align="center"><label>Tipo Comprobante</label></td>
            <td align="center"><label>Sub Total</label></td>
			<td align="center"><label>Igv</label></td>
			<td align="center"><label>Precio Total</label></td>
			<td align="center" class="hide"><label>Opcion</label></td>	
        </tr>
        </thead>
        <c:forEach var="ingresoPropioG" items="${listIngresoPropio}"  varStatus="indice">
        <c:choose>
					<c:when test="${indice.count %2== 0}">
						<c:set var="classIdi" value="f2"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="classIdi" value="f1"></c:set>
					</c:otherwise>
				</c:choose>
				<tr  class="<c:out value="${classIdi}"></c:out>">
				<td style="text-align: center;">
						<a
					href="javascript:expandcollapse('divIP<c:out value="${ingresoPropioG.ingresoPropioID }"></c:out>', 'one');">
						<img
						id='imgdivIP<c:out value="${ingresoPropioG.ingresoPropioID }"></c:out>'
						border="0" src="<c:url value="/images/Plus001.gif" />"
						width="15px" /> </a>
						</td>
	        	<td align="left"><label><c:out value="${ingresoPropioG.razonSocial}"></c:out></label></td>
				<td align="left"><label><c:out value="${ingresoPropioG.rucComprador}"></c:out></label></td>
				<td align="left"><label><c:out value="${ingresoPropioG.numeroComprobante}"></c:out></label>
				</td>
			<td align="left"><label>
			    	<c:forEach items="${listTipoComprobante}" var="modali">
		            <c:if test="${ ingresoPropioG.fkIdtablaespTipoComprobantePago == modali.tablaEspecificaID}">
		            <c:out value="${modali.descripcionCabecera}"/>
		            </c:if>
		          </c:forEach></label>
    			</td>
				<td align="center"><label><c:out value="${ingresoPropioG.precioSinIgv}"></c:out> <br>
				 <c:forEach items="${listTipoMoneda}" var="modali1">
                       <c:if	test="${modali1.tablaEspecificaID== ingresoPropioG.fkIdtablaespTipoMoneda}">
            				<c:out value="${modali1.descripcionCabecera}" />
                        </c:if>
                    </c:forEach></label>
				</td>
				<td align="center"><label><c:out value="${ingresoPropioG.igv}"></c:out><br>
				 <c:forEach items="${listTipoMoneda}" var="modali2">
                       <c:if	test="${modali2.tablaEspecificaID== ingresoPropioG.fkIdtablaespTipoMoneda}">
                  				<c:out value="${modali2.descripcionCabecera}" />
                        </c:if>
                    </c:forEach></label>
				</td>
				<td align="center"><label><c:out value="${ingresoPropioG.precioTotal}"></c:out><br>
				  <c:forEach items="${listTipoMoneda}" var="modali1">
                       <c:if	test="${modali1.tablaEspecificaID== ingresoPropioG.fkIdtablaespTipoMoneda}">
            				<c:out value="${modali1.descripcionCabecera}" />
                        </c:if>
                    </c:forEach></label>
				</td>
				<td class="hide"><label><a	href="javascript:agregarObservacion('<c:out value="${ingresoPropioG.ingresoPropioID }" ></c:out>','<c:out value="${liquidacionGasto.datoProyecto.datoProyectoID }" ></c:out>','6','26','<c:out value="${liquidacionGastoID}" ></c:out>')"
														class="linkSelecciona">Observacion</a></label></td>
			</tr>
					<tr class="<c:out value="${classIdi}"></c:out>">
					<td colspan="100%">
					<div
						id='divIP<c:out value="${ingresoPropioG.ingresoPropioID }"></c:out>'
						style="display: none; position: relative; overflow: auto; padding-left: 15px; padding-bottom: 15px; width: 97%;">
						<div>
					<label> <b>Resultado:</b> <c:forEach items="${listResultado}" var="modali">
	            	 <c:if test="${ ingresoPropioG.resultado.resultadoID == modali.resultadoID}">                                                                                  
	            		<c:out value="${modali.definicionResultado}" />
	            	</c:if>
	        		</c:forEach>
						</label>
				</div>
		</div>
		</td>
		</tr>
        </c:forEach>
</table> 
</form>
</body>
</html>



                                                                                                                    
