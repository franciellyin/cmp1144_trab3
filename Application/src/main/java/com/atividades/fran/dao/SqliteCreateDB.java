package com.atividades.fran.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteCreateDB extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco_trabalho3.db";
    public static final String TABELA = "contatos";
    public static final String FIELD_ID = "_id";
    public static final String FIELD_NOME = "nome";
    public static final String FIELD_TELEFONE = "telefone";
    public static final String FIELD_EMAIL = "email";
    private static final int VERSAO = 1;

    public SqliteCreateDB(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABELA+" ( " + FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FIELD_NOME + " TEXT,"
                + FIELD_TELEFONE + " TEXT,"
                + FIELD_EMAIL + " TEXT"
                +" )";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
