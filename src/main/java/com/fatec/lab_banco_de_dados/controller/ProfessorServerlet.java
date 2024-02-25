package com.fatec.lab_banco_de_dados.controller;

import com.fatec.lab_banco_de_dados.model.Professor;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/professor")
public class ProfessorServerlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public ProfessorServerlet()
    {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String cmd = request.getParameter("botao");
        String codigo = request.getParameter("codigo");
        String nome = request.getParameter("nome");
        String titulacao = request.getParameter("titulacao");

        String saida = "";
        String erro = "";
        Professor p = new Professor();
        List<Professor> professores = new ArrayList<>();

        if (!cmd.equalsIgnoreCase("listar"))
            p.setCodigo(Integer.parseInt(codigo));
        if (cmd.equalsIgnoreCase("cadastrar") || cmd.equalsIgnoreCase("alterar"))
        {
            p.setNome(nome);
            p.setTitulacao(titulacao);
        }

        try
        {
            if (cmd.equalsIgnoreCase("cadastrar"))
            {
                inserirProfessor(p);
                saida = "Professor cadastrado com sucesso!";
                p = new Professor();
            }
            else if (cmd.equalsIgnoreCase("alterar"))
            {
                altualizarProfessor(p);
                saida = "Dados de professor alterados com sucesso!";
                p = new Professor();
            }
            else if (cmd.equalsIgnoreCase("excluir"))
            {
                deletarProfessor(p);
                saida = "Dados de professor excluido com sucesso!";
                p = new Professor();
            }
            else if (cmd.equalsIgnoreCase("buscar"))
                p = buscarProfessor(p);
            else if (cmd.equalsIgnoreCase("listar"))
                professores = listarProfessor();
        }
        catch (SQLException | ClassNotFoundException e)
        {
            erro = e.getMessage();
        }
        finally {
            request.setAttribute("saida", saida);
            request.setAttribute("erro", erro);
            request.setAttribute("professor", p);
            request.setAttribute("professores", professores);

            RequestDispatcher rd = request.getRequestDispatcher("professor.jsp");
            rd.forward(request, response);

        }
    }

    private void inserirProfessor(Professor p) throws SQLException, ClassNotFoundException
    {
        System.out.println(p);
    }

    private void altualizarProfessor(Professor p) throws SQLException, ClassNotFoundException
    {
        System.out.println(p);
    }

    private void deletarProfessor(Professor p) throws SQLException, ClassNotFoundException
    {
        System.out.println(p.getCodigo());
    }

    private Professor buscarProfessor(Professor p) throws SQLException, ClassNotFoundException
    {
        p.setNome("Fulanno");
        p.setTitulacao("Mestre");
        return p;
    }

    private List<Professor> listarProfessor() throws SQLException, ClassNotFoundException
    {
        List<Professor> professores = new ArrayList<>();
        Professor p1 = new Professor(10, "Beltrano", "Mestre");
        Professor p2 = new Professor(10, "Cicrano", "Mestre");
        Professor p3 = new Professor(10, "Marcio", "Mestre");
        professores.add(p1);
        professores.add(p2);
        professores.add(p3);
        return professores;
    }
}