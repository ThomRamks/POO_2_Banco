package menus;

import banco.Banco;
import exceptions.ValidatorException;
import interfaces.IMenu;
import validator.LoginValidator;
import validator.SenhaLoginValidator;
import java.util.Scanner;

public class MenuLogin implements IMenu<String> {
    Scanner sc = new Scanner(System.in);
    private static final MenuLogin menuLogin = new MenuLogin();
    public static MenuLogin getInstance(){
        return menuLogin;
    }

    @Override
    public void exibir() {
        System.out.println("==============    LOGIN   ================");
        System.out.println("Digite seu documento:");
        String login = sc.nextLine();
        processarOpcao(login);
        System.out.println("Digite sua senha:");
        String senha = sc.nextLine();
        processarSenha(senha);
        System.out.println();
        MenuCliente.getInstance().exibir(Banco.getInstance().getCliente(Banco.getInstance().getClienteLogin()));
    }

    @Override
    public void processarOpcao(String login) {
        try {
            Banco.getInstance().valida(new LoginValidator(), login);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
            MenuInicial.getInstance().exibir();
        }
    }

    public void processarSenha(String senha){
        try {
            Banco.getInstance().valida(new SenhaLoginValidator(), senha);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
            menuLogin.exibir();
        }
    }
}
