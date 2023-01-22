package banco;

import conta.*;
import interfaces.IConta;
import pessoa.Pessoa;
import pessoa.PessoaFisica;
import pessoa.PessoaJuridica;

import java.util.Map;
import java.util.Scanner;

public class Banco {

    private static final Banco AdaBank = new Banco();

    public static Banco getInstance() {
        return AdaBank;
    }

    private Map<Integer, Pessoa> clientes;

    protected void cadastrarCliente(Pessoa cliente, Integer numeroConta) {
        clientes.put(numeroConta, cliente);
        System.out.println("Conta: " + numeroConta + "cadastrada!");
    }

    /*public void cadastrarConta(Pessoa cliente) {
        int agencia = 913;
        int numeroConta = (int) (1000 + (9999 - 1000 + 1) * Math.random());
        Scanner sc = new Scanner(System.in);
        System.out.println("Você deseja criar: \nConta de Pessoa Física  (1) \nConta de Pessoa Jurídica? (2)");
        String requisicaoDoUsuario = sc.next();
        if (!requisicaoDoUsuario.equals("1") & !requisicaoDoUsuario.equals("2")) {
            System.out.println("Não entendemos sua requisição, tente novamente!");
            cadastrarConta(cliente);
        } else if (requisicaoDoUsuario.equals("1")) {
            System.out.println("Criação de Conta - Pessoa Física");
            System.out.println("Digite seu CPF");
            String cpf = sc.next();
            PessoaFisica PF = new PessoaFisica(cliente.getNome(), cpf);
            Conta CCPF = new ContaCorrentePessoaFisica(agencia, numeroConta, PF);
            Conta CIPF = new ContaInvestimentoPessoaFisica(agencia, numeroConta, PF);
            Conta Poupanca = new ContaPoupanca(agencia, numeroConta, PF);
            cadastrarCliente(cliente, numeroConta);
        } else {
            System.out.println("Criação de Conta - Pessoa Jurídica");
            System.out.println("Digite seu CNPJ");
            String cnpj = sc.next();
            PessoaJuridica PJ = new PessoaJuridica(cliente.getNome(), cnpj);
            Conta CCPJ = new ContaCorrentePessoaJuridica(agencia, numeroConta, PJ);
            Conta CIPJ = new ContaInvestimentoPessoaJuridica(agencia, numeroConta, PJ);
            cadastrarCliente(cliente, numeroConta);
        }
    }*/

    public void depositar(IConta conta, double valor) {
        conta.depositar(valor);
    }

    public boolean transferir(IConta contaOrigem, double valor, IConta contaDestino) {
        return contaOrigem.transferir(valor, contaDestino);
    }

    public void sacar(IConta conta, double valor) {
        conta.sacar(valor);
    }

    public void validarLogin(String cliente, String senha) {

    }


}