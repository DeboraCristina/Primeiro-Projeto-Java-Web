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
        <form action="disciplina" method="post">
            <h1>Disciplinas</h1>
            <br>
            <div>
                <label for="codigo">Código</label>
                <input type="text" placeholder="codigo" name="codigo" id="codigo">
                <input type="submit" name="botao"value="Buscar" id="buscar">
            </div>
            <div>
                <label for="nome">Nome</label>
                <input type="text" placeholder="nome" name="nome" id="nome">
            </div>
            <div>
                <label for="professor">Professor</label>
                <select name="professor" name="professor" id="professor">
                </select>
            </div>
            <div>
                <input type="submit" name="botao"value="Cadastrar" id="Cadastrar">
                <input type="submit" name="botao"value="Alterar" id="Alterar">
                <input type="submit" name="botao"value="Excluir" id="Excluir">
            </div>
            <div>
                <input type="submit" name="botao"value="Listar" id="Listar">
            </div>
            <div class="tabela_container">
                <table>
                    <thead>
                        <th>Código</th>
                        <th>Nome</th>
                        <th>Titulação</th>
                    </thead>
                    <tbody>
                        <!-- TODO:  -->
                        <c:if test="${not empty professores}">
                            <c:forEach items="${professores}" var="p">
                                <tr>
                                    <td><c:out value="${p.codigo}"/></td>
                                    <td><c:out value="${p.nome}"/></td>
                                    <td><c:out value="${p.titulacao}"/></td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </tbody>
                </table>
            </div>
            <div>
                <c:if test="${not empty erro}">
                    <h3 class="erro"><c:out value="${erro}"/></h3>
                </c:if>
                <c:if test="${not empty saida}">
                    <h3 class="saida"><c:out value="${saida}"/></h3>
                </c:if>
            </div>
        </form>
    </main>
</body>
</html>
