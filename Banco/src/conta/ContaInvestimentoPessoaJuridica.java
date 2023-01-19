package conta;

import interfaces.IConta;
import interfaces.IContaInvestimento;
import pessoa.PessoaJuridica;

public class ContaInvestimentoPessoaJuridica extends Conta implements IConta, IContaInvestimento {

    public ContaInvestimentoPessoaJuridica(int agencia, int numero, PessoaJuridica titular) {
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
