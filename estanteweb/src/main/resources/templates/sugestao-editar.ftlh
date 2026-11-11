<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Sugestão</title>
</head>
<body>
    <#include "cabecalho.ftlh">

    <h2>Editar Sugestão</h2>

    <#if erro??>
        <p class="erro">${erro}</p>
    </#if>

    <form method="post" action="/sugestoes/${sugestao.id?c}">

        <label for="nomeCliente">Nome do cliente:</label>
        <input
            type="text"
            id="nomeCliente"
            name="nomeCliente"
            value="${sugestao.nomeCliente}"
            required
        >

        <label for="livroSugerido">Livro sugerido:</label>
        <input
            type="text"
            id="livroSugerido"
            name="livroSugerido"
            value="${sugestao.livroSugerido}"
            required
        >

        <button type="submit">Salvar alterações</button>
    </form>

    <a href="/sugestoes">Cancelar</a>
</body>
</html>