import banco.Banco;
import cliente.Cliente;
import cliente.ClientePessoaFisica;
import interfaces.ICliente;
import interfaces.IConta;
import util.GeraDadosIniciais;

import java.util.Scanner;


public class Application {
    static Scanner sc = new Scanner(System.in);
    static String respostasUsuario;
    static Banco banco = Banco.getInstance();


    public static void main(String[] args) {

        GeraDadosIniciais dadosIniciais = new GeraDadosIniciais();
        dadosIniciais.carregaDadosIniciais();
        Banco banco = Banco.getInstance();
    }

//        String documentoArthur = "578.179.380-16";
//        banco.listarContasUsuario(documentoArthur);
//
//        String documentoDiego = "123.456.789-10";
//        banco.listarContasUsuario(documentoDiego);
//
//        String documentoAda = "24.861.255/0001-07";
//        banco.listarContasUsuario(documentoAda);
//
//        String documentoSinqia = "04.065.791/0001-99";
//        banco.listarContasUsuario(documentoSinqia);

//        Application app = new Application();
//        Banco.getInstance();
//        app.menuUsuario();

//        PessoaFisica pessoaFisica = new PessoaFisica("Diego", "123.456.789-10");
//        PessoaJuridica pessoaJuridica = new PessoaJuridica("Ada", "12.455.455/0001-00");
//
//
//        ContaPoupanca contaPoupanca = new ContaPoupanca(123,45678, pessoaFisica);
//        ContaCorrentePessoaFisica ccPessoaFisica = new ContaCorrentePessoaFisica(321, 7898, pessoaFisica);
//        ContaCorrentePessoaJuridica ccPessoaJuridica = new ContaCorrentePessoaJuridica(456, 445588, pessoaJuridica);
//        ContaInvestimentoPessoaFisica ciPessoaFisica = new ContaInvestimentoPessoaFisica(321, 7898, pessoaFisica);
//        ContaInvestimentoPessoaJuridica ciPessoaJuridica = new ContaInvestimentoPessoaJuridica(456, 445588, pessoaJuridica);
//
//
//        Banco operacao = new Banco();
//        operacao.depositar(contaPoupanca, 100);
//        operacao.depositar(ccPessoaFisica, 100);
//        operacao.depositar(ccPessoaJuridica, 200);
//        operacao.depositar(ciPessoaFisica, 100);
//        operacao.depositar(ciPessoaJuridica, 200);
//
//        System.out.printf("Saldo Poupança: %.2f%n",contaPoupanca.getSaldo());
//        System.out.printf("Saldo Conta Corrente PF: %.2f%n",ccPessoaFisica.getSaldo());
//        System.out.printf("Saldo Conta Corrente PJ: %.2f%n",ccPessoaJuridica.getSaldo());
//        System.out.printf("Saldo da Conta de Investimento PF: %.2f%n", ciPessoaFisica.getSaldo());
//        System.out.printf("Saldo da Conta de Investimento PJ: %.2f%n", ciPessoaJuridica.getSaldo());
//
//        System.out.println();
//
//        boolean transferiu  = operacao.transferir(ccPessoaJuridica, 100, ccPessoaFisica);
//
//        if (transferiu) {
//            System.out.println("Transferência realizada com sucesso!");
//        } else {
//            System.out.println("Transferência não realizada!");
//        }
//
//        System.out.println();
//
//        System.out.printf("Saldo Poupança: %.2f%n",contaPoupanca.getSaldo());
//        System.out.printf("Saldo Conta Corrente PF: %.2f%n",ccPessoaFisica.getSaldo());
//        System.out.printf("Saldo Conta Corrente PJ: %.2f%n",ccPessoaJuridica.getSaldo());
//
//
//        operacao.sacar(ccPessoaFisica, 50);
//        System.out.printf("Saldo Conta Corrente PF: %.2f%n",ccPessoaFisica.getSaldo());
//        operacao.sacar(ccPessoaJuridica, 200);
//        System.out.printf("Saldo Conta Corrente PJ: %.2f%n",ccPessoaJuridica.getSaldo());


    public void menuInicial() {
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
        System.out.println("==============    LOGIN   ================");
        System.out.println("Digite seu documento:");
        String login = sc.next();
        if(banco.contemLogin(login)){
            ICliente cliente = banco.getCliente(login);
            System.out.println("Digite sua senha:");
            String senha = sc.next();
            if (banco.checarSenha(cliente, senha)){
                System.out.println("");
                menuCliente(cliente);
            } else {
                //criar exception InvalidPassword
            }
        } else {
            // criar exception UserNotFound
        }
    }

    public Cliente cadastrarPF(){
        System.out.println("Qual seu nome?");
        String nome = sc.next();
        //validacaonome
        System.out.println("Digite seu CPF:");
        String cpf = sc.next();
        //validacao cpf
        System.out.println("Digite uma senha:");
        String senha = sc.next();
        //validacao senha

        Cliente cliente = new ClientePessoaFisica(nome, senha, cpf);
        return cliente;
    }

