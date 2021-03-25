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

public class TabelaCalculos extends Activity {
    private DatabaseHelper helper;


    EditText editIDTABELA;
    Button BotaoTAB_Alterar;
    Button BotaoTAB_ListaTela;
    Button BotaoTAB_Buscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabelacalculo);

//Segunda Parte
//-------------

        editIDTABELA = (EditText) findViewById(R.id.IDTabela);

        final EditText editTABINSSFAIXA001 = (EditText) findViewById(R.id.INSSFX001);
        editTABINSSFAIXA001.addTextChangedListener(new MascaraMonetaria(editTABINSSFAIXA001));

        final EditText editTABINSSFAIXA002 = (EditText) findViewById(R.id.INSSFX002);
        editTABINSSFAIXA002.addTextChangedListener(new MascaraMonetaria(editTABINSSFAIXA002));

        final EditText editTABINSSFAIXA003 = (EditText) findViewById(R.id.INSSFX003);
        editTABINSSFAIXA003.addTextChangedListener(new MascaraMonetaria(editTABINSSFAIXA003));

        final EditText editTABINSSFAIXA004 = (EditText) findViewById(R.id.INSSFX004);
        editTABINSSFAIXA004.addTextChangedListener(new MascaraMonetaria(editTABINSSFAIXA004));

        final EditText editTABINSSFAIXA005 = (EditText) findViewById(R.id.INSSFX005);
        editTABINSSFAIXA005.addTextChangedListener(new MascaraMonetaria(editTABINSSFAIXA005));

        final EditText editTABINSSFAIXA006 = (EditText) findViewById(R.id.INSSFX006);
        editTABINSSFAIXA006.addTextChangedListener(new MascaraMonetaria(editTABINSSFAIXA006));

        final EditText editTABTETOINSS = (EditText) findViewById(R.id.INSSVALORTETO);
        editTABTETOINSS.addTextChangedListener(new MascaraMonetaria(editTABTETOINSS));

        final EditText editTABINSSPERC001 = (EditText) findViewById(R.id.INSSPERC001);
        editTABINSSPERC001.addTextChangedListener(new MascaraMonetaria(editTABINSSPERC001));

        final EditText editTABINSSPERC002 = (EditText) findViewById(R.id.INSSPERC002);
        editTABINSSPERC002.addTextChangedListener(new MascaraMonetaria(editTABINSSPERC002));

        final EditText editTABINSSPERC003 = (EditText) findViewById(R.id.INSSPERC003);
        editTABINSSPERC003.addTextChangedListener(new MascaraMonetaria(editTABINSSPERC003));

        final EditText editTABIRRFFAIXA001 = (EditText) findViewById(R.id.IRRFFX001);
        editTABIRRFFAIXA001.addTextChangedListener(new MascaraMonetaria(editTABIRRFFAIXA001));

        final EditText editTABIRRFFAIXA002 = (EditText) findViewById(R.id.IRRFFX002);
        editTABIRRFFAIXA002.addTextChangedListener(new MascaraMonetaria(editTABIRRFFAIXA002));

        final EditText editTABIRRFFAIXA003 = (EditText) findViewById(R.id.IRRFFX003);
        editTABIRRFFAIXA003.addTextChangedListener(new MascaraMonetaria(editTABIRRFFAIXA003));

        final EditText editTABIRRFFAIXA004 = (EditText) findViewById(R.id.IRRFFX004);
        editTABIRRFFAIXA004.addTextChangedListener(new MascaraMonetaria(editTABIRRFFAIXA004));

        final EditText editTABIRRFFAIXA005 = (EditText) findViewById(R.id.IRRFFX005);
        editTABIRRFFAIXA005.addTextChangedListener(new MascaraMonetaria(editTABIRRFFAIXA005));

        final EditText editTABIRRFFAIXA006 = (EditText) findViewById(R.id.IRRFFX006);
        editTABIRRFFAIXA006.addTextChangedListener(new MascaraMonetaria(editTABIRRFFAIXA006));

        final EditText editTABIRRFFAIXA007 = (EditText) findViewById(R.id.IRRFFX007);
        editTABIRRFFAIXA007.addTextChangedListener(new MascaraMonetaria(editTABIRRFFAIXA007));

        final EditText editTABIRRFFAIXA008 = (EditText) findViewById(R.id.IRRFFX008);
        editTABIRRFFAIXA008.addTextChangedListener(new MascaraMonetaria(editTABIRRFFAIXA008));

        final EditText editTABIRRFPERCFAIXA001 = (EditText) findViewById(R.id.IRRFPERC001);
        editTABIRRFPERCFAIXA001.addTextChangedListener(new MascaraMonetaria(editTABIRRFPERCFAIXA001));

        final EditText editTABIRRFPERCFAIXA002 = (EditText) findViewById(R.id.IRRFPERC002);
        editTABIRRFPERCFAIXA002.addTextChangedListener(new MascaraMonetaria(editTABIRRFPERCFAIXA002));

        final EditText editTABIRRFPERCFAIXA003 = (EditText) findViewById(R.id.IRRFPERC003);
        editTABIRRFPERCFAIXA003.addTextChangedListener(new MascaraMonetaria(editTABIRRFPERCFAIXA003));

        final EditText editTABIRRFPERCFAIXA004 = (EditText) findViewById(R.id.IRRFPERC004);
        editTABIRRFPERCFAIXA004.addTextChangedListener(new MascaraMonetaria(editTABIRRFPERCFAIXA004));

        final EditText editTABIRRFDEDUFAIXA001 = (EditText) findViewById(R.id.IRRFDEDU001);
        editTABIRRFDEDUFAIXA001.addTextChangedListener(new MascaraMonetaria(editTABIRRFDEDUFAIXA001));

        final EditText editTABIRRFDEDUFAIXA002 = (EditText) findViewById(R.id.IRRFDEDU002);
        editTABIRRFDEDUFAIXA002.addTextChangedListener(new MascaraMonetaria(editTABIRRFDEDUFAIXA002));

        final EditText editTABIRRFDEDUFAIXA003 = (EditText) findViewById(R.id.IRRFDEDU003);
        editTABIRRFDEDUFAIXA003.addTextChangedListener(new MascaraMonetaria(editTABIRRFDEDUFAIXA003));

        final EditText editTABIRRFDEDUFAIXA004 = (EditText) findViewById(R.id.IRRFDEDU004);
        editTABIRRFDEDUFAIXA004.addTextChangedListener(new MascaraMonetaria(editTABIRRFDEDUFAIXA004));

        final EditText editTABIRRFDEDUCAO = (EditText) findViewById(R.id.IRRFVLDEP);
        editTABIRRFDEDUCAO.addTextChangedListener(new MascaraMonetaria(editTABIRRFDEDUCAO));

        final EditText editTABIRRFISENTO = (EditText) findViewById(R.id.IRRFVLISENTO);
        editTABIRRFISENTO.addTextChangedListener(new MascaraMonetaria(editTABIRRFISENTO));

        final EditText editTABSALFAMFAIXA001 = (EditText) findViewById(R.id.SALFMFX001);
        editTABSALFAMFAIXA001.addTextChangedListener(new MascaraMonetaria(editTABSALFAMFAIXA001));

        final EditText editTABSALFAMFAIXA002 = (EditText) findViewById(R.id.SALFMFX002);
        editTABSALFAMFAIXA002.addTextChangedListener(new MascaraMonetaria(editTABSALFAMFAIXA002));

        final EditText editTABSALFAMFAIXA003 = (EditText) findViewById(R.id.SALFMFX003);
        editTABSALFAMFAIXA003.addTextChangedListener(new MascaraMonetaria(editTABSALFAMFAIXA003));

        final EditText editTABSALFAMFAIXA004 = (EditText) findViewById(R.id.SALFMFX004);
        editTABSALFAMFAIXA004.addTextChangedListener(new MascaraMonetaria(editTABSALFAMFAIXA004));

        final EditText editTABSALFAMVAL001 = (EditText) findViewById(R.id.SALFMVAL001);
        editTABSALFAMVAL001.addTextChangedListener(new MascaraMonetaria(editTABSALFAMVAL001));

        final EditText editTABSALFAMVAL002 = (EditText) findViewById(R.id.SALFMVAL002);
        editTABSALFAMVAL002.addTextChangedListener(new MascaraMonetaria(editTABSALFAMVAL002));


        BotaoTAB_Alterar = (Button) findViewById(R.id.BotaoTAB_Alterar);
        BotaoTAB_ListaTela = (Button) findViewById(R.id.BotaoTAB_ListaTela);
        BotaoTAB_Buscar = (Button) findViewById(R.id.BotaoTAB_Buscar);



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




        BotaoTAB_ListaTela.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(View view) {

                SQLiteDatabase db = helper.getWritableDatabase();

                Cursor c = db.rawQuery("SELECT * FROM TabelaLegais", null);


                if (c.getCount() == 0) {
                    showMessage("Erro!!", "Nada Encontrado");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (c.moveToNext()) {
                    buffer.append("ID: " + c.getString(0) + "\n");
                }
                showMessage("Tabela Cálculos", buffer.toString());
            }

        });//FIM DO BOTÃO BotaoTAB_Buscar




