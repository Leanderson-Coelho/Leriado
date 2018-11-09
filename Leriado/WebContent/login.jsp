<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="icon" type="image/png" href="img/icone.png" />
	<link rel="stylesheet" type="text/css" href="./css/estilo.css"/>
	<title>Tela de login</title>
</head>
<body>
	<div class="containerLogin">
		<div class="boxlogin">
			<form action="./controller?comando=Logar" method="POST">
				<input type="text" name="login" value="usuário">
				<input type="password" name="senha" value="senha">
				<input type="submit" value ="LOGIN">
			</form>
		</div>
	</div>

</body>
</html>