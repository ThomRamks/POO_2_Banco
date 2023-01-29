package menus;

import banco.Banco;
import interfaces.IConta;
import interfaces.IMenu;
import interfaces.IMenuParametrizado;
import util.formata.FormataDouble;

import java.util.Scanner;

public class MenuSacar implements IMenuParametrizado<Double, IConta> {
    Scanner sc = new Scanner(System.in);
    private static final MenuSacar menuSaque = new MenuSacar();

    @Override
    public void exibir(IConta conta) {
        try {
            System.out.println("Qual valor voce deseja sacar?");
            String valor = sc.next();
            double valorDesejado = FormataDouble.validaDouble(valor);
            if (valorDesejado > 0) {
                processarOpcao(valorDesejado, conta);
            } else {
                System.out.println("Valor precisa ser maior que 0.");
                exibir(conta);
            }
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido! Tente novamente.");
            menuSaque.exibir(conta);
        }
    }

    @Override
    public void processarOpcao(Double valor, IConta conta) {

        if (Banco.getInstance().sacar(conta, valor)) {
            System.out.println("Saque efetuado!");
            MenuOperacoes.getInstance().exibir(conta);
        } else {
            System.out.println("Saldo insuficiente!");
        }

    }

    public static MenuSacar getInstance() {
        return menuSaque;
    }


}