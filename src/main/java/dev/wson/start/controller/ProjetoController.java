package dev.wson.start.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dev.wson.start.model.Projeto;
import dev.wson.start.util.ConnectionFactory;

public class ProjetoController {
    public void salvar(Projeto projeto) throws SQLException {
        String sql = """
                INSERT INTO projeto(nome, descricao, dataCriacao, dataAtualizacao)
                VALUES(?, ?, ?, ?)
                """;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, projeto.getNome());
            statement.setString(2, projeto.getDescricao());
            statement.setDate(3, Date.valueOf(projeto.getDataCriacao()));
            statement.setDate(4, Date.valueOf(projeto.getDataAtualizacao()));
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao salvar o projeto");
        }
    }
    public void atualizar(Projeto projeto) throws SQLException {
        String sql = """
                UPDATE projeto
                SET nome = ?, descricao = ?, dataCriacao = ?, dataAtualizacao = ?
                WHERE id = ?
                """;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, projeto.getNome());
            statement.setString(2, projeto.getDescricao());
            statement.setDate(3, Date.valueOf(projeto.getDataCriacao()));
            statement.setDate(4, Date.valueOf(projeto.getDataAtualizacao()));
            statement.setInt(5, projeto.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar o projeto #" + projeto.getId());
        }
    }
    public void remover(int id) throws SQLException {
        String sql = "DELETE FROM projeto WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao remover o projeto #" + id);
        }
    }
    public Projeto buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM projeto WHERE id = ?";
        Projeto projeto = new Projeto();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            projeto = getProjeto(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar o projeto #" + id);
        }
        return projeto;
    }
    public List<Projeto> buscarTudo() throws SQLException {
        String sql = "SELECT * FROM projeto";
        List<Projeto> projetos = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            projetos = getProjetos(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar projetos");
        }
        return projetos;
    }
    private Projeto getProjeto(ResultSet resultSet) throws SQLException {
        Projeto projeto = new Projeto();
        resultSet.next();
        projeto.setId(resultSet.getInt("id"));
        projeto.setNome(resultSet.getString("nome"));
        projeto.setDescricao(resultSet.getString("descricao"));
        projeto.setDataCriacao(resultSet.getDate("dataCriacao").toLocalDate());
        projeto.setDataAtualizacao(resultSet.getDate("dataAtualizacao").toLocalDate());
        return projeto;
    }
    private List<Projeto> getProjetos(ResultSet resultSet) throws SQLException {
        List<Projeto> projetos = new ArrayList<>();
        while(resultSet.next()) {
            Projeto projeto = new Projeto();
            projeto.setId(resultSet.getInt("id"));
            projeto.setNome(resultSet.getString("nome"));
            projeto.setDescricao(resultSet.getString("descricao"));
            projeto.setDataCriacao(resultSet.getDate("dataCriacao").toLocalDate());
            projeto.setDataAtualizacao(resultSet.getDate("dataAtualizacao").toLocalDate());
            projetos.add(projeto);
        }
        return projetos;
    }
}