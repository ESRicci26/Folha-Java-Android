package ricciandroid.com.br.zeusgeniusfp;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;

import static java.lang.Double.parseDouble;
import static java.lang.String.format;


public class Contratados extends Activity {


    private DatabaseHelper helper;




    //Primeira Parte
//--------------
    EditText editText_ID_Contratado;
    EditText editText_CON_DssNome;
    EditText editText_CON_RGNumero;
    EditText editText_CON_RGOrgaoEmissor;
    EditText editText_CON_EnderecoResidencial;
    EditText editText_CON_Email;
    EditText editText_CON_IDCargo;
    EditText editText_CON_IDCCusto;
    EditText editText_CON_IDEstadosCivis;
    EditText editText_CON_IDGrauInstrucao;
    EditText editText_CON_IDSexo;
    EditText editText_CON_IDHorario;
    EditText editText_CON_IDEncargo;
    EditText editTextIDContratadoIncluir;

    Button BotaoCON_Alterar;
    Button BotaoCON_Adicionar;
    Button btnBotaoCON_Deletar;
    Button BotaoCON_Buscar;
    Button BotaoCON_LimparCampos;
    Button BotaoCalculoAutomatico;

    Button BotaoCONListaLike;
    Button BotaoSalvarTexto;
    Button BotaoCON_Lista;
    Button btnCONultimoID;
    Button BotaoCON_Deletar;
    Button BotaoSalvarDownloads;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contratados);

