package conta;

import interfaces.IConta;
import interfaces.IContaInvestimento;
import pessoa.PessoaFisica;

public class ContaInvestimentoPessoaFisica extends Conta implements IConta, IContaInvestimento<IConta> {

    public ContaInvestimentoPessoaFisica(int agencia, int numero, PessoaFisica titular) {
        super(agencia, numero, titular);
    }

    @Override
    public void depositar(double valor) {

    }

    @Override
    public boolean transferir(double valor, IConta conta) {
        return false;
    }

    @Override
    public boolean sacar(double valor) {
        return false;
    }

    @Override
    public void investir(double valor) {
    }
}
