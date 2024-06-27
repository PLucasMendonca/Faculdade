package com.example.lca.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.example.lca.model.Item;

public class ItemController extends SQLiteOpenHelper {

    // Definições do database
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "BANCO_LCA";

    // Definições das tabelas
    private static final String TB_ITEM = "tb_item";
    private static final String ID = "id_item";
    private static final String ID_LISTA = "id_lista";
    private static final String NOME = "tx_nome";
    private static final String DESCRICAO = "tx_descricao";
    private static final String CHECKBOX = "lo_checkbox";
    private static final String ID_CATEGORIA = "id_categoria";
    private static final String ID_SUBCATEGORIA = "id_subcategoria";
    private static final String DATE = "tx_date";
    private static final String TIME = "tx_time";
    private static final String VALOR = "vr_valor";

    public ItemController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String CREATE_TB_ITEM = "CREATE TABLE " + TB_ITEM + " ("
                    + ID + " INTEGER PRIMARY KEY, "
                    + ID_LISTA + " INT, "
                    + NOME + " TEXT, "
                    + DESCRICAO + " TEXT, "
                    + CHECKBOX + " INTEGER, "
                    + ID_CATEGORIA + " INT, "
                    + ID_SUBCATEGORIA + " INT, "
                    + DATE + " TEXT, "
                    + TIME + " TEXT, "
                    + VALOR + " NUMERIC)";

            db.execSQL(CREATE_TB_ITEM);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TB_ITEM);
            onCreate(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Função para listar os itens de uma lista
    public List<Item> buscarItens(int idLista) {
        List<Item> itens = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            String sql = "SELECT * FROM " + TB_ITEM + " WHERE " + ID_LISTA + " = ?";
            db = this.getReadableDatabase();
            cursor = db.rawQuery(sql, new String[]{String.valueOf(idLista)});

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") int idItem = cursor.getInt(cursor.getColumnIndex(ID));
                    @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex(NOME));
                    @SuppressLint("Range") String descricao = cursor.getString(cursor.getColumnIndex(DESCRICAO));
                    @SuppressLint("Range") boolean checkbox = cursor.getInt(cursor.getColumnIndex(CHECKBOX)) > 0;
                    @SuppressLint("Range") int idCategoria = cursor.getInt(cursor.getColumnIndex(ID_CATEGORIA));
                    @SuppressLint("Range") int idSubCategoria = cursor.getInt(cursor.getColumnIndex(ID_SUBCATEGORIA));
                    @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(DATE));
                    @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex(TIME));
                    @SuppressLint("Range") double valor = cursor.getDouble(cursor.getColumnIndex(VALOR));

                    Item item = new Item(idItem, idLista, nome, descricao, checkbox, idCategoria, idSubCategoria, date,
                            time, valor);
                    itens.add(item);
                } while (cursor.moveToNext());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return itens;
    }

    // Função para criar um item
    public Item inserirItem(int idLista, String nome, String descricao, boolean checkbox, int idCategoria,
                            int idSubCategoria, String date, String time, double valor) {
        SQLiteDatabase db = null;
        long idItem = -1;

        try {
            db = this.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(ID_LISTA, idLista);
            valores.put(NOME, nome);
            valores.put(DESCRICAO, descricao);
            valores.put(CHECKBOX, checkbox ? 1 : 0);
            valores.put(ID_CATEGORIA, idCategoria);
            valores.put(ID_SUBCATEGORIA, idSubCategoria);
            valores.put(DATE, date);
            valores.put(TIME, time);
            valores.put(VALOR, valor);

            idItem = db.insert(TB_ITEM, null, valores);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }

        return new Item((int) idItem, idLista, nome, descricao, checkbox, idCategoria, idSubCategoria, date, time,
                valor);
    }

    // Função para alterar os dados de um item
    public Item alterarItem(Item item, String nome, String descricao, boolean checkbox, int idCategoria,
                            int idSubCategoria, String date, String time, double valor) {
        SQLiteDatabase db = null;

        try {
            db = this.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(NOME, nome);
            valores.put(DESCRICAO, descricao);
            valores.put(CHECKBOX, checkbox ? 1 : 0);
            valores.put(ID_CATEGORIA, idCategoria);
            valores.put(ID_SUBCATEGORIA, idSubCategoria);
            valores.put(DATE, date);
            valores.put(TIME, time);
            valores.put(VALOR, valor);

            db.update(TB_ITEM, valores, ID + " = ?", new String[]{String.valueOf(item.getIdItem())});
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }

        item.alteraDadosItem(nome, descricao, checkbox, idCategoria, idSubCategoria, date, time, valor);
        return item;
    }

    // Função para deletar um item
    public void deletarItem(Item item) {
        SQLiteDatabase db = null;

        try {
            db = this.getWritableDatabase();
            db.delete(TB_ITEM, ID + " = ? AND " + ID_LISTA + " = ?",
                    new String[]{String.valueOf(item.getIdItem()), String.valueOf(item.getIdLista())});
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}
