package com.atividades.fran.view;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.atividades.fran.R;
import com.atividades.fran.controller.ContatoController;
import com.atividades.fran.dao.SqliteCreateDB;
import com.atividades.fran.model.Contato;

public class FormActivity extends AppCompatActivity {

    private Button btSalvar;
    private EditText etNome;
    private EditText etTelefone;
    private EditText etEmail;
    private String id;
    private ContatoController contatoController;
    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        contatoController = new ContatoController(this);
        contato = new Contato();

        btSalvar = findViewById(R.id.btSalvar);
        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        etEmail = findViewById(R.id.etEmail);

        btSalvar.setOnClickListener(view -> salvar());

        id = this.getIntent().getStringExtra("id");
        if(id!=null){
            Cursor cursor = contatoController.findById(Integer.parseInt(id));
            contato.setId(cursor.getInt(cursor.getColumnIndexOrThrow(SqliteCreateDB.FIELD_ID)));
            contato.setNome(cursor.getString(cursor.getColumnIndexOrThrow(SqliteCreateDB.FIELD_NOME)));
            contato.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(SqliteCreateDB.FIELD_EMAIL)));
            contato.setTelefone(cursor.getString(cursor.getColumnIndexOrThrow(SqliteCreateDB.FIELD_TELEFONE)));
            etEmail.setText(contato.getEmail());
            etNome.setText(contato.getNome());
            etTelefone.setText(contato.getTelefone());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_excluir:
                excluir();
                return true;
            case R.id.menu_salvar:
                salvar();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void salvar() {
        contato.setNome(etNome.getText().toString());
        contato.setEmail(etEmail.getText().toString());
        contato.setTelefone(etTelefone.getText().toString());

        if(id!=null){
            contato.setId(Integer.parseInt(id));
            editar(contato);
        }else{
            incluir(contato);
        }
    }

    private void incluir(Contato contato) {
        boolean result = contatoController.create(contato);

        String mensagem = "Contato inserido com sucesso";
        if (!result) {
            mensagem = "Houve falha ao inserir os dados";
        }

        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();

        if (result) {
            Intent intent = new Intent(FormActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void editar(Contato contato) {
        boolean result = contatoController.update(contato);

        String mensagem = "Contato alterado com sucesso";
        if (!result) {
            mensagem = "Houve falha ao atualizar os dados";
        }

        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();

        if (result) {
            Intent intent = new Intent(FormActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void excluir() {
        if(id!=null){
            contato.setId(Integer.parseInt(id));

            boolean result = contatoController.delete(contato.getId());

            String mensagem = "Contato removido com sucesso";
            if(!result){
                mensagem = "Erro na exclusão";
            }

            Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();

            if (result) {
                Intent intent = new Intent(FormActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }


}