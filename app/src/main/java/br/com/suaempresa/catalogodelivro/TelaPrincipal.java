package br.com.suaempresa.catalogodelivro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class TelaPrincipal extends AppCompatActivity implements View.OnClickListener   {

    private Button btnCadastrar;
    private Button btnPesquisar;
    private RadioGroup rdgPesquisarPor;
    private EditText edtPesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        btnCadastrar = (Button)findViewById(R.id.btnCadastrar);
        btnPesquisar = (Button)findViewById(R.id.btnPesquisar);
        rdgPesquisarPor = ( RadioGroup )findViewById (R.id.rdgPesquisarPor);
        edtPesquisar = (EditText)findViewById(R.id.edtPesquisar);


        btnCadastrar.setOnClickListener(this);
        btnPesquisar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent it = null;
        switch (v.getId()){
            case R.id.btnCadastrar:
                it = new Intent(this,TelaCadastrar.class);
                break;
            case R.id.btnPesquisar:
                it = new Intent(this,TelaPesquisar.class);

                it.putExtra("tipo", rdgPesquisarPor.getCheckedRadioButtonId());
                it.putExtra("chave", edtPesquisar.getText().toString());
                break;
        }

         if (it!= null){
            startActivity(it);
         }
    }
}