//Botão "BUSCAR CADASTRO BD" Tabelas (INSS, IRRF, SALÁRIO FAMÍLIA)
//Informa dados do Banco de Dados no formulario

        BotaoTAB_Buscar.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(View view) {

                SQLiteDatabase db = helper.getWritableDatabase();

                if (editIDTABELA.getText().toString().trim().length() == 0) {
                    showMessage("Erro!!", "Favor Entrar com o Nº ID");
                    return;
                }

                Cursor c = db.rawQuery("SELECT * FROM TabelaLegais WHERE ID_Tabela='" + editIDTABELA.getText() + "'", null);

                if (c.moveToFirst()) {
                    editIDTABELA.setText(c.getString(0));
                    editTABINSSFAIXA001.setText(c.getString(1));
                    editTABINSSFAIXA002.setText(c.getString(2));
                    editTABINSSFAIXA003.setText(c.getString(3));
                    editTABINSSFAIXA004.setText(c.getString(4));
                    editTABINSSFAIXA005.setText(c.getString(5));
                    editTABINSSFAIXA006.setText(c.getString(6));
                    editTABTETOINSS.setText(c.getString(7));
                    editTABINSSPERC001.setText(c.getString(8));
                    editTABINSSPERC002.setText(c.getString(9));
                    editTABINSSPERC003.setText(c.getString(10));
                    editTABIRRFFAIXA001.setText(c.getString(11));
                    editTABIRRFFAIXA002.setText(c.getString(12));
                    editTABIRRFFAIXA003.setText(c.getString(13));
                    editTABIRRFFAIXA004.setText(c.getString(14));
                    editTABIRRFFAIXA005.setText(c.getString(15));
                    editTABIRRFFAIXA006.setText(c.getString(16));
                    editTABIRRFFAIXA007.setText(c.getString(17));
                    editTABIRRFFAIXA008.setText(c.getString(18));
                    editTABIRRFDEDUCAO.setText(c.getString(19));
                    editTABIRRFPERCFAIXA001.setText(c.getString(20));
                    editTABIRRFPERCFAIXA002.setText(c.getString(21));
                    editTABIRRFPERCFAIXA003.setText(c.getString(22));
                    editTABIRRFPERCFAIXA004.setText(c.getString(23));
                    editTABIRRFDEDUFAIXA001.setText(c.getString(24));
                    editTABIRRFDEDUFAIXA002.setText(c.getString(25));
                    editTABIRRFDEDUFAIXA003.setText(c.getString(26));
                    editTABIRRFDEDUFAIXA004.setText(c.getString(27));
                    editTABSALFAMFAIXA001.setText(c.getString(28));
                    editTABSALFAMFAIXA002.setText(c.getString(29));
                    editTABSALFAMFAIXA003.setText(c.getString(30));
                    editTABSALFAMFAIXA004.setText(c.getString(31));
                    editTABSALFAMVAL001.setText(c.getString(32));
                    editTABSALFAMVAL002.setText(c.getString(33));
                    editTABIRRFISENTO.setText(c.getString(34));

                }
                else
                {
                    showMessage("Erro!", "ID Inválido");

                }

            }

        });//FIM DO BOTÃO BotaoTAB_Buscar





