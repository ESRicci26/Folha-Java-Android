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



public class TabelaCargos extends Activity {
    private DatabaseHelper helper;


    //Primeira Parte
//--------------
    EditText editID_Cargo;
    EditText editCAR_D1sCargoRes;
    EditText editCAR_DescritivoCargo;
    EditText editCAR_IDCBO;
    EditText editCBO_CodOficialCBO;
    Button botaoalt;
    Button botaoadictab;
    Button botaodeltab;

   EditText EditText_IDCargoIncluir;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabelacargos);

//Segunda Parte
//-------------
        editID_Cargo = (EditText) findViewById(R.id.ID_Cargo);
        editCAR_D1sCargoRes = (EditText) findViewById(R.id.CAR_D1sCargoRes);
        editCAR_DescritivoCargo = (EditText) findViewById(R.id.CAR_DescritivoCargo);
        editCAR_IDCBO = (EditText) findViewById(R.id.CAR_IDCBO);
        editCBO_CodOficialCBO = (EditText) findViewById(R.id.CBO_CodOficialCBO);
        botaoalt = (Button) findViewById(R.id.btnalterarTAB);
        botaoadictab = (Button) findViewById(R.id.btnaddTAB);
        botaodeltab = (Button) findViewById(R.id.btndelTAB);
        EditText_IDCargoIncluir = (EditText) findViewById(R.id.EditText_IDCargoIncluir);

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


    //BOTÃO VER LISTA DE CARGOS PELA DESCRIÇÃO DO CARGO
//-------------------------------------------------
    public void btnlistalike(View View) {

        SQLiteDatabase db = helper.getWritableDatabase();
        if (editCAR_D1sCargoRes.getText().toString().trim().length() == 0) {
            showMessage("Erro!!", "Digite o Texto no Campo DESCRIÇÃO RESUMIDA DO CARGO");
            return;
        }

        Cursor c = db.rawQuery("SELECT * FROM Cargos WHERE CAR_D1sCargoRes like '%" + editCAR_D1sCargoRes.getText() + "%'", null);


        if (c.getCount() == 0) {
            showMessage("Erro!!", "Nada Encontrado");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            buffer.append("ID: " + c.getString(0) + "\n" + "Marca: " + c.getString(1) + "\n");
        }
        showMessage("Nome do Cargo", buffer.toString());
    }



    //BOTÃO VER LISTA DE ID TABELA
//-----------------------------
    public void btnlistacontatosTAB(View View) {

        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM Cargos", null);


        if (c.getCount() == 0) {
            showMessage("Erro!!", "Nada Encontrado");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            buffer.append("ID: " + c.getString(0) + "\n" + "Marca: " + c.getString(1) + "\n");
        }
        showMessage("Nome do Aparelho", buffer.toString());
    }


    //Botão "BUSCAR CADASTRO BD"
//Informa dados do Banco de Dados no formulario
    public void btnbuscarcontatoTAB(View View) {

        SQLiteDatabase db = helper.getWritableDatabase();

        if (editID_Cargo.getText().toString().trim().length() == 0) {
            showMessage("Erro!!", "Favor Entrar com o Nº ID");
            return;
        }

        Cursor c = db.rawQuery("SELECT * FROM Cargos, CBOCodBraOcupa WHERE CAR_IDCBO = ID_CBO And ID_Cargo='" + editID_Cargo.getText() + "'", null);


        if (c.moveToFirst()) {
            editID_Cargo.setText(c.getString(0));
            editCAR_D1sCargoRes.setText(c.getString(1));
            editCAR_DescritivoCargo.setText(c.getString(2));
            editCAR_IDCBO.setText(c.getString(3));
            editCBO_CodOficialCBO.setText(c.getString(6));


        }
        else
        {
            showMessage("Erro!", "ID Inválido");
            clearText();
        }
    }



    //Botão ALTERAR Tabela
