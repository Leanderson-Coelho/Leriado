/*POVOANDO TABELA USUARIO*/
INSERT INTO usuario(email,senha,nome,sobrenome,sexo,datanasc,telefone,rua,cidade,estado,numero,cep)
	VALUES('rrobertoricardoyagorodrigues@oas.com','7o2fmowuxa','Roberto','Ricardo Yago Rodrigues','M','23/05/1997','(68) 3983-5473','Rua Belém','Rio Branco','AC','261','69918-354');
INSERT INTO usuario(email,senha,nome,sobrenome,sexo,datanasc,telefone,rua,cidade,estado,numero,cep)
	VALUES('eeloalizallanaassuncao@bb.com.br','0YOYEYsEIJ','Eloá','Liz Allana Assunção','F','16/09/1981','(68) 98746-0450','Rua Brigadeiro Franco','Curitiba','PR','541','80430-210');
INSERT INTO usuario(email,senha,nome,sobrenome,sexo,datanasc,telefone,rua,cidade,estado,numero,cep)
	VALUES('sophiereginalima__sophiereginalima@yogoothies.com.br','sj9diyn2FE','Sophie','Liz Allana Assunção','F','23/10/1999','(68) 2732-7774','Rua Camburiú','Rio Branco','AC','968','69918-496');

/*POVOANDO TABELA GRUPO*/
INSERT INTO grupo(nome,descricao,foto)
	VALUES('Aprendendo Postgres','Grupo para discutir conteúdos sobre Postgres','7f0694f2b12794b41614704b852b29b3');
INSERT INTO grupo(nome,descricao,foto)
	VALUES('Aprendendo MongoDB','Grupo para discutir conteúdos sobre MongoDB','6e09609f855e27b30a18a2f2a50ca16c');
INSERT INTO grupo(nome,descricao,foto)
		VALUES('Aprendendo Redis','Grupo para discutir conteúdos sobre Redis','c32995b0aaf1fc361c5f3fd907af47b3');

/*POVOANDO TABELA TEXTO*/
INSERT INTO texto(conteudo,usuarioid)
	VALUES('Donec porttitor diam nostra mauris id laoreet vulputate pellentesque dictumst, eros taciti nec felis auctor ultrices luctus senectus elit, amet tempus tristique curae pulvinar ut convallis netus. mi nec nulla imperdiet congue dolor commodo metus dictum, mi erat bibendum porttitor massa suspendisse praesent, condimentum nec habitasse diam mattis malesuada dictum. ',1);
INSERT INTO texto(conteudo,usuarioid)
	VALUES('Class imperdiet nisi ornare luctus semper fusce cubilia, aliquet semper quam elementum tempor in phasellus, nunc quisque odio sem risus mi. vestibulum risus in ullamcorper tincidunt tempus curae purus enim, sem lorem tristique viverra curabitur quisque tincidunt cursus, etiam nullam vulputate amet ligula gravida feugiat. bibendum sit duis vehicula, auctor.',2);
INSERT INTO texto(conteudo,usuarioid)
	VALUES('Facilisis per vel pharetra etiam massa potenti primis, ut eget euismod nisi facilisis ac porttitor netus, vestibulum semper dictum odio maecenas platea. lacus ligula lectus duis sollicitudin litora nisi condimentum ipsum fusce, lobortis habitasse etiam rutrum vehicula sagittis ac semper imperdiet sit, auctor purus senectus dictum varius odio ligula ullamcorper.',3);
INSERT INTO texto(conteudo,usuarioid)
	VALUES('Porttitor scelerisque malesuada torquent luctus nostra fames mollis congue dolor hac, orci hac lobortis integer nunc augue nibh rutrum rhoncus habitasse, ornare eleifend tellus diam placerat commodo etiam vestibulum phasellus. suspendisse ipsum sem euismod nulla eros curabitur rutrum, eros aenean urna aptent placerat lobortis, amet enim euismod cras egestas fringilla.',2);
INSERT INTO texto(conteudo,usuarioid)
	VALUES('Volutpat tristique donec augue eu facilisis, molestie nisi rhoncus curabitur, magna mauris phasellus accumsan. fusce leo a nostra luctus netus amet duis commodo dictumst mi, risus habitant arcu per venenatis habitant suspendisse condimentum fusce, accumsan erat inceptos habitasse himenaeos rhoncus blandit mollis eu. orci aenean lacus vestibulum nam, sollicitudin varius.',3);
INSERT INTO texto(conteudo,usuarioid)
	VALUES('Suscipit quam hac faucibus libero nisi torquent ante feugiat, nec massa porta torquent dapibus elementum lobortis suspendisse rhoncus, ut consectetur suscipit ante tristique netus vel. etiam consectetur rutrum at habitant lectus accumsan dui facilisis, tellus turpis aliquet non urna habitant ultrices himenaeos vehicula, pellentesque lacus potenti nec id fusce elit. ',1);
INSERT INTO texto(conteudo,usuarioid)
	VALUES('Etiam sapien consectetur metus malesuada tristique sem quisque gravida venenatis etiam, nisl etiam senectus sociosqu varius viverra donec vitae. quam magna orci lobortis aenean nulla sociosqu lectus iaculis turpis sociosqu, ligula metus senectus dapibus curabitur etiam sodales donec ligula eros donec, vulputate taciti per sollicitudin luctus taciti cursus facilisis aliquet.',1);
