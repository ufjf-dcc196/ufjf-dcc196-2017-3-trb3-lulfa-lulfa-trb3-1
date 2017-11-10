package br.ufjf.dcc196.sqlexm03;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnCreate;
    Button btnSelect;
    BibliotecaDbHelper biblotecaHelper;
    TextView saida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saida = (TextView) findViewById(R.id.text_saida);
        biblotecaHelper = new BibliotecaDbHelper(getApplicationContext());
        btnCreate = (Button) findViewById(R.id.button_create);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SQLiteDatabase db = biblotecaHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(BibliotecaContract.Livro.COLUMN_NAME_TITULO, "Deu a louca no império");
                    values.put(BibliotecaContract.Livro.COLUMN_NAME_AUTOR, "JRR RR");
                    values.put(BibliotecaContract.Livro.COLUMN_NAME_ANO, 1978);
                    long id = db.insert(BibliotecaContract.Livro.TABLE_NAME, null, values);
                } catch (Exception e) {
                    Log.e("BIBLIO", e.getLocalizedMessage());
                    Log.e("BIBLIO", e.getStackTrace().toString());
                }
            }
        });
        btnSelect = (Button) findViewById(R.id.button_select);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SQLiteDatabase db = biblotecaHelper.getReadableDatabase();
                    String[] visao = {
                            BibliotecaContract.Livro._ID,
                            BibliotecaContract.Livro.COLUMN_NAME_TITULO,
                            BibliotecaContract.Livro.COLUMN_NAME_AUTOR,
                            BibliotecaContract.Livro.COLUMN_NAME_ANO,
                    };
                    String selecao = BibliotecaContract.Livro.COLUMN_NAME_ANO + " > ?";
                    String[] args = {"1950"};
                    String sort = BibliotecaContract.Livro.COLUMN_NAME_AUTOR + " DESC";
                    Cursor c = db.query(BibliotecaContract.Livro.TABLE_NAME, visao, selecao, args, null, null, sort);
                    c.moveToFirst();
                    int idxId = c.getColumnIndexOrThrow(BibliotecaContract.Livro._ID);
                    int idxTitulo = c.getColumnIndexOrThrow(BibliotecaContract.Livro.COLUMN_NAME_TITULO);
                    int idxAutor = c.getColumnIndexOrThrow(BibliotecaContract.Livro.COLUMN_NAME_AUTOR);
                    int idxAno = c.getColumnIndexOrThrow(BibliotecaContract.Livro.COLUMN_NAME_ANO);
                    saida.setText(c.getString(idxId) + " " + c.getString(idxTitulo) + " " + c.getInt(idxAno));
                } catch (Exception e) {
                    Log.e("BIBLIO", e.getLocalizedMessage());
                    Log.e("BIBLIO", e.getStackTrace().toString());
                }
            }
        });
    }
}