//--------------------
    public void Botaoalt(View View) {

        SQLiteDatabase db = helper.getWritableDatabase();

        if ((editID_Cargo.getText().toString().trim().length() == 0)) {
            showMessage("Erro!!", "Favor Digitar o ID");
            return;
        }
        Cursor c = db.rawQuery("SELECT * FROM Cargos WHERE ID_Cargo='" + editID_Cargo.getText() + "'", null);
        if (c.moveToFirst()) {
            db.execSQL("UPDATE Cargos SET CAR_D1sCargoRes='" + editCAR_D1sCargoRes.getText()
                    +"', CAR_DescritivoCargo='" + editCAR_DescritivoCargo.getText()
                    +"', CAR_IDCBO='" + editCAR_IDCBO.getText()
                    + "' WHERE ID_Cargo='" + editID_Cargo.getText() + "'");

            showMessage("Ótimo!!", "Dados Alterados");
            clearText();
        } else {
            showMessage("Erro!", "Faça uma Busca Primeiro, use ID");
            clearText();
        }

    }


    //Botão ADICIONAR/INCLUIR
//-----------------------
    public void btnaddTAB(View View) {


        SQLiteDatabase db = helper.getWritableDatabase();


        if (EditText_IDCargoIncluir.getText().toString().isEmpty() || (editCAR_D1sCargoRes.getText().toString().isEmpty() )) {
            showMessage("Erro", "Aperte o Botão Auto Incremento e PREENCHA Todos os Campos");
            return;
        }



        db.execSQL("INSERT INTO Cargos VALUES('" + (EditText_IDCargoIncluir.getText() + "','" +
                editCAR_D1sCargoRes.getText() + "','" +
                editCAR_DescritivoCargo.getText() + "','" +
                editCAR_IDCBO.getText() + "');"));

        showMessage("OK!", "Dados Gravados");





        clearText();
    }


    //Botão DELETAR usando "ID"
    //-------------------------
    public void btndelTAB(View View) {

        SQLiteDatabase db = helper.getWritableDatabase();

        if (editID_Cargo.getText().toString().trim().length() == 0) {
            showMessage("Erro!!", "Entre com o Nº ID");
            return;
        }
        Cursor c = db.rawQuery("SELECT * FROM Cargos WHERE ID_Cargo='" + editID_Cargo.getText() + "'", null);
        if (c.moveToFirst()) {
            db.execSQL("DELETE FROM Cargos WHERE ID_Cargo='" + editID_Cargo.getText() + "'");
            showMessage("Sucesso!!", "Dados Deletados");
        }
        else
        {
            showMessage("Erro!!", "Inválido, Informar ID para DELETAR");
        }
        clearText();
    }



    //Esse BOTÃO busca o último ID da Tabela Cargos e soma +1 e grava no campo EditText
    public void BTNultimoIDCargo(View View) {

        SQLiteDatabase db = helper.getWritableDatabase();

        String query = "SELECT MAX(ID_Cargo) AS ultimoIDtabela FROM Cargos";
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {

                String Valor1string = c.getString(c.getColumnIndex("ultimoIDtabela"));  //Grava o último ID da Tabela Usuários na String Valor1string
                int Valor2Int = Integer.parseInt((String.valueOf(Valor1string).toString())); //Converte a String Valor1string para INTEIRO e grava na variável inteira Valor2Int
                int Total = Valor2Int + 1; //Soma 1 no último ID da tabela e grava na variável inteira Total
                EditText_IDCargoIncluir.setText(String.valueOf(Total)); //Imprime a soma no campo EditText ID Usuário

                System.out.println("Último ID Tabela: " + c.getString(c.getColumnIndex("ultimoIDtabela"))); //Imprime na console o último ID
                System.out.println("Último +1: " + Total); //Imprime na console o último somado com +1

            } while (c.moveToNext());
        }

        c.close();

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

        editID_Cargo.setText("");
        editCAR_D1sCargoRes.setText("");
        editCAR_DescritivoCargo.setText("");
        editCAR_IDCBO.setText("");
        editCBO_CodOficialCBO.setText("");
        EditText_IDCargoIncluir.setText("");

    }


    //BOTÃO LIMPAR CAMPOS
//-------------------
    public void limparcampostab(View View) {

        editID_Cargo.setText("");
        editCAR_D1sCargoRes.setText("");
        editCAR_DescritivoCargo.setText("");
        editCAR_IDCBO.setText("");
        editCBO_CodOficialCBO.setText("");
        EditText_IDCargoIncluir.setText("");

    }






}
