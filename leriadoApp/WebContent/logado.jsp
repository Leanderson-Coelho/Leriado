<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Olá ${sessionScope.usuarioLogado.nome }</h1>
<button><a href="Leriado?command=UsuarioController&acao=remover">Remover Conta</a></button>
<button><a href="atualizar.jsp">Atualizar</a></button>
</body>
</html>