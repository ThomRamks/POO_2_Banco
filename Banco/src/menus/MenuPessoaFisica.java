package menus;

import interfaces.ICliente;
import interfaces.IMenuParametrizado;
import java.util.Scanner;

public class MenuPessoaFisica implements IMenuParametrizado<String, ICliente> {
    Scanner sc = new Scanner(System.in);
    private static final MenuPessoaFisica menuPessoaFisica = new MenuPessoaFisica();
    public static MenuPessoaFisica getInstance(){
        return menuPessoaFisica;
    }

    @Override
    public void exibir(ICliente cliente) {
        System.out.println("Qual conta voce deseja acessar:\n"
                + "1 - Conta Corrente \n"
                + "2 - Conta Investimento \n"
                + "3 - Conta Poupança \n"
                + "4 - Voltar");
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
                MenuOperacoesInvestir.getInstance().exibir(cliente.getContasUsuario().get(1));
                break;
            case "3":
                MenuOperacoes.getInstance().exibir(cliente.getContasUsuario().get(2));
                break;
            case "4":
                MenuInicial.getInstance().exibir();
                break;
            default:
                System.out.println("Operação inválida. Tente novamente.");
                MenuPessoaFisica.getInstance().exibir(cliente);
                break;
        }
    }
}
