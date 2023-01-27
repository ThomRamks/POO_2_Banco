package util.formata;

public class FormataDouble {
    public static double validaDouble(String valor){
        double valorDesejado = Double.parseDouble(valor.replace(',', '.'));
        return valorDesejado;
    }

}