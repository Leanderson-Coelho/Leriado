%@page language="java" contentType="text/html; charset=ISO-8859-1"%>
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
</script>
</head>

<body>
	<!-- MENU -->
	<jsp:include page="headerOn.jsp" />
	<div class="row">
		<!-- PERFIL -->
		<div class="col s2 red lighten-1">
			<div class="row">
				<div class="col s12">
					<ul class="collection">
						<!-- USUARIO -->
						<li class="collection-item avatar"><img
							src="../userimg/${fotoPerfil.arquivo}" class="circle">
							<p>${usuarioLogado.nome}${usuarioLogado.sobrenome}</p> <c:if
								test="${msg!=null}">
								<p class="red-text">${msg}</p>
							</c:if></li>
						<c:import url="/Leriado?command=GrupoController&acao=grupos" />
					</ul>

				</div>
			</div>
		</div>
		<div class="col s8 blue">
			conteudo
		</div>
		 <!-- AMIGOS -->
        <div class="col s2 green lighten-1">
            AMIGOS
        </div>
	</div>

</body>
</html>