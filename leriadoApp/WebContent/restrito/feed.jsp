<%@page	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script>
	function mudarpagina(obj) {
		location.href = obj.value;
	};
</script>
<div>
<select id="selecao" onchange="mudarpagina(this)">
	<c:forEach var="i" begin="0" end="${feedQtd-1}">
		<option <c:if test="${i==pag}">selected</c:if>
		<c:if test="${i!=pag}">value="Leriado?command=PublicacaoController&acao=feed&pag=${i}"</c:if>
		>P�gina	${i+1}</option>
	</c:forEach>
</select>
</div>


		
<c:forEach var="feed" items="${feedPublicacao}">
 	<div class="divider"></div>
	<div class="section grey lighten-3">			
		<h4>
			<b>${feed.compartilha.usuario.nome}</b>
			<c:if test="${!feed.seuConteudo}">
				<small>compartilhou a publica��o de</small> ${feed.compartilha.publicacao.usuario.nome}
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
			<!-- NOT�CIA -->
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
			<div class="row">
				<div class="col s1"></div>
				<div class="input-field col s8">
					<textarea id="textarea1" class="materialize-textarea"></textarea>
					<label for="textarea1">Comentar</label>
				</div>
				<div class="col s2">
					<button class="waves-effect waves-teal btn-flat" type="submit" name="action">Comentar
				</div>
				<div class="col s1"></div>
			</div>
				
			</button>
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

