package conta;

import pessoa.Pessoa;

public abstract class Conta {
    private int agencia;
    private int numero;
    private Pessoa titular;
    protected double saldo;

    public Conta(int agencia, int numero, Pessoa titular) {
        this.agencia = agencia;
        this.numero = numero;
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }
}
