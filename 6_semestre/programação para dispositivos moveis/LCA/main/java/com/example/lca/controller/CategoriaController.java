package com.example.lca.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.lca.db.Conexao;

import java.util.ArrayList;
import java.util.List;

import com.example.lca.model.SubCategoria;
import com.example.lca.model.Categoria;

public class CategoriaController extends SQLiteOpenHelper {

    // Definições do database
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "BANCO_LCA";

    // Definições das tabelas
    private static final String TB_CATEGORIA = "tb_categoria";
    private static final String ID = "id_categoria";
    private static final String NOME = "tx_nome";

    public CategoriaController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TB_CATEGORIA = "CREATE TABLE " + TB_CATEGORIA + " ("
                + ID + " INTEGER PRIMARY KEY, "
                + NOME + " TEXT)";
        db.execSQL(CREATE_TB_CATEGORIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_CATEGORIA);
        onCreate(db);
    }

    // Função para listar as categorias
    public List<Categoria> buscarCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM " + TB_CATEGORIA;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") int idCategoria = cursor.getInt(cursor.getColumnIndex(ID));
                    @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex(NOME));
                    Categoria categoria = new Categoria(idCategoria, nome);
                    categorias.add(categoria);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        db.close();
        return categorias;
    }

    // Função para inserir uma categoria
    public Categoria inserirCategoria(String nome) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(NOME, nome);

        long idCategoria = db.insert(TB_CATEGORIA, null, valores);
        db.close();

        return new Categoria((int) idCategoria, nome);
    }

    // Função para alterar o nome de uma categoria
    public Categoria alteraCategoria(Categoria categoria, String nome) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(NOME, nome);

        db.update(TB_CATEGORIA, valores, ID + " = ?", new String[]{String.valueOf(categoria.getIdCategoria())});
        db.close();

        categoria.setNome(nome);
        return categoria;
    }

    // Função para deletar uma categoria
    public void deletarCategoria(Categoria categoria) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TB_CATEGORIA, ID + " = ?", new String[]{String.valueOf(categoria.getIdCategoria())});
        db.close();
    }

}
