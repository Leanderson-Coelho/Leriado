<!-- MENU DESKTOP-->
<script type="text/javascript">
	$(document).ready(function() {
		$('.sidenav').sidenav();
	});
</script>
<header>
	<div class="navbar-fixed">
		<nav>
			<div class="container">
				<div class="nav-wrapper">
					<a href="/leriadoApp/restrito/home.jsp" class="brand-logo "><img
						class="logo" src="../img/logobranco.png" /></a>
					<ul class="right hide-on-med-and-down">
						<li><a href="meusGrupos.jsp"><i
								class="material-icons right">group</i>Meus Grupos</a></li>
						<div data-target="slide-out" class="sidenav-trigger ">
							<li><a href="#"><i class="material-icons right">face</i>${usuarioLogado.nome }</a></li>
						</div>
					</ul>
				</div>
			</div>
		</nav>
	</div>
</header>

<ul id="slide-out" class="sidenav">
	<li>
		<div class="user-view">
			<div class="background green"></div>
			<form action="/leriadoApp/Leriado?command=UsuarioController&acao=fotoPerfil" method="post" enctype="multipart/form-data">
				<div class="file-field input-field">
					<img class="circle" src="../userimg/${fotoPerfil.arquivo}"> <input
						type="file" name="file" onchange="this.form.submit()">
				</div>
			</form>
			<a href="#"> <span class="white-text name">${usuarioLogado.nome}
					${usuarioLogado.sobrenome}</span></a> <a href="#"><span
				class="white-text email">${usuarioLogado.email}</span></a>
		</div>
	</li>
	<li><a href="/leriadoApp/restrito/atualizar.jsp"><i
			class="material-icons">edit</i>Atualizar Conta</a></li>
	<li><a
		href="/leriadoApp/Leriado?command=UsuarioController&acao=remover"><i
			class="material-icons">remove</i>Remover Conta</a></li>
	<li><div class="divider"></div></li>
	<li><a
		href="/leriadoApp/Leriado?command=UsuarioController&acao=logout"><i
			class="material-icons">arrow_back</i>Logout</a></li>
	<li><a class="subheader">Leriado</a></li>
</ul>




<!-- MENU MOBILE -->
<div class="row container hide-on-large-only"></div>