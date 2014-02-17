<%@ include file="includesTaglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


</head>
<body>
<br>
<form action="#" class="form-clasico">
<div id="div_montosAcumulados">
				<fieldset>
					<legend id="montos1">Montos Acumulados</legend>
					<table width="100%" border="0">
						<tr>
							<td style="text-align: right;"><label>Fondo Americas:</label>
							</td>
							<td>
								<label><fmt:formatNumber value="${montosAcumulados.montoFondam}" /> 
								<c:if test="${montosAcumulados.montoFondam >0}"> Dolares</c:if>
								</label>
							</td>
											<!-- 	<td style="text-align: right;">&nbsp;
							</td>   -->
							<td colspan="2" style="text-align: right;">
							     <input type="button" value="cerrar" onclick="window.close()">
							</td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>Cofinanciador:</label>
							</td>
							<td>
								<label><fmt:formatNumber value="${montosAcumulados.montoCofinanciador}"/>
								<c:if test="${montosAcumulados.montoCofinanciador >0}"> Dolares</c:if>
								</label>
							</td>
						    <td style="text-align: right;">
						    	&nbsp;
							</td>  
							<td >
							    &nbsp;
							</td>
						</tr>
						<tr>
							<td style="text-align: right;"><label>Contrapartida:</label>
							</td>
							<td>
								<label><fmt:formatNumber value="${montosAcumulados.montoContrapartida}" />
								<c:if test="${montosAcumulados.montoContrapartida >0}"> Dolares</c:if>
								</label>
							</td>
							<td style="text-align: right;">
								&nbsp;
							</td>   
							<td colspan="2" style="text-align: right;">
							     &nbsp;
							</td>
						</tr>
					</table>
				</fieldset>
		</div>
</form>
</body>
</html>



                                                                                                                    




