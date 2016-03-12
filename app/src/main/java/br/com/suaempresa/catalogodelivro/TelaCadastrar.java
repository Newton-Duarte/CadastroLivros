package br.com.suaempresa.catalogodelivro;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaCadastrar extends AppCompatActivity implements View.OnClickListener {

    private Button btnSalvar;
    private Button btnVoltar;
    private EditText edtTitulo;
    private EditText edtAutor;
    private EditText edtAno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastrar);

        btnSalvar = (Button)findViewById(R.id.btnSalvar);
        btnVoltar = (Button)findViewById(R.id.btnVoltar);
        edtTitulo = (EditText)findViewById(R.id.edtTitulo);
        edtAutor = (EditText)findViewById(R.id.edtAutor);
        edtAno = (EditText)findViewById(R.id.edtAno);

        btnSalvar.setOnClickListener(this);
        btnVoltar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnVoltar){
            onBackPressed();
        }else if (v.getId() == R.id.btnSalvar)
        {
            ContentValues cv = new ContentValues();
            cv.put("titulo",edtTitulo.getText().toString());
            cv.put("autor", edtAutor.getText().toString());
            cv.put("ano",edtAno.getText().toString());

            DatabaseHelper dh = new DatabaseHelper(this);
            String msg = "";

            if ( dh.inserir(cv) > 0 ){
                msg = "Operação realizada com sucesso!";
                edtTitulo.setText("");
                edtAutor.setText("");
                edtAno.setText("");
                edtTitulo.requestFocus();
            }
            else{
                msg = "Ocorreu um erro durante a operação.";
            }

            Toast.makeText(this,msg, Toast.LENGTH_LONG).show();
            }
    }
}