//Botão ALTERAR Tabelas (INSS, IRRF, SALÁRIO FAMÍLIA)
//---------------------------------------------------
        BotaoTAB_Alterar.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(View view) {

                SQLiteDatabase db = helper.getWritableDatabase();

                if ((editIDTABELA.getText().toString().trim().length() == 0)) {
                    showMessage("Erro!!", "Favor Digitar o ID");
                    return;
                }

                Cursor c = db.rawQuery("SELECT * FROM TabelaLegais WHERE ID_Tabela='" + editIDTABELA.getText() + "'", null);

                if (c.moveToFirst()) {
                    db.execSQL("UPDATE TabelaLegais SET TAB_FAIXA001INSS_DE='" + editTABINSSFAIXA001.getText()
                            +"', TAB_FAIXA002INSS_ATE='" + editTABINSSFAIXA002.getText()
                            +"', TAB_FAIXA003INSS_DE='" + editTABINSSFAIXA003.getText()
                            +"', TAB_FAIXA004INSS_ATE='" + editTABINSSFAIXA004.getText()
                            +"', TAB_FAIXA005INSS_DE='" + editTABINSSFAIXA005.getText()
                            +"', TAB_FAIXA006INSS_ATE='" + editTABINSSFAIXA006.getText()
                            +"', TAB_TETOINSS='" + editTABTETOINSS.getText()
                            +"', TAB_PERC001INSS='" + editTABINSSPERC001.getText()
                            +"', TAB_PERC002INSS='" + editTABINSSPERC002.getText()
                            +"', TAB_PERC003INSS='" + editTABINSSPERC003.getText()
                            +"', TAB_FAIXA001IRRF_DE='" + editTABIRRFFAIXA001.getText()
                            +"', TAB_FAIXA002IRRF_ATE='" + editTABIRRFFAIXA002.getText()
                            +"', TAB_FAIXA003IRRF_DE='" + editTABIRRFFAIXA003.getText()
                            +"', TAB_FAIXA004IRRF_ATE='" + editTABIRRFFAIXA004.getText()
                            +"', TAB_FAIXA005IRRF_DE='" + editTABIRRFFAIXA005.getText()
                            +"', TAB_FAIXA006IRRF_ATE='" + editTABIRRFFAIXA006.getText()
                            +"', TAB_FAIXA007IRRF_DE='" + editTABIRRFFAIXA007.getText()
                            +"', TAB_FAIXA008IRRF_ATE='" + editTABIRRFFAIXA008.getText()
                            +"', TAB_VALORPORDEPIRRF='" + editTABIRRFDEDUCAO.getText()
                            +"', TAB_PERC001IRRF='" + editTABIRRFPERCFAIXA001.getText()
                            +"', TAB_PERC002IRRF='" + editTABIRRFPERCFAIXA002.getText()
                            +"', TAB_PERC003IRRF='" + editTABIRRFPERCFAIXA003.getText()
                            +"', TAB_PERC004IRRF='" + editTABIRRFPERCFAIXA004.getText()
                            +"', TAB_VLDEDUCAO001IRRF='" + editTABIRRFDEDUFAIXA001.getText()
                            +"', TAB_VLDEDUCAO002IRRF='" + editTABIRRFDEDUFAIXA002.getText()
                            +"', TAB_VLDEDUCAO003IRRF='" + editTABIRRFDEDUFAIXA003.getText()
                            +"', TAB_VLDEDUCAO004IRRF='" + editTABIRRFDEDUFAIXA004.getText()
                            +"', TAB_VALORISENTOIRRF='" + editTABIRRFISENTO.getText()
                            +"', TAB_FAIXA001SALFAM_DE='" + editTABSALFAMFAIXA001.getText()
                            +"', TAB_FAIXA002SALFAM_ATE='" + editTABSALFAMFAIXA002.getText()
                            +"', TAB_FAIXA003SALFAM_DE='" + editTABSALFAMFAIXA003.getText()
                            +"', TAB_FAIXA004SALFAM_ATE='" + editTABSALFAMFAIXA004.getText()
                            +"', TAB_VALORSALFAM001='" + editTABSALFAMVAL001.getText()
                            +"', TAB_VALORSALFAM002='" + editTABSALFAMVAL002.getText()
                            + "' WHERE ID_Tabela='" + editIDTABELA.getText() + "'");

                    showMessage("Ótimo!!", "Dados Alterados");

                } else {
                    showMessage("Erro!", "Faça uma Busca Primeiro, use ID");
                }

            }

        });//FIM DO BOTÃO BotaoTAB_Alterar




    }  //FIM DO protected void onCreate




 //ShowMessage
//-------------
    public void showMessage(String title, String message) {

        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
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



}


