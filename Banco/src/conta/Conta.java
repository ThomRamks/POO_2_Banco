package conta;

import interfaces.IConta;
import cliente.Cliente;

public abstract class Conta implements IConta {

    private int agencia;
<<<<<<< HEAD
    private String numero;
    private Pessoa titular;
=======
    private int numero;
    private String senha;
    private Cliente titular;
>>>>>>> a00f882b465452e38ec8ade7c7f76719f1cf53c7
    protected double saldo;
    private GeraNumeroConta geraNumeroConta = new GeraNumeroConta() ;

<<<<<<< HEAD
    public Conta(Pessoa titular) {
        this.agencia = 1;
        this.numero = geraNumeroConta.getNumeroConta();
=======
    public Conta(int agencia, int numero, Cliente titular) {
        this.agencia = agencia;
        this.numero = numero;
>>>>>>> a00f882b465452e38ec8ade7c7f76719f1cf53c7
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
    public String getSenha(){return senha;}
    protected void setSenha(String novaSenha){
    }

    public Cliente getTitular() {
        return titular;
    }
}
