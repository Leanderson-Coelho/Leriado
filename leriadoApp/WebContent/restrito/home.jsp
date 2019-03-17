<%@page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<!-- JQUERY -->
<script src="../js/JQuery.js"></script>
<!-- MASCARAS JQUERY PLUGIN -->
<script src="../js/jQuery-Mask-Plugin-master/src/jquery.mask.js"></script>
<!-- MASCARAS JQUERY CONFIG -->
<script src="../js/mascaras.js"></script>
<!-- MATERIALIZE CSS-->
<link rel="stylesheet" href="../css/materialize.css">
<!-- MATERIALIZE JS -->
<script src="../js/materialize.js"></script>
<!-- ICONS MATERIALIZA -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!-- CUSTOM -->
<link rel="stylesheet" href="../css/custom.css">
<title><%=request.getServletContext().getInitParameter("tituloAplicacao")%></title>
<script>
	$(document).ready(function() {
		$('.modal').modal();
	});
	$(document).ready(function() {
		$('.collapsible').collapsible();
	});
</script>
</head>

<body>
	<!-- MENU -->
	<jsp:include page="headerOn.jsp" />
	<!-- CONTEUDO -->
	<div class="row">
		<!-- PERFIL -->
		<div class="col s2 red lighten-1">
			<div class="row">
				<div class="col s12">
					<ul class="collection">
						<!-- USUARIO -->
						<li class="collection-item avatar"><img
							src="../userimg/${fotoPerfil.arquivo}" class="circle">
							<p>${usuarioLogado.nome} ${usuarioLogado.sobrenome}</p>
							Grupos:
						</li>
						
						<div class="divider"></div>
						<c:import url="/Leriado?command=GrupoController&acao=grupos" />
						<div class="row collection-item"></div>
					</ul>

				</div>
			</div>
		</div>
		<!-- FEED -->
		<div class="col s8 green lighten-5">
			<!-- -----------------------------------IAN FAZER PUBLICA��O------------------------------------------- -->
			<div class="grey lighten-5">
				<ul class="collapsible">
					<li class="active">
						<div class="collapsible-header">
							<i class="material-icons">chat</i>Mensagem
						</div>
						<div class="collapsible-body">

							<!-- Mensagem -->
							<div class="container">
								<div class="row">
									<form method="post"
										action="/leriadoApp/Leriado?command=FeedController&acao=mensagem"
										enctype="multipart/form-data">
										<div class="input-field col s12">
											<textarea class="materialize-textarea"
												placeholder="Sua publica��o aqui" name="conteudo"></textarea>
										</div>
										<div class="col s12 center-align">
											<button class="green lighten-2 z-depth-1 btn" type="submit"
												name="action">
												Leriar <i class="material-icons right">send</i>
											</button>
										</div>
									</form>
								</div>
							</div>

						</div>
					</li>
					<li>
						<div class="collapsible-header">
							<i class="material-icons">insert_link</i>Link
						</div>
						<div class="collapsible-body">

							<!-- Link -->
							<div class="container">
								<div class="row">
									<form method="post"
										action="/leriadoApp/Leriado?command=FeedController&acao=link"
										enctype="multipart/form-data">
										<div class="input-field col s12">
											<textarea class="materialize-textarea"
												placeholder="Sua publica��o aqui" name="conteudo"></textarea>
										</div>
										<div class="col s12 offset">
											<input type="url" placeholder="Link" name="link">
										</div>
										<div class="col s12 center-align">
											<button class="green lighten-2 z-depth-1 btn" type="submit"
												name="action">
												Leriar <i class="material-icons right">send</i>
											</button>
										</div>
									</form>
								</div>
							</div>

						</div>
					</li>
					<li>
						<div class="collapsible-header">
							<i class="material-icons">image</i>Fotos
						</div>
						<div class="collapsible-body">

							<!-- Fotos -->
							<div class="container">
								<div class="row">
									<form method="post"
										action="/leriadoApp/Leriado?command=FeedController&acao=foto"
										enctype="multipart/form-data">
										<div class="input-field col s12">
											<textarea class="materialize-textarea"
												placeholder="Sua publica��o aqui" name="conteudo"></textarea>
										</div>
										<div class="file-field input-field">
											<div class="col s12">
												<div class="file-field input-field">
													<div class="btn green">
														<i class="material-icons">add</i> <input type="file"
															name="files">
													</div>
													<div class="file-path-wrapper">
														<input class="file-path validate" type="text"
															placeholder="Documentos/Imagens">
													</div>
												</div>
											</div>
										</div>
										<div class="col s12 center-align">
											<button class="green lighten-2 z-depth-1 btn" type="submit"
												name="action">
												Leriar <i class="material-icons right">send</i>
											</button>
										</div>
									</form>
								</div>
							</div>

						</div>
					</li>
					<li>
						<div class="collapsible-header">
							<i class="material-icons">whatshot</i>Not�cia
						</div>
						<div class="collapsible-body">

							<!-- Not�cia -->
							<div class="container">
								<div class="row">
									<form method="post"
										action="/leriadoApp/Leriado?command=FeedController&acao=noticia"
										enctype="multipart/form-data">
										<div class="col s12">
											<input type="text" name="titulo"
												placeholder="Digite um titulo para sua publica��o"
												class="center-align">
										</div>
										<div class="input-field col s12">
											<textarea class="materialize-textarea"
												placeholder="Sua publica��o aqui" name="conteudo"></textarea>
										</div>
										<div class="file-field input-field">
											<div class="col s12">
												<div class="file-field input-field">
													<div class="btn green">
														<i class="material-icons">add</i> <input type="file"
															name="files" multiple>
													</div>
													<div class="file-path-wrapper">
														<input class="file-path validate" type="text"
															placeholder="Documentos/Imagens">
													</div>
												</div>
											</div>
										</div>
										<div class="col s12 center-align">
											<button class="green lighten-2 z-depth-1 btn" type="submit"
												name="action">
												Leriar <i class="material-icons right">send</i>
											</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
			<!-- ISLEIMAR FEED -->
			<div class="row grey lighten-3">
				<c:import url="/Leriado?command=FeedController&acao=feed" />
			</div>
		</div>
		<!-- AMIGOS -->
		<div class="col s2 green lighten-1">AMIGOS</div>
	</div>
</body>
</html>