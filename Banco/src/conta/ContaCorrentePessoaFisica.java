package conta;

import banco.Banco;
import interfaces.ICliente;
import interfaces.IConta;

public class ContaCorrentePessoaFisica extends Conta {

    private String operacao = "1";

    public ContaCorrentePessoaFisica(int numero, ICliente titular) {
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
        if (super.saldo >= valor) {
            super.saldo -= valor;
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
