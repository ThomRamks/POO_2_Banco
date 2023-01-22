package conta;

import interfaces.IConta;
import pessoa.Pessoa;

public abstract class Conta implements IConta {

    private int agencia;
    private String numero;
    private Pessoa titular;
    protected double saldo;
    private GeraNumeroConta geraNumeroConta = new GeraNumeroConta() ;

    public Conta(Pessoa titular) {
        this.agencia = 1;
        this.numero = geraNumeroConta.getNumeroConta();
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }
    public int getAgencia() {
        return agencia;
    }

    public String getNumero() {
        return numero;
    }

    public Pessoa getTitular() {
        return titular;
    }
}
