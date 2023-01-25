package banco;

import conta.*;
import interfaces.ICliente;
import interfaces.IConta;
import interfaces.IContaInvestimento;

import java.util.*;

public class Banco {

    private static final Banco AdaBank = new Banco();


//    public List<IConta> getContas() {
//        return contas;
//    }

    //    private List<IConta> contas = new ArrayList<>();
    private HashMap<IConta, ICliente> contasNoBanco = new HashMap<>();
    //    private List<IConta> contasUsuario = new ArrayList<>(); // passar para o cliente
    private int numeroDefault;

    public Banco() {
        numeroDefault = 1000;
    }

    public static Banco getInstance() {
        return AdaBank;
    }

    public void abrirContaPessoaFisica(ICliente cliente) {
        int numero = 0;
//        getContasUsuario(cliente.getDocumento());
        if (cliente.getContasUsuario().size() > 0) {
            numero = cliente.getContasUsuario().get(0).getNumero();
        } else {
            numeroDefault++;
            numero = numeroDefault;
        }
        IConta ccPessoaFisica = new ContaCorrentePessoaFisica(numero, cliente);
//        contas.add(ccPessoaFisica);
//        cliente.getContasUsuario().add(ccPessoaFisica);
        IConta ciPessoaFisica = new ContaInvestimentoPessoaFisica(numero, cliente);
//        contas.add(ciPessoaFisica);
//        cliente.getContasUsuario().add(ciPessoaFisica);
        IConta cpPessoaFisica = new ContaPoupanca(numero, cliente);
//        contas.add(cpPessoaFisica);
//        cliente.getContasUsuario().add(cpPessoaFisica);
        Collections.addAll(cliente.getContasUsuario(), ccPessoaFisica, ciPessoaFisica, cpPessoaFisica);
//        Collections.addAll(contas, ccPessoaFisica, ciPessoaFisica, cpPessoaFisica);
        contasNoBanco.put(ccPessoaFisica, cliente);
        contasNoBanco.put(ciPessoaFisica, cliente);
        contasNoBanco.put(cpPessoaFisica, cliente);
    }

    public void abrirContaPessoaJuridica(ICliente cliente) {
        int numero = 0;
//        getContasUsuario(cliente.getDocumento());
        if (cliente.getContasUsuario().size() > 0) {
            numero = cliente.getContasUsuario().get(0).getNumero();
        } else {
            numeroDefault++;
            numero = numeroDefault;
        }
        IConta ccPessoaJuridica = new ContaCorrentePessoaJuridica(numero, cliente);
//        contas.add(ccPessoaJuridica);
        cliente.getContasUsuario().add(ccPessoaJuridica);
        IConta ciPessoaJuridica = new ContaInvestimentoPessoaJuridica(numero, cliente);
//        contas.add(ciPessoaJuridica);
        cliente.getContasUsuario().add(ciPessoaJuridica);
        Collections.addAll(cliente.getContasUsuario(), ccPessoaJuridica, ciPessoaJuridica);
//        Collections.addAll(contas, ccPessoaJuridica, ciPessoaJuridica);
        contasNoBanco.put(ccPessoaJuridica, cliente);
        contasNoBanco.put(ciPessoaJuridica, cliente);
    }


    public boolean contemLogin(String login) {
        boolean contem = false;
        for (Map.Entry<IConta, ICliente> contaS : contasNoBanco.entrySet()) {
            if (contaS.getValue().getDocumento().equals(login)) {
                contem = true;
            }

        }
        return contem;
    }

//                        CASO USEMOS LIST AO INVÉS DE HASHMAP:
//    public boolean contemLogin(String login) {
//        boolean contem = false;
//        for (IConta conta : contas) {
//            if (conta.getTitular().getDocumento().equals(login)) {
//                contem = true;
//            }
//        }
//        return contem;
//    }


    public boolean cadastrarSenha(String senha) {
        boolean tamanhoCorreto;
        if (senha.length() < 8 || senha.isBlank()) {
            tamanhoCorreto = false;
        } else {
            tamanhoCorreto = true;
        }
        return tamanhoCorreto;
    }

    public ICliente getCliente(String login) {
        ICliente cliente = null;
        for (Map.Entry<IConta, ICliente> contaS : contasNoBanco.entrySet()) {
            if (contaS.getValue().getDocumento().equals(login)) {
                cliente = contaS.getValue();
            }
        }
        return cliente;
    }

    //                        CASO USEMOS LIST AO INVÉS DE HASHMAP:
//    public ICliente getCliente(String login){
//        ICliente cliente = null;
//        for(IConta conta : contas){
//            if(conta.getTitular().getDocumento().equals(login)){
//                cliente = conta.getTitular();
//            }
//        }
//        return cliente;
//    }

    public boolean checarSenha(ICliente cliente, String senha) {
        return cliente.validaSenha(senha);
    }

    public boolean contemConta(int numeroConta) {
        boolean contem = false;
        for (Map.Entry<IConta, ICliente> contaS : contasNoBanco.entrySet()) {
            if (contaS.getKey().getNumero() == numeroConta) {
                contem = true;
            }
        }
        return contem;
    }


//                        CASO USEMOS LIST AO INVÉS DE HASHMAP:
//    public boolean contemConta(int numeroConta){
//        boolean contem = false;
//        for (IConta conta: contas){
//            if(conta.getNumero() == numeroConta){
//                contem = true;
//            }
//        }
//        return contem;
//    }

    public String getTipoPessoa(int numeroConta) {
        String tipoPessoa = null;
        for (Map.Entry<IConta, ICliente> contaS : contasNoBanco.entrySet()) {
            if (contaS.getKey().getNumero() == numeroConta) {
                if (contaS.getValue().getDocumento().length() == 14) {
                    tipoPessoa = "PF";
                } else if (contaS.getValue().getDocumento().length() == 18) {
                    tipoPessoa = "PJ";
                }
            }
        }
        return tipoPessoa;
    }

    public IConta getSubTipoConta(int numeroConta, int operacao) {
        IConta conta = null;
        for (Map.Entry<IConta, ICliente> contaS : contasNoBanco.entrySet()) {
            if (contaS.getKey().getNumero() == numeroConta && contaS.getKey().getOperacao() == operacao) {
                conta = contaS.getKey();
            }
        }
        return conta;
    }


//    public void getContasUsuario(String documento) {
//        contasUsuario.clear();
//        for (IConta conta : contas) {
//            if (conta.getTitular().getDocumento().equals(documento)){
//                contasUsuario.add(conta);
//            }
//        }
//    }

//    public int getNumeroConta(String documento) {
//        return contasUsuario.get(0).getNumero();
//    }
//
//    public ICliente getCliente(String documento) {
//        getContasUsuario(documento);
//        return contasUsuario.get(0).getTitular();
//    }

    public void listarContasUsuario(ICliente cliente) {
        String nome = cliente.getNome();
        System.out.println("\n\t-- Conta " + nome + " --\n");
        cliente.getContasUsuario().forEach(conta -> {
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
//<<<<<<< HEAD
//
//
//
//=======
//}
//>>>>>>> Developing
