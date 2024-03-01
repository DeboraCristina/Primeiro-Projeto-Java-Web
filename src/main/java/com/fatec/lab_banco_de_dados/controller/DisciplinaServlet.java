package com.fatec.lab_banco_de_dados.controller;

import com.fatec.lab_banco_de_dados.model.Disciplina;
import com.fatec.lab_banco_de_dados.model.Professor;
import com.fatec.lab_banco_de_dados.persistence.DisciplinaDAO;
import com.fatec.lab_banco_de_dados.persistence.GenericDAO;
import com.fatec.lab_banco_de_dados.persistence.ProfessorDAO;
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

@WebServlet("/disciplina")
public class DisciplinaServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public DisciplinaServlet()
    {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String erro = "";
        List<Professor> professores = new ArrayList<>();

        GenericDAO genericDAO = new GenericDAO();
        ProfessorDAO professorDAO = new ProfessorDAO(genericDAO);
        try {
            professores = professorDAO.list();
        } catch (SQLException | ClassNotFoundException e) {
            erro = e.getMessage();
        }
        finally {
            request.setAttribute("erro", erro);
            request.setAttribute("professores", professores);

            RequestDispatcher rd = request.getRequestDispatcher("disciplina.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String cmd = request.getParameter("botao");
        String codigo = request.getParameter("codigo");
        String nome = request.getParameter("nome");
        String professor = request.getParameter("professor");

        String saida = "";
        String erro = "";
        Disciplina d = new Disciplina();
        List<Disciplina> disciplinas = new ArrayList<>();
        List<Professor> professores = new ArrayList<>();

        if (!cmd.equalsIgnoreCase("listar"))
            d.setCodigo(Integer.parseInt(codigo));
        try {
            professores = listarProfessor();

            if (cmd.equalsIgnoreCase("cadastrar") || cmd.equalsIgnoreCase("alterar"))
            {
                d.setNome(nome);

                Professor p = new Professor();
                p.setCodigo(Integer.parseInt(professor));
                p = buscarProfessor(p);
                d.setProfessor(p);
            }

            if (cmd.equalsIgnoreCase("cadastrar"))
            {
                inserirDisciplina(d);
                saida = "Disciplina cadastrada com sucesso!";
                d = new Disciplina();
            }
            else if (cmd.equalsIgnoreCase("alterar"))
            {
                altualizarDisciplina(d);
                saida = "Dados da disciplina alterados com sucesso!";
                d = new Disciplina();
            }
            else if (cmd.equalsIgnoreCase("excluir"))
            {
                deletarDisciplina(d);
                saida = "Dados da disciplina excluidos com sucesso!";
                d = new Disciplina();
            }
            else if (cmd.equalsIgnoreCase("buscar"))
                d = buscarDisciplina(d);
            else if (cmd.equalsIgnoreCase("listar"))
                disciplinas = listarDisciplina();
        }
        catch (SQLException | ClassNotFoundException e)
        {
            erro = e.getMessage();
        }
        finally {
            request.setAttribute("saida", saida);
            request.setAttribute("erro", erro);
            request.setAttribute("disciplina", d);
            request.setAttribute("disciplinas", disciplinas);
            request.setAttribute("professores", professores);

            RequestDispatcher rd = request.getRequestDispatcher("disciplina.jsp");
            rd.forward(request, response);
        }
    }

    private void inserirDisciplina(Disciplina d) throws SQLException, ClassNotFoundException
    {
        GenericDAO gDAO = new GenericDAO();
        DisciplinaDAO dDAO = new DisciplinaDAO(gDAO);
        dDAO.insert(d);
    }

    private void altualizarDisciplina(Disciplina d) throws SQLException, ClassNotFoundException
    {
        GenericDAO gDAO = new GenericDAO();
        DisciplinaDAO dDAO = new DisciplinaDAO(gDAO);
        dDAO.update(d);
    }

    private void deletarDisciplina(Disciplina d) throws SQLException, ClassNotFoundException
    {
        GenericDAO gDAO = new GenericDAO();
        DisciplinaDAO dDAO = new DisciplinaDAO(gDAO);
        dDAO.delete(d);
    }

    private Disciplina buscarDisciplina(Disciplina d) throws SQLException, ClassNotFoundException
    {
        GenericDAO gDAO = new GenericDAO();
        DisciplinaDAO dDAO = new DisciplinaDAO(gDAO);
        d = dDAO.find(d);
        return d;
    }

    private List<Disciplina> listarDisciplina() throws SQLException, ClassNotFoundException
    {
        GenericDAO gDAO = new GenericDAO();
        DisciplinaDAO dDAO = new DisciplinaDAO(gDAO);
        List<Disciplina> disciplinas = dDAO.list();
        return disciplinas;
    }

    private Professor buscarProfessor(Professor p) throws SQLException, ClassNotFoundException
    {
        GenericDAO genericDAO = new GenericDAO();
        ProfessorDAO professorDAO = new ProfessorDAO(genericDAO);
        p = professorDAO.find(p);
        return p;
    }

    private List<Professor> listarProfessor() throws SQLException, ClassNotFoundException
    {
        GenericDAO genericDAO = new GenericDAO();
        ProfessorDAO professorDAO = new ProfessorDAO(genericDAO);
        List<Professor> professores = professorDAO.list();
        return professores;
    }
}


