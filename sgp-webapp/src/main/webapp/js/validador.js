// Archivo JScript
    function LTrim(s){
        var i=0;
        var j=0;
        for(i=0; i<=s.length-1; i++)
               if(s.substring(i,i+1) != ' '){
                       j=i;
                       break;
               }
        return s.substring(j, s.length);
    }
    
    function RTrim(s){
        var j=0;
        for(var i=s.length-1; i>-1; i--)
               if(s.substring(i,i+1) != ' '){
                       j=i;
                       break;
               }
        return s.substring(0, j+1);
    }
    
    function Trim(s){
        return LTrim(RTrim(s));
    }
    
    function isContrasena(str)
	{
		for(var i=0; i<str.length; i++ )
		{
		  if ( !isValidChr(str.charAt(i)))
			return false;
		}
			return true;
	}
	
	function isValidChr(c) 
	{
		validChrs = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
		for (var j=0; j<validChrs.length; j++)
		{
			if (c==validChrs.charAt(j))
				return true;
		}
				return false;
	}
	
	function validacorreo(cadena){ 
        var x; 
        var cuentaarroba = 0; 
        var cuentaPunto = 0; 
        for (x = 1; x <= cadena.length; x++) { 
            if (cadena.substring(x,x-1) == "@") { 
                cuentaarroba = cuentaarroba + 1; 
            } 
            if (cadena.substring(x,x-1) == ".") { 
                cuentaPunto = cuentaPunto + 1; 
            } 
        } 
        if (cuentaarroba != 1 | cuentaPunto > 4 | cuentaPunto < 1) { 
            return false;
        } 
        return true;
        
     } 
     
     
    function solonumeros(e){
	    var target = (e.target ? e.target : e.srcElement);
	    var key = (e ? e.keyCode || e.which : window.event.keyCode);
	    return ((key>=48 && key <=57));
    }
    
    function numerosLetras(e){
	    var target = (e.target ? e.target : e.srcElement);
	    var key = (e ? e.keyCode || e.which : window.event.keyCode);
	    return ((key>=48 && key <=57)||(key>=65 && key <=90)||(key>=97 && key <=122));
    }
    
    function vacioSinChars(e){
	    var target = (e.target ? e.target : e.srcElement);
	    var key = (e ? e.keyCode || e.which : window.event.keyCode);
	    return ((key>=1 && key <=1));
    }
    
    function solonumerosConChar(e){
	    var target = (e.target ? e.target : e.srcElement);
	    var key = (e ? e.keyCode || e.which : window.event.keyCode);
	    return ((key>=48 && key <=57)||(key == 42));
    }

    function solonumerosConPunto(e){
	    var target = (e.target ? e.target : e.srcElement);
	    var key = (e ? e.keyCode || e.which : window.event.keyCode);
	    return ((key>=48 && key <=57)||(key == 46));
    }


    function fechaSinBackSpace(e) 
	{
	    var target = (e.target ? e.target : e.srcElement);
	    var key = (e ? e.keyCode || e.which : window.event.keyCode);
	    return ((key >= 1 && key <= 1));

	    switch (e.keyCode) 
	    {
            case 08: // BackSpace
                event.keyCode = 0;
                return false;
             case 127: // BackSpace
                event.keyCode = 0;
                return false;
             default:
                break;   
            }
	}
    
    
    function desactivaTecla(){
        //document.onkeydown = function(){
        //alert(window.event.keyCode)
        if(window.event.keyCode == 112){
        //tuFuncionDeAyuda()
        return false;
        }
        if(window.event && (window.event.keyCode == 116 || window.event.keyCode == 122 || window.event.keyCode == 8 || window.event.keyCode == 112)){
            return false;
        }
        //}
    }
    
   function ValidarCaracteres(textareaControl,maxlength)
    {                                                                             
        if (textareaControl.value.length > maxlength){
            textareaControl.value = textareaControl.value.substring(0,maxlength-1);
            alert("Debe ingresar hasta un maximo de "+maxlength+" caracteres");
        }
    }
    
    function val(e) 
    {
        tecla = (document.all) ? e.keyCode : e.which;
        if (tecla==8) return true;
        if (tecla==32) return true; //Tecla de espacio
        patron =/[A-Za-zÑñ,.()ÁÉÍÓÚáéíóú]/; //caracteres
        //patron = /[a-zA-ZÑñ,.()-ÁÉÍÓÚáéíóú]/; //caracteres y numeros
        te = String.fromCharCode(tecla);
        return patron.test(te);
    }
    
    function Valida_Dato(e,opc)
    {
        //Segun la opcion se habilita o no el espacio
        tecla = (document.all) ? e.keyCode : e.which;
        if (tecla==8 || tecla==0) return true; //Tecla de retroceso (para poder borrar)
        switch(opc)
        {
        case 1://Para numeros enteros en general. Solicitudes, cedulas,nit, numeros de identificacion, etc
        patron = /[1234567890-]/; //solo numeros y lineas
        break;

        case 2://Para texto largo general
        if (tecla==32) return true; //Tecla de espacio
        patron = /[a-zA-ZÑñ1234567890,.()-ÁÉÍÓÚáéíóú]/; //caracteres y numeros
        break;

        case 3://Para numeros telefonicos
        if (tecla==32) return true; //Tecla de espacio
        patron = /[EXText1234567890]/;
        break;

        case 4://para campos que necesitan valores numericos de precios
        patron = /[1234567890.,]/; //solo numeros puntos y comas
        break;

        case 5://Para validar campos de fecha
        patron = /[1234567890-]/; //solo numeros
        break;

        case 6://Para validar campos de nombre
        if (tecla==32) return true; //Tecla de espacio
        patron = /[a-zA-ZÑñáéíóú]/;
        break;

        case 7://Para validar email
        patron = /[a-zA-ZÑñ1234567890@.-_]/;
        break;

        case 8://Para números enteros positivos
        patron = /[1234567890]/; //solo numeros enteros positivos
        break;

        case 9://Direcciones
        if (tecla==32) return true; //Tecla de espacio
        patron = /[a-zA-ZÑñ1234567890.()$,-]/;
        break;

        case 10://Caracteres y números
        if (tecla==32) return true;
        patron = /[a-zA-ZÑñ1234567890]/;
        break;
        }
        te = String.fromCharCode(tecla);
        return patron.test(te);
    }
    function isNumberKeyCP(evt){ //numero con comas y punto
        var charCode = (evt.which) ? evt.which : event.keyCode;
        
        if(charCode == 44)
        	return true;
        
        if (charCode > 31 &&  (  (charCode < 46 || charCode > 57) || charCode == 47 ))   	
           return false;
        return true;
    }
    function isNumberKeyP(evt){ //numero con punto
        var charCode = (evt.which) ? evt.which : event.keyCode;
        
        if (charCode > 31 &&  (  (charCode < 46 || charCode > 57) || charCode == 47 ))   	
           return false;
        return true;
    }
    
    function isNumberKeyPBD(evt){ //numero con punto, backspace y delete
        var charCode = (evt.which) ? evt.which : event.keyCode;
        
        if ((charCode == 8) || (charCode == 127) || (charCode > 23 && charCode < 28 ) || (charCode > 31 &&  ((charCode < 46 || charCode > 57) || charCode == 47)))   	
           return false;
        return true;
    }
    