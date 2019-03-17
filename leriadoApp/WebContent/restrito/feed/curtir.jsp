<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
<form action="/leriadoApp/Leriado?command=FeedController&acao=curte" method="POST">
	<input hidden name="textoid" type="text" value="${param.textoId}" readonly>		
	<input hidden name="textocurtido" type="text" value="${param.textoCurtido}" readonly>
	<div class="row">
		<button class="waves-effect waves-light btn red lighten-1" type="submit" name="action">
		<c:if test="${param.textoCurtido}">Descurtir</c:if>
		<c:if test="${!param.textoCurtido}">Curtir</c:if>
		</button>
	</div>			
</form>
</div>	