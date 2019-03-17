<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<form action="/leriadoApp/Leriado?command=FeedController&acao=compartilha" method="POST">
		<input hidden name="textoid" type="text" value="${param.textoId}" readonly>		
		<div class="row">		
			<button class="waves-effect waves-light btn grey lighten-1" type="submit" name="action">
				Compartilha
			</button>		
		</div>			
	</form>
</div>	