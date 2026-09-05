<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <#include "cabecalho.ftlh">
    <#if erro??>
        <p class="erro">${erro}</p>
    </#if>
    <form action="/contato" method="post">
        <label for="nome">Nome:</label>
        <input id="nome" name="nome" type="text" value="${nome!}">
        <br>
        <label for="mensagem">Mensagem:</label>
        <input id="mensagem" name="mensagem" type="text">
        <br>
        <button type="submit">Enviar</button>
    </form>
</body>
</html>