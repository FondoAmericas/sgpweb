<%@ include file="/common/taglibs.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
 <%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<html>
<head>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/gestorAjax.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/AtssefGrid.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/AtssefCBX.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/util.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/AtssefValidadorForm.js"></script>

<title>Insert title here</title>

<script type="text/javascript">
$(document).ready(function() {
	cargaGrillaProyectosPorEvaluar();
});

/***************combobox AtssefCBX****************/
var VALUE_INI_CBX = "0";
var TEXT_INI_CBX = "-- Seleccionar --";
/***************combobox AtssefCBX****************/
 
/* objetos AJAX*/
var objXHR58890;//COMBOBOX RESULTADOS
var objXHR58891;//COMBOBOX PROGRAMA 
var objXHR58892;//COMBOBOX MODALIDAD FINANCIERA
/* fin objetos AJAX*/
 
var filtroBusqueda;
var programaID;
var opcionBusqueda;

/* objetos de AtssefCBX */
var objCBXTipoMoneda;
var objCBXEstadosDesembolso;
var objCBXPrograma;
var objCBXModalidadFinan;
/* fin objetos de AtssefCBX */


/* objetos de AtssefGrid */
var objGRIDSolicitudesDesembolso;
var objGRIDDetalleDesembolso;
/* objetos de AtssefGrid */


var arraySolicitudesDesembolso = new Array();
var objInnerDesembolso;
var arrayEstadosDesembolso = new Array();
var arrayTipoMoneda = new Array();

var arrayPrograma=new Array();
var arrayModalidadFinan = new Array();
	
/***************FIN GLOBALES**********************************************/

/***************COMBOBOX RESULTADOS**********************************************/
	
	function cargarDataCBXResultados(){
		objXHR25869 = new ObjetoAJAX();
		objXHR25869.enviar("cargarCBXResultado.jspx","post","llenarDataCBXResultado",null);
	}
	function llenarDataCBXResultado(){
		arrayResultados=JSON.parse(objXHR25869.respuestaTexto());
		//alert(JSON.stringify(arrayResultados));
		rederizarCBXResultados();
	}
	function rederizarCBXResultados(){
		objCBXResultados = new AtssefCBX({
						  AttributeCBX : { id:"cbxResultado" , name:"cbxResultado" , style:"width:320px"},
	                      AttributeOPIni : { value: VALUE_INI_CBX , text: TEXT_INI_CBX},
						  dataCBX : arrayResultados,
						  IDContenedor : "div_cbxResultado",
						  funcionCBX : {
										onchange:{estado:"1", funcion:"cargarDataCBXActividades();"}
						               }
	                     });		
	}

function rederizarCBXTipoMoneda(){
	objCBXTipoMoneda = new AtssefCBX({
					  AttributeCBX : { id:"cbxTipoMoneda" , name:"cbxTipoMoneda" ,style:"width:70px"},
                      AttributeOPIni : { value: VALUE_INI_CBX , text: "---moneda---"},
					  dataCBX : arrayTipoMoneda,
					  IDContenedor : "div_cbxTipoMoneda",
					  funcionCBX : {
									onchange:{estado:"0", funcion:"alert('tipo desem')"}
					               }
                     });		
}

function rederizarCBXEstadosDesembolso(){
	objCBXEstadosDesembolso = new AtssefCBX({
					  AttributeCBX : { id:"cbxEstadoDesembolso" , name:"cbxEstadoDesembolso" ,style:""},
                      AttributeOPIni : { value: VALUE_INI_CBX , text: "--estado---"},
					  dataCBX : arrayEstadosDesembolso,
					  IDContenedor : "div_cbxEstadoDesembolso",
					  funcionCBX : {
									onchange:{estado:"0", funcion:"alert('tipo desem')"}
					               }
                     });		
}



/***************OPCION DE BUSQUEDA*****************/
function cambiarFiltro(){
	opcionBusqueda=document.getElementById("cbxOpBusDesembolso").value;

	if(opcionBusqueda=="1"){ //por nombre de proyecto
		
		$("#div_txtNomProyecto").show('slow');
		document.getElementById("txtNomProyecto").focus();
		$("#txtCodigoProyecto").attr("value","");
		$("#div_txtCodigoProyecto").hide();
		
	}
	else if(opcionBusqueda=="2"){ //por codigo de proyecto
		
		$("#div_txtCodigoProyecto").show('slow');
		document.getElementById("txtCodigoProyecto").focus();
		$("#txtNomProyecto").attr("value","");
		$("#div_txtNomProyecto").hide();
		
	}
	/*else{
		ocultarCamposPrograma();

	}*/
}
String.prototype.trim = function() { return this.replace(/^\s+|\s+$/g, ""); };


