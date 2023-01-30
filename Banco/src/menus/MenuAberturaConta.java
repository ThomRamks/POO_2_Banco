package menus;

import banco.Banco;
import interfaces.ICliente;
import interfaces.IMenu;
import java.util.Scanner;

public class MenuAberturaConta implements IMenu<String> {
    Scanner sc = new Scanner(System.in);
    private static final MenuAberturaConta menuAberturaConta = new MenuAberturaConta();

    public static MenuAberturaConta getInstance() {
        return menuAberturaConta;
    }

    @Override
    public void exibir() {
        System.out.println("==============   ABERTURA DE CONTA   ================");
        System.out.println(
                "Qual tipo de conta voce deseja criar:\n"
                        + "1 - Conta Pessoa Fisica \n"
                        + "2 - Conta Pessoa Juridica \n"
                        + "3 - Voltar ao Menu Inicial");
        processarOpcao(sc.next());
    }

    @Override
    public void processarOpcao(String respostasUsuario) {
        switch (respostasUsuario) {
            case "1":
                ICliente clientePF = MenuCadastroPF.getInstance().exibir(respostasUsuario);
                Banco.getInstance().abrirContaPessoaFisica(clientePF);
                System.out.println("Conta criada com sucesso!");
                System.out.println("Voce sera redirecionado ao Menu Inicial!");
                MenuInicial.getInstance().exibir();
                break;
            case "2":
                ICliente clientePJ = MenuCadastroPJ.getInstance().exibir(respostasUsuario);
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
