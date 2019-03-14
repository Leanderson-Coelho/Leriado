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
    <title>Document</title>
</head>

<body>
    <!-- MENU -->
    <jsp:include page="header.html"/>
    <div class="row"></div>
    <!-- CONTEUDO -->
    <div class="row">
        <!-- PERFIL -->
        <div class="col s2 red lighten-4">
            PERFIL
        </div>
        <!-- FEED -->
        <div class="col s8 blue lighten-4">
            FEED
        </div>
        <!-- AMIGOS -->
        <div class="col s2 green lighten-4">
            AMIGOS
        </div>
    </div>
    <jsp:include page="footer.html"/>
</body>
</html>