package br.com.suaempresa.catalogodelivro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 Esta classe
 herda características e comportamentos da classe SQLiteOpenHelper,
 além disso, incluir métodos para inserção, pesquisa e listagem dos dados.
 Os dados do aplicativo são armazenados em uma tabela denominada
 catalogo e possui os campos id, titulo, autor e ano.


 A classe SQLite-
 Database permite realizar
 operações com a base de dados
 sem a necessidade de instruções SQL.
 Para realizar a atualização de um registro
 é preciso, apenas, chamar o método update,
 por exemplo, SQLiteDatabase db.update
 informando o nome da tabela, os valores e
 a condição (where)


 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "catalogo";
    private static final int DATABASE_VERSION = 3;

    private final String CREATE_TABLE_CATALOGO = "CREATE TABLE catalogo (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "titulo TEXT, autor TEXT, ano INTEGER);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CATALOGO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS catalogo ");
        onCreate(db);
    }

    public long inserir(ContentValues cv) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.insert("catalogo", null, cv);
        return id;
    }

    public List<ContentValues> pesquisarPorTitulo(String titulo) {
        String sql = "SELECT * FROM catalogo " +
                "WHERE titulo LIKE ? ";

        String where[] = new String[]
                {"%" + titulo + "%"};

        return pesquisar(sql, where);
    }

    public List<ContentValues> pesquisarPorAno(int ano) {
        String sql = "SELECT * FROM catalogo WHERE ano=? ";
        String where[] = new String[]{String.valueOf(ano)};
        return pesquisar(sql, where);
    }

    public List<ContentValues> pesquisarPorTodos() {
        String sql = "SELECT * FROM catalogo ORDER BY id ";

        String where[] = null;
        return pesquisar(sql, where);
    }

    public List<ContentValues> pesquisar(String sql, String where[]) {
        List<ContentValues> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql, where);

        if (c.moveToFirst()) {
            do {
                ContentValues cv = new ContentValues();
                cv.put("id", c.getInt(c.getColumnIndex("id")));
                cv.put("titulo", c.getString(c.getColumnIndex("titulo")));
                cv.put("autor", c.getString(c.getColumnIndex("autor")));
                cv.put("ano", c.getInt(c.getColumnIndex("ano")));
                lista.add(cv);
            } while (c.moveToNext());
        }
        return lista;
    }
}



