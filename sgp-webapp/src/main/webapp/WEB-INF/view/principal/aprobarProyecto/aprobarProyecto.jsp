<%@ include file="/common/taglibs.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/viewMain.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloContabilidad.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloGeneral.css" />
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/gestorAjax.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/AtssefGrid.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/AtssefCBX.js"></script>

<script type="text/javascript">


//COMBOBOX ESTADO PROYECTO
var arrayEstadoProyecto = new Array();
var objXHR58822635;
var objCBXEstadoProyecto;
//COMBOBOX ESTADO PROYECTO

/****************COMBOBOX ESTADO PROYECTO**********************/
   
function cargarDataCBXEstadoProyecto(){
	  objXHR58822635 = new ObjetoAJAX();
	  objXHR58822635.enviar("cargarDataCBXEstadoProyecto.jspx","post","llenarDataCBXEstadoProyecto",null);
	}
	
function llenarDataCBXEstadoProyecto(){
	arrayEstadoProyecto=JSON.parse(objXHR58822635.respuestaTexto());
		//alert(JSON.stringify(arrayEstadoProyecto));
		renderizarCBXEstadoProyecto();
	}
 
function renderizarCBXEstadoProyecto(){
	objCBXEstadoProyecto = new AtssefCBX({
					  AttributeCBX : { id:"cbxEstProg" , name:"cbxEstProg" ,style:""},
                      AttributeOPIni : { value: "0" , text: "---ESTADO---"},
					  dataCBX : arrayEstadoProyecto,
					  IDContenedor : "div_cbxEstProg",
					  funcionCBX : {
									onchange:{estado:"0", funcion:"cargarDataCBXPrograma()"}
					               }
                     });		
}
/****************FIN COMBOBOX ESTADO PROYECTO**********************/







   /* PROGRAMAS */

var filtroBusqueda;
var opcionBusqueda;
function cambiarFiltro(){
	opcionBusqueda=document.getElementById("cbxOpBusProg").value;

	if(opcionBusqueda=="1"){
		
		$("#div_cbxModFinan").hide();
		$("#div_cbxTipoCuenta").hide();
		$("#div_opTextBusProg").show('slow');
		$("#div_txtNomPrograma").show('slow');
		document.getElementById("txtNomPrograma").focus();
	}
	else if(opcionBusqueda=="2"){
		
		$("#div_cbxTipoCuenta").hide();
		$("#div_txtNomPrograma").hide();
		$("#div_opTextBusProg").show('slow');
		$("#div_cbxModFinan").show('slow');
		document.getElementById("cbxModFinan").focus();
	}
	else if(opcionBusqueda=="3"){
		
		$("#div_txtNomPrograma").hide();
		$("#div_cbxModFinan").hide();
		$("#div_opTextBusProg").show('slow');
		$("#div_cbxTipoCuenta").show('slow');
		document.getElementById("cbxTipoCuenta").focus();
	}
	else{
		ocultarCamposPrograma();

	}
}

function ocultarCamposPrograma(){
	$("#div_txtNomPrograma").hide();
	$("#div_cbxModFinan").hide();
	$("#div_cbxTipoCuenta").hide();
	$("#div_opTextBusProg").hide();
	
}


function onclick_btnBusProg(){
	var validacion=true;
	if(document.getElementById("cbxOpBusProg").value=="0"){
		alert("Seleccionar tipo de busqueda!");
		validacion=false;
	}else{
		if(opcionBusqueda=="1"){
			if(document.getElementById("txtNomPrograma").value.trim().length==0){
				alert("ingresar nombre del programa!");
				validacion=false;
			}	
		}
		if(opcionBusqueda=="2"){
			if(document.getElementById("cbxModFinan").value=="0"){
				alert("seleccionar modalidad financiera del programa!");
				validacion=false;
			}	
		}
		if(opcionBusqueda=="3"){
			if(document.getElementById("cbxTipoCuenta").value=="0"){
				alert("seleccionar tipo de cuenta del programa!");
				validacion=false;
			}	
		}
	}
	
	if(validacion){
		if(opcionBusqueda=="1"){
			filtroBusqueda=document.getElementById("txtNomPrograma").value;
		}
		if(opcionBusqueda=="2"){
			filtroBusqueda=document.getElementById("cbxModFinan").value;
		}
		if(opcionBusqueda=="3"){
			filtroBusqueda=document.getElementById("cbxTipoCuenta").value;
		}
		
		document.getElementById("opcionBusqueda").value=opcionBusqueda;
		document.getElementById("filtroBusqueda").value=filtroBusqueda;
		cargaGrillaProgramas();
		//alert("ok");
	}
}
String.prototype.trim = function() { return this.replace(/^\s+|\s+$/g, ""); };


var objXHR1;
var listProgramas=new Array();
var tablaGrid111;
function cargaGrillaProgramas(){
	var form = document.forms[0];
	objXHR1 = new ObjetoAJAX();
	objXHR1.enviar(form.action,"post","llenaGrillaProgramas",form);
}

function llenaGrillaProgramas(){
	//alert(objXHR1.respuestaTexto());
	var listadoProgramas=JSON.parse(objXHR1.respuestaTexto());
	listProgramas = listadoProgramas[0];
	//alert(JSON.stringify(listProgramas));
	//alert("tamano "+listProgramas.length);
	
	if(listProgramas.length==0){
		limpiarCamposPrograma();
		borrarDatosGrilla111();
		armarGrilla111();
		$("#div_msjResultadosProgramas").show('slow');	
	}else{
		$("#div_msjResultadosProgramas").hide(); 
		//alert("entro");
		armarGrilla111();
		limpiarCamposPrograma();	
	}
	

}



function limpiarCamposPrograma(){
	document.getElementById("cbxOpBusProg").value="0";
	document.getElementById("txtNomPrograma").value="";
	document.getElementById("cbxModFinan").value="0";
	document.getElementById("cbxTipoCuenta").value="0";
	ocultarCamposPrograma();
}

function borrarDatosGrilla111(){
	listProgramas=new Array();
}

function armarGrilla111(){
	tablaGrid111=new AtssefGrid({
	 "cabecera":["Programa","Modalidad Finan.","Tipo Cuenta","Duracion (meses)"],
	 "campos_cabecera":[
						{name:"nombrePrograma"},
						{name:"modalidadFinancia"},
						{name:"tipoCuenta"},
						{name:"duracionPrograma"}
					   ],
	 "arrayDataTabla":listProgramas,
	 "id_contenedor":"tabla111",
	 "atributos_tabla":{ width:"100%",
	                     align:"left",
						 class:"table-clasico"
	                    },
	 "titulo_tabla":{estado:"si",titulo:"Listado de programas"},
	 "campos_extra_tabla":{
							"detalle":{estado:"si",titulo:"",textoCampo:"Ver Proyectos",funcionDetalle:"listarProyectosPorPrograma();"},
						    "eliminarFila":{estado:"no",funcionEliminarFila:"eliminarIndicador();"}
	                     },
	 "numeracion":"si",
	 "objTabla":"tablaGrid111",
	 "estiloTabla":[{"tablaCebra":"no",
	                 "color":{filaPar:"#BBBBBB",filaImpar:"#D4D4D4",filaOver:"#DEB87F",filaSel:"brown"}
					},
					{"funcion":{apuntarOverOut:"no",sel:"no"}}
				   ],	   
	 "paginacionTabla":{"pagTabla":"no","paginado":10},
	 "espacio_entre_paginacion_gestion":19,
	 "gestionDatos":{		
		                    "gestion":"no",
	                        "agregar":"si",
							"modificar":"si",
							"eliminar":"si"
	                     },
	 "alineacion_de_paginacion_gestion":"right"
	 
	});
	}
	
	/* PROYECTOS */
	
	
var filtroBusquedaProy;
var opcionBusquedaProy;
function cambiarFiltroProy(){
	opcionBusquedaProy=document.getElementById("cbxOpBusProy").value;

	if(opcionBusquedaProy=="1"){
		
		$("#div_txtNomProyecto").hide();
		$("#div_opTextBusProy").show('slow');
		$("#div_txtCodProyecto").show('slow');
		document.getElementById("txtCodProyecto").focus();
	}
	else if(opcionBusquedaProy=="2"){
		
		$("#div_txtCodProyecto").hide();
		$("#div_opTextBusProy").show('slow');
		$("#div_txtNomProyecto").show('slow');
		document.getElementById("txtNomProyecto").focus();
	}
	else{
		ocultarCamposProyecto();

	}
}

function ocultarCamposProyecto(){
	$("#div_txtNomProyecto").hide();
	$("#div_txtCodProyecto").hide();
	$("#div_opTextBusProy").hide();
	
}

	
function onclick_btnBusProy(){
	var validacion=true;
	
	if(document.getElementById("cbxOpBusProy").value=="0"){
		alert("Seleccionar tipo de busqueda!");
		validacion=false;
	}else{
		if(opcionBusquedaProy=="1"){
			if(document.getElementById("txtCodProyecto").value.trim().length==0){
				alert("ingresar codigo del proyecto!");
				validacion=false;
			}	
		}
		if(opcionBusquedaProy=="2"){
			if(document.getElementById("txtNomProyecto").value.trim().length==0){
				alert("ingresar nombre del proyecto!");
				validacion=false;
			}	
		}
	}
	
	if(validacion){
		if(opcionBusquedaProy=="1"){
			filtroBusquedaProy=document.getElementById("txtCodProyecto").value;
		}
		if(opcionBusquedaProy=="2"){
			filtroBusquedaProy=document.getElementById("txtNomProyecto").value;
		}
	
		document.getElementById("opcionBusquedaProy").value=opcionBusquedaProy;
		document.getElementById("filtroBusquedaProy").value=filtroBusquedaProy;
		cargaGrillaProyectos();
	}
}


var idPrograma;
var objXHR23540;
var listProyectos=new Array();
function listarProyectosPorPrograma(){
	mostrarProyectos();
	//buscarProyectosPorPrograma 
	//alert("obtener proyectos por: "+ listProgramas[tablaGrid1.getIndiceArray()].programaID);
	
	document.getElementById("nombreProgramaTit").innerHTML=listProgramas[tablaGrid111.getIndiceArray()].nombrePrograma;
	document.getElementById("idPrograma").value=listProgramas[tablaGrid111.getIndiceArray()].programaID;
	idPrograma=listProgramas[tablaGrid111.getIndiceArray()].programaID;
	
	var form = document.forms[1];
	objXHR23540 = new ObjetoAJAX();
	objXHR23540.enviar(form.action,"post","llenaGrillaProyectos",form);	
}

function cargaGrillaProyectos(){
	document.getElementById("idPrograma2").value=idPrograma;
	var form = document.forms[2];
	objXHR23540 = new ObjetoAJAX();
	objXHR23540.enviar(form.action,"post","llenaGrillaProyectos",form);	
	
}

function llenaGrillaProyectos(){
	//alert(objXHR2.respuestaTexto());
	var listProyecto=JSON.parse(objXHR23540.respuestaTexto());
	listProyectos = listProyecto[0];
	//alert(JSON.stringify(listProyectos));
	
	//armarGrilla2();
	//limpiarCamposProyecto();
	
	
	if(listProyectos.length==0){
		limpiarCamposProyecto();
		borrarDatosGrilla2();
		armarGrilla2();
		$("#div_msjResultadosProyectos").show('slow');	
	}else{
		$("#div_msjResultadosProyectos").hide(); 
		armarGrilla2();
		limpiarCamposProyecto();	
	}

}
function limpiarCamposProyecto(){
	document.getElementById("cbxOpBusProy").value="0";
	document.getElementById("txtNomProyecto").value="";
	document.getElementById("txtCodProyecto").value="";
	ocultarCamposProyecto();
}

function ocultarCamposProyecto(){
	$("#div_txtNomProyecto").hide();
	$("#div_txtCodProyecto").hide();
	$("#div_opTextBusProy").hide();
}

function borrarDatosGrilla2(){
	listProyectos=new Array();
}


function mostrarDatosProyecto(){
	
	mostrarDetalleProyecto();
	document.getElementById("nombreProyectoTit").innerHTML=listProyectos[tablaGrid2.getIndiceArray()].nombreProyecto;
	//alert("codigo proyecto:"+listProyectos[tablaGrid2.getIndiceArray()].datoProyectoID);
	
	//Hacer la peticion para obtener los datos del proyecto, mandando si ID
	obtenerDatosProyecto();
}


var objXHR225;
var datosProyecto;
function obtenerDatosProyecto(){
	
	var form=document.getElementById("obtenerDatosProyecto");
	//alert("action del formulario: "+formxx.action);
	//mandar el id al hidden
	var idProy=listProyectos[tablaGrid2.getIndiceArray()].datoProyectoID;
	console.debug("id del proyecto-------------> "+idProy);
	document.getElementById("idproyecto1").value = idProy;
	
	//listProgramas[tablaGrid1.getIndiceArray()].programaID;
	//alert("idProy :"+idProy);
	objXHR225 = new ObjetoAJAX();
	objXHR225.enviar("obtenerDatosProyectoDetalle.jspx","post","mostrarDetallesProyecto",form);
	
	
}

