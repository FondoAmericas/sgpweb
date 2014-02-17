<%@ include file="/common/taglibs.jsp"%>
	
<script type="text/javascript">
	$(document).ready(function() {
		$("#login").focus();	
	});
</script>

<table style="width: 100%; ">
<tr>
<td style="width: 25%"></td>
<td style="width: 25%"></td>
<td style="width: 25%">

<form:form	method="post" action="actionAutenticate.jspx" cssClass="form-clasico">

<fieldset>
		<legend>Ingreso al Sistema</legend>
			
	<table class="table" style="width: 100%; ">
		<tr>
			<td style="text-align: right; vertical-align: middle;"><label>Usuario:</label></td>
			<td style="text-align: left; vertical-align: bottom;"><input
				type="text" name="login" id="login"  style="width: 100%;"></td>
		</tr>
		<tr>
			<td style="text-align: right; vertical-align: middle;"><label>Contrase&ntilde;a</label></td>
			<td style="text-align: left; vertical-align: bottom;"><input
				type="password" name="password" id="password"  style="width: 100%;"></td>
		</tr>
		<tr>
			<td style="text-align: right; vertical-align: middle;"><label>Perfil</label>
			</td>
			<td style="text-align: left; vertical-align: bottom;"><select
				name="listaPerfilUsuario" id="listaPerfilUsuario" style="width: 100%;">
				<option value="-1"><c:out value="-- Seleccione --"></c:out></option>
				<c:forEach items="${perfilUsuarios}" var="perfil">
					<option value="${perfil.perfilUsuarioID}"><c:out
						value="${perfil.descripcionPerfil}" /></option>
				</c:forEach>
			</select></td>

		</tr>
		<tr>
			<td style="text-align: right;" colspan="2">
			<a href="${pageContext.request.contextPath}/principal/showCrearPerfil.jspx" style="color:#619850;font-size:14px; padding-right: 25px;">
				Registrar Perfil</a>
			<input type="submit"
				id="Buscar" name="Buscar" value="Ingresar" />
					
		    </td>
		</tr>
		<tr>
			<td colspan="2" class="mensajeError">
			<c:out	value="${labelError}" /> <c:out value="${messageError}" /></td>
		</tr>
	</table>
</fieldset>
</form:form>
	
</td>
<td style="width: 25%"></td>
</tr>
</table>
