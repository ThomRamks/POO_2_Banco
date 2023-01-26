import Classes_De_Validacao.Validacao_Senha;
import exceptions.InvalidPasswordException;
import exceptions.UserNotFoundException;
import banco.Banco;
import exceptions.ValidatorException;
import interfaces.ICliente;
import interfaces.IConta;
import util.GeraDadosIniciais;
import util.valida.ValidaDocumento;
import util.valida.ValidaTexto;
import util.valida.ValidaDouble;

import java.util.Scanner;


public class Application {
    static Scanner sc = new Scanner(System.in);
    static String respostasUsuario;
    static Banco banco = Banco.getInstance();
    static Validacao_Senha validatorSenha = Validacao_Senha.getInstance();
    static GeraDadosIniciais dadosIniciais = GeraDadosIniciais.getInstance();
    private static final Application app = new Application();
    public static Application getInstance(){
        return app;
    }

    public static void main(String[] args) {

        dadosIniciais.carregaDadosIniciais();
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

        respostasUsuario = sc.next();

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
        //validarRequisicao(respostasUsuario);
    }

    private void fazerLogin() {
        try {
            System.out.println("==============    LOGIN   ================");
            System.out.println("Digite seu documento:");
            String login = sc.next();
            login = ValidaDocumento.formataDocumento(login); // implementar a IValidator aqui tambem?
            if (banco.contemLogin(login)) {
                ICliente cliente = banco.getCliente(login);
                System.out.println("Digite sua senha:");
                String senha = sc.next();
                validatorSenha.valida(cliente,senha); // interface de validação
                menuCliente(cliente);
            } else{
                System.out.println("Usuário não encontrado!");
                fazerLogin();
            }
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
            fazerLogin();
        }
    }

    private ICliente cadastrarPF() {
        System.out.println("Qual seu nome?");
        String nome = sc.next();
        nome = ValidaTexto.upperfirstCase(nome);
        if (ValidaTexto.somenteLetras(nome)) {
            System.out.println("Digite seu CPF:");
            String cpf = sc.next();
            cpf = ValidaDocumento.formataCpf(cpf);
            if (ValidaDocumento.isCpf(cpf) & !banco.contemLogin(cpf)) {
                System.out.println("Digite uma senha:");
                String senha = sc.next();
                if (banco.cadastrarSenha(senha)) {
                    return banco.registrarConta(nome, cpf, senha);
                } else {
                    System.out.println("Sua senha deve conter, no mínimo, oito caracteres. Por favor, tente novamente.");
                    abrirConta();
                }
            } else {
                System.out.println("Documento inválido. Por favor, tente novamente.");
                abrirConta();
            }
        } else {
            System.out.println("Digite seu nome. Por favor, tente novamente.");
            abrirConta();
        }
        return null;
    }

    private ICliente cadastrarPJ() {
        System.out.println("Digite sua razão social:");
        String nome = sc.next();
        nome = ValidaTexto.upperfirstCase(nome);
        if (!nome.isBlank()) {
            System.out.println("Digite seu CNPJ:");
            String cnpj = sc.next();
            cnpj = ValidaDocumento.formataCnpj(cnpj);
            if (ValidaDocumento.isCNPJ(cnpj) & !banco.contemLogin(cnpj)) {
                System.out.println("Digite uma senha:");
                String senha = sc.next();
                if (banco.cadastrarSenha(senha)) {
                    return banco.registrarConta(nome, cnpj, senha);
                } else {
                    System.out.println("Sua senha deve conter, no mínimo, oito caracteres. Por favor, tente novamente.");
                    abrirConta();
                }
            } else {
                System.out.println("Documento inválido. Por favor, tente novamente.");
                abrirConta();
            }
        } else {
            System.out.println("Digite sua Razão Social. Por favor, tente novamente.");
            abrirConta();
        }
        return null;
    }


    private void abrirConta() {
        System.out.println("==============   ABERTURA DE CONTA   ================");
        System.out.println(
                "Qual tipo de conta voce deseja criar:\n"
                        + "1 - Conta Pessoa Fisica \n"
                        + "2 - Conta Pessoa Juridica \n"
                        + "3 - Voltar ao Menu Inicial");

        respostasUsuario = sc.next();

        switch (respostasUsuario) {
            case "1":
                ICliente cliente = cadastrarPF();
                banco.abrirContaPessoaFisica(cliente);
                System.out.println("Conta criada com sucesso!");
                System.out.println("Voce sera redirecionado ao Menu Inicial!");
                menuInicial();
                break;
            case "2":
                ICliente clientePJ = cadastrarPJ();
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

    public void menuCliente(ICliente cliente) {
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
            double valorDesejado = ValidaDouble.validaDouble(valor);
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

    private void menuTransferir(IConta conta) { // filtrar transferencia para mesma conta
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
    }

   private double requisitarValorTransferencia() {
        try {
            System.out.println("Digite o valor a ser transferido: ");
            String valor = sc.next();
            double valorDesejado = ValidaDouble.validaDouble(valor);
            return valorDesejado;
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido! Tente novamente.");
            requisitarValorTransferencia();
        }
        return 0;
    }
    
   private void menuDepositar(IConta conta) {
        try {
            System.out.println("Digite a quantia que você deseja depositar: ");
            String valor = sc.next();
            double valorDesejado = ValidaDouble.validaDouble(valor);
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
            double valorDesejado = ValidaDouble.validaDouble(valor);
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
                menuSubtipoPJ(contaOrigem, numeroContaDestino);
        }
    }

    private void sair() {
        System.out.println("ADA BANK agradece sua preferencia.");
        System.out.println("Esperamos de te ver em breve! o/");
        System.exit(0);
    }
}
