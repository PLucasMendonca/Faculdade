package com.example.lca.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;

public class Conexao extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "banco_lca.db";
    private static final int DATABASE_VERSION = 2;

    // Construtor da classe Conexao
    public Conexao(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        createDatabaseDirectory(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criar tabelas necessárias quando o banco de dados é criado pela primeira vez
        criarTabelaLista(db);
        criarTabelaSubCategoria(db);
        criarTabelaCategoria(db);
        criarTabelaItem(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Atualizar esquema do banco de dados, se necessário
        db.execSQL("DROP TABLE IF EXISTS tb_lista");
        db.execSQL("DROP TABLE IF EXISTS tb_subcategoria");
        onCreate(db);
    }
    public void criarTabelaCategoria(SQLiteDatabase db) {
        String CREATE_TB_CATEGORIA = "CREATE TABLE " + " tb_categoria " + " ("
                + " id_categoria " + " INTEGER PRIMARY KEY, "
                + " tx_nome " + " TEXT)";
        db.execSQL(CREATE_TB_CATEGORIA);
    }

    public void criarTabelaItem(SQLiteDatabase db) {
        try {
            String CREATE_TB_ITEM = "CREATE TABLE " + " tb_item " + " ("
                    + " id_item " + " INTEGER PRIMARY KEY, "
                    + " id_lista " + " INT, "
                    + " tx_nome" + " TEXT, "
                    + "tx_descricao" + " TEXT, "
                    + "lo_checkbox" + " INTEGER, "
                    + " id_categoria " + " INT, "
                    + " id_subcategoria " + " INT, "
                    + " tx_date " + " TEXT, "
                    + " tx_time " + " TEXT, "
                    + " vr_valor " + " NUMERIC)";

            db.execSQL(CREATE_TB_ITEM);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para criar a tabela de Lista
    private void criarTabelaLista(SQLiteDatabase db) {
        String sql = "CREATE TABLE tb_lista (" +
                "id_lista INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tx_nome TEXT, " +
                "tx_descricao TEXT, " +
                "lo_descricao INTEGER, " +
                "lo_descricao_item INTEGER, " +
                "lo_checkbox_item INTEGER, " +
                "lo_categoria_item INTEGER, " +
                "lo_subcategoria_item INTEGER, " +
                "lo_date_item INTEGER, " +
                "lo_time_item INTEGER, " +
                "lo_valor_item INTEGER)";
        db.execSQL(sql);
    }

    // Método para criar a tabela de SubCategoria
    private void criarTabelaSubCategoria(SQLiteDatabase db) {
        String sql = "CREATE TABLE tb_subcategoria (" +
                "id_subcategoria INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_categoria INTEGER, " +
                "tx_nome TEXT)";
        db.execSQL(sql);
    }

    // Método para criar o diretório 'databases' se não existir
    private void createDatabaseDirectory(Context context) {
        File databaseDirectory = context.getDatabasePath(DATABASE_NAME).getParentFile();
        if (!databaseDirectory.exists()) {
            if (databaseDirectory.mkdirs()) {
                Log.d("DATABASE", "Diretório 'databases' criado com sucesso");
            } else {
                Log.e("DATABASE", "Falha ao criar diretório 'databases'");
            }
        } else {
            Log.d("DATABASE", "Diretório 'databases' já existe");
        }
    }

    // Método para obter uma instância do SQLiteDatabase para escrita
    public SQLiteDatabase getDatabase() {
        return this.getWritableDatabase();
    }
}
