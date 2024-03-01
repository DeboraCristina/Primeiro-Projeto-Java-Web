<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <jsp:include page="menu.jsp" />
    </div>
    <br>
    <main>
        <form action="disciplina" method="post">
            <h1>Disciplinas</h1>
            <br>
            <div>
                <label for="codigo">Código</label>
                <input type="text" placeholder="codigo" name="codigo" id="codigo"
                       value='<c:out value="${disciplina.codigo}"/>'>
                <input type="submit" name="botao" value="Buscar" id="buscar">
            </div>
            <div>
                <label for="nome">Nome</label>
                <input type="text" placeholder="nome" name="nome" id="nome"
                       value='<c:out value="${disciplina.nome}"/>'>
            </div>
            <div>
                <label for="professor">Professor</label>
                <select name="professor" name="professor" id="professor">
                    <option value="0">Escolha um Professor</option>
                    <c:forEach items="${professores}" var="p">
                        <c:if test="${(empty disciplina) || (p.codigo ne disciplina.professor.codigo)}">
                            <option value="${p.codigo}"><c:out value="${p}"/></option>
                        </c:if>
                        <c:if test="${p.codigo eq disciplina.professor.codigo}">
                            <option selected="selected" value="${p.codigo}"><c:out value="${p}"/></option>
                        </c:if>
                    </c:forEach>
                </select>
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
                        <th>Professor</th>
                    </thead>
                    <tbody>
                        <c:if test="${not empty disciplinas}">
                            <c:forEach items="${disciplinas}" var="d">
                                <tr>
                                    <td><c:out value="${d.codigo}"/></td>
                                    <td><c:out value="${d.nome}"/></td>
                                    <td><c:out value="${d.professor}"/></td>
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
