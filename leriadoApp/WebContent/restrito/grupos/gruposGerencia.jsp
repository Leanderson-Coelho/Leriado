<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${gruposUsuarioAdministra ne null }">
	<ul class="collapsible">
		<c:forEach var="grupo" items="${gruposUsuarioAdministra }">
		<c:if test="${grupo.id != null}">
			<li>
				<div class="collapsible-header grey lighten-3">${grupo.nome}</div>
				<div class="collapsible-body grey lighten-3">
					<div class="row">
						<h5><b>Informações:</b></h5>
					</div>
					<div class="row" >
						<div class="col s12 l6">
							<b>NOME: ${grupo.nome}</b>
						</div>
						<div class="col s12 l6">
							<b>DATA E HORA DE CRIAÇÃO: <fmt:parseDate
					value="${grupo.dataHora}" pattern="yyyy-MM-dd'T'HH:mm"
					var="parsedDateTime" type="both" /> <fmt:formatDate
					pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" /></b>
						</div>
					</div>
					<div class="row" >
						<div class="col s12 l6">
							<b>DESCRIÇÃO: ${grupo.descricao}</b>
						</div>
						<div class="col s12 l6">
							<c:if test="${grupo.foto == null}">
								<b>SEM FOTO: </b>
							</c:if>
							<c:if test="${grupo.foto != null}">
								<b>FOTO: </b><img class="circle imgGrupo" src="<%=request.getServletContext().getInitParameter("pastaImagensUsuario")%>/${grupo.foto}"/>
							</c:if>
						</div>
					</div>
					<div class="row">
						<p>Adicionar usuário</p>
						<form action="/leriadoApp/Leriado?command=GrupoController&acao=adicionarUsuario" method="post">
							<input hidden type="text" value="${grupo.nome}" name="nomeGrupo" />
							<div class="col s8 l6 input-field">
								<i class="material-icons prefix">account_circle</i> <input
									type="email" name="email" id="email" /> <label for="email">Email
									do novo usuário</label>
							</div>
							<div class="col s4 l6 input-field">
								<button class="btn waves-effect waves-light z-depth-0"
									type="submit" name="action">
									Adicionar <i class="material-icons right">send</i>
								</button>
							</div>
						</form>
					</div>
					<div class="row">
						<p class="red-text">Remover usuário:</p>
						<form action="/leriadoApp/Leriado?command=GrupoController&acao=removerUsuario" method="post">
							<input hidden type="text" value="${grupo.id}" name="idGrupo" />
							<div class="col s8 l6 input-field">
								<i class="material-icons prefix iconRed">account_circle</i> <input class="emailRemover"
									type="email" name="emailRemover" id="emailRemover" /> <label
									for="emailRemover">Email do usuário</label>
							</div>
							<div class="col s4 l6 input-field">
								<button class="btn waves-effect waves-light z-depth-0 red"
									type="submit" name="action">
									Remover <i class="material-icons right">send</i>
								</button>
							</div>
						</form>
					</div>
					<div class="row">
						<h5><b class="red-text">Apagar grupo:</b></h5>
						<a class="waves-effect waves-light btn modal-trigger rigth" href="#${grupo.nome }">REMOVER GRUPO</a>
					</div>
					<div id="${grupo.nome }" class="modal">
					    <div class="modal-content">
					      <h4>Deseja realmente Apagar o grupo:<i> ${grupo.nome }</i>?</h4>
					    </div>
					    <div class="modal-footer">
				    		<form action="/leriadoApp/Leriado?command=GrupoController&acao=removerGrupo" method="post">
				    			<div class="input-field">
				      				<input hidden type="text" name="idGrupo" value="${grupo.id}" />
				      			</div>
				    			<div class="input-field">
				    				<a href="#!" class="modal-close waves-effect waves-green btn-flat">Cancelar</a>
				    				<button class="btn modal-close red lighten-2 waves-effect" type="submit">REMOVER</button>
				      			</div>
				      		</form>
					    </div>
 					</div>
				</div>
			</li>
		</c:if>
		</c:forEach>
	</ul>
</c:if>
<c:if test="${gruposUsuarioAdministra eq null }">
	<h6>Você não é administrador de nenhum grupo.</h6>
</c:if>