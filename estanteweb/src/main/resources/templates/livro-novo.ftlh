<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de livro</title>
</head>
<body>
    <#include "cabecalho.ftlh">
    <h1>Cadastro de livro</h1>

    <#if erro??>
        <div style="color: red; margin-bottom: 15px; font-weight: bold;">
            <span>${erro}</span>
        </div>
    </#if>
    <form method="post" action="/livros">
        <label for="titulo">Titulo:</label>
        <input id="titulo" name="titulo" type="text" value="${titulo!}">
        <br>
        <label for="autor">Autor:</label>
        <input id="autor" name="autor" type="text" value="${autor!}">
        <br>
        <label for="ano">Ano:</label>
        <input id="ano" name="ano" type="number" value="${(ano?c)!''}">
        <br>
        <label for="preco">Preço:</label>
        <input id="preco" name="preco" type="number" min="0" step="0.01" placeholder="0,00" value="${(preco?c)!''}">
        <br>
        <button type="submit">Cadastrar</button>
    </form>
</body>
</html>