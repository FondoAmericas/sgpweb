	
	
	var TEXTBOX_TYPE  = "text";
	var COMBOBOX_TYPE = "select";
	var COMBOBOX_VALORINICIAL = "0";
	var VACIO = "";
	


function AtssefValidadorForm(jsonArray){
	
	var VALOR_CERO = 0;
	var VALOR_UNO = 1;
	var VALOR_VACIO = "";
	
	var jsonArrayCampos = jsonArray;
	
	this.validar = m_validar;
	
	function m_validar(){
	       
			for(var i=0; i<jsonArrayCampos.length; i++){
				console.log("AtssefValidadorForm :::::::::::::::");
				//if(jsonArrayCampos[i].valorInicial == VALOR_VACIO){
						if(document.getElementById(jsonArrayCampos[i].id).value == jsonArrayCampos[i].valorInicial){
									alert(jsonArrayCampos[i].msj);
									document.getElementById(jsonArrayCampos[i].id).focus();
									return false;
						}						
				//}else if(jsonArrayCampos[i].valorInicial == VALOR_CERO){
				/*		if(document.getElementById(jsonArrayCampos[i].id).value == jsonArrayCampos[i].valorInicial){
									alert(jsonArrayCampos[i].msj);
									document.getElementById(jsonArrayCampos[i].id).focus();
									return false;
						}*/
				//}
			}
			return true;
	}
	
}


/*

function validarCamposForm(){
		var objFildsForm = new AtssefValidadorForm([
		  {tipo:TEXTBOX_TYPE, id:"txtNombre", msj:"Ingresar nombre!"},
		  {tipo:TEXTBOX_TYPE, id:"txtApellido", msj:"Ingresar apellido!"},
		  {tipo:COMBOBOX_TYPE, id:"cbxTipoPersona", msj:"Seleccionar tipo persona!"} 
		]);		
		return objFildsForm.validar();
	}
	
	function registrarFormulario(){
	   if(validarCamposForm()){
			alert("SE REGISTRARON LOS DATOS!!!");
	   }else{
			//alert("FALTAN DATOS!!!");
	   }
	}


*/