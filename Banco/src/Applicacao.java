import menus.MenuInicial;
import util.GeraDadosIniciais;


public class Applicacao {

    public static void main(String[] args) {
        GeraDadosIniciais.getInstance().carregaDadosIniciais();

        System.out.println("====================================================");
        System.out.println("             Seja bem vindo(a) ao                   ");
        System.out.println("		            ADA BANK                        ");
        System.out.println("          Seu dinheiro, nossa renda!                ");
        System.out.println("====================================================\n");


        MenuInicial.getInstance().exibir();

    }
}
