 
  $(document).ready(function() {
        $('#idBtnCerrar').click(function(){
           window.close();
         });
  } );
  
 function fOpenModalDialog(url, width, height, top, left) {
	var stiloPopUp='dialogWidth='+width+'px; dialogHeight='+height+'px; dialogTop='+top+'px; dialogLeft='+left+'px; location=no; addressbar:no; toolbar=no; menubar=no; status=no; scrollbars=yes; resizable=no;';
	window.showModalDialog(url,'',stiloPopUp);
  }
 
  function fReFreshOpenerLocation(){
	  window.opener.location.reload(true);
  }
  
  function fReFreshLocation(){
	  window.location.reload(true);
  }
  
  function fSetTimeOutRefreshLocation(tiempo){
	  setTimeout("fReFreshLocation()",1000);
  }
  
  function fSetIntervalRefreshLocation(tiempo){
	  window.setInterval("fReFreshLocation()",tiempo);
  }
  
  function expandcollapse(obj,row)
  {
      var div = document.getElementById(obj);
      var img = document.getElementById('img' + obj);
      
      if (div.style.display == "none")
      {
          div.style.display = "block";
          //alert("Abierto")
          if (row == 'alt')
          {
              img.src = "../images/Minus001.gif";
          }
          else
          {
              img.src = "../images/Minus001.gif";
          }
          //img.alt = "Cierre para ver otro ";
      }
      else
      {
          div.style.display = "none";
          //alert("Cerrado")
          if (row == 'alt')
          {
              img.src = "../images/Plus001.gif";
          }
          else
          {
              img.src = "../images/Plus001.gif";
          }
          //img.alt = "Expand to show Orders";
      }
  }
  
  //PARA MARCAR TODOS LOS CHECKS
  function marcarTodos(formulario){
 	 var form = document.getElementById(formulario);
 	 if(form.chkregistro != undefined){
 	    if(form.chkregistro.length != undefined){
 		   for(var i = 0; i<form.chkregistro.length; i++){
 		       form.chkregistro[i].checked = form.chkregistroTodo.checked;
 		   }
 		}else{
 		       form.chkregistro.checked = form.chkregistroTodo.checked;
 		}
 	 }
   }
  
   //PARA MARCAR LOS CHECKS
   function marcar(formulario){
 	 var form = document.getElementById(formulario);
 	 var cont = 0;
 	 if(form.chkregistro != undefined){
 		if(form.chkregistro.length != undefined){
 		   for(var i = 0; i<form.chkregistro.length; i++){
 			   if(form.chkregistro[i].checked){
 						cont++;
 			   }
 		   }
 		   if(cont == form.chkregistro.length){
 			  form.chkregistroTodo.checked = true;				
 		   }else if(cont == 0){
 			  form.chkregistroTodo.checked = false;
 		   }else{
 			  form.chkregistroTodo.checked = false;
 		   }
 		}else{
 		   form.chkregistroTodo.checked = form.chkregistro.checked;
 		}
 	 }

  }
  
  function deshabilitarCampo(id){ 
	  	console.log("deshabilitarCampo :: "+ id);
		document.getElementById(id).disabled=true;
		return false; 
  }  
  
  
  String.prototype.trim = function() { return this.replace(/^\s+|\s+$/g, ""); };
  