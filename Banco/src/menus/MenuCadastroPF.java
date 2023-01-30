package menus;

import banco.Banco;
import exceptions.ValidatorException;
import interfaces.ICliente;
import validator.CPFValidator;
import validator.CriarLoginValidator;
import validator.CriarSenhaValidator;
import validator.NomeValidator;
import java.util.Scanner;

public class MenuCadastroPF {
    Scanner sc = new Scanner(System.in);
    private static final MenuCadastroPF menuCadastroPF = new MenuCadastroPF();
    public static MenuCadastroPF getInstance(){
        return menuCadastroPF;
    }

    public ICliente exibir(String tipoCliente){
        System.out.println("Digite seu nome:");
        String nome = sc.nextLine();
        System.out.println("Digite seu CPF:");
        String cpf = sc.nextLine();
        System.out.println("Digite uma senha:");
        String senha = sc.nextLine();
        return validaCadastro(nome,cpf,senha,tipoCliente);
    }

    public ICliente validaCadastro(String nome, String cpf, String senha, String tipoCliente ){
        try {
            Banco.getInstance().valida(new NomeValidator(), nome);
            Banco.getInstance().valida(new CPFValidator(), cpf);
            Banco.getInstance().valida(new CriarLoginValidator(), cpf);
            Banco.getInstance().valida(new CriarSenhaValidator(), senha);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
            MenuInicial.getInstance().exibir();
        }
        return Banco.getInstance().registrarConta(nome, cpf, senha, tipoCliente);
    }
}
