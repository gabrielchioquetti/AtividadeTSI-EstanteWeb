
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Livro</title>
</head>
<body>
    <#include "cabecalho.ftlh">

    <h1>Editar Livro</h1>

    <#if erro??>
        <div style="color: red; margin-bottom: 15px; font-weight: bold;">
            <span>${erro}</span>
        </div>
    </#if>

    <form method="post" action="/livros/${livro.id?c}">
        <label for="titulo">Título:</label>
        <input id="titulo" name="titulo" type="text"
               value="${livro.titulo}" required>
        <br>

        <label for="autor">Autor:</label>
        <input id="autor" name="autor" type="text"
               value="${livro.autor}" required>
        <br>

        <label for="ano">Ano:</label>
        <input id="ano" name="ano" type="number"
               value="${livro.ano?c}" required>
        <br>

        <label for="preco">Preço:</label>
        <input id="preco" name="preco" type="number"
               min="0" step="0.01" placeholder="0,00"
               value="${livro.preco?c}" required>
        <br>

        <button type="submit">Salvar alterações</button>
    </form>
</body>
</html>