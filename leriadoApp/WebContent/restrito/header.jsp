<!-- MENU DESKTOP-->
<header>
	<nav>
		<div class="container">
			<div class="nav-wrapper">
				<a href="#" class="brand-logo "><img class="logo"
					src="img/logobranco.png" /></a>
				<ul class="right hide-on-med-and-down">
					<form method="post"
						action="Leriado?command=UsuarioController&acao=login">
						<div class="row">
							<div class="input-field col l4">
								<input class="validate camposLogin" type="text" id="emailLogin"
									name="login" required
									pattern="^[\w\.\-]+@([\w\-]+\.)+[A-Za-z]{2,4}$" /> <label
									for="emailLogin">Email</label>
							</div>
							<div class="input-field col l4">
								<input class="camposLogin" type="password" id="senha"
									name="senha" required pattern=".{8,}" /> <label>Senha</label>
							</div>
							<div class="input-field col l4">
								<button type="submit" class="btn waves-effect waves-light">Enviar</button>
							</div>
						</div>
						<h6 class="row red-text">${erro}</h6>
					</form>
				</ul>
			</div>
		</div>
	</nav>
</header>
<!-- MENU MOBILE -->
<div class="row container hide-on-large-only">
	<form method="post"
		action="Leriado?command=UsuarioController&acao=login">
		<div class="input-field col s12">
			<input class="validate" type="text" id="emailLogin" name="login"
				required pattern="^[\w\.\-]+@([\w\-]+\.)+[A-Za-z]{2,4}$" /> <label
				for="email">Email</label>
		</div>
		<div class="input-field col s12">
			<input type="password" name="senha" id="senha" required min="8" /> <label
				for="senha">Senha</label>
		</div>
		<div class="input-field col s2">
			<button type="submit" class="btn waves-effect waves-light center">Enviar</button>
		</div>
	</form>
</div>