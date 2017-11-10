package br.ufjf.dcc196.sqlexm03;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class LivroAdapter extends CursorAdapter {
    public LivroAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.livro_layout,viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtTitulo = (TextView) view.findViewById(R.id.text_titulo);
        TextView txtAno = (TextView) view.findViewById(R.id.text_ano);
        TextView txtAutor = (TextView) view.findViewById(R.id.text_autor);
        String titulo = cursor.getString(cursor.getColumnIndexOrThrow(BibliotecaContract.Livro.COLUMN_NAME_TITULO));
        String autor = cursor.getString(cursor.getColumnIndexOrThrow(BibliotecaContract.Livro.COLUMN_NAME_AUTOR));
        int ano = cursor.getInt(cursor.getColumnIndexOrThrow(BibliotecaContract.Livro.COLUMN_NAME_ANO));
        txtTitulo.setText(titulo);
        txtAutor.setText(autor);
        txtAno.setText(String.valueOf(ano));
    }
}