function mostrarDetallesProyecto(){
	//alert("datos del proyecto"+objXHR225.respuestaTexto());
	datosProyecto=JSON.parse(objXHR225.respuestaTexto());
	
	document.getElementById("codProyecto").innerHTML=datosProyecto.codigoProyecto;
		document.getElementById("txtProyecto").innerHTML=datosProyecto.nombreProyecto;
		document.getElementById("modalidadFinan").innerHTML=datosProyecto.modFinanProyecto;
		document.getElementById("nombrePrograma").innerHTML=listProgramas[tablaGrid111.getIndiceArray()].nombrePrograma;
		document.getElementById("areaTematicaSpan").innerHTML=datosProyecto.areaTemProyecto;
		document.getElementById("subAreaTematicaSpan").innerHTML=datosProyecto.subAreaTemProyecto;
		document.getElementById("duracionProySpan").innerHTML=datosProyecto.duracionProyecto+" (meses)";
		
		/* OJO DESDE AQUI SE TRAEN LOS DATOS DEL PROYECTO PARA CARGARLOS EN LA PANTALLA!!!!!!!!!*/
		//obtener datos de la ubicacion del proyecto
		obtenerDataUbicacionProyecto();
		//obtener datos de las fuentes financieras
		obtenerDataIntitucionFuenFinan();		
		obtenerDataResumenProyecto();
	    //OBTENIENDO DATA CUENTAS CORRIENTES
		obtenerDataCuentasCorrientes();	
	    //OBTENIENDO DATA MARCO LOGICO E INDICADORES
	    obtenerMarcoLogicoIndicadores();
	    //OBTENIENDO ARCHIVOS
	    obtenerArchivo();
}


function obtenerArchivo(){
	document.getElementById("frameDownload").src="showArchivoDownloadPerfil2.jspx";//hace nuevamente la peticion
}


function armarGrilla2(){
	tablaGrid2=new AtssefGrid({
	 "cabecera":["Codigo","Proyecto","Duracion (meses)","Periodos","Sub Area Tem."],
	 "campos_cabecera":[
						{name:"codigoProyecto"},
						{name:"nombreProyecto"},
						{name:"duracionProyecto"},
						{name:"cantidadPeriodo"},
						{name:"descSubAreaTematica"}
					   ],
	 "arrayDataTabla":listProyectos,
	 "id_contenedor":"tabla2",
	 "atributos_tabla":{ width:"100%",
	                     align:"left",
						 class:"table-clasico"
	                    },
	 "titulo_tabla":{estado:"si",titulo:"Listado de proyectos"},
	 "campos_extra_tabla":{
							"detalle":{estado:"si",titulo:"",textoCampo:"Detalle",funcionDetalle:"mostrarDatosProyecto();"},
						    "eliminarFila":{estado:"no",funcionEliminarFila:"eliminarIndicador();"}
	                     },
	 "numeracion":"si",
	 "objTabla":"tablaGrid1",
	 "estiloTabla":[{"tablaCebra":"no",
	                 "color":{filaPar:"#BBBBBB",filaImpar:"#D4D4D4",filaOver:"#DEB87F",filaSel:"brown"}
					},
					{"funcion":{apuntarOverOut:"no",sel:"no"}}
				   ],	   
	 "paginacionTabla":{"pagTabla":"no","paginado":10},
	 "espacio_entre_paginacion_gestion":19,
	 "gestionDatos":{		
		                    "gestion":"no",
	                        "agregar":"si",
							"modificar":"si",
							"eliminar":"si"
	                     },
	 "alineacion_de_paginacion_gestion":"right"
	 
	});
	}


function aproDesProyecto(){
	if(document.getElementById("cbxEstProg").value!="0"){
		//obtener el codigo del proyecto
		//mandar el id al hidden
	    var idProy=listProyectos[tablaGrid2.getIndiceArray()].datoProyectoID;
		document.getElementById("idProy").value=idProy;
		var subAreaTem=listProyectos[tablaGrid2.getIndiceArray()].idSubAreaTematica;
		document.getElementById("subAreaTem").value=subAreaTem;
		var idProg = listProgramas[tablaGrid111.getIndiceArray()].programaID;
		document.getElementById("idProg").value=idProg;
		var cantPeriodo=listProyectos[tablaGrid2.getIndiceArray()].cantidadPeriodo;
		document.getElementById("cantPeriodo").value=cantPeriodo;
		var codProy=listProyectos[tablaGrid2.getIndiceArray()].codigoProyecto;
		document.getElementById("codProy").value=codProy;
		var duracionProy=listProyectos[tablaGrid2.getIndiceArray()].duracionProyecto;
		document.getElementById("duracionProy").value=duracionProy;
		var nombreProy=listProyectos[tablaGrid2.getIndiceArray()].nombreProyecto;
		document.getElementById("nombreProy").value=nombreProy;
		var nroOrdenProy=listProyectos[tablaGrid2.getIndiceArray()].numero_orden_dato_proyecto;
		document.getElementById("nroOrdenProy").value=nroOrdenProy;
		//grabar estado
	    document.getElementById("formAprobarProyecto").submit();
	}else{
		alert("Seleccione estado de proyecto (Aprobado/Desaprobado)");
	}
}
	
	

function ocultarDivsInicio(){
	$("#proyectos_programa_div").hide();
	$("#datos_proyecto_div").hide();
}

function mostrarProyectos(){
	$("#proyectos_programa_div").show('slow');
}

function mostrarDetalleProyecto(){
	$("#datos_proyecto_div").show('slow');
}


$(document).ready(function() {
	$("#div_cbxModFinan").hide();
	$("#div_cbxTipoCuenta").hide();
	$("#div_txtNomPrograma").hide();
	$("#div_opTextBusProg").hide();
	
	$("#div_msjResultadosProyectos").hide(); 
	
	$("#div_txtNomProyecto").hide();
	$("#div_txtCodProyecto").hide();
	$("#div_opTextBusProy").hide();
	
	$("#div_msjResultadosProgramas").hide();
	
	ocultarDivsInicio();
	armarGrilla111();
	armarGrilla1();
	armarGrilla2();
	
	/*PARA LOS DATOS DEL PROYECTO*/
	//CUENTA CORRIENTE
	cargaComboBancos();
	cargaComboTipoMoneda();
	armarGrillaBancos();
    //UBICACION DEL PROYECTO
	cargaCombosDepProvDist();
	cargaCombosEstratos();
	////INSTITUCIONES
	cargaComboTipoFuenteFinanciadora();
	//document.getElementById("cbxInstitucionEjecutora").disabled=true;
	//RESUMEN DE PROYECTO
	cargaComboTipoResumenProyecto();
	
	
	cargarDataCBXEstadoProyecto();
	
	
});

//***********************DATOS DEL PROYECTO***************************//
/////////////////////////////////CUENTA CORRIENTE//////////////////////////
var objXHR10;
var tipoMoneda;
function cargaComboTipoMoneda(){
	objXHR10 = new ObjetoAJAX();
	objXHR10.enviar("cargaComboTipoMoneda.jspx","post","cargaDataComboTipoMoneda",null);
}

function cargaDataComboTipoMoneda(){
	tipoMoneda = JSON.parse(objXHR10.respuestaTexto());
	//alert(objXHR10.respuestaTexto());
	//alert(tipoMoneda[0].length);

	//llenaComboTipoMoneda1();
	llenaComboTipoMoneda();
	//obtenerDataIntitucionFuenFinan();
}

	function llenaComboTipoMoneda1(){
	var cbxTipoMoneda = document.createElement("select");
	cbxTipoMoneda.setAttribute("id","cbxTipoMoneda1");
	cbxTipoMoneda.setAttribute("name","cbxTipoMoneda1");
	//cbxTipoResumenProyecto.onchange=function(){alert(this.value);};
	//valor por defecto
	var op = document.createElement("option");
	op.setAttribute("value","0");
	var text=document.createTextNode("----SELECCIONAR----");
	op.appendChild(text);
	cbxTipoMoneda.appendChild(op);
	//llenar valores tipo resumen de proyecto
	for(var i=0; i<tipoMoneda[0].length; i++){
		op = document.createElement("option");
		op.setAttribute("value",tipoMoneda[0][i].tablaEspecificaID);
		text=document.createTextNode(tipoMoneda[0][i].descripcionCabecera);
		op.appendChild(text);
		cbxTipoMoneda.appendChild(op);
	}
	//limpia div
  	document.getElementById("div_cbxTipoMoneda1").innerHTML="";
	//pinta combo en el div
  	document.getElementById("div_cbxTipoMoneda1").appendChild(cbxTipoMoneda);
	
}
	
	
	var arrayBancos=new Array();
	var banco;
	var tablaCuentaCorriente;
	function onclick_btnAgregarCuenta(){
		banco=new Object();
		banco.cbxBancos=document.getElementById("cbxBancos").value;
		banco.cbxBancosText=document.getElementById("cbxBancos").options[document.getElementById("cbxBancos").selectedIndex].text;
		banco.cbxTipoMoneda1=document.getElementById("cbxTipoMoneda1").value;
		banco.cbxTipoMoneda1Text=document.getElementById("cbxTipoMoneda1").options[document.getElementById("cbxTipoMoneda1").selectedIndex].text;
		banco.txtNroCuenta=document.getElementById("txtNroCuenta").value;
		arrayBancos.push(banco);
		armarGrillaBancos();
		limpiarCamposCuentaCorriente();
	}
	
	function eliminarBanco(){
		var rpt=confirm("¿Seguro que desea eliminar el indicador nro:"+(tablaCuentaCorriente.getIndiceArray()+1)+"?");
		if(rpt==true){
			arrayBancos.splice(tablaCuentaCorriente.getIndiceArray(),1);
		}
		armarGrillaBancos();
		
	}
	
	function armarGrillaBancos(){
		tablaCuentaCorriente=new AtssefGrid({
		 "cabecera":["Banco","Tipo de moneda","Nro. de cuenta"],
		 "campos_cabecera":[
							{name:"cbxBancosText"},
							{name:"cbxTipoMoneda1Text"},
							{name:"txtNroCuenta"}
						   ],
		 "arrayDataTabla":arrayBancos,
		 "id_contenedor":"tablaCuentaCorriente",
		 "atributos_tabla":{ width:"100%",
		                     align:"left",
							 class:"table-clasico"
		                    },
		 "titulo_tabla":{estado:"si",titulo:"Listado de cuentas corrientes"},
		 "campos_extra_tabla":{
								"detalle":{estado:"no",titulo:"Details",textoCampo:"Ver Detalle",funcionDetalle:"detalleX1();"},
							    "eliminarFila":{estado:"no",funcionEliminarFila:"eliminarBanco();"}
		                     },
		 "numeracion":"si",
		 "objTabla":"tablaCuentaCorriente",
		 "estiloTabla":[{"tablaCebra":"no",
		                 "color":{filaPar:"#BBBBBB",filaImpar:"#D4D4D4",filaOver:"#DEB87F",filaSel:"brown"}
						},
						{"funcion":{apuntarOverOut:"no",sel:"no"}}
					   ],	   
		 "paginacionTabla":{"pagTabla":"no","paginado":10},
		 "espacio_entre_paginacion_gestion":19,
		 "gestionDatos":{		
			                    "gestion":"no",
		                        "agregar":"si",
								"modificar":"si",
								"eliminar":"si"
		                     },
		 "alineacion_de_paginacion_gestion":"right"
		 
		});
		}

	function limpiarCamposCuentaCorriente(){
		document.getElementById("cbxBancos").value="0";
		document.getElementById("cbxTipoMoneda1").value="0";
		document.getElementById("txtNroCuenta").value="";
	}
	
	
	function testeoCuentas(){
		alert("listado cuentas: "+JSON.stringify(arrayBancos));
		
	}
	
//////////////////////////LISTADO DE BANCOS
	var objXHR102;
	var bancos;
		function cargaComboBancos(){
			objXHR102 = new ObjetoAJAX();
			objXHR102.enviar("obtenerBancos.jspx","post","cargaDataComboBancos",null);
		}

		function cargaDataComboBancos(){
			bancos = JSON.parse(objXHR102.respuestaTexto());
			//alert(objXHR102.respuestaTexto());

			//llenaComboBancos();
		}

		function llenaComboBancos(){
			var cbxBancos = document.createElement("select");
			cbxBancos.setAttribute("id","cbxBancos");
			cbxBancos.setAttribute("name","cbxBancos");
			//cbxTipoResumenProyecto.onchange=function(){alert(this.value);};
			//valor por defecto
			var op = document.createElement("option");
			op.setAttribute("value","0");
			var text=document.createTextNode("----SELECCIONAR----");
			op.appendChild(text);
			cbxBancos.appendChild(op);
			//llenar valores tipo resumen de proyecto
			for(var i=0; i<bancos[0].length; i++){
				op = document.createElement("option");
				op.setAttribute("value",bancos[0][i].tablaEspecificaID);
				text=document.createTextNode(bancos[0][i].descripcionCabecera);
				op.appendChild(text);
				cbxBancos.appendChild(op);
			}
			//limpia div
		  	document.getElementById("div_cbxBancos").innerHTML="";
			//pinta combo en el div
		  	document.getElementById("div_cbxBancos").appendChild(cbxBancos);
			
		}
		


