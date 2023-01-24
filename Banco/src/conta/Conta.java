package conta;

import interfaces.IConta;
import cliente.Cliente;

public abstract class Conta implements IConta {
    private static int numeroContaPadrao = 1000;
    private int agencia;
    private int numero;
    private String senha;
    private Cliente titular;
    protected double saldo;

    public Conta(String senha, Cliente titular) {
        numeroContaPadrao++;
        this.agencia = 913;
        this.numero = numeroContaPadrao;
        this.senha = senha;
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
    public boolean validaSenha(String senha) {
        return this.senha.equals(senha);
    }
}