<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>
    <#include "cabecalho.ftlh">

    <#if erro??>
        <div style="color: red; margin-bottom: 15px; font-weight: bold;">
            <span>${erro}</span>
        </div>
    </#if>

    <form method="post" action="/login">
        <label for="email">Email:</label>
        <input id="email" name="email" type="email" placeholder="Ex: João" value="${email!}" required>
        <br>
        <label for="senha">Senha:</label>
        <input id="senha" name="senha" type="password" required>
        <br>
        <button type="submit">Entrar</button>
    </form>
</body>
</html>