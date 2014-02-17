<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="formp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/viewMain.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloContabilidad.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloGeneral.css" />
</head>
<body>
<formp:form method="post" action="#" cssClass="form-clasico">

  <table width="495" border="0" align="center">
    <tr>
      <td colspan="2">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2"><div align="center">FONDO DE LAS AMERICAS</div></td>
    </tr>
    <tr>
      <td width="187">&nbsp;</td>
      <td width="245">&nbsp;</td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>PROYECTO DE:</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>PROYECTO:</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>SUB AREA TEMATICA:</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>ONG / INSTITUCION:</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>ORGANISMO(S) / ASOCIADO(S):</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>INSTITUCION(ES) / COFINANCIADOR(ES):</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2" align="center">UBICACION GEOGRAFICA</td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>DEPARTAMENTO:</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>PROVINCIA:</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>DISTRITO:</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>LOCALIDAD:</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>DONACION DEL FONDO:</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>MONTO DE COFINANCIAMIENTO:</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>MONTO DE CONTRAPARTIDA:</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>FECHA DE INICIO:</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>DURACION (MESES):</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>PERIODO:</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2" align="center">DIRECCION ACTUAL DEL EJECUTOR</td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>DIRECCION:</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>TELEFONO:</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>CORREO:</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>FAX:</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>REPRESENTANTE:</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>DIRECTOR DEL PROYECTO:</label></td>
      <td><input type="text"></td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"><label>DOCUMENTO DE PERFIL:</label></td>
      <td><input type="file" name="txtFile" id="txtFile" /></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table>
  <p>&nbsp;</p>
</formp:form>

</body>
</html>