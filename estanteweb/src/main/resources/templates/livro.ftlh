<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <#include "cabecalho.ftlh">
    <h1>Título: ${livro.titulo}</h1>
    <p>Autor: ${livro.autor}</p>
    <p>Ano: ${livro.ano?c}</p>
    <p>Valor: R$ ${livro.preco?c}</p>
    <a href="/livros/html">Voltar para o acervo</a>

    <#if outrosLivros?? && outrosLivros?has_content>
        <h2>Outros livros do mesmo autor</h2>

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