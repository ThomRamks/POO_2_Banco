package menus;

import banco.Banco;
import interfaces.ICliente;
import interfaces.IMenu;

import java.util.Scanner;

public class MenuCadastroPF implements IMenu<String> {
    private static final MenuAberturaConta menuAberturaConta = new MenuAberturaConta();
    
    public static MenuAberturaConta getInstance(){
        return menuAberturaConta;
    }
    Scanner sc = new Scanner(System.in);
    @Override
    public void exibir() {
        System.out.println("==============   ABERTURA DE CONTA   ================");
        System.out.println(
                "Qual tipo de conta voce deseja criar:\n"
                        + "1 - Conta Pessoa Fisica \n"
                        + "2 - Conta Pessoa Juridica \n"
                        + "3 - Voltar ao Menu Inicial");

        processarOpcao(sc.next());
        sc.nextLine();
    }

    @Override
    public void processarOpcao(String opcaoCliente) {
        switch (opcaoCliente) {
            case "1":
                ICliente cliente = cadastrarPF(opcaoCliente);
                Banco.getInstance().abrirContaPessoaFisica(cliente);
                System.out.println("Conta criada com sucesso!");
                System.out.println("Voce sera redirecionado ao Menu Inicial!");
                MenuInicial.getInstance().exibir();
                break;
            case "2":
                ICliente clientePJ = cadastrarPJ(opcaoCliente);
                Banco.getInstance().abrirContaPessoaJuridica(clientePJ);
                System.out.println("Conta criada com sucesso!");
                System.out.println("Voce sera redirecionado ao Menu Inicial!");
                MenuInicial.getInstance().exibir();
                break;
            case "3":
                MenuInicial.getInstance().exibir();
                break;
            default:
                System.out.println("Operação inválida. Tente novamente.");
                menuAberturaConta.exibir();
                break;
        }
    }
}
