package banco;

import conta.*;
import interfaces.IConta;
import cliente.Cliente;
import cliente.ClientePessoaFisica;
import cliente.ClientePessoaJuridica;

import java.util.HashMap;
import java.util.Scanner;

public class Banco {
    Scanner sc = new Scanner(System.in);

    private static final Banco AdaBank = new Banco();

    public static Banco getInstance() {
        return AdaBank;
    }

    private HashMap<String, Cliente> clientes;

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    protected void cadastrarCliente(String login, Cliente cliente) {
        clientes.put(login, cliente);
        // System.out.println("Conta: " + numeroConta + "cadastrada!");
    }

    public void cadastrarConta(Cliente cliente) {
        int numeroConta = (int) (1000 + (9999 - 1000 + 1) * Math.random());
        System.out.println("Você deseja criar: \nConta de Pessoa Física  (1) \nConta de Pessoa Jurídica? (2)");
        String requisicaoDoUsuario = sc.next();
        if (!requisicaoDoUsuario.equals("1") & !requisicaoDoUsuario.equals("2")) {
            System.out.println("Não entendemos sua requisição, tente novamente!");
            cadastrarConta(cliente);
        } else if (requisicaoDoUsuario.equals("1")) {
            System.out.println("Criação de Conta - Pessoa Física");
            System.out.println("Digite seu CPF");
            String cpf = sc.next();
            validarLoginCriacao(cpf);
            String senha = validarSenhaCriacao();
            ClientePessoaFisica PF = new ClientePessoaFisica(cliente.getNome(), cpf);
            Conta CCPF = new ContaCorrentePessoaFisica(numeroConta, senha, PF);
            Conta CIPF = new ContaInvestimentoPessoaFisica(numeroConta, senha, PF);
            Conta Poupanca = new ContaPoupanca(numeroConta, senha, PF);
            cadastrarCliente(cpf, cliente);
        } else {
            System.out.println("Criação de Conta - Pessoa Jurídica");
            System.out.println("Digite seu CNPJ");
            String cnpj = sc.next();
            validarLoginCriacao(cnpj);
            String senha = validarSenhaCriacao();
            ClientePessoaJuridica PJ = new ClientePessoaJuridica(cliente.getNome(), cnpj);
            Conta CCPJ = new ContaCorrentePessoaJuridica(numeroConta, senha, PJ);
            Conta CIPJ = new ContaInvestimentoPessoaJuridica(numeroConta, senha, PJ);
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

    public void validarLoginCriacao(String login) {
        if (Banco.getInstance().contemLogin(login)) { // CPF e CNPJ precisam ter seus formatos definidos
            System.out.println("CPF ou CNPJ já cadastrados. Tente novamente");
        } else {
            validarSenhaCriacao();
        }
    }

    public String validarSenhaCriacao() {
        System.out.println("Digite sua senha: ");
        String respostaSenha = sc.next();
        if (respostaSenha.isBlank() || respostaSenha.length() <= 8) {
            System.out.println("Sua senha deve ter, ao menos, oito caracteres. Por favor, tente novamente.");
            validarSenhaCriacao();
        }
        return respostaSenha;
    }
}