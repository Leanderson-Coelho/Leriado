<%@page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta http-equiv="content-type" content="text/html; charset=iso-8859-1"/>
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
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- CUSTOM -->
    <link rel="stylesheet" href="../css/custom.css">
    <title><%=request.getServletContext().getInitParameter("tituloAplicacao")%></title>
    <script>
        $(document).ready(function () {
            $('.modal').modal();
        });
    </script>
</head>

<body>
    <!-- MENU -->
    <jsp:include page="headerOnHome.jsp" />
    <!-- CONTEUDO -->
    <div class="row">
        <!-- PERFIL -->

        <div class="col s2 red lighten-1">
            <div class="row">
                <div class="col s12">
                    <ul class="collection">
                        <!-- USUARIO -->
                        <li class="collection-item avatar">
                            <img src="" class="circle">
                            <p>${usuarioLogado.nome} ${usuarioLogado.sobrenome}</p>
                            <c:if test="${msg!=null}">
                                <p class="red-text">${msg}</p>
                            </c:if>
                        </li>
                        <c:import url="/Leriado?command=GrupoController&acao=grupos"/>           
                    </ul>
                   
                </div>
            </div>
        </div>
        <!-- FEED -->
        <div class="col s8 green lighten-5">
            <!-- IAN FAZER PUBLICA��O -->
            <div class="grey lighten-5" style="padding-bottom: 1px;">
				<div class="container">
					<div class="row">
						<form method="post" action="/leriadoApp/Leriado?command=FeedController&acao=publicacao" enctype="multipart/form-data">
							<div class="col s12">
								<input type="text" name="titulo"
									placeholder="Digite um titulo para sua publica��o"
			 						class="center-align">
							</div>
							<div class="input-field col s12">
								<textarea class="materialize-textarea" placeholder="Sua publica��o aqui" name="conteudo"></textarea>
							</div>
							<div class="col 5s file-field input-field">
								<div class="col btn-floating">
									<input type="file" name="files" multiple>
									<i class="material-icons">add_circle_outline</i>
								</div>
								<div class="col file-path-wrapper">
									<input class="file-path validate" type="text" placeholder="Documentos/Imagens">
								</div>
							</div>
							<div class="col s5 offset">
								<input type="url" placeholder="Link" name="link">
							</div>
							<div class="col s12 center-align">
								<button class="green lighten-2 z-depth-1" type="submit"
									name="action">
									Leriar <i class="material-icons right">send</i>
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
            <!-- ISLEIMAR FEED -->
            <div class="row grey lighten-3">
				<c:import url="/Leriado?command=FeedController&acao=feed"/>
            </div>
        </div>
        <!-- AMIGOS -->
        <div class="col s2 green lighten-1">
            AMIGOS
        </div>
    </div>
</body>
</html>