package cliente;

import banco.Banco;
import conta.Conta;
import interfaces.ICliente;

public abstract class Cliente implements ICliente {
    private String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
