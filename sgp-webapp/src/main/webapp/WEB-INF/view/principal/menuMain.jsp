<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$(document).ready(function() {
    var posicion = $("#menu").offset();
    var margenSuperior = 15;
     $(window).scroll(function() {
         if ($(window).scrollTop() > posicion.top) {
             $("#menu").stop().animate({
                 marginTop: $(window).scrollTop() - posicion.top + margenSuperior
             });
         } else {
             $("#menu").stop().animate({
                 marginTop: 0
             });
         };
     });
});
</script>


<div class="menu" id="menu">
	<ul style="width: 100%;">
		<c:forEach items="${sessionScope.FUNCIONALIDAD_PERFIL}" var="perfil">
			<li><a id="menu${perfil.funcionalidad.funcionalidadID}"
				<c:choose>
<c:when test="${perfil.funcionalidad.url == funcionalidadSelect }"> class="clickeado" </c:when>
<c:otherwise> class="imp" </c:otherwise>
</c:choose>
				<c:choose>
	<c:when test="${perfil.funcionalidad.url !='#'}">
	href="${pageContext.request.contextPath}/principal/${perfil.funcionalidad.url}"
	</c:when>
	<c:otherwise>
	href="${perfil.funcionalidad.url}"
	</c:otherwise>
	</c:choose>>
					<c:out value="${perfil.funcionalidad.titulo}" />
			</a>
			</li>
		</c:forEach>
		<li><a id="close" class="imp"
			href="${pageContext.request.contextPath}/security/cerrarSesion.jspx"><c:out
					value="Salir" /> </a>
		</li>
	</ul>
</div>
<br>