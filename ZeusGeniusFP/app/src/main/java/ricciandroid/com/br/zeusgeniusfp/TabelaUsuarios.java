package ricciandroid.com.br.zeusgeniusfp;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


public class TabelaUsuarios extends Activity {
    private DatabaseHelper helper;


    //Primeira Parte
//--------------
    EditText EditText_ID_Usuario;
    EditText EditText_USU_NomeUsuario;
    EditText EditText_USU_SenhaUsuario;
    EditText EditText_USU_NomeCompletoUsuario;
    EditText EditText_USU_DataInclusao;
    EditText EditText_USU_DataValidade;
    EditText EditText_IDUsuarioIncluir;

    Button botaoalt;
    Button botaoadictab;
    Button botaodeltab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabelausuarios);

//Segunda Parte
//-------------
        EditText_ID_Usuario = (EditText) findViewById(R.id.EditText_ID_Usuario);
        EditText_USU_NomeUsuario = (EditText) findViewById(R.id.EditText_USU_NomeUsuario);
        EditText_USU_SenhaUsuario = (EditText) findViewById(R.id.EditText_USU_SenhaUsuario);
        EditText_USU_NomeCompletoUsuario = (EditText) findViewById(R.id.EditText_USU_NomeCompletoUsuario);


        EditText_USU_DataInclusao = (EditText) findViewById(R.id.EditText_USU_DataInclusao);
        final EditText EditText_USU_DataInclusao = (EditText) findViewById(R.id.EditText_USU_DataInclusao);
        EditText_USU_DataInclusao.addTextChangedListener(MaskEditUtil.mask(EditText_USU_DataInclusao, MaskEditUtil.FORMAT_DATE));

        EditText_USU_DataValidade = (EditText) findViewById(R.id.EditText_USU_DataValidade);
        final EditText EditText_USU_DataValidade = (EditText) findViewById(R.id.EditText_USU_DataValidade);
        EditText_USU_DataValidade.addTextChangedListener(MaskEditUtil.mask(EditText_USU_DataValidade, MaskEditUtil.FORMAT_DATE));


        EditText_IDUsuarioIncluir = (EditText) findViewById(R.id.EditText_IDUsuarioIncluir);

        botaoalt = (Button) findViewById(R.id.btnalterarTAB);
        botaoadictab = (Button) findViewById(R.id.btnaddTAB);
        botaodeltab = (Button) findViewById(R.id.btndelTAB);




        this.helper = new DatabaseHelper(this);
        DatabaseHelper mDBHelper = new DatabaseHelper(this);


        //Check exists database
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if (!database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if (copyDatabase(this)) {
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
            byte[] buff = new byte[1024];
            int length;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity", "DB copied");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //BOT??O VER LISTA DE USU??RIOS PELA DESCRI????O DO NOME DO USU??RIO
//-----------------------------------------------------------------
    public void btnlistalike(View View) {

        SQLiteDatabase db = helper.getWritableDatabase();
        if (EditText_USU_NomeUsuario.getText().toString().trim().length() == 0) {
            showMessage("Erro!!", "Digite o Texto no Campo Nome do Usu??rio");
            return;
        }

        Cursor c = db.rawQuery("SELECT * FROM Usuarios WHERE USU_NomeUsuario like '%" + EditText_USU_NomeUsuario.getText() + "%'", null);


        if (c.getCount() == 0) {
            showMessage("Erro!!", "Nada Encontrado");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            buffer.append("ID: " + c.getString(0) + "\n" + "Marca: " + c.getString(1) + "\n");
        }
        showMessage("Nome do Usu??rio", buffer.toString());
    }


    //BOT??O VER LISTA DE ID TABELA
    //-----------------------------
    public void btnlistacontatosTAB(View View) {

        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM Usuarios", null);


        if (c.getCount() == 0) {
            showMessage("Erro!!", "Nada Encontrado");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            buffer.append("ID: " + c.getString(0) + "\n" + "Marca: " + c.getString(1) + "\n");
        }
        showMessage("Nome do Usuario", buffer.toString());
    }


    //Bot??o "BUSCAR CADASTRO BD"
    //Informa dados do Banco de Dados no formulario
    public void btnbuscarcontatoTAB(View View) {

        SQLiteDatabase db = helper.getWritableDatabase();

        if (EditText_ID_Usuario.getText().toString().trim().length() == 0) {
            showMessage("Erro!!", "Favor Entrar com o N?? ID");
            return;
        }

        Cursor c = db.rawQuery("SELECT * FROM Usuarios WHERE _id='" + EditText_ID_Usuario.getText() + "'", null);


        if (c.moveToFirst()) {
            EditText_ID_Usuario.setText(c.getString(0));
            EditText_USU_NomeUsuario.setText(c.getString(1));
            EditText_USU_SenhaUsuario.setText(c.getString(2));
            EditText_USU_NomeCompletoUsuario.setText(c.getString(3));
            EditText_USU_DataInclusao.setText(c.getString(4));
            EditText_USU_DataValidade.setText(c.getString(5));


        } else {
            showMessage("Erro!", "ID Inv??lido");
            clearText();
        }

    }


    //Esse BOT??O busca o ??ltimo ID da Tabela Usuarios e soma +1 e grava no campo EditText
    public void BTNultimoID(View View) {

        SQLiteDatabase db = helper.getWritableDatabase();

        String query = "SELECT MAX(_id) AS ultimoIDtabela FROM Usuarios";
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {

                String Valor1string = c.getString(c.getColumnIndex("ultimoIDtabela"));  //Grava o ??ltimo ID da Tabela Usu??rios na String Valor1string
                int Valor2Int = Integer.parseInt((String.valueOf(Valor1string).toString())); //Converte a String Valor1string para INTEIRO e grava na vari??vel inteira Valor2Int
                int Total = Valor2Int + 1; //Soma 1 no ??ltimo ID da tabela e grava na vari??vel inteira Total
                EditText_IDUsuarioIncluir.setText(String.valueOf(Total)); //Imprime a soma no campo EditText ID Usu??rio

                System.out.println("??ltimo ID Tabela: " + c.getString(c.getColumnIndex("ultimoIDtabela"))); //Imprime na console o ??ltimo ID
                System.out.println("??ltimo +1: " + Total); //Imprime na console o ??ltimo somado com +1

            } while (c.moveToNext());
        }

        c.close();

    }


    //Bot??o ALTERAR Tabela
    //--------------------
    public void Botaoalt(View View) {

        SQLiteDatabase db = helper.getWritableDatabase();

        if ((EditText_ID_Usuario.getText().toString().trim().length() == 0)) {
            showMessage("Erro!!", "Favor Digitar o ID");
            return;
        }
        Cursor c = db.rawQuery("SELECT * FROM Usuarios WHERE _id='" + EditText_ID_Usuario.getText() + "'", null);
        if (c.moveToFirst()) {
            db.execSQL("UPDATE Usuarios SET USU_NomeUsuario='" + EditText_USU_NomeUsuario.getText()
                    + "', USU_SenhaUsuario='" + EditText_USU_SenhaUsuario.getText()
                    + "', USU_NomeCompletoUsuario='" + EditText_USU_NomeCompletoUsuario.getText()
                    + "', USU_DataInclusao='" + EditText_USU_DataInclusao.getText()
                    + "', USU_DataValidade='" + EditText_USU_DataValidade.getText()
                    + "' WHERE _id='" + EditText_ID_Usuario.getText() + "'");

            showMessage("??timo!!", "Dados Alterados");
            clearText();
        } else {
            showMessage("Erro!", "Fa??a uma Busca Primeiro, use ID");
            clearText();
        }

    }


    //Bot??o ADICIONAR/INCLUIR
    //-----------------------
    public void btnaddTAB(View View) {


        SQLiteDatabase db = helper.getWritableDatabase();



        if (EditText_IDUsuarioIncluir.getText().toString().isEmpty() || (EditText_USU_NomeUsuario.getText().toString().isEmpty() )) {
            showMessage("Erro", "Aperte o Bot??o Auto Incremento e PREENCHA o Campo Nome do Usu??rio ");
            return;
        }

        db.execSQL("INSERT INTO Usuarios VALUES('" + (EditText_IDUsuarioIncluir.getText() + "','" +
                EditText_USU_NomeUsuario.getText() + "','" +
                EditText_USU_SenhaUsuario.getText() + "','" +
                EditText_USU_NomeCompletoUsuario.getText() + "','" +
                EditText_USU_DataInclusao.getText() + "','" +
                EditText_USU_DataValidade.getText() + "');"));

        showMessage("OK!", "Dados Gravados");

        EditText_ID_Usuario.setText("");
        EditText_IDUsuarioIncluir.setText("");
        EditText_USU_NomeUsuario.setText("");
        EditText_USU_SenhaUsuario.setText("");
        EditText_USU_NomeCompletoUsuario.setText("");
        EditText_USU_DataInclusao.setText("");
        EditText_USU_DataValidade.setText("");



        clearText();


    }


    //Bot??o DELETAR usando "ID"
    //-------------------------
    public void btndelTAB(View View) {

        SQLiteDatabase db = helper.getWritableDatabase();

        //if (EditText_ID_Usuario.getText().toString().trim().length() == 0) {
        //return;
        //}

        //if (EditText_ID_Usuario.getText().toString().isEmpty()) {
        if (EditText_ID_Usuario.getText().toString() == null || (EditText_ID_Usuario.getText().toString().trim().isEmpty() )) {
            showMessage("Erro!!", "Voc?? ?? um Animal!! Informe um ID V??lido para Deletar");
            return;
        }


        Cursor c = db.rawQuery("SELECT * FROM Usuarios WHERE _id ='" + EditText_ID_Usuario.getText() + "'", null);
        if (c.moveToFirst()) {
            db.execSQL("DELETE FROM Usuarios WHERE _id='" + EditText_ID_Usuario.getText() + "'");
            showMessage("Sucesso!!", "Usu??rio Deletado!!!");
        }
        else
        {
            showMessage("Erro!!", "Informar um ID V??lido para DELETAR");
        }
        clearText();
    }


    //ShowMessage
//-----------
    public void showMessage(String title, String message) {

        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    //ClearText
//---------
    public void clearText() {

        EditText_ID_Usuario.setText("");
        EditText_USU_NomeUsuario.setText("");
        EditText_USU_SenhaUsuario.setText("");
        EditText_USU_NomeCompletoUsuario.setText("");
        EditText_USU_DataInclusao.setText("");
        EditText_USU_DataValidade.setText("");
        EditText_IDUsuarioIncluir.setText("");

    }


    //BOT??O LIMPAR CAMPOS
//-------------------
    public void limparcampostab(View View) {

        EditText_ID_Usuario.setText("");
        EditText_USU_NomeUsuario.setText("");
        EditText_USU_SenhaUsuario.setText("");
        EditText_USU_NomeCompletoUsuario.setText("");
        EditText_USU_DataInclusao.setText("");
        EditText_USU_DataValidade.setText("");
        EditText_IDUsuarioIncluir.setText("");

    }






}
