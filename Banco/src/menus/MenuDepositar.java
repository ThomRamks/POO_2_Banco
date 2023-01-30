package menus;

import banco.Banco;
import interfaces.IConta;
import interfaces.IMenuParametrizado;
import util.formata.FormataDouble;

import java.util.Scanner;

public class MenuDepositar implements IMenuParametrizado<Double, IConta> {
    private static final MenuDepositar menuDepositar = new MenuDepositar();
    public static MenuDepositar getInstance(){
        return menuDepositar;
    }
    Scanner sc = new Scanner(System.in);

    @Override
    public void exibir(IConta conta) {
        try {
            System.out.println("Digite a quantia que você deseja depositar: ");
            String valor = sc.next();
            double valorDesejado = FormataDouble.validaDouble(valor);
            processarOpcao(valorDesejado, conta);
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido! Tente novamente.");
            MenuDepositar.getInstance().exibir(conta);
        }
    }

    @Override
    public void processarOpcao(Double valor, IConta conta) {
        if (valor > 0 ) {
            Banco.getInstance().depositar(conta, valor);
            System.out.println("Depósito efetuado no valor de R$ " + valor + " \n");
            MenuOperacoes.getInstance().exibir(conta);
        } else {
            System.out.println("Valor precisa ser maior que 0.");
            exibir(conta);
        }
    }
}