//////////////////////////////////////UBICACION DEL PROYECTO/////////////////////////////
		var objXHR1;
		var departamentos;
		var provincias;
		var distritos;
		var depProvDist;
		
		function cargaCombosDepProvDist(){
			objXHR1 = new ObjetoAJAX();
			objXHR1.enviar("cargaCombosDepProvDist.jspx","post","llenaCombosDepProvDist",null);
		}

		function llenaCombosDepProvDist(){
			depProvDist=JSON.parse(objXHR1.respuestaTexto());
			//var depProvDist=objXHR.respuestaTexto();
			//alert(JSON.stringify(depProvDist));
			//alert(JSON.stringify(distritos));
			departamentos=depProvDist[0];
			provincias=depProvDist[1];
			distritos=depProvDist[2];
			//llenaComboDepartamentos();
			
			/* OBTENER DATOS DE UBICACION DE PROYECTO */
			//obtenerDataUbicacionProyecto();
		}

		function llenaComboDepartamentos(){
			var cbxDep = document.createElement("select");
			cbxDep.setAttribute("id", "cbxDepartamentos");
			cbxDep.setAttribute("name", "cbxDepartamentos");
			//evento para seleccionar la lista de provincias deacuerdo al departamento seleccionado.
			cbxDep.onchange=function(){llenaComboProvincias();limpiarComboDistritos();};
			//cbxDep.onchange=function(){alert(this.value);};
			//valor por defecto
			var op = document.createElement("option");
			op.setAttribute("value","0");
			var text=document.createTextNode("----SELECCIONAR----");
			op.appendChild(text);
			cbxDep.appendChild(op);
			//llenar valores de todos los departamentos
			for(var i=0; i<departamentos.length; i++){
				op = document.createElement("option");
				op.setAttribute("value",departamentos[i].depProvDistID);
				text=document.createTextNode(departamentos[i].descripcion);
				op.appendChild(text);
				cbxDep.appendChild(op);
			}
			//limpia div
			document.getElementById("div_cbxDepartamentos").innerHTML="";
			//pinta combo en el div
			document.getElementById("div_cbxDepartamentos").appendChild(cbxDep);
			
		}

		var idDepartamento;
		function llenaComboProvincias(){
			////obtener datos del departamento seleccionado para llenar sus provincias respectivas
			var depProvDistID= document.getElementById("cbxDepartamentos").options[document.getElementById("cbxDepartamentos").selectedIndex].value;
			idDepartamento=obtenerDepartamentoID(depProvDistID);
			////alert("depProvDistID del departamento seleccionado: "+depProvDistID);
			////alert("idDepartamento del departamento seleccionado: "+idDepartamento);
			var cbxProv = document.createElement("select");
			cbxProv.setAttribute("id", "cbxProvincias");
			cbxProv.setAttribute("name", "cbxProvincias");
			cbxProv.onchange=function(){llenaComboDistritos();};
			////valor por defecto
			var op = document.createElement("option");
			op.setAttribute("value","0");
			var text=document.createTextNode("----SELECCIONAR----");
			op.appendChild(text);
			cbxProv.appendChild(op);
			////almacenar todas la provincias deacuerdo al departamento seleccionado
			var provinciasPorDepartamento=new Array();
			for(var i=0; i<provincias.length; i++){
				if(provincias[i].idDepartamento==idDepartamento && provincias[i].idProvincia!=0 && provincias[i].idDistrito==0){
					provinciasPorDepartamento.push(provincias[i]);
				}
		    }
			////alert("numero de provincias para el departamento seleccionado: "+provinciasPorDepartamento.length);
			////llenar valores de todos las provincias descuerdo al departamento seleccionado
			for(var i=0; i<provinciasPorDepartamento.length; i++){
				op = document.createElement("option");
				op.setAttribute("value",provinciasPorDepartamento[i].depProvDistID);
				text=document.createTextNode(provinciasPorDepartamento[i].descripcion);
				op.appendChild(text);
				cbxProv.appendChild(op);
			}
			////limpia div
			document.getElementById("div_cbxProvincias").innerHTML="";
			////pinta combo en el div
			document.getElementById("div_cbxProvincias").appendChild(cbxProv);
		}

		function llenaComboDistritos(){
			//obtener datos de la provincia seleccionada para llenar sus distritos respectivas
			var depProvDistID= document.getElementById("cbxProvincias").options[document.getElementById("cbxProvincias").selectedIndex].value;
			var idProvincia=obtenerProvinciaID(depProvDistID);
			// 	alert("depProvDistID de la provincia seleccionada: "+depProvDistID);
			// 	alert("idProvincia de la provincia seleccionada: "+idProvincia);
			var cbxDist = document.createElement("select");
			cbxDist.setAttribute("id", "cbxDistritos");
			cbxDist.setAttribute("name", "cbxDistritos");
			//valor por defecto
			var op = document.createElement("option");
			op.setAttribute("value","0");
			var text=document.createTextNode("----SELECCIONAR----");
			op.appendChild(text);
			cbxDist.appendChild(op);
			//almacenar todas la provincias deacuerdo al departamento seleccionado
			var distritosPorProvincias=new Array();
			for(var i=0; i<distritos.length; i++){
				if(distritos[i].idDepartamento==idDepartamento && distritos[i].idProvincia==idProvincia && distritos[i].idDistrito!=0){
					distritosPorProvincias.push(distritos[i]);
				}
		    }
			//alert("numero de distritos para la provincia seleccionada: "+distritosPorProvincias.length);
			//llenar valores de todos los distritos deacuerdo a la provincia seleccionada
			for(var i=0; i<distritosPorProvincias.length; i++){
				op = document.createElement("option");
				op.setAttribute("value",distritosPorProvincias[i].depProvDistID);
				text=document.createTextNode(distritosPorProvincias[i].descripcion);
				op.appendChild(text);
				cbxDist.appendChild(op);
			}
			//limpia div
			document.getElementById("div_cbxDistritos").innerHTML="";
			//pinta combo en el div
			document.getElementById("div_cbxDistritos").appendChild(cbxDist);
			
		}

		function limpiarComboDistritos(){
			var cbxDist = document.createElement("select");
			cbxDist.setAttribute("id", "cbxDistritos");
			cbxDist.setAttribute("name", "cbxDistritos");
			//valor por defecto
			var op = document.createElement("option");
			op.setAttribute("value","0");
			var text=document.createTextNode("----SELECCIONAR----");
			op.appendChild(text);
			cbxDist.appendChild(op);
			//limpia div
			document.getElementById("div_cbxDistritos").innerHTML="";
			//pinta combo en el div
			document.getElementById("div_cbxDistritos").appendChild(cbxDist);
			
		}

		function obtenerProvinciaID(depProvDistID){
			var idProvincia=null;
			//obtener el idDistrito 
			for(var i=0; i<provincias.length; i++){
		          if(provincias[i].depProvDistID == depProvDistID){
		        	  idProvincia=provincias[i].idProvincia;break;
		          }
			}
			return idProvincia;
		}

		function obtenerDepartamentoID(depProvDistID){
			var idDepartamento=null;
			//obtener el idDepartamento 
			for(var i=0; i<departamentos.length; i++){
		          if(departamentos[i].depProvDistID == depProvDistID){
		        	 idDepartamento=departamentos[i].idDepartamento;break;
		          }
			}
			return idDepartamento;
		}
		 
		///////////////////////////////////////////////////////////////////////////////////llena estratos
		var objXHR2345;
		var estratosSoc;

		function cargaCombosEstratos(){
			objXHR2345 = new ObjetoAJAX();
			objXHR2345.enviar("cargaCombosEstratoSocial.jspx","post","llenaCombosEstratos",null);
		}

		function llenaCombosEstratos(){
			estratosSoc=JSON.parse(objXHR2345.respuestaTexto());
			//alert(estratosSoc[0].length);
			//llenaComboEstratoBenDir();
		}

		function llenaComboEstratoBenDir(){//tablaEspecificaID  descripcionCabecera
			var cbxEstBenDir = document.createElement("select");
			cbxEstBenDir.setAttribute("id", "cbxEstSocBen");
			cbxEstBenDir.setAttribute("name", "cbxEstSocBen");
			//valor por defecto
			var op = document.createElement("option");
			op.setAttribute("value","0");
			var text=document.createTextNode("----ESTRATO SOC.----");
			op.appendChild(text);
			cbxEstBenDir.appendChild(op);
			//llenar los estratos
			for(var i=0; i<estratosSoc[0].length; i++){
				op = document.createElement("option");
				op.setAttribute("value",estratosSoc[0][i].tablaEspecificaID);
				text=document.createTextNode(estratosSoc[0][i].descripcionCabecera);
				op.appendChild(text);
				cbxEstBenDir.appendChild(op);
			}
		    //limpia div
			document.getElementById("div_cbxEstSocBen").innerHTML="";
		    //pinta combo en el div
			document.getElementById("div_cbxEstSocBen").appendChild(cbxEstBenDir);	
		}

		///////////////////////////////////////////funcionalidad para la tabla ubicacion de proyectos
		var arrayUbicacionBeneficiarios=new Array();
		//var objUbicacionBeneficiarios;
		var indiceFila=0;
		var elmTBODY;
		var elmTR;
		var elmTD;


		function onclick_btnAgregarUbicacion(){

			agregarUbicacion();
			limpiarCamposUbicacion();
		}

		function agregarUbicacion(){
			cargaDatosArray();
			cargaDatosTabla();
		}
		function cargaDatosArray(){

			var cbxDep=document.getElementById("cbxDepartamentos");
			var cbxProv=document.getElementById("cbxProvincias");
			var cbxDist=document.getElementById("cbxDistritos");
			var txaLoc=document.getElementById("txaLocalizacion");
			//armado de una fila de la tabla
		    var objUbicacion= new Object();
			objUbicacion.cbxDepValue=cbxDep.value;
		    objUbicacion.cbxDepText=cbxDep.options[cbxDep.selectedIndex].text;
		   
		    objUbicacion.cbxProvValue=cbxProv.value;
		    objUbicacion.cbxProvText=cbxProv.options[cbxProv.selectedIndex].text;
		    
		    objUbicacion.cbxDistValue=cbxDist.value;
		    objUbicacion.cbxDistText=cbxDist.options[cbxDist.selectedIndex].text;
		    
		    objUbicacion.txaLoc=txaLoc.value;

		    objUbicacion.DepProvDistID=cbxDist.value;
			
			var objUbicacionBeneficiarios=new Object;
			objUbicacionBeneficiarios.ubicacion=objUbicacion;
			objUbicacionBeneficiarios.beneficiarios=null;
		      
		    arrayUbicacionBeneficiarios.push(objUbicacionBeneficiarios);
		}

		function conteoUbicaciones(){
			alert("array ubicaciones: " +JSON.stringify(arrayUbicacionBeneficiarios));
			//alert("tamano de array json: " +arrayUbicacionTabla.length);
//		 	elmTBODY = document.getElementById("tbodyUbiProy");
//		 	var tam = elmTBODY.rows.length;
//		 	alert("tamano de las filas de la tabla: "+tam);
		}

		///////////////////////////////////////////////////////////////////////////////////

		function cargaDatosTabla(){
			   limpiarTabla();
			   elmTBODY = document.getElementById("tbodyUbiProy");
			   //alert(arrayUbicacionBeneficiarios[0].ubicacion.cbxDepText);
			     for(var p=0; p<arrayUbicacionBeneficiarios.length; p++){
					 elmTR = elmTBODY.insertRow(p);
					 for (var i=0; i<6; i++) {
						elmTD = elmTR.insertCell(i);
						if(i==0){
						  var ind=indiceFila+1;
						  elmText = document.createTextNode(ind);
							var span = document.createElement("div");
							span.setAttribute("align","center");
							span.appendChild(elmText);
							 elmTD.appendChild(span);
						}
						if(i==1)
						  elmText = document.createTextNode(arrayUbicacionBeneficiarios[p].ubicacion.cbxDepText);
						if(i==2)
						  elmText = document.createTextNode(arrayUbicacionBeneficiarios[p].ubicacion.cbxProvText);
						if(i==3)
						  elmText = document.createTextNode(arrayUbicacionBeneficiarios[p].ubicacion.cbxDistText);
						if(i==4)
						  elmText = document.createTextNode(arrayUbicacionBeneficiarios[p].ubicacion.txaLoc);
						if(i==5){
						  var link = document.createElement("a");
						  link.setAttribute("class","link");
						  link.setAttribute("id",indiceFila);
						  link.onclick=function(){
							  //limpiarCamposBeneficiarios();
							  $("#div_beneficiarios").show('slow');
							  verBeneficiarios(this.id);}; //limpiarCamposInstitucion();$("#div_fuenteFinanciadora").show('slow');verFuentesFinancieras(this.id);
						  var text=document.createTextNode("Beneficiarios");
						  link.appendChild(text);
						  var div = document.createElement("div");
						  div.setAttribute("align","center");
						  div.appendChild(link);
						  elmTD.appendChild(div);
						  }
						/*
						if(i==6){
						  var imagen = document.createElement("img");
						  imagen.setAttribute("src","${pageContext.request.contextPath}/images/del.png");
						  imagen.setAttribute("id",indiceFila);
						  imagen.onclick=function(){eliminarFilaUbi(this.id);};  
						  var div = document.createElement("div");
						  div.setAttribute("align","center");
						  div.appendChild(imagen);
						  elmTD.appendChild(div);
						  }*/
						if(i!=0 && i!=5)  //i!=0 && i!=5 && i!=6
						  elmTD.appendChild(elmText);
				    }
					indiceFila++;
				 }
		}


		var indUbiBen;

		function verBeneficiarios(indInstitucion){
			indUbiBen=parseInt(indInstitucion);
			document.getElementById("dep_Prov_Dist").innerHTML=arrayUbicacionBeneficiarios[indUbiBen].ubicacion.cbxDepText+"/"+arrayUbicacionBeneficiarios[indUbiBen].ubicacion.cbxProvText+"/"+arrayUbicacionBeneficiarios[indUbiBen].ubicacion.cbxDistText;
			limpiarTablaBeneficiarios();
			arrayBeneficiarios=new Array();
			
			if(arrayUbicacionBeneficiarios[indUbiBen].beneficiarios != null){//si tiene fuente financiera
			   //alert("tiene beneficiarios");
			   arrayBeneficiarios=arrayUbicacionBeneficiarios[indUbiBen].beneficiarios;
			    cargaDatosTablaBeneficiarios();
			}
			
		}

		function eliminarFilaUbi(numeroFilaEliminar){ 
			    var eli=parseInt(numeroFilaEliminar);
			    var eliMostrar=eli+1;
				var rpt=confirm("¿Seguro que desea eliminar a la ubicacion de proyecto nro:"+eliMostrar+"?");
				if(rpt==true){
					arrayUbicacionBeneficiarios.splice(eli,1);
					limpiarTabla();
					cargaDatosTabla();	
				}
		}

		function limpiarTabla(){
			indiceFila=0;
			elmTBODY = document.getElementById("tbodyUbiProy");
			var tam = elmTBODY.rows.length;
			//alert("tamano de tabla cuando se limpia :"+tam);
			  for(var w=0; w<tam; w++){
			    elmTBODY.deleteRow(0); 
			  } 
		}

		///////////////////////////////////////////funcionalidad para la tabla de beneficiarios y estratos

		var arrayBeneficiarios=new Array();
		var indiceFila4=0;
		var elmTBODY4;
		var elmTR4;
		var elmTD4;
		function onclick_btnAgregarEstrato(){

		 limpiarTablaBeneficiarios();
		 cargaDatosArrayBeneficiarios();
		 cargaDatosTablaBeneficiarios();
		 limpiarCamposBeneficiarios(); 
		 
		}


		function cargaDatosArrayBeneficiarios(){

		 var txtCantBen=document.getElementById("txtCantBen");
		 var cbxEstSocBen=document.getElementById("cbxEstSocBen");
		 var cbxTipoBen=document.getElementById("cbxTipoBen");
		 var txaCaracPoblacion=document.getElementById("txaCaracPoblacion");
		 var txaDescripcionPoblacion=document.getElementById("txaDescripcionPoblacion");
		 var objBeneficiario=new Object();
		 objBeneficiario.txtCantBen=txtCantBen.value;
		 objBeneficiario.cbxEstSocBenValue=cbxEstSocBen.value;
		 objBeneficiario.cbxEstSocBenText=cbxEstSocBen.options[cbxEstSocBen.selectedIndex].text;
		 objBeneficiario.cbxTipoBenValue=cbxTipoBen.value;
		 objBeneficiario.cbxTipoBenText=cbxTipoBen.options[cbxTipoBen.selectedIndex].text;
		 objBeneficiario.txaCaracPoblacion=txaCaracPoblacion.value;
		 objBeneficiario.txaDescripcionPoblacion=txaDescripcionPoblacion.value;
		 arrayBeneficiarios.push(objBeneficiario);
		 
		}

		function  cargaDatosTablaBeneficiarios(){
		elmTBODY4 = document.getElementById("tbodyBeneficiarios");
			for(var p=0; p<arrayBeneficiarios.length; p++){
				elmTR4 = elmTBODY4.insertRow(p);
				for (var i=0; i<5; i++) {
					elmTD4 = elmTR4.insertCell(i);
					if(i==0){
						  var ind=indiceFila4+1;
						  //alert("indicador de fila:"+indiceFila3);
						  elmText = document.createTextNode(ind);
						  var div = document.createElement("div");
						  div.setAttribute("align","center");
					      div.appendChild(elmText);
						  elmTD4.appendChild(div);
					}
					if(i==1)
						  elmText = document.createTextNode(arrayBeneficiarios[p].txtCantBen+"--"+arrayBeneficiarios[p].cbxEstSocBenText);
					if(i==2)
						  elmText = document.createTextNode(arrayBeneficiarios[p].cbxTipoBenText);
					if(i==3)
						  elmText = document.createTextNode(arrayBeneficiarios[p].txaCaracPoblacion);
				    if(i==4)
						  elmText = document.createTextNode(arrayBeneficiarios[p].txaDescripcionPoblacion);
					/*
				    if(i==5){
						  //elmText = document.createTextNode("drer");
						  var imagen = document.createElement("img");
						  imagen.setAttribute("src","${pageContext.request.contextPath}/images/del.png");
						  imagen.setAttribute("id",indiceFila4);
						  imagen.onclick=function(){eliminarFila(this.id);}; 
						  var div = document.createElement("div");
						  div.setAttribute("align","center");
						  div.appendChild(imagen);
						  elmTD4.appendChild(div);
					}
				    */
					if(i!=0)//i!=0 && i!=5
					     elmTD4.appendChild(elmText);
				}
				indiceFila4++;
			}
		}



		function eliminarFila(numeroFilaEliminar){ 
		    var eli=parseInt(numeroFilaEliminar);
		    var eliMostrar=eli+1;
			var rpt=confirm("¿Seguro que desea eliminar el estrato nro:"+eliMostrar+"?");
			if(rpt==true){
				arrayBeneficiarios.splice(eli,1);
				limpiarTablaBeneficiarios();
				cargaDatosTablaBeneficiarios();	
			}
		}

		function limpiarTablaBeneficiarios(){
			indiceFila4=0;
			var elmTBODY4 = document.getElementById("tbodyBeneficiarios");
			var tam = elmTBODY4.rows.length;
			//alert("tamano de tabla cuando se limpia :"+tam);
			for(var w=0; w<tam; w++){
				elmTBODY4.deleteRow(0); 
			} 
		}


		function onclick_btnAgregarUbicacionBeneficiarios(){
			$("#div_beneficiarios").hide();	
			/*
			agregarListadoBeneficiarios();
			limpiarCamposBeneficiarios();*/
		}

		//Boton OK
		function agregarListadoBeneficiarios(){
			if(arrayBeneficiarios.length){
			    arrayUbicacionBeneficiarios[indUbiBen].beneficiarios=arrayBeneficiarios;
			}else{
				alert("Debe ingresar al menos un beneficiario");
			}
			arrayBeneficiarios=new Array();
			limpiarTablaBeneficiarios();
			
			//ocultar Div de metas
			//$("#div_metas").hide();
		}

		
		/********************* OBTENER DATOS UBICACION DEL PROYECTO ************************/
		
		var objXHR120;
		var listUbicacionProyectoBenResul;
		var listUbicacionProyecto;
		var listBeneficiariosPorResultado;
		
		function obtenerDataUbicacionProyecto(){
			objXHR120 = new ObjetoAJAX();
			objXHR120.enviar("obtenerDataUbicacionProyecto2.jspx","post","cargaDataUbicacionProyecto",null);
			
		}	
		
		function cargaDataUbicacionProyecto(){
			
			listUbicacionProyectoBenResul = JSON.parse(objXHR120.respuestaTexto());
			listUbicacionProyecto = listUbicacionProyectoBenResul[0];
			listBeneficiariosPorResultado = listUbicacionProyectoBenResul[1];
			
			//alert("listUbicacionProyecto: " +JSON.stringify(listUbicacionProyecto));
			//alert("listBeneficiariosPorResultado: " +JSON.stringify(listBeneficiariosPorResultado));
			armarArrayUbicacionProyecto();
		}
		
		var idDep;
		var idProv;
		var idDist;
		
		function armarArrayUbicacionProyecto(){
		    
		    //alert("aa "+listUbicacionProyecto.length);
		    for(var i=0; i<listUbicacionProyecto.length; i++){	
		    	
		    	var arrayData=obtenerIdDepIdProvIdDist(listUbicacionProyecto[i].depProvDistID);
		    	
		    	idDep=arrayData[0];
		    	idProv=arrayData[1];
		    	idDist=arrayData[2];
		    	
		    	var objUbicacion= new Object();
		    	
		    	var codDep = obtenerCodDepartamento();
				objUbicacion.cbxDepValue=codDep;
			    objUbicacion.cbxDepText=obtenerTxtDepartamento(codDep);
			   
			    var codProv = obtenerCodProvincia();
			    objUbicacion.cbxProvValue=codProv;
			    objUbicacion.cbxProvText=obtenerTxtProvincia(codProv);
			    
			    var codDist = obtenerCodDistrito();
			    objUbicacion.cbxDistValue=codDist;
			    objUbicacion.cbxDistText=obtenerTxtDistrito(codDist);
			    
			    objUbicacion.txaLoc=listUbicacionProyecto[i].detalleUbicacion;//chapa de bd
			    objUbicacion.DepProvDistID=listUbicacionProyecto[i].depProvDistID;//chapa de bd
			    
			    var arrayBeneficiarios= new Array();
			    var objBeneficiario;
			   
				//op.setAttribute("value",estratosSoc[0][i].tablaEspecificaID);
				//text=document.createTextNode(estratosSoc[0][i].descripcionCabecera);
			    for(var j=0; j<listBeneficiariosPorResultado.length; j++){
			    	
			    	if(listBeneficiariosPorResultado[j].ubicacionProyectoID == listUbicacionProyecto[i].ubicacionProyectoID){
			    		objBeneficiario=new Object();
					    objBeneficiario.txtCantBen=listBeneficiariosPorResultado[j].cantidadProgramado;
					    objBeneficiario.cbxEstSocBenValue=listBeneficiariosPorResultado[j].fkidtablaespEstrato;
					    objBeneficiario.cbxEstSocBenText=obtenerTxtEstratoSoc(listBeneficiariosPorResultado[j].fkidtablaespEstrato);
					    objBeneficiario.cbxTipoBenValue=listBeneficiariosPorResultado[j].fkIdtablaespTipoBeneficiario;
					    var txtTipoBen;
					    if(listBeneficiariosPorResultado[j].fkIdtablaespTipoBeneficiario==115)
					    	txtTipoBen="Directos";
					    else if(listBeneficiariosPorResultado[j].fkIdtablaespTipoBeneficiario==116)
					    	txtTipoBen="Indirectos";
					    objBeneficiario.cbxTipoBenText=txtTipoBen;
					    objBeneficiario.txaCaracPoblacion=listBeneficiariosPorResultado[j].caracteristicasPoblacion;
					    objBeneficiario.txaDescripcionPoblacion=listBeneficiariosPorResultado[j].descripcion;
					    arrayBeneficiarios.push(objBeneficiario);
			    	}
			    }
			    
			    var objUbicacionBeneficiarios=new Object();
				objUbicacionBeneficiarios.ubicacion=objUbicacion;
				objUbicacionBeneficiarios.beneficiarios=arrayBeneficiarios;
			    arrayUbicacionBeneficiarios.push(objUbicacionBeneficiarios);
			    
		    }
		    cargaDatosTabla();
		    
		}
		
		function obtenerTxtEstratoSoc(codEstSoc){
			var txtEstSoc;
			for(var i=0; i<estratosSoc[0].length; i++){
				if(estratosSoc[0][i].tablaEspecificaID==codEstSoc){
					txtEstSoc=estratosSoc[0][i].descripcionCabecera;
				}
			}
			return txtEstSoc;
		}
		
		function obtenerIdDepIdProvIdDist(depProvDistID){
			var array=new Array();
			for(var i=0; i<distritos.length; i++){
			   if(distritos[i].depProvDistID == depProvDistID){
				   array.push(distritos[i].idDepartamento);
				   array.push(distritos[i].idProvincia);
				   array.push(distritos[i].idDistrito);
			   }
			}
			return array;
		}
		
		function obtenerCodDepartamento(){
			var cod;
			for(var i=0; i<departamentos.length; i++){
				   if(departamentos[i].idDepartamento == idDep && departamentos[i].idProvincia=="0" && departamentos[i].idDistrito=="0"){
					   cod = departamentos[i].depProvDistID;
				   }
			}
			return cod;
			
		}
		
		function obtenerTxtDepartamento(codDep){
			var desc;
			for(var i=0; i<departamentos.length; i++){
				   if(departamentos[i].depProvDistID == codDep){
					   desc = departamentos[i].descripcion;
				   }
			}
			return desc;
		}
		
		function obtenerCodProvincia(){
			var cod;
			for(var i=0; i<provincias.length; i++){
				   if(provincias[i].idDepartamento==idDep && provincias[i].idProvincia==idProv && provincias[i].idDistrito=="0"){
					   cod = provincias[i].depProvDistID;
				   }
			}
			return cod;
			
		}
		
		function obtenerTxtProvincia(codProv){
			var desc;
			for(var i=0; i<provincias.length; i++){
				   if(provincias[i].depProvDistID == codProv){
					   desc = provincias[i].descripcion;
				   }
			}
			return desc;
			
		}
		
		function obtenerCodDistrito(){
			var cod;
			for(var i=0; i<distritos.length; i++){
				   if(distritos[i].idDepartamento==idDep && distritos[i].idProvincia==idProv && distritos[i].idDistrito==idDist){
					   cod = distritos[i].depProvDistID;
				   }
			}
			return cod;
		}
		
		function obtenerTxtDistrito(codDist){
			var desc;
			for(var i=0; i<distritos.length; i++){
				   if(distritos[i].depProvDistID == codDist){
					   desc = distritos[i].descripcion;
				   }
			}
			return desc;
			
		}
		

