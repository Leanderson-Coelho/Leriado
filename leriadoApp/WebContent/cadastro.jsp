<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript"
	src="js/jQuery-Mask-Plugin-master/src/jquery.mask.js"></script>
    <script type="text/javascript" src="js/mascaras.js"></script>

<link rel="stylesheet" type="text/css" href="./css/estilo2.css">
</head>
<body>
	<div id="container">
		<div id="cabecalho"></div>
		<div id="conteudo">
			<form action="Leriado?command=UsuarioController&acao=cadastrar" method="post">
				<table>
					<tr>
						<td><input type="text" name="nome"
							placeholder="*Nome" class="campo" required pattern="^[A-Za-z]{2,}$"></td>
						<td><input type="text" name="sobrenome"
							placeholder="*Sobrenome" class="campo" required pattern="^[A-Za-z]{2,}$"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" name="email"
							placeholder="*E-mail" class="campo" required pattern="^[\w\.\-]+@([\w\-]+\.)+[A-Za-z]{2,4}$"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="password" name="senha"
							placeholder="*Senha(no mínimo 8 dígitos)" class="campo" required min="8"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="password" name="confirma-senha"
							placeholder="*Repita a senha" class="campo" required min="8"></td>
					</tr>
					<tr>
						<td><input type="radio" name="sexo" value="M" required> <label
							class="campo" for="M" style="border: 0px;">Masculino</label></td>
						<td><input type="radio" name="sexo" value="F" required> <label
							class="campo" for="F" style="border: 0px;">Feminino</label></td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" name="data"
							placeholder="*Nascimento" id="data" class="campo" required></td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" name="telefone"
							placeholder="Telefone" id="telefone" class="campo" pattern="^\(\d{2}\) \d{4}\d?-\d{4}$"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" name="rua"
							placeholder="Rua" class="campo"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" name="cidade"
							placeholder="Cidade" class="campo"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" name="estado"
							placeholder="Estado" class="campo"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" name="numero"
							placeholder="Numero" id="numero" class="campo" pattern="\d+"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" name="cep"
							placeholder="CEP" id="cep" class="campo" pattern="\d{5}-\d{3}"></td>
					</tr>
					<tr>
						<td style="text-align: center;" colspan="2"><input
							type="submit" name="cadastrar" value="Leriar"></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="rodape"></div>
	</div>
</body>
</html>