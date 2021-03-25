package ricciandroid.com.br.zeusgeniusfp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText NumTelefone;
    EditText EndSite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //public void onClick(View view) {
        //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //        .setAction("Action", null).show();
        // }
        //});

        NumTelefone = (EditText) findViewById(R.id.NumTelefone);
        EndSite = (EditText) findViewById(R.id.EnderecoSite);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_option1:
                Toast.makeText(getApplicationContext(), "Opção 1 Selecionada - Funcionarios", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,Contratados.class));

                break;

            case R.id.item_option2:
                Toast.makeText(getApplicationContext(), "Opção 2 Selecionada - Usuarios",       Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,TabelaUsuarios.class));
                break;

            case R.id.item_option3:
                Toast.makeText(getApplicationContext(), "Opção 3 Selecionada - Tabela Legais", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,TabelaCalculos.class));

                break;

            case R.id.item_option4:
                Toast.makeText(getApplicationContext(), "Opção 4 Selecionada - Cargos", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,TabelaCargos.class));

                break;

            case R.id.ItemSair:
                Toast.makeText(getApplicationContext(), "Fechar Aplicativo", Toast.LENGTH_SHORT).show();
                finish();
                break;

            case R.id.ItemInternet:
                Toast.makeText(getApplicationContext(), "Navegar Site Notícias", Toast.LENGTH_SHORT).show();
                Intent irParaOSite = new Intent(Intent.ACTION_VIEW);
                Uri localSite = Uri.parse(String.valueOf((EndSite.getText())));
                //Uri localSite = Uri.parse("http://uol.com.br");
                irParaOSite.setData(localSite);
                startActivity(irParaOSite);
            break;


            case R.id.ItemTelefone:
                Toast.makeText(getApplicationContext(), "Ligação Telefônica", Toast.LENGTH_SHORT).show();
                String telefone = String.valueOf((NumTelefone.getText()));
                //String telefone = ("30184611");
                Uri uri = Uri.parse("tel:" + telefone);
                Intent intencao = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intencao);
                break;


        }


        //  int id = item.getItemId();
        // if (id == R.id.action_settings) {
        //   return true;
        // }

        return super.onOptionsItemSelected(item);
    }




    public void BotaoMenuContratados(View v){

        startActivity(new Intent(this,Contratados.class));
        return;
    }


    public void BotaoMenuTabelas(View v){

        startActivity(new Intent(this,TabelaCalculos.class));
        return;
    }


    public void BotaoMenuUsuarios(View v){

        startActivity(new Intent(this,TabelaUsuarios.class));
        return;
    }


    public void BotaoMenuCargos(View v){

        startActivity(new Intent(this,TabelaCargos.class));
        return;
    }


}
