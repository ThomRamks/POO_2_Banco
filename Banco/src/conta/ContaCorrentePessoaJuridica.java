package conta;

import interfaces.ICliente;
import interfaces.IConta;
import banco.Banco;

public class ContaCorrentePessoaJuridica extends Conta {

    private String operacao = "1";

    public ContaCorrentePessoaJuridica(int numero, ICliente titular) {
        super(numero, titular);
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
        double valorComTaxa = valor + (valor * 0.005);
        if (super.saldo >= valorComTaxa) {
            super.saldo -= valorComTaxa;
            return true;
        }
        return false;
    }

    @Override
    public String getTipoConta() {
        return TipoConta.CONTA_CORRENTE.getDescricao();
    }

    @Override
    public String getOperacao() {
        return this.operacao;
    }
}
