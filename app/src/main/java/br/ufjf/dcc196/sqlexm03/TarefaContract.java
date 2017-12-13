package br.ufjf.dcc196.sqlexm03;

import android.provider.BaseColumns;

public final class TarefaContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";
    public static final String SQL_CREATE_TAREFA = "CREATE TABLE " + Tarefa.TABLE_NAME + " (" +
            Tarefa._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            Tarefa.COLUMN_NAME_TITULO + TEXT_TYPE + SEP +
            Tarefa.COLUMN_NAME_AUTOR + TEXT_TYPE + SEP +
            Tarefa.COLUMN_NAME_ANO + INT_TYPE + ")";
    public static final String SQL_DROP_TAREFA = "DROP TABLE IF EXISTS " + Tarefa.TABLE_NAME;

    public TarefaContract() {
    }

    public static final class Tarefa implements BaseColumns {
        public static final String TABLE_NAME = "tarefa";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_AUTOR = "autor";
        public static final String COLUMN_NAME_ANO = "ano";
    }
}
