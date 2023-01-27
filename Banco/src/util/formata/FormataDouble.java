package util.formata;

public class FormataDouble {
    public static double validaDouble(String valor){
        return Double.parseDouble(valor.replace(',', '.'));
    }

}
