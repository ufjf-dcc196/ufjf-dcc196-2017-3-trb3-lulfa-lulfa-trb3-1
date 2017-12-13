package br.ufjf.dcc196.sqlexm03;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnCreate;
    Button btnSelect;
    TarefaDbHelper tarefaHelper;
    TextView saida;
    ListView lstTarefas;
    TarefaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstTarefas = (ListView) findViewById(R.id.list_tarefas);
        adapter = new TarefaAdapter(getBaseContext(), null);

        lstTarefas.setAdapter(adapter);
        adapter.atualizar();
        lstTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), "ID do registro: "+l, Toast.LENGTH_SHORT).show();
            }
        });

        tarefaHelper = new TarefaDbHelper(getApplicationContext());
        btnCreate = (Button) findViewById(R.id.button_create);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.inserirAleatorio();
            }
        });
        btnSelect = (Button) findViewById(R.id.button_select);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    adapter.atualizar();
            }
        });
    }
}
