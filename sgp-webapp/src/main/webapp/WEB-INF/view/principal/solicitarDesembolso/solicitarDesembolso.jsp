<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/gestorAjax.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/AtssefGrid.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/AtssefCBX.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/AtssefValidadorForm.js"></script>

<script type="text/javascript"	src="${pageContext.request.contextPath}/js/util.js"></script>

<title>Solicitar Desembolso</title>

<script type="text/javascript">


/***************GLOBALES**********************************************/

/***************combobox AtssefCBX****************/
var VALUE_INI_CBX = "0";
var TEXT_INI_CBX = "----seleccionar----";
/***************combobox AtssefCBX****************/
 
    /* constantes */
    var RUC_FONDAM="101026326";//fuente financiadora por defecto
    var PERFIL_EJECUTOR = "7";
    var PERFIL_DAFI = "3";
    var COD_ESTADO_DESEMBOLSO_POR_EVALUAR = "22";
    var COD_ESTADO_DESEMBOLSO_APROBADO = "23";
    var COD_ESTADO_DESEMBOLSO_RECHAZADO = "24";
    
    /* fin constantes */
 
    /* objetos AJAX*/
    var objXHR25869;//COMBOBOX RESULTADOS
    var objXHR25870;//COMBOBOX ACTIVIDADES
    var objXHR25871;//COMBOBOX COSTO-ACTIVIDAD
    var objXHR25872;//COMBOBOX-PERIODO / GRILLA-CRONOGRAMA-COSTO-ACTIVIDAD
    var objXHR25873;  //TIPO DE DESEMBOLSO
    var objXHR25874;  //TIPO DE MONEDA
    var objXHR25875;//DESEMBOLSO
    var objXHR25876; //FUENTE FINANCIADORA
    var objXHR25877; //CUENTA CORRIENTE
    var objXHR25878; //ESTADO DESEMBOLSO
    var objXHR25879; //TIPO DE CAMBIO
    var objXHR25880; //ULTIMA VERSION DESEMBOLSO
    /* fin objetos AJAX*/
    
    /* objetos de AtssefCBX */
    var objCBXResultados;
    var objCBXActividades;
    var objCBXCostoActividad;
    var objCBXCronogramaCostoActividad;
    var objCBXTipoDesembolso;
    var objCBXTipoMoneda;
    //var objCBXTipoMoneda2;       
    var objCBXFuenteFinanciadora;
    var objCBXCuentaCorriente;
    var objCBXEstadosDesembolso;
    /* fin objetos de AtssefCBX */
    
    /* objetos de AtssefGrid */
    var objGRIDCronogramaCostoActividad;
    var objGRIDSolicitudesDesembolso;
    var objGRIDDetalleSolicitudDesembolso;
    var objGRIDTipoCambio;
    /* objetos de AtssefGrid */
    
 	var arrayResultados=new Array();
	
	var arrayActividades=new Array();
	
	var arrayCostoActividad=new Array();
		
	var arrayTipoDesembolso=new Array();
	var idCBXTipoDesembolso;
	
	var arrayTipoMoneda=new Array();

	//var objDesembolso=new Object();
	
	var arrayCronogramaCostoActividad=new Array();//grilla
	var arrayCronogramaCostoActividadTemp=new Array();//grilla
	var arrayPeriodosCronoCostoActividad=new Array();//combo
	
	var arrayFuenteFinanciadora=new Array();
	
	var arrayCuentaCorriente = new Array(); 
	
	var arraySolicitudesDesembolso = new Array();
	
	var arrayEstadosDesembolso = new Array();
	
	var arrayDetalleSolicitudDesembolso = new Array();
	
	var MONTO_ACUMULADO=0;
	
	var arrayTipoCambio = new Array();
	
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
						  AttributeCBX : { id:"cbxResultado" , name:"cbxResultado" , style:"width:460px"},
	                      AttributeOPIni : { value: VALUE_INI_CBX , text: TEXT_INI_CBX},
						  dataCBX : arrayResultados,
						  IDContenedor : "div_cbxResultado",
						  funcionCBX : {
										onchange:{estado:"1", funcion:"cargarDataCBXActividades()"}
						               }
	                     });		
	}
	
/***************FIN COMBOBOX RESULTADOS**********************************************/

/***************COMBOBOX ACTIVIDADES**********************************************/

	function cargarDataCBXActividades(){
		limpiarCombosCambiandoResultado();
	
		objXHR25870 = new ObjetoAJAX();
		var url = "cargarCBXActividad.jspx?" +
	    "idResultado=" + encodeURIComponent(objCBXResultados.getIdAtssefCBX());
		objXHR25870.enviar(url,"post","llenarDataCBXActividad",null);
	}
	
	function llenarDataCBXActividad(){
		arrayActividades=JSON.parse(objXHR25870.respuestaTexto());
		//alert(JSON.stringify(arrayActividades));
		rederizarCBXActividades();
	}

	function rederizarCBXActividades(){
		objCBXActividades = new AtssefCBX({
						  AttributeCBX : { id:"cbxActividad" , name:"cbxActividad" ,style:"width:460px"},
	                      AttributeOPIni : { value: VALUE_INI_CBX , text: TEXT_INI_CBX},
						  dataCBX : arrayActividades,
						  IDContenedor : "div_cbxActividad",
						  funcionCBX : {
										onchange:{estado:"1", funcion:"cargarDataCBXCostoActividad();"}
						               }
	                     });		
	}
	
/***************FIN COMBOBOX ACTIVIDADES**********************************************/

/********FUNCIONES PARA COMBOS*********/
  function limpiarCombosCambiandoResultado(){

		document.getElementById("cbxActividad").value="0";
		document.getElementById("cbxFuenteFinanciadora").value="0";
		document.getElementById("cbxCostoActividad").value="0";

		arrayCronogramaCostoActividad = new Array();
		SF_renderizarGrillaCronogramaCostoActividad(arrayCronogramaCostoActividad);

	}

	function limpiarCombosCambiandoActividad(){

		//document.getElementById("cbxActividad").value="0";
		document.getElementById("cbxFuenteFinanciadora").value="0";
		document.getElementById("cbxCostoActividad").value="0";

		arrayCronogramaCostoActividad = new Array();
		SF_renderizarGrillaCronogramaCostoActividad(arrayCronogramaCostoActividad);

	}

	function limpiarCombosCambiandoFuenteFinanciadora(){

		//document.getElementById("cbxActividad").value="0";
		//document.getElementById("cbxFuenteFinanciadora").value="0";
		document.getElementById("cbxCostoActividad").value="0";

		arrayCronogramaCostoActividad = new Array();
		SF_renderizarGrillaCronogramaCostoActividad(arrayCronogramaCostoActividad);

	}

	function limpiarCombosCambiandoCostoActividad(){

		//document.getElementById("cbxActividad").value="0";
		//document.getElementById("cbxFuenteFinanciadora").value="0";
		//document.getElementById("cbxCostoActividad").value="0";

		arrayCronogramaCostoActividad = new Array();
		SF_renderizarGrillaCronogramaCostoActividad(arrayCronogramaCostoActividad);

	}
/********FIN FUNCIONES PARA COMBOS*********/


