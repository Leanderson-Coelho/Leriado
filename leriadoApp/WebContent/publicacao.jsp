<%@page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1"/>
<title>Insert title here</title>
</head>
<body>
<h1>Página de ${sessionScope.usuarioLogado.nome}</h1>
<c:forEach var="compartilha" items="${feed}">
	<div>
	<h2>${compartilha.usuario.nome}</h2>
	<p>${compartilha.publicacao.conteudo}</p>	
	<p>${compartilha.dataHora}</p>
	</div>
</c:forEach>
<button><a href="Leriado?command=UsuarioController&acao=logout">Logout</a></button>

</body>
</html>