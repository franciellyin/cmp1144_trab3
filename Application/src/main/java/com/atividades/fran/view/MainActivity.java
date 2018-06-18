package com.atividades.fran.view;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.atividades.fran.R;
import com.atividades.fran.controller.ContatoController;
import com.atividades.fran.dao.SqliteCreateDB;

public class MainActivity extends Activity {

    private Button btAdd;
    private ListView listView;
    private ContatoController contatoController;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btAdd = findViewById(R.id.btAdd);
        listView = findViewById(R.id.listView);

        btAdd.setOnClickListener(view -> add());

        contatoController = new ContatoController(getBaseContext());
        cursor = contatoController.list();

        String[] fields = new String[] {SqliteCreateDB.FIELD_NOME, SqliteCreateDB.FIELD_TELEFONE, SqliteCreateDB.FIELD_ID};
        int[] idViews = new int[] {R.id.tvListNome, R.id.tvListTelefone, R.id.tvListId };

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.layout_listview, cursor, fields, idViews, 0);
        listView.setAdapter(adaptador);

        listView.setOnItemClickListener((parent, view, position, id) -> selecionarContato(position));

    }

    private void selecionarContato(int position) {
        cursor.moveToPosition(position);
        String id = cursor.getString(cursor.getColumnIndexOrThrow(SqliteCreateDB.FIELD_ID));
        Intent intent = new Intent(MainActivity.this, FormActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }

    private void add() {
        Intent intent = new Intent(MainActivity.this, FormActivity.class);
        startActivity(intent);
    }

}