/***************COMBOBOX COSTO-ACTIVIDAD**********************************************/

	function cargarDataCBXCostoActividad(){
		limpiarCombosCambiandoActividad();
	
	    	objXHR25871 = new ObjetoAJAX();
			var url = "cargarCBXCostoActividad.jspx?" +
		    "idActividad=" + encodeURIComponent(objCBXActividades.getIdAtssefCBX());
			objXHR25871.enviar(url,"post","llenarDataCBXCostoActividad",null);	
	   
	}
	
	function llenarDataCBXCostoActividad(){
		arrayCostoActividad=JSON.parse(objXHR25871.respuestaTexto());
		//alert(JSON.stringify(arrayCostoActividad));
		rederizarCBXCostoActividad();
	}

	
	function rederizarCBXCostoActividad(){
		objCBXCostoActividad = new AtssefCBX({
						  AttributeCBX : { id:"cbxCostoActividad" , name:"cbxCostoActividad" ,style:"width:460px"},
	                      AttributeOPIni : { value: VALUE_INI_CBX , text: "cant. - unid.Medida"},
						  dataCBX : arrayCostoActividad,
						  IDContenedor : "div_cbxCostoActividad",
						  funcionCBX : {
										onchange:{estado:"1", funcion:"cargarDataCronogramaCostoActividad()"}
						               }
	                     });		
	}
	
/***************FIN COMBOBOX COSTO-ACTIVIDAD**********************************************/



