package com.atividades.fran.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.atividades.fran.dao.SqliteCreateDB;
import com.atividades.fran.model.Contato;

public class ContatoController {

    private SqliteCreateDB createDB;
    private SQLiteDatabase db;

    public ContatoController(Context context) {
        createDB = new SqliteCreateDB(context);
    }

    public boolean create(Contato contato){
        ContentValues contentValues = new ContentValues();
        long result;

        db = createDB.getWritableDatabase();
        contentValues.put(SqliteCreateDB.FIELD_NOME, contato.getNome());
        contentValues.put(SqliteCreateDB.FIELD_EMAIL, contato.getEmail());
        contentValues.put(SqliteCreateDB.FIELD_TELEFONE, contato.getTelefone());

        result = db.insert(SqliteCreateDB.TABELA, null, contentValues);
        if((result == -1)){
            return false;
        }
        return true;
    }

    public Cursor list(){


        Cursor cursor;
        String[] fields = { SqliteCreateDB.FIELD_ID,SqliteCreateDB.FIELD_NOME, SqliteCreateDB.FIELD_TELEFONE };
        db = createDB.getReadableDatabase();
        cursor = db.query(SqliteCreateDB.TABELA, fields, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
