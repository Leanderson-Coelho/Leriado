			<!-- -----------------------------------IAN FAZER publicação------------------------------------------- -->
			<div class="grey lighten-5">
				<ul class="collapsible">
					<li class="active">
						<div class="collapsible-header">
							<i class="material-icons">chat</i>Mensagem
						</div>
						<div class="collapsible-body">

							<!-- Mensagem -->
							<div class="container">
								<div class="row">
									<form method="post"
										action="/leriadoApp/Leriado?command=PublicacaoController&acao=mensagem"
										enctype="multipart/form-data">
										<div class="input-field col s12">
											<textarea class="materialize-textarea"
												placeholder="Sua publicação aqui" name="conteudo"></textarea>
										</div>
										<div class="col s12 center-align">
											<button class="green lighten-2 z-depth-1 btn" type="submit"
												name="action">
												Leriar <i class="material-icons right">send</i>
											</button>
										</div>
									</form>
								</div>
							</div>

						</div>
					</li>
					<li>
						<div class="collapsible-header">
							<i class="material-icons">insert_link</i>Link
						</div>
						<div class="collapsible-body">

							<!-- Link -->
							<div class="container">
								<div class="row">
									<form method="post"
										action="/leriadoApp/Leriado?command=PublicacaoController&acao=link"
										enctype="multipart/form-data">
										<div class="input-field col s12">
											<textarea class="materialize-textarea"
												placeholder="Sua publicaçãoo aqui" name="conteudo"></textarea>
										</div>
										<div class="col s12 offset">
											<input type="url" placeholder="Link" name="link">
										</div>
										<div class="col s12 center-align">
											<button class="green lighten-2 z-depth-1 btn" type="submit"
												name="action">
												Leriar <i class="material-icons right">send</i>
											</button>
										</div>
									</form>
								</div>
							</div>

						</div>
					</li>
					<li>
						<div class="collapsible-header">
							<i class="material-icons">image</i>Fotos
						</div>
						<div class="collapsible-body">

							<!-- Fotos -->
							<div class="container">
								<div class="row">
									<form method="post"
										action="/leriadoApp/Leriado?command=PublicacaoController&acao=foto"
										enctype="multipart/form-data">
										<div class="input-field col s12">
											<textarea class="materialize-textarea"
												placeholder="Sua publicação aqui" name="conteudo"></textarea>
										</div>
										<div class="file-field input-field">
											<div class="col s12">
												<div class="file-field input-field">
													<div class="btn green">
														<i class="material-icons">add</i> <input type="file"
															name="files">
													</div>
													<div class="file-path-wrapper">
														<input class="file-path validate" type="text"
															placeholder="Documentos/Imagens">
													</div>
												</div>
											</div>
										</div>
										<div class="col s12 center-align">
											<button class="green lighten-2 z-depth-1 btn" type="submit"
												name="action">
												Leriar <i class="material-icons right">send</i>
											</button>
										</div>
									</form>
								</div>
							</div>

						</div>
					</li>
					<li>
						<div class="collapsible-header">
							<i class="material-icons">whatshot</i>Notícia
						</div>
						<div class="collapsible-body">

							<!-- Notícia -->
							<div class="container">
								<div class="row">
									<form method="post"
										action="/leriadoApp/Leriado?command=PublicacaoController&acao=noticia"
										enctype="multipart/form-data">
										<div class="col s12">
											<input type="text" name="titulo"
												placeholder="Digite um título para sua publicação"
												class="center-align">
										</div>
										<div class="input-field col s12">
											<textarea class="materialize-textarea"
												placeholder="Sua publicação aqui" name="conteudo"></textarea>
										</div>
										<div class="file-field input-field">
											<div class="col s12">
												<div class="file-field input-field">
													<div class="btn green">
														<i class="material-icons">add</i> <input type="file"
															name="files" multiple>
													</div>
													<div class="file-path-wrapper">
														<input class="file-path validate" type="text"
															placeholder="Documentos/Imagens">
													</div>
												</div>
											</div>
										</div>
										<div class="col s12 center-align">
											<button class="green lighten-2 z-depth-1 btn" type="submit"
												name="action">
												Leriar <i class="material-icons right">send</i>
											</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
			