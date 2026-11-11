<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Excluir Sugestão</title>
</head>
<body>
    <#include "cabecalho.ftlh">

    <h2>Confirmar exclusão</h2>

    <p>Deseja realmente excluir esta sugestão?</p>

    <p>
        <strong>Cliente:</strong> ${sugestao.nomeCliente}
    </p>

    <p>
        <strong>Livro sugerido:</strong> ${sugestao.livroSugerido}
    </p>

    <form method="post"
          action="/sugestoes/${sugestao.id?c}/excluir">

        <button type="submit">Confirmar exclusão</button>
    </form>

    <a href="/sugestoes">Cancelar</a>
</body>
</html>