package menus;

import banco.Banco;
import interfaces.IConta;
import interfaces.IMenuParametrizado;
import util.formata.FormataDouble;
import java.util.Scanner;

public class MenuInvestir implements IMenuParametrizado<Double, IConta> {
    Scanner sc = new Scanner(System.in);
    private static final MenuInvestir menuInvestir = new MenuInvestir();
    public static MenuInvestir getInstance(){
        return menuInvestir;
    }

    @Override
    public void exibir(IConta conta) {
        try {
            System.out.println("Digite a quantia que você deseja investir: ");
            String valor = sc.next();
            double valorDesejado = FormataDouble.validaDouble(valor);
            processarOpcao(valorDesejado,conta);
        } catch (NumberFormatException e){
            System.out.println("Valor inválido! Tente novamente.");
            MenuInvestir.getInstance().exibir(conta);
        }
    }

    @Override
    public void processarOpcao(Double valorDesejado, IConta conta) {
        Banco.getInstance().depositar(conta, valorDesejado);
        System.out.println("Investimento efetuado!");
        MenuOperacoesInvestir.getInstance().exibir(conta);
    }
}
