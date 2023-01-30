package menus;

import banco.Banco;
import excecoes.ValidadorExcecao;
import interfaces.ICliente;
import validator.CPFValidador;
import validator.CriarLoginValidador;
import validator.CriarSenhaValidador;
import validator.NomeValidador;
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
        System.out.println("Digite uma senha entre 8 e 32 caracteres, sem espaços:");
        String senha = sc.nextLine();
        return validaCadastro(nome,cpf,senha,tipoCliente);
    }

    public ICliente validaCadastro(String nome, String cpf, String senha, String tipoCliente ){
        try {
            Banco.getInstance().valida(new NomeValidador(), nome);
            Banco.getInstance().valida(new CPFValidador(), cpf);
            Banco.getInstance().valida(new CriarLoginValidador(), cpf);
            Banco.getInstance().valida(new CriarSenhaValidador(), senha);
        } catch (ValidadorExcecao e) {
            System.out.println(e.getMessage());
            MenuInicial.getInstance().exibir();
        }
        return Banco.getInstance().registrarConta(nome, cpf, senha, tipoCliente);
    }
}
