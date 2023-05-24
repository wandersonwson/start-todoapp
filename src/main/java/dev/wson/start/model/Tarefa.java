package dev.wson.start.model;

import java.time.LocalDate;

public class Tarefa {
    private int id;
    private int idProjeto;
    private String nome;
    private String descricao;
    private String observacao;
    private boolean status;
    private LocalDate prazo;
    private LocalDate dataCriacao;
    private LocalDate dataAtualizacao;

    public Tarefa(int id, int idProjeto, String nome, String descricao, String observacao, boolean status,
                  LocalDate prazo, LocalDate dataCriacao, LocalDate dataAtualizacao) {
        this.id = id;
        this.idProjeto = idProjeto;
        this.nome = nome;
        this.descricao = descricao;
        this.observacao = observacao;
        this.status = status;
        this.prazo = prazo;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }
    public Tarefa(){}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdProjeto() {
        return idProjeto;
    }
    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String verificarStatus() {
        return (status) ? "Concluída" : "Em andamento";
    }
    public LocalDate getPrazo() {
        return prazo;
    }
    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }
    public void setDataAtualizacao(LocalDate dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
    @Override
    public String toString() {
        return String.format("Tarefa #%d [Nome = %s; Projeto: %d ; Descrição: %s; Observação: %s; Status: %s; Prazo: %s; Criada em: %s; Atualizada em: %s]", id, nome, idProjeto, descricao, observacao, verificarStatus(), prazo.toString(), dataCriacao.toString(), dataAtualizacao.toString());
    }
}