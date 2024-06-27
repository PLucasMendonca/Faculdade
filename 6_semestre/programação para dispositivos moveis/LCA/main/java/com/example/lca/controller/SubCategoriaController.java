package com.example.lca.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.example.lca.model.SubCategoria;
import com.example.lca.model.Categoria;

public class SubCategoriaController extends SQLiteOpenHelper {

    // definições do database
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "BANCO_LCA";

    // definições das tabelas
    private static final String TB_SUBCATEGORIA = "tb_subcategoria";
    private static final String ID = "id_subcategoria";
    private static final String ID_CATEGORIA = "id_categoria";
    private static final String NOME = "tx_nome";

    public SubCategoriaController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TB_SUBCATEGORIA = "CREATE TABLE " + TB_SUBCATEGORIA + " ("
                + ID + " INTEGER PRIMARY KEY, "
                + ID_CATEGORIA + " INT, "
                + NOME + " TEXT)";

        db.execSQL(CREATE_TB_SUBCATEGORIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_SUBCATEGORIA);
        onCreate(db);
    }

    // Função para listar as subcategorias
    public List<SubCategoria> buscarSubCategorias(int idCategoria) {
        List<SubCategoria> subCategorias = new ArrayList<>();
        String sql = "SELECT * FROM " + TB_SUBCATEGORIA + " WHERE " + ID_CATEGORIA + " = " + idCategoria;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {

                    @SuppressLint("Range") int idSubCategoria = cursor.getInt(cursor.getColumnIndex(ID));
                    @SuppressLint("Range") String Nome = cursor.getString(cursor.getColumnIndex(NOME));

                    SubCategoria subCategoria = new SubCategoria(idSubCategoria, idCategoria, Nome);

                    subCategorias.add(subCategoria);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        db.close();

        return subCategorias;
    }

    // Função para inserir uma subcategoria
    public SubCategoria inserirSubCategoria(int idCategoria, String nome) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ID_CATEGORIA, idCategoria);
        valores.put(NOME, nome);

        long idSubcategoria = db.insert(TB_SUBCATEGORIA, null, valores);

        db.close();

        return new SubCategoria((int) idSubcategoria, idCategoria, nome);
    }

    // Função para alterar o nome de uma subcategoria
    public SubCategoria alterarNomeSubCategoria(SubCategoria subCategoria, String nome) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(NOME, nome);

        db.update(TB_SUBCATEGORIA, valores, ID + " = ?", new String[]{String.valueOf(subCategoria.getIdSubcategoria())});

        db.close();

        subCategoria.setNome(nome);
        return subCategoria;
    }

    // Função para deletar uma subcategoria
    public void deletarSubCategoria(SubCategoria subCategoria, Categoria categoria) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TB_SUBCATEGORIA, ID + " = ? AND " + ID_CATEGORIA + " = ?",
                new String[]{String.valueOf(subCategoria.getIdSubcategoria()), String.valueOf(categoria.getIdCategoria())});

        db.close();
    }
}
