<%@page import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="com.ifpb.edu.model.domain.publicacao.Compartilha"%>
<%@page import="java.util.List"%>
<%@page import="com.ifpb.edu.model.domain.publicacao.Noticia"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1"/>
<title>Leriado</title>
<style type="text/css">
      body{
        background-color: #CCC;
      }
      .divPublicacao{
        background-color: #fff;
        width: 890px;
        margin: 25px 20px;
        padding: 10px;
      }
    </style>
</head>
<body>
<h1><%=request.getServletContext().getInitParameter("tituloAplicacao")%></h1>
<h2>P�gina de ${sessionScope.usuarioLogado.nome}</h2>
<button><a href="Leriado?command=UsuarioController&acao=logout">Logout</a></button>
<c:forEach var="compartilha" items="${feed}">
	<div class="divPublicacao">
	<span><b>${compartilha.usuario.nome}</b></span>	
	<small> >> Grupo:${compartilha.grupo.nome}</small>	
	<c:if test="${compartilha.publicacao.tipoTexto eq 'NOTICIA'}">
		<!-- NOT�CIA -->		
		<h3><c:out value="${compartilha.publicacao.titulo}"/></h3>
		<p>${compartilha.publicacao.conteudo}</p>
		<c:forEach var="foto" items="${compartilha.publicacao.fotos}">
			<img alt="${foto.conteudo}" src="<%=request.getServletContext().getInitParameter("pastaImagensUsuario")%>/${foto.arquivo}">
		</c:forEach>
		
	</c:if>
	<c:if test="${compartilha.publicacao.tipoTexto eq 'PUBLICACAO'}">
		<!-- PUBLICACAO -->
		<p>${compartilha.publicacao.conteudo}</p>
	</c:if>
	<c:if test="${compartilha.publicacao.tipoTexto eq 'FOTO'}">
		<!-- FOTO -->		
		<img alt="${compartilha.publicacao.conteudo}" src="<%=request.getServletContext().getInitParameter("pastaImagensUsuario")%>/${compartilha.publicacao.arquivo}">
		<p>${compartilha.publicacao.conteudo}</p>
	</c:if>
	<c:if test="${compartilha.publicacao.tipoTexto eq 'LINK'}">
		<!-- LINK -->		
		<p>${compartilha.publicacao.conteudo}</p>
	</c:if>	
	
	<small>
		<fmt:parseDate value="${compartilha.dataHora}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
		<fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />
	</small>
	</div>	

</c:forEach>

<script>
	function mudarpagina(obj){
	    location.href = obj.value;
	};
</script>
<select id="selecao" onchange="mudarpagina(this)">
	<c:forEach var="i" begin="0" end="${feedQtd-1}" >
		<option 
		<c:if test="${i==pag}">
			selected
		</c:if>
		<c:if test="${i!=pag}"> 
			value="Leriado?command=PublicacaoController&acao=feed&pag=${i}"
		</c:if>
		>P�gina ${i+1}</option>
	</c:forEach>
</select>
</body>
</html>