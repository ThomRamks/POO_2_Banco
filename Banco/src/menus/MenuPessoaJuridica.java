package menus;

import interfaces.ICliente;
import interfaces.IMenu;
import interfaces.IMenuParametrizado;

import java.util.Scanner;

public class MenuPessoaJuridica implements IMenuParametrizado<String, ICliente> {
    Scanner sc = new Scanner(System.in);
    private static final MenuPessoaJuridica menuPessoaJuridica = new MenuPessoaJuridica();

    public static MenuPessoaJuridica getInstance(){
        return menuPessoaJuridica;
    }


    @Override
    public void exibir(ICliente cliente) {
        System.out.println("Qual conta voce deseja acessar:\n"
                + "1 - Conta Corrente \n"
                + "2 - Conta Investimento \n"
                + "3 - Voltar");
        String opcaoCliente = sc.next();
        processarOpcao(opcaoCliente, cliente);
    }

    @Override
    public void processarOpcao(String opcaoCliente, ICliente cliente) {

        switch (opcaoCliente) {
            case "1":
                MenuOperacoes.getInstance().exibir(cliente.getContasUsuario().get(0));
                break;
            case "2":
                menuOperacoesInvestir(cliente.getContasUsuario().get(1));
                break;

            case "3":
                MenuInicial.getInstance().exibir();
                break;
            default:
                System.out.println("Operação inválida. Tente novamente.");
                MenuPessoaJuridica.getInstance().exibir(cliente);
                break;
        }
    }
}
