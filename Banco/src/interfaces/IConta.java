package interfaces;

import cliente.Cliente;
import conta.Conta;

public interface IConta {
    void depositar(double valor);
    boolean transferir(double valor, IConta conta);
    boolean sacar(double valor);
    int getAgencia();
    int getNumero();
    ICliente getTitular();
    String getTipoConta();
    String getOperacao();
}
