package com.example.lca.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Categoria implements Serializable {

    private int idCategoria;
    private String nome;
    private List<SubCategoria> subCategorias;

    // Criando a categoria
    public Categoria(int idCategoria, String nome) {
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.subCategorias = new ArrayList<>();
    }

    // Montando a categoria
    public Categoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    // Pegando o id da categoria
    public int getIdCategoria() {
        return this.idCategoria;
    }

    // Pegando o nome da categoria
    public String getNome() {
        return this.nome;
    }

    // Alterando o nome da categoria
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Retornando as subcategorias de a categoria
    public List<SubCategoria> getSubCategorias() {
        return this.subCategorias;
    }

    // Adicionando uma subcategoria na categoria
    public void addSubCategoria(SubCategoria subCategoria) {
        this.subCategorias.add(subCategoria);
    }

    // Deletando uma subcategoria da categoria
    public void deleteSubCategoria(int indexSubCategoria) {
        this.subCategorias.remove(indexSubCategoria);
    }

    // Alterando os dados da categoria
    public void alterarDadosCategoria(String nome) {
        if (nome != this.getNome()) {
            this.setNome(nome);
        }
    }
    @Override
    public String toString() {
        return nome;
    }

}
