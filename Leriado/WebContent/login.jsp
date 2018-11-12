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
			<form action="controller?comando=Logar" method="POST">
				<div class="boxElemento"><input type="text" name="login" value="usuário"></div>
				<div class="boxElemento"><input type="password" name="senha" value="senha"></div>
				<div class="boxElemento"><input type="submit" value ="LOGIN"></div>
			</form>
		</div>
	</div>
	
	<div id="barraMenu">
		<div id="barraCentral">
			<div id="areaLogo">
				<img id="imgLogoPrincipal" alt="" src="./img/logo1.png">
			</div>
			<div id="menu">
			</div>			
		</div>
		
	</div>

</body>
</html>