INSERT INTO texto(conteudo,usuarioid)
	VALUES('Magna phasellus orci platea ornare urna curae, magna sit netus nunc pharetra, lacus sollicitudin sed orci pretium. eleifend lectus platea sodales viverra turpis venenatis donec morbi elementum velit egestas purus porttitor, quisque curabitur quam sem euismod at arcu volutpat erat vehicula quisque. molestie fames lorem sed risus, sit ornare tempus.',1);

/*POVOANDO TABELA COMENTARIO*/
INSERT INTO comentario(textoid,respondeid)
	VALUES(2,1);
INSERT INTO comentario(textoid,respondeid)
	VALUES(3,1);

/*POVOANDO TABELA PUBLICACAO*/
INSERT INTO publicacao(textoid)
	VALUES(1);
INSERT INTO publicacao(textoid)
	VALUES(4);
INSERT INTO publicacao(textoid)
	VALUES(5);
INSERT INTO publicacao(textoid)
	VALUES(6);
INSERT INTO publicacao(textoid)
	VALUES(7);
INSERT INTO publicacao(textoid)
	VALUES(8);

/*POVOANDO TABELA FOTO*/
INSERT INTO foto(publicacaoid,arquivo)
	VALUES(4,'e199eb58bd6c24f40881ea7b15cd10c4');
INSERT INTO foto(publicacaoid,arquivo)
	VALUES(7,'e3c66fdb8ff3741b518a755bcc8832ed');
INSERT INTO foto(publicacaoid,arquivo)
	VALUES(8,'b136693d02a2ce0468ab51be43f0e04f');

/*POVOANDO TABELA NOTICIA*/
INSERT INTO noticia(publicacaoid,titulo)
	VALUES(5,'Curae primis ipsum magna duis, euismod aptent primis eros, viverra et cursus.');

/*POVOANDO TABELA LINK*/
INSERT INTO link(publicacaoid,link)
	VALUES(6,'https://estudante.ifpb.edu.br/cursos/12');

/*POVOANDO TABELA VISUALIZA*/
INSERT INTO visualiza(publicacaoid,usuarioid)
	VALUES(1,2);
INSERT INTO visualiza(publicacaoid,usuarioid)
	VALUES(1,3);

/*POVOANDO TABELA SEGUE*/
INSERT INTO segue(segueid,seguidoid)
	VALUES(1,2);
INSERT INTO segue(segueid,seguidoid)
	VALUES(2,1);
INSERT INTO segue(segueid,seguidoid)
	VALUES(1,3);
INSERT INTO segue(segueid,seguidoid)
	VALUES(3,1);

/*POVOANDO TABELA CURTE*/
INSERT INTO curte(textoid,usuarioid)
	VALUES(1,2);
INSERT INTO curte(textoid,usuarioid)
	VALUES(1,3);
INSERT INTO curte(textoid,usuarioid)
	VALUES(2,1);
INSERT INTO curte(textoid,usuarioid)
	VALUES(3,1);

/*POVOANDO TABELA MARCA*/
INSERT INTO marca(textoid,usuarioid)
	VALUES(1,2);
INSERT INTO marca(textoid,usuarioid)
	VALUES(1,3);

/*POVOANDO TABELA ADMGRUPO*/
INSERT INTO admgrupo(usuarioid,grupoid)
	VALUES(1,1);
INSERT INTO admgrupo(usuarioid,grupoid)
	VALUES(2,2);
INSERT INTO admgrupo(usuarioid,grupoid)
	VALUES(1,3);

/*POVOANDO TABELA PARTICIPAGRUPO*/
INSERT INTO participagrupo(usuarioid,grupoid)
	VALUES(1,1);
INSERT INTO participagrupo(usuarioid,grupoid)
	VALUES(1,2);
INSERT INTO participagrupo(usuarioid,grupoid)
	VALUES(1,3);
INSERT INTO participagrupo(usuarioid,grupoid)
	VALUES(2,1);
INSERT INTO participagrupo(usuarioid,grupoid)
	VALUES(2,2);
INSERT INTO participagrupo(usuarioid,grupoid)
	VALUES(2,3);
INSERT INTO participagrupo(usuarioid,grupoid)
	VALUES(3,1);

/*POVOANDO TABELA COMPARTILHA*/
INSERT INTO compartilha(usuarioid,publicacaoid,grupoid)
	VALUES(1,1,1);
INSERT INTO compartilha(usuarioid,publicacaoid,grupoid)
	VALUES(2,5,1);
INSERT INTO compartilha(usuarioid,publicacaoid,grupoid)
	VALUES(1,6,1);
INSERT INTO compartilha(usuarioid,publicacaoid,grupoid)
	VALUES(3,7,1);


/*POVOANDO TABELA FOTONOTICIA*/
INSERT INTO fotonoticia(noticiaid,fotoid)
	VALUES(5,4);

/*POVOANDO TABELA FOTOPERFIL*/
INSERT INTO fotoperfil(usuarioid,fotoid)
	VALUES(1,8);


