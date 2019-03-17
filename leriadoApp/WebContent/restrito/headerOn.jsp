<!-- MENU DESKTOP-->
<header>
	<div class="navbar-fixed">
		<nav>
			<div class="container">
				<div class="nav-wrapper">
					<a href="/leriadoApp/restrito/home.jsp" class="brand-logo "><img
						class="logo" src="../img/logobranco.png" /></a>
					<ul class="right hide-on-med-and-down">
						<li><a href="#"><i class="material-icons right">face</i>${usuarioLogado.nome }</a></li>
						<li><a href="meusGrupos.jsp"><i
								class="material-icons right">group</i>Meus Grupos</a>
						<li><a
							href="/leriadoApp/Leriado?command=UsuarioController&acao=logout"><i
								class="material-icons right">exit_to_app</i>Sair</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
</header>

<!-- MENU MOBILE -->
<div class="row container hide-on-large-only"></div>