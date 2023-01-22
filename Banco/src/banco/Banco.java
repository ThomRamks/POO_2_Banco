package banco;

import conta.*;
import interfaces.IConta;
import cliente.Cliente;
import cliente.ClientePessoaFisica;
import cliente.ClientePessoaJuridica;

import java.util.Map;
import java.util.Scanner;

public class Banco {

    private static final Banco AdaBank = new Banco();

    public static Banco getInstance() {
        return AdaBank;
    }

    private Map<String, Cliente> clientes;

    protected void cadastrarCliente(String login, Cliente cliente) {
        clientes.put(login, cliente);
        // System.out.println("Conta: " + numeroConta + "cadastrada!");
    }

    public void cadastrarConta(Cliente cliente) {
        int agencia = 913;
        int login = (int) (1000 + (9999 - 1000 + 1) * Math.random());
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
            ClientePessoaFisica PF = new ClientePessoaFisica(cliente.getNome(), cpf);
            Conta CCPF = new ContaCorrentePessoaFisica(agencia, login, PF);
            Conta CIPF = new ContaInvestimentoPessoaFisica(agencia, login, PF);
            Conta Poupanca = new ContaPoupanca(agencia, login, PF);
            cadastrarCliente(cpf, cliente);
        } else {
            System.out.println("Criação de Conta - Pessoa Jurídica");
            System.out.println("Digite seu CNPJ");
            String cnpj = sc.next();
            ClientePessoaJuridica PJ = new ClientePessoaJuridica(cliente.getNome(), cnpj);
            Conta CCPJ = new ContaCorrentePessoaJuridica(agencia, login, PJ);
            Conta CIPJ = new ContaInvestimentoPessoaJuridica(agencia, login, PJ);
            cadastrarCliente(cnpj, cliente);
        }
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

    public boolean contemLogin(String login) {
        boolean contem = false;
        if (clientes.containsKey(login)) {
            contem = true;
        }
        return contem;
    }

    public void validarSenha(String senha) {

    }
}