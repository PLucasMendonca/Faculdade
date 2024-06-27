package com.example.lca.model;

import java.io.Serializable;

public class SubCategoria implements Serializable {

    private int idSubcategoria;
    private int idCategoria;
    private String nome;

    // Criando a subcategoria
    public SubCategoria(int idSubcategoria, int idCategoria, String nome) {
        this.idSubcategoria = idSubcategoria;
        this.idCategoria = idCategoria;
        this.nome = nome;
    }

    // Montando a subcategoria
    public SubCategoria(int idSubcategoria, int idCategoria) {
        this.idSubcategoria = idSubcategoria;
        this.idCategoria = idCategoria;
    }

    // Pegando o id da subcategoria
    public int getIdSubcategoria() {
        return this.idSubcategoria;
    }

    // Pegando o id da categoria
    public int getIdCategoria() {
        return this.idCategoria;
    }

    // Pegando o nome da subcategoria
    public String getNome() {
        return this.nome;
    }

    // Alterando o nome da subcategoria
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}

