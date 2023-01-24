package conta;

import banco.Banco;
import interfaces.IConta;
import cliente.ClientePessoaFisica;


public class ContaCorrentePessoaFisica extends Conta {private int operacao = 0;

    public ContaCorrentePessoaFisica(String senha, ClientePessoaFisica titular) {
        super(senha, titular);

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

    @Override
    public String getTipoConta() {
        return TipoConta.CONTA_CORRENTE.getDescricao();
    }

    @Override
    public int getOperacao() {
        return this.operacao;
    }
}
