package cliente;

import banco.Banco;
import conta.Conta;

public class Cliente {
    private String nome;
    private Conta conta;
    public Cliente(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public Conta getConta() {
        return conta;
    }

    public void abrirConta(Cliente novoCliente) {
        Banco.getInstance().cadastrarConta(novoCliente);
    }

    public void sacar() {

    }

    public void transferir() {
    }

    public void depositar() {

    }

    public void consultarSaldo() {

    }

    public void investir() {

    }
}
