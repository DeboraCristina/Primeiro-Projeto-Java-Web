<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Professor</title>
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
    <div>
        <jsp:include page="menu.jsp" />
    </div>
    <br>
    <main>
        <form action="professor" method="post">
            <h1>Professor</h1>
            <br>
            <div>
                <label for="codigo">Código</label>
                <input type="number" placeholder="0" name="codigo" id="codigo"
                       value='<c:out value="${professor.codigo}"/>'>
                <input type="submit" name="botao" value="Buscar" id="buscar">
            </div>
            <div>
                <label for="nome">Nome</label>
                <input type="text" placeholder="nome" name="nome" id="nome"
                       value='<c:out value="${professor.nome}"/>'>
            </div>
            <div>
                <label for="titulacao">Titulação</label>
                <input type="text" placeholder="titulacao" name="titulacao" id="titulacao"
                       value='<c:out value="${professor.titulacao}"/>'>
            </div>
            <div>
                <input type="submit" name="botao" value="Cadastrar" id="Cadastrar">
                <input type="submit" name="botao" value="Alterar" id="Alterar">
                <input type="submit" name="botao" value="Excluir" id="Excluir">
            </div>
            <div>
                <input type="submit" name="botao" value="Listar" id="Listar">
            </div>
            <div class="tabela_container">
                <table>
                    <thead>
                        <th>Código</th>
                        <th>Nome</th>
                        <th>Titulação</th>
                    </thead>
                    <tbody>
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
