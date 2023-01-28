package menus;

import interfaces.IMenu;

import java.util.Scanner;

public class MenuInicial implements IMenu<String> {
    Scanner sc = new Scanner(System.in);
    MenuLogin menuLogin = new MenuLogin();
    @Override
    public void exibir() {
        System.out.println("Digite a operação desejada:\n"
                + "1 - Acessar sua conta \n"
                + "2 - Abrir conta \n"
                + "3 - Sair");

        processarOpcao(sc.nextLine());
    }

    @Override
    public void processarOpcao(String respostasUsuario) {
        switch (respostasUsuario) {
            case "1":
                menuLogin.exibir();
                break;
            case "2":
                abrirConta();
                menuInicial();
                break;
            case "3":
                sair();
                break;
            default:
                System.out.println("Operação inválida. Tente novamente.");
                menuInicial();
                break;
        }
    }

    private void sair(){
        System.out.println("ADA BANK agradece sua preferencia.");
        System.out.println("Esperamos te ver em breve! o/");
        System.exit(0);
    }
}
