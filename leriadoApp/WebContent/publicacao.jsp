<%@page import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="com.ifpb.edu.model.domain.publicacao.Compartilha"%>
<%@page import="java.util.List"%>
<%@page import="com.ifpb.edu.model.domain.publicacao.Noticia"%>
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
	<c:if test="${compartilha.publicacao.tipoTexto eq 'NOTICIA'}">		
		<h3><c:out value="${compartilha.publicacao.titulo}"/></h3>
	</c:if>
	<p>${compartilha.publicacao.conteudo}</p>		
	<p>${compartilha.dataHora}</p>
	</div>
</c:forEach>-->
<button><a href="Leriado?command=UsuarioController&acao=logout">Logout</a></button>

</body>
</html>