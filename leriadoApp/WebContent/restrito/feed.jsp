<%@page	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:forEach var="feed" items="${feedPublicacao}">	
 	<div class="divider"></div>
	<div class="section white">			
		<h4>
			<b>${feed.compartilha.usuario.nome}</b>
			<c:if test="${!feed.seuConteudo}">
				<small>compartilhou a publicação de</small> ${feed.compartilha.publicacao.usuario.nome}
			</c:if> 
		</h4>
		<p>
			<small class="blue-text text-darken-2"> <fmt:parseDate
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
			<div class="row s12">
			<div class="col s1"></div>
			<img class="col s10" alt="${feed.compartilha.publicacao.conteudo}"
				src="<%=request.getServletContext().getInitParameter("pastaImagensUsuario")%>/${feed.compartilha.publicacao.arquivo}">			
			<div class="col s1"></div>			
			</div>
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
		<div class="row">
			<div class="col">
				<!-- CURTIR -->
				<c:url value="./feed/curtir.jsp" var = "campoCurtir">
					<c:param name="textoId" value="${feed.compartilha.publicacao.id}"/>
					<c:param name="textoCurtido" value="${feed.curtido}"/>
				</c:url>
				<c:import url = "${campoCurtir}"/>
			</div>
			<div class = "col">
				<button type="button">Compartilhar</button>
			</div>
		</div>
		<div class = "row">		
			<!-- COMENTAR -->
			<c:url value="./feed/comenta.jsp" var = "campoComenta">
				<c:param name="action" value="${feed.compartilha.publicacao.id}"/>
			</c:url>
			<c:import url = "${campoComenta}"/>
		</div>
		<!-- COMENTÁRIOS -->
		<div class = "row">
			<div class = "col s1"></div>
			<div class = "col s10">
			<ul class = "listaComentario">
				<c:forEach var="feedComentario"	items="${feed.feedComentarios}">
				
					<li>	
						<div class="row">
							<!-- COMENTÁRIO -->							
							<b>${feedComentario.comentario.usuario.nome}</b>
							${feedComentario.comentario.conteudo} 
							<b>
							<c:if test="${feedComentario.quantCurtidas == 1}"> - ${feedComentario.quantCurtidas} curtida.</c:if>
							<c:if test="${feedComentario.quantCurtidas > 1}"> - ${feedComentario.quantCurtidas} curtidas.</c:if>
							</b>
						</div>
						<div class="row"> 
							<!-- CURTIR COMENTÁRIO-->
							<c:url value="./feed/curtir.jsp" var = "campoCurtir">
							<c:param name="textoId" value="${feedComentario.comentario.id}"/>
							<c:param name="textoCurtido" value="${feedComentario.curtido}"/>
							</c:url>
							<c:import url = "${campoCurtir}"/>
						</div>						
						<div class="divider"></div>
					</li>
				</c:forEach>
			</ul>
			</div>
			<div class = "col s1"></div>
		</div>
	</div>
</c:forEach>
<div class="row white">
<ul class="pagination">

	<c:if test="${pag==1}">
		<li class="disabled"><a><i class="material-icons">chevron_left</i></a></li>
	</c:if>
	<c:if test="${pag>1}">
		<li class="waves-effect"><a href="home.jsp?pag=${pag-1}"><i class="material-icons">chevron_left</i></a></li>
	</c:if>
	
	<c:forEach var="i" begin="1" end="${qtdPag}">
		<c:if test="${pag==i}"><li class="active"><a>${i}</a></li></c:if>
		<c:if test="${pag!=i}"><li class="waves-effect"><a href="home.jsp?pag=${i}">${i}</a></li></c:if>		
	</c:forEach>
	<c:if test="${pag<qtdPag}">
		<li class="waves-effect"><a href="home.jsp?pag=${pag+1}"><i class="material-icons">chevron_right</i></a></li>
	</c:if>
	<c:if test="${pag>=qtdPag}">
		<li class="disabled"><a><i class="material-icons">chevron_right</i></a></li>
	</c:if>
</ul>
</div>

