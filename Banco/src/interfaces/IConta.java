package interfaces;

import conta.Conta;

public interface IConta {
    void depositar(double valor);
    boolean transferir(double valor, IConta conta);
    boolean sacar(double valor);
}