///////////////////////////////////////////funcionalidad para la tabla de instituciones

		var arrayInstitucionFuenFinan=new Array();
		var objInstitucionFuenFinan;
		var indiceFila10=0;
		var elmTBODY10;
		var elmTR10;
		var elmTD10;

		function onclick_btnAgregarInstitucion(){
		    $("#div_fuenteFinanciadora").hide();
			limpiarTablaInstituciones();
			cargaDatosArrayInstitucionFuenFinan();
			cargaDatosTablaInstitucion();
			limpiarCamposInstitucion();
			llenaComboInstitucionEjecutora();
		}

		function cargaDatosArrayInstitucionFuenFinan(){
			var txtInstitucion = document.getElementById("txtInstitucion");
			var txtRuc = document.getElementById("txtRuc");
			var txtDireccion = document.getElementById("txtDireccion");
			var txtTelefono = document.getElementById("txtTelefono");
			var txtRepLegal = document.getElementById("txtRepLegal");
			var txtContacto = document.getElementById("txtContacto");
			var txtCorreo = document.getElementById("txtCorreo");
			var txaObservacion = document.getElementById("txaObservacion");
			
			var objInstitucion = new Object();
			objInstitucion.txtInstitucion=txtInstitucion.value;
			objInstitucion.txtRuc=txtRuc.value;
			objInstitucion.txtDireccion=txtDireccion.value;
			objInstitucion.txtTelefono=txtTelefono.value;
			objInstitucion.txtRepLegal=txtRepLegal.value;
			objInstitucion.txtContacto=txtContacto.value;
			objInstitucion.txtCorreo=txtCorreo.value;
			objInstitucion.txaObservacion=txaObservacion.value;
			
			var objFuenteFinanciera = new Object();
			objFuenteFinanciera.cbxTipoFuenteFinan = "";
			objFuenteFinanciera.cbxTipoMoneda = "";
			objFuenteFinanciera.txtMontoFinan = "";
			objFuenteFinanciera.cbxInstitucionEjecutora = "";
			//arrayInstitucionFuenFinan[indIns].fuenFinan=objFuenteFinanciera;
			
			objInstitucionFuenFinan=new Object();
			objInstitucionFuenFinan.institucion=objInstitucion;
			objInstitucionFuenFinan.fuenFinan=objFuenteFinanciera;
			
			arrayInstitucionFuenFinan.push(objInstitucionFuenFinan);
		}

		function cargaDatosTablaInstitucion(){//tbodyInstitucion
			elmTBODY10 = document.getElementById("tbodyInstitucion");
			for(var p=0; p<arrayInstitucionFuenFinan.length; p++){
				elmTR10 = elmTBODY10.insertRow(p);
				for (var i=0; i<10; i++) {
					elmTD10 = elmTR10.insertCell(i);
					if(i==0){
						  var ind=indiceFila10+1;
						  //alert("indicador de fila:"+indiceFila10);
						  elmText = document.createTextNode(ind);
						  var div = document.createElement("div");
						  div.setAttribute("align","center");
					      div.appendChild(elmText);
						  elmTD10.appendChild(div);
					}
					if(i==1)
						  elmText = document.createTextNode(arrayInstitucionFuenFinan[p].institucion.txtRuc);
					if(i==2)
						  elmText = document.createTextNode(arrayInstitucionFuenFinan[p].institucion.txtInstitucion);
					if(i==3)
						  elmText = document.createTextNode(arrayInstitucionFuenFinan[p].institucion.txtDireccion);
				    if(i==4)
						  elmText = document.createTextNode(arrayInstitucionFuenFinan[p].institucion.txtTelefono);
				    if(i==5)
						  elmText = document.createTextNode(arrayInstitucionFuenFinan[p].institucion.txtRepLegal);
				    if(i==6)
						  elmText = document.createTextNode(arrayInstitucionFuenFinan[p].institucion.txtContacto);
					if(i==7)
						  elmText = document.createTextNode(arrayInstitucionFuenFinan[p].institucion.txtCorreo);
					if(i==8)
						  elmText = document.createTextNode(arrayInstitucionFuenFinan[p].institucion.txaObservacion);
					if(i==9){
						// <a id="perfil" class="link" onclick="seleccionar(1)" href="javascript:muestra_perfil()">Perfil</a>
						
						  var link = document.createElement("a");
						  link.setAttribute("class","link");
						  link.setAttribute("id",indiceFila10);
						  // link.setAttribute=("href","javascript:limpiarCamposInstitucion();$('#div_fuenteFinanciadora').show('slow');verFuentesFinancieras(this.id);");
						  link.onclick=function(){
							  //limpiarCamposInstitucion();
							  $("#div_fuenteFinanciadora").show('slow');
							  verFuentesFinancieras(this.id);}; //$("#div_fuenteFinanciadora").show('slow');verFuentesFinancieras(this.id);
						  var text=document.createTextNode("Fte.Finan.");
						  link.appendChild(text);
						  var div = document.createElement("div");
						  div.setAttribute("align","center");
						  div.appendChild(link);
						  elmTD10.appendChild(div);
					}
					/*
					if(i==10){
			            var imagen = document.createElement("img");
						imagen.setAttribute("src","${pageContext.request.contextPath}/images/del.png");
						imagen.setAttribute("id",indiceFila10);
						imagen.onclick=function(){eliminarFila10(this.id);$("#div_fuenteFinanciadora").hide();llenaComboInstitucionEjecutora();};//eliminarFila3(this.id);$("#div_metas").hide();
						var div = document.createElement("div");
						div.setAttribute("align","center");
					    div.appendChild(imagen);
						elmTD10.appendChild(div);
			        }*/
					if(i!=0 && i!=9) //i!=0 && i!=9 && i!=10
					     elmTD10.appendChild(elmText);
				}
				indiceFila10++;
			}
		}

		function eliminarFila10(numeroFilaEliminar){ 
		    var eli=parseInt(numeroFilaEliminar);
		    var eliMostrar=eli+1;
			var rpt=confirm("¿Seguro que desea eliminar la institucion nro:"+eliMostrar+"?");
			if(rpt==true){
			    arrayInstitucionFuenFinan.splice(eli,1);
				limpiarTablaInstituciones();
				cargaDatosTablaInstitucion();
			}
		}

		function limpiarTablaInstituciones(){
			indiceFila10=0;
			var elmTBODY10 = document.getElementById("tbodyInstitucion");
			var tam = elmTBODY10.rows.length;
			//alert("tamano de tabla cuando se limpia :"+tam);
			for(var w=0; w<tam; w++){
				elmTBODY10.deleteRow(0); 
			} 
		}

		function testearInstitucionFuenFinan(){
			alert(JSON.stringify(arrayInstitucionFuenFinan));
		}

		/////////////////////////////////////funcionalidad para las fuentes financieras para las instituciones
		var indIns;
		var objFuenteFinanciera;
		function verFuentesFinancieras(indInstitucion){
			indIns=parseInt(indInstitucion);
			document.getElementById("nomIns").innerHTML=arrayInstitucionFuenFinan[indIns].institucion.txtInstitucion;
			limpiarCamposFuentesFinan();
			objFuenteFinanciera=new Object();
			var ind=verificarSiFuenteFinanciera(indIns);
		    //alert("tiene fuente financiera??? "+ind);
			if(ind==true){//si la actividad tiene metas cargar esas metas
			    objFuenteFinanciera=arrayInstitucionFuenFinan[indIns].fuenFinan;
		 		cargaDatosFuenFinan();
			}
		}

		function verificarSiFuenteFinanciera(indIns){
			var rpt = false;
			if(arrayInstitucionFuenFinan[indIns].fuenFinan != null){//si tiene fuente financiera
			   //alert("tiene metas");
			   rpt=true;
			}
			return rpt;
		}

		function cargaDatosFuenFinan(){
		    document.getElementById("cbxTipoFuenteFinan").value = objFuenteFinanciera.cbxTipoFuenteFinan;
			document.getElementById("cbxTipoMoneda").value = objFuenteFinanciera.cbxTipoMoneda;
			document.getElementById("txtMontoFinan").value = objFuenteFinanciera.txtMontoFinan;

		}

		function cargaObjFuenteFinanciera(){
			objFuenteFinanciera.cbxTipoFuenteFinan = document.getElementById("cbxTipoFuenteFinan").value;
			objFuenteFinanciera.cbxTipoMoneda = document.getElementById("cbxTipoMoneda").value;
			objFuenteFinanciera.txtMontoFinan = document.getElementById("txtMontoFinan").value;
			objFuenteFinanciera.cbxInstitucionEjecutora = document.getElementById("cbxInstitucionEjecutora").value;
			arrayInstitucionFuenFinan[indIns].fuenFinan=objFuenteFinanciera;
			
		}


		//Boton OK
		function onclick_btnAgregarFuenteFinan(){
			/*
			cargaObjFuenteFinanciera();
			if(arrayInstitucionFuenFinan[indIns].fuenFinan == null){
				alert("No se guardo ninguna Fuente financiadora.");
			}
			objFuenteFinanciera=new Object();
			*/
			limpiarCamposFuentesFinan();
			
			//ocultar Div 
			$("#div_fuenteFinanciadora").hide();
		}

		/////////////////llenar combo de institucion financiadora

		function llenaComboInstitucionEjecutora(){
			var cbxInstitucionEjecutora = document.createElement("select");
			cbxInstitucionEjecutora.setAttribute("id","cbxInstitucionEjecutora");
			cbxInstitucionEjecutora.setAttribute("name","cbxInstitucionEjecutora");
			cbxInstitucionEjecutora.setAttribute("disabled","true");
			cbxInstitucionEjecutora.onchange=function(){seleccionarInstitucionEjecutora(this.value);};
			//valor por defecto
			var op = document.createElement("option");
			op.setAttribute("value","0");
			var text=document.createTextNode("----SELECCIONAR----");
			op.appendChild(text);
			cbxInstitucionEjecutora.appendChild(op);
			//llenar valores tipo resumen de proyecto
			for(var i=0; i<arrayInstitucionFuenFinan.length; i++){
				op = document.createElement("option");
				op.setAttribute("value",arrayInstitucionFuenFinan[i].institucion.txtInstitucion);
				text=document.createTextNode(arrayInstitucionFuenFinan[i].institucion.txtInstitucion);
				op.appendChild(text);
				cbxInstitucionEjecutora.appendChild(op);
			}
			//limpia div
		  	document.getElementById("div_cbxInstitucionEjecutora").innerHTML="";
			//pinta combo en el div
		  	document.getElementById("div_cbxInstitucionEjecutora").appendChild(cbxInstitucionEjecutora);
			
		}

		function seleccionarInstitucionEjecutora(nombreInstitucion){
			
			for(var i=0; i<arrayInstitucionFuenFinan.length; i++){
				if(arrayInstitucionFuenFinan[i].institucion.txtInstitucion==nombreInstitucion){
					arrayInstitucionFuenFinan[i].fuenFinan.cbxInstitucionEjecutora="1";
				}else
					arrayInstitucionFuenFinan[i].fuenFinan.cbxInstitucionEjecutora="0";
			}
		}

		
