package conta;

import banco.Banco;
import interfaces.ICliente;
import interfaces.IConta;
import interfaces.IContaInvestimento;
import cliente.ClientePessoaJuridica;

public class ContaInvestimentoPessoaJuridica extends Conta implements IContaInvestimento<IConta> {

    private String operacao = "2";

    public ContaInvestimentoPessoaJuridica(int numero, ICliente titular) {
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
        double valorComTaxa = valor + (valor * 0.005);
        if (super.saldo >= valorComTaxa) {
            super.saldo -= valorComTaxa;
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
        super.saldo +=  (valor * 1.035);
    }

}
