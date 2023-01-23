package conta;

import banco.Banco;
import interfaces.IConta;
import cliente.ClientePessoaFisica;


public class ContaCorrentePessoaFisica extends Conta {

    public ContaCorrentePessoaFisica(int numero, String senha, ClientePessoaFisica titular) {
        super(numero, senha, titular);
    }

    @Override
    public void depositar(double valor) {
        System.out.println("Qual valor voce gostaria de depositar?");
        // valor = sc.nextDouble(); instanciar um scanner nessa classe??
        super.saldo += valor;
        System.out.println("Deposito de R$ " + valor + " realizado com sucesso!");
        System.out.println("Saldo atual: R$" + saldo);
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
        System.out.println("Qual valor voce gostaria de sacar?");
        // valor = sc.nextDouble(); instanciar um scanner nessa classe??
        if (super.saldo >= valor) {
            int saques = 0; // pensei em colocar um limite diario de saldo = 3
            super.saldo -= valor;
            System.out.println("Saque no valor de R$ " + valor + " realizado com sucesso!");
            System.out.println("Saldo atual: R$" + saldo);
            saques++;
                if (saques > 3) {
                System.out.println("Voce atingiu o limite maximo de 3 saques diarios, tente novamente amanha.");
                }
            return true;
        }
        System.out.println("Não foi possível realizar a operação. O valor é maior do que se encontra na conta.");
        return false;
    }
}
