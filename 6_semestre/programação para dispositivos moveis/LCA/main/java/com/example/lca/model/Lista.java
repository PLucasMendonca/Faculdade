package com.example.lca.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.lca.model.Item;

public class Lista implements Serializable {

    private int idLista;
    private String nome;
    private String descricao;
    private boolean loDescricao;
    private boolean loDescricaoItem;
    private boolean loCheckboxItem;
    private boolean loCategoriaItem;
    private boolean loSubcategoriaItem;
    private boolean loDateItem;
    private boolean loTimeItem;
    private boolean loValorItem;
    private List<Item> itens;

    // Criando a lista
    public Lista(int idLista, String nome, String descricao, boolean loDescricao, boolean loDescricaoItem,
                 boolean loCheckboxItem, boolean loCategoriaItem, boolean loSubcategoriaItem, boolean loDateItem,
                 boolean loTimeItem, boolean loValorItem) {
        this.idLista = idLista;
        this.nome = nome;
        this.descricao = descricao;
        this.loDescricao = loDescricao;
        this.loDescricaoItem = loDescricaoItem;
        this.loCheckboxItem = loCheckboxItem;
        this.loCategoriaItem = loCategoriaItem;
        this.loSubcategoriaItem = loSubcategoriaItem;
        this.loDateItem = loDateItem;
        this.loTimeItem = loTimeItem;
        this.loValorItem = loValorItem;
        this.itens = new ArrayList<Item>();
    }

    // Montando a lista
    public Lista(int idLista) {
        this.idLista = idLista;
        this.nome = "";
        this.descricao = "";
        this.loDescricao = false;
        this.loDescricaoItem = false;
        this.loCheckboxItem = false;
        this.loCategoriaItem = false;
        this.loSubcategoriaItem = false;
        this.loDateItem = false;
        this.loTimeItem = false;
        this.loValorItem = false;
        this.itens = new ArrayList<Item>();
    }

    // Pagando o id da lista
    public int getIdLista() {
        return this.idLista;
    }

    // Pagando o nome da lista
    public String getNome() {
        return this.nome;
    }

    // Pegando a descrição da lista
    public String getDescricao() {
        return this.descricao;
    }

    // Pegando o boleano da descrição do lista
    public boolean getLoDescricao() {
        return this.loDescricao;
    }

    // Pegando o boleano da descrição do item
    public boolean getLoDescricaoItem() {
        return this.loDescricaoItem;
    }

    // Pegando o boleano do checkbox do item
    public boolean getLoCheckboxItem() {
        return this.loCheckboxItem;
    }

    // Pegando o boleano da categoria do item
    public boolean getLoCategoriaItem() {
        return this.loCategoriaItem;
    }

    // Pegando o boleano da subcategoria do item
    public boolean getLoSubCategoriaItem() {
        return this.loSubcategoriaItem;
    }

    // Pegando o boleano da data do item
    public boolean getLoDateItem() {
        return this.loDateItem;
    }

    // Pegando o boleano da hora do item
    public boolean getLoTimeItem() {
        return this.loTimeItem;
    }

    // Pegando o boleano do valor do item
    public boolean getLoValorItem() {
        return this.loValorItem;
    }

    // Pagando os itens da lista
    public List<Item> getItens() {
        return this.itens;
    }

    // Alterando o nome da lista
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Alterando a descricao da lista
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Alterando o boleano da descrição da lista
    public void setLoDescricao(boolean loDescricao) {
        this.loDescricao = loDescricao;
    }

    // Alterando o boleano da descrição do item
    public void setLoDescricaoItem(boolean loDescricaoItem) {
        this.loDescricaoItem = loDescricaoItem;
    }

    // Alterando o boleano do checkbox do item
    public void setLoCheckboxItem(boolean loCheckboxItem) {
        this.loCheckboxItem = loCheckboxItem;
    }

    // Alterando o boleano da categoria do item
    public void setLoCategoriaItem(boolean loCategoriaItem) {
        this.loCategoriaItem = loCategoriaItem;
    }

    // Alterando o boleano da subcategoria do item
    public void setLoSubCategoriaItem(boolean loSubcategoriaItem) {
        this.loSubcategoriaItem = loSubcategoriaItem;
    }

    // Alterando o boleano da data do item
    public void setLoDateItem(boolean loDateItem) {
        this.loDateItem = loDateItem;
    }

    // Alterando o boleano da hora do item
    public void setLoTimeItem(boolean loTimeItem) {
        this.loTimeItem = loTimeItem;
    }

    // Alterando o boleano do valor do item
    public void setLoValorItem(boolean loValorItem) {
        this.loValorItem = loValorItem;
    }

    // Adicionando um item na lista
    public void addItem(Item item) {
        this.itens.add(item);
    }

    // Removendo um item da lista
    public void removeItem(Item item) {
        this.itens.remove(item);
    }

    // Alterando os dados da lista
    public void alteraDadosLista(String nome, String descricao, boolean loDescricao, boolean loDescricaoItem,
                                 boolean loCheckboxItem, boolean loCategoriaItem, boolean loSubcategoriaItem, boolean loDateItem,
                                 boolean loTimeItem, boolean loValorItem) {
        setNome(nome);
        setDescricao(descricao);
        setLoDescricao(loDescricao);
        setLoDescricaoItem(loDescricaoItem);
        setLoCheckboxItem(loCheckboxItem);
        setLoCategoriaItem(loCategoriaItem);
        setLoSubCategoriaItem(loSubcategoriaItem);
        setLoDateItem(loDateItem);
        setLoTimeItem(loTimeItem);
        setLoValorItem(loValorItem);
    }
}
