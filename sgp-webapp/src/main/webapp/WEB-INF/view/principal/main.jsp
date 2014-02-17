<%@ include file="/common/taglibs.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=5, IE=8, IE=9, IE=10">

<title>Sistema de Gesti&oacute;n Proyectos - Fondam</title>

<%@ include file="/common/includesTaglibsGenerico.jsp"%>

<style type="text/css">
div.ui-datepicker {
	font-size: 62.5%;
	background: #ade89f;
}

div.ui-dialog {
	font-size: 62.5%;
}

div.ui-accordion {
	font-size: 60.5%;
}
</style>

<script type="text/javascript">

	$(document)
			.ready(
					function() {
						/*$("li a").mousedown(function() {
							$('.clickeado').removeClass('clickeado');
							$('a').addClass('imp');

						}).mouseup(function() {
							//alert("hola");
							$('#' + $(this).attr('id')).removeClass('imp');
							$('#' + $(this).attr('id')).addClass('clickeado');
						});*/

						var cantObservaciones = "<c:out value="${cantObservaciones }"></c:out>";
						var cantMuestraMensajeObs = "<c:out value="${cantMuestraMensajeObs }"></c:out>";
						//alert(cantMuestraMensajeObs);
						if ((cantObservaciones > 0) && (cantMuestraMensajeObs==0)) {
							alert("Tiene " + cantObservaciones
									+ " Observaciones no levantadas");
						}

					});
</script>


<script type="text/javascript">
	function noBack() {
		window.history.forward();
	};
	noBack();
	window.onload = noBack;
	window.onpageshow = function(evt) {
		if (evt.persisted)
			noBack();
	};
	window.onunload = function() {
		void (0);
	};
</script>

</head>
<body class="bodyGeneral">
	<div id="main">
		<table width="100%" >
			<tr>
				<td colspan="2" style="width: 100%;">
					<div id="mainCabecera" >
						<tiles:insertAttribute name="cabecera" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="width: 100%;">
					<div id="mainBienvenida">
						<tiles:insertAttribute name="mensajesBienvenida" />
					</div></td>
			</tr>
			<tr>
				<td style="width: 20%; vertical-align: top;background: url(<c:url value="/images/img_btn04_sinPie.gif"></c:url>) repeat;">
					<div id="mainMenu">
							<tiles:insertAttribute name="menu" />
						</div>
					</td>
				<td style="width: 80%; vertical-align: top;">
					<div id="mainCuerpo">
						<tiles:insertAttribute name="body" />
					</div></td>
			</tr>
			<tr>
				<td colspan="2" style="width: 100%;">
					<div id="mainFoot">
						<tiles:insertAttribute name="foot" />
					</div></td>
			</tr>
		</table>
	</div>
</body>


</html>