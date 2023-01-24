package cliente;

import banco.Banco;
import conta.Conta;
import interfaces.ICliente;
import interfaces.IConta;

import java.util.List;

public abstract class Cliente implements ICliente {
    private String nome;
    private String senha;


    public Cliente(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public boolean validaSenha(String senha) {
        return this.senha.equals(senha);
    }
}
