package banco;

import conta.*;
import interfaces.ICliente;
import interfaces.IConta;
import cliente.Cliente;
import cliente.ClientePessoaFisica;
import cliente.ClientePessoaJuridica;

import java.util.*;

public class Banco {


     Scanner sc = new Scanner(System.in);

    private static final Banco AdaBank = new Banco();

    //private HashMap<String, Cliente> clientes = new HashMap<>();
    //private HashMap<String, IConta> contas = new HashMap<>();
    private List<IConta> contas = new ArrayList<>();

    /*public HashMap<String, Cliente> getClientes() {
        return clientes;
    }*/

    public Banco() {
        carregaDadosIniciais();
    }

    /*public void listarContas(){
        for (IConta conta : contas.values()) {
            System.out.println("Tipo conta: " + conta.getTipoConta());
            System.out.println("Operação: " + conta.getOperacao());
            System.out.println("Titular: " + conta.getTitular().getNome());
            System.out.println("Documento: " + conta.getTitular().getDocumento());
            System.out.println("Agência: " + conta.getAgencia());
            System.out.println("Número: " + conta.getNumero());
            System.out.println("\n\t --- \n");
        }
    }*/

    public void abrirConta(ICliente cliente, IConta conta) {
        contas.add(conta);
    }

//    public void abrirConta(ICliente cliente, IConta conta) {
//        contas.put(cliente.getDocumento(), conta);
//    }



    /*public void cadastrarConta(Cliente cliente) {
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
    }*/

    public void listarContas() {
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

    /*public boolean contemLogin(String login) {
        //boolean contem = false;
        //if (clientes.containsKey(login)) {
            //contem = true;
        //}
        return contas.containsKey(login);
    }*/

    /*public boolean validarLoginCriacao(String login) {
        //if (Banco.getInstance().contemLogin(login)) { // CPF e CNPJ precisam ter seus formatos definidos
            //System.out.println("CPF ou CNPJ já cadastrados. Tente novamente");
        //} else {
            validarSenhaCriacao();
        //}
        return Banco.getInstance().contemLogin(login);
    }*/

    public boolean validarSenhaCriacao(String respostaSenha) {
        /*System.out.println("Digite sua senha: ");
        String respostaSenha = sc.next();
        if (respostaSenha.isBlank() || respostaSenha.length() < 8) {
            System.out.println("Sua senha deve ter, ao menos, oito caracteres. Por favor, tente novamente.");
            validarSenhaCriacao();
              return false;
        }*/
        return respostaSenha.isBlank() || respostaSenha.length() <= 8;
    }

    public void carregaDadosIniciais() {

        ClientePessoaFisica arthur = new ClientePessoaFisica("Arthur Laureano Silva", "578.179.380-16");
        ClientePessoaFisica bruno = new ClientePessoaFisica("Bruno Rafael Ribeiro Martins", "252.243.160-90");
        ClientePessoaFisica diego = new ClientePessoaFisica("Diego Ruescas", "236.966.140-25");
        ClientePessoaFisica noemi = new ClientePessoaFisica("Alice Noemi Jorge Dos Santos", "580.380.430-49");
        ClientePessoaFisica thomas = new ClientePessoaFisica("Thomas Ramiro", "305.378.440-82");

        ClientePessoaJuridica ada = new ClientePessoaJuridica("Ada Tecnologia e Educação S.A.", "24.861.255/0001-07");
        ClientePessoaJuridica sinqia = new ClientePessoaJuridica("SINQIA S.A", "04.065.791/0001-99");

        ContaCorrentePessoaFisica ccArthur = new ContaCorrentePessoaFisica("123456", arthur);
        ContaCorrentePessoaFisica ccBruno = new ContaCorrentePessoaFisica("123456", bruno);
        ContaCorrentePessoaFisica ccDiego = new ContaCorrentePessoaFisica("123456", diego);
        ContaCorrentePessoaFisica ccNoemi = new ContaCorrentePessoaFisica("123456", noemi);
        ContaCorrentePessoaFisica ccThomas = new ContaCorrentePessoaFisica("123456", thomas);

        ContaPoupanca cpArthur = new ContaPoupanca("123456", arthur);
        ContaPoupanca cpBruno = new ContaPoupanca("123456", bruno);
        ContaPoupanca cpDiego = new ContaPoupanca( "123456", diego);
        ContaPoupanca cpNoemi = new ContaPoupanca( "123456", noemi);
        ContaPoupanca cpThomas = new ContaPoupanca("123456", thomas);

        ContaInvestimentoPessoaFisica ciArthur = new ContaInvestimentoPessoaFisica("123456", arthur);
        ContaInvestimentoPessoaFisica ciBruno = new ContaInvestimentoPessoaFisica("123456", bruno);
        ContaInvestimentoPessoaFisica ciDiego = new ContaInvestimentoPessoaFisica("123456", diego);
        ContaInvestimentoPessoaFisica ciNoemi = new ContaInvestimentoPessoaFisica("123456", noemi);
        ContaInvestimentoPessoaFisica ciThomas = new ContaInvestimentoPessoaFisica("123456", thomas);

        ContaCorrentePessoaJuridica ccAda = new ContaCorrentePessoaJuridica("123456", ada);
        ContaCorrentePessoaJuridica ccSinqia = new ContaCorrentePessoaJuridica("123456",sinqia);

        ContaInvestimentoPessoaJuridica ciAda = new ContaInvestimentoPessoaJuridica("123456", ada);
        ContaInvestimentoPessoaJuridica ciSinqia = new ContaInvestimentoPessoaJuridica("123456", sinqia);

        abrirConta(arthur, ccArthur);
        abrirConta(bruno, ccBruno);
        abrirConta(diego, ccDiego);
        abrirConta(noemi, ccNoemi);
        abrirConta(thomas, ccThomas);

        abrirConta(arthur, cpArthur);
        abrirConta(bruno, cpBruno);
        abrirConta(diego, cpDiego);
        abrirConta(noemi,cpNoemi);
        abrirConta(thomas, cpThomas);

        abrirConta(arthur, ciArthur);
        abrirConta(bruno, ciBruno);
        abrirConta(diego, ciDiego);
        abrirConta(noemi, ciNoemi);
        abrirConta(thomas, ciThomas);

        abrirConta(ada, ccAda);
        abrirConta(sinqia, ccSinqia);

        abrirConta(ada, ciAda);
        abrirConta(sinqia, ciSinqia);
    }

    public static Banco getInstance() {
        return AdaBank;
    }

    public void menuCliente(Cliente cliente) {
        System.out.println("==============    MENU CLIENTE   ================");
        System.out.println("Seja bem vindo(a) " + cliente.getConta().getTitular());
        //fazer separacao por tipo de cliente/conta ????
        // qual tipo de conta vai acessar???
        System.out.println("Qual operação você deseja realizar:\n"
                + "1 - Sacar \n"
                + "2 - Transferir \n"
                + "3 - Depositar \n"
                + "4 - Investir \n"
                + "5 - Consultar saldo \n"
                + "6 - Sair");

        String opcaoCliente = sc.next();
        switch (opcaoCliente) {
            case "1":
                //sacar();
                break;
            case "2":
                //transferir();
                break;
            case "3":
                //depositar();

                break;
            case "4":
                //investir();
                break;
            case "5":
                //consultarSaldo();
                break;
            case "6":
                //sair();
                break;
            default:
                System.out.println("Operação inválida. Tente novamente.");
                menuCliente(cliente);
                break;
        }
    }
}