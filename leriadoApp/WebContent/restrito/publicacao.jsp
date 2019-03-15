<%@page import="com.ifpb.edu.model.domain.publicacao.Texto"%>
<%@page
	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="com.ifpb.edu.model.domain.publicacao.Compartilha"%>
<%@page import="java.util.List"%>
<%@page import="com.ifpb.edu.model.domain.publicacao.Noticia"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<title>Leriado</title>
<style type="text/css">
body {
	background-color: #CCC;
}

.divPublicacao {
	background-color: #fff;
	width: 800px;
	margin: 2px;
	padding: 1px 30px 50px 30px;
}

.data {
	color: #AAA;
}
</style>
</head>
<body>
	<h1><%=request.getServletContext().getInitParameter("tituloAplicacao")%></h1>
	<h2>Página de ${sessionScope.usuarioLogado.nome}</h2>
	<button>
		<a href="Leriado?command=UsuarioController&acao=logout">Logout</a>
	</button>
	<c:forEach var="compartilha" items="${feed}">
		<div class="divPublicacao">
			<h3>
				<b>${compartilha.usuario.nome}</b>
				<c:if
					test="${compartilha.usuario.id != compartilha.publicacao.usuario.id}">
					<small>compartilhou a publicação de</small> ${compartilha.publicacao.usuario.nome}
	</c:if>
			</h3>
			<p>
				<small class="data"> <fmt:parseDate
						value="${compartilha.dataHora}" pattern="yyyy-MM-dd'T'HH:mm"
						var="parsedDateTime" type="both" /> <fmt:formatDate
						pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />
				</small> <small> >> <a href="#">${compartilha.grupo.nome}</a></small>
			</p>
			<c:if test="${compartilha.publicacao.tipoTexto eq 'NOTICIA'}">
				<!-- NOTÍCIA -->
				<h3>
					<c:out value="${compartilha.publicacao.titulo}" />
				</h3>
				<p>${compartilha.publicacao.conteudo}</p>
				<c:forEach var="foto" items="${compartilha.publicacao.fotos}">
					<img alt="${foto.conteudo}"
						src="<%=request.getServletContext().getInitParameter("pastaImagensUsuario")%>/${foto.arquivo}">
				</c:forEach>

			</c:if>
			<c:if test="${compartilha.publicacao.tipoTexto eq 'PUBLICACAO'}">
				<!-- PUBLICACAO -->
				<p>${compartilha.publicacao.conteudo}</p>
			</c:if>
			<c:if test="${compartilha.publicacao.tipoTexto eq 'FOTO'}">
				<!-- FOTO -->
				<img alt="${compartilha.publicacao.conteudo}"
					src="<%=request.getServletContext().getInitParameter("pastaImagensUsuario")%>/${compartilha.publicacao.arquivo}">
				<p>${compartilha.publicacao.conteudo}</p>
			</c:if>
			<c:if test="${compartilha.publicacao.tipoTexto eq 'LINK'}">
				<!-- LINK -->
				<p>${compartilha.publicacao.conteudo}</p>
			</c:if>
			<small>
				<p>
					<c:if test="${compartilha.publicacao.curtidas > 0}">	
		${compartilha.publicacao.curtidas} curtidas
	</c:if>
					<c:if test="${compartilha.publicacao.compartilhamentos > 0}">	
		${compartilha.publicacao.compartilhamentos} compartilhamentos
	</c:if>
				</p>
			</small>
			<p>
				<button type="button">Curtir</button>
				<button type="button">Compartilhar</button>
			</p>
			<form>
				<input type="text">
				<button type="button">comentar</button>
			</form>
			<ul>
				<c:forEach var="comentario"
					items="${compartilha.publicacao.comentarios}">
					<li><b>${comentario.usuario.nome}</b> ${comentario.conteudo} <c:if
							test="${comentario.curtidas>0}">
		 - ${comentario.curtidas} curtidas. 
		 </c:if> <br>
					<button type="button">Curtir</button></li>
				</c:forEach>
			</ul>

		</div>


	</c:forEach>

	<script>
		function mudarpagina(obj) {
			location.href = obj.value;
		};
	</script>
	<select id="selecao" onchange="mudarpagina(this)">
		<c:forEach var="i" begin="0" end="${feedQtd-1}">
			<option <c:if test="${i==pag}">
			selected
		</c:if>
				<c:if test="${i!=pag}"> 
			value="Leriado?command=PublicacaoController&acao=feed&pag=${i}"
		</c:if>>Página
				${i+1}</option>
		</c:forEach>
	</select>
</body>
</html>