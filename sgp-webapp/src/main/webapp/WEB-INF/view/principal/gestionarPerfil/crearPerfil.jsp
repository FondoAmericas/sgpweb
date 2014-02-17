<%@ include file="/common/taglibs.jsp"%>


<script type="text/javascript">
	$(document).ready(function() {
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
		}
	}


	///////////////////////////////////////////////EFECTOS DE PANTALLA

	function ocultarFuenteFinanciadora() {
		$("#div_fuenteFinanciadora").hide();

	}

	function ocultarFuenteFinanciadora2() {
		$("#div_fuenteFinanciadoraFondam").hide();
	}

	$(document).ready(function() {

		//$("#perfil_div").show('slow');
		$("#institucion_div").hide();
		//document.getElementById("perfil").style.fontWeight = "bold";//inicialmente
		//$("#div_metas").hide();

		$("#div_fuenteFinanciadora").hide();

		$("#div_fuenteFinanciadoraFondam").hide();

		$("#div_beneficiarios").hide();
		$("#nombreDocumentoAdjunto").attr("value","");
		//cargarDataCBXTipoBeneficiario();

	});

	
	///////////////////////////////////////////funcionalidad para la tabla de instituciones
	var arrayInstitucionFuenFinan;
	var objInstitucionFuenFinan;
	var indiceFila10 = 0;
	var elmTBODY10;
	var elmTR10;
	var elmTD10;

	///datos del fondam
	var objXHR27688553;
	//var RUCFONDAM="15151515155";
	var RUCFONDAM;
	var objInstitucion = null;

	//montos
	var MONTO_FONDAM;
	var MONTO_COFINANCIADOR;
	var MONTO_CONTRAPARTIDA;
	//tipos de fuentes financiadoras
	var FUENTE_FINAN_EJECUTOR = "183";
	var FUENTE_FINAN_COFINANCIADOR = "185";
	var FUENTE_FINAN_BENEFICIARIOS = "186";
	var FUENTE_FINAN_OTROS = "188";

	function obtenerDatosFondam() {
		objXHR27688553 = new ObjetoAJAX();
		var url = "obtenerDatosFondam.jspx";
		objXHR27688553.enviar(url, "get", "armarDatosFondam", null);
	}

	function armarDatosFondam() {
		console.log("datosfondam: " + objXHR27688553.respuestaTexto());
		objInstitucion = JSON.parse(objXHR27688553.respuestaTexto());
		if (objInstitucion.estado == "si") {
			RUCFONDAM = objInstitucion.txtRuc;
		}
	}

	function registroFonfoInstitucion() {
		//LLENAR VECTOR DE FUENTES FINANCIADORAS: PONER EN LA POSICION CERO LOS DATOS DEL FONDAM.
		var cbxFondoAmericas = document.getElementById("cbxFondoAmericas").value;

		if (cbxFondoAmericas == "1") {
			if (objInstitucion != null && objInstitucion.estado == "si") {

				var objFuenteFinanciera = new Object();
				objFuenteFinanciera.cbxTipoFuenteFinan = "";
				objFuenteFinanciera.cbxTipoMoneda = "";
				objFuenteFinanciera.txtMontoFinan = "0";
				objFuenteFinanciera.cbxInstitucionEjecutora = "0";

				objInstitucionFuenFinan = new Object();
				objInstitucionFuenFinan.institucion = objInstitucion;
				objInstitucionFuenFinan.fuenFinan = objFuenteFinanciera;

				arrayInstitucionFuenFinan.unshift(objInstitucionFuenFinan);

				limpiarTablaInstituciones();
				cargaDatosTablaInstitucion();

				llenaComboInstitucionEjecutora();
			} else {
				alert("Los datos del fondam no se encuentran registrados, registrelos.");
			}

		} else if (cbxFondoAmericas == "2") {
			//alert("arrayInstitucionFuenFinan.length: "+arrayInstitucionFuenFinan.length);

			//verificar si el fondam existe:
			var rpt = verificarFondam();
			if (rpt == true) {
				//alert("Eliminar FONDAM?");
				arrayInstitucionFuenFinan.shift();
				limpiarTablaInstituciones();
				cargaDatosTablaInstitucion();
				llenaComboInstitucionEjecutora();
			}

		}

	}

	function verificarFondam() {

		for ( var i = 0; i < arrayInstitucionFuenFinan.length; i++) {
			if (arrayInstitucionFuenFinan[i].institucion.txtRuc == RUCFONDAM) {//ruc del fondam
				return true;
			}
		}
		return false;
	}

	function borrarPosicionVector(num) {
		provi = vector.slice(num + 1);
		vector = vector.slice(0, num);
		vector = vector.concat(provi);
	}
	
</script>

<script type="text/javascript">
	///////////////////////////////////////////funcionalidad para la tabla ubicacion de proyectos
	var arrayUbicacionBeneficiarios = new Array();
	//var objUbicacionBeneficiarios;
	var indiceFila = 0;
	var elmTBODY;
	var elmTR;
	var elmTD;

	function onclick_btnAgregarUbicacion() {
		var rpt = validarUbicacion();
		if (rpt) {
			agregarUbicacion();
			limpiarCamposUbicacion();
		}

	}

	function agregarUbicacion() {
		cargaDatosArray();
		cargaDatosTabla();
	}
	function cargaDatosArray() {

		/*var cbxDep = document.getElementById("cbxDepartamentos");
		var cbxProv = document.getElementById("cbxProvincias");
		var cbxDist = document.getElementById("cbxDistritos");
		var txaLoc = document.getElementById("txaLocalizacion");*/
		//armado de una fila de la tabla
		var objUbicacion = new Object();
		objUbicacion.cbxDepValue = $("#cbxDepartamentos").val();
		objUbicacion.cbxDepText = $("#cbxDepartamentos option:selected").html(); //cbxDep.options[cbxDep.selectedIndex].text;

		objUbicacion.cbxProvValue = $("#cbxProvincias").val(); //cbxProv.value;
		objUbicacion.cbxProvText = $("#cbxProvincias option:selected").html(); //cbxProv.options[cbxProv.selectedIndex].text;

		objUbicacion.cbxDistValue = $("#cbxDistritos").val(); //cbxDist.value;
		objUbicacion.cbxDistText = $("#cbxDistritos option:selected").html(); //cbxDist.options[cbxDist.selectedIndex].text;

		objUbicacion.txaLoc = $("#txaLocalizacion").val(); //txaLoc.value;

		objUbicacion.DepProvDistID = $("#cbxDistritos").val(); //cbxDist.value;

		var objUbicacionBeneficiarios = new Object;
		objUbicacionBeneficiarios.ubicacion = objUbicacion;
		
		var arrayBeneficiarios = new Array();
		arrayBeneficiarios = null;
		objUbicacionBeneficiarios.beneficiarios = arrayBeneficiarios;

		arrayUbicacionBeneficiarios.push(objUbicacionBeneficiarios);
	}


	///////////////////////////////////////////////////////////////////////////////////

	function cargaDatosTabla() {
		limpiarTabla();
		elmTBODY = document.getElementById("tbodyUbiProy");
		//alert(arrayUbicacionBeneficiarios[0].ubicacion.cbxDepText);
		for ( var p = 0; p < arrayUbicacionBeneficiarios.length; p++) {
			elmTR = elmTBODY.insertRow(p);
			if (p % 2 == 1) {
				elmTR.setAttribute("class", "f1");
			} else {
				elmTR.setAttribute("class", "f2");
			}
			for ( var i = 0; i < 7; i++) {
				elmTD = elmTR.insertCell(i);
				if (i == 0) {
					var ind = indiceFila + 1;
					elmText = document.createTextNode(ind);
					var span = document.createElement("div");
					span.setAttribute("align", "center");
					span.appendChild(elmText);
					elmTD.appendChild(span);
				}
				if (i == 1)
					elmText = document
							.createTextNode(arrayUbicacionBeneficiarios[p].ubicacion.cbxDepText);
				if (i == 2)
					elmText = document
							.createTextNode(arrayUbicacionBeneficiarios[p].ubicacion.cbxProvText);
				if (i == 3)
					elmText = document
							.createTextNode(arrayUbicacionBeneficiarios[p].ubicacion.cbxDistText);
				if (i == 4)
					elmText = document
							.createTextNode(arrayUbicacionBeneficiarios[p].ubicacion.txaLoc);
				if (i == 5) {
					var link = document.createElement("a");
					link.setAttribute("class", "link");
					link.setAttribute("id", indiceFila);
					link.onclick = function() {
						limpiarCamposBeneficiarios();
						$("#div_beneficiarios").show('slow');
						verBeneficiarios(this.id);
					}; //limpiarCamposInstitucion();$("#div_fuenteFinanciadora").show('slow');verFuentesFinancieras(this.id);
					var text = document.createTextNode("Beneficiarios");
					link.appendChild(text);
					var div = document.createElement("div");
					div.setAttribute("align", "center");
					div.appendChild(link);
					elmTD.appendChild(div);
				}
				if (i == 6) {
					//alert("entro");
					var imagen = document.createElement("img");
					imagen
							.setAttribute("src",
									"${pageContext.request.contextPath}/images/del.png");
					imagen.setAttribute("id", indiceFila);
					imagen.onclick = function() {
						eliminarFilaUbi(this.id);
					};
					var div = document.createElement("div");
					div.setAttribute("align", "center");
					div.appendChild(imagen);
					elmTD.appendChild(div);

				}
				if (i != 0 && i != 5 && i != 6)
					elmTD.appendChild(elmText);
			}
			indiceFila++;
		}
	}

	var indUbiBen;

	function verBeneficiarios(indInstitucion) {
		indUbiBen = parseInt(indInstitucion);
		document.getElementById("dep_Prov_Dist").innerHTML = arrayUbicacionBeneficiarios[indUbiBen].ubicacion.cbxDepText
				+ "/"
				+ arrayUbicacionBeneficiarios[indUbiBen].ubicacion.cbxProvText
				+ "/"
				+ arrayUbicacionBeneficiarios[indUbiBen].ubicacion.cbxDistText;
		limpiarTablaBeneficiarios();
		arrayBeneficiarios = new Array();

		if (arrayUbicacionBeneficiarios[indUbiBen].beneficiarios != null) {//si tiene fuente financiera
			//alert("tiene beneficiarios");
			arrayBeneficiarios = arrayUbicacionBeneficiarios[indUbiBen].beneficiarios;
			cargaDatosTablaBeneficiarios();
		}

	}

	function eliminarFilaUbi(numeroFilaEliminar) {
		var eli = parseInt(numeroFilaEliminar);
		var eliMostrar = eli + 1;
		var rpt = confirm("¿Seguro que desea eliminar a la ubicacion de proyecto nro:"
				+ eliMostrar + "?");
		if (rpt == true) {
			arrayUbicacionBeneficiarios.splice(eli, 1);
			limpiarTabla();
			cargaDatosTabla();
			onclick_btnCerrar();
		}
	}

	function limpiarTabla() {
		indiceFila = 0;
		elmTBODY = document.getElementById("tbodyUbiProy");
		var tam = elmTBODY.rows.length;
		//alert("tamano de tabla cuando se limpia :"+tam);
		for ( var w = 0; w < tam; w++) {
			elmTBODY.deleteRow(0);
		}
	}

	///////////////////////////////////////////funcionalidad para la tabla de beneficiarios y estratos

	var arrayBeneficiarios = new Array();
	var indiceFila4 = 0;
	var elmTBODY4;
	var elmTR4;
	var elmTD4;
	function onclick_btnAgregarEstrato() {
		var rpt = validarBeneficiarosEstratos();
		if (rpt) {
			limpiarTablaBeneficiarios();
			cargaDatosArrayBeneficiarios();
			cargaDatosTablaBeneficiarios();
			limpiarCamposBeneficiarios();
			agregarListadoBeneficiarios();
		}
	}

	function cargaDatosArrayBeneficiarios() {

		var txtCantBen = document.getElementById("txtCantBen");
		var cbxEstSocBen = document.getElementById("cbxEstSocBen");
		var cbxTipoBen = document.getElementById("cbxTipoBen");
		var txaCaracPoblacion = document.getElementById("txaCaracPoblacion");
		var txaDescripcionPoblacion = document
				.getElementById("txaDescripcionPoblacion");
		var objBeneficiario = new Object();
		objBeneficiario.txtCantBen = txtCantBen.value;
		objBeneficiario.cbxEstSocBenValue = cbxEstSocBen.value;
		objBeneficiario.cbxEstSocBenText = cbxEstSocBen.options[cbxEstSocBen.selectedIndex].text;
		objBeneficiario.cbxTipoBenValue = cbxTipoBen.value;
		objBeneficiario.cbxTipoBenText = cbxTipoBen.options[cbxTipoBen.selectedIndex].text;
		objBeneficiario.txaCaracPoblacion = txaCaracPoblacion.value;
		objBeneficiario.txaDescripcionPoblacion = txaDescripcionPoblacion.value;
		arrayBeneficiarios.push(objBeneficiario);

	}

	function cargaDatosTablaBeneficiarios() {
		elmTBODY4 = document.getElementById("tbodyBeneficiarios");
		for ( var p = 0; p < arrayBeneficiarios.length; p++) {
			elmTR4 = elmTBODY4.insertRow(p);
			if (p % 2 == 1) {
				elmTR4.setAttribute("class", "f1");
			} else {
				elmTR4.setAttribute("class", "f2");
			}
			for ( var i = 0; i < 6; i++) {
				elmTD4 = elmTR4.insertCell(i);
				if (i == 0) {
					var ind = indiceFila4 + 1;
					//alert("indicador de fila:"+indiceFila3);
					elmText = document.createTextNode(ind);
					var div = document.createElement("div");
					div.setAttribute("align", "center");
					div.appendChild(elmText);
					elmTD4.appendChild(div);
				}
				if (i == 1)
					elmText = document
							.createTextNode(arrayBeneficiarios[p].txtCantBen
									+ " - "
									+ arrayBeneficiarios[p].cbxEstSocBenText);
				if (i == 2)
					elmText = document
							.createTextNode(arrayBeneficiarios[p].cbxTipoBenText);
				if (i == 3)
					elmText = document
							.createTextNode(arrayBeneficiarios[p].txaCaracPoblacion);
				if (i == 4)
					elmText = document
							.createTextNode(arrayBeneficiarios[p].txaDescripcionPoblacion);
				if (i == 5) {
					//elmText = document.createTextNode("drer");
					var imagen = document.createElement("img");
					imagen
							.setAttribute("src",
									"${pageContext.request.contextPath}/images/del.png");
					imagen.setAttribute("id", indiceFila4);
					imagen.onclick = function() {
						eliminarFila(this.id);
					};
					var div = document.createElement("div");
					div.setAttribute("align", "center");
					div.appendChild(imagen);
					elmTD4.appendChild(div);
				}
				if (i != 0 && i != 5)
					elmTD4.appendChild(elmText);
			}
			indiceFila4++;
		}
	}

	function eliminarFila(numeroFilaEliminar) {
		var eli = parseInt(numeroFilaEliminar);
		var eliMostrar = eli + 1;
		var rpt = confirm("¿Seguro que desea eliminar el estrato nro:"
				+ eliMostrar + "?");
		if (rpt == true) {
			arrayBeneficiarios.splice(eli, 1);
			limpiarTablaBeneficiarios();
			cargaDatosTablaBeneficiarios();
		}
	}

	function limpiarTablaBeneficiarios() {
		indiceFila4 = 0;
		var elmTBODY4 = document.getElementById("tbodyBeneficiarios");
		var tam = elmTBODY4.rows.length;
		//alert("tamano de tabla cuando se limpia :"+tam);
		for ( var w = 0; w < tam; w++) {
			elmTBODY4.deleteRow(0);
		}
	}

	//Boton OK
	function agregarListadoBeneficiarios() {
		if (arrayBeneficiarios.length) {
			arrayUbicacionBeneficiarios[indUbiBen].beneficiarios = arrayBeneficiarios;
			//arrayBeneficiarios = new Array();
			//limpiarTablaBeneficiarios();
			//$("#div_beneficiarios").hide();
		} else {
			alert("Debe ingresar al menos un estrato");
		}
		//arrayBeneficiarios=new Array();
		//limpiarTablaBeneficiarios();

		//ocultar Div de metas
		//$("#div_metas").hide();
	}

	function onclick_btnCerrar() {
		$("#div_beneficiarios").hide();
		//agregarListadoBeneficiarios();
		limpiarCamposBeneficiarios();
		limpiarTablaBeneficiarios();
	}
