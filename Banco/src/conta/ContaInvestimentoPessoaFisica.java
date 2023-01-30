package conta;

import banco.Banco;
import interfaces.ICliente;
import interfaces.IConta;
import interfaces.IContaInvestimento;


public class ContaInvestimentoPessoaFisica extends Conta implements IContaInvestimento<IConta> {

    private String operacao = "2";

    public ContaInvestimentoPessoaFisica(int numero, ICliente titular) {
        super(numero, titular);
    }

    @Override
    public void depositar(double valor) {
        this.investir(valor);
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
        return false;
    }

    @Override
    public String getTipoConta() {
        return TipoConta.CONTA_INVESTIMENTO.getDescricao();
    }

    @Override
    public String getOperacao() {
        return this.operacao;
    }

    @Override
    public void investir(double valor) {
        super.saldo += (valor * 1.015);
    }
}