//Segunda Parte
//-------------


        editText_ID_Contratado = (EditText) findViewById(R.id.XML_ID_Contratado);

        //Classe MaskEditUtil Formata Documentos com MÁSCARA
        final EditText editText_CON_CPF = (EditText) findViewById(R.id.XML_CON_CPF);
        editText_CON_CPF.addTextChangedListener(MaskEditUtil.mask(editText_CON_CPF, MaskEditUtil.FORMAT_CPF));

        editText_CON_DssNome = (EditText) findViewById(R.id.XML_CON_DssNome);

        //Classe MaskEditUtil Formata Documentos com MÁSCARA
        final EditText editText_CON_DtdAdmissao = (EditText) findViewById(R.id.XML_CON_DtdAdmissao);
        editText_CON_DtdAdmissao.addTextChangedListener(MaskEditUtil.mask(editText_CON_DtdAdmissao, MaskEditUtil.FORMAT_DATE));

        editText_CON_RGNumero = (EditText) findViewById(R.id.XML_CON_RGNumero);

        //Classe MaskEditUtil Formata Documentos com MÁSCARA
        final EditText editText_CON_RGDtdEmissao = (EditText) findViewById(R.id.XML_CON_RGDtdEmissao);
        editText_CON_RGDtdEmissao.addTextChangedListener(MaskEditUtil.mask(editText_CON_RGDtdEmissao, MaskEditUtil.FORMAT_DATE));


        editText_CON_RGOrgaoEmissor = (EditText) findViewById(R.id.XML_CON_RGOrgaoEmissor);

        //Classe MaskEditUtil Formata Documentos com MÁSCARA
        final EditText editText_CON_PISNumero = (EditText) findViewById(R.id.XML_CON_PISNumero);
        editText_CON_PISNumero.addTextChangedListener(MaskEditUtil.mask(editText_CON_PISNumero, MaskEditUtil.FORMAT_PIS));


        editText_CON_EnderecoResidencial = (EditText) findViewById(R.id.XML_CON_EnderecoResidencial);
        editText_CON_Email = (EditText) findViewById(R.id.XML_CON_Email);
        editText_CON_IDCargo = (EditText) findViewById(R.id.XML_CON_IDCargo);
        editText_CON_IDCCusto = (EditText) findViewById(R.id.XML_CON_IDCCusto);
        editText_CON_IDEstadosCivis = (EditText) findViewById(R.id.XML_CON_IDEstadosCivis);
        editText_CON_IDGrauInstrucao = (EditText) findViewById(R.id.XML_CON_IDGrauInstrucao);
        editText_CON_IDSexo = (EditText) findViewById(R.id.XML_CON_IDSexo);
        editText_CON_IDHorario = (EditText) findViewById(R.id.XML_CON_IDHorario);

        final EditText editText_CON_QtdDepIRRF = (EditText) findViewById(R.id.XML_CON_QtdDepIRRF);
        editText_CON_QtdDepIRRF.addTextChangedListener(new MascaraMonetaria(editText_CON_QtdDepIRRF));

        final EditText editText_CON_QtdDepSF = (EditText) findViewById(R.id.XML_CON_QtdDepSF);
        editText_CON_QtdDepSF.addTextChangedListener(new MascaraMonetaria(editText_CON_QtdDepSF));

        final EditText editText_CON_HorasBaseMes = (EditText) findViewById(R.id.XML_CON_HorasBaseMes);
        editText_CON_HorasBaseMes.addTextChangedListener(new MascaraMonetaria(editText_CON_HorasBaseMes));

        final EditText editText_CON_SalarioContratual = (EditText) findViewById(R.id.XML_CON_SalarioContratual);
        editText_CON_SalarioContratual.addTextChangedListener(new MascaraMonetaria(editText_CON_SalarioContratual));

        editText_CON_IDEncargo = (EditText) findViewById(R.id.XML_CON_IDEncargo);

        final EditText editText_CON_SalarioHora = (EditText) findViewById(R.id.XML_CON_SalarioHora);
        editText_CON_SalarioHora.addTextChangedListener(new MascaraMonetaria(editText_CON_SalarioHora));

        final EditText editText_CON_SalarioDia = (EditText) findViewById(R.id.XML_CON_SalarioDia);
        editText_CON_SalarioDia.addTextChangedListener(new MascaraMonetaria(editText_CON_SalarioDia));

        final EditText editText_CON_DiasTrabalhados = (EditText) findViewById(R.id.XML_CON_DiasTrabalhados);
        editText_CON_DiasTrabalhados.addTextChangedListener(new MascaraMonetaria(editText_CON_DiasTrabalhados));

        final EditText editText_CON_HorasTrabalhadas = (EditText) findViewById(R.id.XML_CON_HorasTrabalhadas);
        editText_CON_HorasTrabalhadas.addTextChangedListener(new MascaraMonetaria(editText_CON_HorasTrabalhadas));

        final EditText editText_CON_VERSalMesHora = (EditText) findViewById(R.id.XML_CON_VERSalMesHora);
        editText_CON_VERSalMesHora.addTextChangedListener(new MascaraMonetaria(editText_CON_VERSalMesHora));

        final EditText editText_CON_VERVL01TodasINC = (EditText) findViewById(R.id.XML_CON_VERVL01TodasINC);
        editText_CON_VERVL01TodasINC.addTextChangedListener(new MascaraMonetaria(editText_CON_VERVL01TodasINC));

        final EditText editText_CON_VERVL02TodasINC = (EditText) findViewById(R.id.XML_CON_VERVL02TodasINC);
        editText_CON_VERVL02TodasINC.addTextChangedListener(new MascaraMonetaria(editText_CON_VERVL02TodasINC));

        final EditText editText_CON_VERVL03SemINC = (EditText) findViewById(R.id.XML_CON_VERVL03SemINC);
        editText_CON_VERVL03SemINC.addTextChangedListener(new MascaraMonetaria(editText_CON_VERVL03SemINC));

        final EditText editText_CON_VERVL04SemINC = (EditText) findViewById(R.id.XML_CON_VERVL04SemINC);
        editText_CON_VERVL04SemINC.addTextChangedListener(new MascaraMonetaria(editText_CON_VERVL04SemINC));

        final EditText editText_CON_PERCHoraExtra1 = (EditText) findViewById(R.id.XML_CON_PERCHoraExtra1);
        editText_CON_PERCHoraExtra1.addTextChangedListener(new MascaraMonetaria(editText_CON_PERCHoraExtra1));

        final EditText editText_CON_QTDHoraExtra1 = (EditText) findViewById(R.id.XML_CON_QTDHoraExtra1);
        editText_CON_QTDHoraExtra1.addTextChangedListener(new MascaraMonetaria(editText_CON_QTDHoraExtra1));

        final EditText editText_CON_VERHoraExtra1 = (EditText) findViewById(R.id.XML_CON_VERHoraExtra1);
        editText_CON_VERHoraExtra1.addTextChangedListener(new MascaraMonetaria(editText_CON_VERHoraExtra1));

        final EditText editText_CON_PERCAdicNoturno1 = (EditText) findViewById(R.id.XML_CON_PERCAdicNoturno1);
        editText_CON_PERCAdicNoturno1.addTextChangedListener(new MascaraMonetaria(editText_CON_PERCAdicNoturno1));

        final EditText editText_CON_QTDHoraAdicNotu1 = (EditText) findViewById(R.id.XML_CON_QTDHoraAdicNotu1);
        editText_CON_QTDHoraAdicNotu1.addTextChangedListener(new MascaraMonetaria(editText_CON_QTDHoraAdicNotu1));

        final EditText editText_CON_VERAdicNoturno1 = (EditText) findViewById(R.id.XML_CON_VERAdicNoturno1);
        editText_CON_VERAdicNoturno1.addTextChangedListener(new MascaraMonetaria(editText_CON_VERAdicNoturno1));

        final EditText editText_CON_QTDDiasDSRUteis = (EditText) findViewById(R.id.XML_CON_QTDDiasDSRUteis);
        editText_CON_QTDDiasDSRUteis.addTextChangedListener(new MascaraMonetaria(editText_CON_QTDDiasDSRUteis));

        final EditText editText_CON_QTDDiasDSRInuteis = (EditText) findViewById(R.id.XML_CON_QTDDiasDSRInuteis);
        editText_CON_QTDDiasDSRInuteis.addTextChangedListener(new MascaraMonetaria(editText_CON_QTDDiasDSRInuteis));

        final EditText editText_CON_VERValorDSR = (EditText) findViewById(R.id.XML_CON_VERValorDSR);
        editText_CON_VERValorDSR.addTextChangedListener(new MascaraMonetaria(editText_CON_VERValorDSR));

        final EditText editText_CON_QtdDiasFalta = (EditText) findViewById(R.id.XML_CON_QtdDiasFalta);
        editText_CON_QtdDiasFalta.addTextChangedListener(new MascaraMonetaria(editText_CON_QtdDiasFalta));

        final EditText editText_CON_VERValorFalta = (EditText) findViewById(R.id.XML_CON_VERValorFalta);
        editText_CON_VERValorFalta.addTextChangedListener(new MascaraMonetaria(editText_CON_VERValorFalta));

        final EditText editText_CON_QtdHorasAtraso = (EditText) findViewById(R.id.XML_CON_QtdHorasAtraso);
        editText_CON_QtdHorasAtraso.addTextChangedListener(new MascaraMonetaria(editText_CON_QtdHorasAtraso));

        final EditText editText_CON_VERValorAtraso = (EditText) findViewById(R.id.XML_CON_VERValorAtraso);
        editText_CON_VERValorAtraso.addTextChangedListener(new MascaraMonetaria(editText_CON_VERValorAtraso));

        final EditText editText_CON_PERCPGBL = (EditText) findViewById(R.id.XML_CON_PERCPGBL);
        editText_CON_PERCPGBL.addTextChangedListener(new MascaraMonetaria(editText_CON_PERCPGBL));

        final EditText editText_CON_VERPGBL = (EditText) findViewById(R.id.XML_CON_VERPGBL);
        editText_CON_VERPGBL.addTextChangedListener(new MascaraMonetaria(editText_CON_VERPGBL));

        final EditText editText_CON_PERCVGBL = (EditText) findViewById(R.id.XML_CON_PERCVGBL);
        editText_CON_PERCVGBL.addTextChangedListener(new MascaraMonetaria(editText_CON_PERCVGBL));

        final EditText editText_CON_VERVGBL = (EditText) findViewById(R.id.XML_CON_VERVGBL);
        editText_CON_VERVGBL.addTextChangedListener(new MascaraMonetaria(editText_CON_VERVGBL));

        final EditText editText_CON_VERPensaoDesconto = (EditText) findViewById(R.id.XML_CON_VERPensaoDesconto);
        editText_CON_VERPensaoDesconto.addTextChangedListener(new MascaraMonetaria(editText_CON_VERPensaoDesconto));

        final EditText editText_CON_VERPensaoDedIRRF = (EditText) findViewById(R.id.XML_CON_VERPensaoDedIRRF);
        editText_CON_VERPensaoDedIRRF.addTextChangedListener(new MascaraMonetaria(editText_CON_VERPensaoDedIRRF));

        final EditText editText_CON_PERCAdtoQuinz = (EditText) findViewById(R.id.XML_CON_PERCAdtoQuinz);
        editText_CON_PERCAdtoQuinz.addTextChangedListener(new MascaraMonetaria(editText_CON_PERCAdtoQuinz));

        final EditText editText_CON_VERVlAdtoQuinz = (EditText) findViewById(R.id.XML_CON_VERVlAdtoQuinz);
        editText_CON_VERVlAdtoQuinz.addTextChangedListener(new MascaraMonetaria(editText_CON_VERVlAdtoQuinz));

        final EditText editText_CON_CSindDias = (EditText) findViewById(R.id.XML_CON_CSindDias);
        editText_CON_CSindDias.addTextChangedListener(new MascaraMonetaria(editText_CON_CSindDias));

        final EditText editText_CON_CSindValor = (EditText) findViewById(R.id.XML_CON_CSindValor);
        editText_CON_CSindValor.addTextChangedListener(new MascaraMonetaria(editText_CON_CSindValor));

        final EditText editText_CON_CAVlFixo = (EditText) findViewById(R.id.XML_CON_CAVlFixo);
        editText_CON_CAVlFixo.addTextChangedListener(new MascaraMonetaria(editText_CON_CAVlFixo));

        final EditText editText_CON_CAPERC = (EditText) findViewById(R.id.XML_CON_CAPERC);
        editText_CON_CAPERC.addTextChangedListener(new MascaraMonetaria(editText_CON_CAPERC));

        final EditText editText_CON_CAVlTeto = (EditText) findViewById(R.id.XML_CON_CAVlTeto);
        editText_CON_CAVlTeto.addTextChangedListener(new MascaraMonetaria(editText_CON_CAVlTeto));

        final EditText editText_CON_CAVlDesc = (EditText) findViewById(R.id.XML_CON_CAVlDesc);
        editText_CON_CAVlDesc.addTextChangedListener(new MascaraMonetaria(editText_CON_CAVlDesc));

        final EditText editText_CON_PERCDescVR = (EditText) findViewById(R.id.XML_CON_PERCDescVR);
        editText_CON_PERCDescVR.addTextChangedListener(new MascaraMonetaria(editText_CON_PERCDescVR));

        final EditText editText_CON_VLCredVR = (EditText) findViewById(R.id.XML_CON_VLCredVR);
        editText_CON_VLCredVR.addTextChangedListener(new MascaraMonetaria(editText_CON_VLCredVR));

        final EditText editText_CON_VERVlDescVR = (EditText) findViewById(R.id.XML_CON_VERVlDescVR);
        editText_CON_VERVlDescVR.addTextChangedListener(new MascaraMonetaria(editText_CON_VERVlDescVR));

        final EditText editText_CON_PERCDescVT = (EditText) findViewById(R.id.XML_CON_PERCDescVT);
        editText_CON_PERCDescVT.addTextChangedListener(new MascaraMonetaria(editText_CON_PERCDescVT));

        final EditText editText_CON_VlCredVT = (EditText) findViewById(R.id.XML_CON_VlCredVT);
        editText_CON_VlCredVT.addTextChangedListener(new MascaraMonetaria(editText_CON_VlCredVT));

        final EditText editText_CON_VERVLDescVT = (EditText) findViewById(R.id.XML_CON_VERVLDescVT);
        editText_CON_VERVLDescVT.addTextChangedListener(new MascaraMonetaria(editText_CON_VERVLDescVT));

        final EditText editText_CON_QtdVidasAssMed = (EditText) findViewById(R.id.XML_CON_QtdVidasAssMed);
        editText_CON_QtdVidasAssMed.addTextChangedListener(new MascaraMonetaria(editText_CON_QtdVidasAssMed));

        final EditText editText_CON_VlPVidasAssMed = (EditText) findViewById(R.id.XML_CON_VlPVidasAssMed);
        editText_CON_VlPVidasAssMed.addTextChangedListener(new MascaraMonetaria(editText_CON_VlPVidasAssMed));

        final EditText editText_CON_VlDescAssMed = (EditText) findViewById(R.id.XML_CON_VlDescAssMed);
        editText_CON_VlDescAssMed.addTextChangedListener(new MascaraMonetaria(editText_CON_VlDescAssMed));

        final EditText editText_CON_QtdVidasAssOdo = (EditText) findViewById(R.id.XML_CON_QtdVidasAssOdo);
        editText_CON_QtdVidasAssOdo.addTextChangedListener(new MascaraMonetaria(editText_CON_QtdVidasAssOdo));

        final EditText editText_CON_VlPVidasAssOdo = (EditText) findViewById(R.id.XML_CON_VlPVidasAssOdo);
        editText_CON_VlPVidasAssOdo.addTextChangedListener(new MascaraMonetaria(editText_CON_VlPVidasAssOdo));

        final EditText editText_CON_VlDescAssOdo = (EditText) findViewById(R.id.XML_CON_VlDescAssOdo);
        editText_CON_VlDescAssOdo.addTextChangedListener(new MascaraMonetaria(editText_CON_VlDescAssOdo));

        final EditText editText_CON_VlDesc01SI = (EditText) findViewById(R.id.XML_CON_VlDesc01SI);
        editText_CON_VlDesc01SI.addTextChangedListener(new MascaraMonetaria(editText_CON_VlDesc01SI));

        final EditText editText_CON_VlDesc02SI = (EditText) findViewById(R.id.XML_CON_VlDesc02SI);
        editText_CON_VlDesc02SI.addTextChangedListener(new MascaraMonetaria(editText_CON_VlDesc02SI));

        final EditText editText_CON_PercFGTS = (EditText) findViewById(R.id.XML_CON_PercFGTS);
        editText_CON_PercFGTS.addTextChangedListener(new MascaraMonetaria(editText_CON_PercFGTS));

        final EditText editText_CON_BaseFGTS = (EditText) findViewById(R.id.XML_CON_BaseFGTS);
        editText_CON_BaseFGTS.addTextChangedListener(new MascaraMonetaria(editText_CON_BaseFGTS));

        final EditText editText_CON_DepositoFGTS = (EditText) findViewById(R.id.XML_CON_DepositoFGTS);
        editText_CON_DepositoFGTS.addTextChangedListener(new MascaraMonetaria(editText_CON_DepositoFGTS));

        final EditText editText_CON_TotalVencimentos = (EditText) findViewById(R.id.XML_CON_TotalVencimentos);
        editText_CON_TotalVencimentos.addTextChangedListener(new MascaraMonetaria(editText_CON_TotalVencimentos));

        final EditText editText_CON_TotalDescontos = (EditText) findViewById(R.id.XML_CON_TotalDescontos);
        editText_CON_TotalDescontos.addTextChangedListener(new MascaraMonetaria(editText_CON_TotalDescontos));

        final EditText editText_CON_TotalLiquido = (EditText) findViewById(R.id.XML_CON_TotalLiquido);
        editText_CON_TotalLiquido.addTextChangedListener(new MascaraMonetaria(editText_CON_TotalLiquido));

        final EditText editINSSBASECALCULO = (EditText)findViewById(R.id.INSSBaseCalculo);
        editINSSBASECALCULO.addTextChangedListener(new MascaraMonetaria(editINSSBASECALCULO));

        final EditText editINSSVALDESC = (EditText)findViewById(R.id.INSSValDesc);
        editINSSVALDESC.addTextChangedListener(new MascaraMonetaria(editINSSVALDESC));

        final EditText editIRRFBASECALCULO = (EditText)findViewById(R.id.IRRFBaseCalculo);
        editIRRFBASECALCULO.addTextChangedListener(new MascaraMonetaria(editIRRFBASECALCULO));

        final EditText editIRRFVALDESC = (EditText)findViewById(R.id.IRRFValDesc);
        editIRRFVALDESC.addTextChangedListener(new MascaraMonetaria(editIRRFVALDESC));

        final EditText editBASECALCSALFAM = (EditText)findViewById(R.id.BaseCalcSalFam);
        editBASECALCSALFAM.addTextChangedListener(new MascaraMonetaria(editBASECALCSALFAM));

        final EditText editSALFAMPAGAR = (EditText)findViewById(R.id.SalFamPagar);
        editSALFAMPAGAR.addTextChangedListener(new MascaraMonetaria(editSALFAMPAGAR));

        editTextIDContratadoIncluir = (EditText)findViewById(R.id.XML_ID_ContratadoIncluir);


        BotaoCON_Alterar = (Button) findViewById(R.id.BotaoCON_Alterar);
        BotaoCON_Adicionar = (Button) findViewById(R.id.BotaoCON_Adicionar);
        btnBotaoCON_Deletar = (Button) findViewById(R.id.BotaoCON_Deletar);
        BotaoCON_Buscar = (Button) findViewById(R.id.BotaoCON_Buscar);
        BotaoCON_LimparCampos = (Button) findViewById(R.id.BotaoCON_LimparCampos);
        BotaoCalculoAutomatico = (Button) findViewById(R.id.BotaoCalculoAutomatico);

        BotaoCONListaLike = (Button) findViewById(R.id.BotaoCONListaLike);
        BotaoSalvarTexto = (Button) findViewById(R.id.BotaoSalvarTexto);
        BotaoCON_Lista = (Button) findViewById(R.id.BotaoCON_Lista);
        btnCONultimoID = (Button) findViewById(R.id.btnCONultimoID);
        BotaoCON_Deletar = (Button) findViewById(R.id.BotaoCON_Deletar);
        BotaoSalvarDownloads = (Button) findViewById(R.id.BotaoSalvarDownloads);



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








        BotaoCON_Buscar.setOnClickListener(new android.view.View.OnClickListener(){
              @Override
            public void onClick(View view) {

                SQLiteDatabase db = helper.getWritableDatabase();

                if (editText_ID_Contratado.getText().toString().trim().length() == 0) {
                    showMessage("Erro!!", "Favor Entrar com o Nº ID");
                    return;
                }


                Cursor c = db.rawQuery("SELECT * FROM Contratados WHERE id='" + editText_ID_Contratado.getText() + "'", null);

                if (c.moveToFirst()) {
                    editText_ID_Contratado.setText(c.getString(0));
                    editText_CON_DssNome.setText(c.getString(1));
                    editText_CON_CPF.setText(c.getString(2));
                    editText_CON_DtdAdmissao.setText(c.getString(3));
                    editText_CON_IDCargo.setText(c.getString(4));
                    editText_CON_QtdDepIRRF.setText(c.getString(5));
                    editText_CON_QtdDepSF.setText(c.getString(6));
                    editText_CON_HorasBaseMes.setText(c.getString(7));
                    editText_CON_SalarioContratual.setText(c.getString(8));
                    editText_CON_SalarioHora.setText(c.getString(9));
                    editText_CON_SalarioDia.setText(c.getString(10));
                    editText_CON_DiasTrabalhados.setText(c.getString(11));
                    editText_CON_HorasTrabalhadas.setText(c.getString(12));
                    editText_CON_VERSalMesHora.setText(c.getString(13));
                    editSALFAMPAGAR.setText(c.getString(14));
                    editText_CON_PERCHoraExtra1.setText(c.getString(15));
                    editText_CON_QTDHoraExtra1.setText(c.getString(16));
                    editText_CON_VERHoraExtra1.setText(c.getString(17));
                    editText_CON_PERCAdicNoturno1.setText(c.getString(18));
                    editText_CON_QTDHoraAdicNotu1.setText(c.getString(19));
                    editText_CON_VERAdicNoturno1.setText(c.getString(20));
                    editText_CON_QTDDiasDSRUteis.setText(c.getString(21));
                    editText_CON_QTDDiasDSRInuteis.setText(c.getString(22));
                    editText_CON_VERValorDSR.setText(c.getString(23));
                    editText_CON_VERVL01TodasINC.setText(c.getString(24));
                    editText_CON_VERVL02TodasINC.setText(c.getString(25));
                    editText_CON_VERVL03SemINC.setText(c.getString(26));
                    editText_CON_VERVL04SemINC.setText(c.getString(27));
                    editText_CON_PERCPGBL.setText(c.getString(28));
                    editText_CON_VERPGBL.setText(c.getString(29));
                    editText_CON_PERCVGBL.setText(c.getString(30));
                    editText_CON_VERVGBL.setText(c.getString(31));
                    editText_CON_QtdDiasFalta.setText(c.getString(32));
                    editText_CON_VERValorFalta.setText(c.getString(33));
                    editText_CON_QtdHorasAtraso.setText(c.getString(34));
                    editText_CON_VERValorAtraso.setText(c.getString(35));
                    editText_CON_VERPensaoDedIRRF.setText(c.getString(36));
                    editText_CON_VERPensaoDesconto.setText(c.getString(37));
                    editText_CON_PERCAdtoQuinz.setText(c.getString(38));
                    editText_CON_VERVlAdtoQuinz.setText(c.getString(39));
                    editText_CON_CSindDias.setText(c.getString(40));
                    editText_CON_CSindValor.setText(c.getString(41));
                    editText_CON_CAVlFixo.setText(c.getString(42));
                    editText_CON_CAPERC.setText(c.getString(43));
                    editText_CON_CAVlTeto.setText(c.getString(44));
                    editText_CON_CAVlDesc.setText(c.getString(45));
                    editText_CON_PERCDescVT.setText(c.getString(46));
                    editText_CON_VlCredVT.setText(c.getString(47));
                    editText_CON_VERVLDescVT.setText(c.getString(48));
                    editText_CON_VlDesc01SI.setText(c.getString(49));
                    editText_CON_VlDesc02SI.setText(c.getString(50));
                    editText_CON_PERCDescVR.setText(c.getString(51));
                    editText_CON_VLCredVR.setText(c.getString(52));
                    editText_CON_VERVlDescVR.setText(c.getString(53));
                    editText_CON_QtdVidasAssMed.setText(c.getString(54));
                    editText_CON_VlPVidasAssMed.setText(c.getString(55));
                    editText_CON_VlDescAssMed.setText(c.getString(56));
                    editText_CON_QtdVidasAssOdo.setText(c.getString(57));
                    editText_CON_VlPVidasAssOdo.setText(c.getString(58));
                    editText_CON_VlDescAssOdo.setText(c.getString(59));
                    editINSSBASECALCULO.setText(c.getString(60));
                    editINSSVALDESC.setText(c.getString(61));
                    editIRRFBASECALCULO.setText(c.getString(62));
                    editIRRFVALDESC.setText(c.getString(63));
                    editText_CON_BaseFGTS.setText(c.getString(64));
                    editText_CON_DepositoFGTS.setText(c.getString(65));
                    editText_CON_TotalVencimentos.setText(c.getString(66));
                    editText_CON_TotalDescontos.setText(c.getString(67));
                    editText_CON_TotalLiquido.setText(c.getString(68));
                    editText_CON_PercFGTS.setText(c.getString(69));
                    editText_CON_IDGrauInstrucao.setText(c.getString(70));
                    editText_CON_RGNumero.setText(c.getString(71));
                    editText_CON_RGDtdEmissao.setText(c.getString(72));
                    editText_CON_RGOrgaoEmissor.setText(c.getString(73));
                    editText_CON_PISNumero.setText(c.getString(74));
                    editText_CON_EnderecoResidencial.setText(c.getString(75));
                    editText_CON_Email.setText(c.getString(76));
                    editText_CON_IDCCusto.setText(c.getString(77));
                    editText_CON_IDEstadosCivis.setText(c.getString(78));
                    editText_CON_IDSexo.setText(c.getString(79));
                    editText_CON_IDHorario.setText(c.getString(80));
                    editText_CON_IDEncargo.setText(c.getString(81));


                } else {
                    showMessage("Erro!", "ID Inválido");
                }
            }

            });//FIM DO BOTÃO BotaoCON_Buscar





        BotaoCON_Alterar.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(View view) {

                SQLiteDatabase db = helper.getWritableDatabase();

                if ((editText_ID_Contratado.getText().toString().trim().length() == 0)) {
                    showMessage("Erro!!", "Favor Digitar o ID");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM Contratados WHERE id='" + editText_ID_Contratado.getText() + "'", null);
                if (c.moveToFirst()) {
                    db.execSQL("UPDATE Contratados SET CON_DssNome='" + editText_CON_DssNome.getText()
                            + "', CON_CPF='" + editText_CON_CPF.getText()
                            + "', CON_DtdAdmissao='" + editText_CON_DtdAdmissao.getText()
                            + "', CON_IDCargo='" + editText_CON_IDCargo.getText()
                            + "', CON_QtdDepIRRF='" + editText_CON_QtdDepIRRF.getText()
                            + "', CON_QtdDepSF='" + editText_CON_QtdDepSF.getText()
                            + "', CON_HorasBaseMes='" + editText_CON_HorasBaseMes.getText()
                            + "', CON_SalarioContratual='" + editText_CON_SalarioContratual.getText()
                            + "', CON_SalarioHora='" + editText_CON_SalarioHora.getText()
                            + "', CON_SalarioDia='" + editText_CON_SalarioDia.getText()
                            + "', CON_DiasTrabalhados='" + editText_CON_DiasTrabalhados.getText()
                            + "', CON_HorasTrabalhadas='" + editText_CON_HorasTrabalhadas.getText()
                            + "', CON_VERSalMesHora='" + editText_CON_VERSalMesHora.getText()
                            + "', CON_VERSalFam='" + editSALFAMPAGAR.getText()
                            + "', CON_PERCHoraExtra1='" + editText_CON_PERCHoraExtra1.getText()
                            + "', CON_QTDHoraExtra1='" + editText_CON_QTDHoraExtra1.getText()
                            + "', CON_VERHoraExtra1='" + editText_CON_VERHoraExtra1.getText()
                            + "', CON_PERCAdicNoturno1='" + editText_CON_PERCAdicNoturno1.getText()
                            + "', CON_QTDHoraAdicNotu1='" + editText_CON_QTDHoraAdicNotu1.getText()
                            + "', CON_VERAdicNoturno1='" + editText_CON_VERAdicNoturno1.getText()
                            + "', CON_QTDDiasDSRUteis='" + editText_CON_QTDDiasDSRUteis.getText()
                            + "', CON_QTDDiasDSRInuteis='" + editText_CON_QTDDiasDSRInuteis.getText()
                            + "', CON_VERValorDSR='" + editText_CON_VERValorDSR.getText()
                            + "', CON_VERVL01TodasINC='" + editText_CON_VERVL01TodasINC.getText()
                            + "', CON_VERVL02TodasINC='" + editText_CON_VERVL02TodasINC.getText()
                            + "', CON_VERVL03SemINC='" + editText_CON_VERVL03SemINC.getText()
                            + "', CON_VERVL04SemINC='" + editText_CON_VERVL04SemINC.getText()
                            + "', CON_PERCPGBL='" + editText_CON_PERCPGBL.getText()
                            + "', CON_VERPGBL='" + editText_CON_VERPGBL.getText()
                            + "', CON_PERCVGBL='" + editText_CON_PERCVGBL.getText()
                            + "', CON_VERVGBL='" + editText_CON_VERVGBL.getText()
                            + "', CON_QtdDiasFalta='" + editText_CON_QtdDiasFalta.getText()
                            + "', CON_VERValorFalta='" + editText_CON_VERValorFalta.getText()
                            + "', CON_QtdHorasAtraso='" + editText_CON_QtdHorasAtraso.getText()
                            + "', CON_VERValorAtraso='" + editText_CON_VERValorAtraso.getText()
                            + "', CON_VERPensaoDedIRRF='" + editText_CON_VERPensaoDedIRRF.getText()
                            + "', CON_VERPensaoDesconto='" + editText_CON_VERPensaoDesconto.getText()
                            + "', CON_PERCAdtoQuinz='" + editText_CON_PERCAdtoQuinz.getText()
                            + "', CON_VERVlAdtoQuinz='" + editText_CON_VERVlAdtoQuinz.getText()
                            + "', CON_CSindDias='" + editText_CON_CSindDias.getText()
                            + "', CON_CSindValor='" + editText_CON_CSindValor.getText()
                            + "', CON_CAVlFixo='" + editText_CON_CAVlFixo.getText()
                            + "', CON_CAPERC='" + editText_CON_CAPERC.getText()
                            + "', CON_CAVlTeto='" + editText_CON_CAVlTeto.getText()
                            + "', CON_CAVlDesc='" + editText_CON_CAVlDesc.getText()
                            + "', CON_PERCDescVT='" + editText_CON_PERCDescVT.getText()
                            + "', CON_VlCredVT='" + editText_CON_VlCredVT.getText()
                            + "', CON_VERVLDescVT='" + editText_CON_VERVLDescVT.getText()
                            + "', CON_VlDesc01SI='" + editText_CON_VlDesc01SI.getText()
                            + "', CON_VlDesc02SI='" + editText_CON_VlDesc02SI.getText()
                            + "', CON_PERCDescVR='" + editText_CON_PERCDescVR.getText()
                            + "', CON_VLCredVR='" + editText_CON_VLCredVR.getText()
                            + "', CON_VERVlDescVR='" + editText_CON_VERVlDescVR.getText()
                            + "', CON_QtdVidasAssMed='" + editText_CON_QtdVidasAssMed.getText()
                            + "', CON_VlPVidasAssMed='" + editText_CON_VlPVidasAssMed.getText()
                            + "', CON_VlDescAssMed='" + editText_CON_VlDescAssMed.getText()
                            + "', CON_QtdVidasAssOdo='" + editText_CON_QtdVidasAssOdo.getText()
                            + "', CON_VlPVidasAssOdo='" + editText_CON_VlPVidasAssOdo.getText()
                            + "', CON_VlDescAssOdo='" + editText_CON_VlDescAssOdo.getText()
                            + "', CON_BaseINSS='" + editINSSBASECALCULO.getText()
                            + "', CON_DescontoINSS='" + editINSSVALDESC.getText()
                            + "', CON_BaseIRRF='" + editIRRFBASECALCULO.getText()
                            + "', CON_DescontoIRRF='" + editIRRFVALDESC.getText()
                            + "', CON_BaseFGTS='" + editText_CON_BaseFGTS.getText()
                            + "', CON_DepositoFGTS='" + editText_CON_DepositoFGTS.getText()
                            + "', CON_TotalVencimentos='" + editText_CON_TotalVencimentos.getText()
                            + "', CON_TotalDescontos='" + editText_CON_TotalDescontos.getText()
                            + "', CON_TotalLiquido='" + editText_CON_TotalLiquido.getText()
                            + "', CON_PercFGTS='" + editText_CON_PercFGTS.getText()
                            + "', CON_IDGrauInstrucao='" + editText_CON_IDGrauInstrucao.getText()
                            + "', CON_RGNumero='" + editText_CON_RGNumero.getText()
                            + "', CON_RGDtdEmissao='" + editText_CON_RGDtdEmissao.getText()
                            + "', CON_RGOrgaoEmissor='" + editText_CON_RGOrgaoEmissor.getText()
                            + "', CON_PISNumero='" + editText_CON_PISNumero.getText()
                            + "', CON_EnderecoResidencial='" + editText_CON_EnderecoResidencial.getText()
                            + "', CON_Email='" + editText_CON_Email.getText()
                            + "', CON_IDCCusto='" + editText_CON_IDCCusto.getText()
                            + "', CON_IDEstadosCivis='" + editText_CON_IDEstadosCivis.getText()
                            + "', CON_IDSexo='" + editText_CON_IDSexo.getText()
                            + "', CON_IDHorario='" + editText_CON_IDHorario.getText()
                            + "', CON_IDEncargo='" + editText_CON_IDEncargo.getText()


                            + "' WHERE id='" + editText_ID_Contratado.getText() + "'");

                    showMessage("Ótimo!!", "Dados Alterados");
                   // clearText();
                } else {
                    showMessage("Erro!", "Esse ID NÃO existe no Banco de Dados");
                    //clearText();

                }

            }
            });//FIM DO BOTÃO BotaoCON_Alterar





        BotaoCON_Adicionar.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(View view) {

                SQLiteDatabase db = helper.getWritableDatabase();


                if (editTextIDContratadoIncluir.getText().toString().isEmpty() || (editText_CON_SalarioContratual.getText().toString().isEmpty() )) {
                    showMessage("Erro", "Aperte o Botão Auto Incremento e PREENCHA os Campos Horas Bs Mês, Salário Mês e Dias Trabalhados");
                    return;
                }

                db.execSQL("INSERT INTO Contratados VALUES('" + (editTextIDContratadoIncluir.getText() + "','" +
                        editText_CON_DssNome.getText() + "','" +
                        editText_CON_CPF.getText() + "','" +
                        editText_CON_DtdAdmissao.getText() + "','" +
                        editText_CON_IDCargo.getText() + "','" +
                        editText_CON_QtdDepIRRF.getText() + "','" +
                        editText_CON_QtdDepSF.getText() + "','" +
                        editText_CON_HorasBaseMes.getText() + "','" +
                        editText_CON_SalarioContratual.getText() + "','" +
                        editText_CON_SalarioHora.getText() + "','" +
                        editText_CON_SalarioDia.getText() + "','" +
                        editText_CON_DiasTrabalhados.getText() + "','" +
                        editText_CON_HorasTrabalhadas.getText() + "','" +
                        editText_CON_VERSalMesHora.getText() + "','" +
                        editSALFAMPAGAR.getText() + "','" +
                        editText_CON_PERCHoraExtra1.getText() + "','" +
                        editText_CON_QTDHoraExtra1.getText() + "','" +
                        editText_CON_VERHoraExtra1.getText() + "','" +
                        editText_CON_PERCAdicNoturno1.getText() + "','" +
                        editText_CON_QTDHoraAdicNotu1.getText() + "','" +
                        editText_CON_VERAdicNoturno1.getText() + "','" +
                        editText_CON_QTDDiasDSRUteis.getText() + "','" +
                        editText_CON_QTDDiasDSRInuteis.getText() + "','" +
                        editText_CON_VERValorDSR.getText() + "','" +
                        editText_CON_VERVL01TodasINC.getText() + "','" +
                        editText_CON_VERVL02TodasINC.getText() + "','" +
                        editText_CON_VERVL03SemINC.getText() + "','" +
                        editText_CON_VERVL04SemINC.getText() + "','" +
                        editText_CON_PERCPGBL.getText() + "','" +
                        editText_CON_VERPGBL.getText() + "','" +
                        editText_CON_PERCVGBL.getText() + "','" +
                        editText_CON_VERVGBL.getText() + "','" +
                        editText_CON_QtdDiasFalta.getText() + "','" +
                        editText_CON_VERValorFalta.getText() + "','" +
                        editText_CON_QtdHorasAtraso.getText() + "','" +
                        editText_CON_VERValorAtraso.getText() + "','" +
                        editText_CON_VERPensaoDedIRRF.getText() + "','" +
                        editText_CON_VERPensaoDesconto.getText() + "','" +
                        editText_CON_PERCAdtoQuinz.getText() + "','" +
                        editText_CON_VERVlAdtoQuinz.getText() + "','" +
                        editText_CON_CSindDias.getText() + "','" +
                        editText_CON_CSindValor.getText() + "','" +
                        editText_CON_CAVlFixo.getText() + "','" +
                        editText_CON_CAPERC.getText() + "','" +
                        editText_CON_CAVlTeto.getText() + "','" +
                        editText_CON_CAVlDesc.getText() + "','" +
                        editText_CON_PERCDescVT.getText() + "','" +
                        editText_CON_VlCredVT.getText() + "','" +
                        editText_CON_VERVLDescVT.getText() + "','" +
                        editText_CON_VlDesc01SI.getText() + "','" +
                        editText_CON_VlDesc02SI.getText() + "','" +
                        editText_CON_PERCDescVR.getText() + "','" +
                        editText_CON_VLCredVR.getText() + "','" +
                        editText_CON_VERVlDescVR.getText() + "','" +
                        editText_CON_QtdVidasAssMed.getText() + "','" +
                        editText_CON_VlPVidasAssMed.getText() + "','" +
                        editText_CON_VlDescAssMed.getText() + "','" +
                        editText_CON_QtdVidasAssOdo.getText() + "','" +
                        editText_CON_VlPVidasAssOdo.getText() + "','" +
                        editText_CON_VlDescAssOdo.getText() + "','" +
                        editINSSBASECALCULO.getText() + "','" +
                        editINSSVALDESC.getText() + "','" +
                        editIRRFBASECALCULO.getText() + "','" +
                        editIRRFVALDESC.getText() + "','" +
                        editText_CON_BaseFGTS.getText() + "','" +
                        editText_CON_DepositoFGTS.getText() + "','" +
                        editText_CON_TotalVencimentos.getText() + "','" +
                        editText_CON_TotalDescontos.getText() + "','" +
                        editText_CON_TotalLiquido.getText() + "','" +
                        editText_CON_PercFGTS.getText() + "','" +
                        editText_CON_IDGrauInstrucao.getText() + "','" +
                        editText_CON_RGNumero.getText() + "','" +
                        editText_CON_RGDtdEmissao.getText() + "','" +
                        editText_CON_RGOrgaoEmissor.getText() + "','" +
                        editText_CON_PISNumero.getText() + "','" +
                        editText_CON_EnderecoResidencial.getText() + "','" +
                        editText_CON_Email.getText() + "','" +
                        editText_CON_IDCCusto.getText() + "','" +
                        editText_CON_IDEstadosCivis.getText() + "','" +
                        editText_CON_IDSexo.getText() + "','" +
                        editText_CON_IDHorario.getText() + "','" +
                        editText_CON_IDEncargo.getText() + "');"));

                showMessage("OK!", "Dados Gravados");
                //clearText();

            }

        });//FIM DO BOTÃO BotaoCON_Adicionar





        //Esse método efetua os cálculos de INSS, IRRF, SALARIO FAMÍLIA USANDO A TABELADO DO BANCO DE DADOS
