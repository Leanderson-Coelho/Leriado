<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="icon" type="image/png" href="img/icone.png" />
	<link rel="stylesheet" type="text/css" href="./css/estilo.css"/>
	<title>Erro!</title>
</head>
<body>
	<div class="containerErro">
		<div class="box"><img src="./img/falha.png"></div>
		<h1>Erro: ${erro}</h1>
		<button type="button" onclick="window.location.href=${voltar}">Voltar</button>		
	</div>
</body>
</html>