<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <#include "cabecalho.ftlh">
    <h2>Acervo EstanteWeb: </h2>
    <#if livros?size == 0>
        <p>Nenhum livro no acervo no momento</p>
    <#else>
        <ul>
            <#list livros as livro>
                <li>${livro.titulo}, de ${livro.autor} e valor R$ ${livro.preco?c}</li>
            </#list>
        </ul>
    </#if>
    
</body>
</html>