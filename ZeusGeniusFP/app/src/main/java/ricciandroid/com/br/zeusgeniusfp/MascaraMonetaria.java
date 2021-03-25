package ricciandroid.com.br.zeusgeniusfp;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;

import static java.lang.Double.parseDouble;

/**
 * Created by ericci on 04/02/2018.
 */

public class MascaraMonetaria implements TextWatcher {

    final EditText campo;

    public MascaraMonetaria(EditText campo) {
        super();
        this.campo = campo;
    }

    private boolean isUpdating = false;
    // Pega a formatacao do sistema, se for brasil R$ se EUA US$
    //private NumberFormat nf = NumberFormat.getCurrencyInstance(); //Os números ficam com máscara da MOEDA do país
    //private NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")); //Os números ficam exemplo R$987.234,78
    private DecimalFormat nf = new DecimalFormat("###,##0.00"); //Os números ficam exemplo 987.234,78
   // private DecimalFormat nf = new DecimalFormat("0.00"); //Os números ficam exemplo 987234,78

    // Formato moeda especificando a localidade (Estados Unidos)
    //NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("en", "US"));//Os números ficam exemplo $987,234.78


   // private DecimalFormat nf = (DecimalFormat) new DecimalFormat("0.00").getCurrencyInstance(Locale.US);




   // String nf = NumberFormat.getCurrencyInstance(Locale.US).format("0.00");



    // public static String ricciformat(double num) {
    //DecimalFormatSymbols nfricci = DecimalFormatSymbols.getInstance();
      //  nfricci.setDecimalSeparator('.');
        //return new DecimalFormat("0.00", nfricci).format(num);
    //}

//https://stackoverflow.com/questions/5054132/how-to-change-the-decimal-separator-of-decimalformat-from-comma-to-dot-point



    @Override
    public void onTextChanged(CharSequence s, int start, int before, int after) {
        // Evita que o método seja executado varias vezes.
        // Se tirar ele entre em loop
        if (isUpdating) {
            isUpdating = false;
            return;
        }

        isUpdating = true;
        String str = s.toString();
        // Verifica se já existe a máscara no texto.
        //boolean hasMask = ((str.indexOf("R$") > -1 || str.indexOf("$") > -1) && (str.indexOf(".") > -1 || str.indexOf(",") > -1));
        boolean hasMask = ((str.indexOf("") > -1 || str.indexOf("") > -1) || (str.indexOf(".") <= -1 || str.indexOf(",") <= -1));


        // Verificamos se existe máscara
        if (hasMask) {
            // Retiramos a máscara.
            str = str.replaceAll("[R$]", "").replaceAll("[,]", "").replaceAll("[.]", "");


        }

        try {
            // Transformamos o número que está escrito no EditText em
            // monetário.
            str = nf.format(parseDouble(str) / 100);
            campo.setText(str);
            campo.setSelection(campo.getText().length());
        } catch (NumberFormatException e) {
            s = "";
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Não utilizado
    }


    @Override
    public void afterTextChanged(Editable s) {
        // Não utilizado
    }
}

