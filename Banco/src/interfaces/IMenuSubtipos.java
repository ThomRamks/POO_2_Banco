package interfaces;

public interface IMenuSubtipos<IConta, String, T>{

    void exibir(IConta contaOrigem, int contaDestino);
    void processarOpcao(T t, Integer numeroConta, IConta conta);

    void processarOpcao(interfaces.IConta contaOrigem, int numeroContaDestino, String subtipoDesejado);

    void processarOpcao(interfaces.IConta contaOrigem, int numeroContaDestino, java.lang.String subtipoDesejado);
}
