<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/displaytagex.css"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/tavConteiner.css" />	


<script type="text/javascript"
	src="<c:url value="/js/gestorAjax.js"></c:url>"></script>
<script type="text/javascript"
	src="<c:url value="/js/validador.js"></c:url>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.hashchange.js"></c:url>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.easytabs.js"></c:url>"></script>	
	
<script type="text/javascript">

$(document).ready(function() {
	////DATOS DEL PROYECTO
	cargaComboArea_AreaSubTematica();
	////UBICACION DEL PROYECTO
	cargaCombosDepProvDist();
	//fCargarProvincia();
	//fCargarDistrito();
	$('#tab-container').easytabs();

	//calendario
	$("#fechaConvocatoria").datepicker({
		changeMonth : true,
		changeYear : true
	});
	
	//muestra el div 
	if($('#mensaje').attr("value") != '' && $('#mensaje').attr("value") != undefined){
        alert($('#mensaje').attr("value"));
     }
	
	if ($('#idPrograma').attr("value") != 0 / 2) {
		$('#divCrearPrograma').show();
	}

});
</script>

<script type="text/javascript">

function  fActivaInput() {

	if ($('#chkRqEI').attr('checked') == 'checked') {
		$('#puntajeMinRqEI').attr('disabled', false);
		$('#puntajeMaxRqEI').attr('disabled', false);
		$('#minimoAprobatorioEI').attr('disabled', false);

		}else{
			$('#puntajeMinRqEI').attr('disabled', true);
			$('#puntajeMaxRqEI').attr('disabled', true);
			$('#minimoAprobatorioEI').attr('disabled', true);
		}
	if ($('#chkRqET').attr('checked') == 'checked') {
		$('#puntajeMinRqET').attr('disabled', false);
		$('#puntajeMaxRqET').attr('disabled', false);
		$('#minimoAprobatorioET').attr('disabled', false);

		}else{
			$('#puntajeMinRqET').attr('disabled', true);
			$('#puntajeMaxRqET').attr('disabled', true);
			$('#minimoAprobatorioET').attr('disabled', true);
		}
	if ($('#chkRqPY').attr('checked') == 'checked') {
		$('#puntajeMinRqPY').attr('disabled', false);
		$('#puntajeMaxRqPY').attr('disabled', false);
		$('#minimoAprobatorioPY').attr('disabled', false);

		}else{
			$('#puntajeMinRqPY').attr('disabled', true);
			$('#puntajeMaxRqPY').attr('disabled', true);
			$('#minimoAprobatorioPY').attr('disabled', true);
		}

}


$(window).load(function () {

	var actualizar=document.getElementById('variable').value;

		if (actualizar=="actualizar") {
			//grilla de Ubicacion 
			cargaSubAreaTematicaModificar();
			 //cargaDepProvDistModificar();
			$('#divListaRestriccion').load("actionAgregarRestriccion.jspx", {
				descripcionTipoRestriccion :"actualizar"
			});
			
			$('#divCargaActividadObligatoriaAgrilla').load(
					"actionCargaActividadObligatoriaAgrilla.jspx",
					{
						estado : "actualizar"
					});
			$('#crearPrograma').show("slow");
			$('#listarProgramas').hide("slow");
		}
		if (actualizar=="crear") {
			$('#crearPrograma').show("slow");
			$('#listarProgramas').hide("slow");
		}
		if ($('#chkRqEI').attr('checked') == 'checked') {
			$('#puntajeMinRqEI').attr('disabled', false);
			$('#puntajeMaxRqEI').attr('disabled', false);
			$('#minimoAprobatorioEI').attr('disabled', false);
		}
		if ($('#chkRqET').attr('checked') == 'checked') {
			$('#puntajeMinRqET').attr('disabled', false);
			$('#puntajeMaxRqET').attr('disabled', false);
			$('#minimoAprobatorioET').attr('disabled', false);
		}
		if ($('#chkRqPY').attr('checked') == 'checked') {
			$('#puntajeMinRqPY').attr('disabled', false);
			$('#puntajeMaxRqPY').attr('disabled', false);
			$('#minimoAprobatorioPY').attr('disabled', false);

			}
}); 


function fCrearPrograma(){
		$('#crearPrograma').show("slow");
		$('#listarProgramas').hide("slow");
		
	}

	function fEliminarPrograma(idPrograma) {
		//document.getElementById('variable').value=idPrograma; 
		
		if (confirm("Esta Seguro de Eliminar el Programa?")) {
			var form = document.formCrearPrograma;
			form.action = "actionEliminarPrograma.jspx?idPrograma="+idPrograma;
			form.submit();
		}		
	}
	function fModificarPrograma() {
	$('#dialogoModificarPrograma').load("actionModificarPrograma.jspx", {
			nombrePrograma:$('#nombrePrograma').attr("value"),
			identificadorModFinan:$('#identificadorModFinan').attr("value"),
			idTipoCuentas:$('#idTipoCuentas').attr("value"),
			idModalidadFinanciera:$('#idModalidadFinanciera').attr("value"),
			idTipoPeriodos:$('#idTipoPeriodos').attr("value"),
			fechaConvocatoria:$('#fechaConvocatoria').attr("value"),
			duracionPrograma:$('#duracionPrograma').attr("value")
			},
			$(function() {
				alert("El programa fue exitosamente modificado");
				return false;

			})
			);
			
			
	}
	
	function fCargarModificarDatosPrograma(idPrograma) {
				var form = document.formCrearPrograma;
				form.action = "actionCargarModificarDatosPrograma.jspx?idPrograma="+idPrograma;
				form.submit();		
	}
	
	
	function fModificarDatosPrograma() {
		//alert(idPrograma);
		if (validarDatosPrograma()) {
			return;
		}
		$("#nombreDocumentoAdjunto").attr("value",$("#imagenODocumento").val());
		/*if($("#nombreDocumentoAdjunto").val().length > 0){
			var validacion = validarExtensionArchivo();
			if (validacion== true ){*/
		if (confirm("Esta Seguro que desea modificar el Programa?")) {
			capturarListasDatos();
			var form = document.formCrearPrograma;
			form.action = "actionModificarDatosPrograma.jspx";
			form.submit();
		}
		//}
		//}
		}

	
	function fCancelarPrograma(){
		var form = document.formCrearPrograma;
		form.action = "showPrograma.jspx";
		form.submit();
	}
	
	function fRegistrarDatosPrograma() {
		if (validarDatosPrograma()) {
			return;
		}
		$("#nombreDocumentoAdjunto").attr("value",$("#imagenODocumento").val());
		if($("#nombreDocumentoAdjunto").val().length > 0){
			var validacion = validarExtensionArchivo();
			if (validacion== true ){
	
		capturarListasDatos();
		var form = document.formCrearPrograma;
		form.action = "actionRegistrarDatosPrograma.jspx";
		form.submit();
	
			}}}
	
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
	
	//Lista de Datos de Ubicacion y SubAreasTematicas
	function capturarListasDatos(){
	document.getElementById("txtListadoUbicacionBeneficiarios").value=JSON.stringify(arrayUbicacionBeneficiarios);
	document.getElementById("txtListadoSubAreaTematica").value=JSON.stringify(arrayAreaTematica);
	
	}
	
	function fEliminarRestricionPrograma(id) {

			$('#divListaRestriccion').load("actionEliminarRestriccion.jspx", {
				idRestriccion : id
			});
		}
	function fCargaActividadObligatoria(idActividad) {

		$('#divListCheckBoxActividadObligatoria').load(
				"actionCargaActividadObligatoria.jspx", {
					idResultado : idActividad
				});
	}

	function fCargaActividadObligatoriaAgrilla(idTipoActividad) {
		var id = "#" + idTipoActividad.value;

		if ($(id).attr('checked') == 'checked') {
			estado = "1";
		} else {
			estado = "0";
		}
		$('#divCargaActividadObligatoriaAgrilla').load(
				"actionCargaActividadObligatoriaAgrilla.jspx",
				{
					idResultado : $('#idResultadoActividadObligatoria').attr("value"),
					idTipoActividad : idTipoActividad.value,
					desActividad : idTipoActividad.name,
					estado : estado
				});
	}
	function fAgregarRestriccionAgrilla() {
		
		if (validarRestriccion()) {
			return;
		}
		var dropdown = document.getElementById("idTipoRestriccion");
		var index = dropdown.selectedIndex;
		var ddText = dropdown.options[index].text;

		$('#divListaRestriccion').load("actionAgregarRestriccion.jspx", {
			idTipoRestriccion : $('#idTipoRestriccion').attr("value"),
			descripcionTipoRestriccion : ddText,
			porcentajeMaxino : $('#porcentajeMaxino').attr("value"),
			porcentajeMinimo : $('#porcentajeMinimo').attr("value")
		});
		$('#idTipoRestriccion').attr("value",0);
		$('#porcentajeMaxino').attr("value","");
		$('#porcentajeMinimo').attr("value","");
	}

	function fMostrarSelect(valor){
	
		if (valor ==100) {
			$('#divlistTipoPeriodos').show();
			$('#divlistTipoCuentas').hide();
			$('#divlistModalidadFinanciera').hide();
			$('#divNombre').hide();
			$('#divSelect').hide();
		} 
		if(valor ==101){
			$('#divlistTipoPeriodos').hide();
			$('#divlistTipoCuentas').show();
			$('#divlistModalidadFinanciera').hide();
			$('#divNombre').hide();
			$('#divSelect').hide();
		}
		if(valor ==102){
			$('#divlistTipoPeriodos').hide();
			$('#divlistTipoCuentas').hide();
			$('#divlistModalidadFinanciera').show();
			$('#divNombre').hide();
			$('#divSelect').hide();
		}
		if(valor ==103){
			$('#divNombre').show();
			$('#divlistTipoPeriodos').hide();
			$('#divlistTipoCuentas').hide();
			$('#divlistModalidadFinanciera').hide();
			$('#divSelect').hide();
		}
		if (valor ==104) {
			$('#divlistTipoPeriodos').hide();
			$('#divlistTipoCuentas').hide();
			$('#divlistModalidadFinanciera').hide();
			$('#divNombre').hide();
			$('#divSelect').hide();
		}	

	}
	function fBuscarPrograma(){
		var form = document.formCrearPrograma;
		form.action = "actionBuscarCrearPrograma.jspx";
		form.submit();
	}
	

	
