package conta;

import banco.Banco;
import interfaces.IConta;
import interfaces.IContaInvestimento;
import pessoa.PessoaJuridica;

public class ContaInvestimentoPessoaJuridica extends Conta implements IConta, IContaInvestimento {

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
        if (super.saldo >= valor) {
            super.saldo -= valor;
            return true;
        }
        return false;
    }

    @Override
    public void investir(double valor) {
        super.saldo +=  (valor * 1.035);
    }
}