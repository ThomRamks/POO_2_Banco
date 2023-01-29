package menus;

import banco.Banco;
import interfaces.IConta;
import interfaces.IMenu;
import interfaces.IMenuSubtipos;
import util.formata.FormataDouble;

import java.util.Scanner;

public class MenuSubtipoPJ implements IMenuSubtipos<IConta, Integer, String> {
    Scanner sc = new Scanner(System.in);
    private static final MenuSubtipoPJ menuSubtipoPJ = new MenuSubtipoPJ();
    public static MenuSubtipoPJ getInstance(){
        return menuSubtipoPJ;
    }

    @Override
    public void exibir(IConta contaOrigem, int contaDestino) {
        System.out.println(
                        "1 - Conta Corrente\n" +
                        "2 - Conta Investimento\n");
        processarOpcao(sc.next(),contaDestino,contaOrigem);
    }

    @Override
    public void processarOpcao(String opcaoCliente, Integer numeroContaDestino, IConta contaOrigem) {
        switch (opcaoCliente) {
            case "1":
            case "2":
                double valorDesejado = requisitarValorTransferencia();
                IConta contaDestino = Banco.getInstance().getSubTipoConta(numeroContaDestino, opcaoCliente);
                if (Banco.getInstance().transferir(contaOrigem, valorDesejado, contaDestino)) {
                    System.out.println("Transferência realizada com sucesso!");
                } else {
                    System.out.println("Saldo insuficiente para transferência!");
                }
                MenuCliente.getInstance().exibir(contaOrigem.getTitular());
                break;
            default:
                MenuSubtipoPJ.menuSubtipoPJ.exibir(contaOrigem, numeroContaDestino);
        }
    }
        public double requisitarValorTransferencia () {
            try {
                System.out.println("Digite o valor a ser transferido: ");
                String valor = sc.next();
                double valorDesejado = FormataDouble.validaDouble(valor);
                return valorDesejado;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Tente novamente.");
                requisitarValorTransferencia();
            }
            return 0.0;
        }

    }

