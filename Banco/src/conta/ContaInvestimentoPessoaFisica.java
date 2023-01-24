package conta;


import banco.Banco;
import interfaces.IConta;
import interfaces.IContaInvestimento;
import cliente.ClientePessoaFisica;

public class ContaInvestimentoPessoaFisica extends Conta implements IContaInvestimento<IConta> {

    private int operacao = 2;

    public ContaInvestimentoPessoaFisica(String senha, ClientePessoaFisica titular) {
        super(senha, titular);
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
        System.out.println("Não é possível realizar a operação. O valor é maior do que se encontra na conta.");
        return false;
    }

    @Override
    public String getTipoConta() {
        return "Conta Investimento";
    }

    @Override
    public int getOperacao() {
        return this.operacao;
    }

    @Override
    public void investir(double valor) {
        super.saldo +=  (valor * 1.015);
    }
}