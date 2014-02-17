<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Apreciación de Resultados</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/viewMain.css" ></c:url>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/estiloContabilidad.css"></c:url>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/estiloGeneral.css"></c:url>" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/gestorAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/AtssefGrid.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/validador.js"></script>

</head>
<body>
	<div class="form-clasico">
		<div class="encabezado">Detalle de Apreciación de Resultados</div>
		<br>
		<c:forEach items="${listApreciacionResultadoBean }" var="apreciacionResultadoBean" >
		<fieldset style="padding-left: 10px">
			<legend><c:out value="${apreciacionResultadoBean.descripcionTipoApreciacionResultadoRa }"></c:out> </legend>
			<div style="width: 100%; text-align: justify;">
				<label><c:out value="${apreciacionResultadoBean.comentario }"></c:out>
				</label>
			</div>
		</fieldset>
		<br>
		</c:forEach>
		
	</div>
</body>
</html>