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

<<<<<<< HEAD:Banco/src/pessoa/Pessoa.java
    public void abrirConta(Pessoa novoCliente) {
//        Banco.getInstance().cadastrarConta(novoCliente);
=======
    public void abrirConta(Cliente novoCliente) {
        Banco.getInstance().cadastrarConta(novoCliente);
>>>>>>> a00f882b465452e38ec8ade7c7f76719f1cf53c7:Banco/src/cliente/Cliente.java
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