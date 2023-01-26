package interfaces;

public interface IConta {
    ICliente getTitular();
    String getTipoConta();
    String getOperacao();
    int getAgencia();
    int getNumero();
    double getSaldo();
    void depositar(double valor);
    boolean transferir(double valor, IConta conta);
    boolean sacar(double valor);
}