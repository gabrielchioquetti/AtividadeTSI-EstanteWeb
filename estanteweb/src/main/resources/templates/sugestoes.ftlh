<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <#include "cabecalho.ftlh">
    <#if enviada?? && enviada>
        <p class="enviada">Sugestão enviada com sucesso!</p>
    </#if>
    <h2>Sugestões recebidas:</h2>
    <ul>
        <#list sugestoes as sugestao>
            <li>Cliente: ${sugestao.nomeCliente}, Livro Sugerido: ${sugestao.livroSugerido}</li>
        </#list>
    </ul>
</body>
</html>