package menus;

import interfaces.IConta;
import interfaces.IMenuParametrizado;

import java.util.*;

public class MenuOperacoes implements IMenuParametrizado<String, IConta> {
    private static final MenuOperacoes menuOperacoes = new MenuOperacoes();
    Scanner sc = new Scanner(System.in);

    public static MenuOperacoes getInstance(){
        return menuOperacoes;
    }

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
                MenuSacar.getInstance().exibir(conta);
                MenuOperacoes.menuOperacoes.exibir(conta);
                break;
            case "2":
                MenuTransferir.getInstance().exibir(conta);
                MenuOperacoes.menuOperacoes.exibir(conta);
                break;
            case "3":
                MenuDepositar.getInstance().exibir(conta);
                MenuOperacoes.menuOperacoes.exibir(conta);
                break;
            case "4":
                System.out.printf("Seu saldo atual é: R$ %.2f \n ", conta.getSaldo());
                MenuOperacoes.menuOperacoes.exibir(conta);
                break;
            case "5":
                MenuCliente.getInstance().exibir(conta.getTitular());
                break;
            default:
                System.out.println("Operação inválida. Tente novamente. \n");
                MenuOperacoes.menuOperacoes.exibir(conta);
                break;
        }
    }
}
