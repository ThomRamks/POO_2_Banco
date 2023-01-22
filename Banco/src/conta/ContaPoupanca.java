package conta;

import banco.Banco;
import interfaces.IConta;
import cliente.ClientePessoaFisica;

public class ContaPoupanca extends Conta {

    private ClientePessoaFisica titular;

    public ContaPoupanca(int agencia, int numero, ClientePessoaFisica titular) {
        super(agencia, numero, titular);
    }

    @Override
    public void depositar(double valor) {
        super.saldo += valor + (valor * 0.01);
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
