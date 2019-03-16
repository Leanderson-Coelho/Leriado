<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <jsp:include page="headerOn.jsp" />
    <!-- CONTEUDO -->
    <div class="row">
        <!-- PERFIL -->

        <div class="col s2 red lighten-1">
            <div class="row">
                <div class="col s12">
                    <ul class="collection">
                        <!-- USUARIO -->
                        <li class="collection-item avatar">
                            <img src="../userimg/b136693d02a2ce0468ab51be43f0e04f" class="circle">
                            <p>${usuarioLogado.nome} ${usuarioLogado.sobrenome}</p>
                            <c:if test="${msg!=null}">
                                <p class="red-text">${msg}</p>
                            </c:if>
                        </li>
                        <!-- GRUPOS -->
                        <a class="collection-item modal-trigger" href="#modal1">Aprendendo Redis</a>
                    </ul>
                    <!-- GERENCIA GRUPO -->
                    <div id="modal1" class="modal modal-fixed-footer">
                        <div class="modal-content ">
                            <h4>Gerênciar Grupo</h4>
                            <div class="row">
                                <div class="col s12">
                                    <p class="green-text">Adicionar usuário:</p>
                                    <form action="/leriadoApp/Leriado?command=GrupoController&acao=adicionarUsuario" method="post">
                                        <input hidden type="text" value="Aprendendo Redis" name="nomeGrupo" />
                                        <div class="col s6 input-field">
                                            <i class="material-icons prefix">account_circle</i>
                                            <input type="email" name="email" id="email" />
                                            <label for="email">Email do novo usuário</label>
                                        </div>
                                        <div class="col s6 input-field">
                                            <button class="btn waves-effect waves-light z-depth-0" type="submit"
                                                name="action">Adicionar
                                                <i class="material-icons right">send</i>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="divider"></div>
                            <div class="row">
                                <div class="col s12">
                                    <p class="red-text">Remover usuário:</p>
                                    <form>
                                        <input hidden type="text" value="Aprendendo Redis" name="nomeGrupo" />
                                        <div class="col s6 input-field">
                                            <i class="material-icons prefix">account_circle</i>
                                            <input type="email" name="emailRemover" id="emailRemover" />
                                            <label for="emailRemover">Email do usuário</label>
                                        </div>
                                        <div class="col s6 input-field">
                                            <button class="btn waves-effect waves-light z-depth-0 red" type="submit"
                                                name="action">Remover
                                                <i class="material-icons right">send</i>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a href="#!" class="modal-close waves-effect waves-green btn-flat">OK</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- FEED -->
        <div class="col s8 green lighten-5">
            <!-- IAN FAZER PUBLICAÇÂO -->
            <div class="grey lighten-5" style="padding-bottom: 1px;">
				<div class="container">
					<div class="row">
						<form>
							<div class="col s12">
								<input type="text"
									placeholder="Digite um titulo para sua publicação"
									class="center-align">
							</div>
							<div class="input-field col s12">
								<textarea class="materialize-textarea" placeholder="Sua publicação aqui"></textarea>
							</div>
							<div class="col 5s file-field input-field">
								<div class="col btn-floating">
									<input type="file" multiple>
									<i class="material-icons">add_circle_outline</i>
								</div>
								<div class="col file-path-wrapper">
									<input class="file-path validate" type="text" placeholder="Documentos/Imagens">
								</div>
							</div>
							<div class="col s5 offset">
								<input type="url" placeholder="Link">
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