package conta;

import interfaces.IConta;
import cliente.Cliente;

public abstract class Conta implements IConta {
    private int agencia;
    private int numero;
    private String senha;
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
    public String getSenha(){return senha;}
    protected void setSenha(String novaSenha){
    }

    public Cliente getTitular() {
        return titular;
    }
}
