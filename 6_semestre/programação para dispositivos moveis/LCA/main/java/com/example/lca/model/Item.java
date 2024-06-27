package com.example.lca.model;

import com.example.lca.model.Categoria;
import com.example.lca.model.SubCategoria;

import java.io.Serializable;

public class Item implements Serializable {
    private int idItem;
    private int idLista;
    private String nome;
    private String descricao;
    private boolean checkbox;
    private Categoria categoria;
    private SubCategoria subCategoria;;
    private String date;
    private String time;
    private double valor;

    //Criando o item
    public Item(int idItem, int idLista, String nome, String descricao, boolean checkbox, int idCategoria, int idSubCategoria, String date, String time, double valor) {
        this.idItem = idItem;
        this.idLista = idLista;
        this.nome = nome;
        this.descricao = descricao;
        this.checkbox = checkbox;
        this.categoria = null;
        this.subCategoria = null;
        this.date = date;
        this.time = time;
        this.valor = valor;

        if(idCategoria > 0){
            this.categoria = new Categoria(idCategoria);

            if(idSubCategoria > 0){
                this.subCategoria = new SubCategoria(idSubCategoria, idCategoria);
            }
        }
    }

    //Montando o item
    public Item(int idItem, int idLista){
        this.idItem = idItem;
        this.idLista = idLista;
    }

    //Pegando o id do item
    public int getIdItem() {
        return idItem;
    }
    //Pegando o id da lista
    public int getIdLista() {
        return idLista;
    }

    //Pegando o nome do item
    public String getNome() {
        return nome;
    }

    //Pegando a descrição do item
    public String getDescricao() {
        return descricao;
    }

    //Pegando o checkbox do item
    public boolean getCheckBox() {
        return this.checkbox;
    }

    //Pegando a categoria do item
    public Categoria getCategoria() {
        return this.categoria;
    }

    //Pegando a sub categoria do item
    public SubCategoria getSubCategoria() {
        return this.subCategoria;
    }

    //Pegando a data do item
    public String getDate() {
        return this.date;
    }

    //Pegando o time do item
    public String getTime() {
        return this.time;
    }

    //Pegando o valor do item
    public Double getValor() {
        return this.valor;
    }

    //Alterando o nome do item
    public void setNome(String nome) {
        this.nome = nome;
    }

    //Alterando a descrição do item
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    //Alterando o checkbox do item
    public void setCheckBox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    //Alterando a categoria do item
    public void setCategoria(int idCategoria) {
        this.categoria= new Categoria(idCategoria);
    }

    //Alterando a subcategoria do item
    public void setSubCategoria(int idSubCategoria, int idCategoria) {
        this.subCategoria = new SubCategoria(idSubCategoria, idCategoria);
    }

    //Alterando a data do item
    public void setDate(String date) {
        this.date = date;
    }

    //Alterando o time do item
    public void setTime(String time) {
        this.time = time;
    }

    //Alterando o valor do item
    public void setValor(double valor) {
        this.valor = valor;
    }

    //Alterando os dados do item
    public void alteraDadosItem(String nome, String descricao, boolean checkbox, int idCategoria, int idSubCategoria, String date, String time, double valor){
        if(nome != this.nome){
            setNome(nome);
        }

        if(descricao != this.descricao){
            setDescricao(descricao);
        }

        if(checkbox != this.checkbox){
            setCheckBox(checkbox);
        }

        if(idCategoria != this.categoria.getIdCategoria()){
            setCategoria(idCategoria);
        }

        if(this.categoria != null && idSubCategoria != this.subCategoria.getIdSubcategoria()){
            setSubCategoria(idSubCategoria, idCategoria);
        }

        if(date != this.date){
            setDate(date);
        }

        if(time != this.time){
            setTime(time);
        }

        if(valor != this.valor){
            setValor(valor);
        }
    }

}

