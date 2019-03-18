<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
<title>Document</title>
</head>

<body>
	<jsp:include page="headerOn.jsp" />
	<div class="row container">
		<c:if test="${msgErro != null}">
			<h5 class="red-text">${msgErro}</h5>
		</c:if>
		<div class="col s12 ">
			<form method="POST"
				action="/leriadoApp/Leriado?command=UsuarioController&acao=atualizar"
				class="col s12">
				<div class="row">
					<div class="col s6 input-field">
						<input value="${usuarioLogado.nome}" type="text" name="nome"
							id="nome" required /> <label for="nome">Nome</label>
					</div>
					<div class="col s6 input-field">
						<input value="${usuarioLogado.sobrenome}" type="text"
							name="sobrenome" id="sobrenome" required /> <label
							for="sobrenome">Sobrenome</label>
					</div>
				</div>
				<div class="row">
					<div class="col s6 input-field">
						<input type="password" name="senha" id="senhaDesktop" required
							pattern=".{8,}" /> <label for="senhaDesktop">Senha</label>
					</div>
					<div class="col s6 input-field">
						<input value="${usuarioLogado.telefone}" type="text"
							name="telefone" id="telefone"
							pattern="^\(\d{2}\) \d{4}\d?-\d{4}$" /> <label for="telefone">Telefone</label>
					</div>
				</div>
				<div class="row">
					<div class="col s6 input-field">
						<p class="col s6">
							<label> <input name="sexo" type="radio" value="M"
								<c:if test="${usuarioLogado.sexo == 'M'}">checked</c:if>
								required /> <span><b>Masculino</b></span>
							</label>
						</p>
						<p>
							<label> <input name="sexo" type="radio" value="F"
								<c:if test="${usuarioLogado.sexo == 'F'}">checked</c:if>
								required /> <span><b>Feminino</b></span>
							</label>
						</p>
					</div>

				</div>
				<div class="row">
					<div class="col s6 input-field">
						<c:set var = "now" value = "${usuarioLogado.datanasc}" />
						
						<input value = "${now[0]}" type="text" name="data"
							id="data" required /> <label for="data">Data de
							Nascimento</label>
					</div>
					<div class="col s6 input-field">
						<input value="${usuarioLogado.numero}" type="text" name="numero"
							id="numero" pattern="\d+" /> <label for="numero">Número
							de casa</label>
					</div>
				</div>
				<div class="row">
					<div class="col s6 input-field">
						<input value="${usuarioLogado.rua}" type="text" name="rua"
							id="rua" /> <label for="rua">Rua</label>
					</div>
					<div class="col s6 input-field">
						<input value="${usuarioLogado.cidade}" type="text" name="cidade"
							id="cidade" /> <label for="Cidade">Cidade</label>
					</div>
				</div>
				<div class="row">
					<div class="col s6 input-field">
						<input value="${usuarioLogado.cep}" type="text" name="cep"
							id="cep" pattern="\d{5}-\d{3}" /> <label for="cep">CEP</label>
					</div>
					<div class="col s6 input-field">
						<input value="${usuarioLogado.estado}" type="text" name="estado"
							id="estado" /> <label for="estado">Estado</label>
					</div>
				</div>
				<div class="input-field">
					<button class="btn waves-effect waves-light" type="submit"
						name="action">
						Modificar <i class="material-icons right">send</i>
					</button>
				</div>
			</form>
		</div>
	</div>
</body>

</html>