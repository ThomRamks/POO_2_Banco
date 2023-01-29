package conta;

import banco.Banco;
import interfaces.ICliente;
import interfaces.IConta;

public class ContaPoupanca extends Conta {
    private String operacao = "3";

    public ContaPoupanca(int numero, ICliente titular) {
        super(numero, titular);
    }

    @Override
    public void depositar(double valor) {
        super.saldo += valor + (valor * 0.01);
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
        return TipoConta.CONTA_POUPANCA.getDescricao();
    }

    @Override
    public String getOperacao() {
        return this.operacao;
    }
}
