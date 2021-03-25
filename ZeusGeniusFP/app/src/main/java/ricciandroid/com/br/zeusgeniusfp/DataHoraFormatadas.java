package ricciandroid.com.br.zeusgeniusfp;

/**
 * Created by ericci on 01/05/2019.
 */
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DataHoraFormatadas {

    Calendar       DiaSem = Calendar.getInstance();
    Calendar DataHoje = Calendar.getInstance(); //Data da Máquina, Hoje
    Format DataDMA_HMS = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); //Data Normal Brasil DIA/MES/ANO + Horas, Minutos, Segundos
    Format DataDMA = new SimpleDateFormat("dd/MM/yyyy"); //Data Normal Brasil DIA/MES/ANO
    Format DataAMD = new SimpleDateFormat("yyyy/MM/dd"); //Data Invertida ANO/MES/DIA
    Format Horas_HMS = new SimpleDateFormat("HH:mm:ss"); //Horas Normais Horas/Minutos/Segundos

    int diasemana =DiaSem.get(Calendar.DAY_OF_WEEK);  //Dia da Semana NÚMERO

    int Hora = DiaSem.get(Calendar.HOUR);
    int Minuto = DiaSem.get(Calendar.MINUTE);
    int Segundo = DiaSem.get(Calendar.SECOND);

}
