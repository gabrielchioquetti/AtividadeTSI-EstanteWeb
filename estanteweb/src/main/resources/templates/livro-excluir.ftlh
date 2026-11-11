<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Excluir Livro</title>
</head>
<body>
    <#include "cabecalho.ftlh">

    <h1>Confirmar exclusão</h1>

    <p>Tem certeza de que deseja excluir este livro?</p>

    <h2>${livro.titulo}</h2>
    <p>Autor: ${livro.autor}</p>
    <p>Ano: ${livro.ano?c}</p>
    <p>Valor: R$ ${livro.preco?c}</p>

    <p>Esta ação não poderá ser desfeita.</p>

    <form method="post" action="/livros/${livro.id?c}/excluir">
        <button type="submit">Confirmar exclusão</button>
    </form>

    <a href="/livros/${livro.id?c}">
        Cancelar
    </a>
</body>
</html>
