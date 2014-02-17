<%@ include file="/common/taglibs.jsp"%>

<c:if test="${fn:length(listLinkFile)>0}">
	<c:forEach items="${listLinkFile}" var="linkFile">
		<div style="float: left;">
		<c:if test="${linkFile.extension == '.doc'}">
		   <img src="${pageContext.request.contextPath}/images/icono_doc.jpg"  style="width: 30px; height: 30px;"/>
		</c:if>
		<c:if test="${linkFile.extension == '.docx'}">
		   <img src="${pageContext.request.contextPath}/images/icono_doc.jpg"  style="width: 30px; height: 30px;"/>
		</c:if>
		<c:if test="${linkFile.extension == '.pdf'}">
		   <img src="${pageContext.request.contextPath}/images/icono_pdf.jpg"  style="width: 30px; height: 30px;"/>
		</c:if>
		<c:if test="${linkFile.extension == '.jpg'}">
		   <img src="${pageContext.request.contextPath}/images/icono_jpg.jpg" style="width: 30px; height: 30px;"/>
		</c:if>
		<c:if test="${linkFile.extension == '.jpeg'}">
		   <img src="${pageContext.request.contextPath}/images/icono_jpg.jpg" style="width: 30px; height: 30px;"/>
		</c:if>		
		<c:if test="${linkFile.extension == '.DOC'}">
		   <img src="${pageContext.request.contextPath}/images/icono_doc.jpg"  style="width: 30px; height: 30px;"/>
		</c:if>
		<c:if test="${linkFile.extension == '.DOCX'}">
		   <img src="${pageContext.request.contextPath}/images/icono_doc.jpg"  style="width: 30px; height: 30px;"/>
		</c:if>
		<c:if test="${linkFile.extension == '.PDF'}">
		   <img src="${pageContext.request.contextPath}/images/icono_pdf.jpg"  style="width: 30px; height: 30px;"/>
		</c:if>
		<c:if test="${linkFile.extension == '.JPG'}">
		   <img src="${pageContext.request.contextPath}/images/icono_jpg.jpg" style="width: 30px; height: 30px;"/>
		</c:if>
		<c:if test="${linkFile.extension == '.JPEG'}">
		   <img src="${pageContext.request.contextPath}/images/icono_jpg.jpg" style="width: 30px; height: 30px;"/>
		</c:if>
		<c:if test="${linkFile.extension == '.rar'}">
		   <img src="${pageContext.request.contextPath}/images/icono_rar.jpg" style="width: 30px; height: 30px;"/>
		</c:if>
		<c:if test="${linkFile.extension == '.RAR'}">
		   <img src="${pageContext.request.contextPath}/images/icono_rar.jpg" style="width: 30px; height: 30px;"/>
		</c:if>		
		<c:if test="${linkFile.extension == '.zip'}">
		   <img src="${pageContext.request.contextPath}/images/icono_zip.jpg" style="width: 30px; height: 30px;"/>
		</c:if>
		<c:if test="${linkFile.extension == '.ZIP'}">
		   <img src="${pageContext.request.contextPath}/images/icono_zip.jpg" style="width: 30px; height: 30px;"/>
		</c:if>	
		
		</div>
		<div style="float: left; margin-left: 10px;">
		<a href="${variable}?param=${linkFile.id}">
		   <c:out value="${linkFile.nombre}${linkFile.extension}"/>
		</a>
		<%System.out.println("entro si hay descargas!!"); %>
		</div>
		<br style="clear: both;"/>	  
		
		<c:set var="extencion" value="${linkFile.extension }"></c:set>
	</c:forEach>
</c:if>
<c:if test="${ (fn:length(listLinkFile)==0) || (listLinkFile==null) }">
    <span>No hay archivo para descargar.</span><br>
    <%System.out.println("entro no hay descargas!!"); %>
</c:if>
<input type="hidden" id="extencionArchivoDownload" name="extencionArchivoDownload" value="${extencion }">