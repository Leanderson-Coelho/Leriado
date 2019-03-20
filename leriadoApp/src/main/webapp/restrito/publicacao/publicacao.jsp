<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row"></div>
<div class="row">
	<div class=" col offset-s2 s8">
		<div class="row">
			<div class="input-field col s8">
				<input id="mensagem" type="text"> <label for="mensagem">
					Vamos leriar!!!</label>
			</div>
			<div class="col s2">
				<a href="#" data-target="modalPublica" class="btn modal-trigger">Mensagem</a>
			</div>
		</div>
	</div>
</div>
<!-- TELA PARA PUBLICAÇÃO -->
<div class="row">
	<div class="row">
		<!-- Modal Structure -->
		<div id="modalPublica" class="modal modal-fixed-footer">
			<div class="modal-content">
				<c:import url="compartilha/compartilha.jsp" />	
			</div>
			<div class="modal-footer">
				<a href="#!" class="modal-close waves-effect waves-green btn-flat">Cancelar</a>
			</div>
		</div>
	</div>
</div>