</script>

<script type="text/javascript">
	//////////////////////////////////////INSTITUCION (FUENTES FINANCIADORAS)/////////////////////////////

	var objXHR9;
	var tipoFuenteFinanciadora;
	function cargaComboTipoFuenteFinanciadora() {
		objXHR9 = new ObjetoAJAX();
		objXHR9.enviar("cargaComboTipoFuenteFinanciadora.jspx", "post",
				"cargaDataComboTipoFuenteFinanciadora", null);
	}

	function cargaDataComboTipoFuenteFinanciadora() {
		tipoFuenteFinanciadora = JSON.parse(objXHR9.respuestaTexto());
		//alert(objXHR9.respuestaTexto());
		//alert(tipoFuenteFinanciadora[0].length);
		//llenaComboTipoFuenteFinanciadora();
	}

	var objXHR10;
	var tipoMoneda;
	function cargaComboTipoMoneda() {
		objXHR10 = new ObjetoAJAX();
		objXHR10.enviar("cargaComboTipoMoneda.jspx", "post",
				"cargaDataComboTipoMoneda", null);
	}

	function cargaDataComboTipoMoneda() {
		tipoMoneda = JSON.parse(objXHR10.respuestaTexto());
		//alert(objXHR10.respuestaTexto());
		//alert(tipoMoneda[0].length);
		//llenaComboTipoMoneda();
	}
	///////////////////////////////////////////funcionalidad para la tabla de instituciones

	function onclick_btnAgregarInstitucion() {
		var rpt = validarEjecutorInformacionFinanciamiento();
		if (rpt) {
			$("#div_fuenteFinanciadora").hide();
			limpiarTablaInstituciones();
			cargaDatosArrayInstitucionFuenFinan();
			cargaDatosTablaInstitucion();
			limpiarCamposInstitucion();

			//deshabilitarCamposInstitucion();
			//$("#div_fuenteFinanciadora").show('slow');	
			//verFuentesFinancieras(this.id);

			llenaComboInstitucionEjecutora();
		}
	}

	function testAgregarInstitucion() {
		alert(JSON.stringify(arrayInstitucionFuenFinan));

	}

	function deshabilitarCamposInstitucion() {

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

	function habilitarCamposInstitucion() {

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

	function cargaDatosArrayInstitucionFuenFinan() {

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
		objInstitucion.txtInstitucion = txtInstitucion.value;
		objInstitucion.txtRuc = txtRuc.value;
		objInstitucion.txtDireccion = txtDireccion.value;
		objInstitucion.txtTelefono = txtTelefono.value;
		objInstitucion.txtRepLegal = txtRepLegal.value;
		objInstitucion.txtContacto = txtContacto.value;
		objInstitucion.txtCorreo = txtCorreo.value;
		objInstitucion.txaObservacion = txaObservacion.value;

		var objFuenteFinanciera = new Object();
		objFuenteFinanciera.cbxTipoFuenteFinan = cbxTipoFuenteFinan.value;
		objFuenteFinanciera.cbxTipoMoneda = cbxTipoMoneda.value;
		objFuenteFinanciera.txtMontoFinan = txtMontoFinan.value;
		objFuenteFinanciera.cbxInstitucionEjecutora = "0";

		objInstitucionFuenFinan = new Object();
		objInstitucionFuenFinan.institucion = objInstitucion;
		objInstitucionFuenFinan.fuenFinan = objFuenteFinanciera;

		arrayInstitucionFuenFinan.push(objInstitucionFuenFinan);
	}

	function cargaDatosTablaInstitucion() {//tbodyInstitucion
		elmTBODY10 = document.getElementById("tbodyInstitucion");
		for ( var p = 0; p < arrayInstitucionFuenFinan.length; p++) {
			elmTR10 = elmTBODY10.insertRow(p);
			if (p % 2 == 1) {
				elmTR10.setAttribute("class", "f1");
			} else {
				elmTR10.setAttribute("class", "f2");
			}
			for ( var i = 0; i < 11; i++) {
				elmTD10 = elmTR10.insertCell(i);
				if (i == 0) {
					var ind = indiceFila10 + 1;
					//alert("indicador de fila:"+indiceFila10);
					elmText = document.createTextNode(ind);
					var div = document.createElement("div");
					div.setAttribute("align", "center");
					div.appendChild(elmText);
					elmTD10.appendChild(div);
				}
				if (i == 1)
					elmText = document
							.createTextNode(arrayInstitucionFuenFinan[p].institucion.txtRuc);
				if (i == 2)
					elmText = document
							.createTextNode(arrayInstitucionFuenFinan[p].institucion.txtInstitucion);
				if (i == 3)
					elmText = document
							.createTextNode(arrayInstitucionFuenFinan[p].institucion.txtDireccion);
				if (i == 4)
					elmText = document
							.createTextNode(arrayInstitucionFuenFinan[p].institucion.txtTelefono);
				if (i == 5)
					elmText = document
							.createTextNode(arrayInstitucionFuenFinan[p].institucion.txtRepLegal);
				if (i == 6)
					elmText = document
							.createTextNode(arrayInstitucionFuenFinan[p].institucion.txtContacto);
				if (i == 7)
					elmText = document
							.createTextNode(arrayInstitucionFuenFinan[p].institucion.txtCorreo);
				if (i == 8)
					elmText = document
							.createTextNode(arrayInstitucionFuenFinan[p].institucion.txaObservacion);
				if (i == 9) {
					var link = document.createElement("a");
					link.setAttribute("class", "link");
					link.setAttribute("id", indiceFila10);
					// link.setAttribute=("href","javascript:limpiarCamposInstitucion();$('#div_fuenteFinanciadora').show('slow');verFuentesFinancieras(this.id);");

					// ruc del fondam   1515151515
					link.onclick = function() {
						var cbxFondoAmericas = document
								.getElementById("cbxFondoAmericas").value;
						//alert("this.id"+ this.id);
						//alert("ruc :"+arrayInstitucionFuenFinan[this.id].institucion.txtRuc);
						if (arrayInstitucionFuenFinan[this.id].institucion.txtRuc == RUCFONDAM) { //cuando se requiere del fondam
							//alert("para fondam");
							$("#div_fuenteFinanciadoraFondam").show('slow');
							$("#div_fuenteFinanciadora").hide();
							verFuentesFinancieras(this.id);
						} else {//cuando no se requiere del fondam
							limpiarCamposInstitucion();
							$("#div_fuenteFinanciadora").show('slow');
							$("#div_fuenteFinanciadoraFondam").hide();
							verFuentesFinancieras(this.id);
						}

					};
					var text = document.createTextNode("Fte.Finan.");
					link.appendChild(text);
					var div = document.createElement("div");
					div.setAttribute("align", "center");
					div.appendChild(link);
					elmTD10.appendChild(div);

				}
				if (i == 10) {
					var imagen = document.createElement("img");
					imagen
							.setAttribute("src",
									"${pageContext.request.contextPath}/images/del.png");
					imagen.setAttribute("id", indiceFila10);
					imagen.onclick = function() {
						eliminarFila10(this.id);
						llenaComboInstitucionEjecutora();
						//
						if (document.getElementById("cbxFondoAmericas").value == "1") {
							document.getElementById("cbxFondoAmericas").value = "2";
						}
						//
						ocultarFuenteFinanciadora();
						ocultarFuenteFinanciadora2();
						limpiarFuenteFinan2();
					};
					//eliminarFila3(this.id);$("#div_metas").hide();

					var div = document.createElement("div");
					div.setAttribute("align", "center");
					div.appendChild(imagen);
					elmTD10.appendChild(div);
				}
				if (i != 0 && i != 9 && i != 10)
					elmTD10.appendChild(elmText);
			}
			indiceFila10++;
		}
	}

	function limpiarFuenteFinan2() {
		//document.getElementById("cbxTipoMoneda2").value = "0";
		document.getElementById("txtMontoFondam").value = "";
	}

	function eliminarFila10(numeroFilaEliminar) {
		var eli = parseInt(numeroFilaEliminar);
		var eliMostrar = eli + 1;
		var rpt = confirm("¿Seguro que desea eliminar la institucion nro:"
				+ eliMostrar + "?");
		if (rpt == true) {
			arrayInstitucionFuenFinan.splice(eli, 1);
			limpiarTablaInstituciones();
			cargaDatosTablaInstitucion();
		}
	}

	function limpiarTablaInstituciones() {
		indiceFila10 = 0;
		var elmTBODY10 = document.getElementById("tbodyInstitucion");
		var tam = elmTBODY10.rows.length;
		//alert("tamano de tabla cuando se limpia :"+tam);
		for ( var w = 0; w < tam; w++) {
			elmTBODY10.deleteRow(0);
		}
	}

	function testearInstitucionFuenFinan() {
		alert(JSON.stringify(arrayInstitucionFuenFinan));
	}

	/////////////////////////////////////funcionalidad para las fuentes financieras para las instituciones
	var indIns;
	var objFuenteFinanciera;
	function verFuentesFinancieras(indInstitucion) {
		indIns = parseInt(indInstitucion);
		document.getElementById("nomIns").innerHTML = arrayInstitucionFuenFinan[indIns].institucion.txtInstitucion;
		//limpiarCamposFuentesFinan();
		objFuenteFinanciera = new Object();
		//var ind=verificarSiFuenteFinanciera(indIns);
		//alert("tiene fuente financiera??? "+ind);
		//if(ind==true){//si la actividad tiene metas cargar esas metas
		objFuenteFinanciera = arrayInstitucionFuenFinan[indIns].fuenFinan;
		//cargaDatosFuenFinan();
		cargaDatosFuenFinan(indIns);
		//}
	}

	function verificarSiFuenteFinanciera(indIns) {
		var rpt = false;
		if (arrayInstitucionFuenFinan[indIns].fuenFinan != null) {//si tiene fuente financiera
			//alert("tiene metas");
			rpt = true;
		}
		return rpt;
	}

	function cargaDatosFuenFinan(indIns) {
		var descFuenteFinan;
		for ( var i = 0; i < tipoFuenteFinanciadora[0].length; i++) {
			if (tipoFuenteFinanciadora[0][i].tablaEspecificaID == arrayInstitucionFuenFinan[indIns].fuenFinan.cbxTipoFuenteFinan) {
				descFuenteFinan = tipoFuenteFinanciadora[0][i].descripcionCabecera;
			}
		}
		var descTipoMoneda;
		for ( var i = 0; i < tipoMoneda[0].length; i++) {
			if (tipoMoneda[0][i].tablaEspecificaID == arrayInstitucionFuenFinan[indIns].fuenFinan.cbxTipoMoneda) {
				descTipoMoneda = tipoMoneda[0][i].descripcionCabecera;
			}
		}

		document.getElementById("spanTipoFuenteFinan").innerHTML = descFuenteFinan;
		document.getElementById("spanTipoMoneda").innerHTML = descTipoMoneda;
		document.getElementById("spanMontoFinan").innerHTML = arrayInstitucionFuenFinan[indIns].fuenFinan.txtMontoFinan;
	}

	function cargaObjFuenteFinanciera() {
		objFuenteFinanciera.cbxTipoFuenteFinan = document
				.getElementById("cbxTipoFuenteFinan").value;
		objFuenteFinanciera.cbxTipoMoneda = document
				.getElementById("cbxTipoMoneda").value;
		objFuenteFinanciera.txtMontoFinan = document
				.getElementById("txtMontoFinan").value;
		objFuenteFinanciera.cbxInstitucionEjecutora = document
				.getElementById("cbxInstitucionEjecutora").value;
		arrayInstitucionFuenFinan[indIns].fuenFinan = objFuenteFinanciera;

	}

	//Boton OK
	function onclick_btnAgregarFuenteFinan() {
		var rpt = validarFuenteFinanciadora();
		if (rpt) {
			cargaObjFuenteFinanciera();
			if (arrayInstitucionFuenFinan[indIns].fuenFinan == null) {
				alert("No se guardo ninguna Fuente financiadora.");
			}
			objFuenteFinanciera = new Object();
			limpiarCamposFuentesFinan();
			//ocultar Div 
			$("#div_fuenteFinanciadora").hide();
		}
	}

	/////////////////llenar combo de institucion financiadora

	function llenaComboInstitucionEjecutora() {
		var cbxInstitucionEjecutora = document.createElement("select");
		cbxInstitucionEjecutora.setAttribute("id", "cbxInstitucionEjecutora");
		cbxInstitucionEjecutora.setAttribute("name", "cbxInstitucionEjecutora");
		cbxInstitucionEjecutora.onchange = function() {
			seleccionarInstitucionEjecutora(this.value);
		};
		//valor por defecto
		var op = document.createElement("option");
		op.setAttribute("value", "0");
		var text = document.createTextNode("-- Ejecutor --");
		op.appendChild(text);
		cbxInstitucionEjecutora.appendChild(op);
		//llenar valores 
		for ( var i = 0; i < arrayInstitucionFuenFinan.length; i++) {
			
			///**************************** habilito para que Fondam no salga en el combo de ejecutores  *********************// 
			
			//if(arrayInstitucionFuenFinan[i].institucion.txtInstitucion != 'Fondo de las Americas'){
			op = document.createElement("option");
			op.setAttribute("value",
					arrayInstitucionFuenFinan[i].institucion.txtInstitucion);
			text = document
					.createTextNode(arrayInstitucionFuenFinan[i].institucion.txtInstitucion);
			op.appendChild(text);
			cbxInstitucionEjecutora.appendChild(op);
			//}
		}
		//limpia div
		document.getElementById("div_cbxInstitucionEjecutora").innerHTML = "";
		//pinta combo en el div
		document.getElementById("div_cbxInstitucionEjecutora").appendChild(
				cbxInstitucionEjecutora);

	}

	function seleccionarInstitucionEjecutora(nombreInstitucion) {
		//alert("nombre institucion" + nombreInstitucion);
		for ( var i = 0; i < arrayInstitucionFuenFinan.length; i++) {
			if (arrayInstitucionFuenFinan[i].institucion.txtInstitucion == nombreInstitucion) {
				arrayInstitucionFuenFinan[i].fuenFinan.cbxInstitucionEjecutora = "1";
			} else
				arrayInstitucionFuenFinan[i].fuenFinan.cbxInstitucionEjecutora = "0";
		}
	}
</script>

<script type="text/javascript">

	///////////////////////////////////////////funcionalidad para la tabla de resumen y observaciones de perfil
	function onclick_btnAgregarResumen() {
		var rpt = validarResumenObservaciones();
		if (rpt==0) {
				//agregarResumenPerfil();
				cargaGrillaResumenProyecto();
				limpiarResumenObservacionesPerfil();	
		}			
	}
		
	function modificarResumenProyecto(resumen,tipoResumenId){
		$("#txaResumenPerfil").attr("value",resumen);
		$("#cbxTipoResumenProyecto").attr("value",tipoResumenId);
		$("#cbxTipoResumenProyecto").attr("disabled","disabled");
		$("#flagModifica").attr("value","m");
	}	

	function eliminarResumenProyecto(tipoResumenId){
		$("#divGrillaResumenProyecto").load("eliminarRegistroGrillaResumenProyecto.jspx",{
			tipoResumenId : tipoResumenId
		});	
	}
	
/********** RESTRICCION SUBAREA TEMATICA y tiempo de duracion del proyecto***********************************************************************************/
var objXHR7A4;
var restDuracionProy = null;
	function restriccionDuracionProyecto(){
		//para Restriccion de duracion de proyecto
		objXHR7A4 = new ObjetoAJAX();
		var oform = document.forms[1];
		objXHR7A4.enviar("cargarRestriccionDuracionProyecto.jspx?idPrograma="+$("#cbxPrograma").val(), "get",
				"cargaDataRestriccionDuracionProyecto", oform);
	}

	function cargaDataRestriccionDuracionProyecto() {
		objProg = JSON.parse(objXHR7A4.respuestaTexto());
		restDuracionProy = objProg.duracionProg;
		//alert("duracion maxima del proyecto: "+restDuracionProy);	

	}
	/***************************LIMPIAR CAMPOS***********************/
	function limpiarCamposUbicacion() {
		document.getElementById('cbxDepartamentos').options.selectedIndex = 0;
		document.getElementById('cbxProvincias').options.selectedIndex = 0;
		document.getElementById('cbxDistritos').options.selectedIndex = 0;
		document.getElementById('txaLocalizacion').value = "";
	}

	function limpiarCamposBeneficiarios() {
		document.getElementById('txtCantBen').value = "";
		document.getElementById('cbxEstSocBen').options.selectedIndex = 0;
		document.getElementById('cbxTipoBen').options.selectedIndex = 0;
		document.getElementById('txaCaracPoblacion').value = "";
		document.getElementById('txaDescripcionPoblacion').value = "";
	}

	function limpiarCamposActividades() {
		document.getElementById('txtActividad').value = "";
		document.getElementById('txtDuracionAct').value = "";
		document.getElementById('txaDescripcion').value = "";
	}
	function limpiarCamposMetas() {
		document.getElementById('cbxUnidadMedida').options.selectedIndex = 0;
		document.getElementById('txtCantidad').value = "";
	}
	function limpiarResumenObservacionesPerfil() {
		$("#cbxTipoResumenProyecto").attr("value","0");
		$("#txaResumenPerfil").attr("value","");
		$("#flagModifica").attr("value","0");
		$("#cbxTipoResumenProyecto").removeAttr("disabled");
	}

	function limpiarCamposInstitucion() {
		document.getElementById('cbxTipoFuenteFinan').value = "0";
		//document.getElementById("cbxTipoMoneda").value = "0";
		document.getElementById("txtMontoFinan").value = "";
		document.getElementById("txtInstitucion").value = "";
		document.getElementById("txtRuc").value = "";
		document.getElementById("txtDireccion").value = "";
		document.getElementById("txtTelefono").value = "";
		document.getElementById("txtRepLegal").value = "";
		document.getElementById("txtContacto").value = "";
		document.getElementById("txtCorreo").value = "";
		document.getElementById("txaObservacion").value = "";

	}

	function limpiarCamposFuentesFinan() {
		document.getElementById("cbxTipoFuenteFinan").options.selectedIndex = 0;
		//document.getElementById("cbxTipoMoneda").options.selectedIndex = 0;
		document.getElementById("txtMontoFinan").value = "";
	}

	/**************VALIDACIONES*********************/
	String.prototype.trim = function() {
		return this.replace(/^\s+|\s+$/g, "");
	};

	/***************************VALIDAR CAMPOS DATO_PROYECTO***********************/
	var nomFile;
	function validarDatosProyecto() {

		var mensaje = "";
		var errores = 0;
		var rpt = true;
		
		if($("#txtProyecto").val().length > 0 && $("#cbxPrograma").val()!=0 ){
			restringirEntidadEjecutoraPorPrograma();	
		}
		
		if (restEntidadEje.existe == "si") {
			mensaje = mensaje + "El proyecto ya existe en el programa seleccionado! \n";
			//restEntidadEje = new Object();
			errores = errores + 1;
		}
		
		if (document.getElementById("txtProyecto").value.trim().length == 0) {
			mensaje = mensaje + "Ingresar nombre del proyecto \n";
			//document.getElementById("txtProyecto").focus();
			errores = errores + 1;
		} 
		
		if (document.getElementById("cbxModalidadFinan").value == "0") {
			mensaje = mensaje + "Seleccionar una modalidad de financiamiento \n";
			//document.getElementById("cbxModalidadFinan").focus();
			errores = errores + 1;//rpt = false;
		} 

		if (document.getElementById("cbxPrograma").value == "0") {
			mensaje = mensaje + "Seleccionar un programa \n";
			//document.getElementById("cbxPrograma").focus();
			errores = errores + 1;//rpt = false;
		}

		if (document.getElementById("cbxAreaTematica").value == "0") {
			mensaje = mensaje + "Seleccionar un area tematica \n";
			//document.getElementById("cbxAreaTematica").focus();
			errores = errores + 1;////rpt = false;
		} 

		if (document.getElementById("cbxSubAreaTematica").value == "0") {
			mensaje = mensaje +  "Seleccionar un subarea tematica \n";
			//document.getElementById("cbxSubAreaTematica").focus();
			errores = errores + 1;//rpt = false;
		} 

		if (document.getElementById("txtDuracion").value.trim().length == 0) {
			mensaje = mensaje + "Ingresar duracion del proyecto \n";
			//document.getElementById("txtDuracion").focus();
			errores = errores + 1;//rpt = false;
		} 
		//para upload del archivo
		//validarUpload();
		if ($("#imagenODocumento").val().length == 0 ) {
			mensaje = mensaje + "Debe adjuntar al menos el Documento de Perfil. \n";
			//document.getElementById("cbxSubAreaTematica").focus();
			errores = errores + 1;//rpt = false;
		}
		
		if ($("#imagenODocumento").val().length > 0 ) {
			var extencion = validarExtensionArchivo("imagenODocumento","nombreArchivo","extension");
			if(extencion==false){
				mensaje = mensaje + "Solo se pueden subir archivos con las siguientes extensiones: "+extensiones_permitidas.join()+" . \nError en extencion de Documento Perfil. \n ";
				errores = errores + 1;//rpt=false;
			}
		}
		
		if(arrayUbicacionBeneficiarios.length == 0) {//sino hay ninguna ubicacion 
			mensaje = mensaje + "Agregar al menos una ubicacion de proyecto \n";
			errores = errores + 1;//ubiProy = validarUbicacion();
			
		} else {//cuando hay una o mas ubicaciones 
			for ( var i = 0; i < arrayUbicacionBeneficiarios.length; i++) {
				if (arrayUbicacionBeneficiarios[i].beneficiarios == null
						|| arrayUbicacionBeneficiarios[i].beneficiarios.length == 0) {
					mensaje = mensaje + "Agregar Beneficiarios a la ubicacion: "+ arrayUbicacionBeneficiarios[i].ubicacion.cbxDepText +" / "+ arrayUbicacionBeneficiarios[i].ubicacion.cbxProvText +" / "+ arrayUbicacionBeneficiarios[i].ubicacion.cbxDistText +". \n";
					errores = errores + 1;
				}
			}
		}
		
		var validaFondam = $("#cbxFondoAmericas option:selected").html(); //"no";
		//alert(validaFondam);
		if (arrayInstitucionFuenFinan.length > 0){
			if(validaFondam == "Si"){
				if(document.getElementById("cbxTipoMoneda2").value == "0"){
					mensaje = mensaje + " Debe seleccionar el tipo de moneda para el monto ingresado para FONDAM. \n";
					errores = errores + 1;
				}
				if($("#txtMontoFondam").val() == "0"){
					mensaje = mensaje + "Debe ingresar el monto que financiara el FONDAM. \n";
					errores = errores + 1;
				}
			}
			
			if($("#cbxInstitucionEjecutora").val() == 0) {
				mensaje = mensaje + "Seleccionar institucion ejecutora \n";
				//document.getElementById("cbxInstitucionEjecutora").focus();
				errores = errores + 1;
			}
		}else if (arrayInstitucionFuenFinan.length == 0){
			mensaje = mensaje + "Debe ingresar al menos una institucion. \n";
			//document.getElementById("cbxInstitucionEjecutora").focus();
			errores = errores + 1;	
		}
	
		if ($("#cantidadResumenPerfil").val() == 0) {
			mensaje = mensaje + "Debe agregar al menos un tipo de resumen.\n";
			//document.getElementById("cbxTipoResumenProyecto").focus();
			errores = errores + 1;
		}
		
		if (errores > 0){
			alert(mensaje);
		}
		return errores;
	}

	extensiones_permitidas = new Array("doc","docx","pdf","DOC","DOCX","PDF","rar","zip","RAR","ZIP");//".jpg",".jpeg",".JPG",".JPEG");
	
	function validarExtensionArchivo(nombreComponente,nombreArchivoComponente,extensionComponente){
		   //extensiones_permitidas = new Array("doc","docx","pdf","DOC","DOCX","PDF");//".jpg",".jpeg",".JPG",".JPEG");
		   ruta_archivo=$("#"+nombreComponente).val();
		   if(!ruta_archivo){
		      //alert("No se ha seleccionado ningun archivo!");
		      return false;
		   }else{
			  arrayNombreArchivo = ruta_archivo.split(".");
			  nombreArchivo=arrayNombreArchivo[0];
			  //alert(nombreArchivo);
			  //nombreArchivoUpload = nombreArchivo;//variable en gestorAjax
			  //alert(nombreArchivo);
			  //nombreArchivo = nombreArchivo.split('\\');
			  //alert(nombreArchivo);
			  //nombreArchivo=nombreArchivo[nombreArchivo.length-1];
			  //alert(nombreArchivo);
			  //extension="."+arrayNombreArchivo[1];
			  extension=arrayNombreArchivo[1];
			  //alert("nombre de archivo: "+nombreArchivo);
		      //alert("extension de archivo: "+extension);
		      $("#"+nombreArchivoComponente).attr("value",nombreArchivo);
		      $("#"+extensionComponente).attr("value",extension);
		      extensionPermitida=false;
			  for (var i = 0; i < extensiones_permitidas.length; i++) { 
		         if (extensiones_permitidas[i] == extension) { 
					 extensionPermitida=true; 
					 break; 
		         } 
		      } 
			  if (!extensionPermitida) { 
				//alert("Solo se pueden subir archivos con las siguientes extensiones: "+extensiones_permitidas.join()); 
			    return false;
			  }
		   }
		   return true;
		}
	
	var objXHR78511;
	var objNomArchivo;
	function validarUpload() {
		objXHR78511 = new ObjetoAJAX();
		objXHR78511.enviar("validarUpload.jspx", "post", "validarUploadRpt",
				null);
	}
	function validarUploadRpt() {
		objNomArchivo = JSON.parse(objXHR78511.respuestaTexto());
		nomFile = objNomArchivo.fileName;
	}

	/***************************VALIDAR CAMPOS DEP_PROV_DIST***********************/
	function validarUbicacion() {
		if (document.getElementById("cbxDepartamentos").value == "0") {
			alert("seleccionar departamento");
			document.getElementById("cbxDepartamentos").focus();
			return false;
		}
		if (document.getElementById("cbxProvincias").value == "0") {
			alert("seleccionar provincia");
			document.getElementById("cbxProvincias").focus();
			return false;
		}
		if (document.getElementById("cbxDistritos").value == "0") {
			alert("seleccionar distrito");
			document.getElementById("cbxDistritos").focus();
			return false;
		}
		/*if (document.getElementById("txaLocalizacion").value.trim().length == 0) {
			alert("ingresar localizacion");
			document.getElementById("txaLocalizacion").focus();
			return false;
		}*/
		return true;
	}

	function validarBeneficiarosEstratos() {
		if (document.getElementById("txtCantBen").value.trim().length == 0) {
			alert("ingresar cantidad de beneficiarios");
			document.getElementById("txtCantBen").focus();
			return false;
		}
		if (document.getElementById("cbxEstSocBen").value == "0") {
			alert("seleccionar estrato social");
			document.getElementById("cbxEstSocBen").focus();
			return false;
		}
		if (document.getElementById("cbxTipoBen").value == "0") {
			alert("seleccionar tipo de beneficiario");
			document.getElementById("cbxTipoBen").focus();
			return false;
		}
		if (document.getElementById("txaCaracPoblacion").value.trim().length == 0) {
			alert("ingresar caracteristicas de poblacion");
			document.getElementById("txaCaracPoblacion").focus();
			return false;
		}
		if (document.getElementById("txaDescripcionPoblacion").value.trim().length == 0) {
			alert("ingresar descripcion de poblacion");
			document.getElementById("txaDescripcionPoblacion").focus();
			return false;
		}
		return true;
	}

	/***************************VALIDAR CAMPOS RESUMEN***********************/
	function validarResumenObservaciones() {
		var errores = 0;
		var mensaje = "";

		if ($("#cbxTipoResumenProyecto").val() == 0) {
			mensaje += "Debe seleccionar tipo de resumen. \n";
			errores = errores + 1;
		}

		if ($("#txaResumenPerfil").val().length == 0) {
			mensaje += "Debe ingresar el resumen. \n";
			errores = errores + 1;
		}

		if ($("#flagModifica").val()=="0"){
			for ( var p = 0; p < arrayResumenProyecto.length; p++) {
				if (arrayResumenProyecto[p].tipoResumenProyecto == $('#cbxTipoResumenProyecto').attr("value")) {
					mensaje += "El tipo de resumen de perfil que desea ingresa ya se encuentra en la lista. \n";
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

	/***************************VALIDAR CAMPOS EJECUTOR FINANCIAMIENTO***********************/
	function validarEjecutorInformacionFinanciamiento() {

		//Institucion
		if (document.getElementById("txtInstitucion").value.trim().length == 0) {
			alert("ingresar nombre de institucion");
			document.getElementById("txtInstitucion").focus();
			return false;
		}
		if (document.getElementById("txtRuc").value.trim().length == 0) {
			alert("ingresar ruc");
			document.getElementById("txtRuc").focus();
			return false;
		}
		if (document.getElementById("txtDireccion").value.trim().length == 0) {
			alert("ingresar direccion");
			document.getElementById("txtDireccion").focus();
			return false;
		}
		if (document.getElementById("txtTelefono").value.trim().length == 0) {
			alert("ingresar telefono");
			document.getElementById("txtTelefono").focus();
			return false;
		}
		if (document.getElementById("txtRepLegal").value.trim().length == 0) {
			alert("ingresar representante legal");
			document.getElementById("txtRepLegal").focus();
			return false;
		}
		if (document.getElementById("txtContacto").value.trim().length == 0) {
			alert("ingresar contacto");
			document.getElementById("txtContacto").focus();
			return false;
		}
		if (document.getElementById("txtCorreo").value.trim().length == 0) {
			alert("ingresar correo");
			document.getElementById("txtCorreo").focus();
			return false;
		}else{
			var validaEmail=validacorreo($("#txtCorreo").val());
			if(validaEmail==false){
				alert("Formato de correo incorrecto");
				$("#txtCorreo").focus();
				return false;
			}
		}
		
			
		if (document.getElementById("txaObservacion").value.trim().length == 0) {
			alert("ingresar observacion");
			document.getElementById("txaObservacion").focus();
			return false;
		}

		//Fuente Financiadora
		if (document.getElementById("cbxTipoFuenteFinan").value == "0") {
			alert("seleccionar tipo de fuente financiadora");
			document.getElementById("cbxTipoFuenteFinan").focus();
			return false;
		}

		/*if (document.getElementById("cbxTipoMoneda").value == "0") {
			alert("seleccionar tipo de moneda");
			document.getElementById("cbxTipoMoneda").focus();
			return false;
		}*/

		if (document.getElementById("txtMontoFinan").value.trim().length == 0) {
			alert("Ingresar monto financiador");
			document.getElementById("txtMontoFinan").focus();
			return false;
		}

		return true;
	}

	function validarFuenteFinanciadora() {

		//onclick_btnAgregarFuenteFinan();
		if (document.getElementById("cbxTipoFuenteFinan").value == "0") {
			alert("seleccionar tipo de fuente financiera");
			document.getElementById("cbxTipoFuenteFinan").focus();
			return false;
		}
		/*if (document.getElementById("cbxTipoMoneda").value == "0") {
			alert("seleccionar tipo de moneda");
			document.getElementById("cbxTipoMoneda").focus();
			return false;
		}*/
		if (document.getElementById("txtMontoFinan").value.trim().length == 0) {
			alert("Ingresar monto");
			document.getElementById("txtMontoFinan").focus();
			return false;
		}
		return true;
	}

	function onclick_btnMostrarFuenteFinanFondam() {

		var rpt = validarFuenteFinanFondam();
		if (rpt == true) {
			//agregar datos
			var objFuenteFinanciera = new Object();
			objFuenteFinanciera.cbxTipoFuenteFinan = "184"; //fondam
			objFuenteFinanciera.cbxTipoMoneda = document
					.getElementById("cbxTipoMoneda2").value;
			objFuenteFinanciera.txtMontoFinan = document
					.getElementById("txtMontoFondam").value;
			objFuenteFinanciera.cbxInstitucionEjecutora = "0";

			var objInstitucionFuenFinan = new Object();
			// objInstitucionFuenFinan.institucion=objInstitucion;
			//objInstitucionFuenFinan.fuenFinan=objFuenteFinanciera;

			arrayInstitucionFuenFinan[0].fuenFinan = objFuenteFinanciera;

			llenaComboInstitucionEjecutora();

			ocultarFuenteFinanciadora2();
		}
	}

	function validarFuenteFinanFondam() {
		if (document.getElementById("cbxTipoMoneda2").value == "0") {
			alert("seleccionar tipo de moneda");
			document.getElementById("cbxTipoMoneda2").focus();
			return false;
		}
		if (document.getElementById("txtMontoFondam").value.trim().length == 0
				|| document.getElementById("txtMontoFondam").value == "0") {
			alert("ingresar monto");
			document.getElementById("txtMontoFondam").focus();
			return false;
		}
		return true;
	}

	/***************************REGISTRAR PERFIL***********************************/

	var restEntidadEje = new Object();
	/*
	function onclick_btnRegistrarPerfil() {
		//DATOS DE PROYECTO
		var rptDatProy = validarDatosProyecto();
		
		//UBICACION DEL PROYECTO (solo si no hay ninguna ubicacion y rptDatProy=true)
		var ubiProy = true;
		if (rptDatProy == true) {
			restringirEntidadEjecutoraPorPrograma();
			//alert("tam vector ubicacion: "+arrayUbicacionBeneficiarios.length);
			if (arrayUbicacionBeneficiarios.length == 0) {//sino hay ninguna ubicacion 
				alert("Agregar al menos una ubicacion de proyecto!");
				ubiProy = validarUbicacion();
				//if (ubiProy == true)
					//document.getElementById("cbxDepartamentos").focus();
			} else {//cuando hay una o mas ubicaciones 
				var mensaje = "";
				var validaUbiProy =0;
				for ( var i = 0; i < arrayUbicacionBeneficiarios.length; i++) {
					if (arrayUbicacionBeneficiarios[i].beneficiarios == null
							|| arrayUbicacionBeneficiarios[i].beneficiarios.length == 0) {
						mensaje = mensaje + "Agregar Beneficiarios a la ubicacion: "+ arrayUbicacionBeneficiarios[i].cbxDepText +" / "+ arrayUbicacionBeneficiarios[i].cbxProvText +" / "+ arrayUbicacionBeneficiarios[i].cbxDistText +". \n";
						//document.getElementById("btnAgregarUbicacion").focus();
					
						validaUbiProy = validaUbiProy+1;
					}
				}
				if(validaUbiProy > 0){
					ubiProy = false;
				}
				alert(mensaje);
			}
		}

		// DATOS DEL PERFIL RESUMEN OBSERVACIONES
		var perfilResObs = true;
		if (rptDatProy == true && ubiProy == true
				&& arrayUbicacionBeneficiarios.length > 0
		//&& prefilMontos == true
		) {
			if ($("#cantidadResumenPerfil").val() == 0) {
				alert("Agregar al menos un tipo de resumen!");
				document.getElementById("cbxTipoResumenProyecto").focus();
				perfilResObs = false;
			}
			
		}

		// DATOS DE EJECUTOR E INFORMACIÓN FINANCIAMIENTO
		var ejecutorFinan = true; // aqui tengo que hacer el cambio

		if (rptDatProy == true && ubiProy == true
				&& arrayUbicacionBeneficiarios.length > 0
				//&& prefilMontos == true 
				&& perfilResObs == true) {
			//muestra_ambos();
			if (arrayInstitucionFuenFinan.length == 0) { //sino hay ninguna fuente financiamiento 
				alert("Agregar al menos una institucion!");
				ejecutorFinan = validarEjecutorInformacionFinanciamiento();
				if (ejecutorFinan == true)
					document.getElementById("txtInstitucion").focus();
			}

			//validar si el fondam tiene sus moneda y montos como fuente de financiamiento
			var validaFondam = $("#cbxFondoAmericas option:selected").html; //"no";
			if (arrayInstitucionFuenFinan.length > 0
					&& (document.getElementById("cbxTipoMoneda2").value == "0" || document
							.getElementById("txtMontoFondam").value == "")
					&& arrayInstitucionFuenFinan[0].fuenFinan.cbxTipoMoneda == "") {
				validaFondam = "si";
				ejecutorFinan = false;
				alert("Ingresar datos de monto para el Fondo de las Americas");
				document.getElementById("btnAgregarInstitucion").focus();
			}

			if (validaFondam == "no") {
				//si hay ejecutor??
				if (arrayInstitucionFuenFinan.length > 0
						&& document.getElementById("cbxInstitucionEjecutora").value == "0") {
					alert("Seleccionar institucion ejecutora!");
					document.getElementById("cbxInstitucionEjecutora").focus();
					ejecutorFinan = false;
				}
			}
		}

		//alert("ubiProy: "+ubiProy);
		////////////////////////////////////// GRABAR!!!!!!! ////////////////////////////////////// 
		if (rptDatProy == true && ubiProy == true
				&& arrayUbicacionBeneficiarios.length > 0
				//&& prefilMontos == true 
				&& perfilResObs == true && ejecutorFinan == true
				&& arrayInstitucionFuenFinan.length > 0) {

			//restringirEntidadEjecutoraPorPrograma();
			//console.log("despues de la llamada-----------RestringirEntidadEjecutoraPorPrograma: "	+ JSON.stringify(restEntidadEje));
			//alert("restEntidadEje  "+restEntidadEje);
			var rpt = confirm("¿Esta seguro de registrar el perfil? \n\n ADVERTENCIA: Despues de grabar no podra realizar ninguna modificacion, \n la propuesta quedara como se ha ingresado.");
			if (rpt) {
				if (restEntidadEje.existe == "no") {
					//console.log("GRABA DATOS DEL PERFIL -------------------------------!!!");
					mostrarMontosAcumulados(false);
					capturarListasDatos();
					document.crearPerfil.submit();
					alert("Se registro perfil satisfactoriamente!");
				} else {
					alert("El proyecto ya existe en el programa seleccionado!");
					restEntidadEje = new Object();
				}
			}

		}
	}
	*/

	function onclick_btnRegistrarPerfil() {
		//DATOS DE PROYECTO
		var rptDatProy = validarDatosProyecto();
		
		//UBICACION DEL PROYECTO (solo si no hay ninguna ubicacion y rptDatProy=true)
		if (rptDatProy == 0) {
		////////////////////////////////////// GRABAR!!!!!!! ////////////////////////////////////// 
			var rpt = confirm("¿Esta seguro de registrar el perfil? \n\n ADVERTENCIA: Despues de grabar no podra realizar ninguna modificacion, \n la propuesta quedara como se ha ingresado.");
			if (rpt) {
				//if (restEntidadEje.existe == "no") {
					//console.log("GRABA DATOS DEL PERFIL -------------------------------!!!");
					mostrarMontosAcumulados(false);
					capturarListasDatos();
					document.crearPerfil.submit();
					alert("Se registro perfil satisfactoriamente!");
			}
		}
	}

	
	function capturarListasDatos() {
		//alert(JSON.stringify(arrayUbicacionTabla));
		document.getElementById("txtListadoUbicacionBeneficiarios").value = JSON
				.stringify(arrayUbicacionBeneficiarios);
		//document.getElementById("txtListadoActividadesMetas").value=JSON.stringify(arrayActividadesMetas);
		document.getElementById("txtListadoInstitucionFuenFinan").value = JSON
				.stringify(arrayInstitucionFuenFinan);
		document.getElementById("txtListadoResumenProyecto").value = JSON
				.stringify(arrayResumenProyecto);
		$("#nombreDocumentoAdjunto").attr("value",$("#imagenODocumento").val());
	}

	var objXHR2256308;
	var nombreProy;
	var codProg;
	function restringirEntidadEjecutoraPorPrograma() {
		objXHR2256308 = new ObjetoAJAX();
		nombreProy = document.getElementById("txtProyecto").value;
		codProg = document.getElementById("cbxPrograma").value;
		var url = "restringirEntidadEjecutoraPorPrograma.jspx?" + "par1="
				+ encodeURIComponent(nombreProy) + "&par2="
				+ encodeURIComponent(codProg);

		objXHR2256308.enviar(url, "get",
				"obtenerRestringirEntidadEjecutoraPorPrograma", null);
	}

	function obtenerRestringirEntidadEjecutoraPorPrograma() {
		restEntidadEje = JSON.parse(objXHR2256308.respuestaTexto());
		//alert(JSON.stringify(restEntidadEje));
		console
				.log("cuando se hace la llamada-----------RestringirEntidadEjecutoraPorPrograma: "
						+ JSON.stringify(restEntidadEje));
	}

	function mostrarMontosAcumulados(popup) {
		$("#div_montosAcumulados").show('slow');
		console
				.log("ENTRO EN MONTOS ACUMULADOS   arrayInstitucionFuenFinan.length= "
						+ arrayInstitucionFuenFinan.length);
		if (arrayInstitucionFuenFinan.length > 0) {
			console.log("document.getElementById('cbxFondoAmericas').value "
					+ document.getElementById("cbxFondoAmericas").value);

			if (verificarMontoFinanFondam()) {

				if (document.getElementById("cbxFondoAmericas").value == "1") {
					MONTO_FONDAM = parseFloat(document
							.getElementById("txtMontoFondam").value);
				} else {
					MONTO_FONDAM = parseFloat("0");
				}

				//inicializar monntos
				MONTO_CONTRAPARTIDA = parseFloat("0");
				MONTO_COFINANCIADOR = parseFloat("0");
				//obtener monto cofinanciador y contrapartida
				for ( var i = 0; i < arrayInstitucionFuenFinan.length; i++) {
					if (arrayInstitucionFuenFinan[i].fuenFinan.cbxTipoFuenteFinan == FUENTE_FINAN_COFINANCIADOR) {
						MONTO_COFINANCIADOR = MONTO_COFINANCIADOR
								+ parseFloat(arrayInstitucionFuenFinan[i].fuenFinan.txtMontoFinan);
					} else if (arrayInstitucionFuenFinan[i].fuenFinan.cbxTipoFuenteFinan == FUENTE_FINAN_EJECUTOR
							|| arrayInstitucionFuenFinan[i].fuenFinan.cbxTipoFuenteFinan == FUENTE_FINAN_BENEFICIARIOS
							|| arrayInstitucionFuenFinan[i].fuenFinan.cbxTipoFuenteFinan == FUENTE_FINAN_OTROS) {
						MONTO_CONTRAPARTIDA = MONTO_CONTRAPARTIDA
								+ parseFloat(arrayInstitucionFuenFinan[i].fuenFinan.txtMontoFinan);
					}
				}

				var final_fondam = MONTO_FONDAM;
				var final_contrapartida = MONTO_CONTRAPARTIDA;
				var final_cofinanciador = MONTO_COFINANCIADOR;

				//guardar en el formulario los hidenn
				document.getElementById("txtFondo").value = final_fondam
						.toString();
				document.getElementById("txtContrapartida").value = final_contrapartida
						.toString();
				document.getElementById("txtCofinanciador").value = final_cofinanciador
						.toString();

				if (popup) {

					fOpenModalDialog(
							'showMontosAcumulados.jspx?'
									+ 'montoFondam='
									+ encodeURIComponent(final_fondam
											.toString())
									+ '&montoContrapartida='
									+ encodeURIComponent(final_contrapartida
											.toString())
									+ '&montoCofinanciador='
									+ encodeURIComponent(final_cofinanciador
											.toString()), '500', '200', '150',
							'200');

				}

			}
		} else {
			alert("no hay institucion registrada!");
			MONTO_FONDAM = parseFloat("0");
			MONTO_COFINANCIADOR = parseFloat("0");
			MONTO_CONTRAPARTIDA = parseFloat("0");
		}
	}

	function verificarMontoFinanFondam() {
		//alert("RUCFONDAM: "+RUCFONDAM);
		for ( var i = 0; i < arrayInstitucionFuenFinan.length; i++) {
			if (arrayInstitucionFuenFinan[i].institucion.txtRuc == RUCFONDAM
					&& arrayInstitucionFuenFinan[i].fuenFinan.txtMontoFinan == "0") {
				alert("ingresar monto de financiamiento para el Fondam!");
				$("#div_fuenteFinanciadoraFondam").show('slow');
				return false;
			}
			return true;
		}

	}
</script>

<script type="text/javascript">
	//////////////////////////////////////////SCRIPT EJECUTADO AL CARGAR LA PAGINA
	arrayResumenProyecto = new Array();
	arrayInstitucionFuenFinan = new Array();
	
	window.onload = function() {
		//INICIALIZAR ARRAY INSTITUCIONES
		//arrayInstitucionFuenFinan = new Array();
		//arrayResumenProyecto = new Array();
		//MONTOS				
		MONTO_FONDAM = parseFloat("0");
		MONTO_COFINANCIADOR = parseFloat("0");
		MONTO_CONTRAPARTIDA = parseFloat("0");
		//
		document.getElementById("cbxFondoAmericas").value = "2";
	};

	//carga antes del load
	$(document).ready(function() {

		////DATOS DEL PROYECTO
		//cargaComboModFinanProg();
		//cargaComboArea_AreaSubTematica();
		////UBICACION DEL PROYECTO
		//cargaCombosDepProvDist();
		//cargaCombosEstratos();
		//cargaComboTipoResumenProyecto();
		cargaComboTipoMoneda();
		cargaComboTipoFuenteFinanciadora();

		obtenerDatosFondam();

		$("#div_montosAcumulados").hide();

	});

	function deshabilitarCombosDepProvDist() {
		document.getElementById("cbxDepartamentos").disabled = true;
		document.getElementById("cbxProvincias").disabled = true;
		document.getElementById("cbxDistritos").disabled = true;
	}

	function validarRestriccionDuracionProyecto() {

		//alert("aver:" + $("#txtDuracion").val());
		var duracionProyecto = $("#txtDuracion").val();
		if (duracionProyecto > restDuracionProy) {
			alert("La duracion maxima del proyecto exede de la duracion maxima del programa: ( "
					+ restDuracionProy + " ) ");
			$("#txtDuracion").val("");
			document.getElementById("txtDuracion").focus();
		}
	}
</script>

<script type="text/javascript">

function cargarModalidadFinanciamiento(){
	$("#cbxPrograma").load("cargarCombo.jspx", {modalidadFinanciamientoId:$("#cbxModalidadFinan").val(),metodo:"cargarProgramas"});
	
}

function cargarRestricAreaTematica(){
	$("#cbxAreaTematica").load("cargarCombo.jspx", {programaId:$("#cbxPrograma").val(),metodo:"cargarRestricAreaTematica"}, function(){
		cargarRestricSubAreaTematica();
	});
	
}
function cargarRestricSubAreaTematica(){
	$("#cbxSubAreaTematica").load("cargarCombo.jspx", {programaId:$("#cbxPrograma").val(),areaTematicaId:$("#cbxAreaTematica").val(),metodo:"cargarRestricSubAreaTematica"});
	
}
function cargaRestricDepartamentos(){
	$("#cbxDepartamentos").load("cargarCombo.jspx", {programaId:$("#cbxPrograma").val(),metodo:"cargarRestricDepartamento"}, function(){
		cargaRestricProvincia();
		cargaRestricDistrito();
	});
}

function cargaRestricProvincia(){
	$("#cbxProvincias").load("cargarCombo.jspx", {programaId:$("#cbxPrograma").val(),departamentoId:$("#cbxDepartamentos").val(),metodo:"cargaRestricProvincia"}, function(){
		cargaRestricDistrito();
	});
}

function cargaRestricDistrito(){
	$("#cbxDistritos").load("cargarCombo.jspx", {programaId:$("#cbxPrograma").val(),departamentoId:$("#cbxDepartamentos").val(),provinciaId:$("#cbxProvincias").val(),metodo:"cargaRestricDistrito"});
}

function cargaGrillaResumenProyecto(){
	$("#divGrillaResumenProyecto").load("cargaGrillaResumenProyecto.jspx",{
		tipoResumenId : $("#cbxTipoResumenProyecto").val(),
		resumen : $("#txaResumenPerfil").val(),
		flagModifica : $("#flagModifica").val()
	});	
}

function limpiaDuracionProyecto(){
	$("#txtDuracion").attr("value","");
}
</script>


	<form:form commandName="imagenOArchivoTempBean"  method="POST" 
				action="registrarPerfil.jspx" cssClass="form-clasico"
				enctype="multipart/form-data" id="crearPerfil" name="crearPerfil">

		<input type="hidden" id="txtFondo" name="txtFondo"> <input
			type="hidden" id="txtContrapartida" name="txtContrapartida">
		<input type="hidden" id="txtCofinanciador" name="txtCofinanciador">

		<div class="encabezado">CREAR PERFIL</div>
		<br>
		<!-- <div id="proyecto_div"> -->

		<fieldset>
			<legend>DATOS DE PROYECTO</legend>
			<table width="100%">
				<tr>
					<td style="text-align: right; width: 25%; vertical-align: top;"><label>Proyecto:</label>
					</td>
					<td colspan="3" style="text-align: left; width: 75%;"><textarea
							id="txtProyecto" name="txtProyecto" rows="3" cols="5"
							maxlength="200" style="width: 99%;"></textarea></td>
				</tr>
				<tr>
					<td style="text-align: right; width: 25%;"><label>Modalidad
							Finaciamiento:</label></td>
					<td style="text-align: left; width: 25%;">
							<select name="cbxModalidadFinan" onchange="cargarModalidadFinanciamiento()"
							id="cbxModalidadFinan" style="width: 100%;">
								<option value="0">
									<c:out value="-- Modalidad Financiamiento --" />
								</option>
								<c:forEach items="${listModalidadFinanciamiento}" var="modalidadFinanciamiento">
									<option value="${modalidadFinanciamiento.tablaEspecificaID}">
										<c:out value="${modalidadFinanciamiento.descripcionCabecera}" />
									</option>
								</c:forEach>
						</select>
					</td>
					<td style="text-align: right; width: 25%;"><label>Programa:</label>
					</td>
					<td style="text-align: left; width: 25%;">
							<select name="cbxPrograma" id="cbxPrograma" onchange="cargarRestricAreaTematica(); cargaRestricDepartamentos();restriccionDuracionProyecto();limpiaDuracionProyecto();"  style="width: 100%;">
								<option value="0">-- Programas --</option>
							</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right; width: 25%;"><label>Area
							Tematica:</label></td>
					<td style="text-align: left; width: 25%;">
							<select name="cbxAreaTematica" id="cbxAreaTematica" onchange="cargarRestricSubAreaTematica()"  style="width: 100%;">
								<option value="0">-- Area Tematica --</option>
							</select>
					</td>
					<td style="text-align: right; width: 25%;"><label>Sub
							area Tematica:</label></td>
					<td style="text-align: left; width: 25%;">
							<select name="cbxSubAreaTematica" id="cbxSubAreaTematica"  style="width: 100%;">
								<option value="0">-- Sub Area Tematica --</option>
							</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right; width: 25%;"><label>Duraci&oacute;n
							(meses):</label></td>
					<td style="text-align: left; width: 25%;"><input
						name="txtDuracion" id="txtDuracion" type="text"
						onkeypress="javascript:return Valida_Dato(event,8);" maxlength="2"
						onblur="validarRestriccionDuracionProyecto()" /></td>
					<td colspan="2" style="width: 50%;"></td>
				</tr>
				<tr>
					<td style="text-align: right;"><label>Documento (pdf,docx, rar,  zip):</label></td>
					<td colspan="3">
							<div>
							<spring:bind path="imagenODocumento">
											<input type="file" name="imagenODocumento" id="imagenODocumento" />
										</spring:bind>
										</div>
										<input type="hidden" id="nombreDocumentoAdjunto" name="nombreDocumentoAdjunto">
										<input type="hidden" id="nombreArchivo" name="nombreArchivo">
										<input type="hidden" id="extension" name="extension">
							</td>
				</tr>
				<tr>
					<td colspan="4" style="text-align: right;"><input
						type="button" name="btnRegistrarPerfil" id="btnRegistrarPerfil"
						value="Registrar perfil" onmousedown="validarUpload();"
						onclick="onclick_btnRegistrarPerfil();" /></td>
				</tr>
			</table>
		</fieldset>
		<br>
		<div id="tab-container" class="tab-container" style="width: 100%;">
			<ul class='etabs'>
				<li class='tab'><a href="#ubicacionProyecto">Ubicacion de
						Proyecto</a></li>
				<li class='tab'><a href="#resumenObservacionesPerfil" onclick="cargaGrillaResumenProyecto()">Resumen
						&amp; Observaciones de Perfil</a></li>
				<li class='tab'><a href="#institucion">Fuentes de
						Financiamiento</a></li>
			</ul>
			<div class='panel-container'>
				<div id="ubicacionProyecto">
					<br />
					<fieldset>
						<legend>UBICACION DEL PROYECTO</legend>
						<table width="100%">
							<tr>
								<td colspan="2" style="width: 50%;">
									<table width="100%">
										<tr>
								<td style="width: 40%; text-align: right;" ><label>Departamento:</label>
								</td>
								<td style="width: 50%; text-align: left;">
										<select name="cbxDepartamentos" id="cbxDepartamentos" onchange="cargaRestricProvincia()">
											<option value="0">-- Departamento --</option>
										</select>
								</td>
							</tr>
							<tr>	
								<td style="width: 40%;text-align: right;" ><label>Provincia:</label>
								</td>
								<td style="width: 50%;text-align: left;">
										<select name="cbxProvincias" id="cbxProvincias" onchange="cargaRestricDistrito()">
											<option value="0">-- Provincia --</option>
										</select>
								</td>
							</tr>
							<tr>
							<td style="width: 40%;text-align: right;"><label>Distrito:</label></td>
								<td style="width: 50%;text-align: left;">
										<select name="cbxDistritos" id="cbxDistritos">
											<option value="0">-- Distrito --</option>
										</select>
								</td>
							</tr>		
									</table>
								</td>
								<td style="text-align: right;width: 15%;vertical-align: top;"><label>Localizacion:</label></td>
								<td style="text-align: left;width: 35%;"><textarea id="txaLocalizacion" name="txaLocalizacion"
										COLS="50" ROWS="4" maxlength="350"></textarea></td>
							</tr>
							<tr>
								<td height="63" colspan="4">
									<table width="90%" align="center" border="0">
										<tr height="40px" valign="middle">
											<td align="right"><input type="button"
												name="btnAgregarUbicacion" id="btnAgregarUbicacion"
												value="Agregar ubicacion"
												onclick="onclick_btnAgregarUbicacion();" /> <!-- onclick="agregarUbicacion();limpiarCamposUbicacionProyecto()" -->
											</td>
											<!-- <input type="button" value="nro" onclick="conteoUbicaciones()"> -->
										</tr>
									</table>
									<table class="table-clasico" width="100%">
										<!-- class="table-clasico"-->
										<caption>Lista de Ubicaciones</caption>
										<thead>
											<tr>
												<th style="width: 2%; text-align: center;"><label>N°</label>
												</th>
												<th style="width: 15%; text-align: center;"><label>Dep.</label>
												</th>
												<th style="width: 15%; text-align: center;"><label>Prov.</label>
												</th>
												<th style="width: 15%; text-align: center;"><label>Dist.</label>
												</th>
												<th style="width: 40%; text-align: center;"><label>Localización.</label>
												</th>
												<th style="width: 8%; text-align: center;"><label>Estrato</label>
												</th>
												<th style="width: 5%; text-align: center;">&nbsp;</th>
											</tr>
										</thead>
										<tbody id="tbodyUbiProy">
										</tbody>
									</table>
								</td>
							</tr>
						</table>
						<br>

						<div id="div_beneficiarios" style="width: 100%">
							<fieldset>
								<legend>
									Estrato de :&nbsp;<span id="dep_Prov_Dist"></span>
								</legend>
								<table border="0" align="center" width="100%">
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
										</select></td>
									</tr>
									<tr>
										<td align="right" style="width: 25%; vertical-align: top;"><label>Caracteristicas
												Poblac.:</label>
										</td>
										<td colspan="3" align="left" style="width: 75%;"><textarea
												id="txaCaracPoblacion" name="txaCaracPoblacion" COLS="6"
												ROWS="4" style="width: 98%;"></textarea>
										</td>
									</tr>
									<tr>
										<td align="right" style="width: 25%; vertical-align: top;"><label>Descripcion:</label>
										</td>
										<td colspan="3" align="left" style="width: 75%;"><textarea
												id="txaDescripcionPoblacion" name="txaDescripcionPoblacion"
												COLS="6" ROWS="4" style="width: 98%;"></textarea>
										</td>
									</tr>
									<tr>
										<td colspan="2" style="width: 50%;"></td>
										<td colspan="2" style="width: 50%;" align="right"><input
											type="button" name="btnAgregarEstrato" id="btnAgregarEstrato"
											value="Agregar estrato"
											onclick="onclick_btnAgregarEstrato();" style="margin: 10px;" />
											<input type="button" value="Ocultar"
											onclick="onclick_btnCerrar()" style="margin: 10px;">
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
											<th style="width: 5%; text-align: center;"><label>N°</label>
											</th>
											<th style="width: 15%; text-align: center;"><label>Beneficiarios<br />Estrato</label>
											</th>
											<th style="width: 10%; text-align: center;"><label>Tipo
													Ben.</label>
											</th>
											<th style="width: 32%; text-align: center;"><label>Carac.
													Poblacion</label>
											</th>
											<th style="width: 33%; text-align: center;"><label>Descripción</label>
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
				<div id="resumenObservacionesPerfil">
					<br />
					<fieldset>
						<legend>RESUMEN Y OBSERVACIONES DEL PERFIL</legend>
						<table width="100%" border="0" align="center">

							<tr>
								<td style="width: 25%; text-align: right;"><label>Tipo
										de Resumen de Perfil:</label></td>
								<td colspan="3" style="width: 75%; text-align: left;"><select
									name="cbxTipoResumenProyecto" id="cbxTipoResumenProyecto"
									style="width: 98%;">
										<option value="0">-- Tipo de Resumen de Proyecto--</option>
										<c:forEach items="${listTipoResumenPerfil}"
											var="tipoResumenPerfil">
											<option value="${tipoResumenPerfil.tablaEspecificaID}">
												<c:out value="${tipoResumenPerfil.descripcionCabecera }" />
											</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td style="width: 25%; text-align: right; vertical-align: top;"><label>Resumen:</label>
								</td>
								<td colspan="3" style="width: 75%; text-align: left;"><textarea
										id="txaResumenPerfil" name="txaResumenPerfil" COLS="50"
										ROWS="8" style="width: 99%;"></textarea></td>
							</tr>
							<tr>
								<td colspan="4" style="width: 100%; text-align: right;"><input
									type="button" name="btnAgregarResumen" id="btnAgregarResumen"
									value="Agregar Resumen" onclick="onclick_btnAgregarResumen();" />
									<!--  <input type="button"  value="test resumen" onclick="testearResumen()"/> -->
								</td>
							</tr>
							<tr>
								<td colspan="4" style="width: 100%;"><br>
									<div id="divGrillaResumenProyecto">
										<input type="hidden" id="cantidadResumenPerfil" name="cantidadResumenPerfil" value='0'>
									</div>
									<input type="hidden" id="flagModifica" name="flagModifica" value="0">
									<!--<table class="table-clasico" width="100%">
										
										<caption>Listado de Resumenes y Observaciones</caption>
										<thead>
											<tr>
												<th><label>nro</label></th>
												<th><label>Tipo.</label></th>
												<th><label>Resumen</label></th>
												<th><label>Eliminar</label>
												</th>
											</tr>
										</thead>
										<tbody id="tbodyResumenObserv">
										</tbody>
									</table> -->
								</td>
							</tr>
						</table>
					</fieldset>
				</div>
				<div id="institucion">
					<br />
					<fieldset>
						<legend>EJECUTOR E INFORMACI&Oacute;N FINANCIAMIENTO</legend>
						<br>
						<table>
							<tr>
								<td><label>Contar con el Fondo de las Americas? :</label></td>
								<td><select id="cbxFondoAmericas" name="cbxFondoAmericas"
									onchange="registroFonfoInstitucion()">
										<option value="2">No</option>
										<option value="1">Si</option>
								</select>
								</td>
							</tr>
						</table>
						<br>
						<div id="div_datosInst">
							<fieldset>
								<legend>Datos Institucion</legend>
								<table width="100%">
									<tr>
										<td style="text-align: right;vertical-align: top;"><label>Instituci&oacute;n:</label>
										</td>
										<td colspan="3"><textarea id="txtInstitucion"
												name="txtInstitucion" maxlength="350" style="width: 98%;"></textarea>
										</td>
									</tr>
									<tr>
										<td style="text-align: right;"><label>RUC:</label></td>
										<td colspan="3"><input id="txtRuc" name="txtRuc"
											type="text" maxlength="11"
											onkeypress="javascript:return Valida_Dato(event,8);" /></td>

									</tr>
									<tr>
										<td style="text-align: right;"><label>Representante
												Legal:</label></td>
										<td><input id="txtRepLegal" name="txtRepLegal"
											type="text" maxlength="40"
											onkeypress="javascript:return Valida_Dato(event,6);" /></td>

										<td style="text-align: right;"><label>Tel&eacute;fono:</label>
										</td>
										<td><input id="txtTelefono" name="txtTelefono"
											type="text" maxlength="15"
											onkeypress="javascript:return Valida_Dato(event,8);" /></td>
									</tr>
									<tr>
										<td style="text-align: right;"><label>Contacto:</label></td>
										<td><input id="txtContacto" name="txtContacto"
											type="text" maxlength="40"
											onkeypress="javascript:return Valida_Dato(event,6);" /></td>
										<td style="text-align: right;"><label>Correo:</label></td>
										<td><input id="txtCorreo" name="txtCorreo" type="text"
											maxlength="40"
											onkeypress="javascript:return Valida_Dato(event,7);" /></td>
									</tr>
									<tr>
										<td style="text-align: right;vertical-align: top;"><label>Direcci&oacute;n:</label>
										</td>
										<td colspan="3"><textarea id="txtDireccion"
												name="txtDireccion" maxlength="200" style="width: 98%;"></textarea>
										</td>
									</tr>
									<tr>
										<td style="text-align: right;vertical-align: top;"><label>Observaci&oacute;n:</label>
										</td>
										<td colspan="3"><textarea id="txaObservacion"
												name="txaObservacion" rows="3" style="width: 98%;"
												maxlength="200"></textarea></td>
									</tr>
									<tr>
										<td align="right" colspan="4"><input type="button"
											name="btnAgregarInstitucion" id="btnAgregarInstitucion"
											value="Agregar Institucion"
											onclick="onclick_btnAgregarInstitucion()" /> <!-- <input type="button" name="btnTestAgregarInstitucion" id="btnTestAgregarInstitucion"  
							onclick="testAgregarInstitucion()">--> <br></td>
									</tr>
								</table>


								<div id="div_fuenFinan">
									<fieldset>
										<legend>Fuente Financiadora</legend>
										<table width="100%">
											<tr>
												<td style="text-align: right;"><label>Tipo
														Fuente Financiadora:</label></td>
												<td><select name="cbxTipoFuenteFinan"
													id="cbxTipoFuenteFinan" style="width: 150px;"
													onchange="cargaCajasInstitucion()">
														<option value="0">--Fuente Financiadora--</option>
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
														Moneda:</label></td>
												<td><select name="cbxTipoMoneda" id="cbxTipoMoneda"
													disabled="disabled">
														<option value="0">--Moneda--</option>
														<c:forEach items="${listMoneda}" var="moneda">
															<c:choose>
																<c:when test="${moneda.tablaEspecificaID =='169'}">
																	<option selected="selected"
																		value="${moneda.tablaEspecificaID}">
																		<c:out value="${moneda.descripcionCabecera }" />
																	</option>
																</c:when>
																<c:otherwise>
																	<option value="${moneda.tablaEspecificaID}">
																		<c:out value="${moneda.descripcionCabecera }" />
																	</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
												</select></td>
											</tr>
											<tr>
												<td style="text-align: right;"><label>Monto:</label></td>
												<td><input name="txtMontoFinan" id="txtMontoFinan"
													type="text" maxlength="6"
													onkeypress="javascript:return Valida_Dato(event,8);" /></td>
												<td style="text-align: right;">&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
										</table>
									</fieldset>
								</div>
							</fieldset>
						</div>
						<br>
						<table class="table-clasico" width="100%">

							<caption>
								Lista de Instituciones
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" value="Montos Acumulados"
									onclick="mostrarMontosAcumulados(true)" />
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
											Ejecutora:</label></td>
									<td>
										<div id="div_cbxInstitucionEjecutora">
											<select name="cbxInstitucionEjecutora"
												id="cbxInstitucionEjecutora">
												<option value="0">-- Ejecutor --</option>
											</select>
										</div>
									</td>
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
												Fuente Financiadora:</label></td>
										<td><label><span id="spanTipoFuenteFinan"></span>
										</label>
										</td>
										<td style="text-align: right; width: 25%;"><label>Tipo
												Moneda:</label></td>
										<td><label><span id="spanTipoMoneda"></span> </label>
										</td>
									</tr>
									<tr>
										<td style="text-align: right; width: 25%;"><label>Monto:</label>
										</td>
										<td><label><span id="spanMontoFinan"></span> </label>
										</td>
										<td style="text-align: right;">&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>

								<table width="90%" align="center" border="0">
									<tr height="40px" valign="middle">
										<td align="right"><input type="button"
											id="btnMostrarFuenteFinan" value="OK"
											onclick="ocultarFuenteFinanciadora();" /> <!-- onclick="onclick_btnAgregarFuenteFinan();" -->
										</td>
									</tr>
								</table>
							</fieldset>
						</div>
						<br>
						<div id="div_fuenteFinanciadoraFondam">
							<fieldset>
								<legend>
									Fuente Financiadora:&nbsp;<span id="nomIns"></span>
								</legend>
								<table width="100%">
									<tr>
										<td style="text-align: right; width: 25%;"><label>Tipo
												Fuente Financiadora:</label></td>
										<td><label><span id="spanTipoFuenteFinan">Fondo
													de las Americas.</span> </label>
										</td>
										<td style="text-align: right; width: 25%;"><label>Tipo
												Moneda:</label></td>
										<td><select name="cbxTipoMoneda2" id="cbxTipoMoneda2"
											disabled="disabled">
												<option value="0">--Moneda--</option>
												<c:forEach items="${listMoneda}" var="moneda">
													<c:choose>
														<c:when test="${moneda.tablaEspecificaID ==169}">
															<option selected="selected"
																value="${moneda.tablaEspecificaID}">
																<c:out value="${moneda.descripcionCabecera }" />
															</option>
														</c:when>
														<c:otherwise>
															<option value="${moneda.tablaEspecificaID}">
																<c:out value="${moneda.descripcionCabecera }" />
															</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
										</select></td>
									</tr>
									<tr>
										<td style="text-align: right; width: 25%;"><label>Monto:</label>
										</td>
										<td><label><input id="txtMontoFondam"
												name="txtMontoFondam" type="text" maxlength="6"
												onkeypress="javascript:return Valida_Dato(event,8);"
												value="0"> </label>
										</td>
										<td style="text-align: right;">&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>

								<table width="90%" align="center" border="0">
									<tr height="40px" valign="middle">
										<td align="right"><input type="button"
											id="btnMostrarFuenteFinanFondam" value="OK"
											onclick="onclick_btnMostrarFuenteFinanFondam();" /> <!-- onclick="onclick_btnAgregarFuenteFinan();" -->
										</td>
									</tr>
								</table>
							</fieldset>

						</div>

					</fieldset>
				</div>
			</div>
		</div>
		<input type="hidden" id="txtListadoInstitucionFuenFinan"
			name="txtListadoInstitucionFuenFinan"/>
		<input type="hidden" id="txtListadoUbicacionBeneficiarios"
			name="txtListadoUbicacionBeneficiarios" />
		<!-- 
<input type="text" id="nombreArchivo" name="nombreArchivo" style="visibility:hidden"/>
<input type="text" id="extension" name="extension" style="visibility:hidden"/>
 -->
		<input type="hidden" id="txtListadoResumenProyecto"
			name="txtListadoResumenProyecto" /> <input
			type="hidden" id="txtListadoActividadesMetas"
			name="txtListadoActividadesMetas" />
	
		<input type="hidden" id="hiddenIdPrograma" name="hiddenIdPrograma">
	</form:form>

