package com.example.lca.controller;

import com.example.lca.model.Item;
import com.example.lca.model.Lista;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ListaController extends SQLiteOpenHelper {

    // Definições do database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BANCO_LCA";

    // Definições das tabelas
    private static final String TB_LISTA = "tb_lista";
    private static final String ID = "id_lista";
    private static final String NOME = "tx_nome";
    private static final String DESCRICAO = "tx_descricao";
    private static final String LO_DESCRICAO = "lo_descricao";
    private static final String LO_DESCRICAO_ITEM = "lo_descricao_item";
    private static final String LO_CHECKBOX_ITEM = "lo_checkbox_item";
    private static final String LO_CATEGORIA_ITEM = "lo_categoria_item";
    private static final String LO_SUBCATEGORIA_ITEM = "lo_subcategoria_item";
    private static final String LO_DATE_ITEM = "lo_date_item";
    private static final String LO_TIME_ITEM = "lo_time_item";
    private static final String LO_VALOR_ITEM = "lo_valor_item";

    public ListaController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String CREATE_TB_LISTA = "CREATE TABLE " + TB_LISTA + " ("
                    + ID + " INTEGER PRIMARY KEY, "
                    + NOME + " TEXT, "
                    + DESCRICAO + " TEXT, "
                    + LO_DESCRICAO + " INTEGER, "
                    + LO_DESCRICAO_ITEM + " INTEGER, "
                    + LO_CHECKBOX_ITEM + " INTEGER, "
                    + LO_CATEGORIA_ITEM + " INTEGER, "
                    + LO_SUBCATEGORIA_ITEM + " INTEGER, "
                    + LO_DATE_ITEM + " INTEGER, "
                    + LO_TIME_ITEM + " INTEGER, "
                    + LO_VALOR_ITEM + " INTEGER)";  // Corrigido os tipos para INTEGER

            db.execSQL(CREATE_TB_LISTA);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TB_LISTA);
            onCreate(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar todas as listas
    public List<Lista> listarListas() {
        List<Lista> listas = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            String sql = "SELECT * FROM " + TB_LISTA;

            db = this.getReadableDatabase();
            cursor = db.rawQuery(sql, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int idLista = cursor.getInt(cursor.getColumnIndex(ID));
                    String nome = cursor.getString(cursor.getColumnIndex(NOME));
                    String descricao = cursor.getString(cursor.getColumnIndex(DESCRICAO));
                    boolean loDescricao = cursor.getInt(cursor.getColumnIndex(LO_DESCRICAO)) > 0;
                    boolean loDescricaoItem = cursor.getInt(cursor.getColumnIndex(LO_DESCRICAO_ITEM)) > 0;
                    boolean loCheckboxItem = cursor.getInt(cursor.getColumnIndex(LO_CHECKBOX_ITEM)) > 0;
                    boolean loCategoriaItem = cursor.getInt(cursor.getColumnIndex(LO_CATEGORIA_ITEM)) > 0;
                    boolean loSubcategoriaItem = cursor.getInt(cursor.getColumnIndex(LO_SUBCATEGORIA_ITEM)) > 0;
                    boolean loDateItem = cursor.getInt(cursor.getColumnIndex(LO_DATE_ITEM)) > 0;
                    boolean loTimeItem = cursor.getInt(cursor.getColumnIndex(LO_TIME_ITEM)) > 0;
                    boolean loValorItem = cursor.getInt(cursor.getColumnIndex(LO_VALOR_ITEM)) > 0;

                    Lista lista = new Lista(idLista, nome, descricao, loDescricao, loDescricaoItem, loCheckboxItem, loCategoriaItem, loSubcategoriaItem, loDateItem, loTimeItem, loValorItem);

                    listas.add(lista);
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

        return listas;
    }

    // Criar uma nova lista
    public Lista criarLista(String nome, String descricao, boolean loDescricao, boolean loDescricaoItem, boolean loCheckboxItem, boolean loCategoriaItem, boolean loSubcategoriaItem, boolean loDateItem, boolean loTimeItem, boolean loValorItem) {
        SQLiteDatabase db = null;
        long idLista = -1;

        try {
            db = this.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(NOME, nome);
            valores.put(DESCRICAO, descricao);
            valores.put(LO_DESCRICAO, loDescricao ? 1 : 0);
            valores.put(LO_DESCRICAO_ITEM, loDescricaoItem ? 1 : 0);
            valores.put(LO_CHECKBOX_ITEM, loCheckboxItem ? 1 : 0);
            valores.put(LO_CATEGORIA_ITEM, loCategoriaItem ? 1 : 0);
            valores.put(LO_SUBCATEGORIA_ITEM, loSubcategoriaItem ? 1 : 0);
            valores.put(LO_DATE_ITEM, loDateItem ? 1 : 0);
            valores.put(LO_TIME_ITEM, loTimeItem ? 1 : 0);
            valores.put(LO_VALOR_ITEM, loValorItem ? 1 : 0);

            idLista = db.insert(TB_LISTA, null, valores);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }

        return new Lista((int) idLista, nome, descricao, loDescricao, loDescricaoItem, loCheckboxItem, loCategoriaItem, loSubcategoriaItem, loDateItem, loTimeItem, loValorItem);
    }

    // Alterar os dados de uma lista
    public Lista alterarLista(Lista lista, String nome, String descricao, boolean loDescricao, boolean loDescricaoItem, boolean loCheckboxItem, boolean loCategoriaItem, boolean loSubcategoriaItem, boolean loDateItem, boolean loTimeItem, boolean loValorItem) {
        SQLiteDatabase db = null;

        try {
            db = this.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(NOME, nome);
            valores.put(DESCRICAO, descricao);
            valores.put(LO_DESCRICAO, loDescricao ? 1 : 0);
            valores.put(LO_DESCRICAO_ITEM, loDescricaoItem ? 1 : 0);
            valores.put(LO_CHECKBOX_ITEM, loCheckboxItem ? 1 : 0);
            valores.put(LO_CATEGORIA_ITEM, loCategoriaItem ? 1 : 0);
            valores.put(LO_SUBCATEGORIA_ITEM, loSubcategoriaItem ? 1 : 0);
            valores.put(LO_DATE_ITEM, loDateItem ? 1 : 0);
            valores.put(LO_TIME_ITEM, loTimeItem ? 1 : 0);
            valores.put(LO_VALOR_ITEM, loValorItem ? 1 : 0);

            db.update(TB_LISTA, valores, ID + " = ?", new String[]{String.valueOf(lista.getIdLista())});
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }

        lista.alteraDadosLista(nome, descricao, loDescricao, loDescricaoItem, loCheckboxItem, loCategoriaItem, loSubcategoriaItem, loDateItem, loTimeItem, loValorItem);

        return lista;
    }

    // Excluir uma lista
    public void excluirLista(Lista lista) {
        SQLiteDatabase db = null;

        try {
            db = this.getWritableDatabase();
            db.delete(TB_LISTA, ID + " = ?", new String[]{String.valueOf(lista.getIdLista())});
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}
