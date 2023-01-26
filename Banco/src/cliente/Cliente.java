package cliente;


import interfaces.ICliente;
import interfaces.IConta;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente implements ICliente {
    private String nome;
    private String senha;
    private List<IConta> contasUsuario = new ArrayList<>();

    public Cliente(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public List<IConta> getContasUsuario() {
        return contasUsuario;
    }

    public boolean validaSenha(String senha) {
        return this.senha.equals(senha);
    }
}
