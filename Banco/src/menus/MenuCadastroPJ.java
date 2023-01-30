package menus;

import banco.Banco;
import excecoes.ValidadorExcecao;
import interfaces.ICliente;
import util.formata.FormataTexto;
import validator.*;
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
            Banco.getInstance().valida(new NomeEmpresaValidador(), nome);
            Banco.getInstance().valida(new CNPJValidador(), cnpj);
            Banco.getInstance().valida(new CriarLoginValidador(), cnpj);
            Banco.getInstance().valida(new CriarSenhaValidador(), senha);
        } catch (ValidadorExcecao e) {
            System.out.println(e.getMessage());
            MenuInicial.getInstance().exibir();
        }
        return Banco.getInstance().registrarConta(nome, cnpj, senha, tipoCliente);
    }
}
