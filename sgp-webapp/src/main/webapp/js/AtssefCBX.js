
// AtssefCBX :: Combobox Dinamico :: version 1.1

function AtssefCBX(arrayAtssefCBX){
	console.log("AtssefCBX version 1.1 " + " RENDERIZANDO " + arrayAtssefCBX.AttributeCBX.id);
	
	var indiceArray=null;
	
	this.init=m_init();
	this.getIdAtssefCBX=m_getIdAtssefCBX;
		
	function m_init(){
		var CBX = document.createElement("select");
		CBX.setAttribute("id", arrayAtssefCBX.AttributeCBX.id);
		CBX.setAttribute("name",arrayAtssefCBX.AttributeCBX.name);
		
		//<select size="5" style="width:120px">
		CBX.setAttribute("style",arrayAtssefCBX.AttributeCBX.style);
		
		if(arrayAtssefCBX.funcionCBX.onchange.estado!="" && arrayAtssefCBX.funcionCBX.onchange.estado=="1"){
		    CBX.onchange=function(){eval(arrayAtssefCBX.funcionCBX.onchange.funcion);};
		}
		var op = document.createElement("option");
		op.setAttribute("value",arrayAtssefCBX.AttributeOPIni.value);
		var text=document.createTextNode(arrayAtssefCBX.AttributeOPIni.text);
		op.appendChild(text);
		CBX.appendChild(op);
		console.log("Data :"+ arrayAtssefCBX.AttributeCBX.id +" :: "+ JSON.stringify(arrayAtssefCBX.dataCBX));
		for(var i=0; i<arrayAtssefCBX.dataCBX.length; i++){
			op = document.createElement("option");
			op.setAttribute("value",arrayAtssefCBX.dataCBX[i].value);
			text=document.createTextNode(arrayAtssefCBX.dataCBX[i].text);
			op.appendChild(text);
			CBX.appendChild(op);
		}
	  	document.getElementById(arrayAtssefCBX.IDContenedor).innerHTML="";
	  	document.getElementById(arrayAtssefCBX.IDContenedor).appendChild(CBX);
	}
	
	
	function m_getIdAtssefCBX(){
		return document.getElementById(arrayAtssefCBX.AttributeCBX.id).value;
	}
}


/*var CBX = document.createElement("select");
CBX.setAttribute("id", arrayAtssefCBX.AttributeCBX.id);
CBX.setAttribute("name",arrayAtssefCBX.AttributeCBX.name);
if(arrayAtssefCBX.funcionCBX.onchange.estado!="" && arrayAtssefCBX.funcionCBX.onchange.estado=="1"){
    CBX.onchange=function(){eval(arrayAtssefCBX.funcionCBX.onchange.funcion);};
}
var op = document.createElement("option");
op.setAttribute("value",arrayAtssefCBX.AttributeOPIni.value);
var text=document.createTextNode(arrayAtssefCBX.AttributeOPIni.text);
op.appendChild(text);
CBX.appendChild(op);
for(var i=0; i<arrayAtssefCBX.dataCBX.length; i++){
	op = document.createElement("option");
	op.setAttribute("value",arrayAtssefCBX.dataCBX[i].value);
	text=document.createTextNode(arrayAtssefCBX.dataCBX[i].text);
	op.appendChild(text);
	CBX.appendChild(op);
}
	document.getElementById(arrayAtssefCBX.IDContenedor).innerHTML="";
	document.getElementById(arrayAtssefCBX.IDContenedor).appendChild(CBX);	*/
