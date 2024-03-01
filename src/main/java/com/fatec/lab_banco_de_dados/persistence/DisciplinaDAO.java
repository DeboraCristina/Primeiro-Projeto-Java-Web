package com.fatec.lab_banco_de_dados.persistence;

import com.fatec.lab_banco_de_dados.model.Disciplina;
import com.fatec.lab_banco_de_dados.model.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO implements ICRUD<Disciplina>
{
    private GenericDAO gDAO;

    public DisciplinaDAO(GenericDAO gDAO)
    {
        this.gDAO = gDAO;
    }

    @Override
    public void insert(Disciplina disciplina) throws SQLException, ClassNotFoundException
    {
        Connection connection = gDAO.getConnection();
        String sql = "INSERT INTO disciplina(codigo, nome, codigo_professor) VALUES(?, ?, ?)";
        PreparedStatement p = connection.prepareStatement(sql);
        p.setInt    (1, disciplina.getCodigo());
        p.setString (2, disciplina.getNome());
        p.setInt    (3, disciplina.getProfessor().getCodigo());

        p.execute();
        p.close();
        connection.close();
    }

    @Override
    public void update(Disciplina disciplina) throws SQLException, ClassNotFoundException
    {
        Connection connection = gDAO.getConnection();
        String sql = "UPDATE disciplina SET nome=?, codigo_professor=? WHERE codigo=?";
        PreparedStatement p = connection.prepareStatement(sql);
        p.setString (1, disciplina.getNome());
        p.setInt    (2, disciplina.getProfessor().getCodigo());
        p.setInt    (3, disciplina.getCodigo());

        p.execute();
        p.close();
        connection.close();
    }

    @Override
    public void delete(Disciplina disciplina) throws SQLException, ClassNotFoundException
    {
        Connection connection = gDAO.getConnection();
        String sql = "DELETE disciplina WHERE codigo=?";
        PreparedStatement p = connection.prepareStatement(sql);
        p.setInt    (1, disciplina.getCodigo());

        p.execute();
        p.close();
        connection.close();
    }

    @Override
    public Disciplina find(Disciplina disciplina) throws SQLException, ClassNotFoundException
    {
        Connection connection = gDAO.getConnection();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT d.codigo AS codDisc, d.nome AS nomeDisc, ");
        sql.append("p.codigo AS codProf, p.nome nomeProf, p.titulacao AS titulacaoProf ");
        sql.append("FROM disciplina d, professor p ");
        sql.append("WHERE d.codigo_professor = p.codigo ");
        sql.append("AND d.codigo=?");
        PreparedStatement p = connection.prepareStatement(sql.toString());
        p.setInt(1, disciplina.getCodigo());

        ResultSet resultSet = p.executeQuery();
        if (resultSet.next())
        {
            Professor professor = new Professor();
            professor.setCodigo(resultSet.getInt("codProf"));
            professor.setNome(resultSet.getString("nomeProf"));
            professor.setTitulacao(resultSet.getString("titulacaoProf"));

            disciplina.setCodigo(resultSet.getInt("codDisc"));
            disciplina.setNome(resultSet.getString("nomeDisc"));
            disciplina.setProfessor(professor);
        }

        resultSet.close();
        p.close();
        connection.close();
        return disciplina;
    }

    @Override
    public List<Disciplina> list() throws SQLException, ClassNotFoundException
    {
        List<Disciplina> disciplinas = new ArrayList<>();

        Connection connection = gDAO.getConnection();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT d.codigo AS codDisc, d.nome AS nomeDisc, ");
        sql.append("p.codigo AS codProf, p.nome nomeProf, p.titulacao AS titulacaoProf ");
        sql.append("FROM disciplina d, professor p ");
        sql.append("WHERE d.codigo_professor = p.codigo");
        PreparedStatement p = connection.prepareStatement(sql.toString());

        ResultSet resultSet = p.executeQuery();
        while (resultSet.next())
        {
            Professor professor = new Professor();
            professor.setCodigo(resultSet.getInt("codProf"));
            professor.setNome(resultSet.getString("nomeProf"));
            professor.setTitulacao(resultSet.getString("titulacaoProf"));

            Disciplina disciplina = new Disciplina();
            disciplina.setCodigo(resultSet.getInt("codDisc"));
            disciplina.setNome(resultSet.getString("nomeDisc"));
            disciplina.setProfessor(professor);
            disciplinas.add(disciplina);
        }

        resultSet.close();
        p.close();
        connection.close();

        return disciplinas;
    }
}
