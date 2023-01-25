package banco;

import conta.*;
import interfaces.ICliente;
import interfaces.IConta;
import interfaces.IContaInvestimento;

import java.util.*;

public class Banco {

    private static final Banco AdaBank = new Banco();
    private HashMap<IConta, ICliente> contasNoBanco = new HashMap<>();
    private int numeroDefault;

    public Banco() {
        numeroDefault = 1000;
    }

    public void abrirContaPessoaFisica(ICliente cliente) {
        int numero = 0;
        if (cliente.getContasUsuario().size() > 0) {
            numero = cliente.getContasUsuario().get(0).getNumero();
        } else {
            numeroDefault++;
            numero = numeroDefault;
        }
        IConta ccPessoaFisica = new ContaCorrentePessoaFisica(numero, cliente);
        IConta ciPessoaFisica = new ContaInvestimentoPessoaFisica(numero, cliente);
        IConta cpPessoaFisica = new ContaPoupanca(numero, cliente);
        Collections.addAll(cliente.getContasUsuario(), ccPessoaFisica, ciPessoaFisica, cpPessoaFisica);
        contasNoBanco.put(ccPessoaFisica, cliente);
        contasNoBanco.put(ciPessoaFisica, cliente);
        contasNoBanco.put(cpPessoaFisica, cliente);
    }

    public void abrirContaPessoaJuridica(ICliente cliente) {
        int numero = 0;
        if (cliente.getContasUsuario().size() > 0) {
            numero = cliente.getContasUsuario().get(0).getNumero();
        } else {
            numeroDefault++;
            numero = numeroDefault;
        }
        IConta ccPessoaJuridica = new ContaCorrentePessoaJuridica(numero, cliente);
        IConta ciPessoaJuridica = new ContaInvestimentoPessoaJuridica(numero, cliente);
        Collections.addAll(cliente.getContasUsuario(), ccPessoaJuridica, ciPessoaJuridica);
        contasNoBanco.put(ccPessoaJuridica, cliente);
        contasNoBanco.put(ciPessoaJuridica, cliente);
    }

    public boolean contemLogin(String login) {
        boolean contem = false;
        for (Map.Entry<IConta, ICliente> contaS : contasNoBanco.entrySet()) {
            if (contaS.getValue().getDocumento().equals(login)) {
                contem = true;
            }

        }
        return contem;
    }

    public boolean cadastrarSenha(String senha) {
        boolean tamanhoCorreto;
        if (senha.length() < 8 || senha.isBlank()) {
            tamanhoCorreto = false;
        } else {
            tamanhoCorreto = true;
        }
        return tamanhoCorreto;
    }

    public ICliente getCliente(String login) {
        ICliente cliente = null;
        for (Map.Entry<IConta, ICliente> contaS : contasNoBanco.entrySet()) {
            if (contaS.getValue().getDocumento().equals(login)) {
                cliente = contaS.getValue();
            }
        }
        return cliente;
    }

    public boolean checarSenha(ICliente cliente, String senha) {
        return cliente.validaSenha(senha);
    }

    public boolean contemConta(int numeroConta) {
        boolean contem = false;
        for (Map.Entry<IConta, ICliente> contaS : contasNoBanco.entrySet()) {
            if (contaS.getKey().getNumero() == numeroConta) {
                contem = true;
            }
        }
        return contem;
    }

    public String getTipoPessoa(int numeroConta) {
        String tipoPessoa = null;
        for (Map.Entry<IConta, ICliente> contaS : contasNoBanco.entrySet()) {
            if (contaS.getKey().getNumero() == numeroConta) {
                if (contaS.getValue().getDocumento().length() == 14) {
                    tipoPessoa = "PF";
                } else if (contaS.getValue().getDocumento().length() == 18){
                    tipoPessoa = "PJ";
                }
            }
        }
        return tipoPessoa;
    }

    public IConta getSubTipoConta(int numeroConta, int operacao) {
        IConta conta = null;
        for (Map.Entry<IConta, ICliente> contaS : contasNoBanco.entrySet()) {
            if(contaS.getKey().getNumero() == numeroConta && contaS.getKey().getOperacao() == operacao) {
                conta = contaS.getKey();
            }
        }
        return conta;
    }

    public void listarContasUsuario(String documento) {
        ICliente cliente = getCliente(documento);
        String nome = cliente.getNome();
        System.out.println("\n\t-- Conta " + nome + " --\n");
        cliente.getContasUsuario().forEach(conta -> {
            System.out.println("Tipo conta: " + conta.getTipoConta());
            System.out.println("Operação: " + conta.getOperacao());
            System.out.println("Titular: " + conta.getTitular().getNome());
            System.out.println("Documento: " + conta.getTitular().getDocumento());
            System.out.println("Agência: " + conta.getAgencia());
            System.out.println("Número: " + conta.getNumero());
            System.out.println("\n\t --- \n");
        });
    }

    public void depositar(IConta conta, double valor) {
        conta.depositar(valor);
    }

    public boolean transferir(IConta contaOrigem, double valor, IConta contaDestino) {
        return contaOrigem.transferir(valor, contaDestino);
    }

    public void sacar(IConta conta, double valor) {
        conta.sacar(valor);
    }

    public void investir(IContaInvestimento conta, double valor) {
        conta.investir(valor);
    }

    public static Banco getInstance() {
        return AdaBank;
    }

}
