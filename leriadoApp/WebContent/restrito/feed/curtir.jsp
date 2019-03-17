<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
<form action="/leriadoApp/Leriado?command=FeedController&acao=curte" method="POST">
	<input hidden name="textoid" type="text" value="${param.textoId}" readonly>		
	<input hidden name="textocurtido" type="text" value="${param.textoCurtido}" readonly>
	<div class="row">
		<div class = "col ">
		<button class="waves-effect waves-light btn
		<c:if test="${!param.textoCurtido}">grey</c:if>
		<c:if test="${param.textoCurtido}">red</c:if>		 
		lighten-1" type="submit" name="action">
		<c:if test="${param.textoCurtido}">Descurtir</c:if>
		<c:if test="${!param.textoCurtido}">Curtir</c:if>
		</button>
		</div><div class = "col valign-wrapper">
			<small>
				<c:if test="${param.qtdCurtidas == 1}"> ${param.qtdCurtidas} curtida.</c:if>
				<c:if test="${param.qtdCurtidas > 1}"> ${param.qtdCurtidas} curtidas.</c:if>
			</small>
		</div>
	</div>			
</form>
</div>	