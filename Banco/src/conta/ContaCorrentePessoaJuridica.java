package conta;

import interfaces.IConta;
import banco.Banco;
import cliente.ClientePessoaJuridica;

public class ContaCorrentePessoaJuridica extends Conta {

<<<<<<< HEAD
    public ContaCorrentePessoaJuridica(PessoaJuridica titular) {
        super(titular);
=======
    public ContaCorrentePessoaJuridica(int agencia, int numero, ClientePessoaJuridica titular) {
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
        double valorComTaxa = valor + (valor * 0.005);
        if (super.saldo >= valorComTaxa) {
            super.saldo -= valorComTaxa;
            return true;
        }
        System.out.println("Não é possível realizar a operação. O valor é maior do que se encontra na conta.");
        return false;
    }
}
