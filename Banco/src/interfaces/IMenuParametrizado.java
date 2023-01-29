package interfaces;

public interface IMenuParametrizado<V, T>{
    void exibir(T t);
    void processarOpcao(V v, T t);
}
