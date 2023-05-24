package dev.wson.start.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dev.wson.start.model.Tarefa;
import dev.wson.start.util.ConnectionFactory;

public class TarefaController {
    public void salvar(Tarefa tarefa) throws SQLException {
        String sql = """
                INSERT INTO tarefa (idProjeto, nome, descricao, status, observacao, prazo, dataCriacao, dataAtualizacao)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, tarefa.getIdProjeto());
            statement.setString(2, tarefa.getNome());
            statement.setString(3, tarefa.getDescricao());
            statement.setBoolean(4, tarefa.getStatus());
            statement.setString(5, tarefa.getObservacao());
            statement.setDate(6, Date.valueOf(tarefa.getPrazo()));
            statement.setDate(7, Date.valueOf(tarefa.getDataCriacao()));
            statement.setDate(8, Date.valueOf(tarefa.getDataAtualizacao()));
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao salvar a tarefa");
        }
    }
    public void atualizar(Tarefa tarefa) throws SQLException {
        String sql = """
                UPDATE tarefa
                SET idProjeto = ?, nome = ?, descricao = ?, status = ?, observacao = ?, prazo = ?, dataCriacao = ?, dataAtualizacao = ?
                WHERE id = ?
                """;
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, tarefa.getIdProjeto());
            statement.setString(2, tarefa.getNome());
            statement.setString(3, tarefa.getDescricao());
            statement.setBoolean(4, tarefa.getStatus());
            statement.setString(5, tarefa.getObservacao());
            statement.setDate(6, Date.valueOf(tarefa.getPrazo()));
            statement.setDate(7, Date.valueOf(tarefa.getDataCriacao()));
            statement.setDate(8, Date.valueOf(tarefa.getDataAtualizacao()));
            statement.setInt(9, tarefa.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar a tarefa");
        }
    }
    public void remover(int id) throws SQLException {
        String sql = "DELETE FROM tarefa WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar a tarefa");
        }
    }
    public Tarefa buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM tarefa WHERE id = ?";
        Tarefa tarefa = new Tarefa();
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            tarefa = getTarefa(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar a tarefa #" + id);
        }
        return tarefa;
    }
    public List<Tarefa> buscarTudo(int idProjeto) throws SQLException {
        String sql = "SELECT * FROM tarefa WHERE idProjeto = ?";
        List<Tarefa> tarefas = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idProjeto);
            ResultSet resultSet = statement.executeQuery();
            tarefas = getTarefas(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar as tarefas do projeto #" + idProjeto);
        }
        return tarefas;
    }
    private Tarefa getTarefa(ResultSet resultSet) throws SQLException {
        Tarefa tarefa = new Tarefa();
        resultSet.next();
        tarefa.setId(resultSet.getInt("id"));
        tarefa.setIdProjeto(resultSet.getInt("idProjeto"));
        tarefa.setNome(resultSet.getString("nome"));
        tarefa.setDescricao(resultSet.getString("descricao"));
        tarefa.setStatus(resultSet.getBoolean("status"));
        tarefa.setObservacao(resultSet.getString("observacao"));
        tarefa.setPrazo(resultSet.getDate("prazo").toLocalDate());
        tarefa.setDataCriacao(resultSet.getDate("dataCriacao").toLocalDate());
        tarefa.setDataAtualizacao(resultSet.getDate("dataAtualizacao").toLocalDate());
        return tarefa;
    }
    private List<Tarefa> getTarefas(ResultSet resultSet) throws SQLException {
        List<Tarefa> tarefas = new ArrayList<>();
        while (resultSet.next()) {
            Tarefa tarefa = new Tarefa();
            tarefa.setId(resultSet.getInt("id"));
            tarefa.setIdProjeto(resultSet.getInt("idProjeto"));
            tarefa.setNome(resultSet.getString("nome"));
            tarefa.setDescricao(resultSet.getString("descricao"));
            tarefa.setStatus(resultSet.getBoolean("status"));
            tarefa.setObservacao(resultSet.getString("observacao"));
            tarefa.setPrazo(resultSet.getDate("prazo").toLocalDate());
            tarefa.setDataCriacao(resultSet.getDate("dataCriacao").toLocalDate());
            tarefa.setDataAtualizacao(resultSet.getDate("dataAtualizacao").toLocalDate());
            tarefas.add(tarefa);
        }
        return tarefas;
    }
}