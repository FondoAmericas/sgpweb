
// AtssefGrid :: Grilla Dinamica :: version 1.2

function AtssefGrid(jsonInicio){
	console.log("AtssefGrid version 1.0 " + " RENDERIZANDO " + jsonInicio.id_contenedor);
	
	//ATRIBUTOS
	var numeracion=0;
	/*variables estilo de la tabla*/
	var colorFilaImpar=jsonInicio.estiloTabla[0].color.filaImpar;
	var colorFilaPar =jsonInicio.estiloTabla[0].color.filaPar;
	var colorFilaOnMouseOver =jsonInicio.estiloTabla[0].color.filaOver;
	var colorFilaSeleccionar=jsonInicio.estiloTabla[0].color.filaSel;
	var idSeleccionado="null_fila_null";
	var idSeleccionadoAnte="null_fila_null";
	//indice para los campos extra:
	var indiceArray=null;
	//cantidad de columnas de la tabla
	var cantColTabla=0;
	/*fin variables estilo de la tabla*/
	
	//DECLARACION DE METODOS
	this.datos=m_datos;
	this.init=m_init();//cuando se pone asi "m_init()" el metodo se ejecuta automaticamente al inicializar 
	
	this.encima=m_encima;
	this.fuera=m_fuera;
	this.seleccionar=m_seleccionar;
	this.getIdSeleccionado=m_getIdSeleccionado;
	this.getObjSeleccionado=m_getObjSeleccionado;
	
	//indice para los campos extra:
	this.getIndiceArray=m_getIndiceArray;
	//this.m_obtenerNumColumnasAA=m_obtenerNumColumnas;
	
	//METODOS
	function m_datos(){
      alert(JSON.stringify(jsonInicio));
	}
	
	function m_getObjSeleccionado(){
		var objSel = jsonInicio.arrayDataTabla[m_getIndiceArray()];
		console.log("AtssefGrid / metodo : getObjSeleccionado(): "+ JSON.stringify(objSel));
		return JSON.stringify(objSel);	
	}
	
	function m_getIndiceArray(){
	    return indiceArray;
	}

	function m_objetoToArray(objeto){
		var arrayObjeto=new Array();
		for (name in objeto){     
			//alert(name);     
			//alert(objeto[name]); 
			if(m_compruebaCampoCabecera(name)){
			  arrayObjeto.push(name);
			  arrayObjeto.push(objeto[name]);
			}
		} 
		//alert(JSON.stringify(arrayObjeto));
		var arrayObjetoOrdenado=new Array();
		for(i=0; i<jsonInicio.campos_cabecera.length; i++){
		     for(j=0; j<arrayObjeto.length; j++){
			      if(jsonInicio.campos_cabecera[i].name==arrayObjeto[j]){
				       arrayObjetoOrdenado.push(arrayObjeto[j]);
					   arrayObjetoOrdenado.push(arrayObjeto[j+1]);
				  }
			 }
		}
		//alert(JSON.stringify(arrayObjetoOrdenado))
		return arrayObjetoOrdenado;
    }
	
	function m_compruebaCampoCabecera(name1){
	     for(var i=0; i<jsonInicio.campos_cabecera.length; i++){
		     if(jsonInicio.campos_cabecera[i].name==name1){
			     return true;
			 }
		 }
		 return false;
	}
	
	/* metodos para el estilo de la tabla*/
	function m_encima(idFila) {
		if(idFila!=idSeleccionado)
			document.getElementById(idFila).style.background = colorFilaOnMouseOver;
	} 

	function m_fuera(idFila) {
		if(idFila!=idSeleccionado){
			var num=obtenerNumId(idFila);
			if (num % 2 == 1){//impares
				document.getElementById(idFila).style.background=colorFilaImpar;
			}else{
				document.getElementById(idFila).style.background=colorFilaPar;
			}
		}else{
			document.getElementById(idSeleccionado).style.background=colorFilaSeleccionar;
		}
	} 

	function m_seleccionar(idFila){
	    alert("seleccionar");
		idSeleccionadoAnte=idSeleccionado;
		idSeleccionado=idFila;
		document.getElementById(idFila).style.background = colorFilaSeleccionar;
		if(jsonInicio.estiloTabla[0].tablaCebra=="si"){
			var num=obtenerNumId(idSeleccionadoAnte);
			if (num % 2 == 1){//impares
			   if(idSeleccionadoAnte!="null_fila_null" && idSeleccionadoAnte!=idSeleccionado)
				document.getElementById(idSeleccionadoAnte).style.background=colorFilaImpar;
			}else{
			   if(idSeleccionadoAnte!="null_fila_null" && idSeleccionadoAnte!=idSeleccionado)
				document.getElementById(idSeleccionadoAnte).style.background=colorFilaPar;
			}
		}else{
		    if(idSeleccionadoAnte!="null_fila_null" && idSeleccionadoAnte!=idSeleccionado)
		    document.getElementById(idSeleccionadoAnte).style.background="none";
		}
	}

	function obtenerNumId(idFila){
		var vectorIdFila = idFila.split('_'); //La cadena de la ruta se convierte en vector.
		return vectorIdFila[2];
	}
	
	function m_getIdSeleccionado(){
       return idSeleccionado;
	   
	}
    /* fin metodos para el estilo de la tabla*/
	
	function m_init(){
	 var table = document.createElement("table");
	     table.setAttribute("id",jsonInicio.objTabla+"_tabla");
	    //ATRIBUTOS
		table.setAttribute("align",jsonInicio.atributos_tabla.align);
		table.setAttribute("width",jsonInicio.atributos_tabla.width);
		table.setAttribute("class",jsonInicio.atributos_tabla.class);
		//CAPTION
		if(jsonInicio.titulo_tabla.estado=="si"){
			var textCaption=document.createTextNode(jsonInicio.titulo_tabla.titulo);
			var caption=document.createElement("caption");
			var label=document.createElement("label");
			caption.appendChild(label);
			label.appendChild(textCaption);
			table.appendChild(caption);
		}	
		//THEAD
		    var thead = document.createElement("thead");
			    var tr = document.createElement("tr");
				//campo de numeracion
				if(jsonInicio.numeracion=="si"){
					var th1 = document.createElement("th");
					var textTh1 = document.createTextNode("nro");
					var label = document.createElement("label");
					label.appendChild(textTh1);
					th1.appendChild(label);
					tr.appendChild(th1);
					cantColTabla++;
				}
				//fin campo de numeracion
				
				for(var i=0; i<jsonInicio.cabecera.length; i++){
					var th = document.createElement("th");
					var textTh = document.createTextNode(jsonInicio.cabecera[i]);
					var label = document.createElement("label");
					label.appendChild(textTh);
					th.appendChild(label);
					tr.appendChild(th);
					cantColTabla++;
				}
				//campos de operaciones
				
				//campos extra de la tabla
				if(jsonInicio.campos_extra_tabla.detalle.estado=="si"){
					var th11 = document.createElement("th");
					var textTh11 = document.createTextNode(jsonInicio.campos_extra_tabla.detalle.titulo);
					var label = document.createElement("label");
					label.appendChild(textTh11);
					th11.appendChild(label);
					tr.appendChild(th11);
					cantColTabla++;
				}
				
				if(jsonInicio.campos_extra_tabla.eliminarFila.estado=="si"){
					var th12 = document.createElement("th");
					//var textTh12 = document.createTextNode("&nbsp;");
					var label = document.createElement("label");
					var span=document.createElement("span");
					span.appendChild(espacioVacio());
					label.appendChild(span);
					th12.appendChild(label);
					tr.appendChild(th12);
					cantColTabla++;
				}
				//campo extra solo para solicitarDesembolso.jsp (objGRIDCronogramaCostoActividad)
				if(jsonInicio.campos_extra_tabla.hasOwnProperty('detalleDinamico')){//se ejecuta solo si existe esta propiedad
					if(jsonInicio.campos_extra_tabla.detalleDinamico.estado=="si"){
						var th13 = document.createElement("th");
						var textTh13 = document.createTextNode(jsonInicio.campos_extra_tabla.detalleDinamico.titulo);
						var label = document.createElement("label");
						var span=document.createElement("span");
						//span.appendChild(espacioVacio());
						label.appendChild(textTh13);
						th13.appendChild(label);
						tr.appendChild(th13);
						cantColTabla++;
					}
				}	
				
				//campo extra solo para autorizarDesembolso.jsp (objGRIDSolicitudesDesembolso)
				if(jsonInicio.campos_extra_tabla.hasOwnProperty('detalleDinamicoEstDesembolso')){//se ejecuta solo si existe esta propiedad
					if(jsonInicio.campos_extra_tabla.detalleDinamicoEstDesembolso.estado=="si"){
						var th13 = document.createElement("th");
						var textTh13 = document.createTextNode(jsonInicio.campos_extra_tabla.detalleDinamicoEstDesembolso.titulo);
						var label = document.createElement("label");
						var span=document.createElement("span");
						//span.appendChild(espacioVacio());
						label.appendChild(textTh13);
						th13.appendChild(label);
						tr.appendChild(th13);
						cantColTabla++;
					}
				}
				
				//campo extra solo para Proyecto.jsp (tablaCuentaCorriente)
				if(jsonInicio.campos_extra_tabla.hasOwnProperty('detalleFlagReferencia')){//se ejecuta solo si existe esta propiedad
					if(jsonInicio.campos_extra_tabla.detalleFlagReferencia.estado=="si"){
						var th13 = document.createElement("th");
						var textTh13 = document.createTextNode(jsonInicio.campos_extra_tabla.detalleFlagReferencia.titulo);
						var label = document.createElement("label");
						var span=document.createElement("span");
						//span.appendChild(espacioVacio());
						label.appendChild(textTh13);
						th13.appendChild(label);
						tr.appendChild(th13);
						cantColTabla++;
					}
				}
				
				//fin campos extra de la tabla
				thead.appendChild(tr);
		//TBODY
			var tbody = document.createElement("tbody");	
            var indiceCSScebra=1;
			
				/*paginacion*/
				
				/*fin paginacion*/
				
				for(var i=0; i<jsonInicio.arrayDataTabla.length; i++){//for(var i=0; i<5; i++){//
				    var tr_body = document.createElement("tr");
					var textTd;
					
					var obj=jsonInicio.arrayDataTabla[i];
					var ArrayObjeto=m_objetoToArray(obj);
					//alert(ArrayObjeto.join("-----"));
					
					numeracion=numeracion+1;//importante para generar los id, de las filas
					
					//campo de numeracion
					if(jsonInicio.numeracion=="si"){
						var td1 = document.createElement("td");
						td1.setAttribute("align","center");
						//numeracion=numeracion+1;
						var textTd1 = document.createTextNode(numeracion);
						td1.appendChild(textTd1);
						tr_body.appendChild(td1);
					}
					//fin campo de numeracion
					
					var inc=0;
					for(var j=0; j<jsonInicio.campos_cabecera.length; j++){
							var td = document.createElement("td");		    
						   if(j==0){
							  textTd = document.createTextNode(ArrayObjeto[j+1]);
							  td.appendChild(textTd);
						   }else{
							  textTd = document.createTextNode(ArrayObjeto[j+2+inc]);
							  td.appendChild(textTd);
							  inc++;
						   }		 
                        tr_body.appendChild(td);
					}
					//campos extra de la tabla
					if(jsonInicio.campos_extra_tabla.detalle.estado=="si"){
					   var link = document.createElement("a");
					   link.setAttribute("id",numeracion);
				       link.setAttribute("class","link");
					   link.onclick=function(){
					    indiceArray=((this.id)-1);
					    eval(jsonInicio.campos_extra_tabla.detalle.funcionDetalle);
					   };
					   var text=document.createTextNode(jsonInicio.campos_extra_tabla.detalle.textoCampo);
				       link.appendChild(text);
					   var div = document.createElement("div");
				       div.setAttribute("align","center");
				       div.appendChild(link);
					   var td = document.createElement("td");
					   td.appendChild(div);
					   tr_body.appendChild(td);
					}
					
					if(jsonInicio.campos_extra_tabla.eliminarFila.estado=="si"){
					   var imagen = document.createElement("img");
					   //imagen.setAttribute("src","../../../../images/del.png"); ${pageContext.request.contextPath}/images/del.png
					   imagen.setAttribute("src","../images/del.png"); //${pageContext.request.contextPath}/images/del.png
				       imagen.setAttribute("id",numeracion);
				       imagen.onclick=function(){
					   indiceArray=((this.id)-1);
					   eval(jsonInicio.campos_extra_tabla.eliminarFila.funcionEliminarFila);
					   };	   
					   var div = document.createElement("div");
				       div.setAttribute("align","center");
				       div.appendChild(imagen);
					   var td = document.createElement("td");
					   td.appendChild(div);
					   tr_body.appendChild(td);
					}
					
					//campo extra solo para solicitarDesembolso.jsp (objGRIDCronogramaCostoActividad)
					if(jsonInicio.campos_extra_tabla.hasOwnProperty('detalleDinamico')){//se ejecuta solo si existe esta propiedad 
						if(jsonInicio.campos_extra_tabla.detalleDinamico.estado=="si"){
							   var strike = document.createElement("strike");
							   var link = document.createElement("a");
							   link.setAttribute("id",numeracion);
							   var text=document.createTextNode(jsonInicio.campos_extra_tabla.detalleDinamico.textoCampo);
							   link.setAttribute("class","link");
							   if(jsonInicio.arrayDataTabla[i].estadoLiquidacion == "0"){ //no liquidado
						    	   link.onclick=function(){
								   indiceArray=((this.id)-1);
								   eval(jsonInicio.campos_extra_tabla.detalleDinamico.funcionDetalleDinamico);
							       };							       
							       link.appendChild(text);							       
						       }else{
							       link.appendChild(text);
							       strike.appendChild(link);
						       }							   
							   var div = document.createElement("div");
						       div.setAttribute("align","center");						       
						       div.appendChild( (jsonInicio.arrayDataTabla[i].estadoLiquidacion == "0") ? link : strike  );						       
							   var td = document.createElement("td");
							   td.appendChild(div);
							   tr_body.appendChild(td);							
						}
					}			
					
					//campo extra solo para autorizarDesembolso.jsp (objGRIDSolicitudesDesembolso)
					if(jsonInicio.campos_extra_tabla.hasOwnProperty('detalleDinamicoEstDesembolso')){//se ejecuta solo si existe esta propiedad 
						var POR_EVALUAR = "22", APROBADO = "23", RECHAZADO = "24";
						if(jsonInicio.campos_extra_tabla.detalleDinamicoEstDesembolso.estado=="si"){
							   console.log("detalleDinamicoEstDesembolso --------");						 						   
							   var text=document.createTextNode(jsonInicio.arrayDataTabla[i].descEstadoDesembolso);
							   							   
							   if(jsonInicio.arrayDataTabla[i].idEstadoDesembolso == POR_EVALUAR){ 
								   var link = document.createElement("a");
								   link.setAttribute("id",numeracion);
								   link.setAttribute("class","link");
								   
						    	   link.onclick=function(){
								   indiceArray=((this.id)-1);
								   eval(jsonInicio.campos_extra_tabla.detalleDinamicoEstDesembolso.funcionDetalleDinamicoEstDesembolso);
							       };							       
							       link.appendChild(text);							       
						       }else{
						    	   var link2 = document.createElement("a");
								   link2.setAttribute("id",numeracion);
								   link2.setAttribute("class","link2");	       
							       link2.appendChild(text);						    	   
						       }
							   
							   var div = document.createElement("div");
						       div.setAttribute("align","center");						       
						       div.appendChild( jsonInicio.arrayDataTabla[i].idEstadoDesembolso == POR_EVALUAR ? link : link2  );						       
							   var td = document.createElement("td");
							   td.appendChild(div);
							   tr_body.appendChild(td);							
						}
					}	
					
					//campo extra solo para Proyecto.jsp (tablaCuentaCorriente)
					if(jsonInicio.campos_extra_tabla.hasOwnProperty('detalleFlagReferencia')){//se ejecuta solo si existe esta propiedad 
						if(jsonInicio.campos_extra_tabla.detalleFlagReferencia.estado=="si"){			 						   
							   var flagReferencia=jsonInicio.arrayDataTabla[i].flagReferencia;
							   var div = document.createElement("div");
						       div.setAttribute("align","center");		   
							   if(flagReferencia == true){ 
								   var link = document.createElement("a");
								   link.setAttribute("id",numeracion);
								   link.setAttribute("class","link");
								   text=document.createTextNode("--");										       
							       link.appendChild(text);	
							       div.appendChild(link);
						       }else{								       							       
							       var imagen = document.createElement("img");
								   imagen.setAttribute("src","../images/del.png"); 
							       imagen.setAttribute("id",numeracion);
							       imagen.onclick=function(){
								   indiceArray=((this.id)-1);
								   eval(jsonInicio.campos_extra_tabla.detalleFlagReferencia.funcionDetalleCuentasCorrientes);
								   };
								   div.appendChild(imagen);
						       }							   						       
							   var td = document.createElement("td");
							   td.appendChild(div);
							   tr_body.appendChild(td);							   						
						}
					}
					
										
					//fin campos extra de la tabla
					
					tr_body.setAttribute("id",jsonInicio.objTabla+"_fila_"+indiceCSScebra);
					//estilo CSS cebra para la tabla  
					if(jsonInicio.estiloTabla[0].tablaCebra=="si"){
					  if (indiceCSScebra % 2 == 1){//impares
					        tr_body.setAttribute("class","f1");
					  }else{
							tr_body.setAttribute("class","f2");
					  }
					} 
					  indiceCSScebra++;
					  
					if(jsonInicio.estiloTabla[1].funcion.apuntarOverOut=="si"){
						tr_body.setAttribute("onmouseover",jsonInicio.objTabla+".encima(this.id)");
					    tr_body.setAttribute("onmouseout",jsonInicio.objTabla+".fuera(this.id)");
						}
					if(jsonInicio.estiloTabla[1].funcion.sel=="si")
					    tr_body.setAttribute("onclick",jsonInicio.objTabla+".seleccionar(this.id)");

					//fin estilo CSS cebra para la tabla
					
				 tbody.appendChild(tr_body);				  
				}	
        //TFOOT
		if(jsonInicio.paginacionTabla.pagTabla == "si" || jsonInicio.gestionDatos.gestion == "si"){
		    var tfoot = document.createElement("tfoot");
			tfoot.setAttribute("id",jsonInicio.objTabla+"_tfoot");
			var colspan=7; //m_obtenerNumColumnas();
			//var tdFoot=armadoFilaPaginacion_GestionDatos(jsonInicio.objTabla,"si","si",6,5,"center");
			var tdFoot=armadoFilaPaginacion_GestionDatos(jsonInicio.objTabla, jsonInicio.paginacionTabla.pagTabla, jsonInicio.gestionDatos.gestion, jsonInicio.espacio_entre_paginacion_gestion, jsonInicio.alineacion_de_paginacion_gestion);
			var trFoot = document.createElement("tr");
			trFoot.appendChild(tdFoot);
			tfoot.appendChild(trFoot);
		}	
		//Armar tabla
		table.appendChild(thead);
		table.appendChild(tbody);
		if(jsonInicio.paginacionTabla.pagTabla == "si" || jsonInicio.gestionDatos.gestion == "si")
			table.appendChild(tfoot);
			
		//limpia contenedor
		document.getElementById(jsonInicio.id_contenedor).innerHTML="";
		//pinta tabla en el contenedor
		document.getElementById(jsonInicio.id_contenedor).appendChild(table);

	}
	
	function armadoFilaPaginacion_GestionDatos(nomObj,paginacion,gestionDatos,cantEspacios,alineacionTD_paginacion_gestionDatos){
	   /*
		var nomObj="grilla123";
		var colspan=2;//numero de columnas de la tabla
		var cantEspacios=30;
		var paginacion="no";
		var gestionDatos="no";
		var alineacionTD_paginacion_gestionDatos="center";
	   */
		if(paginacion=="si"){
			/*PAGINACION*/
				//numeracion inicio
				var spanNumInicio=document.createElement("span");
				spanNumInicio.setAttribute("id",nomObj+"_numeracionIni");
				var txtNumInicio=document.createTextNode("369");
				spanNumInicio.appendChild(txtNumInicio);
				//concatenador
				var txtConcat=document.createTextNode("de");
				//numeracion fin
				var spanNumFin=document.createElement("span");
				spanNumFin.setAttribute("id",nomObj+"_numeracionFin");
				var txtNumFin=document.createTextNode("500");
				spanNumFin.appendChild(txtNumFin);
				
				//boton inicio
				var btnInicio=document.createElement("input");
				btnInicio.setAttribute("type","button");
				btnInicio.setAttribute("id",nomObj+"_btnInicio");
				btnInicio.setAttribute("value","\\<");
				btnInicio.onclick=function(){m_paginacion_inicio();};
				//boton anterior
				var btnAnterior=document.createElement("input");
				btnAnterior.setAttribute("type","button");
				btnAnterior.setAttribute("id",nomObj+"_btnAnterior");
				btnAnterior.setAttribute("value","<");
				btnAnterior.onclick=function(){m_paginacion_anterior();};
				//boton siguiente
				var btnSiguiente=document.createElement("input");
				btnSiguiente.setAttribute("type","button");
				btnSiguiente.setAttribute("id",nomObj+"_btnSiguiente");
				btnSiguiente.setAttribute("value",">");
				btnSiguiente.onclick=function(){m_paginacion_siguiente();};
				//boton final
				var btnFinal=document.createElement("input");
				btnFinal.setAttribute("type","button");
				btnFinal.setAttribute("id",nomObj+"_btnFinal");
				btnFinal.setAttribute("value",">/");
				btnFinal.onclick=function(){m_paginacion_final();};
				
				//<span> paginacion (contadores de paginacion)
				var span_paginacion=document.createElement("span");
				span_paginacion.setAttribute("id",nomObj+"_span_paginacion");
				
				span_paginacion.appendChild(espacioVacio());
				span_paginacion.appendChild(spanNumInicio);
				span_paginacion.appendChild(espacioVacio());
				span_paginacion.appendChild(txtConcat);
				span_paginacion.appendChild(espacioVacio());
				span_paginacion.appendChild(spanNumFin);
				span_paginacion.appendChild(espacioVacio());
				  //botones de paginacion
				span_paginacion.appendChild(btnInicio);
				span_paginacion.appendChild(espacioVacio());
				span_paginacion.appendChild(btnAnterior);
				span_paginacion.appendChild(espacioVacio());
				span_paginacion.appendChild(btnSiguiente);
				span_paginacion.appendChild(espacioVacio());
				span_paginacion.appendChild(btnFinal);
				span_paginacion.appendChild(espacioVacio());
			/*FIN PAGINACION*/
		}	
		
		if(paginacion=="si" && gestionDatos=="si"){
			/*ESPACIOS*/
			//<span> espacios separacion (espacios)
				var span_espaciosSeparacion=document.createElement("span");
				span_espaciosSeparacion.setAttribute("id",nomObj+"_span_espaciosSeparacion");
				for(var i=0; i<cantEspacios; i++)
				   span_espaciosSeparacion.appendChild(espacioVacio());
			/*FIN ESPACIOS*/
		}
		
		if(gestionDatos=="si"){	
			/*GESTION DE DATOS*/
				//boton agregar
				var btnAgregar=document.createElement("input");
				btnAgregar.setAttribute("type","button");
				btnAgregar.setAttribute("id",nomObj+"_btnAgregar");
				btnAgregar.setAttribute("value","Agregar");
				btnAgregar.onclick=function(){m_agregarDatos();};
				//boton modificar
				var btnModificar=document.createElement("input");
				btnModificar.setAttribute("type","button");
				btnModificar.setAttribute("id",nomObj+"_btnModificar");
				btnModificar.setAttribute("value","Modificar");
				btnModificar.onclick=function(){m_modificarDatos();};
				//boton eliminar
				var btnEliminar=document.createElement("input");
				btnEliminar.setAttribute("type","button");
				btnEliminar.setAttribute("id",nomObj+"_btnEliminar");
				btnEliminar.setAttribute("value","Eliminar");
				btnEliminar.onclick=function(){m_eliminarDatos();};
				
				//<span> gestion de datos(agregar,modificar,eliminar)
				var span_gestionDatos=document.createElement("span");
				span_gestionDatos.setAttribute("id",nomObj+"_span_gestionDatos");
				
				span_gestionDatos.appendChild(espacioVacio());
				span_gestionDatos.appendChild(btnAgregar);
				span_gestionDatos.appendChild(espacioVacio());
				span_gestionDatos.appendChild(btnModificar);
				span_gestionDatos.appendChild(espacioVacio());
				span_gestionDatos.appendChild(btnEliminar);
				span_gestionDatos.appendChild(espacioVacio());
			/*FIN GESTION DE DATOS*/
		}	
			
			var td_paginacion_gestion=document.createElement("td");
			td_paginacion_gestion.setAttribute("id",nomObj+"_td_paginacion_gestion");
			td_paginacion_gestion.setAttribute("align",alineacionTD_paginacion_gestionDatos);
			td_paginacion_gestion.setAttribute("colspan",cantColTabla);
			//span paginacion
			if(paginacion=="si")
				 td_paginacion_gestion.appendChild(span_paginacion);
			//span espacios separacion
			if(paginacion=="si" && gestionDatos=="si")
				 td_paginacion_gestion.appendChild(span_espaciosSeparacion);
			//span gestion de datos (agregar, modificar, eliminar)
			if(gestionDatos=="si")
				 td_paginacion_gestion.appendChild(span_gestionDatos);
			
			//alert(td_paginacion_gestion);
			return td_paginacion_gestion;

	}
	
	function espacioVacio(){
	   var espacio=document.createTextNode("\u00a0");
	   return espacio;
	}
	
    function m_paginacion_inicio(){
		alert("iniciooo");
	}

	function m_paginacion_anterior(){
		alert("anteriorrr");
	}

	function m_paginacion_siguiente(){
		alert("siguienteee");
	}

	function m_paginacion_final(){
		alert("finalll");
	}

	function m_agregarDatos(){
	   //alert("agregar datos!");
	   agregarPersona();
	}

	function m_modificarDatos(){
	   alert("modificar datos!");
	}

	function m_eliminarDatos(){
	   alert("modificar datos!");
	}
	
	function m_obtenerNumColumnas(){
      //el número de las columnas es igual al número de las celdas de cualquier fila, digamos que la primera fila (cabecera TH)
      var nrcols = document.getElementById(jsonInicio.objTabla+"_tabla").getElementsByTagName('tr')[0].getElementsByTagName('th').length;
		return nrcols;
	}

}


//Muy buena pagina para ver javascript orientado a objetos (ejemplo de hasOwnProperty) :http://bonsaiden.github.com/JavaScript-Garden/es/