//TabelaLegais buscando os campos onde constam as faixas, pois NÃO PRECISA que os valores das esteja
//num EditText da mesma classe java  **Criado em 08/02/2018**
        BotaoCalculoAutomatico.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(View view) {

                SQLiteDatabase db = helper.getWritableDatabase();

                if (editText_ID_Contratado.getText().toString().trim().length() == 0) {
                    showMessage("Erro!!", "Favor Entrar com o Nº ID");
                    return;
                }

                Cursor c = db.rawQuery("SELECT * FROM Contratados, TabelaLegais WHERE id='" + editText_ID_Contratado.getText() + "'", null);


                if (c.moveToFirst()) {
                    editText_ID_Contratado.setText(c.getString(0));
                    editText_CON_DssNome.setText(c.getString(1));
                    editText_CON_CPF.setText(c.getString(2));
                    editText_CON_DtdAdmissao.setText(c.getString(3));
                    editText_CON_IDCargo.setText(c.getString(4));
                    editText_CON_QtdDepIRRF.setText(c.getString(5));
                    editText_CON_QtdDepSF.setText(c.getString(6));
                    editText_CON_HorasBaseMes.setText(c.getString(7));
                    editText_CON_SalarioContratual.setText(c.getString(8));
                    editText_CON_SalarioHora.setText(c.getString(9));
                    editText_CON_SalarioDia.setText(c.getString(10));
                    editText_CON_DiasTrabalhados.setText(c.getString(11));
                    editText_CON_HorasTrabalhadas.setText(c.getString(12));
                    editText_CON_VERSalMesHora.setText(c.getString(13));
                    editSALFAMPAGAR.setText(c.getString(14));
                    editText_CON_PERCHoraExtra1.setText(c.getString(15));
                    editText_CON_QTDHoraExtra1.setText(c.getString(16));
                    editText_CON_VERHoraExtra1.setText(c.getString(17));
                    editText_CON_PERCAdicNoturno1.setText(c.getString(18));
                    editText_CON_QTDHoraAdicNotu1.setText(c.getString(19));
                    editText_CON_VERAdicNoturno1.setText(c.getString(20));
                    editText_CON_QTDDiasDSRUteis.setText(c.getString(21));
                    editText_CON_QTDDiasDSRInuteis.setText(c.getString(22));
                    editText_CON_VERValorDSR.setText(c.getString(23));
                    editText_CON_VERVL01TodasINC.setText(c.getString(24));
                    editText_CON_VERVL02TodasINC.setText(c.getString(25));
                    editText_CON_VERVL03SemINC.setText(c.getString(26));
                    editText_CON_VERVL04SemINC.setText(c.getString(27));
                    editText_CON_PERCPGBL.setText(c.getString(28));
                    editText_CON_VERPGBL.setText(c.getString(29));
                    editText_CON_PERCVGBL.setText(c.getString(30));
                    editText_CON_VERVGBL.setText(c.getString(31));
                    editText_CON_QtdDiasFalta.setText(c.getString(32));
                    editText_CON_VERValorFalta.setText(c.getString(33));
                    editText_CON_QtdHorasAtraso.setText(c.getString(34));
                    editText_CON_VERValorAtraso.setText(c.getString(35));
                    editText_CON_VERPensaoDedIRRF.setText(c.getString(36));
                    editText_CON_VERPensaoDesconto.setText(c.getString(37));
                    editText_CON_PERCAdtoQuinz.setText(c.getString(38));
                    editText_CON_VERVlAdtoQuinz.setText(c.getString(39));
                    editText_CON_CSindDias.setText(c.getString(40));
                    editText_CON_CSindValor.setText(c.getString(41));
                    editText_CON_CAVlFixo.setText(c.getString(42));
                    editText_CON_CAPERC.setText(c.getString(43));
                    editText_CON_CAVlTeto.setText(c.getString(44));
                    editText_CON_CAVlDesc.setText(c.getString(45));
                    editText_CON_PERCDescVT.setText(c.getString(46));
                    editText_CON_VlCredVT.setText(c.getString(47));
                    editText_CON_VERVLDescVT.setText(c.getString(48));
                    editText_CON_VlDesc01SI.setText(c.getString(49));
                    editText_CON_VlDesc02SI.setText(c.getString(50));
                    editText_CON_PERCDescVR.setText(c.getString(51));
                    editText_CON_VLCredVR.setText(c.getString(52));
                    editText_CON_VERVlDescVR.setText(c.getString(53));
                    editText_CON_QtdVidasAssMed.setText(c.getString(54));
                    editText_CON_VlPVidasAssMed.setText(c.getString(55));
                    editText_CON_VlDescAssMed.setText(c.getString(56));
                    editText_CON_QtdVidasAssOdo.setText(c.getString(57));
                    editText_CON_VlPVidasAssOdo.setText(c.getString(58));
                    editText_CON_VlDescAssOdo.setText(c.getString(59));
                    editINSSBASECALCULO.setText(c.getString(60));
                    editINSSVALDESC.setText(c.getString(61));
                    editIRRFBASECALCULO.setText(c.getString(62));
                    editIRRFVALDESC.setText(c.getString(63));
                    editText_CON_BaseFGTS.setText(c.getString(64));
                    editText_CON_DepositoFGTS.setText(c.getString(65));
                    editText_CON_TotalVencimentos.setText(c.getString(66));
                    editText_CON_TotalDescontos.setText(c.getString(67));
                    editText_CON_TotalLiquido.setText(c.getString(68));
                    editText_CON_PercFGTS.setText(c.getString(69));
                    editText_CON_IDGrauInstrucao.setText(c.getString(70));
                    editText_CON_RGNumero.setText(c.getString(71));
                    editText_CON_RGDtdEmissao.setText(c.getString(72));
                    editText_CON_RGOrgaoEmissor.setText(c.getString(73));
                    editText_CON_PISNumero.setText(c.getString(74));
                    editText_CON_EnderecoResidencial.setText(c.getString(75));
                    editText_CON_Email.setText(c.getString(76));
                    editText_CON_IDCCusto.setText(c.getString(77));
                    editText_CON_IDEstadosCivis.setText(c.getString(78));
                    editText_CON_IDSexo.setText(c.getString(79));
                    editText_CON_IDHorario.setText(c.getString(80));
                    editText_CON_IDEncargo.setText(c.getString(81));




                    DecimalFormat dfricci = new DecimalFormat("###,##0.00");  //Código para converter MOEDA em número
                    String salario = String.valueOf(editText_CON_SalarioContratual.getText().toString());  //Pega o valor digitado no EditText e guarda o valor na String salario
                    String HorasBaseMes = String.valueOf(editText_CON_HorasBaseMes.getText().toString());
                    String SalarioDia = String.valueOf(editText_CON_SalarioDia.getText().toString());
                    String DiasTrabalhadosMes = String.valueOf(editText_CON_DiasTrabalhados.getText().toString());
                    String HorasTrabalhadasMes = String.valueOf(editText_CON_HorasTrabalhadas.getText().toString());
                    String VerbaVl01TodasINC = String.valueOf(editText_CON_VERVL01TodasINC.getText().toString());
                    String VerbaVl02TodasINC = String.valueOf(editText_CON_VERVL02TodasINC.getText().toString());
                    String VerbaVl03SemINC = String.valueOf(editText_CON_VERVL03SemINC.getText().toString());
                    String VerbaVl04SemINC = String.valueOf(editText_CON_VERVL04SemINC.getText().toString());
                    String HorasExtraPERC1 = String.valueOf(editText_CON_PERCHoraExtra1.getText().toString());
                    String HorasExtraQTD1 = String.valueOf(editText_CON_QTDHoraExtra1.getText().toString());
                    String HorasExtraVal1 = String.valueOf(editText_CON_VERHoraExtra1.getText().toString());
                    String AdicNotPERC1 = String.valueOf(editText_CON_PERCAdicNoturno1.getText().toString());
                    String AdicNotQTD1 = String.valueOf(editText_CON_QTDHoraAdicNotu1.getText().toString());
                    String AdicNotVal1 = String.valueOf(editText_CON_VERAdicNoturno1.getText().toString());
                    String DSRQtdDiasUteis = String.valueOf(editText_CON_QTDDiasDSRUteis.getText().toString());
                    String DSRQtdDiasInuteis = String.valueOf(editText_CON_QTDDiasDSRInuteis.getText().toString());
                    String DSRValor = String.valueOf(editText_CON_VERValorDSR.getText().toString());
                    String FaltasQtdDias1 = String.valueOf(editText_CON_QtdDiasFalta.getText().toString());
                    String FaltasValor1 = String.valueOf(editText_CON_VERValorFalta.getText().toString());
                    String FaltasQtdHoras1 = String.valueOf(editText_CON_QtdHorasAtraso.getText().toString());
                    String FaltasQtdHorasValor = String.valueOf(editText_CON_VERValorAtraso.getText().toString());
                    String PercPGBL1 = String.valueOf(editText_CON_PERCPGBL.getText().toString());
                    String ValorPGBL1 = String.valueOf(editText_CON_VERPGBL.getText().toString());
                    String PercVGBL1 = String.valueOf(editText_CON_PERCVGBL.getText().toString());
                    String ValorVGBL1 = String.valueOf(editText_CON_VERVGBL.getText().toString());
                    String PenDesc = String.valueOf(editText_CON_VERPensaoDesconto.getText().toString());
                    String PenReducaoIRRF = String.valueOf(editText_CON_VERPensaoDedIRRF.getText().toString());
                    String PercAdtoQuinz = String.valueOf(editText_CON_PERCAdtoQuinz.getText().toString());
                    String ValorAdtoQuinz = String.valueOf(editText_CON_VERVlAdtoQuinz.getText().toString());
                    String CsindQtdDias = String.valueOf(editText_CON_CSindDias.getText().toString());
                    String CSindValorDesc = String.valueOf(editText_CON_CSindValor.getText().toString());
                    String CAssistPerc1 = String.valueOf(editText_CON_CAPERC.getText().toString());
                    String CAssistVlFixo1 = String.valueOf(editText_CON_CAVlFixo.getText().toString());
                    String CAssistVlTeto1 = String.valueOf(editText_CON_CAVlTeto.getText().toString());
                    String CAssistVlDesc1 = String.valueOf(editText_CON_CAVlDesc.getText().toString());
                    String VRPerc1 = String.valueOf(editText_CON_PERCDescVR.getText().toString());
                    String VRValorCred1 = String.valueOf(editText_CON_VLCredVR.getText().toString());
                    String VRValorDesc1 = String.valueOf(editText_CON_VERVlDescVR.getText().toString());
                    String VTPerc1 = String.valueOf(editText_CON_PERCDescVT.getText().toString());
                    String VTValorCred1 = String.valueOf(editText_CON_VlCredVT.getText().toString());
                    String VTValorDesc1 = String.valueOf(editText_CON_VERVLDescVT.getText().toString());
                    String AssMedQuantidadeVidas = String.valueOf(editText_CON_QtdVidasAssMed.getText().toString());
                    String AssMedValoresVidas1 = String.valueOf(editText_CON_VlPVidasAssMed.getText().toString());
                    String AssMedValoresDesconto = String.valueOf(editText_CON_VlDescAssMed.getText().toString());
                    String AssOdoQuantidadeVidas = String.valueOf(editText_CON_QtdVidasAssOdo.getText().toString());
                    String AssOdoValoresVidas1 = String.valueOf(editText_CON_VlPVidasAssOdo.getText().toString());
                    String AssOdoValoresDesconto = String.valueOf(editText_CON_VlDescAssOdo.getText().toString());
                    String ValorDesc1SI = String.valueOf(editText_CON_VlDesc01SI.getText().toString());
                    String ValorDesc2SI = String.valueOf(editText_CON_VlDesc02SI.getText().toString());
                    String DescontoINSS = String.valueOf(editINSSVALDESC.getText().toString());
                    String BaseCalculoINSS = String.valueOf(editINSSBASECALCULO.getText().toString());
                    String DescontoIRRF = String.valueOf(editIRRFVALDESC.getText().toString());
                    String BaseCalculoIRRF1 = String.valueOf(editIRRFBASECALCULO.getText().toString());
                    String DepIRRFQtd = String.valueOf(editText_CON_QtdDepIRRF.getText().toString());
                    String SalFamiliaPagar = String.valueOf(editSALFAMPAGAR.getText().toString());
                    String BaseCalculoSF = String.valueOf(editBASECALCSALFAM.getText().toString());
                    String DepSalFamQtd = String.valueOf(editText_CON_QtdDepSF.getText().toString());
                    String PercentualFGTS = String.valueOf(editText_CON_PercFGTS.getText().toString());
                    String ValorDepositoFGTS = String.valueOf(editText_CON_DepositoFGTS.getText().toString());
                    String BaseFGTS1 = String.valueOf(editText_CON_BaseFGTS.getText().toString());
                    String ValorTotalVencimentos = String.valueOf(editText_CON_TotalVencimentos.getText().toString());
                    String ValorTotalDescontos = String.valueOf(editText_CON_TotalDescontos.getText().toString());
                    String ValorTotalLiquido = String.valueOf(editText_CON_TotalLiquido.getText().toString());












                    try {


//*** Salário Cadastral ***
                        String strCONSalarioContratual = (dfricci.parse(salario).toString());  //Grava na String strCONSalarioContratual a conversao do salario digitado no EditText valorMonetario sem R$, sem vírgula
                        Double vardoublesalario = parseDouble(String.valueOf(strCONSalarioContratual)); //Converte para double a String strCONSalarioContratual e grava na variavel vardoublesalario
                        Double SalCad = parseDouble(String.valueOf(strCONSalarioContratual)); //Converte para double a String strCONSalarioContratual e grava na variavel SalCad
                        //editText_CON_SalarioContratual.setText(format("%.2f", vardoublesalario).replace(",", "."));

//*** Salário Hora ***
                        String strCONHorasBaseMes = (dfricci.parse(HorasBaseMes).toString());
                        Double HrBsMes = parseDouble(String.valueOf(strCONHorasBaseMes));
                        double SalHora = SalCad / HrBsMes;
                        editText_CON_SalarioHora.setText(format("%.2f", SalHora).replace(",", "."));


//*** Salário Dia ***
                        String strCONSalarioDia = (dfricci.parse(SalarioDia).toString());
                        Double SalDiario = parseDouble(String.valueOf(strCONSalarioDia));
                        double Qtdias = 30.00;
                        SalDiario = SalCad / Qtdias;
                        editText_CON_SalarioDia.setText(format("%.2f", SalDiario).replace(",", "."));

//*** Salário Mensal a Pagar ***
                        String strCONDiasTrabalhados = (dfricci.parse(DiasTrabalhadosMes).toString());
                        Double DiasTrabMes = parseDouble(String.valueOf(strCONDiasTrabalhados));
                        String strCONHorasTrabalhadas = (dfricci.parse(HorasTrabalhadasMes).toString());
                        Double HorasTrabMes = parseDouble(String.valueOf(strCONHorasTrabalhadas));

                        Double SalMesHoraTrabalhado;

                        if (DiasTrabMes > 0.00 && HorasTrabMes == 0.00 && SalCad > 0.00) {SalMesHoraTrabalhado = (SalDiario * DiasTrabMes) ;
                        editText_CON_VERSalMesHora.setText(format("%.2f",SalMesHoraTrabalhado).replace(",", "."));}
                        else {SalMesHoraTrabalhado = (SalHora *  HorasTrabMes);
                        editText_CON_VERSalMesHora.setText(format("%.2f",SalMesHoraTrabalhado).replace(",", "."));}
//*** Valores de Vencimento "COM" Incidências (FGTS, INSS, IRRF) ***
                        String strCONVerVl01TodasINC = (dfricci.parse(VerbaVl01TodasINC).toString());
                        Double VlVenc1TI = parseDouble(String.valueOf(strCONVerVl01TodasINC));


                        String strCONVerVl02TodasINC = (dfricci.parse(VerbaVl02TodasINC).toString());
                        Double VlVenc2TI = parseDouble(String.valueOf(strCONVerVl02TodasINC));
                        //editText_CON_VERVL02TodasINC.setText(format("%.2f",VlVenc2TI).replace(",", "."));
//*** Valores de Vencimento "SEM" Incidências (FGTS, INSS, IRRF) ***
                        String strCONVERVL03SemINC = (dfricci.parse(VerbaVl03SemINC).toString());
                        Double VlVenc3SI = parseDouble(String.valueOf(strCONVERVL03SemINC));

                        String strCONVERVL04SemINC = (dfricci.parse(VerbaVl04SemINC).toString());
                        Double VlVenc4SI = parseDouble(String.valueOf(strCONVERVL04SemINC));
//*** Cálculo de Horas Extra ***
                        String strCONPERCHoraExtra1 = (dfricci.parse(HorasExtraPERC1).toString());
                        Double HoraExtraPerc = parseDouble(String.valueOf(strCONPERCHoraExtra1));

                        String strCONQTDHoraExtra1 = (dfricci.parse(HorasExtraQTD1).toString());
                        Double HoraExtraQtd = parseDouble(String.valueOf(strCONQTDHoraExtra1));

                        String strCONVERHoraExtra1 = (dfricci.parse(HorasExtraVal1).toString());
                        Double VlHEPagar = parseDouble(String.valueOf(strCONVERHoraExtra1));

                        if (HoraExtraPerc > 0.00 && HoraExtraQtd > 0.00 && SalCad > 0.00 && HrBsMes > 0.00) {VlHEPagar = SalHora * HoraExtraPerc * HoraExtraQtd;
                        editText_CON_VERHoraExtra1.setText(format("%.2f",VlHEPagar).replace(",", "."));}
                        else {editText_CON_VERHoraExtra1.setText(String.valueOf(0.00));}
//*** Cálculo de Adicional Noturno ***
                        String strCONPERCAdicNoturno1 = (dfricci.parse(AdicNotPERC1).toString());
                        Double AdicNotPerc = parseDouble(String.valueOf(strCONPERCAdicNoturno1));

                        String strCONQTDHoraAdicNotu1 = (dfricci.parse(AdicNotQTD1).toString());
                        Double AdicNotQtd = parseDouble(String.valueOf(strCONQTDHoraAdicNotu1));

                        String strCONVERAdicNoturno1 = (dfricci.parse(AdicNotVal1).toString());
                        Double VlAdicNotPagar = parseDouble(String.valueOf(strCONVERAdicNoturno1));

                        if (AdicNotPerc > 0.00 && AdicNotQtd > 0.00 && SalCad > 0.00) {VlAdicNotPagar = SalHora * AdicNotPerc * AdicNotQtd;
                        editText_CON_VERAdicNoturno1.setText(format("%.2f",VlAdicNotPagar).replace(",", "."));}
                        else {editText_CON_VERAdicNoturno1.setText(String.valueOf(0.00));}
//*** Cálculo de DSR Sobre Horas Exra e Adicional Noturno ***
                        String strCONQTDDiasDSRUteis = (dfricci.parse(DSRQtdDiasUteis).toString());
                        Double DSRDiasUteis = parseDouble(String.valueOf(strCONQTDDiasDSRUteis));

                        String strCONQTDDiasDSRInuteis = (dfricci.parse(DSRQtdDiasInuteis).toString());
                        Double DSRDiasInuteis = parseDouble(String.valueOf(strCONQTDDiasDSRInuteis));

                        String strCONVERValorDSR = (dfricci.parse(DSRValor).toString());
                        Double ValorDSRPagar = parseDouble(String.valueOf(strCONVERValorDSR));

                        double BaseCalcDSR = VlHEPagar + VlAdicNotPagar;
                        if (BaseCalcDSR > 0.00 && DSRDiasUteis > 0.00 && DSRDiasInuteis > 0.00) {ValorDSRPagar = BaseCalcDSR / DSRDiasUteis * DSRDiasInuteis;
                        editText_CON_VERValorDSR.setText(format("%.2f",ValorDSRPagar).replace(",", "."));}
                        else {editText_CON_VERValorDSR.setText(String.valueOf(0.00));}
//*** Cálculo de Faltas/DSR em Quantidade de "DIAS" ***
                        String strCONQtdDiasFalta = (dfricci.parse(FaltasQtdDias1).toString());
                        Double FaltasQtdDias = parseDouble(String.valueOf(strCONQtdDiasFalta));

                        String strCONVERValorFalta = (dfricci.parse(FaltasValor1).toString());
                        Double FaltasValorDesc = parseDouble(String.valueOf(strCONVERValorFalta));

                        if (FaltasQtdDias > 0.00) {FaltasValorDesc = SalDiario * FaltasQtdDias;
                        editText_CON_VERValorFalta.setText(format("%.2f",FaltasValorDesc).replace(",", "."));}
                        else {editText_CON_VERValorFalta.setText(String.valueOf(0.00));}
//*** Cálculo de Atrasos em Quantidade de "HORAS" ***
                        String strCONQtdHorasAtraso = (dfricci.parse(FaltasQtdHoras1).toString());
                        Double AtrasosQtdHoras = parseDouble(String.valueOf(strCONQtdHorasAtraso));

                        String strCONVERValorAtraso = (dfricci.parse(FaltasQtdHorasValor).toString());
                        Double AtrasosValorDesc = parseDouble(String.valueOf(strCONVERValorAtraso));

                        if (AtrasosQtdHoras > 0.00) {AtrasosValorDesc = SalHora * AtrasosQtdHoras;//}
                        editText_CON_VERValorAtraso.setText(format("%.2f",AtrasosValorDesc).replace(",", "."));}
                        else {editText_CON_VERValorAtraso.setText(String.valueOf(0.00));}
//*** Cálculo da Previdência Privada "PGBL" ***
                        String strCONPERCPGBL = (dfricci.parse(PercPGBL1).toString());
                        Double PGBLPerc = parseDouble(String.valueOf(strCONPERCPGBL));

                        String strCONVERPGBL = (dfricci.parse(ValorPGBL1).toString());
                        Double PGBLValorDesc = parseDouble(String.valueOf(strCONVERPGBL));

                        if (PGBLPerc > 0.00) {PGBLValorDesc = SalCad * PGBLPerc;
                        editText_CON_VERPGBL.setText(format("%.2f",PGBLValorDesc).replace(",", "."));}
                        else {editText_CON_VERPGBL.setText(String.valueOf(0.00));}
//*** Cálculo da Previdência Privada "VGBL" ***
                        String strCONPERCVGBL = (dfricci.parse(PercVGBL1).toString());
                        Double VGBLPerc = parseDouble(String.valueOf(strCONPERCVGBL));

                        String strCONVERVGBL = (dfricci.parse(ValorVGBL1).toString());
                        Double VGBLValorDesc = parseDouble(String.valueOf(strCONVERVGBL));

                        if (VGBLPerc > 0.00) {VGBLValorDesc = SalCad * VGBLPerc;
                        editText_CON_VERVGBL.setText(format("%.2f",VGBLValorDesc).replace(",", "."));}
                        else {editText_CON_VERVGBL.setText(String.valueOf(0.00));}
//*** Pensão Alimentícia DESCONTO e Neutra P/dedução base de IRRF ***
                        String strCONVERPensaoDesconto = (dfricci.parse(PenDesc).toString());
                        Double PensaoDesconto = parseDouble(String.valueOf(strCONVERPensaoDesconto));

                        String strCONVERPensaoDedIRRF = (dfricci.parse(PenReducaoIRRF).toString());
                        Double PensaoDedIRRF = parseDouble(String.valueOf(strCONVERPensaoDedIRRF));

//*** Cálculo Adiantamento Quinzenal Desconto ***
                        String strCONPERCAdtoQuinz = (dfricci.parse(PercAdtoQuinz).toString());
                        Double AdtoQuinzPerc = parseDouble(String.valueOf(strCONPERCAdtoQuinz));

                        String strCONVERVlAdtoQuinz = (dfricci.parse(ValorAdtoQuinz).toString());
                        Double AdtoQDesc = parseDouble(String.valueOf(strCONVERVlAdtoQuinz));

                        if (AdtoQuinzPerc > 0.00) {AdtoQDesc = SalCad * AdtoQuinzPerc;
                        editText_CON_VERVlAdtoQuinz.setText(format("%.2f",AdtoQDesc).replace(",", "."));}
                        else {editText_CON_VERVlAdtoQuinz.setText(String.valueOf(0.00));}
//*** Cálculo Contribuição Sindical ***
                        String strCONCSindDias = (dfricci.parse(CsindQtdDias).toString());
                        Double CSQtdDias = parseDouble(String.valueOf(strCONCSindDias));

                        String strCONCSindValor = (dfricci.parse(CSindValorDesc).toString());
                        Double ConSindDesc = parseDouble(String.valueOf(strCONCSindValor));

                        if (CSQtdDias > 0.00) {ConSindDesc = SalDiario * CSQtdDias;
                            editText_CON_CSindValor.setText(format("%.2f",ConSindDesc).replace(",", "."));}
                        else {editText_CON_CSindValor.setText(String.valueOf(0.00));}
//Calculo da Contribuição Assistencial ***
                        String strCONCAPERC = (dfricci.parse(CAssistPerc1).toString());
                        Double ContAssistPerc = parseDouble(String.valueOf(strCONCAPERC));

                        String strCONCAVlFixo = (dfricci.parse(CAssistVlFixo1).toString());
                        Double ContAssistValFixo = parseDouble(String.valueOf(strCONCAVlFixo));

                        String strCONCAVlTeto = (dfricci.parse(CAssistVlTeto1).toString());
                        Double ContAssistValTeto = parseDouble(String.valueOf(strCONCAVlTeto));

                        String strCONCAVlDesc = (dfricci.parse(CAssistVlDesc1).toString());
                        Double ContAssistVD = parseDouble(String.valueOf(strCONCAVlDesc));
                        editText_CON_CAVlDesc.setText(format("%.2f",ContAssistVD).replace(",", "."));

                        double CASSIPERCVALOR = SalCad  * ContAssistPerc;

                        if (ContAssistPerc == 0.00) {ContAssistVD = ContAssistValFixo ;
                        editText_CON_CAVlDesc.setText(format("%.2f",ContAssistVD).replace(",", "."));}
                        else if (CASSIPERCVALOR >= ContAssistValTeto) {ContAssistVD = ContAssistValTeto;
                        editText_CON_CAVlDesc.setText(format("%.2f",ContAssistVD).replace(",", "."));}
                        else if (CASSIPERCVALOR < ContAssistValTeto) {ContAssistVD = CASSIPERCVALOR;
                        editText_CON_CAVlDesc.setText(format("%.2f",ContAssistVD).replace(",", "."));}
                        else {editText_CON_CAVlDesc.setText(String.valueOf(0.00));}
//*** Cálculo do Vale Refeição ***
                        String strCONPERCDescVR = (dfricci.parse(VRPerc1).toString());
                        Double VRPerc = parseDouble(String.valueOf(strCONPERCDescVR));

                        String strCONVLCredVR = (dfricci.parse(VRValorCred1).toString());
                        Double VRValCred = parseDouble(String.valueOf(strCONVLCredVR));

                        String strCONVERVlDescVR = (dfricci.parse(VRValorDesc1).toString());
                        Double ValorVRDesc = parseDouble(String.valueOf(strCONVERVlDescVR));

                        if (VRValCred > 0.00 && VRPerc > 0.00) {ValorVRDesc = VRValCred * VRPerc;//}
                        editText_CON_VERVlDescVR.setText(format("%.2f",ValorVRDesc).replace(",", "."));}
                        else {editText_CON_VERVlDescVR.setText(String.valueOf(0.00));}
//*** Cálculo do Vale Transporte ***
                        String strCONPERCDescVT = (dfricci.parse(VTPerc1).toString());
                        Double VTPerc = parseDouble(String.valueOf(strCONPERCDescVT));

                        String strCONVlCredVT = (dfricci.parse(VTValorCred1).toString());
                        Double VTValCred = parseDouble(String.valueOf(strCONVlCredVT));

                        String strCONVERVLDescVT = (dfricci.parse(VTValorDesc1).toString());
                        Double ValorVTDesc = parseDouble(String.valueOf(strCONVERVLDescVT));

                        double VTPERCSAL = SalCad  * VTPerc;

                        if (VTValCred > 0.00 && VTPerc > 0.00 && VTValCred <= VTPERCSAL ) {ValorVTDesc = VTValCred;
                            editText_CON_VERVLDescVT.setText(format("%.2f",ValorVTDesc).replace(",", "."));}
                        else {ValorVTDesc = VTPERCSAL;editText_CON_VERVLDescVT.setText(format("%.2f",ValorVTDesc).replace(",", "."));}
//*** Cálculo da Assistência Médica ***
                        String strCONQtdVidasAssMed = (dfricci.parse(AssMedQuantidadeVidas).toString());
                        Double AssMedQtdVidas = parseDouble(String.valueOf(strCONQtdVidasAssMed));

                        String strCONVlPVidasAssMed = (dfricci.parse(AssMedValoresVidas1).toString());
                        Double AssMedValorVidas = parseDouble(String.valueOf(strCONVlPVidasAssMed));

                        String strCONVlDescAssMed = (dfricci.parse(AssMedValoresDesconto).toString());
                        Double AssMedDescontar = parseDouble(String.valueOf(strCONVlDescAssMed));

                        if (AssMedQtdVidas > 0.00 && AssMedValorVidas > 0.00) {AssMedDescontar = AssMedQtdVidas * AssMedValorVidas;
                        editText_CON_VlDescAssMed.setText(format("%.2f",AssMedDescontar).replace(",", "."));}
                        else {editText_CON_VlDescAssMed.setText(String.valueOf(0.00));}
//*** Cálculo da Assistência Odontológica ***
                        String strCONtdVidasAssOdo = (dfricci.parse(AssOdoQuantidadeVidas).toString());
                        Double AssOdoQtdVidas = parseDouble(String.valueOf(strCONtdVidasAssOdo));

                        String strCONVlPVidasAssOdo = (dfricci.parse(AssOdoValoresVidas1).toString());
                        Double AssOdoValorVidas = parseDouble(String.valueOf(strCONVlPVidasAssOdo));

                        String strCONVlDescAssOdo = (dfricci.parse(AssOdoValoresDesconto).toString());
                        Double AssOdoDescontar = parseDouble(String.valueOf(strCONVlDescAssOdo));

                        if (AssOdoQtdVidas > 0.00 && AssOdoValorVidas > 0.00) {AssOdoDescontar = AssOdoQtdVidas * AssOdoValorVidas;
                        editText_CON_VlDescAssOdo.setText(format("%.2f",AssOdoDescontar).replace(",", "."));}
                        else {editText_CON_VlDescAssOdo.setText(String.valueOf(0.00));}
//*** Valores de Descontos "SEM" Incidências (FGTS, INSS, IRRF) ***
                        String strCONVlDesc01SI = (dfricci.parse(ValorDesc1SI).toString());
                        Double VlDesc1SI = parseDouble(String.valueOf(strCONVlDesc01SI));

                        String strCONVlDesc02SI = (dfricci.parse(ValorDesc2SI).toString());
                        Double VlDesc2SI = parseDouble(String.valueOf(strCONVlDesc02SI));

//** Calculo do INSS Desconto conforme Tabela **
                        String strNSSVALDESC = (dfricci.parse(DescontoINSS).toString());
                        Double INSSValorDescontar = parseDouble(String.valueOf(strNSSVALDESC));

                        String strINSSBASECALCULO = (dfricci.parse(BaseCalculoINSS).toString());
                        Double BASEINSS = parseDouble(String.valueOf(strINSSBASECALCULO));

                        int ValorCem = 100;

                        BASEINSS = SalMesHoraTrabalhado+VlVenc1TI+VlVenc2TI+VlHEPagar+VlAdicNotPagar+ValorDSRPagar-FaltasValorDesc-AtrasosValorDesc;

                        String INSSFAIXA002string = c.getString(c.getColumnIndex("TAB_FAIXA002INSS_ATE"));  //Busca valor na Tabela TabelaLegais campo TAB_FAIXA002INSS_ATE e grava na string INSSFAIXA002string
                        String INSSFAIXA002stringMoeda = (dfricci.parse(INSSFAIXA002string).toString());  //Pega valor gravado na string INSSFAIXA002string e CONVERTE para DECIMAL e grava na string INSSFAIXA002stringMoeda
                        Double INSSFAIXA002 = parseDouble(String.valueOf(INSSFAIXA002stringMoeda));  //Pega valor gravado na string INSSFAIXA002stringMoeda e CONVERTE para DOUBLE e grava na VARIÁVEL INSSFAIXA002

                        String INSSFAIXA003string = c.getString(c.getColumnIndex("TAB_FAIXA003INSS_DE"));
                        String INSSFAIXA003stringMoeda = (dfricci.parse(INSSFAIXA003string).toString());
                        Double INSSFAIXA003 = parseDouble(String.valueOf(INSSFAIXA003stringMoeda));

                        String INSSFAIXA004string = c.getString(c.getColumnIndex("TAB_FAIXA004INSS_ATE"));
                        String INSSFAIXA004stringMoeda = (dfricci.parse(INSSFAIXA004string).toString());
                        Double INSSFAIXA004 = parseDouble(String.valueOf(INSSFAIXA004stringMoeda));

                        String INSSFAIXA005string = c.getString(c.getColumnIndex("TAB_FAIXA005INSS_DE"));
                        String INSSFAIXA005stringMoeda = (dfricci.parse(INSSFAIXA005string).toString());
                        Double INSSFAIXA005 = parseDouble(String.valueOf(INSSFAIXA005stringMoeda));

                        String INSSFAIXA006string = c.getString(c.getColumnIndex("TAB_FAIXA006INSS_ATE"));
                        String INSSFAIXA006stringMoeda = (dfricci.parse(INSSFAIXA006string).toString());
                        Double INSSFAIXA006 = parseDouble(String.valueOf(INSSFAIXA006stringMoeda));

                        String TETOINSSstring = c.getString(c.getColumnIndex("TAB_TETOINSS"));
                        String TETOINSSMoeda = (dfricci.parse(TETOINSSstring).toString());
                        Double TETOINSS = parseDouble(String.valueOf(TETOINSSMoeda));

                        String INSSPERC001string = c.getString(c.getColumnIndex("TAB_PERC001INSS"));
                        String INSSPERC001Moeda = (dfricci.parse(INSSPERC001string).toString());
                        Double INSSPERC001 = parseDouble(String.valueOf(INSSPERC001Moeda));

                        String INSSPERC002string = c.getString(c.getColumnIndex("TAB_PERC002INSS"));
                        String INSSPERC002Moeda = (dfricci.parse(INSSPERC002string).toString());
                        Double INSSPERC002 = parseDouble(String.valueOf(INSSPERC002Moeda));

                        String INSSPERC003string = c.getString(c.getColumnIndex("TAB_PERC003INSS"));
                        String INSSPERC003Moeda = (dfricci.parse(INSSPERC003string).toString());
                        Double INSSPERC003 = parseDouble(String.valueOf(INSSPERC003Moeda));



                        if (BASEINSS <= INSSFAIXA002) {INSSValorDescontar = (BASEINSS * INSSPERC001) / ValorCem;
                            editINSSVALDESC.setText(format("%.2f",INSSValorDescontar).replace(",", "."));}
                        else if (BASEINSS >= INSSFAIXA003 && BASEINSS <= INSSFAIXA004) {INSSValorDescontar = (BASEINSS * INSSPERC002) / ValorCem;
                            editINSSVALDESC.setText(format("%.2f",INSSValorDescontar).replace(",", "."));}
                        else if (BASEINSS >= INSSFAIXA005 && BASEINSS <= INSSFAIXA006) {INSSValorDescontar = (BASEINSS * INSSPERC003) / ValorCem;
                            editINSSVALDESC.setText(format("%.2f",INSSValorDescontar).replace(",", "."));}
                        else {editINSSVALDESC.setText(String.valueOf(TETOINSS));}

                       Double INSSVALDEDIR = INSSValorDescontar;

                        editINSSBASECALCULO.setText(format("%.2f",BASEINSS).replace(",", "."));

                        //System.out.println("Base de INSS     "+BASEINSS);
                        //System.out.println("Valor Teto INSS  "+TETOINSS);
                        //System.out.println("Vl Ded.Bas.IRRF  "+INSSVALDEDIR);
//Calculo do Imposto de Renda Desconto conforme Tabela
                        String strIRRFVALDESC = (dfricci.parse(DescontoIRRF).toString());
                        Double IRRFVALDESC1 = parseDouble(String.valueOf(strNSSVALDESC));

                        String strIRRFBASECALCULO = (dfricci.parse(BaseCalculoIRRF1).toString());
                        Double BASEIRRF = parseDouble(String.valueOf(strINSSBASECALCULO));

                        String strCONQtdDepIRRF = (dfricci.parse(DepIRRFQtd).toString());
                        Double DepIRRF = parseDouble(String.valueOf(strCONQtdDepIRRF));
                        //editText_CON_QtdDepIRRF.setText(format("%.2f",DepIRRF).replace(",", "."));

                        String  IRRFFAIXA002string = c.getString(c.getColumnIndex("TAB_FAIXA002IRRF_ATE"));  //Busca valor na Tabela TabelaLegais campo TAB_FAIXA002IRRF_ATE e grava na string IRRFFAIXA002string
                        String IRRFFAIXA002Moeda = (dfricci.parse(IRRFFAIXA002string).toString());  //Pega valor gravado na string IRRFFAIXA002string e CONVERTE para DECIMAL e grava na string IRRFFAIXA002Moeda
                        Double IRRFFAIXA002 = parseDouble(String.valueOf(IRRFFAIXA002Moeda));  //Pega valor gravado na string IRRFFAIXA002Moeda e CONVERTE para DOUBLE e grava na VARIÁVEL IRRFFAIXA002

                        String IRRFFAIXA003string = c.getString(c.getColumnIndex("TAB_FAIXA003IRRF_DE"));
                        String IRRFFAIXA003Moeda = (dfricci.parse(IRRFFAIXA003string).toString());
                        Double IRRFFAIXA003 = parseDouble(String.valueOf(IRRFFAIXA003Moeda));

                        String  IRRFFAIXA004string = c.getString(c.getColumnIndex("TAB_FAIXA004IRRF_ATE"));
                        String IRRFFAIXA004Moeda = (dfricci.parse(IRRFFAIXA004string).toString());
                        Double IRRFFAIXA004 = parseDouble(String.valueOf(IRRFFAIXA004Moeda));

                        String  IRRFFAIXA005string = c.getString(c.getColumnIndex("TAB_FAIXA005IRRF_DE"));
                        String IRRFFAIXA005Moeda = (dfricci.parse(IRRFFAIXA005string).toString());
                        Double IRRFFAIXA005 = parseDouble(String.valueOf(IRRFFAIXA005Moeda));

                        String  IRRFFAIXA006string = c.getString(c.getColumnIndex("TAB_FAIXA006IRRF_ATE"));
                        String IRRFFAIXA006Moeda = (dfricci.parse(IRRFFAIXA006string).toString());
                        Double IRRFFAIXA006 = parseDouble(String.valueOf(IRRFFAIXA006Moeda));

                        String  IRRFFAIXA007string = c.getString(c.getColumnIndex("TAB_FAIXA007IRRF_DE"));
                        String IRRFFAIXA007Moeda = (dfricci.parse(IRRFFAIXA007string).toString());
                        Double IRRFFAIXA007 = parseDouble(String.valueOf(IRRFFAIXA007Moeda));

                        String  IRRFFAIXA008string = c.getString(c.getColumnIndex("TAB_FAIXA008IRRF_ATE"));
                        String IRRFFAIXA008Moeda = (dfricci.parse(IRRFFAIXA008string).toString());
                        Double IRRFFAIXA008 = parseDouble(String.valueOf(IRRFFAIXA008Moeda));

                        String  IRRFPERCFAIXA001string = c.getString(c.getColumnIndex("TAB_PERC001IRRF"));
                        String IRRFPERCFAIXA001Moeda = (dfricci.parse(IRRFPERCFAIXA001string).toString());
                        Double IRRFPERCFAIXA001 = parseDouble(String.valueOf(IRRFPERCFAIXA001Moeda));

                        String  IRRFPERCFAIXA002string = c.getString(c.getColumnIndex("TAB_PERC002IRRF"));
                        String IRRFPERCFAIXA002Moeda = (dfricci.parse(IRRFPERCFAIXA002string).toString());
                        Double IRRFPERCFAIXA002 = parseDouble(String.valueOf(IRRFPERCFAIXA002Moeda));

                        String  IRRFPERCFAIXA003string = c.getString(c.getColumnIndex("TAB_PERC003IRRF"));
                        String IRRFPERCFAIXA003Moeda = (dfricci.parse(IRRFPERCFAIXA003string).toString());
                        Double IRRFPERCFAIXA003 = parseDouble(String.valueOf(IRRFPERCFAIXA003Moeda));

                        String  IRRFPERCFAIXA004string = c.getString(c.getColumnIndex("TAB_PERC004IRRF"));
                        String IRRFPERCFAIXA004Moeda = (dfricci.parse(IRRFPERCFAIXA004string).toString());
                        Double IRRFPERCFAIXA004 = parseDouble(String.valueOf(IRRFPERCFAIXA004Moeda));

                        String  IRRFDEDUFAIXA001string = c.getString(c.getColumnIndex("TAB_VLDEDUCAO001IRRF"));
                        String IRRFDEDUFAIXA001Moeda = (dfricci.parse(IRRFDEDUFAIXA001string).toString());
                        Double IRRFDEDUFAIXA001 = parseDouble(String.valueOf(IRRFDEDUFAIXA001Moeda));

                        String  IRRFDEDUFAIXA002string = c.getString(c.getColumnIndex("TAB_VLDEDUCAO002IRRF"));
                        String IRRFDEDUFAIXA002Moeda = (dfricci.parse(IRRFDEDUFAIXA002string).toString());
                        Double IRRFDEDUFAIXA002 = parseDouble(String.valueOf(IRRFDEDUFAIXA002Moeda));

                        String  IRRFDEDUFAIXA003string = c.getString(c.getColumnIndex("TAB_VLDEDUCAO003IRRF"));
                        String IRRFDEDUFAIXA003Moeda = (dfricci.parse(IRRFDEDUFAIXA003string).toString());
                        Double IRRFDEDUFAIXA003 = parseDouble(String.valueOf(IRRFDEDUFAIXA003Moeda));

                        String  IRRFDEDUFAIXA004string = c.getString(c.getColumnIndex("TAB_VLDEDUCAO004IRRF"));
                        String IRRFDEDUFAIXA004Moeda = (dfricci.parse(IRRFDEDUFAIXA004string).toString());
                        Double IRRFDEDUFAIXA004 = parseDouble(String.valueOf(IRRFDEDUFAIXA004Moeda));

                        String  IRRFISENTOstring = c.getString(c.getColumnIndex("TAB_VALORISENTOIRRF"));
                        String IRRFISENTOMoeda = (dfricci.parse(IRRFISENTOstring).toString());
                        Double IRRFISENTO = parseDouble(String.valueOf(IRRFISENTOMoeda));

                        String  VLPORDEPstring = c.getString(c.getColumnIndex("TAB_VALORPORDEPIRRF"));
                        String VLPORDEPMoeda = (dfricci.parse(VLPORDEPstring).toString());
                        Double VLPORDEP = parseDouble(String.valueOf(VLPORDEPMoeda));


                        double  VlTotalDEP;
                        VlTotalDEP = DepIRRF * VLPORDEP;

                        BASEIRRF = SalMesHoraTrabalhado+VlVenc1TI+VlVenc2TI+VlHEPagar+VlAdicNotPagar+ValorDSRPagar-FaltasValorDesc-AtrasosValorDesc-INSSVALDEDIR-VlTotalDEP-PGBLValorDesc-PensaoDesconto-PensaoDedIRRF;

                        if (BASEIRRF <= IRRFFAIXA002) {IRRFVALDESC1 = BASEIRRF * 0;
                            editIRRFVALDESC.setText(format("%.2f",IRRFVALDESC1).replace(",", "."));}
                        else if (BASEIRRF >= IRRFFAIXA003 && BASEIRRF <= IRRFFAIXA004) {IRRFVALDESC1 = (BASEIRRF * IRRFPERCFAIXA001 / ValorCem) - IRRFDEDUFAIXA001;
                            editIRRFVALDESC.setText(format("%.2f",IRRFVALDESC1).replace(",", "."));}
                        else if (BASEIRRF >= IRRFFAIXA005 && BASEIRRF <= IRRFFAIXA006) {IRRFVALDESC1 = (BASEIRRF * IRRFPERCFAIXA002 / ValorCem) - IRRFDEDUFAIXA002;
                            editIRRFVALDESC.setText(format("%.2f",IRRFVALDESC1).replace(",", "."));}
                        else if (BASEIRRF >= IRRFFAIXA007 && BASEIRRF <= IRRFFAIXA008) {IRRFVALDESC1 = (BASEIRRF * IRRFPERCFAIXA003 / ValorCem) - IRRFDEDUFAIXA003;
                            editIRRFVALDESC.setText(format("%.2f",IRRFVALDESC1).replace(",", "."));}
                        else {IRRFVALDESC1 = (BASEIRRF * IRRFPERCFAIXA004 / ValorCem) - IRRFDEDUFAIXA004;
                            editIRRFVALDESC.setText(format("%.2f",IRRFVALDESC1).replace(",", "."));}

                        Double valorzeroIR = 0.00;
                        double editIRRFVALDESCMDez = IRRFVALDESC1;
                        if (editIRRFVALDESCMDez <= IRRFISENTO) {editIRRFVALDESC.setText(format("%.2f",valorzeroIR).replace(",", "."));}

                        editIRRFBASECALCULO.setText(format("%.2f",BASEIRRF).replace(",", "."));

//CALCULA O SALARIO FAMILIA CONFORME TABELA VIGENTE
                        String strSALFAMPAGAR = (dfricci.parse(SalFamiliaPagar).toString());
                        Double SalFamPagar = parseDouble(String.valueOf(strSALFAMPAGAR));

                        String strCONQtdDepSF = (dfricci.parse(DepSalFamQtd).toString());
                        Double QTDSF = parseDouble(String.valueOf(strCONQtdDepSF));

                        String SALFAMFAIXA002string = c.getString(c.getColumnIndex("TAB_FAIXA002SALFAM_ATE"));
                        String SALFAMFAIXA002Moeda = (dfricci.parse(SALFAMFAIXA002string).toString());
                        Double SALFAMFAIXA002 = parseDouble(String.valueOf(SALFAMFAIXA002Moeda));

                        String SALFAMFAIXA003string = c.getString(c.getColumnIndex("TAB_FAIXA003SALFAM_DE"));
                        String SALFAMFAIXA003Moeda = (dfricci.parse(SALFAMFAIXA003string).toString());
                        Double SALFAMFAIXA003 = parseDouble(String.valueOf(SALFAMFAIXA003Moeda));

                        String SALFAMFAIXA004string = c.getString(c.getColumnIndex("TAB_FAIXA004SALFAM_ATE"));
                        String SALFAMFAIXA004Moeda = (dfricci.parse(SALFAMFAIXA004string).toString());
                        Double SALFAMFAIXA004 = parseDouble(String.valueOf(SALFAMFAIXA004Moeda));

                        String SALFAMVAL001string = c.getString(c.getColumnIndex("TAB_VALORSALFAM001"));
                        String SALFAMVAL001Moeda = (dfricci.parse(SALFAMVAL001string).toString());
                        Double SALFAMVAL001 = parseDouble(String.valueOf(SALFAMVAL001Moeda));

                        String SALFAMVAL002string = c.getString(c.getColumnIndex("TAB_VALORSALFAM002"));
                        String SALFAMVAL002Moeda = (dfricci.parse(SALFAMVAL002string).toString());
                        Double SALFAMVAL002 = parseDouble(String.valueOf(SALFAMVAL002Moeda));


                        double BASESF = SalMesHoraTrabalhado+VlVenc1TI+VlVenc2TI+VlHEPagar+VlAdicNotPagar+ValorDSRPagar-FaltasValorDesc-AtrasosValorDesc;

                        Double valorzeroSF = 0.00;

                        if (BASESF <= SALFAMFAIXA002) {SalFamPagar = SALFAMVAL001 * QTDSF;
                            editSALFAMPAGAR.setText(format("%.2f",SalFamPagar).replace(",", "."));}
                        else if (BASESF >= SALFAMFAIXA003 && BASESF <= SALFAMFAIXA004) {SalFamPagar = SALFAMVAL002 * QTDSF;
                            editSALFAMPAGAR.setText(format("%.2f",SalFamPagar).replace(",", "."));}
                        else {editSALFAMPAGAR.setText(format("%.2f",valorzeroSF).replace(",", "."));}

                        editBASECALCSALFAM.setText(format("%.2f",BASESF).replace(",", "."));

                         System.out.println("Base de SF      "+BASESF);
                         System.out.println("Valor SF Pagarf  "+SalFamPagar);
                         System.out.println("Qtd Dep S.F      "+QTDSF);
//*** Cálculo do FGTS (Base, Percentual e Depósito) ***
                        String strCONPercFGTS = (dfricci.parse(PercentualFGTS).toString());
                        Double FGTSPerc = parseDouble(String.valueOf(strCONPercFGTS));

                        String strCONDepositoFGTS = (dfricci.parse(ValorDepositoFGTS).toString());
                        Double ValorDepFGTS = parseDouble(String.valueOf(strCONDepositoFGTS));

                        String strCONBaseFGTS = (dfricci.parse(BaseFGTS1).toString());
                        Double BASEFGTS = parseDouble(String.valueOf(strCONBaseFGTS));

                        Double valorzeroFGTS = 0.00;

                        BASEFGTS = SalMesHoraTrabalhado+VlVenc1TI+VlVenc2TI+VlHEPagar+VlAdicNotPagar+ValorDSRPagar-FaltasValorDesc-AtrasosValorDesc;

                        if (BASEFGTS > 0.00 && FGTSPerc > 0.00 ) {ValorDepFGTS = BASEFGTS * FGTSPerc;
                            editText_CON_DepositoFGTS.setText(format("%.2f",ValorDepFGTS).replace(",", "."));}
                        else {editText_CON_DepositoFGTS.setText(format("%.2f",valorzeroFGTS).replace(",", "."));}

                        editText_CON_BaseFGTS.setText(format("%.2f",BASEFGTS).replace(",", "."));

                        System.out.println("Base de FGTS     "+BASEFGTS);
                        System.out.println("Valor do FGTS    "+ValorDepFGTS);
//*** Total de Vencimentos, Descontos, Líquido ***
                        String strCONTotalVencimentos = (dfricci.parse(ValorTotalVencimentos).toString());
                        Double TotalVencimentos = parseDouble(String.valueOf(strCONTotalVencimentos));

                        String strCONTotalDescontos = (dfricci.parse(ValorTotalDescontos).toString());
                        Double TotalDescontos = parseDouble(String.valueOf(strCONTotalDescontos));

                        String strCONTotalLiquido = (dfricci.parse(ValorTotalLiquido).toString());
                        Double TotalLiquido = parseDouble(String.valueOf(strCONTotalLiquido));

                        TotalVencimentos = SalMesHoraTrabalhado+VlVenc1TI+VlVenc2TI+VlVenc3SI+VlVenc4SI+VlHEPagar+VlAdicNotPagar+ValorDSRPagar+SalFamPagar;
                        editText_CON_TotalVencimentos.setText(format("%.2f",TotalVencimentos).replace(",", "."));

                        TotalDescontos = FaltasValorDesc+AtrasosValorDesc+PGBLValorDesc+VGBLValorDesc+PensaoDesconto+AdtoQDesc+ConSindDesc+ContAssistVD+ValorVRDesc+ValorVTDesc+AssMedDescontar+AssOdoDescontar+VlDesc1SI+VlDesc2SI+INSSVALDEDIR+IRRFVALDESC1;
                        editText_CON_TotalDescontos.setText(format("%.2f",TotalDescontos).replace(",", "."));

                        TotalLiquido = TotalVencimentos - TotalDescontos;
                        editText_CON_TotalLiquido.setText(format("%.2f",TotalLiquido).replace(",", "."));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                else
                {
                    showMessage("Erro!", "ID Inválido");
                }


            }


        });//FIM DO BOTÃO BotaoCalculoAutomatico






        BotaoCON_LimparCampos.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editText_ID_Contratado.setText("");
                editText_CON_DssNome.setText("");
                editText_CON_CPF.setText("");
                editText_CON_DtdAdmissao.setText("");
                editText_CON_IDCargo.setText("");
                editText_CON_QtdDepIRRF.setText("0,00");
                editText_CON_QtdDepSF.setText("0,00");
                editText_CON_HorasBaseMes.setText("0,00");
                editText_CON_SalarioContratual.setText("0,00");
                editText_CON_SalarioHora.setText("0,00");
                editText_CON_SalarioDia.setText("0,00");
                editText_CON_DiasTrabalhados.setText("0,00");
                editText_CON_HorasTrabalhadas.setText("0,00");
                editText_CON_VERSalMesHora.setText("0,00");
                editSALFAMPAGAR.setText("0,00");
                editText_CON_PERCHoraExtra1.setText("0,00");
                editText_CON_QTDHoraExtra1.setText("0,00");
                editText_CON_VERHoraExtra1.setText("0,00");
                editText_CON_PERCAdicNoturno1.setText("0,00");
                editText_CON_QTDHoraAdicNotu1.setText("0,00");
                editText_CON_VERAdicNoturno1.setText("0,00");
                editText_CON_QTDDiasDSRUteis.setText("0,00");
                editText_CON_QTDDiasDSRInuteis.setText("0,00");
                editText_CON_VERValorDSR.setText("0,00");
                editText_CON_VERVL01TodasINC.setText("0,00");
                editText_CON_VERVL02TodasINC.setText("0,00");
                editText_CON_VERVL03SemINC.setText("0,00");
                editText_CON_VERVL04SemINC.setText("0,00");
                editText_CON_PERCPGBL.setText("0,00");
                editText_CON_VERPGBL.setText("0,00");
                editText_CON_PERCVGBL.setText("0,00");
                editText_CON_VERVGBL.setText("0,00");
                editText_CON_QtdDiasFalta.setText("0,00");
                editText_CON_VERValorFalta.setText("0,00");
                editText_CON_QtdHorasAtraso.setText("0,00");
                editText_CON_VERValorAtraso.setText("0,00");
                editText_CON_VERPensaoDedIRRF.setText("0,00");
                editText_CON_VERPensaoDesconto.setText("0,00");
                editText_CON_PERCAdtoQuinz.setText("0,00");
                editText_CON_VERVlAdtoQuinz.setText("0,00");
                editText_CON_CSindDias.setText("0,00");
                editText_CON_CSindValor.setText("0,00");
                editText_CON_CAVlFixo.setText("0,00");
                editText_CON_CAPERC.setText("0,00");
                editText_CON_CAVlTeto.setText("0,00");
                editText_CON_CAVlDesc.setText("0,00");
                editText_CON_PERCDescVT.setText("0,00");
                editText_CON_VlCredVT.setText("0,00");
                editText_CON_VERVLDescVT.setText("0,00");
                editText_CON_VlDesc01SI.setText("0,00");
                editText_CON_VlDesc02SI.setText("0,00");
                editText_CON_PERCDescVR.setText("0,00");
                editText_CON_VLCredVR.setText("0,00");
                editText_CON_VERVlDescVR.setText("0,00");
                editText_CON_QtdVidasAssMed.setText("0,00");
                editText_CON_VlPVidasAssMed.setText("0,00");
                editText_CON_VlDescAssMed.setText("0,00");
                editText_CON_QtdVidasAssOdo.setText("0,00");
                editText_CON_VlPVidasAssOdo.setText("0,00");
                editText_CON_VlDescAssOdo.setText("0,00");
                editINSSBASECALCULO.setText("0,00");
                editINSSVALDESC.setText("0,00");
                editIRRFBASECALCULO.setText("0,00");
                editIRRFVALDESC.setText("0,00");
                editText_CON_BaseFGTS.setText("0,00");
                editText_CON_DepositoFGTS.setText("0,00");
                editText_CON_TotalVencimentos.setText("0,00");
                editText_CON_TotalDescontos.setText("0,00");
                editText_CON_TotalLiquido.setText("0,00");
                editText_CON_PercFGTS.setText("0,00");
                editText_CON_IDGrauInstrucao.setText("");
                editText_CON_RGNumero.setText("");
                editText_CON_RGDtdEmissao.setText("");
                editText_CON_RGOrgaoEmissor.setText("");
                editText_CON_PISNumero.setText("");
                editText_CON_EnderecoResidencial.setText("");
                editText_CON_Email.setText("");
                editText_CON_IDCCusto.setText("");
                editText_CON_IDEstadosCivis.setText("");
                editText_CON_IDSexo.setText("");
                editText_CON_IDHorario.setText("");
                editText_CON_IDEncargo.setText("");
                editTextIDContratadoIncluir.setText("");



            }
        });//FIM DO BOTÃO BotaoCON_LimparCampos



        BotaoSalvarDownloads.setOnClickListener(new android.view.View.OnClickListener(){
            public void onClick(View view) {

                SQLiteDatabase db = helper.getWritableDatabase();
                Cursor SalvarTexto = db.rawQuery("SELECT * FROM Contratados", null);

                if (SalvarTexto.getCount() == 0) {
                    showMessage("Erro!!", "Nada Encontrado");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (SalvarTexto.moveToNext()) {

                    buffer.append( SalvarTexto.getString(0) + ";" +SalvarTexto.getString(1) + ";" +SalvarTexto.getString(2) + "\n");


                    File sdcard = Environment.getExternalStorageDirectory();
                    File dirRicci = new File(sdcard.getAbsolutePath() + "/RicciArquivos/");
                    dirRicci.mkdir();
                    File fileRicci = new File(dirRicci, "AlperExcel.csv");
                    FileOutputStream FOPSRicci = null;
                    try {
                        FOPSRicci = new FileOutputStream(fileRicci);
                        FOPSRicci.write(buffer.toString().getBytes());
                        FOPSRicci.close();

                        System.out.println("Diretorio Onde Arquivo pessoas.txt foi SALVO:   "+ getFilesDir());

                    } catch (IOException e) {
                        e.printStackTrace();

                        Toast.makeText(Contratados.this, "ERRO Arquivo Texto NÃO Foi Salvo!!", Toast.LENGTH_LONG).show();
                    }

                }

            }//FIM public void onClick
        });//FIM DO BOTÃO BotaoSalvarDownloads



//BOTÃO SALVAR DADOS CONTRATADOS EM ARQUIVO TEXTO (*.TXT) NO DIRETÓRIO DO PROJETO (/data/user/0/ricciandroid.com.br.zeusgeniusfp/files) COM NOME "pessoas.txt"
//============================================================================================================================================================
        BotaoSalvarTexto.setOnClickListener(new android.view.View.OnClickListener(){
            public void onClick(View view) {

                SQLiteDatabase db = helper.getWritableDatabase();
                Cursor SalvarTexto = db.rawQuery("SELECT * FROM Contratados", null);

                if (SalvarTexto.getCount() == 0) {
                    showMessage("Erro!!", "Nada Encontrado");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (SalvarTexto.moveToNext()) {

                    buffer.append( SalvarTexto.getString(0) + ";" +SalvarTexto.getString(1) + ";" +SalvarTexto.getString(2) + "\n");

                    String filename = "pessoas.txt";
                    String strToSave = buffer.toString();
                    FileOutputStream outputStream;
                    //Toast.makeText(Contratados.this, "Salvo Arquivo Texto com Sucesso!!" + getFilesDir(), Toast.LENGTH_LONG).show();
                    //System.out.println("Diretorio Onde Arquivo pessoas.txt foi SALVO:   "+ getFilesDir());

                    //ESTE COMANDO COMPARTILHA O CONTEUDO DO ARQUIVO (Gmail, WhatsApp, Google Drive, SMS, etc..)
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, strToSave);
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);


            /*
Intent sendIntent = new Intent();
sendIntent.setAction(Intent.ACTION_SEND);
String texto = "Olá sou um texto compartilhado";
sendIntent.putExtra(Intent.EXTRA_TEXT, texto);
sendIntent.setType("text/plain");
startActivity(sendIntent);
*/


                    try {
                        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                        outputStream.write(strToSave.getBytes());
                        outputStream.close();

                    } catch (Exception e) {
                        Log.e("SAVE_FILE", e.getMessage());
                        Toast.makeText(Contratados.this, "ERRO Não Foi Salvo Arquivo Texto!!", Toast.LENGTH_LONG).show();
                    }


                }
                //showMessage("Dados Arquivo", buffer.toString());
                //System.out.println("Dados Arquivo pessoas.txt     "+buffer);
                //editText_CON_DssNome.setText(buffer);

            }//FIM public void onClick
        });//FIM DO BOTÃO BotaoSalvarTexto







