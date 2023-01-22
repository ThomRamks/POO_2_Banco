package conta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeraNumeroConta {
    List<String> numeroContas = new ArrayList<>();

    public String criaNumero() {
        Random geraNumero = new Random();
        String numero = Integer.toString(1 + geraNumero.nextInt(99999));
        String digito = Integer.toString(1 + geraNumero.nextInt(8));
        return numero + "-" + digito;
    }

    public boolean existeConta(String numeroConta) {
        for (String numero : numeroContas) {
            if (numeroConta.equals(numero)) {
                return true;
            }
        }
        return false;
    }

    public String getNumeroConta() {
        String numeroConta = "";
        do {
            numeroConta = criaNumero();
        } while(existeConta(numeroConta));
        return numeroConta;
    }
}
