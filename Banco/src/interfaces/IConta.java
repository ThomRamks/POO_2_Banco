
package interfaces;

import cliente.Cliente;
import conta.Conta;

public interface IConta {
    void depositar(double valor);
    boolean transferir(double valor, IConta conta);
    boolean sacar(double valor);
    double getSaldo();
    int getAgencia();
    int getNumero();
    Cliente getTitular();
    boolean validaSenha(String senha);
    String getTipoConta();

    int getOperacao();
}