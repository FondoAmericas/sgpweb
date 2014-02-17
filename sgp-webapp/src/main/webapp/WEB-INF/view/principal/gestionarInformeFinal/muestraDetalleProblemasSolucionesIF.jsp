<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detalle de Problemas</title>
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
		<div class="encabezado">Detalle de Problemas</div>
		<br/>
		<fieldset>
		<div style="text-align: left;">
			<label>Problema Relevante al Proyecto: </label>
			<c:choose>
				<c:when test="${problemaSolucion.problemaRelevanteAlProy==1 }">
					<label style="color: blue;">SI</label>
				</c:when>
				<c:otherwise>
					<label style="color: red;">NO</label>
				</c:otherwise>
			</c:choose>
		</div> 
		<br/>
		<fieldset style="padding-left: 10px">
			<legend>Problema</legend>
			<div style="width: 100%; text-align: justify;">
				<label><c:out value="${problemaSolucion.problema }"></c:out>
				</label>
			</div>
		</fieldset>
		<br>
		<fieldset style="padding-left: 10px">
			<legend>Solución</legend>
			<div style="width: 100%; text-align: justify;">
				<label><c:out value="${problemaSolucion.solucion }"></c:out>
				</label>
			</div>
		</fieldset>
		<br>
		<fieldset style="padding-left: 10px">
			<legend>Resultado</legend>
			<div style="width: 100%; text-align: justify;">
				<label><c:out value="${problemaSolucion.resultado }"></c:out>
				</label>
			</div>
		</fieldset>
		<br>
		<fieldset style="padding-left: 10px">
			<legend>Comentario</legend>
			<div style="width: 100%; text-align: justify;">
				<label><c:out value="${problemaSolucion.comentario }"></c:out>
				</label>
			</div>
		</fieldset>
</fieldset>
	</div>
</body>
</html>