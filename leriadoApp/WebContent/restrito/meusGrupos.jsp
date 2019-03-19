<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="ISO-8859-1">
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
	<div class="row grey lighten-3 conteudo">
		<!-- PERFIL -->
		<div class="col s12 l2 grey lighten-5 perfil">
			<div class="row">
				<div class="col s12 l12">
					<ul class="collection">
						<!-- USUARIO -->
						<li class="collection-item avatar"><img
							src="../userimg/${fotoPerfil.arquivo}" class="circle">
							<b>${usuarioLogado.nome} ${usuarioLogado.sobrenome}</b> <c:if
								test="${msg!=null}">
								<p class="red-text">${msg}</p>
							</c:if></li>
						<b class="grey-text text-darken-3">Feed</b>
						<div class="divider"></div>
						<a href="/leriadoApp/restrito/home.jsp" class="collection-item">Feed de Notícias</a>
						<b class="grey-text text-darken-3">Grupos que você participa:</b>
						<div class="divider"></div>
						<c:import url="/Leriado?command=GrupoController&acao=grupos" />
						<b class="grey-text text-darken-3">Gerenciar seus grupos:</b>
						<div class="divider"></div>
						<a href="/leriadoApp/restrito/meusGrupos.jsp" class="collection-item">Meus Grupos</a>
					</ul>
				</div>
			</div>
		</div>
		<div class="col s12 l8 white lighten-2">
			<h3>Seus Grupos:</h3>
			<c:import url="/Leriado?command=GrupoController&acao=gerenciarGrupos" />
			<!-- ADICIONAR NOVO GRUPO -->
			<h5>Crie um novo Grupo e adicione amigos!</h5>
			<div class="col s12 l6 push-l3"">
				<form action="/leriadoApp/Leriado?command=GrupoController&acao=criarGrupo" method="post" enctype="multipart/form-data">  
					<div class="input-field">
						<input type="text" name="nome" id="nome" required />
						<label for="nome">Nome do Grupo</label>
					</div>
					<div class="input-field">
						<input type="text" name="descricao" id="descricao" required />
						<label for="descricao">Descrição do Grupo</label>
					</div>
					<div class="file-field input-field">
						<div class="btn green">
							<i class="material-icons">add</i><input type="file" name="file">
						</div>
						<div class="file-path-wrapper">
							<input class="file-path validate" type="text" placeholder="Imagem do grupo">
						</div>
					</div>
					<div class="input-field">
						<input type="submit" class="btn" />
					</div>
				</form>
				
			</div>
		</div>
		
		<!-- AMIGOS -->
		<div class="col s12 l2 grey lighten-3"></div>
	</div>

</body>

</html>