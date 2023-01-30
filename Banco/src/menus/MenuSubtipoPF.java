package menus;

import banco.Banco;
import interfaces.IConta;
import interfaces.IMenuSubtipos;
import util.formata.FormataDouble;
import java.util.Scanner;

public class MenuSubtipoPF implements IMenuSubtipos<IConta, Integer, String> {
    Scanner sc = new Scanner(System.in);
    private static final MenuSubtipoPF menuSubtipoPF = new MenuSubtipoPF();

    public static MenuSubtipoPF getInstance() {
        return menuSubtipoPF;
    }

    @Override
    public void exibir(IConta contaOrigem, int contaDestino) {
        System.out.println(
                "1 - Conta Corrente\n" +
                        "2 - Conta Investimento\n" +
                        "3 - Conta Poupança");
        processarOpcao(sc.next(), contaDestino, contaOrigem);
    }

    @Override
    public void processarOpcao(String opcaoCliente, Integer numeroContaDestino, IConta contaOrigem) {
        switch (opcaoCliente) {
            case "1":
            case "2":
            case "3":
                requisitarValorTransferencia(opcaoCliente, numeroContaDestino, contaOrigem);
                break;
            default:
                menuSubtipoPF.exibir(contaOrigem, numeroContaDestino);
        }
    }

    private void requisitarValorTransferencia(String opcaoCliente, Integer numeroContaDestino, IConta contaOrigem) {
        try {
            System.out.println("Digite o valor a ser transferido: ");
            String valor = sc.next();
            double valorDesejado = FormataDouble.validaDouble(valor);
            if (valorDesejado > 0) {
                validarTransferencia(opcaoCliente, numeroContaDestino, contaOrigem, valorDesejado);
                MenuCliente.getInstance().exibir(contaOrigem.getTitular());
            } else {
                System.out.println("Valor precisa ser maior que R$0.00 \n");
                requisitarValorTransferencia(opcaoCliente, numeroContaDestino, contaOrigem);
            }
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido! Tente novamente. \n");
            requisitarValorTransferencia(opcaoCliente, numeroContaDestino, contaOrigem);
        }
    }

    private boolean validarTransferencia(String opcaoCliente, Integer numeroContaDestino, IConta contaOrigem, double valorDesejado) {
        IConta contaDestino = Banco.getInstance().getSubTipoConta(numeroContaDestino, opcaoCliente);
        if (Banco.getInstance().transferir(contaOrigem, valorDesejado, contaDestino)) {
            System.out.println("Transferência realizada com sucesso!");
            return true;
        } else {
            System.out.println("Saldo insuficiente para transferência! \n");
        }
        return false;
    }
}
