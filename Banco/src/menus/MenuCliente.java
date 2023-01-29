package menus;

import banco.Banco;
import interfaces.ICliente;
import interfaces.IMenuParametrizado;

import java.util.*;

public class MenuCliente implements IMenuParametrizado<String, ICliente>{
    private static final MenuCliente menuCliente = new MenuCliente();
    Scanner sc = new Scanner(System.in);


    public static MenuCliente getInstance(){
        return menuCliente;
    }

    @Override
    public void exibir(ICliente cliente) {
        System.out.println("==============    MENU CLIENTE   ================");
        System.out.println("Seja bem vindo(a) " + cliente.getContasUsuario().get(0).getTitular().getNome());
        if (Banco.getInstance().getTipoPessoa(cliente.getContasUsuario().get(0).getNumero()).equals("PF")) {
            MenuPessoaFisica.getInstance().exibir(cliente);
        } else if (Banco.getInstance().getTipoPessoa(cliente.getContasUsuario().get(0).getNumero()).equals("PJ")) {
            MenuPessoaJuridica.getInstance().exibir(cliente);
        }
    }

    @Override
    public void processarOpcao(String s, ICliente cliente) {

    }
}