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
        db.close();

        if((result == -1)){
            return false;
        }
        return true;
    }

    public boolean update(Contato contato){
        ContentValues contentValues = new ContentValues();
        long result;

        db = createDB.getWritableDatabase();
        contentValues.put(SqliteCreateDB.FIELD_NOME, contato.getNome());
        contentValues.put(SqliteCreateDB.FIELD_EMAIL, contato.getEmail());
        contentValues.put(SqliteCreateDB.FIELD_TELEFONE, contato.getTelefone());

        String where = createDB.FIELD_ID + "=" + contato.getId();

        result = db.update(SqliteCreateDB.TABELA, contentValues, where, null);
        db.close();

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

    public Cursor findById(int id){
        Cursor cursor;
        String[] fields =  {createDB.FIELD_ID,createDB.FIELD_NOME, createDB.FIELD_EMAIL, createDB.FIELD_TELEFONE};
        String where = createDB.FIELD_ID + "=" + id;
        String order = createDB.FIELD_NOME;
        db = createDB.getReadableDatabase();
        cursor = db.query(createDB.TABELA, fields, where, null, null, null, order, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public boolean delete(int id){
        String where = createDB.FIELD_ID + "=" + id;
        db = createDB.getReadableDatabase();
        int result = db.delete(createDB.TABELA, where,null);
        db.close();
        if(result == 1){
            return true;
        }
        return false;
    }

}
