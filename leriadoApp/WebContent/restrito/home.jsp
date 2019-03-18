<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
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
<script src="../js/custom.js"></script>

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
	<div class="row grey lighten-3">
		<!-- PERFIL -->
		<div class="col s12 l2 grey lighten-5 perfil">
			<div class="row">
				<div class="col s12 l12">
					<ul class="collection">
						<!-- USUARIO -->
						<li class="collection-item avatar"><img
							src="../userimg/${fotoPerfil.arquivo}" class="circle">
							<b>${usuarioLogado.nome} ${usuarioLogado.sobrenome}</b>
						</li>
						<p class="grey-text text-darken-3">Feed</p>
						<div class="divider"></div>
						<a href="/leriadoApp/restrito/home.jsp" class="collection-item">Feed de Notícias</a>
						<div class="divider"></div>
						<p class="grey-text text-darken-3">Grupos que você participa:</p>
						<div class="divider"></div>
						<c:import url="/Leriado?command=GrupoController&acao=grupos" />
						<div class="divider"></div>
						<a href="/leriadoApp/restrito/meusGrupos.jsp" class="collection-item">Meus Grupos</a>
					</ul>
				</div>
			</div>
		</div>
		<!-- FEED -->
		<div class="col s12 l8 grey lighten-3">
			<div class="grey lighten-3"></div>				
			<div>
				<!-- FEED -->
				<c:import url="/Leriado?command=FeedController&acao=feed" />
			</div> 
		</div>
		<!-- AMIGOS -->
		<div class="col s2 grey lighten-3"></div>
	</div>
</body>
</html>