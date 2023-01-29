package menus;

import interfaces.ICliente;
import interfaces.IConta;
import interfaces.IMenu;
import interfaces.IMenuParametrizado;

import java.util.Scanner;

public class MenuOperacoesInvestir implements IMenuParametrizado<String, IConta> {
    Scanner sc = new Scanner(System.in);
    private static final MenuOperacoesInvestir menuOperacoesInvestir = new MenuOperacoesInvestir();
    public static MenuOperacoesInvestir getInstance(){
        return menuOperacoesInvestir;
    }

    @Override
    public void exibir(IConta conta) {
        System.out.println("Qual operação você deseja realizar:\n"
                + "1 - Sacar \n"
                + "2 - Transferir \n"
                + "3 - Depositar \n"
                + "4 - Investir \n"
                + "5 - Consultar Saldo\n"
                + "6 - Voltar");
        processarOpcao(sc.next(),conta);
    }

    @Override
    public void processarOpcao(String opcaoCliente, IConta conta) {
        switch (opcaoCliente) {
            case "1":
                MenuSacar.getInstance().exibir(conta);
                menuOperacoesInvestir.exibir(conta);
                break;
            case "2":
                MenuTransferir.getInstance().exibir(conta);
                menuOperacoesInvestir.exibir(conta);
                break;
            case "3":
                MenuDepositar.getInstance().exibir(conta);
                menuOperacoesInvestir.exibir(conta);
                break;
            case "4":
                MenuInvestir.getInstance().exibir(conta);
                menuOperacoesInvestir.exibir(conta);
            case "5":
                System.out.printf("Seu saldo atual é: R$ %.2f \n ", conta.getSaldo());
                menuOperacoesInvestir.exibir(conta);
                break;
            case "6":
                MenuCliente.getInstance().exibir(conta.getTitular());
                break;
            default:
                System.out.println("Operação inválida. Tente novamente.");
                menuOperacoesInvestir.exibir(conta);
                break;
        }
    }
}

