package com.atividades.fran.view;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btAdd = findViewById(R.id.btAdd);
        listView = findViewById(R.id.listView);

        btAdd.setOnClickListener(view -> add());

        ContatoController contatoController = new ContatoController(getBaseContext());
        Cursor cursor = contatoController.list();

        String[] fields = new String[] {SqliteCreateDB.FIELD_NOME, SqliteCreateDB.FIELD_TELEFONE};
        int[] idViews = new int[] {R.id.tvListNome, R.id.tvListTelefone};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.layout_listview, cursor, fields, idViews, 0);
        listView.setAdapter(adaptador);

    }

    private void add() {
        Intent intent = new Intent(MainActivity.this, FormActivity.class);
        startActivity(intent);
    }

}
