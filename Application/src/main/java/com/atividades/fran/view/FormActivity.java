package com.atividades.fran.view;

import android.content.Intent;
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
import com.atividades.fran.model.Contato;

public class FormActivity extends AppCompatActivity {

    private Button btSalvar;
    private EditText etNome;
    private EditText etTelefone;
    private EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        btSalvar = findViewById(R.id.btSalvar);
        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        etEmail = findViewById(R.id.etEmail);

        btSalvar.setOnClickListener(view -> salvar());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate our menu from the resources by using the menu inflater.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("main::", "item:" + item.getItemId());
        switch (item.getItemId()) {
            case R.id.menu_excluir:

                return true;
            case R.id.menu_salvar:

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void salvar() {

        ContatoController contatoController = new ContatoController(this);

        Contato contato = new Contato();
        contato.setNome(etNome.getText().toString());
        contato.setEmail(etEmail.getText().toString());
        contato.setTelefone(etTelefone.getText().toString());

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

}