/****************VALIDACION Y REGISTRO AUTORIZACION DESEMBOLSO**********************/
/*
function RegistrarAutorizarDesembolso(){
	
	var estado=document.getElementById("cbxEstadoDesembolso").value;// 24:rechazado
	if(estado != 24){
		if(validarFormulario()){
			var montoSolicitado  = parseFloat(objInnerDesembolso.montoSolicitado);
			var montoAutorizado  = parseFloat(document.getElementById("txtMontoAutorizado").value);
			console.log("montos :: montoSolicitado  :: montoAutorizado"+montoSolicitado+" :: "+montoAutorizado);
			if(montoAutorizado > montoSolicitado){
				alert("monto autorizado es mayor a monto solicitado!");
			}else{
				//alert("Registrar!!!");			
				document.getElementById("hiddenDesembolsoID").value = objInnerDesembolso.desembolsoID;
				document.getElementById("aprobDesembolsoForm").submit();
		    	alert("Se registro solicitud de desembolso!");
			}						
		}		
	}else{
		document.getElementById("hiddenDesembolsoID").value = objInnerDesembolso.desembolsoID;
		document.getElementById("aprobDesembolsoForm").submit();
    	alert("Se registro solicitud de desembolso!");		
	}
	
}

function validarFormulario(){
	var objFildsForm = new AtssefValidadorForm([
	                                  		  {valorInicial:"", id:"txtMontoAutorizado", msj:"ingresar monto a autorizar!"},
	                                  		  {valorInicial:"0", id:"cbxTipoMoneda", msj:"seleccionar tipo moneda!"},
	                                  		  {valorInicial:"0", id:"cbxEstadoDesembolso", msj:"selecionar estado desembolso!"}	                                  		
	                                  		  ]);		
	                                  		return objFildsForm.validar();
}
*/
/****************FIN VALIDACION Y REGISTRO AUTORIZACION DESEMBOLSO**********************/
 

/****************COMBOBOX MODALIDAD FINANCIERA**********************/
   
function cargarDataCBXModalidadFinan(){
	  objXHR58892 = new ObjetoAJAX();
	  objXHR58892.enviar("cargarDataCBXModalidadFinan.jspx","post","llenarDataCBXModalidadFinan",null);
	}
	
function llenarDataCBXModalidadFinan(){
	arrayModalidadFinan=JSON.parse(objXHR58892.respuestaTexto());
		//alert(JSON.stringify(arrayModalidadFinan));
		renderizarCBXModalidadFinan();
	}
 
function renderizarCBXModalidadFinan(){
	objCBXModalidadFinan = new AtssefCBX({
					  AttributeCBX : { id:"cbxModalidadFinan" , name:"cbxModalidadFinan" ,style:""},
                      AttributeOPIni : { value: VALUE_INI_CBX , text: "-- Modalidad --"},
					  dataCBX : arrayModalidadFinan,
					  IDContenedor : "div_cbxModalidadFinan",
					  funcionCBX : {
									onchange:{estado:"1", funcion:"cargarDataCBXPrograma()"}
					               }
                     });		
}
/****************COMBOBOX MODALIDAD FINANCIERA**********************/


/****************COMBOBOX PROGRAMA**********************/
 
function cargarDataCBXPrograma(){
	objXHR58891 = new ObjetoAJAX();
	var url = "cargarDataCBXPrograma.jspx?" +
    "idModFinan=" + encodeURIComponent(objCBXModalidadFinan.getIdAtssefCBX());
	objXHR58891.enviar(url,"get","llenarDataCBXPrograma",null);
	}

function llenarDataCBXPrograma(){
		arrayPrograma=JSON.parse(objXHR58891.respuestaTexto());
		//alert(JSON.stringify(arrayPrograma));
		renderizarCBXPrograma();
	}
 
function renderizarCBXPrograma(){
	objCBXPrograma = new AtssefCBX({
					  AttributeCBX : { id:"cbxPrograma" , name:"cbxPrograma" ,style:""},
                      AttributeOPIni : { value: VALUE_INI_CBX , text: "-- Programa --"},
					  dataCBX : arrayPrograma,
					  IDContenedor : "div_cbxPrograma",
					  funcionCBX : {
									onchange:{estado:"0", funcion:"alert('tipo desem')"}
					               }
                     });		
}
/****************FIN COMBOBOX PROGRAMA**********************/

 
/***************INICIO**********************************************/

//$(window).load(function() {
$(document).ready(function() {
	
/****************COMBOBOX MODALIDAD FINANCIERA**********************/
	 cargarDataCBXModalidadFinan();
/****************COMBOBOX MODALIDAD FINANCIERA**********************/

/***********OPCIONES DE BUSQUEDA**********/	
	$("#div_txtNomProyecto").hide();	
	$("#div_txtCodigoProyecto").hide();

/***********DETALLE SOLICITUDES DESEMBOLSO**********/
	//ocultarDetalleDesembolso();
/***********DETALLE SOLICITUDES DESEMBOLSO**********/
});

/***************FIN INICIO**********************************************/

</script>

<script type="text/javascript">
function cargaGrillaProyectosPorEvaluar(){
	$("#grillaDesembolsosPorEvaluar").load("cargaGrillaDesembolsoPorEvaluar.jspx",{
		programaId : $("#cbxPrograma").val(),
		filtroNombre : $("#txtNomProyecto").val(),
		filtroCodigo : $("#txtCodigoProyecto").val(),
		opcionBusqueda : $("#cbxOpBusDesembolso").val()
	});
	
}

