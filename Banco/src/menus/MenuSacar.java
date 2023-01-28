package menus;

import interfaces.IMenu;

import java.util.Scanner;

public class MenuSacar implements IMenu<String> {
    Scanner sc = new Scanner(System.in);
    @Override
    public void exibir() {
        processarOpcao(sc.next());
    }

    @Override
    public void processarOpcao(String objeto) {

    }
}