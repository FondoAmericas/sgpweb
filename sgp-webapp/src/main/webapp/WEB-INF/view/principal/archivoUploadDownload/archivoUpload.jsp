<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/viewMain.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloContabilidad.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloGeneral.css" />
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/gestorAjax.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/displaytagex.css" type="text/css" />
<link type="text/css"
	href="<%=request.getContextPath()%>/css/custom-theme/jquery-ui-1.8.12.custom.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-ui-1.8.12.custom.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.ui.datepicker-es.js"></script>
<script type="text/javascript">

function onclick_btnGuardarArchivo(){
	var rpt = validarExtensionArchivo();
	//rpt=true;
    if(rpt){
   	 	document.forms[0].action=document.getElementById("peticion").value;
    	document.forms[0].submit();
    	ajaxFunction();
    }
    
}

function validarExtensionArchivo(){
	   extensiones_permitidas = new Array(".doc",".docx",".pdf",".jpg",".jpeg",".JPG",".JPEG",".rar",".zip",".RAR",".ZIP");
	   ruta_archivo=document.getElementById("fileDocumento").value;
	   if(!ruta_archivo){
	      alert("No se ha seleccionado ningun archivo!");
	      return false;
	   }else{
		  arrayNombreArchivo = ruta_archivo.split(".");
		  nombreArchivo=arrayNombreArchivo[0];
		  
		  nombreArchivoUpload=nombreArchivo;//variable en gestorAjax
		  
		  nombreArchivo = nombreArchivo.split('\\');
		  nombreArchivo=nombreArchivo[nombreArchivo.length-1];
		  extension="."+arrayNombreArchivo[1];
		  //alert("nombre de archivo: "+nombreArchivo);
	      //alert("extension de archivo: "+extension);
	      document.getElementById("nombreArchivo").value=nombreArchivo;
	      document.getElementById("extension").value=extension;
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


window.onload=function(){
	//alert("peticion: "+document.getElementById('archivoResp').value);
	var rptFile=document.getElementById('archivoResp').value;
	if(rptFile!="false"){
		//document.getElementById('nomArchivo').value=document.getElementById('nombreArchivoUp').value;//nombreArchivo;
		document.getElementById('nomArchivo1').innerHTML=document.getElementById('nombreArchivoUp').value;//nombreArchivo;
	}
	
 };
 
 
 
 
 
 
 function ajaxFunction()
 {
 	var url = "ajaxUpload.jspx";

 	if (window.XMLHttpRequest) // Non-IE browsers
 	{
 		req = new XMLHttpRequest();
 		req.onreadystatechange = processStateChange;
                 
 		try
 		{
 			req.open("GET", url, true);
 		}
 		catch (e)
 		{
 			alert(e);
 		}
 		req.send(null);
 	}
 	else if (window.ActiveXObject) // IE Browsers
 	{
 		req = new ActiveXObject("Microsoft.XMLHTTP");

 		if (req)
 		{
 			req.onreadystatechange = processStateChange;
 			req.open("GET", url, true);
 			req.send();
 		}
 	}
 }

 function processStateChange()
 {
 	/**
 	 *	State	Description
 	 *	0		The request is not initialized
 	 *	1		The request has been set up
 	 *	2		The request has been sent
 	 *	3		The request is in process
 	 *	4		The request is complete
 	 */
 	if (req.readyState == 4)
 	{
 		if (req.status == 200) // OK response
 		{
 			var xml = req.responseXML;

 			// No need to iterate since there will only be one set of lines
 			var isNotFinished = xml.getElementsByTagName("finished")[0];
 			var myBytesRead = xml.getElementsByTagName("bytes_read")[0];
 			var myContentLength = xml.getElementsByTagName("content_length")[0];
 			var myPercent = xml.getElementsByTagName("percent_complete")[0];

 			// Check to see if it's even started yet
 			if ((isNotFinished == null) && (myPercent == null))
 			{
 				document.getElementById("initializing").style.visibility = "visible";

 				// Sleep then call the function again
 				window.setTimeout("ajaxFunction();", 100);
 			}
 			else
 			{
 				document.getElementById("initializing").style.visibility = "hidden";
 				document.getElementById("progressBarTable").style.visibility = "visible";
 				document.getElementById("percentCompleteTable").style.visibility = "visible";
 				document.getElementById("bytesRead").style.visibility = "visible";

 				myBytesRead = myBytesRead.firstChild.data;
 				myContentLength = myContentLength.firstChild.data;

 				if (myPercent != null) // It's started, get the status of the upload
 				{
 					myPercent = myPercent.firstChild.data;

 					document.getElementById("progressBar").style.width = myPercent + "%";
 					document.getElementById("bytesRead").innerHTML = myBytesRead + " of " +
 						myContentLength + " bytes read";
 					document.getElementById("percentComplete").innerHTML = myPercent + "%";

 					// Sleep then call the function again
 					window.setTimeout("ajaxFunction();", 100);
 				}
 				else
 				{
 					document.getElementById("bytesRead").style.visibility = "hidden";
 					document.getElementById("progressBar").style.width = "100%";
 					document.getElementById("percentComplete").innerHTML = "Done!";
 				}
 			}
 		}
 		else
 		{
 			alert(req.statusText);
 		}
 	}
 }


</script>
</head>
<body>
<!-- action="archivoUpload.jspx" -->
<div>
<form id="formArchivo" method="post"  class="form-clasico" enctype="multipart/form-data" accept-charset="UTF-8">
 
   <input type="file" id="fileDocumento" name="fileDocumento" style="margin-right: 55px;"/>
   <input type="button" value="Subir al Servidor" id="ok" onclick="onclick_btnGuardarArchivo();"> 
   <!-- <input type="text" id="nomArchivo" name="nomArchivo" value="xxx" size="80"/>-->
   <br/>
   <label><span id="nomArchivo1" ></span></label>
   <input type="hidden" id="nombreArchivoUp" name="nombreArchivoUp" value="${nombreArchivoUP}"/> 
 
   <input type="text" id="nombreArchivo" name="nombreArchivo" style="visibility:hidden"/>
   <input type="text" id="extension" name="extension" style="visibility:hidden"/>
   
   <input type="hidden" id="peticion" name="peticion" value="${peticion}"/>
   
   
</form>
<input type="hidden" id="archivoResp" name="archivoResp" value="${archivoResp}"/>



<!-- Add hidden DIVs for updating and the progress bar (just a table) below the form -->
	<div id="initializing" style="visibility: hidden; position: absolute; top: 80px;">
		<table width="100%" style="border: 1px; background-color: black;">
			<tr>
				<td>
					<table width="100%" style="border: 1px; background-color: black; color: white;">
						<tr>
							<td align="center">
								<b>Initializing Upload...</b>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>

	<div id="progressBarTable" style="visibility: hidden; position: absolute; top: 80px;">
		<table width="100%" style="border: 1px; background-color: black; color: white;">
			<tr>
				<td>
					<table id="progressBar" width="0px"
						style="border: 1px; width: 0px; background-color: blue;">
						<tr>
							<td>&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table width="100%" style="background-color: white; color: black;">
			<tr>
				<td align="center" nowrap="nowrap">
					<span id="bytesRead" style="font-weight: bold;">&nbsp;</span>
				</td>
			</tr>
		</table>
	</div>

	<div id="percentCompleteTable" align="center"
		style="visibility: hidden; position: absolute; top: 80px;">
		<table width="100%" style="border: 1px;">
			<tr>
				<td>
					<table width="100%" style="border: 1px;">
						<tr>
							<td align="center" nowrap="nowrap">
 								<span id="percentComplete" style="color: white; font-weight: bold;">&nbsp;</span>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
   	</div>



</div>
</body>
</html>