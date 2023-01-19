package conta;

import interfaces.IConta;
import pessoa.PessoaFisica;

public class ContaPoupanca extends Conta implements IConta {

    private PessoaFisica titular;

    public ContaPoupanca(int agencia, int numero, PessoaFisica titular) {
        super(agencia, numero, titular);
    }


    @Override
    public void depositar(double valor) {
        super.saldo += valor + (valor * 0.01);
    }

    @Override
    public boolean transferir(double valor, IConta conta) {
        return false;
    }

    @Override
    public boolean sacar(double valor) {
        return false;
    }
}
