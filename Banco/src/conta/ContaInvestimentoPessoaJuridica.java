package conta;

import banco.Banco;
import interfaces.IConta;
import interfaces.IContaInvestimento;
import pessoa.PessoaJuridica;

public class ContaInvestimentoPessoaJuridica extends Conta implements IContaInvestimento<IConta> {

    Banco operacao = new Banco();

    public ContaInvestimentoPessoaJuridica(int agencia, int numero, PessoaJuridica titular) {
        super(agencia, numero, titular);
    }

    @Override
    public void depositar(double valor) {
        this.investir(valor);
    }

    @Override
    public boolean transferir(double valor, IConta conta) {
        if (this.sacar(valor)) {
            this.operacao.depositar(conta, valor);
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

    @Override
    public void investir(double valor) {
        super.saldo +=  (valor * 1.035);
    }
}