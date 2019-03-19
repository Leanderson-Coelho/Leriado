<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form method="post"
	action="/leriadoApp/Leriado?command=PublicacaoController&acao=foto"
	enctype="multipart/form-data">
	<input  hidden name="usuarioid" type="text" value="${usuarioId}" readonly>
	<div class="input-field col s12">
		<textarea class="materialize-textarea"
			placeholder="Sua publicação aqui" name="conteudo"></textarea>
	</div>
	<div class="file-field input-field">
		<div class="col s12">
			<div class="file-field input-field">
				<div class="btn green">
					<i class="material-icons">add</i> <input type="file" name="files">
				</div>
				<div class="file-path-wrapper">
					<input class="file-path validate" type="text"
						placeholder="Documentos/Imagens">
				</div>
			</div>
		</div>
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
				name="action">
				Leriar <i class="material-icons right">send</i>
			</button>
		</div>
</form>