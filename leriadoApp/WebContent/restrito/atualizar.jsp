<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <!-- JQUERY -->
    <script src="../js/JQuery.js"></script>
    <!-- MASCARAS JQUERY PLUGIN -->
    <script src="../js/jQuery-Mask-Plugin-master/src/jquery.mask.js"></script>
    <!-- MASCARAS JQUERY CONFIG -->
    <script src="../js/mascaras.js"></script>
    <!-- MATERIALIZE CSS-->
    <link rel="stylesheet" href="../css/materialize.css">
    <!-- MATERIALIZE JS -->
    <script src="../js/materialize.js"></script>
    <!-- ICONS MATERIALIZA -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- CUSTOM -->
    <link rel="stylesheet" href="../css/custom.css">
    <title>Document</title>
</head>

<body>
    <jsp:include page="headerOn.jsp"/>
    <div class="row container">
        <div class="col s12 ">
            <form method="POST" action="/leriadoApp/Leriado?command=UsuarioController&acao=atualizar" class="col s12">
                <div class="row">
                    <div class="col s6 input-field">
                        <input type="text" name="nome" id="nome" required />
                        <label for="nome">Nome</label>
                    </div>
                    <div class="col s6 input-field">
                        <input type="text" name="sobrenome" id="sobrenome" required />
                        <label for="sobrenome">Sobrenome</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col s6 input-field">
                        <input class="validate" type="email" name="email" id="email" required
                            pattern="^[\w\.\-]+@([\w\-]+\.)+[A-Za-z]{2,4}$" />
                        <label for="email">Email</label>
                    </div>
                    <div class="col s6 input-field">
                        <input type="password" name="senha" id="senhaDesktop" required min="8" />
                        <label for="senhaDesktop">Senha</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col s6 input-field">
                        <p class="col s6">
                            <label>
                                <input name="sexo" type="radio" value="M" required />
                                <span><b>Masculino</b></span>
                            </label>
                        </p>
                        <p>
                            <label>
                                <input name="sexo" type="radio" value="F" required />
                                <span><b>Feminino</b></span>
                            </label>
                        </p>
                    </div>
                    <div class="col s6 input-field">
                        <input type="text" name="telefone" id="telefone" pattern="^\(\d{2}\) \d{4}\d?-\d{4}$" />
                        <label for="telefone">Telefone</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col s6 input-field">
                        <input type="text" name="data" id="data" required />
                        <label for="data">Data de Nascimento</label>
                    </div>
                    <div class="col s6 input-field">
                        <input type="text" name="numero" id="numero" pattern="\d+" />
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
                        <input type="text" name="cep" id="cep" pattern="\d{5}-\d{3}" />
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
        </div>
    </div>
</body>

</html>