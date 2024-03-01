package com.fatec.lab_banco_de_dados.persistence;

import com.fatec.lab_banco_de_dados.model.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO implements ICRUD<Professor>
{
    private GenericDAO gDAO;

    public ProfessorDAO(GenericDAO gDAO)
    {
        this.gDAO = gDAO;
    }

    @Override
    public void insert(Professor professor) throws SQLException, ClassNotFoundException
    {
        Connection connection = gDAO.getConnection();
        String sql = "INSERT INTO professor(codigo, nome, titulacao) VALUES (?,?,?)";
        PreparedStatement p = connection.prepareStatement(sql);
        p.setInt    (1, professor.getCodigo());
        p.setString (2, professor.getNome());
        p.setString (3, professor.getTitulacao());

        p.execute();
        p.close();
        connection.close();
    }

    @Override
    public void update(Professor professor) throws SQLException, ClassNotFoundException
    {
        Connection connection = gDAO.getConnection();
        String sql = "UPDATE professor SET nome=?, titulacao=? WHERE codigo=?";
        PreparedStatement p = connection.prepareStatement(sql);
        p.setString (1, professor.getNome());
        p.setString (2, professor.getTitulacao());
        p.setInt    (3, professor.getCodigo());

        p.execute();
        p.close();
        connection.close();
    }

    @Override
    public void delete(Professor professor) throws SQLException, ClassNotFoundException
    {
        Connection connection = gDAO.getConnection();
        String sql = "DELETE professor WHERE codigo=?";
        PreparedStatement p = connection.prepareStatement(sql);
        p.setInt    (1, professor.getCodigo());

        p.execute();
        p.close();
        connection.close();
    }

    @Override
    public Professor find(Professor professor) throws SQLException, ClassNotFoundException
    {
        Connection connection = gDAO.getConnection();
        String sql = "SELECT codigo, nome, titulacao FROM professor WHERE codigo = ?";
        PreparedStatement p = connection.prepareStatement(sql);
        p.setInt (1, professor.getCodigo());

        ResultSet resultSet = p.executeQuery();
        if (resultSet.next())
        {
            professor.setNome(resultSet.getString("nome"));
            professor.setTitulacao(resultSet.getString("titulacao"));
        }

        resultSet.close();
        p.close();
        connection.close();
        return professor;
    }

    @Override
    public List<Professor> list() throws SQLException, ClassNotFoundException
    {
        Connection connection = gDAO.getConnection();
        String sql = "SELECT codigo, nome, titulacao FROM professor";
        PreparedStatement p = connection.prepareStatement(sql);

        List<Professor> professores = new ArrayList<>();
        ResultSet resultSet = p.executeQuery();

        while (resultSet.next())
        {
            Professor professor = new Professor();
            professor.setCodigo(resultSet.getInt("codigo"));
            professor.setNome(resultSet.getString("nome"));
            professor.setTitulacao(resultSet.getString("titulacao"));
            professores.add(professor);
        }

        resultSet.close();
        p.close();
        connection.close();
        return professores;
    }
}
