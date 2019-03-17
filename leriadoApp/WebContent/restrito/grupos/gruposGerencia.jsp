<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${gruposUsuarioAdministra ne null }">
	<ul class="collapsible">
		<c:forEach var="grupo" items="${gruposUsuarioAdministra }">
			<li>
				<div class="collapsible-header">${grupo.nome}</div>
				<div class="collapsible-body white">
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
								<b>SEM FOTO: </b><i class="material-icons">group</i>
							</c:if>
							<c:if test="${grupo.foto != null}">
								<b>FOTO: </b><img src="../../userimg/${grupo.foto}"/>
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
								<i class="material-icons prefix">account_circle</i> <input
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
				</div>
			</li>
		</c:forEach>
	</ul>
</c:if>
<c:if test="${gruposUsuarioAdministra eq null }">
	<h6>Você não é administrador de nenhum grupo.</h6>
</c:if>