package br.com.suaempresa.catalogodelivro;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TelaPesquisar extends AppCompatActivity implements View.OnClickListener {

    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pesquisar);

        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(this);

        Intent it = getIntent();
         if (it != null) {
             int tipo = it.getIntExtra("tipo", 0);

             String chave = it.getStringExtra("chave");
             List<ContentValues> lista = new ArrayList<>();

             if (tipo == R.id.rbPesquisarPorTitulo) {
                 lista = new DatabaseHelper(this).pesquisarPorTitulo(chave);

             } else if (tipo == R.id.rbPesquisarPorAno) {
                 lista = new DatabaseHelper(this).pesquisarPorAno(Integer.parseInt(chave.toString()));
             } else if (tipo == R.id.rbPesquisarPorTodos) {
                 lista = new DatabaseHelper(this).pesquisarPorTodos();
             }

             if (lista != null) { //Aqui é a magica
                 if (lista.size() > 0) {
                     TableLayout tb = (TableLayout) findViewById(R.id.tbPesquisa);

                     for (ContentValues cv : lista) {
                         TableRow tr = new TableRow(this);

                         TextView col1 = new TextView(this);
                         col1.setText((String.valueOf(cv.getAsInteger("id"))));
                         tr.addView(col1);

                         TextView col2 = new TextView(this);
                         col2.setText(cv.getAsString("titulo"));
                         tr.addView(col2);


                         TextView col3 = new TextView(this);
                         col3.setText(cv.getAsString("autor"));
                         tr.addView(col3);

                         TextView col4 = new TextView(this);
                         col4.setText((String.valueOf(cv.getAsInteger("ano"))));
                         tr.addView(col4);

                         tb.addView(tr);
                     }
                 }
             }
         }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnVoltar){
            onBackPressed();
        }
    }
}
