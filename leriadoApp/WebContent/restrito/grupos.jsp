<%@page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- GRUPOS -->
<c:forEach var="grupo" items="${gruposParticipa }">
    <a class="collection-item modal-trigger" href="#${grupo}">${grupo}</a>
    <!-- GERENCIA GRUPO -->
    <div id="${grupo}" class="modal modal-fixed-footer">
        <div class="modal-content ">
            <h4>Gerênciar Grupo</h4>
            <div class="row">
                <div class="col s12">
                    <p class="green-text">Adicionar usu�rio:</p>
                    <form action="/leriadoApp/Leriado?command=GrupoController&acao=adicionarUsuario" method="post">
                        <input hidden type="text" value="${grupo}" name="nomeGrupo" />
                        <div class="col s6 input-field">
                            <i class="material-icons prefix">account_circle</i>
                            <input type="email" name="email" id="email" />
                            <label for="email">Email do novo usu�rio</label>
                        </div>
                        <div class="col s6 input-field">
                            <button class="btn waves-effect waves-light z-depth-0" type="submit" name="action">Adicionar
                                <i class="material-icons right">send</i>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="divider"></div>
            <div class="row">
                <div class="col s12">
                    <p class="red-text">Remover usu�rio:</p>
                    <form>
                        <input hidden type="text" value="${grupo}" name="nomeGrupo" />
                        <div class="col s6 input-field">
                            <i class="material-icons prefix">account_circle</i>
                            <input type="email" name="emailRemover" id="emailRemover" />
                            <label for="emailRemover">Email do usu�rio</label>
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
</c:forEach>