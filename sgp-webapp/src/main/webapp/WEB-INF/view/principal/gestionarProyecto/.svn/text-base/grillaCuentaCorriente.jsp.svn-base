	<%@ include file="/common/taglibs.jsp"%>
	
	<c:forEach items="${listCuentaCorrienteBean }" var="cuentaCorrienteBean" varStatus="indice">
	<c:choose>
										<c:when test="${indice.count %2== 0}">
											<c:set var="classIdi" value="f2"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="classIdi" value="f1"></c:set>
										</c:otherwise>
									</c:choose>
	<tr class="<c:out value="${classIdi}"></c:out>">
					<td style="width: 3%; text-align: center;"><label><c:out value="${indice.count }"></c:out> </label>
							</td>
							<td style="width: 25%; text-align: left;"><label><c:out value="${cuentaCorrienteBean.descripcionBanco }"></c:out> </label>
							</td>
							<td style="width: 15%; text-align: left;"><label><c:out value="${cuentaCorrienteBean.descripcionTipoMoneda }"></c:out> </label>
							</td>
							<td style="width: 50%; text-align: left;"><label><c:out value="${cuentaCorrienteBean.numeroCuenta }"></c:out> </label>
							</td>
					<td style="width: 7%; text-align: center;" ><label>
					<c:choose>
						<c:when test="${cuentaCorrienteBean.flagReferencia > 0 }">Cuenta usada en Desembolso</c:when>
						<c:otherwise>
			<a href="javascript:eliminarCuentaCorriente('<c:out value="${cuentaCorrienteBean.cuentaCorrienteID  }" ></c:out>')" 
			class="linkSelecciona" >Eliminar</a><br>
			<a href="javascript:modificarCuentaCorriente('<c:out value="${cuentaCorrienteBean.cuentaCorrienteID }" ></c:out>','<c:out value="${cuentaCorrienteBean.fkIdtablaespBanco }"></c:out>','<c:out value="${cuentaCorrienteBean.fkIdtablaespTipomoneda }"></c:out>','<c:out value="${cuentaCorrienteBean.numeroCuenta }"></c:out>')" 
			 class="linkSelecciona">Modificar</a>			
						</c:otherwise>
					</c:choose>
			</label></td>
		</tr>
				<script type="text/javascript">
var objCuentaCorriente = new Object();
objCuentaCorriente.arrayId= "<c:out value="${indice.count }"></c:out>";
objCuentaCorriente.cuentaCorrienteID = "<c:out value="${cuentaCorrienteBean.cuentaCorrienteID }"></c:out>";
objCuentaCorriente.bancoId = "<c:out value="${cuentaCorrienteBean.fkIdtablaespBanco }"></c:out>";
objCuentaCorriente.nuemroCuenta = "<c:out value="${cuentaCorrienteBean.numeroCuenta }"></c:out>";
arrayCuentaCorriente
		.push(objCuentaCorriente);
</script>
	</c:forEach>
	