<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/viewMain.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloContabilidad.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloGeneral.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/tavConteiner.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/gestorAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/AtssefGrid.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/validador.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/AtssefCBX.js"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.hashchange.js"></c:url>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.easytabs.js"></c:url>"></script>


<script type="text/javascript">
var estado;

	$(document).ready(function(){
		//ocultaCampos();
		$("#tbodyResumenObserv").load("cargaResumenProyecto.jspx");
		$("#tbodyCuentaCorriente").load("cargarCuentasCorrientes.jspx");
		$('#tab-container').easytabs();
		
	});

	function cargaCajasInstitucion() {
		//alert($("#cbxTipoFuenteFinan").val());
		if ($("#cbxTipoFuenteFinan").val() == '186'
				|| $("#cbxTipoFuenteFinan").val() == '188') {
			//Institucion
			if ($("#cbxTipoFuenteFinan").val() == '186') {
				$("#txtInstitucion").attr("value", "Beneficiarios");
				$("#txtRuc").attr("value", "00000000000");
			} else if ($("#cbxTipoFuenteFinan").val() == '188') {
				$("#txtInstitucion").attr("value", "Otros");
				$("#txtRuc").attr("value", "00000000001");
			}
			$("#txtDireccion").attr("value", "Sin especificar");
			$("#txtTelefono").attr("value", "0000000");
			$("#txtRepLegal").attr("value", "Sin especificar");
			$("#txtContacto").attr("value", "Sin especificar");
			$("#txtCorreo").attr("value", "Sin especificar");
			$("#txaObservacion").attr("value", "Sin especificar");
		}else{
			//Institucion
			$("#txtInstitucion").attr("value", "");
			$("#txtRuc").attr("value", "");
			
			$("#txtDireccion").attr("value", "");
			$("#txtTelefono").attr("value", "");
			$("#txtRepLegal").attr("value", "");
			$("#txtContacto").attr("value", "");
			$("#txtCorreo").attr("value", "");
			$("#txaObservacion").attr("value", "");
		}
	}

	$(window).load(function() {
		////UBICACION DEL PROYECTO
		cargaCombosDepProvDist();
		cargaCombosEstratos();
		////INSTITUCIONES
		cargaComboTipoMoneda();//AQUI SE OBTIENE LA DATA DE INSTITUCION
		cargaComboTipoFuenteFinanciadora();
		obtenerDatosFondam();
		ocultaCampos();
	});
	
	function ocultaCampos(){
		estado= "<c:out value="${estadoProyecto}"></c:out>" ;
		//alert(estado);
		if (estado =='apro'){
			$(".hide").hide();//attr("style","visibility: hidden;");
			//alert($('#cbxInstitucionEjecutora option:selected').html());
			//$("#div_cbxInstitucionEjecutora").attr("style","visibility: hidden;");
		}
		
	}
</script>

<script type="text/javascript">
///////////////////////////////////////////funcionalidad para la tabla de instituciones

var arrayInstitucionFuenFinan=new Array();
var objInstitucionFuenFinan;
var indiceFila10=0;
var elmTBODY10;
var elmTR10;
var elmTD10;

///datos del fondam
var objXHR27688553;	
var RUCFONDAM="20434051950";
var FONDAM_REFERENCIA=false;
var objInstitucion=null;


//montos
var MONTO_FONDAM;
var MONTO_COFINANCIADOR;
var MONTO_CONTRAPARTIDA;
//tipos de fuentes financiadoras
var FUENTE_FINAN_EJECUTOR = "183";
var FUENTE_FINAN_COFINANCIADOR = "185";
var FUENTE_FINAN_BENEFICIARIOS = "186";
var FUENTE_FINAN_OTROS = "188";


//GLOBALES

function onclick_btnGestionarProyecto(){
	//var rptDatProy=validarDatosProyecto();//validarFormulario();
	//var rptDatProy=validarMarcoLogico();
    //if(rptDatProy==0){
    	var valida=validarExtensionArchivo();
    	if(valida==true){
    	capturarListasDatos();
    	document.getElementById("gestionProyForm").submit();
    	}
    	//document.forms[0].submit();
    //}
}

function capturarListasDatos(){
	
	//$("#tamanioArchivo").load("tamanioArchivo.jspx?imagenOArchivo="+$("#imagenOArchivo").val());
	//document.getElementById("txtListadoCuentaCorriente").value=JSON.stringify(arrayBancos);
	document.getElementById("txtListadoUbicacionBeneficiarios").value=JSON.stringify(arrayUbicacionBeneficiarios);
	document.getElementById("txtListadoInstitucionFuenFinan").value=JSON.stringify(arrayInstitucionFuenFinan);
	//document.getElementById("txtListadoResumenProyecto").value=JSON.stringify(arrayResumenPerfil);
	//document.getElementById("txtListadoIndicadores").value=JSON.stringify(arrayIndMarcoLog);
}
	