//////////////////////////////////////INSTITUCION (FUENTES FINANCIADORAS)/////////////////////////////
		var objXHR9;
		var tipoFuenteFinanciadora;
		function cargaComboTipoFuenteFinanciadora(){
			objXHR9 = new ObjetoAJAX();
			objXHR9.enviar("cargaComboTipoFuenteFinanciadora.jspx","post","cargaDataComboTipoFuenteFinanciadora",null);	
		}

		function cargaDataComboTipoFuenteFinanciadora(){
			tipoFuenteFinanciadora = JSON.parse(objXHR9.respuestaTexto());
			//alert(objXHR9.respuestaTexto());
			//alert(tipoFuenteFinanciadora[0].length);
			llenaComboTipoFuenteFinanciadora();
		}

		function llenaComboTipoFuenteFinanciadora(){
			//alert(tipoFuenteFinanciadora[0].length);
			var cbxTipoFuenteFinan = document.createElement("select");
			cbxTipoFuenteFinan.setAttribute("id","cbxTipoFuenteFinan");
			cbxTipoFuenteFinan.setAttribute("name","cbxTipoFuenteFinan");
			cbxTipoFuenteFinan.setAttribute("disabled","true");
			//cbxTipoResumenProyecto.onchange=function(){alert(this.value);};
			//valor por defecto
			var op = document.createElement("option");
			op.setAttribute("value","0");
			var text=document.createTextNode("----SELECCIONAR----");
			op.appendChild(text);
			cbxTipoFuenteFinan.appendChild(op);
			//llenar valores tipo resumen de proyecto
			for(var i=0; i<tipoFuenteFinanciadora[0].length; i++){
				op = document.createElement("option");
				op.setAttribute("value",tipoFuenteFinanciadora[0][i].tablaEspecificaID);
				text=document.createTextNode(tipoFuenteFinanciadora[0][i].descripcionCabecera);
				op.appendChild(text);
				cbxTipoFuenteFinan.appendChild(op);
			}
			//limpia div
		  	document.getElementById("div_cbxTipoFuenteFinan").innerHTML="";
			//pinta combo en el div
		  	document.getElementById("div_cbxTipoFuenteFinan").appendChild(cbxTipoFuenteFinan);
			
		}

		function llenaComboTipoMoneda(){
			var cbxTipoMoneda = document.createElement("select");
			cbxTipoMoneda.setAttribute("id","cbxTipoMoneda");
			cbxTipoMoneda.setAttribute("name","cbxTipoMoneda");
			cbxTipoMoneda.setAttribute("disabled","true");
			//cbxTipoResumenProyecto.onchange=function(){alert(this.value);};
			//valor por defecto
			var op = document.createElement("option");
			op.setAttribute("value","0");
			var text=document.createTextNode("----SELECCIONAR----");
			op.appendChild(text);
			cbxTipoMoneda.appendChild(op);
			//llenar valores tipo resumen de proyecto
			for(var i=0; i<tipoMoneda[0].length; i++){
				op = document.createElement("option");
				op.setAttribute("value",tipoMoneda[0][i].tablaEspecificaID);
				text=document.createTextNode(tipoMoneda[0][i].descripcionCabecera);
				op.appendChild(text);
				cbxTipoMoneda.appendChild(op);
			}
			//limpia div
		  	document.getElementById("div_cbxTipoMoneda").innerHTML="";
			//pinta combo en el div
		  	document.getElementById("div_cbxTipoMoneda").appendChild(cbxTipoMoneda);
			
		}
		

		/********************* OBTENER DATOS INSTITUCION FUENTE FINANCIERA ************************/
		var objXHR122;
		var listInstitucion;
		var listFuenteFinancieras;

		
		function obtenerDataIntitucionFuenFinan(){
			objXHR122 = new ObjetoAJAX();
			objXHR122.enviar("obtenerDataIntitucionFuenFinan2.jspx","post","cargaDataIntitucionFuenFinan",null);
			
			//inicializar Array de todos los datos del proyecto
			arrayBancos = new Array();
			arrayUbicacionBeneficiarios = new Array();
			arrayInstitucionFuenFinan = new Array(); limpiarTablaInstituciones();
			arrayResumenPerfil = new Array();
			arrayIndMarcoLog = new Array();
		}	
		
		function cargaDataIntitucionFuenFinan(){

			//var intitucionFuenFinan = JSON.parse(objXHR122.respuestaTexto());
			 arrayInstitucionFuenFinan = JSON.parse(objXHR122.respuestaTexto());

			cargaDatosTablaInstitucion();
			llenaComboInstitucionEjecutora();
			seleccionaComboInstitucionEjecutora();
		}		
		
		function seleccionaComboInstitucionEjecutora(){
			for(var i=0; i<arrayInstitucionFuenFinan.length; i++){
			    if(arrayInstitucionFuenFinan[i].fuenFinan.cbxInstitucionEjecutora == "1"){
			    	document.getElementById("cbxInstitucionEjecutora").value=arrayInstitucionFuenFinan[i].institucion.txtInstitucion; 
					break;
				}
			}
			
		}
		
		

		/********************* OBTENER DATOS CUENTAS CORRIENTES ************************/
		var objXHR1263;
		var listCuentasCorrientes;
		
		function obtenerDataCuentasCorrientes(){
			objXHR1263 = new ObjetoAJAX();
			objXHR1263.enviar("obtenerDataCuentasCorrientes1.jspx","post","cargaDataCuentasCorrientes",null);
		}
		
		function cargaDataCuentasCorrientes(){
			//alert("cuentas corrientes: "+ objXHR1263.respuestaTexto());
			var data = JSON.parse(objXHR1263.respuestaTexto());
			listCuentasCorrientes = data[0];
			armarArrayCuentasCorrientes();
		}
		
		function armarArrayCuentasCorrientes(){
			var banco;
			for(var i=0; i<listCuentasCorrientes.length; i++){
				banco=new Object();
				banco.cbxBancos=listCuentasCorrientes[i].fkIdtablaespBanco;
				banco.cbxBancosText=obtenerNombreBanco(banco.cbxBancos);
				banco.cbxTipoMoneda1=listCuentasCorrientes[i].fkIdtablaespTipomoneda;
				banco.cbxTipoMoneda1Text=obtenerNombreMoneda(listCuentasCorrientes[i].fkIdtablaespTipomoneda);
				banco.txtNroCuenta=listCuentasCorrientes[i].numeroCuenta;
				arrayBancos.push(banco);
			}	
			armarGrillaBancos();
		}
		
		function obtenerNombreBanco(codBanco){
			
			for(var i=0; i<bancos[0].length; i++){
				if(bancos[0][i].tablaEspecificaID == codBanco)
					return bancos[0][i].descripcionCabecera;
			}
			
			return "no existe";
		}
		
		function obtenerNombreMoneda(codMoneda){
			//alert("codigo: "+codMoneda);
			//alert(JSON.stringify(tipoMoneda));
			for(var i=0; i<tipoMoneda[0].length; i++){
				if(tipoMoneda[0][i].tablaEspecificaID == codMoneda){
					//alert(tipoMoneda[0][i].descripcionCabecera);
					return tipoMoneda[0][i].descripcionCabecera; 
				}
			}
			
			return "no existe";
			
		}
		
		
