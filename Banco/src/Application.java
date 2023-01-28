import banco.Banco;
import exceptions.ValidatorException;
import interfaces.ICliente;
import interfaces.IConta;
import util.GeraDadosIniciais;
import util.formata.FormataDouble;
import util.formata.FormataTexto;
import validator.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Application {
    static Scanner sc = new Scanner(System.in);
    static String respostasUsuario;
    static Banco banco = Banco.getInstance();


    public static void main(String[] args) {
        Application app = new Application();
        GeraDadosIniciais.getInstance().carregaDadosIniciais();

        System.out.println("====================================================");
        System.out.println("             Seja bem vindo(a) ao                   ");
        System.out.println("		            ADA BANK                        ");
        System.out.println("          Seu dinheiro, nossa renda!                ");
        System.out.println("====================================================\n");


        app.menuInicial();

    }

    private void menuInicial() {
        System.out.println("====================================================");
        System.out.println("             Seja bem vindo(a) ao                   ");
        System.out.println("		            ADA BANK                        ");
        System.out.println("          Seu dinheiro, nossa renda!                ");
        System.out.println("====================================================");
        System.out.println("");
        System.out.println("Digite a operação desejada:\n"
                + "1 - Acessar sua conta \n"
                + "2 - Abrir conta \n"
                + "3 - Sair");

        respostasUsuario = sc.nextLine();

        switch (respostasUsuario) {
            case "1":
                fazerLogin();
                break;
            case "2":
                abrirConta();
                menuInicial();
                break;
            case "3":
                sair();
                break;
            default:
                System.out.println("Operação inválida. Tente novamente.");
                menuInicial();
                break;
        }
    }

    private void fazerLogin() {
        System.out.println("==============    LOGIN   ================");

        System.out.println("Digite seu documento:");
        String login = sc.nextLine();
        try {
            banco.valida(new LoginValidator(), login);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
            menuInicial();
        }
        System.out.println("Digite sua senha:");
        String senha = sc.nextLine();
        try {
            banco.valida(new SenhaLoginValidator(), senha);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
            fazerLogin();
        }
        System.out.println();
        menuCliente(banco.getCliente(banco.getClienteLogin()));
    }

    private ICliente cadastrarPF(String tipoCliente) {

        System.out.println("Digite seu nome:");
        String nome = sc.nextLine();
        System.out.println("Digite seu CPF:");
        String cpf = sc.nextLine();
        System.out.println("Digite uma senha:");
        String senha = sc.nextLine();
        try {
            banco.valida(new NomeValidator(), nome);
            banco.valida(new CPFValidator(), cpf);
            banco.valida(new CriarLoginValidator(), cpf);
            banco.valida(new CriarSenhaValidator(), senha);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
            menuInicial();
        }
        return banco.registrarConta(nome, cpf, senha, tipoCliente);
    }

    private ICliente cadastrarPJ(String tipoCliente) {
        System.out.println("Digite sua razão social:");
        String nome = FormataTexto.upperfirstCase(sc.nextLine());
        System.out.println("Digite seu CNPJ:");
        String cnpj = sc.nextLine();
        System.out.println("Digite uma senha:");
        String senha = sc.nextLine();
        try {
            banco.valida(new NomeEmpresaValidator(), nome);
            banco.valida(new CNPJValidator(), cnpj);
            banco.valida(new CriarLoginValidator(), cnpj);
            banco.valida(new CriarSenhaValidator(), senha);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
            menuInicial();
        }

        return banco.registrarConta(nome, cnpj, senha, tipoCliente);
    }

    private void abrirConta() {
        System.out.println("==============   ABERTURA DE CONTA   ================");
        System.out.println(
                "Qual tipo de conta voce deseja criar:\n"
                        + "1 - Conta Pessoa Fisica \n"
                        + "2 - Conta Pessoa Juridica \n"
                        + "3 - Voltar ao Menu Inicial");

        respostasUsuario = sc.next();
        sc.nextLine();

        switch (respostasUsuario) {
            case "1":
                ICliente cliente = cadastrarPF(respostasUsuario);
                banco.abrirContaPessoaFisica(cliente);
                System.out.println("Conta criada com sucesso!");
                System.out.println("Voce sera redirecionado ao Menu Inicial!");
                menuInicial();
                break;
            case "2":
                ICliente clientePJ = cadastrarPJ(respostasUsuario);
                banco.abrirContaPessoaJuridica(clientePJ);
                System.out.println("Conta criada com sucesso!");
                System.out.println("Voce sera redirecionado ao Menu Inicial!");
                menuInicial();
                break;
            case "3":
                menuInicial();
                break;
            default:
                System.out.println("Operação inválida. Tente novamente.");
                abrirConta();
                break;
        }
    }

    private void menuCliente(ICliente cliente) {
        System.out.println("==============    MENU CLIENTE   ================");
        System.out.println("Seja bem vindo(a) " + cliente.getContasUsuario().get(0).getTitular().getNome());
        if (banco.getTipoPessoa(cliente.getContasUsuario().get(0).getNumero()).equals("PF")) {
            menuPF(cliente);
        } else if (banco.getTipoPessoa(cliente.getContasUsuario().get(0).getNumero()).equals("PJ")) {
            menuPJ(cliente);
        }
    }

    private void menuPF(ICliente cliente) {
        System.out.println("Qual conta voce deseja acessar:\n"
                + "1 - Conta Corrente \n"
                + "2 - Conta Investimento \n"
                + "3 - Conta Poupança \n"
                + "4 - Voltar");
        String opcaoCliente = sc.next();

        switch (opcaoCliente) {
            case "1":
                menuOperacoes(cliente.getContasUsuario().get(0));
                break;
            case "2":
                menuOperacoesInvestir(cliente.getContasUsuario().get(1));
                break;
            case "3":
                menuOperacoes(cliente.getContasUsuario().get(2));
                break;
            case "4":
                menuInicial();
                break;
            default:
                System.out.println("Operação inválida. Tente novamente.");
                menuPF(cliente);
                break;
        }
    }

    private void menuPJ(ICliente cliente) {
        System.out.println("Qual conta voce deseja acessar:\n"
                + "1 - Conta Corrente \n"
                + "2 - Conta Investimento \n"
                + "3 - Voltar");
        String opcaoCliente = sc.next();

        switch (opcaoCliente) {
            case "1":
                menuOperacoes(cliente.getContasUsuario().get(0));
                break;
            case "2":
                menuOperacoesInvestir(cliente.getContasUsuario().get(1));
                break;

            case "3":
                menuInicial();
                break;
            default:
                System.out.println("Operação inválida. Tente novamente.");
                menuPJ(cliente);
                break;
        }
    }

    private void menuOperacoes(IConta conta) {
        System.out.println("Qual operação você deseja realizar:\n"
                + "1 - Sacar \n"
                + "2 - Transferir \n"
                + "3 - Depositar \n"
                + "4 - Consultar Saldo\n"
                + "5 - Voltar");
        String opcaoCliente = sc.next();
        switch (opcaoCliente) {
            case "1":
                menuSacar(conta);
                menuOperacoes(conta);
                break;
            case "2":
                menuTransferir(conta);
                menuOperacoes(conta);
                break;
            case "3":
                menuDepositar(conta);
                menuOperacoes(conta);
                break;
            case "4":
                System.out.printf("Seu saldo atual é: R$ %.2f \n ", conta.getSaldo());
                menuOperacoes(conta);
                break;
            case "5":
                menuCliente(conta.getTitular());
                break;
            default:
                System.out.println("Operação inválida. Tente novamente.");
                menuOperacoes(conta);
                break;
        }
    }

    private void menuOperacoesInvestir(IConta conta) {
        System.out.println("Qual operação você deseja realizar:\n"
                + "1 - Sacar \n"
                + "2 - Transferir \n"
                + "3 - Depositar \n"
                + "4 - Investir \n"
                + "5 - Consultar Saldo\n"
                + "6 - Voltar");
        String opcaoCliente = sc.next();
        switch (opcaoCliente) {
            case "1":
                menuSacar(conta);
                menuOperacoesInvestir(conta);
                break;
            case "2":
                menuTransferir(conta);
                menuOperacoesInvestir(conta);
                break;
            case "3":
                menuDepositar(conta);
                menuOperacoesInvestir(conta);
                break;
            case "4":
                menuInvestir(conta);
                menuOperacoesInvestir(conta);
            case "5":
                System.out.printf("Seu saldo atual é: R$ %.2f \n ", conta.getSaldo());
                menuOperacoesInvestir(conta);
                break;
            case "6":
                menuCliente(conta.getTitular());
                break;
            default:
                System.out.println("Operação inválida. Tente novamente.");
                menuOperacoesInvestir(conta);
                break;
        }
    }

    private void menuSacar(IConta conta) {
        try {
            System.out.println("Qual valor voce deseja sacar?");
            String valor = sc.next();
            double valorDesejado = FormataDouble.validaDouble(valor);
            if (banco.sacar(conta, valorDesejado)) {
                System.out.println("Saque efetuado!");
            } else {
                System.out.println("Saldo insuficiente!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido! Tente novamente.");
            menuSacar(conta);
        }

    }

    private void menuTransferir(IConta conta) { // filtrar transferencia para mesma conta?
        try {
            System.out.println("Informe o número da conta de destino:");
            int contaDestino = sc.nextInt();
            if (banco.contemConta(contaDestino)) {
                System.out.println("Para qual tipo conta você deseja transferir?");
                if (banco.getTipoPessoa(contaDestino).equals("PF")) {
                    menuSubtipoPF(conta, contaDestino);
                } else if (banco.getTipoPessoa(contaDestino).equals("PJ")) {
                    menuSubtipoPJ(conta, contaDestino);
                }
            } else {
                System.out.println("Conta não encontrada. Tente novamente!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Número de conta inválida! Tente novamente.");
        }
    }

    private void menuDepositar(IConta conta) {
        try {
            System.out.println("Digite a quantia que você deseja depositar: ");
            String valor = sc.next();
            double valorDesejado = FormataDouble.validaDouble(valor);
            banco.depositar(conta, valorDesejado);
            System.out.println("Depósito efetuado!");
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido! Tente novamente.");
            menuDepositar(conta);
        }
    }

    private void menuInvestir(IConta conta) {
        try {
            System.out.println("Digite a quantia que você deseja investir: ");
            String valor = sc.next();
            double valorDesejado = FormataDouble.validaDouble(valor);
            banco.depositar(conta, valorDesejado);
            System.out.println("Investimento efetuado!");
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido! Tente novamente.");
            menuDepositar(conta);
        }
    }

    private void menuSubtipoPF(IConta contaOrigem, int numeroContaDestino) {
        String contaDesejada;
        System.out.println(
                "1 - Conta Corrente\n" +
                        "2 - Conta Investimento\n" +
                        "3 - Conta Poupança");
        contaDesejada = sc.next();

        switch (contaDesejada) {
            case "1":
            case "2":
            case "3":
                double valorDesejado = requisitarValorTransferencia();
                IConta contaDestino = banco.getSubTipoConta(numeroContaDestino, contaDesejada);
                if (banco.transferir(contaOrigem, valorDesejado, contaDestino)) {
                    System.out.println("Transferência realizada com sucesso!");
                } else {
                    System.out.println("Saldo insuficiente para transferência!");
                }
                menuCliente(contaOrigem.getTitular());
                break;
            default:
                menuSubtipoPF(contaOrigem, numeroContaDestino);
        }
    }

    private void menuSubtipoPJ(IConta contaOrigem, int numeroContaDestino) {
        String contaDesejada;
        System.out.println(
                "1 - Conta Corrente\n" +
                        "2 - Conta Investimento\n");
        contaDesejada = sc.next();

        switch (contaDesejada) {
            case "1":
            case "2":
                double valorDesejado = requisitarValorTransferencia();
                IConta contaDestino = banco.getSubTipoConta(numeroContaDestino, contaDesejada);
                if (banco.transferir(contaOrigem, valorDesejado, contaDestino)) {
                    System.out.println("Transferência realizada com sucesso!");
                } else {
                    System.out.println("Saldo insuficiente para transferência!");
                }
                menuCliente(contaOrigem.getTitular());
                break;
            default:
                menuSubtipoPF(contaOrigem, numeroContaDestino);
        }
    }

    private double requisitarValorTransferencia() {
        try {
            System.out.println("Digite o valor a ser transferido: ");
            String valor = sc.next();
            double valorDesejado = FormataDouble.validaDouble(valor);
            return valorDesejado;
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido! Tente novamente.");
            requisitarValorTransferencia();
        }
        return 0;
    }

    private void sair() {
        System.out.println("ADA BANK agradece sua preferencia.");
        System.out.println("Esperamos te ver em breve! o/");
        System.exit(0);
    }
}
