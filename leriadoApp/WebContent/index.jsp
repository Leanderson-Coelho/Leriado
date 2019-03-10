<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
  <meta charset="UTF-8">
  <!-- JQUERY -->
  <script src="js/JQuery.js"></script>
  <!-- MASCARAS JQUERY PLUGIN -->
  <script src="js/jQuery-Mask-Plugin-master/src/jquery.mask.js"></script>
  <!-- MASCARAS JQUERY CONFIG -->
  <script src="js/mascaras.js"></script>
  <!-- MATERIALIZE CSS-->
  <link rel="stylesheet" href="css/materialize.css">
  <!-- MATERIALIZE JS -->
  <script src="js/materialize.js"></script>
  <!-- ICONS MATERIALIZA -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <!-- CUSTOM -->
  <link rel="stylesheet" href="css/custom.css">
  <title>Inicio</title>
  <script>
    // INICIALIZAR CAMPO DATA
    $(document).ready(function () {
      $('.datepicker').datepicker();
    });
  </script>
</head>

<body>
  <!-- MENU DESKTOP-->
  <header>
    <nav>
      <div class="container">
        <div class="nav-wrapper">
          <a href="#" class="brand-logo "><img class="logo" src="img/logo.png"/></a>
          <ul class="right hide-on-med-and-down">
            <form method="post" action="Leriado?command=UsuarioController&acao=login">
              <div class="row">
                <div class="input-field col l4">
                  <input class="validate camposLogin" type="text" id="emailLogin" name="login" required pattern="^[\w\.\-]+@([\w\-]+\.)+[A-Za-z]{2,4}$" />
                  <label for="emailLogin">Email</label>
                </div>
                <div class="input-field col l4">
                  <input class="camposLogin" type="password" id="senha" name="senha" required min="8" />
                  <label>Senha</label>
                </div>
                <div class="input-field col l4">
                  <button type="submit" class="btn waves-effect waves-light">Enviar</button>
                </div>
              </div>
            </form>
          </ul>
        </div>
      </div>
    </nav>
  </header>
  <!-- MENU MOBILE -->
  <div class="row container hide-on-large-only">
    <form action="" method="">
      <div class="input-field col s12">
        <input class="validate" name="email" id="email" type="email" />
        <label for="email">Email</label>
      </div>
      <div class="input-field col s12">
        <input type="password" name="senha" id="senha" />
        <label for="senha">Senha</label>
      </div>
      <div class="input-field col s2">
          <button type="submit" class="btn waves-effect waves-light center">Enviar</button>
      </div>
    </form>
  </div>
  <!-- CONTEUDO -->
  <div class="row orange lighten-5 separador"></div>
  <div class="orange lighten-5">
    <div class="container hide-on-med-and-down">
      <div class="row">
        <div class="col s6">
          <section>
            <div class="row">
              <div class="col s6">
                <img class="imgConteudo" src="img/carambola.png" />
                <p>Carambola Inc</p>
              </div>
              <div class="col s6">
                <img class="imgConteudo" src="img/logo.png" />
              </div>
            </div>
          </section>
        </div>
        <!-- FORMULÁRIO DE CADASTRO -->
        <div class="col s6">
          <section>
            <h4>Comece um Leriado</h4>
            <h5>É na poíva, e sempre será.</h5>
            <h6 class="red lighten-2">${requestScope.msgsErro}</h6>
            <form method="POST" action="Leriado?command=UsuarioController&acao=cadastrar" class="col s12">
              <div class="row">
                <div class="col s6 input-field">
                  <input type="text" name="nome" id="nome" required pattern="^[A-Za-z]{2,}$"/>
                  <label for="nome">Nome</label>
                </div>
                <div class="col s6 input-field">
                  <input type="text" name="sobrenome" id="sobrenome" required pattern="^[A-Za-z]{2,}$"/>
                  <label for="sobrenome">Sobrenome</label>
                </div>
              </div>
              <div class="row">
                <div class="col s6 input-field">
                  <input class="validate" type="email" name="email" id="email" required pattern="^[\w\.\-]+@([\w\-]+\.)+[A-Za-z]{2,4}$"/>
                  <label for="email">Email</label>
                </div>
                <div class="col s6 input-field">
                  <input type="password" name="senha" id="senhaDesktop" required min="8"/>
                  <label for="senhaDesktop">Senha</label>
                </div>
              </div>
              <div class="row">
                <div class="col s6 input-field">
                  <p class="col s6">
                    <label>
                      <input name="sexo" type="radio" value="M" required/>
                      <span><b>Masculino</b></span>
                    </label>
                  </p>
                  <p>
                    <label>
                      <input name="sexo" type="radio" value="F" required/>
                      <span><b>Feminino</b></span>
                    </label>
                  </p>
                </div>
                <div class="col s6 input-field">
                  <input type="text" name="telefone" id="telefone" pattern="^\(\d{2}\) \d{4}\d?-\d{4}$"/>
                  <label for="telefone">Telefone</label>
                </div>
              </div>
              <div class="row">
                <div class="col s6 input-field">
                  <input type="text" name="data" id="data" required/>
                  <label for="data">Data de Nascimento</label>
                </div>
                <div class="col s6 input-field">
                  <input type="text" name="numero" id="numero" pattern="\d+"/>
                  <label for="numero">Número de casa</label>
                </div>
              </div>
              <div class="row">
                <div class="col s6 input-field">
                  <input type="text" name="rua" id="rua" />
                  <label for="rua">Rua</label>
                </div>
                <div class="col s6 input-field">
                  <input type="text" name="cidade" id="cidade" />
                  <label for="Cidade">Cidade</label>
                </div>
              </div>
              <div class="row">
                <div class="col s6 input-field">
                  <input type="text" name="cep" id="cep" pattern="\d{5}-\d{3}"/>
                  <label for="cep">CEP</label>
                </div>
                <div class="col s6 input-field">
                  <input type="text" name="estado" id="estado" />
                  <label for="estado">Estado</label>
                </div>
              </div>
              <div class="input-field">
                <button class="btn waves-effect waves-light" type="submit" name="action">Que o Leriado comece!!
                  <i class="material-icons right">send</i>
                </button>
              </div>
            </form>
          </section>
        </div>
      </div>
    </div>
  </div>
  <!-- RODAPÉ -->
  <footer class="page-footer">
    <div class="footer-copyright">
      <div class="container center">
          LERIADO © 2019
      </div>
    </div>
  </footer>
</body>

</html>