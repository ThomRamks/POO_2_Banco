package util.valida;

public class ValidaDouble {
    public static double validaDouble(String valor){
        double valorDesejado = Double.parseDouble(valor.replace(',', '.'));
        return valorDesejado;
    }

}
