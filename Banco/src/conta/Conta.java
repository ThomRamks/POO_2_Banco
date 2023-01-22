package conta;

import interfaces.IConta;
import cliente.Cliente;

public abstract class Conta implements IConta {
    private int agencia;
    private int numero;
    private Cliente titular;
    protected double saldo;

    public Conta(int agencia, int numero, Cliente titular) {
        this.agencia = agencia;
        this.numero = numero;
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }
    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getTitular() {
        return titular;
    }
}
