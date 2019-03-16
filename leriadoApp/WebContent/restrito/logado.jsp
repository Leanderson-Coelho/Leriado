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
<button><a href="/leriadoApp/Leriado?command=UsuarioController&acao=remover">Remover Conta</a></button>
<button><a href="/leriadoApp/restrito/atualizar.jsp">Atualizar</a></button>
<button><a href="/leriadoApp/Leriado?command=PublicacaoController&acao=feed">Publicação</a></button>
<button><a href="/leriadoApp/Leriado?command=GrupoController&acao=home">Feed</a></button>
<button><a href="/leriadoApp/Leriado?command=UsuarioController&acao=logout">Logout</a></button>
</body>
</html>