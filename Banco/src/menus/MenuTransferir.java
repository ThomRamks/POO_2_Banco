package menus;

import banco.Banco;
import interfaces.IConta;
import interfaces.IMenuParametrizado;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuTransferir implements IMenuParametrizado<Integer, IConta> {
    private static final MenuTransferir menuTransferir = new MenuTransferir();

    public static MenuTransferir getInstance(){
        return menuTransferir;
    }

    Scanner sc = new Scanner(System.in);
    @Override
    public void exibir(IConta conta) {
        try{
        System.out.println("Informe o número da conta de destino:");
        int contaDestino = sc.nextInt();
        processarOpcao(contaDestino,conta);
        } catch (InputMismatchException e) {
            System.out.println("Número de conta inválida! Tente novamente. \n");
            sc.nextLine();
        }
    }

    @Override
    public void processarOpcao(Integer contaDestino, IConta conta) {
            if (Banco.getInstance().contemConta(contaDestino)) {
                System.out.println("Para qual tipo de conta você deseja transferir?");
                if (Banco.getInstance().getTipoPessoa(contaDestino).equals("PF")) {
                    MenuSubtipoPF.getInstance().exibir(conta, contaDestino);
                } else if (Banco.getInstance().getTipoPessoa(contaDestino).equals("PJ")) {
                    MenuSubtipoPJ.getInstance().exibir(conta, contaDestino);
                }
            } else {
                System.out.println("Conta não encontrada. Tente novamente! \n");
            }
    }
}

