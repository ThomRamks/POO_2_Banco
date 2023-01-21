package conta;

import interfaces.IConta;
import banco.Banco;
import pessoa.PessoaJuridica;

public class ContaCorrentePessoaJuridica extends Conta  {

    Banco operacao = new Banco();

    public ContaCorrentePessoaJuridica(int agencia, int numero, PessoaJuridica titular) {
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
        double valorComTaxa = valor + (valor * 0.005);
        if (super.saldo >= valorComTaxa) {
            super.saldo -= valorComTaxa;
            return true;
        }
        return false;
    }
}