/***************COMBOBOX-PERIODO / GRILLA-CRONOGRAMA-COSTO-ACTIVIDAD*********************/
	
	function cargarDataCronogramaCostoActividad(){
		limpiarCombosCambiandoCostoActividad();
		if(objCBXCostoActividad.getIdAtssefCBX() != VALUE_INI_CBX){//solo si selecciona un costo actividad!						
			
			if(document.getElementById("cbxFuenteFinanciadora").value != VALUE_INI_CBX){//solo si selecciona una fuente financiadora
				objXHR25872 = new ObjetoAJAX();
				var url = "cargarDataCronogramaCostoActividad.jspx?" +
			    "objCostoActividad=" + encodeURIComponent(  obtenerObjSeleccionadoCostoActividad(objCBXCostoActividad.getIdAtssefCBX())  )+"&"+
			    "idFuenteFinanciadora=" + encodeURIComponent(  document.getElementById("cbxFuenteFinanciadora").value  );
				objXHR25872.enviar(url,"post","llenarDataCronogramaCostoActividad",null);	
			}else{
				alert("Seleccionar una fuente financiadora!");
			}
							
		}
	}
	
	function obtenerObjSeleccionadoCostoActividad(idCostoActSeleccionado){
		for(var i=0; i<arrayCostoActividad.length; i++){
			if(arrayCostoActividad[i].costoActividadID == idCostoActSeleccionado){
				console.log("METODO :: obtenerObjSeleccionadoCostoActividad : "+JSON.stringify(arrayCostoActividad[i]));
				return JSON.stringify(arrayCostoActividad[i]);
			}
		}
	}
	
	function llenarDataCronogramaCostoActividad(){
		arrayCronogramaCostoActividad = JSON.parse(objXHR25872.respuestaTexto());
		
		var  arrayDataCronogramaCostoActividad = JSON.parse(objXHR25872.respuestaTexto());
		//alert("DATA CRONOGRAMA COSTO ACTIVIDAD :"+JSON.stringify(arrayDataCronogramaCostoActividad));
		//arrayCronogramaCostoActividad = arrayDataCronogramaCostoActividad[0]; 
		//arrayPeriodosCronoCostoActividad = arrayDataCronogramaCostoActividad[1];
		
		//SF_rederizarCBXPeriodoCronogramaCostoActividad();
		SF_renderizarGrillaCronogramaCostoActividad(arrayCronogramaCostoActividad);
	}

	function SF_rederizarCBXPeriodoCronogramaCostoActividad(){
		objCBXCronogramaCostoActividad = new AtssefCBX({
						  AttributeCBX : { id:"cbxPeriodoCronogramaCostoActividad" , name:"cbxPeriodoCronogramaCostoActividad" },
	                      AttributeOPIni : { value: VALUE_INI_CBX , text: TEXT_INI_CBX, style:"width:150px"},
						  dataCBX : arrayPeriodosCronoCostoActividad,
						  IDContenedor : "div_cbxPeriodoCronogramaCostoActividad",
						  funcionCBX : {
										onchange:{estado:"1", funcion:"getCronogramaCostoActByPeriodo()"}
						               }
	                     });		
	}
	

	function SF_renderizarGrillaCronogramaCostoActividad(arrayCronoCostoActividad){
		objGRIDCronogramaCostoActividad = new AtssefGrid({
		 "cabecera":["Periodo"
		             ,"Cantidad"
		             ,"Saldo Prog."
		             ,"Saldo Disp."
		             ],
		 "campos_cabecera":[
							{name:"periodo"}
							,{name:"cantidadDesUnidadMedida"}
							,{name:"saldoProgramadoDesc"}
							,{name:"saldoDisponibleDesc"}
						   ],
		 "arrayDataTabla":arrayCronoCostoActividad,
		 "id_contenedor":"grillaCronogramaCostoActividad",
		 "atributos_tabla":{ width:"100%",
		                     align:"left",
		                     class:"table-clasico"
		                    },
		 "titulo_tabla":{estado:"si",titulo:"Cronograma Costo Actividad"},
		 "campos_extra_tabla":{
								"detalle":{estado:"no",titulo:" ddd",textoCampo:" detalle  ",funcionDetalle:"#"},
							    "eliminarFila":{estado:"no",funcionEliminarFila:"#"},
								"detalleDinamico":{estado:"si",titulo:"Desembolso",textoCampo:"Agregar",funcionDetalleDinamico:"solicitarDesembolsoByCronoCostoAct();"}
		                      },
		 "numeracion":"no",
		 "objTabla":"objGRIDCronogramaCostoActividad",
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
	
	
	
	function solicitarDesembolsoByCronoCostoAct(){
		var idActividad = document.getElementById("cbxActividad").value;
		console.log(" idActividad :: "+idActividad);
		var idCostoActividad = document.getElementById("cbxCostoActividad").value;
		console.log(" idCostoActividad :: "+idCostoActividad);
		var objCosAct = JSON.parse(obtenerObjSeleccionadoCostoActividad(idCostoActividad));
		console.log(" objeto costo actividad seleccionado :: "+JSON.stringify(objCosAct));
		var objCronoCosAct = JSON.parse(objGRIDCronogramaCostoActividad.getObjSeleccionado());
		console.log(" objeto cronograma costo actividad seleccionado :: "+JSON.stringify(objCronoCosAct));
		//validar el combo de moneda para monto solicitado
		if(document.getElementById("cbxTipoMoneda").value != "0"){
			if(!existeArrayDetalleDesembolso(objCronoCosAct.cronogramaCostoActividadID)){					
					//calcular saldo solicitado
					var saldoPorAgregar = parseFloat(objCronoCosAct.saldoDisponible);
					var montoSolicitado = parseFloat(document.getElementById("txtMontoSolicitado").value);
					MONTO_ACUMULADO = (saldoPorAgregar+montoSolicitado);
					
					document.getElementById("txtMontoSolicitado").value = MONTO_ACUMULADO;
					
					//llenar la grilla de detalle de desembolso
					var objDetalleDesembolso = new Object();
					objDetalleDesembolso.resultadoDesc = document.getElementById("cbxResultado").options[document.getElementById("cbxResultado").selectedIndex].text;
					objDetalleDesembolso.idActividad = idActividad; 
					objDetalleDesembolso.descActividad = obtenerDescActividad(idActividad);//
					objDetalleDesembolso.idCostoActividad = idCostoActividad;
					objDetalleDesembolso.descCostoAct = obtenerDescCostoAct(idCostoActividad);//
					objDetalleDesembolso.cantidad = objCronoCosAct.cantidad;
					objDetalleDesembolso.periodo =  objCronoCosAct.periodo;
					objDetalleDesembolso.cronogramaCostoActividadID = objCronoCosAct.cronogramaCostoActividadID;
					objDetalleDesembolso.montoSolicitado = objCronoCosAct.saldoDisponible;
					//objDetalleSolicitudDesembolso.desembolsoID = //se llena en el controlador luego del save de desembolso
					objDetalleDesembolso.tipoMonedaDesc =document.getElementById("cbxTipoMoneda").options[document.getElementById("cbxTipoMoneda").selectedIndex].text;  
					objDetalleDesembolso.tipoMonedaID = document.getElementById("cbxTipoMoneda").value;
					objDetalleDesembolso.detalleMontoMoneda = objDetalleDesembolso.montoSolicitado+" "+objDetalleDesembolso.tipoMonedaDesc; //mostrar en la grilla detalle
					
					arrayDetalleSolicitudDesembolso.push(objDetalleDesembolso);			
					renderizarGrillaDetalleSolicitudDesembolso();		
			}else{
				alert("el cronograma costo actividad ya ha sido seleccionado!");
			}}else{
			alert("seleccionar moneda de monto solicitado");
			document.getElementById("cbxTipoMoneda").focus();
		}			
	}
	
	function existeArrayDetalleDesembolso(idCostoAct){
		
		for(var i=0; i<arrayDetalleSolicitudDesembolso.length; i++){
			if( arrayDetalleSolicitudDesembolso[i].cronogramaCostoActividadID == idCostoAct)								
				return true;
		}
		
		return false;
	}
	
	/*
	function obtenerSaldoTotalActividad(idActividad){
		var saldoTotAct = 0;
		console.log("SUMANDO saldoTotAct ::::::::  idActividad "+ idActividad);
		for(var i=0; i<arrayCostoActividad.length; i++){
			if(arrayCostoActividad[i].actividadID == idActividad){
				saldoTotAct = parseFloat(arrayCostoActividad[i].saldoTotalCostAct) + saldoTotAct;
				console.log("SUMANDO saldoTotAct ::::::::"+ saldoTotAct);
			}
		}
		return saldoTotAct;		
	}
	*/
	
//arrayActividades; arrayCostoActividad;

	function obtenerDescActividad(idAct){
		console.log("obtenerDescActividad :::: "+idAct);
		for(var i=0; i<arrayActividades.length; i++){
			if(arrayActividades[i].value  == idAct){				
				return arrayActividades[i].text; 
			}
		}
	}
	
	function obtenerDescCostoAct(idCostoAct){
		for(var i=0; i<arrayCostoActividad.length; i++){
			if(arrayCostoActividad[i].costoActividadID  == idCostoAct){				
				return arrayCostoActividad[i].text; 
			}
		}
	}
/***************FIN COMBOBOX-PERIODO / GRILLA-CRONOGRAMA-COSTO-ACTIVIDAD*****************/


/***************GRILLA DETALLE SOLICITUD DE DESEMBOLSO*****************/
	function renderizarGrillaDetalleSolicitudDesembolso(){
			objGRIDDetalleSolicitudDesembolso = new AtssefGrid({
			 "cabecera":["Resultado"
			             ,"Actividad"
			             ,"Periodo"
			             ,"Costo Actividad"			             
			             ,"Cantidad"
			             ,"Monto"
			             ],
			 "campos_cabecera":[
			                    {name:"resultadoDesc"}
								,{name:"descActividad"}
								,{name:"periodo"}
								,{name:"descCostoAct"}
								,{name:"cantidad"}
								,{name:"detalleMontoMoneda"}
							   ],
			 "arrayDataTabla":arrayDetalleSolicitudDesembolso,
			 "id_contenedor":"grillaDetalleSolicitudDesembolso",
			 "atributos_tabla":{ width:"100%",
			                     align:"left",
			                     class:"table-clasico"
			                    },
			 "titulo_tabla":{estado:"si",titulo:"Detalle solicitud de desembolso"},
			 "campos_extra_tabla":{
									"detalle":{estado:"no",titulo:" ddd",textoCampo:" detalle  ",funcionDetalle:"#"},
								    "eliminarFila":{estado:"si",funcionEliminarFila:"eliminarDetalleDesembolso()"},
									"detalleDinamico":{estado:"no",titulo:"Desembolso",textoCampo:"Agregar",funcionDetalleDinamico:""}
			                      },
			 "numeracion":"no",
			 "objTabla":"objGRIDCronogramaCostoActividad",
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
			
			
	function eliminarDetalleDesembolso(){
			//Antes de eliminar restar el monto detalle al monto acumulado
			
			MONTO_ACUMULADO = (MONTO_ACUMULADO - JSON.parse(objGRIDDetalleSolicitudDesembolso.getObjSeleccionado()).montoSolicitado );
			document.getElementById("txtMontoSolicitado").value = MONTO_ACUMULADO;
			
		//var rpt=confirm("�Seguro que desea eliminar el indicador nro:"+(objGRIDDetalleSolicitudDesembolso.getIndiceArray()+1)+"?");
		//if(rpt==true){
			arrayDetalleSolicitudDesembolso.splice(objGRIDDetalleSolicitudDesembolso.getIndiceArray(),1);
		//}
		
			
			renderizarGrillaDetalleSolicitudDesembolso();
		
	}	
	
	
	/***************FIN GRILLA DETALLE SOLICITUD DE DESEMBOLSO*****************/



/******************************TIPO DE DESEMBOLSO***********************************/
 	function cargarDataCBXTipoDesembolso(){
 		objXHR25873 = new ObjetoAJAX();
 		objXHR25873.enviar("cargarCBXTipoDesembolso.jspx","post","llenarDataCBXTipoDesembolso",null);
	}
	
	
	function llenarDataCBXTipoDesembolso(){
		arrayTipoDesembolso = JSON.parse(objXHR25873.respuestaTexto());
		//alert(JSON.stringify(arrayTipoDesembolso));
		rederizarCBXTipoDesembolso();
	}
	
	function rederizarCBXTipoDesembolso(){
		objCBXTipoDesembolso = new AtssefCBX({
						  AttributeCBX : { id:"cbxTipoDesembolso" , name:"cbxTipoDesembolso" ,style:""},
	                      AttributeOPIni : { value: VALUE_INI_CBX , text: TEXT_INI_CBX},
						  dataCBX : arrayTipoDesembolso,
						  IDContenedor : "div_cbxTipoDesembolso",
						  funcionCBX : {
										onchange:{estado:"0", funcion:"alert('tipo desem')"}
						               }
	                     });		
	}
	
	function obtenerIdCBXTipoDesembolso(){
		idCBXTipoDesembolso = document.getElementById("cbxTipoDesembolso").value;
	}
 
/****************************FIN TIPO DE DESEMBOLSO***********************************/ 


/******************************TIPO DE MONEDA***********************************/
function cargarDataCBXTipoMoneda(){
	objXHR25874 = new ObjetoAJAX();
	objXHR25874.enviar("cargarCBXTipoMoneda.jspx","post","llenarDataCBXTipoMoneda",null);
}

function llenarDataCBXTipoMoneda(){
	arrayTipoMoneda=JSON.parse(objXHR25874.respuestaTexto());
	//alert(JSON.stringify(arrayTipoMoneda));
	rederizarCBXTipoMoneda();
	//rederizarCBXTipoMoneda2();
}
//MONEDA 1
function rederizarCBXTipoMoneda(){
	objCBXTipoMoneda = new AtssefCBX({
					  AttributeCBX : { id:"cbxTipoMoneda" , name:"cbxTipoMoneda" ,style:"width:80px"},
                      AttributeOPIni : { value: VALUE_INI_CBX , text: "--moneda--"},
					  dataCBX : arrayTipoMoneda,
					  IDContenedor : "div_cbxTipoMoneda",
					  funcionCBX : {
									onchange:{estado:"0", funcion:"alert('tipo desem')"}
					               }
                     });		
}

//MONEDA 2
/*
function rederizarCBXTipoMoneda2(){
	objCBXTipoMoneda2 = new AtssefCBX({
					  AttributeCBX : { id:"cbxTipoMoneda2" , name:"cbxTipoMoneda2" ,style:""},
                      AttributeOPIni : { value: VALUE_INI_CBX , text: TEXT_INI_CBX},
					  dataCBX : arrayTipoMoneda,
					  IDContenedor : "div_cbxTipoMoneda2",
					  funcionCBX : {
									onchange:{estado:"0", funcion:"alert('tipo desem')"}
					               }
                     });		
}
*/
/****************************FIN TIPO DE MONEDA***********************************/
 
 
/****************************DESEMBOLSO*************************************/
 
function cargarDataDesembolso(){
	objXHR25875 = new ObjetoAJAX();
	objXHR25875.enviar("cargarDataDesembolso.jspx","post","llenarDataDesembolso",null);
}
function llenarDataDesembolso(){
	arraySolicitudesDesembolso=JSON.parse(objXHR25875.respuestaTexto());//listado de desembolsos por proyecto,ordenado en forma descendente 
	//alert(JSON.stringify(arraySolicitudesDesembolso));
	if(arraySolicitudesDesembolso.length >0 ){
		 cargarCamposDesembolso(2);
		 
	}else{
		cargarCamposDesembolso(1);//inicialmente cuando no hay desembolsos registrados
	}
	    
	deshabilitarCamposDesem();
	
	renderizarGrillaSolicitudesDesembolso();
}

/*********CARGAR COMBOBOX ESTADO DESEMBOLSO*****/
function cargarEstadosDesembolso(){
	objXHR25878 = new ObjetoAJAX();
	objXHR25878.enviar("cargarEstadosDesembolso.jspx","post","llenarDataEstadosDesembolso",null);
}
function llenarDataEstadosDesembolso(){
	arrayEstadosDesembolso = JSON.parse(objXHR25878.respuestaTexto()); 
	//alert(JSON.stringify(arraySolicitudesDesembolso));
	rederizarCBXEstadosDesembolso();
	deshabilitarCampo("cbxEstadoDesembolso");
}

function rederizarCBXEstadosDesembolso(){
	objCBXEstadosDesembolso = new AtssefCBX({
					  AttributeCBX : { id:"cbxEstadoDesembolso" , name:"cbxEstadoDesembolso" ,style:""},
                      AttributeOPIni : { value: VALUE_INI_CBX , text: TEXT_INI_CBX},
					  dataCBX : arrayEstadosDesembolso,
					  IDContenedor : "div_cbxEstadoDesembolso",
					  funcionCBX : {
									onchange:{estado:"0", funcion:"alert('tipo desem')"}
					               }
                     });		
}
/********FIN CARGAR COMBOBOX ESTADO DESEMBOLSO*****/
 

/********CARGAR / DESHABILITAR CAMPOS DESEMBOLSO*****/
function cargarCamposDesembolso(flag){
	
	document.getElementById("cbxTipoMoneda").value = document.getElementById("hiddenTipoMonedaPlanOpe").value;
	var perfilUsuario = document.getElementById("hiddenPerfilUsuario").value;//???
	document.getElementById("cbxEstadoDesembolso").value = COD_ESTADO_DESEMBOLSO_POR_EVALUAR;
	if(flag == 1){//inicialmente cuando no hay desembolsos registrados		
		document.getElementById("txtSaldoDesembolsoAnterior").value = 0;
		//document.getElementById("txtVersionDesembolso").value = "1";				
	}else if(flag == 2){
		document.getElementById("txtSaldoDesembolsoAnterior").value = arraySolicitudesDesembolso[0].saldoDesembolsoAnteriorView;//el primer desembolso es la ultima version
		var ultimaVersionDesembolso=arraySolicitudesDesembolso[0].versionDePeriodo;
		//document.getElementById("txtVersionDesembolso").value = (parseInt(ultimaVersionDesembolso)+1);		
	}	
	cargarComboPeriodosProyecto();		
}

function deshabilitarCamposDesem(){		
	deshabilitarCampo("txtSaldoDesembolsoAnterior");
	deshabilitarCampo("txtVersionDesembolso");
	deshabilitarCampo("cbxTipoMoneda");
	deshabilitarCampo("txtMontoSolicitado");
	//deshabilitarCampo("cbxEstadoDesembolso");
}

/*
 * 
 function cargarCamposDesembolso(flag){
		
		var perfilUsuario = document.getElementById("hiddenPerfilUsuario").value;//???
		if(flag == 1){//inicialmente cuando no hay desembolsos registrados
			//deshabilitarCampo("txtMontoSolicitado");		
			//deshabilitarCampo("cbxEstadoDesembolso");
			document.getElementById("cbxEstadoDesembolso").value = COD_ESTADO_DESEMBOLSO_POR_EVALUAR;
			deshabilitarCampo("txtSaldoDesembolsoAnterior");
			document.getElementById("txtSaldoDesembolsoAnterior").value = 0;
			//cargarComboPeriodosProyecto();
			deshabilitarCampo("txtVersionDesembolso");
			document.getElementById("txtVersionDesembolso").value = "1";				
		}else if(flag == 2){
			//deshabilitarCampo("txtMontoSolicitado");
			document.getElementById("cbxEstadoDesembolso").value = COD_ESTADO_DESEMBOLSO_POR_EVALUAR;//????
			//deshabilitarCampo("cbxEstadoDesembolso");
			deshabilitarCampo("txtSaldoDesembolsoAnterior");
			document.getElementById("txtSaldoDesembolsoAnterior").value = arraySolicitudesDesembolso[0].saldoDesembolsoAnterior;//el primer desembolso es la ultima version
	    	//cargarComboPeriodosProyecto();
			deshabilitarCampo("txtVersionDesembolso");
			var ultimaVersionDesembolso=arraySolicitudesDesembolso[0].versionDePeriodo;
			document.getElementById("txtVersionDesembolso").value = (parseInt(ultimaVersionDesembolso)+1);		
		}
		
		//seleccionar la moneda automaticamente segun la menoda del plan operativo
		
		document.getElementById("cbxTipoMoneda").value = document.getElementById("hiddenTipoMonedaPlanOpe").value;
		deshabilitarCampo("cbxTipoMoneda");
		deshabilitarCampo("txtMontoSolicitado");
		deshabilitarCampo("cbxEstadoDesembolso");
		cargarComboPeriodosProyecto();
			
	}
 
 
 */



function cargarComboPeriodosProyecto(){
	var cantPeriodosProyecto = document.getElementById("hiddenPeriodoProyecto").value;
	var arrayCantPeriodosProy = new Array();
	var objPerProy;
	for(var i=0; i<cantPeriodosProyecto; i++){
		objPerProy = new Object();
		objPerProy.value = (i+1);
		objPerProy.text  = (i+1);
		arrayCantPeriodosProy.push(objPerProy);
	}
	//renderizar combo
	var objCBXCantPerProy = new AtssefCBX({
		  AttributeCBX : { id:"cbxCantPeriodoProy" , name:"cbxCantPeriodoProy" ,style:""},
        AttributeOPIni : { value: VALUE_INI_CBX , text: TEXT_INI_CBX},
		  dataCBX : arrayCantPeriodosProy,
		  IDContenedor : "div_cbxCantPeriodoProy",
		  funcionCBX : {
						onchange:{estado:"1", funcion:"getUltimaVersionDesembolso()"}
		               }
    });	
	
}
 
 
 function getUltimaVersionDesembolso(){
	 if(document.getElementById("cbxCantPeriodoProy").value != 0){
		 objXHR25880 = new ObjetoAJAX();
		 var url = "getUltimaVersionDesembolso.jspx?" +
		    "periodo=" + encodeURIComponent(document.getElementById("cbxCantPeriodoProy").value);
		 objXHR25880.enviar(url,"get","mostrarUltimaVersionDesembolso",null); 
	 }
 }
 
 function mostrarUltimaVersionDesembolso(){
		var ultimaVersionDesem = JSON.parse(objXHR25880.respuestaTexto()); 
		//alert("ultima version desembolso : "+ ultimaVersionDesem);
		document.getElementById("txtVersionDesembolso").value=ultimaVersionDesem;
 }

/*******FIN CARGAR / DESHABILITAR CAMPOS DESEMBOLSO********/

/**************************FIN DESEMBOLSO***********************************/


/****************************FUENTE FINANCIADORA************************************/
function cargarDataFuenteFinanciadora(){
	objXHR25876 = new ObjetoAJAX();
	objXHR25876.enviar("cargarFuenteFinanciadora.jspx","post","llenarDataFuenteFinanciadora",null);
}
function llenarDataFuenteFinanciadora(){
	arrayFuenteFinanciadora=JSON.parse(objXHR25876.respuestaTexto());
	//alert(JSON.stringify(arrayFuenteFinanciadora));
	if(arrayFuenteFinanciadora.length>0){
		rederizarCBXFuenteFinanciadora();
		seleccionarFuenteFinanciadoraPorDefecto();				
	}	
}

function rederizarCBXFuenteFinanciadora(){
	objCBXFuenteFinanciadora = new AtssefCBX({
					  AttributeCBX : { id:"cbxFuenteFinanciadora" , name:"cbxFuenteFinanciadora" , style:"width:460px"},
                      AttributeOPIni : { value: VALUE_INI_CBX , text: TEXT_INI_CBX},
					  dataCBX : arrayFuenteFinanciadora,
					  IDContenedor : "div_cbxFuenteFinanciadora",
					  funcionCBX : {
									onchange:{estado:"1", funcion:"limpiarCombosCambiandoFuenteFinanciadora()"}
					               }
                     });	
}


function seleccionarFuenteFinanciadoraPorDefecto(){
	
	for(var i=0; i<arrayFuenteFinanciadora.length; i++){
		if(arrayFuenteFinanciadora[i].rucInstitucion == RUC_FONDAM){
			document.getElementById("cbxFuenteFinanciadora").value = arrayFuenteFinanciadora[i].value;
			break;
		}
	}
	
}

/***************************FIN FUENTE FINANCIADORA*********************************/


/****************************CUENTA CORRIENTE************************************/
function cargarDataCuentaCorriente(){
	objXHR25877 = new ObjetoAJAX();
	objXHR25877.enviar("cargarCuentaCorriente.jspx","post","llenarDataCuentaCorriente",null);
}
function llenarDataCuentaCorriente(){
	arrayCuentaCorriente=JSON.parse(objXHR25877.respuestaTexto());
	//alert(JSON.stringify(arrayCuentaCorriente));
	rederizarCBXCuentaCorriente();
}

function rederizarCBXCuentaCorriente(){
	objCBXCuentaCorriente = new AtssefCBX({
					  AttributeCBX : { id:"cbxCuentaCorriente" , name:"cbxCuentaCorriente" , style:""},
                      AttributeOPIni : { value: VALUE_INI_CBX , text: TEXT_INI_CBX},
					  dataCBX : arrayCuentaCorriente,
					  IDContenedor : "div_cbxCuentaCorriente",
					  funcionCBX : {
									onchange:{estado:"0", funcion:"alert('tipo desem')"}
					               }
                     });	
}

/***************************CUENTA CORRIENTE*********************************/



/***************LOGICA DE LA PANTALLA*****************/
 
 function getCronogramaCostoActByPeriodo(){
	arrayCronogramaCostoActividadTemp = new Array();
	var idCBXCronoCostoAct = objCBXCronogramaCostoActividad.getIdAtssefCBX();
	if(idCBXCronoCostoAct != VALUE_INI_CBX){//solo si seleccina un periodo
		if(idCBXCronoCostoAct != "todo"){
			for(var i=0; i<arrayCronogramaCostoActividad.length; i++){
				if(arrayCronogramaCostoActividad[i].periodo==idCBXCronoCostoAct){
					arrayCronogramaCostoActividadTemp.push(arrayCronogramaCostoActividad[i]);	
				}
			}
			SF_renderizarGrillaCronogramaCostoActividad(arrayCronogramaCostoActividadTemp);
		}else{
			SF_renderizarGrillaCronogramaCostoActividad(arrayCronogramaCostoActividad);
		}	
		
	}
}


/***************FIN LOGICA DE LA PANTALLA*****************/



/***************GRILLA LISTADO DE SOLICITUDES DE DESEMBOLSO*****************/
 
function renderizarGrillaSolicitudesDesembolso(){
	objGRIDSolicitudesDesembolso = new AtssefGrid({
	 "cabecera":["Periodo","version Periodo","Fuente Finan."],
	 "campos_cabecera":[
						{name:"periodo"},
						{name:"versionDePeriodo"},
						{name:"fuenteFinanciadoraDesc"},
					   ],
	 "arrayDataTabla":arraySolicitudesDesembolso,
	 "id_contenedor":"grillaSolicitudesDesembolso",
	 "atributos_tabla":{ width:"100%",
	                     align:"left",
						 class:"table-clasico"
	                    },
	 "titulo_tabla":{estado:"si",titulo:"Solicitudes de Desembolso"},
	 "campos_extra_tabla":{
							"detalle":{estado:"si",titulo:" ",textoCampo:"Detalle",funcionDetalle:"cargarDesembolsoDetalle();"},
						    "eliminarFila":{estado:"no",funcionEliminarFila:"eliminarBanco();"},
							"detalleDinamico":{estado:"no",titulo:"Desembolso",funcionDetalleDinamico:""}
	                      },
	 "numeracion":"si",
	 "objTabla":"objGRIDSolicitudesDesembolso",
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



/***************FIN GRILLA LISTADO DE SOLICITUDES DE DESEMBOLSO*****************/


/*****************DETALLE SOLICITUDES DE DESEMBOLSO **********************/
function ocultarDetalleDesembolso(){
	$("#div_detalleDesembolso").hide();
}

function mostrarDetalleDesembolso(){
	$("#div_detalleDesembolso").show('slow');
}

function cargarDesembolsoDetalle(){
	
	var objInnerDesembolso = JSON.parse(objGRIDSolicitudesDesembolso.getObjSeleccionado());
	
	document.getElementById("span_periodo").innerHTML=objInnerDesembolso.periodo;
	document.getElementById("span_versionPeriodo").innerHTML=objInnerDesembolso.versionDePeriodo;
	document.getElementById("span_tipoDesembolso").innerHTML=objInnerDesembolso.tipoDesembolsoDesc;
	//document.getElementById("span_tipoCambio").innerHTML=objInnerDesembolso.tipoCambio;
	document.getElementById("span_montoSolicitado").innerHTML=objInnerDesembolso.montoSolicitado +" "+objInnerDesembolso.tipoMonedaDesc;
	document.getElementById("span_estDesembolso").innerHTML=objInnerDesembolso.estadoDesembolso;
	document.getElementById("span_montoAutorizado").innerHTML=objInnerDesembolso.montoAutorizado +" "+objInnerDesembolso.tipoMonedaDesc;
	document.getElementById("span_fuenteFinan").innerHTML=objInnerDesembolso.fuenteFinanciadoraDesc;
	document.getElementById("span_cuentaCorriente").innerHTML=objInnerDesembolso.cuentaCorrienteDesc;
	document.getElementById("span_saldoDesemAnt").innerHTML=objInnerDesembolso.saldoDesembolsoAnterior +" "+objInnerDesembolso.tipoMonedaDesc;
	
	
	mostrarDetalleDesembolso();
}
/*************FIN DETALLE SOLICITUDES DE DESEMBOLSO**********************/


/****************VALIDACION Y REGISTRO SOLICITUD**********************/
function registrarSolicitudDesembolso(){
	console.log("entro a registrarSolicitudDesembolso");

	if(validarDetalleDesembolso()){
		if(validarFormulario()){
			if(validarTipoCambio()){
				console.log("registrar solicitud desembolso !!!");
				capturarListasDatos();
		    	document.getElementById("solDesembolsoForm").submit();
		    	alert("Se registro solicitud de desembolso!");	
				
			}			
	    }
	}		
}



function capturarListasDatos(){
	
	document.getElementById("hiddenArrayDetalleSolicitudDesembolso").value=JSON.stringify(arrayDetalleSolicitudDesembolso);
	
	document.getElementById("hiddenTxtMontoSolicitado").value=document.getElementById("txtMontoSolicitado").value;
	document.getElementById("hiddenCbxTipoMoneda").value=document.getElementById("cbxTipoMoneda").value;
	document.getElementById("hiddenTxtSaldoDesembolsoAnterior").value=document.getElementById("txtSaldoDesembolsoAnterior").value;
	document.getElementById("hiddenCbxEstadoDesembolso").value=document.getElementById("cbxEstadoDesembolso").value;
	document.getElementById("hiddenTxtVersionDesembolso").value=document.getElementById("txtVersionDesembolso").value;

}

function validarDetalleDesembolso(){
	if(arrayDetalleSolicitudDesembolso.length > 0){
		console.log("validarDetalleDesembolso :"+arrayDetalleSolicitudDesembolso.length);
		return true;
	}else{
		alert("seleccionar al menos un cronograma costo actividad!");
		return false;
	}
}

function validarTipoCambio(){
	if(arrayTipoCambio.length > 0){
		console.log("validarTipoCambio :"+arrayDetalleSolicitudDesembolso.length);
		return true;
	}else{
		alert("Debe registrar el tipo de cambio!");
		return false;
	}
	
}

function validarFormulario(){
	var objFildsForm = new AtssefValidadorForm([
	                                  		  {valorInicial:"0", id:"cbxTipoDesembolso", msj:"seleccionar tipo desembolso!"},
	                                  		//{valorInicial:"", id:"txtTipoCambio", msj:"ingresar tipo cambio!"},
	                                  		{valorInicial:"0", id:"txtMontoSolicitado", msj:"el monto solicitado tiene que ser mayor a cero!"},
	                                  		//{valorInicial:"", id:"txtFechaTipoCambio", msj:"ingresar fecha tipo de cambio!"}, 
	                                  		{valorInicial:"0", id:"cbxCantPeriodoProy", msj:"seleccionar periodo proyecto!"},
	                                  		{valorInicial:"0", id:"cbxFuenteFinanciadora", msj:"seleccionar fuente financiadora!"},
	                                  		{valorInicial:"0", id:"cbxCuentaCorriente", msj:"seleccionar cuenta corriente!"}
	                                  		]);		
	                                  		return objFildsForm.validar();
}

/**************FIN VALIDACION Y REGISTRO SOLICITUD**********************/

 
 
 
/**************TIPO CAMBIO**********************/ 
 	function obtenerListadoTipoCambio(){
 		objXHR25879 = new ObjetoAJAX();
 		objXHR25879.enviar("obtenerGRIDListadoTipoCambio.jspx","post","llenarDataGRIDListadoTipoCambio",null);
	}
	
	function llenarDataGRIDListadoTipoCambio(){
		//alert(objXHR25879.respuestaTexto());
		console.log("data "+ objXHR25879.respuestaTexto());
		if(objXHR25879.respuestaTexto() != ""){
			arrayTipoCambio=JSON.parse(objXHR25879.respuestaTexto());
			//alert(JSON.stringify(arrayTipoCambio));
			renderizarGrillaTipoCambio();	
			
		}
		
	}
 
 
 
 function renderizarGrillaTipoCambio(){
	objGRIDTipoCambio = new AtssefGrid({
	 "cabecera":["Tipo Cambio","Fecha","De","A"],
	 "campos_cabecera":[
						{name:"tipoCambio"},
						{name:"fechaTipoCambioDesc"},
						{name:"tipoMonedaDENombre"},
						{name:"tipoMonedaANombre"},
					   ],
	 "arrayDataTabla":arrayTipoCambio,
	 "id_contenedor":"grillaTipoCambio",
	 "atributos_tabla":{ width:"100%",
	                     align:"left",
						 class:"table-clasico"
	                    },
	 "titulo_tabla":{estado:"si",titulo:"Lista tipo cambio"},
	 "campos_extra_tabla":{
							"detalle":{estado:"no",titulo:" ",textoCampo:"Detalle",funcionDetalle:"cargarDesembolsoDetalle();"},
						    "eliminarFila":{estado:"si",funcionEliminarFila:"eliminarTiposCambio()"},
							"detalleDinamico":{estado:"no",titulo:"Desembolso",funcionDetalleDinamico:""}
	                      },
	 "numeracion":"no",
	 "objTabla":"objGRIDSolicitudesDesembolso",
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
 
    var inicializarTipoCambio=1;
    var eliminarTipoCambio=2;
    
    var objXHR258790120;
	function eliminarTiposCambio(){
	
		var rpt=confirm("�Seguro que desea eliminar el tipo de cambio nro:"+(objGRIDTipoCambio.getIndiceArray()+1)+"?");
		if(rpt==true){
			arrayTipoCambio.splice(objGRIDTipoCambio.getIndiceArray(),1);
			
			//alert(objGRIDTipoCambio.getIndiceArray());			
			objXHR258790120 = new ObjetoAJAX();
			var url = "inicializarOeliminarTipoCambio.jspx?"+
					 "modo=" + encodeURIComponent(eliminarTipoCambio) +
					 "&valor=" + encodeURIComponent(objGRIDTipoCambio.getIndiceArray()) ;
			objXHR258790120.enviar(url , "post" , "respuestaTipoCambioEliminar"  , null );
		}
		renderizarGrillaTipoCambio();
    }	
	
	
	var objXHR258790113;
	function inicializarTiposCambios(){
				
			arrayTipoCambio = new Array();
			
			//alert(objGRIDTipoCambio.getIndiceArray());			
			objXHR258790113 = new ObjetoAJAX();
			var url = "inicializarOeliminarTipoCambio.jspx?"+
					 "modo=" + encodeURIComponent(inicializarTipoCambio) +
					 "&valor=" + encodeURIComponent("33") ;
			objXHR258790113.enviar(url , "post" , "respuestaTipoCambioInicializar"  , null );
		
		renderizarGrillaTipoCambio();
    }	 
	
	function respuestaTipoCambioEliminar(){
		var rpt = objXHR258790120.respuestaTexto(); 
		console.log("respuesta Eliminar Tipo Cambio : "+ rpt);	
	}
	
	function respuestaTipoCambioInicializar(){
		var rpt = objXHR258790113.respuestaTexto(); 
		console.log("respuesta Inicializar Tipo Cambio : "+ rpt);
	}
 /**************FIN TIPO CAMBIO**********************/ 
 
 
 
/***************INICIO**********************************************/

//$(window).load(function() {
$(document).ready(function() {

/***********COMBOBOX RESULTADOS**********/
	cargarDataCBXResultados();	
	
/********FIN COMBOBOX RESULTADOS*********/
 
/***************COMBOBOX-PERIODO / GRILLA-CRONOGRAMA-COSTO-ACTIVIDAD*********************/
 	SF_renderizarGrillaCronogramaCostoActividad(arrayCronogramaCostoActividad);//mostrar tabla vacia al cargar la pagina
 /***************FIN COMBOBOX-PERIODO / GRILLA-CRONOGRAMA-COSTO-ACTIVIDAD*****************/
 
 
/***********COMBOBOX TIPO DE DESEMBOLSO**********/
	cargarDataCBXTipoDesembolso();
/***********FIN COMBOBOX TIPO DE DESEMBOLSO**********/
 
/***********TIPO DE MONEDA**********/ 
 	cargarDataCBXTipoMoneda();
/***********FIN TIPO DE MONEDA**********/ 	
 
/****************************FUENTE FINANCIADORA************************************/
    cargarDataFuenteFinanciadora();
/***************************FIN FUENTE FINANCIADORA*********************************/
 
/****************************CUENTA CORRIENTE************************************/
    cargarDataCuentaCorriente();
/***************************CUENTA CORRIENTE*********************************/

/*********************DETALLE DESEMBOLSO*********************************/
	renderizarGrillaDetalleSolicitudDesembolso();
/*********************FIN DETALLE DESEMBOLSO*****************************/

/*************TIPO CAMBIO******************/
obtenerListadoTipoCambio();
renderizarGrillaTipoCambio();
/*************FIN TIPO CAMBIO******************/

/*****************DESEMBOLSO****************/
	cargarEstadosDesembolso();
    ocultarDetalleDesembolso();
    cargarDataDesembolso();
/*****************FIN DESEMBOLSO****************/


    inicializarTiposCambios();

});

$(window).load(function() {
	
	
});





//calendario
/*
$(function() {

	$("#txtFechaTipoCambio").datepicker({
		changeMonth : true,
		changeYear : true
	});
});*/
/***************FIN INICIO**********************************************/

</script>

</head>
<body>

<form id="solDesembolsoForm" method="post" action="registrarSolicitudDesembolso.jspx" class="form-clasico" accept-charset="UTF-8">
<div class="encabezado">SOLICITAR DESEMBOLSO</div>
	<br />
  <div id="desembolso_div">
  <fieldset>
  <legend>DESEMBOLSO</legend>
    <fieldset>
  <legend>Costo Actividad / Cronograma Costo Actividad</legend>
                <table width="100%" border="0">
 
					<tr >
						<td style="text-align: right; width: 20%;">
							<label>Resultado:</label>
						</td>
						<td style="text-align: left; width: 80%;">
						    <div id="div_cbxResultado">
								<select name="cbxResultado" id="cbxResultado" style="width:460px">
									<option value="0">----seleccionar----</option>
								</select>
							</div>
						</td>
					</tr>
					<tr >
						<td style="text-align: right; width: 20%;">
						    <label>Actividad:</label>
						</td>
						<td style="text-align: left; width: 80%;">
							<div id="div_cbxActividad">
								<select name="cbxActividad" id="cbxActividad" style="width:460px">
									<option value="0">----seleccionar----</option>
								</select>
							</div>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 20%;">
							<label>Fuente Financiadora:</label>
						</td>
						<td style="text-align: left; width: 80%;">
							<div id="div_cbxFuenteFinanciadora">
							<select name="cbxFuenteFinanciadora" id="cbxFuenteFinanciadora" style="width:460px">
								<option value="0">----SELECCIONAR----</option>
						    </select>
					    </div>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 20%;"><label>Costo Actividad:</label>
						</td>
						<td style="text-align: left; width: 80%;">
							<div id="div_cbxCostoActividad">
								<select name="cbxCostoActividad" id="cbxCostoActividad" style="width:460px">
									<option value="0">cant. - unid.Medida</option>
								</select>
							</div>
						</td>												
					</tr>
					<!-- 
					<tr >
						<td style="text-align: right; width: 20%;">
							<label>Periodo:</label>
						</td>
						<td style="text-align: left; width: 80%;">
							<div id="div_cbxPeriodoCronogramaCostoActividad">
								<select name="cbxPeriodoCronogramaCostoActividad" id="cbxPeriodoCronogramaCostoActividad">
									<option value="0">----seleccionar----</option>
								</select>
							</div>
						</td>
					</tr>
					 -->
					<tr>
						<td colspan="4" style="text-align: right; width: 25%;">
						<br /> 
                           <fieldset id="grillaCronogramaCostoActividad" style="margin:auto;border:none;" >a</fieldset>
						</td>
						
					</tr>
		
				</table>

        </fieldset>
        
        <br />
        
    <fieldset>
	    <legend>Solicitud de Desembolso</legend>    
      <table width="100%" border="0">      
      			<tr>
      			<td style="text-align: right; width: 25%;"><label>Estado de&nbsp;&nbsp; Desembolso:</label></td>
      			<td style="text-align: left; width: 25%;">
      				<div id="div_cbxEstadoDesembolso">
							<select name="cbxEstadoDesembolso" id="cbxEstadoDesembolso">
								<option value="0">----SELECCIONAR----</option>
						    </select>
					</div>
      			</td>
      			<td colspan="2" rowspan="4" style="width:50%; text-align: right;">
	      			<div id="tipoCambio" style="width: 98%">
				
								<input type="button" value="Nuevo Tipo Cambio" 
										    ondblclick="fOpenModalDialog('createTipoCambioDesembolso2.jspx','610','185','70','70');obtenerListadoTipoCambio()"
										    />
									 <fieldset id="grillaTipoCambio" style="margin:auto;border:none;" >a</fieldset>
					</div>
					</td>
      			</tr>
				<tr>
					<td style="text-align: right; width: 25%;"><label>Tipo&nbsp;&nbsp; Desembolso:</label>
				    </td>
					<td style="text-align: left; width: 25%;"><div
								id="div_cbxTipoDesembolso">
							<select name="cbxTipoDesembolso" id="cbxTipoDesembolso">
								<option value="0">----seleccionar----</option>
						    </select>
						</div>
                    </td>
                  </tr>
                  <tr>  
					<td style="text-align: right; width: 25%;"><label>Saldo Desem. Anterior:</label>
				    </td>
					<td style="text-align: left; width: 25%;">
					   	<div id="div_txtSaldoDesembolsoAnterior">
							<input name="txtSaldoDesembolsoAnterior" id="txtSaldoDesembolsoAnterior" type="text" maxlength="8" />
					    </div>
					</td>
			    </tr>
				<tr>
					<td style="text-align: right; width: 25%;"><label>Monto Solicitado:</label>
				    </td>
					<td style="text-align: left; width: 25%;">
						<div id="div_txtMontoSolicitado" style="float: left;">
                          <input name="txtMontoSolicitado" id="txtMontoSolicitado" value="0" type="text" maxlength="8" style="width:80px;"/>
                        </div>
						<div id="div_cbxTipoMoneda" style="float: left;">
							<select name="cbxTipoMoneda" id="cbxTipoMoneda" style="width:80px;">
								<option value="0">----seleccionar----</option>
						    </select>
					    </div>
                        </td>
					</tr>
					<tr>
					<td style="text-align: right; width: 25%;">
                    <label>Cuenta Corriente:</label>
				    </td>
					<td style="text-align: left; width: 25%;">
							<div id="div_cbxCuentaCorriente">
							<select name="cbxCuentaCorriente" id="cbxCuentaCorriente">
								<option value="0">----SELECCIONAR----</option>
						    </select>
					    </div>
                        </td>
			    </tr>
                <tr>
					<td style="text-align: right; width: 25%;">
						<label>Periodo:</label>
				    </td>
					<td style="text-align: left; width: 25%;">
						<div id="div_cbxCantPeriodoProy">
							<select name="cbxCantPeriodoProy" id="cbxCantPeriodoProy">
								<option value="0">----SELECCIONAR----</option>
						    </select>
					    </div>										                    		
                    </td>
					<td style="text-align: right; width: 25%;">
                    <label>Version de&nbsp;Desembolso:</label>
				    </td>
					<td style="text-align: left; width: 25%;">
											<div id="div_txtVersionDesembolso">
							<input name="txtVersionDesembolso" id="txtVersionDesembolso" type="text" maxlength="8" />
					    </div>
                        </td>
			    </tr>
                <tr>
					<td style="text-align: right; width: 25%;">&nbsp;
				    </td>
					<td style="text-align: left; width: 25%;">&nbsp;
							
                        </td>
					<td style="text-align: right; width: 25%;">&nbsp;
				    </td>
					<td style="text-align: left; width: 25%;">&nbsp;
						
                        </td>
			    </tr>
			    <tr>
				    <td colspan="3" style="text-align: right; width: 75%;">&nbsp;
				    </td>
				    <td style="text-align: right; width: 25%;">
                        <input type="button" id="btnGrabarSolicitudDesembolso" value="Grabar Solicitud" onclick="registrarSolicitudDesembolso();"/>
				    </td>
				  </tr>
		</table>
<br />
     		<fieldset id="grillaDetalleSolicitudDesembolso" style="margin:auto;border:none;" >Error</fieldset>
      </fieldset>
      
         <br />
        
    <fieldset>
		  <legend>Listado de Solicitudes de Desembolso</legend>    
   		 <br />
     		<fieldset id="grillaSolicitudesDesembolso" style="margin:auto;border:none;" >Error</fieldset>
    </fieldset>
     <br />
    <div id="div_detalleDesembolso">
	    <fieldset>
			  <legend>Detalle de Solicitudes de Desembolso</legend>    
	   		 
	   		 <table align="right"><tr><td align="right"><input type="button" value="ok" onclick="ocultarDetalleDesembolso();"/>
	   		 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr></table><br />
	     	 <table width="100%">
				<tr>
					<td width="20%" style="text-align: right; width: 25%;"><label>Periodo:</label>
				    </td>
				    
					<td colspan="2" style="text-align: left; width: 25%;">
					    <label><span id="span_periodo"></span></label>
                    </td>
					
					<td width="25%" style="text-align: right; width: 25%;"><label>Version Desembolso:</label>
				    </td>
				    
					<td width="20%" style="text-align: left; width: 25%;">
						<label><span id="span_versionPeriodo"></span></label>
					</td>					
			    </tr>
				
				<tr>
					<td width="20%" style="text-align: right; width: 25%;"><label>Tipo Desembolso:</label>
				    </td>
				    
					<td colspan="2" style="text-align: left; width: 25%;">
					    <label><span id="span_tipoDesembolso"></span></label>
                    </td>
					
					<td width="25%" style="text-align: right; width: 25%;">
                    <label>Fuente Financiadora:</label>
                    				    </td>
				    
					<td width="20%" style="text-align: left; width: 25%;">
						<label><span id="span_fuenteFinan"></span></label>
					</td>					
			    </tr>
			    
                <tr>
                
					<td width="20%" style="text-align: right; width: 25%;"><label>Monto Solicitado:</label>
				    </td>
				    
					<td colspan="2" style="text-align: left; width: 25%;">
					    <label><span id="span_montoSolicitado"></span></label>
                    </td>
					
					<td width="25%" style="text-align: right; width: 25%;"><label>Estado desembolso:</label>
				    </td>
				    
					<td width="20%" style="text-align: left; width: 25%;">
						<label><span id="span_estDesembolso"></span></label>
					</td>					
			    </tr>
			    
			   <tr>              
					<td width="20%" style="text-align: right; width: 25%;"><label>Monto Autorizado:</label>
				    </td>
				    
					<td colspan="2" style="text-align: left; width: 25%;">
					    <label><span id="span_montoAutorizado"></span></label>
                    </td>
                    
                    <td width="20%" style="text-align: right; width: 25%;"><label>Saldo Desembolso Anterior:</label>
				    </td>
				    
					<td colspan="2" style="text-align: left; width: 25%;">
					    <label><span id="span_saldoDesemAnt"></span></label>
                    </td>
                    
					<!-- 
					<td width="25%" style="text-align: right; width: 25%;"><label>Moneda Monto Autorizado:</label>
				    </td>
				    
					<td width="20%" style="text-align: left; width: 25%;">
						<span id="span_monedaMontoAuto"></span>
					</td>
					 -->
						
									
			    </tr>
			    
			    <tr>              
					<td width="20%" style="text-align: right; width: 25%;"><label>Cuenta Corriente:</label>
				    </td>
				    
					<td colspan="2" style="text-align: left; width: 25%;">
					    <label><span id="span_cuentaCorriente"></span></label>
                    </td>
					
					<td width="25%" style="text-align: right; width: 25%;">&nbsp;
				    </td>
				    
					<td width="20%" style="text-align: left; width: 25%;">&nbsp;
						
					</td>										
			    </tr>	
			    
			    		    			
			  </table>	
			  <br />
	    </fieldset>
    </div> 
      
  </fieldset>
  </div>
  
<input type="hidden" id="hiddenArrayDetalleSolicitudDesembolso" name="hiddenArrayDetalleSolicitudDesembolso" />

<input type="hidden" id="hiddenTxtMontoSolicitado" name="hiddenTxtMontoSolicitado" />
<!-- <input type="hidden" id="hiddenTxtMontoAutorizado" name="hiddenTxtMontoAutorizado">   -->  
<input type="hidden" id="hiddenCbxTipoMoneda" name="hiddenCbxTipoMoneda" />  
<input type="hidden" id="hiddenTxtSaldoDesembolsoAnterior" name="hiddenTxtSaldoDesembolsoAnterior" />
<input type="hidden" id="hiddenCbxEstadoDesembolso" name="hiddenCbxEstadoDesembolso" />
<input type="hidden" id="hiddenTxtVersionDesembolso" name="hiddenTxtVersionDesembolso" />
</form>
<input type="hidden" id="hiddenPerfilUsuario" name="hiddenPerfilUsuario" value="${perfilUsuario}" />
<input type="hidden" id="hiddenPeriodoProyecto" name="hiddenPeriodoProyecto" value="${periodoProyecto}" />
<input type="hidden" id="hiddenTipoMonedaPlanOpe" name="hiddenTipoMonedaPlanOpe" value="${tipoMonedaPlanOpe}" />
</body>
</html>


