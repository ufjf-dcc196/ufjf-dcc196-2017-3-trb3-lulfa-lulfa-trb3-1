package br.ufjf.dcc196.sqlexm03;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.Random;


public class TarefaAdapter extends CursorAdapter {
    private TarefaDbHelper tarefaHelper;

    public TarefaAdapter(Context context, Cursor c) {
        super(context, c, 0);
        tarefaHelper = new TarefaDbHelper(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.tarefa_layout,viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtTitulo = (TextView) view.findViewById(R.id.text_titulo);
        TextView txtAno = (TextView) view.findViewById(R.id.text_ano);
        TextView txtAutor = (TextView) view.findViewById(R.id.text_autor);
        String titulo = cursor.getString(cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLUMN_NAME_TITULO));
        String autor = cursor.getString(cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLUMN_NAME_AUTOR));
        int ano = cursor.getInt(cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLUMN_NAME_ANO));
        txtTitulo.setText(titulo);
        txtAutor.setText(autor);
        txtAno.setText(String.valueOf(ano));
    }

    public void atualizar(){
        try {
            SQLiteDatabase db = tarefaHelper.getReadableDatabase();
            String[] visao = {
                    TarefaContract.Tarefa._ID,
                    TarefaContract.Tarefa.COLUMN_NAME_TITULO,
                    TarefaContract.Tarefa.COLUMN_NAME_AUTOR,
                    TarefaContract.Tarefa.COLUMN_NAME_ANO,
            };
            String selecao = TarefaContract.Tarefa.COLUMN_NAME_ANO + " > ?";
            String[] args = {"1950"};
            String sort = TarefaContract.Tarefa.COLUMN_NAME_AUTOR + " DESC";
            Cursor c = db.query(TarefaContract.Tarefa.TABLE_NAME, visao, selecao, args, null, null, sort);
            this.changeCursor(c);

        } catch (Exception e) {
            Log.e("TAREFA", e.getLocalizedMessage());
            Log.e("TAREFA", e.getStackTrace().toString());
        }
    }

    public void inserirAleatorio(){
        try {
            Random rnd = new Random();
            SQLiteDatabase db = tarefaHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(TarefaContract.Tarefa.COLUMN_NAME_TITULO, "Tarefa "+rnd.nextInt(100));
            values.put(TarefaContract.Tarefa.COLUMN_NAME_AUTOR, "Autor "+rnd.nextInt(50));
            values.put(TarefaContract.Tarefa.COLUMN_NAME_ANO, 1978+rnd.nextInt(30));
            long id = db.insert(TarefaContract.Tarefa.TABLE_NAME, null, values);
            atualizar();
        } catch (Exception e) {
            Log.e("TAREFA", e.getLocalizedMessage());
            Log.e("TAREFA", e.getStackTrace().toString());
        }
    }
}
