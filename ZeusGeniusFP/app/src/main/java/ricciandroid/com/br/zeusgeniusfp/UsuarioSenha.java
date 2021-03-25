package ricciandroid.com.br.zeusgeniusfp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UsuarioSenha extends Activity {
    private DatabaseHelper helper;


    private EditText usuario;
    private EditText senha;
    private EditText DataMaquina;
    private EditText UsuarioDataValidade;
    //private EditText UsuarioDataNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usuario = (EditText) findViewById(R.id.usuario);
        senha = (EditText) findViewById(R.id.senha);
        DataMaquina = (EditText) findViewById(R.id.DataMaquina);
        UsuarioDataValidade = (EditText) findViewById(R.id.UsuarioDataValidade);



        this.helper = new DatabaseHelper(this);
        DatabaseHelper mDBHelper = new DatabaseHelper(this);
        //Check exists database
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(!database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if(copyDatabase(this)) {
                Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }



    }




    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }





    public void enterOnClick(View View) throws ParseException {


        SQLiteDatabase db = helper.getWritableDatabase();


        Cursor c = db.rawQuery("SELECT * FROM Usuarios WHERE USU_NomeUsuario like '%" + usuario.getText() + "%' And  USU_SenhaUsuario like '%" + senha.getText() + "%'", null);


        if (c.getCount() == 0) {
            showMessage("Erro!!", "NÃO Existe Usuário ou Senha Banco de Dados");
            return;
        }


        if (c.moveToFirst()) {

            Calendar CalRicci = Calendar.getInstance();
            DataHoraFormatadas data = new DataHoraFormatadas();
            String DataNormalDMA = data.DataDMA.format(CalRicci.getTime());

            //String DataNumerosDMA = data.DataDMANumero.format(CalRicci.getTime());


            //Grava na String abaixo o usuario e senha digitados pelo usuário nos campos EditText
            //depois da SELECT fazer a busca na Tabela Usuarios na linha do cursor acima para verificar
            //se o que foi digitado existe na no banco de dados SQLite
            String usuarioInformado = c.getString(c.getColumnIndex("USU_NomeUsuario"));
            String senhaInformada = c.getString(c.getColumnIndex("USU_SenhaUsuario"));

            //Criado em 01/05/2019 as 20:27
            DataMaquina.setText(DataNormalDMA);

            String UserDataValidade = c.getString(c.getColumnIndex("USU_DataValidade"));
            UsuarioDataValidade.setText(UserDataValidade);



            //Converte a data em DOUBLE e separa a decimal com ponto
            //Exemplo: A data 18/05/2019 depois de Convertida fica 18052019.00
            //double DataAtualMaquina = parseDouble(DataNumerosDMA);
            //String DataAtualMaquinaNumero =  format("%.2f", DataAtualMaquina).replace(",", ".");
            //System.out.println("Data Maquina DOUBLE  " +DataAtualMaquinaNumero  );

            //int DataAtualMaquina = Integer.parseInt((DataNumerosDMA.toString()));
            //System.out.println("Data Maquina DOUBLE  " +DataAtualMaquina  );


//Linha 001 = Pega a Data de Validade do usuário no B.D e grava na String "DataValidadeUsuario" e ELIMINA TODOS OS CARACTERES QUE
//não seja NÚMERO com replaceAll("\\D",""), pois ele tem o poder de fazer isso.
//Linha 002 converte a string da linha 001 para Double
//Linha 003 formata o Double da linha 002 com separador de decimal ponto (.00) e Grava na String
//Linha 004 converte a string da Linha 003 para double
//Linha 004 imprime o valor convertido no terminal Android Monitor
            //String DataValidadeUsuario = UsuarioDataValidadeIF.replaceAll("\\D","");
            //double DataValidadeUsuarioNumero = Double.valueOf(DataValidadeUsuario.toString()).doubleValue();
            //String DataConvertidaPonto =  format("%.2f", DataValidadeUsuarioNumero).replace(",", ".");
            //double DataValidadeDouble = Double.valueOf(DataConvertidaPonto.toString()).doubleValue();
            //System.out.println("Formato Data Convertida Ponto   " +DataConvertidaPonto);
            //System.out.println("Formato Data Convertida Double   " +DataValidadeDouble);

            //String DataValidadeUsuario = UsuarioDataValidadeIF.replaceAll("\\D","");
            //int DataValidadeUsuarioNumero = Integer.parseInt((DataValidadeUsuario.toString()));
            //System.out.println("Formato Data Convertida Ponto   " +DataValidadeUsuarioNumero);

            //Grava na String abaixo o usuario e senha digitados pelo usuário nos campos EditText
            //para ser usada no if abaixo
            String UsuarioDigitado = usuario.getText().toString();
            String SenhaDigitada = senha.getText().toString();



//Exemplo usando before
            //SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
            //Date date1 = sdf.parse("23/02/1995");
            //Date date2 = sdf.parse("31/10/2001");
            //Date date3 = sdf.parse("23/02/1995");
            //System.out.println(date1.before(date2)); //Retorna true (Verdadeiro) 23/02/1995 é inferior a 31/10/2001
            //System.out.println(date2.before(date2)); //Retorna false (Falso) 31/10/2001 NÃO É INFERIOR a 31/10/2001


            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
            Date date1 = sdf.parse(DataNormalDMA); //Data da Máquina (Faz um PARSE de String para Data)
            Date date2 = sdf.parse(UserDataValidade); //Data Validade do Usuário Banco de Dados Faz um PARSE de String para Data)



            //if ("ericci".equals(usuarioInformado) && ("123".equals(senhaInformada))){ ///ESSE FUNCIONA

            //if (UsuarioDigitado.equals(usuarioInformado) && (SenhaDigitada.equals(senhaInformada))){
            if (UsuarioDigitado.equals(usuarioInformado) && (SenhaDigitada.equals(senhaInformada) && (date1.before(date2)))){

                startActivity(new Intent(this,MainActivity.class));
                finish();  //Esse comando desabilita a tela Login quando clicar a tecla voltar no CELULAR
            }
            else{
                String messageErr = getString(R.string.Erro_Autenticacao);
                Toast toast = Toast.makeText(this, messageErr, Toast.LENGTH_SHORT);
                Toast.makeText(this, "ACONTECEU ALGUMA MERDA!! VERIFICAR", Toast.LENGTH_SHORT).show();
                toast.show();
            }
        }
    }



    //BOTÃO VER LISTA DE ID TABELA
//-----------------------------
    public void BotaoCON_Lista(View View) {

        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM Usuarios", null);


        if (c.getCount() == 0) {
            showMessage("Erro!!", "Nada Encontrado");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            buffer.append("ID: " + c.getString(0) + "\n" + "Funcionário: " + c.getString(1) + "\n");
        }
        showMessage("Nome do Usuario", buffer.toString());
    }





    // private void showMessage(String s, String s1) {
    //   Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    //}



    public void showMessage(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}

