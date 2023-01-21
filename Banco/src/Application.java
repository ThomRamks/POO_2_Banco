import banco.Banco;
import pessoa.Pessoa;

import java.util.Scanner;


public class Application {
    static Scanner sc = new Scanner(System.in);
    static String respostasUsuario;
    public static void main(String[] args) {
        Application app = new Application();
        Banco.getInstance();
        app.menuUsuario();
        respostasUsuario = sc.next();
        app.validarRequisicao(respostasUsuario);


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

    public void menuUsuario(){
        System.out.println("Seja Bem-Vindo ao AdaBank! Acesse sua conta ou abra uma!\n Acessar sua conta (1)\n Abertura de Conta(2)");
    }

    public void validarRequisicao(String requisicaoDoUsuario){
        if(!requisicaoDoUsuario.equals("1") & !requisicaoDoUsuario.equals("2")){
            System.out.println("Não entendemos sua requisição, tente novamente!");
        }else if(requisicaoDoUsuario.equals("1")){
            menuLogin();
        }else{
            System.out.println("Digite seu nome: ");
            Pessoa cliente = new Pessoa(sc.next());
            cliente.abrirConta(cliente);
        }
    }

    public void menuLogin(){
        System.out.println("Digite o seu CPF ou CNPJ: ");
        respostasUsuario = sc.next();
        String respostaLogin = respostasUsuario;
        System.out.println("Digite sua senha: ");
        String respostaSenha = sc.next();

        Banco.getInstance().validarLogin(respostaLogin,respostaSenha);
    }

}
