package menus;

import interfaces.IMenu;

import java.util.Scanner;

public class MenuLogin implements IMenu<String> {
    Scanner sc = new Scanner(System.in);
    @Override
    public void exibir() {
        System.out.println("==============    LOGIN   ================");
        System.out.println("Digite seu documento:");
        String login = sc.nextLine();
    }

    @Override
    public void processarOpcao(String login) {

    }

}