function buscarProyectosPorPrograma(){
	var valida = validaCombos();
	
	if(valida==0){
		cargaGrillaProyectosPorEvaluar();
	}	
}

</script>

<script type="text/javascript">
	/*function agregarObservacion(tablaId,datoProyectoID,tablaClaseId,tablaProfundidadId,claseId){
		var url = "showGestionarObservacion.jspx?tablaId=" + tablaId + "&datoProyectoID="+datoProyectoID+"&tablaClaseId="+tablaClaseId+"&tablaProfundidadId="+tablaProfundidadId+"&claseId="+claseId;
		var stiloPopUp = 'dialogWidth=800px; dialogHeight=500px; dialogTop=70px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
		//ventanaPopUp = showModalDialog(url,'',stiloPopUp);//window.open(url,'',stiloPopUp);

		//window.opener.document.formWindowAsignaTutor.profesorAsignado.value = componenteId;

		//window.showModalDialog(url,datos,stiloPopUp);
		window.showModalDialog(url, "", stiloPopUp);

		}*/
	
	function validaCombos() {
		var errores = 0;
		var mensaje = "";

		if ($("#cbxModalidadFinan").val() == 0) {
			mensaje += "Debe seleccionar una modalidad de financiamiento. \n";
			errores = errores + 1;
		}
		
		if ($("#cbxPrograma").val() == 0) {
			mensaje += "Debe seleccionar un programa. \n";
			errores = errores + 1;
		}

		if (errores > 0) {
			alert(mensaje);
		}

		return errores;
	}

</script>
</head>

<body>
<form method="post" action="registrarAutoDesembolso.jspx" class="form-clasico" accept-charset="UTF-8" id="aprobDesembolsoForm">
<div class="encabezado">AUTORIZAR DESEMBOLSO</div>
		<br>
  <div id="desembolsosEvaluar_div">
    
  		<fieldset>
  			<legend>DESEMBOLSOS POR EVALUAR</legend>
  			<input type="hidden" id="ruta" name="ruta" style="width: 550px;" value="Esta es la ruta: ${ruta }" >
                <table width="100%" >
                	<tr>
						<td  style="text-align: right; width: 25%;">
						    <label>Modalidad Financiamiento:</label>
					    </td>
						<td style="text-align: left; width: 25%;">
						    <div id="div_cbxModalidadFinan">
	    					    <select name="cbxModalidadFinan" id="cbxModalidadFinan">
							       <option value="0">-- Modalidad --</option>
							    </select>
							</div>
	                    </td>
						<td style="text-align: right; width: 25%;">
							<label>Programa:</label>
					    </td>
						<td style="text-align: left; width: 25%;">
							<div id="div_cbxPrograma">
	    					    <select name="cbxPrograma" id="cbxPrograma">
							       <option value="0">-- Programa --</option>
							    </select>
							</div>
						</td>
			    	</tr>	                
					<tr>
						<td style="text-align: right; width: 25%;">
						    <label>Buscar por:</label>
					    </td>
						<td style="text-align: left; width: 25%;">
						    <div id="div_cbxOpBusDesembolso">
	    					    <select name="cbxOpBusDesembolso" id="cbxOpBusDesembolso" onchange="cambiarFiltro()">
							       <option value="0">-- Seleccionar --</option>
							       <option value="1">Por nombre proyecto</option>
							       <option value="2">Por codigo proyecto</option>
							    </select>
							</div>
	                    </td>
	                    <td style="width: 25%;"></td>
	                    <td style="text-align: left; width: 25%;">
							<input type="button" name="btnBusProg" id="btnBusProg" value="Buscar" onclick="buscarProyectosPorPrograma()" />
						</td>
	                    </tr>
	                    <tr>
	                    <td style="width: 25%;"></td>
						<td colspan="3" style="width: 75%;text-align: left;">
						    <div id="div_txtNomProyecto">
				    			<input type="text" id="txtNomProyecto" name="txtNomProyecto" style="width: 95%;"/>
				 			</div>
				 			<div id="div_txtCodigoProyecto">
				    			<input type="text" id="txtCodigoProyecto" name="txtCodigoProyecto" style="width: 95%;"/>
				 			</div>
					    </td>
						
			    	</tr>		
				</table>
				 <br />
     		<div id="grillaDesembolsosPorEvaluar"></div>
  		</fieldset>      
 </div>
 <input type="hidden" id="hiddenDesembolsoID" name="hiddenDesembolsoID" value="${perfilUsuario}">
</form>
<input type="hidden" id="hiddenPerfilUsuario" name="hiddenPerfilUsuario" value="${perfilUsuario}">
<input type="hidden" id="hiddenPeriodoProyecto" name="hiddenPeriodoProyecto" value="${periodoProyecto}">
</body>
</html>