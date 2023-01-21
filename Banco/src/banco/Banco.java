package banco;

import conta.Conta;
import interfaces.IConta;
import pessoa.Pessoa;

import java.util.List;
import java.util.Map;

public class Banco {

    private static final Banco AdaBank = new Banco();

//    public Banco(){
//        System.out.println("Funcionando!!");
//    }

    public static Banco getInstance(){
        return AdaBank;
    }
    private Map<Conta,Pessoa> clientes;

    protected void cadastrarCliente(){

    }

    public void cadastrarConta(Pessoa cliente){

    }
    public void depositar(IConta conta, double valor) {
        conta.depositar(valor);
    }

    public boolean transferir(IConta contaOrigem, double valor, IConta contaDestino) {
        return contaOrigem.transferir(valor, contaDestino);
    }

       public void validarLogin(String cliente, String senha){

   }

}
