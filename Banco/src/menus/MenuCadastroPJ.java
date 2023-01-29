package menus;

import banco.Banco;
import exceptions.ValidatorException;
import interfaces.ICliente;
import util.formata.FormataTexto;
import validator.CPFValidator;
import validator.CriarLoginValidator;
import validator.CriarSenhaValidator;
import validator.NomeValidator;

import java.util.Scanner;

public class MenuCadastroPJ {
    Scanner sc = new Scanner(System.in);
    private static final MenuCadastroPJ menuCadastroPJ = new MenuCadastroPJ();
    public static MenuCadastroPJ getInstance(){
        return menuCadastroPJ;
    }

    public ICliente exibir(String tipoCliente){
        System.out.println("Digite sua razão social:");
        String nome = FormataTexto.upperfirstCase(sc.nextLine());
        System.out.println("Digite seu CNPJ:");
        String cnpj = sc.nextLine();
        System.out.println("Digite uma senha:");
        String senha = sc.nextLine();
        return validaCadastro(nome,cnpj,senha,tipoCliente);
    }
    public ICliente validaCadastro(String nome, String cnpj, String senha, String tipoCliente ){
        try {
            Banco.getInstance().valida(new NomeValidator(), nome);
            Banco.getInstance().valida(new CPFValidator(), cnpj);
            Banco.getInstance().valida(new CriarLoginValidator(), cnpj);
            Banco.getInstance().valida(new CriarSenhaValidator(), senha);


        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
            MenuInicial.getInstance().exibir();
        }
        return Banco.getInstance().registrarConta(nome, cnpj, senha, tipoCliente);
    }
}
