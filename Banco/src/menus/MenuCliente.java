package menus;

import banco.Banco;
import interfaces.ICliente;
import interfaces.IConta;
import interfaces.IMenuParametrizado;

public class MenuCliente implements IMenuParametrizado<IConta, ICliente>{
    private static final MenuCliente menuCliente = new MenuCliente();
    public static MenuCliente getInstance(){
        return menuCliente;
    }

    @Override
    public void exibir(ICliente cliente) {
        System.out.println("================    MENU CLIENTE   ==================");
        System.out.println("Seja bem vindo(a) " + cliente.getContasUsuario().get(0).getTitular().getNome());
        System.out.println("Conta: " + cliente.getContasUsuario().get(0).getNumero() + " \n");
        IConta conta = cliente.getContasUsuario().get(0);
        processarOpcao(conta, cliente);
    }

    @Override
    public void processarOpcao(IConta conta, ICliente cliente) {
        if (Banco.getInstance().getTipoPessoa(conta.getNumero()).equals("PF")) {
            MenuPessoaFisica.getInstance().exibir(cliente);
        } else if (Banco.getInstance().getTipoPessoa(conta.getNumero()).equals("PJ")) {
            MenuPessoaJuridica.getInstance().exibir(cliente);
        }
    }
}