//////////////////////////////////////UBICACION DEL PROYECTO/////////////////////////////
	var objXHR1;
	var departamentos=new Array();
	var provincias=new Array();
	var distritos=new Array();
	var depProvDist=new Array();
	function cargaCombosDepProvDist(){
		objXHR1 = new ObjetoAJAX();
		objXHR1.enviar("cargaCombosDepProvDist.jspx","post","llenaCombosDepProvDist",null);
	}

	function llenaCombosDepProvDist(){
		depProvDist=JSON.parse(objXHR1.respuestaTexto());
		//var depProvDist=objXHR.respuestaTexto();
		//alert(objXHR.respuestaTexto());
		//alert(JSON.stringify(depProvDist));
		departamentos=depProvDist[0];
		provincias=depProvDist[1];
		distritos=depProvDist[2];
		//llenaComboDepartamentos();
		
		/* OBTENER DATOS DE UBICACION DE PROYECTO */
		obtenerDataUbicacionProyecto();
		
		/*OBTENER RESTRICCIONES */
		 cargarRestriccionDepProvDist();
	}

	function llenaComboDepartamentos(){
		var cbxDep = document.createElement("select");
		cbxDep.setAttribute("id", "cbxDepartamentos");
		cbxDep.setAttribute("name", "cbxDepartamentos");
		//evento para seleccionar la lista de provincias deacuerdo al departamento seleccionado.
		cbxDep.onchange=function(){
			llenaComboProvincias();limpiarComboDistritos();
			};
		//cbxDep.onchange=function(){alert(this.value);};
		//valor por defecto
		var op = document.createElement("option");
		op.setAttribute("value","0");
		var text=document.createTextNode("----SELECCIONAR----");
		op.appendChild(text);
		cbxDep.appendChild(op);
		//llenar valores de todos los departamentos
		for(var i=0; i<departamentosNew.length; i++){
			op = document.createElement("option");
			op.setAttribute("value",departamentosNew[i].depProvDistID);
			text=document.createTextNode(departamentosNew[i].descripcion);
			op.appendChild(text);
			cbxDep.appendChild(op);
		}
		//limpia div
		document.getElementById("div_cbxDepartamentos").innerHTML="";
		//pinta combo en el div
		document.getElementById("div_cbxDepartamentos").appendChild(cbxDep);
		//deshabilitar los 3 combos
		//deshabilitarCombosDepProvDist();
		
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
		for(var i=0; i<provinciasNew.length; i++){
			if(provinciasNew[i].idDepartamento==idDepartamento && provinciasNew[i].idProvincia!=0 && provinciasNew[i].idDistrito==0){
				provinciasPorDepartamento.push(provinciasNew[i]);
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
		for(var i=0; i<distritosNew.length; i++){
			if(distritosNew[i].idDepartamento==idDepartamento && distritosNew[i].idProvincia==idProvincia && distritosNew[i].idDistrito!=0){
				distritosPorProvincias.push(distritosNew[i]);
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


	function limpiarComboProvincias(){
		var cbxDist = document.createElement("select");
		cbxDist.setAttribute("id", "cbxProvincias");
		cbxDist.setAttribute("name", "cbxProvincias");
		//valor por defecto
		var op = document.createElement("option");
		op.setAttribute("value","0");
		var text=document.createTextNode("----SELECCIONAR----");
		op.appendChild(text);
		cbxDist.appendChild(op);
		//limpia div
		document.getElementById("div_cbxProvincias").innerHTML="";
		//pinta combo en el div
		document.getElementById("div_cbxProvincias").appendChild(cbxDist);
		
	}



	function limpiarComboDepartamentos(){
		var cbxDist = document.createElement("select");
		cbxDist.setAttribute("id", "cbxDepartamentos");
		cbxDist.setAttribute("name", "cbxDepartamentos");
		//valor por defecto
		var op = document.createElement("option");
		op.setAttribute("value","0");
		var text=document.createTextNode("----SELECCIONAR----");
		op.appendChild(text);
		cbxDist.appendChild(op);
		//limpia div
		document.getElementById("div_cbxDepartamentos").innerHTML="";
		//pinta combo en el div
		document.getElementById("div_cbxDepartamentos").appendChild(cbxDist);
		
	}


	function obtenerProvinciaID(depProvDistID){
		var idProvincia=null;
		//obtener el idDistrito 
		for(var i=0; i<provinciasNew.length; i++){
	          if(provinciasNew[i].depProvDistID == depProvDistID){
	        	  idProvincia=provinciasNew[i].idProvincia;break;
	          }
		}
		return idProvincia;
	}

	function obtenerDepartamentoID(depProvDistID){
		var idDepartamento=null;
		//obtener el idDepartamento 
		for(var i=0; i<departamentosNew.length; i++){
	          if(departamentosNew[i].depProvDistID == depProvDistID){
	        	 idDepartamento=departamentosNew[i].idDepartamento;break;
	          }
		}
		return idDepartamento;
	}
	
	///////////////////////////////////////////////////////////////////////////////////llena estratos
	var objXHR2;
	var estratosSoc;

	function cargaCombosEstratos(){
		objXHR2 = new ObjetoAJAX();
		objXHR2.enviar("cargaCombosEstratoSocial.jspx","post","llenaCombosEstratos",null);
	}

	function llenaCombosEstratos(){
		estratosSoc=JSON.parse(objXHR2.respuestaTexto());
		//alert(estratosSoc[0].length);
		//llenaComboEstratoBenDir();
	}

	///////////////////////////////////////////funcionalidad para la tabla ubicacion de proyectos
	var arrayUbicacionBeneficiarios=new Array();
	//var objUbicacionBeneficiarios;
	var indiceFila=0;
	var elmTBODY;
	var elmTR;
	var elmTD;


	function onclick_btnAgregarUbicacion(){
	    var rpt=validarUbicacion();
	    if(rpt){
	    	agregarUbicacion();
	    	limpiarCamposUbicacion();
	    }

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
		
	    objUbicacion.flagReferencia = false;
	    
	    objUbicacion.ubicacionProyectoID = 0;
	    
		var objUbicacionBeneficiarios=new Object;
		objUbicacionBeneficiarios.ubicacion=objUbicacion;
		objUbicacionBeneficiarios.beneficiarios=null;
	      
	    arrayUbicacionBeneficiarios.push(objUbicacionBeneficiarios);
	}

	function conteoUbicaciones(){
		alert("array ubicaciones: " +JSON.stringify(arrayUbicacionBeneficiarios));
		//alert("tamano de array json: " +arrayUbicacionTabla.length);
//	 	elmTBODY = document.getElementById("tbodyUbiProy");
//	 	var tam = elmTBODY.rows.length;
//	 	alert("tamano de las filas de la tabla: "+tam);
	}

	///////////////////////////////////////////////////////////////////////////////////

	function cargaDatosTabla(){
		   limpiarTabla();
		   elmTBODY = document.getElementById("tbodyUbiProy");
		   //alert(arrayUbicacionBeneficiarios[0].ubicacion.cbxDepText);
		     for(var p=0; p<arrayUbicacionBeneficiarios.length; p++){
				 elmTR = elmTBODY.insertRow(p);
				 if(p%2==0){
					  style="f1";  
				  }else{
					  style="f2";  
				  }
				  elmTR.setAttribute("class",style);
				 for (var i=0; i<7; i++) {
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
					  link.onclick=function(){limpiarCamposBeneficiarios();$("#div_beneficiarios").show('slow');verBeneficiarios(this.id);}; //limpiarCamposInstitucion();$("#div_fuenteFinanciadora").show('slow');verFuentesFinancieras(this.id);
					  var text=document.createTextNode("Beneficiarios");
					  link.appendChild(text);
					  var div = document.createElement("div");
					  div.setAttribute("align","center");
					  div.appendChild(link);
					  elmTD.appendChild(div);
					  }
					if(i==6){
					  var flagReferencia=arrayUbicacionBeneficiarios[p].ubicacion.flagReferencia;
					 
					   var div = document.createElement("div");
				       div.setAttribute("align","center");
				       if(estado !='apro'){
				       if(flagReferencia == true){ 
						   var link = document.createElement("a");
						   link.setAttribute("id",indiceFila);
						   link.setAttribute("class","link");
						   text=document.createTextNode("--");										       
					       link.appendChild(text);	
					       div.appendChild(link);
				       }else{								       							       
					       var imagen = document.createElement("img");
					       imagen.setAttribute("src","${pageContext.request.contextPath}/images/del.png");//imagen.setAttribute("src","../images/del.png"); 
					       imagen.setAttribute("id",indiceFila);
					       imagen.onclick=function(){
					    	   eliminarFilaUbi(this.id);
					       //indiceArray=((this.id)-1);
						   //eval(jsonInicio.campos_extra_tabla.detalleFlagReferencia.funcionDetalleCuentasCorrientes);
						   };
						   div.appendChild(imagen);					   
					  }}
				       elmTD.appendChild(div);
					}
					if(i!=0 && i!=5 && i!=6)
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
			var rpt=confirm("�Seguro que desea eliminar a la ubicacion de proyecto nro:"+eliMostrar+"?");
			if(rpt==true){
				arrayUbicacionBeneficiarios.splice(eli,1);
				limpiarTabla();
				cargaDatosTabla();	
				$("#div_beneficiarios").hide();
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
		 var rpt=validarBeneficiarosEstratos();
		 if(rpt){
			 limpiarTablaBeneficiarios();
			 cargaDatosArrayBeneficiarios();
			 cargaDatosTablaBeneficiarios();
			 limpiarCamposBeneficiarios(); 
			 agregarListadoBeneficiarios();
		 }
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
	 objBeneficiario.flagReferencia=false;
	 arrayBeneficiarios.push(objBeneficiario);
	 
	}

	function  cargaDatosTablaBeneficiarios(){
	elmTBODY4 = document.getElementById("tbodyBeneficiarios");
		for(var p=0; p<arrayBeneficiarios.length; p++){
			elmTR4 = elmTBODY4.insertRow(p);
			for (var i=0; i<6; i++) {
				elmTD4 = elmTR4.insertCell(i);
				if(p%2==0){
					  style="f1";  
				  }else{
					  style="f2";  
				  }
				  elmTR4.setAttribute("class",style);
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
				if(i==5){
					 					  
					  var flagReferencia=arrayBeneficiarios[p].flagReferencia;
					   var div = document.createElement("div");
				       div.setAttribute("align","center");		   
				       div.setAttribute("class","hide");		   
				       if(estado !='apro'){
				       if(flagReferencia == true){ 
						   var link = document.createElement("a");
						   link.setAttribute("id",indiceFila4);
						   link.setAttribute("class","link");
						   text=document.createTextNode("--");										       
					       link.appendChild(text);	
					       div.appendChild(link);
				       }else{								       							       
					       var imagen = document.createElement("img");
					       imagen.setAttribute("src","${pageContext.request.contextPath}/images/del.png");//imagen.setAttribute("src","../images/del.png"); 
					       imagen.setAttribute("id",indiceFila4);
					       imagen.onclick=function(){
					    	   eliminarFila(this.id);
					       //indiceArray=((this.id)-1);
						   //eval(jsonInicio.campos_extra_tabla.detalleFlagReferencia.funcionDetalleCuentasCorrientes);
						   };
						   div.appendChild(imagen);
				       }
				       }							   						       
					   //var td = document.createElement("td");
					   //td.appendChild(div);
					   //tr_body.appendChild(td);	
					   elmTD4.appendChild(div);
					  
				}
				if(i!=0 && i!=5)
				     elmTD4.appendChild(elmText);
			}
			indiceFila4++;
		}
	}



	function eliminarFila(numeroFilaEliminar){ 
	    var eli=parseInt(numeroFilaEliminar);
	    var eliMostrar=eli+1;
		var rpt=confirm("�Seguro que desea eliminar beneficiario nro:"+eliMostrar+"?");
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


	function onclick_btnCerrar(){
		$("#div_beneficiarios").hide();	
		//agregarListadoBeneficiarios();
		limpiarCamposBeneficiarios();
		limpiarTablaBeneficiarios();
	}

	//Boton OK
	function agregarListadoBeneficiarios(){
		if(arrayBeneficiarios.length){
		    arrayUbicacionBeneficiarios[indUbiBen].beneficiarios=arrayBeneficiarios;
		}else{
			alert("Debe ingresar al menos un beneficiario");
		}
		//arrayBeneficiarios=new Array();
		//limpiarTablaBeneficiarios();
		
		//ocultar Div de metas
		//$("#div_metas").hide();
	}
	
	
//////////////////////////////////////INSTITUCION (FUENTES FINANCIADORAS)/////////////////////////////
	var objXHR9336254158;
	var tipoFuenteFinanciadora=new Array();
	function cargaComboTipoFuenteFinanciadora(){
		objXHR9336254158 = new ObjetoAJAX();
		objXHR9336254158.enviar("cargaComboTipoFuenteFinanciadora1.jspx","post","cargaDataComboTipoFuenteFinanciadora",null);	
	}

	function cargaDataComboTipoFuenteFinanciadora(){
		tipoFuenteFinanciadora = JSON.parse(objXHR9336254158.respuestaTexto());
		//alert(objXHR9336254158.respuestaTexto());
		//llenaComboTipoFuenteFinanciadora();
	}

	var objXHR1022234;
	var tipoMoneda=new Array();
	function cargaComboTipoMoneda(){
		objXHR1022234 = new ObjetoAJAX();
		objXHR1022234.enviar("cargaComboTipoMoneda.jspx","post","cargaDataComboTipoMoneda",null);
	}

	function cargaDataComboTipoMoneda(){
		tipoMoneda = JSON.parse(objXHR1022234.respuestaTexto());
		//OBTENER DATA INSTITUCION
		obtenerDataIntitucionFuenFinan();
	}

///////////////////////////////////////////funcionalidad para la tabla de instituciones


function onclick_btnAgregarInstitucion(){
	var rpt=validarEjecutorInformacionFinanciamiento();
	if(rpt){
		$("#div_fuenteFinanciadora").hide();
		limpiarTablaInstituciones();
		cargaDatosArrayInstitucionFuenFinan();
		cargaDatosTablaInstitucion();
		limpiarCamposInstitucion();

		//llenaComboInstitucionEjecutora();
	}
}


function testAgregarInstitucion(){
	alert(JSON.stringify(arrayInstitucionFuenFinan));
	
}


function deshabilitarCamposInstitucion(){

	document.getElementById("txtInstitucion").disabled = false;
	document.getElementById("txtRuc").disabled = true;
	document.getElementById("txtDireccion").disabled = true;
	document.getElementById("txtTelefono").disabled = true;
	document.getElementById("txtRepLegal").disabled = true;
	document.getElementById("txtContacto").disabled = true;
	document.getElementById("txtCorreo").disabled = true;
	document.getElementById("txaObservacion").disabled = true;
	
	document.getElementById("btnAgregarInstitucion").disabled = true;
	document.getElementById("cbxInstitucionEjecutora").disabled = true;
	

	}

function habilitarCamposInstitucion(){

	document.getElementById("txtInstitucion").disabled = false;
	document.getElementById("txtRuc").disabled = false;
	document.getElementById("txtDireccion").disabled = false;
	document.getElementById("txtTelefono").disabled = false;
	document.getElementById("txtRepLegal").disabled = false;
	document.getElementById("txtContacto").disabled = false;
	document.getElementById("txtCorreo").disabled = false;
	document.getElementById("txaObservacion").disabled = false;
	
	document.getElementById("btnAgregarInstitucion").disabled = false;
	document.getElementById("cbxInstitucionEjecutora").disabled = false;
	

	}

function cargaDatosArrayInstitucionFuenFinan(){
	
	var cbxTipoFuenteFinan = document.getElementById("cbxTipoFuenteFinan");
	var cbxTipoMoneda = document.getElementById("cbxTipoMoneda");
	var txtMontoFinan = document.getElementById("txtMontoFinan");
	
	
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
	objInstitucion.flagReferencia=false;
	
	var objFuenteFinanciera = new Object();
	objFuenteFinanciera.cbxTipoFuenteFinan = cbxTipoFuenteFinan.value;
	objFuenteFinanciera.cbxTipoMoneda = cbxTipoMoneda.value;
	objFuenteFinanciera.txtMontoFinan = txtMontoFinan.value;
	objFuenteFinanciera.cbxInstitucionEjecutora = "0";
	
	objInstitucionFuenFinan=new Object();
	objInstitucionFuenFinan.institucion=objInstitucion;
	objInstitucionFuenFinan.fuenFinan=objFuenteFinanciera;
	
	arrayInstitucionFuenFinan.push(objInstitucionFuenFinan);
}

var indiceInstitucionSel=null;
function cargaDatosTablaInstitucion(){//tbodyInstitucion
	elmTBODY10 = document.getElementById("tbodyInstitucion");
	for(var p=0; p<arrayInstitucionFuenFinan.length; p++){
		elmTR10 = elmTBODY10.insertRow(p);
		if(p%2==0){
			  style="f1";  
		  }else{
			  style="f2";  
		  }
		  elmTR10.setAttribute("class",style);
		for (var i=0; i<11; i++) {
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
				  var link = document.createElement("a");
				  link.setAttribute("class","link");
				  link.setAttribute("id",indiceFila10);
				  // link.setAttribute=("href","javascript:limpiarCamposInstitucion();$('#div_fuenteFinanciadora').show('slow');verFuentesFinancieras(this.id);");
				  
				  // ruc del fondam   1515151515
				  link.onclick=function(){
					 var cbxFondoAmericas = document.getElementById("cbxFondoAmericas").value;
					 //alert("this.id"+ this.id);
					 //alert("ruc :"+arrayInstitucionFuenFinan[this.id].institucion.txtRuc);
					 if(arrayInstitucionFuenFinan[this.id].institucion.txtRuc == RUCFONDAM){  //cuando se requiere del fondam
						 $("#div_fuenteFinanciadoraFondam").show('slow');
						 $("#div_fuenteFinanciadora").hide();
						 verFuentesFinancieras(this.id);
						 console.debug(" mostrar datos del fondam : "+this.id);
					 }else{//cuando no se requiere del fondam
						 
						 limpiarCamposInstitucion();
						 $("#div_fuenteFinanciadora").show('slow');
						 $("#div_fuenteFinanciadoraFondam").hide();
						 verFuentesFinancieras(this.id);
						 indiceInstitucionSel = this.id;
					 }
					
				  };
				  var text=document.createTextNode("Fte.Finan.");
				  link.appendChild(text);
				  var div = document.createElement("div");
				  div.setAttribute("align","center");
				  div.appendChild(link);
				  elmTD10.appendChild(div);

			}
			if(i==10){
	            var flagReferencia=arrayInstitucionFuenFinan[p].institucion.flagReferencia;
				// alert(arrayInstitucionFuenFinan[p].fuenFinan.cbxInstitucionEjecutora +" -- "+ flagReferencia);
				
				   var div = document.createElement("div");
			       div.setAttribute("align","center");
			       div.setAttribute("class","hide");
			       if(estado !='apro'){
			       
			    	   if(arrayInstitucionFuenFinan[p].fuenFinan.cbxInstitucionEjecutora=="1" || arrayInstitucionFuenFinan[p].institucion.txtRuc == RUCFONDAM ){ //si es ejecutor no permitir eliminar
					   FONDAM_REFERENCIA=true;
					   var link = document.createElement("a");
					   link.setAttribute("id",indiceFila10);
					   link.setAttribute("class","link");
					   text=document.createTextNode("--");										       
				       link.appendChild(text);	
				       div.appendChild(link);
			       }else{								       							       
				       var imagen = document.createElement("img");
				       imagen.setAttribute("src","${pageContext.request.contextPath}/images/del.png");//imagen.setAttribute("src","../images/del.png"); 
				       imagen.setAttribute("id",indiceFila10);
				       imagen.onclick=function(){
				    	   eliminarFila10(this.id);
				    	   
							//llenaComboInstitucionEjecutora();
							if(document.getElementById("cbxFondoAmericas").value=="1"){
								document.getElementById("cbxFondoAmericas").value="2";
							}
							ocultarFuenteFinanciadora();
							ocultarFuenteFinanciadora2();			    	   
					   };
					   div.appendChild(imagen);					   
				  }
			       }
				   elmTD10.appendChild(div);
				
	        }
			if(i!=0 && i!=9 && i!=10)
			     elmTD10.appendChild(elmText);
		}
		indiceFila10++;
	}
}

function eliminarFila10(numeroFilaEliminar){ 
    var eli=parseInt(numeroFilaEliminar);
    var eliMostrar=eli+1;
	var rpt=confirm("�Seguro que desea eliminar la institucion nro:"+eliMostrar+"?");
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

	objFuenteFinanciera = new Object();
	objFuenteFinanciera = arrayInstitucionFuenFinan[indIns].fuenFinan;
 	cargaDatosFuenFinan(indIns);

}

function verificarSiFuenteFinanciera(indIns){
	var rpt = false;
	if(arrayInstitucionFuenFinan[indIns].fuenFinan != null){//si tiene fuente financiera
	   //alert("tiene metas");
	   rpt=true;
	}
	return rpt;
}

function cargaDatosFuenFinan(indIns){
	
	if(arrayInstitucionFuenFinan[indIns].institucion.txtRuc == RUCFONDAM ){//SOLO SI ES FONDAM
		console.debug("ENTROOOO MOSTRAR DATOS DEL FONDAMMMMM!");
		
		var descFuenteFinanFondam;
		console.debug("tipoFuenteFinanciadora[0] "+JSON.stringify(tipoFuenteFinanciadora[0]));
		console.debug("arrayInstitucionFuenFinan[indIns].fuenFinan.cbxTipoFuenteFinan "+arrayInstitucionFuenFinan[indIns].fuenFinan.cbxTipoFuenteFinan);
		
		for(var i=0; i<tipoFuenteFinanciadora[0].length; i++){
			if(tipoFuenteFinanciadora[0][i].tablaEspecificaID == "184"){//CODIGO DEL FONDAM
				descFuenteFinanFondam = tipoFuenteFinanciadora[0][i].descripcionCabecera;
			}
		}
		document.getElementById("cbxTipoMoneda2").value="0";
	    document.getElementById("nomIns2").innerHTML=arrayInstitucionFuenFinan[indIns].institucion.txtInstitucion;
		document.getElementById("spanTipoFuenteFinanFondam").innerHTML=descFuenteFinanFondam;
		//console.debug("aaaaaaaaaaaaaaaaaaaaaaaaaaa: "+arrayInstitucionFuenFinan[indIns].fuenFinan.cbxTipoMoneda);
		document.getElementById("cbxTipoMoneda2").value=arrayInstitucionFuenFinan[indIns].fuenFinan.cbxTipoMoneda;
		document.getElementById("txtMontoFondam").value=arrayInstitucionFuenFinan[indIns].fuenFinan.txtMontoFinan;
		
	}else{
	    var descFuenteFinan;
		for(var i=0; i<tipoFuenteFinanciadora[0].length; i++){
			if(tipoFuenteFinanciadora[0][i].tablaEspecificaID == arrayInstitucionFuenFinan[indIns].fuenFinan.cbxTipoFuenteFinan){
				descFuenteFinan = tipoFuenteFinanciadora[0][i].descripcionCabecera;
			}
		}
		var descTipoMoneda;
		for(var i=0; i<tipoMoneda[0].length; i++){
			if( tipoMoneda[0][i].tablaEspecificaID == arrayInstitucionFuenFinan[indIns].fuenFinan.cbxTipoMoneda){
				descTipoMoneda = tipoMoneda[0][i].descripcionCabecera;
			}
		}
		
		document.getElementById("nomIns").innerHTML=arrayInstitucionFuenFinan[indIns].institucion.txtInstitucion;
	    document.getElementById("spanTipoFuenteFinan").innerHTML = descFuenteFinan;
		document.getElementById("spanTipoMoneda").innerHTML = descTipoMoneda;
		document.getElementById("spanMontoFinan").value = arrayInstitucionFuenFinan[indIns].fuenFinan.txtMontoFinan;
	}
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
	var rpt=validarFuenteFinanciadora();
	if(rpt){
		cargaObjFuenteFinanciera();
		if(arrayInstitucionFuenFinan[indIns].fuenFinan == null){
			alert("No se guardo ninguna Fuente financiadora.");
		}
		objFuenteFinanciera=new Object();
		limpiarCamposFuentesFinan();
		//ocultar Div 
		$("#div_fuenteFinanciadora").hide();
	}
}

/////////////////llenar combo de institucion financiadora

function llenaComboInstitucionEjecutora(){
	var cbxInstitucionEjecutora = document.createElement("select");
	cbxInstitucionEjecutora.setAttribute("id","cbxInstitucionEjecutora");
	cbxInstitucionEjecutora.setAttribute("name","cbxInstitucionEjecutora");
	cbxInstitucionEjecutora.setAttribute("disabled","disabled");
	cbxInstitucionEjecutora.onchange=function(){seleccionarInstitucionEjecutora(this.value);};
	//valor por defecto
	var op = document.createElement("option");
	op.setAttribute("value","0");
	var text=document.createTextNode("----SELECCIONAR----");
	op.appendChild(text);
	cbxInstitucionEjecutora.appendChild(op);
	//llenar valores 
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
</script>

<script type="text/javascript">
	///////////////////////////////////////////funcionalidad para la tabla de resumen y observaciones de perfil

	var arrayResumenPerfil=new Array();
	
	function onclick_btnAgregarResumen(){	 
		var rpt=validarResumenTipoObservacion();
		if(rpt==0){
			$("#tbodyResumenObserv").load(
					"cargaResumenProyecto.jspx", {
						tipoResumenId : $("#cbxTipoResumenProyecto").val(), 
						resumenProyectoId : $("#resumenProyectoId").val(),
						resumenProyecto : $("#txaResumenPerfil").val()
					});
			$("#cbxTipoResumenProyecto").removeAttr("disabled");
			$("#flagModifica").attr("value","");
			//agregarResumenPerfil();
			limpiarResumenObservacionesPerfil();
			
		}
	}

	function modificarResumenProyecto(tipoResumenId){
		//alert(tipoResumenId);
		for(var i=0; i<arrayResumenPerfil.length; i++){
			if(arrayResumenPerfil[i].tipoResumenId ==tipoResumenId){
				$("#cbxTipoResumenProyecto").attr("value",arrayResumenPerfil[i].tipoResumenId);
				$("#cbxTipoResumenProyecto").attr("disabled","disabled");
				$("#resumenProyectoId").attr("value",arrayResumenPerfil[i].resumenProyectoId);
				$("#txaResumenPerfil").attr("value",arrayResumenPerfil[i].resumenProyecto);
				$("#flagModifica").attr("value","m");
			}
		}
	}
	
	function eliminarResumenProyecto(resumenProyectoID){
		$("#tbodyResumenObserv").load(
				"cargaResumenProyecto.jspx", {
					resumenProyectoId : resumenProyectoID,
					flagElimina : "e"
				}, function(){
					limpiarResumenObservacionesPerfil();
				} );
	}

	function validarResumenTipoObservacion(){
		var errores = 0;
		var mensaje="";

		if ($("#cbxTipoResumenProyecto").val() == 0) {
			mensaje += "Debe seleccionar un tipo de resumen.\n";
			errores = errores + 1;
		}
		
		if ($("#txaResumenPerfil").val().length == 0) {
			mensaje += "Debe ingresar un resumen. \n";
			errores = errores + 1;
		}

		if ($("#flagModifica").val()!="m"){
		for ( var p = 0; p < arrayResumenPerfil.length; p++) {
			if (arrayResumenPerfil[p].tipoResumenId == $('#cbxTipoResumenProyecto').attr("value")) {
				mensaje += "El tipo de resumen que desea ingresa ya se encuentra en la lista. \n";
				errores = errores + 1;
				break;
			}
		}
		}
		
		if (errores > 0) {
			alert(mensaje);
		}
		
		return errores;
	}
</script>

<script type="text/javascript">	
	/********************* OBTENER DATOS UBICACION DEL PROYECTO ************************/
	
	var objXHR120;
	var listUbicacionProyectoBenResul;
	var listUbicacionProyecto;
	var listBeneficiariosPorResultado;
	
	function obtenerDataUbicacionProyecto(){
		objXHR120 = new ObjetoAJAX();
		objXHR120.enviar("obtenerDataUbicacionProyecto.jspx","post","cargaDataUbicacionProyecto",null);
		
	}	
	
	function cargaDataUbicacionProyecto(){
		
		listUbicacionProyectoBenResul = JSON.parse(objXHR120.respuestaTexto());
		listUbicacionProyecto = listUbicacionProyectoBenResul[0];
		listBeneficiariosPorResultado = listUbicacionProyectoBenResul[1];
		//alert("listUbicacionProyectoBenResul: " +JSON.stringify(listUbicacionProyectoBenResul));
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
		    
		    objUbicacion.flagReferencia = listUbicacionProyecto[i].flagReferencia;
		    objUbicacion.ubicacionProyectoID = listUbicacionProyecto[i].ubicacionProyectoID;
		    		    		    
		    var arrayBeneficiarios= new Array();
		    var objBeneficiario;
		   
			//op.setAttribute("value",estratosSoc[0][i].tablaEspecificaID);
			//text=document.createTextNode(estratosSoc[0][i].descripcionCabecera);
		    for(var j=0; j<listBeneficiariosPorResultado.length; j++){
		    	
		    	if(listBeneficiariosPorResultado[j].ubicacionProyectoID == listUbicacionProyecto[i].ubicacionProyectoID){
		    		objBeneficiario=new Object();
		    		objBeneficiario.flagReferencia=listBeneficiariosPorResultado[j].flagReferencia;
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
	
	// INICIO MONTOS ACUMULADOS
	function mostrarMontosAcumulados(){
		$("#div_montosAcumulados").show('slow');	
		console.log("ENTRO EN MONTOS ACUMULADOS   arrayInstitucionFuenFinan.length= "+arrayInstitucionFuenFinan.length);
		if(arrayInstitucionFuenFinan.length > 0){
			console.log("document.getElementById('cbxFondoAmericas').value "+document.getElementById("cbxFondoAmericas").value);
											
			if(verificarMontoFinanFondam()){
			
				if(document.getElementById("cbxFondoAmericas").value == "1"){
					console.log("MONTO_FONDAM A");
					console.log("document.getElementById('txtMontoFondam').value "+document.getElementById("txtMontoFondam").value);
					MONTO_FONDAM = parseFloat(obtenerMontoFondam());	
				}else{
					console.log("MONTO_FONDAM B");
					MONTO_FONDAM = 	parseFloat("0");
				}
				
				console.log("MONTO_FONDAM "+MONTO_FONDAM);
			
				//inicializar monntos
		        MONTO_CONTRAPARTIDA = parseFloat("0");
		        MONTO_COFINANCIADOR = parseFloat("0");
				//obtener monto cofinanciador y contrapartida
				for(var i=0; i<arrayInstitucionFuenFinan.length; i++){					
					if(arrayInstitucionFuenFinan[i].fuenFinan.cbxTipoFuenteFinan == FUENTE_FINAN_COFINANCIADOR){
						MONTO_COFINANCIADOR = MONTO_COFINANCIADOR + parseFloat(arrayInstitucionFuenFinan[i].fuenFinan.txtMontoFinan);
					}else if(arrayInstitucionFuenFinan[i].fuenFinan.cbxTipoFuenteFinan == FUENTE_FINAN_EJECUTOR 
							   || arrayInstitucionFuenFinan[i].fuenFinan.cbxTipoFuenteFinan == FUENTE_FINAN_BENEFICIARIOS
							   || arrayInstitucionFuenFinan[i].fuenFinan.cbxTipoFuenteFinan == FUENTE_FINAN_OTROS){
						MONTO_CONTRAPARTIDA = MONTO_CONTRAPARTIDA + parseFloat(arrayInstitucionFuenFinan[i].fuenFinan.txtMontoFinan);
					}
				}
		
				var final_fondam = MONTO_FONDAM;
				var final_contrapartida = MONTO_CONTRAPARTIDA;
				var final_cofinanciador = MONTO_COFINANCIADOR;
				
				//guardar en el formulario los hidenn
				document.getElementById("txtFondo").value = final_fondam.toString();
				document.getElementById("txtContrapartida").value = final_contrapartida.toString();
				document.getElementById("txtCofinanciador").value = final_cofinanciador.toString();
				
				fOpenModalDialog('showMontosAcumulados.jspx?' +
						    'montoFondam=' + encodeURIComponent(final_fondam.toString()) +
						    '&montoContrapartida=' + encodeURIComponent(final_contrapartida.toString()) +
						    '&montoCofinanciador=' + encodeURIComponent(final_cofinanciador.toString())			    				
							,'500','200','150','200');	        									
			}					
		}else{
			alert("no hay institucion registrada!");
			MONTO_FONDAM = parseFloat("0");
			MONTO_COFINANCIADOR = parseFloat("0");
			MONTO_CONTRAPARTIDA = parseFloat("0");
		}
	}
	
	function verificarMontoFinanFondam(){
		//alert("RUCFONDAM: "+RUCFONDAM);
		for(var i=0; i<arrayInstitucionFuenFinan.length; i++){
			if(arrayInstitucionFuenFinan[i].institucion.txtRuc == RUCFONDAM &&
					arrayInstitucionFuenFinan[i].fuenFinan.txtMontoFinan=="0"){
				alert("ingresar monto de financiamiento para el Fondam!");
				$("#div_fuenteFinanciadoraFondam").show('slow');
				return false;
			}
			return true;
		}
		
	}
	function obtenerMontoFondam(){
		for(var i=0; i<arrayInstitucionFuenFinan.length; i++){
			if(arrayInstitucionFuenFinan[i].institucion.txtRuc == RUCFONDAM){
				return arrayInstitucionFuenFinan[i].fuenFinan.txtMontoFinan;	
			}
		}
	}
	// FIN MONTOS ACUMULADOS
	
	
	/********************* OBTENER DATOS INSTITUCION FUENTE FINANCIERA ************************/
	var objXHR122;
	var listInstitucion;
	var listFuenteFinancieras;

	
	function obtenerDataIntitucionFuenFinan(){
		objXHR122 = new ObjetoAJAX();
		objXHR122.enviar("obtenerDataIntitucionFuenFinan.jspx","post","cargaDataIntitucionFuenFinan",null);
		
	}	
	
	function cargaDataIntitucionFuenFinan(){
		//alert("financieras: "+objXHR122.respuestaTexto());
		var intitucionFuenFinan = JSON.parse(objXHR122.respuestaTexto());
		listInstitucion = intitucionFuenFinan[0];
		listFuenteFinancieras = intitucionFuenFinan[1];
		console.log("listFuenteFinancieras A : "+JSON.stringify(listFuenteFinancieras));
		
		// = intitucionFuenFinan[2];
		
		armarArrayFuenteFinan();
	}
	
	
	function armarArrayFuenteFinan(){
	
		var objInstitucion;
		
		for(var j=0; j<listInstitucion.length; j++){
			objInstitucion = new Object();
			objInstitucion.institucionID = listInstitucion[j].institucionID;
			objInstitucion.txtInstitucion = listInstitucion[j].nombreInstitucion;
			objInstitucion.txtRuc = listInstitucion[j].rucInstitucion;
			objInstitucion.txtDireccion = listInstitucion[j].direccion;
			objInstitucion.txtTelefono = listInstitucion[j].telefono;
			objInstitucion.txtRepLegal = listInstitucion[j].representanteLegal;
			objInstitucion.txtContacto = listInstitucion[j].contacto;
			objInstitucion.txtCorreo = listInstitucion[j].correo;
			objInstitucion.txaObservacion = listInstitucion[j].observacionDeInstitucion;
			objInstitucion.flagReferencia = listInstitucion[j].flagReferencia;
			
			var objFuenteFinanciera = new Object();
			for(var i=0; i<listFuenteFinancieras.length; i++){
				 if(listFuenteFinancieras[i].institucionID == listInstitucion[j].institucionID){
					    objFuenteFinanciera.fuenteFinanciadoraID = listFuenteFinancieras[i].fuenteFinanciadoraID;
					    objFuenteFinanciera.cbxTipoFuenteFinan = listFuenteFinancieras[i].fkIdtablaespTipoFuenteFinanciadora;
						objFuenteFinanciera.cbxTipoMoneda = listFuenteFinancieras[i].fkIdtablaespTipoMoneda;
						objFuenteFinanciera.txtMontoFinan = listFuenteFinancieras[i].montoFinanciado;
						objFuenteFinanciera.cbxInstitucionEjecutora = listFuenteFinancieras[i].defineSiEsEjecutor; 
				 }
			}
			
			objInstitucionFuenFinan=new Object();
			objInstitucionFuenFinan.institucion=objInstitucion;
			objInstitucionFuenFinan.fuenFinan=objFuenteFinanciera;
			
			arrayInstitucionFuenFinan.push(objInstitucionFuenFinan);
			
			
		}
		console.log("arrayInstitucionFuenFinan B : "+JSON.stringify(arrayInstitucionFuenFinan));
		
		//SELECCIONAR COMBO DEL FONDAM
		var fondo = verificarFondam();
		if(fondo==true){
			document.getElementById("cbxFondoAmericas").value="1";
			if(verificarFondamEsReferenciado()){
				document.getElementById("cbxFondoAmericas").disabled=true;
				//detalle financiamiento								
				document.getElementById("cbxTipoMoneda2").disabled=true;
				//document.getElementById("txtMontoFondam").disabled=true;
			}
		}
		
		cargaDatosTablaInstitucion();
		llenaComboInstitucionEjecutora();
		seleccionaComboInstitucionEjecutora();
	}
	

	function verificarFondamEsReferenciado(){

		for(var i=0; i<arrayInstitucionFuenFinan.length; i++){
			if (arrayInstitucionFuenFinan[i].institucion.txtRuc == RUCFONDAM 
					&& arrayInstitucionFuenFinan[i].institucion.flagReferencia == true){//ruc del fondam
				return true;
			}
		}
		return false;
	}
		
	
	function seleccionaComboInstitucionEjecutora(){
		for(var i=0; i<listFuenteFinancieras.length; i++){
			if(listFuenteFinancieras[i].defineSiEsEjecutor==1){
			   for(var j=0; j<listInstitucion.length; j++){
			       if(listFuenteFinancieras[i].institucionID == listInstitucion[j].institucionID){
			    	  //alert("ejecutora "+listInstitucion[j].nombreInstitucion);
			    	  document.getElementById("cbxInstitucionEjecutora").value=listInstitucion[j].nombreInstitucion;  
			    	  break;
			       }
			   }
			}
		}
		
	}
</script>

<script type="text/javascript">
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
	
	function limpiarResumenObservacionesPerfil(){
		//document.getElementById('cbxTipoResumenProyecto').options.selectedIndex = 0;
		//document.getElementById('txaResumenPerfil').value = "";
		$("#cbxTipoResumenProyecto").val(0);
		$("#txaResumenPerfil").val("");
		$("#resumenProyectoId").val("");
	}

	function limpiarCamposInstitucion(){
		document.getElementById('cbxTipoFuenteFinan').value="0";
		document.getElementById("cbxTipoMoneda").value="0";
		document.getElementById("txtMontoFinan").value="";
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
	
	function limpiarCamposCuentaCorriente(){
		document.getElementById("cbxBancos").value="0";
		document.getElementById("cbxTipoMoneda1").value="0";
		document.getElementById("txtNroCuenta").value="";
		document.getElementById("cuentaCorrienteId").value="";
		document.getElementById("flagModificaCuenta").value="";
	}
</script>

<script type="text/javascript">

////////////////////////// LISTADO DE BANCOS ////////////////
	var arrayCuentaCorriente = new Array();
	
	function onclick_btnAgregarCuenta(){
		
	  var cajasCuenta = validarCuentasCorrientes();
	  //alert(cajasCuenta);
	  if(cajasCuenta==0){
		  if($("#flagModificaCuenta").val()!='m'){
					$("#tbodyCuentaCorriente").load("cargarCuentasCorrientes.jspx",{
						bancoId : $("#cbxBancos").val(),
						tipoMonedaId : $("#cbxTipoMoneda1").val(),
						numeroCuenta : $("#txtNroCuenta").val()												
					}, function(){
						limpiarCamposCuentaCorriente();
					});	
			    }else{
			    	$("#tbodyCuentaCorriente").load("cargarCuentasCorrientes.jspx",{
						bancoId : $("#cbxBancos").val(),
						tipoMonedaId : $("#cbxTipoMoneda1").val(),
						numeroCuenta : $("#txtNroCuenta").val(),
						cuentaCorrienteId : $("#cuentaCorrienteId").val()
					}, function(){
						limpiarCamposCuentaCorriente();
					});	
			    }
			}
	}

function modificarCuentaCorriente(cuentaCorrienteId,bancoId,tipoMonedaId,numeroCuenta){
	$("#cbxBancos").attr("value",bancoId);
	$("#cbxTipoMoneda1").attr("value",tipoMonedaId);
	$("#txtNroCuenta").attr("value",numeroCuenta);
	$("#flagModificaCuenta").attr("value","m");
	$("#cuentaCorrienteId").attr("value",cuentaCorrienteId);
	
}
	
	function eliminarCuentaCorriente(cuentaCorrienteId){
		$("#tbodyCuentaCorriente").load("cargarCuentasCorrientes.jspx",{
					cuentaCorrienteId : cuentaCorrienteId,
					flagElimina : "e"
				});
		
	}
	
	function validarCuentasCorrientes(){
		var errores = 0;
		var mensaje="";

		if ($("#cbxBancos").val() == 0) {
			mensaje += "Debe seleccionar un Banco.\n";
			errores = errores + 1;
		}
		
		if ($("#cbxTipoMoneda1").val() == 0) {
			mensaje += "Debe seleccionar el tipo de moneda. \n";
			errores = errores + 1;
		}
		

		if ($("#txtNroCuenta").val().length == 0) {
			mensaje += "Debe ingresar un numero de cuenta. \n";
			errores = errores + 1;
		}

		if ($("#flagModificaCuenta").val()!="m"){
		for ( var p = 0; p < arrayCuentaCorriente.length; p++) {
			if ((arrayCuentaCorriente[p].bancoId == $("#cbxBancos").val())&&(arrayCuentaCorriente[p].nuemroCuenta == $("#txtNroCuenta").val())) {
				mensaje += "El numero de cuenta ya se encuentra en la lista. \n";
				errores = errores + 1;
				break;
			}
		}
		}
		
		if (errores > 0) {
			alert(mensaje);
		}
		
		return errores;
	}
	
	
	
///////////////////////////////////////////////EFECTOS DE PANTALLA

function ocultarFuenteFinanciadora(){
	
	
	if(document.getElementById("spanMontoFinan").value.trim().length==0){
		 alert("ingresar monto");
		 document.getElementById("spanMontoFinan").focus();

	}else{
		
		var objFuenteFinanciera = new Object();	
		var txtMontoFinan_ = document.getElementById("spanMontoFinan").value;
			
		arrayInstitucionFuenFinan[indiceInstitucionSel].fuenFinan.txtMontoFinan=txtMontoFinan_;
		
		$("#div_fuenteFinanciadora").hide();
	
	}	
}

function ocultarFuenteFinanciadora2(){
	$("#div_fuenteFinanciadoraFondam").hide();
}


$(document).ready(function(){
	
	$("#div_fuenteFinanciadora").hide();
	$("#div_fuenteFinanciadoraFondam").hide();
	
	$("#div_beneficiarios").hide();
	
	//cargarDataCBXTipoBeneficiario();
	
});

function obtenerDatosFondam(){
	objXHR27688553 = new ObjetoAJAX();
	var url = "obtenerDatosFondam.jspx";
    objXHR27688553.enviar(url,"get","armarDatosFondam",null);
}

function armarDatosFondam(){
	console.log("datosfondam: "+objXHR27688553.respuestaTexto());
	objInstitucion = JSON.parse(objXHR27688553.respuestaTexto());
	if(objInstitucion.estado == "si"){
		RUCFONDAM = objInstitucion.txtRuc;
	}
}

function registroFondoInstitucion(){
	//LLENAR VECTOR DE FUENTES FINANCIADORAS: PONER EN LA POSICION CERO LOS DATOS DEL FONDAM.
	var cbxFondoAmericas = document.getElementById("cbxFondoAmericas").value;
	
	if(cbxFondoAmericas=="1"){
		if(objInstitucion!=null && objInstitucion.estado=="si"){
		
		var objFuenteFinanciera = new Object();
		objFuenteFinanciera.cbxTipoFuenteFinan = "";
		objFuenteFinanciera.cbxTipoMoneda = "";
		objFuenteFinanciera.txtMontoFinan = "0";
		objFuenteFinanciera.cbxInstitucionEjecutora = "0";
				
		objInstitucionFuenFinan=new Object();
		objInstitucionFuenFinan.institucion=objInstitucion;
		objInstitucionFuenFinan.fuenFinan=objFuenteFinanciera;

		arrayInstitucionFuenFinan.unshift(objInstitucionFuenFinan);
	
		limpiarTablaInstituciones();
		cargaDatosTablaInstitucion();
		
		//llenaComboInstitucionEjecutora();
	 } else{
			alert("Los datos del fondam no se encuentran registrados, registrelos.");	
		}
		
			
	}else if(cbxFondoAmericas=="2"){
		//alert("arrayInstitucionFuenFinan.length: "+arrayInstitucionFuenFinan.length);
		
		//verificar si el fondam existe:
		var rpt = verificarFondam();
		if(rpt==true){
			//alert("Eliminar FONDAM?");
			arrayInstitucionFuenFinan.shift();
			limpiarTablaInstituciones();
			cargaDatosTablaInstitucion();
			//llenaComboInstitucionEjecutora();
		}			
		
	}
	//anadir al inicio del vector
	//DatosPieza.unshift(odoTipCod,pzaDentCod,estPidCod);
	//eliminar el primer elemento del vector
	//DatosPieza.pop()
}


function verificarFondam(){

	for(var i=0; i<arrayInstitucionFuenFinan.length; i++){
		if (arrayInstitucionFuenFinan[i].institucion.txtRuc == RUCFONDAM){//ruc del fondam
			return true;
		}
	}
	return false;
}

/********** RESTRICCION DEPPROVDIST***********************************************************************************/

//para los combos
var departamentosNew;
var provinciasNew;
var distritosNew;
//para la restriccion segun el programa
var departamentosRest;
var provinciasRest;
var distritosRest;

var objXHR78;
var restDepProvDist;

function cargarRestriccionDepProvDist() {
	objXHR78 = new ObjetoAJAX();
	var oform = document.forms[1];
	objXHR78.enviar("cargarRestriccionDepProvDist.jspx", "post",
			"cargaDataRestriccionDepProvDist", oform);
}

function cargaDataRestriccionDepProvDist() {
	var rpt = JSON.parse(objXHR78.respuestaTexto());
	restDepProvDist = rpt[0];
	//alert("rest.. DepProvDist: "+JSON.stringify(restDepProvDist));
	//alert("departamentos: "+JSON.stringify(departamentos));
	//alert("provincias: "+JSON.stringify(provincias));
	//alert("distritos: "+JSON.stringify(distritos));

	llenarRestriccionesDepProvDist();//para los combos
	obtenerRestriccionesDepProvDist();//para la restriccion segun el programa

	//PARA RESTRICCIONES DE DEPARTAMENTO(de un departamento llenar todas sus provincias y distritos)
	for ( var i = 0; i < departamentosRest.length; i++) {
		for ( var j = 0; j < provincias.length; j++) {
			if (departamentosRest[i].idDepartamento == provincias[j].idDepartamento) {
				provinciasNew.push(provincias[j]);
			}

		}
	}

	for ( var i = 0; i < departamentosRest.length; i++) {
		for ( var j = 0; j < distritos.length; j++) {
			if (departamentosRest[i].idDepartamento == distritos[j].idDepartamento) {
				distritosNew.push(distritos[j]);
			}
		}
	}

	//alert("cuando se llenan Prov y Dist: "+JSON.stringify(departamentosNew));		
	
    //alert("Restriccion de provincias : "+ JSON.stringify(provinciasRest));
    
	//PARA RESTRICCIONES DE PROVINCIAS(de una provincia llenar su departamento y sus respectivos distritos)
	for ( var i = 0; i < provinciasRest.length; i++) {
		for ( var j = 0; j < departamentos.length; j++) {
			if (provinciasRest[i].idDepartamento == departamentos[j].idDepartamento) {
				//verificar si ya existe en la lista
				var cont = 0;
				for ( var x = 0; x < departamentosNew.length; x++) {
					if (departamentosNew[x].idDepartamento == departamentos[j].idDepartamento)
						cont++;
				}
				if (cont == 0){
					departamentosNew.push(departamentos[j]);//break;
				}
			}
		}
	}
	

	
	for ( var i = 0; i < provinciasRest.length; i++) {
		for ( var j = 0; j < distritos.length; j++) {
			if (provinciasRest[i].idDepartamento == distritos[j].idDepartamento
					&& provinciasRest[i].idProvincia == distritos[j].idProvincia) {
				distritosNew.push(distritos[j]);
			}

		}
	}

	//alert("cuando se llenan Dep y Dist: "+ JSON.stringify(departamentosNew));
	

	//PARA RESTRICCIONES DE DISTRITOS(de un distrito llenar su provincia y su departamento)
    //alert("distritosRest : "+JSON.stringify(distritosRest));
    //alert("departamentosNew : "+JSON.stringify(departamentosNew));
    
	for ( var i = 0; i < distritosRest.length; i++) {
		for ( var j = 0; j < departamentos.length; j++) {
			if (distritosRest[i].idDepartamento == departamentos[j].idDepartamento) {
				//verificar si ya existe en la lista
					var cont = 0;
					for ( var x = 0; x < departamentosNew.length; x++) {
						if (departamentosNew[x].idDepartamento == departamentos[j].idDepartamento)
							cont++;
					}
					if (cont == 0){
						departamentosNew.push(departamentos[j]);//break;
						//alert("se agrego departamentosNew : "+JSON.stringify(departamentosNew));
					}							
			}

		}
	}

	for ( var i = 0; i < distritosRest.length; i++) {
		for ( var j = 0; j < provincias.length; j++) {
			if (distritosRest[i].idDepartamento == provincias[j].idDepartamento
					&& distritosRest[i].idProvincia == provincias[j].idProvincia) {
				//alert("provincias[j]: "+provincias[j].descripcion);
				//provinciasNew.push(provincias[j]);//break;
				
				var cont = 0;
				for ( var x = 0; x < provinciasNew.length; x++) {
					if (provinciasNew[x].idDepartamento == provincias[j].idDepartamento 
							&& provinciasNew[x].idProvincia == provincias[j].idProvincia)
						cont++;
				}
				if (cont == 0){
					provinciasNew.push(provincias[j]);//break;
					//alert("se agrego provinciasNew : "+JSON.stringify(provinciasNew));
				}	
				
			}

		}
	}
	//alert("cuando se llenan Prov y Dep");
	llenaComboDepartamentos();

	//alert("provinciasNew: "+JSON.stringify(provinciasNew));
	//alert("distritosNew: "+JSON.stringify(distritosNew));

}

function llenarRestriccionesDepProvDist() {
	departamentosNew = new Array();
	for ( var i = 0; i < restDepProvDist.length; i++) {
		for ( var j = 0; j < departamentos.length; j++) {
			if (restDepProvDist[i].depProvDist == departamentos[j].depProvDistID) {
				departamentosNew.push(departamentos[j]);
				break;
			}
		}
	}
	//alert("Departamentos de la restriccion: "+JSON.stringify(departamentosNew));

	provinciasNew = new Array();
	for ( var i = 0; i < restDepProvDist.length; i++) {
		for ( var j = 0; j < provincias.length; j++) {
			if (restDepProvDist[i].depProvDist == provincias[j].depProvDistID) {
				provinciasNew.push(provincias[j]);
				break;
			}
		}
	}
	//alert("Provincias de la restriccion: "+JSON.stringify(provinciasNew));

	distritosNew = new Array();
	for ( var i = 0; i < restDepProvDist.length; i++) {
		for ( var j = 0; j < distritos.length; j++) {
			if (restDepProvDist[i].depProvDist == distritos[j].depProvDistID) {
				distritosNew.push(distritos[j]);
				break;
			}
		}
	}
	//alert("Distritos de la restriccion: "+JSON.stringify(distritosNew));

}

function obtenerRestriccionesDepProvDist() {

	departamentosRest = new Array();
	for ( var i = 0; i < restDepProvDist.length; i++) {
		for ( var j = 0; j < departamentos.length; j++) {
			if (restDepProvDist[i].depProvDist == departamentos[j].depProvDistID) {
				departamentosRest.push(departamentos[j]);
				break;
			}
		}
	}
	//alert("DepartamentosRest de la restriccion: "+JSON.stringify(departamentosRest));

	provinciasRest = new Array();
	for ( var i = 0; i < restDepProvDist.length; i++) {
		for ( var j = 0; j < provincias.length; j++) {
			if (restDepProvDist[i].depProvDist == provincias[j].depProvDistID) {
				provinciasRest.push(provincias[j]);
				break;
			}
		}
	}
	//alert("ProvinciasRest de la restriccion: "+JSON.stringify(provinciasRest));

	distritosRest = new Array();
	for ( var i = 0; i < restDepProvDist.length; i++) {
		for ( var j = 0; j < distritos.length; j++) {
			if (restDepProvDist[i].depProvDist == distritos[j].depProvDistID) {
				distritosRest.push(distritos[j]);
				break;
			}
		}
	}
	//alert("DistritosRest de la restriccion: "+JSON.stringify(distritosRest));

}


/***************************VALIDAR CAMPOS DEP_PROV_DIST***********************/
function validarUbicacion(){
	if(document.getElementById("cbxDepartamentos").value=="0"){
		 alert("seleccionar departamento");
		 document.getElementById("cbxDepartamentos").focus();
		 return false;
	}
		if(document.getElementById("cbxProvincias").value=="0"){
		 alert("seleccionar provincia");
		 document.getElementById("cbxProvincias").focus();
		 return false;
	}
		if(document.getElementById("cbxDistritos").value=="0"){
		 alert("seleccionar distrito");
		 document.getElementById("cbxDistritos").focus();
		 return false;
	}
	/*if(document.getElementById("txaLocalizacion").value.trim().length==0){
		 alert("ingresar localizacion");
		 document.getElementById("txaLocalizacion").focus();
		 return false;
	}*/
		return true;
}
	
function validarBeneficiarosEstratos(){
	if(document.getElementById("txtCantBen").value.trim().length==0){
		 alert("ingresar cantidad de beneficiarios");
		 document.getElementById("txtCantBen").focus();
		 return false;
	}
	if(document.getElementById("cbxEstSocBen").value=="0"){
		 alert("seleccionar estrato social");
		 document.getElementById("cbxEstSocBen").focus();
		 return false;
	}
	if(document.getElementById("cbxTipoBen").value=="0"){
		 alert("seleccionar tipo de beneficiario");
		 document.getElementById("cbxTipoBen").focus();
		 return false;
	}
	if(document.getElementById("txaCaracPoblacion").value.trim().length==0){
		 alert("ingresar caracteristicas de poblacion");
		 document.getElementById("txaCaracPoblacion").focus();
		 return false;
	}
	if(document.getElementById("txaDescripcionPoblacion").value.trim().length==0){
		 alert("ingresar descripcion de poblacion");
		 document.getElementById("txaDescripcionPoblacion").focus();
		 return false;
	}	
	return true;
}


/***************************VALIDAR CAMPOS EFECUTOR FINANCIAMIENTO***********************/
function validarEjecutorInformacionFinanciamiento(){
	//Fuente Financiadora
	if(document.getElementById("cbxTipoFuenteFinan").value=="0"){
		 alert("seleccionar tipo de fuente financiadora");
		 document.getElementById("cbxTipoFuenteFinan").focus();
		 return false;
	}
	
	if(document.getElementById("cbxTipoMoneda").value=="0"){
		 alert("seleccionar tipo de moneda");
		 document.getElementById("cbxTipoMoneda").focus();
		 return false;
	}
	
	if(document.getElementById("txtMontoFinan").value.trim().length==0){
		 alert("ingresar monto financiador");
		 document.getElementById("txtMontoFinan").focus();
		 return false;
	}
	
	//Institucion       
	if(document.getElementById("txtInstitucion").value.trim().length==0){
		 alert("ingresar institucion");
		 document.getElementById("txtInstitucion").focus();
		 return false;
	}
	if(document.getElementById("txtRuc").value.trim().length==0){
		 alert("ingresar ruc");
		 document.getElementById("txtRuc").focus();
		 return false;
	}
	if(document.getElementById("txtDireccion").value.trim().length==0){
		 alert("ingresar direccion");
		 document.getElementById("txtDireccion").focus();
		 return false;
	}
	if(document.getElementById("txtTelefono").value.trim().length==0){
		 alert("ingresar telefono");
		 document.getElementById("txtTelefono").focus();
		 return false;
	}
	if(document.getElementById("txtRepLegal").value.trim().length==0){
		 alert("ingresar representante legal");
		 document.getElementById("txtRepLegal").focus();
		 return false;
	}
	if(document.getElementById("txtContacto").value.trim().length==0){
		 alert("ingresar contacto");
		 document.getElementById("txtContacto").focus();
		 return false;
	}
	if(document.getElementById("txtCorreo").value.trim().length==0){
		 alert("ingresar correo");
		 document.getElementById("txtCorreo").focus();
		 return false;
	}
	if(document.getElementById("txaObservacion").value.trim().length==0){
		 alert("ingresar observacion");
		 document.getElementById("txaObservacion").focus();
		 return false;
	}
	
	return true;
}

function validarFuenteFinanciadora(){
	
	//onclick_btnAgregarFuenteFinan();
	if(document.getElementById("cbxTipoFuenteFinan").value=="0"){
		 alert("seleccionar tipo de fuente financiera");
		 document.getElementById("cbxTipoFuenteFinan").focus();
		 return false;
	}
	if(document.getElementById("cbxTipoMoneda").value=="0"){
		 alert("seleccionar tipo de moneda");
		 document.getElementById("cbxTipoMoneda").focus();
		 return false;
	}
	if(document.getElementById("txtMontoFinan").value.trim().length==0){
		 alert("ingresar monto");
		 document.getElementById("txtMontoFinan").focus();
		 return false;
	}
	return true;
}


function onclick_btnMostrarFuenteFinanFondam(){
	
	var rpt=validarFuenteFinanFondam();
	if(rpt==true){
		//agregar datos
		/*var objFuenteFinanciera = new Object();
		objFuenteFinanciera.cbxTipoFuenteFinan = "184";  //fondam
		objFuenteFinanciera.cbxTipoMoneda = document.getElementById("cbxTipoMoneda2").value;
		objFuenteFinanciera.txtMontoFinan = document.getElementById("txtMontoFondam").value;
		objFuenteFinanciera.cbxInstitucionEjecutora = "0";
		
		var objInstitucionFuenFinan=new Object();
		
		arrayInstitucionFuenFinan[0].fuenFinan=objFuenteFinanciera;*/
		
		arrayInstitucionFuenFinan[0].fuenFinan.cbxTipoFuenteFinan = "184";  //fondam
		arrayInstitucionFuenFinan[0].fuenFinan.cbxTipoMoneda = document.getElementById("cbxTipoMoneda2").value;
		arrayInstitucionFuenFinan[0].fuenFinan.txtMontoFinan = document.getElementById("txtMontoFondam").value;
		
		ocultarFuenteFinanciadora2();
	}
}


function validarFuenteFinanFondam(){
	if(document.getElementById("cbxTipoMoneda2").value=="0"){
		 alert("seleccionar tipo de moneda");
		 document.getElementById("cbxTipoMoneda2").focus();
		 return false;
	}
	if(document.getElementById("txtMontoFondam").value.trim().length==0){
		 alert("ingresar monto");
		 document.getElementById("txtMontoFondam").focus();
		 return false;
	}
	return true;
}


/***************************REGISTRAR GESTIONAR PROYECTO***********************************/
/*
function onclick_btnRegistrarProyecto(){
	//DATOS DE PROYECTO
	var rptDatProy=validarDatosProyecto();
	
	//UBICACION DEL PROYECTO (solo si no hay ninguna ubicacion y rptDatProy=true)
	var ubiProy=true;
	if(rptDatProy==true){
		//alert("tam vector ubicacion: "+arrayUbicacionBeneficiarios.length);
		if (arrayUbicacionBeneficiarios.length==0){//sino hay ninguna ubicacion 
			alert("Agregar al menos una ubicacion de proyecto!");
			ubiProy=validarUbicacion();
			if(ubiProy==true)
				document.getElementById("cbxDepartamentos").focus();
		}else {//cuando hay una o mas ubicaciones 
			for(var i=0; i<arrayUbicacionBeneficiarios.length; i++){
				if(arrayUbicacionBeneficiarios[i].beneficiarios == null || arrayUbicacionBeneficiarios[i].beneficiarios.length==0){
					alert("Agregar Beneficiarios a la ubicacion: "+(i+1));
					document.getElementById("btnAgregarUbicacion").focus();
					ubiProy=false;
					break;		
			     }
	    	}
		}
		
	}
	
	// DATOS DEL PERFIL MONTOS
	var perfilMontos=true;
	if(rptDatProy==true && ubiProy==true && arrayUbicacionBeneficiarios.length>0){
		prefilMontos=validarDatosPerfilMontos();
	}
	
	// DATOS DEL PERFIL RESUMEN OBSERVACIONES
	var perfilResObs=true;
	if(rptDatProy==true && ubiProy==true && arrayUbicacionBeneficiarios.length>0 && prefilMontos==true){
		if(arrayResumenPerfil.length == 0){
			alert("Agregar resumen!");
			document.getElementById("cbxTipoResumenProyecto").focus();
			perfilResObs=false;
		}
	}
	
	
	// DATOS DE EJECUTOR E INFORMACI�N FINANCIAMIENTO
	var ejecutorFinan=true;
	var flag=0;
	if(rptDatProy==true && ubiProy==true && arrayUbicacionBeneficiarios.length>0 && prefilMontos==true && perfilResObs==true){
		muestra_ambos();
		if (arrayInstitucionFuenFinan.length==0){ //sino hay ninguna fuente financiamiento 
			alert("Agregar al menos una institucion!");
			ejecutorFinan=validarEjecutorInformacionFinanciamiento(); 
			if(ejecutorFinan==true)
				document.getElementById("txtInstitucion").focus();
		}else {//cuando hay una o mas fuente financiamiento 
			for(var i=0; i<arrayInstitucionFuenFinan.length; i++){
				if(arrayInstitucionFuenFinan[i].fuenFinan.cbxTipoFuenteFinan == "" || 
				   arrayInstitucionFuenFinan[i].fuenFinan.cbxTipoMoneda == "" ||
				   arrayInstitucionFuenFinan[i].fuenFinan.txtMontoFinan == "" ||
				   arrayInstitucionFuenFinan[i].fuenFinan.cbxInstitucionEjecutora == ""){
						alert("Agregar Fuente financiadora a la institucion: "+(i+1));
						document.getElementById("btnAgregarInstitucion").focus();
						ejecutorFinan=false;
						flag=1;
						break;		
			     }
	    	}
		}
		//si es ejecutor??
		if(flag==0 && arrayInstitucionFuenFinan.length>0 && document.getElementById("cbxInstitucionEjecutora").value=="0"){
			alert("Seleccionar institucion ejecutora!");
			document.getElementById("cbxInstitucionEjecutora").focus();
			ejecutorFinan=false;
		}
		
	}
	*/
	/************************ GRABAR!!!!!!! **********************************/
	/*if(rptDatProy==true && ubiProy==true && arrayUbicacionBeneficiarios.length>0 && prefilMontos==true && perfilResObs==true && ejecutorFinan==true && arrayInstitucionFuenFinan.length>0){
		alert("GRABA DATOS!!!");
		//capturarListasDatos();
    	//document.forms[0].submit();
    }
}*/

</script>

<script type="text/javascript">
function validarExtensionArchivo(){
	   extensiones_permitidas = new Array("doc","docx","pdf","DOC","DOCX","PDF");//".jpg",".jpeg",".JPG",".JPEG");
	   ruta_archivo=$("#imagenODocumento").val();
	   if(!ruta_archivo){
	      alert("No se ha seleccionado ningun archivo!");
	      return false;
	   }else{
		  arrayNombreArchivo = ruta_archivo.split(".");
		  nombreArchivo=arrayNombreArchivo[0];
		  
		  nombreArchivoUpload=nombreArchivo;//variable en gestorAjax
		  
		  nombreArchivo = nombreArchivo.split('\\');
		  nombreArchivo=nombreArchivo[nombreArchivo.length-1];
		  //extension="."+arrayNombreArchivo[1];
		  extension=arrayNombreArchivo[1];
		  //alert("nombre de archivo: "+nombreArchivo);
	      //alert("extension de archivo: "+extension);
	      $("#nombreArchivo").attr("value",nombreArchivo);
	      $("#extension").attr("value",extension);
	      extensionPermitida=false;
		  for (var i = 0; i < extensiones_permitidas.length; i++) { 
	         if (extensiones_permitidas[i] == extension) { 
				 extensionPermitida=true; 
				 break; 
	         } 
	      } 
		  if (!extensionPermitida) { 
			alert("Solo se pueden subir archivos con las siguientes extensiones: "+extensiones_permitidas.join()); 
		    return false;
		  }
	   }
	   return true;
	}

</script>
</head>
<body>
	<form:form commandName="imagenOArchivoTempBean"  id="gestionProyForm" 
		name="gestionProyForm" method="post"
		action="saveRegistrarGestionProyecto.jspx" class="form-clasico"
		enctype="multipart/form-data">
		<!-- enctype="multipart/form-data" accept-charset="UTF-8"  -->

		<input type="hidden" id="txtFondo" name="txtFondo"> <input
			type="hidden" id="txtContrapartida" name="txtContrapartida">
		<input type="hidden" id="txtCofinanciador" name="txtCofinanciador">

		<div class="encabezado">GESTIONAR PROYECTO</div>
		<br>
		<div id="proyecto_div">

			<fieldset>
				<legend>DATOS DE PROYECTO</legend>
				<table width="100%">
					<tr>
						<td style="text-align: right; width: 25%"><label>Proyecto:</label>
						</td>
						<td colspan="3" style="text-align: left; width: 75%;"><label><span
								id="txtProyecto">${nombreProyecto}</span> </label>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%;"><label>Codigo
								de Proyecto:</label></td>
						<td style="text-align: left; width: 25%;"><label><span
								id="codProyecto">${codProyecto}</span> </label></td>

						<td style="text-align: right; width: 25%;"><label>Modalidad
								Finaciamiento:</label></td>
						<td style="text-align: left; width: 25%;"><label><span
								id="modalidadFinan">${modFinan}</span> </label>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%;"><label>Programa:</label>
						</td>
						<td style="text-align: left; width: 25%;"><label><span
								id="nombrePrograma">${nomPrograma}</span> </label>
						</td>
						<td style="text-align: right; width: 25%; vertical-align: top;"><label>Estado de Proyecto:</label></td>
						<td style="text-align: left; width: 25%;"><label><span
								id="estadoProyecto">${estadoProyectoDescripcion }</span> </label>
						</td>
					</tr>
					<tr>	
						<td style="text-align: right; width: 25%;"><label>Area
								Tematica:</label></td>
						<td style="text-align: left; width: 25%;"><label><span
								id="areaTematicaSpan">${areaTematica}</span> </label>
						</td>
						<td style="text-align: right; width: 25%; vertical-align: top;"><label>Sub
								area Tematica:</label></td>
						<td style="text-align: left; width: 25%;"><label><span
								id="subAreaTematicaSpan">${subAreaTematica}</span> </label>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%;"><label>Duraci&oacute;n:</label>
						</td>
						<td style="text-align: left; width: 25%;"><label><span
								id="duracionProySpan">${duracionProy}&nbsp;(meses)</span> </label>
						</td>
					</tr>
				</table>
			</fieldset>
			<br>
			<table width="100%" border="0" align="right" class="hide">
				<tr>
					<td align="right"><input type="button"
						name="btnRegistrarGestProy" id="btnRegistrarGestProy"
						value="Guardar Gestion de Proyecto"
						onclick="onclick_btnGestionarProyecto()" />
					</td>
					<!-- onclick="onclick_btnRegistrarPerfil()" -->
				</tr>
			</table>

			<div id="tab-container" class="tab-container" style="width: 100%;">
				<ul class='etabs'>
					<li class='tab'><a href="#cuentaCorriente">Cuenta
							Corriente</a></li>
					<li class='tab'><a href="#ubicacionProyecto">Ubicacion de
							Proyecto</a></li>
					<li class='tab'><a href="#financiamiento">Ejecutor e
							Informacion de Financiamiento</a></li>
					<li class='tab'><a href="#resumenProyecto">Resumen de
							Proyecto</a></li>
					<li class='tab'><a href="#documento">Documento</a></li>
				</ul>
				<div class='panel-container'>
					<div id="cuentaCorriente">
						<br />
						<fieldset>
							<table width="100%" >
								<tr>
									<td style="width: 25%; text-align: right;"><label>Banco:</label>
										<input type="hidden" id="flagModificaCuenta"
										name="flagModificaCuenta"> <input type="hidden"
										id="cuentaCorrienteId" name="cuentaCorrienteId">
									</td>
									<td style="width: 25%; text-align: left;"><select
										name="cbxBancos" id="cbxBancos">
											<option value="0">--Banco--</option>
											<c:forEach items="${listBancos}" var="banco">
												<option value="${banco.tablaEspecificaID}">
													<c:out value="${banco.descripcionCabecera }" />
												</option>
											</c:forEach>
									</select>
									</td>
									<td style="width: 25%" align="right"><label>Tipo
											Moneda:</label></td>
									<td style="width: 25%; text-align: left;"><select
										name="cbxTipoMoneda1" id="cbxTipoMoneda1">
											<option value="0">--Moneda--</option>
											<c:forEach items="${listMoneda}" var="moneda">
												<option value="${moneda.tablaEspecificaID}">
													<c:out value="${moneda.descripcionCabecera }" />
												</option>
											</c:forEach>
									</select>
									</td>
								</tr>
								<tr>
									<td style="width: 25%" align="right"><label>Nro.
											de Cuenta:</label></td>
									<td style="width: 25%" align="left"><input type="text"
										id="txtNroCuenta" name="txtNroCuenta" maxlength="20"
										onkeypress="javascript:return Valida_Dato(event,8);">
									</td>
									<td style="width: 25%" align="right">&nbsp;</td>
									<td style="width: 25%" align="left"><a
										href="javascript:onclick_btnAgregarCuenta()"
										style="font-size: 15px;" id="btnAgregarCuenta"
										class="linkSelecciona">Agregar cuenta</a>
									</td>
								</tr>
							</table>
							<br />
							<fieldset id="tablaCuentaCorriente"
								style="margin: auto; border: none;"></fieldset>

							<table class="table-clasico" width="100%">
								<!-- class="table-clasico"-->
								<caption>
									<label>Lista de Cuentas Corrientes del Proyecto</label>
								</caption>
								<thead>
									<tr>
										<td style="width: 3%; text-align: center;"><label>Nro</label>
										</td>
										<td style="width: 25%; text-align: center;"><label>Banco</label>
										</td>
										<td style="width: 15%; text-align: center;"><label>Tipo
												Moneda</label>
										</td>
										<td style="width: 50%; text-align: center;"><label>Numero
												de Cuenta</label>
										</td>
										<td style="width: 7%; text-align: center;"><label>Opciones</label>
										</td>
									</tr>
								</thead>
								<tbody id="tbodyCuentaCorriente">

								</tbody>
							</table>
						</fieldset>
						<br>
					</div>
					<div id="ubicacionProyecto">
						<br />
						<fieldset>
							<table width="100%" class="hide">
								<tr>
									<td style="width: 40%;;">
										<table width="100%">
											<tr>
									<td style="width: 40%; text-align: right;"><label>Departamento:</label>
									</td>
									<td style="width: 50%; text-align: left;"><div
											id="div_cbxDepartamentos">
											<select name="cbxDepartamentos" id="cbxDepartamentos">
												<option value="0">----SELECCIONAR----</option>
											</select>
										</div>
									</td>
								</tr>
								<tr>	
									<td style="width: 40%; text-align: right;"><label>Provincia:</label>
									</td>
									<td style="width: 50%; text-align: left;"><div
											id="div_cbxProvincias">
											<select name="cbxProvincias" id="cbxProvincias">
												<option value="0">----SELECCIONAR----</option>
											</select>
										</div>
									</td>
								</tr>
								<tr>
									<td style="width: 40%; text-align: right;"><label>Distrito:</label>
									</td>
									<td style="width: 50%; text-align: left;"><div
											id="div_cbxDistritos">
											<select name="cbxDistritos" id="cbxDistritos">
												<option value="0">----SELECCIONAR----</option>
											</select>
										</div>
									</td>
								</tr>			
										</table>
									</td>
									<td style="width: 15%; text-align: right; vertical-align: top;"><label>Localizacion:</label>
									</td>
									<td style="width: 45%; text-align: left;"><textarea
											id="txaLocalizacion" name="txaLocalizacion" style="width: 100%;"
											onkeypress="javascript:return Valida_Dato(event,10);"
											COLS="50" ROWS="4"></textarea>
									</td>
								</tr>
								<tr>
									<td style="width: 100%; text-align: right;" colspan="4">
										<!-- <input
									type="button" name="btnAgregarUbicacion"
									id="btnAgregarUbicacion" value="Agregar ubicacion"
									onclick="onclick_btnAgregarUbicacion()" />--> <a
										href="javascript:onclick_btnAgregarUbicacion()"
										style="font-size: 15px;" id="btnAgregarUbicacion"
										class="linkSelecciona">Agregar ubicacion</a>
									</td>
								</tr>
							</table>
							<br />
							<table class="table-clasico" width="100%">
								<caption>
									<label>Ubicaciones del Proyecto</label>
								</caption>
								<thead>
									<tr>

										<th style="width: 2%; text-align: center;"><label>N�</label>
										</th>
										<th style="width: 15%; text-align: center;"><label>Dep.</label>
										</th>
										<th style="width: 15%; text-align: center;"><label>Prov.</label>
										</th>
										<th style="width: 15%; text-align: center;"><label>Dist.</label>
										</th>
										<th style="width: 40%; text-align: center;"><label>Localizaci�n.</label>
										</th>
										<th style="width: 8%; text-align: center;"><label>Estrato</label>
										</th>
										<th style="width: 5%; text-align: center;">&nbsp;</th>
									</tr>
								</thead>
								<tbody id="tbodyUbiProy">
								</tbody>
							</table>
							<br>
							<div id="div_beneficiarios" style="width: 100%">
								<fieldset>
									<legend>
										Estrato de :&nbsp;<span id="dep_Prov_Dist"></span>
									</legend>
									<table border="0" align="center" width="100%" class="hide">
										<tr>
											<td align="right" style="width: 25%;"><label>Beneficiarios:</label>
											</td>
											<td colspan="3" style="width: 75%;"><input type="text"
												name="txtCantBen" id="txtCantBen" maxlength="8"
												onkeypress="javascript:return Valida_Dato(event,8);"
												style="width: 70px;" /> <select name="cbxEstSocBen"
												id="cbxEstSocBen">
													<option value="0">--ESTRATO--</option>
													<c:forEach items="${listEstrato}" var="estrato">
														<option value="${estrato.tablaEspecificaID}">
															<c:out value="${estrato.descripcionCabecera}" />
														</option>
													</c:forEach>
											</select> <select name="cbxTipoBen" id="cbxTipoBen"
												style="width: 180px;">
													<option value="0">--TIPO BENEFICIARIO--</option>
													<c:forEach items="${listTipoBeneficiario}"
														var="tipoBeneficiario">
														<option value="${tipoBeneficiario.tablaEspecificaID}">
															<c:out value="${tipoBeneficiario.descripcionCabecera}" />
														</option>
													</c:forEach>
											</select>
											</td>
										</tr>
										<tr>
											<td align="right" style="width: 25%; vertical-align: top;"><label>Caracteristicas
													Poblac.:</label></td>
											<td colspan="3" align="left" style="width: 75%;"><textarea
													id="txaCaracPoblacion" name="txaCaracPoblacion"
													onkeypress="javascript:return Valida_Dato(event,10);"
													COLS="6" ROWS="4" style="width: 98%;"></textarea></td>
										</tr>
										<tr>
											<td align="right" style="width: 25%; vertical-align: top;"><label>Descripcion:</label>
											</td>
											<td colspan="3" align="left" style="width: 75%;"><textarea
													id="txaDescripcionPoblacion" name="txaDescripcionPoblacion"
													onkeypress="javascript:return Valida_Dato(event,10);"
													COLS="6" ROWS="4" style="width: 98%;"></textarea></td>
										</tr>
										<tr>
											<td colspan="2" style="width: 50%;"></td>
											<td colspan="2" style="width: 50%;" align="right">
												<!-- <input
											type="button" name="btnAgregarEstrato" id="btnAgregarEstrato"
											value="Agregar estrato"
											onclick="onclick_btnAgregarEstrato();" style="margin: 10px;" />
											<input type="button" value="Cerrar"
											onclick="onclick_btnAgregarUbicacionBeneficiarios()"
											style="margin: 10px;">
											--> <a href="javascript:onclick_btnAgregarEstrato()"
												style="font-size: 15px; margin-left: 15px;"
												id="btnAgregarEstrato" class="linkSelecciona">Agregar
													estrato</a> <a href="javascript:onclick_btnCerrar()"
												style="font-size: 15px; margin-left: 15px;" id="btnCerrar"
												class="linkSelecciona">Cerrar</a>
											</td>
										</tr>
									</table>

									<table class="table-clasico" width="100%">
										<!-- class="table-clasico"-->
										<caption>
											<label>Beneficiarios</label>
										</caption>
										<thead>
											<tr>
												<th style="width: 5%; text-align: center;"><label>N�</label>
												</th>
												<th style="width: 15%; text-align: center;"><label>Beneficiarios<br />Estrato</label>
												</th>
												<th style="width: 10%; text-align: center;"><label>Tipo
														Ben.</label></th>
												<th style="width: 32%; text-align: center;"><label>Carac.
														Poblacion</label></th>
												<th style="width: 33%; text-align: center;"><label>Descripci�n</label>
												</th>
												<th style="width: 5%; text-align: center;">&nbsp;</th>
											</tr>
										</thead>
										<tbody id="tbodyBeneficiarios">
										</tbody>
									</table>

								</fieldset>
							</div>
						</fieldset>
					</div>
					<div id="financiamiento">
						<br />
						<fieldset>
						<legend>Datos Institucion</legend>
							<table class="hide">
								<tr>
									<td><label>Contar con el Fondo de las Americas? :</label>
									</td>
									<td><select id="cbxFondoAmericas" name="cbxFondoAmericas"
										onchange="registroFondoInstitucion()" disabled="disabled">
											<option value="2">No</option>
											<option value="1">Si</option>
									</select></td>
								</tr>
							</table>
							<br class="hide"/>
							<div id="div_datosInst" class="hide">
								<fieldset>
									<div id="div_fuenFinan">
										<fieldset>
											<legend>Fuente Financiadora</legend>
											<table width="100%">
												<tr>
													<td style="text-align: right;"><label>Tipo
															Fuente Financiadora:</label>
													</td>
													<td><select name="cbxTipoFuenteFinan"
														onchange="cargaCajasInstitucion()" id="cbxTipoFuenteFinan"
														style="width: 220px;">
															<option value="0">--FUENTE FINANCIADORA--</option>
															<c:forEach items="${listFuenteFinanciadora}"
																var="fuenteFinanciadora">
																<c:if
																	test="${fuenteFinanciadora.tablaEspecificaID != '184' }">
																	<option value="${fuenteFinanciadora.tablaEspecificaID}">
																		<c:out
																			value="${fuenteFinanciadora.descripcionCabecera }" />
																	</option>
																</c:if>
															</c:forEach>
													</select></td>
													<td style="text-align: right;"><label>Tipo
															Moneda:</label>
													</td>
													<td><select name="cbxTipoMoneda" id="cbxTipoMoneda">
															<option value="0">--MONEDA--</option>
															<c:forEach items="${listMoneda}" var="moneda">
																<option value="${moneda.tablaEspecificaID}">
																	<c:out value="${moneda.descripcionCabecera }" />
																</option>
															</c:forEach>
													</select>
													</td>
												</tr>
												<tr>
													<td style="text-align: right;"><label>Monto Financiado:</label>
													</td>
													<td><input name="txtMontoFinan" id="txtMontoFinan"
														type="text" maxlength="6"
														onkeypress="javascript:return Valida_Dato(event,8);" />
													</td>
													<td style="text-align: right;">&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
											</table>
										</fieldset>
									</div>
									<table width="100%">
										<tr>
											<td style="text-align: right; text-align: 20%;"><label>Instituci&oacute;n:</label>
											</td>
											<td style="text-align: left; text-align: 80%;" colspan="3"><textarea
													id="txtInstitucion" name="txtInstitucion" maxlength="350"
													onkeypress="javascript:return Valida_Dato(event,10);"
													style="width: 98%;"></textarea></td>
										</tr>
										<tr>
											<td style="text-align: right; text-align: 20%;"><label>RUC:</label>
											</td>
											<td style="text-align: left; text-align: 80%;" colspan="3"><input
												id="txtRuc" name="txtRuc" type="text" maxlength="11"
												onkeypress="javascript:return Valida_Dato(event,8);" />
											</td>

										</tr>
										<tr>
											<td style="text-align: right; text-align: 20%;"><label>Representante
													Legal:</label>
											</td>
											<td style="text-align: left; text-align: 30%;"><input
												id="txtRepLegal" name="txtRepLegal" type="text"
												maxlength="40"
												onkeypress="javascript:return Valida_Dato(event,6);" />
											</td>
											<td style="text-align: right; text-align: 20%;"><label>Tel&eacute;fono:</label>
											</td>
											<td style="text-align: left; text-align: 30%;"><input
												id="txtTelefono" name="txtTelefono" type="text"
												maxlength="15"
												onkeypress="javascript:return Valida_Dato(event,8);" />
											</td>
										</tr>
										<tr>
											<td style="text-align: right; text-align: 20%;"><label>Contacto:</label>
											</td>
											<td style="text-align: left; text-align: 30%;"><input
												id="txtContacto" name="txtContacto" type="text"
												maxlength="40"
												onkeypress="javascript:return Valida_Dato(event,6);" />
											</td>
											<td style="text-align: right; text-align: 20%;"><label>Correo:</label>
											</td>
											<td style="text-align: left; text-align: 30%;"><input
												id="txtCorreo" name="txtCorreo" type="text" maxlength="40"
												onkeypress="javascript:return Valida_Dato(event,7);" />
											</td>
										</tr>
										<tr>
											<td style="text-align: right; text-align: 20%;"><label>Direcci&oacute;n:</label>
											</td>
											<td style="text-align: left; text-align: 80%;" colspan="3"><textarea
													id="txtDireccion" name="txtDireccion" maxlength="200"
													onkeypress="javascript:return Valida_Dato(event,10);"
													style="width: 98%;"></textarea></td>
										</tr>
										<tr>
											<td style="text-align: right; text-align: 20%;"><label>Observaci&oacute;n:</label>
											</td>
											<td style="text-align: left; text-align: 80%;" colspan="3"><textarea
													id="txaObservacion" name="txaObservacion" rows="3"
													style="width: 98%;"
													onkeypress="javascript:return Valida_Dato(event,10);"></textarea>
											</td>
										</tr>
									</table>
									<br>
									<table align="center" width="100%" class="hide">
										<tr>
											<td align="right">
												<!-- <input type="button"
									name="btnAgregarInstitucion" id="btnAgregarInstitucion"
									value="Agregar Institucion"
									onclick="onclick_btnAgregarInstitucion()" />--> <a
												href="javascript:onclick_btnAgregarInstitucion()"
												style="font-size: 15px;" id="btnAgregarInstitucion"
												class="linkSelecciona">Agregar Institucion</a> <br>
											</td>
										</tr>
									</table>
									
								</fieldset>
							</div>
							<br class="hide"/>
							<table class="table-clasico" width="100%">
								<caption>
									<div style="float: left;">
										<label>Lista de Instituciones</label>
									</div>
									<div style="float: right;">
										<!-- <input type="button" value="Montos Acumulados"
									onclick="mostrarMontosAcumulados()" />-->
										<a href="javascript:mostrarMontosAcumulados()"
											style="font-size: 13px;" id="btnMontosAcumulados"
											class="linkSelecciona">Montos Acumulados</a>
									</div>
								</caption>
								<thead>
									<tr>
										<th><label>Nro.</label>
										</th>
										<th><label>RUC.</label>
										</th>
										<th><label>Inst.</label>
										</th>
										<th><label>Direccion</label>
										</th>
										<th><label>Tfno.</label>
										</th>
										<th><label>Rep. Legal</label>
										</th>
										<th><label>Contacto</label>
										</th>
										<th><label>Correo</label>
										</th>
										<th><label>Observ.</label>
										</th>
										<th><label>&nbsp;</label>
										</th>
										<th>&nbsp;</th>
									</tr>
								</thead>
								<tbody id="tbodyInstitucion">
								</tbody>
							</table>
							<table class="table-clasico" width="100%">
								<tfoot>
									<tr>
										<td style="text-align: right;" width="75%"><label>Institucion
												Ejecutora:</label>
										</td>
										<td>
											<div id="div_cbxInstitucionEjecutora">
												<select name="cbxInstitucionEjecutora"
													id="cbxInstitucionEjecutora">
													<option value="0">----SELECCIONAR----</option>
												</select>
											</div></td>
									</tr>
								</tfoot>
							</table>
							<br>
							<div id="div_fuenteFinanciadora">
								<fieldset>
									<legend>
										Fuente Financiadora:&nbsp;<span id="nomIns"></span>
									</legend>
									<table width="100%">
										<tr>
											<td style="text-align: right; width: 25%;"><label>Tipo
													Fuente Financiadora:</label>
											</td>
											<td style="text-align: left; width: 25%;"><label><span
													id="spanTipoFuenteFinan"></span> </label></td>
											<td style="text-align: right; width: 25%;"><label>Tipo
													Moneda:</label>
											</td>
											<td style="text-align: left; width: 25%;"><label><span
													id="spanTipoMoneda"></span> </label></td>
										</tr>
										<tr>
											<td style="text-align: right; width: 25%;"><label>Monto:</label>
											</td>
											<td style="text-align: left; width: 25%;"><label><input
													id="spanMontoFinan" name="spanMontoFinan" type="text"
													maxlength="6"
													onkeypress="javascript:return Valida_Dato(event,8);"
													value="0"> </label> <!--  <label><span id="spanMontoFinan"></span></label>-->

											</td>
											<td colspan="2" style="text-align: center; width: 50%;">
												<!-- 
										<input type="button" class="hide"
											id="btnMostrarFuenteFinan" value="OK"
											onclick="ocultarFuenteFinanciadora();" /> --> <a
												href="javascript:ocultarFuenteFinanciadora()"
												style="font-size: 15px; margin-left: 15px;" id="btnOk1"
												class="linkSelecciona">OK</a>
											</td>
										</tr>
									</table>
									<!-- 
								<table width="90%" align="center" border="0">
									<tr height="40px" valign="middle">
										<td align="right"><input type="button" class="hide"
											id="btnMostrarFuenteFinan" value="OK"
											onclick="ocultarFuenteFinanciadora();" /> 
																					</td>
									</tr>
								</table>-->
								</fieldset>
							</div>

							<div id="div_fuenteFinanciadoraFondam">
								<fieldset>
									<legend>
										Fuente Financiadora:&nbsp;<span id="nomIns2"></span>
									</legend>
									<table width="100%">
										<tr>
											<td style="text-align: right; width: 25%;"><label>Tipo
													Fuente Financiadora:</label>
											</td>
											<td style="text-align: left; width: 25%;"><label><span
													id="spanTipoFuenteFinanFondam">Fondo de las
														Americas.</span> </label></td>
											<td style="text-align: right; width: 25%;"><label>Tipo
													Moneda:</label>
											</td>
											<td style="text-align: left; width: 25%;"><select
												name="cbxTipoMoneda2" id="cbxTipoMoneda2">
													<option value="0">--Moneda--</option>
													<c:forEach items="${listMoneda}" var="moneda">
														<option value="${moneda.tablaEspecificaID}">
															<c:out value="${moneda.descripcionCabecera }" />
														</option>
													</c:forEach>
											</select></td>
										</tr>
										<tr>
											<td style="text-align: right; width: 25%;"><label>Monto:</label>
											</td>
											<td style="text-align: left; width: 25%;"><label><input
													id="txtMontoFondam" name="txtMontoFondam" type="text"
													maxlength="6"
													onkeypress="javascript:return Valida_Dato(event,8);"
													value="0"> </label></td>
											<td colspan="2" style="text-align: center; width: 25%;">
												<!-- <input type="button" class="hide"
											id="btnMostrarFuenteFinanFondam" value="OK"
											onclick="onclick_btnMostrarFuenteFinanFondam();" />--> <a
												href="javascript:onclick_btnMostrarFuenteFinanFondam()"
												style="font-size: 15px; margin-left: 15px;" id="btnOk2"
												class="linkSelecciona">OK</a>
											</td>
										</tr>
									</table>
									<!-- 
								<table width="90%" align="center" border="0">
									<tr height="40px" valign="middle">
										<td align="right"><input type="button" class="hide"
											id="btnMostrarFuenteFinanFondam" value="OK"
											onclick="onclick_btnMostrarFuenteFinanFondam();" /> 
										</td>
									</tr>
								</table>-->
								</fieldset>

							</div>
						</fieldset>
					</div>
					<div id="resumenProyecto">
						<br />
						<fieldset>
							<table width="100%" border="0" align="center" class="hide">
								<tr>
									<td style="width: 25%; text-align: right;"><label>Tipo
											de Resumen de Proyecto:</label></td>
									<td style="width: 75%; text-align: left;"><select
										name="cbxTipoResumenProyecto" id="cbxTipoResumenProyecto"
										style="width: 250px;">
											<option value="0">-- Tipo de Resumen de Proyecto--</option>
											<c:forEach items="${listTipoResumenProyecto}"
												var="tipoResumenProyecto">
												<option value="${tipoResumenProyecto.tablaEspecificaID}">
													<c:out value="${tipoResumenProyecto.descripcionCabecera }" />
												</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td style="width: 25%; text-align: right; vertical-align: top;"><label>Resumen:</label>
										<input type="hidden" id="resumenProyectoId"
										name="resumenProyectoId"> <input type="hidden"
										id="flagModifica" name="flagModifica">
									</td>
									<td style="width: 75%; text-align: letf;"><textarea
											id="txaResumenPerfil" name="txaResumenPerfil" COLS="3"
											ROWS="4" style="width: 98%;"></textarea></td>
								</tr>
								<tr>
									<td colspan="2" style="width: 100%; text-align: right;">
										<!-- <input
									type="button" name="btnAgregarResumen" id="btnAgregarResumen"
									value="Agregar Resumen" onclick="onclick_btnAgregarResumen();" />
									 --> <a href="javascript:onclick_btnAgregarResumen()"
										style="font-size: 13px;" id="btnAgregarResumen"
										class="linkSelecciona">Agregar Resumen</a>
									</td>
								</tr>
							</table>
							<br>
							<div id="tbodyResumenObserv"></div>
						</fieldset>
					</div>
					<div id="documento">
						<br />
						<fieldset>
							<table width="100%">
								<tr>
									<td style="text-align: right; width: 20%;"><label>Documento:</label>
									</td>
									<td><iframe src="showImagenArchivoDownloadProyecto.jspx"
											id="window" frameborder="0" height="40px" width="100%"
											scrolling="no"></iframe>
									</td>
								</tr>
								<tr class="hide">
									<td style="text-align: right;"><label>Agregar Documento
											Resumen: (pdf,docx)</label>
									</td>
									<td><!--  <iframe src="showArchivoUploadProyecto.jspx"
											id="window" frameborder="0" height="50px" width="100%"
											scrolling="no"></iframe>-->
											<div>
							<spring:bind path="imagenODocumento">
											<input type="file" name="imagenODocumento" id="imagenODocumento" />
										</spring:bind>
										</div>
										<input type="hidden" id="nombreDocumentoAdjunto" name="nombreDocumentoAdjunto">
										<input type="hidden" id="nombreArchivo" name="nombreArchivo">
										<input type="hidden" id="extension" name="extension">
										<input type="hidden" id="tamanioArchivo" name="tamanioArchivo">
										</td>
								</tr>
							</table>
						</fieldset>
					</div>
				</div>
			</div>

			<!-- Al guardar -->
			<input type="hidden" id="txtListadoUbicacionBeneficiarios"
				name="txtListadoUbicacionBeneficiarios" /> <input type="hidden"
				id="txtListadoInstitucionFuenFinan"
				name="txtListadoInstitucionFuenFinan" /> <input type="hidden"
				id="txtListadoResumenProyecto" name="txtListadoResumenProyecto" />
			<input type="hidden" id="txtListadoCuentaCorriente"
				name="txtListadoCuentaCorriente" />
			<!-- <input type="hidden"
			id="txtListadoIndicadores" name="txtListadoIndicadores" />  -->
			
		</div>
	</form:form>

	<form>
		<input type="hidden" id="hiddenIdPrograma" name="hiddenIdPrograma"
			value="${idPrograma}">
	</form>
</body>
</html>