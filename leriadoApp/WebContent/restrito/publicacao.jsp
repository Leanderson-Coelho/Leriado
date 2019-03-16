<%@page	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<title><%=request.getServletContext().getInitParameter("tituloAplicacao")%></title>
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
	<a href="Leriado?command=UsuarioController&acao=logout"><button>Logout</button></a>	
	<c:forEach var="feed" items="${feedPublicacao}">
		<div class="divPublicacao">			
			<h3>
				<b>${feed.compartilha.usuario.nome}</b>
				<c:if test="${!feed.seuConteudo}">
					<small>compartilhou a publicação de</small> ${feed.compartilha.publicacao.usuario.nome}
				</c:if> 
			</h3>
			<p>
				<small class="data"> <fmt:parseDate
						value="${feed.compartilha.dataHora}" pattern="yyyy-MM-dd'T'HH:mm"
						var="parsedDateTime" type="both" /> <fmt:formatDate
						pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />
				</small> <small> >> <a href="#">${feed.compartilha.grupo.nome}</a></small>
			</p>
			<c:if test="${feed.compartilha.publicacao.tipoTexto eq 'NOTICIA'}">
				<!-- NOTÍCIA -->
				<h3>
					<c:out value="${feed.compartilha.publicacao.titulo}" />
				</h3>
				<p>${feed.compartilha.publicacao.conteudo}</p>
				<c:forEach var="foto" items="${feed.compartilha.publicacao.fotos}">
					<img alt="${foto.conteudo}"
						src="<%=request.getServletContext().getInitParameter("pastaImagensUsuario")%>/${foto.arquivo}">
				</c:forEach>

			</c:if>
			<c:if test="${feed.compartilha.publicacao.tipoTexto eq 'PUBLICACAO'}">
				<!-- PUBLICACAO -->
				<p>${feed.compartilha.publicacao.conteudo}</p>
			</c:if>
			<c:if test="${feed.compartilha.publicacao.tipoTexto eq 'FOTO'}">
				<!-- FOTO -->
				<img alt="${feed.compartilha.publicacao.conteudo}"
					src="<%=request.getServletContext().getInitParameter("pastaImagensUsuario")%>/${feed.compartilha.publicacao.arquivo}">
				<p>${feed.compartilha.publicacao.conteudo}</p>
			</c:if>
			<c:if test="${feed.compartilha.publicacao.tipoTexto eq 'LINK'}">
				<!-- LINK -->
				<p>${feed.compartilha.publicacao.conteudo}</p>
			</c:if>			
				<p>
				<small>
					<c:if test="${feed.quantCurtidas > 0}">${feed.quantCurtidas} curtidas	</c:if>
					<c:if test="${feed.quantCompartilhamentos > 0}">${feed.quantCompartilhamentos} compartilhamentos</c:if>
				</small>
				</p>			
			<p>
				<c:if test="${!feed.curtido}">
					<button type="button">Curtir</button>
				</c:if>
				<c:if test="${feed.curtido}">
					<button type="button">Descurtir</button>
				</c:if>
				<button type="button">Compartilhar</button>
			</p>
			<form>
				<input type="text">
				<button type="button">comentar</button>
			</form>
			<ul>
				<c:forEach var="feedComentario"	items="${feed.feedComentarios}">
					<li><b>${feedComentario.comentario.usuario.nome}</b> ${feedComentario.comentario.conteudo} <c:if
							test="${feedComentario.quantCurtidas>0}">
		 - ${feedComentario.quantCurtidas} curtidas. 
		 </c:if> <br>
					<c:if test="${!feedComentario.curtido}">
					<button type="button">Curtir</button>
				</c:if>
				<c:if test="${feedComentario.curtido}">
					<button type="button">Descurtir</button>
				</c:if>
					</li>
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