    public Cliente cadastrarPJ(){
        System.out.println("Qual seu nome?");
        String nome = sc.next();
        //validacaonome
        System.out.println("Digite seu CNPJ:");
        String cnpj = sc.next();
        //validacao documento
        System.out.println("Digite uma senha:");
        String senha = sc.next();
        //validacao senha

        Cliente cliente = new ClientePessoaFisica(nome, senha, cnpj);
        return cliente;
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
                Cliente cliente = cadastrarPF();
                banco.abrirContaPessoaFisica(cliente);
                System.out.println("Conta criada com sucesso!");
                System.out.println("Voce sera redirecionado ao Menu Inicial!");
                menuInicial();
                break;
            case "2":
                Cliente clientePJ = cadastrarPJ();
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

    public void inputDocumento(){
        //validacao documento
        // fazer if/else
    }

    public void menuCliente(ICliente cliente) {
        System.out.println("==============    MENU CLIENTE   ================");
        System.out.println("Seja bem vindo(a) " + cliente.getContasUsuario().get(0).getTitular());

        if (banco.getTipoPessoa(cliente.getContasUsuario().get(0).getNumero()).equals("PF")){
            menuPF();
        } else if (banco.getTipoPessoa(cliente.getContasUsuario().get(0).getNumero()).equals("PJ")) {
            //menuPJ();
        }

        //fazer separacao por tipo de cliente/conta ????
        // qual tipo de conta vai acessar???
    }

    public void menuPF(){
        System.out.println("Qual conta voce deseja acessar:\n"
                + "1 - Conta Corrente \n"
                + "2 - Conta Poupança \n"
                + "3 - Conta Investimento \n"
                + "4 - Voltar");
        String opcaoCliente = sc.next();

        switch (opcaoCliente) {
            case "1":
                //menuOperacoes
                break;
            case "2":
                //ContaPoupanca
                break;
            case "3":
                //investimento;
                break;
            case "4":
                menuInicial();
                break;
            default:
                System.out.println("Operação inválida. Tente novamente.");
                menuPF();
                break;
        }
    }

    public void operacoes(IConta conta) {
        System.out.println("Qual operação você deseja realizar:\n"
                + "1 - Sacar \n"
                + "2 - Transferir \n"
                + "3 - Depositar \n"
                + "4 - Consultar saldo \n"
                + "5 - Sair");
        String opcaoCliente = sc.next();
        switch (opcaoCliente) {
            case "1":
                menuSacar(conta);
                break;
            case "2":
                menuTransferir(conta);
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
                //menuCliente();
                break;
        }
    }

    private void menuSacar(IConta conta) {
        System.out.println("Qual valor voce deseja sacar?");
        double valor = sc.nextDouble();
        banco.sacar(conta, valor);
    }

    public void menuTransferir(IConta conta){
        System.out.println("Para qual conta voce deseja transferir?");
        int contaDestino = sc.nextInt();
        if (banco.contemConta(contaDestino)) {
            System.out.println("Para qual tipo conta voce deseja transferir?");
            if (banco.getTipoPessoa(contaDestino).equals("PF")){
                //menu opcoes cc, ci, cp
            } else if (banco.getTipoPessoa(contaDestino).equals("PF")){
                //menu opcoes cc, ci
            }
        }

        System.out.println("Qual valor voce deseja transferir?");
        double valor = sc.nextDouble();
        banco.sacar(conta, valor);
    }

    /* public void operacoesInvestir(){
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
    }*/

    public void sair(){
        System.out.println("ADA BANK agradece sua preferencia.");
        System.out.println("Esperamos de te ver em breve! o/");
        System.exit(0);
    }


    /*public void validarRequisicao(String requisicaoDoUsuario) {
        if (!requisicaoDoUsuario.equals("1") & !requisicaoDoUsuario.equals("2")) {
            System.out.println("Não entendemos sua requisição, tente novamente!");
            menuUsuario();
        } else if (requisicaoDoUsuario.equals("1")) {
            menuLogin();
        } else {
            System.out.println("Digite seu nome: ");
            Cliente cliente = new Cliente(sc.next());
            cliente.abrirConta(cliente);
        }
    }
    public void menuLogin() {
        System.out.println("Digite o seu CPF ou CNPJ: ");
        respostasUsuario = sc.next();
        String respostaLogin = respostasUsuario;
        validarLoginEntrada(respostaLogin);
    }
    public void validarLoginEntrada(String login) {
        if (!Banco.getInstance().contemLogin(login)) {
            System.out.println("CPF ou CNPJ inválido. Tente novamente.");
            menuLogin();
        } else {
            Cliente cliente = Banco.getInstance().getClientes().get(login);
            validarSenhaEntrada(cliente);
        }
    }
    public void validarSenhaEntrada(Cliente cliente) {
        System.out.println("Digite sua senha: ");
        String respostaSenha = sc.next();
        if (respostaSenha.isBlank()) {
            System.out.println("Não capturamos sua senha. Por favor, tente novamente.");
            validarSenhaEntrada(cliente);
        } else {
            if (respostaSenha.contentEquals(cliente.getConta().getSenha())) {
                System.out.println("Seja bem vindo(a) " + cliente.getConta().getTitular());
                //INSERIR MENU DO CLIENTE (COM AS OPÇÕES DE SACAR, TRANSFERIR...)
            } else {
                System.out.println("Senha incorreta. Por favor, tente novamente.");
                validarSenhaEntrada(cliente);
            }
        }
    }*/
}
