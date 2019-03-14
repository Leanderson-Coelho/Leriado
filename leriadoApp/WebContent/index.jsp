<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  <jsp:include page="restrito/header.html"/>
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
            <c:if test="${msgErro != null}">
            	<h5 class="red-text">${msgErro}</h5>
            </c:if>
            <h6 class="red lighten-2">${requestScope.msgsErro}</h6>
            <form method="POST" action="Leriado?command=UsuarioController&acao=cadastrar" class="col s12">
              <div class="row">
                <div class="col s6 input-field">
                  <input type="text" name="nome" id="nome" required />
                  <label for="nome">Nome</label>
                </div>
                <div class="col s6 input-field">
                  <input type="text" name="sobrenome" id="sobrenome" required/>
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
  <jsp:include page="restrito/footer.html"/>
</body>
</html>