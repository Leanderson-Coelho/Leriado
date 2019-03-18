<%@page	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- PUBLICAR -->
<ul class="collapsible popout">
	<li>
		<!-- MENSAGEM -->
		<div class="collapsible-header"><i class="material-icons">chat</i>Mensagem</div>
		<div class="collapsible-body"><c:import url="compartilha/mensagem.jsp" /></div>
	</li>
	<li>
		<!-- LINK -->
		<div class="collapsible-header"><i class="material-icons">insert_link</i>Link</div>
		<div class="collapsible-body"><c:import url="compartilha/link.jsp" /></div>
	</li>
	<li>
		<!-- FOTO -->
		<div class="collapsible-header"><i class="material-icons">image</i>Foto</div>
		<div class="collapsible-body"><c:import url="compartilha/foto.jsp" /></div>
	</li>
	<li>
		<!-- NOTICIA -->
		<div class="collapsible-header"><i class="material-icons">whatshot</i>Noticia</div>
		<div class="collapsible-body"><c:import url="compartilha/noticia.jsp" /></div>
	</li>
</ul>


<!-- FEED -->
<c:if test="${feedQtd==0}">
	<div class = "row center-align"><h4>Você não tem publicações para visualizar</h4></div>
</c:if>
<c:if test="${feedQtd>0}">
<c:forEach var="feed" items="${feedPublicacao}">	
 	<div class="divider"></div>	
 	<div class="white z-depth-2 publicacao">
 		<div class="row">	
		<h5>
			<b class="blue-text">${feed.compartilha.usuario.nome}</b>
			<c:if test="${!feed.seuConteudo}">
				<small>compartilhou a publicação de</small> <b class="blue-text">${feed.compartilha.publicacao.usuario.nome}</b>
			</c:if> 
		</h5>
		<p>
			<small class="grey-text text-darken-2"> <fmt:parseDate
					value="${feed.compartilha.dataHora}" pattern="yyyy-MM-dd'T'HH:mm"
					var="parsedDateTime" type="both" /> <fmt:formatDate
					pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />
			</small> <small> >> <a href="home.jsp?grp=${feed.compartilha.grupo.nome}">${feed.compartilha.grupo.nome}</a></small>
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
			<a href="${feed.compartilha.publicacao.link}">${feed.compartilha.publicacao.link}</a></p>
		</c:if>			
			<p>
			<small>
				<c:if test="${feed.quantCurtidas > 0}">${feed.quantCurtidas} curtidas	</c:if>
				<c:if test="${feed.quantCompartilhamentos > 0}">${feed.quantCompartilhamentos} compartilhamentos</c:if>
			</small>
			</p>
		</div>
		<div class="row">
			<div class="col">
				<!-- CURTIR -->
				<c:url value="./feed/curtir.jsp" var = "campoCurtir">
					<c:param name="textoId" value="${feed.compartilha.publicacao.id}"/>
					<c:param name="textoCurtido" value="${feed.curtido}"/>
				</c:url>
				<c:import url = "${campoCurtir}"/>
			</div>
			<div class="col">
				<!-- COMPARTILHA -->
				<button data-target="modalCompartilha" 
				class="btn modal-trigger" 
				onclick="compartilharGrupo(${feed.compartilha.publicacao.id})">Compartilhar</button>			
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
						<!-- MOSTRAR COMENTÁRIOS-->
						<div class="row">
							<!-- COMENTÁRI -->							
							<b class="blue-text">${feedComentario.comentario.usuario.nome}</b> ${feedComentario.comentario.conteudo}
							<small class="grey-text text-darken-2"> <fmt:parseDate
								value="${feedComentario.comentario.datahora}" pattern="yyyy-MM-dd'T'HH:mm"
								var="parsedDateTime" type="both" /> <fmt:formatDate
								pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />
							</small>
						</div>
						<div class="row"> 
							<!-- CURTIR COMENTÁRIO-->
							<c:url value="./feed/curtir.jsp" var = "campoCurtir">
							<c:param name="textoId" value="${feedComentario.comentario.id}"/>
							<c:param name="textoCurtido" value="${feedComentario.curtido}"/>
							<c:param name="qtdCurtidas" value="${feedComentario.quantCurtidas}"/>
							
							</c:url>
							<c:import url = "${campoCurtir}"/>
						</div>
					</li>
				</c:forEach>
			</ul>
			</div>
			<div class = "col s1"></div>
		</div>
 	</div>		
	</br>
</c:forEach>

<!-- TELA PARA COMPARTILAR -->
<div class="row">
	<div class="row">
		<!-- Modal Structure -->
		<div id="modalCompartilha" class="modal modal-fixed-footer">
			<div class="modal-content">
				<form id="formCompartilha"
					action="/leriadoApp/Leriado?command=FeedController&acao=compartilha"
					method="POST">
					<h4>Compartilhar em grupos</h4>
					<p>Selecione os grupos que deseja compartilhar a publicação.</p>
					<input hidden name="usuarioid" type="text" value="${usuarioId}" readonly>
					<input hidden id="compartilhaTextoid" name="textoid" type="text" value="">
					<c:forEach var="nomeGrupo"	items="${gruposParticipa}">
						<p><label> <input type="checkbox" name="grupo" value="${nomeGrupo}" /> <span>${nomeGrupo}</span></label></p>
					</c:forEach>					
					<button class="btn waves-effect waves-light" type="submit"
						name="action">
						Compartilhar <i class="material-icons right">send</i>
					</button>
				</form>
			</div>
			<div class="modal-footer">
				<a href="#!" class="modal-close waves-effect waves-green btn-flat">Cancelar</a>
			</div>
		</div>
	</div>
</div>

<!-- PAGINAÇÃO -->
<div class="row white">
<ul class="pagination">
	<c:if test="${pag==1}">
		<li class="disabled"><a><i class="material-icons">chevron_left</i></a></li>
	</c:if>
	<c:if test="${pag>1}">
		<li class="waves-effect"><a href="home.jsp?pag=${pag-1}${grp}"><i class="material-icons">chevron_left</i></a></li>
	</c:if>
	
	<c:forEach var="i" begin="1" end="${qtdPag}">
		<c:if test="${pag==i}"><li class="active"><a>${i}</a></li></c:if>
		<c:if test="${pag!=i}"><li class="waves-effect"><a href="home.jsp?pag=${i}${grp}">${i}</a></li></c:if>		
	</c:forEach>
	<c:if test="${pag<qtdPag}">
		<li class="waves-effect"><a href="home.jsp?pag=${pag+1}${grp}"><i class="material-icons">chevron_right</i></a></li>
	</c:if>
	<c:if test="${pag>=qtdPag}">
		<li class="disabled"><a><i class="material-icons">chevron_right</i></a></li>
	</c:if>
</ul>
</div>

</c:if>

<script>
function compartilharGrupo(e) {	  
	document.getElementById("compartilhaTextoid").value = e;
}
</script>

