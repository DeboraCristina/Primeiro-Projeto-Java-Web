<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Disciplina</title>
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
    <div>
        <header>
            <a href="index.jsp">Home</a>
            <a href="professor.jsp">Professor</a>
            <a href="disciplina.jsp">Disciplina</a>
        </header>
    </div>
    <br>
    <main>
        <div>
            <label for="codigo">Código</label>
            <input type="text" placeholder="codigo" id="codigo">
            <input type="submit" value="Buscar" id="buscar">
        </div>
        <div>
            <label for="nome">Nome</label>
            <input type="text" placeholder="nome" id="nome">
        </div>
        <div>
            <label for="professor">Professor</label>
            <select name="professor" id="professor">
            </select>
        </div>
        <div>
            <input type="submit" value="Cadastrar" id="Cadastrar">
            <input type="submit" value="Alterar" id="Alterar">
            <input type="submit" value="Excluir" id="Excluir">
            <input type="submit" value="Listar" id="Listar">
        </div>
        <div>
            <textarea name="lista" id="lista" cols="30" rows="10"></textarea>
        </div>
    </main>
</body>
</html>
