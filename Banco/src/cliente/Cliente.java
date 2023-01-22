package cliente;

import banco.Banco;

public class Cliente {
    private String nome;
    private String conta;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
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