//////////////////////////////////////UBICACION/////////////////////////////
	var objXHR1;
	var objXHR111;
	var departamentos;
	var provincias;
	var distritos;

	function cargaCombosDepProvDist(){
		objXHR1 = new ObjetoAJAX();
		objXHR1.enviar("cargaCombosDepProvDist.jspx","post","llenaCombosDepProvDist",null);
	}

	function llenaCombosDepProvDist(){
		var depProvDist=JSON.parse(objXHR1.respuestaTexto());
		departamentos=depProvDist[0];
		provincias=depProvDist[1];
		distritos=depProvDist[2];
		 cargaDepProvDistModificar();
		//llenaComboDepartamentos();
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
	///////////////////////////////////////////funcionalidad para la tabla ubicacion //////////////
	var arrayUbicacionBeneficiarios=new Array();
	
	var indiceFila=0;
	var elmTBODY;
	var elmTR;
	var elmTD;


	function onclick_btnAgregarUbicacion(){
		if (validarUbicacion()) {
		return;
		}
		if (validarSiRepiteUbicacion()) {
			return;
		}
		agregarUbicacion();
		limpiarCamposUbicacion();	

	}

	function agregarUbicacion(){
		cargaDatosArray();
		cargaDatosTabla();
	}
	function cargaDatosArray(){

		var cbxDep=document.getElementById("departamento");
		var cbxProv=document.getElementById("provincia");
		var cbxDist=document.getElementById("distrito");
	
	/*	var cbxDep=document.getElementById("cbxDepartamentos");
		var cbxProv=document.getElementById("cbxProvincias");
		var cbxDist=document.getElementById("cbxDistritos");
	*/
		
	    var objUbicacion= new Object();
	  
	    //DEPARTAMENTO
	    if (cbxDep.value ==0) {
			objUbicacion.cbxDepText="0";
		}else {
	   		 objUbicacion.cbxDepText=cbxDep.options[cbxDep.selectedIndex].text;
		}
		   	objUbicacion.cbxDepValue=cbxDep.value;
		   
		//PROVINCIA
		if (cbxProv.value ==0) {
			objUbicacion.cbxProvText="0";
		}else {
	    	objUbicacion.cbxProvText=cbxProv.options[cbxProv.selectedIndex].text;
		}
		     objUbicacion.cbxProvValue=cbxProv.value;
	 
	 //DISTRITO
	    if (cbxDist.value ==0) {
				    objUbicacion.cbxDistText="0";
		}else {
				    objUbicacion.cbxDistText=cbxDist.options[cbxDist.selectedIndex].text;
		}
		   objUbicacion.cbxDistValue=cbxDist.value;

	  var agregarNuevo=3000;

	    objUbicacion.depProvDistID=agregarNuevo;
		
		var objUbicacionBeneficiarios=new Object;
		objUbicacionBeneficiarios.ubicacion=objUbicacion;
	
	      
	    arrayUbicacionBeneficiarios.push(objUbicacionBeneficiarios);
	}


	function validarSiRepiteUbicacion(){
/*	cbxDep=$('#cbxDepartamentos').attr('value');
	cbxProv=$('#cbxProvincias').attr('value');
	cbxDist=$('#cbxDistritos').attr('value');
*/
	cbxDep=$('#departamento').attr('value');
	cbxProv=$('#provincia').attr('value');
	cbxDist=$('#distrito').attr('value');

	for(var p=0; p<arrayUbicacionBeneficiarios.length; p++){
		
		depa=arrayUbicacionBeneficiarios[p].ubicacion.cbxDepValue;
		prov=arrayUbicacionBeneficiarios[p].ubicacion.cbxProvValue;	
		dist=arrayUbicacionBeneficiarios[p].ubicacion.cbxDistValue;
		
		if (cbxDep==depa && cbxProv== prov && cbxDist==dist) {
			alert("Ubicacion ya existe en la lista");
			return true;
		}
	}
}
	
	function cargaDatosTabla(){
		   limpiarTabla();
		   elmTBODY = document.getElementById("tbodyUbiProy");
		  
		     for(var p=0; p<arrayUbicacionBeneficiarios.length; p++){
				 elmTR = elmTBODY.insertRow(p);
				 for (var i=0; i<5; i++) {
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
					if(i==4){
					  var link = document.createElement("a");
					  link.setAttribute("class","ui-icon ui-icon-circle-close");
					  link.setAttribute("style","cursor:pointer");
					  link.setAttribute("title","Eliminar");
					  link.setAttribute("id",indiceFila);
					  link.onclick=function(){eliminarFilaUbi(this.id);};  
					 
					  var div = document.createElement("div");
					  div.setAttribute("align","center");
					  
					  div.appendChild(link);
					  elmTD.appendChild(div);
					  }
					if(i!=0 && i!=4)
					  elmTD.appendChild(elmText);
			    }
				indiceFila++;
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
			}
	}

	function limpiarTabla(){
		indiceFila=0;
		elmTBODY = document.getElementById("tbodyUbiProy");
		var tam = elmTBODY.rows.length;
			  for(var w=0; w<tam; w++){
		    elmTBODY.deleteRow(0);
		  } 
	}
/*INICIO/*Cargar lista de Ubicacion Para modificar*/
	var ubicacion;

	function cargaDepProvDistModificar(){
		objXHR111 = new ObjetoAJAX();
		objXHR111.enviar("actionCargaGrillaDepProvDist.jspx","post","llenaListaUbicacionModificar",null);
	}

	function llenaListaUbicacionModificar(){
		var listaUbicacion=JSON.parse(objXHR111.respuestaTexto());

		ubicacion=listaUbicacion[0];
		llenaGrillaUbicacion();
	}

	function llenaGrillaUbicacion(){
	
		//llenar valores de todos los departamentos
		for(var i=0; i<ubicacion.length; i++){
			 var objUbicacion2= new Object();
			 
			 
		    //Departamento
		    if (ubicacion[i].idProvincia==0 &&  ubicacion[i].idDistrito==0) {
		    	objUbicacion2.cbxDepText=ubicacion[i].descripcion;
		    	objUbicacion2.cbxDepValue=ubicacion[i].depProvDistID;
			   	objUbicacion2.depProvDistID=ubicacion[i].depProvDistID;
			   	objUbicacion2.cbxDistText="0";
	   			objUbicacion2.cbxDistValue="0";
		    	objUbicacion2.cbxProvText="0";
		    	objUbicacion2.cbxProvValue="0";
		    }
		    
		    //Provincia
		    if (ubicacion[i].idProvincia!=0 &&  ubicacion[i].idDistrito==0) {
		     	objUbicacion2.cbxProvText=ubicacion[i].descripcion;
			    objUbicacion2.cbxProvValue=ubicacion[i].depProvDistID;
		    	objUbicacion2.cbxDepText=getNombreDepartamento(ubicacion[i].idDepartamento);
			   	objUbicacion2.cbxDepValue="0";
			   	objUbicacion2.depProvDistID=ubicacion[i].depProvDistID;
			   	objUbicacion2.cbxDistText="0";
	   			objUbicacion2.cbxDistValue="0";
		
		    }
		  //Distrito
		    if (ubicacion[i].idProvincia!=0 &&  ubicacion[i].idDistrito!=0) {
		    	objUbicacion2.cbxDistText=ubicacion[i].descripcion;
	   			objUbicacion2.cbxDistValue=ubicacion[i].depProvDistID;
		    	objUbicacion2.cbxProvText=getNombreProvincia(ubicacion[i].idProvincia,ubicacion[i].idDepartamento);
			    objUbicacion2.cbxProvValue=1;
		    	objUbicacion2.cbxDepText=getNombreDepartamento(ubicacion[i].idDepartamento);   	
		    	objUbicacion2.cbxDepValue=1;
			   	objUbicacion2.depProvDistID=ubicacion[i].depProvDistID;
	
		    }
			  
		    var agregarModificar=ubicacion[i].depProvDistID;
		    objUbicacion2.depProvDistID=agregarModificar;
		    objUbicacionBeneficiarios=new Object();
		    objUbicacionBeneficiarios.ubicacion = objUbicacion2;

			arrayUbicacionBeneficiarios.push(objUbicacionBeneficiarios);
		}

 				cargaDatosTabla();

	}
	 //obtener el nombre del departamento  
    function getNombreDepartamento(idDep){
		var idDepartamento=null;
		//obtener el idDepartamento 
		for(var i=0; i<departamentos.length; i++){
	          if(departamentos[i].idDepartamento==idDep && departamentos[i].idProvincia==0){
	        	 idDepartamento=departamentos[i].descripcion;break;
	          }
		}
		return idDepartamento;
	}
	 //obtener el nombre de provincia
    function getNombreProvincia(idProv, idDep){
		var idProvincia=null;
		 
		for(var i=0; i<provincias.length; i++){
	          if(provincias[i].idProvincia==idProv && provincias[i].idDepartamento== idDep){
	        	  idProvincia=provincias[i].descripcion;break;
	          }
		}
		return idProvincia;
	}
    
    

/*FIN/*Cargar lista de Ubicacion Para modificar*/
///////////////////////////////////////////////AREAS TEMATICAS///////////////////////////////
var objXHR5;
var areasTematicas;
var subAreasTematicas;
var subNivel;

function cargaComboArea_AreaSubTematica(){
objXHR5 = new ObjetoAJAX();
objXHR5.enviar("cargaCombosAreasTematicas.jspx","post","llenaDataAreasTematicas",null);

}

function llenaDataAreasTematicas() {
var areasTematicasTodas=JSON.parse(objXHR5.respuestaTexto());
areasTematicas=areasTematicasTodas[0];
var subAreasTematicasSubNivel=areasTematicasTodas[1];
subAreasTematicas=obtenerSubAreasTematicasOsubniveles(subAreasTematicasSubNivel,1);

llenaComboAreasTematicas();
}

function llenaComboAreasTematicas() {
var cbxAreaTem = document.createElement("select");
cbxAreaTem.setAttribute("id", "cbxAreaTematica");
cbxAreaTem.setAttribute("name", "cbxAreaTematica");
//evento para seleccionar la lista de provincias deacuerdo al departamento seleccionado.
cbxAreaTem.onchange = function() {
llenaComboSubAreasTematicas();
};
//valor por defecto
var op = document.createElement("option");
op.setAttribute("value", "0");
var text = document.createTextNode("----Seleccionar----");
op.appendChild(text);
cbxAreaTem.appendChild(op);
//llenar valores de todos los departamentos
for ( var i = 0; i < areasTematicas.length; i++) {
op = document.createElement("option");
op.setAttribute("value", areasTematicas[i].tablaEspecificaID);
text = document
.createTextNode(areasTematicas[i].descripcionCabecera);
op.appendChild(text);
cbxAreaTem.appendChild(op);
}
//limpia div
document.getElementById("div_cbxAreaTematica").innerHTML = "";
//pinta combo en el div
document.getElementById("div_cbxAreaTematica").appendChild(cbxAreaTem);

}

function llenaComboSubAreasTematicas() {
//obtener datos del area tematica seleccionado para llenar sus subareas tematicas respectivas
var areaTematicaID = document.getElementById("cbxAreaTematica").options[document
.getElementById("cbxAreaTematica").selectedIndex].value;
areaTematicaID = parseInt(areaTematicaID);
var subAreasTematicasSeleccionadas = new Array();
for ( var i = 0; i < subAreasTematicas.length; i++) {
if (subAreasTematicas[i].fkIdtablaespAreaTematica == areaTematicaID)
subAreasTematicasSeleccionadas.push(subAreasTematicas[i]);
}
var cbxSubAreaTematica = document.createElement("select");
cbxSubAreaTematica.setAttribute("id", "cbxSubAreaTematica");
cbxSubAreaTematica.setAttribute("name", "cbxSubAreaTematica");
cbxSubAreaTematica.onchange = function() {
//llenaComboSubnivel();
};
//valor por defecto
var op = document.createElement("option");
op.setAttribute("value", "0");
var text = document.createTextNode("----Seleccionar----");
op.appendChild(text);
cbxSubAreaTematica.appendChild(op);
//llenar valores de las subareas tematicas dependiendo del area tematica seleccionada
for ( var x = 0; x < subAreasTematicasSeleccionadas.length; x++) {
op = document.createElement("option");
op.setAttribute("value",
subAreasTematicasSeleccionadas[x].subAreaTematicaID);
text = document
.createTextNode(subAreasTematicasSeleccionadas[x].descripcionSubAt);
op.appendChild(text);
cbxSubAreaTematica.appendChild(op);
}
//limpia div
document.getElementById("div_cbxSubAreaTematica").innerHTML = "";
//pinta combo en el div
document.getElementById("div_cbxSubAreaTematica").appendChild(
cbxSubAreaTematica);

}
function obtenerSubAreasTematicasOsubniveles(subAreasTematicasSubNivel, op) {
var resultado = new Array();
for ( var i = 0; i < subAreasTematicasSubNivel.length; i++) {
if (subAreasTematicasSubNivel[i].codInternoSubNivel == 0 && op == 1) {//subareas Tematicas
resultado.push(subAreasTematicasSubNivel[i]);
}
}
return resultado;
}

var arrayAreaTematica=new Array();

function onclick_btnAgregarSubAreaTematica(){
	if (validarSubAreaTematica()) {
		return;
	}
	agregarAreaTematica();
	limpiarCamposAreaTematica();
}

function agregarAreaTematica(){
	if (!verificarAreaTematica()) {
		cargaDatosArrayAreaTematica();
		cargaDatosTablaAreaTematica();
	}else {
		alert("Ya existe en la lista el Area Tematica");
	}
	
}


function cargaDatosArrayAreaTematica(){

	var cbxAT=document.getElementById("cbxAreaTematica");
	var cbxSAT=document.getElementById("cbxSubAreaTematica");
    var objAreaTematica= new Object();

    //AREA TEMATICA
    if (cbxAT.value ==0) {
    	objAreaTematica.cbxATtext="0";
	}else {
		objAreaTematica.cbxATtext=cbxAT.options[cbxAT.selectedIndex].text;
	}
	   	objAreaTematica.cbxATvalue=cbxAT.value;
	   
	  //SUB AREA TEMATICA
	if (cbxSAT.value ==0) {
		objAreaTematica.cbxSATtext="0";
	}else {
    	objAreaTematica.cbxSATtext=cbxSAT.options[cbxSAT.selectedIndex].text;
	}
	     objAreaTematica.cbxSATvalue=cbxSAT.value;

	var objSubAreaTematica=new Object;
	objSubAreaTematica.areaTematica=objAreaTematica;

		       
	arrayAreaTematica.push(objSubAreaTematica);
}

function limpiarCamposAreaTematica(){

	document.getElementById('cbxAreaTematica').options.selectedIndex = 0;
	document.getElementById('cbxSubAreaTematica').options.selectedIndex = 0;
	
}
/*
var indiceFilaA=0;
var elmTBODYA;
var elmTRA;
var elmTDA;*/

function cargaDatosArrayAreaTematica(){

	var cbxAT=document.getElementById("cbxAreaTematica");
	var cbxSAT=document.getElementById("cbxSubAreaTematica");
    var objAreaTematica= new Object();

    //AREA TEMATICA
    if (cbxAT.value ==0) {
    	objAreaTematica.cbxATtext="0";
		}else {
		objAreaTematica.cbxATtext=cbxAT.options[cbxAT.selectedIndex].text;
		}
	   	objAreaTematica.cbxATvalue=cbxAT.value;
	   
	  //SUB AREA TEMATICA
	if (cbxSAT.value ==0) {
		objAreaTematica.cbxSATtext="0";
		}else {
    	objAreaTematica.cbxSATtext=cbxSAT.options[cbxSAT.selectedIndex].text;
		}
	     objAreaTematica.cbxSATvalue=cbxSAT.value;
 
	     objAreaTematica.estado=cbxSAT.value;
  
	var objSubAreaTematica=new Object;
	objSubAreaTematica.areaTematica=objAreaTematica;

		       
	arrayAreaTematica.push(objSubAreaTematica);
}

/* function limpiarCamposAreaTematica(){

	document.getElementById('cbxAreaTematica').options.selectedIndex = 0;
	document.getElementById('cbxSubAreaTematica').options.selectedIndex = 0;
	
} */

function verificarAreaTematica(){
	for(var p=0; p<arrayAreaTematica.length; p++){
	valueAreaT = arrayAreaTematica[p].areaTematica.cbxATvalue;
	valueSubAreaT= arrayAreaTematica[p].areaTematica.cbxSATvalue;	
	if (valueAreaT==$('#cbxAreaTematica').attr('value') && valueSubAreaT==$('#cbxSubAreaTematica').attr('value')) {
		return true;
	}
					
			
	}
}
var indiceFilaA=0;
var elmTBODYA;
var elmTRA;
var elmTDA;
	function cargaDatosTablaAreaTematica(){
		   limpiarTablaAreaTematica();
		   elmTBODYA = document.getElementById("tbodyAreaTematica");
		   
		     for(var p=0; p<arrayAreaTematica.length; p++){
				 elmTRA = elmTBODYA.insertRow(p);
				 for (var i=0; i<4; i++) {
					elmTDA = elmTRA.insertCell(i);
					if(i==0){
					  var ind=indiceFilaA+1;
					  elmTextA = document.createTextNode(ind);
						var span = document.createElement("div");
						span.setAttribute("align","center");
						span.appendChild(elmTextA);
						 elmTDA.appendChild(span);
						}
					if(i==1){
						elmTextA = document.createTextNode(arrayAreaTematica[p].areaTematica.cbxATtext);
					}
						if(i==2){
					  elmTextA = document.createTextNode(arrayAreaTematica[p].areaTematica.cbxSATtext);
						}
					  if(i==3){
				 	  var linkA = document.createElement("a");
					 linkA.setAttribute("class","ui-icon ui-icon-circle-close");
					 linkA.setAttribute("style","cursor:pointer");
					 linkA.setAttribute("title","Eliminar");
					 linkA.setAttribute("id",indiceFilaA);
					 linkA.onclick=function(){eliminarFilaAreaTematica(this.id);};  
					 
					  var div = document.createElement("div");
					  div.setAttribute("align","center");
					  
					  div.appendChild(linkA);
					  elmTDA.appendChild(div);
		 }			
		 if(i!=0 && i!=3)
					  elmTDA.appendChild(elmTextA);
			    }
				indiceFilaA++;
			 }
	}


	function eliminarFilaAreaTematica(numeroFilaEliminar){ 
		    var eli=parseInt(numeroFilaEliminar);
		    var eliMostrar=eli+1;
			var rpt=confirm("�Seguro que desea eliminar el area tematica nro:"+eliMostrar+"?");
			if(rpt==true){
				arrayAreaTematica.splice(eli,1);
				limpiarTablaAreaTematica();
				cargaDatosTablaAreaTematica();	
			}
	}

	function limpiarTablaAreaTematica(){
		indiceFilaA=0;
		elmTBODYA = document.getElementById("tbodyAreaTematica");
		var tam = elmTBODYA.rows.length;
	
		  for(var w=0; w<tam; w++){
		    elmTBODYA.deleteRow(0); 
		  } 
	}

	/*INICIO/*Cargar lista de Ubicacion Para modificar*/

	var subAreaTematica;
	var objXHR55;

	function cargaSubAreaTematicaModificar(){
		objXHR55 = new ObjetoAJAX();
		objXHR55.enviar("actionCargaGrillaRestriccionSubAreaTematica.jspx","post","llenaListaSubAreaTematicaModificar",null);
	}

	function llenaListaSubAreaTematicaModificar(){
		var listaSubAreaTematica=JSON.parse(objXHR55.respuestaTexto());

		subAreaTematica=listaSubAreaTematica[0];
		llenaGrillaSubAreaTematica();
	}

	function llenaGrillaSubAreaTematica(){
	
		//llenar valores de todos los departamentos
		for(var i=0; i<subAreaTematica.length; i++){
			 var objSubAreaTematica2= new Object();
			 
		  		objSubAreaTematica2.cbxATvalue=subAreaTematica[i].fkIdtablaespAreaTematica;
		  		objSubAreaTematica2.cbxATtext=getAreaTematica(subAreaTematica[i].fkIdtablaespAreaTematica);
		  		objSubAreaTematica2.cbxSATvalue=subAreaTematica[i].subAreaTematicaID;
		  		objSubAreaTematica2.cbxSATtext=subAreaTematica[i].descripcionSubAt;
		  		//para guiarse si en la eliminacion
		  		objSubAreaTematica2.estado=3000;
		  		
		    objListSubAreaTematicas=new Object();
		    objListSubAreaTematicas.areaTematica = objSubAreaTematica2;
		    arrayAreaTematica.push(objListSubAreaTematicas);
		}
		cargaDatosTablaAreaTematica();
	}
	 //obtener el nombre del departamento  
    function getAreaTematica(idAreaTematica){
		var cadena="";
		 for(var i=0; i<areasTematicas.length; i++){
	          if(areasTematicas[i].tablaEspecificaID == idAreaTematica){
	        	  cadena=areasTematicas[i].descripcionCabecera;
	        	 break;
	          }
		}
		return cadena;
	}
    

/*FIN/*Cargar lista de Ubicacion Para modificar*/
function fGrabarPrograma(){
	if (validarPrograma()) {
		return;
	}
	var form = document.formCrearPrograma;
	form.action = "actionCrearPrograma.jspx";
	form.submit();

}
//VALIDACIONES DE LOS CAMPOS
	function validarPrograma(){
		  var cabecera = "Debe ingresar la siguiente informaci\u00F3n obligatoria(*):";
		  var mensaje = "";
		  if($('#idTipoCuentas').attr("value") == 0){
			  
			 mensaje += "\n - Seleccionar un Tipo de Cuentas.";
		  }
		  if($('#idModalidadFinanciera').attr("value") == 0){
			 mensaje += "\n - Seleccionar Modalidad financiera.";
		  }
	      if($('#idTipoPeriodos').attr("value") == 0){
	     	 mensaje += "\n - Seleccionar un Tipo de Periodo.";
	 	  }
		  if($('#identificadorModFinan').attr("value") == ''){
			 mensaje += "\n - Identificador del programa.";
		  }
		  if($('#fechaConvocatoria').attr("value") == ''){
			 mensaje += "\n - Fecha Convocatoria.";
		  }
		  if($('#duracionPrograma').attr("value") == ''){
			 mensaje += "\n - Duraci\u00F3n Programa.";
		  }
		  if($('#duracionPrograma').attr("value") == ''){
				 mensaje += "\n - Duraci\u00F3n Programa.";
			  }
		  
		  if($('#duracionPrograma').attr("value") == ''){
				 mensaje += "\n - Duraci\u00F3n Programa.";
			  }
		  if($('#duracionPrograma').attr("value") == ''){
				 mensaje += "\n - Duraci\u00F3n Programa.";
			  }
		 
		  if(mensaje != ""){
		     alert(cabecera + mensaje);
		     return true;
		  }else{
			 return false;
		  }
	}
	function validarDatosPrograma(){
		  var cabecera = "Debe ingresar la siguiente informaci\u00F3n obligatoria(*):";
		  var mensaje = "";

		  if ($('#chkRqEI').attr("checked")== 'checked') {
			 if ($('#puntajeMaxRqEI').attr("value")==''||$('#puntajeMinRqEI').attr("value")==''|| $('#minimoAprobatorioEI').attr("value")=='') {
				 mensaje += "\n - Puntaje Evaluacion Institucional.";
			} else {
				  var maxEI =  parseFloat($('#puntajeMinRqEI').attr("value"));
		  	      var minEI =parseFloat($('#puntajeMaxRqEI').attr("value"));
				if(!maxEI > minEI){
		 			 mensaje += "\n - Puntaje Max Debe ser Mayor que Puntaje Minimo Evaluacion Institucional.";
		 		  }
			}		
			}
		  if ($('#chkRqET').attr("checked")== 'checked') {
			  if ($('#puntajeMaxRqET').attr("value")==''||$('#puntajeMinRqET').attr("value")==''|| $('#minimoAprobatorioET').attr("value")=='') {
					 mensaje += "\n - Puntaje Evaluacion Tecnica.";
				}
			  else {
				  var maxET =  parseFloat($('#puntajeMinRqET').attr("value"));
		  	      var minET =parseFloat($('#puntajeMaxRqET').attr("value"));
				if(!maxET > minET){
		 			 mensaje += "\n - Puntaje Max Debe ser Mayor que Puntaje Minimo Evaluacion Tecnica.";
		 		  }
			}		
			}
		  if ($('#chkRqPY').attr("checked")== 'checked') {
			  if ($('#puntajeMaxRqPY').attr("value")==''||$('#puntajeMinRqPY').attr("value")==''|| $('#minimoAprobatorioPY').attr("value")=='') {
					 mensaje += "\n - Puntaje Evaluacion Proyecto.";
				}else {
					  var maxPY =  parseFloat($('#puntajeMinRqPY').attr("value"));
			  	      var minPY =parseFloat($('#puntajeMaxRqPY').attr("value"));
					if(!maxPY > minPY){
			 			 mensaje += "\n - Puntaje Max Debe ser Mayor que Puntaje Minimo Proyecto.";
			 		  }
				}		
			}
		  
		  if(mensaje != ""){
		     alert(cabecera + mensaje);
		     return true;
		  }else{
			 return false;
		  }
	}
	
	function validarSubAreaTematica(){
		  var cabecera = "Debe ingresar la siguiente informaci\u00F3n obligatoria(*):";
		  var mensaje = "";
		  if($('#cbxAreaTematica').attr("value") == 0){
			  
			 mensaje += "\n - Seleccionar un Tipo de Area Tematica.";
		  }
		  if($('#cbxSubAreaTematica').attr("value")== 0){
			 mensaje += "\n - Seleccionar un Tipo de Sub Area Tematica.";
		  }
	 	  if(mensaje != ""){
		     alert(cabecera + mensaje);
		     return true;
		  }else{
			 return false;
		  }
	}
	function validarRestriccion(){
		  var cabecera = "Debe ingresar la siguiente informaci\u00F3n obligatoria(*):";
		  var mensaje = "";
		  if($('#idTipoRestriccion').attr("value") == 0){
			  
			 mensaje += "\n - Seleccionar un Tipo de Restriccion.";
		  }
		  if($('#porcentajeMaxino').attr("value")== ''){
			 mensaje += "\n - Porcentaje Maximo.";
		  }
	      if($('#porcentajeMinimo').attr("value") == ''){
	     	 mensaje += "\n - Porcentaje Minimo.";
	 	  }	      
	      if($('#porcentajeMinimo').attr("value") != '' && $('#porcentajeMaxino').attr("value") != ''){
	    	    var max =  parseFloat($('#porcentajeMaxino').attr("value"));
	  	     	 var min =parseFloat($('#porcentajeMinimo').attr("value"));
	    	
	  	     	 if( min>max ){    	 
	    		  mensaje += "\n - Porcentaje Maximo debe ser mayor que Porcentaje Minimo.";
	 	 	  }	
		 	  }
		  if(mensaje != ""){
		     alert(cabecera + mensaje);
		     return true;
		  }else{
			 return false;
		  }
	}
	function validarUbicacion(){
		  var cabecera = "Debe ingresar la siguiente informaci\u00F3n obligatoria(*):";
		  var mensaje = "";
		  if($('#departamento').attr("value") == 0 &&  $('#provincia').attr("value")== 0 && $('#distrito').attr("value") == 0){ 
			 mensaje += "\n - Seleccionar una Departamento - Provincia - Distrito.";
		  }
		  if(mensaje != ""){
		     alert(cabecera + mensaje);
		     return true;
		  }else{
			 return false;
		  }
	}
	
	function validarPuntajeEvaluacion()	{  
		  var cabecera = "Debe ingresar la siguiente informaci\u00F3n obligatoria(*):";
		  var mensaje = "";
 		 
 		 switch(opc) {
         case 1://Para numeros enteros en general. Solicitudes, cedulas,nit, numeros de identificacion, etc
        	
         break;
         case 2://Para texto largo general
        	 if($('#puntajeMaxRqEI').attr("value")> $('#minimoAprobatorioEI').attr("value")> $('#puntajeMinRqEI').attr("value")){
    			 mensaje += "Verificar Puntaje Minimo.";
    		  }
         break;
				}

 		 if(mensaje != ""){
		     alert(cabecera + mensaje);
		     return true;
		  }else{
			 return false;}
	}
	
	function fCargarProvincia(){  
		  var idDepartamento = $("#departamento").val();
		  var nomMetodo = "provincia";
		  //if(idDepartamento!=0){
		  $("#provincia").load("cargarComboUbigeo.jspx", {departamentoID:idDepartamento,metodo:nomMetodo},function(){
			  fCargarDistrito();
			  $("#distrito").attr("value",0);
		  });
		  //}
		}

	function fCargarDistrito(){  
		  var idDepartamento = $("#departamento").val();
		  var idProvincia = $("#provincia").val();
			//if(idProvincia !=0){
		  var nomMetodo = "distrito";
		    $("#distrito").load("cargarComboUbigeo.jspx", {departamentoID:idDepartamento,provinciaID:idProvincia,metodo:nomMetodo});
		//}
	}
	
</script>

<style type="text/css">
/*demo page css*/
/*crearPrograma*/
.demoHeaders {
	margin-top: 1em;
}
</style>

	<form:form commandName="imagenOArchivoTempBean" id="formCrearPrograma" 
			name="formCrearPrograma" action="actionCrearPrograma.jspx"
			method="POST" cssClass="form-clasico" enctype="multipart/form-data" >
		<!--INICIO DIALOGOS PARA MENSAJES  -->
		<div id="dialogoModificarPrograma" title="Mensaje" align="center" style="display: none;"></div>
		

		<!--FIN DIALOGOS PARA MENSAJES  -->

		<div class="encabezado">MANTENIMIENTO PROGRAMA</div>
		<h2 class="demoHeaders"></h2>

		<!-- Fin del tag listarPrograma -->
		<div id="crearPrograma" style="display: none;">
			<!-- Inicio del tag crearPrograma -->
			<fieldset style="padding-left: 15px;">
				<legend>CREAR PROGRAMA</legend>
				<input type="hidden" id="idPrograma" value="${programaID}">
				<input type="hidden" id="variable" name="variable"
					value="${variable}">


				<table style="width: 100%;">
					<tr>
						<td style="text-align: right; width: 25%;"><label>Programa:</label>
						</td>
						<td style="text-align: left; width: 25%;"><input type="text"
							name="nombrePrograma" id="nombrePrograma"
							value="${nombrePrograma}" maxlength="255" />
						</td>
						<td style="text-align: right; width: 25%;"><label>Identificador del Programa:</label>
						</td>
						<td style="text-align: left; width: 25%;"><input type="text"
							name="identificadorModFinan" id="identificadorModFinan"
							value="${identificadorModFinan}" />
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%"><label>Tipo
								Cuenta:</label>
						</td>
						<td style="text-align: left; width: 25%;"><select
							name="idTipoCuentas" id="idTipoCuentas">
								<option value="0">
									<c:out value="-- Seleccionar --" />
								</option>
								<c:forEach items="${listTipoCuentas}" var="tipoCuenta">
									<c:if test="${ idTipoCuentas != tipoCuenta.tablaEspecificaID}">
										<option value="${tipoCuenta.tablaEspecificaID}">
											<c:out value="${tipoCuenta.descripcionCabecera}" />
										</option>
									</c:if>
									<c:if test="${ idTipoCuentas == tipoCuenta.tablaEspecificaID}">
										<option value="${tipoCuenta.tablaEspecificaID}"
											selected="selected">
											<c:out value="${tipoCuenta.descripcionCabecera}" />
										</option>
									</c:if>

								</c:forEach>
						</select>
						</td>
						<td colspan="2" style="width: 50%;"></td>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%;"><label>Modalidad:</label>
						</td>
						<td style="text-align: left; width: 25%;"><select
							name="idModalidadFinanciera" id="idModalidadFinanciera">
								<option value="0">
									<c:out value="-- Seleccionar --" />
								</option>
								<c:forEach items="${listModalidadFinanciera}" var="modali">
									<c:if
										test="${ idModalidadFinanciera != modali.tablaEspecificaID}">
										<option value="${modali.tablaEspecificaID}">
											<c:out value="${modali.descripcionCabecera}" />
										</option>
									</c:if>
									<c:if
										test="${ idModalidadFinanciera == modali.tablaEspecificaID}">
										<option value="${modali.tablaEspecificaID}"
											selected="selected">
											<c:out value="${modali.descripcionCabecera}" />
										</option>
									</c:if>

								</c:forEach>
						</select>
						</td>
						<td style="text-align: right; width: 25%"><label>Fecha
								Convocatoria: </label>
						</td>
						<td style="text-align: left; width: 25%;"><input type="text"
							name="fechaConvocatoria" maxlength="0" style="width: 90px;"
							id="fechaConvocatoria" value="${fechaConvocatoria}" /><label>(dd/mm/aaaa)</label>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 25%;"><label>Duraci&oacute;n
								Max: </label>
						</td>
						<td style="text-align: left; width: 25%;"><input type="text"
							name="duracionPrograma" maxlength="2" id="duracionPrograma"
							value="${duracionPrograma}" style="width: 40%"
							 /><label>(meses):
						</label>
						</td>
						<td style="text-align: right; width: 25%;"><label>Periodo:</label>
						</td>
						<td style="text-align: left; width: 25%;"><select
							name="idTipoPeriodos" id="idTipoPeriodos">
								<option value="0">
									<c:out value="--Seleccionar--" />
								</option>
								<c:forEach items="${listTipoPeriodos}" var="tipoPeriodo">
									<c:if test="${ idTipoPeriodos != tipoPeriodo.tipoPeriodoID}">
										<option value="${tipoPeriodo.tipoPeriodoID}">
											<c:out value="${tipoPeriodo.descripPeriodo}" />
										</option>
									</c:if>
									<c:if test="${ idTipoPeriodos == tipoPeriodo.tipoPeriodoID}">
										<option value="${tipoPeriodo.tipoPeriodoID}"
											selected="selected">
											<c:out value="${tipoPeriodo.descripPeriodo}" />
										</option>
									</c:if>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align: right; width: 100%;"><c:if
								test="${programaID== null}">
								<input type="button" value="Crear Programa" name="crearPrograma"
									onclick="fGrabarPrograma()" width="80px" />
								<input type="button" value="Cancelar"
									onclick="fCancelarPrograma()" width="80px" />
							</c:if> <c:if test="${programaID!= null}">
								<input type="button" value="Modificar Programa"
									name="modificarPrograma" onclick="fModificarPrograma()" />

							</c:if>
						</td>
					</tr>
				</table>
				<div id="divCrearPrograma" style="display: none;">
					<div id="tab-container" class="tab-container" style="width: 100%;">
						<ul class='etabs'>
							<li class='tab'><a href="#puntajeEvaluacion">Puntajes de<br/> Evaluacion</a></li>
							<li class='tab'><a href="#restriccion_01">Restricciones &amp; <br/>Actividad Obligatoria</a></li>
							<li class='tab'><a href="#restriccion_02">Restricciones de <br/>Area Tematica &amp; Ubicacion </a></li>
							<li class='tab'><a href="#archivo">Archivo <br/>(Bases del Concurso)</a></li>
							<li class='tab'><a href="#proyectos">Proyectos del <br/>Programa</a></li>
						</ul>
				<div class='panel-container'>
					<div id="puntajeEvaluacion">
					<br />
					<fieldset>
						<table style="width: 100%">
							<tr>
								<c:if test="${chkRqPYG== null || chkRqPYG==0}">
									<td colspan="4" style="width: 100%;"><input
										type="checkbox" name="chkRqPYG" id="chkRqPYG"><label>
											Requiere Proyecto Global?</label>
									</td>
								</c:if>
								<c:if test="${chkRqPYG==1}">
									<td colspan="4" style="width: 100%;"><input
										type="checkbox" name="chkRqPYG" id="chkRqPYG"
										checked="checked"><label> Requiere Proyecto
											Global?</label>
									</td>
								</c:if>
							</tr>
							<tr>
								<c:if test="${chkRqEI== null || chkRqEI==0}">
									<td style="width: 35%;"><input type="checkbox"
										name="chkRqEI" id="chkRqEI" onclick="fActivaInput()"><label>Requiere
											Evaluaci&oacute;n Institucional?</label>
									</td>
								</c:if>
								<c:if test="${chkRqEI== 1}">
									<td style="width: 35%;"><input type="checkbox"
										name="chkRqEI" id="chkRqEI" checked="checked"
										onclick="fActivaInput()"><label>RequiereEvaluaci&oacute;n
											Institucional?</label>
									</td>
								</c:if>
								<td colspan="3" style="width: 65%;">
									<table width="100%">
										<tr>
											<td><label>Puntaje Min:</label>
											</td>
											<td><input type="text" name="puntajeMinRqEI"
												maxlength="3" id="puntajeMinRqEI" value="${puntajeMinRqEI}"
												disabled="disabled" onkeypress="javascript:return Valida_Dato(event,8);" 
												style="width: 40px;">
											</td>
											<td><label>Puntaje Max:</label>
											</td>
											<td><input type="text" name="puntajeMaxRqEI"
												maxlength="3" id="puntajeMaxRqEI" value="${puntajeMaxRqEI}"
												disabled="disabled" onkeypress="javascript:return Valida_Dato(event,8);" 
												style="width: 40px;">
											</td>
											<td><label>Minimo Aprobatorio:</label>
											</td>
											<td><input type="text" name="minimoAprobatorioEI"
												maxlength="3" id="minimoAprobatorioEI" onkeypress="javascript:return Valida_Dato(event,8);"
												value="${minimoAprobatorioEI}" disabled="disabled"
												style="width: 40px;">
											</td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<c:if test="${chkRqET== null || chkRqET==0 }">
									<td style="width: 35%;"><input type="checkbox"
										name="chkRqET" id="chkRqET" onchange="fActivaInput()"><label>Requiere
											Evaluaci&oacute;n T&eacute;cnica?</label></td>
								</c:if>
								<c:if test="${chkRqET== 1}">
									<td style="width: 35%;"><input type="checkbox"
										name="chkRqET" id="chkRqET" checked="checked"
										onchange="fActivaInput()"><label>Requiere
											Evaluaci&oacute;n T&eacute;cnica?</label></td>
								</c:if>
								<td colspan="3" style="width: 65%;">
									<table width="100%">
										<tr>
											<td><label>Puntaje Min:</label>
											</td>
											<td><input type="text" name="puntajeMinRqET" onkeypress="javascript:return Valida_Dato(event,8);"
												maxlength="3" id="puntajeMinRqET" value="${puntajeMinRqET}"
												disabled="disabled" 
												style="width: 40px;">
											</td>
											<td><label>Puntaje Max:</label>
											</td>
											<td><input type="text" name="puntajeMaxRqET"
												maxlength="3" id="puntajeMaxRqET" value="${puntajeMaxRqET}"
												disabled="disabled" onkeypress="javascript:return Valida_Dato(event,8);" 
												style="width: 40px;">
											</td>
											<td><label>Minimo Aprobatorio:</label>
											</td>
											<td><input type="text" name="minimoAprobatorioET"
												maxlength="3" id="minimoAprobatorioET" 
												value="${minimoAprobatorioET}" disabled="disabled"
												onkeypress="javascript:return Valida_Dato(event,8);"
												style="width: 40px;">
											</td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<c:if test="${chkRqPY== null||chkRqPY== 0}">
									<td style="width: 35%;"><input type="checkbox"
										name="chkRqPY" id="chkRqPY" onclick="fActivaInput()"><label>Requiere
											Evaluaci&oacute;n Proyecto?</label>
									</td>
								</c:if>
								<c:if test="${chkRqPY== 1}">
									<td style="width: 35%;"><input type="checkbox"
										name="chkRqPY" id="chkRqPY" checked="checked"
										onclick="fActivaInput()"><label>Requiere
											Evaluaci&oacute;n Proyecto?</label>
									</td>
								</c:if>
								<td colspan="3" style="width: 65%;">
									<table width="100%">
										<tr>
											<td><label>Puntaje Min:</label>
											</td>
											<td><input type="text" name="puntajeMinRqPY"
												id="puntajeMinRqPY" value="${puntajeMinRqPY}"
												disabled="disabled" maxlength="3"
												onkeypress="javascript:return Valida_Dato(event,8);"				
												style="width: 40px;">
											</td>
											<td><label>Puntaje Max:</label>
											</td>
											<td><input type="text" name="puntajeMaxRqPY"
												id="puntajeMaxRqPY" value="${puntajeMaxRqPY}"
												disabled="disabled" maxlength="3"
												onkeypress="javascript:return Valida_Dato(event,8);"
												style="width: 40px;">
											</td>
											<td><label>Minimo Aprobatorio:</label>
											</td>
											<td><input type="text" name="minimoAprobatorioPY"
												id="minimoAprobatorioPY" value="${minimoAprobatorioPY}"
												disabled="disabled" maxlength="3"
												onkeypress="javascript:return Valida_Dato(event,8);"
												style="width: 40px;">
											</td>
										</tr>
									</table></td>
							</tr>
						</table>
					</fieldset>
					</div>
					<div id="restriccion_01">
					<br />
					<table style="width: 100%;">
						<tr>
							<td style="vertical-align: top; width: 50%;">
								<fieldset>
									<legend>RESTRICCIONES </legend>
									<table style="width: 100%;">
										<tr>
											<td style="width: 20%; text-align: right;"><label> Restriccion:</label>
											</td>
											<td style="width: 80%; text-align: left;"><select name="idTipoRestriccion"
												id="idTipoRestriccion">
													<option value="0">
														<c:out value="-- Seleccionar --" />
													</option>
													<c:forEach items="${listTipoRestriccion}"
														var="tipoRestriccion">
														<option value="${tipoRestriccion.tablaEspecificaID}">
															<c:out value="${tipoRestriccion.descripcionCabecera}" />
														</option>
													</c:forEach>
											</select>
											</td>
										</tr>
										<tr>
											<td colspan="2" style="width: 100%;">
												<table style="width: 100%;">
													<tr>

														<td style="text-align: right; width: 25%;"><label>% Minimo:</label>
														</td>
														<td  style="text-align: left; width: 25%;"><input type="text" name="porcentajeMinimo"
															maxlength="3"
															id="porcentajeMinimo" style="width: 40px;">
														</td>
														<td  style="text-align: right; width: 25%;"><label>% Maximo:</label>
														</td>
														<td  style="text-align: left; width: 25%;"><input type="text" name="porcentajeMaxino"
															maxlength="3"
															
															id="porcentajeMaxino" style="width: 40px;">
														</td>
													</tr>
													<tr>
														<td colspan="4"  style="text-align: right; width: 100%;"><input type="button"
															value="agregar Restriccion" name="agregarRestriccion"
															onclick="fAgregarRestriccionAgrilla()" /></td>
													</tr>
												</table></td>
										</tr>
										<tr>
											<td colspan="2" style="width: 100%;">
									<div id="divListaRestriccion"
										style="width: 100%; text-align: center;"></div>		
											</td>
										</tr>	
									</table>
								</fieldset></td>
							<td style="vertical-align: top; width: 50%;">
								<fieldset>
									<legend>ACTIVIDAD OBLIGATORIA</legend>
									<table style="width: 100%;">
										<tr>
											<td style="text-align: right;width: 25%;"><label>Resultado:</label>
											</td>
											<td style="text-align: left;width: 75%;"><select name="idResultadoActividadObligatoria"
												id="idResultadoActividadObligatoria"
												onchange="fCargaActividadObligatoria(this.value)">
													<option value="-1">
														<c:out value="--Seleccionar--" />
													</option>
													<c:forEach items="${listResultadoActividadObligatoria}"
														var="resultadoActividadObligatoria">
														<option
															value="${resultadoActividadObligatoria.tablaEspecificaID}">
															<c:out
																value="${resultadoActividadObligatoria.descripcionCabecera}"></c:out>
														</option>
													</c:forEach>
											</select>
											</td>
										</tr>
										<tr>
											<td  style="text-align: right;width: 25%; vertical-align: top;"><label>Actividad:</label>
											</td>
											<td  style="text-align: left;width: 75%;">
												<div id="divListCheckBoxActividadObligatoria"></div></td>
										</tr>
										<tr>
											<td colspan="2" style="width: 100%;">
											<div style="width: 100%;" id="divCargaActividadObligatoriaAgrilla"></div>
											</td>
										</tr>										
									</table>
								</fieldset></td>
						</tr>
					</table>
					</div>
					<div id="restriccion_02">
					<br />
					<table style="width: 100%;">
						<tr>
							<td style="vertical-align: top;width: 50%;">
								<fieldset>
									<legend>RESTRICCION SUB AREA TEMATICA</legend>
									<table style="width: 100%;">
										<tr>
											<td style=" text-align: right; width: 50%;"><label>Area Tematica:</label>
											</td>
											<td style=" text-align: left; width: 50%;"><div id="div_cbxAreaTematica">
													<select name="cbxAreaTematica" id="cbxAreaTematica">
														<option value="0">----Seleccionar----</option>
													</select>
												</div>
											</td>
										</tr>
										<tr>
											<td style=" text-align: right; width: 50%;"><label>Sub Area Tematica:</label>
											</td>
											<td style=" text-align: left; width: 50%;"><div id="div_cbxSubAreaTematica">
													<select name="cbxSubAreaTematica" id="cbxSubAreaTematica">
														<option value="0">----Seleccionar----</option>
													</select>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2" style=" text-align: right; width: 100%;"><input type="button"
												name="btnAgregarSubAreaTematica"
												id="btnAgregarSubAreaTematica"
												value="Agregar Sub Area Tematica"
												onclick="onclick_btnAgregarSubAreaTematica();" />
											</td>
										</tr>
										<tr>
											<td height="63" colspan="2" style="width: 100%;">

												<table class="table-clasico" style="width: 100%;">
													<caption style="text-align: center;">
														<label>Lista de R. Sub Area Tematica</label>
													</caption>
													<thead>
														<tr>
															<th style="width: 10%;"><label>nro</label>
															</th>
															<th style="width: 30%;"><label>Area&nbsp;Tematica </label>
															</th>
															<th style="width: 50%;"><label>Sub&nbsp;Area&nbsp;Tematica</label>
															</th>
															<th style="width: 10%;"><label>Eliminar</label>�</th>
														</tr>
													</thead>
													<tbody id="tbodyAreaTematica">
													</tbody>
												</table></td>
										</tr>
									</table>
								</fieldset></td>
													
<td style="vertical-align: top; width: 100%;">
								<fieldset>
									<legend>RESTRICCION UBICACION </legend>
									<table style="width: 100%;">
										<tr>
											<td style="width: 25%; text-align: right;"><label>Departamento:</label>
											</td>
											<td style="width: 25%; text-align: left;" >
												<select name="departamento" id="departamento"  style="width: 100%" onchange="javascript:fCargarProvincia();">
											        <option value="0"><c:out value="-- Seleccionar --" /></option>
											        <c:forEach items="${listDepartamento}" var="modali">
											            <option value="${modali.depProvDistID}">
											            <c:out value="${modali.descripcion}" />
											            </option>
											        </c:forEach>
											    </select>
											</td>
										</tr>
										<tr>
											<td style="width: 25%; text-align: right;" ><label>Provincia:</label>
											</td>
											<td  style="width: 25%; text-align: left;" style="width: 100%">
											<select name="provincia" id="provincia" style="width: 100%" onchange="javascript:fCargarDistrito();">
												<option value="0">----Seleccionar----</option>
											</select>
											</td>
										</tr>
										<tr>
											<td  style="width: 25%; text-align: right;" style="width: 100%"><label>Distrito:</label>
											</td>
											<td style="width: 25%; text-align: left;" >
													<select name="distrito" id="distrito" style="width: 100%">
														<option value="0">----Seleccionar----</option>
													</select>
											</td>

										</tr>
										<tr>
											<td colspan="2"  style="width: 25%; text-align: right;" ><input type="button"
												name="btnAgregarUbicacion" id="btnAgregarUbicacion"
												value="Agregar ubicaci&oacute;n"
												onclick="onclick_btnAgregarUbicacion();" />
											</td>
										</tr>
										<tr>
											<td height="63" colspan="2"  style="width: 100%;" >

												<table class="table-clasico" style="width: 100%;">
													<caption style="text-align: center;">
														<label>Lista de R. Ubicaci&oacute;n</label>
													</caption>
													<thead>
														<tr>
															<th><label>nro</label>
															</th>
															<th><label>Dep.</label>
															</th>
															<th><label>Prov.</label>
															</th>
															<th><label>Dist.</label>
															</th>
															<th><label>Eliminar</label>�</th>
														</tr>
													</thead>
													<tbody id="tbodyUbiProy">
													</tbody>
												</table></td>
										</tr>
									</table>
								</fieldset></td>
						</tr>
							</table>
					</div>
					<div id="archivo">
					<br/>
					<fieldset>
						<table width="100%">
							<tr>
								<td  style="width: 25%; text-align: right;" ><label>Archivo:</label>
								</td>
								<td  style="width: 75%; text-align: left;" >
								<div>
							<spring:bind path="imagenODocumento">
											<input type="file" style="width: 220px;" name="imagenODocumento" id="imagenODocumento" />
										</spring:bind>
										</div>
										<input type="hidden" id="nombreDocumentoAdjunto" name="nombreDocumentoAdjunto">
										<input type="hidden" id="nombreArchivo" name="nombreArchivo">
										<input type="hidden" id="extension" name="extension">
							</td>
							</tr>
							<tr>
								<td  style="width: 25%; text-align: right;" ><label>Archivo:</label>
								</td>
								<td  style="width: 75%; text-align: left;" ><iframe src="showImagenArchivoDownloadPrograma.jspx"
										id="window" frameborder="0" height="40px" width="100%"
										scrolling="no"></iframe></td>
							</tr>
						</table>
					</fieldset>
					</div>
					<div id="proyectos">
					<br/>
					<fieldset>
						<display:table uid="proy" name="${listDatoProyecto}"
							defaultsort="1" defaultorder="ascending" pagesize="7"
							requestURI="" class="table-clasico"
							style="width:100%; font-size: 12px;">
							<display:caption>
								<label>Lista de proyectos aprobados del programa seleccionado</label>
							</display:caption>
							<display:column property="nombreProyecto" title="Nombre"
								sortable="true" />
							<display:column property="duracionProyecto"
								title="Duraci&oacute;n" sortable="true" />
							<display:column property="codigoProyecto" title="Codig&oacute;"
								sortable="true" />
							<display:column property="cantidadPeriodo"
								title="Cantidad Periodo " sortable="true" />
							<display:column property="subAreaTematica.descripcionSubAt"
								title="subArea Tematica" sortable="true" />
							<display:column property="subAreaTematica.descripcionSubNivel"
								title="SubNivel" sortable="true" />

						</display:table>
					</fieldset>
					</div>
					</div>
					</div>
					<div style="width: 100%; text-align: right;">
					<br />
					<c:if test="${variable == 'actualizar'}">
						<input type="button" value="Modificar Datos Programa"
							name="modificarDatosPrograma" onclick="fModificarDatosPrograma()" />
					</c:if>
					<c:if test="${variable != 'actualizar'}">
						<input type="button" value="Registrar Datos Programa"
							name="registrarDatosPrograma" onclick="fRegistrarDatosPrograma()" />
					</c:if>
					<input type="button" value="Cancelar" onclick="fCancelarPrograma()" />
					</div>
					
				</div>
			</fieldset>
		</div>
		<!-- Fin del tag crearPrograma -->

		<div id="listarProgramas">
			<!-- Inicio del tag listarPrograma -->
			<fieldset>
				<legend>PROGRAMAS</legend>
				<table style="width: 100%;">
					<tr>
						<td><label>Filtro:</label></td>
						<td><select name="idFiltro" id="idFiltro"
							onchange="fMostrarSelect(this.value)">
								<option value="0">
									<c:out value="--Seleccionar--" />
								<option value="100">
									<c:out value="Tipo Periodo" />
								</option>
								<option value="101">
									<c:out value="Tipo Cuenta" />
								</option>
								<option value="102">
									<c:out value="Modalidad" />
								</option>
								<option value="103">
									<c:out value="Nombre Programa" />
								</option>
								<option value="104">
									<c:out value="Todos" />
								</option>
						</select>
						</td>

						<td>
							<div id="divSelect">
								<select name="sele" disabled="disabled">
									<option value="0">
										<c:out value="--Seleccionar--" />
									</option>
								</select>
							</div>

							<div id="divNombre" style="display: none;">
								<label>Nombre:</label><input type="text" name="buscarNombre" />
							</div>
							<div id="divlistTipoPeriodos" style="display: none;">
								<select name="idTipoPeriodo" id="idTipoPeriodo">
									<option value="0">
										<c:out value="--Seleccionar--" />
									</option>
									<c:forEach items="${listTipoPeriodos}" var="tipoPeriodo">
										<option value="${tipoPeriodo.tipoPeriodoID}">
											<c:out value="${tipoPeriodo.descripPeriodo}" />
										</option>
									</c:forEach>
								</select>
							</div>
							<div id="divlistTipoCuentas" style="display: none;">
								<select name="idTipoCuenta" id="idTipoCuenta">
									<option value="0">
										<c:out value="-- Seleccionar --" />
									</option>
									<c:forEach items="${listTipoCuentas}" var="tipoCuenta">
										<option value="${tipoCuenta.tablaEspecificaID}">
											<c:out value="${tipoCuenta.descripcionCabecera}" />
										</option>
									</c:forEach>
								</select>
							</div>
							<div id="divlistModalidadFinanciera" style="display: none;">
								<select name="idModFinanciera" id="idModFinanciera">
									<option value="0">
										<c:out value="-- Seleccionar --" />
									</option>
									<c:forEach items="${listModalidadFinanciera}" var="modali">
										<option value="${modali.tablaEspecificaID}">
											<c:out value="${modali.descripcionCabecera}" />
										</option>
									</c:forEach>
								</select>
							</div></td>
						<td><input value="Buscar Programa" type="button"
							onclick="fBuscarPrograma()" />
						</td>
						<td><input value="Crear Programa" type="button"
							onclick="fCrearPrograma()" />
						</td>
					</tr>
				</table>

				<display:table uid="programa" name="${listPrograma}" defaultsort="1"
					defaultorder="ascending" pagesize="20" requestURI=""
					class="table-clasico" style="width:100%; font-size: 12px;">
					<display:caption>
						<label>Lista de Programas</label>
					</display:caption>
					<display:column title="Id" sortable="true">
						<c:out value="${programa_rowNum}" />
					</display:column>
					<display:column property="nombrePrograma" title="Programa"
						sortable="true" />
					<display:column property="duracionPrograma"
						title="Duracion Programa" sortable="true" />
					<display:column property="fechaConvocatoria"
						title="Fecha Convocatoria" sortable="true" />
					<display:column property="identificadorModFinan"
						title="Identificador" sortable="true" />
					<display:column title="Eliminar">
						<a href="javascript:fEliminarPrograma('${programa.programaID}')"
							class="ui-icon ui-icon-circle-close" style="cursor: pointer"
							title="Eliminar"></a>
					</display:column>
					<display:column title="Modificar">
						<a
							href="javascript:fCargarModificarDatosPrograma('${programa.programaID}')"
							class="ui-icon ui-icon-circle-arrow-s" style="cursor: pointer"
							title="Modificar"></a>
					</display:column>
				</display:table>
			</fieldset>
		</div>
		<input type="hidden" id="txtListadoSubAreaTematica"
			name="txtListadoSubAreaTematica" />
		<input type="hidden" id="txtListadoUbicacionBeneficiarios"
			name="txtListadoUbicacionBeneficiarios"/>
		<input type="hidden" id="nombreArchivo" name="nombreArchivo"
			/>
		<input type="hidden" id="extension" name="extension"
			/>
			<input type="hidden" id="mensaje"
			name="mensaje" value="${mensaje}" />
	</form:form>