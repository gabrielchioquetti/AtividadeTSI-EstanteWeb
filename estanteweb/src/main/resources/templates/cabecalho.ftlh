<header>
    <h1>EstanteWEB</h1>

    <nav>
        <a href="/">Início</a> |
        <a href="/livros/html">Livros</a> |
        <a href="/sugestoes">Sugestões</a>
    </nav>

    <#if usuarioLogado??>
        <span>Olá, ${usuarioLogado.nome}</span>

        <form action="/logout" method="post">
            <button type="submit">Sair</button>
        </form>
    <#else>
        <a href="/login">Entrar</a>
    </#if>
</header>