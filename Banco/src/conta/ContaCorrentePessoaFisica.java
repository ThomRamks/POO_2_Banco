package conta;

import banco.Banco;
import interfaces.IConta;
import pessoa.PessoaFisica;

public class ContaCorrentePessoaFisica extends Conta {

    Banco operacao = new Banco();

    public ContaCorrentePessoaFisica(int agencia, int numero, PessoaFisica titular) {
        super(agencia, numero, titular);
    }

    @Override
    public void depositar(double valor) {
        super.saldo += valor;
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
}
