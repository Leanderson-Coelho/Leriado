<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Atulizar</title>
    <style>
        fieldset{
            max-width: 50%;
        }
    </style>
</head>
<body>
    <fieldset>
        <form action="Leriado?command=UsuarioController&acao=atualizar" method="post">
            <input type="text" name="email" placeholder="Email"/>
            <input type="text" name="nome" placeholder="Nome" />
            <input type="text" name="sobrenome" placeholder="Sobrenome" />
            <input type="password" name="senha" placeholder="Senha"/>
            <select name="sexo">
                <option>M</option>
                <option>F</option>
            </select>
            <input type="text" name="telefone" placeholder="(XX) XXXXX-XXXX" />
            <input type="text" name="cidade" placeholder="Cidade" />
            <input type="text" name="rua" placeholder="Rua" />
            <input type="text" name="estado" placeholder="Estado" />
            <input type="text" name="numero" placeholder="Numero" />
            <input type="text" name="cep" placeholder="Cep" />
            <input type="date" name="data" placeholder="dd/MM/yyyy"/>
            <input type="submit" value="Atualizar"/>
        </form>
    </fieldset>
</body>
</html>