//////////////////////////////////////RESUMEN DE PROYECTO/////////////////////////////

		var objXHR4;
		var tipoResumenProyecto;
		function cargaComboTipoResumenProyecto(){
			objXHR4 = new ObjetoAJAX();
			objXHR4.enviar("cargaComboTipoResumenProyecto.jspx","post","cargaDataComboTipoResumenProyecto",null);
			
		}

		function cargaDataComboTipoResumenProyecto(){
			tipoResumenProyecto = JSON.parse(objXHR4.respuestaTexto());
			//alert(objXHR4.respuestaTexto());
			//alert(tipoResumenProyecto[0].length);
			//llenaComboTipoResumenProyecto();//cbxTipoResumenProyecto
		}

		function llenaComboTipoResumenProyecto(){
			var cbxTipoResumenProyecto = document.createElement("select");
			cbxTipoResumenProyecto.setAttribute("id","cbxTipoResumenProyecto");
			cbxTipoResumenProyecto.setAttribute("name","cbxTipoResumenProyecto");
			//cbxTipoResumenProyecto.onchange=function(){alert(this.value);};
			//valor por defecto
			var op = document.createElement("option");
			op.setAttribute("value","0");
			var text=document.createTextNode("----SELECCIONAR----");
			op.appendChild(text);
			cbxTipoResumenProyecto.appendChild(op);
			//llenar valores tipo resumen de proyecto
			for(var i=0; i<tipoResumenProyecto[0].length; i++){
				op = document.createElement("option");
				op.setAttribute("value",tipoResumenProyecto[0][i].tablaEspecificaID);
				text=document.createTextNode(tipoResumenProyecto[0][i].descripcionCabecera);
				op.appendChild(text);
				cbxTipoResumenProyecto.appendChild(op);
			}
			//limpia div
		  	document.getElementById("div_cbxTipoResumenProyecto").innerHTML="";
			//pinta combo en el div
		  	document.getElementById("div_cbxTipoResumenProyecto").appendChild(cbxTipoResumenProyecto);
		}


		///////////////////////////////////////////funcionalidad para la tabla de resumen y observaciones de perfil

		var arrayResumenPerfil=new Array();
		var indiceFila2=0;
		var elmTBODY2;
		var elmTR2;
		var elmTD2;

		function testearResumen(){
			alert(JSON.stringify(arrayResumenPerfil));
		}

		function onclick_btnAgregarResumen(){
			var rpt1=verificarTipoResumenUnico();
			if(rpt1==true){
				agregarResumenPerfil();
			}
			limpiarResumenObservacionesPerfil();
		}

		function verificarTipoResumenUnico(){
		    var rpt=true;
			if(arrayResumenPerfil.length){
				//alert(arrayResumenPerfil.length);
				var cbxTipResProy=document.getElementById("cbxTipoResumenProyecto").value;
					for(var j=0; j<arrayResumenPerfil.length; j++){
						if(arrayResumenPerfil[j].cbxCodTipoResumenProyecto==cbxTipResProy){
							alert("El tipo resumen proyecto ya existe en la lista!");
							rpt=false;
						}		  
					}
			}
			return rpt;
		}

		function agregarResumenPerfil(){
			cargaDatosArrayResumen();
			cargaDatosTablaResumen();
		}

		function cargaDatosArrayResumen(){
			var cbxTipoResumenProyecto = document.getElementById("cbxTipoResumenProyecto");
			var txaResumenPerfil = document.getElementById("txaResumenPerfil");
			var objResumenPerfil=new Object();
			objResumenPerfil.cbxCodTipoResumenProyecto = cbxTipoResumenProyecto.value;
			objResumenPerfil.cbxTipoResumenProyecto = cbxTipoResumenProyecto.options[cbxTipoResumenProyecto.selectedIndex].text;
			objResumenPerfil.txaResumenPerfil=txaResumenPerfil.value;
			arrayResumenPerfil.push(objResumenPerfil);
			//alert(arrayResumenPerfil.length);
		}

		function cargaDatosTablaResumen(){
			
			  limpiarTablaResumen();
			  //alert(arrayResumenPerfil.join("-"));
			  elmTBODY2 = document.getElementById("tbodyResumenObserv");
			  for(var p=0; p<arrayResumenPerfil.length; p++){
				  elmTR2 = elmTBODY2.insertRow(p);
				  for (var i=0; i<3; i++) {
					  elmTD2 = elmTR2.insertCell(i);
					  if(i==0){
						  var ind=indiceFila2+1;
						  //alert("indicador de fila:"+indiceFila2);
						  elmText = document.createTextNode(ind);
						  var div = document.createElement("div");
						  div.setAttribute("align","center");
					      div.appendChild(elmText);
						  elmTD2.appendChild(div);
					  }
					  if(i==1)
						  elmText = document.createTextNode(arrayResumenPerfil[p].cbxTipoResumenProyecto);
					  if(i==2)
						  elmText = document.createTextNode(arrayResumenPerfil[p].txaResumenPerfil);
					  /*
					  if(i==3){
			            var imagen = document.createElement("img");
						imagen.setAttribute("src","${pageContext.request.contextPath}/images/del.png");
						imagen.setAttribute("id",indiceFila2);
						imagen.onclick=function(){eliminarFila2(this.id);}; 
						
						var div = document.createElement("div");
						div.setAttribute("align","center");
					    div.appendChild(imagen);
						elmTD2.appendChild(div);
				      }
					  */
					  if(i!=0)//i!=0 && i!=3
					     elmTD2.appendChild(elmText);
		 	    }
				  indiceFila2++;
		     }
		}

		function eliminarFila2(numeroFilaEliminar){ 
				    var eli=parseInt(numeroFilaEliminar);
				    var eliMostrar=eli+1;
					var rpt=confirm("¿Seguro que desea eliminar al resumen del perfil nro:"+eliMostrar+"?");
					if(rpt==true){
						arrayResumenPerfil.splice(eli,1);
						limpiarTablaResumen();
						cargaDatosTablaResumen();	
					}
		}

		function limpiarTablaResumen(){
			indiceFila2=0;
			var elmTBODY2 = document.getElementById("tbodyResumenObserv");
			var tam = elmTBODY2.rows.length;
			//alert("tamano de tabla cuando se limpia :"+tam);
			  for(var w=0; w<tam; w++){
			    elmTBODY2.deleteRow(0); 
			  } 
		}

		
		
		/***************************LIMPIAR CAMPOS***********************/

		function limpiarCamposUbicacion(){
			document.getElementById('cbxDepartamentos').options.selectedIndex = 0;
			document.getElementById('cbxProvincias').options.selectedIndex = 0;
			document.getElementById('cbxDistritos').options.selectedIndex = 0;
			document.getElementById('txaLocalizacion').value = "";
		}
		
		function limpiarCamposBeneficiarios(){
			document.getElementById('txtCantBen').value = "";
			document.getElementById('cbxEstSocBen').options.selectedIndex = 0;
			document.getElementById('cbxTipoBen').options.selectedIndex = 0;
			document.getElementById('txaCaracPoblacion').value = "";
			document.getElementById('txaDescripcionPoblacion').value = "";
		}
		
		function limpiarCamposInstitucion(){
			document.getElementById("txtInstitucion").value="";
			document.getElementById("txtRuc").value="";
			document.getElementById("txtDireccion").value="";
			document.getElementById("txtTelefono").value="";
			document.getElementById("txtRepLegal").value="";
			document.getElementById("txtContacto").value="";
			document.getElementById("txtCorreo").value="";
			document.getElementById("txaObservacion").value="";
			
		}

		function limpiarCamposFuentesFinan(){
			document.getElementById("cbxTipoFuenteFinan").options.selectedIndex = 0;
			document.getElementById("cbxTipoMoneda").options.selectedIndex = 0;
			document.getElementById("txtMontoFinan").value = "";
		}
		
		function limpiarResumenObservacionesPerfil(){
			document.getElementById('cbxTipoResumenProyecto').options.selectedIndex = 0;
			document.getElementById('txaResumenPerfil').value = "";
		}
		
		function limpiarCamposIndicadorMarcoLogico(){
			document.getElementById("txtIndicador").value = "";
			document.getElementById("txtdefinicionIndicador").value = "";
			document.getElementById("txtUnidadMedida").value = "";
			document.getElementById("txtMedioVerificacion").value = "";
			document.getElementById("txtSituacionActual").value = "";
			document.getElementById("txtSituacionFinal").value = "";
		}
		
		
		/********************* OBTENER DATOS RESUMEN DE PROYECTO ************************/
		
		var objXHR124;
		var listResumenPerfil;
		
		function obtenerDataResumenProyecto(){
			objXHR124 = new ObjetoAJAX();
			objXHR124.enviar("obtenerDataResumenProyecto2.jspx","post","cargaDataResumenProyecto",null);
		}
		
		function cargaDataResumenProyecto(){
			//alert(objXHR124.respuestaTexto());
			var arrayResumenPerfil = JSON.parse(objXHR124.respuestaTexto());
			listResumenPerfil = arrayResumenPerfil[0];
			
			armarArrayResumen();
			
		}
		
		function armarArrayResumen(){
		
			var objResumenPerfil;
			
			for(var i=0; i<listResumenPerfil.length; i++){
				objResumenPerfil=new Object();
				objResumenPerfil.cbxCodTipoResumenProyecto = listResumenPerfil[i].fkIdtablaespTipoResumenProy;
				objResumenPerfil.cbxTipoResumenProyecto = obtenerTxtTipoResumenProy(listResumenPerfil[i].fkIdtablaespTipoResumenProy);
				objResumenPerfil.txaResumenPerfil = listResumenPerfil[i].definicion;
				arrayResumenPerfil.push(objResumenPerfil);
				
			}
			
			cargaDatosTablaResumen();
		}
		
		function obtenerTxtTipoResumenProy(codTipoResumenProyecto){
			var txtResumenProy;
			for(var i=0; i<tipoResumenProyecto[0].length; i++){
				if(tipoResumenProyecto[0][i].tablaEspecificaID == codTipoResumenProyecto){
					txtResumenProy = tipoResumenProyecto[0][i].descripcionCabecera;
				}
		    }
			return txtResumenProy;
		}
		
		


		/********************* MARCO LOGICO ************************/
		
		//********* INDICADOR **********
		var arrayIndMarcoLog=new Array();
		var objIndMarcoLog;
		var tablaGrid1;
		function onclick_btnAgregarIndicador(){
			objIndMarcoLog=new Object();
			objIndMarcoLog.idIndMarcoLog="null";
			objIndMarcoLog.idMarcoLog="null";
			objIndMarcoLog.indicador=document.getElementById("txtIndicador").value;
			objIndMarcoLog.definicionIndicador=document.getElementById("txtdefinicionIndicador").value;
			objIndMarcoLog.unidadMedida=document.getElementById("txtUnidadMedida").value;
			objIndMarcoLog.medioVerificacion=document.getElementById("txtMedioVerificacion").value;
			objIndMarcoLog.situacionActual=document.getElementById("txtSituacionActual").value;
			objIndMarcoLog.situacionFinal=document.getElementById("txtSituacionFinal").value;
			arrayIndMarcoLog.push(objIndMarcoLog);
			armarGrilla1();
			limpiarCamposIndicadorMarcoLogico();
		}
		
		
		function eliminarIndicador(){
			var rpt=confirm("¿Seguro que desea eliminar el indicador nro:"+(tablaGrid1.getIndiceArray()+1)+"?");
			if(rpt==true){
				arrayIndMarcoLog.splice(tablaGrid1.getIndiceArray(),1);
			}
			armarGrilla1();
			
		}
		
		function armarGrilla1(){
			tablaGrid1=new AtssefGrid({
			 "cabecera":["Indicador","Def. Indicador","Und. Medida","Med. Verificacion","Sit. Actual","Sit. Final"],
			 "campos_cabecera":[
								{name:"indicador"},
								{name:"definicionIndicador"},
								{name:"unidadMedida"},
								{name:"medioVerificacion"},
								{name:"situacionActual"},
								{name:"situacionFinal"}
							   ],
			 "arrayDataTabla":arrayIndMarcoLog,
			 "id_contenedor":"tabla1",
			 "atributos_tabla":{ width:"100%",
			                     align:"left",
								 class:"table-clasico"
			                    },
			 "titulo_tabla":{estado:"si",titulo:"Listado de indicadores"},
			 "campos_extra_tabla":{
									"detalle":{estado:"no",titulo:"Details",textoCampo:"Ver Detalle",funcionDetalle:"detalleX1();"},
								    "eliminarFila":{estado:"no",funcionEliminarFila:"eliminarIndicador();"}
			                     },
			 "numeracion":"si",
			 "objTabla":"tablaGrid1",
			 "estiloTabla":[{"tablaCebra":"no",
			                 "color":{filaPar:"#BBBBBB",filaImpar:"#D4D4D4",filaOver:"#DEB87F",filaSel:"brown"}
							},
							{"funcion":{apuntarOverOut:"no",sel:"no"}}
						   ],	   
			 "paginacionTabla":{"pagTabla":"no","paginado":10},
			 "espacio_entre_paginacion_gestion":19,
			 "gestionDatos":{		
				                    "gestion":"no",
			                        "agregar":"si",
									"modificar":"si",
									"eliminar":"si"
			                     },
			 "alineacion_de_paginacion_gestion":"right"
			 
			});
			}
		
		
		/********************* OBTENER DATOS MARCO LOGICO E INDICADORES ************************/
		
		var objXHR3101;
		var marcoLogico;
		var listIndicadores;
		
		function obtenerMarcoLogicoIndicadores(){
			objXHR3101 = new ObjetoAJAX();
			objXHR3101.enviar("obtenerDataMarcoLogicoIndicadores1.jspx","post","cargaDataMarcoLogicoIndicadores",null);
		}
		
		function cargaDataMarcoLogicoIndicadores(){
			//alert("marco logico e indicadores: "+ objXHR3101.respuestaTexto());
			var data = JSON.parse(objXHR3101.respuestaTexto());
			marcoLogico = data[0];
			listIndicadores = data[1];	
			muestraDataMarcoLogico();
			armarArrayIndicadores();
		}
		
		function muestraDataMarcoLogico(){
			/*document.getElementById("txaResumenEjecutivo").value=marcoLogico.resumenEjecutivo;
			document.getElementById("txaDescripcionFin").value=marcoLogico.finDescrip;
			document.getElementById("txaSupuestoFin").value=marcoLogico.finSupuesto;
			document.getElementById("txaDescripcionProposito").value=marcoLogico.propositoDescrip;
			document.getElementById("txaSupuestoProposito").value=marcoLogico.propositoSupuesto;
			*/
			document.getElementById("lblResumenEjecutivo").innerHTML=marcoLogico.resumenEjecutivo;
			document.getElementById("lblDescripcionFin").innerHTML=marcoLogico.finDescrip;
			document.getElementById("lblSupuestoFin").innerHTML=marcoLogico.finSupuesto;
			document.getElementById("lblDescripcionProposito").innerHTML=marcoLogico.propositoDescrip;
			document.getElementById("lblSupuestoProposito").innerHTML=marcoLogico.propositoSupuesto;
			
		}
		
		function armarArrayIndicadores(){
			var objIndMarcoLog;
			for(var i=0; i<listIndicadores.length; i++){
				objIndMarcoLog=new Object();
				objIndMarcoLog.idIndMarcoLog="null";
				objIndMarcoLog.idMarcoLog="null";
				objIndMarcoLog.indicador=listIndicadores[i].indicador;
				objIndMarcoLog.definicionIndicador=listIndicadores[i].definicionIndicador;
				objIndMarcoLog.unidadMedida=listIndicadores[i].unidadMedida;
				objIndMarcoLog.medioVerificacion=listIndicadores[i].medioVerificacion;
				objIndMarcoLog.situacionActual=listIndicadores[i].situacionActural;
				objIndMarcoLog.situacionFinal=listIndicadores[i].situacionFinal;
				arrayIndMarcoLog.push(objIndMarcoLog);
			}
			armarGrilla1();
			
		}
		
		
		
		
