package banco;

import conta.*;
import interfaces.ICliente;
import interfaces.IConta;
import interfaces.IContaInvestimento;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    private static final Banco AdaBank = new Banco();
    private List<IConta> contas = new ArrayList<>();
    private List<IConta> contasUsuario = new ArrayList<>();
    private int numeroDefault;


    public Banco() {
        numeroDefault = 1000;
    }
    public void abrirContaCorrentePessoaFisica(ICliente cliente){
        int numero = 0;
        getContasUsuario(cliente.getDocumento());
        if (contasUsuario.size() > 0) {
            numero = getNumeroConta(cliente.getDocumento());
        } else {
            numeroDefault++;
            numero = numeroDefault;
        }
        IConta conta = new ContaCorrentePessoaFisica(numero, cliente);
        contas.add(conta);
    }

    public void abrirContaCorrentePessoaJuridica(ICliente cliente){
        int numero = 0;
        getContasUsuario(cliente.getDocumento());
        if (contasUsuario.size() > 0) {
            numero = getNumeroConta(cliente.getDocumento());
        } else {
            numeroDefault++;
            numero = numeroDefault;
        }
        IConta conta = new ContaCorrentePessoaJuridica(numero, cliente);
        contas.add(conta);
    }

    public void abrirContaInvestimentoPessoaFisica(ICliente cliente){
        int numero = 0;
        getContasUsuario(cliente.getDocumento());
        if (contasUsuario.size() > 0) {
            numero = getNumeroConta(cliente.getDocumento());
        } else {
            numeroDefault++;
            numero = numeroDefault;
        }
        IConta conta = new ContaInvestimentoPessoaFisica(numero, cliente);
        contas.add(conta);
    }

    public void abrirContaInvestimentoPessoaJuridica(ICliente cliente){
        int numero = 0;
        getContasUsuario(cliente.getDocumento());
        if (contasUsuario.size() > 0) {
            numero = getNumeroConta(cliente.getDocumento());
        } else {
            numeroDefault++;
            numero = numeroDefault;
        }
        IConta conta = new ContaInvestimentoPessoaJuridica(numero, cliente);
        contas.add(conta);
    }

    public void abrirContaPoupanca(ICliente cliente){
        int numero = 0;
        getContasUsuario(cliente.getDocumento());
        if (contasUsuario.size() > 0) {
            numero = getNumeroConta(cliente.getDocumento());
        } else {
            numeroDefault++;
            numero = numeroDefault;
        }
        IConta conta = new ContaPoupanca(numero, cliente);
        contas.add(conta);
    }

    public void getContasUsuario(String documento) {
        contasUsuario.clear();
        for (IConta conta : contas) {
            if (conta.getTitular().getDocumento().equals(documento)){
                contasUsuario.add(conta);
            }
        }
    }

    public int getNumeroConta(String documento) {
        return contasUsuario.get(0).getNumero();
    }

    public ICliente getCliente(String documento) {
        getContasUsuario(documento);
        return contasUsuario.get(0).getTitular();
    }

    public void listarContasUsuario(String documento) {
        String nome = getCliente(documento).getNome();
        System.out.println("\n\t-- Conta " + nome + " --");
        contas.forEach(conta -> {
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