//BOTÃO VER LISTA DE ID TABELA
//-----------------------------
            BotaoCON_Lista.setOnClickListener(new android.view.View.OnClickListener(){
                public void onClick(View view) {

                    SQLiteDatabase db = helper.getWritableDatabase();

            Cursor c = db.rawQuery("SELECT * FROM Contratados", null);

            if (c.getCount() == 0) {
                showMessage("Erro!!", "Nada Encontrado");
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()) {
                buffer.append("ID: " + c.getString(0) + "\n" + "Funcionário: " + c.getString(1) + "\n");
            }
            showMessage("Nome do Funcionário", buffer.toString());

                }//FIM public void onClick
            });//FIM DO BOTÃO BotaoCON_Lista




        //BOTÃO VER LISTA DE CONTRATADOS PELA DESCRIÇÃO DO NOME
//-------------------------------------------------
        BotaoCONListaLike.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(View view) {


                SQLiteDatabase db = helper.getWritableDatabase();
                if (editText_CON_DssNome.getText().toString().trim().length() == 0) {
                    showMessage("Erro!!", "Digite o Texto no Campo NOME");
                    return;
                }

                Cursor c = db.rawQuery("SELECT * FROM Contratados WHERE CON_DssNome like '%" + editText_CON_DssNome.getText() + "%'", null);


                if (c.getCount() == 0) {
                    showMessage("Erro!!", "Nada Encontrado");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (c.moveToNext()) {
                    buffer.append("ID: " + c.getString(0) + "\n" + "Nome: " + c.getString(1) + "\n");
                }
                showMessage("Nome do Cargo", buffer.toString());

            }//FIM public void onClick
        });//FIM DO BOTÃO BotaoCONListaLike