///////////////////////////////////////////////EFECTOS DE PANTALLA
		$(document).ready(function(){
			
			$("#div_fuenteFinanciadora").hide();
			$("#div_beneficiarios").hide();
			
		});
		
</script>



</head>
<body>

<div  class="form-clasico" >

<div class="encabezado">APROBAR PROYECTO</div>
<br>


<div id="programa_div">

<fieldset><legend>PROGRAMA</legend>
<form action="buscarProgramaPorFiltro.jspx" >
 
<table width="100%">
  <tr>
    <td style="width:25%" align="right"><label>Buscar programa :</label></td>
    <td style="width:25%"><div id="div_cbxOpBusProg">
    <select name="cbxOpBusProg" id="cbxOpBusProg" onchange="cambiarFiltro();">
       <option value="0">----SELECCIONAR----</option>
       <option value="1">Por nombre</option>
       <option value="2">Por modalidad financiera</option>
       <option value="3">Por tipo de cuenta</option>
       <option value="4">Todos</option>
    </select></div>
   </td>
    <td style="width:25%" align="right">
	    <div id="div_opTextBusProg">
			<label id="opTextBusProg">Dato de busqueda:</label>
	    </div>
    </td>
    <td style="width:25%">
	    <div id="div_filtroBusProg">
	         <div id="div_txtNomPrograma">
			    <input type="text" id="txtNomPrograma" name="txtNomPrograma" />
			 </div>
	         <div id="div_cbxModFinan">
			    <select name="cbxModFinan" id="cbxModFinan">
			      <option value="0">----Modalidad----</option>
			      <c:forEach items="${listModalidadFinanciamiento}"
									var="modalidad">
									<option value="${modalidad.tablaEspecificaID}">
										<c:out value="${modalidad.descripcionCabecera}" />
									</option>
								</c:forEach>
			    </select>
			 </div>
			 <div id="div_cbxTipoCuenta">
			    <select name="cbxTipoCuenta" id="cbxTipoCuenta">
			      <option value="0">----SELECCIONAR----</option>
			      <c:forEach items="${listTipoCuenta }"
									var="tipoCuenta">
									<option value="${tipoCuenta.tablaEspecificaID}">
										<c:out value="${tipoCuenta.descripcionCabecera}" />
									</option>
								</c:forEach>
			    </select>
			 </div>	    
	    </div>
    </td>
  </tr>
  <tr>
    <td height="63" colspan="4">
		  <table width="90%" align="center" border="0">
			    <tr height="40px" valign="middle">
			       <td align="right">
			          <input type="button" name="btnBusProg" id="btnBusProg" value="Buscar Programa" onclick="onclick_btnBusProg();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		           </td>
			    </tr>
		  </table>
		    <fieldset id="tabla111" style="margin:auto;border:none;"><!--border:none;-->
  			</fieldset>
  			<div id="div_msjResultadosProgramas" style="margin:auto;text-align: center;"> <label><span>No hay datos en la busqueda.</span></label> </div>
      </td>
  </tr>
  </table>
  <input type="hidden" id="filtroBusqueda" name="filtroBusqueda" />
  <input type="hidden" id="opcionBusqueda" name="opcionBusqueda" />
 </form>
 <form action="buscarProyectosPorPrograma.jspx">
 <input type="hidden" id="idPrograma" name="idPrograma" />
 </form>
</fieldset>

</div>

<br>

<div id="proyectos_programa_div">

<fieldset><legend>PROYECTOS DEL PROGRAMA:&nbsp;<span id="nombreProgramaTit"></span></legend>
<form action="buscarProyectoPorFiltro.jspx" >
<table width="100%">
  <tr>
    <td style="width:25%" align="right"><label>Buscar proyecto por:</label></td>
    <td style="width:25%">
    <div id="div_cbxOpBusProy">
	    <select name="cbxOpBusProy" id="cbxOpBusProy" onchange="cambiarFiltroProy();">
	       <option value="0">----SELECCIONAR----</option>
	       <option value="1">Codigo proyecto</option>
	       <option value="2">Nombre proyecto</option>
	       <option value="3">Todos</option>
	    </select>
    </div>
   </td>
    <td style="width:25%" align="right">
   		<div id="div_opTextBusProy">
			<label id="opTextBusProy">Dato de busqueda:</label>
	    </div> 
    
    </td>
    <td style="width:25%">
	<div id="div_filtroBusProy">
	         <div id="div_txtCodProyecto">
			    <input type="text" id="txtCodProyecto" name="txtCodProyecto" />
			 </div>
			 <div id="div_txtNomProyecto">
			    <input type="text" id="txtNomProyecto" name="txtNomProyecto" />
			 </div>
	</div>
   </td>
  </tr>
  <tr>
    <td height="63" colspan="4">
		  <table width="90%" align="center" border="0">
			    <tr height="40px" valign="middle">
			       <td align="right">
			          <input type="button" name="btnBusProy" id="btnBusProy" value="Buscar Proyecto" onclick="onclick_btnBusProy();" />
		           </td>
			    </tr>
		  </table>
	     <fieldset id="tabla2" style="margin:auto;border:none;"><!--border:none;-->
  		 </fieldset>
  		 <div id="div_msjResultadosProyectos" style="margin:auto;text-align: center;"> <label><span>No hay datos en la busqueda.</span></label> </div>
      </td>
  </tr>
  </table>
   <input type="hidden" id="filtroBusquedaProy" name="filtroBusquedaProy" />
  <input type="hidden" id="opcionBusquedaProy" name="opcionBusquedaProy" />
  <input type="hidden" id="idPrograma2" name="idPrograma2" />
  
