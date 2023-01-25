import conta.ContaCorrentePessoaFisica;
import conta.ContaCorrentePessoaJuridica;
import conta.ContaPoupanca;
import banco.Banco;
import cliente.ClientePessoaFisica;
import cliente.ClientePessoaJuridica;
import util.GeraDadosIniciais;

import java.util.Scanner;

public class Application {
    static Scanner sc = new Scanner(System.in);
    static String respostasUsuario;
    static Banco banco = Banco.getInstance();

    public static void main(String[] args) {
        GeraDadosIniciais dadosIniciais = new GeraDadosIniciais();
        dadosIniciais.carregaDadosIniciais();

        menuInicial();
    }

    public static void menuInicial() {
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
                //fazerLogin();
                break;
            case "2":
                abrirConta();
                break;
            case "3":
                //sair();
                break;
            default:
                System.out.println("Operação inválida. Tente novamente.");
                menuInicial();
                break;
        }
    }

    public static void abrirConta() {
        System.out.println("Qual tipo de conta voce deseja criar:\n"
                + "1 - Conta Pessoa Fisica \n"
                + "2 - Conta Pessoa Juridica \n"
                + "3 - Voltar ao Menu Inicial");

        respostasUsuario = sc.next();

        switch (respostasUsuario) {
            case "1":
                //abrircontaPF();
                break;
            case "2":
                //abrirContaPJ();
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
}