//Esse BOTÃO busca o último ID da Tabela Contratados e soma +1 e grava no campo EditText
        btnCONultimoID.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(View view) {


                SQLiteDatabase db = helper.getWritableDatabase();

                String query = "SELECT MAX(id) AS ultimoIDtabela FROM Contratados";
                Cursor c = db.rawQuery(query, null);
                if (c.moveToFirst()) {
                    do {

                        String Valor1string = c.getString(c.getColumnIndex("ultimoIDtabela"));  //Grava o último ID da Tabela Usuários na String Valor1string
                        int Valor2Int = Integer.parseInt((String.valueOf(Valor1string).toString())); //Converte a String Valor1string para INTEIRO e grava na variável                   inteira Valor2Int
                        int Total = Valor2Int + 1; //Soma 1 no último ID da tabela e grava na variável inteira Total
                        editTextIDContratadoIncluir.setText(String.valueOf(Total)); //Imprime a soma no campo EditText ID Usuário

                        //System.out.println("Último ID Tabela: " + c.getString(c.getColumnIndex("ultimoIDtabela"))); //Imprime na console o último ID
                        //System.out.println("Último +1: " + Total); //Imprime na console o último somado com +1

                    } while (c.moveToNext());
                }

                c.close();

            }//FIM public void onClick
        });//FIM DO BOTÃO btnCONultimoID



        //Botão DELETAR usando "ID"