</form>

<form id="obtenerDatosProyecto" name="obtenerDatosProyecto">
	<input type="hidden" id="idproyecto1" name="idproyecto1"/>
</form>


</fieldset>
</div>

<br>
<div id="datos_proyecto_div">
<form id="formAprobarProyecto" name="formAprobarProyecto" action="aproDesProyecto.jspx">
<fieldset><legend>APROBAR / DESAPROBAR:&nbsp;<span id="nombreProyectoTit"></span></legend>
<table width="100%">
  <tr>
    <td style="width:25%" align="right"><label>Estado :</label></td>
    <td style="width:25%">
	    <div id="div_cbxEstProg">
		    <select name="cbxEstProg" id="cbxEstProg">
		      <option value="0">----SELECCIONAR----</option>
		      
		    </select>
	    </div>
   </td>
   <td style="width:25%" align="left">
    	<input type="button" value="Guardar" onclick="aproDesProyecto();"/>
   </td>
   <td style="width:25%">
      &nbsp;
   </td>
  </tr>
</table>
</fieldset>
<input type="hidden" id="idProy" name="idProy">
<input type="hidden" id="subAreaTem" name="subAreaTem">
<input type="hidden" id="idProg" name="idProg">
<input type="hidden" id="cantPeriodo" name="cantPeriodo">
<input type="hidden" id="codProy" name="codProy">
<input type="hidden" id="duracionProy" name="duracionProy">
<input type="hidden" id="nombreProy" name="nombreProy">
<input type="hidden" id="nroOrdenProy" name="nroOrdenProy">
<input type="hidden" id="cbxEstProg" name="cbxEstProg">
</form>


<br>

<fieldset><legend>DETALLE DE PROYECTO</legend>
<table width="100%">
  <tr>
	<td style="text-align: right;"><label>Codigo de Proyecto:</label></td>
	<td><label><span id="codProyecto"></span></label></td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
  </tr>
  <tr>
    <td style="text-align: right;"><label>Proyecto:</label></td>
    <td><label><span id="txtProyecto"></span></label>
     
    </td>
    <td style="text-align: right;"><label>Modalidad Finaciamiento:</label></td>
    <td><label><span id="modalidadFinan"></span></label>

    </td>
  </tr>
  <tr>
    <td style="text-align: right;"><label>Programa:</label></td>
    <td><label><span id="nombrePrograma"></span></label>  

    </td>
    <td style="text-align: right;"><label>Area Tematica:</label></td>
    <td>
       <label><span id="areaTematicaSpan"></span></label>
    </td>
  </tr>
  <tr>
    <td style="text-align: right;"><label>Sub area Tematica:</label></td>
    <td>
       <label><span id="subAreaTematicaSpan"></span></label>
    </td>

   <td style="text-align: right;"><label>Duraci&oacute;n:</label></td>
    <td>
    	<label><span id="duracionProySpan"></span></label>
    </td>
  </tr>
   </table>
</fieldset>  

<br>

<fieldset><legend>CUENTA CORRIENTE</legend>
  <table width="100%">
  <!-- 
  <tr>
    <td style="width:25%" align="right"><label>Banco:</label></td>
    <td style="width:25%">
		<div id="div_cbxBancos">
			<select name="cbxBancos" id="cbxBancos">
			   <option value="0">----SELECCIONAR----</option>
			</select>
		</div>
    </td>
    <td style="width:25%" align="right"><label>Tipo Moneda:</label></td>
    <td style="width:25%">
		<div id="div_cbxTipoMoneda1">
			<select name="cbxTipoMoneda1" id="cbxTipoMoneda1">
			  <option value="0">----SELECCIONAR----</option>
			</select>
		</div>
    </td>
  </tr>
  <tr>
    <td align="right"><label>Nro. de Cuenta:</label></td>
    <td>
		<input type="text" id="txtNroCuenta" name="txtNroCuenta">
    </td>
    <td align="right">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
   -->
  <tr>
    <td height="63" colspan="4">
    <!-- 
	  <table width="90%" align="center" border="0">
		<tr height="0px" valign="middle">
		   <td align="right"><input type="button" name="btnAgregarCuenta" id="btnAgregarCuenta" value="Agregar cuenta" onclick="onclick_btnAgregarCuenta();"/>
		<input type="button" value="testear cuenta" onclick="testeoCuentas();"></td> 
		</tr>
	  </table>   -->
	  <fieldset id="tablaCuentaCorriente" style="margin:auto;border:none;" ></fieldset>
	  
    </td>
  </tr>
  </table>
</fieldset>


<br>


<fieldset><legend>UBICACION DEL PROYECTO</legend>
  <table width="100%">
  <tr>
    <td height="63" colspan="4">
      <table class="table-clasico" width="100%" >
      	<caption>
		<label>Listado de ubicaciones</label>
		</caption>
      
          <thead>
            <tr>
              <th><label>nro</label></th>
              <th><label>Dep.</label></th>
              <th><label>Prov.</label></th>
              <th><label>Dist.</label></th>
			  <th><label>Loc.</label></th>
              <th><label>Estrato</label></th>
              <!-- <th>&nbsp;</th> -->
            </tr>
           </thead>
           <tbody id="tbodyUbiProy">
           </tbody>       
     </table>
       </td>
  </tr>
  </table>
  <br>
  <div id="div_beneficiarios">
  <fieldset><legend>Estrato de :&nbsp;<span id="dep_Prov_Dist"></span></legend>
  
    <table class="table-clasico" width="100%" >
    <caption>
		<label>Listado de beneficiarios</label>
	</caption>
          <thead>
            <tr>
              <th><label>nro</label></th>
              <th><label>Beneficiarios/Estrato</label></th>
              <th><label>Tipo Ben.</label></th>
              <th><label>Carac. Poblacion</label></th>
              <th><label>Desc.</label></th>
              <!-- <th>&nbsp;</th> -->
            </tr>
           </thead>
           <tbody id="tbodyBeneficiarios">
           </tbody>       
     </table>
     
    <table width="85%" align="center" border="0">
	    <tr valign="middle"><!--  height="40px"  -->
	       <td align="right">
	       <!-- <input type="button" name="btnAgregarEstrato" id="btnAgregarEstrato" value="Agregar estrato" onclick="onclick_btnAgregarEstrato();"/> -->
           <input type="button" value="OK" onclick="onclick_btnAgregarUbicacionBeneficiarios()"></td>
	    </tr>
    </table>
    
  </fieldset>
  </div>
  
</fieldset>

<br>

<fieldset><legend>EJECUTOR E INFORMACI&Oacute;N FINANCIAMIENTO</legend>

<br>
      <table class="table-clasico" width="100%" ><!-- class="table-clasico"-->
		<caption>
			<label>Listado de instituciones de financiamiento</label>
		</caption>
          <thead>
            <tr>
              <th><label>nro</label></th>
              <th><label>RUC.</label></th>
              <th><label>Inst.</label></th>
              <th><label>Dir.</label></th>
              <th><label>Tfno.</label></th>
              <th><label>Rep. Legal</label></th>
              <th><label>Contacto</label></th>
              <th><label>Correo</label></th>
              <th><label>Observ.</label></th>
              <th><label>&nbsp;</label></th>
              <!-- <th>&nbsp;</th> -->
            </tr>
           </thead>
           <tbody id="tbodyInstitucion">
           </tbody>       
     </table>
     <table class="table1" width="100%" >
     <tr>
      <td style="text-align: right;" width="75%"><label>Institucion Ejecutora:</label></td>
	    <td>
	      <div id="div_cbxInstitucionEjecutora">
	       <select name="cbxInstitucionEjecutora" id="cbxInstitucionEjecutora">
	            <option value="0">----SELECCIONAR----</option>
	       </select>
          </div>   
	    </td>
  </tr>
     </table>
     <br>
  <div id="div_fuenteFinanciadora">
  <fieldset><legend>Fuente Financiadora:&nbsp;<span id="nomIns"></span></legend>
  <table width="100%">
	  <tr>
	    <td style="text-align: right;"><label>Tipo Fuente Financiadora:</label></td>
	    <td>
	      <div id="div_cbxTipoFuenteFinan">
	        <select name="cbxTipoFuenteFinan" id="cbxTipoFuenteFinan">
	          <option value="0">----SELECCIONAR----</option>
	          </select>
	      </div>
	      </td>
	    <td style="text-align: right;"><label>Tipo Moneda:</label></td>
	    <td>
	     <div id="div_cbxTipoMoneda">
	        <select name="cbxTipoMoneda" id="cbxTipoMoneda">
	          <option value="0">----SELECCIONAR----</option>
	        </select>
	     </div>  
	    </td>
	  </tr>
	  <tr>
	    <td style="text-align: right;"><label>Monto:</label></td>
	    <td><input name="txtMontoFinan" id="txtMontoFinan" type="text"  disabled="disabled"/></td>
	    <td style="text-align: right;">&nbsp;</td>
	    <td>&nbsp;</td>
	  </tr>
  </table>
    <table width="90%" align="center" border="0">
	    <tr valign="middle"><!-- height="40px"   -->
	       <td align="right"><input type="button" name="btnAgregarFuenteFinan" id="btnAgregarFuenteFinan" value="OK" onclick="onclick_btnAgregarFuenteFinan();"/></td>
	    </tr>
    </table>
  </fieldset>
  </div>
     
</fieldset>

<br>

<fieldset><legend>RESUMEN DEL PROYECTO</legend>
<table width="100%" border="0" align="center">

  <tr>
    <td colspan="5">       
	    <table class="table-clasico" width="100%" >
	    <caption>
			<label>Listado de resumenes</label>
		</caption>
	          <thead>
	            <tr>
	              <th><label>nro</label></th>
	              <th><label>Tipo.</label></th>
	              <th><label>Resumen</label></th>
	            </tr>
	           </thead>
	           <tbody id="tbodyResumenObserv">
	           </tbody>       
	    </table>
     </td>
  </tr>
  </table>
</fieldset>


<br>

<fieldset><legend>MARCO LOGICO</legend>
  <table width="100%">
  <tr>
    <td align="right" width="25%"><label>Resumen Ejecutivo:</label></td>
    <td colspan="4">
     <!--  <textarea id="txaResumenEjecutivo" name="txaResumenEjecutivo" COLS="50" ROWS="3"></textarea>  -->
       <label id="lblResumenEjecutivo"></label>  
    </td>
  </tr>
  </table>
  
  <fieldset><legend>FIN</legend>
  <table width="100%">
	  <tr>
	    <td style="text-align: right;" width="25%"><label>Descripcion:</label></td>
	    <td>
	    <!--  <textarea id="txaDescripcionFin" name="txaDescripcionFin" COLS="35" ROWS="2"></textarea> -->
	      <label id="lblDescripcionFin"></label>    
	    </td>
	    <td style="text-align: right;" width="25%"><label>Supuesto:</label></td>
	    <td>
	    <!-- <textarea id="txaSupuestoFin" name="txaSupuestoFin" COLS="35" ROWS="2"></textarea>   -->  
	     <label id="lblSupuestoFin"></label>
	    </td>
	  </tr>
  </table>
  </fieldset>
  
  <br />
  
<fieldset><legend>PROPOSITO</legend>
  <table width="100%">
	  <tr>
	    <td style="text-align: right;" width="25%"><label>Descripcion:</label></td>
	    <td>
	     <!--  <textarea id="txaDescripcionProposito" name="txaDescripcionProposito" COLS="35" ROWS="2"></textarea> -->
	      <label id="lblDescripcionProposito"></label>
	      </td>
	    <td style="text-align: right;" width="25%"><label>Supuesto:</label></td>
	    <td>
	    <!--  <textarea id="txaSupuestoProposito" name="txaSupuestoProposito" COLS="35" ROWS="2"></textarea>   --> 
	     <label id="lblSupuestoProposito"></label>
	    </td>
	  </tr>
  </table>
  </fieldset>
  
  <br>
  <div id="div_indicador">
  <fieldset><legend>INDICADOR</legend>
  
<fieldset id="tabla1" style="margin:auto;border:none;">

</fieldset>
</fieldset>
  </div>
</fieldset>
<br>

<fieldset><legend>DOCUMENTOS</legend>
  <table width="100%">
  <tr>
    <td style="text-align: right;"><label>Documento Resumen (pdf,docx):</label></td>
    <td>
   		<!--  <iframe src="showArchivoDownloadPerfil2.jspx" id="window" frameborder="0" height="50px" width="100%" scrolling="no"></iframe> -->  
       <iframe id="frameDownload"  id="window" frameborder="0" height="45px" width="100%" scrolling="no"></iframe>  
    </td>
    <td style="text-align: right;">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  </table>
  </fieldset>
</div>
</div>

</body>
</html>