import banco.Banco;
import cliente.Cliente;

import java.util.Scanner;


public class Application {
    static Scanner sc = new Scanner(System.in);
    static String respostasUsuario;


    public static void main(String[] args) {
        Application app = new Application();
        Banco.getInstance();
        app.menuUsuario();

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

    }

    public void menuUsuario() {
        System.out.println("Seja Bem-Vindo ao AdaBank! Acesse sua conta ou abra uma!\n Acessar sua conta (1)\n Abertura de Conta (2)");
        respostasUsuario = sc.next();
        validarRequisicao(respostasUsuario);
    }

    public void validarRequisicao(String requisicaoDoUsuario) {
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
    }
}
