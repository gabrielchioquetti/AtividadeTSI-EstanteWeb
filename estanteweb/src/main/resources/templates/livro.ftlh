<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalhes do Livro</title>
</head>
<body>
    <#include "cabecalho.ftlh">
    <h2>Título: ${livro.titulo}</h2>
    <p>Autor: ${livro.autor}</p>
    <p>Ano: ${livro.ano?c}</p>
    <p>Valor: R$ ${livro.preco?c}</p>
    <a href="/livros/html">Voltar para o acervo</a>
    <#if usuarioLogado??>
        <a href="/livros/${livro.id?c}/editar">Editar</a>
        <a href="/livros/${livro.id?c}/excluir">Excluir Livro</a>
    </#if>

    <#if outrosLivros?? && outrosLivros?has_content>
        <h3>Outros livros do mesmo autor</h3>

        <ul>
            <#list outrosLivros as outro>
                <li>
                    <a href="/livros/${outro.id}">${outro.titulo}</a>
                </li>
            </#list>
        </ul>
    </#if>
</body>
</html>