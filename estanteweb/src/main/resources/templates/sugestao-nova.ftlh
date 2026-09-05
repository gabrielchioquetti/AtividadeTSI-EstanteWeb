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
    <form action="/sugestoes" method="post">
        <label for="nomeCliente">Nome do cliente:</label>
        <input id="nomeCliente" name="nomeCliente" type="text" value="${nomeCliente!}">
        <br>
        <label for="livroSugerido">Livro sugerido::</label>
        <input id="livroSugerido" name="livroSugerido" type="text" value="${livroSugerido!}">
        <br>
        <button type="submit">Enviar sugestão</button>
    </form>
</body>
</html>