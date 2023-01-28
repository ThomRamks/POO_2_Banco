package menus;

import interfaces.IConta;
import interfaces.IMenu;
import interfaces.IMenuParametrizado;

import java.util.Scanner;

public class MenuOperacoes<String, T> implements IMenuParametrizado<String, T> {
    Scanner sc = new Scanner(System.in);

    @Override
    public void exibir(IConta conta) {
        System.out.println("Qual operação você deseja realizar:\n"
                + "1 - Sacar \n"
                + "2 - Transferir \n"
                + "3 - Depositar \n"
                + "4 - Consultar Saldo\n"
                + "5 - Voltar");
        processarOpcao(sc.next(), conta);
            }

    @Override
    public void processarOpcao(String opcaoCliente, IConta conta) {
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

}
