package interfaces;

public interface IMenuSubtipos<IConta, String, T>{

    void exibir(IConta contaOrigem, int contaDestino);
    void processarOpcao(T t, Integer numeroConta, IConta conta);

}
