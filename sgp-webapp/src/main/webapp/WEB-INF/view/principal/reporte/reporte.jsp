<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/viewMain.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloContabilidad.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloGeneral.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.5.2.min.js"></script>

<script type="text/javascript">
	function mainmenu() {
		// Oculto los submenus
		$(" #navReporte ul ").css({
			display : "none"
		});
		// Defino que submenus deben estar visibles cuando se pasa el mouse por encima
		$(" #navReporte li").hover(function() {
			$(this).find('ul:first:hidden').css({
				visibility : "visible",
				display : "none"
			}).slideDown(400);
		}, function() {
			$(this).find('ul:first').slideUp(400);
		});
	}
	$(document).ready(function() {
		mainmenu();
	});
	function openWindow(url){
		window.open(url,'','width=900,height=850,left=0,top=50,screenX=0,screenY=50');
	}
	function goTo(url) {
		window.location = url;
	}
</script>

<style type="text/css">
#menuReporte {
	background-repeat: repeat-x;
	margin: auto;
	padding-left: 1px;
	padding-top: 1px;
	font-size: 12px;
}

#navReporte {
	list-style: none;
}

#navReporte li {
	border: thin groove #1f5f20;
	font-weight: bold;
	color: #1f5f20;
	/*font-style: italic;*/
	/*background-color:#ffffff;*/
	float: left;
	/*background-image: url(nav_li_bg.png);*/
	background-repeat: no-repeat;
	background-position: right;
}

#navReporte li a {
	display: block;
	padding: 7px 10px;
	text-decoration: none;
	color: #1f5f20;
	font-weight: bold;
}

#navReporte li a:hover {
	color: #1f5f20;
	text-decoration: underline;
}
/* Submenu */
#navReporte ul.submenuReporte { /*border: 0px solid #1f5f20;*/
	padding: 5px;
	position: absolute;
	list-style: none;
	/*background-color: #333333;*/
}

#navReporte ul.submenuReporte li {
	float: none;
	width: auto;
	/*background-image: none;
	border-bottom: 0px solid #999999;
	width: 200px;*/
}
</style>

</head>
<body>
	<div id="menuReporte" style="height: 450px; background-color: white;">
		<ul id="navReporte">
			<li><a href="#">Seleccionar Reporte</a>
				<ul class="submenuReporte">
					<li><a href="javascript:openWindow('reportPlanOperativoResultados.jspx')">Plan Operativo - Resultados</a></li>
					<li><a href="javascript:openWindow('reportPlanOperativoCronogramaActividades.jspx')">Plan Operativo - Cronograma de Actividades</a></li>
					<li><a href="javascript:goTo('reportCostoActividadFuente.jspx')">Plan Operativo - Costo Actividad por Fuente</a></li>
					<li><a href="javascript:openWindow('reportPlanOperativoEstructuraInversionFinanciamiento.jspx')">Plan Operativo - Estructura de Inversion y Financiamiento</a></li>
					<li><a href="javascript:goTo('reportContribucionDonacionFuente.jspx')">Plan Operativo - Contribucion de la Donacion por Fuente</a></li>
					<li><a href="javascript:openWindow('reportPlanOperativoBeneficiariosAreaIntervencionDirectos.jspx')">Plan Operativo - Beneficiarios en el Area de Intervencion(Directos)</a></li>
					<li><a href="javascript:openWindow('reportPlanOperativoOperacionesCostosCronogramaResultados.jspx')">Plan Operativo - Operaciones y Costos(Cronograma de Resultados)</a></li>
					<li><a href="javascript:openWindow('reportPlanOperativoContraPartida.jspx')">Plan Operativo - ContraPartida</a></li>
					<li><a href="javascript:openWindow('reportPlanOperativoDesembolsoRecursosDonacion.jspx')">Plan Operativo - Desemboloso de Recursos de Donacion</a></li>
					
				</ul>
			</li>
		</ul>
	</div>
</body>
</html>