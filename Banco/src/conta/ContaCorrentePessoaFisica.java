package conta;

import banco.Banco;
import interfaces.IConta;
import cliente.ClientePessoaFisica;


public class ContaCorrentePessoaFisica extends Conta {

<<<<<<< HEAD
    public ContaCorrentePessoaFisica(PessoaFisica titular) {
        super(titular);
=======
    public ContaCorrentePessoaFisica(int agencia, int numero, ClientePessoaFisica titular) {
        super(agencia, numero, titular);
>>>>>>> a00f882b465452e38ec8ade7c7f76719f1cf53c7
    }

    @Override
    public void depositar(double valor) {
        super.saldo += valor;
    }

    @Override
    public boolean transferir(double valor, IConta conta) {
        if (this.sacar(valor)) {
            Banco.getInstance().depositar(conta, valor);
            return true;
        }
        return false;
    }

    @Override
    public boolean sacar(double valor) {
        if (super.saldo >= valor) {
            super.saldo -= valor;
            return true;
        }
        System.out.println("Não é possível realizar a operação. O valor é maior do que se encontra na conta.");
        return false;
    }
}