//-------------------------
        BotaoCON_Deletar.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(View view) {


                SQLiteDatabase db = helper.getWritableDatabase();

                if (editText_ID_Contratado.getText().toString().trim().length() == 0) {
                    showMessage("Erro!!", "Entre com o Nº ID");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM Contratados WHERE id='" + editText_ID_Contratado.getText() + "'", null);
                if (c.moveToFirst()) {
                    db.execSQL("DELETE FROM Contratados WHERE id='" + editText_ID_Contratado.getText() + "'");
                    showMessage("Sucesso!!", "Dados Deletados");
                }
                else
                {
                    showMessage("Erro!!", "Inválido, Informar ID para DELETAR");
                }

            }//FIM public void onClick
        });//FIM DO BOTÃO BotaoCON_Deletar







    }//FIM DA CLASE PROTECTED VOID





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






/*

        //Esse método efetua os cálculos de INSS, IRRF, SALARIO FAMÍLIA USANDO A TABELADO DO BANCO DE DADOS
//TabelaLegais buscando os campos onde constam as faixas, pois NÃO PRECISA que os valores das esteja
//num EditText da mesma classe java  **Criado em 08/02/2018**
    public void BotaoCalculoAutomatico(View View) {

        SQLiteDatabase db = helper.getWritableDatabase();

        if (editText_ID_Contratado.getText().toString().trim().length() == 0) {
            showMessage("Erro!!", "Favor Entrar com o Nº ID");
            return;
        }

        Cursor c = db.rawQuery("SELECT * FROM Contratados, TabelaLegais WHERE id='" + editText_ID_Contratado.getText() + "'", null);


        if (c.moveToFirst()) {
            editText_ID_Contratado.setText(c.getString(0));
            editText_CON_DssNome.setText(c.getString(1));
            editText_CON_CPF.setText(c.getString(2));
            editText_CON_DtdAdmissao.setText(c.getString(3));
            editText_CON_IDCargo.setText(c.getString(4));
            editText_CON_QtdDepIRRF.setText(c.getString(5));
            editText_CON_QtdDepSF.setText(c.getString(6));
            editText_CON_HorasBaseMes.setText(c.getString(7));
            editText_CON_SalarioContratual.setText(c.getString(8));
            editText_CON_SalarioHora.setText(c.getString(9));
            editText_CON_SalarioDia.setText(c.getString(10));
            editText_CON_DiasTrabalhados.setText(c.getString(11));
            editText_CON_HorasTrabalhadas.setText(c.getString(12));
            editText_CON_VERSalMesHora.setText(c.getString(13));
            editSALFAMPAGAR.setText(c.getString(14));
            editText_CON_PERCHoraExtra1.setText(c.getString(15));
            editText_CON_QTDHoraExtra1.setText(c.getString(16));
            editText_CON_VERHoraExtra1.setText(c.getString(17));
            editText_CON_PERCAdicNoturno1.setText(c.getString(18));
            editText_CON_QTDHoraAdicNotu1.setText(c.getString(19));
            editText_CON_VERAdicNoturno1.setText(c.getString(20));
            editText_CON_QTDDiasDSRUteis.setText(c.getString(21));
            editText_CON_QTDDiasDSRInuteis.setText(c.getString(22));
            editText_CON_VERValorDSR.setText(c.getString(23));
            editText_CON_VERVL01TodasINC.setText(c.getString(24));
            editText_CON_VERVL02TodasINC.setText(c.getString(25));
            editText_CON_VERVL03SemINC.setText(c.getString(26));
            editText_CON_VERVL04SemINC.setText(c.getString(27));
            editText_CON_PERCPGBL.setText(c.getString(28));
            editText_CON_VERPGBL.setText(c.getString(29));
            editText_CON_PERCVGBL.setText(c.getString(30));
            editText_CON_VERVGBL.setText(c.getString(31));
            editText_CON_QtdDiasFalta.setText(c.getString(32));
            editText_CON_VERValorFalta.setText(c.getString(33));
            editText_CON_QtdHorasAtraso.setText(c.getString(34));
            editText_CON_VERValorAtraso.setText(c.getString(35));
            editText_CON_VERPensaoDedIRRF.setText(c.getString(36));
            editText_CON_VERPensaoDesconto.setText(c.getString(37));
            editText_CON_PERCAdtoQuinz.setText(c.getString(38));
            editText_CON_VERVlAdtoQuinz.setText(c.getString(39));
            editText_CON_CSindDias.setText(c.getString(40));
            editText_CON_CSindValor.setText(c.getString(41));
            editText_CON_CAVlFixo.setText(c.getString(42));
            editText_CON_CAPERC.setText(c.getString(43));
            editText_CON_CAVlTeto.setText(c.getString(44));
            editText_CON_CAVlDesc.setText(c.getString(45));
            editText_CON_PERCDescVT.setText(c.getString(46));
            editText_CON_VlCredVT.setText(c.getString(47));
            editText_CON_VERVLDescVT.setText(c.getString(48));
            editText_CON_VlDesc01SI.setText(c.getString(49));
            editText_CON_VlDesc02SI.setText(c.getString(50));
            editText_CON_PERCDescVR.setText(c.getString(51));
            editText_CON_VLCredVR.setText(c.getString(52));
            editText_CON_VERVlDescVR.setText(c.getString(53));
            editText_CON_QtdVidasAssMed.setText(c.getString(54));
            editText_CON_VlPVidasAssMed.setText(c.getString(55));
            editText_CON_VlDescAssMed.setText(c.getString(56));
            editText_CON_QtdVidasAssOdo.setText(c.getString(57));
            editText_CON_VlPVidasAssOdo.setText(c.getString(58));
            editText_CON_VlDescAssOdo.setText(c.getString(59));
            editINSSBASECALCULO.setText(c.getString(60));
            editINSSVALDESC.setText(c.getString(61));
            editIRRFBASECALCULO.setText(c.getString(62));
            editIRRFVALDESC.setText(c.getString(63));
            editText_CON_BaseFGTS.setText(c.getString(64));
            editText_CON_DepositoFGTS.setText(c.getString(65));
            editText_CON_TotalVencimentos.setText(c.getString(66));
            editText_CON_TotalDescontos.setText(c.getString(67));
            editText_CON_TotalLiquido.setText(c.getString(68));
            editText_CON_PercFGTS.setText(c.getString(69));
            editText_CON_IDGrauInstrucao.setText(c.getString(70));
            editText_CON_RGNumero.setText(c.getString(71));
            editText_CON_RGDtdEmissao.setText(c.getString(72));
            editText_CON_RGOrgaoEmissor.setText(c.getString(73));
            editText_CON_PISNumero.setText(c.getString(74));
            editText_CON_EnderecoResidencial.setText(c.getString(75));
            editText_CON_Email.setText(c.getString(76));
            editText_CON_IDCCusto.setText(c.getString(77));
            editText_CON_IDEstadosCivis.setText(c.getString(78));
            editText_CON_IDSexo.setText(c.getString(79));
            editText_CON_IDHorario.setText(c.getString(80));
            editText_CON_IDEncargo.setText(c.getString(81));

            double Qtdias = 30.00;
//*** Salário Cadastral ***
            double vardoublesalario = Double.parseDouble(editText_CON_SalarioContratual.getText().toString());
            double SalCad = parseDouble(editText_CON_SalarioContratual.getText().toString());
            editText_CON_SalarioContratual.setText(format("%.2f", SalCad).replace(",", "."));
//*** Salário Hora ***
            double HrBsMes = Double.parseDouble(editText_CON_HorasBaseMes.getText().toString());
            double SalHora = SalCad / HrBsMes;
            //editText_CON_SalarioHora.setText(String.valueOf(SalHora));
            editText_CON_SalarioHora.setText(format("%.2f",SalHora).replace(",", "."));
//*** Salário Dia ***
            double SalDiario = SalCad / Qtdias;
            editText_CON_SalarioDia.setText(format("%.2f", SalDiario).replace(",", "."));
//*** Salário Mensal a Pagar ***
            double DiasTrabMes = Double.parseDouble(editText_CON_DiasTrabalhados.getText().toString());
            double HorasTrabMes = Double.parseDouble(editText_CON_HorasTrabalhadas.getText().toString());
            if (DiasTrabMes > 0.00 && HorasTrabMes == 0.00 && SalCad > 0.00) {editText_CON_VERSalMesHora.setText(String.valueOf(SalDiario * DiasTrabMes ));}
            else {editText_CON_VERSalMesHora.setText(String.valueOf(SalHora *  HorasTrabMes));}
            double SalMesHoraTrabalhado = Double.parseDouble(editText_CON_VERSalMesHora.getText().toString());
            editText_CON_VERSalMesHora.setText(format("%.2f",SalMesHoraTrabalhado).replace(",", "."));
//*** Valores de Vencimento "COM" Incidências (FGTS, INSS, IRRF) ***
            double VlVenc1TI = Double.parseDouble(editText_CON_VERVL01TodasINC.getText().toString());
            double VlVenc2TI = Double.parseDouble(editText_CON_VERVL02TodasINC.getText().toString());
//*** Valores de Vencimento "SEM" Incidências (FGTS, INSS, IRRF) ***
            double VlVenc3SI = Double.parseDouble(editText_CON_VERVL03SemINC.getText().toString());
            double VlVenc4SI = Double.parseDouble(editText_CON_VERVL04SemINC.getText().toString());
//*** Cálculo de Horas Extra ***
            double HoraExtraPerc = Double.parseDouble(editText_CON_PERCHoraExtra1.getText().toString());
            double HoraExtraQtd = Double.parseDouble(editText_CON_QTDHoraExtra1.getText().toString());
            if (HoraExtraPerc > 0.00 && HoraExtraQtd > 0.00 && SalCad > 0.00 && HrBsMes > 0.00) {editText_CON_VERHoraExtra1.setText(String.valueOf(SalHora * HoraExtraPerc * HoraExtraQtd));}
            else {editText_CON_VERHoraExtra1.setText(String.valueOf(0.00));}
            double VlHEPagar = Double.parseDouble(editText_CON_VERHoraExtra1.getText().toString());
            editText_CON_VERHoraExtra1.setText(format("%.2f",VlHEPagar).replace(",", "."));
//*** Cálculo de Adicional Noturno ***
            double AdicNotPerc = Double.parseDouble(editText_CON_PERCAdicNoturno1.getText().toString());
            double AdicNotQtd = Double.parseDouble(editText_CON_QTDHoraAdicNotu1.getText().toString());
            if (AdicNotPerc > 0.00 && AdicNotQtd > 0.00 && SalCad > 0.00) {editText_CON_VERAdicNoturno1.setText(String.valueOf(SalHora * AdicNotPerc * AdicNotQtd));}
            else {editText_CON_VERAdicNoturno1.setText(String.valueOf(0.00));}
            double VlAdicNotPagar = Double.parseDouble(editText_CON_VERAdicNoturno1.getText().toString());
            editText_CON_VERAdicNoturno1.setText(format("%.2f",VlAdicNotPagar).replace(",", "."));
//*** Cálculo de DSR Sobre Horas Exra e Adicional Noturno ***
            double DSRDiasUteis = Double.parseDouble(editText_CON_QTDDiasDSRUteis.getText().toString());
            double DSRDiasInuteis = Double.parseDouble(editText_CON_QTDDiasDSRInuteis.getText().toString());
            double BaseCalcDSR = VlHEPagar + VlAdicNotPagar;
            if (BaseCalcDSR > 0.00 && DSRDiasUteis > 0.00 && DSRDiasInuteis > 0.00) {editText_CON_VERValorDSR.setText(String.valueOf(BaseCalcDSR / DSRDiasUteis * DSRDiasInuteis));}
            else {editText_CON_VERValorDSR.setText(String.valueOf(0.00));}
            double ValorDSRPagar = Double.parseDouble(editText_CON_VERValorDSR.getText().toString());
            editText_CON_VERValorDSR.setText(format("%.2f",ValorDSRPagar).replace(",", "."));
//*** Cálculo de Faltas/DSR em Quantidade de "DIAS" ***
            double FaltasQtdDias = Double.parseDouble(editText_CON_QtdDiasFalta.getText().toString());
            if (FaltasQtdDias > 0.00) {editText_CON_VERValorFalta.setText(String.valueOf(SalDiario * FaltasQtdDias));}
            else {editText_CON_VERValorFalta.setText(String.valueOf(0.00));}
            double FaltasValorDesc = Double.parseDouble(editText_CON_VERValorFalta.getText().toString());
            editText_CON_VERValorFalta.setText(format("%.2f",FaltasValorDesc).replace(",", "."));
//*** Cálculo de Atrasos em Quantidade de "HORAS" ***
            double AtrasosQtdHoras = Double.parseDouble(editText_CON_QtdHorasAtraso.getText().toString());
            if (AtrasosQtdHoras > 0.00) {editText_CON_VERValorAtraso.setText(String.valueOf(SalHora * AtrasosQtdHoras));}
            else {editText_CON_VERValorAtraso.setText(String.valueOf(0.00));}
            double AtrasosValorDesc = Double.parseDouble(editText_CON_VERValorAtraso.getText().toString());
            editText_CON_VERValorAtraso.setText(format("%.2f",AtrasosValorDesc).replace(",", "."));
//*** Cálculo da Previdência Privada "PGBL" ***
            double PGBLPerc = Double.parseDouble(editText_CON_PERCPGBL.getText().toString());
            if (PGBLPerc > 0.00) {editText_CON_VERPGBL.setText(String.valueOf(SalCad * PGBLPerc));}
            else {editText_CON_VERPGBL.setText(String.valueOf(0.00));}
            double PGBLValorDesc = Double.parseDouble(editText_CON_VERPGBL.getText().toString());
            editText_CON_VERPGBL.setText(format("%.2f",PGBLValorDesc).replace(",", "."));
//*** Cálculo da Previdência Privada "VGBL" ***
            double VGBLPerc = Double.parseDouble(editText_CON_PERCVGBL.getText().toString());
            if (VGBLPerc > 0.00) {editText_CON_VERVGBL.setText(String.valueOf(SalCad * VGBLPerc));}
            else {editText_CON_VERVGBL.setText(String.valueOf(0.00));}
            double VGBLValorDesc = Double.parseDouble(editText_CON_VERVGBL.getText().toString());
            editText_CON_VERVGBL.setText(format("%.2f",VGBLValorDesc).replace(",", "."));
//*** Pensão Alimentícia DESCONTO e Neutra P/dedução base de IRRF ***
            double PensaoDesconto = Double.parseDouble(editText_CON_VERPensaoDesconto.getText().toString());
            double PensaoDedIRRF = Double.parseDouble(editText_CON_VERPensaoDedIRRF.getText().toString());
//*** Cálculo Adiantamento Quinzenal Desconto ***
            double AdtoQuinzPerc = Double.parseDouble(editText_CON_PERCAdtoQuinz.getText().toString());
            if (AdtoQuinzPerc > 0.00) {editText_CON_VERVlAdtoQuinz.setText(String.valueOf(SalCad * AdtoQuinzPerc));}
            else {editText_CON_VERVlAdtoQuinz.setText(String.valueOf(0.00));}
            double AdtoQDesc = Double.parseDouble(editText_CON_VERVlAdtoQuinz.getText().toString());
            editText_CON_VERVlAdtoQuinz.setText(format("%.2f",AdtoQDesc).replace(",", "."));
//*** Cálculo Contribuição Sindical ***
            double CSQtdDias = Double.parseDouble(editText_CON_CSindDias.getText().toString());
            if (CSQtdDias > 0.00) {editText_CON_CSindValor.setText(String.valueOf(SalDiario * CSQtdDias));}
            else {editText_CON_CSindValor.setText(String.valueOf(0.00));}
            double ConSindDesc = Double.parseDouble(editText_CON_CSindValor.getText().toString());
            editText_CON_CSindValor.setText(format("%.2f",ConSindDesc).replace(",", "."));
//Calculo da Contribuição Assistencial ***
            double ContAssistPerc = Double.parseDouble(editText_CON_CAPERC.getText().toString());
            double ContAssistValFixo = Double.parseDouble(editText_CON_CAVlFixo.getText().toString());
            double ContAssistValTeto = Double.parseDouble(editText_CON_CAVlTeto.getText().toString());
            double CASSIPERCVALOR = SalCad  * ContAssistPerc;
            if (ContAssistPerc == 0.00) {editText_CON_CAVlDesc.setText(String.valueOf(ContAssistValFixo ));}
            else if (CASSIPERCVALOR >= ContAssistValTeto) {editText_CON_CAVlDesc.setText(String.valueOf(ContAssistValTeto));}
            else if (CASSIPERCVALOR < ContAssistValTeto) {editText_CON_CAVlDesc.setText(String.valueOf(CASSIPERCVALOR));}
            else {editText_CON_CAVlDesc.setText(String.valueOf(0.00));}
            double ContAssistVD = Double.parseDouble(editText_CON_CAVlDesc.getText().toString());
            editText_CON_CAVlDesc.setText(format("%.2f",ContAssistVD).replace(",", "."));
//*** Cálculo do Vale Refeição ***
            double VRPerc = Double.parseDouble(editText_CON_PERCDescVR.getText().toString());
            double VRValCred = Double.parseDouble(editText_CON_VLCredVR.getText().toString());
            if (VRValCred > 0.00 && VRPerc > 0.00) {editText_CON_VERVlDescVR.setText(String.valueOf(VRValCred * VRPerc));}
            else {editText_CON_VERVlDescVR.setText(String.valueOf(0.00));}
            double ValorVRDesc = Double.parseDouble(editText_CON_VERVlDescVR.getText().toString());
            editText_CON_VERVlDescVR.setText(format("%.2f",ValorVRDesc).replace(",", "."));
//*** Cálculo do Vale Transporte ***
            double VTPerc = Double.parseDouble(editText_CON_PERCDescVT.getText().toString());
            double VTValCred = Double.parseDouble(editText_CON_VlCredVT.getText().toString());
            double VTPERCSAL = SalCad  * VTPerc;
            if (VTValCred > 0.00 && VTPerc > 0.00 && VTValCred <= VTPERCSAL ) {editText_CON_VERVLDescVT.setText(String.valueOf(VTValCred));}
            else {editText_CON_VERVLDescVT.setText(String.valueOf(VTPERCSAL));}
            double ValorVTDesc = Double.parseDouble(editText_CON_VERVLDescVT.getText().toString());
            editText_CON_VERVLDescVT.setText(format("%.2f",ValorVTDesc).replace(",", "."));
//*** Cálculo da Assistência Médica ***
            double AssMedQtdVidas = Double.parseDouble(editText_CON_QtdVidasAssMed.getText().toString());
            double AssMedValorVidas = Double.parseDouble(editText_CON_VlPVidasAssMed.getText().toString());
            if (AssMedQtdVidas > 0.00 && AssMedValorVidas > 0.00) {editText_CON_VlDescAssMed.setText(String.valueOf(AssMedQtdVidas * AssMedValorVidas));}
            else {editText_CON_VlDescAssMed.setText(String.valueOf(0.00));}
            double AssMedDescontar = Double.parseDouble(editText_CON_VlDescAssMed.getText().toString());
            editText_CON_VlDescAssMed.setText(format("%.2f",AssMedDescontar).replace(",", "."));
//*** Cálculo da Assistência Odontológica ***
            double AssOdoQtdVidas = Double.parseDouble(editText_CON_QtdVidasAssOdo.getText().toString());
            double AssOdoValorVidas = Double.parseDouble(editText_CON_VlPVidasAssOdo.getText().toString());
            if (AssOdoQtdVidas > 0.00 && AssOdoValorVidas > 0.00) {editText_CON_VlDescAssOdo.setText(String.valueOf(AssOdoQtdVidas * AssOdoValorVidas));}
            else {editText_CON_VlDescAssOdo.setText(String.valueOf(0.00));}
            double AssOdoDescontar = Double.parseDouble(editText_CON_VlDescAssOdo.getText().toString());
            editText_CON_VlDescAssOdo.setText(format("%.2f",AssOdoDescontar).replace(",", "."));
//*** Valores de Descontos "SEM" Incidências (FGTS, INSS, IRRF) ***
            double VlDesc1SI = Double.parseDouble(editText_CON_VlDesc01SI.getText().toString());
            double VlDesc2SI = Double.parseDouble(editText_CON_VlDesc02SI.getText().toString());


            //Calculo do INSS Desconto conforme Tabela
            double BASEINSS = SalMesHoraTrabalhado+VlVenc1TI+VlVenc2TI+VlHEPagar+VlAdicNotPagar+ValorDSRPagar-FaltasValorDesc-AtrasosValorDesc;
            String INSSFAIXA002string = c.getString(c.getColumnIndex("TAB_FAIXA002INSS_ATE"));
            String INSSFAIXA003string = c.getString(c.getColumnIndex("TAB_FAIXA003INSS_DE"));
            String INSSFAIXA004string = c.getString(c.getColumnIndex("TAB_FAIXA004INSS_ATE"));
            String INSSFAIXA005string = c.getString(c.getColumnIndex("TAB_FAIXA005INSS_DE"));
            String INSSFAIXA006string = c.getString(c.getColumnIndex("TAB_FAIXA006INSS_ATE"));
            String TETOINSSstring = c.getString(c.getColumnIndex("TAB_TETOINSS"));
            String INSSPERC001string = c.getString(c.getColumnIndex("TAB_PERC001INSS"));
            String INSSPERC002string = c.getString(c.getColumnIndex("TAB_PERC002INSS"));
            String INSSPERC003string = c.getString(c.getColumnIndex("TAB_PERC003INSS"));

            double INSSFAIXA002 = parseDouble(String.valueOf(INSSFAIXA002string).toString());
            double INSSFAIXA003 = parseDouble(String.valueOf(INSSFAIXA003string).toString());
            double INSSFAIXA004 = parseDouble(String.valueOf(INSSFAIXA004string).toString());
            double INSSFAIXA005 = parseDouble(String.valueOf(INSSFAIXA005string).toString());
            double INSSFAIXA006 = parseDouble(String.valueOf(INSSFAIXA006string).toString());
            double TETOINSS = parseDouble(String.valueOf(TETOINSSstring).toString());
            double INSSPERC001 = parseDouble(String.valueOf(INSSPERC001string).toString());
            double INSSPERC002 = parseDouble(String.valueOf(INSSPERC002string).toString());
            double INSSPERC003 = parseDouble(String.valueOf(INSSPERC003string).toString());



            if (BASEINSS <= INSSFAIXA002) {editINSSVALDESC.setText(String.valueOf(BASEINSS * INSSPERC001 ));}
            else if (BASEINSS >= INSSFAIXA003 && BASEINSS <= INSSFAIXA004) {editINSSVALDESC.setText(String.valueOf(BASEINSS * INSSPERC002));}
            else if (BASEINSS >= INSSFAIXA005 && BASEINSS <= INSSFAIXA006) {editINSSVALDESC.setText(String.valueOf(BASEINSS * INSSPERC003));}
            else {editINSSVALDESC.setText(String.valueOf(TETOINSS));}

            double INSSVALDEDIR = parseDouble(editINSSVALDESC.getText().toString());
            editINSSVALDESC.setText(format("%.2f",INSSVALDEDIR).replace(",", "."));
            editINSSBASECALCULO.setText(format("%.2f",BASEINSS).replace(",", "."));

//Calculo do Imposto de Renda Desconto conforme Tabela


            String  IRRFFAIXA002string = c.getString(c.getColumnIndex("TAB_FAIXA002IRRF_ATE"));
            String  IRRFFAIXA003string = c.getString(c.getColumnIndex("TAB_FAIXA003IRRF_DE"));
            String  IRRFFAIXA004string = c.getString(c.getColumnIndex("TAB_FAIXA004IRRF_ATE"));
            String  IRRFFAIXA005string = c.getString(c.getColumnIndex("TAB_FAIXA005IRRF_DE"));
            String  IRRFFAIXA006string = c.getString(c.getColumnIndex("TAB_FAIXA006IRRF_ATE"));
            String  IRRFFAIXA007string = c.getString(c.getColumnIndex("TAB_FAIXA007IRRF_DE"));
            String  IRRFFAIXA008string = c.getString(c.getColumnIndex("TAB_FAIXA008IRRF_ATE"));
            String  IRRFPERCFAIXA001string = c.getString(c.getColumnIndex("TAB_PERC001IRRF"));
            String  IRRFPERCFAIXA002string = c.getString(c.getColumnIndex("TAB_PERC002IRRF"));
            String  IRRFPERCFAIXA003string = c.getString(c.getColumnIndex("TAB_PERC003IRRF"));
            String  IRRFPERCFAIXA004string = c.getString(c.getColumnIndex("TAB_PERC004IRRF"));
            String  IRRFDEDUFAIXA001string = c.getString(c.getColumnIndex("TAB_VLDEDUCAO001IRRF"));
            String  IRRFDEDUFAIXA002string = c.getString(c.getColumnIndex("TAB_VLDEDUCAO002IRRF"));
            String  IRRFDEDUFAIXA003string = c.getString(c.getColumnIndex("TAB_VLDEDUCAO003IRRF"));
            String  IRRFDEDUFAIXA004string = c.getString(c.getColumnIndex("TAB_VLDEDUCAO004IRRF"));
            String  IRRFISENTOstring = c.getString(c.getColumnIndex("TAB_VALORISENTOIRRF"));
            String  VLPORDEPstring = c.getString(c.getColumnIndex("TAB_VALORPORDEPIRRF"));
            String  DepIRRFstring = c.getString(c.getColumnIndex("CON_QtdDepIRRF"));


            double IRRFFAIXA002 = parseDouble(String.valueOf(IRRFFAIXA002string).toString());
            double IRRFFAIXA003 = parseDouble(String.valueOf(IRRFFAIXA003string).toString());
            double IRRFFAIXA004 = parseDouble(String.valueOf(IRRFFAIXA004string).toString());
            double IRRFFAIXA005 = parseDouble(String.valueOf(IRRFFAIXA005string).toString());
            double IRRFFAIXA006 = parseDouble(String.valueOf(IRRFFAIXA006string).toString());
            double IRRFFAIXA007 = parseDouble(String.valueOf(IRRFFAIXA007string).toString());
            double IRRFFAIXA008 = parseDouble(String.valueOf(IRRFFAIXA008string).toString());
            double IRRFPERCFAIXA001 = parseDouble(String.valueOf(IRRFPERCFAIXA001string).toString());
            double IRRFPERCFAIXA002 = parseDouble(String.valueOf(IRRFPERCFAIXA002string).toString());
            double IRRFPERCFAIXA003 = parseDouble(String.valueOf(IRRFPERCFAIXA003string).toString());
            double IRRFPERCFAIXA004 = parseDouble(String.valueOf(IRRFPERCFAIXA004string).toString());
            double IRRFDEDUFAIXA001 = parseDouble(String.valueOf(IRRFDEDUFAIXA001string).toString());
            double IRRFDEDUFAIXA002 = parseDouble(String.valueOf(IRRFDEDUFAIXA002string).toString());
            double IRRFDEDUFAIXA003 = parseDouble(String.valueOf(IRRFDEDUFAIXA003string).toString());
            double IRRFDEDUFAIXA004 = parseDouble(String.valueOf(IRRFDEDUFAIXA004string).toString());
            double IRRFISENTO = parseDouble(String.valueOf(IRRFISENTOstring).toString());
            double VLPORDEP = parseDouble(String.valueOf(VLPORDEPstring).toString());
            double DepIRRF = parseDouble(String.valueOf(DepIRRFstring).toString());

            double  BASEIRRF,VlTotalDEP;
            VlTotalDEP = DepIRRF * VLPORDEP;

            BASEIRRF = SalMesHoraTrabalhado+VlVenc1TI+VlVenc2TI+VlHEPagar+VlAdicNotPagar+ValorDSRPagar-FaltasValorDesc-AtrasosValorDesc-INSSVALDEDIR-VlTotalDEP-PGBLValorDesc-PensaoDesconto-PensaoDedIRRF;

            if (BASEIRRF <= IRRFFAIXA002) {editIRRFVALDESC.setText(String.valueOf(BASEIRRF * 0));}
            else if (BASEIRRF >= IRRFFAIXA003 && BASEIRRF <= IRRFFAIXA004) {editIRRFVALDESC.setText(String.valueOf(BASEIRRF * IRRFPERCFAIXA001 - IRRFDEDUFAIXA001));}
            else if (BASEIRRF >= IRRFFAIXA005 && BASEIRRF <= IRRFFAIXA006) {editIRRFVALDESC.setText(String.valueOf(BASEIRRF * IRRFPERCFAIXA002 - IRRFDEDUFAIXA002));}
            else if (BASEIRRF >= IRRFFAIXA007 && BASEIRRF <= IRRFFAIXA008) {editIRRFVALDESC.setText(String.valueOf(BASEIRRF * IRRFPERCFAIXA003 - IRRFDEDUFAIXA003));}
            else {editIRRFVALDESC.setText(String.valueOf(BASEIRRF * IRRFPERCFAIXA004 - IRRFDEDUFAIXA004));}

            double editIRRFVALDESCMDez = parseDouble(editIRRFVALDESC.getText().toString());
            if (editIRRFVALDESCMDez <= IRRFISENTO) {editIRRFVALDESC.setText(String.valueOf(0.00));}

            double IRRFVlDescontado = parseDouble(editIRRFVALDESC.getText().toString());
            editIRRFVALDESC.setText(format("%.2f",IRRFVlDescontado).replace(",", "."));
            editIRRFBASECALCULO.setText(format("%.2f",BASEIRRF).replace(",", "."));

//CALCULA O SALARIO FAMILIA CONFORME TABELA VIGENTE
            String SALFAMFAIXA002string = c.getString(c.getColumnIndex("TAB_FAIXA002SALFAM_ATE"));
            String SALFAMFAIXA003string = c.getString(c.getColumnIndex("TAB_FAIXA003SALFAM_DE"));
            String SALFAMFAIXA004string = c.getString(c.getColumnIndex("TAB_FAIXA004SALFAM_ATE"));
            String SALFAMVAL001string = c.getString(c.getColumnIndex("TAB_VALORSALFAM001"));
            String SALFAMVAL002string = c.getString(c.getColumnIndex("TAB_VALORSALFAM002"));
            String QTDSFstring = c.getString(c.getColumnIndex("CON_QtdDepSF"));

            double SALFAMFAIXA002 = parseDouble(String.valueOf(SALFAMFAIXA002string).toString());
            double SALFAMFAIXA003 = parseDouble(String.valueOf(SALFAMFAIXA003string).toString());
            double SALFAMFAIXA004 = parseDouble(String.valueOf(SALFAMFAIXA004string).toString());
            double SALFAMVAL001   = parseDouble(String.valueOf(SALFAMVAL001string).toString());
            double SALFAMVAL002   = parseDouble(String.valueOf(SALFAMVAL002string).toString());
            double QTDSF          = parseDouble(String.valueOf(QTDSFstring).toString());
            double BASESF = SalMesHoraTrabalhado+VlVenc1TI+VlVenc2TI+VlHEPagar+VlAdicNotPagar+ValorDSRPagar-FaltasValorDesc-AtrasosValorDesc;

            if (BASESF <= SALFAMFAIXA002) {editSALFAMPAGAR.setText(String.valueOf(SALFAMVAL001 * QTDSF));}
            else if (BASESF >= SALFAMFAIXA003 && BASESF <= SALFAMFAIXA004) {editSALFAMPAGAR.setText(String.valueOf(SALFAMVAL002 * QTDSF));}
            else {editSALFAMPAGAR.setText(String.valueOf(0.00));}

            double SalFamPagar = parseDouble(editSALFAMPAGAR.getText().toString());
            editSALFAMPAGAR.setText(format("%.2f",SalFamPagar).replace(",", "."));
            editBASECALCSALFAM.setText(format("%.2f",BASESF).replace(",", "."));
//*** Cálculo do FGTS (Base, Percentual e Depósito) ***
            double BASEFGTS;
            BASEFGTS = SalMesHoraTrabalhado+VlVenc1TI+VlVenc2TI+VlHEPagar+VlAdicNotPagar+ValorDSRPagar-FaltasValorDesc-AtrasosValorDesc;
            double FGTSPerc = Double.parseDouble(editText_CON_PercFGTS.getText().toString());
            if (BASEFGTS > 0.00 && FGTSPerc > 0.00 ) {editText_CON_DepositoFGTS.setText(String.valueOf(BASEFGTS * FGTSPerc));}
            else {editText_CON_DepositoFGTS.setText(String.valueOf(0.00));}
            double ValorDepFGTS = Double.parseDouble(editText_CON_DepositoFGTS.getText().toString());
            editText_CON_DepositoFGTS.setText(format("%.2f",ValorDepFGTS).replace(",", "."));
            editText_CON_BaseFGTS.setText(format("%.2f",BASEFGTS).replace(",", "."));
//*** Total de Vencimentos, Descontos, Líquido ***
            double TotalVencimentos;
            TotalVencimentos = SalMesHoraTrabalhado+VlVenc1TI+VlVenc2TI+VlVenc3SI+VlVenc4SI+VlHEPagar+VlAdicNotPagar+ValorDSRPagar+SalFamPagar;
            editText_CON_TotalVencimentos.setText(format("%.2f",TotalVencimentos).replace(",", "."));

            double TotalDescontos;
            TotalDescontos = FaltasValorDesc+AtrasosValorDesc+PGBLValorDesc+VGBLValorDesc+PensaoDesconto+AdtoQDesc+ConSindDesc+ContAssistVD+ValorVRDesc+ValorVTDesc+AssMedDescontar+AssOdoDescontar+VlDesc1SI+VlDesc2SI+INSSVALDEDIR+IRRFVlDescontado;
            editText_CON_TotalDescontos.setText(format("%.2f",TotalDescontos).replace(",", "."));

            double TotalLiquido;
            TotalLiquido = TotalVencimentos - TotalDescontos;
            editText_CON_TotalLiquido.setText(format("%.2f",TotalLiquido).replace(",", "."));






        }
        else
        {
            showMessage("Erro!", "ID Inválido");
            clearText();
        }





    }
*/




//ShowMessage
//-----------
    public void showMessage(String title, String message) {

        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



}


