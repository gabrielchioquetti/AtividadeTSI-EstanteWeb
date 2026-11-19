<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Excluir Livro</title>
</head>
<body>
    <#include "cabecalho.ftlh">

    <h2>Confirmar exclusão</h2>

    <p>Tem certeza de que deseja excluir este livro?</p>

    <h3>${livro.titulo}</h3>
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
