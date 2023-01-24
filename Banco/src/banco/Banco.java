package banco;

import cliente.Cliente;
import conta.*;
import interfaces.ICliente;
import interfaces.IConta;
import interfaces.IContaInvestimento;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    private static final Banco AdaBank = new Banco();
    private List<IConta> contas = new ArrayList<>();
    private List<IConta> contasUsuario = new ArrayList<>(); // passar para o cliente
    private int numeroDefault;

    public Banco() {
        numeroDefault = 1000;
    }

    public void abrirContaPessoaFisica(ICliente cliente){
        int numero = 0;
        getContasUsuario(cliente.getDocumento());
        if (contasUsuario.size() > 0) {
            numero = getNumeroConta(cliente.getDocumento());
        } else {
            numeroDefault++;
            numero = numeroDefault;
        }
        IConta ccPessoaFisica = new ContaCorrentePessoaFisica(numero, cliente);
        contas.add(ccPessoaFisica);
        IConta ciPessoaFisica = new ContaInvestimentoPessoaFisica(numero, cliente);
        contas.add(ciPessoaFisica);
        IConta cpPessoaFisica = new ContaPoupanca(numero, cliente);
        contas.add(cpPessoaFisica);
    }

    public void abrirContaPessoaJuridica(ICliente cliente){
        int numero = 0;
        getContasUsuario(cliente.getDocumento());
        if (contasUsuario.size() > 0) {
            numero = getNumeroConta(cliente.getDocumento());
        } else {
            numeroDefault++;
            numero = numeroDefault;
        }
        IConta ccPessoaJuridica = new ContaCorrentePessoaJuridica(numero, cliente);
        contas.add(ccPessoaJuridica);
        IConta ciPessoaJuridica = new ContaInvestimentoPessoaJuridica(numero, cliente);
        contas.add(ciPessoaJuridica);
    }

    public void getContasUsuario(String documento) {
        contasUsuario.clear();
        for (IConta conta : contas) {
            if (conta.getTitular().getDocumento().equals(documento)){
                contasUsuario.add(conta);
            }
        }
    }

    public int getNumeroConta(String documento) {
        return contasUsuario.get(0).getNumero();
    }

    public ICliente getCliente(String documento) {
        getContasUsuario(documento);
        return contasUsuario.get(0).getTitular();
    }

    public void listarContasUsuario(String documento) {
        String nome = getCliente(documento).getNome();
        System.out.println("\n\t-- Conta " + nome + " --\n");
        contasUsuario.forEach(conta -> {
            System.out.println("Tipo conta: " + conta.getTipoConta());
            System.out.println("Operação: " + conta.getOperacao());
            System.out.println("Titular: " + conta.getTitular().getNome());
            System.out.println("Documento: " + conta.getTitular().getDocumento());
            System.out.println("Agência: " + conta.getAgencia());
            System.out.println("Número: " + conta.getNumero());
            System.out.println("\n\t --- \n");
        });
    }

    public void depositar(IConta conta, double valor) {
        conta.depositar(valor);
    }

    public boolean transferir(IConta contaOrigem, double valor, IConta contaDestino) {
        return contaOrigem.transferir(valor, contaDestino);
    }

    public void sacar(IConta conta, double valor) {
        conta.sacar(valor);
    }

    public void investir(IContaInvestimento conta, double valor) {
        conta.investir(valor);
    }

    public static Banco getInstance() {
        return AdaBank;
    }


    /*public boolean contemLogin(String login) {
        //boolean contem = false;
        //if (clientes.containsKey(login)) {
            //contem = true;
        //}
        return contas.containsKey(login);
    }*/

    /*public boolean validarLoginCriacao(String login) {
        //if (Banco.getInstance().contemLogin(login)) { // CPF e CNPJ precisam ter seus formatos definidos
            //System.out.println("CPF ou CNPJ já cadastrados. Tente novamente");
        //} else {
            validarSenhaCriacao();
        //}
        return Banco.getInstance().contemLogin(login);
    }*/

    /*public boolean validarSenhaCriacao(String respostaSenha) {
        System.out.println("Digite sua senha: ");
        String respostaSenha = sc.next();
        if (respostaSenha.isBlank() || respostaSenha.length() < 8) {
            System.out.println("Sua senha deve ter, ao menos, oito caracteres. Por favor, tente novamente.");
            validarSenhaCriacao();
              return false;
        return respostaSenha.isBlank() || respostaSenha.length() <= 8;
    }*/


   /* public void menuCliente(Cliente cliente) {
        System.out.println("==============    MENU CLIENTE   ================");
        System.out.println("Seja bem vindo(a) " + cliente.getConta().getTitular());
        //fazer separacao por tipo de cliente/conta ????
        // qual tipo de conta vai acessar???
        System.out.println("Qual operação você deseja realizar:\n"
                + "1 - Sacar \n"
                + "2 - Transferir \n"
                + "3 - Depositar \n"
                + "4 - Investir \n"
                + "5 - Consultar saldo \n"
                + "6 - Sair");

        //String opcaoCliente = sc.next();
        switch (opcaoCliente) {
            case "1":
                //sacar();
                break;
            case "2":
                //transferir();
                break;
            case "3":
                //depositar();

                break;
            case "4":
                //investir();
                break;
            case "5":
                //consultarSaldo();
                break;
            case "6":
                //sair();
                break;
            default:
                System.out.println("Operação inválida. Tente novamente.");
                menuCliente(cliente);
                break;
        }
    }*/
}