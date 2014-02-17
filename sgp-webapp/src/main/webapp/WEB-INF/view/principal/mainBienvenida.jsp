<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/includesTaglibsGenerico.jsp"%>

<script type="text/javascript">
function verObservacionesEjecutor(datoProyectoId) {
//alert(datoProyectoId);
	var url = "verObservacionesEjecutor.jspx?datoProyectoId="
			+ datoProyectoId;
	var stiloPopUp = 'dialogWidth=950px; dialogHeight=700px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
	//ventanaPopUp = showModalDialog(url,'',stiloPopUp);//window.open(url,'',stiloPopUp);

	//window.opener.document.formWindowAsignaTutor.profesorAsignado.value = componenteId;

	//window.showModalDialog(url,datos,stiloPopUp);
	window.showModalDialog(url, "", stiloPopUp);

}
</script>

<div style="float: left; margin-left: 15px;margin-top: 1px; margin-bottom: 1px;">
			<c:if test="${DATO_USER_SESSION.nombre != null}">
				<label style="font-size: 14px; font-weight: bold;">Bienvenido  <c:out value="${DATO_USER_SESSION.nombre}" /> <c:out value="${DATO_USER_SESSION.paterno}" />	<c:out value="${DATO_USER_SESSION.materno}" /> al Sistema</label>
				<br/>
				<label style="font-size: 14px; font-weight: bold;"><c:out value="${USER_SESSION.descripcionPerfil }"></c:out></label>
			</c:if>
		</div>
		<div style="float: right; margin-right: 15px;margin-top: 10px;">
			<c:if test="${USER_SESSION.perfilUsuarioID == 7 }">
				<label style="color: red;"><a href="javascript:verObservacionesEjecutor('${USER_SESSION.datoProyectoID }')" class="linkSeleccionaObs">Tiene <c:out value="${cantObservaciones }"></c:out> Observaciones no levantadas</a></label>
			</c:if>	
		</div>
