<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form method="post"
	action="/leriadoApp/Leriado?command=PublicacaoController&acao=link"
	enctype="multipart/form-data">
	<input  hidden name="usuarioid" type="text" value="${usuarioId}" readonly>
	<div class="input-field col s12">
		<textarea class="materialize-textarea"
			placeholder="Sua publicaçãoo aqui" name="conteudo"></textarea>
	</div>
	<div class="col s12 offset">
		<input type="url" placeholder="Link" name="link">
	</div>
	<h5>Selecione os grupos para publicar</h5>
	<c:forEach var="nomeGrupo" items="${gruposParticipa}">
		<p>
			<label> <input type="checkbox" name="grupo"
				value="${nomeGrupo}" /> <span>${nomeGrupo}</span>
			</label>
		</p>
	</c:forEach>

<div class="row">
<div class="col s12 center-align">
	<button class="green lighten-2 z-depth-1 btn" type="submit"
		name="action">Leriar <i class="material-icons right">send</i>		
	</button>
</div>
</form>