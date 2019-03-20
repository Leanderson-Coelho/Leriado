<div class="row">
<form action="/leriadoApp/Leriado?command=FeedController&acao=comenta" method="POST">
	<input hidden name="textoid" type="text" value="${param.action}" readonly>		
	<div class="row">
		<div class="col s1"></div>
		<div class="input-field col s8">
			<textarea name="comentario" id="textarea1" class="materialize-textarea" onKeyPress="return submitenter(this,event)"></textarea>
			<label for="textarea1">Comentar</label>
		</div>
		<div class="col s2">
			<button class="waves-effect waves-light btn grey lighten-1" type="submit" name="action">Comentar</button>
		</div>
		<div class="col s1"></div>
	</div>
			
</form>
</div>		
