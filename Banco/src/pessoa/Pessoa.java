package pessoa;

import banco.Banco;

public class Pessoa {
    private String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void abrirConta(Pessoa novoCliente) {
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